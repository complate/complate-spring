package org.complate.spring.source;

import org.complate.core.ComplateSource;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;

/**
 * {@link Resource} based {@link ComplateSource}.
 *
 * @author mvitz
 * @since 0.1.0
 */
public class ResourceComplateSource implements ComplateSource {

    private final Resource resource;

    public ResourceComplateSource(Resource resource) {
        Assert.notNull(resource, "resource must not be null");
        this.resource = resource;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return resource.getInputStream();
    }

    @Override
    public String getDescription() {
        return resource.getDescription();
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
