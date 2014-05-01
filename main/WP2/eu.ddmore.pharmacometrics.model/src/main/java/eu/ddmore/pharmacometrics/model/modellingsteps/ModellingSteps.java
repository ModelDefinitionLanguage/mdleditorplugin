package eu.ddmore.pharmacometrics.model.modellingsteps;

import java.util.ArrayList;
import java.util.List;


public class ModellingSteps {

    private List<EstimationStep> estimationSteps;
    
    public ModellingSteps() {
        estimationSteps = new ArrayList<EstimationStep>();
    }
    
    public void add(EstimationStep s) {
        estimationSteps.add(s);
    }
    
    public List<EstimationStep> getEstimationSteps() {
        return estimationSteps;
    }

    @Override
    public String toString() {
        return "ModellingSteps [estimationSteps=" + estimationSteps + "]";
    }
    
    
}
