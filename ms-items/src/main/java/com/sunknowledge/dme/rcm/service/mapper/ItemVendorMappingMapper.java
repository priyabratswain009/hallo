package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ItemVendorMapping;
import com.sunknowledge.dme.rcm.service.dto.ItemVendorMappingDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ItemVendorMapping} and its DTO {@link ItemVendorMappingDTO}.
 */
@Mapper(componentModel = "spring")
public interface ItemVendorMappingMapper extends EntityMapper<ItemVendorMappingDTO, ItemVendorMapping> {}
