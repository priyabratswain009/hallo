package com.sunknowledge.dme.rcm.domain.core;

import com.sunknowledge.dme.rcm.domain.token.TokenResponseOutput;

import lombok.Data;

@Data
public class TokenOutCome {
	private TokenResponseOutput tokenResponseOutput;
	private Boolean outcome;
	private String message;
}
