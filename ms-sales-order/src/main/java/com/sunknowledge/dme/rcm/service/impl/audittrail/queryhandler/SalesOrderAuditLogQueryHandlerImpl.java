package com.sunknowledge.dme.rcm.service.impl.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.repository.SalesOrderAuditLogRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.SalesOrderOverallAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.SalesOrderAuditLogBySONoAndUserIdOrDateQuery;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderMasterAuditLogMapper;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.SalesOrderAuditLogQueryHandler;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalesOrderAuditLogQueryHandlerImpl implements SalesOrderAuditLogQueryHandler {
    @Autowired
    SalesOrderAuditLogRepositoryExtended auditTrailRepository;

    private final SalesOrderMasterAuditLogMapper salesOrderMasterAuditLogMapper;

    public SalesOrderAuditLogQueryHandlerImpl(SalesOrderMasterAuditLogMapper salesOrderMasterAuditLogMapper) {
        this.salesOrderMasterAuditLogMapper = salesOrderMasterAuditLogMapper;
    }

    @Override
    public List<SalesOrderMasterAuditLogDTO> getSOAuditTrailInfoByQueryHandler(SalesOrderAuditLogBySONoAndUserIdOrDateQuery parameterObj) {
        try {

            return auditTrailRepository.findBySalsOdrId(parameterObj.getSoID())
                .map(salesOrderMasterAuditLogMapper::toDto).collectList().toFuture().get()
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
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());

        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<SalesOrderOverallAuditLogDTO> getSOOverallAuditTrailInfoByQueryHandler(SalesOrderAuditLogBySONoAndUserIdOrDateQuery parameterObj) {
        try {
            return auditTrailRepository.findAllSectionsBySalsOdrId(parameterObj.getSoID())
                .collectList().toFuture().get()
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
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());

        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
