package com.hazem.jakarta.demo2;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Logger;

@WebFilter("/*")
public class LoggingFilter implements Filter {
  private static final Logger LOGGER = Logger.getLogger(LoggingFilter.class.getName());

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    LOGGER.info("Logging Filter has been initialized");
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;

    LOGGER.info(
        String.format(
            "Request details => Method: %s, Path: %s, URL: %s, ",
            request.getMethod(),
            request.getServletPath(),
            request.getRequestURL()
        )
    );

    filterChain.doFilter(servletRequest, servletResponse);

    LOGGER.info("Response sent for: " + request.getRequestURI());
  }

  @Override
  public void destroy() {
    LOGGER.info("Logging Filter has been destroyed");
  }
}
