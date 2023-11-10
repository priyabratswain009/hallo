package com.sunknowledge.dme.rcm.pojo.common;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ResultClaimSubmissionOutcome {

	private ClaimSubmissionOutput claimSubmissionOutput;
	private TokenResponseOutput tokenResponseOutput;
	private HealthCheckOutcome healthCheckOutcome;
	private ProfessionalClaimsErrorHandler errorhandler;
	private AccessTokenError accessTokenError;
	public ClaimSubmissionOutput getClaimSubmissionOutput() {
		return claimSubmissionOutput;
	}
	public void setClaimSubmissionOutput(ClaimSubmissionOutput claimSubmissionOutput) {
		this.claimSubmissionOutput = claimSubmissionOutput;
	}
	public TokenResponseOutput getTokenResponseOutput() {
		return tokenResponseOutput;
	}
	public void setTokenResponseOutput(TokenResponseOutput tokenResponseOutput) {
		this.tokenResponseOutput = tokenResponseOutput;
	}
	public HealthCheckOutcome getHealthCheckOutcome() {
		return healthCheckOutcome;
	}
	public void setHealthCheckOutcome(HealthCheckOutcome healthCheckOutcome) {
		this.healthCheckOutcome = healthCheckOutcome;
	}
	public ProfessionalClaimsErrorHandler getErrorhandler() {
		return errorhandler;
	}
	public void setErrorhandler(ProfessionalClaimsErrorHandler errorhandler) {
		this.errorhandler = errorhandler;
	}
	public AccessTokenError getAccessTokenError() {
		return accessTokenError;
	}
	public void setAccessTokenError(AccessTokenError accessTokenError) {
		this.accessTokenError = accessTokenError;
	}
	
	
}
