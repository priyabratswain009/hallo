package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.DeliveryTicket;
import com.sunknowledge.dme.rcm.service.dto.DeliveryTicketDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DeliveryTicket} and its DTO {@link DeliveryTicketDTO}.
 */
@Mapper(componentModel = "spring")
public interface DeliveryTicketMapper extends EntityMapper<DeliveryTicketDTO, DeliveryTicket> {}
