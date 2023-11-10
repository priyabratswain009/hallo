package com.sunknowledge.dme.rcm.pojo.claimreports.claimsremittance835;

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
@JsonPropertyOrder({
    "claimAdjustmentGroupCode",
    "claimAdjustmentGroupCodeValue",
    "adjustmentReasonCode1",
    "adjustmentAmount1",
    "adjustmentReasonCode2",
    "adjustmentAmount2",
    "adjustmentReasonCode3",
    "adjustmentAmount3"
})
@Generated("jsonschema2pojo")
public class ServiceAdjustment {

    @JsonProperty("claimAdjustmentGroupCode")
    private String claimAdjustmentGroupCode;
    @JsonProperty("claimAdjustmentGroupCodeValue")
    private String claimAdjustmentGroupCodeValue;
    @JsonProperty("adjustmentReasonCode1")
    private String adjustmentReasonCode1;
    @JsonProperty("adjustmentAmount1")
    private String adjustmentAmount1;
    @JsonProperty("adjustmentReasonCode2")
    private String adjustmentReasonCode2;
    @JsonProperty("adjustmentAmount2")
    private String adjustmentAmount2;
    @JsonProperty("adjustmentReasonCode3")
    private String adjustmentReasonCode3;
    @JsonProperty("adjustmentAmount3")
    private String adjustmentAmount3;

    @JsonProperty("adjustmentReasonCode4")
    private String adjustmentReasonCode4;
    @JsonProperty("adjustmentAmount4")
    private String adjustmentAmount4;

    @JsonProperty("adjustmentReasonCode5")
    private String adjustmentReasonCode5;
    @JsonProperty("adjustmentAmount5")
    private String adjustmentAmount5;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("claimAdjustmentGroupCode")
    public String getClaimAdjustmentGroupCode() {
        return claimAdjustmentGroupCode;
    }

    @JsonProperty("claimAdjustmentGroupCode")
    public void setClaimAdjustmentGroupCode(String claimAdjustmentGroupCode) {
        this.claimAdjustmentGroupCode = claimAdjustmentGroupCode;
    }

    @JsonProperty("claimAdjustmentGroupCodeValue")
    public String getClaimAdjustmentGroupCodeValue() {
        return claimAdjustmentGroupCodeValue;
    }

    @JsonProperty("claimAdjustmentGroupCodeValue")
    public void setClaimAdjustmentGroupCodeValue(String claimAdjustmentGroupCodeValue) {
        this.claimAdjustmentGroupCodeValue = claimAdjustmentGroupCodeValue;
    }

    @JsonProperty("adjustmentReasonCode1")
    public String getAdjustmentReasonCode1() {
        return adjustmentReasonCode1;
    }

    @JsonProperty("adjustmentReasonCode1")
    public void setAdjustmentReasonCode1(String adjustmentReasonCode1) {
        this.adjustmentReasonCode1 = adjustmentReasonCode1;
    }

    @JsonProperty("adjustmentAmount1")
    public String getAdjustmentAmount1() {
        return adjustmentAmount1;
    }

    @JsonProperty("adjustmentAmount1")
    public void setAdjustmentAmount1(String adjustmentAmount1) {
        this.adjustmentAmount1 = adjustmentAmount1;
    }

    @JsonProperty("adjustmentReasonCode2")
    public String getAdjustmentReasonCode2() {
        return adjustmentReasonCode2;
    }

    @JsonProperty("adjustmentReasonCode2")
    public void setAdjustmentReasonCode2(String adjustmentReasonCode2) {
        this.adjustmentReasonCode2 = adjustmentReasonCode2;
    }

    @JsonProperty("adjustmentAmount2")
    public String getAdjustmentAmount2() {
        return adjustmentAmount2;
    }

    @JsonProperty("adjustmentAmount2")
    public void setAdjustmentAmount2(String adjustmentAmount2) {
        this.adjustmentAmount2 = adjustmentAmount2;
    }

    @JsonProperty("adjustmentReasonCode3")
    public String getAdjustmentReasonCode3() {
        return adjustmentReasonCode3;
    }

    @JsonProperty("adjustmentReasonCode3")
    public void setAdjustmentReasonCode3(String adjustmentReasonCode3) {
        this.adjustmentReasonCode3 = adjustmentReasonCode3;
    }

    @JsonProperty("adjustmentAmount3")
    public String getAdjustmentAmount3() {
        return adjustmentAmount3;
    }

    @JsonProperty("adjustmentAmount3")
    public void setAdjustmentAmount3(String adjustmentAmount3) {
        this.adjustmentAmount3 = adjustmentAmount3;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

	public String getAdjustmentReasonCode4() {
		return adjustmentReasonCode4;
	}

	public void setAdjustmentReasonCode4(String adjustmentReasonCode4) {
		this.adjustmentReasonCode4 = adjustmentReasonCode4;
	}

	public String getAdjustmentAmount4() {
		return adjustmentAmount4;
	}

	public void setAdjustmentAmount4(String adjustmentAmount4) {
		this.adjustmentAmount4 = adjustmentAmount4;
	}

	public String getAdjustmentReasonCode5() {
		return adjustmentReasonCode5;
	}

	public void setAdjustmentReasonCode5(String adjustmentReasonCode5) {
		this.adjustmentReasonCode5 = adjustmentReasonCode5;
	}

	public String getAdjustmentAmount5() {
		return adjustmentAmount5;
	}

	public void setAdjustmentAmount5(String adjustmentAmount5) {
		this.adjustmentAmount5 = adjustmentAmount5;
	}

}
