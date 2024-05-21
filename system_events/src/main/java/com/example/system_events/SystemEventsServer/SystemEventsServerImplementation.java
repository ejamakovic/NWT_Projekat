package com.example.system_events.SystemEventsServer;

import com.example.system_events.SystemEventsGrpc;
import com.example.system_events.SystemEventsRequest;
import com.example.system_events.SystemEventsResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class SystemEventsServerImplementation extends SystemEventsGrpc.SystemEventsImplBase {

    @Override
    public void getSystemEvents(SystemEventsRequest request, StreamObserver<SystemEventsResponse> responseObserver) {
        // Implement your logic here

        SystemEventsResponse response = SystemEventsResponse.newBuilder()
                .setStatus("Success")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}