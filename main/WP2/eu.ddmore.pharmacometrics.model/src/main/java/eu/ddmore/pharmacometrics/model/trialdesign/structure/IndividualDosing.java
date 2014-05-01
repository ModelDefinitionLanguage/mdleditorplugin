package eu.ddmore.pharmacometrics.model.trialdesign.structure;

import java.util.Collection;

import com.google.common.base.Preconditions;

import eu.ddmore.pharmacometrics.model.trialdesign.population.DataRow;
import eu.ddmore.pharmacometrics.model.trialdesign.population.DataSet;


public class IndividualDosing implements Activity {
    private final DataSet dataset;
    
    public IndividualDosing(DataSet dataset) {
        this.dataset = dataset;
    }

    public Collection<DataRow> getDosingInfoFor(String subjectId) {
        Preconditions.checkNotNull(subjectId);
        return dataset.getRowsFor(subjectId);
    }

    @Override
    public String toString() {
        return "IndividualDosing [dataset=" + dataset + "]";
    }

}
