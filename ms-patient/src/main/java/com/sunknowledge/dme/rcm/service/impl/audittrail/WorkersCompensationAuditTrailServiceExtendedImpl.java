package com.sunknowledge.dme.rcm.service.impl.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditTrailUtil;
import com.sunknowledge.dme.rcm.service.audittrail.WorkersCompensationAuditTrailServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.WorkerCompensationAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.WorkersCompensationDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.AuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.WorkerCompensationAuditLogByPatientNoAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.WorkerCompensationSearchParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.WorkerCompensationSearchByPatIdOrWorkCompIdOrInsuredEmployer;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientMasterSearchServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.WorkerCompensationSearchServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@Service("workersCompensationAuditTrailServiceExtendedImpl")
public class WorkersCompensationAuditTrailServiceExtendedImpl implements WorkersCompensationAuditTrailServiceExtended {

    @Autowired
    PatientMasterSearchServiceExtended patientMasterSearchServiceExtended;
    @Autowired
    WorkerCompensationSearchServiceExtended workerCompensationSearchServiceExtended;
    @Autowired
    WorkerCompensationAuditLogQueryHandler workerCompensationAuditLogQueryHandler;

    @Override
    public Flux<AuditLogReportDTO> getPatientWorkersCompensationAuditLog(AuditLogParameterDTO auditLogParameterDTO) {
        List<AuditLogReportDTO> auditLogReportDTOList = new ArrayList<>();
        try {
            Long patientId = patientMasterSearchServiceExtended.
                getPatientByPatientIdNo(auditLogParameterDTO.getPatientIdNo())
                .collectList().toFuture().get().stream().
                max(Comparator.comparing(PatientMasterDTO::getPatientId))
                .orElseThrow(NoSuchElementException::new).getPatientId();
            System.out.println("Patient Id=" + patientId);

            WorkerCompensationSearchByPatIdOrWorkCompIdOrInsuredEmployer obj = new WorkerCompensationSearchByPatIdOrWorkCompIdOrInsuredEmployer();
            obj.setPatientId(patientId);
            obj.setWorkersCompensationId(0L);
            List<Long> workerCompensationIdList = workerCompensationSearchServiceExtended.
                getWorkerCompensationBySearchParameters(obj).collectList().toFuture().get().stream()
                .map(WorkersCompensationDTO::getWorkersCompensationId).collect(Collectors.toList());


            WorkerCompensationAuditLogByPatientNoAndUserIdAndDateQuery queryObj = new WorkerCompensationAuditLogByPatientNoAndUserIdAndDateQuery();
            queryObj.setPatientId(patientId);
            queryObj.setPatientIdNo(auditLogParameterDTO.getPatientIdNo());
            queryObj.setWorkerCompensationIdList(workerCompensationIdList);
            queryObj.setUserId(auditLogParameterDTO.getUserId());
            queryObj.setFromDate(auditLogParameterDTO.getFromDate());
            queryObj.setToDate(auditLogParameterDTO.getToDate());

            Mono<List<AuditLogDTO>> auditLogDTO = workerCompensationAuditLogQueryHandler.
                getWorkerCompensationAuditTrailInfoByQueryHandler(queryObj)
                .map(x -> new AuditLogDTO(x.getId(), x.getWorersComenationId(), x.getOldRowData(), x.getNewRowData(),
                    x.getDmlType(), x.getDmlTimestamp(), x.getDmlCreatedBy(), "Patient Document"))
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
