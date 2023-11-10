package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ClaimsReportFileProcessStatus;
import com.sunknowledge.dme.rcm.service.dto.ClaimsReportFileProcessStatusDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClaimsReportFileProcessStatus} and its DTO {@link ClaimsReportFileProcessStatusDTO}.
 */
@Mapper(componentModel = "spring")
public interface ClaimsReportFileProcessStatusMapper
    extends EntityMapper<ClaimsReportFileProcessStatusDTO, ClaimsReportFileProcessStatus> {}
