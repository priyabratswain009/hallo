package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeliveryDocumentsMapperTest {

    private DeliveryDocumentsMapper deliveryDocumentsMapper;

    @BeforeEach
    public void setUp() {
        deliveryDocumentsMapper = new DeliveryDocumentsMapperImpl();
    }
}
