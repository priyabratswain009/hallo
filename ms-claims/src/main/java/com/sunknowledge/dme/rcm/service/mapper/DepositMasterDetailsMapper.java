package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.DepositMasterDetails;
import com.sunknowledge.dme.rcm.service.dto.DepositMasterDetailsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DepositMasterDetails} and its DTO {@link DepositMasterDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface DepositMasterDetailsMapper extends EntityMapper<DepositMasterDetailsDTO, DepositMasterDetails> {}
