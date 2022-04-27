package com.nhnacademy.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*", initParams = {
@WebInitParam(name = "whitelist", value = "/login," + "/logout," +"/loginForm.html,"+"/index.html,"+"/")
})
public class LoginCheckFilter implements Filter {
    List<String> urls = new ArrayList<>();
    String uri;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String[] url = filterConfig.getInitParameter("whitelist").split(",");
        Arrays.stream(url).forEach(s-> urls.add(s));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        String requestURI = ((HttpServletRequest)servletRequest).getRequestURI();
        if(!urls.contains(requestURI)){
            HttpSession session = ((HttpServletRequest)servletRequest).getSession(false);
            uri = requestURI;

            if(Objects.isNull(session)){
                RequestDispatcher rd = servletRequest.getRequestDispatcher("/loginForm.html");
                rd.forward(servletRequest, servletResponse);
        }

        }
        servletRequest.setAttribute("uri", uri);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
