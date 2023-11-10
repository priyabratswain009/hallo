package com.sunknowledge.dme.rcm.service.impl.audittrail.query;

import com.sunknowledge.dme.rcm.repository.audittrail.ManufacturerAuditLogRepositoryExtended;
import com.sunknowledge.dme.rcm.service.audittrail.query.ManufacturerAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.ManufacturerAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.ManufacturerAuditLogByManufacturerNoAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.mapper.ManufacturerAuditLogMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManufacturerAuditLogQueryHandlerImpl implements ManufacturerAuditLogQueryHandler {

    @Autowired
    ManufacturerAuditLogRepositoryExtended manufacturerAuditLogRepositoryExtended;
    @Autowired
    ManufacturerAuditLogMapper manufacturerAuditLogMapper;

    @Override
    public List<ManufacturerAuditLogDTO> getManufacturerAuditTrailInfoByQueryHandler(ManufacturerAuditLogByManufacturerNoAndUserIdAndDateQuery queryObj) {
        return  manufacturerAuditLogRepositoryExtended.findAll().stream()
            .map(manufacturerAuditLogMapper::toDto).filter(x -> {
                try {
                    return (queryObj.getManufacturerId().equals(x.getMnufcturerId()) && (
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
