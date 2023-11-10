package com.sunknowledge.dme.rcm.repository.itemothers;

import com.sunknowledge.dme.rcm.domain.VendorMaster;
import com.sunknowledge.dme.rcm.repository.VendorMasterRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VendorMasterRepositoryExtended extends VendorMasterRepository {


    List<VendorMaster> findByStatusIgnoreCase(String status);

    List<VendorMaster> findByVendorDescriptionLikeAndStatusIgnoreCase(String s, String active);

    VendorMaster findByVendorIdAndStatusIgnoreCase(Long vendorId, String active);

    VendorMaster findByVendorNameAndStatusIgnoreCase(String vendorName, String active);

    VendorMaster findByVendorNoAndStatusIgnoreCase(String vendorNo, String active);

    VendorMaster findByVendorId(Long vendorId);

    List<VendorMaster> findByVendorDescriptionLikeIgnoreCaseAndStatusIgnoreCase(String s, String active);

    List<VendorMaster> findByVendorNameLikeIgnoreCaseAndStatusIgnoreCase(String s, String active);

    @Query(value = "select item.f_get_vendor_no()" ,nativeQuery = true)
    String getVendorNumber();
}
