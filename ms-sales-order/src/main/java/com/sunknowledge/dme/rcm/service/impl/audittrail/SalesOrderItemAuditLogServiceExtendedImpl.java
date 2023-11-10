package com.sunknowledge.dme.rcm.service.impl.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditTrailUtil;
import com.sunknowledge.dme.rcm.domain.SalesOrderItemDetails;
import com.sunknowledge.dme.rcm.repository.SalesOrderItemAuditLogRepositoryExtended;
import com.sunknowledge.dme.rcm.service.audittrail.SalesOrderItemAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.SalesOrderItemAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderItemDetailsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.AuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.SalesOrderItemAuditLogBySONoAndUserIdOrDateQuery;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderMasterSearchOutputDTO;
import com.sunknowledge.dme.rcm.service.soentryandsearch.SalesOrderItemDetailsServiceExtendedImpl;
import com.sunknowledge.dme.rcm.service.soentryandsearch.SalesOrderMasterSearchServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@Primary
@Service
public class SalesOrderItemAuditLogServiceExtendedImpl implements SalesOrderItemAuditLogServiceExtended {

    @Autowired
    SalesOrderItemAuditLogQueryHandler salesOrderItemAuditLogQueryHandler;

    @Autowired
    SalesOrderItemAuditLogRepositoryExtended salesOrderItemAuditLogRepositoryExtended;

    @Autowired
    SalesOrderItemDetailsServiceExtendedImpl salesOrderItemDetailsServiceExtended;
    @Autowired
    SalesOrderMasterSearchServiceExtended salesOrderMasterSearchServiceExtended;
    @Override
    public Flux<AuditLogReportDTO> getSOItemAuditTrailInfo(AuditLogParameterDTO auditLogParameterDTO) {
        List<AuditLogReportDTO> auditLogReportDTOList = new ArrayList<>();
        try {
            Long soId = salesOrderMasterSearchServiceExtended.
                getSODetailsBySalesOrderNumber(auditLogParameterDTO.getSalesOrderNo())
                .collectList().toFuture().get().stream().
                max(Comparator.comparing(SalesOrderMasterSearchOutputDTO::getSid))
                .orElseThrow(NoSuchElementException::new).getSid();

            List<Long> salesOrderItemIDList = salesOrderItemDetailsServiceExtended.
                findBySalesOrderId(soId).collectList().toFuture().get().stream().map(SalesOrderItemDetails::getSalesOrderItemDetailsId).collect(Collectors.toList());

            SalesOrderItemAuditLogBySONoAndUserIdOrDateQuery queryObj = new SalesOrderItemAuditLogBySONoAndUserIdOrDateQuery();
            queryObj.setSoID(soId);
            queryObj.setSoItemID(salesOrderItemIDList);
            queryObj.setSoNo(auditLogParameterDTO.getSalesOrderNo());
            queryObj.setUserID(auditLogParameterDTO.getUserId());
            queryObj.setFromDate(auditLogParameterDTO.getFromDate());
            queryObj.setToDate(auditLogParameterDTO.getToDate());

            List<AuditLogDTO> auditLogDTO = salesOrderItemAuditLogQueryHandler.
                getSOItemAuditTrailInfoByQueryHandler(queryObj).stream()
                .map(x -> new AuditLogDTO(x.getId(), x.getSalsOrdrItemDetailId(), x.getOldRowData(), x.getNewRowData(),
                    x.getDmlType(), x.getDmlTimestamp(), x.getDmlCreatedBy(), "Sales Order Item"))
                .collect(Collectors.toList());

            Map insertKeys = new HashMap();
            insertKeys.put("idKey", "sales_order_item_details_id");
            insertKeys.put("noValue", auditLogParameterDTO.getSalesOrderNo());
            insertKeys.put("userIdKey", "created_by_id");
            insertKeys.put("userNameKey", "created_by_name");
            insertKeys.put("dateTimeKey", "created_date");

            Map updateKeys = new HashMap();
            updateKeys.put("idKey", "sales_order_item_details_id");
            updateKeys.put("noValue", auditLogParameterDTO.getSalesOrderNo());
            updateKeys.put("userIdKey", "updated_by_id");
            updateKeys.put("userNameKey", "updated_by_name");
            updateKeys.put("dateTimeKey", "updated_date");

            auditLogReportDTOList = AuditTrailUtil.generateReportJSON(auditLogDTO,
                insertKeys, updateKeys);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Mono.just(auditLogReportDTOList).flatMapMany(Flux::fromIterable)
            .log();
    }

    @Override
    public Mono<SalesOrderItemDetailsAuditLogDTO> save(SalesOrderItemDetailsAuditLogDTO salesOrderItemDetailsAuditLogDTO) {
        return null;
    }

    @Override
    public Mono<SalesOrderItemDetailsAuditLogDTO> update(SalesOrderItemDetailsAuditLogDTO salesOrderItemDetailsAuditLogDTO) {
        return null;
    }

    @Override
    public Mono<SalesOrderItemDetailsAuditLogDTO> partialUpdate(SalesOrderItemDetailsAuditLogDTO salesOrderItemDetailsAuditLogDTO) {
        return null;
    }

    @Override
    public Flux<SalesOrderItemDetailsAuditLogDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Mono<Long> countAll() {
        return null;
    }

    @Override
    public Mono<SalesOrderItemDetailsAuditLogDTO> findOne(Long id) {
        return null;
    }

    @Override
    public Mono<Void> delete(Long id) {
        return null;
    }
}
