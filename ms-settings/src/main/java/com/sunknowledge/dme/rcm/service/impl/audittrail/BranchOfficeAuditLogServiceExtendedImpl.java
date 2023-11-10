package com.sunknowledge.dme.rcm.service.impl.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditTrailUtil;
import com.sunknowledge.dme.rcm.service.audittrail.BranchInsuranceMapAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.BranchItemLocationMapAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.BranchOfficeAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.BranchOfficeAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.branch.BranchOfficeServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.BranchOfficeAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.BranchOfficeDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.BranchNoAuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.BranchOfficeAuditLogByBranchNoAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.insurance.BranchInsuranceMapServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Primary
@Service("branchOfficeAuditLogServiceExtendedImpl")
public class BranchOfficeAuditLogServiceExtendedImpl implements BranchOfficeAuditLogServiceExtended {

    @Autowired
    BranchOfficeAuditLogQueryHandler branchOfficeAuditLogQueryHandler;
    @Autowired
    BranchOfficeServiceExtended branchOfficeServiceExtended;
    @Autowired
    BranchInsuranceMapServiceExtended branchInsuranceMapServiceExtended;

    @Override
    public List<AuditLogReportDTO> getBranchOfficeAuditLog(BranchNoAuditLogParameterDTO auditLogParameterDTO) {
        List<AuditLogReportDTO> auditLogReportDTOList = new ArrayList<>();
        try {

            Long branchId = branchOfficeServiceExtended.
                getBranchOfficeByBranchNo(String.valueOf(auditLogParameterDTO.getBranchNo()))
                .stream().
                max(Comparator.comparing(BranchOfficeDTO::getBranchId))
                .orElseThrow(NoSuchElementException::new).getBranchId();

            BranchOfficeAuditLogByBranchNoAndUserIdAndDateQuery queryObj = new BranchOfficeAuditLogByBranchNoAndUserIdAndDateQuery();
            queryObj.setBranchId(branchId);
            queryObj.setUserId(auditLogParameterDTO.getUserId());
            queryObj.setFromDate(auditLogParameterDTO.getFromDate());
            queryObj.setToDate(auditLogParameterDTO.getToDate());

            List<AuditLogDTO> auditLogDTO = branchOfficeAuditLogQueryHandler.
                getBranchOfficeAuditTrailInfoByQueryHandler(queryObj).stream()
                .map(x -> new AuditLogDTO(x.getId(), x.getBrnchId(), x.getOldRowData(), x.getNewRowData(),
                    x.getDmlType(), x.getDmlTimestamp(), x.getDmlCreatedBy(), "Branch Office"))
                .collect(Collectors.toList());


            Map insertKeys = new HashMap();
            insertKeys.put("idKey", "branch_id");
            insertKeys.put("noValue", auditLogParameterDTO.getBranchNo());
            insertKeys.put("userIdKey", "created_by_id");
            insertKeys.put("userNameKey", "created_by_name");
            insertKeys.put("dateTimeKey", "created_date");

            Map updateKeys = new HashMap();
            updateKeys.put("idKey", "branch_id");
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

    @Autowired
    BranchInsuranceMapAuditLogServiceExtended branchInsuranceMapAuditLogServiceExtended;
    @Autowired
    BranchItemLocationMapAuditLogServiceExtended branchItemLocationMapAuditLogServiceExtended;

    @Override
    public List<AuditLogReportDTO> getOverallBranchAuditLog(BranchNoAuditLogParameterDTO auditLogParameterDTO) {
        List<AuditLogReportDTO> overallData = generateOverallData(getBranchOfficeAuditLog(auditLogParameterDTO),
            branchInsuranceMapAuditLogServiceExtended.getBranchInsuranceMapAuditLog(auditLogParameterDTO),
            branchItemLocationMapAuditLogServiceExtended.getBranchItemLocationMapAuditLog(auditLogParameterDTO)
        );

        return overallData;
    }

    private List<AuditLogReportDTO> generateOverallData(List<AuditLogReportDTO> branchOfficeAuditLog,
                                                        List<AuditLogReportDTO> branchInsuranceMapAuditLog,
                                                        List<AuditLogReportDTO> branchItemLocationMapAuditLog) {

        List<AuditLogReportDTO> overallData = new ArrayList<>();
        overallData.addAll(branchOfficeAuditLog);
        overallData.addAll(branchInsuranceMapAuditLog);
        overallData.addAll(branchItemLocationMapAuditLog);

        return overallData;
    }

    @Override
    public BranchOfficeAuditLogDTO save(BranchOfficeAuditLogDTO branchOfficeAuditLogDTO) {
        return null;
    }

    @Override
    public BranchOfficeAuditLogDTO update(BranchOfficeAuditLogDTO branchOfficeAuditLogDTO) {
        return null;
    }

    @Override
    public Optional<BranchOfficeAuditLogDTO> partialUpdate(BranchOfficeAuditLogDTO branchOfficeAuditLogDTO) {
        return Optional.empty();
    }

    @Override
    public Page<BranchOfficeAuditLogDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<BranchOfficeAuditLogDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }


}
