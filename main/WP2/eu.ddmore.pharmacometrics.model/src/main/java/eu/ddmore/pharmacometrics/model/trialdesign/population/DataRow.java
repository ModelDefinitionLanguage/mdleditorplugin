package eu.ddmore.pharmacometrics.model.trialdesign.population;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DataRow {

    private List<String> data;
    
    public DataRow() {
        data = new ArrayList<String>();
    }
    
    public void add(String d) {
        data.add(d);
    }
    
    public List<String> getData() {
        return data;
    }
    
    public String getDatumAt(int index) {
        return data.get(index);
    }

    public Map<String, String> asMap(List<String> columns) {
        Map<String, String> m = new HashMap<String, String>();
        int i=0;
        for (String column : columns) {
            m.put(column, getDatumAt(i++));
        }
        return m;
    }
    
    @Override
    public String toString() {
        return "DataRow [data=" + data + "]";
    }
    
}
