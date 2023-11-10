package com.sunknowledge.dme.rcm.domain.core;

import com.sunknowledge.dme.rcm.domain.Coverage.CoverageOutput;
import com.sunknowledge.dme.rcm.domain.token.TokenResponseOutput;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ResultCoverageOutcome {

	private CoverageOutput CoverageOutput;
	private TokenResponseOutput tokenResponseOutput;
	private CoverageErrorHandler errorhandler;
	private AccessTokenError accessTokenError;
	public CoverageOutput getCoverageOutput() {
		return CoverageOutput;
	}
	public void setCoverageOutput(CoverageOutput CoverageOutput) {
		this.CoverageOutput = CoverageOutput;
	}
	public TokenResponseOutput getTokenResponseOutput() {
		return tokenResponseOutput;
	}
	public void setTokenResponseOutput(TokenResponseOutput tokenResponseOutput) {
		this.tokenResponseOutput = tokenResponseOutput;
	}
	public CoverageErrorHandler getErrorhandler() {
		return errorhandler;
	}
	public void setErrorhandler(CoverageErrorHandler errorhandler) {
		this.errorhandler = errorhandler;
	}
	public AccessTokenError getAccessTokenError() {
		return accessTokenError;
	}
	public void setAccessTokenError(AccessTokenError accessTokenError) {
		this.accessTokenError = accessTokenError;
	}
	
	
}
