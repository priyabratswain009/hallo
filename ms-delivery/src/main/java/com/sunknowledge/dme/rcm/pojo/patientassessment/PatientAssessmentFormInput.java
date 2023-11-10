
package com.sunknowledge.dme.rcm.pojo.patientassessment;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "formName",
    "deliveryTicketId",
    "companyName",
    "streetAddress",
    "cityStateZip",
    "phoneNo",
    "fax",
    "patientName",
    "address",
    "p_cityStateZip",
    "phone",
    "patientId",
    "account",
    "alternateContacts",
    "patientVision",
    "patientHearing",
    "patientSpeech",
    "patientAmbulatory",
    "alertUnderstand",
    "confused",
    "dementia",
    "patientMobile",
    "patientBedRidden",
    "patientFallRisk",
    "patientNutritional",
    "caregiverWilling",
    "caregiverAble",
    "caregiverUnderstands",
    "caregiverMaintain",
    "comments",
    "signaturePatientRepresentative",
    "patientRepresentativeName",
    "patientRelationship",
    "reasonNotToSign",
    "date",
    "signatureCompRepresentative",
    "companyRepresentativeName"
})
@Generated("jsonschema2pojo")
public class PatientAssessmentFormInput {
    @JsonProperty("formName")
    private String formName;
    @JsonProperty("deliveryTicketId")
    private String deliveryTicketId;
    @JsonProperty("companyName")
    private String companyName;
    @JsonProperty("streetAddress")
    private String streetAddress;
    @JsonProperty("cityStateZip")
    private String cityStateZip;
    @JsonProperty("phoneNo")
    private String phoneNo;
    @JsonProperty("fax")
    private String fax;
    @JsonProperty("patientName")
    private String patientName;
    @JsonProperty("address")
    private String address;
    @JsonProperty("p_cityStateZip")
    private String pCityStateZip;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("patientId")
    private String patientId;
    @JsonProperty("account")
    private String account;
    @JsonProperty("alternateContacts")
    private AlternateContacts alternateContacts;
    @JsonProperty("patientVision")
    private String patientVision;
    @JsonProperty("patientHearing")
    private String patientHearing;
    @JsonProperty("patientSpeech")
    private String patientSpeech;
    @JsonProperty("patientAmbulatory")
    private String patientAmbulatory;
    @JsonProperty("alertUnderstand")
    private String alertUnderstand;
    @JsonProperty("confused")
    private String confused;
    @JsonProperty("dementia")
    private String dementia;
    @JsonProperty("patientMobile")
    private String patientMobile;
    @JsonProperty("patientBedRidden")
    private String patientBedRidden;
    @JsonProperty("patientFallRisk")
    private String patientFallRisk;
    @JsonProperty("patientNutritional")
    private String patientNutritional;
    @JsonProperty("caregiverWilling")
    private String caregiverWilling;
    @JsonProperty("caregiverAble")
    private String caregiverAble;
    @JsonProperty("caregiverUnderstands")
    private String caregiverUnderstands;
    @JsonProperty("caregiverMaintain")
    private String caregiverMaintain;
    @JsonProperty("comments")
    private String comments;
    @JsonProperty("signaturePatientRepresentative")
    private String signaturePatientRepresentative;
    @JsonProperty("patientRepresentativeName")
    private String patientRepresentativeName;
    @JsonProperty("patientRelationship")
    private String patientRelationship;
    @JsonProperty("reasonNotToSign")
    private String reasonNotToSign;
    @JsonProperty("date")
    private String date;
    @JsonProperty("signatureCompRepresentative")
    private String signatureCompRepresentative;
    @JsonProperty("companyRepresentativeName")
    private String companyRepresentativeName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("formName")
    public String getFormName() {
        return formName;
    }

    @JsonProperty("formName")
    public void setFormName(String formName) {
        this.formName = formName;
    }

    @JsonProperty("deliveryTicketId")
    public String getDeliveryTicketId() {
        return deliveryTicketId;
    }

