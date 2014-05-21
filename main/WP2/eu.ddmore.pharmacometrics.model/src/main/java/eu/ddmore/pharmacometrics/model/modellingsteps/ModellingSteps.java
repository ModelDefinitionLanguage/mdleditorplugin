package eu.ddmore.pharmacometrics.model.modellingsteps;

import java.util.ArrayList;
import java.util.List;


public class ModellingSteps {

    private List<EstimationStep> estimationSteps = new ArrayList<EstimationStep>();
    private List<NonmemDataSet> nonmemDataSets = new ArrayList<NonmemDataSet>();
    
  
    public void add(EstimationStep s) {
        estimationSteps.add(s);
    }
    
    public List<EstimationStep> getEstimationSteps() {
        return estimationSteps;
    }

    public void add(NonmemDataSet s) {
    	nonmemDataSets.add(s);
    }
    
    public List<NonmemDataSet> getNonmemDataSets() {
        return nonmemDataSets;
    }

    @Override
    public String toString() {
        return "ModellingSteps [estimationSteps=" + estimationSteps + " nonmemDataSets=" + nonmemDataSets + "]";
    }
    
    
}
