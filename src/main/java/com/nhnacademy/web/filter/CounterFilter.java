package com.nhnacademy.web.filter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    List<String> uri = new ArrayList<>();
    Integer counter;
    String path;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String[] url = filterConfig.getInitParameter("whitelist").split(",");
        Arrays.stream(url).forEach(s->uri.add(s));
        path = filterConfig.getInitParameter("");
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("/count.txt")))){
            String readCount = reader.readLine();
            counter = Integer.parseInt(readCount);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        String requestURI = ((HttpServletRequest)servletRequest).getRequestURI();

        if(uri.contains(requestURI)){
            counter++;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        URL url = this.getClass().getClassLoader().getResource("count.txt");
        File file = null;
        try {
            file = Paths.get(url.toURI()).toFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try(FileWriter fileWriter=new FileWriter(file, false)){
            fileWriter.write(counter.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
