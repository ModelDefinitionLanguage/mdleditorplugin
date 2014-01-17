/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.conversion;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import eu.ddmore.convertertoolbox.api.conversion.ConversionListener;
import eu.ddmore.convertertoolbox.api.conversion.Converter;
import eu.ddmore.convertertoolbox.api.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.api.domain.Version;
import eu.ddmore.convertertoolbox.api.response.ConversionDetail;
import eu.ddmore.convertertoolbox.api.response.ConversionReport;
import eu.ddmore.convertertoolbox.api.response.ConversionReport.ConversionCode;
import eu.ddmore.convertertoolbox.api.spi.ConverterProvider;
import eu.ddmore.convertertoolbox.response.ConversionDetailImpl;
import eu.ddmore.convertertoolbox.response.ConversionReportImpl;

/**
 * Represents a converter available in the toolbox
 */
public class ConverterImpl implements Converter {

    private ConverterProvider provider;

    public void setProvider(ConverterProvider provider) {
        this.provider = provider;
    }

    @Override
    public ConversionReport convert(File src, File outputDirectory) throws IOException {
        return provider.performConvert(src, outputDirectory);
    }

    @Override
    public ConversionReport[] convert(File[] src, File outputDirectory) throws IOException {
        return provider.performConvert(src, outputDirectory);
    }

    @Override
    public void convert(final File src, final File outputDirectory, final ConversionListener listener) throws IOException {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor());
        
        ListenableFuture<ConversionReport> conversion = service.submit(new Callable<ConversionReport>() {

            public ConversionReport call() throws IOException {
                return convert(src, outputDirectory);
            }
        });
        Futures.addCallback(conversion, new FutureCallback<ConversionReport>() {

            public void onSuccess(ConversionReport report) {
                listener.conversionComplete(report);
            }

            public void onFailure(Throwable thrown) {
                ConversionReport report = createConversionReport(thrown);
                listener.conversionComplete(report);
            }

        });
    }

    @Override
    public void convert(final File[] src, final File outputDirectory, final ConversionListener listener) throws IOException {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        ListenableFuture<ConversionReport[]> conversion = service.submit(new Callable<ConversionReport[]>() {

            public ConversionReport[] call() throws IOException {
                return convert(src, outputDirectory);
            }
        });
        Futures.addCallback(conversion, new FutureCallback<ConversionReport[]>() {

            public void onSuccess(ConversionReport[] reports) {
                listener.conversionComplete(reports);
            }

            public void onFailure(Throwable thrown) {
                ConversionReport[] reports = new ConversionReportImpl[src.length];
                for (int i=0; i<src.length; i++) {
                    reports[i] = createConversionReport(thrown);
                }
                listener.conversionComplete(reports);
            }
        });
    }
    
    private ConversionReport createConversionReport(Throwable thrown) {
        ConversionReport report = new ConversionReportImpl();
        report.setReturnCode(ConversionCode.FAILURE);
        ConversionDetail detail = new ConversionDetailImpl();
        detail.addInfo("Error in reading/writing input/output file", thrown.getMessage());
        report.addDetail(detail);
        return report;
    }


    @Override
    public ConverterProvider getConverterProvider() {
        return provider;
    }

    @Override
    public LanguageVersion getTarget() {
        return getConverterProvider().getTarget();
    }

    @Override
    public LanguageVersion getSource() {
        return getConverterProvider().getSource();
    }

    @Override
    public Version getConverterVersion() {
        return getConverterProvider().getConverterVersion();
    }

    @Override
    public String toString() {
        return String.format("ConverterImpl [provider=%s]", provider);
    }

}
