package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.DeliveryPatientCommunications;
import com.sunknowledge.dme.rcm.service.dto.DeliveryPatientCommunicationsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DeliveryPatientCommunications} and its DTO {@link DeliveryPatientCommunicationsDTO}.
 */
@Mapper(componentModel = "spring")
public interface DeliveryPatientCommunicationsMapper
    extends EntityMapper<DeliveryPatientCommunicationsDTO, DeliveryPatientCommunications> {}
