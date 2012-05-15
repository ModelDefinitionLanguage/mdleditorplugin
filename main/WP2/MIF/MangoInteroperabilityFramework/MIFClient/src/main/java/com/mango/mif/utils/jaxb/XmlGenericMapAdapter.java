package com.mango.mif.utils.jaxb;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * The Class XmlGenericMapAdapter for handling the JAXB stuff for Generic Map object.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public class XmlGenericMapAdapter<K, V> extends XmlAdapter<MapType<K, V>, Map<K, V>> {

  
    /** 
     * Unmashalls the map.
     */
    @Override
    public Map<K, V> unmarshal(MapType<K, V> v) throws Exception {
        HashMap<K, V> map = new HashMap<K, V>();

        for (MapEntryType<K, V> mapEntryType : v.getEntry()) {
            map.put(mapEntryType.getKey(), mapEntryType.getValue());
        }
        return map;
    }

    /** 
     * Mashalls the map.
     */
    @Override
    public MapType marshal(Map<K, V> v) throws Exception {
        MapType<K, V> mapType = new MapType<K, V>();

        for (Map.Entry<K, V> entry : v.entrySet()) {
            MapEntryType<K, V> mapEntryType = new MapEntryType<K, V>();
            mapEntryType.setKey(entry.getKey());
            mapEntryType.setValue(entry.getValue());
            mapType.getEntry().add(mapEntryType);
        }
        return mapType;
    }
}
