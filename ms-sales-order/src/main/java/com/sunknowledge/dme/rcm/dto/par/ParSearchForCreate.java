package com.sunknowledge.dme.rcm.dto.par;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "isparrequired", "paruuid", "paridno", "parno", "effectivestartdate", "expirationdate",
		"log_in_status", "patientfirstname", "patientlastname", "patientdob", "patientgender", "payeridno",
		"policynumber", "policystartdate", "policyenddate", "itemno", "itemname", "hcpcscode", "salesorderid" })
@Generated("jsonschema2pojo")
public class ParSearchForCreate {

	@JsonProperty("isparrequired")
	private String isparrequired;
	@JsonProperty("paruuid")
	private UUID paruuid;
	@JsonProperty("paridno")
	private String paridno;
	@JsonProperty("parno")
	private String parno;
	@JsonProperty("effectivestartdate")
	private LocalDate effectivestartdate;
	@JsonProperty("expirationdate")
	private LocalDate expirationdate;
	@JsonProperty("log_in_status")
	private String logInStatus;
	@JsonProperty("patientfirstname")
	private String patientfirstname;
	@JsonProperty("patientlastname")
	private String patientlastname;
	@JsonProperty("patientdob")
	private LocalDate patientdob;
	@JsonProperty("patientgender")
	private String patientgender;
	@JsonProperty("payeridno")
	private String payeridno;
	@JsonProperty("policynumber")
	private String policynumber;
	@JsonProperty("policystartdate")
	private LocalDate policystartdate;
	@JsonProperty("policyenddate")
	private LocalDate policyenddate;
	@JsonProperty("itemno")
	private String itemno;
	@JsonProperty("itemname")
	private String itemname;
	@JsonProperty("hcpcscode")
	private String hcpcscode;
	@JsonProperty("salesorderid")
	private String salesorderid;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("isparrequired")
	public String getIsparrequired() {
		return isparrequired;
	}

	@JsonProperty("isparrequired")
	public void setIsparrequired(String isparrequired) {
		this.isparrequired = isparrequired;
	}

	@JsonProperty("paruuid")
	public UUID getParuuid() {
		return paruuid;
	}

	@JsonProperty("paruuid")
	public void setParuuid(UUID paruuid) {
		this.paruuid = paruuid;
	}

	@JsonProperty("paridno")
	public String getParidno() {
		return paridno;
	}

	@JsonProperty("paridno")
	public void setParidno(String paridno) {
		this.paridno = paridno;
	}

	@JsonProperty("parno")
	public String getParno() {
		return parno;
	}

	@JsonProperty("parno")
	public void setParno(String parno) {
		this.parno = parno;
	}

	@JsonProperty("effectivestartdate")
	public LocalDate getEffectivestartdate() {
		return effectivestartdate;
	}

	@JsonProperty("effectivestartdate")
	public void setEffectivestartdate(LocalDate effectivestartdate) {
		this.effectivestartdate = effectivestartdate;
	}

	@JsonProperty("expirationdate")
	public LocalDate getExpirationdate() {
		return expirationdate;
	}

	@JsonProperty("expirationdate")
	public void setExpirationdate(LocalDate expirationdate) {
		this.expirationdate = expirationdate;
	}

	@JsonProperty("log_in_status")
	public String getLogInStatus() {
		return logInStatus;
	}

	@JsonProperty("log_in_status")
	public void setLogInStatus(String logInStatus) {
		this.logInStatus = logInStatus;
	}

	@JsonProperty("patientfirstname")
	public String getPatientfirstname() {
		return patientfirstname;
	}

	@JsonProperty("patientfirstname")
	public void setPatientfirstname(String patientfirstname) {
		this.patientfirstname = patientfirstname;
	}

	@JsonProperty("patientlastname")
	public String getPatientlastname() {
		return patientlastname;
	}

	@JsonProperty("patientlastname")
	public void setPatientlastname(String patientlastname) {
		this.patientlastname = patientlastname;
	}

	@JsonProperty("patientdob")
	public LocalDate getPatientdob() {
		return patientdob;
	}

	@JsonProperty("patientdob")
	public void setPatientdob(LocalDate patientdob) {
		this.patientdob = patientdob;
	}

	@JsonProperty("patientgender")
	public String getPatientgender() {
		return patientgender;
	}

	@JsonProperty("patientgender")
	public void setPatientgender(String patientgender) {
		this.patientgender = patientgender;
	}

	@JsonProperty("payeridno")
	public String getPayeridno() {
		return payeridno;
	}

	@JsonProperty("payeridno")
	public void setPayeridno(String payeridno) {
		this.payeridno = payeridno;
	}

	@JsonProperty("policynumber")
	public String getPolicynumber() {
		return policynumber;
	}

	@JsonProperty("policynumber")
	public void setPolicynumber(String policynumber) {
		this.policynumber = policynumber;
	}

	@JsonProperty("policystartdate")
	public LocalDate getPolicystartdate() {
		return policystartdate;
	}

	@JsonProperty("policystartdate")
	public void setPolicystartdate(LocalDate policystartdate) {
		this.policystartdate = policystartdate;
	}

	@JsonProperty("policyenddate")
	public LocalDate getPolicyenddate() {
		return policyenddate;
	}

	@JsonProperty("policyenddate")
	public void setPolicyenddate(LocalDate policyenddate) {
		this.policyenddate = policyenddate;
	}

	@JsonProperty("itemno")
	public String getItemno() {
		return itemno;
	}

	@JsonProperty("itemno")
	public void setItemno(String itemno) {
		this.itemno = itemno;
	}

	@JsonProperty("itemname")
	public String getItemname() {
		return itemname;
	}

	@JsonProperty("itemname")
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	@JsonProperty("hcpcscode")
	public String getHcpcscode() {
		return hcpcscode;
	}

	@JsonProperty("hcpcscode")
	public void setHcpcscode(String hcpcscode) {
		this.hcpcscode = hcpcscode;
	}

	@JsonProperty("salesorderid")
	public String getSalesorderid() {
		return salesorderid;
	}

	@JsonProperty("salesorderid")
	public void setSalesorderid(String salesorderid) {
		this.salesorderid = salesorderid;
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