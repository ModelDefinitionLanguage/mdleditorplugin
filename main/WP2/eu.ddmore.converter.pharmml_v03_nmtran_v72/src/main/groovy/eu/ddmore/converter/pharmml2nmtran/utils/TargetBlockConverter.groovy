/*******************************************************************************
 * Copyright (C) 2014 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.pharmml2nmtran.utils

import eu.ddmore.converter.pharmml2nmtran.model.TargetBlock
import groovy.lang.Closure;

public class TargetBlockConverter {
	
	 public Map externalCodeStart = new HashMap<String, TargetBlock>() 
	 public Map externalCodeEnd = new HashMap<String, TargetBlock>()
	 public final static String NMTRAN_CODE = "NMTRAN_CODE";
	 
	 public void initExternalCodeMaps(){
		 externalCodeStart = new HashMap<String, TargetBlock>()
		 externalCodeEnd = new HashMap<String, TargetBlock>()
	 }
	 
	 /**
	  * Checks if statement type passed has any target block defined.
	  * 
	  * @param statementType
	  * @return
	  */
	def isTargetDefined(String statementType){
		return (externalCodeStart.containsKey(statementType) || externalCodeEnd.containsKey(statementType)) 
	}
	
	/**
	 * This method will look into map for code block associated with statement location for start=true option.
	 *
	 * @param section
	 * @return
	 */
	def getExternalCodeStart(String location){
		TargetBlock targetblock= externalCodeStart.get(location)
		if(targetblock!=null){
			String code = targetblock.code +"\n"
			if(targetblock.sameline){
				return code
			}else{
				return "\n"+code
			}
		}
		return ""
	}
	
	/**
	 * This method will look into map for code block associated with statement location for last=true option. 
	 * 
	 * @param section
	 * @return
	 */
	def getExternalCodeEnd(String location){
		TargetBlock targetblock= externalCodeEnd.get(location)
		if(targetblock!=null){
			String code = targetblock.code+"\n"
			if(targetblock.sameline){
				return code
			}else{
				return "\n"+code
			}
		}
		return ""
	}
	
	/**
	 * This method read each target block and adds code to maps to add at start of the block or at the end of the block.
	 * 
	 * @param targetBlock
	 * @return
	 */
	def prepareExternalCode(TargetBlock targetBlock){
		if(targetBlock.target.toString().trim().equals(NMTRAN_CODE)){
			if(targetBlock.last){
				externalCodeEnd.put(trimLocationName(targetBlock.location), targetBlock)
			}else{
				externalCodeStart.put(trimLocationName(targetBlock.location), targetBlock)
			}
		}
	}
	
	/**
	 * This method trims the target block location to first 5 characters. 
	 * This will help to have uniform keys for different styles of same location name.  
	 * 
	 * @param location
	 * @return
	 */
	public String trimLocationName(String location){
		if(location!=null && location.length() >0){
			if(location.length() > 5){
				return location.substring(0, 4);
			}else{
				return location;
			}
		}else{
			throw new IllegalArgumentException("Empty or Not a valid target block location: "+ location);
		}
	}
	
	/**
	 * Add targeblock details and execute closure if there is no targetblock specified
	 *
	 * @param statementName
	 * @param nonTargetBlockClosure
	 * @return
	 */
	def addTargetBlockCode(String statementName, Closure nonTargetBlockClosure){
		addTargetBlockCode(statementName, nonTargetBlockClosure, {})
	}
	
	/**
	 * Add targeblock details along with some code to insert in between target block
	 * and execute closure if there is no targetblock specified
	 *
	 * @param statementName
	 * @param nonTargetBlockClosure
	 * @param targetBlockClosure
	 * @return
	 */
	def addTargetBlockCode(String statementName, Closure nonTargetBlockClosure, Closure targetBlockClosure){
		def statement = new StringBuilder();
		def trimmedBlockName = trimLocationName(statementName);
		
		if(isTargetDefined(trimLocationName(trimmedBlockName))){
			statement << getExternalCodeStart(trimmedBlockName)
			String state = targetBlockClosure()
			if(state != null){
				statement << state
			}
			statement << getExternalCodeEnd(trimmedBlockName)
			statement
		}else{
			nonTargetBlockClosure()
		}
	}
}
