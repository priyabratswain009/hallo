package com.sunknowledge.dme.rcm.service.impl.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.repository.PatientDoctorMapAuditLogRepositoryExtended;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.PatientDoctorMappingAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.PatientDoctorMapAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PatientDoctorMapAuditLogByPatientNoAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.mapper.PatientDoctorMapAuditLogMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class PatientDoctorMappingAuditLogQueryHandlerImpl implements PatientDoctorMappingAuditLogQueryHandler {

    @Autowired
    PatientDoctorMapAuditLogRepositoryExtended patientDoctorMapAuditLogRepositoryExtended;
    @Autowired
    PatientDoctorMapAuditLogMapper patientDoctorMapAuditLogMapper;

    @Override
    public Flux<PatientDoctorMapAuditLogDTO> getPatientDoctorMappingAuditTrailInfoByQueryHandler(PatientDoctorMapAuditLogByPatientNoAndUserIdAndDateQuery queryObj) {
        return patientDoctorMapAuditLogRepositoryExtended.findAll()
            .map(patientDoctorMapAuditLogMapper::toDto).filter(x -> {
                try {
                    return (queryObj.getPatientDoctorMapIdList().contains(x.getPaentDctorMapId())) && ((((JSONObject) new JSONParser()
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
