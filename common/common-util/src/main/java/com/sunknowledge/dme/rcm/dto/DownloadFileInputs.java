package com.sunknowledge.dme.rcm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DownloadFileInputs {
    private String moduleName;
    private String filename;
    private String filetype;
}
