package eu.ddmore.convertertoolbox.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.task.TaskExecutor;

import eu.ddmore.convertertoolbox.api.conversion.Converter;
import eu.ddmore.convertertoolbox.api.conversion.ConverterManager;
import eu.ddmore.convertertoolbox.api.exception.ConverterNotFoundException;
import eu.ddmore.convertertoolbox.domain.Conversion;
import eu.ddmore.convertertoolbox.domain.ConversionStatus;
import eu.ddmore.convertertoolbox.domain.LanguageVersionImpl;
import eu.ddmore.convertertoolbox.domain.VersionImpl;
import eu.ddmore.convertertoolbox.service.ConversionRepository;
import eu.ddmore.convertertoolbox.service.ExceededCapacity;


/**
 * Tests {@link ConversionServiceWithTaskExecutor}
 */
@RunWith(MockitoJUnitRunner.class)
public class ConversionServiceWithTaskExecutorTest {
    
    @InjectMocks
    private final ConversionServiceWithTaskExecutor instance = new ConversionServiceWithTaskExecutor();
    
    @Mock
    private ConversionRepository conversionRepository;
    
    @Mock
    private TaskExecutor conversionTaskExecutor;

    @Mock
    private ConverterManager converterManager;

    @Mock
    private ConversionTaskFactory conversionTaskFactory;
    
    @Before
    public void setUp() throws Exception {
        instance.setServiceCapacity(1);
    }

    @Test(expected=NullPointerException.class)
    public void schedule_shouldThrowRuntimeExceptionForNullConversion() throws ExceededCapacity {
        instance.schedule(null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void schedule_shouldThrowRuntimeExceptionIfConversionScheduledForUnsupportedConversion() throws ExceededCapacity, ConverterNotFoundException {
        Conversion conversion =createTestConversion("NON-EXISTING-FROM", "TO", "mock/input/file");
        doThrow(ConverterNotFoundException.class).when(converterManager).getConverter(conversion.getFrom(), conversion.getTo());
        instance.schedule(conversion);
    }

    @Test(expected = ExceededCapacity.class)
    public void schedule_shouldThrowExceededCapacityExceptionIfNoSpaceLeftOnTaskExecutor() throws ExceededCapacity {
        Conversion conversion = createTestConversion("FROM", "TO", "mock/input/file");
        when(conversionRepository.countUncompletedConversions()).thenReturn(3);
        instance.setServiceCapacity(3);
        instance.schedule(conversion);
    }

    @Test
    public void schedule_shouldPersistConversionAndScheduleItForExecution() throws ExceededCapacity, ConverterNotFoundException {
        Conversion conversion = createTestConversion("FROM", "TO", "mock/input/file");
        
        when(converterManager.getConverter(eq(conversion.getFrom()), eq(conversion.getTo()))).thenReturn(mock(Converter.class));
        when(conversionRepository.save(same(conversion))).thenReturn(conversion);
        
        Conversion result = instance.schedule(conversion);
        
        verify(conversionRepository).save(same(conversion));
        verify(conversionTaskExecutor).execute(any(ConversionTask.class));
        assertNotNull(result);
    }

    @Test
    public void isFull_shouldReturn_TRUE_IfServiceIsAbleToAcceptConversions() {
        when(conversionRepository.countUncompletedConversions()).thenReturn(3);
        instance.setServiceCapacity(3);
        
        assertTrue(instance.isFull());
    }

    @Test
    public void isFull_shouldReturn_FALSE_IfServiceIsAbleToAcceptConversions() {
        when(conversionRepository.countUncompletedConversions()).thenReturn(2);
        instance.setServiceCapacity(3);
        
        assertFalse(instance.isFull());
    }


    @Test
    public void getConversions_shouldDelegateToRepository() throws ExceededCapacity {
        Collection<Conversion> mockResult = mock(Collection.class);
        when(conversionRepository.getConversions())
            .thenReturn(mockResult);
        assertTrue(instance.getConversions() == mockResult);
    }

    @Test
    public void getCompletedConversions_shouldDelegateToRepository() throws ExceededCapacity {
        Collection<Conversion> mockResult = mock(Collection.class);
        when(conversionRepository.getConversionsWithStatus(eq(ConversionStatus.Completed)))
            .thenReturn(mockResult);
        assertTrue(instance.getCompletedConversions() == mockResult);
    }

    @Test
    public void getConversionForId_shouldDelegateToRepository() throws ExceededCapacity {
        instance.getConversionForId("1234");
        verify(conversionRepository).getConversion("1234");
    }

    private Conversion createTestConversion(String form, String to, String inputFile) {
        return new Conversion().setFrom(new LanguageVersionImpl(form,new VersionImpl(1, 0, 0))).setTo(new LanguageVersionImpl(to,new VersionImpl(1, 0, 0))).setInputFileName(inputFile);
    }
}
