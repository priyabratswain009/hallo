package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ElligibilityResponseBenefitinformation;
import com.sunknowledge.dme.rcm.service.dto.ElligibilityResponseBenefitinformationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ElligibilityResponseBenefitinformation} and its DTO {@link ElligibilityResponseBenefitinformationDTO}.
 */
@Mapper(componentModel = "spring")
public interface ElligibilityResponseBenefitinformationMapper
    extends EntityMapper<ElligibilityResponseBenefitinformationDTO, ElligibilityResponseBenefitinformation> {}
