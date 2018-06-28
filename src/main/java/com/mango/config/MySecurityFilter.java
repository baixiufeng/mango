package com.mango.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.*;
import java.io.IOException;

@Component
public class MySecurityFilter extends AbstractSecurityInterceptor implements Filter{
    @Autowired
    private CustomInvocationSecurityMetadataSourceService customInvocationSecurityMetadataSourceService;
    @Autowired
    private CustomAccessDecisionManager customAccessDecisionManager;
    @Autowired
    public AuthenticationManager authenticationManager;


    @PostConstruct
    public void init() {
        super.setAuthenticationManager(authenticationManager);
        super.setAccessDecisionManager(customAccessDecisionManager);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("------------" + getClass().getCanonicalName() + "-----------");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        FilterInvocation filterInvocation = new FilterInvocation(request, response, chain);
        invoke(filterInvocation);

    }

    private void invoke(FilterInvocation filterInvocation) throws IOException, ServletException {
        InterceptorStatusToken token = super.beforeInvocation(filterInvocation);
        try {
            filterInvocation.getChain().doFilter(filterInvocation.getRequest(), filterInvocation.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }

    @Override
    public void destroy() {
        logger.info("-----end-------" + getClass().getCanonicalName() + "------end-----");
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.customInvocationSecurityMetadataSourceService;
    }


}
