package ba.nwt.keycard.api_gateway.Logs;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;

import com.example.system_events.SystemEventsGrpc;
import com.example.system_events.SystemEventsRequest;
import com.example.system_events.SystemEventsResponse;

import reactor.core.publisher.Mono;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class RequestLoggingFilter implements GlobalFilter, Ordered {
    private int grpcPort = 6565;

    private final ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", grpcPort)
            .usePlaintext()
            .build();
    private final SystemEventsGrpc.SystemEventsBlockingStub stub = SystemEventsGrpc.newBlockingStub(channel);

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

            SystemEventsRequest request = SystemEventsRequest.newBuilder()
                    .setDate("2022-01-01")
                    .setMicroservice("api_gateway")
                    .setUser("user1")
                    .setAction("action1")
                    .setResource("resource1")
                    .setResponse("response1")
                    .build();

            // System.out.println("Stub: " + stub.toString());
            SystemEventsResponse response = stub.getSystemEvents(request);
            System.out.println("Response from gRPC server: " +
                    response.getStatus());

        }));
    }

    @Override
    public int getOrder() {
        return -1; // The order of this filter. Lower values have higher priority.
    }
}