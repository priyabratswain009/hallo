package com.sunknowledge.dme.rcm.dto.par;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "soId", "itemDetails" })
@Generated("jsonschema2pojo")
public class DelinkItemsinPAR {

	@JsonProperty("soId")
	private Long soId;
	@JsonProperty("itemDetails")
	private List<ItemDetail> itemDetails;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("soId")
	public Long getSoId() {
		return soId;
	}

	@JsonProperty("soId")
	public void setSoId(Long soId) {
		this.soId = soId;
	}

	@JsonProperty("itemDetails")
	public List<ItemDetail> getItemDetails() {
		return itemDetails;
	}

	@JsonProperty("itemDetails")
	public void setItemDetails(List<ItemDetail> itemDetails) {
		this.itemDetails = itemDetails;
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