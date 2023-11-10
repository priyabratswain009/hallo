package com.sunknowledge.dme.rcm.service.impl.audittrail.query;

import com.sunknowledge.dme.rcm.repository.audittrail.ItemMasterAuditLogRepositoryExtended;
import com.sunknowledge.dme.rcm.service.audittrail.query.ItemMasterAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.ItemMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.ItemMasterAuditLogByItemNoAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.mapper.ItemMasterAuditLogMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service()
public class ItemMasterAuditLogQueryHandlerImpl implements ItemMasterAuditLogQueryHandler {

    @Autowired
    ItemMasterAuditLogRepositoryExtended itemMasterAuditLogRepositoryExtended;
    @Autowired
    ItemMasterAuditLogMapper itemMasterAuditLogMapper;
    @Override
    public List<ItemMasterAuditLogDTO> getItemMasterAuditTrailInfoByQueryHandler(ItemMasterAuditLogByItemNoAndUserIdAndDateQuery queryObj) {
        return  itemMasterAuditLogRepositoryExtended.findAll().stream()
            .map(itemMasterAuditLogMapper::toDto).filter(x -> {
                try {
                    return (queryObj.getItemId().equals(x.getItmId()) && (
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
