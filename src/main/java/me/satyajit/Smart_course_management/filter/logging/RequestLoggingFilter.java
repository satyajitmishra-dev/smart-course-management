package me.satyajit.Smart_course_management.filter.logging;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;

@Component
@Order(2)
public class RequestLoggingFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        System.out.println("============  Logging ==============");

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        System.out.println("Request Method Name ::" + httpServletRequest.getMethod());
        System.out.println("Request URL :: "+httpServletRequest.getRequestURI());

        chain.doFilter(httpServletRequest, httpServletResponse);
    }
}
