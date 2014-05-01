package eu.ddmore.pharmacometrics.model.trialdesign.structure;

import eu.ddmore.pharmacometrics.model.trialdesign.math.Expression;


public class Bolus implements Activity {

    private boolean isAssignedToODE=false;
    private Expression doseAmount;
    private DosingTimes dosingTimes;
    
    public Expression getDoseAmount() {
        return doseAmount;
    }

    public DosingTimes getDosingTimes() {
        return dosingTimes;
    }
    
    public Bolus(Expression doseAmount, DosingTimes dosingTimes, boolean isAssignedToODE) {
        this.doseAmount = doseAmount;
        this.dosingTimes = dosingTimes;
        this.isAssignedToODE = isAssignedToODE;
    }

    public boolean isAssignedToODE() {
        return isAssignedToODE;
    }

    @Override
    public String toString() {
        return String.format("Bolus [doseAmount=%s, dosingTimes=%s, isAssignedToODE=%s]", getDoseAmount(), getDosingTimes(), isAssignedToODE);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((doseAmount == null) ? 0 : doseAmount.hashCode());
        result = prime * result + ((dosingTimes == null) ? 0 : dosingTimes.hashCode());
        result = prime * result + (isAssignedToODE ? 1231 : 1237);
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
        Bolus other = (Bolus) obj;
        if (doseAmount == null) {
            if (other.doseAmount != null) {
                return false;
            }
        } else if (!doseAmount.equals(other.doseAmount)) {
            return false;
        }
        if (dosingTimes == null) {
            if (other.dosingTimes != null) {
                return false;
            }
        } else if (!dosingTimes.equals(other.dosingTimes)) {
            return false;
        }
        if (isAssignedToODE != other.isAssignedToODE) {
            return false;
        }
        return true;
    }


}
