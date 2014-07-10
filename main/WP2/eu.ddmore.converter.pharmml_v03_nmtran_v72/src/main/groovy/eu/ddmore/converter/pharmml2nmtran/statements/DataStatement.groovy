/*******************************************************************************
 * Copyright (C) 2014 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.pharmml2nmtran.statements

import java.util.Map;
import java.util.Set;

import com.google.common.collect.Multimap
import com.google.common.collect.TreeMultimap
import eu.ddmore.converter.pharmml2nmtran.loader.ModellingStepsLoader
import eu.ddmore.converter.pharmml2nmtran.loader.TrialDesignLoader
import eu.ddmore.converter.pharmml2nmtran.model.SubjectComparator
import eu.ddmore.converter.pharmml2nmtran.utils.TextFileWriter;
import eu.ddmore.libpharmml.dom.PharmML
import eu.ddmore.libpharmml.dom.commontypes.IdValueType
import eu.ddmore.libpharmml.dom.commontypes.RealValueType
import eu.ddmore.libpharmml.dom.dataset.DatasetRowType
import eu.ddmore.libpharmml.dom.modellingsteps.EstimationStepType
import eu.ddmore.libpharmml.dom.trialdesign.ActivityType
import eu.ddmore.libpharmml.dom.trialdesign.BolusType;
import eu.ddmore.libpharmml.dom.trialdesign.DosingTimesPointsType
import eu.ddmore.libpharmml.dom.trialdesign.DosingVariableType
import eu.ddmore.pharmacometrics.model.data.DataSet
import eu.ddmore.pharmacometrics.model.data.Row
import eu.ddmore.pharmacometrics.model.modellingsteps.ModellingSteps
import eu.ddmore.pharmacometrics.model.modellingsteps.ObjectiveDataSet
import eu.ddmore.pharmacometrics.model.trialdesign.Subject
import eu.ddmore.pharmacometrics.model.trialdesign.math.Expression
import eu.ddmore.pharmacometrics.model.trialdesign.structure.Activity;
import eu.ddmore.pharmacometrics.model.trialdesign.structure.Arm;
import eu.ddmore.pharmacometrics.model.trialdesign.structure.Bolus;
import eu.ddmore.pharmacometrics.model.trialdesign.structure.Cell
import eu.ddmore.pharmacometrics.model.trialdesign.structure.DosingTimes
import eu.ddmore.pharmacometrics.model.trialdesign.structure.IndividualDosing;
import eu.ddmore.pharmacometrics.model.trialdesign.structure.Structure


class DataStatement extends NMTranFormatter {

	private static final String DATA_FILE_NAME_SUFFIX = "_data.csv"

    private final PharmML pmlDOM
    private final String dataFileNamePathAndPrefix

    private Structure structure
    private ModellingSteps modellingSteps
    private File file
	private String fileName
    private TextFileWriter textWriter = new TextFileWriter();
    private List<String> headers

    public DataStatement(PharmML pmlDOM, String dataFileNamePathAndPrefix) {
        this.pmlDOM = pmlDOM
        this.dataFileNamePathAndPrefix = dataFileNamePathAndPrefix

        ModellingStepsLoader modellingStepsLoader = new ModellingStepsLoader(pmlDOM.modellingSteps)
        modellingStepsLoader.load()
        modellingSteps = modellingStepsLoader.modellingSteps

		ObjectiveDataSet ods = modellingSteps?.estimationSteps[0]?.objectiveDataSets[0]
		
		if (ods) {
			// use objective data set
			computeEstimationHeaders(ods.dataSet)
			fileName = getBaseFileName(dataFileNamePathAndPrefix) + DATA_FILE_NAME_SUFFIX
			composeData()
		} else {
			// use NONMEM data set
			DataSet dataSet = getNonmemDataSet()
			computeEstimationHeaders(dataSet)

			/*
			 * The use of the <url> property here is temporary until PharmML
			 * 0.3.1 is released.  From 0.3.1 it will be called <path>
			 */
			fileName = getBaseFileName(dataSet.importData.url)
		}
    }

	public String getStatement() {
		endline(" $fileName IGNORE=@")
	}

    public List<String> getHeaders() {
        headers
    }

	private getBaseFileName(fileNamePath) {
		new File(fileNamePath).getName()
	}

    private void computeEstimationHeaders(DataSet dataSet) {
        headers = new ArrayList<String>()
        List<String> dataColumns = dataSet?.columnNames ?: []
        for (String dataColumn : dataColumns) {
            headers.add(dataColumn.toUpperCase())
        }
    }
	
    private void computeDosingHeaders(List<String> dosingColumns) {
        headers.add('ARM')

        for (String dosingColumn : dosingColumns) {
			String columnName = ( dosingColumn == "DOSE" ? "AMT" : dosingColumn )
            if (!headers.contains(columnName)) {
				headers.add(columnName)
            }
        }
        headers.add('MDV')
        headers.add('EVID')
    }

    public void composeData() {
		file = new File(dataFileNamePathAndPrefix + DATA_FILE_NAME_SUFFIX)

		if (pmlDOM.trialDesign) {
			TrialDesignLoader trialDesignloader = new TrialDesignLoader("trialDesign":pmlDOM.trialDesign)
			trialDesignloader.load()
			structure = trialDesignloader.structure
		}

		for (Object epoch : structure.epochs) {
            List<Cell> cellsOfEpoch = structure.getCellsOfEpoch(epoch)
            for (Cell cell : cellsOfEpoch){
                cell.segments.each {
                    it.activities.each { activity ->
                        printArms(cell, activity)
                    }
                }
            }
        }
    }


    private void printArms(Cell cell, Activity activity) {
        cell.getArms().each {
            printArm(it, cell, activity)
        }
    }

    private void printArm(Arm arm, Cell cell, Bolus activity) {

        if (modellingSteps.estimationSteps) {
            printEstimationStepArm(arm, cell, activity)
        } else {
            printSimulationStepArm(arm, cell, activity)
        }
    }

    private void printEstimationStepArm(Arm arm, Cell cell, Bolus activity) {
        Expression doseAmount = activity.doseAmount
        DosingTimes dosingTimes = activity.dosingTimes

        DataSet dataSet = getObjectiveDataSet()
        TreeSet<Subject> orderedSubjects = orderSubjects(arm.subjects)

        List<String> subjectHeaders = getSubjectHeaders(orderedSubjects.first())
        printHeaders(subjectHeaders)

        orderedSubjects.each { subject ->
            Collection<Row> dataRows = dataSet.getRowsFor(subject.name)
            if (dataRows) {
                Set<Double> times = new TreeSet<Double>()
                TreeMultimap<Double, String> dosingLines = createDosingRowsForSubject(subject, activity, arm.index)
                times.addAll(dosingLines.keySet())
                TreeMultimap<Double, String> dataLines = createDataRowsForSubject(subject, dataRows, capitalize(dataSet.getColumnNames()), arm.index)
                times.addAll(dataLines.keySet())

                printRecordsInTimeOrder(times, dosingLines, dataLines)
            }
        }
    }

    private List<String> getSubjectHeaders(Subject subject) {
        List<String> columns = new ArrayList<String>()
        columns.addAll(subject.getAttributeToValue().keySet())
        columns.add('AMT')
        return columns
    }

    private void printSimulationStepArm(Arm arm, Cell cell, Bolus activity) {
        Expression doseAmount = activity.doseAmount
        DosingTimes dosingTimes = activity.dosingTimes

        DataSet dataSet = getObjectiveDataSet()
        TreeSet<Subject> orderedSubjects = orderSubjects(arm.subjects)
        List<String> subjectHeaders = getSubjectHeaders(orderedSubjects.first())
		//computeHeaders(orderedSubjects.first()) 
        
        orderedSubjects.each { subject ->
            Collection<Row> dataRows = dataSet.getRowsFor(subject.name)
            if (dataRows) {
                Set<Double> times = new TreeSet<Double>()
                TreeMultimap<Double, String> dosingLines = createDosingRowsForSubject(subject, activity, arm.index)
                times.addAll(dosingLines.keySet())
                TreeMultimap<Double, String> dataLines = createDataRowsForSubject(subject, dataRows, capitalize(dataSet.getColumnNames()), arm.index)
                times.addAll(dataLines.keySet())

                printRecordsInTimeOrder(times, dosingLines, dataLines)
            }
        }
    }

    private List<String> capitalize(List<String> input) {
        List<String> result = new ArrayList<String>()
        for (String s : input) {
            result.add(s.toUpperCase())
        }
        result
    }

    private void printArm(Arm arm, Cell cell, IndividualDosing activity) {
        //TODO: Don't know what to do if the above have more than 1 elements
        if (modellingSteps.estimationSteps.size()>1) {
            throw new RuntimeException("Multiple EstimationSteps are not yet supported.")
        }
        if (modellingSteps.estimationSteps[0].objectiveDataSets.size()>1) {
            throw new RuntimeException("Multiple ObjectiveDataSets inside EstimationSteps are not yet supported.")
        }
        
        DataSet dataSet = getObjectiveDataSet()

        TreeSet<Subject> orderedSubjects = orderSubjects(arm.subjects)

        printHeaders(activity.dataSet.getColumnNames())
        
        orderedSubjects.each { subject ->
            //If data is given in external files, then individuals are represented by order ids. e.g. '1'
            Collection<Row> dataRows = dataSet.getRowsFor(''+ subject.id)
            if (!dataRows) {
                //If data is given in PharmML, then individuals are represented by the their PharmML name, e.g. 'i1'
                dataRows = dataSet.getRowsFor(subject.name)
            }
            if (dataRows) {
                Collection<Row> dosingRows = activity.getDosingInfoFor(''+ subject.id)
                if (!dosingRows) {
                    dosingRows = activity.getDosingInfoFor(subject.name)
                }

                Set<Double> times = new TreeSet<Double>()
                TreeMultimap<Double, String> dosingLines = createDosingRowsForSubject(subject, dosingRows, activity.dataSet.getColumnNames(), arm.index)
                times.addAll(dosingLines.keySet())
                TreeMultimap<Double, String> dataLines = createDataRowsForSubject(subject, dataRows, dataSet.getColumnNames(), arm.index)
                times.addAll(dataLines.keySet())

                printRecordsInTimeOrder(times, dosingLines, dataLines)
            }
        }
    }

    private void printHeaders(List<String> dosingColumns) {
        computeDosingHeaders(dosingColumns)

        //Sets the header of the csv. Starts with '@' to omit the first line.
        textWriter.writeToFile(file, '@' + headers.join(','))
    }

    private Set<Subject> orderSubjects(Set<Subject> subjects) {
        Set<Subject> orderedSubjects = new TreeSet<Subject>(new SubjectComparator())
        orderedSubjects.addAll(subjects)
        orderedSubjects
    }

    private void printRecordsInTimeOrder(Set<Double> times, Multimap<Double, String> dosingLines, Multimap<Double, String> dataLines) {
        for (Double time : times) {
            Collection<String> dosingLinesForTime = dosingLines.get(time)
            Collection<String> dataLinesForTime = dataLines.get(time)

            for (String dosingLineForTime : dosingLinesForTime) {
                textWriter.appendToFile(file, endline() + dosingLineForTime)
            }
            for (String dataLineForTime : dataLinesForTime) {
                textWriter.appendToFile(file, endline() + dataLineForTime)
            }
        }
    }

    private TreeMultimap<Double, String> createDataRowsForSubject(Subject subject, Collection<Row> dataRows, List<String> columns, int armIndex) {
        TreeMultimap<Double, String> timeToLine = TreeMultimap.create()
        for (Row dataRow : dataRows) {
            def dataLine = new StringBuilder()
            dataLine << "${subject.id}"
            for (int i=1; i<dataRow.data.size(); i++ ) {
                dataLine << ", ${dataRow.data.get(i)}"
            }
            dataLine << ", ${armIndex}"
            for (String value: subject.getAttributeToValue().values()) {
                dataLine << ", ${value}"
            }
            dataLine << ", ., ., 0"

            def timeDatum = dataRow.asMap(columns).get('TIME')
            Double time = Double.valueOf(timeDatum)
            timeToLine.put(time, dataLine.toString())
        }
        timeToLine
    }

    private TreeMultimap<Double, String> createDosingRowsForSubject(Subject subject, Bolus activity, int armIndex) {
        Expression doseAmount = activity.doseAmount
        DosingTimes dosingTimes = activity.dosingTimes

        TreeMultimap<Double, String> timeToLine = TreeMultimap.create()

        for (Integer dosingTime : dosingTimes.getTimes()) {
            def dataLine = new StringBuilder(subject.id)
            dataLine << "${subject.id}, ${dosingTime}, ., ${armIndex}"
            for (String value: subject.getAttributeToValue().values()) {
                dataLine << ", ${value}"
            }
            dataLine << ", ${doseAmount.evaluate()}, 1, 1"

            Double time = Double.valueOf(dosingTime)
            timeToLine.put(time, dataLine.toString())
        }

        timeToLine
    }

    private TreeMultimap<Double, String> createDosingRowsForSubject(Subject subject, Collection<Row> dosingRows, List<String> columns, int armIndex) {
        TreeMultimap<Double, String> timeToLine = TreeMultimap.create()
        for (Row dataRow : dosingRows) {
            def dataLine = new StringBuilder(subject.id)
            def dosingMap = dataRow.asMap(columns)
            String doseColumnName = columns.get(columns.size()-1)
            dataLine << "${subject.id}, ${dosingMap.get('TIME')}, ., ${armIndex}, ${dosingMap.get(doseColumnName)}, 1, 1"

            Double time = Double.valueOf(dataRow.asMap(columns).get('TIME'))
            timeToLine.put(time, dataLine.toString())
        }
        timeToLine
    }

	private getObjectiveDataSet() {
		modellingSteps.estimationSteps[0].objectiveDataSets[0].dataSet
	}
	
	private getNonmemDataSet() {
		modellingSteps.nonmemDataSets[0].dataSet
	}
}