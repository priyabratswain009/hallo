package com.sunknowledge.dme.rcm.service.impl.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.repository.SalesOrderAuditLogRepositoryExtended;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.UserActivityReportQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.audittrail.SalesOrderOverallAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.UserActivityReportDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.SalesOrderAuditLogBySONoAndUserIdOrDateQuery;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.UserActivityReportByDateQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserActivityReportQueryHandlerImpl implements UserActivityReportQueryHandler {

    @Autowired
    SalesOrderAuditLogRepositoryExtended salesOrderAuditLogRepositoryExtended;

    @Override
    public List<UserActivityReportDTO> getSOUserActivityReportByQueryHandler(UserActivityReportByDateQuery userActivityReportByDateQuery) {
        try {
            return salesOrderAuditLogRepositoryExtended.findUserActivityDataByDmlTimestamp(
                userActivityReportByDateQuery.getFromDate(),
                userActivityReportByDateQuery.getToDate()).collectList().toFuture().get();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
