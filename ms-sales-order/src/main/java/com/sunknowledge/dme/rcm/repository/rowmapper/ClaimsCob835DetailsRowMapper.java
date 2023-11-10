package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.ClaimsCob835Details;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link ClaimsCob835Details}, with proper type conversions.
 */
@Service
public class ClaimsCob835DetailsRowMapper implements BiFunction<Row, String, ClaimsCob835Details> {

    private final ColumnConverter converter;

    public ClaimsCob835DetailsRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link ClaimsCob835Details} stored in the database.
     */
    @Override
    public ClaimsCob835Details apply(Row row, String prefix) {
        ClaimsCob835Details entity = new ClaimsCob835Details();
        entity.setClaimCob835DetailId(converter.fromRow(row, prefix + "_claim_cob_835_detail_id", Long.class));
        entity.setServiceDate(converter.fromRow(row, prefix + "_service_date", LocalDate.class));
        entity.setAdjudicatedProcedureCode(converter.fromRow(row, prefix + "_adjudicated_procedure_code", String.class));
        entity.setAdjudicatedProcedureModifierCodes(converter.fromRow(row, prefix + "_adjudicated_procedure_modifier_codes", String.class));
        entity.setChargeAmount(converter.fromRow(row, prefix + "_charge_amount", Double.class));
        entity.setAllowedAmount(converter.fromRow(row, prefix + "_allowed_amount", Double.class));
        entity.setAdjustmentPrCode1(converter.fromRow(row, prefix + "_adjustment_pr_code_1", String.class));
        entity.setAdjustmentPrCode1Amount(converter.fromRow(row, prefix + "_adjustment_pr_code_1_amount", Double.class));
        entity.setAdjustmentPrCode2(converter.fromRow(row, prefix + "_adjustment_pr_code_2", String.class));
        entity.setAdjustmentPrCode2Amount(converter.fromRow(row, prefix + "_adjustment_pr_code_2_amount", Double.class));
        entity.setAdjustmentPrCode3(converter.fromRow(row, prefix + "_adjustment_pr_code_3", String.class));
        entity.setAdjustmentPrCode3Amount(converter.fromRow(row, prefix + "_adjustment_pr_code_3_amount", Double.class));
        entity.setAdjustmentPrCode4(converter.fromRow(row, prefix + "_adjustment_pr_code_4", String.class));
        entity.setAdjustmentPrCode4Amount(converter.fromRow(row, prefix + "_adjustment_pr_code_4_amount", Double.class));
        entity.setAdjustmentCoCode1(converter.fromRow(row, prefix + "_adjustment_co_code_1", String.class));
        entity.setAdjustmentCoCode1Amount(converter.fromRow(row, prefix + "_adjustment_co_code_1_amount", Double.class));
        entity.setAdjustmentCoCode2(converter.fromRow(row, prefix + "_adjustment_co_code_2", String.class));
        entity.setAdjustmentCoCode2Amount(converter.fromRow(row, prefix + "_adjustment_co_code_2_amount", Double.class));
        entity.setAdjustmentCoCode3(converter.fromRow(row, prefix + "_adjustment_co_code_3", String.class));
        entity.setAdjustmentCoCode3Amount(converter.fromRow(row, prefix + "_adjustment_co_code_3_amount", Double.class));
        entity.setAdjustmentCoCode4(converter.fromRow(row, prefix + "_adjustment_co_code_4", String.class));
        entity.setAdjustmentCoCode4Amount(converter.fromRow(row, prefix + "_adjustment_co_code_4_amount", Double.class));
        entity.setAdjustmentCrCode1(converter.fromRow(row, prefix + "_adjustment_cr_code_1", String.class));
        entity.setAdjustmentCrCode1Amount(converter.fromRow(row, prefix + "_adjustment_cr_code_1_amount", Double.class));
        entity.setAdjustmentCrCode2(converter.fromRow(row, prefix + "_adjustment_cr_code_2", String.class));
        entity.setAdjustmentCrCode2Amount(converter.fromRow(row, prefix + "_adjustment_cr_code_2_amount", Double.class));
        entity.setAdjustmentCrCode3(converter.fromRow(row, prefix + "_adjustment_cr_code_3", String.class));
        entity.setAdjustmentCrCode3Amount(converter.fromRow(row, prefix + "_adjustment_cr_code_3_amount", Double.class));
        entity.setAdjustmentCrCode4(converter.fromRow(row, prefix + "_adjustment_cr_code_4", String.class));
        entity.setAdjustmentCrCode4Amount(converter.fromRow(row, prefix + "_adjustment_cr_code_4_amount", Double.class));
        entity.setAdjustmentOaCode1(converter.fromRow(row, prefix + "_adjustment_oa_code_1", String.class));
        entity.setAdjustmentOaCode1Amount(converter.fromRow(row, prefix + "_adjustment_oa_code_1_amount", Double.class));
        entity.setAdjustmentOaCode2(converter.fromRow(row, prefix + "_adjustment_oa_code_2", String.class));
        entity.setAdjustmentOaCode2Amount(converter.fromRow(row, prefix + "_adjustment_oa_code_2_amount", Double.class));
        entity.setAdjustmentOaCode3(converter.fromRow(row, prefix + "_adjustment_oa_code_3", String.class));
        entity.setAdjustmentOaCode3Amount(converter.fromRow(row, prefix + "_adjustment_oa_code_3_amount", Double.class));
        entity.setAdjustmentOaCode4(converter.fromRow(row, prefix + "_adjustment_oa_code_4", String.class));
        entity.setAdjustmentOaCode4Amount(converter.fromRow(row, prefix + "_adjustment_oa_code_4_amount", Double.class));
        entity.setAdjustmentPiCode1(converter.fromRow(row, prefix + "_adjustment_pi_code_1", String.class));
        entity.setAdjustmentPiCode1Amount(converter.fromRow(row, prefix + "_adjustment_pi_code_1_amount", Double.class));
        entity.setAdjustmentPiCode2(converter.fromRow(row, prefix + "_adjustment_pi_code_2", String.class));
        entity.setAdjustmentPiCode2Amount(converter.fromRow(row, prefix + "_adjustment_pi_code_2_amount", Double.class));
        entity.setAdjustmentPiCode3(converter.fromRow(row, prefix + "_adjustment_pi_code_3", String.class));
        entity.setAdjustmentPiCode3Amount(converter.fromRow(row, prefix + "_adjustment_pi_code_3_amount", Double.class));
        entity.setAdjustmentPiCode4(converter.fromRow(row, prefix + "_adjustment_pi_code_4", String.class));
        entity.setAdjustmentPiCode4Amount(converter.fromRow(row, prefix + "_adjustment_pi_code_4_amount", Double.class));
        entity.setProviderPaymentAmount(converter.fromRow(row, prefix + "_provider_payment_amount", Double.class));
        entity.setClaimCob835MasterId(converter.fromRow(row, prefix + "_claim_cob_835_master_id", Long.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setServiceDateTo(converter.fromRow(row, prefix + "_service_date_to", LocalDate.class));
        entity.setUnitCount(converter.fromRow(row, prefix + "_unit_count", Long.class));
        entity.setClaimsCob835DetailsUuid(converter.fromRow(row, prefix + "_claims_cob_835_details_uuid", UUID.class));
        return entity;
    }
}
