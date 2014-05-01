package eu.ddmore.pharmacometrics.model.trialdesign.structure;

import java.util.List;


public class Cell {

    private Epoch epoch;
    private List<Arm> arms;
    private List<Segment> segments;
    
    public Cell(Epoch epoch, List<Arm> arms, List<Segment> segments) {
        this.epoch = epoch;
        this.arms = arms;
        this.segments = segments;
    }

    public Epoch getEpoch() {
        return epoch;
    }
    
    public List<Arm> getArms() {
        return arms;
    }
    
    public List<Segment> getSegments() {
        return segments;
    }
    
    public boolean appearsInArm(Arm a) {
        return arms.contains(a);
    }

    @Override
    public String toString() {
        return String.format("Cell [epoch=%s, arm=%s, segment=%s]", epoch, arms, segments);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((arms == null) ? 0 : arms.hashCode());
        result = prime * result + ((epoch == null) ? 0 : epoch.hashCode());
        result = prime * result + ((segments == null) ? 0 : segments.hashCode());
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
        Cell other = (Cell) obj;
        if (arms == null) {
            if (other.arms != null) {
                return false;
            }
        } else if (!arms.equals(other.arms)) {
            return false;
        }
        if (epoch == null) {
            if (other.epoch != null) {
                return false;
            }
        } else if (!epoch.equals(other.epoch)) {
            return false;
        }
        if (segments == null) {
            if (other.segments != null) {
                return false;
            }
        } else if (!segments.equals(other.segments)) {
            return false;
        }
        return true;
    }
    
}
