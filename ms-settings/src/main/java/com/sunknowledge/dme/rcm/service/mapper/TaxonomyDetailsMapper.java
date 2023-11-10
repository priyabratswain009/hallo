package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.TaxonomyDetails;
import com.sunknowledge.dme.rcm.service.dto.TaxonomyDetailsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TaxonomyDetails} and its DTO {@link TaxonomyDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface TaxonomyDetailsMapper extends EntityMapper<TaxonomyDetailsDTO, TaxonomyDetails> {}
