package com.example.system_events.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "logs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Date must not be null")
    @Column(name = "date")
    private String date;

    @NotNull(message = "Microservice must not be null")
    @Column(name = "microservice")
    private String microservice;

    @NotNull(message = "User must not be null")
    @Column(name = "user")
    private String user;

    @NotNull(message = "ActionType must not be null")
    @Column(name = "actionType")
    private String action;

    @NotNull(message = "Resource must not be null")
    @Column(name = "resource")
    private String resource;

    @NotNull(message = "Status must not be null")
    @Column(name = "status")
    private int response;

    // getters and setters...
}