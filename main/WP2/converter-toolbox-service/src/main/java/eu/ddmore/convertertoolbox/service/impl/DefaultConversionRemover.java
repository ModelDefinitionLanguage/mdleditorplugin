package eu.ddmore.convertertoolbox.service.impl;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

import eu.ddmore.convertertoolbox.domain.Conversion;
import eu.ddmore.convertertoolbox.domain.ConversionStatus;
import eu.ddmore.convertertoolbox.service.ConversionRepository;

/**
 * Default implementation of the conversion remover
 */
@Component
@Qualifier("conversionRemover")
public class DefaultConversionRemover implements ConversionRemover {

    private final ConversionRepository conversionRepository;

    @Autowired(required=true)
    public DefaultConversionRemover(ConversionRepository conversionRepository) {
        super();
        this.conversionRepository = conversionRepository;
    }

    @Override
    public void remove(Conversion conversion) {
        Preconditions.checkNotNull(conversion, "Conversoin was null");
        Optional<Conversion> internalConversion = conversionRepository.getConversion(conversion.getId());
        
        if(!internalConversion.isPresent()) {
            throw new IllegalStateException(String.format("Requested deletion of non-existing conversion %s",conversion.getId()));
        }
        
        if(!ConversionStatus.Completed.equals(internalConversion.get().getStatus())) {
            throw new IllegalStateException(String.format("Requested deletion of uncompleted conversion %s",conversion.getId()));
        }
        
        try {
            if(conversion.getWorkingDirectory()!=null && conversion.getWorkingDirectory().exists()) {
                FileUtils.deleteDirectory(conversion.getWorkingDirectory());
            }
        } catch (IOException e) {
            throw new RuntimeException(String.format("Conversion [%s] Could not remove working directory %s", conversion.getId(), conversion.getWorkingDirectory().getAbsolutePath()),e);
        }
        
        conversionRepository.delete(conversion);
    }

}
