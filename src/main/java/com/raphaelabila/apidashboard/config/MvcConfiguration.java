package com.raphaelabila.apidashboard.config;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry viewControllerRegistry) {
        viewControllerRegistry.addViewController("/login").setViewName("login");
        viewControllerRegistry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }



    // @Override
    // public void addViewControllers(ViewControllerRegistry viewControllerRegistry) {
    //     viewControllerRegistry.addViewController("/").setViewName("redirect:/pages/dashboard");
    // }

}
