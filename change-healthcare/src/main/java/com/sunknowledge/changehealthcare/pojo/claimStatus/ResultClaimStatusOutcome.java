package com.sunknowledge.changehealthcare.pojo.claimStatus;

import com.sunknowledge.changehealthcare.pojo.TokenResponseOutput;
import com.sunknowledge.changehealthcare.pojo.elligibility.HealthCheckOutcome;
import com.sunknowledge.changehealthcare.pojo.professionalclaims.AccessTokenError;
import com.sunknowledge.changehealthcare.pojo.professionalclaims.ProfessionalClaimsErrorHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultClaimStatusOutcome {
	
	private ClaimStatusRequestOutput claimStatusRequestOutput;
	private TokenResponseOutput tokenResponseOutput;
	private ProfessionalClaimsErrorHandler errorhandler;
	private AccessTokenError accessTokenError;
	private HealthCheckOutcome healthCheckOutcome;

}
