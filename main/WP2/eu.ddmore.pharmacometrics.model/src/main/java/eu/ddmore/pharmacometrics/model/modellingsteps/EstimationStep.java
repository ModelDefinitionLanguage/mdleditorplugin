package eu.ddmore.pharmacometrics.model.modellingsteps;

import java.util.ArrayList;
import java.util.List;


public class EstimationStep {

    private final String name;
    private final List<ObjectiveDataSet> objectiveDataSets;
    
    public EstimationStep(String name) {
        this.name = name;
        objectiveDataSets = new ArrayList<ObjectiveDataSet>();
    }

    public void add(ObjectiveDataSet s) {
        objectiveDataSets.add(s);
    }
    
    public String getName() {
        return name;
    }

    public List<ObjectiveDataSet> getObjectiveDataSets() {
        return objectiveDataSets;
    }

    @Override
    public String toString() {
        return "EstimationStep [name=" + name + ", objectiveDataSets=" + objectiveDataSets + "]";
    }
}
