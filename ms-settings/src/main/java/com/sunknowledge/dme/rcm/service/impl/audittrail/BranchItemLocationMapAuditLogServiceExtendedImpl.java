package com.sunknowledge.dme.rcm.service.impl.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditTrailUtil;
import com.sunknowledge.dme.rcm.service.audittrail.BranchItemLocationMapAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.BranchItemLocationMapAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.branch.BranchOfficeServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.BranchItemLocationMapAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.BranchItemLocationMapDTO;
import com.sunknowledge.dme.rcm.service.dto.BranchOfficeDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.BranchNoAuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.BranchItemLocationMapAuditLogByBranchItemLocationIdAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.others.BranchItemLocationMapServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Primary
@Service("branchItemLocationMapAuditLogServiceExtendedImpl")
public class BranchItemLocationMapAuditLogServiceExtendedImpl implements BranchItemLocationMapAuditLogServiceExtended {
    @Override
    public BranchItemLocationMapAuditLogDTO save(BranchItemLocationMapAuditLogDTO branchItemLocationMapAuditLogDTO) {
        return null;
    }

    @Override
    public BranchItemLocationMapAuditLogDTO update(BranchItemLocationMapAuditLogDTO branchItemLocationMapAuditLogDTO) {
        return null;
    }

    @Override
    public Optional<BranchItemLocationMapAuditLogDTO> partialUpdate(BranchItemLocationMapAuditLogDTO branchItemLocationMapAuditLogDTO) {
        return Optional.empty();
    }

    @Override
    public Page<BranchItemLocationMapAuditLogDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<BranchItemLocationMapAuditLogDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Autowired
    BranchItemLocationMapAuditLogQueryHandler branchItemLocationMapAuditLogQueryHandler;
    @Autowired
    BranchOfficeServiceExtended branchOfficeServiceExtended;
    @Autowired
    BranchItemLocationMapServiceExtended branchItemLocationMapServiceExtended;

    @Override
    public List<AuditLogReportDTO> getBranchItemLocationMapAuditLog(BranchNoAuditLogParameterDTO auditLogParameterDTO) {
        List<AuditLogReportDTO> auditLogReportDTOList = new ArrayList<>();
        try {

            Long branchId = branchOfficeServiceExtended.
                getBranchOfficeByBranchNo(String.valueOf(auditLogParameterDTO.getBranchNo()))
                .stream().
                max(Comparator.comparing(BranchOfficeDTO::getBranchId))
                .orElseThrow(NoSuchElementException::new).getBranchId();

            List<Long> branchItemLocationMapIdList = branchItemLocationMapServiceExtended.
                getBranchItemLocationMapByBranchId(branchId).stream().map(BranchItemLocationMapDTO::getBranchItemLocationMapId).collect(Collectors.toList());


            BranchItemLocationMapAuditLogByBranchItemLocationIdAndUserIdAndDateQuery queryObj =
                new BranchItemLocationMapAuditLogByBranchItemLocationIdAndUserIdAndDateQuery();
            queryObj.setBranchItemLocationMapIdList(branchItemLocationMapIdList);
            queryObj.setUserId(auditLogParameterDTO.getUserId());
            queryObj.setFromDate(auditLogParameterDTO.getFromDate());
            queryObj.setToDate(auditLogParameterDTO.getToDate());

            List<AuditLogDTO> auditLogDTO = branchItemLocationMapAuditLogQueryHandler.
                getBranchItemLocationMapAuditTrailInfoByQueryHandler(queryObj).stream()
                .map(x -> new AuditLogDTO(x.getId(), x.getBrnchItmLoctinMapId(), x.getOldRowData(), x.getNewRowData(),
                    x.getDmlType(), x.getDmlTimestamp(), x.getDmlCreatedBy(), "Branch Item Location Map"))
                .collect(Collectors.toList());


            Map insertKeys = new HashMap();
            insertKeys.put("idKey", "branch_item_location_map_id");
            insertKeys.put("noValue", auditLogParameterDTO.getBranchNo());
            insertKeys.put("userIdKey", "created_by_id");
            insertKeys.put("userNameKey", "created_by_name");
            insertKeys.put("dateTimeKey", "created_date");

            Map updateKeys = new HashMap();
            updateKeys.put("idKey", "branch_item_location_map_id");
            updateKeys.put("noValue", auditLogParameterDTO.getBranchNo());
            updateKeys.put("userIdKey", "updated_by_id");
            updateKeys.put("userNameKey", "updated_by_name");
            updateKeys.put("dateTimeKey", "updated_date");

            auditLogReportDTOList = AuditTrailUtil.generateReportJSON(auditLogDTO,
                insertKeys, updateKeys);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return auditLogReportDTOList;
    }
}
