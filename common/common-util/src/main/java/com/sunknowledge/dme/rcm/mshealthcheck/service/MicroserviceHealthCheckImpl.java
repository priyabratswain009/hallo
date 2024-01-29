package com.sunknowledge.dme.rcm.mshealthcheck.service;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.application.properties.AsyncConfigurationSetup;
import com.sunknowledge.dme.rcm.application.properties.FileConfigUtility;
import com.sunknowledge.dme.rcm.commonutil.AccessTokenUtilities;
import com.sunknowledge.dme.rcm.mshealthcheck.pojo.HealthCheckMain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class MicroserviceHealthCheckImpl implements MicroserviceHealthCheck {
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private FileConfigUtility fileConfigUtility;

    @Override
    public Mono<Boolean> microserviceHealthCheck(String microserviceName) {
        String token = AccessTokenUtilities.getOtherwaytoFindAccessToken();
        String url = fileConfigUtility.getProperty("api-gateway.ip") + "/gateway/management/health";
        log.info("===========Health Check=====url============>"+url);
        Mono<Boolean> result = webClientBuilder.build().get()
                .uri(url)
                .header("Authorization", "Bearer " + token)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<HealthCheckMain>() {})
                        .map(res -> res.getComponents().getDiscoveryComposite().getComponents().getDiscoveryClient().getDetails().getServices().contains(microserviceName));
        log.info("===========Health Check=====microserviceName======4444======>"+microserviceName);
        return result;
    }

    @Override
    public Boolean microserviceHealthCheckNonReactive(String microserviceName) throws ExecutionException, InterruptedException {
        String token = AccessTokenUtilities.getOtherwaytoFindAccessToken();
        String url = fileConfigUtility.getProperty("api-gateway.ip") + "/gateway/management/health";
        log.info("===========Health Check=====url============>"+url);
        return webClientBuilder.build().get()
            .uri(url)
            .header("Authorization", "Bearer " + token)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<HealthCheckMain>() {})
            .map(res -> {
                if(res.getComponents().getDiscoveryComposite().getComponents().getDiscoveryClient().getDetails().getServices().contains(microserviceName)) {
                    log.info("===========Health Check=====microserviceName======4444======>"+microserviceName);
                    log.info("===========Health Check=====microserviceName======1111======>"+res.getComponents().getDiscoveryComposite().getComponents().getDiscoveryClient().getDetails().getServices());
                    return true;
                }
                else {
                    log.info("===========Health Check===ELSE==microserviceName======4444======>"+microserviceName);
                    return false;
                }
            }).toFuture().get();
    }

    @Override
    @Async(AsyncConfigurationSetup.TASK_EXECUTOR_SERVICE)
    public CompletableFuture<ServiceOutcome<Boolean>> microserviceHealthCheckCompletableFuture(String microserviceName) {
        String token = AccessTokenUtilities.getOtherwaytoFindAccessToken();
        String url = fileConfigUtility.getProperty("api-gateway.ip") + "/gateway/management/health";
        log.info("===========Health Check=====url============>" + url);
        return webClientBuilder.build().get()
            .uri(url)
            .header("Authorization", "Bearer " + token)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
            .retrieve()
            .bodyToMono(HealthCheckMain.class)
            .map(healthCheckMain -> {
                boolean isHealthy = healthCheckMain != null && healthCheckMain.getComponents().getDiscoveryComposite().getComponents().getDiscoveryClient().getDetails().getServices().contains(microserviceName);
                return new ServiceOutcome<>(isHealthy, isHealthy, "Health check completed");
            })
            .toFuture();
    }
}
