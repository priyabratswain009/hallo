package com.sunknowledge.changehealthcare.pojo.professionalclaims;

import com.sunknowledge.changehealthcare.pojo.TokenResponseOutput;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author Bimal K Sahoo
 * @since 10/05/2022
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultProfessionalClaimOutcome {
	private AccessTokenError accessTokenError;
	private ClaimSubmissionOutput claimSubmissionOutput;
	private ClaimValidationOutput claimValidationOutput;
	private ProfessionalClaimsErrorHandler professionalClaimsErrorHandler;
	private HealthCheckResponse healthCheckResponse;
	private TokenResponseOutput tokenResponseOutput;
}
