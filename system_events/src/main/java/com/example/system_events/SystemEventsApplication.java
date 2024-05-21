package com.example.system_events;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;

import com.example.system_events.SystemEventsServer.SystemEventsServerImplementation;

import io.grpc.Server;
import io.grpc.ServerBuilder;

@SpringBootApplication
public class SystemEventsApplication {

	@Value("${grpc.server.port}")
	private int grpcPort;

	private Server grpcServer;

	public static void main(String[] args) {
		SpringApplication.run(SystemEventsApplication.class, args);
	}

	@Bean(initMethod = "start")
	public Server grpcServer() {
		grpcServer = ServerBuilder
				.forPort(grpcPort)
				.addService(new SystemEventsServerImplementation())
				.build();

		// Start the server and block until it is shut down
		try {
			grpcServer.start();
			grpcServer.awaitTermination();
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException(e);
		}

		return grpcServer;
	}
}