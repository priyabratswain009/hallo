package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.HcpcsDocType;
import com.sunknowledge.dme.rcm.service.dto.HcpcsDocTypeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link HcpcsDocType} and its DTO {@link HcpcsDocTypeDTO}.
 */
@Mapper(componentModel = "spring")
public interface HcpcsDocTypeMapper extends EntityMapper<HcpcsDocTypeDTO, HcpcsDocType> {}
