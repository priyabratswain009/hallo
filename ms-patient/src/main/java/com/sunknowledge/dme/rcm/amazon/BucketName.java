package com.sunknowledge.dme.rcm.amazon;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BucketName {
    BUCKET_NAME("dme-s3-bucket","dme-patient-service", "dme-salesorder-service");

    private final String defaultBucket;
    private final String patientServiceBucket;
    private final String soServiceBucket;
}
