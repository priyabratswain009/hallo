package com.sunknowledge.dme.rcm.service.impl.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditTrailUtil;
import com.sunknowledge.dme.rcm.service.audittrail.PatientDoctorMapAuditTrailServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.PatientDoctorMappingAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.PatientDoctorMapDTO;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.AuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PatientDoctorMapAuditLogByPatientNoAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.dto.patiententry.PatientDoctorMappingOutputDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientDoctorMappingSearchParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientDoctorMappingSearchByPatIdOrMapIdOrDocId;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientDoctorMappingSearchServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientMasterSearchServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@Service("patientDoctorMapAuditTrailServiceExtendedImpl")
public class PatientDoctorMapAuditTrailServiceExtendedImpl implements PatientDoctorMapAuditTrailServiceExtended {

    @Autowired
    PatientMasterSearchServiceExtended patientMasterSearchServiceExtended;
    @Autowired
    PatientDoctorMappingSearchServiceExtended patientDoctorMappingSearchServiceExtended;
    @Autowired
    PatientDoctorMappingAuditLogQueryHandler patientDoctorMappingAuditLogQueryHandler;
    @Override
    public Flux<AuditLogReportDTO> getPatientDoctorMapAuditLog(AuditLogParameterDTO auditLogParameterDTO) {
        List<AuditLogReportDTO> auditLogReportDTOList = new ArrayList<>();
        try {
            Long patientId = patientMasterSearchServiceExtended.
                getPatientByPatientIdNo(auditLogParameterDTO.getPatientIdNo())
                .collectList().toFuture().get().stream().
                max(Comparator.comparing(PatientMasterDTO::getPatientId))
                .orElseThrow(NoSuchElementException::new).getPatientId();

            PatientDoctorMappingSearchByPatIdOrMapIdOrDocId obj = new PatientDoctorMappingSearchByPatIdOrMapIdOrDocId();
            obj.setPatientId(patientId);
            List<Long> patientDoctorMapIdList = List.of(patientDoctorMappingSearchServiceExtended.
                getPatientDoctorMappingBySearchParameters(obj).toFuture().get().getPatientDoctorMapId());
                //.map(PatientDoctorMappingOutputDTO::getPatientDoctorMapId).collect(Collectors.toList());

            PatientDoctorMapAuditLogByPatientNoAndUserIdAndDateQuery queryObj = new PatientDoctorMapAuditLogByPatientNoAndUserIdAndDateQuery();
            queryObj.setPatientId(patientId);
            queryObj.setPatientIdNo(auditLogParameterDTO.getPatientIdNo());
            queryObj.setPatientDoctorMapIdList(patientDoctorMapIdList);
            queryObj.setUserId(auditLogParameterDTO.getUserId());
            queryObj.setFromDate(auditLogParameterDTO.getFromDate());
            queryObj.setToDate(auditLogParameterDTO.getToDate());

            Mono<List<AuditLogDTO>> auditLogDTO = patientDoctorMappingAuditLogQueryHandler.
                getPatientDoctorMappingAuditTrailInfoByQueryHandler(queryObj)
                .map(x -> new AuditLogDTO(x.getId(), x.getPaentDctorMapId(), x.getOldRowData(), x.getNewRowData(),
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
