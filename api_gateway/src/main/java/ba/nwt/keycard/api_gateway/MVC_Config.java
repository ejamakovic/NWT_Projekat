package ba.nwt.keycard.api_gateway;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ba.nwt.keycard.api_gateway.MVC_Inteceptor.LoggerInterceptor;

@Configuration
public class MVC_Config implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerInterceptor());
    }

}
