package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeliveryAssignmentMapperTest {

    private DeliveryAssignmentMapper deliveryAssignmentMapper;

    @BeforeEach
    public void setUp() {
        deliveryAssignmentMapper = new DeliveryAssignmentMapperImpl();
    }
}
