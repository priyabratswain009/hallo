package com.sunknowledge.dme.rcm.pojo.claimreports.transaction;

import java.util.List;

import com.sunknowledge.dme.rcm.domain.ClaimsCOB835Master;
import com.sunknowledge.dme.rcm.domain.ClaimsStatus277Master;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaimTransactionOutcome {
	private List<ClaimsCOB835Data> claimsCOB835MasterList;
	private List<ClaimsStatus277Data> claimsStatus277MasterList;
}
