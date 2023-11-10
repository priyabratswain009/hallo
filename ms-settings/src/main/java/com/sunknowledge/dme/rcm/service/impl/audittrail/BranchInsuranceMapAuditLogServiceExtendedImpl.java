package com.sunknowledge.dme.rcm.service.impl.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditTrailUtil;
import com.sunknowledge.dme.rcm.service.audittrail.BranchInsuranceMapAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.BranchInsuranceMapAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.branch.BranchOfficeServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.BranchInsuranceMapAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.BranchInsuranceMapDTO;
import com.sunknowledge.dme.rcm.service.dto.BranchOfficeDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.BranchNoAuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.BranchInsuranceMapAuditLogByBranchInsuranceIdAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.insurance.BranchInsuranceMapServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Primary
@Service
public class BranchInsuranceMapAuditLogServiceExtendedImpl implements BranchInsuranceMapAuditLogServiceExtended {

    @Autowired
    BranchInsuranceMapAuditLogQueryHandler branchInsuranceMapAuditLogQueryHandler;
    @Autowired
    BranchOfficeServiceExtended branchOfficeServiceExtended;
    @Autowired
    BranchInsuranceMapServiceExtended branchInsuranceMapServiceExtended;

    @Override
    public List<AuditLogReportDTO> getBranchInsuranceMapAuditLog(BranchNoAuditLogParameterDTO auditLogParameterDTO) {
        List<AuditLogReportDTO> auditLogReportDTOList = new ArrayList<>();
        try {

            Long branchId = branchOfficeServiceExtended.
                getBranchOfficeByBranchNo(String.valueOf(auditLogParameterDTO.getBranchNo()))
                .stream().
                max(Comparator.comparing(BranchOfficeDTO::getBranchId))
                .orElseThrow(NoSuchElementException::new).getBranchId();

            List<Long> branchInsuranceMapIdList = branchInsuranceMapServiceExtended.
                getBranchInsuranceMapByBranchId(branchId).stream().map(BranchInsuranceMapDTO::getBranchInsuranceMapId).collect(Collectors.toList());


            BranchInsuranceMapAuditLogByBranchInsuranceIdAndUserIdAndDateQuery queryObj = new BranchInsuranceMapAuditLogByBranchInsuranceIdAndUserIdAndDateQuery();
            queryObj.setBranchInsuranceMapIdList(branchInsuranceMapIdList);
            queryObj.setUserId(auditLogParameterDTO.getUserId());
            queryObj.setFromDate(auditLogParameterDTO.getFromDate());
            queryObj.setToDate(auditLogParameterDTO.getToDate());

            List<AuditLogDTO> auditLogDTO = branchInsuranceMapAuditLogQueryHandler.
                getBranchInsuranceMapAuditTrailInfoByQueryHandler(queryObj).stream()
                .map(x -> new AuditLogDTO(x.getId(), x.getBrachInsanceMapId(), x.getOldRowData(), x.getNewRowData(),
                    x.getDmlType(), x.getDmlTimestamp(), x.getDmlCreatedBy(), "Branch Insurance Map"))
                .collect(Collectors.toList());


            Map insertKeys = new HashMap();
            insertKeys.put("idKey", "branch_insurance_map_id");
            insertKeys.put("noValue", auditLogParameterDTO.getBranchNo());
            insertKeys.put("userIdKey", "created_by_id");
            insertKeys.put("userNameKey", "created_by_name");
            insertKeys.put("dateTimeKey", "created_date");

            Map updateKeys = new HashMap();
            updateKeys.put("idKey", "branch_insurance_map_id");
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

    @Override
    public BranchInsuranceMapAuditLogDTO save(BranchInsuranceMapAuditLogDTO branchInsuranceMapAuditLogDTO) {
        return null;
    }

    @Override
    public BranchInsuranceMapAuditLogDTO update(BranchInsuranceMapAuditLogDTO branchInsuranceMapAuditLogDTO) {
        return null;
    }

    @Override
    public Optional<BranchInsuranceMapAuditLogDTO> partialUpdate(BranchInsuranceMapAuditLogDTO branchInsuranceMapAuditLogDTO) {
        return Optional.empty();
    }

    @Override
    public Page<BranchInsuranceMapAuditLogDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<BranchInsuranceMapAuditLogDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }


}
