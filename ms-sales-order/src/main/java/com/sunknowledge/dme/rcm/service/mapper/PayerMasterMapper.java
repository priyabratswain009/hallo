package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.PayerMaster;
import com.sunknowledge.dme.rcm.service.dto.PayerMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PayerMaster} and its DTO {@link PayerMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface PayerMasterMapper extends EntityMapper<PayerMasterDTO, PayerMaster> {}
