package com.mango.mif.core.api;

import com.mango.mif.core.resource.ResourcePublisher;
import com.mango.mif.core.resource.shell.PublisherParameters;

/**
 * The Interface ResourcePublisherfactory.
 */
public interface ResourcePublisherfactory {
	ResourcePublisher create(PublisherParameters publisherParameters);
}
