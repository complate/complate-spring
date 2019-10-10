package org.complate.spring.mvc;

import org.complate.core.ComplateRenderer;
import org.complate.core.ComplateStream;
import org.complate.servlet.ComplateHttpServletResponseStream;
import org.springframework.util.Assert;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
