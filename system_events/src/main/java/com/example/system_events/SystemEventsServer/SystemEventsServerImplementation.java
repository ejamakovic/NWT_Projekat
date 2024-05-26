package com.example.system_events.SystemEventsServer;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.system_events.SystemEventsGrpc;
import com.example.system_events.SystemEventsRequest;
import com.example.system_events.SystemEventsResponse;
import com.example.system_events.Services.LogService;
import com.example.system_events.Models.Log;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class SystemEventsServerImplementation extends SystemEventsGrpc.SystemEventsImplBase {

    private final LogService logService;

    @Autowired
    public SystemEventsServerImplementation(LogService logService) {
        this.logService = logService;
    }

    @Override
    public void getSystemEvents(SystemEventsRequest request, StreamObserver<SystemEventsResponse> responseObserver) {
        // Save a log
        System.out.println(request);

        Log log = new Log();
        log.setDate(request.getDate());
        log.setMicroservice(request.getMicroservice());
        log.setUser(request.getUser());
        log.setAction(request.getAction());
        log.setResource(request.getResource());
        log.setResponse(Integer.parseInt(request.getResponse()));

        System.out.println("Adding to log to db: " + log.toString());
        logService.addLog(log);
        System.out.println("Log added to db");

        SystemEventsResponse response = SystemEventsResponse.newBuilder()
                .setStatus("Success")
                .build();

        System.out.println(response);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}