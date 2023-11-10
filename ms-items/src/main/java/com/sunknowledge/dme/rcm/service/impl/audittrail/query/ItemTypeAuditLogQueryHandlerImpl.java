package com.sunknowledge.dme.rcm.service.impl.audittrail.query;

import com.sunknowledge.dme.rcm.repository.audittrail.ItemTypeAuditLogRepositoryExtended;
import com.sunknowledge.dme.rcm.service.audittrail.query.ItemTypeAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.ItemTypeAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.ItemTypeAuditLogByItemTypeIdAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.mapper.ItemTypeAuditLogMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemTypeAuditLogQueryHandlerImpl implements ItemTypeAuditLogQueryHandler {

    @Autowired
    ItemTypeAuditLogRepositoryExtended itemTypeAuditLogRepositoryExtended;
    @Autowired
    ItemTypeAuditLogMapper itemTypeAuditLogMapper;

    @Override
    public List<ItemTypeAuditLogDTO> getItemTypeAuditTrailInfoByQueryHandler(ItemTypeAuditLogByItemTypeIdAndUserIdAndDateQuery queryObj) {
        return  itemTypeAuditLogRepositoryExtended.findAll().stream()
            .map(itemTypeAuditLogMapper::toDto).filter(x -> {
                try {
                    return (queryObj.getItemTypeId().equals(x.getItmTypId()) && (
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
