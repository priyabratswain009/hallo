package com.sunknowledge.dme.rcm.dto.par;

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
@JsonPropertyOrder({ "hcpcsCode", "itemId", "parId" })
@Generated("jsonschema2pojo")
public class ItemDetail {

	@JsonProperty("hcpcsCode")
	private String hcpcsCode;
	@JsonProperty("itemId")
	private Long itemId;
	@JsonProperty("parId")
	private Long parId;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("hcpcsCode")
	public String getHcpcsCode() {
		return hcpcsCode;
	}

	@JsonProperty("hcpcsCode")
	public void setHcpcsCode(String hcpcsCode) {
		this.hcpcsCode = hcpcsCode;
	}

	@JsonProperty("itemId")
	public Long getItemId() {
		return itemId;
	}

	@JsonProperty("itemId")
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	@JsonProperty("parId")
	public Long getParId() {
		return parId;
	}

	@JsonProperty("parId")
	public void setParId(Long parId) {
		this.parId = parId;
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