/*******************************************************************************
 * Copyright (C) 2014 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.pharmml2nmtran.loader

import eu.ddmore.libpharmml.dom.modellingsteps.DatasetMappingType
import eu.ddmore.libpharmml.dom.modellingsteps.EstimationStepType
import eu.ddmore.libpharmml.dom.modellingsteps.IndividualMappingType
import eu.ddmore.libpharmml.dom.modellingsteps.MappingType
import eu.ddmore.libpharmml.dom.modellingsteps.ModellingStepsType
import eu.ddmore.libpharmml.dom.modellingsteps.NONMEMdataSetType
import eu.ddmore.libpharmml.dom.modellingsteps.VariableMappingType
import eu.ddmore.pharmacometrics.model.data.DataSet
import eu.ddmore.pharmacometrics.model.modellingsteps.EstimationStep
import eu.ddmore.pharmacometrics.model.modellingsteps.ModellingSteps
import eu.ddmore.pharmacometrics.model.modellingsteps.NonmemDataSet
import eu.ddmore.pharmacometrics.model.modellingsteps.ObjectiveDataSet


/**
 * Responsible for loading <ModellingSteps> elements in main memory.
 * It maps the JAXB 'ModellingStepsType' type to the domain model 'ModellingSteps' type.
 */
class ModellingStepsLoader {

    private final ModellingStepsType modellingStepsType
    private ModellingSteps modellingSteps = new ModellingSteps()

    public ModellingStepsLoader(ModellingStepsType modellingStepsType) {
        this.modellingStepsType = modellingStepsType
    }

    public void load() {
        modellingStepsType.commonModellingStep.each { step ->
            if (step.value instanceof EstimationStepType) {
                EstimationStep estimationStep = new EstimationStep(step.value.oid);
                step.value.objectiveDataSet.each { DatasetMappingType objDataset ->
                    DataSet dataSet = DataSetLoader.load(objDataset.dataSet)
                    ObjectiveDataSet objectiveDataSet = new ObjectiveDataSet(dataSet)
                    loadMappings(objectiveDataSet, objDataset)
                    estimationStep.add(objectiveDataSet)
                }
                modellingSteps.add(estimationStep)
            } else {
                //TODO: load a simulation step here.
                throw new RuntimeException("Simulations are not yet supported.")
            }
        }

		modellingStepsType.nonmeMdataSet.each { set ->
			if (set instanceof NONMEMdataSetType) {
				NonmemDataSet nonmemDataSet = new NonmemDataSet(set.oid);
				DataSet dataSet = DataSetLoader.load(set.dataSet)
				nonmemDataSet.dataSet = dataSet
				modellingSteps.add(nonmemDataSet)
			} else {
				//TODO: can there be any other types?
				throw new RuntimeException("Only NONMEMdataSetTypes are supported.")
			}
		}
    }

    public void loadMappings(ObjectiveDataSet objectiveDataSet, DatasetMappingType objDataset) {
        objDataset.mapping.each { mapping ->
            if (mapping.value instanceof IndividualMappingType) {
                objectiveDataSet.addIndividualMapping(mapping.value.columnRef.columnIdRef)
            } else if (mapping.value instanceof VariableMappingType) {
                objectiveDataSet.addVariableMapping(mapping.value.columnRef.columnIdRef, mapping.value.symbRef.symbIdRef)
            }
        }
    }

    public String toString() {
        modellingSteps
    }
}
