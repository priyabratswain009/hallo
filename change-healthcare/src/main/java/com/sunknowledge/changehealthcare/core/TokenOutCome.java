package com.sunknowledge.changehealthcare.core;

import com.sunknowledge.changehealthcare.pojo.TokenResponseOutput;

import lombok.Data;

@Data
public class TokenOutCome {
	
	private TokenResponseOutput tokenResponseOutput;
	private Boolean outcome;
	private String message;

}
