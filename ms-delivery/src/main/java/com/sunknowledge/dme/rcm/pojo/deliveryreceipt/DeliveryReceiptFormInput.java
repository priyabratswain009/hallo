
package com.sunknowledge.dme.rcm.pojo.deliveryreceipt;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "formName",
    "deliveryTicketId",
    "branchName",
    "invLocation",
    "date",
    "csr",
    "order",
    "patientId",
    "customer",
    "account",
    "dob",
    "gender",
    "billToAddress",
    "deliveredToAddress",
    "insurance",
    "hippaSignatureOnFile",
    "itemOrderedDetailedList"
})
@Generated("jsonschema2pojo")
public class DeliveryReceiptFormInput {

    @JsonProperty("formName")
    private String formName;
    @JsonProperty("deliveryTicketId")
    private String deliveryTicketId;
    @JsonProperty("branchName")
    private String branchName;
    @JsonProperty("invLocation")
    private String invLocation;
    @JsonProperty("date")
    private String date;
    @JsonProperty("csr")
    private String csr;
    @JsonProperty("order")
    private String order;
    @JsonProperty("patientId")
    private String patientId;
    @JsonProperty("customer")
    private String customer;
    @JsonProperty("account")
    private String account;
    @JsonProperty("dob")
    private String dob;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("billToAddress")
    private BillToAddress billToAddress;
    @JsonProperty("deliveredToAddress")
    private DeliveredToAddress deliveredToAddress;
    @JsonProperty("insurance")
    private String insurance;
    @JsonProperty("hippaSignatureOnFile")
    private String hippaSignatureOnFile;
    @JsonProperty("itemOrderedDetailedList")
    private List<ItemOrderedDetailed> itemOrderedDetailedList;
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

    @JsonProperty("branchName")
    public String getBranchName() {
        return branchName;
    }

    @JsonProperty("branchName")
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    @JsonProperty("invLocation")
    public String getInvLocation() {
        return invLocation;
    }

    @JsonProperty("invLocation")
    public void setInvLocation(String invLocation) {
        this.invLocation = invLocation;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("csr")
    public String getCsr() {
        return csr;
    }

    @JsonProperty("csr")
    public void setCsr(String csr) {
        this.csr = csr;
    }

    @JsonProperty("order")
    public String getOrder() {
        return order;
    }

    @JsonProperty("order")
    public void setOrder(String order) {
        this.order = order;
    }

    @JsonProperty("patientId")
    public String getPatientId() {
        return patientId;
    }

    @JsonProperty("patientId")
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    @JsonProperty("customer")
    public String getCustomer() {
        return customer;
    }

    @JsonProperty("customer")
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @JsonProperty("account")
    public String getAccount() {
        return account;
    }

    @JsonProperty("account")
    public void setAccount(String account) {
        this.account = account;
    }

    @JsonProperty("dob")
    public String getDob() {
        return dob;
    }

    @JsonProperty("dob")
    public void setDob(String dob) {
        this.dob = dob;
    }

    @JsonProperty("gender")
    public String getGender() {
        return gender;
    }

    @JsonProperty("gender")
    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonProperty("billToAddress")
    public BillToAddress getBillToAddress() {
        return billToAddress;
    }

    @JsonProperty("billToAddress")
    public void setBillToAddress(BillToAddress billToAddress) {
        this.billToAddress = billToAddress;
    }

    @JsonProperty("deliveredToAddress")
    public DeliveredToAddress getDeliveredToAddress() {
        return deliveredToAddress;
    }

    @JsonProperty("deliveredToAddress")
    public void setDeliveredToAddress(DeliveredToAddress deliveredToAddress) {
        this.deliveredToAddress = deliveredToAddress;
    }

    @JsonProperty("insurance")
    public String getInsurance() {
        return insurance;
    }

    @JsonProperty("insurance")
    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    @JsonProperty("hippaSignatureOnFile")
    public String getHippaSignatureOnFile() {
        return hippaSignatureOnFile;
    }

    @JsonProperty("hippaSignatureOnFile")
    public void setHippaSignatureOnFile(String hippaSignatureOnFile) {
        this.hippaSignatureOnFile = hippaSignatureOnFile;
    }

    @JsonProperty("itemOrderedDetailedList")
    public List<ItemOrderedDetailed> getItemOrderedDetailedList() {
        return itemOrderedDetailedList;
    }

    @JsonProperty("itemOrderedDetailedList")
    public void setItemOrderedDetailedList(List<ItemOrderedDetailed> itemOrderedDetailedList) {
        this.itemOrderedDetailedList = itemOrderedDetailedList;
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
