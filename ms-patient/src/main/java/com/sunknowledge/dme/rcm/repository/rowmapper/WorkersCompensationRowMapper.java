package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.WorkersCompensation;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link WorkersCompensation}, with proper type conversions.
 */
@Service
public class WorkersCompensationRowMapper implements BiFunction<Row, String, WorkersCompensation> {

    private final ColumnConverter converter;

    public WorkersCompensationRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link WorkersCompensation} stored in the database.
     */
    @Override
    public WorkersCompensation apply(Row row, String prefix) {
        WorkersCompensation entity = new WorkersCompensation();
        entity.setWorkersCompensationId(converter.fromRow(row, prefix + "_workers_compensation_id", Long.class));
        entity.setPatientId(converter.fromRow(row, prefix + "_patient_id", Long.class));
        entity.setInsuredEmployer(converter.fromRow(row, prefix + "_insured_employer", String.class));
        entity.setWorkersCompensationPayerIdNumber(converter.fromRow(row, prefix + "_workers_compensation_payer_id_number", String.class));
        entity.setWorkersCompensationPlanName(converter.fromRow(row, prefix + "_workers_compensation_plan_name", String.class));
        entity.setWorkersCompensationAdditionalDtls(converter.fromRow(row, prefix + "_workers_compensation_additional_dtls", String.class));
        entity.setWorkersCompensationClaimFillingCode(
            converter.fromRow(row, prefix + "_workers_compensation_claim_filling_code", String.class)
        );
        entity.setWorkersCompensationTplCode(converter.fromRow(row, prefix + "_workers_compensation_tpl_code", String.class));
        entity.setWorkersCompensationTplName(converter.fromRow(row, prefix + "_workers_compensation_tpl_name", String.class));
        entity.setWcPropertyCasualtyAgencyClaimNo(converter.fromRow(row, prefix + "_wc_property_casualty_agency_claim_no", String.class));
        entity.setWcCarrierId(converter.fromRow(row, prefix + "_wc_carrier_id", Long.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setWorkersCompensationUuid(converter.fromRow(row, prefix + "_workers_compensation_uuid", UUID.class));
        entity.setEmployerAddressLine1(converter.fromRow(row, prefix + "_employer_address_line_1", String.class));
        entity.setEmployerAddressLine2(converter.fromRow(row, prefix + "_employer_address_line_2", String.class));
        entity.setEmployerCity(converter.fromRow(row, prefix + "_employer_city", String.class));
        entity.setEmployerState(converter.fromRow(row, prefix + "_employer_state", String.class));
        entity.setEmployerCountry(converter.fromRow(row, prefix + "_employer_country", String.class));
        entity.setEmployerZip(converter.fromRow(row, prefix + "_employer_zip", String.class));
        entity.setEmployerContactNo1(converter.fromRow(row, prefix + "_employer_contact_no_1", String.class));
        entity.setEmployerContactNo2(converter.fromRow(row, prefix + "_employer_contact_no_2", String.class));
        entity.setEmployerFax(converter.fromRow(row, prefix + "_employer_fax", String.class));
        entity.setEmployerEfax(converter.fromRow(row, prefix + "_employer_efax", String.class));
        entity.setEmployerEmail(converter.fromRow(row, prefix + "_employer_email", String.class));
        entity.setRelationship(converter.fromRow(row, prefix + "_relationship", String.class));
        entity.setModeOfContact(converter.fromRow(row, prefix + "_mode_of_contact", String.class));
        return entity;
    }
}
