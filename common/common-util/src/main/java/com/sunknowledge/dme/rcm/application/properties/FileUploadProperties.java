package com.sunknowledge.dme.rcm.application.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//@ConfigurationProperties(prefix = "file.upload")
@Component
@ConfigurationProperties
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadProperties {
    private String location;
}
