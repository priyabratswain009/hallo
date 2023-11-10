package com.sunknowledge.dme.rcm.dto.icd;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ICDResultOutcome {
	private String context;
	private String id;
	private List<String> parent;
	private List<String> child;
	private String browserUrl;
	private String code;
	private String titleValue;
	private List<String> inclusionValues;
	private List<String> exclusionValues;
	private List<String> codingHints;
	private String classKind;
	private List<String> diseasesList;
}
