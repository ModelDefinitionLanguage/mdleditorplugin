package eu.ddmore.converter.mdl2json.domain.data;

import groovy.util.Expando;

import java.util.ArrayList;
import java.util.List;

import org.ddmore.mdl.mdl.DataInputBlock;
import org.ddmore.mdl.mdl.SymbolDeclaration;

//import org.ddmore.mdl.mdl.HeaderBlock;
//import org.ddmore.mdl.mdl.SymbolModification;

public class DataInputVariables extends Expando {

	private DataInputBlock dataInputBlock;

	public DataInputVariables(DataInputBlock dataInputBlock) {
		this.dataInputBlock = dataInputBlock;
		
		makeVariables();
	}
	
	private void makeVariables() {
		for( SymbolDeclaration sd : dataInputBlock.getVariables() ) {
			Variable v = new Variable(sd);
			setProperty(v.getName(), v);
		}	
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Variable> getVariables() {
		return new ArrayList<Variable>( getProperties().values() );
	}

	@Override
	public Variable getVariable(String variableName) {
		return (Variable) getProperty(variableName);
	}

}