    @JsonProperty("deliveryTicketId")
    public void setDeliveryTicketId(String deliveryTicketId) {
        this.deliveryTicketId = deliveryTicketId;
    }

    @JsonProperty("companyName")
    public String getCompanyName() {
        return companyName;
    }

    @JsonProperty("companyName")
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @JsonProperty("streetAddress")
    public String getStreetAddress() {
        return streetAddress;
    }

    @JsonProperty("streetAddress")
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    @JsonProperty("cityStateZip")
    public String getCityStateZip() {
        return cityStateZip;
    }

    @JsonProperty("cityStateZip")
    public void setCityStateZip(String cityStateZip) {
        this.cityStateZip = cityStateZip;
    }

    @JsonProperty("phoneNo")
    public String getPhoneNo() {
        return phoneNo;
    }

    @JsonProperty("phoneNo")
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @JsonProperty("fax")
    public String getFax() {
        return fax;
    }

    @JsonProperty("fax")
    public void setFax(String fax) {
        this.fax = fax;
    }

    @JsonProperty("patientName")
    public String getPatientName() {
        return patientName;
    }

    @JsonProperty("patientName")
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("p_cityStateZip")
    public String getpCityStateZip() {
        return pCityStateZip;
    }

    @JsonProperty("p_cityStateZip")
    public void setpCityStateZip(String pCityStateZip) {
        this.pCityStateZip = pCityStateZip;
    }

    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    @JsonProperty("phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonProperty("patientId")
    public String getPatientId() {
        return patientId;
    }

    @JsonProperty("patientId")
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    @JsonProperty("account")
    public String getAccount() {
        return account;
    }

    @JsonProperty("account")
    public void setAccount(String account) {
        this.account = account;
    }

    @JsonProperty("alternateContacts")
    public AlternateContacts getAlternateContacts() {
        return alternateContacts;
    }

    @JsonProperty("alternateContacts")
    public void setAlternateContacts(AlternateContacts alternateContacts) {
        this.alternateContacts = alternateContacts;
    }

    @JsonProperty("patientVision")
    public String getPatientVision() {
        return patientVision;
    }

    @JsonProperty("patientVision")
    public void setPatientVision(String patientVision) {
        this.patientVision = patientVision;
    }

    @JsonProperty("patientHearing")
    public String getPatientHearing() {
        return patientHearing;
    }

    @JsonProperty("patientHearing")
    public void setPatientHearing(String patientHearing) {
        this.patientHearing = patientHearing;
    }

    @JsonProperty("patientSpeech")
    public String getPatientSpeech() {
        return patientSpeech;
    }

    @JsonProperty("patientSpeech")
    public void setPatientSpeech(String patientSpeech) {
        this.patientSpeech = patientSpeech;
    }

    @JsonProperty("patientAmbulatory")
    public String getPatientAmbulatory() {
        return patientAmbulatory;
    }

    @JsonProperty("patientAmbulatory")
    public void setPatientAmbulatory(String patientAmbulatory) {
        this.patientAmbulatory = patientAmbulatory;
    }

    @JsonProperty("alertUnderstand")
    public String getAlertUnderstand() {
        return alertUnderstand;
    }

    @JsonProperty("alertUnderstand")
    public void setAlertUnderstand(String alertUnderstand) {
        this.alertUnderstand = alertUnderstand;
    }

    @JsonProperty("confused")
    public String getConfused() {
        return confused;
    }

    @JsonProperty("confused")
    public void setConfused(String confused) {
        this.confused = confused;
    }

    @JsonProperty("dementia")
    public String getDementia() {
        return dementia;
    }

    @JsonProperty("dementia")
    public void setDementia(String dementia) {
        this.dementia = dementia;
    }

    @JsonProperty("patientMobile")
    public String getPatientMobile() {
        return patientMobile;
    }

    @JsonProperty("patientMobile")
    public void setPatientMobile(String patientMobile) {
        this.patientMobile = patientMobile;
    }

    @JsonProperty("patientBedRidden")
    public String getPatientBedRidden() {
        return patientBedRidden;
    }

    @JsonProperty("patientBedRidden")
    public void setPatientBedRidden(String patientBedRidden) {
        this.patientBedRidden = patientBedRidden;
    }

    @JsonProperty("patientFallRisk")
    public String getPatientFallRisk() {
        return patientFallRisk;
    }

    @JsonProperty("patientFallRisk")
    public void setPatientFallRisk(String patientFallRisk) {
        this.patientFallRisk = patientFallRisk;
    }

    @JsonProperty("patientNutritional")
    public String getPatientNutritional() {
        return patientNutritional;
    }

    @JsonProperty("patientNutritional")
    public void setPatientNutritional(String patientNutritional) {
        this.patientNutritional = patientNutritional;
    }

    @JsonProperty("caregiverWilling")
    public String getCaregiverWilling() {
        return caregiverWilling;
    }

    @JsonProperty("caregiverWilling")
    public void setCaregiverWilling(String caregiverWilling) {
        this.caregiverWilling = caregiverWilling;
    }

    @JsonProperty("caregiverAble")
    public String getCaregiverAble() {
        return caregiverAble;
    }

    @JsonProperty("caregiverAble")
    public void setCaregiverAble(String caregiverAble) {
        this.caregiverAble = caregiverAble;
    }

    @JsonProperty("caregiverUnderstands")
    public String getCaregiverUnderstands() {
        return caregiverUnderstands;
    }

    @JsonProperty("caregiverUnderstands")
    public void setCaregiverUnderstands(String caregiverUnderstands) {
        this.caregiverUnderstands = caregiverUnderstands;
    }

    @JsonProperty("caregiverMaintain")
    public String getCaregiverMaintain() {
        return caregiverMaintain;
    }

    @JsonProperty("caregiverMaintain")
    public void setCaregiverMaintain(String caregiverMaintain) {
        this.caregiverMaintain = caregiverMaintain;
    }

    @JsonProperty("comments")
    public String getComments() {
        return comments;
    }

    @JsonProperty("comments")
    public void setComments(String comments) {
        this.comments = comments;
    }

    @JsonProperty("signaturePatientRepresentative")
    public String getSignaturePatientRepresentative() {
        return signaturePatientRepresentative;
    }

    @JsonProperty("signaturePatientRepresentative")
    public void setSignaturePatientRepresentative(String signaturePatientRepresentative) {
        this.signaturePatientRepresentative = signaturePatientRepresentative;
    }

    @JsonProperty("patientRepresentativeName")
    public String getPatientRepresentativeName() {
        return patientRepresentativeName;
    }

    @JsonProperty("patientRepresentativeName")
    public void setPatientRepresentativeName(String patientRepresentativeName) {
        this.patientRepresentativeName = patientRepresentativeName;
    }

    @JsonProperty("patientRelationship")
    public String getPatientRelationship() {
        return patientRelationship;
    }

    @JsonProperty("patientRelationship")
    public void setPatientRelationship(String patientRelationship) {
        this.patientRelationship = patientRelationship;
    }

    @JsonProperty("reasonNotToSign")
    public String getReasonNotToSign() {
        return reasonNotToSign;
    }

    @JsonProperty("reasonNotToSign")
    public void setReasonNotToSign(String reasonNotToSign) {
        this.reasonNotToSign = reasonNotToSign;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("signatureCompRepresentative")
    public String getSignatureCompRepresentative() {
        return signatureCompRepresentative;
    }

    @JsonProperty("signatureCompRepresentative")
    public void setSignatureCompRepresentative(String signatureCompRepresentative) {
        this.signatureCompRepresentative = signatureCompRepresentative;
    }

    @JsonProperty("companyRepresentativeName")
    public String getCompanyRepresentativeName() {
        return companyRepresentativeName;
    }

    @JsonProperty("companyRepresentativeName")
    public void setCompanyRepresentativeName(String companyRepresentativeName) {
        this.companyRepresentativeName = companyRepresentativeName;
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
