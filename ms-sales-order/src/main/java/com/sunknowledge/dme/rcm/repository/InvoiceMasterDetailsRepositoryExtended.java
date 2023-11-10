package com.sunknowledge.dme.rcm.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;

import liquibase.pro.packaged.T;
import reactor.core.publisher.Flux;

public interface InvoiceMasterDetailsRepositoryExtended extends InvoiceMasterDetailsRepository {

	@Query(value = "CALL so.so_for_invoice_all(:salesOrderId)")
    Flux<T> saveInvoiceForAll(@Param("salesOrderId") Long salesOrderId);

}
