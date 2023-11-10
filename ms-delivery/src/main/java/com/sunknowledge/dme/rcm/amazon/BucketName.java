package com.sunknowledge.dme.rcm.amazon;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BucketName {
    TODO_IMAGE("dme-s3-bucket");
    private final String bucketName;
}
