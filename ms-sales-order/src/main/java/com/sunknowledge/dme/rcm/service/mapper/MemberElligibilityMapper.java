package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.MemberElligibility;
import com.sunknowledge.dme.rcm.service.dto.MemberElligibilityDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link MemberElligibility} and its DTO {@link MemberElligibilityDTO}.
 */
@Mapper(componentModel = "spring")
public interface MemberElligibilityMapper extends EntityMapper<MemberElligibilityDTO, MemberElligibility> {}
