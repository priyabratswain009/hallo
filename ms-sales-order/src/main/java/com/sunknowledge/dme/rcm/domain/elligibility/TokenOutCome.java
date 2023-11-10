package com.sunknowledge.dme.rcm.domain.elligibility;

import lombok.Data;

@Data
public class TokenOutCome {
	private TokenResponseOutput tokenResponseOutput;
	private Boolean outcome;
	private String message;
}
