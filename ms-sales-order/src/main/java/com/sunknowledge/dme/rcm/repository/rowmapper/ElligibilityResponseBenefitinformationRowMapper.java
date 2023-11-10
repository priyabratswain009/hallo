package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.ElligibilityResponseBenefitinformation;
import io.r2dbc.spi.Row;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link ElligibilityResponseBenefitinformation}, with proper type conversions.
 */
@Service
public class ElligibilityResponseBenefitinformationRowMapper implements BiFunction<Row, String, ElligibilityResponseBenefitinformation> {

    private final ColumnConverter converter;

    public ElligibilityResponseBenefitinformationRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link ElligibilityResponseBenefitinformation} stored in the database.
     */
    @Override
    public ElligibilityResponseBenefitinformation apply(Row row, String prefix) {
        ElligibilityResponseBenefitinformation entity = new ElligibilityResponseBenefitinformation();
        entity.setElligibilityResponseBenefitinformationId(
            converter.fromRow(row, prefix + "_elligibility_response_benefitinformation_id", Long.class)
        );
        entity.setElligibilityResponseStatusId(converter.fromRow(row, prefix + "_elligibility_response_status_id", Long.class));
        entity.setBenefitsinformationCode(converter.fromRow(row, prefix + "_benefitsinformation_code", String.class));
        entity.setBenefitsinformationName(converter.fromRow(row, prefix + "_benefitsinformation_name", String.class));
        entity.setCoverageLevelCode(converter.fromRow(row, prefix + "_coverage_level_code", String.class));
        entity.setServiceTypeCodes(converter.fromRow(row, prefix + "_service_type_codes", String.class));
        entity.setInsuranceTypeCode(converter.fromRow(row, prefix + "_insurance_type_code", String.class));
        entity.setPlanCoverage(converter.fromRow(row, prefix + "_plan_coverage", String.class));
        entity.setBenefitsDateInformation(converter.fromRow(row, prefix + "_benefits_date_information", String.class));
        entity.setElligibilityResponseBenefitInformationUuid(
            converter.fromRow(row, prefix + "_elligibility_response_benefit_information_uuid", UUID.class)
        );
        return entity;
    }
}
