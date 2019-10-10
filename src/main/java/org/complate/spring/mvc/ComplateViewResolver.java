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
