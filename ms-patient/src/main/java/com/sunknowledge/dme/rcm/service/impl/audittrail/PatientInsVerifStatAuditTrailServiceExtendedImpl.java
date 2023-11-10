package com.sunknowledge.dme.rcm.service.impl.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditTrailUtil;
import com.sunknowledge.dme.rcm.service.audittrail.PatientInsVerifStatAuditTrailServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.PatientInsVerifStatAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.PatientInsVerifStatDTO;
import com.sunknowledge.dme.rcm.service.dto.PatientInsuranceDTO;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.AuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PatientInsVerifStatAuditLogByPatientNoAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientInsuranceSearchServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientInsuranceVerificationSearchServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientMasterSearchServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@Service("patientInsVerifStatAuditTrailServiceExtendedImpl")
public class PatientInsVerifStatAuditTrailServiceExtendedImpl implements PatientInsVerifStatAuditTrailServiceExtended {

    @Autowired
    PatientMasterSearchServiceExtended patientMasterSearchServiceExtended;
    @Autowired
    PatientInsVerifStatAuditLogQueryHandler patientInsVerifStatAuditLogQueryHandler;
    @Autowired
    PatientInsuranceSearchServiceExtended patientInsuranceSearchServiceExtended;
    @Autowired
    PatientInsuranceVerificationSearchServiceExtended patientInsuranceVerificationSearchServiceExtended;
    @Override
    public Flux<AuditLogReportDTO> getPatientInsVerifStatAuditLog(AuditLogParameterDTO auditLogParameterDTO) {
        List<AuditLogReportDTO> auditLogReportDTOList = new ArrayList<>();
        try {
            Long patientId = patientMasterSearchServiceExtended.
                getPatientByPatientIdNo(auditLogParameterDTO.getPatientIdNo())
                .collectList().toFuture().get().stream().
                max(Comparator.comparing(PatientMasterDTO::getPatientId))
                .orElseThrow(NoSuchElementException::new).getPatientId();
            System.out.println("Patient Id=" + patientId);

            List<Long> patientInsuranceIdList = patientInsuranceSearchServiceExtended.
                getPatientInsuranceByPatientId(patientId).collectList().toFuture().get().stream().map(PatientInsuranceDTO::getPatientInsuranceId).collect(Collectors.toList());
            System.out.println("patientInsuranceIdList => "+patientInsuranceIdList);


            Long insVerifId = patientInsuranceVerificationSearchServiceExtended.
                getPatientInsuranceVerificationByInsuranceIds(patientInsuranceIdList).collectList().toFuture().get().stream().
                map(PatientInsVerifStatDTO::getInsuranceVerifId).collect(Collectors.toList())
                .stream().max(Comparator.comparingLong(Long::longValue)).get();



            PatientInsVerifStatAuditLogByPatientNoAndUserIdAndDateQuery queryObj = new PatientInsVerifStatAuditLogByPatientNoAndUserIdAndDateQuery();
            queryObj.setPatientId(patientId);
            queryObj.setPatientIdNo(auditLogParameterDTO.getPatientIdNo());
            queryObj.setPatientInsuranceVerifId(insVerifId);
            queryObj.setUserId(auditLogParameterDTO.getUserId());
            queryObj.setFromDate(auditLogParameterDTO.getFromDate());
            queryObj.setToDate(auditLogParameterDTO.getToDate());

            Mono<List<AuditLogDTO>> auditLogDTO = patientInsVerifStatAuditLogQueryHandler.
                getPatientInsVerifStatAuditTrailInfoByQueryHandler(queryObj)
                .map(x -> new AuditLogDTO(x.getId(), x.getInsrnceVrifId(), x.getOldRowData(), x.getNewRowData(),
                    x.getDmlType(), x.getDmlTimestamp(), x.getDmlCreatedBy(), "Patient Insurance"))
                .collect(Collectors.toList());

            auditLogDTO.subscribe(System.out::println);

            Map insertKeys = new HashMap();
            insertKeys.put("idKey", "patient_insurance_id");
            insertKeys.put("noValue", auditLogParameterDTO.getPatientIdNo());
            insertKeys.put("userIdKey", "created_by_id");
            insertKeys.put("userNameKey", "created_by_name");
            insertKeys.put("dateTimeKey", "created_date");

            Map updateKeys = new HashMap();
            updateKeys.put("idKey", "patient_insurance_id");
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
