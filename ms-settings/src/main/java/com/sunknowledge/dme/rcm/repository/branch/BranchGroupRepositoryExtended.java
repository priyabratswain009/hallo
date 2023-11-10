package com.sunknowledge.dme.rcm.repository.branch;

import com.sunknowledge.dme.rcm.domain.BranchGroup;
import com.sunknowledge.dme.rcm.repository.BranchGroupRepository;

import java.util.List;

public interface BranchGroupRepositoryExtended extends BranchGroupRepository {
    BranchGroup findByBranchGroupId(Long branchGroupId);

    BranchGroup findByBranchGroupName(String branchGroupName);

    List<BranchGroup> findByCompanyId(Long companyId);

    List<BranchGroup> findByStatusIgnoreCase(String active);

    List<BranchGroup> findByCompanyIdAndStatusIgnoreCase(Long companyId, String active);

    BranchGroup findByBranchGroupNameAndStatusIgnoreCase(String branchGroupName, String active);

    BranchGroup findByBranchGroupIdAndStatusIgnoreCase(Long branchGroupId, String active);

    List<BranchGroup> findByBranchGroupNameLikeIgnoreCaseAndStatusIgnoreCase(String s, String active);
}
