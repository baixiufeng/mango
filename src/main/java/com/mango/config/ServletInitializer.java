package com.mango.config;

import com.mango.Application;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class ServletInitializer extends SpringBootServletInitializer{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        FilterRegistration.Dynamic openEntityManagerInViewFilter = servletContext.addFilter("openEntityManagerInViewFilter", OpenEntityManagerInViewFilter.class);
        openEntityManagerInViewFilter.setInitParameter("entityManagerFactoryBeanName", "entityManagerFactory");
        openEntityManagerInViewFilter.addMappingForServletNames(null, false, "/*");
        super.onStartup(servletContext);
    }
}
