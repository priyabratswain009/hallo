package com.sunknowledge.dme.rcm.service.impl.audittrail.query;

import com.sunknowledge.dme.rcm.repository.audittrail.StockTransferAuditLogRepositoryExtended;
import com.sunknowledge.dme.rcm.service.audittrail.query.StockTransferAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.StockTransferAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.StockTransferAuditLogByItemNoAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.mapper.StockTransferAuditLogMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockTransferAuditLogQueryHandlerImpl implements StockTransferAuditLogQueryHandler {

    @Autowired
    StockTransferAuditLogRepositoryExtended stockTransferAuditLogRepositoryExtended;
    @Autowired
    StockTransferAuditLogMapper stockTransferAuditLogMapper;
    @Override
    public List<StockTransferAuditLogDTO> getStockTransferAuditTrailInfoByQueryHandler(StockTransferAuditLogByItemNoAndUserIdAndDateQuery queryObj) {
        return  stockTransferAuditLogRepositoryExtended.findAll().stream()
            .map(stockTransferAuditLogMapper::toDto).filter(x -> {
                try {
                    return (queryObj.getStockTransferIdList().contains(x.getStckTrasferId()) && (
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
