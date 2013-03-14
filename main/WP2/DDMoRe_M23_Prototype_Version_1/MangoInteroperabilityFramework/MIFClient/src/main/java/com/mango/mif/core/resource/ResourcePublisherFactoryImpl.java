package com.mango.mif.core.resource;

import com.mango.mif.core.api.ResourcePublisherfactory;
import com.mango.mif.core.resource.shell.PublisherParameters;
import com.mango.mif.core.resource.shell.ShellBasedResourcePublisher;

/**
 * The Class ResourcePublisherFactoryImpl.
 * Responsible for creating the resourcePublisher Instances
 */
public class ResourcePublisherFactoryImpl implements ResourcePublisherfactory {
	
	@Override
	public ResourcePublisher create(PublisherParameters publisherParameters) {
		return new ShellBasedResourcePublisher(publisherParameters);
	}
}
