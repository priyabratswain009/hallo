package com.sunknowledge.dme.rcm.service.impl.audittrail.query;

import com.sunknowledge.dme.rcm.repository.audittrail.ItemInventoryStatusAuditLogRepositoryExtended;
import com.sunknowledge.dme.rcm.service.audittrail.query.ItemInventoryStatusAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.ItemInventoryStatusAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.ItemInventoryStatusAuditLogByItemInventoryIdAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.mapper.ItemInventoryStatusAuditLogMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemInventoryStatusAuditLogQueryHandlerImpl implements ItemInventoryStatusAuditLogQueryHandler {

    @Autowired
    ItemInventoryStatusAuditLogRepositoryExtended itemInventoryStatusAuditLogRepositoryExtended;
    @Autowired
    ItemInventoryStatusAuditLogMapper itemInventoryStatusAuditLogMapper;

    @Override
    public List<ItemInventoryStatusAuditLogDTO> getItemInventoryStatusAuditTrailInfoByQueryHandler(ItemInventoryStatusAuditLogByItemInventoryIdAndUserIdAndDateQuery queryObj) {
        return  itemInventoryStatusAuditLogRepositoryExtended.findAll().stream()
            .map(itemInventoryStatusAuditLogMapper::toDto).filter(x -> {
                try {
                    return (queryObj.getItemInventoryIdList().contains(x.getItemInvtoryStausId()) && (
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
