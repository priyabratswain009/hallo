package com.sunknowledge.dme.rcm.service.impl.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditTrailUtil;
import com.sunknowledge.dme.rcm.service.audittrail.PriceDetailsAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.query.PriceDetailsAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.ItemSerialNumberDTO;
import com.sunknowledge.dme.rcm.service.dto.PriceDetailsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.PriceDetailsDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ItemNoAuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.ItemSerialNumberAuditLogByItemSerialNumberAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PriceDetailsAuditLogByPriceDetailsIdAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.dto.items.ItemMasterOutputDTO;
import com.sunknowledge.dme.rcm.service.items.ItemMasterServiceExtended;
import com.sunknowledge.dme.rcm.service.pricetable.PriceDetailsServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Primary
@Service("priceDetailsAuditLogServiceExtendedImpl")
public class PriceDetailsAuditLogServiceExtendedImpl implements PriceDetailsAuditLogServiceExtended {
    @Autowired
    ItemMasterServiceExtended itemMasterServiceExtended;
    @Autowired
    PriceDetailsServiceExtended priceDetailsServiceExtended;
    @Autowired
    PriceDetailsAuditLogQueryHandler priceDetailsAuditLogQueryHandler;

    @Override
    public PriceDetailsAuditLogDTO save(PriceDetailsAuditLogDTO priceDetailsAuditLogDTO) {
        return null;
    }

    @Override
    public PriceDetailsAuditLogDTO update(PriceDetailsAuditLogDTO priceDetailsAuditLogDTO) {
        return null;
    }

    @Override
    public Optional<PriceDetailsAuditLogDTO> partialUpdate(PriceDetailsAuditLogDTO priceDetailsAuditLogDTO) {
        return Optional.empty();
    }

    @Override
    public Page<PriceDetailsAuditLogDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<PriceDetailsAuditLogDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<AuditLogReportDTO> getPriceDetailsAuditLog(ItemNoAuditLogParameterDTO auditLogParameterDTO) {
        List<AuditLogReportDTO> auditLogReportDTOList = new ArrayList<>();
        try {

            Long itemId = itemMasterServiceExtended.
                getItemByItemNo(String.valueOf(auditLogParameterDTO.getItemNo()))
                .stream().
                max(Comparator.comparing(ItemMasterOutputDTO::getItemId))
                .orElseThrow(NoSuchElementException::new).getItemId();

            List<Long> priceDetailsIdList = priceDetailsServiceExtended.getPriceDetailsByItemId(itemId)
                .stream().map(PriceDetailsDTO::getPriceDetailsId).collect(Collectors.toList());

            PriceDetailsAuditLogByPriceDetailsIdAndUserIdAndDateQuery queryObj =
                new PriceDetailsAuditLogByPriceDetailsIdAndUserIdAndDateQuery();
            queryObj.setItemId(itemId);
            queryObj.setPriceDetailsIdList(priceDetailsIdList);
            queryObj.setItemNo(auditLogParameterDTO.getItemNo());
            queryObj.setUserID(auditLogParameterDTO.getUserId());
            queryObj.setFromDate(auditLogParameterDTO.getFromDate());
            queryObj.setToDate(auditLogParameterDTO.getToDate());

            List<AuditLogDTO> auditLogDTO = priceDetailsAuditLogQueryHandler.
                getPriceDetailsAuditTrailInfoByQueryHandler(queryObj).stream()
                .map(x -> new AuditLogDTO(x.getId(), x.getPrceDetlId(), x.getOldRowData(), x.getNewRowData(),
                    x.getDmlType(), x.getDmlTimestamp(), x.getDmlCreatedBy(), "Price Details"))
                .collect(Collectors.toList());

            Map insertKeys = new HashMap();
            insertKeys.put("idKey", "price_details_id");
            insertKeys.put("noValue", auditLogParameterDTO.getItemNo());
            insertKeys.put("userIdKey", "created_by_id");
            insertKeys.put("userNameKey", "created_by_name");
            insertKeys.put("dateTimeKey", "created_date");

            Map updateKeys = new HashMap();
            updateKeys.put("idKey", "price_details_id");
            updateKeys.put("noValue", auditLogParameterDTO.getItemNo());
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
