package com.sunknowledge.dme.rcm.service.impl.audittrail.query;

import com.sunknowledge.dme.rcm.repository.audittrail.ItemProcedureCodeMapAuditLogRepositoryExtended;
import com.sunknowledge.dme.rcm.service.audittrail.query.ItemProcedureCodeMapAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.ItemProcedureCodeMapAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.ItemProcedureCodeMapAuditLogByItemProcedureCodeMapIdAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.mapper.ItemProcedureCodeMapAuditLogMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemProcedureCodeMapAuditLogQueryHandlerImpl implements ItemProcedureCodeMapAuditLogQueryHandler {
    @Autowired
    ItemProcedureCodeMapAuditLogRepositoryExtended itemProcedureCodeMapAuditLogRepositoryExtended;
    @Autowired
    ItemProcedureCodeMapAuditLogMapper itemProcedureCodeMapAuditLogMapper;

    @Override
    public List<ItemProcedureCodeMapAuditLogDTO> getItemProcedureCodeMapAuditTrailInfoByQueryHandler(ItemProcedureCodeMapAuditLogByItemProcedureCodeMapIdAndUserIdAndDateQuery queryObj) {
        return  itemProcedureCodeMapAuditLogRepositoryExtended.findAll().stream()
            .map(itemProcedureCodeMapAuditLogMapper::toDto).filter(x -> {
                try {
                    return (queryObj.getItemProcedureCodeMapIdList().contains(x.getItemProcdreCdeMapId()) && (
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
