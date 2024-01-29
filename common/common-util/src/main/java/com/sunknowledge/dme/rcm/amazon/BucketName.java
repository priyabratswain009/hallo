package com.sunknowledge.dme.rcm.amazon;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BucketName {
    BUCKET_NAME("dme-s3-bucket","dme-patient-service", "dme-salesorder-service", "dme-item-service", "dme-delivery-service", "dme-claims-service");
    private final String defaultBucket;
    private final String patientServiceBucket;
    private final String soServiceBucket;
    private final String itemServiceBucket;
    private final String deliveryServiceBucket;
    private final String claimsServiceBucket;
}
