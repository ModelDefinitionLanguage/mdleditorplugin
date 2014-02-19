
/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2nonmem;

import java.io.File;

import org.ddmore.mdl.MdlStandaloneSetup;
import org.ddmore.mdl.mdl.Mcl;
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

public class MDLToNonmemConverter extends MdlPrinterUtility implements ConverterProvider {

    private LanguageVersion source;
    private LanguageVersion target;
    private Version converterVersion;

    public MDLToNonmemConverter() {
        source = new LanguageVersionImpl();
        source.setLanguage("MDL");
        Version sourceVersion = new VersionImpl();
        sourceVersion.setMajor(5);
        sourceVersion.setMinor(0);
        sourceVersion.setPatch(8);
        source.setVersion(sourceVersion);

        target = new LanguageVersionImpl();
        target.setLanguage("NONMEM");
        Version targetVersion = new VersionImpl();
        targetVersion.setMajor(7);
        targetVersion.setMinor(2);
        target.setVersion(targetVersion);

        converterVersion = new VersionImpl();
        converterVersion.setMajor(1);
        converterVersion.setMinor(0);
        converterVersion.setPatch(2);
    }

    @Override
    public ConversionReport performConvert(File src, File outputDirectory) {
        Injector injector = new MdlStandaloneSetup().createInjectorAndDoEMFRegistration();
        XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
        resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);

        Resource resource = resourceSet.getResource(URI.createURI("file:///" + src.getAbsolutePath()), true);
        Mcl mcl = (Mcl) resource.getContents().get(0);

        Mdl2Nonmem xtendConverter = new Mdl2Nonmem();
        CharSequence converted = xtendConverter.convertToNMTRAN(mcl);
        
        return printOutputFile(src, outputDirectory, converted.toString(), ".ctl");
    }

    @Override
    public ConversionReport[] performConvert(File[] src, File outputDirectory) {
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
        return String.format("MDLToNonmemConverter [source=%s, target=%s, converterVersion=%s]", source, target, converterVersion) ;
    }

}
