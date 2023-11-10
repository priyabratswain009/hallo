package com.sunknowledge.dme.rcm.service.impl.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditTrailUtil;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.InsuranceMasterAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.InsuranceMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.InsuranceMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.InsuranceMasterAuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.audittrail.InsuranceMasterAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.InsuranceMasterAuditLogByInsuranceIdNoAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.insurance.InsuranceMasterServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Primary
@Service("insuranceMasterAuditLogServiceExtendedImpl")
public class InsuranceMasterAuditLogServiceExtendedImpl implements InsuranceMasterAuditLogServiceExtended {

    @Autowired
    InsuranceMasterServiceExtended insuranceMasterServiceExtended;
    @Autowired
    InsuranceMasterAuditLogQueryHandler insuranceMasterAuditLogQueryHandler;

    @Override
    public List<AuditLogReportDTO> getInsuranceMasterAuditLog(InsuranceMasterAuditLogParameterDTO insuranceMasterAuditLogParameterDTO) {
        List<AuditLogReportDTO> auditLogReportDTOList = new ArrayList<>();
        try {
            Long insuranceId = insuranceMasterServiceExtended.
                getInsuranceMasterByInsuranceIdNo(insuranceMasterAuditLogParameterDTO.getInsuranceIdNo())
                .stream().max(Comparator.comparing(InsuranceMasterDTO::getInsuranceId))
                .orElseThrow(NoSuchElementException::new).getInsuranceId();

            InsuranceMasterAuditLogByInsuranceIdNoAndUserIdAndDateQuery queryObj = new InsuranceMasterAuditLogByInsuranceIdNoAndUserIdAndDateQuery();
            queryObj.setInsuranceId(insuranceId);
            queryObj.setInsuranceIdNo(insuranceMasterAuditLogParameterDTO.getInsuranceIdNo());
            queryObj.setUserId(insuranceMasterAuditLogParameterDTO.getUserId());
            queryObj.setFromDate(insuranceMasterAuditLogParameterDTO.getFromDate());
            queryObj.setToDate(insuranceMasterAuditLogParameterDTO.getToDate());

            List<AuditLogDTO> auditLogDTO = insuranceMasterAuditLogQueryHandler.
                getInsuranceMasterAuditTrailInfoByQueryHandler(queryObj).stream()
                .map(x -> new AuditLogDTO(x.getId(), x.getInsrnceId(), x.getOldRowData(), x.getNewRowData(),
                    x.getDmlType(), x.getDmlTimestamp(), x.getDmlCreatedBy(), "Insurance Master"))
                .collect(Collectors.toList());


            Map insertKeys = new HashMap();
            insertKeys.put("idKey", "insurance_id");
            insertKeys.put("noValue", insuranceMasterAuditLogParameterDTO.getInsuranceIdNo());
            insertKeys.put("userIdKey", "created_by_id");
            insertKeys.put("userNameKey", "created_by_name");
            insertKeys.put("dateTimeKey", "created_date");

            Map updateKeys = new HashMap();
            updateKeys.put("idKey", "insurance_id");
            updateKeys.put("noValue", insuranceMasterAuditLogParameterDTO.getInsuranceIdNo());
            updateKeys.put("userIdKey", "updated_by_id");
            updateKeys.put("userNameKey", "updated_by_name");
            updateKeys.put("dateTimeKey", "updated_date");

            auditLogReportDTOList = AuditTrailUtil.generateReportJSON(auditLogDTO,
                insertKeys, updateKeys);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        return Mono.just(auditLogReportDTOList).flatMapMany(Flux::fromIterable)
//            .log();

        return auditLogReportDTOList;
    }

    @Override
    public InsuranceMasterAuditLogDTO save(InsuranceMasterAuditLogDTO insuranceMasterAuditLogDTO) {
        return null;
    }

    @Override
    public InsuranceMasterAuditLogDTO update(InsuranceMasterAuditLogDTO insuranceMasterAuditLogDTO) {
        return null;
    }

    @Override
    public Optional<InsuranceMasterAuditLogDTO> partialUpdate(InsuranceMasterAuditLogDTO insuranceMasterAuditLogDTO) {
        return Optional.empty();
    }

    @Override
    public Page<InsuranceMasterAuditLogDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<InsuranceMasterAuditLogDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }


}
