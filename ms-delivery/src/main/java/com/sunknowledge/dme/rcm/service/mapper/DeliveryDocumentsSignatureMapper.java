package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.DeliveryDocumentsSignature;
import com.sunknowledge.dme.rcm.service.dto.DeliveryDocumentsSignatureDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DeliveryDocumentsSignature} and its DTO {@link DeliveryDocumentsSignatureDTO}.
 */
@Mapper(componentModel = "spring")
public interface DeliveryDocumentsSignatureMapper extends EntityMapper<DeliveryDocumentsSignatureDTO, DeliveryDocumentsSignature> {}
