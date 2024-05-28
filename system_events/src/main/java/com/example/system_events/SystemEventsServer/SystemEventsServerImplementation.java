package com.example.system_events.SystemEventsServer;

import com.example.system_events.SystemEventsGrpc;
import com.example.system_events.SystemEventsRequest;
import com.example.system_events.SystemEventsResponse;
import com.example.system_events.util.PropertiesLoader;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;

import java.sql.PreparedStatement;

@GrpcService
public class SystemEventsServerImplementation extends SystemEventsGrpc.SystemEventsImplBase {

    @Value("${spring.datasource.url}")
    String datasourceUrl;

    @Value("${spring.datasource.username}")
    String username;

    @Value("${spring.datasource.password}")
    String password;

    private static final String INSERT_LOG_SQL = "INSERT INTO logs" +
            "  (date, microservice, user, action, resource, response) VALUES " +
            " (?, ?, ?, ?, ?, ?);";

    @Override
    public void getSystemEvents(SystemEventsRequest request, StreamObserver<SystemEventsResponse> responseObserver) {

        // fetch data from request
        String date = request.getDate();
        String microservice = request.getMicroservice();
        String user = request.getUser();
        String action = request.getAction();
        String resource = request.getResource();
        String response = request.getResponse();

        String status = "Success";

        try {

            Properties conf = PropertiesLoader.loadProperties();
            String datasourceUrl = conf.getProperty("spring.datasource.url");
            String username = conf.getProperty("spring.datasource.username");
            String password = conf.getProperty("spring.datasource.password");

            Connection connection = DriverManager
                    .getConnection(datasourceUrl, username, password);

            // Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LOG_SQL);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Timestamp timestamp = new Timestamp(dateFormat.parse(date).getTime());

            preparedStatement.setTimestamp(1, timestamp);
            preparedStatement.setString(2, microservice);
            preparedStatement.setString(3, user);
            preparedStatement.setString(4, action);
            preparedStatement.setString(5, resource);
            preparedStatement.setString(6, response);

            // Execute the query or update query
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            // print exception information
            e.printStackTrace();
            status = "Failure";
        }

        SystemEventsResponse gRPCresponse = SystemEventsResponse.newBuilder()
                .setStatus(status)
                .build();

        responseObserver.onNext(gRPCresponse);
        responseObserver.onCompleted();
    }
}