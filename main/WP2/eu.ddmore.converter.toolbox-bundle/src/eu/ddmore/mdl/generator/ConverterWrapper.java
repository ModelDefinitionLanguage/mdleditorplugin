package eu.ddmore.mdl.generator;

import java.io.File;
import java.io.IOException;

import org.ddmore.mdl.mdl.Mcl;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;

import com.google.common.collect.Iterables;

import eu.ddmore.converter.mdlprinting.MdlPrinter;
import eu.ddmore.convertertoolbox.api.conversion.Converter;
import eu.ddmore.convertertoolbox.api.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.api.domain.Version;
import eu.ddmore.convertertoolbox.api.exception.ConverterNotFoundException;
import eu.ddmore.convertertoolbox.conversion.ConverterManagerImpl;
import eu.ddmore.convertertoolbox.domain.LanguageVersionImpl;
import eu.ddmore.convertertoolbox.domain.VersionImpl;

public class ConverterWrapper extends MdlPrinter implements IGenerator {

    private ConverterManagerImpl converterManager;
    private LanguageVersion mdl;
    private LanguageVersion target;

    //TODO: This is actually a property of the runtime eclipse that the MDLEditor user can change. We need to find that by some Preference class.
    private static final String SRC_GEN_PREFIX = "/src-gen";

    public ConverterWrapper() {
        converterManager = new ConverterManagerImpl();
        mdl = new LanguageVersionImpl();
        mdl.setLanguage("MDL");
        Version mdlVersion = new VersionImpl();
        mdlVersion.setMajor(5);
        mdlVersion.setMinor(0);
        mdlVersion.setPatch(8);
        mdl.setVersion(mdlVersion);
    }

    protected void setTargetLanguage(LanguageVersion target) {
        this.target = target;
    }

    public void doGenerate(final Resource resource, final IFileSystemAccess fsa) {
        String relativeResourcePath = resource.getURI().toPlatformString(true);
        String[] uriParts = relativeResourcePath.split("/");
        String projectName = uriParts[1];
        String projectPath = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName).getLocation().toString();

        String sourcePath = relativeResourcePath.replace('/' + projectName, projectPath);
        File source = new File(sourcePath);
        File targetDir = new File(source.getParentFile().getParent() + SRC_GEN_PREFIX);
        
        //TODO: How do we pass that info to the user?
        try {
            Converter converter = converterManager.getConverter(mdl, target);
            converter.convert(source, targetDir);
        } catch (ConverterNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
