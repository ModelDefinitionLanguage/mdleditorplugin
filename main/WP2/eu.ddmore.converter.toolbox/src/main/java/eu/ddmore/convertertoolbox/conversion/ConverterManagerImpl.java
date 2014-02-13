/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.conversion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

import org.apache.log4j.Logger;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Ordering;

import eu.ddmore.convertertoolbox.api.conversion.Converter;
import eu.ddmore.convertertoolbox.api.conversion.ConverterManager;
import eu.ddmore.convertertoolbox.api.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.api.domain.Version;
import eu.ddmore.convertertoolbox.api.exception.ConverterNotFoundException;
import eu.ddmore.convertertoolbox.api.spi.ConverterProvider;

/**
 * The manager provides JVM clients an entry point into the Converter Toolbox
 * and in turn to the converters held within
 */
public class ConverterManagerImpl implements ConverterManager {

    private List<Converter> converters;
    private static final Logger LOGGER = Logger.getLogger(ConverterManagerImpl.class);

    public ConverterManagerImpl() {
        discoverConverters();
    }

    public final void discoverConverters() {
        converters = new ArrayList<Converter>();
        ServiceLoader<ConverterProvider> loader = ServiceLoader.load(ConverterProvider.class);

        for (ConverterProvider provider : loader) {
            ConverterImpl converter = new ConverterImpl();
            LOGGER.info(String.format("Found Provider: %s from:[%s] to:[%s]", provider.getClass().getName(), provider.getSource(), provider.getTarget()));
            converter.setProvider(provider);
            converters.add(converter);
        }
    }

    public void setConverters(List<Converter> converters) {
        LOGGER.info(String.format("Setting converters: %s", converters));
        this.converters = converters;
    }

    @Override
    public Converter getConverter(final LanguageVersion source, final LanguageVersion target) throws ConverterNotFoundException {

        Predicate<Converter> isProper = new Predicate<Converter>() {

            @Override
            public boolean apply(Converter converter) {
                return isConversionSupported(converter, source, target);
            }
        };
        Iterable<Converter> properConverters = Iterables.filter(converters, isProper);

        Ordering<Converter> versionOrder = new Ordering<Converter>() {

            public int compare(Converter left, Converter right) {
                return left.getConverterVersion().compareTo(right.getConverterVersion());
            }
        };

        ImmutableSortedSet<Converter> sortedConverters = ImmutableSortedSet.orderedBy(versionOrder).addAll(properConverters).build();

        if (!sortedConverters.isEmpty()) {
            Converter latestFoundConverter = sortedConverters.last();
            LOGGER.info(String.format("Proper converter found: %s", latestFoundConverter));
            return latestFoundConverter;
        } else {
            throw new ConverterNotFoundException(String.format("No converter matches the given inputs [source=%s, target=%s]", source,
                target));
        }
    }

    private boolean isConversionSupported(Converter converter, LanguageVersion source, LanguageVersion target) {
        return converter.getSource().equals(source) && converter.getTarget().equals(target);
    }

    @Override
    public Converter getConverter(final LanguageVersion source, final LanguageVersion target, final Version converterVersion)
            throws ConverterNotFoundException {
        Predicate<Converter> isProper = new Predicate<Converter>() {

            @Override
            public boolean apply(Converter converter) {
                return isConversionSupported(converter, source, target, converterVersion);
            }
        };

        Iterable<Converter> properConverters = Iterables.filter(converters, isProper);
        if (properConverters.iterator().hasNext()) {
            Converter converter = properConverters.iterator().next();
            LOGGER.info(String.format("Proper converter found: %s", converter));
            return converter;
        } else {
            throw new ConverterNotFoundException(String.format("No converter matches the given inputs [source=%s, target=%s]", source,
                target));
        }
    }

    private boolean isConversionSupported(Converter converter, LanguageVersion source, LanguageVersion target, Version converterVersion) {
        return isConversionSupported(converter, source, target) && converter.getConverterVersion().equals(converterVersion);
    }

    @Override
    public Map<LanguageVersion, Collection<LanguageVersion>> getCapabilities() {
        Map<LanguageVersion, Collection<LanguageVersion>> sourceToTarget = new HashMap<LanguageVersion, Collection<LanguageVersion>>();
        for (Converter converter : converters) {
            addCapability(sourceToTarget, converter.getSource(), converter.getTarget());
        }
        return sourceToTarget;
    }

    private void addCapability(Map<LanguageVersion, Collection<LanguageVersion>> sourceToTarget, LanguageVersion source,
            LanguageVersion target) {
        LOGGER.info(String.format("Adding capability: %s -> %s", source, target));
        Collection<LanguageVersion> targets = sourceToTarget.get(source);
        if (targets == null) {
            targets = new HashSet<LanguageVersion>();
            sourceToTarget.put(source, targets);
        }
        targets.add(target);
    }

}
