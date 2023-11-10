package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.SoRentalHelper;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link SoRentalHelper}, with proper type conversions.
 */
@Service
public class SoRentalHelperRowMapper implements BiFunction<Row, String, SoRentalHelper> {

    private final ColumnConverter converter;

    public SoRentalHelperRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link SoRentalHelper} stored in the database.
     */
    @Override
    public SoRentalHelper apply(Row row, String prefix) {
        SoRentalHelper entity = new SoRentalHelper();
        entity.setSoRentalHelperId(converter.fromRow(row, prefix + "_so_rental_helper_id", Long.class));
        entity.setSoId(converter.fromRow(row, prefix + "_so_id", Long.class));
        entity.setPrimaryInsurerId(converter.fromRow(row, prefix + "_primary_insurer_id", Long.class));
        entity.setPrimaryInsurerName(converter.fromRow(row, prefix + "_primary_insurer_name", String.class));
        entity.setItemId(converter.fromRow(row, prefix + "_item_id", Long.class));
        entity.setItemNo(converter.fromRow(row, prefix + "_item_no", String.class));
        entity.setItemName(converter.fromRow(row, prefix + "_item_name", String.class));
        entity.setChargedAmount(converter.fromRow(row, prefix + "_charged_amount", Double.class));
        entity.setAllowedAmount(converter.fromRow(row, prefix + "_allowed_amount", Double.class));
        entity.setSou(converter.fromRow(row, prefix + "_sou", String.class));
        entity.setQty(converter.fromRow(row, prefix + "_qty", Double.class));
        entity.setDosStart(converter.fromRow(row, prefix + "_dos_start", LocalDate.class));
        entity.setDosEnd(converter.fromRow(row, prefix + "_dos_end", LocalDate.class));
        entity.setPeriodNo(converter.fromRow(row, prefix + "_period_no", String.class));
        entity.setRentalPeriod(converter.fromRow(row, prefix + "_rental_period", String.class));
        entity.setNextDos(converter.fromRow(row, prefix + "_next_dos", LocalDate.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setSoRentalHelperUuid(converter.fromRow(row, prefix + "_so_rental_helper_uuid", UUID.class));
        entity.setPatientId(converter.fromRow(row, prefix + "_patient_id", Long.class));
        entity.setSaleType(converter.fromRow(row, prefix + "_sale_type", String.class));
        entity.setPrimaryInsurancePriceTableId(converter.fromRow(row, prefix + "_primary_insurance_price_table_id", Long.class));
        entity.setPrimaryInsurancePriceTableName(converter.fromRow(row, prefix + "_primary_insurance_price_table_name", String.class));
        entity.setModifier1(converter.fromRow(row, prefix + "_modifier_1", String.class));
        entity.setModifier2(converter.fromRow(row, prefix + "_modifier_2", String.class));
        entity.setModifier3(converter.fromRow(row, prefix + "_modifier_3", String.class));
        entity.setModifier4(converter.fromRow(row, prefix + "_modifier_4", String.class));
        entity.setIcdPointer(converter.fromRow(row, prefix + "_icd_pointer", String.class));
        entity.setProcedureIdentifier(converter.fromRow(row, prefix + "_procedure_identifier", String.class));
        entity.setOrderingProviderFirstName(converter.fromRow(row, prefix + "_ordering_provider_first_name", String.class));
        entity.setOrderingProviderLastName(converter.fromRow(row, prefix + "_ordering_provider_last_name", String.class));
        entity.setOrderingProviderNpi(converter.fromRow(row, prefix + "_ordering_provider_npi", String.class));
        entity.setReference(converter.fromRow(row, prefix + "_reference", String.class));
        entity.setProcCode(converter.fromRow(row, prefix + "_proc_code", String.class));
        return entity;
    }
}
