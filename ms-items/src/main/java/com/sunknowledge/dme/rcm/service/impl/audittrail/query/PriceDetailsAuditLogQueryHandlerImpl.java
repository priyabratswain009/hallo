package com.sunknowledge.dme.rcm.service.impl.audittrail.query;

import com.sunknowledge.dme.rcm.repository.audittrail.PriceDetailsAuditLogRepositoryExtended;
import com.sunknowledge.dme.rcm.service.audittrail.query.PriceDetailsAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.PriceDetailsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PriceDetailsAuditLogByPriceDetailsIdAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.mapper.PriceDetailsAuditLogMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceDetailsAuditLogQueryHandlerImpl implements PriceDetailsAuditLogQueryHandler {

    @Autowired
    PriceDetailsAuditLogRepositoryExtended priceDetailsAuditLogRepositoryExtended;
    @Autowired
    PriceDetailsAuditLogMapper priceDetailsAuditLogMapper;

    @Override
    public List<PriceDetailsAuditLogDTO> getPriceDetailsAuditTrailInfoByQueryHandler(PriceDetailsAuditLogByPriceDetailsIdAndUserIdAndDateQuery queryObj) {
        return  priceDetailsAuditLogRepositoryExtended.findAll().stream()
            .map(priceDetailsAuditLogMapper::toDto).filter(x -> {
                try {
                    return (queryObj.getPriceDetailsIdList().contains(x.getPrceDetlId()) && (
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
