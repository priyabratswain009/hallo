package com.sunknowledge.dme.rcm.service.impl.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.repository.PatientDiagnosisAuditLogRepositoryExtended;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.PatientDiagnosisAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.PatientDiagnosisAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PatientDiagnosisAuditLogByPatientNoAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.mapper.PatientDiagnosisAuditLogMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class PatientDiagnosisAuditLogQueryHandlerImpl implements PatientDiagnosisAuditLogQueryHandler {

    @Autowired
    PatientDiagnosisAuditLogRepositoryExtended patientDiagnosisAuditLogRepositoryExtended;
    @Autowired
    PatientDiagnosisAuditLogMapper patientDiagnosisAuditLogMapper;

    @Override
    public Flux<PatientDiagnosisAuditLogDTO> getPatientDiagnosisAuditTrailInfoByQueryHandler(PatientDiagnosisAuditLogByPatientNoAndUserIdAndDateQuery queryObj) {
        return patientDiagnosisAuditLogRepositoryExtended.findAll()
            .map(patientDiagnosisAuditLogMapper::toDto).filter(x -> {
                try {
                    return (queryObj.getPatientDiagnosisIdList().contains(x.getPatintDiagoisId())) && ((((JSONObject) new JSONParser()
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
