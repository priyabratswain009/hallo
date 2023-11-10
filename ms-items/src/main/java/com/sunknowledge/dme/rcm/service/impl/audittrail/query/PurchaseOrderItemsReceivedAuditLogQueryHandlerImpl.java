package com.sunknowledge.dme.rcm.service.impl.audittrail.query;

import com.sunknowledge.dme.rcm.repository.audittrail.PurchaseOrderItemsReceivedAuditLogRepositoryExtended;
import com.sunknowledge.dme.rcm.service.audittrail.query.PurchaseOrderItemsReceivedAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderItemsReceivedAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PurchaseOrderItemsReceivedAuditLogByItemNoAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.mapper.PurchaseOrderItemsReceivedAuditLogMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseOrderItemsReceivedAuditLogQueryHandlerImpl implements PurchaseOrderItemsReceivedAuditLogQueryHandler {

    @Autowired
    PurchaseOrderItemsReceivedAuditLogRepositoryExtended purchaseOrderItemsReceivedAuditLogRepositoryExtended;
    @Autowired
    PurchaseOrderItemsReceivedAuditLogMapper purchaseOrderItemsReceivedAuditLogMapper;
    @Override
    public List<PurchaseOrderItemsReceivedAuditLogDTO> getPurchaseOrderItemsReceivedAuditTrailInfoByQueryHandler(PurchaseOrderItemsReceivedAuditLogByItemNoAndUserIdAndDateQuery queryObj) {
        return  purchaseOrderItemsReceivedAuditLogRepositoryExtended.findAll().stream()
            .map(purchaseOrderItemsReceivedAuditLogMapper::toDto).filter(x -> {
                try {
                    return (queryObj.getPOItemsReceivedIdList().contains(x.getPoItmRecivedId()) && (
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
