package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A SoRentalHelper.
 */
@Table("t_so_rental_helper")
public class SoRentalHelper implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("so_rental_helper_id")
    private Long soRentalHelperId;

    @Column("so_id")
    private Long soId;

    @Column("primary_insurer_id")
    private Long primaryInsurerId;

    @Column("primary_insurer_name")
    private String primaryInsurerName;

    @Column("item_id")
    private Long itemId;

    @Column("item_no")
    private String itemNo;

    @Column("item_name")
    private String itemName;

    @Column("charged_amount")
    private Double chargedAmount;

    @Column("allowed_amount")
    private Double allowedAmount;

    @Column("sou")
    private String sou;

    @Column("qty")
    private Double qty;

    @Column("dos_start")
    private LocalDate dosStart;

    @Column("dos_end")
    private LocalDate dosEnd;

    @Column("period_no")
    private String periodNo;

    @Column("rental_period")
    private String rentalPeriod;

    @Column("next_dos")
    private LocalDate nextDos;

    @Column("status")
    private String status;

    @Column("created_by_id")
    private Long createdById;

    @Column("created_date")
    private LocalDate createdDate;

    @Column("created_by_name")
    private String createdByName;

    @Column("updated_by_id")
    private Long updatedById;

    @Column("updated_date")
    private LocalDate updatedDate;

    @Column("updated_by_name")
    private String updatedByName;

    @Column("so_rental_helper_uuid")
    private UUID soRentalHelperUuid;

    @Column("patient_id")
    private Long patientId;

    @Column("sale_type")
    private String saleType;

    @Column("primary_insurance_price_table_id")
    private Long primaryInsurancePriceTableId;

    @Column("primary_insurance_price_table_name")
    private String primaryInsurancePriceTableName;

    @Column("modifier_1")
    private String modifier1;

    @Column("modifier_2")
    private String modifier2;

    @Column("modifier_3")
    private String modifier3;

    @Column("modifier_4")
    private String modifier4;

    @Column("icd_pointer")
    private String icdPointer;

    @Column("procedure_identifier")
    private String procedureIdentifier;

    @Column("ordering_provider_first_name")
    private String orderingProviderFirstName;

    @Column("ordering_provider_last_name")
    private String orderingProviderLastName;

    @Column("ordering_provider_npi")
    private String orderingProviderNpi;

    @Column("reference")
    private String reference;

    @Column("proc_code")
    private String procCode;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getSoRentalHelperId() {
        return this.soRentalHelperId;
    }

    public SoRentalHelper soRentalHelperId(Long soRentalHelperId) {
        this.setSoRentalHelperId(soRentalHelperId);
        return this;
    }

    public void setSoRentalHelperId(Long soRentalHelperId) {
        this.soRentalHelperId = soRentalHelperId;
    }

    public Long getSoId() {
        return this.soId;
    }

    public SoRentalHelper soId(Long soId) {
        this.setSoId(soId);
        return this;
    }

    public void setSoId(Long soId) {
        this.soId = soId;
    }

    public Long getPrimaryInsurerId() {
        return this.primaryInsurerId;
    }

    public SoRentalHelper primaryInsurerId(Long primaryInsurerId) {
        this.setPrimaryInsurerId(primaryInsurerId);
        return this;
    }

    public void setPrimaryInsurerId(Long primaryInsurerId) {
        this.primaryInsurerId = primaryInsurerId;
    }

    public String getPrimaryInsurerName() {
        return this.primaryInsurerName;
    }

    public SoRentalHelper primaryInsurerName(String primaryInsurerName) {
        this.setPrimaryInsurerName(primaryInsurerName);
        return this;
    }

    public void setPrimaryInsurerName(String primaryInsurerName) {
        this.primaryInsurerName = primaryInsurerName;
    }

    public Long getItemId() {
        return this.itemId;
    }

    public SoRentalHelper itemId(Long itemId) {
        this.setItemId(itemId);
        return this;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemNo() {
        return this.itemNo;
    }

    public SoRentalHelper itemNo(String itemNo) {
        this.setItemNo(itemNo);
        return this;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemName() {
        return this.itemName;
    }

    public SoRentalHelper itemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getChargedAmount() {
        return this.chargedAmount;
    }

    public SoRentalHelper chargedAmount(Double chargedAmount) {
        this.setChargedAmount(chargedAmount);
        return this;
    }

    public void setChargedAmount(Double chargedAmount) {
        this.chargedAmount = chargedAmount;
    }

    public Double getAllowedAmount() {
        return this.allowedAmount;
    }

    public SoRentalHelper allowedAmount(Double allowedAmount) {
        this.setAllowedAmount(allowedAmount);
        return this;
    }

    public void setAllowedAmount(Double allowedAmount) {
        this.allowedAmount = allowedAmount;
    }

    public String getSou() {
        return this.sou;
    }

    public SoRentalHelper sou(String sou) {
        this.setSou(sou);
        return this;
    }

    public void setSou(String sou) {
        this.sou = sou;
    }

    public Double getQty() {
        return this.qty;
    }

    public SoRentalHelper qty(Double qty) {
        this.setQty(qty);
        return this;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public LocalDate getDosStart() {
        return this.dosStart;
    }

    public SoRentalHelper dosStart(LocalDate dosStart) {
        this.setDosStart(dosStart);
        return this;
    }

    public void setDosStart(LocalDate dosStart) {
        this.dosStart = dosStart;
    }

    public LocalDate getDosEnd() {
        return this.dosEnd;
    }

    public SoRentalHelper dosEnd(LocalDate dosEnd) {
        this.setDosEnd(dosEnd);
        return this;
    }

    public void setDosEnd(LocalDate dosEnd) {
        this.dosEnd = dosEnd;
    }

    public String getPeriodNo() {
        return this.periodNo;
    }

    public SoRentalHelper periodNo(String periodNo) {
        this.setPeriodNo(periodNo);
        return this;
    }

    public void setPeriodNo(String periodNo) {
        this.periodNo = periodNo;
    }

    public String getRentalPeriod() {
        return this.rentalPeriod;
    }

    public SoRentalHelper rentalPeriod(String rentalPeriod) {
        this.setRentalPeriod(rentalPeriod);
        return this;
    }

    public void setRentalPeriod(String rentalPeriod) {
        this.rentalPeriod = rentalPeriod;
    }

    public LocalDate getNextDos() {
        return this.nextDos;
    }

    public SoRentalHelper nextDos(LocalDate nextDos) {
        this.setNextDos(nextDos);
        return this;
    }

    public void setNextDos(LocalDate nextDos) {
        this.nextDos = nextDos;
    }

    public String getStatus() {
        return this.status;
    }

    public SoRentalHelper status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public SoRentalHelper createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public SoRentalHelper createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public SoRentalHelper createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public SoRentalHelper updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public SoRentalHelper updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public SoRentalHelper updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getSoRentalHelperUuid() {
        return this.soRentalHelperUuid;
    }

    public SoRentalHelper soRentalHelperUuid(UUID soRentalHelperUuid) {
        this.setSoRentalHelperUuid(soRentalHelperUuid);
        return this;
    }

    public void setSoRentalHelperUuid(UUID soRentalHelperUuid) {
        this.soRentalHelperUuid = soRentalHelperUuid;
    }

    public Long getPatientId() {
        return this.patientId;
    }

    public SoRentalHelper patientId(Long patientId) {
        this.setPatientId(patientId);
        return this;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getSaleType() {
        return this.saleType;
    }

    public SoRentalHelper saleType(String saleType) {
        this.setSaleType(saleType);
        return this;
    }

    public void setSaleType(String saleType) {
        this.saleType = saleType;
    }

    public Long getPrimaryInsurancePriceTableId() {
        return this.primaryInsurancePriceTableId;
    }

    public SoRentalHelper primaryInsurancePriceTableId(Long primaryInsurancePriceTableId) {
        this.setPrimaryInsurancePriceTableId(primaryInsurancePriceTableId);
        return this;
    }

    public void setPrimaryInsurancePriceTableId(Long primaryInsurancePriceTableId) {
        this.primaryInsurancePriceTableId = primaryInsurancePriceTableId;
    }

    public String getPrimaryInsurancePriceTableName() {
        return this.primaryInsurancePriceTableName;
    }

    public SoRentalHelper primaryInsurancePriceTableName(String primaryInsurancePriceTableName) {
        this.setPrimaryInsurancePriceTableName(primaryInsurancePriceTableName);
        return this;
    }

    public void setPrimaryInsurancePriceTableName(String primaryInsurancePriceTableName) {
        this.primaryInsurancePriceTableName = primaryInsurancePriceTableName;
    }

    public String getModifier1() {
        return this.modifier1;
    }

    public SoRentalHelper modifier1(String modifier1) {
        this.setModifier1(modifier1);
        return this;
    }

    public void setModifier1(String modifier1) {
        this.modifier1 = modifier1;
    }

    public String getModifier2() {
        return this.modifier2;
    }

    public SoRentalHelper modifier2(String modifier2) {
        this.setModifier2(modifier2);
        return this;
    }

    public void setModifier2(String modifier2) {
        this.modifier2 = modifier2;
    }

    public String getModifier3() {
        return this.modifier3;
    }

    public SoRentalHelper modifier3(String modifier3) {
        this.setModifier3(modifier3);
        return this;
    }

    public void setModifier3(String modifier3) {
        this.modifier3 = modifier3;
    }

    public String getModifier4() {
        return this.modifier4;
    }

    public SoRentalHelper modifier4(String modifier4) {
        this.setModifier4(modifier4);
        return this;
    }

    public void setModifier4(String modifier4) {
        this.modifier4 = modifier4;
    }

    public String getIcdPointer() {
        return this.icdPointer;
    }

    public SoRentalHelper icdPointer(String icdPointer) {
        this.setIcdPointer(icdPointer);
        return this;
    }

    public void setIcdPointer(String icdPointer) {
        this.icdPointer = icdPointer;
    }

    public String getProcedureIdentifier() {
        return this.procedureIdentifier;
    }

    public SoRentalHelper procedureIdentifier(String procedureIdentifier) {
        this.setProcedureIdentifier(procedureIdentifier);
        return this;
    }

    public void setProcedureIdentifier(String procedureIdentifier) {
        this.procedureIdentifier = procedureIdentifier;
    }

    public String getOrderingProviderFirstName() {
        return this.orderingProviderFirstName;
    }

    public SoRentalHelper orderingProviderFirstName(String orderingProviderFirstName) {
        this.setOrderingProviderFirstName(orderingProviderFirstName);
        return this;
    }

    public void setOrderingProviderFirstName(String orderingProviderFirstName) {
        this.orderingProviderFirstName = orderingProviderFirstName;
    }

    public String getOrderingProviderLastName() {
        return this.orderingProviderLastName;
    }

    public SoRentalHelper orderingProviderLastName(String orderingProviderLastName) {
        this.setOrderingProviderLastName(orderingProviderLastName);
        return this;
    }

    public void setOrderingProviderLastName(String orderingProviderLastName) {
        this.orderingProviderLastName = orderingProviderLastName;
    }

    public String getOrderingProviderNpi() {
        return this.orderingProviderNpi;
    }

    public SoRentalHelper orderingProviderNpi(String orderingProviderNpi) {
        this.setOrderingProviderNpi(orderingProviderNpi);
        return this;
    }

    public void setOrderingProviderNpi(String orderingProviderNpi) {
        this.orderingProviderNpi = orderingProviderNpi;
    }

    public String getReference() {
        return this.reference;
    }

    public SoRentalHelper reference(String reference) {
        this.setReference(reference);
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getProcCode() {
        return this.procCode;
    }

    public SoRentalHelper procCode(String procCode) {
        this.setProcCode(procCode);
        return this;
    }

    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SoRentalHelper)) {
            return false;
        }
        return soRentalHelperId != null && soRentalHelperId.equals(((SoRentalHelper) o).soRentalHelperId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SoRentalHelper{" +
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
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
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
