package com.sunknowledge.dme.rcm.service.impl.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.repository.FunctionalAbilityAuditLogRepositoryExtended;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.FunctionalAbilityAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.FunctionalAbilityAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.FunctionalAbilityAuditLogByFunctionalAbilityIdAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.mapper.FunctionalAbilityAuditLogMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class FunctionalAbilityAuditLogQueryHandlerImpl implements FunctionalAbilityAuditLogQueryHandler {

    @Autowired
    FunctionalAbilityAuditLogRepositoryExtended functionalAbilityAuditLogRepositoryExtended;
    @Autowired
    FunctionalAbilityAuditLogMapper functionalAbilityAuditLogMapper;

    @Override
    public Flux<FunctionalAbilityAuditLogDTO> getFunctionalAbilityAuditTrailInfoByQueryHandler(FunctionalAbilityAuditLogByFunctionalAbilityIdAndUserIdAndDateQuery queryObj) {
        return functionalAbilityAuditLogRepositoryExtended.findAll()
            .map(functionalAbilityAuditLogMapper::toDto).filter(x -> {
                try {
                    return (x.getFunalAbityId()==queryObj.getFunctionalAbilityId()) && ((((JSONObject) new JSONParser()
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
            });
    }
}
