package com.sunknowledge.dme.rcm.domain.ServiceReview;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "fileName", "id", "idType", "dateReceived" })
@Generated("jsonschema2pojo")
public class Attachment {

	@JsonProperty("fileName")
	private String fileName;
	@JsonProperty("id")
	private String id;
	@JsonProperty("idType")
	private String idType;
	@JsonProperty("dateReceived")
	private String dateReceived;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("fileName")
	public String getFileName() {
		return fileName;
	}

	@JsonProperty("fileName")
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("idType")
	public String getIdType() {
		return idType;
	}

	@JsonProperty("idType")
	public void setIdType(String idType) {
		this.idType = idType;
	}

	@JsonProperty("dateReceived")
	public String getDateReceived() {
		return dateReceived;
	}

	@JsonProperty("dateReceived")
	public void setDateReceived(String dateReceived) {
		this.dateReceived = dateReceived;
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