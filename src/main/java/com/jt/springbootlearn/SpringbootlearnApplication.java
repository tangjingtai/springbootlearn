package com.jt.springbootlearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

/**
 *
 */
//@ImportResource(locations = {"classpath:beans.xml"})
@SpringBootApplication
public class SpringbootlearnApplication {

    public static void main(String[] args) {
        // Spring应用程序启动
        SpringApplication.run(SpringbootlearnApplication.class, args);

    }

    @Bean
    public ViewResolver myViewResolver() {
        return new MyViewResolver();
    }

    private static class MyViewResolver implements ViewResolver {

        /**
         * Resolve the given view by name.
         * <p>Note: To allow for ViewResolver chaining, a ViewResolver should
         * return {@code null} if a view with the given name is not defined in it.
         * However, this is not required: Some ViewResolvers will always attempt
         * to build View objects with the given name, unable to return {@code null}
         * (rather throwing an exception when View creation failed).
         *
         * @param viewName name of the view to resolve
         * @param locale   the Locale in which to resolve the view.
         *                 ViewResolvers that support internationalization should respect this.
         * @return the View object, or {@code null} if not found
         * (optional, to allow for ViewResolver chaining)
         * @throws Exception if the view cannot be resolved
         *                   (typically in case of problems creating an actual View object)
         */
        @Nullable
        @Override
        public View resolveViewName(String viewName, Locale locale) throws Exception {
            return null;
        }
    }
}
