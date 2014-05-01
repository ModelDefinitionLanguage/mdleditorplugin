package eu.ddmore.pharmacometrics.model.modellingsteps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.ddmore.pharmacometrics.model.trialdesign.population.DataSet;


public class ObjectiveDataSet {

    private final List<String> individualMappings;
    private final Map<String, String> variableMappings;
    private final DataSet dataSet;
    
    public ObjectiveDataSet(DataSet dataSet) {
        this.dataSet = dataSet;
        individualMappings = new ArrayList<String>();
        variableMappings = new HashMap<String, String>();
    }
    
    public DataSet getDataSet() {
        return dataSet;
    }
    
    public List<String> getIndividualMappings() {
        return individualMappings;
    }

    public Map<String, String> getVariableMappings() {
        return variableMappings;
    }

    
    public String getVariableMapping(String name) {
        return variableMappings.get(name);
    }
    
    public void addIndividualMapping(String name) {
        individualMappings.add(name);
    }
    
    public void addVariableMapping(String key, String value) {
        variableMappings.put(key, value);
    }

    @Override
    public String toString() {
        return "ObjectiveDataSet [individualMappings=" + individualMappings + ", variableMappings=" + variableMappings + ", dataSet="
            + dataSet + "]";
    }
    
    
}
