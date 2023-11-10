package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ClaimsStatus277Details;
import com.sunknowledge.dme.rcm.service.dto.ClaimsStatus277DetailsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClaimsStatus277Details} and its DTO {@link ClaimsStatus277DetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface ClaimsStatus277DetailsMapper extends EntityMapper<ClaimsStatus277DetailsDTO, ClaimsStatus277Details> {}
