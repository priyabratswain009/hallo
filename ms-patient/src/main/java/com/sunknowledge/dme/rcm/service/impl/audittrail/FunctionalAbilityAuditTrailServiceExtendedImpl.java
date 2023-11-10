package com.sunknowledge.dme.rcm.service.impl.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditTrailUtil;
import com.sunknowledge.dme.rcm.service.audittrail.FunctionalAbilityAuditTrailServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.FunctionalAbilityAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.audittrail.FunctionalAbilityAuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.FunctionalAbilityAuditLogByFunctionalAbilityIdAndUserIdAndDateQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@Service("functionalAbilityAuditTrailServiceExtendedImpl")
public class FunctionalAbilityAuditTrailServiceExtendedImpl implements FunctionalAbilityAuditTrailServiceExtended {
    @Autowired
    FunctionalAbilityAuditLogQueryHandler functionalAbilityAuditLogQueryHandler;
    @Override
    public Flux<AuditLogReportDTO> getFunctionalAbilityAuditLog(FunctionalAbilityAuditLogParameterDTO auditLogParameterDTO) {
        List<AuditLogReportDTO> auditLogReportDTOList = new ArrayList<>();
        try {

            FunctionalAbilityAuditLogByFunctionalAbilityIdAndUserIdAndDateQuery queryObj =
                new FunctionalAbilityAuditLogByFunctionalAbilityIdAndUserIdAndDateQuery();
            queryObj.setFunctionalAbilityId(auditLogParameterDTO.getFunctionalAbilityId());
            queryObj.setUserId(auditLogParameterDTO.getUserId());
            queryObj.setFromDate(auditLogParameterDTO.getFromDate());
            queryObj.setToDate(auditLogParameterDTO.getToDate());

            Mono<List<AuditLogDTO>> auditLogDTO = functionalAbilityAuditLogQueryHandler.
                getFunctionalAbilityAuditTrailInfoByQueryHandler(queryObj)
                .map(x -> new AuditLogDTO(x.getId(), x.getFunalAbityId(), x.getOldRowData(), x.getNewRowData(),
                    x.getDmlType(), x.getDmlTimestamp(), x.getDmlCreatedBy(), "Functional Ability"))
                .collect(Collectors.toList());


            Map insertKeys = new HashMap();
            insertKeys.put("idKey", "functional_ability_id");
            insertKeys.put("noValue", auditLogParameterDTO.getFunctionalAbilityId());
            insertKeys.put("userIdKey", "created_by_id");
            insertKeys.put("userNameKey", "created_by_name");
            insertKeys.put("dateTimeKey", "created_date");

            Map updateKeys = new HashMap();
            updateKeys.put("idKey", "functional_ability_id");
            updateKeys.put("noValue", auditLogParameterDTO.getFunctionalAbilityId());
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
