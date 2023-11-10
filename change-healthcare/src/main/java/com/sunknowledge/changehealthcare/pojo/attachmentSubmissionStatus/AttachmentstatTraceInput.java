package com.sunknowledge.changehealthcare.pojo.attachmentSubmissionStatus;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "traceid", "fieldset" })
@Generated("jsonschema2pojo")
public class AttachmentstatTraceInput {

	@JsonProperty("traceid")
	private String traceid;
	@JsonProperty("fieldset")
	private String fieldset;
	
}
