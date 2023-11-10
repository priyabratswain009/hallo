package com.sunknowledge.dme.rcm.service.soentryandsearch;

import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.SalesOrderMaster;
import com.sunknowledge.dme.rcm.repository.SalesOrderMasterSearchRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderCombinedSearchInternalDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderCommonSearchInternalDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderCommonSearchOutputDTO;
import com.sunknowledge.dme.rcm.service.soentryandsearch.SalesOrderMasterSearchServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderMasterSearchOutputDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderMasterSearchDetailsDTO;
import com.sunknowledge.dme.rcm.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Primary
@Service
public class SalesOrderMasterSearchServiceExtendedImpl implements SalesOrderMasterSearchServiceExtended {
    @Autowired
    SalesOrderMasterSearchRepositoryExtended salesOrderMasterSearchRepository;

    @Override
    public Flux<SalesOrderMasterSearchOutputDTO> getSODetailsByCreatedById(Integer createdById) {
        return salesOrderMasterSearchRepository.findByCreatedById(createdById);
    }

    @Override
    public Flux<SalesOrderMasterSearchDetailsDTO> getSODetailsByStatus(String status, String fromDate, String toDate) {
        return salesOrderMasterSearchRepository.findSODetailsByStatus(status.trim().equals("") ? null : status,
            fromDate.trim().equals("") ? null : CommonUtil.dateconverter(fromDate),
            toDate.trim().equals("") ? null : CommonUtil.dateconverter(toDate));
    }

    @Override
    public Flux<SalesOrderMasterSearchDetailsDTO> getSODetailsByBranchandCreatedDate(String branchName, String createdFromDate,
                                                                                     String createdToDate) {
        return salesOrderMasterSearchRepository.findByBranchandCreatedDate(branchName.trim(),
            createdFromDate.trim().equals("") ? null : CommonUtil.dateconverter(createdFromDate),
            createdToDate.trim().equals("") ? null : CommonUtil.dateconverter(createdToDate));
    }

    @Override
    public Flux<SalesOrderMasterSearchOutputDTO> getSODetailsByFacilityName(String facilityName) {
        return salesOrderMasterSearchRepository.findByFacilityName(facilityName.trim());
    }

    @Override
    public Flux<SalesOrderMasterSearchOutputDTO> getSODetailsBySalesOrderNumber(String salesOrderNumber) {
        return salesOrderMasterSearchRepository.getSODetailsBySalesOrderNumber(salesOrderNumber);
    }

    @Override
    public Flux<SalesOrderMasterSearchOutputDTO> getSODetailsByPatientBranchName(String branchName) {
        return salesOrderMasterSearchRepository.getSODetailsByPatientBranchName(branchName.trim());
    }

    @Override
    public Flux<SalesOrderMasterSearchOutputDTO> getSODetailsByPatientName(String patientName) {
        return salesOrderMasterSearchRepository.getSODetailsByPatientName(patientName.trim());
    }

    @Override
    public Flux<SalesOrderMaster> getSODetailsByPatientId(Long patientId) {
        return salesOrderMasterSearchRepository.findByPatientId(patientId);
    }

    @Override
    public Flux<SalesOrderMasterSearchOutputDTO> getSODetailsByConfirmedById(Integer confirmationbyid) {
        return salesOrderMasterSearchRepository.findByConfirmedById(confirmationbyid);
    }

    @Override
    public Flux<SalesOrderMasterSearchOutputDTO> getSODetailsByConfirmationDetails(String confirmationByName, String status,
                                                                                   String confirmationFromDate, String confirmationToDate) {
        return salesOrderMasterSearchRepository.findByByConfirmationDetails(
            confirmationByName.trim(),
            status.trim().equals("") ? null : status,
            confirmationFromDate.trim().equals("") ? null : CommonUtil.dateconverter(confirmationFromDate),
            confirmationToDate.trim().equals("") ? null : CommonUtil.dateconverter(confirmationToDate)
        );
    }

    @Override
    public Flux<SalesOrderMasterSearchOutputDTO> getSODetailsByCreationDetails(String createdByName, String status, String createdFromDate,
                                                                               String createdToDate) {
        return salesOrderMasterSearchRepository.findByCreationDetails(
            createdByName.trim(),
            status.trim().equals("") ? null : status,
            createdFromDate.trim().equals("") ? null : CommonUtil.dateconverter(createdFromDate),
            createdToDate.trim().equals("") ? null : CommonUtil.dateconverter(createdToDate)
        );
    }

    @Override
    public Flux<SalesOrderMasterSearchOutputDTO> getSODetailsBySOInfo(String salesOrderNo, String deliveryScheduleDatetime,
                                                                      String deliveryActualDatetime, String createdDate) {
        return salesOrderMasterSearchRepository.findBySOInfo(
            salesOrderNo,
            deliveryScheduleDatetime.trim().equals("") ? null : CommonUtil.dateconverter(deliveryScheduleDatetime),
            deliveryActualDatetime.trim().equals("") ? null : CommonUtil.dateconverter(deliveryActualDatetime),
            createdDate.trim().equals("") ? null : CommonUtil.dateconverter(createdDate)
        );
    }

