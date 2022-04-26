package com.nhnacademy.web.filter;

import java.io.IOException;
import java.util.Locale;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter(filterName = "counterFilter", urlPatterns = "/*", initParams = {
    @WebInitParam(name = "whitelist", value ="/cart,/foods")
})
public class CounterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        String[] url = filterConfig.getFilterName()
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        String requestURI = ((HttpServletRequest)servletRequest).getRequestURI();
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
