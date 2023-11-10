package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ClaimSubmissionStatus;
import com.sunknowledge.dme.rcm.service.dto.ClaimSubmissionStatusDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClaimSubmissionStatus} and its DTO {@link ClaimSubmissionStatusDTO}.
 */
@Mapper(componentModel = "spring")
public interface ClaimSubmissionStatusMapper extends EntityMapper<ClaimSubmissionStatusDTO, ClaimSubmissionStatus> {}
