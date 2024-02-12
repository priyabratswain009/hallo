package com.sunknowledge.dme.rcm.dto.claims;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaimSubmissionStatusCustomDTO {

	Long salesorderid;
	String salesorderno;
	Long invoiceid;
	String invoiceno;
	String payorlevel;
	String payoridno;
	Long claimsubmissiondataid;
	String intclaimno;
	String patientaccountno;
	String payorclaimcontrolno;
	String originalclaimcontrolno;
	String patientidno;
	String payor;
	LocalDate claimsubmissiondate;
	String submissionstatus;
	String submissionnote;
	String responsestatus;
	String responsestatusnotes;
	LocalDate responsestatusdate;
	Long response277recordid;
	String erastatus;
	String erastatusnotes;
	LocalDate eradate;
	Long era835recordid;
	String resubmissinstatus;
	Long resubmissiondetailid;
	String resubmissionnotes;
	String voidstatus;
	String voidnote;
	String status;
	LocalDate createddate;
	Long createdbyid;
	String createdbyname;
	LocalDate updateddate;
	Long updatedbyid;
	String updatedbyname;
	String claimSubmissionStatusUuid;
	
}
