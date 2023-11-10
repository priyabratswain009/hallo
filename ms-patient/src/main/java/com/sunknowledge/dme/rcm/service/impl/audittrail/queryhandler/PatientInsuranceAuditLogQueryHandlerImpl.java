package com.sunknowledge.dme.rcm.service.impl.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.repository.PatientInsuranceAuditTrailRepositoryExtended;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.PatientInsuranceAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.PatientInsuranceAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PatientInsuranceAuditLogByPatientNoAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.mapper.PatientInsuranceAuditLogMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class PatientInsuranceAuditLogQueryHandlerImpl implements PatientInsuranceAuditLogQueryHandler {

    @Autowired
    PatientInsuranceAuditTrailRepositoryExtended patientInsuranceAuditTrailRepositoryExtended;

    private final PatientInsuranceAuditLogMapper patientInsuranceAuditLogMapper;

    public PatientInsuranceAuditLogQueryHandlerImpl(PatientInsuranceAuditLogMapper patientInsuranceAuditLogMapper) {
        this.patientInsuranceAuditLogMapper = patientInsuranceAuditLogMapper;
    }
    @Override
    public Flux<PatientInsuranceAuditLogDTO> getPatientInsuranceAuditTrailInfoByQueryHandler(PatientInsuranceAuditLogByPatientNoAndUserIdAndDateQuery queryObj) {
        return patientInsuranceAuditTrailRepositoryExtended.findAll()
            .map(patientInsuranceAuditLogMapper::toDto).filter(x -> {
                try {
                    return (queryObj.getPatientInsuranceIdList().contains(x.getPatintInsnceId())) && ((((JSONObject) new JSONParser()
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
