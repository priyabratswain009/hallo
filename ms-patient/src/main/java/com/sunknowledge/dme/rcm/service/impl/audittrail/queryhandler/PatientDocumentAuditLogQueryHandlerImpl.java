package com.sunknowledge.dme.rcm.service.impl.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.repository.PatientDocumentAuditLogRepositoryExtended;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.PatientDocumentAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.PatientDocumentAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PatientDocumentAuditLogByPatientNoAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.mapper.PatientDocumentAuditLogMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class PatientDocumentAuditLogQueryHandlerImpl implements PatientDocumentAuditLogQueryHandler {

    @Autowired
    PatientDocumentAuditLogRepositoryExtended patientDocumentAuditLogRepositoryExtended;
    @Autowired
    PatientDocumentAuditLogMapper patientDocumentAuditLogMapper;

    @Override
    public Flux<PatientDocumentAuditLogDTO> getPatientDocumentAuditTrailInfoByQueryHandler(PatientDocumentAuditLogByPatientNoAndUserIdAndDateQuery queryObj) {
        return patientDocumentAuditLogRepositoryExtended.findAll()
            .map(patientDocumentAuditLogMapper::toDto).filter(x -> {
                try {
                    return (queryObj.getPatientDocumentIdList().contains(x.getPtientDocmtId())) && ((((JSONObject) new JSONParser()
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
