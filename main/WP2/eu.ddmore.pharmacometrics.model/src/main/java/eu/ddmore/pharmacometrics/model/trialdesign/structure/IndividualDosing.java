package eu.ddmore.pharmacometrics.model.trialdesign.structure;

import java.util.Collection;

import com.google.common.base.Preconditions;

import eu.ddmore.pharmacometrics.model.data.DataSet;
import eu.ddmore.pharmacometrics.model.data.Row;


public class IndividualDosing implements Activity {
    private final DataSet dataSet;
    
    public IndividualDosing(DataSet dataset) {
        this.dataSet = dataset;
    }

    public DataSet getDataSet() {
		return dataSet;
	}

	public Collection<Row> getDosingInfoFor(String subjectId) {
        Preconditions.checkNotNull(subjectId);
        return dataSet.getRowsFor(subjectId);
    }

    @Override
    public String toString() {
        return "IndividualDosing [dataset=" + dataSet + "]";
    }

}
