package com.sunknowledge.dme.rcm.repository.nppes;

import com.sunknowledge.dme.rcm.domain.DoctorMaster;
import com.sunknowledge.dme.rcm.repository.DoctorMasterRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoctorMasterRepo extends DoctorMasterRepository {
    @Query("FROM DoctorMaster WHERE npiNumber = :npiNumber")
    DoctorMaster getDoctorMasterByNpiNumber(@Param("npiNumber") String npiNumber);

    @Query(value = "select concat(tdm.first_name,' ', tdm.middle_name,' ', tdm.last_name) from t_doctor_master tdm where tdm.first_name like %:searchText% or tdm.middle_name like %:searchText% or tdm.last_name like %:searchText%", nativeQuery = true)
    List<String> getDoctorDetailsByName(@Param("searchText") String searchText);

    @Query(value = "select concat(tdm.first_name,' ', tdm.middle_name,' ', tdm.last_name) from t_doctor_master tdm where tdm.npi_number like %:searchNPINumber%", nativeQuery = true)
    List<String> getDoctorDetailsByNPINumber(@Param("searchNPINumber") String searchNPINumber);
}
