package com.sunknowledge.dme.rcm.service.impl.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.repository.audittrail.InsuranceMasterAuditLogRepositoryExtended;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.InsuranceMasterAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.InsuranceMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.InsuranceMasterAuditLogByInsuranceIdNoAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.mapper.InsuranceMasterAuditLogMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InsuranceMasterAuditLogQueryHandlerImpl implements InsuranceMasterAuditLogQueryHandler {

    @Autowired
    InsuranceMasterAuditLogRepositoryExtended insuranceMasterAuditLogRepositoryExtended;
    @Autowired
    InsuranceMasterAuditLogMapper insuranceMasterAuditLogMapper;

    @Override
    public List<InsuranceMasterAuditLogDTO> getInsuranceMasterAuditTrailInfoByQueryHandler(InsuranceMasterAuditLogByInsuranceIdNoAndUserIdAndDateQuery queryObj) {
        return insuranceMasterAuditLogRepositoryExtended.findAll().stream()
            .map(insuranceMasterAuditLogMapper::toDto).filter(x -> {
                try {
                    return (queryObj.getInsuranceId().equals(x.getInsrnceId())) && ((((JSONObject) new JSONParser()
                        .parse(x.getNewRowData()))
                        .get("created_by_id") == null ? "" : ((JSONObject) new JSONParser()
                        .parse(x.getNewRowData()))
                        .get("created_by_id").toString()).equals(String.valueOf(queryObj.getUserId())) ||
                        (((JSONObject) new JSONParser()
                            .parse(x.getNewRowData()))
                            .get("updated_by_id") == null ? "" : ((JSONObject) new JSONParser()
                            .parse(x.getNewRowData()))
                            .get("updated_by_id").toString()).equals(String.valueOf(queryObj.getUserId())) ||
                        (x.getDmlTimestamp().compareTo(queryObj.getFromDate()) >= 0 &&
                            x.getDmlTimestamp().compareTo(queryObj.getToDate()) <= 0) &&
                            !x.getDmlType().equals("DELETE"));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList());
    }
}
