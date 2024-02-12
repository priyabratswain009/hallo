package com.sunknowledge.dme.rcm.service.dto.soelligibility;

import lombok.Data;

@Data
public class TokenOutCome {
	private TokenResponseOutput tokenResponseOutput;
	private Boolean outcome;
	private String message;
}
