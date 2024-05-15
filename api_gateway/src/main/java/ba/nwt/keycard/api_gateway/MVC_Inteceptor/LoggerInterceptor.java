package ba.nwt.keycard.api_gateway.MVC_Inteceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoggerInterceptor implements HandlerInterceptor {
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("Inside the LoggerInterceptor...");
        return true;
    }

    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("Inside the LoggerInterceptor...");
    }
}