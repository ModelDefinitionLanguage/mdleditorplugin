/*******************************************************************************
 * Copyright (C) 2014 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.pharmml2nmtran.loader;

import java.util.Map;
import java.util.TreeMap;

import au.com.bytecode.opencsv.CSVReader
import eu.ddmore.libpharmml.dom.dataset.DataSetType;
import eu.ddmore.libpharmml.dom.dataset.ImportDataType
import eu.ddmore.pharmacometrics.model.data.Column;
import eu.ddmore.pharmacometrics.model.data.DataSet
import eu.ddmore.pharmacometrics.model.data.ImportData
import eu.ddmore.pharmacometrics.model.data.Row


/**
 * Responsible for loading <DataSet> elements in main memory. 
 * It maps the JAXB 'DataSetType' type to the domain model 'DataSet' type.
 */
public class DataSetLoader {

    private static final String COMMA = "COMMA"
    private static final String CSV = "CSV"

//    public static final DataSet load(DataSetType type) {
//        Map<Integer, String> columnIndexToName = new TreeMap<Integer,String>();
//        type.definition.column.each { column ->
//            columnIndexToName[column.columnNum] = column.columnId
//        }
//        List<String> columnsAsList = new ArrayList<String>()
//        for (String value : columnIndexToName.values()) {
//            columnsAsList.add(value)
//        }
//        DataSet dataset = new DataSet(columnsAsList)
//        if (type.importData) {
//            loadExternalFile(type.importData, dataset)
//        } else {
//            loadDataRowsFromPharmML(type.table, dataset)
//        }
//        dataset
//    }

	public static final DataSet load(DataSetType type) {
		DataSet dataSet = new DataSet()
		
		type.definition.column.each { column ->
			dataSet.addColumn(new Column(column.columnId, column.columnType.value, column.valueType.value, column.columnNum))
		}


		if (type.importData) {
			dataSet.setImportData(new ImportData(type.importData.name, type.importData.url, type.importData.format, type.importData.delimiter.toString()))
		} else {
			type.table.row.each { tableRow ->
				Row row = new Row();
				tableRow.scalar.each { val ->
					row.add(val.value.value.toString())
				}
				dataSet.addRow(row)
			}
		}
//			loadDataRowsFromPharmML(type.table, dataSet)

		dataSet
	}

//    private static void loadExternalFile(ImportDataType importDataType, DataSet dataset) {
//        if (!importDataType.format.equalsIgnoreCase(CSV)) {
//            throw RuntimeException("Only CSV format is supported.")
//        }
//        
//        String fileName = '0.3/'+ importDataType.name +'.'+ importDataType.format                
//        File src = new File(Thread.currentThread().getContextClassLoader().getResource(fileName).getPath());
//
//        CSVReader csvReader = new CSVReader(new FileReader(src));
//        String[] row = null;
//        while((row = csvReader.readNext()) != null) {
//            DataRow dataRow = new DataRow()
//            if (row[0].startsWith('@')) {
//                continue;
//            }
//            for (int i=0; i<row.size(); i++) {
//                dataRow.add(row[i])
//            }
//            dataset.addRow(dataRow)
//        }
//        csvReader.close();
//    }
    
//    private static void loadDataRowsFromPharmML(table, DataSet dataset) {
//        table.row.each { row ->
//            DataRow dataRow = new DataRow()
//            row.scalar.each { col ->
//                dataRow.add(col.value.value.toString())
//            }
//            dataset.addRow(dataRow)
//        }
//    }
}