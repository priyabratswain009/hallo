package com.sunknowledge.dme.rcm.dto.icd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestICDInput {
	private String releaseId;
	private String code;
//	private String apiVersion;
//	private String acceptLanguage;
}
