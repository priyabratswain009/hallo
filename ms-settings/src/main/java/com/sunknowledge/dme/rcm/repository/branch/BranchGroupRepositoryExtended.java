package com.sunknowledge.dme.rcm.repository.branch;

import com.sunknowledge.dme.rcm.domain.BranchGroup;
import com.sunknowledge.dme.rcm.repository.BranchGroupRepository;
import com.sunknowledge.dme.rcm.service.dto.branch.BranchGroupExtendedDTO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BranchGroupRepositoryExtended extends BranchGroupRepository {
    BranchGroup findByBranchGroupId(Long branchGroupId);

    BranchGroup findByBranchGroupName(String branchGroupName);

    List<BranchGroup> findByCompanyId(Long companyId);

    @Query(value = "select tbg.branch_group_id as \"branchGroupId\",\n" +
        "tbg.branch_group_name as \"branchGroupName\",\n" +
        "tbg.company_id as \"companyId\",\n" +
        "tbg.status,\n" +
        "tbg.created_by_id as \"createdById\",\n" +
        "tbg.created_date as \"createdDate\",\n" +
        "tbg.updated_by_id as \"updatedById\",\n" +
        "tbg.created_by_name as \"createdByName\",\n" +
        "tbg.updated_by_name as \"updatedByName\",\n" +
        "tbg.updated_date as \"updatedDate\",cast(tbg.branch_group_uuid as varchar)as \"branchGroupUuid\" ,tc.company_name as \"companyName\"  from settings.t_branch_group tbg inner join  settings.t_company tc  on tbg.company_id = tc.company_id ;\n",nativeQuery = true)
    List<BranchGroupExtendedDTO> findByStatusIgnoreCase(String active);

    List<BranchGroup> findByCompanyIdAndStatusIgnoreCase(Long companyId, String active);

    BranchGroup findByBranchGroupNameAndStatusIgnoreCase(String branchGroupName, String active);

    BranchGroup findByBranchGroupIdAndStatusIgnoreCase(Long branchGroupId, String active);

    List<BranchGroup> findByBranchGroupNameLikeIgnoreCaseAndStatusIgnoreCase(String s, String active);

    Optional<BranchGroup> findByBranchGroupUuid(UUID uuid);
}
