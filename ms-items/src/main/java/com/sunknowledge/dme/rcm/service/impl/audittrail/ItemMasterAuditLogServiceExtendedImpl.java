package com.sunknowledge.dme.rcm.service.impl.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditTrailUtil;
import com.sunknowledge.dme.rcm.service.audittrail.*;
import com.sunknowledge.dme.rcm.service.audittrail.query.ItemMasterAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.ItemMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ItemNoAuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.ItemMasterAuditLogByItemNoAndUserIdAndDateQuery;
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
@Service("itemMasterAuditLogServiceExtendedImpl")
public class ItemMasterAuditLogServiceExtendedImpl implements ItemMasterAuditLogServiceExtended {
    @Override
    public ItemMasterAuditLogDTO save(ItemMasterAuditLogDTO itemMasterAuditLogDTO) {
        return null;
    }

    @Override
    public ItemMasterAuditLogDTO update(ItemMasterAuditLogDTO itemMasterAuditLogDTO) {
        return null;
    }

    @Override
    public Optional<ItemMasterAuditLogDTO> partialUpdate(ItemMasterAuditLogDTO itemMasterAuditLogDTO) {
        return Optional.empty();
    }

    @Override
    public Page<ItemMasterAuditLogDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<ItemMasterAuditLogDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Autowired
    ItemMasterServiceExtended itemMasterServiceExtended;
    @Autowired
    ItemMasterAuditLogQueryHandler itemMasterAuditLogQueryHandler;
    @Autowired
    ItemInventoryStatusAuditLogServiceExtended itemInventoryStatusAuditLogServiceExtended;
    @Autowired
    ItemProcedureCodeMapAuditLogServiceExtended itemProcedureCodeMapAuditLogServiceExtended;
    @Autowired
    ItemSerialNumberAuditLogServiceExtended itemSerialNumberAuditLogServiceExtended;
    @Autowired
    ItemVendorMappingAuditLogServiceExtended itemVendorMappingAuditLogServiceExtended;
    @Autowired
    PriceDetailsAuditLogServiceExtended priceDetailsAuditLogServiceExtended;
    @Autowired
    PurchaseOrderItemsAuditLogServiceExtended purchaseOrderItemsAuditLogServiceExtended;
    @Autowired
    PurchaseOrderItemsReceivedAuditLogServiceExtended purchaseOrderItemsReceivedAuditLogServiceExtended;
    @Autowired
    StockAdjustmentAuditLogServiceExtended stockAdjustmentAuditLogServiceExtended;
    @Autowired
    StockTransferAuditLogServiceExtended stockTransferAuditLogServiceExtended;

    @Override
    public List<AuditLogReportDTO> getItemMasterAuditLog(ItemNoAuditLogParameterDTO auditLogParameterDTO) {
        List<AuditLogReportDTO> auditLogReportDTOList = new ArrayList<>();
        try {

            Long itemId = itemMasterServiceExtended.
                getItemByItemNo(String.valueOf(auditLogParameterDTO.getItemNo()))
                .stream().
                max(Comparator.comparing(ItemMasterOutputDTO::getItemId))
                .orElseThrow(NoSuchElementException::new).getItemId();

            ItemMasterAuditLogByItemNoAndUserIdAndDateQuery queryObj =
                new ItemMasterAuditLogByItemNoAndUserIdAndDateQuery();
            queryObj.setItemId(itemId);
            queryObj.setItemNo(auditLogParameterDTO.getItemNo());
            queryObj.setUserID(auditLogParameterDTO.getUserId());
            queryObj.setFromDate(auditLogParameterDTO.getFromDate());
            queryObj.setToDate(auditLogParameterDTO.getToDate());

            List<AuditLogDTO> auditLogDTO = itemMasterAuditLogQueryHandler.
                getItemMasterAuditTrailInfoByQueryHandler(queryObj).stream()
                .map(x -> new AuditLogDTO(x.getId(), x.getItmId(), x.getOldRowData(), x.getNewRowData(),
                    x.getDmlType(), x.getDmlTimestamp(), x.getDmlCreatedBy(), "Item Master"))
                .collect(Collectors.toList());


            Map insertKeys = new HashMap();
            insertKeys.put("idKey", "item_id");
            insertKeys.put("noValue", auditLogParameterDTO.getItemNo());
            insertKeys.put("userIdKey", "created_by_id");
            insertKeys.put("userNameKey", "created_by_name");
            insertKeys.put("dateTimeKey", "created_date");

            Map updateKeys = new HashMap();
            updateKeys.put("idKey", "item_id");
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

    @Override
    public List<AuditLogReportDTO> getOverallItemAuditLog(ItemNoAuditLogParameterDTO auditLogParameterDTO) {
        List<AuditLogReportDTO> overallData = generateOverallData(getItemMasterAuditLog(auditLogParameterDTO),
            itemInventoryStatusAuditLogServiceExtended.getItemInventoryStatusAuditLog(auditLogParameterDTO),
            itemProcedureCodeMapAuditLogServiceExtended.getItemProcedureCodeMapAuditLog(auditLogParameterDTO),
            itemSerialNumberAuditLogServiceExtended.getItemSerialNumberAuditLog(auditLogParameterDTO),
            itemVendorMappingAuditLogServiceExtended.getItemVendorMappingAuditLog(auditLogParameterDTO),
            priceDetailsAuditLogServiceExtended.getPriceDetailsAuditLog(auditLogParameterDTO),
            purchaseOrderItemsAuditLogServiceExtended.getPurchaseOrderItemsAuditLog(auditLogParameterDTO),
            purchaseOrderItemsReceivedAuditLogServiceExtended.getPurchaseOrderItemsReceivedAuditLog(auditLogParameterDTO),
            stockAdjustmentAuditLogServiceExtended.getStockAdjustmentAuditLog(auditLogParameterDTO),
            stockTransferAuditLogServiceExtended.getStockTransferAuditLog(auditLogParameterDTO)
        );

        return overallData;
    }

    private List<AuditLogReportDTO> generateOverallData(List<AuditLogReportDTO> itemMasterAuditLog,
                                                        List<AuditLogReportDTO> itemInventoryStatusAuditLog,
                                                        List<AuditLogReportDTO> itemProcedureCodeMapAuditLog,
                                                        List<AuditLogReportDTO> itemSerialNumberAuditLog,
                                                        List<AuditLogReportDTO> itemVendorMappingAuditLog,
                                                        List<AuditLogReportDTO> priceDetailsAuditLog,
                                                        List<AuditLogReportDTO> purchaseOrderItemsAuditLog,
                                                        List<AuditLogReportDTO> purchaseOrderItemsReceivedAuditLog,
                                                        List<AuditLogReportDTO> stockAdjustmentAuditLog,
                                                        List<AuditLogReportDTO> stockTransferAuditLog) {
        List<AuditLogReportDTO> overallData = new ArrayList<>();
        overallData.addAll(itemMasterAuditLog);
        overallData.addAll(itemInventoryStatusAuditLog);
        overallData.addAll(itemProcedureCodeMapAuditLog);
        overallData.addAll(itemSerialNumberAuditLog);
        overallData.addAll(itemVendorMappingAuditLog);
        overallData.addAll(priceDetailsAuditLog);
        overallData.addAll(purchaseOrderItemsAuditLog);
        overallData.addAll(purchaseOrderItemsReceivedAuditLog);
        overallData.addAll(stockAdjustmentAuditLog);
        overallData.addAll(stockTransferAuditLog);

        return overallData;
    }


}
