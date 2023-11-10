package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.SalesOrderItemDetails;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderItemDetailsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SalesOrderItemDetails} and its DTO {@link SalesOrderItemDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface SalesOrderItemDetailsMapper extends EntityMapper<SalesOrderItemDetailsDTO, SalesOrderItemDetails> {}
