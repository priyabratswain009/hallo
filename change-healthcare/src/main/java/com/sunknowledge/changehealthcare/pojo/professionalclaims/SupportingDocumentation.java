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
@JsonPropertyOrder({ "questionNumber", "questionResponseCode", "questionResponse", "questionResponseAsDate",
		"questionResponseAsPercent" })
@Generated("jsonschema2pojo")
public class SupportingDocumentation {

	@JsonProperty("questionNumber")
	private String questionNumber;
	@JsonProperty("questionResponseCode")
	private String questionResponseCode;
	@JsonProperty("questionResponse")
	private String questionResponse;
	@JsonProperty("questionResponseAsDate")
	private String questionResponseAsDate;
	@JsonProperty("questionResponseAsPercent")
	private String questionResponseAsPercent;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("questionNumber")
	public String getQuestionNumber() {
		return questionNumber;
	}

	@JsonProperty("questionNumber")
	public void setQuestionNumber(String questionNumber) {
		this.questionNumber = questionNumber;
	}

	@JsonProperty("questionResponseCode")
	public String getQuestionResponseCode() {
		return questionResponseCode;
	}

	@JsonProperty("questionResponseCode")
	public void setQuestionResponseCode(String questionResponseCode) {
		this.questionResponseCode = questionResponseCode;
	}

	@JsonProperty("questionResponse")
	public String getQuestionResponse() {
		return questionResponse;
	}

	@JsonProperty("questionResponse")
	public void setQuestionResponse(String questionResponse) {
		this.questionResponse = questionResponse;
	}

	@JsonProperty("questionResponseAsDate")
	public String getQuestionResponseAsDate() {
		return questionResponseAsDate;
	}

	@JsonProperty("questionResponseAsDate")
	public void setQuestionResponseAsDate(String questionResponseAsDate) {
		this.questionResponseAsDate = questionResponseAsDate;
	}

	@JsonProperty("questionResponseAsPercent")
	public String getQuestionResponseAsPercent() {
		return questionResponseAsPercent;
	}

	@JsonProperty("questionResponseAsPercent")
	public void setQuestionResponseAsPercent(String questionResponseAsPercent) {
		this.questionResponseAsPercent = questionResponseAsPercent;
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