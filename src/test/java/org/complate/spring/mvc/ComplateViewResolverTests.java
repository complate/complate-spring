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
import org.complate.core.ComplateStream;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.View;

import static java.util.Collections.emptyMap;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ComplateViewResolverTests {

    @Test
    void new_withNullRenderer_throwsException() {
        // when
        ThrowingCallable createViewResolver = () -> new ComplateViewResolver(null);

        // then
        assertThatThrownBy(createViewResolver)
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("renderer must not be null");
    }

    @Test
    void resolveViewName_returnsViewWithConfiguredRendererAndGivenViewName() throws Exception {
        // given
        ComplateRenderer renderer = mock(ComplateRenderer.class);

        ComplateViewResolver sut = new ComplateViewResolver(renderer);

        // when
        View view = sut.resolveViewName("some-view", null);

        // then
        view.render(emptyMap(), new MockHttpServletRequest(), new MockHttpServletResponse());
        verify(renderer, only()).render(eq("some-view"), eq(emptyMap()), any(ComplateStream.class));
    }
}
