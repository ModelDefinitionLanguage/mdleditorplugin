package eu.ddmore.pharmacometrics.model.trialdesign;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class CategoricalAttribute extends PopulationAttribute {

    private final Map<String,Integer> categoriesToIndex;
    
    public CategoricalAttribute(String name, Set<String> categories) {
        super(name);
        categoriesToIndex = new HashMap<String,Integer>(categories.size());
        int i=1;
        for (String category : categories) {
            categoriesToIndex.put(category, i++);
        }
    }

    public Set<String> getCategories() {
        return categoriesToIndex.keySet();
    }

    public Integer getIndexOf(String category) {
        return categoriesToIndex.get(category);
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((categoriesToIndex == null) ? 0 : categoriesToIndex.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CategoricalAttribute other = (CategoricalAttribute) obj;
        if (categoriesToIndex == null) {
            if (other.categoriesToIndex != null) {
                return false;
            }
        } else if (!categoriesToIndex.equals(other.categoriesToIndex)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return String.format("CategoricalAttribute [name=%s, categoriesToIndex=%s]", name, categoriesToIndex.keySet());
    }

    
}
