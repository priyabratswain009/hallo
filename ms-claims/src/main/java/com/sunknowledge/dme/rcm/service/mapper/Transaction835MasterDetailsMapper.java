package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.Transaction835MasterDetails;
import com.sunknowledge.dme.rcm.service.dto.Transaction835MasterDetailsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Transaction835MasterDetails} and its DTO {@link Transaction835MasterDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface Transaction835MasterDetailsMapper extends EntityMapper<Transaction835MasterDetailsDTO, Transaction835MasterDetails> {}
