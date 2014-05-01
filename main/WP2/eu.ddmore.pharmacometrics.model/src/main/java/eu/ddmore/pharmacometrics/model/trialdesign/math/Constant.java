package eu.ddmore.pharmacometrics.model.trialdesign.math;


public class Constant implements Expression {

    private final double value;
    
    public Constant(double value) {
        this.value = value;
    }
    
    @Override
    public double evaluate() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("Constant [value=%f]", value);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(value);
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
        Constant other = (Constant) obj;
        if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value)) {
            return false;
        }
        return true;
    }
    
}
