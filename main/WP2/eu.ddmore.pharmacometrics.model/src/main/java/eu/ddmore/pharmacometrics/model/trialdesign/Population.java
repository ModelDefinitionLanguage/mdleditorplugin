package eu.ddmore.pharmacometrics.model.trialdesign;

import java.util.List;
import java.util.Map;


public class Population {
    
    public enum CommonAttributes {
        WEIGHT("WEIGHT"), AGE("AGE"), GENDER("GENDER");
        private String attName;
        
        private CommonAttributes(String s) {
            attName = s;
        }
        public String getAttName() {
            return attName;
        }
    };
    
    private Map<String,PopulationAttribute> attributes;
    private List<Subject> subjects;
    
    
    //TODO: determine the type
    private Object variability;
    
    private String label;

    
    public PopulationAttribute getAttribute(String attName) {
        return attributes.get(attName);
    }
    
    public void setAttribute(String key, PopulationAttribute value) {
        attributes.put(key, value);
    }
    
    public ContinuousAttribute getWeight() {
        return (ContinuousAttribute)attributes.get(CommonAttributes.WEIGHT.attName);
    }
    
    public ContinuousAttribute getAge() {
        return (ContinuousAttribute)attributes.get(CommonAttributes.AGE.attName);
    }
    
    public CategoricalAttribute getGender() {
        return (CategoricalAttribute)attributes.get(CommonAttributes.GENDER.attName);
    }
    
    public void setWeight(ContinuousAttribute value) {
        attributes.put(CommonAttributes.WEIGHT.attName, value);
    }
    
    public void setAge(ContinuousAttribute value) {
        attributes.put(CommonAttributes.AGE.attName, value);
    }
    
    public void setGender(ContinuousAttribute value) {
        attributes.put(CommonAttributes.GENDER.attName, value);
    }

    public String getLabel() {
        return label;
    }
    
    public void setLabel(String label) {
        this.label = label;
    }
    
    public void addSubject(Subject s) {
        subjects.add(s);
    }
    
}
