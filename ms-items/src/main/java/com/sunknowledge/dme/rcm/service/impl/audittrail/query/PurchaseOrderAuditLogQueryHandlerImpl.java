package com.sunknowledge.dme.rcm.service.impl.audittrail.query;

import com.sunknowledge.dme.rcm.repository.audittrail.PurchaseOrderAuditLogRepositoryExtended;
import com.sunknowledge.dme.rcm.service.audittrail.query.PurchaseOrderAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PurchaseOrderAuditLogByItemNoAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.mapper.PurchaseOrderAuditLogMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseOrderAuditLogQueryHandlerImpl implements PurchaseOrderAuditLogQueryHandler {

    @Autowired
    PurchaseOrderAuditLogRepositoryExtended purchaseOrderAuditLogRepositoryExtended;
    @Autowired
    PurchaseOrderAuditLogMapper purchaseOrderAuditLogMapper;

    @Override
    public List<PurchaseOrderAuditLogDTO> getPurchaseOrderAuditTrailInfoByQueryHandler(PurchaseOrderAuditLogByItemNoAndUserIdAndDateQuery queryObj) {
        return  purchaseOrderAuditLogRepositoryExtended.findAll().stream()
            .map(purchaseOrderAuditLogMapper::toDto).filter(x -> {
                try {
                    return (queryObj.getPOId().equals(x.getpId()) && (
                        (((JSONObject) new JSONParser()
                            .parse(x.getNewRowData()))
                            .get("created_by_id") == null ? "" : ((JSONObject) new JSONParser()
                            .parse(x.getNewRowData()))
                            .get("created_by_id").toString()).equals(String.valueOf(queryObj.getUserID()))
                            ||
                            (((JSONObject) new JSONParser()
                                .parse(x.getNewRowData()))
                                .get("updated_by_id") == null ? "" : ((JSONObject) new JSONParser()
                                .parse(x.getNewRowData()))
                                .get("updated_by_id").toString()).equals(String.valueOf(queryObj.getUserID()))
                            ||
                            (x.getDmlTimestamp().compareTo(queryObj.getFromDate()) >= 0 &&
                                x.getDmlTimestamp().compareTo(queryObj.getToDate()) <= 0)
                                &&
                                !x.getDmlType().equals("DELETE")));
                }
                catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList());
    }
}
