package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.DeliveryAssignment;
import com.sunknowledge.dme.rcm.service.dto.DeliveryAssignmentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DeliveryAssignment} and its DTO {@link DeliveryAssignmentDTO}.
 */
@Mapper(componentModel = "spring")
public interface DeliveryAssignmentMapper extends EntityMapper<DeliveryAssignmentDTO, DeliveryAssignment> {}
