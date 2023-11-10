package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ClaimsCob835Details;
import com.sunknowledge.dme.rcm.service.dto.ClaimsCob835DetailsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClaimsCob835Details} and its DTO {@link ClaimsCob835DetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface ClaimsCob835DetailsMapper extends EntityMapper<ClaimsCob835DetailsDTO, ClaimsCob835Details> {}
