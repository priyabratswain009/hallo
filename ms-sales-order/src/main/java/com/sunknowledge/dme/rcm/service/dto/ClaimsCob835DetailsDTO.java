package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.ClaimsCob835Details} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ClaimsCob835DetailsDTO implements Serializable {

    private Long claimCob835DetailId;

    private LocalDate serviceDate;

    private String adjudicatedProcedureCode;

    private String adjudicatedProcedureModifierCodes;

    private Double chargeAmount;

    private Double allowedAmount;

    private String adjustmentPrCode1;

    private Double adjustmentPrCode1Amount;

    private String adjustmentPrCode2;

    private Double adjustmentPrCode2Amount;

    private String adjustmentPrCode3;

    private Double adjustmentPrCode3Amount;

    private String adjustmentPrCode4;

    private Double adjustmentPrCode4Amount;

    private String adjustmentCoCode1;

    private Double adjustmentCoCode1Amount;

    private String adjustmentCoCode2;

    private Double adjustmentCoCode2Amount;

    private String adjustmentCoCode3;

    private Double adjustmentCoCode3Amount;

    private String adjustmentCoCode4;

    private Double adjustmentCoCode4Amount;

    private String adjustmentCrCode1;

    private Double adjustmentCrCode1Amount;

    private String adjustmentCrCode2;

    private Double adjustmentCrCode2Amount;

    private String adjustmentCrCode3;

    private Double adjustmentCrCode3Amount;

    private String adjustmentCrCode4;

    private Double adjustmentCrCode4Amount;

    private String adjustmentOaCode1;

    private Double adjustmentOaCode1Amount;

    private String adjustmentOaCode2;

    private Double adjustmentOaCode2Amount;

    private String adjustmentOaCode3;

    private Double adjustmentOaCode3Amount;

    private String adjustmentOaCode4;

    private Double adjustmentOaCode4Amount;

    private String adjustmentPiCode1;

    private Double adjustmentPiCode1Amount;

    private String adjustmentPiCode2;

    private Double adjustmentPiCode2Amount;

    private String adjustmentPiCode3;

    private Double adjustmentPiCode3Amount;

    private String adjustmentPiCode4;

    private Double adjustmentPiCode4Amount;

    private Double providerPaymentAmount;

    private Long claimCob835MasterId;

    private String status;

    private LocalDate serviceDateTo;

    private Long unitCount;

    private UUID claimsCob835DetailsUuid;

    public Long getClaimCob835DetailId() {
        return claimCob835DetailId;
    }

    public void setClaimCob835DetailId(Long claimCob835DetailId) {
        this.claimCob835DetailId = claimCob835DetailId;
    }

    public LocalDate getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(LocalDate serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getAdjudicatedProcedureCode() {
        return adjudicatedProcedureCode;
    }

    public void setAdjudicatedProcedureCode(String adjudicatedProcedureCode) {
        this.adjudicatedProcedureCode = adjudicatedProcedureCode;
    }

    public String getAdjudicatedProcedureModifierCodes() {
        return adjudicatedProcedureModifierCodes;
    }

    public void setAdjudicatedProcedureModifierCodes(String adjudicatedProcedureModifierCodes) {
        this.adjudicatedProcedureModifierCodes = adjudicatedProcedureModifierCodes;
    }

    public Double getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(Double chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public Double getAllowedAmount() {
        return allowedAmount;
    }

    public void setAllowedAmount(Double allowedAmount) {
        this.allowedAmount = allowedAmount;
    }

    public String getAdjustmentPrCode1() {
        return adjustmentPrCode1;
    }

    public void setAdjustmentPrCode1(String adjustmentPrCode1) {
        this.adjustmentPrCode1 = adjustmentPrCode1;
    }

    public Double getAdjustmentPrCode1Amount() {
        return adjustmentPrCode1Amount;
    }

    public void setAdjustmentPrCode1Amount(Double adjustmentPrCode1Amount) {
        this.adjustmentPrCode1Amount = adjustmentPrCode1Amount;
    }

    public String getAdjustmentPrCode2() {
        return adjustmentPrCode2;
    }

    public void setAdjustmentPrCode2(String adjustmentPrCode2) {
        this.adjustmentPrCode2 = adjustmentPrCode2;
    }

    public Double getAdjustmentPrCode2Amount() {
        return adjustmentPrCode2Amount;
    }

    public void setAdjustmentPrCode2Amount(Double adjustmentPrCode2Amount) {
        this.adjustmentPrCode2Amount = adjustmentPrCode2Amount;
    }

    public String getAdjustmentPrCode3() {
        return adjustmentPrCode3;
    }

    public void setAdjustmentPrCode3(String adjustmentPrCode3) {
        this.adjustmentPrCode3 = adjustmentPrCode3;
    }

    public Double getAdjustmentPrCode3Amount() {
        return adjustmentPrCode3Amount;
    }

    public void setAdjustmentPrCode3Amount(Double adjustmentPrCode3Amount) {
        this.adjustmentPrCode3Amount = adjustmentPrCode3Amount;
    }

    public String getAdjustmentPrCode4() {
        return adjustmentPrCode4;
    }

    public void setAdjustmentPrCode4(String adjustmentPrCode4) {
        this.adjustmentPrCode4 = adjustmentPrCode4;
    }

    public Double getAdjustmentPrCode4Amount() {
        return adjustmentPrCode4Amount;
    }

    public void setAdjustmentPrCode4Amount(Double adjustmentPrCode4Amount) {
        this.adjustmentPrCode4Amount = adjustmentPrCode4Amount;
    }

    public String getAdjustmentCoCode1() {
        return adjustmentCoCode1;
    }

    public void setAdjustmentCoCode1(String adjustmentCoCode1) {
        this.adjustmentCoCode1 = adjustmentCoCode1;
    }

    public Double getAdjustmentCoCode1Amount() {
        return adjustmentCoCode1Amount;
    }

    public void setAdjustmentCoCode1Amount(Double adjustmentCoCode1Amount) {
        this.adjustmentCoCode1Amount = adjustmentCoCode1Amount;
    }

    public String getAdjustmentCoCode2() {
        return adjustmentCoCode2;
    }

    public void setAdjustmentCoCode2(String adjustmentCoCode2) {
        this.adjustmentCoCode2 = adjustmentCoCode2;
    }

    public Double getAdjustmentCoCode2Amount() {
        return adjustmentCoCode2Amount;
    }

    public void setAdjustmentCoCode2Amount(Double adjustmentCoCode2Amount) {
        this.adjustmentCoCode2Amount = adjustmentCoCode2Amount;
    }

    public String getAdjustmentCoCode3() {
        return adjustmentCoCode3;
    }

    public void setAdjustmentCoCode3(String adjustmentCoCode3) {
        this.adjustmentCoCode3 = adjustmentCoCode3;
    }

    public Double getAdjustmentCoCode3Amount() {
        return adjustmentCoCode3Amount;
    }

    public void setAdjustmentCoCode3Amount(Double adjustmentCoCode3Amount) {
        this.adjustmentCoCode3Amount = adjustmentCoCode3Amount;
    }

    public String getAdjustmentCoCode4() {
        return adjustmentCoCode4;
    }

    public void setAdjustmentCoCode4(String adjustmentCoCode4) {
        this.adjustmentCoCode4 = adjustmentCoCode4;
    }

    public Double getAdjustmentCoCode4Amount() {
        return adjustmentCoCode4Amount;
    }

    public void setAdjustmentCoCode4Amount(Double adjustmentCoCode4Amount) {
        this.adjustmentCoCode4Amount = adjustmentCoCode4Amount;
    }

    public String getAdjustmentCrCode1() {
        return adjustmentCrCode1;
    }

    public void setAdjustmentCrCode1(String adjustmentCrCode1) {
        this.adjustmentCrCode1 = adjustmentCrCode1;
    }

    public Double getAdjustmentCrCode1Amount() {
        return adjustmentCrCode1Amount;
    }

    public void setAdjustmentCrCode1Amount(Double adjustmentCrCode1Amount) {
        this.adjustmentCrCode1Amount = adjustmentCrCode1Amount;
    }

    public String getAdjustmentCrCode2() {
        return adjustmentCrCode2;
    }

    public void setAdjustmentCrCode2(String adjustmentCrCode2) {
        this.adjustmentCrCode2 = adjustmentCrCode2;
    }

    public Double getAdjustmentCrCode2Amount() {
        return adjustmentCrCode2Amount;
    }

    public void setAdjustmentCrCode2Amount(Double adjustmentCrCode2Amount) {
        this.adjustmentCrCode2Amount = adjustmentCrCode2Amount;
    }

    public String getAdjustmentCrCode3() {
        return adjustmentCrCode3;
    }

    public void setAdjustmentCrCode3(String adjustmentCrCode3) {
        this.adjustmentCrCode3 = adjustmentCrCode3;
    }

    public Double getAdjustmentCrCode3Amount() {
        return adjustmentCrCode3Amount;
    }

    public void setAdjustmentCrCode3Amount(Double adjustmentCrCode3Amount) {
        this.adjustmentCrCode3Amount = adjustmentCrCode3Amount;
    }

    public String getAdjustmentCrCode4() {
        return adjustmentCrCode4;
    }

    public void setAdjustmentCrCode4(String adjustmentCrCode4) {
        this.adjustmentCrCode4 = adjustmentCrCode4;
    }

    public Double getAdjustmentCrCode4Amount() {
        return adjustmentCrCode4Amount;
    }

    public void setAdjustmentCrCode4Amount(Double adjustmentCrCode4Amount) {
        this.adjustmentCrCode4Amount = adjustmentCrCode4Amount;
    }

    public String getAdjustmentOaCode1() {
        return adjustmentOaCode1;
    }

    public void setAdjustmentOaCode1(String adjustmentOaCode1) {
        this.adjustmentOaCode1 = adjustmentOaCode1;
    }

    public Double getAdjustmentOaCode1Amount() {
        return adjustmentOaCode1Amount;
    }

    public void setAdjustmentOaCode1Amount(Double adjustmentOaCode1Amount) {
        this.adjustmentOaCode1Amount = adjustmentOaCode1Amount;
    }

    public String getAdjustmentOaCode2() {
        return adjustmentOaCode2;
    }

    public void setAdjustmentOaCode2(String adjustmentOaCode2) {
        this.adjustmentOaCode2 = adjustmentOaCode2;
    }

    public Double getAdjustmentOaCode2Amount() {
        return adjustmentOaCode2Amount;
    }

    public void setAdjustmentOaCode2Amount(Double adjustmentOaCode2Amount) {
        this.adjustmentOaCode2Amount = adjustmentOaCode2Amount;
    }

    public String getAdjustmentOaCode3() {
        return adjustmentOaCode3;
    }

    public void setAdjustmentOaCode3(String adjustmentOaCode3) {
        this.adjustmentOaCode3 = adjustmentOaCode3;
    }

    public Double getAdjustmentOaCode3Amount() {
        return adjustmentOaCode3Amount;
    }

    public void setAdjustmentOaCode3Amount(Double adjustmentOaCode3Amount) {
        this.adjustmentOaCode3Amount = adjustmentOaCode3Amount;
    }

    public String getAdjustmentOaCode4() {
        return adjustmentOaCode4;
    }

    public void setAdjustmentOaCode4(String adjustmentOaCode4) {
        this.adjustmentOaCode4 = adjustmentOaCode4;
    }

    public Double getAdjustmentOaCode4Amount() {
        return adjustmentOaCode4Amount;
    }

    public void setAdjustmentOaCode4Amount(Double adjustmentOaCode4Amount) {
        this.adjustmentOaCode4Amount = adjustmentOaCode4Amount;
    }

    public String getAdjustmentPiCode1() {
        return adjustmentPiCode1;
    }

    public void setAdjustmentPiCode1(String adjustmentPiCode1) {
        this.adjustmentPiCode1 = adjustmentPiCode1;
    }

    public Double getAdjustmentPiCode1Amount() {
        return adjustmentPiCode1Amount;
    }

    public void setAdjustmentPiCode1Amount(Double adjustmentPiCode1Amount) {
        this.adjustmentPiCode1Amount = adjustmentPiCode1Amount;
    }

    public String getAdjustmentPiCode2() {
        return adjustmentPiCode2;
    }

    public void setAdjustmentPiCode2(String adjustmentPiCode2) {
        this.adjustmentPiCode2 = adjustmentPiCode2;
    }

    public Double getAdjustmentPiCode2Amount() {
        return adjustmentPiCode2Amount;
    }

    public void setAdjustmentPiCode2Amount(Double adjustmentPiCode2Amount) {
        this.adjustmentPiCode2Amount = adjustmentPiCode2Amount;
    }

    public String getAdjustmentPiCode3() {
        return adjustmentPiCode3;
    }

    public void setAdjustmentPiCode3(String adjustmentPiCode3) {
        this.adjustmentPiCode3 = adjustmentPiCode3;
    }

    public Double getAdjustmentPiCode3Amount() {
        return adjustmentPiCode3Amount;
    }

    public void setAdjustmentPiCode3Amount(Double adjustmentPiCode3Amount) {
        this.adjustmentPiCode3Amount = adjustmentPiCode3Amount;
    }

    public String getAdjustmentPiCode4() {
        return adjustmentPiCode4;
    }

    public void setAdjustmentPiCode4(String adjustmentPiCode4) {
        this.adjustmentPiCode4 = adjustmentPiCode4;
    }

    public Double getAdjustmentPiCode4Amount() {
        return adjustmentPiCode4Amount;
    }

    public void setAdjustmentPiCode4Amount(Double adjustmentPiCode4Amount) {
        this.adjustmentPiCode4Amount = adjustmentPiCode4Amount;
    }

    public Double getProviderPaymentAmount() {
        return providerPaymentAmount;
    }

    public void setProviderPaymentAmount(Double providerPaymentAmount) {
        this.providerPaymentAmount = providerPaymentAmount;
    }

    public Long getClaimCob835MasterId() {
        return claimCob835MasterId;
    }

    public void setClaimCob835MasterId(Long claimCob835MasterId) {
        this.claimCob835MasterId = claimCob835MasterId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getServiceDateTo() {
        return serviceDateTo;
    }

    public void setServiceDateTo(LocalDate serviceDateTo) {
        this.serviceDateTo = serviceDateTo;
    }

    public Long getUnitCount() {
        return unitCount;
    }

    public void setUnitCount(Long unitCount) {
        this.unitCount = unitCount;
    }

    public UUID getClaimsCob835DetailsUuid() {
        return claimsCob835DetailsUuid;
    }

    public void setClaimsCob835DetailsUuid(UUID claimsCob835DetailsUuid) {
        this.claimsCob835DetailsUuid = claimsCob835DetailsUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClaimsCob835DetailsDTO)) {
            return false;
        }

        ClaimsCob835DetailsDTO claimsCob835DetailsDTO = (ClaimsCob835DetailsDTO) o;
        if (this.claimCob835DetailId == null) {
            return false;
        }
        return Objects.equals(this.claimCob835DetailId, claimsCob835DetailsDTO.claimCob835DetailId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.claimCob835DetailId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClaimsCob835DetailsDTO{" +
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
