package eu.ddmore.pharmacometrics.model.trialdesign.structure;

import java.util.HashSet;
import java.util.Set;

//It is typically used for Treatment in pharmML.
public class Segment {

    private Set<Activity> activities;
        
    public Segment() {
        activities = new HashSet<Activity>();
    }

    public void addActivity(Activity a) {
        activities.add(a);
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    @Override
    public String toString() {
        return String.format("Segment [activities=%s]", activities);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((activities == null) ? 0 : activities.hashCode());
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
        Segment other = (Segment) obj;
        if (activities == null) {
            if (other.activities != null) {
                return false;
            }
        } else if (!activities.equals(other.activities)) {
            return false;
        }
        return true;
    }
    
}
