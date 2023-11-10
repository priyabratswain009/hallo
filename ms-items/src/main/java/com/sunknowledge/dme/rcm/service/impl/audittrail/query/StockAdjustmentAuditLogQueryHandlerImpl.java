package com.sunknowledge.dme.rcm.service.impl.audittrail.query;

import com.sunknowledge.dme.rcm.repository.audittrail.StockAdjustmentAuditLogRepositoryExtended;
import com.sunknowledge.dme.rcm.service.audittrail.query.StockAdjustmentAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.StockAdjustmentAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.StockAdjustmentAuditLogByItemNoAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.mapper.StockAdjustmentAuditLogMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockAdjustmentAuditLogQueryHandlerImpl implements StockAdjustmentAuditLogQueryHandler {

    @Autowired
    StockAdjustmentAuditLogRepositoryExtended stockAdjustmentAuditLogRepositoryExtended;
    @Autowired
    StockAdjustmentAuditLogMapper stockAdjustmentAuditLogMapper;
    @Override
    public List<StockAdjustmentAuditLogDTO> getStockAdjustmentAuditTrailInfoByQueryHandler(StockAdjustmentAuditLogByItemNoAndUserIdAndDateQuery queryObj) {
        return  stockAdjustmentAuditLogRepositoryExtended.findAll().stream()
            .map(stockAdjustmentAuditLogMapper::toDto).filter(x -> {
                try {
                    return (queryObj.getStockAdjustmentIdList().contains(x.getStckAdjstentId()) && (
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
