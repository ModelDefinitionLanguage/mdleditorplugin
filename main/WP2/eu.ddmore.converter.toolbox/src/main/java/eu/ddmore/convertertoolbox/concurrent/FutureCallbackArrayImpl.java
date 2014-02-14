package eu.ddmore.convertertoolbox.concurrent;

import eu.ddmore.convertertoolbox.api.conversion.ConversionListener;
import eu.ddmore.convertertoolbox.api.response.ConversionReport;
import eu.ddmore.convertertoolbox.response.ConversionReportImpl;

/**
 * The AbstractFutureCallback inplementation for multiple reports, i.e. multiple files conversion.
 */
public class FutureCallbackArrayImpl extends AbstractFutureCallback<ConversionReport[]> {

    private int reportsNumber;

    public FutureCallbackArrayImpl(ConversionListener listener, int reportsNumber) {
        super(listener);
        this.reportsNumber = reportsNumber;
    }

    /**
     * Called if the associated ListenableFuture object succesfully terminates its task.
     * @param result the object returned by the call method of the associated ListenableFuture object.
     */
    @Override
    public void onSuccess(ConversionReport[] result) {
        getListener().conversionComplete(result);
    }

    /**
     * Called if the associated ListenableFuture object fails to execute its task due to an error.
     * @param t the error thrown by the call method of the associated ListenableFuture object.
     */
    @Override
    public void onFailure(Throwable t) {
        ConversionReport[] reports = new ConversionReportImpl[reportsNumber];
        for (int i = 0; i < reportsNumber; i++) {
            reports[i] = createConversionReport(t);
        }
        getListener().conversionComplete(reports);
    }
}
