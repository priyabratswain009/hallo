package com.sunknowledge.dme.rcm.service.impl.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.repository.PatientInsVerifStatAuditLogRepositoryExtended;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.PatientInsVerifStatAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.PatientInsVerifStatAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PatientInsVerifStatAuditLogByPatientNoAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.mapper.PatientInsVerifStatAuditLogMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class PatientInsVerifStatAuditLogQueryHandlerImpl implements PatientInsVerifStatAuditLogQueryHandler {

    @Autowired
    PatientInsVerifStatAuditLogRepositoryExtended patientInsVerifStatAuditLogRepositoryExtended;
    @Autowired
    PatientInsVerifStatAuditLogMapper patientInsVerifStatAuditLogMapper;

    @Override
    public Flux<PatientInsVerifStatAuditLogDTO> getPatientInsVerifStatAuditTrailInfoByQueryHandler(PatientInsVerifStatAuditLogByPatientNoAndUserIdAndDateQuery queryObj) {
        Flux<PatientInsVerifStatAuditLogDTO> data = patientInsVerifStatAuditLogRepositoryExtended.findAll()
            .map(patientInsVerifStatAuditLogMapper::toDto).filter(x -> {
                try {
                    return (queryObj.getPatientInsuranceVerifId() == x.getInsrnceVrifId()) && ((((JSONObject) new JSONParser()
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

        data.subscribe(System.out::println);

        return data;
    }
}
