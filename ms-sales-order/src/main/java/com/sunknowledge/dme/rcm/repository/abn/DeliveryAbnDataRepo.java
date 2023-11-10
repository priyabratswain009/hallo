package com.sunknowledge.dme.rcm.repository.abn;

import com.sunknowledge.dme.rcm.service.dto.abn.CreatedAbnOutputExtendedDTO;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.DeliveryAbnData;
import com.sunknowledge.dme.rcm.repository.DeliveryAbnDataRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DeliveryAbnDataRepo extends DeliveryAbnDataRepository {

	@Query("CALL so.so_create_abn_data(:soid,:item_proc,:abn_reason,:abn_modifier_1,:abn_modifier_2,:uid,:uname)")
	Mono<DeliveryAbnData> abnCreation(@Param("soid") Long soid, @Param("item_proc") String item_proc,
			@Param("abn_reason") String abn_reason, @Param("abn_modifier_1") String abn_modifier_1,
			@Param("abn_modifier_2") String abn_modifier_2, @Param("uid") Long uid, @Param("uname") String uname);


	@Query("select * from t_delivery_abn_data tpd where tpd.sales_order_id = :soid and tpd.sales_order_details_abn_item_proc_code = :item_proc and LOWER(status) = LOWER(:status)")
	Flux<DeliveryAbnData> getAbnData(@Param("soid") Long soid, @Param("item_proc") String item_proc, @Param("status") String status);

    @Query(value = "select * from so.create_abn_for_so(:soid,:item_proc,:abn_reason,:abn_modifier_1,:abn_modifier_2,:uid,:uname) as \n" +
        "(\n" +
        "\tabn_no character varying,\n" +
        "\tabn_id bigint,\n" +
        "    abn_uuid uuid,  \n" +
        "    abn_print_date timestamp without time zone, \n" +
        "    abn_item character varying, \n" +
        "    abn_proc_code character varying, \n" +
        "    abn_charge_amt character varying,\n" +
        "    abn_modifier1 character varying,\n" +
        "    abn_modifier2 character varying,\n" +
        "    abn_user_response character varying,\n" +
        "    abn_reason character varying,\n" +
        "    abn_status character varying\n" +
        ")")
    Mono<CreatedAbnOutputExtendedDTO> abnCreationUsingDbFunction(@Param("soid") Long soid,
                                                                 @Param("item_proc") String item_proc,
                                                                 @Param("abn_reason") String abn_reason,
                                                                 @Param("abn_modifier_1") String abn_modifier_1,
                                                                 @Param("abn_modifier_2") String abn_modifier_2,
                                                                 @Param("uid") Long uid,
                                                                 @Param("uname") String uname);
}
