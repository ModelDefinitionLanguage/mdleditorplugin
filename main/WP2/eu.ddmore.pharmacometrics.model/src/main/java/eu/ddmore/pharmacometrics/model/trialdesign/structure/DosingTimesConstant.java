package eu.ddmore.pharmacometrics.model.trialdesign.structure;

import java.util.ArrayList;
import java.util.List;


public class DosingTimesConstant implements DosingTimes {

    private int time;
    private String name;
    
    public DosingTimesConstant(int time) {
        this.time = time;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    @Override
    public List<Integer> getTimes() {
        List<Integer> times = new ArrayList<Integer>();
        times.add(time);
        return times;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + time;
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
        DosingTimesConstant other = (DosingTimesConstant) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (time != other.time) {
            return false;
        }
        return true;
    }
    
}
