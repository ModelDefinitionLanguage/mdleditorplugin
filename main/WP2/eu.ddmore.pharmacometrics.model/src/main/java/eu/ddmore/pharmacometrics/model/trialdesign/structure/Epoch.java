package eu.ddmore.pharmacometrics.model.trialdesign.structure;


public class Epoch {

    private double start;
    private double end;
    
    public double getStart() {
        return start;
    }
    
    public void setStart(double start) {
        this.start = start;
    }
    
    public double getEnd() {
        return end;
    }
    
    public void setEnd(double end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("Epoch [start=%f, end=%f]", start, end);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(end);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(start);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        Epoch other = (Epoch) obj;
        if (Double.doubleToLongBits(end) != Double.doubleToLongBits(other.end)) {
            return false;
        }
        if (Double.doubleToLongBits(start) != Double.doubleToLongBits(other.start)) {
            return false;
        }
        return true;
    }
    
}
