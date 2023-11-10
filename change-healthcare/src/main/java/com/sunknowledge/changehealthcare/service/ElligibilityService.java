package com.sunknowledge.changehealthcare.service;

import com.sunknowledge.changehealthcare.core.ServiceOutcome;
import com.sunknowledge.changehealthcare.pojo.elligibility.ElligiblityRequestInput;
import com.sunknowledge.changehealthcare.pojo.elligibility.ResultElligibilityOutcome;

public interface ElligibilityService {
	ServiceOutcome<ResultElligibilityOutcome> medicalEligibiltycheck(ElligiblityRequestInput elligiblityRequestInput);
	ServiceOutcome<ResultElligibilityOutcome> eligibiltyHealthcheck(String token);
	ServiceOutcome<ResultElligibilityOutcome> elligiblityCheck(String accessToken, ElligiblityRequestInput elligiblityRequestInput);
}
