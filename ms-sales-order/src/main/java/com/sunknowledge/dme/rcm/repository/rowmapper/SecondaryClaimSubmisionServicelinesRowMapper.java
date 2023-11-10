package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.SecondaryClaimSubmisionServicelines;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link SecondaryClaimSubmisionServicelines}, with proper type conversions.
 */
@Service
public class SecondaryClaimSubmisionServicelinesRowMapper implements BiFunction<Row, String, SecondaryClaimSubmisionServicelines> {

    private final ColumnConverter converter;

    public SecondaryClaimSubmisionServicelinesRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link SecondaryClaimSubmisionServicelines} stored in the database.
     */
    @Override
    public SecondaryClaimSubmisionServicelines apply(Row row, String prefix) {
        SecondaryClaimSubmisionServicelines entity = new SecondaryClaimSubmisionServicelines();
        entity.setChangeHealthSecondarySubmisionServicelinesId(
            converter.fromRow(row, prefix + "_change_health_secondary_submision_servicelines_id", Long.class)
        );
        entity.setChangeHealthSecondarySubmisionMasterId(
            converter.fromRow(row, prefix + "_change_health_secondary_submision_master_id", Long.class)
        );
        entity.setOriginalDos(converter.fromRow(row, prefix + "_original_dos", LocalDate.class));
        entity.setDosTo(converter.fromRow(row, prefix + "_dos_to", LocalDate.class));
        entity.setProcCode(converter.fromRow(row, prefix + "_proc_code", String.class));
        entity.setChargeAmt(converter.fromRow(row, prefix + "_charge_amt", Double.class));
        entity.setItemUom(converter.fromRow(row, prefix + "_item_uom", String.class));
        entity.setModifier1(converter.fromRow(row, prefix + "_modifier_1", String.class));
        entity.setModifier2(converter.fromRow(row, prefix + "_modifier_2", String.class));
        entity.setModifier3(converter.fromRow(row, prefix + "_modifier_3", String.class));
        entity.setModifier4(converter.fromRow(row, prefix + "_modifier_4", String.class));
        entity.setIcdPointer(converter.fromRow(row, prefix + "_icd_pointer", String.class));
        entity.setQty(converter.fromRow(row, prefix + "_qty", Long.class));
        entity.setInsertedById(converter.fromRow(row, prefix + "_inserted_by_id", String.class));
        entity.setInsertedDate(converter.fromRow(row, prefix + "_inserted_date", LocalDate.class));
        entity.setClaimServicelineControlNo(converter.fromRow(row, prefix + "_claim_serviceline_control_no", String.class));
        entity.setProcedureIdentifier(converter.fromRow(row, prefix + "_procedure_identifier", String.class));
        entity.setInsertedByName(converter.fromRow(row, prefix + "_inserted_by_name", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setOrderingProviderFirstName(converter.fromRow(row, prefix + "_ordering_provider_first_name", String.class));
        entity.setOrderingProviderLastName(converter.fromRow(row, prefix + "_ordering_provider_last_name", String.class));
        entity.setOrderingProviderNpi(converter.fromRow(row, prefix + "_ordering_provider_npi", String.class));
        entity.setReference(converter.fromRow(row, prefix + "_reference", String.class));
        entity.setPayorClaimControlNo(converter.fromRow(row, prefix + "_payor_claim_control_no", String.class));
        entity.setProviderPaymentAmount(converter.fromRow(row, prefix + "_provider_payment_amount", Double.class));
        entity.setLineAdjustment(converter.fromRow(row, prefix + "_line_adjustment", String.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", String.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setSecondaryClaimSubmisionServicelinesUuid(
            converter.fromRow(row, prefix + "_secondary_claim_submision_servicelines_uuid", UUID.class)
        );
        return entity;
    }
}
