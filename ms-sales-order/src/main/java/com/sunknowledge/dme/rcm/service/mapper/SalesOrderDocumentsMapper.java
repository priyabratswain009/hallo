package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.SalesOrderDocuments;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderDocumentsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SalesOrderDocuments} and its DTO {@link SalesOrderDocumentsDTO}.
 */
@Mapper(componentModel = "spring")
public interface SalesOrderDocumentsMapper extends EntityMapper<SalesOrderDocumentsDTO, SalesOrderDocuments> {}
