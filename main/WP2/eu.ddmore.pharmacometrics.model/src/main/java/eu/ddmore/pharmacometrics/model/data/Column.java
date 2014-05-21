package eu.ddmore.pharmacometrics.model.data;

import java.math.BigInteger;

public class Column {

	private final String columnId;
	private final String columnType;
	private final String valueType;
	private final BigInteger columnNum;
	
	public Column(String columnId, String columnType, String valueType,	BigInteger columnNum) {
		this.columnId = columnId;
		this.columnType = columnType;
		this.valueType = valueType;
		this.columnNum = columnNum;
	}

	public String getColumnId() {
		return columnId;
	}

	public String getColumnType() {
		return columnType;
	}

	public String getValueType() {
		return valueType;
	}

	public BigInteger getColumnNum() {
		return columnNum;
	}
	
	@Override
	public String toString() {
		return "Column [columnId=" + columnId + ", columnType=" + columnType
				+ ", valueType=" + valueType + ", columnNum=" + columnNum + "]";
	}
}