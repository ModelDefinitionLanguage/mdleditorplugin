package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.utils.DomainObjectModelUtils
import eu.ddmore.mdl.utils.ExpressionUtils
import eu.ddmore.mdl.utils.MdlUtils
import java.io.File
import java.nio.file.Paths
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.Path
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar

class DataFileValidator extends AbstractMdlValidator  {
	
	extension MdlUtils mu = new MdlUtils
	extension ExpressionUtils eu = new ExpressionUtils
	extension DomainObjectModelUtils domu = new DomainObjectModelUtils
	
	override register(EValidatorRegistrar registrar){}
	
	public static val DATA_FILE_NOT_FOUND = "eu.ddmore.mdl.validation.source.file.unknown"
	
	static val MSG_DATA_FILE_NOT_FOUND = "Cannot find data file: path may be incorrect."


	@Check
	//Check that data file exists in the project
	def checkSourceFiles(ValuePair p){
		val blk = p.owningBlock
		val lst = p.owningListDefinition
		if(blk !== null && blk.isDataSourceBlock && lst !== null && p.argumentName == 'file'){
			val dataPath = p.expression.stringValue
			val dataFile = getFile(p, dataPath);
			if (dataFile === null || !dataFile.exists()){
				warning(MSG_DATA_FILE_NOT_FOUND, 
					MdlPackage.eINSTANCE.valuePair_Expression,
					DATA_FILE_NOT_FOUND, dataPath)
			}
		}
	}
	
    /**
     * Locate data/script file in the MDL project.
     * Such file references are resolved against the directory in which the MDL file is present.
     */
    // The validation code is called from both the MDL IDE and from the command-line
    // MDL->PharmML converter, each of which represents a file differently, hence
    // the ugly need to derive the java.io.File object representing the MDL file
    // in two different ways.
    def File getFile(EObject b, String filePath) {
    	val resource = b.eResource()
    	
        var File modelFile
    	if (resource.URI.isPlatform) { // Triggered from 'internal' MDL IDE code
            val platformString = resource.URI.toPlatformString(true)
            val modelFileAsIFile = ResourcesPlugin.getWorkspace().root.getFile(Path.fromOSString(platformString))
            modelFile = new File(modelFileAsIFile.locationURI.path) // Convert org.eclipse.core.resources.IFile to java.io.File
    	} else { // Triggered from 'external' converter code
            modelFile = new File(resource.URI.toFileString())
    	}
    	val java.nio.file.Path pf = if(modelFile.parentFile === null) Paths.get(".") else modelFile.parentFile.toPath()
    	val dataFile = pf.resolve(filePath).toFile().canonicalFile
    	return dataFile
    }
	
}