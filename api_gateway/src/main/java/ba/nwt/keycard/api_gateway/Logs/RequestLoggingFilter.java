package ba.nwt.keycard.api_gateway.Logs;

import java.text.SimpleDateFormat;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.web.server.ServerWebExchange;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
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
        final String[] user = new String[1];
        user[0] = "";

        // token
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String secretKey = "3cfa76ef14937c1c0ea519f8fc057a80fcd04a7420f8e8bcd0a7567c272e007b";

            // validate token
            String token = authHeader.substring(7);
            System.out.println("Token: " + token);
            System.out.println("Secret key: " + secretKey);

            // decode token
            try {
                byte[] decodedSecret = Base64.getDecoder().decode(secretKey);
                Algorithm algorithm = Algorithm.HMAC256(decodedSecret);
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedToken = verifier.verify(token);
                System.out.println("Decoded token: " + decodedToken);

                // extract fields from decodedToken
                String role = decodedToken.getClaim("role").asString();
                String username = decodedToken.getClaim("sub").asString();

                System.out.println("Role: " + role);
                System.out.println("Username: " + username);

                user[0] = username;
            } catch (JWTVerificationException e) {
                System.out.println("Invalid token " + e.getMessage());

                // Cancel the request and set the message to: "Malformed token"
                exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.UNAUTHORIZED);
                // Add the message: "Malformed token" to the response
                exchange.getResponse().getHeaders().add("Content-Type", "application/json");
                DataBuffer buffer = exchange.getResponse().bufferFactory().wrap("Malformed token".getBytes());
                return exchange.getResponse().writeWith(Mono.just(buffer));
            }

        }

        // action type
        String actionType = exchange.getRequest().getMethod().name();

        System.out.println("Date: " + date);
        System.out.println("Microservice: " + routeId);
        System.out.println("User: " + user[0]);
        System.out.println("Action: " + actionType);
        System.out.println("Resource: " + exchange.getRequest().getURI());

        // finish as pre-filter
        // return chain.filter(exchange);

        // finish as post-filter with callback
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            // System.out.println("Response: " + exchange.getResponse().getStatusCode());
            System.out.println("Response: " + exchange.getResponse().getStatusCode().value());

            SystemEventsRequest request = SystemEventsRequest.newBuilder()
                    .setDate(date)
                    .setMicroservice(routeId)
                    .setUser(user[0])
                    .setAction(actionType)
                    .setResource(exchange.getRequest().getURI().toString())
                    // convert to string
                    .setResponse(String.valueOf(exchange.getResponse().getStatusCode().value()))
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