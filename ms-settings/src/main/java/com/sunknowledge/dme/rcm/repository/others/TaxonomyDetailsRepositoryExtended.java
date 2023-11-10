package com.sunknowledge.dme.rcm.repository.others;

import com.sunknowledge.dme.rcm.domain.TaxonomyDetails;
import com.sunknowledge.dme.rcm.repository.TaxonomyDetailsRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaxonomyDetailsRepositoryExtended extends TaxonomyDetailsRepository {

    @Query(value = "select settings.get_t_taxonomy_details_id_by_uuid(:taxonomDetailsUUID)" ,nativeQuery = true)
    Long getIDByUUID(@Param("taxonomDetailsUUID") UUID taxonomDetailsUUID);

    Optional<TaxonomyDetails> findByTaxonomyCodeAndStatusIgnoreCase(String taxonomyCode, String active);

    TaxonomyDetails findByTaxonomyDetailsIdAndStatusIgnoreCase(Long id, String active);

    List<TaxonomyDetails> findByTaxonomyNameIgnoreCaseAndStatusIgnoreCase(String data, String active);

    List<TaxonomyDetails> findByTaxonomyNameLikeIgnoreCaseAndStatusIgnoreCase(String s, String active);
}
