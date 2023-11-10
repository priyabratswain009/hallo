package com.sunknowledge.dme.rcm.service.impl.audittrail.query;

import com.sunknowledge.dme.rcm.repository.audittrail.ItemSerialNumberAuditLogRepositoryExtended;
import com.sunknowledge.dme.rcm.service.audittrail.query.ItemSerialNumberAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.ItemSerialNumberAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.ItemSerialNumberAuditLogByItemSerialNumberAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.mapper.ItemSerialNumberAuditLogMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemSerialNumberAuditLogQueryHandlerImpl implements ItemSerialNumberAuditLogQueryHandler {

    @Autowired
    ItemSerialNumberAuditLogRepositoryExtended itemSerialNumberAuditLogRepositoryExtended;
    @Autowired
    ItemSerialNumberAuditLogMapper itemSerialNumberAuditLogMapper;

    @Override
    public List<ItemSerialNumberAuditLogDTO> getItemSerialNumberAuditTrailInfoByQueryHandler(ItemSerialNumberAuditLogByItemSerialNumberAndUserIdAndDateQuery queryObj) {
        return  itemSerialNumberAuditLogRepositoryExtended.findAll().stream()
            .map(itemSerialNumberAuditLogMapper::toDto).filter(x -> {
                try {
                    return (queryObj.getItemSerialNumberList().contains(x.getItmSrialNmberId()) && (
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
