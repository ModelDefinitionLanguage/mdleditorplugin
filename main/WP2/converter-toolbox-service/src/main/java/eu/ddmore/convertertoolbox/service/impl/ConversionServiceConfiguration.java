/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.google.common.base.Preconditions;


/**
 * Holds context configuration for Conversion Service beans
 */
@Configuration
public class ConversionServiceConfiguration {
    
    @Bean
    public TaskExecutor conversionTaskExecutor(@Value("${cts.serviceCapacity}") int capacity,
            @Value("${cts.parallelConversions}") int threadPool) {
        Preconditions.checkArgument(capacity>threadPool, "service capacity is smaller than thread pool, it doesn't make sense. Please fix application configuration.");
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setDaemon(true);
        taskExecutor.setMaxPoolSize(threadPool);
        taskExecutor.setCorePoolSize(threadPool);
        taskExecutor.setQueueCapacity(capacity-threadPool);
        taskExecutor.initialize();
        return taskExecutor;
    }
}
