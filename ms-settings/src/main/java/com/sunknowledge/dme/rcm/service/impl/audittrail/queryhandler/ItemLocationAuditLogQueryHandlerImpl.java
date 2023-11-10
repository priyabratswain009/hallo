package com.sunknowledge.dme.rcm.service.impl.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.repository.audittrail.ItemLocationAuditLogRepositoryExtended;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.ItemLocationAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.ItemLocationAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.ItemLocationAuditLogByItemLocationIdAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.mapper.ItemLocationAuditLogMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemLocationAuditLogQueryHandlerImpl implements ItemLocationAuditLogQueryHandler {

    @Autowired
    ItemLocationAuditLogRepositoryExtended itemLocationAuditLogRepositoryExtended;
    @Autowired
    ItemLocationAuditLogMapper itemLocationAuditLogMapper;
    @Override
    public List<ItemLocationAuditLogDTO> getItemLocationAuditTrailInfoByQueryHandler(ItemLocationAuditLogByItemLocationIdAndUserIdAndDateQuery queryObj) {
        return  itemLocationAuditLogRepositoryExtended.findAll().stream()
            .map(itemLocationAuditLogMapper::toDto).filter(x -> {
                try {
                    return (queryObj.getItemLocationId().equals(x.getItmLctionId()) && (
                        (((JSONObject) new JSONParser()
                            .parse(x.getNewRowData()))
                            .get("created_by_id") == null ? "" : ((JSONObject) new JSONParser()
                            .parse(x.getNewRowData()))
                            .get("created_by_id").toString()).equals(String.valueOf(queryObj.getUserId()))
                            ||
                            (((JSONObject) new JSONParser()
                                .parse(x.getNewRowData()))
                                .get("updated_by_id") == null ? "" : ((JSONObject) new JSONParser()
                                .parse(x.getNewRowData()))
                                .get("updated_by_id").toString()).equals(String.valueOf(queryObj.getUserId()))
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
