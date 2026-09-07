package me.satyajit.Smart_course_management.filter.authentication;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.satyajit.Smart_course_management.exception.UnauthorizedException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(1)
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest =
                (HttpServletRequest) request;
        HttpServletResponse httpServletResponse =
                (HttpServletResponse) response;

        String token = httpServletRequest.getHeader("app-token");

        if (token == null || !token.equals("myapptoken@1234")){
            throw new UnauthorizedException("Unauthorized access, May be token expired");
        }

        chain.doFilter(httpServletRequest, httpServletResponse);
    }
}
