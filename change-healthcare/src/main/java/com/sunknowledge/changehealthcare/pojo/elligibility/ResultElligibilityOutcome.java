package com.sunknowledge.changehealthcare.pojo.elligibility;

import com.sunknowledge.changehealthcare.pojo.TokenResponseOutput;
import com.sunknowledge.changehealthcare.pojo.professionalclaims.AccessTokenError;
import com.sunknowledge.changehealthcare.pojo.professionalclaims.ProfessionalClaimsErrorHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultElligibilityOutcome {
	
	private ElligiblityResponseOutput elligiblityResponseOutput;
	private TokenResponseOutput tokenResponseOutput;
	private HealthCheckOutcome healthCheckOutcome;
	private ProfessionalClaimsErrorHandler errorhandler;
	private AccessTokenError accessTokenError;

}
