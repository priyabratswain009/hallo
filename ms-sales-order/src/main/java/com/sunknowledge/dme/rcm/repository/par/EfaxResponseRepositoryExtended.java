package com.sunknowledge.dme.rcm.repository.par;

import com.sunknowledge.dme.rcm.domain.EfaxResponse;
import com.sunknowledge.dme.rcm.domain.SalesOrderMaster;
import com.sunknowledge.dme.rcm.dto.cmn.ViewCMNDetailsForEfaxDTO;
import com.sunknowledge.dme.rcm.dto.par.EfaxResponseExtendedDTO;
import com.sunknowledge.dme.rcm.dto.par.ViewPARDetailsForEfaxDTO;
import com.sunknowledge.dme.rcm.repository.EfaxResponseRepository;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface EfaxResponseRepositoryExtended extends EfaxResponseRepository {

    @Query("select * from so.t_efax_response ter where (ter.is_qr_decoded isnull or ter.is_qr_decoded = false) \n" +
        "and (ter.is_manually_tagged isnull)")
    Flux<EfaxResponseExtendedDTO> getAllUnIdentifiedFaxes();

    @Query("select tpm.par_id ,tpm.par_no ,tpm.patient_id ,tpm.patient_id_number ,tpm.patient_first_name \n" +
        ",tpm.patient_last_name ,tpm.insurance_id ,tpm.insurance_name ,tpm.payer_id_no,tpm.payer_level\n" +
        ",tpm.policy_number,tpm.policy_start_date,tpm.policy_end_date,tpm.date_of_contact,tpm.description\n" +
        ",tpm.initial_date,tpm.effective_start_date,tpm.expiration_date,tpm.authorized_by\n" +
        ",tpm.log_in_status,tpm.renewal_status,tpm.renewal_date,tpm.original_par_no,tpm.par_status\n" +
        ",tpm.extension_status,tpm.extension_approval_date,tpm.par_id_no,tprd.par_request_type\n" +
        ",tprd.par_request_doc_name,tprd.par_request_doc_location,cast(tprd.par_request_details_uuid as text) as par_request_details_uuid\n" +
        ",tpsm.so_id ,tpsm.so_no ,cast(tpm.par_uuid  as text) as par_uuid from so.t_par_master tpm  \n" +
        "join so.t_par_request_details tprd on tprd.par_id = tpm.par_id\n" +
        "join so.t_par_so_map tpsm on tpsm.par_id = tpm.par_id \n" +
        "where lower(tpm.par_status) = 'initiated' and lower(tpm.status) = 'active' and lower(tprd.status) = 'active' " +
        "and lower(tpsm.status) = 'active' and tpm.patient_id = :patientId")
    Flux<ViewPARDetailsForEfaxDTO> viewPARDetailsByPatientId(@Param("patientId") long patientId);

    @Query("select tc.cmn_id,tc.cmn_number,tc.cmn_type,tc.cmn_form_name,tc.patient_id,tc.sales_order_id\n" +
        ",tc.sales_order_no,tc.cmn_create_date,tc.cmn_initial_date,tc.cmn_revision_date,tc.cmn_recertification_date\n" +
        ",tc.cmn_expiration_date,tc.cmn_logged_by,tc.cmn_logged_date,tc.cmn_approved_by,tc.cmn_approved_date\n" +
        ",tc.cmn_printed_by,tc.cmn_printed_date,tc.length_of_need,tc.fax_cmn_option,tc.cmn_cover_letter_inclusion_option\n" +
        ",tc.cmn_faxed_by,tc.cmn_faxed_date,tc.fax_status,tc.fax_status_reason,tc.print_cmn_option \n" +
        ",tcdm.cmn_document_id,tcdm.generation_date,tcdm.initial_document_name\n" +
        ",tcdm.out_bound_fax_status,tcdm.out_bound_fax_no,tcdm.out_bound_fax_date_time\n" +
        ",tcdm.in_bound_fax_status,tcdm.in_bound_fax_no,tcdm.in_bound_fax_date_time, cast(tcdm.cmn_document_master_uuid as text) as cmn_document_master_uuid" +
        ",cast(tc.cmn_id_uuid  as text) as cmn_id_uuid  \n" +
        "from so.t_cmn tc \n" +
        "join so.t_cmn_document_master tcdm on tc.cmn_id = tcdm.cmn_id  \n" +
        "where lower(tc.cmn_status) = 'initiated' and lower(tc.status) = 'active' and lower(tcdm.status) = 'active' and tc.patient_id = :patientId")
    Flux<ViewCMNDetailsForEfaxDTO> viewCMNDetailsByPatientId(@Param("patientId") long patientId);

    @Query("select * from so.t_sales_order_master tsom where lower(tsom.status) = 'active' and tsom.patient_id = :patientId")
    Flux<SalesOrderMaster> viewPatientDetailsByPatientId(@Param("patientId") long patientId);

    @Query("select * from so.t_efax_response ter where ter.efax_response_uuid  = :eFaxResponseUUID and lower(ter.status) = 'active'")
    Mono<EfaxResponse> getEfaxDetailsOnUUID(@Param("eFaxResponseUUID") UUID eFaxResponseUUID);

    @Query("select mtpm.patient_id  from masterdata.mv_t_patient_master mtpm where mtpm.patient_master_uuid = :uuid")
    Mono<Long> getPatientIdOnUUID(@Param("uuid") UUID uuid);
}
