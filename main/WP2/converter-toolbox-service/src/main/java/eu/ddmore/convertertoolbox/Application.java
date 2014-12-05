package eu.ddmore.convertertoolbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import eu.ddmore.convertertoolbox.api.conversion.ConverterManager;
import eu.ddmore.convertertoolbox.conversion.ConverterManagerImpl;
import eu.ddmore.convertertoolbox.service.impl.ConversionServiceConfiguration;

@ComponentScan
@EnableAutoConfiguration
@Configuration
@Import(ConversionServiceConfiguration.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ConverterManager converterManager() {
        ConverterManagerImpl converterManager =  new ConverterManagerImpl();
        converterManager.discoverConverters();
        return converterManager;
    }
    
}
