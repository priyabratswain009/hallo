package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.PrimaryClaimResubmisionServicelines;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link PrimaryClaimResubmisionServicelines}, with proper type conversions.
 */
@Service
public class PrimaryClaimResubmisionServicelinesRowMapper implements BiFunction<Row, String, PrimaryClaimResubmisionServicelines> {

    private final ColumnConverter converter;

    public PrimaryClaimResubmisionServicelinesRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link PrimaryClaimResubmisionServicelines} stored in the database.
     */
    @Override
    public PrimaryClaimResubmisionServicelines apply(Row row, String prefix) {
        PrimaryClaimResubmisionServicelines entity = new PrimaryClaimResubmisionServicelines();
        entity.setChangeHealthPrimaryResubmisionServicelinesId(
            converter.fromRow(row, prefix + "_change_health_primary_resubmision_servicelines_id", Long.class)
        );
        entity.setChangeHealthPrimaryResubmisionMasterId(
            converter.fromRow(row, prefix + "_change_health_primary_resubmision_master_id", Long.class)
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
        entity.setInsertedById(converter.fromRow(row, prefix + "_inserted_by_id", Long.class));
        entity.setNsertedDate(converter.fromRow(row, prefix + "_nserted_date", LocalDate.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setClaimServicelineControlNo(converter.fromRow(row, prefix + "_claim_serviceline_control_no", String.class));
        entity.setProcedureIdentifier(converter.fromRow(row, prefix + "_procedure_identifier", String.class));
        entity.setInsertedByName(converter.fromRow(row, prefix + "_inserted_by_name", String.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setOrderingProviderFirstName(converter.fromRow(row, prefix + "_ordering_provider_first_name", String.class));
        entity.setOrderingProviderLastName(converter.fromRow(row, prefix + "_ordering_provider_last_name", String.class));
        entity.setOrderingProviderNpi(converter.fromRow(row, prefix + "_ordering_provider_npi", String.class));
        entity.setReference(converter.fromRow(row, prefix + "_reference", String.class));
        entity.setPrimaryClaimResubmisionServicelinesUuid(
            converter.fromRow(row, prefix + "_primary_claim_resubmision_servicelines_uuid", UUID.class)
        );
        return entity;
    }
}
