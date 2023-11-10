package com.sunknowledge.dme.rcm.repository.common;

import com.sunknowledge.dme.rcm.domain.SalesRep;
import com.sunknowledge.dme.rcm.repository.SalesRepRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface SalesRepRepositoryExtended extends SalesRepRepository {
    @Query(value = "select common.f_get_sales_rep_no()" ,nativeQuery = true)
    String getSalesRepNumber();

    List<SalesRep> findByFirstNameLikeIgnoreCaseAndMiddleNameLikeIgnoreCaseAndLastNameLikeIgnoreCaseAndStatusIgnoreCase(String s, String s1, String s2, String active);

    List<SalesRep> findByFirstNameLikeIgnoreCaseAndLastNameLikeIgnoreCaseAndStatusIgnoreCase(String s, String s1, String active);

    SalesRep findBySalesRepNo(String data);

    @Query(value="select common.get_t_sales_rep_id_by_uuid(:salesRepUUID)", nativeQuery = true)
    Long getIDByUUID(@Param("salesRepUUID") UUID salesRepUUID);

    SalesRep findBySalesRepIdAndStatusIgnoreCase(Long idByUUID, String active);

    SalesRep findBySalesRepUuid(UUID salesRepUuid);

    @Query(value = "SELECT *\n" +
        "FROM common.t_sales_rep\n" +
        "WHERE CASE\n" +
        "         WHEN middle_name IS NULL THEN\n" +
        "             CONCAT(TRIM(first_name), ' ', TRIM(last_name))\n" +
        "         ELSE\n" +
        "             CONCAT(TRIM(first_name), ' ', TRIM(middle_name), ' ', TRIM(last_name))\n" +
        "      END LIKE :fullName", nativeQuery = true)
    List<SalesRep> findByName(@Param("fullName") String fullName);
}
