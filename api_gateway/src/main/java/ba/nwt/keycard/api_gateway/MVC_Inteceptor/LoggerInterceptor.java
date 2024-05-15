package ba.nwt.keycard.api_gateway.MVC_Inteceptor;

import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoggerInterceptor implements HandlerInterceptor {
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        try {
            PrintStream fileOut = new PrintStream("./out.txt");
            System.setOut(fileOut);
            System.out.println("This goes to out.txt");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return true;

    }

    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("Inside the LoggerInterceptor...");
    }
}