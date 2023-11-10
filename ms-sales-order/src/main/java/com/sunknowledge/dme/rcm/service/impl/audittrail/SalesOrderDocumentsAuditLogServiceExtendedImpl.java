package com.sunknowledge.dme.rcm.service.impl.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditTrailUtil;
import com.sunknowledge.dme.rcm.domain.SalesOrderDocuments;
import com.sunknowledge.dme.rcm.service.audittrail.SalesOrderDocumentsAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.SalesOrderDocumentsAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderDocumentsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.AuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.SalesOrderDocumentsAuditLogBySONoAndUserIdOrDateQuery;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderMasterSearchOutputDTO;
import com.sunknowledge.dme.rcm.service.soentryandsearch.SalesOrderDocumentsServiceExtended;
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
public class SalesOrderDocumentsAuditLogServiceExtendedImpl implements SalesOrderDocumentsAuditLogServiceExtended {

    @Autowired
    SalesOrderMasterSearchServiceExtended salesOrderMasterSearchServiceExtended;
    @Autowired
    SalesOrderDocumentsAuditLogQueryHandler salesOrderDocumentsAuditLogQueryHandler;

    @Autowired
    SalesOrderDocumentsServiceExtended salesOrderDocumentsServiceExtended;
    @Override
    public Flux<AuditLogReportDTO> getSODocumentsAuditTrailInfo(AuditLogParameterDTO auditLogParameterDTO) {
        List<AuditLogReportDTO> auditLogReportDTOList = new ArrayList<>();
        try {
            Long soId = salesOrderMasterSearchServiceExtended.
                getSODetailsBySalesOrderNumber(auditLogParameterDTO.getSalesOrderNo())
                .collectList().toFuture().get().stream().
                max(Comparator.comparing(SalesOrderMasterSearchOutputDTO::getSid))
                .orElseThrow(NoSuchElementException::new).getSid();

            List<Long> salesOrderDocumentsIDList = salesOrderDocumentsServiceExtended.
                findBySalesOrderId(soId).collectList().toFuture().get().stream().map(SalesOrderDocuments::getSalesOrderDocumentId).collect(Collectors.toList());

            SalesOrderDocumentsAuditLogBySONoAndUserIdOrDateQuery queryObj = new SalesOrderDocumentsAuditLogBySONoAndUserIdOrDateQuery();
            queryObj.setSoID(soId);
            queryObj.setSoDocumentsID(salesOrderDocumentsIDList);
            queryObj.setSoNo(auditLogParameterDTO.getSalesOrderNo());
            queryObj.setUserID(auditLogParameterDTO.getUserId());
            queryObj.setFromDate(auditLogParameterDTO.getFromDate());
            queryObj.setToDate(auditLogParameterDTO.getToDate());

            List<AuditLogDTO> auditLogDTO = salesOrderDocumentsAuditLogQueryHandler.
                getSODocumentsAuditTrailInfoByQueryHandler(queryObj).stream()
                .map(x -> new AuditLogDTO(x.getId(), x.getSalesOrderDocId(), x.getOldRowData(), x.getNewRowData(),
                    x.getDmlType(), x.getDmlTimestamp(), x.getDmlCreatedBy(), "Sales Order Documents"))
                .collect(Collectors.toList());

            Map insertKeys = new HashMap();
            insertKeys.put("idKey", "sales_order_document_id");
            insertKeys.put("noValue", auditLogParameterDTO.getSalesOrderNo());
            insertKeys.put("userIdKey", "created_by_id");
            insertKeys.put("userNameKey", "created_by_name");
            insertKeys.put("dateTimeKey", "created_date");

            Map updateKeys = new HashMap();
            updateKeys.put("idKey", "sales_order_document_id");
            updateKeys.put("noValue", auditLogParameterDTO.getSalesOrderNo());
            updateKeys.put("userIdKey", "updated_by_id");
            updateKeys.put("userNameKey", "updated_by_name");
            updateKeys.put("dateTimeKey", "updated_date");

            auditLogReportDTOList = AuditTrailUtil.generateReportJSON(auditLogDTO,
                insertKeys, updateKeys);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Mono.just(auditLogReportDTOList).flatMapMany(Flux::fromIterable).log();
    }

    @Override
    public Mono<SalesOrderDocumentsAuditLogDTO> save(SalesOrderDocumentsAuditLogDTO salesOrderDocumentsAuditLogDTO) {
        return null;
    }

    @Override
    public Mono<SalesOrderDocumentsAuditLogDTO> update(SalesOrderDocumentsAuditLogDTO salesOrderDocumentsAuditLogDTO) {
        return null;
    }

    @Override
    public Mono<SalesOrderDocumentsAuditLogDTO> partialUpdate(SalesOrderDocumentsAuditLogDTO salesOrderDocumentsAuditLogDTO) {
        return null;
    }

    @Override
    public Flux<SalesOrderDocumentsAuditLogDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Mono<Long> countAll() {
        return null;
    }

    @Override
    public Mono<SalesOrderDocumentsAuditLogDTO> findOne(Long id) {
        return null;
    }

    @Override
    public Mono<Void> delete(Long id) {
        return null;
    }


}
