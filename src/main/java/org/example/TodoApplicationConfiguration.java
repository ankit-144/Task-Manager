package org.example;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class TodoApplicationConfiguration extends Configuration {

    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @NotNull
    @JsonProperty("cors") // Added JSON property for CORS configuration
    private CorsConfiguration corsConfiguration = new CorsConfiguration(); // Added default initialization for CORS configuration

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory factory) {
        this.database = factory;
    }

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    @JsonProperty("cors") // Getter for CORS configuration
    public CorsConfiguration getCorsConfiguration() {
        return corsConfiguration;
    }

    @JsonProperty("cors") // Setter for CORS configuration
    public void setCorsConfiguration(CorsConfiguration corsConfiguration) {
        this.corsConfiguration = corsConfiguration;
    }

    // Inner class for CORS configuration
    public static class CorsConfiguration {

        @NotEmpty
        @JsonProperty
        private String allowedOrigins = "http://localhost:3000";

        @NotEmpty
        @JsonProperty
        private String allowedMethods = "GET, POST, PUT, DELETE, OPTIONS";

        @NotEmpty
        @JsonProperty
        private String allowedHeaders = "Content-Type, Authorization";

        @JsonProperty
        private String exposedHeaders;

        @JsonProperty
        private boolean allowCredentials;

        @JsonProperty
        private int maxAge = 86400;

        // Getter and setter methods for CORS configuration properties
        // You can add additional properties as needed

        public String getAllowedOrigins() {
            return allowedOrigins;
        }

        public void setAllowedOrigins(String allowedOrigins) {
            this.allowedOrigins = allowedOrigins;
        }

        public String getAllowedMethods() {
            return allowedMethods;
        }

        public void setAllowedMethods(String allowedMethods) {
            this.allowedMethods = allowedMethods;
        }

        public String getAllowedHeaders() {
            return allowedHeaders;
        }

        public void setAllowedHeaders(String allowedHeaders) {
            this.allowedHeaders = allowedHeaders;
        }

        public String getExposedHeaders() {
            return exposedHeaders;
        }

        public void setExposedHeaders(String exposedHeaders) {
            this.exposedHeaders = exposedHeaders;
        }

        public boolean isAllowCredentials() {
            return allowCredentials;
        }

        public void setAllowCredentials(boolean allowCredentials) {
            this.allowCredentials = allowCredentials;
        }

        public int getMaxAge() {
            return maxAge;
        }

        public void setMaxAge(int maxAge) {
            this.maxAge = maxAge;
        }
    }
}
