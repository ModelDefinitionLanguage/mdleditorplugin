package com.mango.mif.core.api;

import com.mango.mif.core.resource.ResourceCopier;
import com.mango.mif.core.resource.ResourceCopierParameters;

/**
 * A factory for creating ResourceCopier objects.
 */
public interface ResourceCopierFactory {
	ResourceCopier create(ResourceCopierParameters resourceCopierParameters);
}
