package eu.ddmore.convertertoolbox.concurrent;

import eu.ddmore.convertertoolbox.api.conversion.ConversionListener;
import eu.ddmore.convertertoolbox.api.response.ConversionReport;

/**
 * The AbstractFutureCallback inplementation for single report, i.e. single file conversion.
 */
public class ConversionCallbackImpl extends AbstractFutureCallback<ConversionReport> {

    public ConversionCallbackImpl(ConversionListener listener) {
        super(listener);
    }

    /**
     * Called if the associated ListenableFuture object successfully terminates its task.
     * @param result the object returned by the call method of the associated ListenableFuture object.
     */
    @Override
    public void onSuccess(ConversionReport result) {
        getListener().conversionComplete(result);
    }

    /**
     * Called if the associated ListenableFuture object fails to execute its task due to an error.
     * @param t the error thrown by the call method of the associated ListenableFuture object.
     */
    @Override
    public void onFailure(Throwable t) {
        ConversionReport report = createConversionReport(t);
        getListener().conversionComplete(report);
    }

}
