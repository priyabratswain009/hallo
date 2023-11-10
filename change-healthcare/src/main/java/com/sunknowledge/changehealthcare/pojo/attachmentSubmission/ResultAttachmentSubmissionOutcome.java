package com.sunknowledge.changehealthcare.pojo.attachmentSubmission;

import com.sunknowledge.changehealthcare.pojo.TokenResponseOutput;
import com.sunknowledge.changehealthcare.pojo.professionalclaims.AccessTokenError;
import com.sunknowledge.changehealthcare.pojo.professionalclaims.HealthCheckResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultAttachmentSubmissionOutcome {
	
	private AccessTokenError accessTokenError;
	private AttachmentSubmissionOutput attachmentSubmissionOutput;
	private HealthCheckResponse healthCheckResponse;
	private TokenResponseOutput tokenResponseOutput;
	public AccessTokenError getAccessTokenError() {
		return accessTokenError;
	}
	public void setAccessTokenError(AccessTokenError accessTokenError) {
		this.accessTokenError = accessTokenError;
	}
	public AttachmentSubmissionOutput getAttachmentSubmissionOutput() {
		return attachmentSubmissionOutput;
	}
	public void setAttachmentSubmissionOutput(AttachmentSubmissionOutput attachmentSubmissionOutput) {
		this.attachmentSubmissionOutput = attachmentSubmissionOutput;
	}
	public HealthCheckResponse getHealthCheckResponse() {
		return healthCheckResponse;
	}
	public void setHealthCheckResponse(HealthCheckResponse healthCheckResponse) {
		this.healthCheckResponse = healthCheckResponse;
	}
	public TokenResponseOutput getTokenResponseOutput() {
		return tokenResponseOutput;
	}
	public void setTokenResponseOutput(TokenResponseOutput tokenResponseOutput) {
		this.tokenResponseOutput = tokenResponseOutput;
	}
	
	

}
