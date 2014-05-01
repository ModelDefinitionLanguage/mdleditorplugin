package eu.ddmore.pharmacometrics.model.trialdesign.structure;

import java.util.ArrayList;
import java.util.List;


public class DosingTimesSequence implements DosingTimes {

    private int begin;
    private int step;
    private int end;
    
    public DosingTimesSequence(int begin, int step, int end) {
        this.begin = begin;
        this.step = step;
        this.end = end;
    }
    
    @Override
    public List<Integer> getTimes() {
        List<Integer> times = new ArrayList<Integer>( (end-begin)/step );
        for (int i=begin; i<=end; i+=step) {
            times.add(i);
        }
        return times;
    }

    @Override
    public String toString() {
        return String.format("DosingTimesSequence [begin=%d, step=%d, end=%d]", begin, step, end);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + begin;
        result = prime * result + end;
        result = prime * result + step;
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
        DosingTimesSequence other = (DosingTimesSequence) obj;
        if (begin != other.begin) {
            return false;
        }
        if (end != other.end) {
            return false;
        }
        if (step != other.step) {
            return false;
        }
        return true;
    }
    
}
