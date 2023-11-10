package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.AbnDelivery;
import com.sunknowledge.dme.rcm.service.dto.AbnDeliveryDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AbnDelivery} and its DTO {@link AbnDeliveryDTO}.
 */
@Mapper(componentModel = "spring")
public interface AbnDeliveryMapper extends EntityMapper<AbnDeliveryDTO, AbnDelivery> {}
