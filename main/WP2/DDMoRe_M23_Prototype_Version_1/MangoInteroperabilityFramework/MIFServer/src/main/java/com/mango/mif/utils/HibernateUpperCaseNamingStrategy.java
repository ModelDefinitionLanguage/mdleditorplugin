package com.mango.mif.utils;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.hibernate.AssertionFailure;
import org.hibernate.annotations.common.util.StringHelper;
import org.hibernate.cfg.NamingStrategy;

/**
 * Hibernate upper case naming strategy
 */
public class HibernateUpperCaseNamingStrategy implements NamingStrategy, Serializable {

    /**
     * A convenient singleton instance
     */
    public static final NamingStrategy INSTANCE = new HibernateUpperCaseNamingStrategy();

    /**
     * Return the unqualified class name, mixed case converted to
     * underscores
     */
    public String classToTableName(String className) {
        return convertToUpperCase(className);
    }
    /**
     * Return the full property path with underscore seperators, mixed
     * case converted to underscores
     */
    public String propertyToColumnName(String propertyName) {
        return convertToUpperCase(propertyName);
    }
    /**
     * Convert mixed case to underscores
     */
    public String tableName(String tableName) {
        return convertToUpperCase(tableName);
    }
    /**
     * Convert mixed case to underscores
     */
    public String columnName(String columnName) {
        return convertToUpperCase(columnName);
    }   

    public String collectionTableName(
            String ownerEntity, String ownerEntityTable, String associatedEntity, String associatedEntityTable,
            String propertyName
    ) {
        return StringHelper.unqualify(convertToUpperCase(propertyName));
    }

    /**
     * Return the argument
     */
    public String joinKeyColumnName(String joinedColumn, String joinedTable) {
        return columnName( joinedColumn );
    }

    /**
     * Return the property name or propertyTableName
     */
    public String foreignKeyColumnName(
            String propertyName, String propertyEntityName, String propertyTableName, String referencedColumnName
    ) {
        String header = propertyName != null ? propertyName : propertyTableName;
        if (header == null) throw new AssertionFailure("NamingStrategy not properly filled");
        header = convertToUpperCase(header);
        return columnName( header ); 
    }

    /**
     * Return the column name or the unqualified property name
     */
    public String logicalColumnName(String columnName, String propertyName) {
        String name = StringHelper.isNotEmpty( columnName ) ? columnName : propertyName;
        return convertToUpperCase(name);
    }

    /**
     * Returns either the table name if explicit or
     * if there is an associated table, the concatenation of owner entity table and associated table
     * otherwise the concatenation of owner entity table and the unqualified property name
     */
    public String logicalCollectionTableName(String tableName,
                                             String ownerEntityTable, String associatedEntityTable, String propertyName) {
        
        if ( tableName != null ) {
            return convertToUpperCase(tableName);
        }
        else {
            //use of a stringbuffer to workaround a JDK bug
            String assocEntityTabName = associatedEntityTable != null ? associatedEntityTable : StringHelper.unqualify( propertyName);
            StringBuffer name = new StringBuffer(ownerEntityTable);
            name.append("_");
            name.append(assocEntityTabName);
            return convertToUpperCase(name.toString());
        }
            
    }
    
    /**
     * Return the column name if explicit or the concatenation of the property name and the referenced column
     */
    public String logicalCollectionColumnName(String columnName, String propertyName, String referencedColumn) {
        
        return StringHelper.isNotEmpty( columnName ) ?
                columnName :
                StringUtils.upperCase( propertyName ) + referencedColumn;
    }
    
    protected static String convertToUpperCase(String name) {
        return StringUtils.upperCase(name);
    }
}