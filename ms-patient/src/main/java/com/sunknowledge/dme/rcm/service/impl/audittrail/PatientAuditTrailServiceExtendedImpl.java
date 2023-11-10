package com.sunknowledge.dme.rcm.service.impl.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditTrailUtil;
import com.sunknowledge.dme.rcm.service.audittrail.*;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.PatientAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.AuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.UserActivityParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.UserActivityReportDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PatientAuditLogByPatientNoAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.UserActivityReportByFromDateAndToDateQuery;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientMasterSearchServiceExtended;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service("patientAuditTrailServiceExtendedImpl")
public class PatientAuditTrailServiceExtendedImpl implements PatientAuditTrailServiceExtended {

    @Autowired
    PatientMasterSearchServiceExtended patientMasterSearchServiceExtended;
    @Autowired
    PatientAuditLogQueryHandler patientAuditLogQueryHandler;
    @Autowired
    PatientClinicalInformationAuditTrailServiceExtended patientClinicalInformationAuditTrailServiceExtended;
    @Autowired
    PatientDiagnosisAuditTrailServiceExtended patientDiagnosisAuditTrailServiceExtended;
    @Autowired
    PatientDoctorMapAuditTrailServiceExtended patientDoctorMapAuditTrailServiceExtended;
    @Autowired
    PatientDocumentAuditTrailServiceExtended patientDocumentAuditTrailServiceExtended;
    @Autowired
    PatientInsuranceAuditTrailServiceExtended patientInsuranceAuditTrailServiceExtended;
    @Autowired
    PatientInsVerifStatAuditTrailServiceExtended patientInsVerifStatAuditTrailServiceExtended;
    @Autowired
    WorkersCompensationAuditTrailServiceExtended workersCompensationAuditTrailServiceExtended;


    @Override
    public Flux<AuditLogReportDTO> getPatientAuditLog(AuditLogParameterDTO auditLogParameterDTO) {
        List<AuditLogReportDTO> auditLogReportDTOList = new ArrayList<>();
        try {
            Long patientId = patientMasterSearchServiceExtended.
                getPatientByPatientIdNo(auditLogParameterDTO.getPatientIdNo())
                .collectList().toFuture().get().stream().
                max(Comparator.comparing(PatientMasterDTO::getPatientId))
                .orElseThrow(NoSuchElementException::new).getPatientId();
            System.out.println("Patient Id=" + patientId);

            PatientAuditLogByPatientNoAndUserIdAndDateQuery queryObj = new PatientAuditLogByPatientNoAndUserIdAndDateQuery();
            queryObj.setPatientId(patientId);
            queryObj.setPatientIdNo(auditLogParameterDTO.getPatientIdNo());
            queryObj.setUserId(auditLogParameterDTO.getUserId());
            queryObj.setFromDate(auditLogParameterDTO.getFromDate());
            queryObj.setToDate(auditLogParameterDTO.getToDate());

            Mono<List<AuditLogDTO>> auditLogDTO = patientAuditLogQueryHandler.
                getPatientAuditTrailInfoByQueryHandler(queryObj)
                .map(x -> new AuditLogDTO(x.getId(), x.getPatintId(), x.getOldRowData(), x.getNewRowData(),
                    x.getDmlType(), x.getDmlTimestamp(), x.getDmlCreatedBy(), "Patient"))
                .collect(Collectors.toList());

            auditLogDTO.subscribe(System.out::println);
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

    @Override
    public Flux<AuditLogReportDTO> getPatientOverallAuditLog(AuditLogParameterDTO auditLogParameterDTO) {

        List<AuditLogReportDTO> overallData = generateOverallData(getPatientAuditLog(auditLogParameterDTO),
            patientClinicalInformationAuditTrailServiceExtended.getPatientClinicalInformationAuditLog(auditLogParameterDTO),
            patientDiagnosisAuditTrailServiceExtended.getPatientDiagnosisMapAuditLog(auditLogParameterDTO),
            patientDoctorMapAuditTrailServiceExtended.getPatientDoctorMapAuditLog(auditLogParameterDTO),
            patientDocumentAuditTrailServiceExtended.getPatientDocumentAuditLog(auditLogParameterDTO),
            patientInsuranceAuditTrailServiceExtended.getPatientInsuranceAuditLog(auditLogParameterDTO),
            patientInsVerifStatAuditTrailServiceExtended.getPatientInsVerifStatAuditLog(auditLogParameterDTO),
            workersCompensationAuditTrailServiceExtended.getPatientWorkersCompensationAuditLog(auditLogParameterDTO));

        return Mono.just(overallData).flatMapMany(Flux::fromIterable);
    }

    private List<AuditLogReportDTO> generateOverallData(Flux<AuditLogReportDTO> patientAuditLog,
                                                        Flux<AuditLogReportDTO> patientClinicalInformationAuditLog,
                                                        Flux<AuditLogReportDTO> patientDiagnosisMapAuditLog,
                                                        Flux<AuditLogReportDTO> patientDoctorMapAuditLog,
                                                        Flux<AuditLogReportDTO> patientDocumentAuditLog,
                                                        Flux<AuditLogReportDTO> patientInsuranceAuditLog,
                                                        Flux<AuditLogReportDTO> patientInsVerifStatAuditLog,
                                                        Flux<AuditLogReportDTO> patientWorkersCompensationAuditLog) {
        List<AuditLogReportDTO> overallData = new ArrayList<>();
        try {
            patientAuditLog.subscribe(System.out::println);
            overallData.addAll(patientAuditLog.collectList().toFuture().get());
            overallData.addAll(patientClinicalInformationAuditLog.collectList().toFuture().get());
            overallData.addAll(patientDiagnosisMapAuditLog.collectList().toFuture().get());
            overallData.addAll(patientDoctorMapAuditLog.collectList().toFuture().get());
            overallData.addAll(patientDocumentAuditLog.collectList().toFuture().get());
            overallData.addAll(patientInsuranceAuditLog.collectList().toFuture().get());
            overallData.addAll(patientInsVerifStatAuditLog.collectList().toFuture().get());
            overallData.addAll(patientWorkersCompensationAuditLog.collectList().toFuture().get());

            return overallData.stream().sorted(Comparator.comparing(AuditLogReportDTO::getSection)).collect(Collectors.toList());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Flux<UserActivityReportDTO> getPatientUserActivityReport(UserActivityParameterDTO userActivityParameterDTO) {

        UserActivityReportByFromDateAndToDateQuery query = new UserActivityReportByFromDateAndToDateQuery();
        BeanUtils.copyProperties(userActivityParameterDTO, query);
        Flux<UserActivityReportDTO> overallData = patientAuditLogQueryHandler.
            getPatientUserActivityReportByQueryHandler(query);

        return overallData;
    }
}
