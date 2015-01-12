/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.domain.internal;

import eu.ddmore.convertertoolbox.api.response.ConversionDetail.Severity;
import eu.ddmore.convertertoolbox.api.response.ConversionReport.ConversionCode;
import eu.ddmore.convertertoolbox.domain.ConversionDetail;
import eu.ddmore.convertertoolbox.domain.ConversionDetailImpl;
import eu.ddmore.convertertoolbox.domain.ConversionDetailSeverity;
import eu.ddmore.convertertoolbox.domain.ConversionReport;
import eu.ddmore.convertertoolbox.domain.ConversionReportImpl;
import eu.ddmore.convertertoolbox.domain.ConversionReportOutcomeCode;
import eu.ddmore.convertertoolbox.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.domain.LanguageVersionImpl;
import eu.ddmore.convertertoolbox.domain.Version;
import eu.ddmore.convertertoolbox.domain.VersionImpl;

/**
 * Utility class performing mapping between Data Model exposed via REST and converter-toolbox-api
 */
public class ConverterToolboxAPIObjectMapper {

    /**
     * Hidden constructor
     */
    private ConverterToolboxAPIObjectMapper() {
        
    }
    
    public static eu.ddmore.convertertoolbox.api.domain.Version toOldAPI(Version version) {
        return new VersionImpl(version.getMajor(),version.getMinor(),version.getPatch(), version.getQualifier());
    }

    public static Version fromOldAPI(eu.ddmore.convertertoolbox.api.domain.Version version) {
        return new Version(version.getMajor(),version.getMinor(),version.getPatch(), version.getQualifier());
    }
    

    public static eu.ddmore.convertertoolbox.api.domain.LanguageVersion toOldAPI(LanguageVersion languageVersion) {
        return new LanguageVersionImpl(languageVersion.getLanguage(), toOldAPI(languageVersion.getVersion()));
    }
    
    public static LanguageVersion fromOldAPI(eu.ddmore.convertertoolbox.api.domain.LanguageVersion languageVersion) {
        return new LanguageVersion(languageVersion.getLanguage(), fromOldAPI(languageVersion.getVersion()));
        
    }

    public static eu.ddmore.convertertoolbox.api.response.ConversionDetail toOldAPI(ConversionDetail fromConversionDetail) {
        ConversionDetailImpl conversionDetail = new ConversionDetailImpl();
        conversionDetail.setInfo(fromConversionDetail.getInfo());
        conversionDetail.setMessage(fromConversionDetail.getMessage());
        conversionDetail.setSeverity(toOldApi(fromConversionDetail.getSeverity()));
        return conversionDetail;
    }

    private static Severity toOldApi(ConversionDetailSeverity severity) {
        return Severity.valueOf(severity.name());
    }

    public static ConversionDetail fromOldAPI(eu.ddmore.convertertoolbox.api.response.ConversionDetail conversionDetail) {
        ConversionDetail result = new ConversionDetail();
        result.setInfo(conversionDetail.getInfo());
        result.setMessage(conversionDetail.getMessage());
        result.setSeverity(fromOldApi(conversionDetail.getServerity()));
        return result;
    }
    

    private static ConversionDetailSeverity fromOldApi(Severity severity) {
        return ConversionDetailSeverity.valueOf(severity.name());
    }

    public static eu.ddmore.convertertoolbox.api.response.ConversionReport toOldAPI(ConversionReport conversionReport) {
        ConversionReportImpl result = new ConversionReportImpl();
        result.setReturnCode(toOldApi(conversionReport.getReturnCode()));
        for(ConversionDetail conversionDetail : conversionReport.getDetails()) {
            result.addDetail(toOldAPI(conversionDetail));
        }
        return result;
    }
    
    private static ConversionCode toOldApi(ConversionReportOutcomeCode returnCode) {
        return ConversionCode.valueOf(returnCode.name());
    }

    public static ConversionReport fromOldAPI(eu.ddmore.convertertoolbox.api.response.ConversionReport conversionReport) {
        ConversionReport result = new ConversionReport();
        result.setReturnCode(fromOldApi(conversionReport.getReturnCode()));
        for(eu.ddmore.convertertoolbox.api.response.ConversionDetail conversionDetail: conversionReport.getDetails(Severity.ALL)) {
            result.addDetail(fromOldAPI(conversionDetail));
        }
        return result;
    }

    private static ConversionReportOutcomeCode fromOldApi(ConversionCode returnCode) {
        return ConversionReportOutcomeCode.valueOf(returnCode.name());
    }
}
