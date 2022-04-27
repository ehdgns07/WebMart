package com.nhnacademy.web;

import java.util.Set;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;

@HandlesTypes({ javax.servlet.http.HttpServlet.class,
    javax.servlet.Filter.class,
    javax.servlet.ServletContextListener.class,
    javax.servlet.http.HttpSessionListener.class})

public class WebAppInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext)
        throws ServletException {
        servletContext.setInitParameter("onion","2,1000");
        servletContext.setInitParameter("egg","5,2000");
        servletContext.setInitParameter("greenOnion","10,500");
        servletContext.setInitParameter("apple","20,2000");
        servletContext.setInitParameter("counter", "count.txt");
    }
}
