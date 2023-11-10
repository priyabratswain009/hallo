package com.sunknowledge.dme.rcm.service.impl.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditTrailUtil;
import com.sunknowledge.dme.rcm.service.audittrail.ItemVendorMappingAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.query.ItemVendorMappingAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.ItemSerialNumberDTO;
import com.sunknowledge.dme.rcm.service.dto.ItemVendorMappingAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.ItemVendorMappingDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ItemNoAuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ItemTypeIdAuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.ItemSerialNumberAuditLogByItemSerialNumberAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.ItemVendorMappingAuditLogByItemVendorIdAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.dto.items.ItemMasterOutputDTO;
import com.sunknowledge.dme.rcm.service.items.ItemMasterServiceExtended;
import com.sunknowledge.dme.rcm.service.items.ItemVendorMappingServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Primary
@Service("itemVendorMappingAuditLogServiceExtendedImpl")
public class ItemVendorMappingAuditLogServiceExtendedImpl implements ItemVendorMappingAuditLogServiceExtended {

    @Autowired
    ItemMasterServiceExtended itemMasterServiceExtended;
    @Autowired
    ItemVendorMappingAuditLogQueryHandler itemVendorMappingAuditLogQueryHandler;
    @Autowired
    ItemVendorMappingServiceExtended itemVendorMappingServiceExtended;

    @Override
    public ItemVendorMappingAuditLogDTO save(ItemVendorMappingAuditLogDTO itemVendorMappingAuditLogDTO) {
        return null;
    }

    @Override
    public ItemVendorMappingAuditLogDTO update(ItemVendorMappingAuditLogDTO itemVendorMappingAuditLogDTO) {
        return null;
    }

    @Override
    public Optional<ItemVendorMappingAuditLogDTO> partialUpdate(ItemVendorMappingAuditLogDTO itemVendorMappingAuditLogDTO) {
        return Optional.empty();
    }

    @Override
    public Page<ItemVendorMappingAuditLogDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<ItemVendorMappingAuditLogDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<AuditLogReportDTO> getItemVendorMappingAuditLog(ItemNoAuditLogParameterDTO auditLogParameterDTO) {
        List<AuditLogReportDTO> auditLogReportDTOList = new ArrayList<>();
        try {

            Long itemId = itemMasterServiceExtended.
                getItemByItemNo(String.valueOf(auditLogParameterDTO.getItemNo()))
                .stream().
                max(Comparator.comparing(ItemMasterOutputDTO::getItemId))
                .orElseThrow(NoSuchElementException::new).getItemId();

            List<Long> itemVendorIdList = itemVendorMappingServiceExtended.getItemVendorMapByItemId(itemId)
                .stream().map(ItemVendorMappingDTO::getItemVendorId).collect(Collectors.toList());

            ItemVendorMappingAuditLogByItemVendorIdAndUserIdAndDateQuery queryObj =
                new ItemVendorMappingAuditLogByItemVendorIdAndUserIdAndDateQuery();
            queryObj.setItemId(itemId);
            queryObj.setItemVendorIdList(itemVendorIdList);
            queryObj.setItemNo(auditLogParameterDTO.getItemNo());
            queryObj.setUserID(auditLogParameterDTO.getUserId());
            queryObj.setFromDate(auditLogParameterDTO.getFromDate());
            queryObj.setToDate(auditLogParameterDTO.getToDate());

            List<AuditLogDTO> auditLogDTO = itemVendorMappingAuditLogQueryHandler.
                getItemVendorMappingAuditTrailInfoByQueryHandler(queryObj).stream()
                .map(x -> new AuditLogDTO(x.getId(), x.getItmVndorId(), x.getOldRowData(), x.getNewRowData(),
                    x.getDmlType(), x.getDmlTimestamp(), x.getDmlCreatedBy(), "Item Vendor Mapping"))
                .collect(Collectors.toList());

            Map insertKeys = new HashMap();
            insertKeys.put("idKey", "item_vendor_id");
            insertKeys.put("noValue", auditLogParameterDTO.getItemNo());
            insertKeys.put("userIdKey", "created_by_id");
            insertKeys.put("userNameKey", "created_by_name");
            insertKeys.put("dateTimeKey", "created_date");

            Map updateKeys = new HashMap();
            updateKeys.put("idKey", "item_vendor_id");
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
