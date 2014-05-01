/*******************************************************************************
 * Copyright (C) 2014 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.pharmml2nmtran.loader

import eu.ddmore.libpharmml.dom.modellingsteps.DatasetMappingType
import eu.ddmore.libpharmml.dom.modellingsteps.EstimationStepType
import eu.ddmore.libpharmml.dom.modellingsteps.IndividualMappingType
import eu.ddmore.libpharmml.dom.modellingsteps.MappingType
import eu.ddmore.libpharmml.dom.modellingsteps.ModellingStepsType
import eu.ddmore.libpharmml.dom.modellingsteps.VariableMappingType
import eu.ddmore.pharmacometrics.model.modellingsteps.EstimationStep
import eu.ddmore.pharmacometrics.model.modellingsteps.ModellingSteps
import eu.ddmore.pharmacometrics.model.modellingsteps.ObjectiveDataSet
import eu.ddmore.pharmacometrics.model.trialdesign.population.DataSet

/**
 * Responsible for loading <ModellingSteps> elements in main memory.
 * It maps the JAXB 'ModellingStepsType' type to the domain model 'ModellingSteps' type.
 */
class ModellingStepsLoader {

    private final ModellingStepsType modellingStepsType
    private ModellingSteps modellingSteps

    public ModellingStepsLoader(ModellingStepsType modellingStepsType) {
        this.modellingStepsType = modellingStepsType
        modellingSteps = new ModellingSteps()
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
