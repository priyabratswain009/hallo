package com.sunknowledge.dme.rcm.repository.primaryclaimrepository;

import com.sunknowledge.dme.rcm.domain.Transaction835MasterDetails;
import com.sunknowledge.dme.rcm.pojo.invoice.TransactionGroupByProjection;
import com.sunknowledge.dme.rcm.repository.Transaction835MasterDetailsRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Transaction835MasterDetailsRepo extends Transaction835MasterDetailsRepository {
    @Query("FROM Transaction835MasterDetails WHERE status = :status ORDER BY transactionId")
    List<Transaction835MasterDetails> getAllTansactionListOnStatus(@Param("status") String status);

    @Query(value = "select patient_control_no as patientControlNo, item_proc_code as itemProcCode from t_835_transaction_master_details " +
        "where status = 'Active' group by patient_control_no, item_proc_code", nativeQuery = true)
    List<TransactionGroupByProjection> getAllActiveGroupByTransactionsOnPatientControlNoAndProcedureCode(@Param("status") String status);

    @Query("FROM Transaction835MasterDetails WHERE patientControlNo = :patientControlNo AND itemProcCode = :itemProcCode AND status = :status")
    List<Transaction835MasterDetails> getActiveTransactionPatientControlNoAndProcedureCode(@Param("patientControlNo") String patientControlNo,
                                                                                     @Param("itemProcCode") String itemProcCode, @Param("status") String status);
}
