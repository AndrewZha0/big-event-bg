package com.kenmi.bigevent.bootstrap.config;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter("/*")
public class LogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        StringBuffer requestURL = servletRequest.getRequestURL();
        log.debug(servletRequest.getMethod() + " " + requestURL.toString());
        chain.doFilter(request, response);
    }
}
