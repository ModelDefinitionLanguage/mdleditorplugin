package eu.ddmore.convertertoolbox.concurrent;

import eu.ddmore.convertertoolbox.api.conversion.ConversionListener;
import eu.ddmore.convertertoolbox.api.response.ConversionReport;
import eu.ddmore.convertertoolbox.response.ConversionReportImpl;

public class FutureCallbackArrayImpl extends AbstractFutureCallback<ConversionReport[]> {

    private int reportsNumber;

    public FutureCallbackArrayImpl(ConversionListener listener, int reportsNumber) {
        super(listener);
        this.reportsNumber = reportsNumber;
    }

    @Override
    public void onSuccess(ConversionReport[] result) {
        listener.conversionComplete(result);
    }

    @Override
    public void onFailure(Throwable t) {
        ConversionReport[] reports = new ConversionReportImpl[reportsNumber];
        for (int i = 0; i < reportsNumber; i++) {
            reports[i] = createConversionReport(t);
        }
        listener.conversionComplete(reports);
    }
}
