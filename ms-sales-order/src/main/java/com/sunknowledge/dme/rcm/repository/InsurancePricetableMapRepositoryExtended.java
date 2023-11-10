package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.InsurancePricetableMap;
import com.sunknowledge.dme.rcm.domain.SalesOrderItemDetailsAuditLog;
import com.sunknowledge.dme.rcm.repository.rowmapper.SalesOrderItemDetailsAuditLogRowMapper;
import com.sunknowledge.dme.rcm.service.dto.InsurancePricetableMapDTO;
import org.springframework.context.annotation.Primary;
import org.springframework.data.r2dbc.convert.R2dbcConverter;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.sql.Condition;
import org.springframework.data.relational.core.sql.Conditions;
import org.springframework.data.relational.core.sql.Table;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public interface InsurancePricetableMapRepositoryExtended extends InsurancePricetableMapRepository{

    Flux<InsurancePricetableMap> findByInsuranceIdNo(String insuranceIdNo);

    Flux<InsurancePricetableMap> findByInsuranceId(Long insuranceId);
}
