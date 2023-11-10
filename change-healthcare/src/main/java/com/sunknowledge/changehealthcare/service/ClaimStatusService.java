package com.sunknowledge.changehealthcare.service;

import com.sunknowledge.changehealthcare.core.ServiceOutcome;
import com.sunknowledge.changehealthcare.pojo.claimStatus.ClaimStatusRequestInput;
import com.sunknowledge.changehealthcare.pojo.claimStatus.ResultClaimStatusOutcome;

public interface ClaimStatusService {
	ServiceOutcome<ResultClaimStatusOutcome> claimstatus(ClaimStatusRequestInput claimStatusRequestInput);

	ServiceOutcome<ResultClaimStatusOutcome> claimstatusHealthcheck(String token);

	ServiceOutcome<ResultClaimStatusOutcome> claimstatusCheck(String accessToken,
			ClaimStatusRequestInput claimStatusRequestInput);
}
