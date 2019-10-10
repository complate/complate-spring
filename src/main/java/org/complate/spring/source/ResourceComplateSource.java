/**
 * Copyright 2019 complate.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
