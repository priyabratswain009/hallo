package com.sunknowledge.dme.rcm.core;

import com.sunknowledge.dme.rcm.pojo.TokenResponseOutput;
import lombok.Data;

@Data
public class TokenOutCome {
	private TokenResponseOutput tokenResponseOutput;
	private Boolean outcome;
	private String message;
}
