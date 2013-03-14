package com.mango.mif.utils.jaxb;

import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * The Class MapEntryType.
 *
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class MapEntryType<K, V> {

    /** The key. */
    private K key;
    
    /** The value. */
    private V value;

    public MapEntryType() {
    }

    public MapEntryType(Map.Entry<K, V> e) {
        key = e.getKey();
        value = e.getValue();
    }

    @XmlElement
    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    @XmlElement
    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
