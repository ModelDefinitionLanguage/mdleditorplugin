package eu.ddmore.convertertoolbox.concurrent;

import eu.ddmore.convertertoolbox.api.conversion.ConversionListener;
import eu.ddmore.convertertoolbox.api.response.ConversionReport;

public class FutureCallbackImpl extends AbstractFutureCallback<ConversionReport> {

    public FutureCallbackImpl(ConversionListener listener) {
        super(listener);
    }

    @Override
    public void onSuccess(ConversionReport result) {
        listener.conversionComplete(result);
    }

    @Override
    public void onFailure(Throwable t) {
        ConversionReport report = createConversionReport(t);
        listener.conversionComplete(report);
    }

}
