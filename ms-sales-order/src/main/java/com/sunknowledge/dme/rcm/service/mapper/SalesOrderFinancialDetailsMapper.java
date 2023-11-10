package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.SalesOrderFinancialDetails;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderFinancialDetailsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SalesOrderFinancialDetails} and its DTO {@link SalesOrderFinancialDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface SalesOrderFinancialDetailsMapper extends EntityMapper<SalesOrderFinancialDetailsDTO, SalesOrderFinancialDetails> {}
