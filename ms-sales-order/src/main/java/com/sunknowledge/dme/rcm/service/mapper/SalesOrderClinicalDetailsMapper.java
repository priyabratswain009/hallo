package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.SalesOrderClinicalDetails;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderClinicalDetailsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SalesOrderClinicalDetails} and its DTO {@link SalesOrderClinicalDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface SalesOrderClinicalDetailsMapper extends EntityMapper<SalesOrderClinicalDetailsDTO, SalesOrderClinicalDetails> {}
