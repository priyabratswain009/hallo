package com.sunknowledge.changehealthcare.validation;

import com.sunknowledge.changehealthcare.core.ServiceOutcome;
import com.sunknowledge.changehealthcare.pojo.claimStatus.ClaimStatusRequestInput;
import com.sunknowledge.changehealthcare.pojo.elligibility.ElligiblityRequestInput;
import com.sunknowledge.changehealthcare.pojo.professionalclaims.ProfessionalClaimSubmission;
import com.sunknowledge.changehealthcare.pojo.professionalclaims.ProfessionalClaimValidation;

public class ValidationUtilities {
	public static ServiceOutcome<ElligiblityRequestInput> elligibilityValidation(ElligiblityRequestInput elligibilityModel){
		return ElligibilityComponentValidation.validateUserInputs(elligibilityModel);
	}

	public static ServiceOutcome<ProfessionalClaimValidation> claimsValidation(ProfessionalClaimValidation professionalClaimValidation){
		return ProfessionalComponentValidation.validateValidationUserInputs(professionalClaimValidation);
	}
	
	public static ServiceOutcome<ProfessionalClaimSubmission> claimsSubmission(ProfessionalClaimSubmission professionalClaimValidation){
		return ProfessionalComponentValidation.validateSubmissionUserInputs(professionalClaimValidation);
	}
	
	public static ServiceOutcome<ClaimStatusRequestInput> validateUserInputs(ClaimStatusRequestInput claimStatusRequestInput){
		return ClaimStatusComponentValidation.validateUserInputs(claimStatusRequestInput);
	}
}
