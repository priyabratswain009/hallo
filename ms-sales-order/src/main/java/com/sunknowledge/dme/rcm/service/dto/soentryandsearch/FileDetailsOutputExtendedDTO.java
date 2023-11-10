package com.sunknowledge.dme.rcm.service.dto.soentryandsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileDetailsOutputExtendedDTO {
    private String fileName;

    private String filePath;

    private Long fileSize;

    private String fileType;
}
