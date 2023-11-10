package com.sunknowledge.dme.rcm.service.impl.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditTrailUtil;
import com.sunknowledge.dme.rcm.service.audittrail.PatientDiagnosisAuditTrailServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.PatientDiagnosisAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.PatientDiagnosisDTO;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.AuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PatientDiagnosisAuditLogByPatientNoAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientDiagnosisSearchParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientDiagnosisSearchByPatIdPatDiaId;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientDiagnosisSearchServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientMasterSearchServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@Service("patientDiagnosisMapAuditTrailServiceExtendedImpl")
public class PatientDiagnosisAuditTrailServiceExtendedImpl implements PatientDiagnosisAuditTrailServiceExtended {
    @Autowired
    PatientMasterSearchServiceExtended patientMasterSearchServiceExtended;
    @Autowired
    PatientDiagnosisSearchServiceExtended patientDiagnosisSearchServiceExtended;
    @Autowired
    PatientDiagnosisAuditLogQueryHandler patientDiagnosisAuditLogQueryHandler;

    @Override
    public Flux<AuditLogReportDTO> getPatientDiagnosisMapAuditLog(AuditLogParameterDTO auditLogParameterDTO) {
        List<AuditLogReportDTO> auditLogReportDTOList = new ArrayList<>();
        try {
            Long patientId = patientMasterSearchServiceExtended.
                getPatientByPatientIdNo(auditLogParameterDTO.getPatientIdNo())
                .collectList().toFuture().get().stream().
                max(Comparator.comparing(PatientMasterDTO::getPatientId))
                .orElseThrow(NoSuchElementException::new).getPatientId();


            PatientDiagnosisSearchByPatIdPatDiaId obj = new PatientDiagnosisSearchByPatIdPatDiaId();
            obj.setPatientID(patientId);
            obj.setPatientDiagnosisId(0L);
            List<Long> patientDiagnosisIdList = patientDiagnosisSearchServiceExtended.
                getPatientDiagnosisBySearchParameters(obj).collectList().toFuture().get().stream()
                .map(PatientDiagnosisDTO::getPatientDiagnosisId).collect(Collectors.toList());


            PatientDiagnosisAuditLogByPatientNoAndUserIdAndDateQuery queryObj = new PatientDiagnosisAuditLogByPatientNoAndUserIdAndDateQuery();
            queryObj.setPatientId(patientId);
            queryObj.setPatientIdNo(auditLogParameterDTO.getPatientIdNo());
            queryObj.setPatientDiagnosisIdList(patientDiagnosisIdList);
            queryObj.setUserId(auditLogParameterDTO.getUserId());
            queryObj.setFromDate(auditLogParameterDTO.getFromDate());
            queryObj.setToDate(auditLogParameterDTO.getToDate());

            Mono<List<AuditLogDTO>> auditLogDTO = patientDiagnosisAuditLogQueryHandler.
                getPatientDiagnosisAuditTrailInfoByQueryHandler(queryObj)
                .map(x -> new AuditLogDTO(x.getId(), x.getPatintDiagoisId(), x.getOldRowData(), x.getNewRowData(),
                    x.getDmlType(), x.getDmlTimestamp(), x.getDmlCreatedBy(), "Patient Doctor Mapping"))
                .collect(Collectors.toList());


            Map insertKeys = new HashMap();
            insertKeys.put("idKey", "patient_id");
            insertKeys.put("noValue", auditLogParameterDTO.getPatientIdNo());
            insertKeys.put("userIdKey", "created_by_id");
            insertKeys.put("userNameKey", "created_by_name");
            insertKeys.put("dateTimeKey", "created_date");

            Map updateKeys = new HashMap();
            updateKeys.put("idKey", "patient_id");
            updateKeys.put("noValue", auditLogParameterDTO.getPatientIdNo());
            updateKeys.put("userIdKey", "updated_by_id");
            updateKeys.put("userNameKey", "updated_by_name");
            updateKeys.put("dateTimeKey", "updated_date");

            auditLogReportDTOList = AuditTrailUtil.generateReportJSON(auditLogDTO.toFuture().get(),
                insertKeys, updateKeys);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Mono.just(auditLogReportDTOList).flatMapMany(Flux::fromIterable)
            .log();
    }
}
