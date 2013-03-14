package com.mango.mif.core.resource;

import com.mango.mif.core.api.ResourceCopierFactory;

/**
 * The Class ResourceCopierFactoryImpl.
 * The implementation of the ResourceCopierfactory.
 */
public class ResourceCopierFactoryImpl implements ResourceCopierFactory{

	@Override
	public ResourceCopier create(ResourceCopierParameters resourceCopierParameters) {
		return new ResourceCopierImpl(resourceCopierParameters);
	}

}
