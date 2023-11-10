package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.DeliveryAbnData;
import com.sunknowledge.dme.rcm.service.dto.DeliveryAbnDataDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DeliveryAbnData} and its DTO {@link DeliveryAbnDataDTO}.
 */
@Mapper(componentModel = "spring")
public interface DeliveryAbnDataMapper extends EntityMapper<DeliveryAbnDataDTO, DeliveryAbnData> {}