    @Override
    public Flux<SalesOrderMasterSearchDetailsDTO> getSODetailsWithCombinedInformation(String patientName, String salesOderNo,
                                                                                      String patientDOBDt, String startDateDt,
                                                                                      String endDateDt, String status, String branchName,
                                                                                      String primaryInsurerName, String patientAddress1, String facilityName) {
        return salesOrderMasterSearchRepository.findSODetailsWithCombinedInformation(
            patientName.trim(), salesOderNo.trim(),
            patientDOBDt.trim().equals("") ? null : CommonUtil.dateconverter(patientDOBDt),
            startDateDt.trim().equals("") ? null : CommonUtil.dateconverter(startDateDt),
            endDateDt.trim().equals("") ? null : CommonUtil.dateconverter(endDateDt),
            status.trim().equals("") ? null : status,
            branchName.trim(), primaryInsurerName.trim(),
            patientAddress1.trim(), facilityName.trim()
        );
    }

    @Override
    public Mono<SalesOrderMasterDTO> save(SalesOrderMasterDTO salesOrderMasterDTO) {
        return null;
    }

    @Override
    public Mono<SalesOrderMasterDTO> update(SalesOrderMasterDTO salesOrderMasterDTO) {
        return null;
    }

    @Override
    public Mono<SalesOrderMasterDTO> partialUpdate(SalesOrderMasterDTO salesOrderMasterDTO) {
        return null;
    }

    @Override
    public Flux<SalesOrderMasterDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Mono<Long> countAll() {
        return null;
    }

    @Override
    public Mono<SalesOrderMasterDTO> findOne(Long id) {
        return null;
    }

    @Override
    public Mono<Void> delete(Long id) {
        return null;
    }

    @Override
    public Mono<String> getsalesOrderNo() {
        // TODO Auto-generated method stub
        return salesOrderMasterSearchRepository.getsalesOrderNo();
    }

