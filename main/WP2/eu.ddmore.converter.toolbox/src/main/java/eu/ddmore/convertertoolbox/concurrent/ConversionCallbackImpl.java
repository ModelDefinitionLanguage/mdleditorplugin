package eu.ddmore.convertertoolbox.concurrent;

import com.google.common.util.concurrent.FutureCallback;

import eu.ddmore.convertertoolbox.api.conversion.ConversionListener;
import eu.ddmore.convertertoolbox.api.response.ConversionDetail;
import eu.ddmore.convertertoolbox.api.response.ConversionReport;
import eu.ddmore.convertertoolbox.api.response.ConversionReport.ConversionCode;
import eu.ddmore.convertertoolbox.domain.ConversionDetailImpl;
import eu.ddmore.convertertoolbox.domain.ConversionReportImpl;

/**
 * The FutureCallback inplementation for single report, i.e. single file conversion.
 */
public class ConversionCallbackImpl implements FutureCallback<ConversionReport> {
    private ConversionListener listener;
    
    public ConversionCallbackImpl(ConversionListener listener) {
        this.listener = listener;
    }
    
    /**
     * Called if the associated ListenableFuture object successfully terminates its task.
     * @param result the object returned by the call method of the associated ListenableFuture object.
     */
    @Override
    public void onSuccess(ConversionReport result) {
        listener.conversionComplete(result);
    }

    /**
     * Called if the associated ListenableFuture object fails to execute its task due to an error.
     * @param t the error thrown by the call method of the associated ListenableFuture object.
     */
    @Override
    public void onFailure(Throwable t) {
        ConversionReport report = createConversionReport(t);
        listener.conversionComplete(report);
    }

    /**
     * 
     * @param thrown the error thrown by the onFailure method.
     * @return a conversion report marked as 'FAILURE', with additional error detail derived from the input error message.
     */
    private ConversionReport createConversionReport(Throwable thrown) {
        ConversionReport report = new ConversionReportImpl();
        report.setReturnCode(ConversionCode.FAILURE);
        ConversionDetail detail = new ConversionDetailImpl();
        detail.addInfo("Error in reading/writing input/output file", thrown.getMessage());
        report.addDetail(detail);
        return report;
    }

}
