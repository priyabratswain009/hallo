package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.InsurancePricetableMap;
import com.sunknowledge.dme.rcm.domain.SalesOrderMaster;
import com.sunknowledge.dme.rcm.repository.rowmapper.ColumnConverter;
import com.sunknowledge.dme.rcm.repository.rowmapper.InsurancePricetableMapRowMapper;
import com.sunknowledge.dme.rcm.repository.rowmapper.SalesOrderMasterRowMapper;
import com.sunknowledge.dme.rcm.service.dto.InsurancePricetableMapDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderCommonSearchOutputDTO;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.convert.R2dbcConverter;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.sql.Comparison;
import org.springframework.data.relational.core.sql.Conditions;
import org.springframework.data.relational.core.sql.Table;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Map;

@SuppressWarnings("unused")
@Repository
public class InsurancePricetableMapRepositoryExtendedImpl extends InsurancePricetableMapRepositoryInternalImpl implements InsurancePricetableMapRepositoryExtended {

    private static final Table entityTable = Table.aliased("t_insurance_pricetable_map", EntityManager.ENTITY_ALIAS);

    public InsurancePricetableMapRepositoryExtendedImpl(R2dbcEntityTemplate template, EntityManager entityManager, InsurancePricetableMapRowMapper insurancepricetablemapMapper, R2dbcEntityOperations entityOperations, R2dbcConverter converter) {
        super(template, entityManager, insurancepricetablemapMapper, entityOperations, converter);
    }

    @Override
    public Flux<InsurancePricetableMap> findByInsuranceIdNo(String insuranceIdNo) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("insurance_id_no"), Conditions.just("'" + insuranceIdNo + "'"));
        return createQuery(null, whereClause).all();
    }

    @Override
    public Flux<InsurancePricetableMap> findByInsuranceId(Long insuranceId){
        Comparison whereClause = Conditions.isEqual(entityTable.column("insurance_id"), Conditions.just(insuranceId.toString()));
        return createQuery(null, whereClause).all();
    }
}
