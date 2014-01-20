package eu.ddmore.convertertoolbox.concurrent;

import eu.ddmore.convertertoolbox.api.conversion.ConversionListener;
import eu.ddmore.convertertoolbox.api.response.ConversionReport;

/**
 * The AbstractFutureCallback inplementation for single report, i.e. single file conversion.
 */
public class FutureCallbackImpl extends AbstractFutureCallback<ConversionReport> {

    public FutureCallbackImpl(ConversionListener listener) {
        super(listener);
    }

    /**
     * Called if the associated ListenableFuture object succesfully terminates its task.
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

}
