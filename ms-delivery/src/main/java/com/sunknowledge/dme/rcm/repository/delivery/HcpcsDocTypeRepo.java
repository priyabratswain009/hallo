package com.sunknowledge.dme.rcm.repository.delivery;

import com.sunknowledge.dme.rcm.domain.HcpcsDocType;
import com.sunknowledge.dme.rcm.repository.HcpcsDocTypeRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HcpcsDocTypeRepo extends HcpcsDocTypeRepository {
    @Query("FROM HcpcsDocType WHERE hcpcsCode = :hcpcsCode")
    List<HcpcsDocType> getHcpcsDocTypeOnHcpcsCode(@Param("hcpcsCode") String hcpcsCode);

    @Query("FROM HcpcsDocType WHERE hcpcsCode = :hcpcsCode AND documentId = :documentId")
    HcpcsDocType getHcpcsDocTypeOnHcpcsCodeAndDocumentId(@Param("hcpcsCode") String hcpcsCode, @Param("documentId") Long documentId);

    @Query("FROM HcpcsDocType WHERE formName = :formName")
    HcpcsDocType getHcpcsDocTypeOnFormName(@Param("formName") String formName);

    @Query("FROM HcpcsDocType WHERE hcpcsCode = :hcpcsCode AND fileType in('Form', 'Doc')")
    List<HcpcsDocType> getHcpcsDocTypeOnHcpcsCodeNFileType(@Param("hcpcsCode") String hcpcsCode);

    @Query(value = "select * from t_hcpcs_doc_type thdt where thdt.hcpcs_code = :hcpcsCode", nativeQuery = true)
    List<HcpcsDocType> getHcpcsDocumentsOnHcpcsCode(@Param("hcpcsCode") String hcpcsCode);
}
