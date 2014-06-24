package eu.ddmore.converter.mdl2json.domain.data;

import java.math.BigInteger;


public interface Subject {
	String getID();
//	Covariates getCovariates();
	BigInteger getDosingRecords();
	BigInteger getObservations();	
}
