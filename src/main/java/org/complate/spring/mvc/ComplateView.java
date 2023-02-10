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
import org.complate.core.ComplateStream;
import org.complate.servlet.ComplateHttpServletResponseStream;
import org.springframework.util.Assert;
import org.springframework.web.servlet.View;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * TODO: add documentation
 *
 * @author mvitz
 * @since 0.1.0
 */
public class ComplateView implements View {

    private static final String DEFAULT_CONTENT_TYPE = "text/html; charset=UTF-8";

    private final ComplateRenderer renderer;
    private final String view;

    ComplateView(ComplateRenderer renderer, String view) {
        Assert.notNull(renderer, "renderer must not be null");
        Assert.notNull(view, "view must not be null");
        this.renderer = renderer;
        this.view = view;
    }

    @Override
    public String getContentType() {
        return DEFAULT_CONTENT_TYPE;
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType(DEFAULT_CONTENT_TYPE);
        final ComplateStream stream = ComplateHttpServletResponseStream.fromResponse(response);
        renderer.render(view, model, stream);
    }
}
