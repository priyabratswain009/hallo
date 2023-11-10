package com.sunknowledge.dme.rcm.service.impl.audittrail.query;

import com.sunknowledge.dme.rcm.repository.audittrail.PriceMasterAuditLogRepositoryExtended;
import com.sunknowledge.dme.rcm.service.audittrail.query.PriceMasterAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.PriceMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PriceMasterAuditLogByPriceTableIdAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.mapper.PriceMasterAuditLogMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceMasterAuditLogQueryHandlerImpl implements PriceMasterAuditLogQueryHandler {

    @Autowired
    PriceMasterAuditLogRepositoryExtended priceMasterAuditLogRepositoryExtended;
    @Autowired
    PriceMasterAuditLogMapper priceMasterAuditLogMapper;

    @Override
    public List<PriceMasterAuditLogDTO> getPriceMasterAuditTrailInfoByQueryHandler(PriceMasterAuditLogByPriceTableIdAndUserIdAndDateQuery queryObj) {
        return  priceMasterAuditLogRepositoryExtended.findAll().stream()
            .map(priceMasterAuditLogMapper::toDto).filter(x -> {
                try {
                    return (queryObj.getPriceTableId().equals(x.getPrceTbleId()) && (
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
