package com.mango.mif.utils.jaxb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The Class MapType.
 *
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class MapType<K, V> {

    /** The entry. */
    private List<MapEntryType<K, V>> entry = new ArrayList<MapEntryType<K, V>>();

    /**
     * Instantiates a new map type.
     */
    public MapType() {
    }

    /**
     * Instantiates a new map type.
     *
     * @param map the map
     */
    public MapType(Map<K, V> map) {
        for (Map.Entry<K, V> e : map.entrySet()) {
            entry.add(new MapEntryType<K, V>(e));
        }
    }

    /**
     * Gets the entry.
     *
     * @return the entry
     */
    public List<MapEntryType<K, V>> getEntry() {
        return entry;
    }

    /**
     * Sets the entry.
     *
     * @param entry the entry
     */
    public void setEntry(List<MapEntryType<K, V>> entry) {
        this.entry = entry;
    }
}
