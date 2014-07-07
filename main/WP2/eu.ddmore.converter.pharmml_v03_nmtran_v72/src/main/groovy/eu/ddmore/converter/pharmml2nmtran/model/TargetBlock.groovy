/*******************************************************************************
 * Copyright (C) 2014 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.pharmml2nmtran.model

class TargetBlock {
		
	String target;
	String location;
	boolean first;	
	boolean last;
	boolean sameline;
	def code;

	TargetBlock(targetBlock){
		target = targetBlock.target.toString().trim()
		location = targetBlock.location.toString().trim()
		first = targetBlock.first.toBoolean()
		last = targetBlock.last.toBoolean()
		sameline = targetBlock.sameline.toBoolean()
		code = targetBlock.code.toString().trim()
	}

}
