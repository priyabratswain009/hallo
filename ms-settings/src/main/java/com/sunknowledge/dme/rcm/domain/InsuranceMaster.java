package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A InsuranceMaster.
 */
@Entity
@Table(name = "t_insurance_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InsuranceMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "insurance_id")
    private Long insuranceId;

    @Column(name = "insurance_name")
    private String insuranceName;

    @Column(name = "insurance_plan_type")
    private String insurancePlanType;

    @Column(name = "tax_type")
    private String taxType;

    @Column(name = "tax_included_allowable_status")
    private String taxIncludedAllowableStatus;

    @Column(name = "insurance_group_id")
    private Long insuranceGroupId;

    @Column(name = "price_table_id")
    private Long priceTableId;

    @Column(name = "claim_type_dme_status")
    private String claimTypeDmeStatus;

    @Column(name = "claim_type_major_madical_status")
    private String claimTypeMajorMadicalStatus;

    @Column(name = "claim_type_pharmacy_status")
    private String claimTypePharmacyStatus;

    @Column(name = "pay_percentage")
    private Double payPercentage;

    @Column(name = "enable_secondary_billing_status")
    private String enableSecondaryBillingStatus;

    @Column(name = "amt_print_on_delivery_ticket_status")
    private String amtPrintOnDeliveryTicketStatus;

    @Column(name = "medigap_status")
    private String medigapStatus;

    @Column(name = "medigap_num")
    private Long medigapNum;

    @Column(name = "notes")
    private String notes;

    @Column(name = "status")
    private String status;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "insurance_master_uuid")
    private UUID insuranceMasterUuid;

    @Column(name = "insurance_payer_id_no")
    private String insurancePayerIdNo;

    @Column(name = "address_line_1")
    private String addressLine1;

    @Column(name = "address_line_2")
    private String addressLine2;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "zip")
    private String zip;

    @Column(name = "contact_no_1")
    private String contactNo1;

    @Column(name = "contact_no_2")
    private String contactNo2;

    @Column(name = "fax")
    private String fax;

    @Column(name = "efax")
    private String efax;

    @Column(name = "email")
    private String email;

    @Column(name = "relationship")
    private String relationship;

    @Column(name = "mode_of_contact")
    private String modeOfContact;

    @Column(name = "claim_program_code")
    private String claimProgramCode;

    @Column(name = "claim_program_name")
    private String claimProgramName;

    @Column(name = "insurance_id_no")
    private String insuranceIdNo;

    @Column(name = "insurance_group_no")
    private String insuranceGroupNo;

    @Column(name = "price_table_name")
    private String priceTableName;

    @Column(name = "claim_program_id")
    private String claimProgramId;

    @Column(name = "claim_form_name")
    private String claimFormName;

    @Column(name = "insurance_group_name")
    private String insuranceGroupName;

    @Column(name = "contact_person_first_name")
    private String contactPersonFirstName;

    @Column(name = "contact_person_middle_name")
    private String contactPersonMiddleName;

    @Column(name = "contact_person_last_name")
    private String contactPersonLastName;

    @Column(name = "tax_tncluded_allowable_status")
    private String taxTncludedAllowableStatus;

    @Column(name = "cms_crossover_insurance_id_no")
    private String cmsCrossoverInsuranceIdNo;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getInsuranceId() {
        return this.insuranceId;
    }

    public InsuranceMaster insuranceId(Long insuranceId) {
        this.setInsuranceId(insuranceId);
        return this;
    }

    public void setInsuranceId(Long insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getInsuranceName() {
        return this.insuranceName;
    }

    public InsuranceMaster insuranceName(String insuranceName) {
        this.setInsuranceName(insuranceName);
        return this;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public String getInsurancePlanType() {
        return this.insurancePlanType;
    }

    public InsuranceMaster insurancePlanType(String insurancePlanType) {
        this.setInsurancePlanType(insurancePlanType);
        return this;
    }

    public void setInsurancePlanType(String insurancePlanType) {
        this.insurancePlanType = insurancePlanType;
    }

    public String getTaxType() {
        return this.taxType;
    }

    public InsuranceMaster taxType(String taxType) {
        this.setTaxType(taxType);
        return this;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public String getTaxIncludedAllowableStatus() {
        return this.taxIncludedAllowableStatus;
    }

    public InsuranceMaster taxIncludedAllowableStatus(String taxIncludedAllowableStatus) {
        this.setTaxIncludedAllowableStatus(taxIncludedAllowableStatus);
        return this;
    }

    public void setTaxIncludedAllowableStatus(String taxIncludedAllowableStatus) {
        this.taxIncludedAllowableStatus = taxIncludedAllowableStatus;
    }

    public Long getInsuranceGroupId() {
        return this.insuranceGroupId;
    }

    public InsuranceMaster insuranceGroupId(Long insuranceGroupId) {
        this.setInsuranceGroupId(insuranceGroupId);
        return this;
    }

    public void setInsuranceGroupId(Long insuranceGroupId) {
        this.insuranceGroupId = insuranceGroupId;
    }

    public Long getPriceTableId() {
        return this.priceTableId;
    }

    public InsuranceMaster priceTableId(Long priceTableId) {
        this.setPriceTableId(priceTableId);
        return this;
    }

    public void setPriceTableId(Long priceTableId) {
        this.priceTableId = priceTableId;
    }

    public String getClaimTypeDmeStatus() {
        return this.claimTypeDmeStatus;
    }

    public InsuranceMaster claimTypeDmeStatus(String claimTypeDmeStatus) {
        this.setClaimTypeDmeStatus(claimTypeDmeStatus);
        return this;
    }

    public void setClaimTypeDmeStatus(String claimTypeDmeStatus) {
        this.claimTypeDmeStatus = claimTypeDmeStatus;
    }

    public String getClaimTypeMajorMadicalStatus() {
        return this.claimTypeMajorMadicalStatus;
    }

    public InsuranceMaster claimTypeMajorMadicalStatus(String claimTypeMajorMadicalStatus) {
        this.setClaimTypeMajorMadicalStatus(claimTypeMajorMadicalStatus);
        return this;
    }

    public void setClaimTypeMajorMadicalStatus(String claimTypeMajorMadicalStatus) {
        this.claimTypeMajorMadicalStatus = claimTypeMajorMadicalStatus;
    }

    public String getClaimTypePharmacyStatus() {
        return this.claimTypePharmacyStatus;
    }

    public InsuranceMaster claimTypePharmacyStatus(String claimTypePharmacyStatus) {
        this.setClaimTypePharmacyStatus(claimTypePharmacyStatus);
        return this;
    }

    public void setClaimTypePharmacyStatus(String claimTypePharmacyStatus) {
        this.claimTypePharmacyStatus = claimTypePharmacyStatus;
    }

    public Double getPayPercentage() {
        return this.payPercentage;
    }

    public InsuranceMaster payPercentage(Double payPercentage) {
        this.setPayPercentage(payPercentage);
        return this;
    }

    public void setPayPercentage(Double payPercentage) {
        this.payPercentage = payPercentage;
    }

    public String getEnableSecondaryBillingStatus() {
        return this.enableSecondaryBillingStatus;
    }

    public InsuranceMaster enableSecondaryBillingStatus(String enableSecondaryBillingStatus) {
        this.setEnableSecondaryBillingStatus(enableSecondaryBillingStatus);
        return this;
    }

    public void setEnableSecondaryBillingStatus(String enableSecondaryBillingStatus) {
        this.enableSecondaryBillingStatus = enableSecondaryBillingStatus;
    }

    public String getAmtPrintOnDeliveryTicketStatus() {
        return this.amtPrintOnDeliveryTicketStatus;
    }

    public InsuranceMaster amtPrintOnDeliveryTicketStatus(String amtPrintOnDeliveryTicketStatus) {
        this.setAmtPrintOnDeliveryTicketStatus(amtPrintOnDeliveryTicketStatus);
        return this;
    }

    public void setAmtPrintOnDeliveryTicketStatus(String amtPrintOnDeliveryTicketStatus) {
        this.amtPrintOnDeliveryTicketStatus = amtPrintOnDeliveryTicketStatus;
    }

    public String getMedigapStatus() {
        return this.medigapStatus;
    }

    public InsuranceMaster medigapStatus(String medigapStatus) {
        this.setMedigapStatus(medigapStatus);
        return this;
    }

    public void setMedigapStatus(String medigapStatus) {
        this.medigapStatus = medigapStatus;
    }

    public Long getMedigapNum() {
        return this.medigapNum;
    }

    public InsuranceMaster medigapNum(Long medigapNum) {
        this.setMedigapNum(medigapNum);
        return this;
    }

    public void setMedigapNum(Long medigapNum) {
        this.medigapNum = medigapNum;
    }

    public String getNotes() {
        return this.notes;
    }

    public InsuranceMaster notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatus() {
        return this.status;
    }

    public InsuranceMaster status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public InsuranceMaster createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public InsuranceMaster createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public InsuranceMaster updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public InsuranceMaster createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public InsuranceMaster updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public InsuranceMaster updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public UUID getInsuranceMasterUuid() {
        return this.insuranceMasterUuid;
    }

    public InsuranceMaster insuranceMasterUuid(UUID insuranceMasterUuid) {
        this.setInsuranceMasterUuid(insuranceMasterUuid);
        return this;
    }

    public void setInsuranceMasterUuid(UUID insuranceMasterUuid) {
        this.insuranceMasterUuid = insuranceMasterUuid;
    }

    public String getInsurancePayerIdNo() {
        return this.insurancePayerIdNo;
    }

    public InsuranceMaster insurancePayerIdNo(String insurancePayerIdNo) {
        this.setInsurancePayerIdNo(insurancePayerIdNo);
        return this;
    }

    public void setInsurancePayerIdNo(String insurancePayerIdNo) {
        this.insurancePayerIdNo = insurancePayerIdNo;
    }

    public String getAddressLine1() {
        return this.addressLine1;
    }

    public InsuranceMaster addressLine1(String addressLine1) {
        this.setAddressLine1(addressLine1);
        return this;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return this.addressLine2;
    }

    public InsuranceMaster addressLine2(String addressLine2) {
        this.setAddressLine2(addressLine2);
        return this;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return this.city;
    }

    public InsuranceMaster city(String city) {
        this.setCity(city);
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public InsuranceMaster state(String state) {
        this.setState(state);
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return this.country;
    }

    public InsuranceMaster country(String country) {
        this.setCountry(country);
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return this.zip;
    }

    public InsuranceMaster zip(String zip) {
        this.setZip(zip);
        return this;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getContactNo1() {
        return this.contactNo1;
    }

    public InsuranceMaster contactNo1(String contactNo1) {
        this.setContactNo1(contactNo1);
        return this;
    }

    public void setContactNo1(String contactNo1) {
        this.contactNo1 = contactNo1;
    }

    public String getContactNo2() {
        return this.contactNo2;
    }

    public InsuranceMaster contactNo2(String contactNo2) {
        this.setContactNo2(contactNo2);
        return this;
    }

    public void setContactNo2(String contactNo2) {
        this.contactNo2 = contactNo2;
    }

    public String getFax() {
        return this.fax;
    }

    public InsuranceMaster fax(String fax) {
        this.setFax(fax);
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEfax() {
        return this.efax;
    }

    public InsuranceMaster efax(String efax) {
        this.setEfax(efax);
        return this;
    }

    public void setEfax(String efax) {
        this.efax = efax;
    }

    public String getEmail() {
        return this.email;
    }

    public InsuranceMaster email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRelationship() {
        return this.relationship;
    }

    public InsuranceMaster relationship(String relationship) {
        this.setRelationship(relationship);
        return this;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getModeOfContact() {
        return this.modeOfContact;
    }

    public InsuranceMaster modeOfContact(String modeOfContact) {
        this.setModeOfContact(modeOfContact);
        return this;
    }

    public void setModeOfContact(String modeOfContact) {
        this.modeOfContact = modeOfContact;
    }

    public String getClaimProgramCode() {
        return this.claimProgramCode;
    }

    public InsuranceMaster claimProgramCode(String claimProgramCode) {
        this.setClaimProgramCode(claimProgramCode);
        return this;
    }

    public void setClaimProgramCode(String claimProgramCode) {
        this.claimProgramCode = claimProgramCode;
    }

    public String getClaimProgramName() {
        return this.claimProgramName;
    }

    public InsuranceMaster claimProgramName(String claimProgramName) {
        this.setClaimProgramName(claimProgramName);
        return this;
    }

    public void setClaimProgramName(String claimProgramName) {
        this.claimProgramName = claimProgramName;
    }

    public String getInsuranceIdNo() {
        return this.insuranceIdNo;
    }

    public InsuranceMaster insuranceIdNo(String insuranceIdNo) {
        this.setInsuranceIdNo(insuranceIdNo);
        return this;
    }

    public void setInsuranceIdNo(String insuranceIdNo) {
        this.insuranceIdNo = insuranceIdNo;
    }

    public String getInsuranceGroupNo() {
        return this.insuranceGroupNo;
    }

    public InsuranceMaster insuranceGroupNo(String insuranceGroupNo) {
        this.setInsuranceGroupNo(insuranceGroupNo);
        return this;
    }

    public void setInsuranceGroupNo(String insuranceGroupNo) {
        this.insuranceGroupNo = insuranceGroupNo;
    }

    public String getPriceTableName() {
        return this.priceTableName;
    }

    public InsuranceMaster priceTableName(String priceTableName) {
        this.setPriceTableName(priceTableName);
        return this;
    }

    public void setPriceTableName(String priceTableName) {
        this.priceTableName = priceTableName;
    }

    public String getClaimProgramId() {
        return this.claimProgramId;
    }

    public InsuranceMaster claimProgramId(String claimProgramId) {
        this.setClaimProgramId(claimProgramId);
        return this;
    }

    public void setClaimProgramId(String claimProgramId) {
        this.claimProgramId = claimProgramId;
    }

    public String getClaimFormName() {
        return this.claimFormName;
    }

    public InsuranceMaster claimFormName(String claimFormName) {
        this.setClaimFormName(claimFormName);
        return this;
    }

    public void setClaimFormName(String claimFormName) {
        this.claimFormName = claimFormName;
    }

    public String getInsuranceGroupName() {
        return this.insuranceGroupName;
    }

    public InsuranceMaster insuranceGroupName(String insuranceGroupName) {
        this.setInsuranceGroupName(insuranceGroupName);
        return this;
    }

    public void setInsuranceGroupName(String insuranceGroupName) {
        this.insuranceGroupName = insuranceGroupName;
    }

    public String getContactPersonFirstName() {
        return this.contactPersonFirstName;
    }

    public InsuranceMaster contactPersonFirstName(String contactPersonFirstName) {
        this.setContactPersonFirstName(contactPersonFirstName);
        return this;
    }

    public void setContactPersonFirstName(String contactPersonFirstName) {
        this.contactPersonFirstName = contactPersonFirstName;
    }

    public String getContactPersonMiddleName() {
        return this.contactPersonMiddleName;
    }

    public InsuranceMaster contactPersonMiddleName(String contactPersonMiddleName) {
        this.setContactPersonMiddleName(contactPersonMiddleName);
        return this;
    }

    public void setContactPersonMiddleName(String contactPersonMiddleName) {
        this.contactPersonMiddleName = contactPersonMiddleName;
    }

    public String getContactPersonLastName() {
        return this.contactPersonLastName;
    }

    public InsuranceMaster contactPersonLastName(String contactPersonLastName) {
        this.setContactPersonLastName(contactPersonLastName);
        return this;
    }

    public void setContactPersonLastName(String contactPersonLastName) {
        this.contactPersonLastName = contactPersonLastName;
    }

    public String getTaxTncludedAllowableStatus() {
        return this.taxTncludedAllowableStatus;
    }

    public InsuranceMaster taxTncludedAllowableStatus(String taxTncludedAllowableStatus) {
        this.setTaxTncludedAllowableStatus(taxTncludedAllowableStatus);
        return this;
    }

    public void setTaxTncludedAllowableStatus(String taxTncludedAllowableStatus) {
        this.taxTncludedAllowableStatus = taxTncludedAllowableStatus;
    }

    public String getCmsCrossoverInsuranceIdNo() {
        return this.cmsCrossoverInsuranceIdNo;
    }

    public InsuranceMaster cmsCrossoverInsuranceIdNo(String cmsCrossoverInsuranceIdNo) {
        this.setCmsCrossoverInsuranceIdNo(cmsCrossoverInsuranceIdNo);
        return this;
    }

    public void setCmsCrossoverInsuranceIdNo(String cmsCrossoverInsuranceIdNo) {
        this.cmsCrossoverInsuranceIdNo = cmsCrossoverInsuranceIdNo;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InsuranceMaster)) {
            return false;
        }
        return insuranceId != null && insuranceId.equals(((InsuranceMaster) o).insuranceId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InsuranceMaster{" +
            "insuranceId=" + getInsuranceId() +
            ", insuranceName='" + getInsuranceName() + "'" +
            ", insurancePlanType='" + getInsurancePlanType() + "'" +
            ", taxType='" + getTaxType() + "'" +
            ", taxIncludedAllowableStatus='" + getTaxIncludedAllowableStatus() + "'" +
            ", insuranceGroupId=" + getInsuranceGroupId() +
            ", priceTableId=" + getPriceTableId() +
            ", claimTypeDmeStatus='" + getClaimTypeDmeStatus() + "'" +
            ", claimTypeMajorMadicalStatus='" + getClaimTypeMajorMadicalStatus() + "'" +
            ", claimTypePharmacyStatus='" + getClaimTypePharmacyStatus() + "'" +
            ", payPercentage=" + getPayPercentage() +
            ", enableSecondaryBillingStatus='" + getEnableSecondaryBillingStatus() + "'" +
            ", amtPrintOnDeliveryTicketStatus='" + getAmtPrintOnDeliveryTicketStatus() + "'" +
            ", medigapStatus='" + getMedigapStatus() + "'" +
            ", medigapNum=" + getMedigapNum() +
            ", notes='" + getNotes() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", insuranceMasterUuid='" + getInsuranceMasterUuid() + "'" +
            ", insurancePayerIdNo='" + getInsurancePayerIdNo() + "'" +
            ", addressLine1='" + getAddressLine1() + "'" +
            ", addressLine2='" + getAddressLine2() + "'" +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", country='" + getCountry() + "'" +
            ", zip='" + getZip() + "'" +
            ", contactNo1='" + getContactNo1() + "'" +
            ", contactNo2='" + getContactNo2() + "'" +
            ", fax='" + getFax() + "'" +
            ", efax='" + getEfax() + "'" +
            ", email='" + getEmail() + "'" +
            ", relationship='" + getRelationship() + "'" +
            ", modeOfContact='" + getModeOfContact() + "'" +
            ", claimProgramCode='" + getClaimProgramCode() + "'" +
            ", claimProgramName='" + getClaimProgramName() + "'" +
            ", insuranceIdNo='" + getInsuranceIdNo() + "'" +
            ", insuranceGroupNo='" + getInsuranceGroupNo() + "'" +
            ", priceTableName='" + getPriceTableName() + "'" +
            ", claimProgramId='" + getClaimProgramId() + "'" +
            ", claimFormName='" + getClaimFormName() + "'" +
            ", insuranceGroupName='" + getInsuranceGroupName() + "'" +
            ", contactPersonFirstName='" + getContactPersonFirstName() + "'" +
            ", contactPersonMiddleName='" + getContactPersonMiddleName() + "'" +
            ", contactPersonLastName='" + getContactPersonLastName() + "'" +
            ", taxTncludedAllowableStatus='" + getTaxTncludedAllowableStatus() + "'" +
            ", cmsCrossoverInsuranceIdNo='" + getCmsCrossoverInsuranceIdNo() + "'" +
            "}";
    }
}
