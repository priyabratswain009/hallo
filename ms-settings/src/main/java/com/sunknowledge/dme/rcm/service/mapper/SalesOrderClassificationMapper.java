package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.SalesOrderClassification;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderClassificationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SalesOrderClassification} and its DTO {@link SalesOrderClassificationDTO}.
 */
@Mapper(componentModel = "spring")
public interface SalesOrderClassificationMapper extends EntityMapper<SalesOrderClassificationDTO, SalesOrderClassification> {}
