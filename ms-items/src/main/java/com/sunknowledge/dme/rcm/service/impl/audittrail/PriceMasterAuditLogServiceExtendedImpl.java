package com.sunknowledge.dme.rcm.service.impl.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditTrailUtil;
import com.sunknowledge.dme.rcm.service.audittrail.PriceMasterAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.query.PriceMasterAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.PriceDetailsDTO;
import com.sunknowledge.dme.rcm.service.dto.PriceMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ItemNoAuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.PriceTableIdAuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PriceDetailsAuditLogByPriceDetailsIdAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PriceMasterAuditLogByPriceTableIdAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.dto.items.ItemMasterOutputDTO;
import com.sunknowledge.dme.rcm.service.items.ItemMasterServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Primary
@Service("priceMasterAuditLogServiceExtendedImpl")
public class PriceMasterAuditLogServiceExtendedImpl implements PriceMasterAuditLogServiceExtended {

    @Autowired
    PriceMasterAuditLogQueryHandler priceMasterAuditLogQueryHandler;
    @Override
    public PriceMasterAuditLogDTO save(PriceMasterAuditLogDTO priceMasterAuditLogDTO) {
        return null;
    }

    @Override
    public PriceMasterAuditLogDTO update(PriceMasterAuditLogDTO priceMasterAuditLogDTO) {
        return null;
    }

    @Override
    public Optional<PriceMasterAuditLogDTO> partialUpdate(PriceMasterAuditLogDTO priceMasterAuditLogDTO) {
        return Optional.empty();
    }

    @Override
    public Page<PriceMasterAuditLogDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<PriceMasterAuditLogDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }


    @Override
    public List<AuditLogReportDTO> getPriceMasterAuditLog(PriceTableIdAuditLogParameterDTO auditLogParameterDTO) {
        List<AuditLogReportDTO> auditLogReportDTOList = new ArrayList<>();
        try {
            PriceMasterAuditLogByPriceTableIdAndUserIdAndDateQuery queryObj =
                new PriceMasterAuditLogByPriceTableIdAndUserIdAndDateQuery();
            queryObj.setPriceTableId(auditLogParameterDTO.getPriceTableId());
            queryObj.setUserID(auditLogParameterDTO.getUserId());
            queryObj.setFromDate(auditLogParameterDTO.getFromDate());
            queryObj.setToDate(auditLogParameterDTO.getToDate());

            List<AuditLogDTO> auditLogDTO = priceMasterAuditLogQueryHandler.
                getPriceMasterAuditTrailInfoByQueryHandler(queryObj).stream()
                .map(x -> new AuditLogDTO(x.getId(), x.getPrceTbleId(), x.getOldRowData(), x.getNewRowData(),
                    x.getDmlType(), x.getDmlTimestamp(), x.getDmlCreatedBy(), "Price Master"))
                .collect(Collectors.toList());

            Map insertKeys = new HashMap();
            insertKeys.put("idKey", "price_table_id");
            insertKeys.put("noValue", auditLogParameterDTO.getPriceTableId());
            insertKeys.put("userIdKey", "created_by_id");
            insertKeys.put("userNameKey", "created_by_name");
            insertKeys.put("dateTimeKey", "created_date");

            Map updateKeys = new HashMap();
            updateKeys.put("idKey", "price_table_id");
            updateKeys.put("noValue", auditLogParameterDTO.getPriceTableId());
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
