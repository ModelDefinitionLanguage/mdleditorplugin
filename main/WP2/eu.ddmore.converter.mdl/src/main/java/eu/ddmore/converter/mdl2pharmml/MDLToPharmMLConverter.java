/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2pharmml;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.ddmore.mdl.MdlStandaloneSetup;
import org.ddmore.mdl.mdl.MOGObject;
import org.ddmore.mdl.mdl.Mcl;
import org.ddmore.mdl.validation.Utils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.Injector;

import eu.ddmore.convertertoolbox.api.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.api.domain.Version;
import eu.ddmore.convertertoolbox.api.response.ConversionDetail;
import eu.ddmore.convertertoolbox.api.response.ConversionDetail.Severity;
import eu.ddmore.convertertoolbox.api.response.ConversionReport;
import eu.ddmore.convertertoolbox.api.response.ConversionReport.ConversionCode;
import eu.ddmore.convertertoolbox.api.spi.ConverterProvider;
import eu.ddmore.convertertoolbox.domain.ConversionDetailImpl;
import eu.ddmore.convertertoolbox.domain.ConversionReportImpl;
import eu.ddmore.convertertoolbox.domain.LanguageVersionImpl;
import eu.ddmore.convertertoolbox.domain.VersionImpl;

public class MDLToPharmMLConverter implements ConverterProvider {

    private final static Logger LOGGER = Logger.getLogger(MDLToPharmMLConverter.class);

    private LanguageVersion source;
    private LanguageVersion target;
    private Version converterVersion;

    public MDLToPharmMLConverter() {
        Version sourceVersion = new VersionImpl(6, 0, 8);
        source = new LanguageVersionImpl("MDL", sourceVersion);

        Version targetVersion = new VersionImpl(0, 6, 0);
        target = new LanguageVersionImpl("PharmML", targetVersion);

        converterVersion = new VersionImpl(1, 0, 5);
    }

    @Override
    public ConversionReport performConvert(File src, File outputDirectory) throws IOException {
        // We know we're going to return a conversion report so create it up front; it is added to at various places in this method
        final ConversionReport report = new ConversionReportImpl();
        
        Injector injector = new MdlStandaloneSetup().createInjectorAndDoEMFRegistration();
        XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
        resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);

        Resource resource = resourceSet.getResource(URI.createURI("file:///" + src.getAbsolutePath()), true);
        
        EList<Diagnostic> errors = resource.getErrors();
        EList<Diagnostic> warnings = resource.getWarnings();
        if (!errors.isEmpty()) {
            LOGGER.error(errors.size() + " errors encountered in parsing MDL file " + src.getAbsolutePath());
            for (Diagnostic e : errors) {
                LOGGER.error(e);
                final ConversionDetail detail = new ConversionDetailImpl();
                detail.setMessage(e.toString());
                detail.setSeverity(Severity.ERROR);
                report.addDetail(detail);
            }
            report.setReturnCode(ConversionCode.FAILURE);
            return report; // Bail out
        }
        if (!warnings.isEmpty()) {
            LOGGER.warn(warnings.size() + " warning(s) encountered in parsing MDL file " + src.getAbsolutePath());
            for (Diagnostic w : warnings) {
                LOGGER.error(w);
                final ConversionDetail detail = new ConversionDetailImpl();
                detail.setMessage(w.toString());
                detail.setSeverity(Severity.WARNING);
                report.addDetail(detail);
            }
        }
        
        Mcl mcl = (Mcl) resource.getContents().get(0);
        List<MOGObject> mogs = Utils.getMOGs(mcl);

        // TODO: We're currently making an assumption that there will be a single MOG
        // in the provided file.  This should be fine for Product 4.
        // This will be addressed under DDMORE-1221
        
        if (mogs.isEmpty()) {
        	throw new IllegalStateException("Must be (at least) one MOG defined in the provided MCL file: " + src); 
        }
        final MOGObject mog = mogs.get(0);

        final CharSequence converted = new Mdl2PharmML().convertToPharmML(mog, src.toString());
        
        final File outputFile = new File(outputDirectory.getAbsoluteFile(), FilenameUtils.getBaseName(src.getName()) + ".xml");
        
        try {
            FileUtils.writeStringToFile(outputFile, converted.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        report.setReturnCode(ConversionCode.SUCCESS);
        return report;
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