package eu.ddmore.pharmacometrics.model.trialdesign.population;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;


public class DataSet {

    private List<String> columns;
    private Multimap<String, DataRow> idToDataRow;
    
    public DataSet(List<String> columns) {
        idToDataRow = HashMultimap.create();
        this.columns = columns;
    }
        
    public void addRow(DataRow r) {
        idToDataRow.put(r.getDatumAt(0), r);
    }
    
    public Collection<DataRow> getRows() {
        return idToDataRow.values();
    }

    public Collection<DataRow> getRowsFor(String id) {
        return idToDataRow.get(id);
    }
    
    public List<String> getColumns() {
        return columns;
    }
    
    public String getColumnAt(int index) {
        return columns.get(index);
    }

    @Override
    public String toString() {
        return "Dataset [columns=" + columns + " idToDataRow=" + idToDataRow + "]";
    }
    
}
