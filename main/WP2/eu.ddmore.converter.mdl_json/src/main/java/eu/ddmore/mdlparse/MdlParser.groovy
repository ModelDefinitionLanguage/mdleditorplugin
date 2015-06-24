/*******************************************************************************
 * Copyright (C) 2014-2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.mdlparse

import org.apache.log4j.Logger
import org.ddmore.mdl.MdlStandaloneSetup
import org.ddmore.mdl.mdl.Mcl
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.Resource.Diagnostic
import org.eclipse.xtext.resource.XtextResource
import org.eclipse.xtext.resource.XtextResourceSet

import com.google.common.base.Preconditions
import com.google.inject.Injector

import eu.ddmore.convertertoolbox.api.response.ConversionDetail
import eu.ddmore.convertertoolbox.api.response.ConversionReport
import eu.ddmore.convertertoolbox.api.response.ConversionDetail.Severity
import eu.ddmore.convertertoolbox.api.response.ConversionReport.ConversionCode
import eu.ddmore.convertertoolbox.domain.ConversionDetailImpl

class MdlParser {
    private static final Logger LOGGER = Logger.getLogger(MdlParser.class)

    static Injector injector;
    static XtextResourceSet resourceSet;

    static {
        injector = new MdlStandaloneSetup().createInjectorAndDoEMFRegistration();
        resourceSet = injector.getInstance(XtextResourceSet.class);
        resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
    }

    /**
     * Parse the MDL file into an object graph of Xtext domain objects rooted at an {@link Mcl} object.
     * Any errors encountered in parsing the MDL will be populated in the provided {@link ConversionReport}
     * and the report's status will be set as FAILED.
     * <p>
     * @param mdlFile - the {@link File} object referencing the MDL file to parse
     * @param report - a newly created {@link ConversionReport}; this will be populated in the event of errors in parsing the MDL
     * @return the {@link Mcl} object graph representing the parsed MDL file
     */
    public Mcl parse(final File mdlFile, final ConversionReport report) {
        Preconditions.checkNotNull(mdlFile, "No MDL File provided to MdlParser.parse()")
        Preconditions.checkNotNull(report, "A ConversionReport must be provided to MdlParser.parse()")
        
        final Resource resource = resourceSet.getResource(URI.createURI("file:///" + mdlFile.getAbsolutePath()), true)

        EList<Diagnostic> errors = resource.getErrors()
        EList<Diagnostic> warnings = resource.getWarnings()
        if (warnings) {
            LOGGER.warn(String.format("%1\$d warning(s) encountered in parsing MDL file %2\$s", warnings.size(), mdlFile.getAbsolutePath()));
            for (Diagnostic w : warnings) {
                LOGGER.warn(w);
                final ConversionDetail detail = new ConversionDetailImpl();
                detail.setMessage(w.toString());
                detail.setSeverity(Severity.WARNING);
                report.addDetail(detail);
            }
        }
        if (errors) {
            LOGGER.error(String.format("%1\$d error(s) encountered in parsing MDL file %2\$s", errors.size(), mdlFile.getAbsolutePath()));
            for (Diagnostic e : errors) {
                LOGGER.error(e);
                final ConversionDetail detail = new ConversionDetailImpl();
                detail.setMessage(e.toString());
                detail.setSeverity(Severity.ERROR);
                report.addDetail(detail);
            }
            report.setReturnCode(ConversionCode.FAILURE);
            return null; // Bail out
        }

        return (Mcl) resource.getContents().get(0);
    }

}
