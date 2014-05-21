package eu.ddmore.pharmacometrics.model.modellingsteps;

import java.util.HashMap;
import java.util.Map;

import eu.ddmore.pharmacometrics.model.data.DataSet;


public class NonmemDataSet {

    private final String name;
    private final Map<String, String> columnMappings = new HashMap<String, String>();

	private DataSet dataSet;
    
    public NonmemDataSet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(DataSet dataSet) {
    	this.dataSet = dataSet;
    }

    public Map<String, String> getColumnMappings() {
		return columnMappings;
	}

    @Override
    public String toString() {
    	return "NonmemDataSet [name=" + name + ", dataSet=" + dataSet + "]";
    }
}