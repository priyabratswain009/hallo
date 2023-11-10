package com.sunknowledge.dme.rcm.service.impl.audittrail.query;

import com.sunknowledge.dme.rcm.repository.audittrail.ItemVendorMappingAuditLogRepositoryExtended;
import com.sunknowledge.dme.rcm.service.audittrail.query.ItemVendorMappingAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.ItemVendorMappingAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.ItemVendorMappingAuditLogByItemVendorIdAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.mapper.ItemVendorMappingAuditLogMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemVendorMappingAuditLogQueryHandlerImpl implements ItemVendorMappingAuditLogQueryHandler {

    @Autowired
    ItemVendorMappingAuditLogRepositoryExtended itemVendorMappingAuditLogRepositoryExtended;
    @Autowired
    ItemVendorMappingAuditLogMapper itemVendorMappingAuditLogMapper;

    @Override
    public List<ItemVendorMappingAuditLogDTO> getItemVendorMappingAuditTrailInfoByQueryHandler(ItemVendorMappingAuditLogByItemVendorIdAndUserIdAndDateQuery queryObj) {
        return  itemVendorMappingAuditLogRepositoryExtended.findAll().stream()
            .map(itemVendorMappingAuditLogMapper::toDto).filter(x -> {
                try {
                    return (queryObj.getItemVendorIdList().contains(x.getItmVndorId()) && (
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
