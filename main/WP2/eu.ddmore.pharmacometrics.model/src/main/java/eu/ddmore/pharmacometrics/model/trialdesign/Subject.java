package eu.ddmore.pharmacometrics.model.trialdesign;

import java.util.LinkedHashMap;
import java.util.Map;


public class Subject {
    private final int id;
    private final String name;
    private Map<String, String> attributeToValue;

    public Subject(String name, int id) {
        attributeToValue = new LinkedHashMap<String,String>();
        this.name = name;
        this.id = id;
    }
    
    public void addAllAttributeValues(Map<String, String> toAdd) {
        attributeToValue.putAll(toAdd);
    }
    
    public void addAttributeValue(String att, String value) {
        attributeToValue.put(att, value);
    }
    
    public String getValueOf(String att) {
        return attributeToValue.get(att);
    }
    
    public Map<String, String> getAttributeToValue() {
        return attributeToValue;
    }
    
    public void setAttributeToValue(Map<String, String> attributeToValue) {
        this.attributeToValue = attributeToValue;
    }

    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Subject [id=%d, name=%s]", id, name);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Subject other = (Subject) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }
    
}
