package com.sunknowledge.dme.rcm.mshealthcheck.service;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface MicroserviceHealthCheck {
    Mono<Boolean> microserviceHealthCheck(String microserviceName) throws ExecutionException, InterruptedException;
    Boolean microserviceHealthCheckNonReactive(String microserviceName) throws ExecutionException, InterruptedException;
    CompletableFuture<ServiceOutcome<Boolean>> microserviceHealthCheckCompletableFuture(String microserviceName) throws ExecutionException, InterruptedException;
}
