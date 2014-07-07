/*******************************************************************************
 * Copyright (C) 2014 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.pharmml2nmtran.statements

import eu.ddmore.converter.pharmml2nmtran.model.TargetBlock
import eu.ddmore.libpharmml.dom.PharmML
import org.apache.commons.io.FileUtils

class TargetBlockStatement extends NMTranFormatter {
	
	List targetBlocks = new ArrayList<TargetBlock>()
	
	/**
	 * Parses the target blocks file and returns target blocks. 
	 * 
	 * @param fileName
	 * @return
	 */
	def getTargetBlocks(File fileName){
		StringBuilder sb = new StringBuilder()
		sb << fileName.getText()
		//Appending tag to wrap all the target blocks of xml and to parse well formed xml. 
		def TARGET_BLOCK_XML = "<targetBlocks>"+sb+"</targetBlocks>"
				
		def targetBlockXML = new XmlSlurper().parseText(TARGET_BLOCK_XML)
		def allTargetBlocks = targetBlockXML.targetBlock
		
		allTargetBlocks.each {targetBlock ->
				targetBlocks.add(new TargetBlock(targetBlock))
		}
		return targetBlocks
	}
}
