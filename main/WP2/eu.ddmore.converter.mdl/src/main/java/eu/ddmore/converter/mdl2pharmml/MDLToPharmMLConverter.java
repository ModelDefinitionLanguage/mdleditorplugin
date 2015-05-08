/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2pharmml;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.ddmore.mdl.MdlStandaloneSetup;
import org.ddmore.mdl.mdl.MOGObject;
import org.ddmore.mdl.mdl.Mcl;
import org.ddmore.mdl.validation.Utils;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.Injector;

import eu.ddmore.converter.mdlprinting.MdlPrinterUtility;
import eu.ddmore.convertertoolbox.api.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.api.domain.Version;
import eu.ddmore.convertertoolbox.api.response.ConversionReport;
import eu.ddmore.convertertoolbox.api.spi.ConverterProvider;
import eu.ddmore.convertertoolbox.domain.LanguageVersionImpl;
import eu.ddmore.convertertoolbox.domain.VersionImpl;

import eu.ddmore.converter.mdl2pharmml.Mdl2PharmML;

public class MDLToPharmMLConverter extends MdlPrinterUtility implements ConverterProvider {

    private LanguageVersion source;
    private LanguageVersion target;
    private Version converterVersion;

    public MDLToPharmMLConverter() {
        Version sourceVersion = new VersionImpl(6, 0, 7);
        source = new LanguageVersionImpl("MDL", sourceVersion);

        Version targetVersion = new VersionImpl(0, 3, 1);
        target = new LanguageVersionImpl("PharmML", targetVersion);

        converterVersion = new VersionImpl(1, 0, 5);
    }

    @Override
    public ConversionReport performConvert(File src, File outputDirectory) throws IOException {
        Injector injector = new MdlStandaloneSetup().createInjectorAndDoEMFRegistration();
        XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
        resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);

        Resource resource = resourceSet.getResource(URI.createURI("file:///" + src.getAbsolutePath()), true);
        Mcl mcl = (Mcl) resource.getContents().get(0);
        List<MOGObject> mogs = Utils.getMOGs(mcl);

        // TODO: We're currently making an assumption that there will only be a single MOG
        // in the provided file.  This should be fine for Product 4.
        // This will be addressed under DDMORE-1221
        MOGObject mog = mogs.get(0);

        Mdl2PharmML xtendConverter = new Mdl2PharmML();
        CharSequence converted = xtendConverter.convertToPharmML(mog, src.toString());

        return printOutputFile(src, outputDirectory, converted.toString(), ".xml");
    }

    public ConversionReport[] performConvert(File[] src, File outputDirectory) throws IOException {
        ConversionReport[] reports = new ConversionReport[src.length];
        int i = 0;
        for (File f : src) {
            reports[i++] = performConvert(f, outputDirectory);
        }
        return reports;
    }

    @Override
    public LanguageVersion getSource() {
        return source;
    }

    @Override
    public LanguageVersion getTarget() {
        return target;
    }

    @Override
    public Version getConverterVersion() {
        return converterVersion;
    }

    @Override
    public String toString() {
        return String.format("MDLToPharmMLConverter [source=%s, target=%s, converterVersion=%s]", source, target, converterVersion);
    }
    
}