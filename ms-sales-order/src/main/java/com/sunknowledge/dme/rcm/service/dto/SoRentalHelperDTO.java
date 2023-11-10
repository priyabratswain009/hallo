package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.SoRentalHelper} entity.
 */
public class SoRentalHelperDTO implements Serializable {

    private Long soRentalHelperId;

    private Long soId;

    private Long primaryInsurerId;

    private String primaryInsurerName;

    private Long itemId;

    private String itemNo;

    private String itemName;

    private Double chargedAmount;

    private Double allowedAmount;

    private String sou;

    private Double qty;

    private LocalDate dosStart;

    private LocalDate dosEnd;

    private String periodNo;

    private String rentalPeriod;

    private LocalDate nextDos;

    private String status;

    private String createdById;

    private LocalDate createdDate;

    private String createdByName;

    private String updatedById;

    private LocalDate updatedDate;

    private String updatedByName;

    private UUID soRentalHelperUuid;

    private Long patientId;

    private String saleType;

    private Long primaryInsurancePriceTableId;

    private String primaryInsurancePriceTableName;

    private String modifier1;

    private String modifier2;

    private String modifier3;

    private String modifier4;

    private String icdPointer;

    private String procedureIdentifier;

    private String orderingProviderFirstName;

    private String orderingProviderLastName;

    private String orderingProviderNpi;

    private String reference;

    private String procCode;

    public Long getSoRentalHelperId() {
        return soRentalHelperId;
    }

    public void setSoRentalHelperId(Long soRentalHelperId) {
        this.soRentalHelperId = soRentalHelperId;
    }

    public Long getSoId() {
        return soId;
    }

    public void setSoId(Long soId) {
        this.soId = soId;
    }

    public Long getPrimaryInsurerId() {
        return primaryInsurerId;
    }

    public void setPrimaryInsurerId(Long primaryInsurerId) {
        this.primaryInsurerId = primaryInsurerId;
    }

    public String getPrimaryInsurerName() {
        return primaryInsurerName;
    }

