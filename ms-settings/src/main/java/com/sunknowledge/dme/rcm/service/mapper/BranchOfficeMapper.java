package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.BranchOffice;
import com.sunknowledge.dme.rcm.service.dto.BranchOfficeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BranchOffice} and its DTO {@link BranchOfficeDTO}.
 */
@Mapper(componentModel = "spring")
public interface BranchOfficeMapper extends EntityMapper<BranchOfficeDTO, BranchOffice> {}
