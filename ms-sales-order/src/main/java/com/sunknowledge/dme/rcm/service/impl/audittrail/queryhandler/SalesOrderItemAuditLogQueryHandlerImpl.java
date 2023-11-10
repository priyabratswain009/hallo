package com.sunknowledge.dme.rcm.service.impl.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.repository.SalesOrderItemAuditLogRepositoryExtended;
import com.sunknowledge.dme.rcm.repository.SalesOrderItemDetailsAuditLogRepository;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.SalesOrderItemAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderItemDetailsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.SalesOrderItemAuditLogBySONoAndUserIdOrDateQuery;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderItemDetailsAuditLogMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalesOrderItemAuditLogQueryHandlerImpl implements SalesOrderItemAuditLogQueryHandler {

    @Autowired
    SalesOrderItemAuditLogRepositoryExtended salesOrderItemDetailsAuditLogRepository;

    private final SalesOrderItemDetailsAuditLogMapper salesOrderItemDetailsAuditLogMapper;

    public SalesOrderItemAuditLogQueryHandlerImpl(SalesOrderItemDetailsAuditLogMapper salesOrderItemDetailsAuditLogMapper) {
        this.salesOrderItemDetailsAuditLogMapper = salesOrderItemDetailsAuditLogMapper;
    }

    @Override
    public List<SalesOrderItemDetailsAuditLogDTO> getSOItemAuditTrailInfoByQueryHandler(SalesOrderItemAuditLogBySONoAndUserIdOrDateQuery parameterObj) {
        try {
            return salesOrderItemDetailsAuditLogRepository.findBySalsOrdrItemDetailId(parameterObj.getSoItemID())
                .map(salesOrderItemDetailsAuditLogMapper::toDto).collectList().toFuture().get()
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
