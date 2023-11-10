package com.sunknowledge.dme.rcm.domain.elligibility;

import lombok.Data;

@Data
public class ResultElligibilityOutcome {
	
	private EligibilityOutput elligiblityResponseOutput;
	private TokenResponseOutput tokenResponseOutput;
	private HealthCheckOutcome healthCheckOutcome;
	private ProfessionalClaimsErrorHandler errorhandler;
	private AccessTokenError accessTokenError;

}
