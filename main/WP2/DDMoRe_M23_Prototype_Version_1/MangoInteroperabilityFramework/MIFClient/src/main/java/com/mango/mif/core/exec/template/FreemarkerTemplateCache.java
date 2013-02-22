/**
 * Copyright Mango Solutions, 2012.
 * <p>SVN Entry: $HeadURL: $
 * <p>SVN ID: $Id: $
 * <p>Last edited by : $Author$
 * @author Tony Bamford &lt;tbamford@mango-solutions.com&gt;
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.core.exec.template;

import java.io.File;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mango.mif.core.exec.ExecutionException;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * The purpose of this class is to be a singleton and to cache Freemarker templates based on their full paths.
 */
public class FreemarkerTemplateCache {

    private static FreemarkerTemplateCache singleton;

    private final LoadingCache<String, Template> timedTemplateCache;

    private FreemarkerTemplateCache() {
        timedTemplateCache = CacheBuilder.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build(
                        new CacheLoader<String, Template>() {
                            public Template load(String fullPath) throws Exception {
                                FileTemplateLoader ftl = new FileTemplateLoader(new File(fullPath).getParentFile());
                                Configuration freeMarkerConfig = new Configuration();
                                freeMarkerConfig.setTemplateLoader(ftl);
                                return freeMarkerConfig.getTemplate(new File(fullPath).getName());
                            }
                        }
                        );
    }

    public static synchronized FreemarkerTemplateCache getInstance() {
        if (singleton == null) {
            singleton = new FreemarkerTemplateCache();
        }
        return singleton;
    }

    public Template get(String fullPath) throws ExecutionException {
        try {
            return timedTemplateCache.get(fullPath);
        } catch (Exception e) {
            throw new ExecutionException("Caught exception while retrieving template from cache via path " + fullPath, e);
        }
    }
}
