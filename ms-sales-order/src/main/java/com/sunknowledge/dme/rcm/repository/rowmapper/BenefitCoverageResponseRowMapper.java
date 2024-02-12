package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.BenefitCoverageResponse;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link BenefitCoverageResponse}, with proper type conversions.
 */
@Service
public class BenefitCoverageResponseRowMapper implements BiFunction<Row, String, BenefitCoverageResponse> {

    private final ColumnConverter converter;

    public BenefitCoverageResponseRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link BenefitCoverageResponse} stored in the database.
     */
    @Override
    public BenefitCoverageResponse apply(Row row, String prefix) {
        BenefitCoverageResponse entity = new BenefitCoverageResponse();
        entity.setBenefitCoverageResponseId(converter.fromRow(row, prefix + "_benefit_coverage_response_id", Long.class));
        entity.setBenefitCoverageRequestId(converter.fromRow(row, prefix + "_benefit_coverage_request_id", Long.class));
        entity.setRequestControlNumberExt(converter.fromRow(row, prefix + "_request_control_number_ext", String.class));
        entity.setAsOnDate(converter.fromRow(row, prefix + "_as_on_date", LocalDate.class));
        entity.setServiceType(converter.fromRow(row, prefix + "_service_type", String.class));
        entity.setMemberFirstName(converter.fromRow(row, prefix + "_member_first_name", String.class));
        entity.setMemberLastName(converter.fromRow(row, prefix + "_member_last_name", String.class));
        entity.setSubscriberMemberId(converter.fromRow(row, prefix + "_subscriber_member_id", String.class));
        entity.setMemberGender(converter.fromRow(row, prefix + "_member_gender", String.class));
        entity.setPatientFirstName(converter.fromRow(row, prefix + "_patient_first_name", String.class));
        entity.setPatientLastName(converter.fromRow(row, prefix + "_patient_last_name", String.class));
        entity.setPatientGender(converter.fromRow(row, prefix + "_patient_gender", String.class));
        entity.setPayerName(converter.fromRow(row, prefix + "_payer_name", String.class));
        entity.setPatientRelationshipCode(converter.fromRow(row, prefix + "_patient_relationship_code", String.class));
        entity.setPatientState(converter.fromRow(row, prefix + "_patient_state", String.class));
        entity.setCoverageStatus(converter.fromRow(row, prefix + "_coverage_status", String.class));
        entity.setPayerGroupNumber(converter.fromRow(row, prefix + "_payer_group_number", String.class));
        entity.setServiceDate(converter.fromRow(row, prefix + "_service_date", LocalDate.class));
        entity.setPlanStartDate(converter.fromRow(row, prefix + "_plan_start_date", LocalDate.class));
        entity.setResponseJsonText(converter.fromRow(row, prefix + "_response_json_text", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setBenefitCoverageResponseUuid(converter.fromRow(row, prefix + "_benefit_coverage_response_uuid", UUID.class));
        return entity;
    }
}