    public void setPrimaryInsurerName(String primaryInsurerName) {
        this.primaryInsurerName = primaryInsurerName;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getChargedAmount() {
        return chargedAmount;
    }

    public void setChargedAmount(Double chargedAmount) {
        this.chargedAmount = chargedAmount;
    }

    public Double getAllowedAmount() {
        return allowedAmount;
    }

    public void setAllowedAmount(Double allowedAmount) {
        this.allowedAmount = allowedAmount;
    }

    public String getSou() {
        return sou;
    }

    public void setSou(String sou) {
        this.sou = sou;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public LocalDate getDosStart() {
        return dosStart;
    }

    public void setDosStart(LocalDate dosStart) {
        this.dosStart = dosStart;
    }

    public LocalDate getDosEnd() {
        return dosEnd;
    }

    public void setDosEnd(LocalDate dosEnd) {
        this.dosEnd = dosEnd;
    }

    public String getPeriodNo() {
        return periodNo;
    }

    public void setPeriodNo(String periodNo) {
        this.periodNo = periodNo;
    }

    public String getRentalPeriod() {
        return rentalPeriod;
    }

    public void setRentalPeriod(String rentalPeriod) {
        this.rentalPeriod = rentalPeriod;
    }

    public LocalDate getNextDos() {
        return nextDos;
    }

    public void setNextDos(LocalDate nextDos) {
        this.nextDos = nextDos;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedById() {
        return createdById;
    }

    public void setCreatedById(String createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(String updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getSoRentalHelperUuid() {
        return soRentalHelperUuid;
    }

    public void setSoRentalHelperUuid(UUID soRentalHelperUuid) {
        this.soRentalHelperUuid = soRentalHelperUuid;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getSaleType() {
        return saleType;
    }

    public void setSaleType(String saleType) {
        this.saleType = saleType;
    }

    public Long getPrimaryInsurancePriceTableId() {
        return primaryInsurancePriceTableId;
    }

    public void setPrimaryInsurancePriceTableId(Long primaryInsurancePriceTableId) {
        this.primaryInsurancePriceTableId = primaryInsurancePriceTableId;
    }

    public String getPrimaryInsurancePriceTableName() {
        return primaryInsurancePriceTableName;
    }

    public void setPrimaryInsurancePriceTableName(String primaryInsurancePriceTableName) {
        this.primaryInsurancePriceTableName = primaryInsurancePriceTableName;
    }

    public String getModifier1() {
        return modifier1;
    }

    public void setModifier1(String modifier1) {
        this.modifier1 = modifier1;
    }

    public String getModifier2() {
        return modifier2;
    }

    public void setModifier2(String modifier2) {
        this.modifier2 = modifier2;
    }

    public String getModifier3() {
        return modifier3;
    }

    public void setModifier3(String modifier3) {
        this.modifier3 = modifier3;
    }

    public String getModifier4() {
        return modifier4;
    }

    public void setModifier4(String modifier4) {
        this.modifier4 = modifier4;
    }

    public String getIcdPointer() {
        return icdPointer;
    }

    public void setIcdPointer(String icdPointer) {
        this.icdPointer = icdPointer;
    }

    public String getProcedureIdentifier() {
        return procedureIdentifier;
    }

    public void setProcedureIdentifier(String procedureIdentifier) {
        this.procedureIdentifier = procedureIdentifier;
    }

    public String getOrderingProviderFirstName() {
        return orderingProviderFirstName;
    }

    public void setOrderingProviderFirstName(String orderingProviderFirstName) {
        this.orderingProviderFirstName = orderingProviderFirstName;
    }

    public String getOrderingProviderLastName() {
        return orderingProviderLastName;
    }

    public void setOrderingProviderLastName(String orderingProviderLastName) {
        this.orderingProviderLastName = orderingProviderLastName;
    }

    public String getOrderingProviderNpi() {
        return orderingProviderNpi;
    }

    public void setOrderingProviderNpi(String orderingProviderNpi) {
        this.orderingProviderNpi = orderingProviderNpi;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getProcCode() {
        return procCode;
    }

    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SoRentalHelperDTO)) {
            return false;
        }

        SoRentalHelperDTO soRentalHelperDTO = (SoRentalHelperDTO) o;
        if (this.soRentalHelperId == null) {
            return false;
        }
        return Objects.equals(this.soRentalHelperId, soRentalHelperDTO.soRentalHelperId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.soRentalHelperId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SoRentalHelperDTO{" +
            "soRentalHelperId=" + getSoRentalHelperId() +
            ", soId=" + getSoId() +
            ", primaryInsurerId=" + getPrimaryInsurerId() +
            ", primaryInsurerName='" + getPrimaryInsurerName() + "'" +
            ", itemId=" + getItemId() +
            ", itemNo='" + getItemNo() + "'" +
            ", itemName='" + getItemName() + "'" +
            ", chargedAmount=" + getChargedAmount() +
            ", allowedAmount=" + getAllowedAmount() +
            ", sou='" + getSou() + "'" +
            ", qty=" + getQty() +
            ", dosStart='" + getDosStart() + "'" +
            ", dosEnd='" + getDosEnd() + "'" +
            ", periodNo='" + getPeriodNo() + "'" +
            ", rentalPeriod='" + getRentalPeriod() + "'" +
            ", nextDos='" + getNextDos() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById='" + getCreatedById() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedById='" + getUpdatedById() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", soRentalHelperUuid='" + getSoRentalHelperUuid() + "'" +
            ", patientId=" + getPatientId() +
            ", saleType='" + getSaleType() + "'" +
            ", primaryInsurancePriceTableId=" + getPrimaryInsurancePriceTableId() +
            ", primaryInsurancePriceTableName='" + getPrimaryInsurancePriceTableName() + "'" +
            ", modifier1='" + getModifier1() + "'" +
            ", modifier2='" + getModifier2() + "'" +
            ", modifier3='" + getModifier3() + "'" +
            ", modifier4='" + getModifier4() + "'" +
            ", icdPointer='" + getIcdPointer() + "'" +
            ", procedureIdentifier='" + getProcedureIdentifier() + "'" +
            ", orderingProviderFirstName='" + getOrderingProviderFirstName() + "'" +
            ", orderingProviderLastName='" + getOrderingProviderLastName() + "'" +
            ", orderingProviderNpi='" + getOrderingProviderNpi() + "'" +
            ", reference='" + getReference() + "'" +
            ", procCode='" + getProcCode() + "'" +
            "}";
    }
}
