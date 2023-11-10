package com.sunknowledge.dme.rcm.repository.itemothers;

import com.sunknowledge.dme.rcm.domain.Manufacturer;
import com.sunknowledge.dme.rcm.repository.ManufacturerRepository;
import com.sunknowledge.dme.rcm.service.dto.ManufacturerDTO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ManufacturerRepositoryExtended extends ManufacturerRepository {

    Manufacturer findByManufacturerIdAndStatusIgnoreCase(Long manufactureId, String active);

    Manufacturer findByManufacturerNameAndStatusIgnoreCase(String manufactureName, String active);

    Manufacturer findByManufacturerNoAndStatusIgnoreCase(String manufacturerNo, String active);

    List<Manufacturer> findByStatusIgnoreCase(String status);

    Manufacturer findByManufacturerId(Long manufactureId);

    List<Manufacturer> findByManufacturerNameLikeIgnoreCaseAndStatusIgnoreCase(String manufactureName, String active);
    @Query(value = "select item.f_get_manufacturer_no()" ,nativeQuery = true)
    String getManufacturerNumber();
}
