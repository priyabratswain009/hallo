package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.MemberElligibilityMaster;
import com.sunknowledge.dme.rcm.service.dto.MemberElligibilityMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link MemberElligibilityMaster} and its DTO {@link MemberElligibilityMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface MemberElligibilityMasterMapper extends EntityMapper<MemberElligibilityMasterDTO, MemberElligibilityMaster> {}
