package eu.ddmore.convertertoolbox.response;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import eu.ddmore.convertertoolbox.api.response.ConversionDetail;
import eu.ddmore.convertertoolbox.api.response.ConversionDetail.Severity;


public class ConversionDetailImplTest {

    @Test
    public void shouldBeEqualErrors() {
        ConversionDetail error1 = createConversionDetail(Severity.ERROR);
        ConversionDetail error2 = createConversionDetail(Severity.ERROR);
        assertEquals(error1, error2);
        assertEquals(error1.hashCode(), error2.hashCode());
        assertEquals(error1.toString(), error2.toString());
    }
    
    @Test
    public void shouldNotBeEqualErrorsAddingDetail() {
        ConversionDetail error1 = createConversionDetail(Severity.ERROR);
        error1.addInfo("error3", "error3");
        ConversionDetail error2 = createConversionDetail(Severity.ERROR);
        assertNotEquals(error1, error2);
    }
    
    @Test
    public void shouldBeEqualErrorsSettingDetails() {
        ConversionDetail error1 = new ConversionDetailImpl();
        error1.setSeverity(Severity.ERROR);
        error1.setMessage("ERROR message");
        Map<String, String> info = new HashMap<String, String>();
        error1.addInfo("error1", "error1");
        error1.addInfo("error2", "error2");
        error1.setInfo(info);
        ConversionDetail error2 = createConversionDetail(Severity.ERROR);
        assertEquals(error1, error2);
    }
    
    @Test
    public void shouldNotBeEqualErrorsSettingDetails() {
        ConversionDetail error1 = new ConversionDetailImpl();
        error1.setMessage("ERROR message");
        Map<String, String> info = new HashMap<String, String>();
        error1.addInfo("error3", "error3");
        error1.setInfo(info);
        ConversionDetail error2 = createConversionDetail(Severity.ERROR);
        assertNotEquals(error1, error2);
    }
    
    private ConversionDetail createConversionDetail(Severity severity) {
        ConversionDetail conversionDetail = new ConversionDetailImpl();
        conversionDetail.setSeverity(severity);
        if (severity.equals(Severity.ERROR)) {
            conversionDetail.addInfo("error1", "error1");
            conversionDetail.addInfo("error2", "error2");
            conversionDetail.setMessage("ERROR message");
        } else if (severity.equals(Severity.WARNING)) {
            conversionDetail.addInfo("warning1", "warning1");
            conversionDetail.addInfo("warning2", "warning2");
            conversionDetail.setMessage("WARNING message");
        } else if (severity.equals(Severity.INFO)) {
            conversionDetail.addInfo("info1", "info1");
            conversionDetail.addInfo("info2", "info2");
            conversionDetail.setMessage("INFO message");
        } else if (severity.equals(Severity.DEBUG)) {
            conversionDetail.addInfo("debug1", "debug1");
            conversionDetail.addInfo("debug2", "debug2");
            conversionDetail.setMessage("DEBUG message");
        }
        return conversionDetail;
    }


}
