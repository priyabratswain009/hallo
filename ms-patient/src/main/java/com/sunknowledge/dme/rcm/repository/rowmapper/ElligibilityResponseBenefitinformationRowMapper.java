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
        entity.setCoveragelevelcode(converter.fromRow(row, prefix + "_coveragelevelcode", String.class));
        entity.setCoveragelevel(converter.fromRow(row, prefix + "_coveragelevel", String.class));
        entity.setServicetypecodes(converter.fromRow(row, prefix + "_servicetypecodes", String.class));
        entity.setServicetypes(converter.fromRow(row, prefix + "_servicetypes", String.class));
        entity.setInsurancetypecode(converter.fromRow(row, prefix + "_insurancetypecode", String.class));
        entity.setInsurancetype(converter.fromRow(row, prefix + "_insurancetype", String.class));
        entity.setPlancoverage(converter.fromRow(row, prefix + "_plancoverage", Long.class));
        entity.setBenefitsLocalDateinformation(converter.fromRow(row, prefix + "_benefits_local_dateinformation", String.class));
        entity.setElligibilityResponseBenefitinformationUuid(
            converter.fromRow(row, prefix + "_elligibility_response_benefitinformation_uuid", UUID.class)
        );
        return entity;
    }
}
