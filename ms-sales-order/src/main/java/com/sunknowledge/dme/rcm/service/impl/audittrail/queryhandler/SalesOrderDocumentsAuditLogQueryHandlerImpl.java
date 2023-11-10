package com.sunknowledge.dme.rcm.service.impl.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.repository.SalesOrderClinicalAuditLogRepositoryExtended;
import com.sunknowledge.dme.rcm.repository.SalesOrderDocumentsAuditLogRepositoryExtended;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.SalesOrderDocumentsAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderClinicalDetailsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderDocumentsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.SalesOrderDocumentsAuditLogBySONoAndUserIdOrDateQuery;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderClinicalDetailsAuditLogMapper;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderDocumentsAuditLogMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalesOrderDocumentsAuditLogQueryHandlerImpl implements SalesOrderDocumentsAuditLogQueryHandler {

    @Autowired
    SalesOrderDocumentsAuditLogRepositoryExtended salesOrderDocumentsAuditLogRepositoryExtended;

    @Autowired
    SalesOrderDocumentsAuditLogMapper salesOrderDocumentsAuditLogMapper;

    @Override
    public List<SalesOrderDocumentsAuditLogDTO> getSODocumentsAuditTrailInfoByQueryHandler(SalesOrderDocumentsAuditLogBySONoAndUserIdOrDateQuery parameterObj) {
        try {
            return salesOrderDocumentsAuditLogRepositoryExtended.findBySalesOrderDocId(parameterObj.getSoDocumentsID())
                .map(salesOrderDocumentsAuditLogMapper::toDto).collectList().toFuture().get()
                .stream().filter(x -> {
                    try {
                        return ((((JSONObject) new JSONParser()
                            .parse(x.getNewRowData()))
                            .get("created_by_id") == null ? "" : ((JSONObject) new JSONParser()
                            .parse(x.getNewRowData()))
                            .get("created_by_id").toString()).equals(String.valueOf(parameterObj.getUserID())) ||
                            (((JSONObject) new JSONParser()
                                .parse(x.getNewRowData()))
                                .get("updated_by_id") == null ? "" : ((JSONObject) new JSONParser()
                                .parse(x.getNewRowData()))
                                .get("updated_by_id").toString()).equals(String.valueOf(parameterObj.getUserID())) ||
                            (x.getDmlTimestamp().compareTo(parameterObj.getFromDate()) >= 0 &&
                                x.getDmlTimestamp().compareTo(parameterObj.getToDate()) <= 0) &&
                                !x.getDmlType().equals("DELETE"));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
