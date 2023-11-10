package com.sunknowledge.dme.rcm.service.dto.patientelligibility;

import lombok.Data;

@Data
public class TokenOutCome {
	private TokenResponseOutput tokenResponseOutput;
	private Boolean outcome;
	private String message;
}
