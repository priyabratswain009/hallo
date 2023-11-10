package com.sunknowledge.dme.rcm.service.impl.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditTrailUtil;
import com.sunknowledge.dme.rcm.domain.SalesOrderInsuranceDetails;
import com.sunknowledge.dme.rcm.repository.SalesOrderInsuranceAuditLogRepositoryExtended;
import com.sunknowledge.dme.rcm.service.audittrail.SalesOrderInsuranceAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.SalesOrderInsuranceAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderInsuranceDetailsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.AuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.SalesOrderInsuranceAuditLogBySONoAndUserIdOrDateQuery;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderMasterSearchOutputDTO;
import com.sunknowledge.dme.rcm.service.soentryandsearch.SalesOrderInsuranceDetailsServiceExtendedImpl;
import com.sunknowledge.dme.rcm.service.soentryandsearch.SalesOrderMasterSearchServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Primary
@Service
public class SalesOrderInsuranceAuditLogServiceExtendedImpl implements SalesOrderInsuranceAuditLogServiceExtended {

    @Autowired
    SalesOrderInsuranceAuditLogQueryHandler salesOrderInsuranceAuditLogQueryHandler;

    @Autowired
    SalesOrderInsuranceAuditLogRepositoryExtended salesOrderInsuranceAuditLogRepositoryExtended;

    @Autowired
    SalesOrderInsuranceDetailsServiceExtendedImpl salesOrderInsuranceDetailsServiceExtended;
    @Autowired
    SalesOrderMasterSearchServiceExtended salesOrderMasterSearchServiceExtended;

    @Override
    public Flux<AuditLogReportDTO> getSOInsuranceAuditTrailInfo(AuditLogParameterDTO auditLogParameterDTO) {
        List<AuditLogReportDTO> auditLogReportDTOList = new ArrayList<>();
        try {
            Long soId = salesOrderMasterSearchServiceExtended.
                getSODetailsBySalesOrderNumber(auditLogParameterDTO.getSalesOrderNo())
                .collectList().toFuture().get().stream().
                max(Comparator.comparing(SalesOrderMasterSearchOutputDTO::getSid))
                .orElseThrow(NoSuchElementException::new).getSid();

            List<Long> salesOrderInsuranceIDList = salesOrderInsuranceDetailsServiceExtended.
                findBySalesOrderId(soId).collectList().toFuture().get().stream().map(SalesOrderInsuranceDetails::getSalesOrderInsuranceDetailsId).collect(Collectors.toList());

            SalesOrderInsuranceAuditLogBySONoAndUserIdOrDateQuery queryObj = new SalesOrderInsuranceAuditLogBySONoAndUserIdOrDateQuery();
            queryObj.setSoID(soId);
            queryObj.setSoInsuranceID(salesOrderInsuranceIDList);
            queryObj.setSoNo(auditLogParameterDTO.getSalesOrderNo());
            queryObj.setUserID(auditLogParameterDTO.getUserId());
            queryObj.setFromDate(auditLogParameterDTO.getFromDate());
            queryObj.setToDate(auditLogParameterDTO.getToDate());

            List<AuditLogDTO> auditLogDTO = salesOrderInsuranceAuditLogQueryHandler.
                getSOInsuranceAuditTrailInfoByQueryHandler(queryObj).stream()
                .map(x -> new AuditLogDTO(x.getId(), x.getSalsOrdInsranceDetailsId(), x.getOldRowData(), x.getNewRowData(),
                    x.getDmlType(), x.getDmlTimestamp(), x.getDmlCreatedBy(), "Sales Order Insurance"))
                .collect(Collectors.toList());

            Map insertKeys = new HashMap();
            insertKeys.put("idKey", "sales_order_insurance_details_id");
            insertKeys.put("noValue", auditLogParameterDTO.getSalesOrderNo());
            insertKeys.put("userIdKey", "created_by_id");
            insertKeys.put("userNameKey", "created_by_name");
            insertKeys.put("dateTimeKey", "created_date");

            Map updateKeys = new HashMap();
            updateKeys.put("idKey", "sales_order_insurance_details_id");
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
    public Mono<SalesOrderInsuranceDetailsAuditLogDTO> save(SalesOrderInsuranceDetailsAuditLogDTO salesOrderInsuranceDetailsAuditLogDTO) {
        return null;
    }

    @Override
    public Mono<SalesOrderInsuranceDetailsAuditLogDTO> update(SalesOrderInsuranceDetailsAuditLogDTO salesOrderInsuranceDetailsAuditLogDTO) {
        return null;
    }

    @Override
    public Mono<SalesOrderInsuranceDetailsAuditLogDTO> partialUpdate(SalesOrderInsuranceDetailsAuditLogDTO salesOrderInsuranceDetailsAuditLogDTO) {
        return null;
    }

    @Override
    public Flux<SalesOrderInsuranceDetailsAuditLogDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Mono<Long> countAll() {
        return null;
    }

    @Override
    public Mono<SalesOrderInsuranceDetailsAuditLogDTO> findOne(Long id) {
        return null;
    }

    @Override
    public Mono<Void> delete(Long id) {
        return null;
    }


}
