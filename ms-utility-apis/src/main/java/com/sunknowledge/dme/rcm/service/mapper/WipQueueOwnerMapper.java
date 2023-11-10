package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.WipQueueOwner;
import com.sunknowledge.dme.rcm.service.dto.WipQueueOwnerDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link WipQueueOwner} and its DTO {@link WipQueueOwnerDTO}.
 */
@Mapper(componentModel = "spring")
public interface WipQueueOwnerMapper extends EntityMapper<WipQueueOwnerDTO, WipQueueOwner> {}
