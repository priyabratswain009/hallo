package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A ClaimsCob835Details.
 */
@Table("t_claims_cob_835_details")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ClaimsCob835Details implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("claim_cob_835_detail_id")
    private Long claimCob835DetailId;

    @Column("service_date")
    private LocalDate serviceDate;

    @Column("adjudicated_procedure_code")
    private String adjudicatedProcedureCode;

    @Column("adjudicated_procedure_modifier_codes")
    private String adjudicatedProcedureModifierCodes;

    @Column("charge_amount")
    private Double chargeAmount;

    @Column("allowed_amount")
    private Double allowedAmount;

    @Column("adjustment_pr_code_1")
    private String adjustmentPrCode1;

    @Column("adjustment_pr_code_1_amount")
    private Double adjustmentPrCode1Amount;

    @Column("adjustment_pr_code_2")
    private String adjustmentPrCode2;

    @Column("adjustment_pr_code_2_amount")
    private Double adjustmentPrCode2Amount;

    @Column("adjustment_pr_code_3")
    private String adjustmentPrCode3;

    @Column("adjustment_pr_code_3_amount")
    private Double adjustmentPrCode3Amount;

    @Column("adjustment_pr_code_4")
    private String adjustmentPrCode4;

    @Column("adjustment_pr_code_4_amount")
    private Double adjustmentPrCode4Amount;

    @Column("adjustment_co_code_1")
    private String adjustmentCoCode1;

    @Column("adjustment_co_code_1_amount")
    private Double adjustmentCoCode1Amount;

    @Column("adjustment_co_code_2")
    private String adjustmentCoCode2;

    @Column("adjustment_co_code_2_amount")
    private Double adjustmentCoCode2Amount;

    @Column("adjustment_co_code_3")
    private String adjustmentCoCode3;

    @Column("adjustment_co_code_3_amount")
    private Double adjustmentCoCode3Amount;

    @Column("adjustment_co_code_4")
    private String adjustmentCoCode4;

    @Column("adjustment_co_code_4_amount")
    private Double adjustmentCoCode4Amount;

    @Column("adjustment_cr_code_1")
    private String adjustmentCrCode1;

    @Column("adjustment_cr_code_1_amount")
    private Double adjustmentCrCode1Amount;

    @Column("adjustment_cr_code_2")
    private String adjustmentCrCode2;

    @Column("adjustment_cr_code_2_amount")
    private Double adjustmentCrCode2Amount;

    @Column("adjustment_cr_code_3")
    private String adjustmentCrCode3;

    @Column("adjustment_cr_code_3_amount")
    private Double adjustmentCrCode3Amount;

    @Column("adjustment_cr_code_4")
    private String adjustmentCrCode4;

    @Column("adjustment_cr_code_4_amount")
    private Double adjustmentCrCode4Amount;

    @Column("adjustment_oa_code_1")
    private String adjustmentOaCode1;

    @Column("adjustment_oa_code_1_amount")
    private Double adjustmentOaCode1Amount;

    @Column("adjustment_oa_code_2")
    private String adjustmentOaCode2;

    @Column("adjustment_oa_code_2_amount")
    private Double adjustmentOaCode2Amount;

    @Column("adjustment_oa_code_3")
    private String adjustmentOaCode3;

    @Column("adjustment_oa_code_3_amount")
    private Double adjustmentOaCode3Amount;

    @Column("adjustment_oa_code_4")
    private String adjustmentOaCode4;

    @Column("adjustment_oa_code_4_amount")
    private Double adjustmentOaCode4Amount;

    @Column("adjustment_pi_code_1")
    private String adjustmentPiCode1;

    @Column("adjustment_pi_code_1_amount")
    private Double adjustmentPiCode1Amount;

    @Column("adjustment_pi_code_2")
    private String adjustmentPiCode2;

    @Column("adjustment_pi_code_2_amount")
    private Double adjustmentPiCode2Amount;

    @Column("adjustment_pi_code_3")
    private String adjustmentPiCode3;

    @Column("adjustment_pi_code_3_amount")
    private Double adjustmentPiCode3Amount;

    @Column("adjustment_pi_code_4")
    private String adjustmentPiCode4;

    @Column("adjustment_pi_code_4_amount")
    private Double adjustmentPiCode4Amount;

    @Column("provider_payment_amount")
    private Double providerPaymentAmount;

    @Column("claim_cob_835_master_id")
    private Long claimCob835MasterId;

    @Column("status")
    private String status;

    @Column("service_date_to")
    private LocalDate serviceDateTo;

    @Column("unit_count")
    private Long unitCount;

    @Column("claims_cob_835_details_uuid")
    private UUID claimsCob835DetailsUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getClaimCob835DetailId() {
        return this.claimCob835DetailId;
    }

    public ClaimsCob835Details claimCob835DetailId(Long claimCob835DetailId) {
        this.setClaimCob835DetailId(claimCob835DetailId);
        return this;
    }

    public void setClaimCob835DetailId(Long claimCob835DetailId) {
        this.claimCob835DetailId = claimCob835DetailId;
    }

    public LocalDate getServiceDate() {
        return this.serviceDate;
    }

    public ClaimsCob835Details serviceDate(LocalDate serviceDate) {
        this.setServiceDate(serviceDate);
        return this;
    }

    public void setServiceDate(LocalDate serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getAdjudicatedProcedureCode() {
        return this.adjudicatedProcedureCode;
    }

    public ClaimsCob835Details adjudicatedProcedureCode(String adjudicatedProcedureCode) {
        this.setAdjudicatedProcedureCode(adjudicatedProcedureCode);
        return this;
    }

    public void setAdjudicatedProcedureCode(String adjudicatedProcedureCode) {
        this.adjudicatedProcedureCode = adjudicatedProcedureCode;
    }

    public String getAdjudicatedProcedureModifierCodes() {
        return this.adjudicatedProcedureModifierCodes;
    }

    public ClaimsCob835Details adjudicatedProcedureModifierCodes(String adjudicatedProcedureModifierCodes) {
        this.setAdjudicatedProcedureModifierCodes(adjudicatedProcedureModifierCodes);
        return this;
    }

    public void setAdjudicatedProcedureModifierCodes(String adjudicatedProcedureModifierCodes) {
        this.adjudicatedProcedureModifierCodes = adjudicatedProcedureModifierCodes;
    }

    public Double getChargeAmount() {
        return this.chargeAmount;
    }

    public ClaimsCob835Details chargeAmount(Double chargeAmount) {
        this.setChargeAmount(chargeAmount);
        return this;
    }

    public void setChargeAmount(Double chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public Double getAllowedAmount() {
        return this.allowedAmount;
    }

    public ClaimsCob835Details allowedAmount(Double allowedAmount) {
        this.setAllowedAmount(allowedAmount);
        return this;
    }

    public void setAllowedAmount(Double allowedAmount) {
        this.allowedAmount = allowedAmount;
    }

    public String getAdjustmentPrCode1() {
        return this.adjustmentPrCode1;
    }

    public ClaimsCob835Details adjustmentPrCode1(String adjustmentPrCode1) {
        this.setAdjustmentPrCode1(adjustmentPrCode1);
        return this;
    }

    public void setAdjustmentPrCode1(String adjustmentPrCode1) {
        this.adjustmentPrCode1 = adjustmentPrCode1;
    }

    public Double getAdjustmentPrCode1Amount() {
        return this.adjustmentPrCode1Amount;
    }

    public ClaimsCob835Details adjustmentPrCode1Amount(Double adjustmentPrCode1Amount) {
        this.setAdjustmentPrCode1Amount(adjustmentPrCode1Amount);
        return this;
    }

    public void setAdjustmentPrCode1Amount(Double adjustmentPrCode1Amount) {
        this.adjustmentPrCode1Amount = adjustmentPrCode1Amount;
    }

    public String getAdjustmentPrCode2() {
        return this.adjustmentPrCode2;
    }

    public ClaimsCob835Details adjustmentPrCode2(String adjustmentPrCode2) {
        this.setAdjustmentPrCode2(adjustmentPrCode2);
        return this;
    }

    public void setAdjustmentPrCode2(String adjustmentPrCode2) {
        this.adjustmentPrCode2 = adjustmentPrCode2;
    }

    public Double getAdjustmentPrCode2Amount() {
        return this.adjustmentPrCode2Amount;
    }

    public ClaimsCob835Details adjustmentPrCode2Amount(Double adjustmentPrCode2Amount) {
        this.setAdjustmentPrCode2Amount(adjustmentPrCode2Amount);
        return this;
    }

    public void setAdjustmentPrCode2Amount(Double adjustmentPrCode2Amount) {
        this.adjustmentPrCode2Amount = adjustmentPrCode2Amount;
    }

    public String getAdjustmentPrCode3() {
        return this.adjustmentPrCode3;
    }

    public ClaimsCob835Details adjustmentPrCode3(String adjustmentPrCode3) {
        this.setAdjustmentPrCode3(adjustmentPrCode3);
        return this;
    }

    public void setAdjustmentPrCode3(String adjustmentPrCode3) {
        this.adjustmentPrCode3 = adjustmentPrCode3;
    }

    public Double getAdjustmentPrCode3Amount() {
        return this.adjustmentPrCode3Amount;
    }

    public ClaimsCob835Details adjustmentPrCode3Amount(Double adjustmentPrCode3Amount) {
        this.setAdjustmentPrCode3Amount(adjustmentPrCode3Amount);
        return this;
    }

    public void setAdjustmentPrCode3Amount(Double adjustmentPrCode3Amount) {
        this.adjustmentPrCode3Amount = adjustmentPrCode3Amount;
    }

    public String getAdjustmentPrCode4() {
        return this.adjustmentPrCode4;
    }

    public ClaimsCob835Details adjustmentPrCode4(String adjustmentPrCode4) {
        this.setAdjustmentPrCode4(adjustmentPrCode4);
        return this;
    }

    public void setAdjustmentPrCode4(String adjustmentPrCode4) {
        this.adjustmentPrCode4 = adjustmentPrCode4;
    }

    public Double getAdjustmentPrCode4Amount() {
        return this.adjustmentPrCode4Amount;
    }

    public ClaimsCob835Details adjustmentPrCode4Amount(Double adjustmentPrCode4Amount) {
        this.setAdjustmentPrCode4Amount(adjustmentPrCode4Amount);
        return this;
    }

    public void setAdjustmentPrCode4Amount(Double adjustmentPrCode4Amount) {
        this.adjustmentPrCode4Amount = adjustmentPrCode4Amount;
    }

    public String getAdjustmentCoCode1() {
        return this.adjustmentCoCode1;
    }

    public ClaimsCob835Details adjustmentCoCode1(String adjustmentCoCode1) {
        this.setAdjustmentCoCode1(adjustmentCoCode1);
        return this;
    }

    public void setAdjustmentCoCode1(String adjustmentCoCode1) {
        this.adjustmentCoCode1 = adjustmentCoCode1;
    }

    public Double getAdjustmentCoCode1Amount() {
        return this.adjustmentCoCode1Amount;
    }

    public ClaimsCob835Details adjustmentCoCode1Amount(Double adjustmentCoCode1Amount) {
        this.setAdjustmentCoCode1Amount(adjustmentCoCode1Amount);
        return this;
    }

    public void setAdjustmentCoCode1Amount(Double adjustmentCoCode1Amount) {
        this.adjustmentCoCode1Amount = adjustmentCoCode1Amount;
    }

    public String getAdjustmentCoCode2() {
        return this.adjustmentCoCode2;
    }

    public ClaimsCob835Details adjustmentCoCode2(String adjustmentCoCode2) {
        this.setAdjustmentCoCode2(adjustmentCoCode2);
        return this;
    }

    public void setAdjustmentCoCode2(String adjustmentCoCode2) {
        this.adjustmentCoCode2 = adjustmentCoCode2;
    }

    public Double getAdjustmentCoCode2Amount() {
        return this.adjustmentCoCode2Amount;
    }

    public ClaimsCob835Details adjustmentCoCode2Amount(Double adjustmentCoCode2Amount) {
        this.setAdjustmentCoCode2Amount(adjustmentCoCode2Amount);
        return this;
    }

    public void setAdjustmentCoCode2Amount(Double adjustmentCoCode2Amount) {
        this.adjustmentCoCode2Amount = adjustmentCoCode2Amount;
    }

    public String getAdjustmentCoCode3() {
        return this.adjustmentCoCode3;
    }

    public ClaimsCob835Details adjustmentCoCode3(String adjustmentCoCode3) {
        this.setAdjustmentCoCode3(adjustmentCoCode3);
        return this;
    }

    public void setAdjustmentCoCode3(String adjustmentCoCode3) {
        this.adjustmentCoCode3 = adjustmentCoCode3;
    }

    public Double getAdjustmentCoCode3Amount() {
        return this.adjustmentCoCode3Amount;
    }

    public ClaimsCob835Details adjustmentCoCode3Amount(Double adjustmentCoCode3Amount) {
        this.setAdjustmentCoCode3Amount(adjustmentCoCode3Amount);
        return this;
    }

    public void setAdjustmentCoCode3Amount(Double adjustmentCoCode3Amount) {
        this.adjustmentCoCode3Amount = adjustmentCoCode3Amount;
    }

    public String getAdjustmentCoCode4() {
        return this.adjustmentCoCode4;
    }

    public ClaimsCob835Details adjustmentCoCode4(String adjustmentCoCode4) {
        this.setAdjustmentCoCode4(adjustmentCoCode4);
        return this;
    }

    public void setAdjustmentCoCode4(String adjustmentCoCode4) {
        this.adjustmentCoCode4 = adjustmentCoCode4;
    }

    public Double getAdjustmentCoCode4Amount() {
        return this.adjustmentCoCode4Amount;
    }

    public ClaimsCob835Details adjustmentCoCode4Amount(Double adjustmentCoCode4Amount) {
        this.setAdjustmentCoCode4Amount(adjustmentCoCode4Amount);
        return this;
    }

    public void setAdjustmentCoCode4Amount(Double adjustmentCoCode4Amount) {
        this.adjustmentCoCode4Amount = adjustmentCoCode4Amount;
    }

    public String getAdjustmentCrCode1() {
        return this.adjustmentCrCode1;
    }

    public ClaimsCob835Details adjustmentCrCode1(String adjustmentCrCode1) {
        this.setAdjustmentCrCode1(adjustmentCrCode1);
        return this;
    }

    public void setAdjustmentCrCode1(String adjustmentCrCode1) {
        this.adjustmentCrCode1 = adjustmentCrCode1;
    }

    public Double getAdjustmentCrCode1Amount() {
        return this.adjustmentCrCode1Amount;
    }

    public ClaimsCob835Details adjustmentCrCode1Amount(Double adjustmentCrCode1Amount) {
        this.setAdjustmentCrCode1Amount(adjustmentCrCode1Amount);
        return this;
    }

    public void setAdjustmentCrCode1Amount(Double adjustmentCrCode1Amount) {
        this.adjustmentCrCode1Amount = adjustmentCrCode1Amount;
    }

    public String getAdjustmentCrCode2() {
        return this.adjustmentCrCode2;
    }

    public ClaimsCob835Details adjustmentCrCode2(String adjustmentCrCode2) {
        this.setAdjustmentCrCode2(adjustmentCrCode2);
        return this;
    }

    public void setAdjustmentCrCode2(String adjustmentCrCode2) {
        this.adjustmentCrCode2 = adjustmentCrCode2;
    }

    public Double getAdjustmentCrCode2Amount() {
        return this.adjustmentCrCode2Amount;
    }

    public ClaimsCob835Details adjustmentCrCode2Amount(Double adjustmentCrCode2Amount) {
        this.setAdjustmentCrCode2Amount(adjustmentCrCode2Amount);
        return this;
    }

    public void setAdjustmentCrCode2Amount(Double adjustmentCrCode2Amount) {
        this.adjustmentCrCode2Amount = adjustmentCrCode2Amount;
    }

    public String getAdjustmentCrCode3() {
        return this.adjustmentCrCode3;
    }

    public ClaimsCob835Details adjustmentCrCode3(String adjustmentCrCode3) {
        this.setAdjustmentCrCode3(adjustmentCrCode3);
        return this;
    }

    public void setAdjustmentCrCode3(String adjustmentCrCode3) {
        this.adjustmentCrCode3 = adjustmentCrCode3;
    }

    public Double getAdjustmentCrCode3Amount() {
        return this.adjustmentCrCode3Amount;
    }

    public ClaimsCob835Details adjustmentCrCode3Amount(Double adjustmentCrCode3Amount) {
        this.setAdjustmentCrCode3Amount(adjustmentCrCode3Amount);
        return this;
    }

    public void setAdjustmentCrCode3Amount(Double adjustmentCrCode3Amount) {
        this.adjustmentCrCode3Amount = adjustmentCrCode3Amount;
    }

    public String getAdjustmentCrCode4() {
        return this.adjustmentCrCode4;
    }

    public ClaimsCob835Details adjustmentCrCode4(String adjustmentCrCode4) {
        this.setAdjustmentCrCode4(adjustmentCrCode4);
        return this;
    }

    public void setAdjustmentCrCode4(String adjustmentCrCode4) {
        this.adjustmentCrCode4 = adjustmentCrCode4;
    }

    public Double getAdjustmentCrCode4Amount() {
        return this.adjustmentCrCode4Amount;
    }

    public ClaimsCob835Details adjustmentCrCode4Amount(Double adjustmentCrCode4Amount) {
        this.setAdjustmentCrCode4Amount(adjustmentCrCode4Amount);
        return this;
    }

    public void setAdjustmentCrCode4Amount(Double adjustmentCrCode4Amount) {
        this.adjustmentCrCode4Amount = adjustmentCrCode4Amount;
    }

    public String getAdjustmentOaCode1() {
        return this.adjustmentOaCode1;
    }

    public ClaimsCob835Details adjustmentOaCode1(String adjustmentOaCode1) {
        this.setAdjustmentOaCode1(adjustmentOaCode1);
        return this;
    }

    public void setAdjustmentOaCode1(String adjustmentOaCode1) {
        this.adjustmentOaCode1 = adjustmentOaCode1;
    }

    public Double getAdjustmentOaCode1Amount() {
        return this.adjustmentOaCode1Amount;
    }

    public ClaimsCob835Details adjustmentOaCode1Amount(Double adjustmentOaCode1Amount) {
        this.setAdjustmentOaCode1Amount(adjustmentOaCode1Amount);
        return this;
    }

    public void setAdjustmentOaCode1Amount(Double adjustmentOaCode1Amount) {
        this.adjustmentOaCode1Amount = adjustmentOaCode1Amount;
    }

    public String getAdjustmentOaCode2() {
        return this.adjustmentOaCode2;
    }

    public ClaimsCob835Details adjustmentOaCode2(String adjustmentOaCode2) {
        this.setAdjustmentOaCode2(adjustmentOaCode2);
        return this;
    }

    public void setAdjustmentOaCode2(String adjustmentOaCode2) {
        this.adjustmentOaCode2 = adjustmentOaCode2;
    }

    public Double getAdjustmentOaCode2Amount() {
        return this.adjustmentOaCode2Amount;
    }

    public ClaimsCob835Details adjustmentOaCode2Amount(Double adjustmentOaCode2Amount) {
        this.setAdjustmentOaCode2Amount(adjustmentOaCode2Amount);
        return this;
    }

    public void setAdjustmentOaCode2Amount(Double adjustmentOaCode2Amount) {
        this.adjustmentOaCode2Amount = adjustmentOaCode2Amount;
    }

    public String getAdjustmentOaCode3() {
        return this.adjustmentOaCode3;
    }

    public ClaimsCob835Details adjustmentOaCode3(String adjustmentOaCode3) {
        this.setAdjustmentOaCode3(adjustmentOaCode3);
        return this;
    }

    public void setAdjustmentOaCode3(String adjustmentOaCode3) {
        this.adjustmentOaCode3 = adjustmentOaCode3;
    }

    public Double getAdjustmentOaCode3Amount() {
        return this.adjustmentOaCode3Amount;
    }

    public ClaimsCob835Details adjustmentOaCode3Amount(Double adjustmentOaCode3Amount) {
        this.setAdjustmentOaCode3Amount(adjustmentOaCode3Amount);
        return this;
    }

    public void setAdjustmentOaCode3Amount(Double adjustmentOaCode3Amount) {
        this.adjustmentOaCode3Amount = adjustmentOaCode3Amount;
    }

    public String getAdjustmentOaCode4() {
        return this.adjustmentOaCode4;
    }

    public ClaimsCob835Details adjustmentOaCode4(String adjustmentOaCode4) {
        this.setAdjustmentOaCode4(adjustmentOaCode4);
        return this;
    }

    public void setAdjustmentOaCode4(String adjustmentOaCode4) {
        this.adjustmentOaCode4 = adjustmentOaCode4;
    }

    public Double getAdjustmentOaCode4Amount() {
        return this.adjustmentOaCode4Amount;
    }

    public ClaimsCob835Details adjustmentOaCode4Amount(Double adjustmentOaCode4Amount) {
        this.setAdjustmentOaCode4Amount(adjustmentOaCode4Amount);
        return this;
    }

    public void setAdjustmentOaCode4Amount(Double adjustmentOaCode4Amount) {
        this.adjustmentOaCode4Amount = adjustmentOaCode4Amount;
    }

    public String getAdjustmentPiCode1() {
        return this.adjustmentPiCode1;
    }

    public ClaimsCob835Details adjustmentPiCode1(String adjustmentPiCode1) {
        this.setAdjustmentPiCode1(adjustmentPiCode1);
        return this;
    }

    public void setAdjustmentPiCode1(String adjustmentPiCode1) {
        this.adjustmentPiCode1 = adjustmentPiCode1;
    }

    public Double getAdjustmentPiCode1Amount() {
        return this.adjustmentPiCode1Amount;
    }

    public ClaimsCob835Details adjustmentPiCode1Amount(Double adjustmentPiCode1Amount) {
        this.setAdjustmentPiCode1Amount(adjustmentPiCode1Amount);
        return this;
    }

    public void setAdjustmentPiCode1Amount(Double adjustmentPiCode1Amount) {
        this.adjustmentPiCode1Amount = adjustmentPiCode1Amount;
    }

    public String getAdjustmentPiCode2() {
        return this.adjustmentPiCode2;
    }

    public ClaimsCob835Details adjustmentPiCode2(String adjustmentPiCode2) {
        this.setAdjustmentPiCode2(adjustmentPiCode2);
        return this;
    }

    public void setAdjustmentPiCode2(String adjustmentPiCode2) {
        this.adjustmentPiCode2 = adjustmentPiCode2;
    }

    public Double getAdjustmentPiCode2Amount() {
        return this.adjustmentPiCode2Amount;
    }

    public ClaimsCob835Details adjustmentPiCode2Amount(Double adjustmentPiCode2Amount) {
        this.setAdjustmentPiCode2Amount(adjustmentPiCode2Amount);
        return this;
    }

    public void setAdjustmentPiCode2Amount(Double adjustmentPiCode2Amount) {
        this.adjustmentPiCode2Amount = adjustmentPiCode2Amount;
    }

    public String getAdjustmentPiCode3() {
        return this.adjustmentPiCode3;
    }

    public ClaimsCob835Details adjustmentPiCode3(String adjustmentPiCode3) {
        this.setAdjustmentPiCode3(adjustmentPiCode3);
        return this;
    }

    public void setAdjustmentPiCode3(String adjustmentPiCode3) {
        this.adjustmentPiCode3 = adjustmentPiCode3;
    }

    public Double getAdjustmentPiCode3Amount() {
        return this.adjustmentPiCode3Amount;
    }

    public ClaimsCob835Details adjustmentPiCode3Amount(Double adjustmentPiCode3Amount) {
        this.setAdjustmentPiCode3Amount(adjustmentPiCode3Amount);
        return this;
    }

    public void setAdjustmentPiCode3Amount(Double adjustmentPiCode3Amount) {
        this.adjustmentPiCode3Amount = adjustmentPiCode3Amount;
    }

    public String getAdjustmentPiCode4() {
        return this.adjustmentPiCode4;
    }

    public ClaimsCob835Details adjustmentPiCode4(String adjustmentPiCode4) {
        this.setAdjustmentPiCode4(adjustmentPiCode4);
        return this;
    }

    public void setAdjustmentPiCode4(String adjustmentPiCode4) {
        this.adjustmentPiCode4 = adjustmentPiCode4;
    }

    public Double getAdjustmentPiCode4Amount() {
        return this.adjustmentPiCode4Amount;
    }

    public ClaimsCob835Details adjustmentPiCode4Amount(Double adjustmentPiCode4Amount) {
        this.setAdjustmentPiCode4Amount(adjustmentPiCode4Amount);
        return this;
    }

    public void setAdjustmentPiCode4Amount(Double adjustmentPiCode4Amount) {
        this.adjustmentPiCode4Amount = adjustmentPiCode4Amount;
    }

    public Double getProviderPaymentAmount() {
        return this.providerPaymentAmount;
    }

    public ClaimsCob835Details providerPaymentAmount(Double providerPaymentAmount) {
        this.setProviderPaymentAmount(providerPaymentAmount);
        return this;
    }

    public void setProviderPaymentAmount(Double providerPaymentAmount) {
        this.providerPaymentAmount = providerPaymentAmount;
    }

    public Long getClaimCob835MasterId() {
        return this.claimCob835MasterId;
    }

    public ClaimsCob835Details claimCob835MasterId(Long claimCob835MasterId) {
        this.setClaimCob835MasterId(claimCob835MasterId);
        return this;
    }

    public void setClaimCob835MasterId(Long claimCob835MasterId) {
        this.claimCob835MasterId = claimCob835MasterId;
    }

    public String getStatus() {
        return this.status;
    }

    public ClaimsCob835Details status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getServiceDateTo() {
        return this.serviceDateTo;
    }

    public ClaimsCob835Details serviceDateTo(LocalDate serviceDateTo) {
        this.setServiceDateTo(serviceDateTo);
        return this;
    }

    public void setServiceDateTo(LocalDate serviceDateTo) {
        this.serviceDateTo = serviceDateTo;
    }

    public Long getUnitCount() {
        return this.unitCount;
    }

    public ClaimsCob835Details unitCount(Long unitCount) {
        this.setUnitCount(unitCount);
        return this;
    }

    public void setUnitCount(Long unitCount) {
        this.unitCount = unitCount;
    }

    public UUID getClaimsCob835DetailsUuid() {
        return this.claimsCob835DetailsUuid;
    }

    public ClaimsCob835Details claimsCob835DetailsUuid(UUID claimsCob835DetailsUuid) {
        this.setClaimsCob835DetailsUuid(claimsCob835DetailsUuid);
        return this;
    }

    public void setClaimsCob835DetailsUuid(UUID claimsCob835DetailsUuid) {
        this.claimsCob835DetailsUuid = claimsCob835DetailsUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClaimsCob835Details)) {
            return false;
        }
        return claimCob835DetailId != null && claimCob835DetailId.equals(((ClaimsCob835Details) o).claimCob835DetailId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClaimsCob835Details{" +
            "claimCob835DetailId=" + getClaimCob835DetailId() +
            ", serviceDate='" + getServiceDate() + "'" +
            ", adjudicatedProcedureCode='" + getAdjudicatedProcedureCode() + "'" +
            ", adjudicatedProcedureModifierCodes='" + getAdjudicatedProcedureModifierCodes() + "'" +
            ", chargeAmount=" + getChargeAmount() +
            ", allowedAmount=" + getAllowedAmount() +
            ", adjustmentPrCode1='" + getAdjustmentPrCode1() + "'" +
            ", adjustmentPrCode1Amount=" + getAdjustmentPrCode1Amount() +
            ", adjustmentPrCode2='" + getAdjustmentPrCode2() + "'" +
            ", adjustmentPrCode2Amount=" + getAdjustmentPrCode2Amount() +
            ", adjustmentPrCode3='" + getAdjustmentPrCode3() + "'" +
            ", adjustmentPrCode3Amount=" + getAdjustmentPrCode3Amount() +
            ", adjustmentPrCode4='" + getAdjustmentPrCode4() + "'" +
            ", adjustmentPrCode4Amount=" + getAdjustmentPrCode4Amount() +
            ", adjustmentCoCode1='" + getAdjustmentCoCode1() + "'" +
            ", adjustmentCoCode1Amount=" + getAdjustmentCoCode1Amount() +
            ", adjustmentCoCode2='" + getAdjustmentCoCode2() + "'" +
            ", adjustmentCoCode2Amount=" + getAdjustmentCoCode2Amount() +
            ", adjustmentCoCode3='" + getAdjustmentCoCode3() + "'" +
            ", adjustmentCoCode3Amount=" + getAdjustmentCoCode3Amount() +
            ", adjustmentCoCode4='" + getAdjustmentCoCode4() + "'" +
            ", adjustmentCoCode4Amount=" + getAdjustmentCoCode4Amount() +
            ", adjustmentCrCode1='" + getAdjustmentCrCode1() + "'" +
            ", adjustmentCrCode1Amount=" + getAdjustmentCrCode1Amount() +
            ", adjustmentCrCode2='" + getAdjustmentCrCode2() + "'" +
            ", adjustmentCrCode2Amount=" + getAdjustmentCrCode2Amount() +
            ", adjustmentCrCode3='" + getAdjustmentCrCode3() + "'" +
            ", adjustmentCrCode3Amount=" + getAdjustmentCrCode3Amount() +
            ", adjustmentCrCode4='" + getAdjustmentCrCode4() + "'" +
            ", adjustmentCrCode4Amount=" + getAdjustmentCrCode4Amount() +
            ", adjustmentOaCode1='" + getAdjustmentOaCode1() + "'" +
            ", adjustmentOaCode1Amount=" + getAdjustmentOaCode1Amount() +
            ", adjustmentOaCode2='" + getAdjustmentOaCode2() + "'" +
            ", adjustmentOaCode2Amount=" + getAdjustmentOaCode2Amount() +
            ", adjustmentOaCode3='" + getAdjustmentOaCode3() + "'" +
            ", adjustmentOaCode3Amount=" + getAdjustmentOaCode3Amount() +
            ", adjustmentOaCode4='" + getAdjustmentOaCode4() + "'" +
            ", adjustmentOaCode4Amount=" + getAdjustmentOaCode4Amount() +
            ", adjustmentPiCode1='" + getAdjustmentPiCode1() + "'" +
            ", adjustmentPiCode1Amount=" + getAdjustmentPiCode1Amount() +
            ", adjustmentPiCode2='" + getAdjustmentPiCode2() + "'" +
            ", adjustmentPiCode2Amount=" + getAdjustmentPiCode2Amount() +
            ", adjustmentPiCode3='" + getAdjustmentPiCode3() + "'" +
            ", adjustmentPiCode3Amount=" + getAdjustmentPiCode3Amount() +
            ", adjustmentPiCode4='" + getAdjustmentPiCode4() + "'" +
            ", adjustmentPiCode4Amount=" + getAdjustmentPiCode4Amount() +
            ", providerPaymentAmount=" + getProviderPaymentAmount() +
            ", claimCob835MasterId=" + getClaimCob835MasterId() +
            ", status='" + getStatus() + "'" +
            ", serviceDateTo='" + getServiceDateTo() + "'" +
            ", unitCount=" + getUnitCount() +
            ", claimsCob835DetailsUuid='" + getClaimsCob835DetailsUuid() + "'" +
            "}";
    }
}
