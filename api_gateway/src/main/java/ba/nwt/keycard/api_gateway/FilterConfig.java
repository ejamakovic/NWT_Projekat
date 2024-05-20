package ba.nwt.keycard.api_gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ba.nwt.keycard.api_gateway.Logs.RequestLoggingFilter;

@Configuration
public class FilterConfig {

    @Bean
    public RequestLoggingFilter requestLoggingFilter() {
        return new RequestLoggingFilter();
    }
}