package ba.nwt.keycard.api_gateway.Logs;

import java.text.SimpleDateFormat;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class RequestLoggingFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // datetime
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = formatter.format(System.currentTimeMillis());

        // microservice
        Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        String routeId = route != null ? route.getId() : "unknown";

        // user
        String user = "unknown";
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            // FINISH LATER
            /*
             * String token = authHeader.substring(7);
             * Claims claims =
             * Jwts.parser().setSigningKey("secretkey").parseClaimsJws(jwt).getBody();
             * user = claims.getSubject();
             */
        }

        // action type
        String actionType = exchange.getRequest().getMethod().name();

        System.out.println("Date: " + date);
        System.out.println("Microservice: " + routeId);
        System.out.println("User: " + user);
        System.out.println("Action: " + actionType);
        System.out.println("Resource: " + exchange.getRequest().getURI());

        // finish as pre-filter
        // return chain.filter(exchange);

        // finish as post-filter with callback
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            // System.out.println("Response: " + exchange.getResponse().getStatusCode());
            System.out.println("Response: " + exchange.getResponse().getStatusCode().value());
        }));
    }

    @Override
    public int getOrder() {
        return -1; // The order of this filter. Lower values have higher priority.
    }
}