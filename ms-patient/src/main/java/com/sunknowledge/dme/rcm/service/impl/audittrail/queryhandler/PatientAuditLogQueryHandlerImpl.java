package com.sunknowledge.dme.rcm.service.impl.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.repository.PatientAuditTrailRepositoryExtended;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.PatientAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.UserActivityReportDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PatientAuditLogByPatientNoAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.UserActivityReportByFromDateAndToDateQuery;
import com.sunknowledge.dme.rcm.service.mapper.PatientMasterAuditLogMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.concurrent.ExecutionException;

@Service
public class PatientAuditLogQueryHandlerImpl implements PatientAuditLogQueryHandler {

    @Autowired
    PatientAuditTrailRepositoryExtended patientAuditTrailRepositoryExtended;

    private final PatientMasterAuditLogMapper patientMasterAuditLogMapper;

    public PatientAuditLogQueryHandlerImpl(PatientMasterAuditLogMapper patientMasterAuditLogMapper) {
        this.patientMasterAuditLogMapper = patientMasterAuditLogMapper;
    }

    @Override
    public Flux<PatientMasterAuditLogDTO> getPatientAuditTrailInfoByQueryHandler(PatientAuditLogByPatientNoAndUserIdAndDateQuery queryObj) {
        return patientAuditTrailRepositoryExtended.findAll()
            .map(patientMasterAuditLogMapper::toDto).filter(x -> {
                try {
                    return (x.getPatintId() == queryObj.getPatientId()) && ((((JSONObject) new JSONParser()
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

    @Override
    public Flux<UserActivityReportDTO> getPatientUserActivityReportByQueryHandler(UserActivityReportByFromDateAndToDateQuery query) {
        return patientAuditTrailRepositoryExtended.findUserActivityReportByDate(query.getFromDate(), query.getToDate());
    }

}
