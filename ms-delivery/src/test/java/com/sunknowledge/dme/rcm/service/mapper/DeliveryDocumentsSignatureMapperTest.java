package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeliveryDocumentsSignatureMapperTest {

    private DeliveryDocumentsSignatureMapper deliveryDocumentsSignatureMapper;

    @BeforeEach
    public void setUp() {
        deliveryDocumentsSignatureMapper = new DeliveryDocumentsSignatureMapperImpl();
    }
}
