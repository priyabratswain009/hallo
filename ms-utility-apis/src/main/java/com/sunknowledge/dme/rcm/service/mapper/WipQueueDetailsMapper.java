package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.WipQueueDetails;
import com.sunknowledge.dme.rcm.service.dto.WipQueueDetailsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link WipQueueDetails} and its DTO {@link WipQueueDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface WipQueueDetailsMapper extends EntityMapper<WipQueueDetailsDTO, WipQueueDetails> {}
