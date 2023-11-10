package com.sunknowledge.changehealthcare.pojo.professionalclaims;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "additionalInformation", "certificationNarrative", "goalRehabOrDischargePlans",
		"diagnosisDescription", "thirdPartOrgNotes" })
@Generated("jsonschema2pojo")
public class ClaimNote {

	@JsonProperty("additionalInformation")
	private String additionalInformation;
	@JsonProperty("certificationNarrative")
	private String certificationNarrative;
	@JsonProperty("goalRehabOrDischargePlans")
	private String goalRehabOrDischargePlans;
	@JsonProperty("diagnosisDescription")
	private String diagnosisDescription;
	@JsonProperty("thirdPartOrgNotes")
	private String thirdPartOrgNotes;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("additionalInformation")
	public String getAdditionalInformation() {
		return additionalInformation;
	}

	@JsonProperty("additionalInformation")
	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	@JsonProperty("certificationNarrative")
	public String getCertificationNarrative() {
		return certificationNarrative;
	}

	@JsonProperty("certificationNarrative")
	public void setCertificationNarrative(String certificationNarrative) {
		this.certificationNarrative = certificationNarrative;
	}

	@JsonProperty("goalRehabOrDischargePlans")
	public String getGoalRehabOrDischargePlans() {
		return goalRehabOrDischargePlans;
	}

	@JsonProperty("goalRehabOrDischargePlans")
	public void setGoalRehabOrDischargePlans(String goalRehabOrDischargePlans) {
		this.goalRehabOrDischargePlans = goalRehabOrDischargePlans;
	}

	@JsonProperty("diagnosisDescription")
	public String getDiagnosisDescription() {
		return diagnosisDescription;
	}

	@JsonProperty("diagnosisDescription")
	public void setDiagnosisDescription(String diagnosisDescription) {
		this.diagnosisDescription = diagnosisDescription;
	}

	@JsonProperty("thirdPartOrgNotes")
	public String getThirdPartOrgNotes() {
		return thirdPartOrgNotes;
	}

	@JsonProperty("thirdPartOrgNotes")
	public void setThirdPartOrgNotes(String thirdPartOrgNotes) {
		this.thirdPartOrgNotes = thirdPartOrgNotes;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}