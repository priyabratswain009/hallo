package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.SoRentalHelper;
import com.sunknowledge.dme.rcm.service.dto.SoRentalHelperDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SoRentalHelper} and its DTO {@link SoRentalHelperDTO}.
 */
@Mapper(componentModel = "spring")
public interface SoRentalHelperMapper extends EntityMapper<SoRentalHelperDTO, SoRentalHelper> {}
