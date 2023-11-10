package com.sunknowledge.dme.rcm.service.impl.audittrail.query;

import com.sunknowledge.dme.rcm.repository.audittrail.ItemGroupAuditLogRepositoryExtended;
import com.sunknowledge.dme.rcm.service.audittrail.query.ItemGroupAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.ItemGroupAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.ItemGroupAuditLogByItemGroupIdAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.mapper.ItemGroupAuditLogMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemGroupAuditLogQueryHandlerImpl implements ItemGroupAuditLogQueryHandler {
    @Autowired
    ItemGroupAuditLogRepositoryExtended itemGroupAuditLogRepositoryExtended;
    private final ItemGroupAuditLogMapper itemGroupAuditLogMapper;

    public ItemGroupAuditLogQueryHandlerImpl(ItemGroupAuditLogMapper itemGroupAuditLogMapper) {
        this.itemGroupAuditLogMapper = itemGroupAuditLogMapper;
    }


    @Override
    public List<ItemGroupAuditLogDTO> getItemGroupAuditTrailInfoByQueryHandler(ItemGroupAuditLogByItemGroupIdAndUserIdAndDateQuery queryObj) {
        return  itemGroupAuditLogRepositoryExtended.findAll().stream()
            .map(itemGroupAuditLogMapper::toDto).filter(x -> {
                try {
                    return (queryObj.getItemGroupId().equals(x.getItemGrpId()) && (
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
