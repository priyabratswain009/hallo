package com.sunknowledge.dme.rcm.service.impl.audittrail.query;

import com.sunknowledge.dme.rcm.repository.audittrail.PurchaseOrderItemsAuditLogRepositoryExtended;
import com.sunknowledge.dme.rcm.service.audittrail.query.PurchaseOrderItemsAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderItemsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PurchaseOrderItemsAuditLogByItemNoAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.mapper.PurchaseOrderItemsAuditLogMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseOrderItemsAuditLogQueryHandlerImpl implements PurchaseOrderItemsAuditLogQueryHandler {

    @Autowired
    PurchaseOrderItemsAuditLogRepositoryExtended purchaseOrderItemsAuditLogRepositoryExtended;
    @Autowired
    PurchaseOrderItemsAuditLogMapper purchaseOrderItemsAuditLogMapper;
    @Override
    public List<PurchaseOrderItemsAuditLogDTO> getPurchaseOrderItemsAuditTrailInfoByQueryHandler(PurchaseOrderItemsAuditLogByItemNoAndUserIdAndDateQuery queryObj) {
        return  purchaseOrderItemsAuditLogRepositoryExtended.findAll().stream()
            .map(purchaseOrderItemsAuditLogMapper::toDto).filter(x -> {
                try {
                    return (queryObj.getPOItemsIdList().contains(x.getPoItmsId()) && (
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
