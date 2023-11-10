package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.DepreciationMethod;
import com.sunknowledge.dme.rcm.service.dto.DepreciationMethodDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DepreciationMethod} and its DTO {@link DepreciationMethodDTO}.
 */
@Mapper(componentModel = "spring")
public interface DepreciationMethodMapper extends EntityMapper<DepreciationMethodDTO, DepreciationMethod> {}
