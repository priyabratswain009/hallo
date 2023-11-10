package com.sunknowledge.dme.rcm.repository.others;

import com.sunknowledge.dme.rcm.domain.BranchOffice;
import com.sunknowledge.dme.rcm.domain.UserMaster;
import com.sunknowledge.dme.rcm.repository.UserMasterRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserMasterRepositoryExtended extends UserMasterRepository {
    List<UserMaster> findByUserIdInAndStatusIgnoreCase(List<Long> userIdList, String active);

    @Query(value = "select settings.get_t_user_master_id_by_uuid(:userUUID)", nativeQuery = true)
    Long getIDByUUID(@Param("userUUID") UUID userUUID);

    UserMaster findByUserIdAndStatusIgnoreCase(Long userId, String active);
}
