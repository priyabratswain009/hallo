package com.sunknowledge.dme.rcm.repository.others;

import com.sunknowledge.dme.rcm.domain.Company;
import com.sunknowledge.dme.rcm.repository.CompanyRepository;
import com.sunknowledge.dme.rcm.service.dto.others.CompanyExtendedDTO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompanyRepositoryExtended extends CompanyRepository {

    @Query(value = "select *,tc.company_id as id,tc.company_name as title from t_company tc",nativeQuery = true)
    List<CompanyExtendedDTO> findAllWithIdandTitle();

    Company findByCompanyIdAndStatusIgnoreCase(Long companyId, String active);

    List<Company> findByStatusIgnoreCase(String active);

    Optional<Company> findByCompanyUuid(UUID uuid);
}