    @Override
    public Flux<HashMap<String, Object>> getSalesOrderDetailsBySearchParameters(SalesOrderCommonSearchInternalDTO salesOrderCommonSearchInternalDTO) {

        //----- Parameter DTO: Field-Column mapper -----
        Map<String, String> columnKeyMapper = new HashMap<>();
        columnKeyMapper.put("branchID", "branch_id");
        columnKeyMapper.put("patientIDNo", "patient_id_no");
        columnKeyMapper.put("patientFirstName", "patient_first_name");
        columnKeyMapper.put("patientMiddleName", "patient_middle_name");
        columnKeyMapper.put("patientLastName", "patient_last_name");
        columnKeyMapper.put("salesOrderNo", "sales_order_no");
        columnKeyMapper.put("scheduleDeliveryFromDate", "delivery_schedule_datetime");
        columnKeyMapper.put("scheduleDeliveryToDate", "delivery_schedule_datetime");
        columnKeyMapper.put("createdByID", "created_by_id");
        columnKeyMapper.put("createdByName", "created_by_name");
        columnKeyMapper.put("createdDateFromDate", "created_date");
        columnKeyMapper.put("createdDateToDate", "created_date");
        columnKeyMapper.put("itemNo", "sales_order_item_number");
        columnKeyMapper.put("itemName", "sales_order_details_item_name");
        columnKeyMapper.put("hCPCSCode", "sales_order_details_proc_code");
        columnKeyMapper.put("primaryPayerId", "primary_insurer_id");
        columnKeyMapper.put("primaryPayerName", "primary_insurer_name");
        columnKeyMapper.put("dropshipStatus", "is_dropship_allowed");
        columnKeyMapper.put("deliveryAddressLine1", "delivery_address_line_1");
        columnKeyMapper.put("deliveryAddressLine2", "delivery_address_line_2");
        columnKeyMapper.put("deliveryCity", "delivery_city_name");
        columnKeyMapper.put("deliveryState", "delivery_state_name");
        columnKeyMapper.put("deliveryZip", "delivery_zip_code");
        columnKeyMapper.put("salesOrderStatus", "order_status");

        Class<?> dtoClass = salesOrderCommonSearchInternalDTO.getClass();
        Field[] fields = dtoClass.getDeclaredFields();
        Map<String, Object> conditionalKeyValue = new HashMap<>();
        for (Field field : fields) {
            field.setAccessible(true); // Allow access to private fields
            String fieldName = field.getName();

            try {
                Object fieldValue = field.get(salesOrderCommonSearchInternalDTO);
                if (fieldValue != null && ((fieldValue instanceof String && !fieldValue.toString().trim().equals(""))
                    || (fieldValue instanceof Long && !fieldValue.equals(0L)))) {
                    conditionalKeyValue.put(fieldName, fieldValue);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Error=" + e);
            }
        }
        System.out.println("==========conditionalKeyValue=" + conditionalKeyValue);
        System.out.println("==========columnKeyMapper=" + columnKeyMapper);

        //--------------------------------------------------------------------------------------------------------------
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT a.sales_order_no as sales_order_no, a.sales_order_master_uuid as sales_order_uuid, \n" +
            "a.patient_id_no as patient_id_no, \n" +
            "a.patient_first_name as patient_first_name, a.patient_middle_name as patient_middle_name,  \n" +
            "a.patient_last_name as patient_last_name, a.created_by_id as created_by_id, a.created_by_name as created_by_name,\n" +
            "a.created_date as created_date, a.delivery_schedule_datetime as schedule_delivery_date, \n" +
            "a.delivery_address_line_1 as delivery_address_line_1, a.delivery_address_line_2 as delivery_address_line_2, \n" +
            "a.delivery_city_name as delivery_city, a.delivery_state_name as delivery_state, \n" +
            "a.delivery_zip_code as delivery_zip, a.order_status as sales_order_status FROM t_sales_order_master a ");
        sqlBuilder.append("JOIN t_sales_order_item_details b ON a.sales_order_id = b.sales_order_id ");
        sqlBuilder.append("JOIN t_sales_order_insurance_details c ON a.sales_order_id = c.sales_order_id ");
        sqlBuilder.append("WHERE 1 = 1 ");

//        Query query = Query.query(Criteria.empty());

        for (Map.Entry<String, Object> entry : conditionalKeyValue.entrySet()) {
            String key = entry.getKey();
            String column = columnKeyMapper.get(key);

            sqlBuilder.append("AND " + column + " = :" + key + " ");
//            query = query.bind("condition2", condition2);
//            Criteria criteria = Criteria.where(column).is(value);
//            query = query.query(criteria);
        }

        System.out.println("Build Query=" + sqlBuilder);
//------------------------------------------------------------------------------------------------------------------

        Flux<SalesOrderCommonSearchOutputDTO> searchList = salesOrderMasterSearchRepository
            .getSalesOrderDetailsForSearchParametersBySalesOrderNo(conditionalKeyValue, columnKeyMapper);

//        Mono<List<Map<String, Object>>> mono = searchList.collect(ArrayList::new, List::add);;

        try {
////            System.out.println("searchList=" + mono.toFuture().get());
            System.out.println("searchList=" + searchList.collectList().toFuture().get().toString());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

//        return Flux.just(new SalesOrderCommonSearchOutputDTO());
//        searchList.subscribe(list -> {
////            // Here, you have the List<Map<String, Object>>
////            // You can perform further operations on the list
//            System.out.println("searchList=" + list);
//        });
        return null;
    }

    public Flux<SalesOrderCommonSearchOutputDTO> getSalesOrderDetailsByCombinedParameters(SalesOrderCombinedSearchInternalDTO salesOrderCommonSearchInternalDTO) {
        CommonUtilities.dtoTrimmer(salesOrderCommonSearchInternalDTO);
        return salesOrderMasterSearchRepository.getSalesOrderDetailsByCombinedParameters(
            salesOrderCommonSearchInternalDTO.getSalesOrderNo(),
            salesOrderCommonSearchInternalDTO.getSalesOrderStatus(),
//            salesOrderCommonSearchInternalDTO.getBranchID(),
            salesOrderCommonSearchInternalDTO.getCreatedByName(),
            salesOrderCommonSearchInternalDTO.getCreatedDateFromDate(),
            salesOrderCommonSearchInternalDTO.getCreatedDateToDate(),
            salesOrderCommonSearchInternalDTO.getDeliveryAddressLine1(),
            salesOrderCommonSearchInternalDTO.getDeliveryAddressLine2(),
            salesOrderCommonSearchInternalDTO.getDeliveryCity(),
            salesOrderCommonSearchInternalDTO.getDeliveryState(),
            salesOrderCommonSearchInternalDTO.getDeliveryZip(),
            salesOrderCommonSearchInternalDTO.getDropshipStatus(),
            salesOrderCommonSearchInternalDTO.getHCPCSCode(),
            salesOrderCommonSearchInternalDTO.getItemName(),
            salesOrderCommonSearchInternalDTO.getItemNo(),
            salesOrderCommonSearchInternalDTO.getPatientFirstName(),
            salesOrderCommonSearchInternalDTO.getPatientMiddleName(),
            salesOrderCommonSearchInternalDTO.getPatientLastName(),
            salesOrderCommonSearchInternalDTO.getPatientIDNo(),
            salesOrderCommonSearchInternalDTO.getScheduleDeliveryFromDate(),
            salesOrderCommonSearchInternalDTO.getScheduleDeliveryToDate(),
            salesOrderCommonSearchInternalDTO.getPrimaryPayerName(),
            salesOrderCommonSearchInternalDTO.getSalesOrderUUID(),
            salesOrderCommonSearchInternalDTO.getBranchName(),
            salesOrderCommonSearchInternalDTO.getDeliveryActualDateStart(),
            salesOrderCommonSearchInternalDTO.getDeliveryActualDateEnd()
        );
    }
}
