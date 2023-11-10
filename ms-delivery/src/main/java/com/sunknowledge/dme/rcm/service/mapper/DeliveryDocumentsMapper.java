package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.DeliveryDocuments;
import com.sunknowledge.dme.rcm.service.dto.DeliveryDocumentsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DeliveryDocuments} and its DTO {@link DeliveryDocumentsDTO}.
 */
@Mapper(componentModel = "spring")
public interface DeliveryDocumentsMapper extends EntityMapper<DeliveryDocumentsDTO, DeliveryDocuments> {}
