package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.SalesOrderInsuranceDetails;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderInsuranceDetailsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SalesOrderInsuranceDetails} and its DTO {@link SalesOrderInsuranceDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface SalesOrderInsuranceDetailsMapper extends EntityMapper<SalesOrderInsuranceDetailsDTO, SalesOrderInsuranceDetails> {}
