package eu.ddmore.convertertoolbox.concurrent;

import com.google.common.util.concurrent.FutureCallback;

import eu.ddmore.convertertoolbox.api.conversion.ConversionListener;
import eu.ddmore.convertertoolbox.api.response.ConversionDetail;
import eu.ddmore.convertertoolbox.api.response.ConversionReport;
import eu.ddmore.convertertoolbox.api.response.ConversionReport.ConversionCode;
import eu.ddmore.convertertoolbox.response.ConversionDetailImpl;
import eu.ddmore.convertertoolbox.response.ConversionReportImpl;

/**
 * An abstract class containing the common state/behaviour of FutureCallbackImpl and FutureCallbackArrayImpl subtypes.
 *
 * @param <T> this will be either ConversionReport or ConversionReport[]
 */
public abstract class AbstractFutureCallback<T> implements FutureCallback<T> {
    
    private ConversionListener listener;
    
    public AbstractFutureCallback(ConversionListener listener) {
        this.listener = listener;
    }

    /**
     * 
     * @param thrown the error thrown by the onFailure method that concrete subclasses should implement.
     * @return a conversion report marked as 'FAILURE', with additional error detail derived from the input error message.
     */
    protected ConversionReport createConversionReport(Throwable thrown) {
        ConversionReport report = new ConversionReportImpl();
        report.setReturnCode(ConversionCode.FAILURE);
        ConversionDetail detail = new ConversionDetailImpl();
        detail.addInfo("Error in reading/writing input/output file", thrown.getMessage());
        report.addDetail(detail);
        return report;
    }

    protected ConversionListener getListener() {
        return listener;
    }
    
}
