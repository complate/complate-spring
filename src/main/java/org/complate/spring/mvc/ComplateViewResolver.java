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
package org.complate.spring.mvc;

import org.complate.core.ComplateRenderer;
import org.springframework.util.Assert;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

/**
 * TODO: add documentation
 *
 * @author mvitz
 * @since 0.1.0
 */
public class ComplateViewResolver implements ViewResolver {

    private final ComplateRenderer renderer;

    public ComplateViewResolver(ComplateRenderer renderer) {
        Assert.notNull(renderer, "renderer must not be null");
        this.renderer = renderer;
    }

    @Override
    public View resolveViewName(String viewName, Locale locale) {
        return new ComplateView(renderer, viewName);
    }
}
