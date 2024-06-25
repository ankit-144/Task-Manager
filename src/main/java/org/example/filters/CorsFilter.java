package org.example.filters;

import org.example.TodoApplicationConfiguration;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;

public class CorsFilter implements ContainerResponseFilter {


    private final TodoApplicationConfiguration.CorsConfiguration corsConfiguration;

    public CorsFilter(TodoApplicationConfiguration.CorsConfiguration corsConfiguration) {
        this.corsConfiguration = corsConfiguration;
    }
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "http://localhost:3000");
        responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        responseContext.getHeaders().add("Access-Control-Allow-Headers", "Content-Type, Authorization");
        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders().add("Access-Control-Max-Age", "86400"); // 24 hours
    }
}
