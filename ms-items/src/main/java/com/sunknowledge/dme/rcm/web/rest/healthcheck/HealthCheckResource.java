package com.sunknowledge.dme.rcm.web.rest.healthcheck;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author Bimal K Sahoo
 * @since 27/07/2023
 */
@RestController
@RequestMapping("/api/item/healthcheck")
@Slf4j
public class HealthCheckResource {
    @ApiOperation(value = "Item Service Health Check")
    @PostMapping(value="/itemServiceHealthCheck")
    public ServiceOutcome<Boolean> itemServiceHealthCheck() {
        Boolean status = false;
        try {
            System.out.println("======================Result============================");
            status = true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("=========END=============Result============================"+status);
        return new ServiceOutcome<>(status, true, "Service is active!!!");
    }
}
