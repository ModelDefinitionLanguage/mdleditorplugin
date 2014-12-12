package eu.ddmore.convertertoolbox.service.impl.conversion;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;

import eu.ddmore.convertertoolbox.domain.Conversion;
import eu.ddmore.convertertoolbox.domain.ConversionStatus;

/**
 * Performs Conversion task validation
 */
@Order(Integer.MIN_VALUE)
@Component
public class ValidateStep implements ConversionStep {

    @Override
    public void execute(ConversionContext conversionContext) {
        Conversion conversion = conversionContext.getConversion();
        Preconditions.checkState(conversion.getInputArchive()!=null,String.format("Input archive was not set for Conversion [%s]", conversion.getId()));
        Preconditions.checkState(conversion.getInputArchive().exists(),String.format("Input archive does not exist for Conversion [%s]", conversion.getId()));
        Preconditions.checkState(StringUtils.isNotBlank(conversion.getInputFileName()),String.format("Input file name not specified for Conversion [%s]", conversion.getId()));
        Preconditions.checkState(ConversionStatus.Running.equals(conversion.getStatus()),String.format("Conversion [%s] was in invalid state [%s] when reached ConversionTask ", conversion.getId(), conversion.getStatus()));
    }

}
