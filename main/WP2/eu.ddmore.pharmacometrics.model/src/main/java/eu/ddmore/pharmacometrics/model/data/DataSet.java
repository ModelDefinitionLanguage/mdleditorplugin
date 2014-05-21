package eu.ddmore.pharmacometrics.model.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class DataSet {

	private List<Column> definition = new ArrayList<Column>();
	private List<Row> table = new ArrayList<Row>();
	private ImportData importData;

    private Multimap<String, Row> idToDataRow = HashMultimap.create();
	private List<String> columnNames = new ArrayList<String>();	

    public List<String> getColumnNames() {
		return columnNames;
	}

	public void addColumn(Column col) {
        definition.add(col);
        columnNames.add(col.getColumnId());
    }

    public List<Column> getColumns() {
        return definition;
    }

    public void addRow(Row row) {
        table.add(row);
        idToDataRow.put(row.getDatumAt(0), row);
    }

    public List<Row> getRows() {
        return table;
    }

    public Collection<Row> getRowsFor(String subjectId) {
        return idToDataRow.get(subjectId);
    }
    
    public void setImportData(ImportData  importData) {
        this.importData = importData;
    }

    public ImportData getImportData() {
        return importData;
    }

    @Override
    public String toString() {
        return "Dataset [definition=" + definition + " table=" + table + " importData=" + importData + "]";
    }
}