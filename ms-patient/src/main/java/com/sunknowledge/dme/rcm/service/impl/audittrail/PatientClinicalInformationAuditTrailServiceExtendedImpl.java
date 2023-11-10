package com.sunknowledge.dme.rcm.service.impl.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditTrailUtil;
import com.sunknowledge.dme.rcm.service.audittrail.PatientClinicalInformationAuditTrailServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.PatientClinicalInfoAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.PatientClinicalInformationDTO;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.AuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PatientClinicalInfoAuditLogByPatientNoAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientClinicalSearchParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientClinicalSearchByPatientId;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientClinicalSearchServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientMasterSearchServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@Service("patientClinicalInformationAuditTrailServiceExtendedImpl")
public class PatientClinicalInformationAuditTrailServiceExtendedImpl implements PatientClinicalInformationAuditTrailServiceExtended {

    @Autowired
    PatientMasterSearchServiceExtended patientMasterSearchServiceExtended;
    @Autowired
    PatientClinicalSearchServiceExtended patientClinicalSearchServiceExtended;
    @Autowired
    PatientClinicalInfoAuditLogQueryHandler patientClinicalInfoAuditLogQueryHandler;


    @Override
    public Flux<AuditLogReportDTO> getPatientClinicalInformationAuditLog(AuditLogParameterDTO auditLogParameterDTO) {
        List<AuditLogReportDTO> auditLogReportDTOList = new ArrayList<>();
        try {
            Long patientId = patientMasterSearchServiceExtended.
                getPatientByPatientIdNo(auditLogParameterDTO.getPatientIdNo())
                .collectList().toFuture().get().stream().
                max(Comparator.comparing(PatientMasterDTO::getPatientId))
                .orElseThrow(NoSuchElementException::new).getPatientId();
            System.out.println("Patient Id=" + patientId);

            PatientClinicalSearchByPatientId obj = new PatientClinicalSearchByPatientId();
            obj.setPatientID(patientId);
            obj.setPatientClinicalInformationId(0L);
            List<Long> patientClinicalInformationIdList = patientClinicalSearchServiceExtended.
                getPatientClinicalBySearchParameters(obj).collectList().toFuture().get().stream()
                .map(PatientClinicalInformationDTO::getPatientClinicalInformationId).collect(Collectors.toList());

            PatientClinicalInfoAuditLogByPatientNoAndUserIdAndDateQuery queryObj = new PatientClinicalInfoAuditLogByPatientNoAndUserIdAndDateQuery();
            queryObj.setPatientId(patientId);
            queryObj.setPatientIdNo(auditLogParameterDTO.getPatientIdNo());
            queryObj.setPatientClinicalInfoIdList(patientClinicalInformationIdList);
            queryObj.setUserId(auditLogParameterDTO.getUserId());
            queryObj.setFromDate(auditLogParameterDTO.getFromDate());
            queryObj.setToDate(auditLogParameterDTO.getToDate());

            Mono<List<AuditLogDTO>> auditLogDTO = patientClinicalInfoAuditLogQueryHandler.
                getPatientClinicalInfoAuditTrailInfoByQueryHandler(queryObj)
                .map(x -> new AuditLogDTO(x.getId(), x.getPatntCliicalInfoationId(), x.getOldRowData(), x.getNewRowData(),
                    x.getDmlType(), x.getDmlTimestamp(), x.getDmlCreatedBy(), "Patient Clinical Information"))
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
