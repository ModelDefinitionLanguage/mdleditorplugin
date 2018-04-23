/*
 * generated by Xtext
 */
package eu.ddmore.mdl.ui.wizard


import com.google.inject.Inject
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.resource.FileExtensionProvider
import java.io.InputStream

class MdlNewProjectWizardInitialContents {
	@Inject
	FileExtensionProvider fileExtensionProvider

	def generateInitialContents(IFileSystemAccess2 fsa) {
	    val InputStream tempIs = this.getClass().getResourceAsStream("NewFileTemplate.mdl")
	    if(tempIs !== null){
        fsa.generateFile(
            "models/Example." + fileExtensionProvider.primaryFileExtension,
            tempIs
            )
            tempIs.close();
	    }
	    else{
	        throw new RuntimeException("MDL template file not found")
	    }
	}
}