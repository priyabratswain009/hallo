package com.sunknowledge.dme.rcm.service.impl.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditTrailUtil;
import com.sunknowledge.dme.rcm.service.audittrail.PurchaseOrderAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.query.PurchaseOrderAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.PurchaseOrderIdAuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PurchaseOrderAuditLogByItemNoAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.po.PurchaseOrderServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Primary
@Service("purchaseOrderAuditLogServiceExtendedImpl")
public class PurchaseOrderAuditLogServiceExtendedImpl implements PurchaseOrderAuditLogServiceExtended {

    @Autowired
    PurchaseOrderAuditLogQueryHandler purchaseOrderAuditLogQueryHandler;
    @Autowired
    PurchaseOrderServiceExtended purchaseOrderServiceExtended;

    @Override
    public PurchaseOrderAuditLogDTO save(PurchaseOrderAuditLogDTO purchaseOrderAuditLogDTO) {
        return null;
    }

    @Override
    public PurchaseOrderAuditLogDTO update(PurchaseOrderAuditLogDTO purchaseOrderAuditLogDTO) {
        return null;
    }

    @Override
    public Optional<PurchaseOrderAuditLogDTO> partialUpdate(PurchaseOrderAuditLogDTO purchaseOrderAuditLogDTO) {
        return Optional.empty();
    }

    @Override
    public Page<PurchaseOrderAuditLogDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<PurchaseOrderAuditLogDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }


    @Override
    public List<AuditLogReportDTO> getPurchaseOrderAuditLog(PurchaseOrderIdAuditLogParameterDTO auditLogParameterDTO) {
        List<AuditLogReportDTO> auditLogReportDTOList = new ArrayList<>();
        try {
            Long poId = 1L;
//            purchaseOrderServiceExtended.
//                getPOByPONumber(String.valueOf(auditLogParameterDTO.getPoNumber()))
//                .stream().
//                max(Comparator.comparing(PurchaseOrderDTO::getPoId))
//                .orElseThrow(NoSuchElementException::new).getPoId();

            PurchaseOrderAuditLogByItemNoAndUserIdAndDateQuery queryObj =
                new PurchaseOrderAuditLogByItemNoAndUserIdAndDateQuery();
            queryObj.setPOId(poId);
            queryObj.setPONumber(auditLogParameterDTO.getPoNumber());
            queryObj.setUserID(auditLogParameterDTO.getUserId());
            queryObj.setFromDate(auditLogParameterDTO.getFromDate());
            queryObj.setToDate(auditLogParameterDTO.getToDate());

            List<AuditLogDTO> auditLogDTO = purchaseOrderAuditLogQueryHandler.
                getPurchaseOrderAuditTrailInfoByQueryHandler(queryObj).stream()
                .map(x -> new AuditLogDTO(x.getId(), x.getpId(), x.getOldRowData(), x.getNewRowData(),
                    x.getDmlType(), x.getDmlTimestamp(), x.getDmlCreatedBy(), "Purchase Order"))
                .collect(Collectors.toList());

            Map insertKeys = new HashMap();
            insertKeys.put("idKey", "po_id");
            insertKeys.put("noValue", auditLogParameterDTO.getPoNumber());
            insertKeys.put("userIdKey", "created_by_id");
            insertKeys.put("userNameKey", "created_by_name");
            insertKeys.put("dateTimeKey", "created_date");

            Map updateKeys = new HashMap();
            updateKeys.put("idKey", "po_id");
            updateKeys.put("noValue", auditLogParameterDTO.getPoNumber());
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
