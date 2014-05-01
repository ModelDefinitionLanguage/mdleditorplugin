package eu.ddmore.pharmacometrics.model.trialdesign.structure;

import java.util.HashSet;
import java.util.Set;

import eu.ddmore.pharmacometrics.model.trialdesign.Subject;


public class Arm {

    private Set<Subject> subjects;
    private final int index;
    private final String name;

    public Arm(String name, int index) {
        this.name = name;
        subjects = new HashSet<Subject>();
        this.index = index;
    }
    
    public boolean add(Subject s) {
        return subjects.add(s);
    }
    
    public int getIndex() {
        return index;
    }
    
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Arm [name=%s, subjects=%s", name, subjects);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((subjects == null) ? 0 : subjects.hashCode());
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
        Arm other = (Arm) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (subjects == null) {
            if (other.subjects != null) {
                return false;
            }
        } else if (!subjects.equals(other.subjects)) {
            return false;
        }
        return true;
    }

}
