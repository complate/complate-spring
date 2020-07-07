/**
 * Copyright 2019-2020 complate.org
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

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.complate.core.ComplateRenderer;
import org.complate.graal.GraalComplateRenderer;
import org.complate.spring.source.ResourceComplateSource;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

class ComplateViewTests {

    @Test
    void new_withNullRenderer_throwsException() {
        // when
        ThrowingCallable createView = () -> new ComplateView(null, "some-view");

        // then
        assertThatThrownBy(createView)
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("renderer must not be null");
    }

    @Test
    void new_withNullView_throwsException() {
        // when
        ThrowingCallable createView = () -> new ComplateView(mock(ComplateRenderer.class), null);

        // then
        assertThatThrownBy(createView)
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("view must not be null");
    }

    @Test
    void getContentType_returnsTextHtmlUtf8() {
        // given
        ComplateView sut = new ComplateView(mock(ComplateRenderer.class), "some-view");

        // when
        String contentType = sut.getContentType();

        // then
        assertThat(contentType)
            .isEqualTo("text/html; charset=UTF-8");
    }

    @Nested
    class WithExistingSource {

        ComplateRenderer renderer = GraalComplateRenderer
            .of(new ResourceComplateSource(new ClassPathResource("/test_bundle.js")))
            .withBinding("constantBinding", "World")
            .withBinding("functionBinding", new FunctionBinding())
            .build();

        Map<String, Object> model = new HashMap<>();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        @Test
        void render_setsCorrectContentType() throws Exception {
            // given
            ComplateView sut = new ComplateView(renderer, "SomeView");

            // when
            sut.render(model, request, response);

            // then
            assertThat(response.getContentType())
                .isEqualTo("text/html; charset=UTF-8");
        }

        @Test
        void render_rendersCorrectView() throws Exception {
            // given
            ComplateView sut = new ComplateView(renderer, "StaticView");

            // when
            sut.render(model, request, response);

            // then
            assertThat(response.getContentAsString())
                .isEqualTo("Render view: StaticView");
        }

        @Test
        void render_hasAccessToModel() throws Exception {
            // given
            ComplateView sut = new ComplateView(renderer, "ModelView");
            model.put("title", "ვეპხის ტყაოსანი შოთა რუსთაველი");

            // when
            sut.render(model, request, response);

            // then
            assertThat(response.getContentAsString())
                .isEqualTo("View ModelView with params.title=ვეპხის ტყაოსანი შოთა რუსთაველი");
        }

        @Test
        void render_hasAccessToBindings() throws Exception {
            // given
            ComplateView sut = new ComplateView(renderer, "BindingView");

            // when
            sut.render(model, request, response);

            // then
            assertThat(response.getContentAsString())
                .isEqualTo("View BindingView with functionBinding.greet=Hello, World!");
        }
    }

    public static final class FunctionBinding {

        public String greet(String name) {
            return "Hello, " + name + "!";
        }
    }
}
