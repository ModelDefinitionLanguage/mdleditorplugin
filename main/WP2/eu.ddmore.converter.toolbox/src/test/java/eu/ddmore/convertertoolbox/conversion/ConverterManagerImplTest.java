package eu.ddmore.convertertoolbox.conversion;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import eu.ddmore.convertertoolbox.api.conversion.Converter;
import eu.ddmore.convertertoolbox.api.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.api.domain.Version;
import eu.ddmore.convertertoolbox.api.exception.ConverterNotFoundException;
import eu.ddmore.convertertoolbox.domain.LanguageVersionImpl;
import eu.ddmore.convertertoolbox.domain.VersionImpl;
import eu.ddmore.convertertoolbox.spi.DummyMDLToNMTRAN;
import eu.ddmore.convertertoolbox.spi.DummyMDLToPharmML;

/**
 * Test for {@link ConverterManagerImpl}.
 */
public class ConverterManagerImplTest {

    private ConverterManagerImpl converterManager;

    private LanguageVersion mdl;

    @Before
    public void init() {
        mdl = new LanguageVersionImpl();
        mdl.setLanguage("MDL");
        Version mdlVersion = new VersionImpl();
        mdlVersion.setMajor(5);
        mdlVersion.setMinor(0);
        mdlVersion.setPatch(8);
        mdl.setVersion(mdlVersion);

        converterManager = new ConverterManagerImpl();
        ArrayList<Converter> converters = new ArrayList<Converter>();
        
        ConverterImpl converter = new ConverterImpl();
        converter.setProvider(new DummyMDLToNMTRAN());
        converters.add(converter);

        ConverterImpl converter2 = new ConverterImpl();
        converter2.setProvider(new DummyMDLToPharmML());
        converters.add(converter2);
        
        converterManager.setConverters(converters);
    }

    private LanguageVersion createPharmMLLanguage() {
        LanguageVersion lang = new LanguageVersionImpl();
        lang.setLanguage("PharmML");
        Version version = new VersionImpl();
        version.setMajor(0);
        version.setMinor(2);
        version.setPatch(1);
        lang.setVersion(version);
        return lang;
    }
    
    private LanguageVersion createNONMEMLanguage() {
        LanguageVersion lang = new LanguageVersionImpl();
        lang.setLanguage("NONMEM");
        Version version = new VersionImpl();
        version.setMajor(7);
        version.setMinor(2);
        lang.setVersion(version);
        return lang;
    }
    
    
    @Test
    public void shouldFindConverterMDLToNONMEM() throws ConverterNotFoundException, IOException {
        LanguageVersion nonmem = createNONMEMLanguage();
        assertNotNull(converterManager.getConverter(mdl, nonmem));
    }

    @Test
    public void shouldFindConverterMDLToPharmML() throws ConverterNotFoundException, IOException {
        LanguageVersion pharmaml = createPharmMLLanguage();
        assertNotNull(converterManager.getConverter(mdl, pharmaml));
    }
    
    
    @Test(expected = ConverterNotFoundException.class)
    public void shouldNotFindConvertor() throws ConverterNotFoundException {
        LanguageVersion nonmem = createNONMEMLanguage();
        LanguageVersion pharmaml = createPharmMLLanguage();
        converterManager.getConverter(pharmaml, nonmem);
    }


}
