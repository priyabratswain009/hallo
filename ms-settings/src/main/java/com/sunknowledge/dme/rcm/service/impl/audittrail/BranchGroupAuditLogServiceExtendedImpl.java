package com.sunknowledge.dme.rcm.service.impl.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditTrailUtil;
import com.sunknowledge.dme.rcm.service.audittrail.BranchGroupAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.BranchGroupAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.branch.BranchOfficeServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.BranchGroupAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.BranchGroupAuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.BranchGroupAuditLogByBranchGroupIdAndUserIdAndDateQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Primary
@Service("branchGroupAuditLogServiceExtendedImpl")
public class BranchGroupAuditLogServiceExtendedImpl implements BranchGroupAuditLogServiceExtended {
    @Override
    public BranchGroupAuditLogDTO save(BranchGroupAuditLogDTO branchGroupAuditLogDTO) {
        return null;
    }

    @Override
    public BranchGroupAuditLogDTO update(BranchGroupAuditLogDTO branchGroupAuditLogDTO) {
        return null;
    }

    @Override
    public Optional<BranchGroupAuditLogDTO> partialUpdate(BranchGroupAuditLogDTO branchGroupAuditLogDTO) {
        return Optional.empty();
    }

    @Override
    public Page<BranchGroupAuditLogDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<BranchGroupAuditLogDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Autowired
    BranchGroupAuditLogQueryHandler branchGroupAuditLogQueryHandler;
    @Autowired
    BranchOfficeServiceExtended branchOfficeServiceExtended;
    @Override
    public List<AuditLogReportDTO> getBranchGroupAuditLog(BranchGroupAuditLogParameterDTO auditLogParameterDTO) {
        List<AuditLogReportDTO> auditLogReportDTOList = new ArrayList<>();
        try {
            BranchGroupAuditLogByBranchGroupIdAndUserIdAndDateQuery queryObj = new BranchGroupAuditLogByBranchGroupIdAndUserIdAndDateQuery();
            queryObj.setBranchGroupId(auditLogParameterDTO.getBranchGroupId());
            queryObj.setUserId(auditLogParameterDTO.getUserId());
            queryObj.setFromDate(auditLogParameterDTO.getFromDate());
            queryObj.setToDate(auditLogParameterDTO.getToDate());

            List<AuditLogDTO> auditLogDTO = branchGroupAuditLogQueryHandler.
                getBranchGroupAuditTrailInfoByQueryHandler(queryObj).stream()
                .map(x -> new AuditLogDTO(x.getId(), x.getBrnchGrpId(), x.getOldRowData(), x.getNewRowData(),
                    x.getDmlType(), x.getDmlTimestamp(), x.getDmlCreatedBy(), "Branch Group"))
                .collect(Collectors.toList());


            Map insertKeys = new HashMap();
            insertKeys.put("idKey", "branch_group_id");
            insertKeys.put("noValue", auditLogParameterDTO.getBranchGroupId());
            insertKeys.put("userIdKey", "created_by_id");
            insertKeys.put("userNameKey", "created_by_name");
            insertKeys.put("dateTimeKey", "created_date");

            Map updateKeys = new HashMap();
            updateKeys.put("idKey", "branch_group_id");
            updateKeys.put("noValue", auditLogParameterDTO.getBranchGroupId());
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
}
