package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ClaimsCOB835Details;
import com.sunknowledge.dme.rcm.service.dto.ClaimsCOB835DetailsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClaimsCOB835Details} and its DTO {@link ClaimsCOB835DetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface ClaimsCOB835DetailsMapper extends EntityMapper<ClaimsCOB835DetailsDTO, ClaimsCOB835Details> {}
