package com.sunknowledge.dme.rcm.service.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.service.dto.audittrail.UserActivityReportDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.UserActivityReportByDateQuery;

import java.util.List;

public interface UserActivityReportQueryHandler {
    List<UserActivityReportDTO> getSOUserActivityReportByQueryHandler(UserActivityReportByDateQuery userActivityReportByDateQuery);
}
