package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.SalesOrderItemDetails;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.SalesOrderItemDetailsRepository;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderItemDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderItemDetailsMapper;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link SalesOrderItemDetailsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class SalesOrderItemDetailsResourceIT {

    private static final Long DEFAULT_SALES_ORDER_ID = 1L;
    private static final Long UPDATED_SALES_ORDER_ID = 2L;

    private static final Long DEFAULT_PATIENT_ID = 1L;
    private static final Long UPDATED_PATIENT_ID = 2L;

    private static final Long DEFAULT_ITEM_LOCATION_ID = 1L;
    private static final Long UPDATED_ITEM_LOCATION_ID = 2L;

    private static final Long DEFAULT_SALES_ORDER_DETAILS_ITEM_ID = 1L;
    private static final Long UPDATED_SALES_ORDER_DETAILS_ITEM_ID = 2L;

    private static final String DEFAULT_SALES_ORDER_DETAILS_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_ITEM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_STOCKING_UOM = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_STOCKING_UOM = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_ASSET_NO = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_ASSET_NO = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_ITEM_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_ITEM_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_DEFAULT_VENDOR = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_DEFAULT_VENDOR = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SALES_ORDER_DETAILS_ORIGINAL_DOS = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SALES_ORDER_DETAILS_ORIGINAL_DOS = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_SALES_ORDER_DETAILS_PREVIOUS_BILLING_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SALES_ORDER_DETAILS_PREVIOUS_BILLING_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_SALES_ORDER_DETAILS_NEXT_BILLING_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SALES_ORDER_DETAILS_NEXT_BILLING_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_SALES_ORDER_DETAILS_DOS_TO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SALES_ORDER_DETAILS_DOS_TO = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SALES_ORDER_DETAILS_NEXT_PERIOD = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_NEXT_PERIOD = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_SPECIAL_PRICING = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_SPECIAL_PRICING = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_PRICE_OVERRIDE = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_PRICE_OVERRIDE = "BBBBBBBBBB";

    private static final Long DEFAULT_SALES_ORDER_DETAILS_SPECIAL_TAX_RATE = 1L;
    private static final Long UPDATED_SALES_ORDER_DETAILS_SPECIAL_TAX_RATE = 2L;

    private static final Long DEFAULT_SALES_ORDER_DETAILS_QTY = 1L;
    private static final Long UPDATED_SALES_ORDER_DETAILS_QTY = 2L;

    private static final Long DEFAULT_SALES_ORDER_DETAILS_BQTY = 1L;
    private static final Long UPDATED_SALES_ORDER_DETAILS_BQTY = 2L;

    private static final Long DEFAULT_SALES_ORDER_DETAILS_LINE_QTY = 1L;
    private static final Long UPDATED_SALES_ORDER_DETAILS_LINE_QTY = 2L;

    private static final String DEFAULT_SALES_ORDER_DETAILS_PROC_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_PROC_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_PRICE_OPTION = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_PRICE_OPTION = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_MODIFIER_1 = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_MODIFIER_1 = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_MODIFIER_2 = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_MODIFIER_2 = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_MODIFIER_3 = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_MODIFIER_3 = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_MODIFIER_4 = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_MODIFIER_4 = "BBBBBBBBBB";

    private static final Double DEFAULT_SALES_ORDER_DETAILS_CHARGE_AMT = 1D;
    private static final Double UPDATED_SALES_ORDER_DETAILS_CHARGE_AMT = 2D;

    private static final Double DEFAULT_SALES_ORDER_DETAILS_ALLOWED_AMT = 1D;
    private static final Double UPDATED_SALES_ORDER_DETAILS_ALLOWED_AMT = 2D;

    private static final String DEFAULT_SALES_ORDER_DETAILS_TAXABLE = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_TAXABLE = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_ABN = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_ABN = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_ABN_UPGRADE = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_ABN_UPGRADE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SALES_ORDER_DETAILS_ABN_PRINT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SALES_ORDER_DETAILS_ABN_PRINT_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SALES_ORDER_DETAILS_ABN_ITEM = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_ABN_ITEM = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_ABN_PROC_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_ABN_PROC_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_ABN_ALLOW = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_ABN_ALLOW = "BBBBBBBBBB";

    private static final Double DEFAULT_SALES_ORDER_DETAILS_ABN_CHARGE = 1D;
    private static final Double UPDATED_SALES_ORDER_DETAILS_ABN_CHARGE = 2D;

    private static final String DEFAULT_SALES_ORDER_DETAILS_ABN_MODIFIER_1 = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_ABN_MODIFIER_1 = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_ABN_MODIFIER_2 = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_ABN_MODIFIER_2 = "BBBBBBBBBB";

    private static final Long DEFAULT_SALES_ORDER_DETAILS_TAX_RATE = 1L;
    private static final Long UPDATED_SALES_ORDER_DETAILS_TAX_RATE = 2L;

    private static final String DEFAULT_SALES_ORDER_DETAILS_TAX_ZONE = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_TAX_ZONE = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_NON_TAX_REASON = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_NON_TAX_REASON = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_NOTE = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_SALE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_SALE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_ITEM_GROUP = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_ITEM_GROUP = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_ITEM_USER_1 = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_ITEM_USER_1 = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_ITEM_USER_2 = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_ITEM_USER_2 = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_ITEM_USER_3 = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_ITEM_USER_3 = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_ITEM_USER_4 = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_ITEM_USER_4 = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_CONVERTED_TO_PURCHASE = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_CONVERTED_TO_PURCHASE = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_MANUAL_CONVERT_TO_PURCHASE_MCTP = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_MANUAL_CONVERT_TO_PURCHASE_MCTP = "BBBBBBBBBB";

    private static final Double DEFAULT_SALES_ORDER_DETAILS_MCTP_CHARGE_AMT = 1D;
    private static final Double UPDATED_SALES_ORDER_DETAILS_MCTP_CHARGE_AMT = 2D;

    private static final Double DEFAULT_SALES_ORDER_DETAILS_MCTP_ALLOWED_AMT = 1D;
    private static final Double UPDATED_SALES_ORDER_DETAILS_MCTP_ALLOWED_AMT = 2D;

    private static final String DEFAULT_SALES_ORDER_DETAILS_MCTP_MODIFIER_1 = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_MCTP_MODIFIER_1 = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_MCTP_MODIFIER_2 = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_MCTP_MODIFIER_2 = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_MCTP_MODIFIER_3 = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_MCTP_MODIFIER_3 = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_MCTP_MODIFIER_4 = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_MCTP_MODIFIER_4 = "BBBBBBBBBB";

    private static final Long DEFAULT_SALES_ORDER_DETAILS_MCTP_PERIOD = 1L;
    private static final Long UPDATED_SALES_ORDER_DETAILS_MCTP_PERIOD = 2L;

    private static final String DEFAULT_SALES_ORDER_DETAILS_ADDTL_MODIFIER_1 = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_ADDTL_MODIFIER_1 = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_ADDTL_MODIFIER_2 = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_ADDTL_MODIFIER_2 = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_ADDTL_MODIFIER_3 = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_ADDTL_MODIFIER_3 = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_ADDTL_MODIFIER_4 = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_ADDTL_MODIFIER_4 = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SALES_ORDER_DETAILS_NEXT_DATE_OF_SERVICE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SALES_ORDER_DETAILS_NEXT_DATE_OF_SERVICE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SALES_ORDER_DETAILS_PRICE_TABLE = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_PRICE_TABLE = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_PRICE_OPTION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_PRICE_OPTION_NAME = "BBBBBBBBBB";

    private static final Double DEFAULT_SALES_ORDER_DETAILS_EXTENDED_CHARGE_AMOUNT = 1D;
    private static final Double UPDATED_SALES_ORDER_DETAILS_EXTENDED_CHARGE_AMOUNT = 2D;

    private static final Double DEFAULT_SALES_ORDER_DETAILS_EXTENDED_ALLOWANCE_AMOUNT = 1D;
    private static final Double UPDATED_SALES_ORDER_DETAILS_EXTENDED_ALLOWANCE_AMOUNT = 2D;

    private static final String DEFAULT_SALES_ORDER_DETAILS_ITEM_NDC_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_ITEM_NDC_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_MANUFACTURER = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_MANUFACTURER = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_CB_PRICING = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_CB_PRICING = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_CB_PRICE_TABLE_OVERRIDE = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_CB_PRICE_TABLE_OVERRIDE = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_CB_OVERRIDE = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_CB_OVERRIDE = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_MESSAGES = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_MESSAGES = "BBBBBBBBBB";

    private static final Long DEFAULT_SALES_ORDER_DETAILS_LOCATION = 1L;
    private static final Long UPDATED_SALES_ORDER_DETAILS_LOCATION = 2L;

    private static final Long DEFAULT_SALES_ORDER_DETAILS_CALORIES_PER_DAY = 1L;
    private static final Long UPDATED_SALES_ORDER_DETAILS_CALORIES_PER_DAY = 2L;

    private static final String DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_PROCUDURE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_PROCUDURE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_PRICE_OPTION = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_PRICE_OPTION = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_PRICE_OPTION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_PRICE_OPTION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_1 = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_1 = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_2 = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_2 = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_3 = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_3 = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_4 = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_4 = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_1 = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_1 = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_2 = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_2 = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_3 = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_3 = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_4 = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_4 = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_IGNORE = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_IGNORE = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_SECONDARY_SPECIAL_BILLING = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_SECONDARY_SPECIAL_BILLING = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_SPAN_DATE_SPLIT_BILLING = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_SPAN_DATE_SPLIT_BILLING = "BBBBBBBBBB";

    private static final Long DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_FORM_ID = 1L;
    private static final Long UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_FORM_ID = 2L;

    private static final String DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_KEY = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_KEY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_CREATE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_CREATE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_EXPIRATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_EXPIRATION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_INITIAL_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_INITIAL_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_RENEWAL_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_RENEWAL_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_RECERTIFICATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_RECERTIFICATION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_PHYSICIAN_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_PHYSICIAN_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_CMNPAR_PAR_ID = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_CMNPAR_PAR_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_CMNPAR_PAR_DESCR = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_CMNPAR_PAR_DESCR = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SALES_ORDER_DETAILS_CMNPAR_PAR_INITIAL_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SALES_ORDER_DETAILS_CMNPAR_PAR_INITIAL_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_SALES_ORDER_DETAILS_CMNPAR_PAR_EXPIRATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SALES_ORDER_DETAILS_CMNPAR_PAR_EXPIRATION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_LOG_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_LOG_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_LENGTH_OF_NEED = 1L;
    private static final Long UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_LENGTH_OF_NEED = 2L;

    private static final LocalDate DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_PRINTED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_PRINTED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_PRINTED_BY = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_PRINTED_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SALES_ORDER_DETAILS_CMNPAR_FAXED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SALES_ORDER_DETAILS_CMNPAR_FAXED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_PLACEHOLDER = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_PLACEHOLDER = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_FAXED_BY = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_FAXED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_LOGGED_BY = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_LOGGED_BY = "BBBBBBBBBB";

    private static final Long DEFAULT_SALES_ORDER_DETAILS_CMNPAR_NUMBER_OF_REFILLS = 1L;
    private static final Long UPDATED_SALES_ORDER_DETAILS_CMNPAR_NUMBER_OF_REFILLS = 2L;

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SALES_ORDER_DETAILS_MANUFACTURER_ITEM_ID_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_MANUFACTURER_ITEM_ID_NUMBER = "BBBBBBBBBB";

    private static final Long DEFAULT_CMN_ID = 1L;
    private static final Long UPDATED_CMN_ID = 2L;

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final UUID DEFAULT_SALES_ORDER_ITEM_DETAILS_UUID = UUID.randomUUID();
    private static final UUID UPDATED_SALES_ORDER_ITEM_DETAILS_UUID = UUID.randomUUID();

    private static final String DEFAULT_SALES_ORDER_ITEM_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_ITEM_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_IS_ASSET_TAGGED = "AAAAAAAAAA";
    private static final String UPDATED_IS_ASSET_TAGGED = "BBBBBBBBBB";

    private static final Long DEFAULT_ITEM_SERIAL_NO = 1L;
    private static final Long UPDATED_ITEM_SERIAL_NO = 2L;

    private static final String DEFAULT_SALES_ORDER_DETAILS_ICD_POINTER = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_ICD_POINTER = "BBBBBBBBBB";

    private static final String DEFAULT_PROCEDURE_IDENTIFIER = "AAAAAAAAAA";
    private static final String UPDATED_PROCEDURE_IDENTIFIER = "BBBBBBBBBB";

    private static final String DEFAULT_PAR_NO = "AAAAAAAAAA";
    private static final String UPDATED_PAR_NO = "BBBBBBBBBB";

    private static final String DEFAULT_WHETHER_SERIALISED = "AAAAAAAAAA";
    private static final String UPDATED_WHETHER_SERIALISED = "BBBBBBBBBB";

    private static final String DEFAULT_PICKUP_EXCHANGE_NO = "AAAAAAAAAA";
    private static final String UPDATED_PICKUP_EXCHANGE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_ABN_USER_RESPONSE = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_ABN_USER_RESPONSE = "BBBBBBBBBB";

    private static final String DEFAULT_IS_DROPSHIP_ALLOWED = "AAAAAAAAAA";
    private static final String UPDATED_IS_DROPSHIP_ALLOWED = "BBBBBBBBBB";

    private static final String DEFAULT_PO_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PO_NUMBER = "BBBBBBBBBB";

    private static final UUID DEFAULT_PURCHASE_ORDER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_PURCHASE_ORDER_UUID = UUID.randomUUID();

    private static final String DEFAULT_IS_RESUPPLY_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_IS_RESUPPLY_TYPE = "BBBBBBBBBB";

    private static final Long DEFAULT_FREQUENCY_COUNT = 1L;
    private static final Long UPDATED_FREQUENCY_COUNT = 2L;

    private static final Long DEFAULT_FREQUENCY_INTERVAL = 1L;
    private static final Long UPDATED_FREQUENCY_INTERVAL = 2L;

    private static final Long DEFAULT_ITEM_GROUP_ID = 1L;
    private static final Long UPDATED_ITEM_GROUP_ID = 2L;

    private static final String ENTITY_API_URL = "/api/sales-order-item-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{salesOrderItemDetailsId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SalesOrderItemDetailsRepository salesOrderItemDetailsRepository;

    @Autowired
    private SalesOrderItemDetailsMapper salesOrderItemDetailsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private SalesOrderItemDetails salesOrderItemDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesOrderItemDetails createEntity(EntityManager em) {
        SalesOrderItemDetails salesOrderItemDetails = new SalesOrderItemDetails()
            .salesOrderId(DEFAULT_SALES_ORDER_ID)
            .patientId(DEFAULT_PATIENT_ID)
            .itemLocationId(DEFAULT_ITEM_LOCATION_ID)
            .salesOrderDetailsItemId(DEFAULT_SALES_ORDER_DETAILS_ITEM_ID)
            .salesOrderDetailsItemName(DEFAULT_SALES_ORDER_DETAILS_ITEM_NAME)
            .salesOrderDetailsStockingUom(DEFAULT_SALES_ORDER_DETAILS_STOCKING_UOM)
            .itemAssetNo(DEFAULT_ITEM_ASSET_NO)
            .salesOrderDetailsItemDescription(DEFAULT_SALES_ORDER_DETAILS_ITEM_DESCRIPTION)
            .salesOrderDetailsDefaultVendor(DEFAULT_SALES_ORDER_DETAILS_DEFAULT_VENDOR)
            .salesOrderDetailsOriginalDos(DEFAULT_SALES_ORDER_DETAILS_ORIGINAL_DOS)
            .salesOrderDetailsPreviousBillingDate(DEFAULT_SALES_ORDER_DETAILS_PREVIOUS_BILLING_DATE)
            .salesOrderDetailsNextBillingDate(DEFAULT_SALES_ORDER_DETAILS_NEXT_BILLING_DATE)
            .salesOrderDetailsDosTo(DEFAULT_SALES_ORDER_DETAILS_DOS_TO)
            .salesOrderDetailsNextPeriod(DEFAULT_SALES_ORDER_DETAILS_NEXT_PERIOD)
            .salesOrderDetailsSpecialPricing(DEFAULT_SALES_ORDER_DETAILS_SPECIAL_PRICING)
            .salesOrderDetailsPriceOverride(DEFAULT_SALES_ORDER_DETAILS_PRICE_OVERRIDE)
            .salesOrderDetailsSpecialTaxRate(DEFAULT_SALES_ORDER_DETAILS_SPECIAL_TAX_RATE)
            .salesOrderDetailsQty(DEFAULT_SALES_ORDER_DETAILS_QTY)
            .salesOrderDetailsBqty(DEFAULT_SALES_ORDER_DETAILS_BQTY)
            .salesOrderDetailsLineQty(DEFAULT_SALES_ORDER_DETAILS_LINE_QTY)
            .salesOrderDetailsProcCode(DEFAULT_SALES_ORDER_DETAILS_PROC_CODE)
            .salesOrderDetailsPriceOption(DEFAULT_SALES_ORDER_DETAILS_PRICE_OPTION)
            .salesOrderDetailsModifier1(DEFAULT_SALES_ORDER_DETAILS_MODIFIER_1)
            .salesOrderDetailsModifier2(DEFAULT_SALES_ORDER_DETAILS_MODIFIER_2)
            .salesOrderDetailsModifier3(DEFAULT_SALES_ORDER_DETAILS_MODIFIER_3)
            .salesOrderDetailsModifier4(DEFAULT_SALES_ORDER_DETAILS_MODIFIER_4)
            .salesOrderDetailsChargeAmt(DEFAULT_SALES_ORDER_DETAILS_CHARGE_AMT)
            .salesOrderDetailsAllowedAmt(DEFAULT_SALES_ORDER_DETAILS_ALLOWED_AMT)
            .salesOrderDetailsTaxable(DEFAULT_SALES_ORDER_DETAILS_TAXABLE)
            .salesOrderDetailsAbn(DEFAULT_SALES_ORDER_DETAILS_ABN)
            .salesOrderDetailsAbnUpgrade(DEFAULT_SALES_ORDER_DETAILS_ABN_UPGRADE)
            .salesOrderDetailsAbnPrintDate(DEFAULT_SALES_ORDER_DETAILS_ABN_PRINT_DATE)
            .salesOrderDetailsAbnItem(DEFAULT_SALES_ORDER_DETAILS_ABN_ITEM)
            .salesOrderDetailsAbnProcCode(DEFAULT_SALES_ORDER_DETAILS_ABN_PROC_CODE)
            .salesOrderDetailsAbnAllow(DEFAULT_SALES_ORDER_DETAILS_ABN_ALLOW)
            .salesOrderDetailsAbnCharge(DEFAULT_SALES_ORDER_DETAILS_ABN_CHARGE)
            .salesOrderDetailsAbnModifier1(DEFAULT_SALES_ORDER_DETAILS_ABN_MODIFIER_1)
            .salesOrderDetailsAbnModifier2(DEFAULT_SALES_ORDER_DETAILS_ABN_MODIFIER_2)
            .salesOrderDetailsTaxRate(DEFAULT_SALES_ORDER_DETAILS_TAX_RATE)
            .salesOrderDetailsTaxZone(DEFAULT_SALES_ORDER_DETAILS_TAX_ZONE)
            .salesOrderDetailsNonTaxReason(DEFAULT_SALES_ORDER_DETAILS_NON_TAX_REASON)
            .salesOrderDetailsNote(DEFAULT_SALES_ORDER_DETAILS_NOTE)
            .salesOrderDetailsSaleType(DEFAULT_SALES_ORDER_DETAILS_SALE_TYPE)
            .salesOrderDetailsItemGroup(DEFAULT_SALES_ORDER_DETAILS_ITEM_GROUP)
            .salesOrderDetailsItemUser1(DEFAULT_SALES_ORDER_DETAILS_ITEM_USER_1)
            .salesOrderDetailsItemUser2(DEFAULT_SALES_ORDER_DETAILS_ITEM_USER_2)
            .salesOrderDetailsItemUser3(DEFAULT_SALES_ORDER_DETAILS_ITEM_USER_3)
            .salesOrderDetailsItemUser4(DEFAULT_SALES_ORDER_DETAILS_ITEM_USER_4)
            .salesOrderDetailsConvertedToPurchase(DEFAULT_SALES_ORDER_DETAILS_CONVERTED_TO_PURCHASE)
            .salesOrderDetailsManualConvertToPurchaseMctp(DEFAULT_SALES_ORDER_DETAILS_MANUAL_CONVERT_TO_PURCHASE_MCTP)
            .salesOrderDetailsMctpChargeAmt(DEFAULT_SALES_ORDER_DETAILS_MCTP_CHARGE_AMT)
            .salesOrderDetailsMctpAllowedAmt(DEFAULT_SALES_ORDER_DETAILS_MCTP_ALLOWED_AMT)
            .salesOrderDetailsMctpModifier1(DEFAULT_SALES_ORDER_DETAILS_MCTP_MODIFIER_1)
            .salesOrderDetailsMctpModifier2(DEFAULT_SALES_ORDER_DETAILS_MCTP_MODIFIER_2)
            .salesOrderDetailsMctpModifier3(DEFAULT_SALES_ORDER_DETAILS_MCTP_MODIFIER_3)
            .salesOrderDetailsMctpModifier4(DEFAULT_SALES_ORDER_DETAILS_MCTP_MODIFIER_4)
            .salesOrderDetailsMctpPeriod(DEFAULT_SALES_ORDER_DETAILS_MCTP_PERIOD)
            .salesOrderDetailsAddtlModifier1(DEFAULT_SALES_ORDER_DETAILS_ADDTL_MODIFIER_1)
            .salesOrderDetailsAddtlModifier2(DEFAULT_SALES_ORDER_DETAILS_ADDTL_MODIFIER_2)
            .salesOrderDetailsAddtlModifier3(DEFAULT_SALES_ORDER_DETAILS_ADDTL_MODIFIER_3)
            .salesOrderDetailsAddtlModifier4(DEFAULT_SALES_ORDER_DETAILS_ADDTL_MODIFIER_4)
            .salesOrderDetailsNextDateOfService(DEFAULT_SALES_ORDER_DETAILS_NEXT_DATE_OF_SERVICE)
            .salesOrderDetailsPriceTable(DEFAULT_SALES_ORDER_DETAILS_PRICE_TABLE)
            .salesOrderDetailsPriceOptionName(DEFAULT_SALES_ORDER_DETAILS_PRICE_OPTION_NAME)
            .salesOrderDetailsExtendedChargeAmount(DEFAULT_SALES_ORDER_DETAILS_EXTENDED_CHARGE_AMOUNT)
            .salesOrderDetailsExtendedAllowanceAmount(DEFAULT_SALES_ORDER_DETAILS_EXTENDED_ALLOWANCE_AMOUNT)
            .salesOrderDetailsItemNdcCode(DEFAULT_SALES_ORDER_DETAILS_ITEM_NDC_CODE)
            .salesOrderDetailsManufacturer(DEFAULT_SALES_ORDER_DETAILS_MANUFACTURER)
            .salesOrderDetailsCbPricing(DEFAULT_SALES_ORDER_DETAILS_CB_PRICING)
            .salesOrderDetailsCbPriceTableOverride(DEFAULT_SALES_ORDER_DETAILS_CB_PRICE_TABLE_OVERRIDE)
            .salesOrderDetailsCbOverride(DEFAULT_SALES_ORDER_DETAILS_CB_OVERRIDE)
            .salesOrderDetailsMessages(DEFAULT_SALES_ORDER_DETAILS_MESSAGES)
            .salesOrderDetailsLocation(DEFAULT_SALES_ORDER_DETAILS_LOCATION)
            .salesOrderDetailsCaloriesPerDay(DEFAULT_SALES_ORDER_DETAILS_CALORIES_PER_DAY)
            .salesOrderDetailsSecondaryBillingProcudureCode(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_PROCUDURE_CODE)
            .salesOrderDetailsSecondaryBillingPriceOption(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_PRICE_OPTION)
            .salesOrderDetailsSecondaryBillingPriceOptionName(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_PRICE_OPTION_NAME)
            .salesOrderDetailsSecondaryBillingModifier1(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_1)
            .salesOrderDetailsSecondaryBillingModifier2(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_2)
            .salesOrderDetailsSecondaryBillingModifier3(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_3)
            .salesOrderDetailsSecondaryBillingModifier4(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_4)
            .salesOrderDetailsSecondaryBillingAdditionalModifier1(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_1)
            .salesOrderDetailsSecondaryBillingAdditionalModifier2(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_2)
            .salesOrderDetailsSecondaryBillingAdditionalModifier3(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_3)
            .salesOrderDetailsSecondaryBillingAdditionalModifier4(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_4)
            .salesOrderDetailsSecondaryBillingIgnore(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_IGNORE)
            .salesOrderDetailsSecondarySpecialBilling(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_SPECIAL_BILLING)
            .salesOrderDetailsSpanDateSplitBilling(DEFAULT_SALES_ORDER_DETAILS_SPAN_DATE_SPLIT_BILLING)
            .salesOrderDetailsCmnparCmnFormId(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_FORM_ID)
            .salesOrderDetailsCmnparCmnKey(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_KEY)
            .salesOrderDetailsCmnparCmnCreateDate(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_CREATE_DATE)
            .salesOrderDetailsCmnparCmnExpirationDate(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_EXPIRATION_DATE)
            .salesOrderDetailsCmnparCmnInitialDate(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_INITIAL_DATE)
            .salesOrderDetailsCmnparCmnRenewalDate(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_RENEWAL_DATE)
            .salesOrderDetailsCmnparCmnRecertificationDate(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_RECERTIFICATION_DATE)
            .salesOrderDetailsCmnparCmnPhysicianDate(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_PHYSICIAN_DATE)
            .salesOrderDetailsCmnparCmnStatus(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_STATUS)
            .salesOrderDetailsCmnparParId(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_PAR_ID)
            .salesOrderDetailsCmnparParDescr(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_PAR_DESCR)
            .salesOrderDetailsCmnparParInitialDate(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_PAR_INITIAL_DATE)
            .salesOrderDetailsCmnparParExpirationDate(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_PAR_EXPIRATION_DATE)
            .salesOrderDetailsCmnparCmnLogDate(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_LOG_DATE)
            .salesOrderDetailsCmnparCmnLengthOfNeed(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_LENGTH_OF_NEED)
            .salesOrderDetailsCmnparCmnPrintedDate(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_PRINTED_DATE)
            .salesOrderDetailsCmnparCmnPrintedBy(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_PRINTED_BY)
            .salesOrderDetailsCmnparFaxedDate(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_FAXED_DATE)
            .salesOrderDetailsCmnparCmnPlaceholder(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_PLACEHOLDER)
            .salesOrderDetailsCmnparCmnFaxedBy(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_FAXED_BY)
            .salesOrderDetailsCmnparCmnLoggedBy(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_LOGGED_BY)
            .salesOrderDetailsCmnparNumberOfRefills(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_NUMBER_OF_REFILLS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .status(DEFAULT_STATUS)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .salesOrderDetailsManufacturerItemIdNumber(DEFAULT_SALES_ORDER_DETAILS_MANUFACTURER_ITEM_ID_NUMBER)
            .cmnId(DEFAULT_CMN_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .salesOrderItemDetailsUuid(DEFAULT_SALES_ORDER_ITEM_DETAILS_UUID)
            .salesOrderItemNumber(DEFAULT_SALES_ORDER_ITEM_NUMBER)
            .isAssetTagged(DEFAULT_IS_ASSET_TAGGED)
            .itemSerialNo(DEFAULT_ITEM_SERIAL_NO)
            .salesOrderDetailsIcdPointer(DEFAULT_SALES_ORDER_DETAILS_ICD_POINTER)
            .procedureIdentifier(DEFAULT_PROCEDURE_IDENTIFIER)
            .parNo(DEFAULT_PAR_NO)
            .whetherSerialised(DEFAULT_WHETHER_SERIALISED)
            .pickupExchangeNo(DEFAULT_PICKUP_EXCHANGE_NO)
            .salesOrderAbnUserResponse(DEFAULT_SALES_ORDER_ABN_USER_RESPONSE)
            .isDropshipAllowed(DEFAULT_IS_DROPSHIP_ALLOWED)
            .poNumber(DEFAULT_PO_NUMBER)
            .purchaseOrderUuid(DEFAULT_PURCHASE_ORDER_UUID)
            .isResupplyType(DEFAULT_IS_RESUPPLY_TYPE)
            .frequencyCount(DEFAULT_FREQUENCY_COUNT)
            .frequencyInterval(DEFAULT_FREQUENCY_INTERVAL)
            .itemGroupId(DEFAULT_ITEM_GROUP_ID);
        return salesOrderItemDetails;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesOrderItemDetails createUpdatedEntity(EntityManager em) {
        SalesOrderItemDetails salesOrderItemDetails = new SalesOrderItemDetails()
            .salesOrderId(UPDATED_SALES_ORDER_ID)
            .patientId(UPDATED_PATIENT_ID)
            .itemLocationId(UPDATED_ITEM_LOCATION_ID)
            .salesOrderDetailsItemId(UPDATED_SALES_ORDER_DETAILS_ITEM_ID)
            .salesOrderDetailsItemName(UPDATED_SALES_ORDER_DETAILS_ITEM_NAME)
            .salesOrderDetailsStockingUom(UPDATED_SALES_ORDER_DETAILS_STOCKING_UOM)
            .itemAssetNo(UPDATED_ITEM_ASSET_NO)
            .salesOrderDetailsItemDescription(UPDATED_SALES_ORDER_DETAILS_ITEM_DESCRIPTION)
            .salesOrderDetailsDefaultVendor(UPDATED_SALES_ORDER_DETAILS_DEFAULT_VENDOR)
            .salesOrderDetailsOriginalDos(UPDATED_SALES_ORDER_DETAILS_ORIGINAL_DOS)
            .salesOrderDetailsPreviousBillingDate(UPDATED_SALES_ORDER_DETAILS_PREVIOUS_BILLING_DATE)
            .salesOrderDetailsNextBillingDate(UPDATED_SALES_ORDER_DETAILS_NEXT_BILLING_DATE)
            .salesOrderDetailsDosTo(UPDATED_SALES_ORDER_DETAILS_DOS_TO)
            .salesOrderDetailsNextPeriod(UPDATED_SALES_ORDER_DETAILS_NEXT_PERIOD)
            .salesOrderDetailsSpecialPricing(UPDATED_SALES_ORDER_DETAILS_SPECIAL_PRICING)
            .salesOrderDetailsPriceOverride(UPDATED_SALES_ORDER_DETAILS_PRICE_OVERRIDE)
            .salesOrderDetailsSpecialTaxRate(UPDATED_SALES_ORDER_DETAILS_SPECIAL_TAX_RATE)
            .salesOrderDetailsQty(UPDATED_SALES_ORDER_DETAILS_QTY)
            .salesOrderDetailsBqty(UPDATED_SALES_ORDER_DETAILS_BQTY)
            .salesOrderDetailsLineQty(UPDATED_SALES_ORDER_DETAILS_LINE_QTY)
            .salesOrderDetailsProcCode(UPDATED_SALES_ORDER_DETAILS_PROC_CODE)
            .salesOrderDetailsPriceOption(UPDATED_SALES_ORDER_DETAILS_PRICE_OPTION)
            .salesOrderDetailsModifier1(UPDATED_SALES_ORDER_DETAILS_MODIFIER_1)
            .salesOrderDetailsModifier2(UPDATED_SALES_ORDER_DETAILS_MODIFIER_2)
            .salesOrderDetailsModifier3(UPDATED_SALES_ORDER_DETAILS_MODIFIER_3)
            .salesOrderDetailsModifier4(UPDATED_SALES_ORDER_DETAILS_MODIFIER_4)
            .salesOrderDetailsChargeAmt(UPDATED_SALES_ORDER_DETAILS_CHARGE_AMT)
            .salesOrderDetailsAllowedAmt(UPDATED_SALES_ORDER_DETAILS_ALLOWED_AMT)
            .salesOrderDetailsTaxable(UPDATED_SALES_ORDER_DETAILS_TAXABLE)
            .salesOrderDetailsAbn(UPDATED_SALES_ORDER_DETAILS_ABN)
            .salesOrderDetailsAbnUpgrade(UPDATED_SALES_ORDER_DETAILS_ABN_UPGRADE)
            .salesOrderDetailsAbnPrintDate(UPDATED_SALES_ORDER_DETAILS_ABN_PRINT_DATE)
            .salesOrderDetailsAbnItem(UPDATED_SALES_ORDER_DETAILS_ABN_ITEM)
            .salesOrderDetailsAbnProcCode(UPDATED_SALES_ORDER_DETAILS_ABN_PROC_CODE)
            .salesOrderDetailsAbnAllow(UPDATED_SALES_ORDER_DETAILS_ABN_ALLOW)
            .salesOrderDetailsAbnCharge(UPDATED_SALES_ORDER_DETAILS_ABN_CHARGE)
            .salesOrderDetailsAbnModifier1(UPDATED_SALES_ORDER_DETAILS_ABN_MODIFIER_1)
            .salesOrderDetailsAbnModifier2(UPDATED_SALES_ORDER_DETAILS_ABN_MODIFIER_2)
            .salesOrderDetailsTaxRate(UPDATED_SALES_ORDER_DETAILS_TAX_RATE)
            .salesOrderDetailsTaxZone(UPDATED_SALES_ORDER_DETAILS_TAX_ZONE)
            .salesOrderDetailsNonTaxReason(UPDATED_SALES_ORDER_DETAILS_NON_TAX_REASON)
            .salesOrderDetailsNote(UPDATED_SALES_ORDER_DETAILS_NOTE)
            .salesOrderDetailsSaleType(UPDATED_SALES_ORDER_DETAILS_SALE_TYPE)
            .salesOrderDetailsItemGroup(UPDATED_SALES_ORDER_DETAILS_ITEM_GROUP)
            .salesOrderDetailsItemUser1(UPDATED_SALES_ORDER_DETAILS_ITEM_USER_1)
            .salesOrderDetailsItemUser2(UPDATED_SALES_ORDER_DETAILS_ITEM_USER_2)
            .salesOrderDetailsItemUser3(UPDATED_SALES_ORDER_DETAILS_ITEM_USER_3)
            .salesOrderDetailsItemUser4(UPDATED_SALES_ORDER_DETAILS_ITEM_USER_4)
            .salesOrderDetailsConvertedToPurchase(UPDATED_SALES_ORDER_DETAILS_CONVERTED_TO_PURCHASE)
            .salesOrderDetailsManualConvertToPurchaseMctp(UPDATED_SALES_ORDER_DETAILS_MANUAL_CONVERT_TO_PURCHASE_MCTP)
            .salesOrderDetailsMctpChargeAmt(UPDATED_SALES_ORDER_DETAILS_MCTP_CHARGE_AMT)
            .salesOrderDetailsMctpAllowedAmt(UPDATED_SALES_ORDER_DETAILS_MCTP_ALLOWED_AMT)
            .salesOrderDetailsMctpModifier1(UPDATED_SALES_ORDER_DETAILS_MCTP_MODIFIER_1)
            .salesOrderDetailsMctpModifier2(UPDATED_SALES_ORDER_DETAILS_MCTP_MODIFIER_2)
            .salesOrderDetailsMctpModifier3(UPDATED_SALES_ORDER_DETAILS_MCTP_MODIFIER_3)
            .salesOrderDetailsMctpModifier4(UPDATED_SALES_ORDER_DETAILS_MCTP_MODIFIER_4)
            .salesOrderDetailsMctpPeriod(UPDATED_SALES_ORDER_DETAILS_MCTP_PERIOD)
            .salesOrderDetailsAddtlModifier1(UPDATED_SALES_ORDER_DETAILS_ADDTL_MODIFIER_1)
            .salesOrderDetailsAddtlModifier2(UPDATED_SALES_ORDER_DETAILS_ADDTL_MODIFIER_2)
            .salesOrderDetailsAddtlModifier3(UPDATED_SALES_ORDER_DETAILS_ADDTL_MODIFIER_3)
            .salesOrderDetailsAddtlModifier4(UPDATED_SALES_ORDER_DETAILS_ADDTL_MODIFIER_4)
            .salesOrderDetailsNextDateOfService(UPDATED_SALES_ORDER_DETAILS_NEXT_DATE_OF_SERVICE)
            .salesOrderDetailsPriceTable(UPDATED_SALES_ORDER_DETAILS_PRICE_TABLE)
            .salesOrderDetailsPriceOptionName(UPDATED_SALES_ORDER_DETAILS_PRICE_OPTION_NAME)
            .salesOrderDetailsExtendedChargeAmount(UPDATED_SALES_ORDER_DETAILS_EXTENDED_CHARGE_AMOUNT)
            .salesOrderDetailsExtendedAllowanceAmount(UPDATED_SALES_ORDER_DETAILS_EXTENDED_ALLOWANCE_AMOUNT)
            .salesOrderDetailsItemNdcCode(UPDATED_SALES_ORDER_DETAILS_ITEM_NDC_CODE)
            .salesOrderDetailsManufacturer(UPDATED_SALES_ORDER_DETAILS_MANUFACTURER)
            .salesOrderDetailsCbPricing(UPDATED_SALES_ORDER_DETAILS_CB_PRICING)
            .salesOrderDetailsCbPriceTableOverride(UPDATED_SALES_ORDER_DETAILS_CB_PRICE_TABLE_OVERRIDE)
            .salesOrderDetailsCbOverride(UPDATED_SALES_ORDER_DETAILS_CB_OVERRIDE)
            .salesOrderDetailsMessages(UPDATED_SALES_ORDER_DETAILS_MESSAGES)
            .salesOrderDetailsLocation(UPDATED_SALES_ORDER_DETAILS_LOCATION)
            .salesOrderDetailsCaloriesPerDay(UPDATED_SALES_ORDER_DETAILS_CALORIES_PER_DAY)
            .salesOrderDetailsSecondaryBillingProcudureCode(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_PROCUDURE_CODE)
            .salesOrderDetailsSecondaryBillingPriceOption(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_PRICE_OPTION)
            .salesOrderDetailsSecondaryBillingPriceOptionName(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_PRICE_OPTION_NAME)
            .salesOrderDetailsSecondaryBillingModifier1(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_1)
            .salesOrderDetailsSecondaryBillingModifier2(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_2)
            .salesOrderDetailsSecondaryBillingModifier3(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_3)
            .salesOrderDetailsSecondaryBillingModifier4(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_4)
            .salesOrderDetailsSecondaryBillingAdditionalModifier1(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_1)
            .salesOrderDetailsSecondaryBillingAdditionalModifier2(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_2)
            .salesOrderDetailsSecondaryBillingAdditionalModifier3(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_3)
            .salesOrderDetailsSecondaryBillingAdditionalModifier4(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_4)
            .salesOrderDetailsSecondaryBillingIgnore(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_IGNORE)
            .salesOrderDetailsSecondarySpecialBilling(UPDATED_SALES_ORDER_DETAILS_SECONDARY_SPECIAL_BILLING)
            .salesOrderDetailsSpanDateSplitBilling(UPDATED_SALES_ORDER_DETAILS_SPAN_DATE_SPLIT_BILLING)
            .salesOrderDetailsCmnparCmnFormId(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_FORM_ID)
            .salesOrderDetailsCmnparCmnKey(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_KEY)
            .salesOrderDetailsCmnparCmnCreateDate(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_CREATE_DATE)
            .salesOrderDetailsCmnparCmnExpirationDate(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_EXPIRATION_DATE)
            .salesOrderDetailsCmnparCmnInitialDate(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_INITIAL_DATE)
            .salesOrderDetailsCmnparCmnRenewalDate(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_RENEWAL_DATE)
            .salesOrderDetailsCmnparCmnRecertificationDate(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_RECERTIFICATION_DATE)
            .salesOrderDetailsCmnparCmnPhysicianDate(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_PHYSICIAN_DATE)
            .salesOrderDetailsCmnparCmnStatus(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_STATUS)
            .salesOrderDetailsCmnparParId(UPDATED_SALES_ORDER_DETAILS_CMNPAR_PAR_ID)
            .salesOrderDetailsCmnparParDescr(UPDATED_SALES_ORDER_DETAILS_CMNPAR_PAR_DESCR)
            .salesOrderDetailsCmnparParInitialDate(UPDATED_SALES_ORDER_DETAILS_CMNPAR_PAR_INITIAL_DATE)
            .salesOrderDetailsCmnparParExpirationDate(UPDATED_SALES_ORDER_DETAILS_CMNPAR_PAR_EXPIRATION_DATE)
            .salesOrderDetailsCmnparCmnLogDate(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_LOG_DATE)
            .salesOrderDetailsCmnparCmnLengthOfNeed(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_LENGTH_OF_NEED)
            .salesOrderDetailsCmnparCmnPrintedDate(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_PRINTED_DATE)
            .salesOrderDetailsCmnparCmnPrintedBy(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_PRINTED_BY)
            .salesOrderDetailsCmnparFaxedDate(UPDATED_SALES_ORDER_DETAILS_CMNPAR_FAXED_DATE)
            .salesOrderDetailsCmnparCmnPlaceholder(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_PLACEHOLDER)
            .salesOrderDetailsCmnparCmnFaxedBy(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_FAXED_BY)
            .salesOrderDetailsCmnparCmnLoggedBy(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_LOGGED_BY)
            .salesOrderDetailsCmnparNumberOfRefills(UPDATED_SALES_ORDER_DETAILS_CMNPAR_NUMBER_OF_REFILLS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .salesOrderDetailsManufacturerItemIdNumber(UPDATED_SALES_ORDER_DETAILS_MANUFACTURER_ITEM_ID_NUMBER)
            .cmnId(UPDATED_CMN_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .salesOrderItemDetailsUuid(UPDATED_SALES_ORDER_ITEM_DETAILS_UUID)
            .salesOrderItemNumber(UPDATED_SALES_ORDER_ITEM_NUMBER)
            .isAssetTagged(UPDATED_IS_ASSET_TAGGED)
            .itemSerialNo(UPDATED_ITEM_SERIAL_NO)
            .salesOrderDetailsIcdPointer(UPDATED_SALES_ORDER_DETAILS_ICD_POINTER)
            .procedureIdentifier(UPDATED_PROCEDURE_IDENTIFIER)
            .parNo(UPDATED_PAR_NO)
            .whetherSerialised(UPDATED_WHETHER_SERIALISED)
            .pickupExchangeNo(UPDATED_PICKUP_EXCHANGE_NO)
            .salesOrderAbnUserResponse(UPDATED_SALES_ORDER_ABN_USER_RESPONSE)
            .isDropshipAllowed(UPDATED_IS_DROPSHIP_ALLOWED)
            .poNumber(UPDATED_PO_NUMBER)
            .purchaseOrderUuid(UPDATED_PURCHASE_ORDER_UUID)
            .isResupplyType(UPDATED_IS_RESUPPLY_TYPE)
            .frequencyCount(UPDATED_FREQUENCY_COUNT)
            .frequencyInterval(UPDATED_FREQUENCY_INTERVAL)
            .itemGroupId(UPDATED_ITEM_GROUP_ID);
        return salesOrderItemDetails;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(SalesOrderItemDetails.class).block();
        } catch (Exception e) {
            // It can fail, if other entities are still referring this - it will be removed later.
        }
    }

    @AfterEach
    public void cleanup() {
        deleteEntities(em);
    }

    @BeforeEach
    public void setupCsrf() {
        webTestClient = webTestClient.mutateWith(csrf());
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        salesOrderItemDetails = createEntity(em);
    }

    @Test
    void createSalesOrderItemDetails() throws Exception {
        int databaseSizeBeforeCreate = salesOrderItemDetailsRepository.findAll().collectList().block().size();
        // Create the SalesOrderItemDetails
        SalesOrderItemDetailsDTO salesOrderItemDetailsDTO = salesOrderItemDetailsMapper.toDto(salesOrderItemDetails);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderItemDetailsDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the SalesOrderItemDetails in the database
        List<SalesOrderItemDetails> salesOrderItemDetailsList = salesOrderItemDetailsRepository.findAll().collectList().block();
        assertThat(salesOrderItemDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        SalesOrderItemDetails testSalesOrderItemDetails = salesOrderItemDetailsList.get(salesOrderItemDetailsList.size() - 1);
        assertThat(testSalesOrderItemDetails.getSalesOrderId()).isEqualTo(DEFAULT_SALES_ORDER_ID);
        assertThat(testSalesOrderItemDetails.getPatientId()).isEqualTo(DEFAULT_PATIENT_ID);
        assertThat(testSalesOrderItemDetails.getItemLocationId()).isEqualTo(DEFAULT_ITEM_LOCATION_ID);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsItemId()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ITEM_ID);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsItemName()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ITEM_NAME);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsStockingUom()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_STOCKING_UOM);
        assertThat(testSalesOrderItemDetails.getItemAssetNo()).isEqualTo(DEFAULT_ITEM_ASSET_NO);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsItemDescription()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ITEM_DESCRIPTION);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsDefaultVendor()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_DEFAULT_VENDOR);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsOriginalDos()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ORIGINAL_DOS);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsPreviousBillingDate())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_PREVIOUS_BILLING_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsNextBillingDate())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_NEXT_BILLING_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsDosTo()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_DOS_TO);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsNextPeriod()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_NEXT_PERIOD);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSpecialPricing()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_SPECIAL_PRICING);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsPriceOverride()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_PRICE_OVERRIDE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSpecialTaxRate()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_SPECIAL_TAX_RATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsQty()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_QTY);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsBqty()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_BQTY);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsLineQty()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_LINE_QTY);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsProcCode()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_PROC_CODE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsPriceOption()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_PRICE_OPTION);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsModifier1()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_MODIFIER_1);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsModifier2()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_MODIFIER_2);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsModifier3()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_MODIFIER_3);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsModifier4()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_MODIFIER_4);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsChargeAmt()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CHARGE_AMT);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAllowedAmt()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ALLOWED_AMT);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsTaxable()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_TAXABLE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAbn()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ABN);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAbnUpgrade()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ABN_UPGRADE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAbnPrintDate()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ABN_PRINT_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAbnItem()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ABN_ITEM);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAbnProcCode()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ABN_PROC_CODE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAbnAllow()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ABN_ALLOW);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAbnCharge()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ABN_CHARGE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAbnModifier1()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ABN_MODIFIER_1);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAbnModifier2()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ABN_MODIFIER_2);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsTaxRate()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_TAX_RATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsTaxZone()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_TAX_ZONE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsNonTaxReason()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_NON_TAX_REASON);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsNote()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_NOTE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSaleType()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_SALE_TYPE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsItemGroup()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ITEM_GROUP);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsItemUser1()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ITEM_USER_1);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsItemUser2()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ITEM_USER_2);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsItemUser3()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ITEM_USER_3);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsItemUser4()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ITEM_USER_4);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsConvertedToPurchase())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CONVERTED_TO_PURCHASE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsManualConvertToPurchaseMctp())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_MANUAL_CONVERT_TO_PURCHASE_MCTP);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsMctpChargeAmt()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_MCTP_CHARGE_AMT);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsMctpAllowedAmt()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_MCTP_ALLOWED_AMT);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsMctpModifier1()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_MCTP_MODIFIER_1);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsMctpModifier2()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_MCTP_MODIFIER_2);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsMctpModifier3()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_MCTP_MODIFIER_3);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsMctpModifier4()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_MCTP_MODIFIER_4);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsMctpPeriod()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_MCTP_PERIOD);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAddtlModifier1()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ADDTL_MODIFIER_1);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAddtlModifier2()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ADDTL_MODIFIER_2);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAddtlModifier3()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ADDTL_MODIFIER_3);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAddtlModifier4()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ADDTL_MODIFIER_4);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsNextDateOfService())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_NEXT_DATE_OF_SERVICE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsPriceTable()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_PRICE_TABLE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsPriceOptionName())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_PRICE_OPTION_NAME);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsExtendedChargeAmount())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_EXTENDED_CHARGE_AMOUNT);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsExtendedAllowanceAmount())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_EXTENDED_ALLOWANCE_AMOUNT);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsItemNdcCode()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ITEM_NDC_CODE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsManufacturer()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_MANUFACTURER);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCbPricing()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CB_PRICING);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCbPriceTableOverride())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CB_PRICE_TABLE_OVERRIDE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCbOverride()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CB_OVERRIDE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsMessages()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_MESSAGES);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsLocation()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_LOCATION);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCaloriesPerDay()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CALORIES_PER_DAY);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingProcudureCode())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_PROCUDURE_CODE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingPriceOption())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_PRICE_OPTION);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingPriceOptionName())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_PRICE_OPTION_NAME);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingModifier1())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_1);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingModifier2())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_2);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingModifier3())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_3);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingModifier4())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_4);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingAdditionalModifier1())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_1);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingAdditionalModifier2())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_2);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingAdditionalModifier3())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_3);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingAdditionalModifier4())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_4);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingIgnore())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_IGNORE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondarySpecialBilling())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_SPECIAL_BILLING);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSpanDateSplitBilling())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_SPAN_DATE_SPLIT_BILLING);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnFormId())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_FORM_ID);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnKey()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_KEY);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnCreateDate())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_CREATE_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnExpirationDate())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_EXPIRATION_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnInitialDate())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_INITIAL_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnRenewalDate())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_RENEWAL_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnRecertificationDate())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_RECERTIFICATION_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnPhysicianDate())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_PHYSICIAN_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnStatus())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_STATUS);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparParId()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_PAR_ID);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparParDescr()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_PAR_DESCR);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparParInitialDate())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_PAR_INITIAL_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparParExpirationDate())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_PAR_EXPIRATION_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnLogDate())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_LOG_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnLengthOfNeed())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_LENGTH_OF_NEED);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnPrintedDate())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_PRINTED_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnPrintedBy())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_PRINTED_BY);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparFaxedDate())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_FAXED_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnPlaceholder())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_PLACEHOLDER);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnFaxedBy())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_FAXED_BY);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnLoggedBy())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_LOGGED_BY);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparNumberOfRefills())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_NUMBER_OF_REFILLS);
        assertThat(testSalesOrderItemDetails.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testSalesOrderItemDetails.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testSalesOrderItemDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSalesOrderItemDetails.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testSalesOrderItemDetails.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsManufacturerItemIdNumber())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_MANUFACTURER_ITEM_ID_NUMBER);
        assertThat(testSalesOrderItemDetails.getCmnId()).isEqualTo(DEFAULT_CMN_ID);
        assertThat(testSalesOrderItemDetails.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testSalesOrderItemDetails.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testSalesOrderItemDetails.getSalesOrderItemDetailsUuid()).isEqualTo(DEFAULT_SALES_ORDER_ITEM_DETAILS_UUID);
        assertThat(testSalesOrderItemDetails.getSalesOrderItemNumber()).isEqualTo(DEFAULT_SALES_ORDER_ITEM_NUMBER);
        assertThat(testSalesOrderItemDetails.getIsAssetTagged()).isEqualTo(DEFAULT_IS_ASSET_TAGGED);
        assertThat(testSalesOrderItemDetails.getItemSerialNo()).isEqualTo(DEFAULT_ITEM_SERIAL_NO);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsIcdPointer()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ICD_POINTER);
        assertThat(testSalesOrderItemDetails.getProcedureIdentifier()).isEqualTo(DEFAULT_PROCEDURE_IDENTIFIER);
        assertThat(testSalesOrderItemDetails.getParNo()).isEqualTo(DEFAULT_PAR_NO);
        assertThat(testSalesOrderItemDetails.getWhetherSerialised()).isEqualTo(DEFAULT_WHETHER_SERIALISED);
        assertThat(testSalesOrderItemDetails.getPickupExchangeNo()).isEqualTo(DEFAULT_PICKUP_EXCHANGE_NO);
        assertThat(testSalesOrderItemDetails.getSalesOrderAbnUserResponse()).isEqualTo(DEFAULT_SALES_ORDER_ABN_USER_RESPONSE);
        assertThat(testSalesOrderItemDetails.getIsDropshipAllowed()).isEqualTo(DEFAULT_IS_DROPSHIP_ALLOWED);
        assertThat(testSalesOrderItemDetails.getPoNumber()).isEqualTo(DEFAULT_PO_NUMBER);
        assertThat(testSalesOrderItemDetails.getPurchaseOrderUuid()).isEqualTo(DEFAULT_PURCHASE_ORDER_UUID);
        assertThat(testSalesOrderItemDetails.getIsResupplyType()).isEqualTo(DEFAULT_IS_RESUPPLY_TYPE);
        assertThat(testSalesOrderItemDetails.getFrequencyCount()).isEqualTo(DEFAULT_FREQUENCY_COUNT);
        assertThat(testSalesOrderItemDetails.getFrequencyInterval()).isEqualTo(DEFAULT_FREQUENCY_INTERVAL);
        assertThat(testSalesOrderItemDetails.getItemGroupId()).isEqualTo(DEFAULT_ITEM_GROUP_ID);
    }

    @Test
    void createSalesOrderItemDetailsWithExistingId() throws Exception {
        // Create the SalesOrderItemDetails with an existing ID
        salesOrderItemDetails.setSalesOrderItemDetailsId(1L);
        SalesOrderItemDetailsDTO salesOrderItemDetailsDTO = salesOrderItemDetailsMapper.toDto(salesOrderItemDetails);

        int databaseSizeBeforeCreate = salesOrderItemDetailsRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderItemDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderItemDetails in the database
        List<SalesOrderItemDetails> salesOrderItemDetailsList = salesOrderItemDetailsRepository.findAll().collectList().block();
        assertThat(salesOrderItemDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllSalesOrderItemDetails() {
        // Initialize the database
        salesOrderItemDetailsRepository.save(salesOrderItemDetails).block();

        // Get all the salesOrderItemDetailsList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=salesOrderItemDetailsId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].salesOrderItemDetailsId")
            .value(hasItem(salesOrderItemDetails.getSalesOrderItemDetailsId().intValue()))
            .jsonPath("$.[*].salesOrderId")
            .value(hasItem(DEFAULT_SALES_ORDER_ID.intValue()))
            .jsonPath("$.[*].patientId")
            .value(hasItem(DEFAULT_PATIENT_ID.intValue()))
            .jsonPath("$.[*].itemLocationId")
            .value(hasItem(DEFAULT_ITEM_LOCATION_ID.intValue()))
            .jsonPath("$.[*].salesOrderDetailsItemId")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_ITEM_ID.intValue()))
            .jsonPath("$.[*].salesOrderDetailsItemName")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_ITEM_NAME))
            .jsonPath("$.[*].salesOrderDetailsStockingUom")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_STOCKING_UOM))
            .jsonPath("$.[*].itemAssetNo")
            .value(hasItem(DEFAULT_ITEM_ASSET_NO))
            .jsonPath("$.[*].salesOrderDetailsItemDescription")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_ITEM_DESCRIPTION))
            .jsonPath("$.[*].salesOrderDetailsDefaultVendor")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_DEFAULT_VENDOR))
            .jsonPath("$.[*].salesOrderDetailsOriginalDos")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_ORIGINAL_DOS.toString()))
            .jsonPath("$.[*].salesOrderDetailsPreviousBillingDate")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_PREVIOUS_BILLING_DATE.toString()))
            .jsonPath("$.[*].salesOrderDetailsNextBillingDate")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_NEXT_BILLING_DATE.toString()))
            .jsonPath("$.[*].salesOrderDetailsDosTo")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_DOS_TO.toString()))
            .jsonPath("$.[*].salesOrderDetailsNextPeriod")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_NEXT_PERIOD))
            .jsonPath("$.[*].salesOrderDetailsSpecialPricing")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_SPECIAL_PRICING))
            .jsonPath("$.[*].salesOrderDetailsPriceOverride")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_PRICE_OVERRIDE))
            .jsonPath("$.[*].salesOrderDetailsSpecialTaxRate")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_SPECIAL_TAX_RATE.intValue()))
            .jsonPath("$.[*].salesOrderDetailsQty")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_QTY.intValue()))
            .jsonPath("$.[*].salesOrderDetailsBqty")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_BQTY.intValue()))
            .jsonPath("$.[*].salesOrderDetailsLineQty")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_LINE_QTY.intValue()))
            .jsonPath("$.[*].salesOrderDetailsProcCode")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_PROC_CODE))
            .jsonPath("$.[*].salesOrderDetailsPriceOption")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_PRICE_OPTION))
            .jsonPath("$.[*].salesOrderDetailsModifier1")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_MODIFIER_1))
            .jsonPath("$.[*].salesOrderDetailsModifier2")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_MODIFIER_2))
            .jsonPath("$.[*].salesOrderDetailsModifier3")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_MODIFIER_3))
            .jsonPath("$.[*].salesOrderDetailsModifier4")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_MODIFIER_4))
            .jsonPath("$.[*].salesOrderDetailsChargeAmt")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_CHARGE_AMT.doubleValue()))
            .jsonPath("$.[*].salesOrderDetailsAllowedAmt")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_ALLOWED_AMT.doubleValue()))
            .jsonPath("$.[*].salesOrderDetailsTaxable")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_TAXABLE))
            .jsonPath("$.[*].salesOrderDetailsAbn")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_ABN))
            .jsonPath("$.[*].salesOrderDetailsAbnUpgrade")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_ABN_UPGRADE))
            .jsonPath("$.[*].salesOrderDetailsAbnPrintDate")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_ABN_PRINT_DATE.toString()))
            .jsonPath("$.[*].salesOrderDetailsAbnItem")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_ABN_ITEM))
            .jsonPath("$.[*].salesOrderDetailsAbnProcCode")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_ABN_PROC_CODE))
            .jsonPath("$.[*].salesOrderDetailsAbnAllow")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_ABN_ALLOW))
            .jsonPath("$.[*].salesOrderDetailsAbnCharge")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_ABN_CHARGE.doubleValue()))
            .jsonPath("$.[*].salesOrderDetailsAbnModifier1")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_ABN_MODIFIER_1))
            .jsonPath("$.[*].salesOrderDetailsAbnModifier2")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_ABN_MODIFIER_2))
            .jsonPath("$.[*].salesOrderDetailsTaxRate")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_TAX_RATE.intValue()))
            .jsonPath("$.[*].salesOrderDetailsTaxZone")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_TAX_ZONE))
            .jsonPath("$.[*].salesOrderDetailsNonTaxReason")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_NON_TAX_REASON))
            .jsonPath("$.[*].salesOrderDetailsNote")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_NOTE))
            .jsonPath("$.[*].salesOrderDetailsSaleType")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_SALE_TYPE))
            .jsonPath("$.[*].salesOrderDetailsItemGroup")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_ITEM_GROUP))
            .jsonPath("$.[*].salesOrderDetailsItemUser1")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_ITEM_USER_1))
            .jsonPath("$.[*].salesOrderDetailsItemUser2")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_ITEM_USER_2))
            .jsonPath("$.[*].salesOrderDetailsItemUser3")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_ITEM_USER_3))
            .jsonPath("$.[*].salesOrderDetailsItemUser4")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_ITEM_USER_4))
            .jsonPath("$.[*].salesOrderDetailsConvertedToPurchase")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_CONVERTED_TO_PURCHASE))
            .jsonPath("$.[*].salesOrderDetailsManualConvertToPurchaseMctp")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_MANUAL_CONVERT_TO_PURCHASE_MCTP))
            .jsonPath("$.[*].salesOrderDetailsMctpChargeAmt")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_MCTP_CHARGE_AMT.doubleValue()))
            .jsonPath("$.[*].salesOrderDetailsMctpAllowedAmt")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_MCTP_ALLOWED_AMT.doubleValue()))
            .jsonPath("$.[*].salesOrderDetailsMctpModifier1")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_MCTP_MODIFIER_1))
            .jsonPath("$.[*].salesOrderDetailsMctpModifier2")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_MCTP_MODIFIER_2))
            .jsonPath("$.[*].salesOrderDetailsMctpModifier3")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_MCTP_MODIFIER_3))
            .jsonPath("$.[*].salesOrderDetailsMctpModifier4")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_MCTP_MODIFIER_4))
            .jsonPath("$.[*].salesOrderDetailsMctpPeriod")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_MCTP_PERIOD.intValue()))
            .jsonPath("$.[*].salesOrderDetailsAddtlModifier1")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_ADDTL_MODIFIER_1))
            .jsonPath("$.[*].salesOrderDetailsAddtlModifier2")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_ADDTL_MODIFIER_2))
            .jsonPath("$.[*].salesOrderDetailsAddtlModifier3")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_ADDTL_MODIFIER_3))
            .jsonPath("$.[*].salesOrderDetailsAddtlModifier4")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_ADDTL_MODIFIER_4))
            .jsonPath("$.[*].salesOrderDetailsNextDateOfService")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_NEXT_DATE_OF_SERVICE.toString()))
            .jsonPath("$.[*].salesOrderDetailsPriceTable")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_PRICE_TABLE))
            .jsonPath("$.[*].salesOrderDetailsPriceOptionName")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_PRICE_OPTION_NAME))
            .jsonPath("$.[*].salesOrderDetailsExtendedChargeAmount")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_EXTENDED_CHARGE_AMOUNT.doubleValue()))
            .jsonPath("$.[*].salesOrderDetailsExtendedAllowanceAmount")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_EXTENDED_ALLOWANCE_AMOUNT.doubleValue()))
            .jsonPath("$.[*].salesOrderDetailsItemNdcCode")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_ITEM_NDC_CODE))
            .jsonPath("$.[*].salesOrderDetailsManufacturer")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_MANUFACTURER))
            .jsonPath("$.[*].salesOrderDetailsCbPricing")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_CB_PRICING))
            .jsonPath("$.[*].salesOrderDetailsCbPriceTableOverride")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_CB_PRICE_TABLE_OVERRIDE))
            .jsonPath("$.[*].salesOrderDetailsCbOverride")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_CB_OVERRIDE))
            .jsonPath("$.[*].salesOrderDetailsMessages")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_MESSAGES))
            .jsonPath("$.[*].salesOrderDetailsLocation")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_LOCATION.intValue()))
            .jsonPath("$.[*].salesOrderDetailsCaloriesPerDay")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_CALORIES_PER_DAY.intValue()))
            .jsonPath("$.[*].salesOrderDetailsSecondaryBillingProcudureCode")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_PROCUDURE_CODE))
            .jsonPath("$.[*].salesOrderDetailsSecondaryBillingPriceOption")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_PRICE_OPTION))
            .jsonPath("$.[*].salesOrderDetailsSecondaryBillingPriceOptionName")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_PRICE_OPTION_NAME))
            .jsonPath("$.[*].salesOrderDetailsSecondaryBillingModifier1")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_1))
            .jsonPath("$.[*].salesOrderDetailsSecondaryBillingModifier2")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_2))
            .jsonPath("$.[*].salesOrderDetailsSecondaryBillingModifier3")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_3))
            .jsonPath("$.[*].salesOrderDetailsSecondaryBillingModifier4")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_4))
            .jsonPath("$.[*].salesOrderDetailsSecondaryBillingAdditionalModifier1")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_1))
            .jsonPath("$.[*].salesOrderDetailsSecondaryBillingAdditionalModifier2")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_2))
            .jsonPath("$.[*].salesOrderDetailsSecondaryBillingAdditionalModifier3")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_3))
            .jsonPath("$.[*].salesOrderDetailsSecondaryBillingAdditionalModifier4")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_4))
            .jsonPath("$.[*].salesOrderDetailsSecondaryBillingIgnore")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_IGNORE))
            .jsonPath("$.[*].salesOrderDetailsSecondarySpecialBilling")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_SPECIAL_BILLING))
            .jsonPath("$.[*].salesOrderDetailsSpanDateSplitBilling")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_SPAN_DATE_SPLIT_BILLING))
            .jsonPath("$.[*].salesOrderDetailsCmnparCmnFormId")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_FORM_ID.intValue()))
            .jsonPath("$.[*].salesOrderDetailsCmnparCmnKey")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_KEY))
            .jsonPath("$.[*].salesOrderDetailsCmnparCmnCreateDate")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_CREATE_DATE.toString()))
            .jsonPath("$.[*].salesOrderDetailsCmnparCmnExpirationDate")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_EXPIRATION_DATE.toString()))
            .jsonPath("$.[*].salesOrderDetailsCmnparCmnInitialDate")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_INITIAL_DATE.toString()))
            .jsonPath("$.[*].salesOrderDetailsCmnparCmnRenewalDate")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_RENEWAL_DATE.toString()))
            .jsonPath("$.[*].salesOrderDetailsCmnparCmnRecertificationDate")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_RECERTIFICATION_DATE.toString()))
            .jsonPath("$.[*].salesOrderDetailsCmnparCmnPhysicianDate")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_PHYSICIAN_DATE.toString()))
            .jsonPath("$.[*].salesOrderDetailsCmnparCmnStatus")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_STATUS))
            .jsonPath("$.[*].salesOrderDetailsCmnparParId")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_PAR_ID))
            .jsonPath("$.[*].salesOrderDetailsCmnparParDescr")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_PAR_DESCR))
            .jsonPath("$.[*].salesOrderDetailsCmnparParInitialDate")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_PAR_INITIAL_DATE.toString()))
            .jsonPath("$.[*].salesOrderDetailsCmnparParExpirationDate")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_PAR_EXPIRATION_DATE.toString()))
            .jsonPath("$.[*].salesOrderDetailsCmnparCmnLogDate")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_LOG_DATE.toString()))
            .jsonPath("$.[*].salesOrderDetailsCmnparCmnLengthOfNeed")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_LENGTH_OF_NEED.intValue()))
            .jsonPath("$.[*].salesOrderDetailsCmnparCmnPrintedDate")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_PRINTED_DATE.toString()))
            .jsonPath("$.[*].salesOrderDetailsCmnparCmnPrintedBy")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_PRINTED_BY))
            .jsonPath("$.[*].salesOrderDetailsCmnparFaxedDate")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_FAXED_DATE.toString()))
            .jsonPath("$.[*].salesOrderDetailsCmnparCmnPlaceholder")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_PLACEHOLDER))
            .jsonPath("$.[*].salesOrderDetailsCmnparCmnFaxedBy")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_FAXED_BY))
            .jsonPath("$.[*].salesOrderDetailsCmnparCmnLoggedBy")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_LOGGED_BY))
            .jsonPath("$.[*].salesOrderDetailsCmnparNumberOfRefills")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_NUMBER_OF_REFILLS.intValue()))
            .jsonPath("$.[*].createdById")
            .value(hasItem(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.[*].createdDate")
            .value(hasItem(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].updatedById")
            .value(hasItem(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.[*].updatedDate")
            .value(hasItem(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.[*].salesOrderDetailsManufacturerItemIdNumber")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_MANUFACTURER_ITEM_ID_NUMBER))
            .jsonPath("$.[*].cmnId")
            .value(hasItem(DEFAULT_CMN_ID.intValue()))
            .jsonPath("$.[*].createdByName")
            .value(hasItem(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.[*].updatedByName")
            .value(hasItem(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.[*].salesOrderItemDetailsUuid")
            .value(hasItem(DEFAULT_SALES_ORDER_ITEM_DETAILS_UUID.toString()))
            .jsonPath("$.[*].salesOrderItemNumber")
            .value(hasItem(DEFAULT_SALES_ORDER_ITEM_NUMBER))
            .jsonPath("$.[*].isAssetTagged")
            .value(hasItem(DEFAULT_IS_ASSET_TAGGED))
            .jsonPath("$.[*].itemSerialNo")
            .value(hasItem(DEFAULT_ITEM_SERIAL_NO.intValue()))
            .jsonPath("$.[*].salesOrderDetailsIcdPointer")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_ICD_POINTER))
            .jsonPath("$.[*].procedureIdentifier")
            .value(hasItem(DEFAULT_PROCEDURE_IDENTIFIER))
            .jsonPath("$.[*].parNo")
            .value(hasItem(DEFAULT_PAR_NO))
            .jsonPath("$.[*].whetherSerialised")
            .value(hasItem(DEFAULT_WHETHER_SERIALISED))
            .jsonPath("$.[*].pickupExchangeNo")
            .value(hasItem(DEFAULT_PICKUP_EXCHANGE_NO))
            .jsonPath("$.[*].salesOrderAbnUserResponse")
            .value(hasItem(DEFAULT_SALES_ORDER_ABN_USER_RESPONSE))
            .jsonPath("$.[*].isDropshipAllowed")
            .value(hasItem(DEFAULT_IS_DROPSHIP_ALLOWED))
            .jsonPath("$.[*].poNumber")
            .value(hasItem(DEFAULT_PO_NUMBER))
            .jsonPath("$.[*].purchaseOrderUuid")
            .value(hasItem(DEFAULT_PURCHASE_ORDER_UUID.toString()))
            .jsonPath("$.[*].isResupplyType")
            .value(hasItem(DEFAULT_IS_RESUPPLY_TYPE))
            .jsonPath("$.[*].frequencyCount")
            .value(hasItem(DEFAULT_FREQUENCY_COUNT.intValue()))
            .jsonPath("$.[*].frequencyInterval")
            .value(hasItem(DEFAULT_FREQUENCY_INTERVAL.intValue()))
            .jsonPath("$.[*].itemGroupId")
            .value(hasItem(DEFAULT_ITEM_GROUP_ID.intValue()));
    }

    @Test
    void getSalesOrderItemDetails() {
        // Initialize the database
        salesOrderItemDetailsRepository.save(salesOrderItemDetails).block();

        // Get the salesOrderItemDetails
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, salesOrderItemDetails.getSalesOrderItemDetailsId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.salesOrderItemDetailsId")
            .value(is(salesOrderItemDetails.getSalesOrderItemDetailsId().intValue()))
            .jsonPath("$.salesOrderId")
            .value(is(DEFAULT_SALES_ORDER_ID.intValue()))
            .jsonPath("$.patientId")
            .value(is(DEFAULT_PATIENT_ID.intValue()))
            .jsonPath("$.itemLocationId")
            .value(is(DEFAULT_ITEM_LOCATION_ID.intValue()))
            .jsonPath("$.salesOrderDetailsItemId")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_ITEM_ID.intValue()))
            .jsonPath("$.salesOrderDetailsItemName")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_ITEM_NAME))
            .jsonPath("$.salesOrderDetailsStockingUom")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_STOCKING_UOM))
            .jsonPath("$.itemAssetNo")
            .value(is(DEFAULT_ITEM_ASSET_NO))
            .jsonPath("$.salesOrderDetailsItemDescription")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_ITEM_DESCRIPTION))
            .jsonPath("$.salesOrderDetailsDefaultVendor")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_DEFAULT_VENDOR))
            .jsonPath("$.salesOrderDetailsOriginalDos")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_ORIGINAL_DOS.toString()))
            .jsonPath("$.salesOrderDetailsPreviousBillingDate")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_PREVIOUS_BILLING_DATE.toString()))
            .jsonPath("$.salesOrderDetailsNextBillingDate")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_NEXT_BILLING_DATE.toString()))
            .jsonPath("$.salesOrderDetailsDosTo")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_DOS_TO.toString()))
            .jsonPath("$.salesOrderDetailsNextPeriod")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_NEXT_PERIOD))
            .jsonPath("$.salesOrderDetailsSpecialPricing")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_SPECIAL_PRICING))
            .jsonPath("$.salesOrderDetailsPriceOverride")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_PRICE_OVERRIDE))
            .jsonPath("$.salesOrderDetailsSpecialTaxRate")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_SPECIAL_TAX_RATE.intValue()))
            .jsonPath("$.salesOrderDetailsQty")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_QTY.intValue()))
            .jsonPath("$.salesOrderDetailsBqty")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_BQTY.intValue()))
            .jsonPath("$.salesOrderDetailsLineQty")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_LINE_QTY.intValue()))
            .jsonPath("$.salesOrderDetailsProcCode")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_PROC_CODE))
            .jsonPath("$.salesOrderDetailsPriceOption")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_PRICE_OPTION))
            .jsonPath("$.salesOrderDetailsModifier1")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_MODIFIER_1))
            .jsonPath("$.salesOrderDetailsModifier2")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_MODIFIER_2))
            .jsonPath("$.salesOrderDetailsModifier3")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_MODIFIER_3))
            .jsonPath("$.salesOrderDetailsModifier4")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_MODIFIER_4))
            .jsonPath("$.salesOrderDetailsChargeAmt")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_CHARGE_AMT.doubleValue()))
            .jsonPath("$.salesOrderDetailsAllowedAmt")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_ALLOWED_AMT.doubleValue()))
            .jsonPath("$.salesOrderDetailsTaxable")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_TAXABLE))
            .jsonPath("$.salesOrderDetailsAbn")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_ABN))
            .jsonPath("$.salesOrderDetailsAbnUpgrade")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_ABN_UPGRADE))
            .jsonPath("$.salesOrderDetailsAbnPrintDate")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_ABN_PRINT_DATE.toString()))
            .jsonPath("$.salesOrderDetailsAbnItem")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_ABN_ITEM))
            .jsonPath("$.salesOrderDetailsAbnProcCode")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_ABN_PROC_CODE))
            .jsonPath("$.salesOrderDetailsAbnAllow")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_ABN_ALLOW))
            .jsonPath("$.salesOrderDetailsAbnCharge")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_ABN_CHARGE.doubleValue()))
            .jsonPath("$.salesOrderDetailsAbnModifier1")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_ABN_MODIFIER_1))
            .jsonPath("$.salesOrderDetailsAbnModifier2")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_ABN_MODIFIER_2))
            .jsonPath("$.salesOrderDetailsTaxRate")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_TAX_RATE.intValue()))
            .jsonPath("$.salesOrderDetailsTaxZone")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_TAX_ZONE))
            .jsonPath("$.salesOrderDetailsNonTaxReason")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_NON_TAX_REASON))
            .jsonPath("$.salesOrderDetailsNote")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_NOTE))
            .jsonPath("$.salesOrderDetailsSaleType")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_SALE_TYPE))
            .jsonPath("$.salesOrderDetailsItemGroup")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_ITEM_GROUP))
            .jsonPath("$.salesOrderDetailsItemUser1")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_ITEM_USER_1))
            .jsonPath("$.salesOrderDetailsItemUser2")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_ITEM_USER_2))
            .jsonPath("$.salesOrderDetailsItemUser3")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_ITEM_USER_3))
            .jsonPath("$.salesOrderDetailsItemUser4")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_ITEM_USER_4))
            .jsonPath("$.salesOrderDetailsConvertedToPurchase")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_CONVERTED_TO_PURCHASE))
            .jsonPath("$.salesOrderDetailsManualConvertToPurchaseMctp")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_MANUAL_CONVERT_TO_PURCHASE_MCTP))
            .jsonPath("$.salesOrderDetailsMctpChargeAmt")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_MCTP_CHARGE_AMT.doubleValue()))
            .jsonPath("$.salesOrderDetailsMctpAllowedAmt")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_MCTP_ALLOWED_AMT.doubleValue()))
            .jsonPath("$.salesOrderDetailsMctpModifier1")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_MCTP_MODIFIER_1))
            .jsonPath("$.salesOrderDetailsMctpModifier2")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_MCTP_MODIFIER_2))
            .jsonPath("$.salesOrderDetailsMctpModifier3")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_MCTP_MODIFIER_3))
            .jsonPath("$.salesOrderDetailsMctpModifier4")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_MCTP_MODIFIER_4))
            .jsonPath("$.salesOrderDetailsMctpPeriod")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_MCTP_PERIOD.intValue()))
            .jsonPath("$.salesOrderDetailsAddtlModifier1")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_ADDTL_MODIFIER_1))
            .jsonPath("$.salesOrderDetailsAddtlModifier2")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_ADDTL_MODIFIER_2))
            .jsonPath("$.salesOrderDetailsAddtlModifier3")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_ADDTL_MODIFIER_3))
            .jsonPath("$.salesOrderDetailsAddtlModifier4")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_ADDTL_MODIFIER_4))
            .jsonPath("$.salesOrderDetailsNextDateOfService")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_NEXT_DATE_OF_SERVICE.toString()))
            .jsonPath("$.salesOrderDetailsPriceTable")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_PRICE_TABLE))
            .jsonPath("$.salesOrderDetailsPriceOptionName")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_PRICE_OPTION_NAME))
            .jsonPath("$.salesOrderDetailsExtendedChargeAmount")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_EXTENDED_CHARGE_AMOUNT.doubleValue()))
            .jsonPath("$.salesOrderDetailsExtendedAllowanceAmount")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_EXTENDED_ALLOWANCE_AMOUNT.doubleValue()))
            .jsonPath("$.salesOrderDetailsItemNdcCode")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_ITEM_NDC_CODE))
            .jsonPath("$.salesOrderDetailsManufacturer")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_MANUFACTURER))
            .jsonPath("$.salesOrderDetailsCbPricing")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_CB_PRICING))
            .jsonPath("$.salesOrderDetailsCbPriceTableOverride")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_CB_PRICE_TABLE_OVERRIDE))
            .jsonPath("$.salesOrderDetailsCbOverride")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_CB_OVERRIDE))
            .jsonPath("$.salesOrderDetailsMessages")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_MESSAGES))
            .jsonPath("$.salesOrderDetailsLocation")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_LOCATION.intValue()))
            .jsonPath("$.salesOrderDetailsCaloriesPerDay")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_CALORIES_PER_DAY.intValue()))
            .jsonPath("$.salesOrderDetailsSecondaryBillingProcudureCode")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_PROCUDURE_CODE))
            .jsonPath("$.salesOrderDetailsSecondaryBillingPriceOption")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_PRICE_OPTION))
            .jsonPath("$.salesOrderDetailsSecondaryBillingPriceOptionName")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_PRICE_OPTION_NAME))
            .jsonPath("$.salesOrderDetailsSecondaryBillingModifier1")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_1))
            .jsonPath("$.salesOrderDetailsSecondaryBillingModifier2")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_2))
            .jsonPath("$.salesOrderDetailsSecondaryBillingModifier3")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_3))
            .jsonPath("$.salesOrderDetailsSecondaryBillingModifier4")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_4))
            .jsonPath("$.salesOrderDetailsSecondaryBillingAdditionalModifier1")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_1))
            .jsonPath("$.salesOrderDetailsSecondaryBillingAdditionalModifier2")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_2))
            .jsonPath("$.salesOrderDetailsSecondaryBillingAdditionalModifier3")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_3))
            .jsonPath("$.salesOrderDetailsSecondaryBillingAdditionalModifier4")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_4))
            .jsonPath("$.salesOrderDetailsSecondaryBillingIgnore")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_IGNORE))
            .jsonPath("$.salesOrderDetailsSecondarySpecialBilling")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_SPECIAL_BILLING))
            .jsonPath("$.salesOrderDetailsSpanDateSplitBilling")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_SPAN_DATE_SPLIT_BILLING))
            .jsonPath("$.salesOrderDetailsCmnparCmnFormId")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_FORM_ID.intValue()))
            .jsonPath("$.salesOrderDetailsCmnparCmnKey")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_KEY))
            .jsonPath("$.salesOrderDetailsCmnparCmnCreateDate")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_CREATE_DATE.toString()))
            .jsonPath("$.salesOrderDetailsCmnparCmnExpirationDate")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_EXPIRATION_DATE.toString()))
            .jsonPath("$.salesOrderDetailsCmnparCmnInitialDate")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_INITIAL_DATE.toString()))
            .jsonPath("$.salesOrderDetailsCmnparCmnRenewalDate")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_RENEWAL_DATE.toString()))
            .jsonPath("$.salesOrderDetailsCmnparCmnRecertificationDate")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_RECERTIFICATION_DATE.toString()))
            .jsonPath("$.salesOrderDetailsCmnparCmnPhysicianDate")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_PHYSICIAN_DATE.toString()))
            .jsonPath("$.salesOrderDetailsCmnparCmnStatus")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_STATUS))
            .jsonPath("$.salesOrderDetailsCmnparParId")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_PAR_ID))
            .jsonPath("$.salesOrderDetailsCmnparParDescr")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_PAR_DESCR))
            .jsonPath("$.salesOrderDetailsCmnparParInitialDate")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_PAR_INITIAL_DATE.toString()))
            .jsonPath("$.salesOrderDetailsCmnparParExpirationDate")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_PAR_EXPIRATION_DATE.toString()))
            .jsonPath("$.salesOrderDetailsCmnparCmnLogDate")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_LOG_DATE.toString()))
            .jsonPath("$.salesOrderDetailsCmnparCmnLengthOfNeed")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_LENGTH_OF_NEED.intValue()))
            .jsonPath("$.salesOrderDetailsCmnparCmnPrintedDate")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_PRINTED_DATE.toString()))
            .jsonPath("$.salesOrderDetailsCmnparCmnPrintedBy")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_PRINTED_BY))
            .jsonPath("$.salesOrderDetailsCmnparFaxedDate")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_FAXED_DATE.toString()))
            .jsonPath("$.salesOrderDetailsCmnparCmnPlaceholder")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_PLACEHOLDER))
            .jsonPath("$.salesOrderDetailsCmnparCmnFaxedBy")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_FAXED_BY))
            .jsonPath("$.salesOrderDetailsCmnparCmnLoggedBy")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_LOGGED_BY))
            .jsonPath("$.salesOrderDetailsCmnparNumberOfRefills")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_NUMBER_OF_REFILLS.intValue()))
            .jsonPath("$.createdById")
            .value(is(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.createdDate")
            .value(is(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.updatedById")
            .value(is(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.updatedDate")
            .value(is(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.salesOrderDetailsManufacturerItemIdNumber")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_MANUFACTURER_ITEM_ID_NUMBER))
            .jsonPath("$.cmnId")
            .value(is(DEFAULT_CMN_ID.intValue()))
            .jsonPath("$.createdByName")
            .value(is(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.updatedByName")
            .value(is(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.salesOrderItemDetailsUuid")
            .value(is(DEFAULT_SALES_ORDER_ITEM_DETAILS_UUID.toString()))
            .jsonPath("$.salesOrderItemNumber")
            .value(is(DEFAULT_SALES_ORDER_ITEM_NUMBER))
            .jsonPath("$.isAssetTagged")
            .value(is(DEFAULT_IS_ASSET_TAGGED))
            .jsonPath("$.itemSerialNo")
            .value(is(DEFAULT_ITEM_SERIAL_NO.intValue()))
            .jsonPath("$.salesOrderDetailsIcdPointer")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_ICD_POINTER))
            .jsonPath("$.procedureIdentifier")
            .value(is(DEFAULT_PROCEDURE_IDENTIFIER))
            .jsonPath("$.parNo")
            .value(is(DEFAULT_PAR_NO))
            .jsonPath("$.whetherSerialised")
            .value(is(DEFAULT_WHETHER_SERIALISED))
            .jsonPath("$.pickupExchangeNo")
            .value(is(DEFAULT_PICKUP_EXCHANGE_NO))
            .jsonPath("$.salesOrderAbnUserResponse")
            .value(is(DEFAULT_SALES_ORDER_ABN_USER_RESPONSE))
            .jsonPath("$.isDropshipAllowed")
            .value(is(DEFAULT_IS_DROPSHIP_ALLOWED))
            .jsonPath("$.poNumber")
            .value(is(DEFAULT_PO_NUMBER))
            .jsonPath("$.purchaseOrderUuid")
            .value(is(DEFAULT_PURCHASE_ORDER_UUID.toString()))
            .jsonPath("$.isResupplyType")
            .value(is(DEFAULT_IS_RESUPPLY_TYPE))
            .jsonPath("$.frequencyCount")
            .value(is(DEFAULT_FREQUENCY_COUNT.intValue()))
            .jsonPath("$.frequencyInterval")
            .value(is(DEFAULT_FREQUENCY_INTERVAL.intValue()))
            .jsonPath("$.itemGroupId")
            .value(is(DEFAULT_ITEM_GROUP_ID.intValue()));
    }

    @Test
    void getNonExistingSalesOrderItemDetails() {
        // Get the salesOrderItemDetails
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putNewSalesOrderItemDetails() throws Exception {
        // Initialize the database
        salesOrderItemDetailsRepository.save(salesOrderItemDetails).block();

        int databaseSizeBeforeUpdate = salesOrderItemDetailsRepository.findAll().collectList().block().size();

        // Update the salesOrderItemDetails
        SalesOrderItemDetails updatedSalesOrderItemDetails = salesOrderItemDetailsRepository
            .findById(salesOrderItemDetails.getSalesOrderItemDetailsId())
            .block();
        updatedSalesOrderItemDetails
            .salesOrderId(UPDATED_SALES_ORDER_ID)
            .patientId(UPDATED_PATIENT_ID)
            .itemLocationId(UPDATED_ITEM_LOCATION_ID)
            .salesOrderDetailsItemId(UPDATED_SALES_ORDER_DETAILS_ITEM_ID)
            .salesOrderDetailsItemName(UPDATED_SALES_ORDER_DETAILS_ITEM_NAME)
            .salesOrderDetailsStockingUom(UPDATED_SALES_ORDER_DETAILS_STOCKING_UOM)
            .itemAssetNo(UPDATED_ITEM_ASSET_NO)
            .salesOrderDetailsItemDescription(UPDATED_SALES_ORDER_DETAILS_ITEM_DESCRIPTION)
            .salesOrderDetailsDefaultVendor(UPDATED_SALES_ORDER_DETAILS_DEFAULT_VENDOR)
            .salesOrderDetailsOriginalDos(UPDATED_SALES_ORDER_DETAILS_ORIGINAL_DOS)
            .salesOrderDetailsPreviousBillingDate(UPDATED_SALES_ORDER_DETAILS_PREVIOUS_BILLING_DATE)
            .salesOrderDetailsNextBillingDate(UPDATED_SALES_ORDER_DETAILS_NEXT_BILLING_DATE)
            .salesOrderDetailsDosTo(UPDATED_SALES_ORDER_DETAILS_DOS_TO)
            .salesOrderDetailsNextPeriod(UPDATED_SALES_ORDER_DETAILS_NEXT_PERIOD)
            .salesOrderDetailsSpecialPricing(UPDATED_SALES_ORDER_DETAILS_SPECIAL_PRICING)
            .salesOrderDetailsPriceOverride(UPDATED_SALES_ORDER_DETAILS_PRICE_OVERRIDE)
            .salesOrderDetailsSpecialTaxRate(UPDATED_SALES_ORDER_DETAILS_SPECIAL_TAX_RATE)
            .salesOrderDetailsQty(UPDATED_SALES_ORDER_DETAILS_QTY)
            .salesOrderDetailsBqty(UPDATED_SALES_ORDER_DETAILS_BQTY)
            .salesOrderDetailsLineQty(UPDATED_SALES_ORDER_DETAILS_LINE_QTY)
            .salesOrderDetailsProcCode(UPDATED_SALES_ORDER_DETAILS_PROC_CODE)
            .salesOrderDetailsPriceOption(UPDATED_SALES_ORDER_DETAILS_PRICE_OPTION)
            .salesOrderDetailsModifier1(UPDATED_SALES_ORDER_DETAILS_MODIFIER_1)
            .salesOrderDetailsModifier2(UPDATED_SALES_ORDER_DETAILS_MODIFIER_2)
            .salesOrderDetailsModifier3(UPDATED_SALES_ORDER_DETAILS_MODIFIER_3)
            .salesOrderDetailsModifier4(UPDATED_SALES_ORDER_DETAILS_MODIFIER_4)
            .salesOrderDetailsChargeAmt(UPDATED_SALES_ORDER_DETAILS_CHARGE_AMT)
            .salesOrderDetailsAllowedAmt(UPDATED_SALES_ORDER_DETAILS_ALLOWED_AMT)
            .salesOrderDetailsTaxable(UPDATED_SALES_ORDER_DETAILS_TAXABLE)
            .salesOrderDetailsAbn(UPDATED_SALES_ORDER_DETAILS_ABN)
            .salesOrderDetailsAbnUpgrade(UPDATED_SALES_ORDER_DETAILS_ABN_UPGRADE)
            .salesOrderDetailsAbnPrintDate(UPDATED_SALES_ORDER_DETAILS_ABN_PRINT_DATE)
            .salesOrderDetailsAbnItem(UPDATED_SALES_ORDER_DETAILS_ABN_ITEM)
            .salesOrderDetailsAbnProcCode(UPDATED_SALES_ORDER_DETAILS_ABN_PROC_CODE)
            .salesOrderDetailsAbnAllow(UPDATED_SALES_ORDER_DETAILS_ABN_ALLOW)
            .salesOrderDetailsAbnCharge(UPDATED_SALES_ORDER_DETAILS_ABN_CHARGE)
            .salesOrderDetailsAbnModifier1(UPDATED_SALES_ORDER_DETAILS_ABN_MODIFIER_1)
            .salesOrderDetailsAbnModifier2(UPDATED_SALES_ORDER_DETAILS_ABN_MODIFIER_2)
            .salesOrderDetailsTaxRate(UPDATED_SALES_ORDER_DETAILS_TAX_RATE)
            .salesOrderDetailsTaxZone(UPDATED_SALES_ORDER_DETAILS_TAX_ZONE)
            .salesOrderDetailsNonTaxReason(UPDATED_SALES_ORDER_DETAILS_NON_TAX_REASON)
            .salesOrderDetailsNote(UPDATED_SALES_ORDER_DETAILS_NOTE)
            .salesOrderDetailsSaleType(UPDATED_SALES_ORDER_DETAILS_SALE_TYPE)
            .salesOrderDetailsItemGroup(UPDATED_SALES_ORDER_DETAILS_ITEM_GROUP)
            .salesOrderDetailsItemUser1(UPDATED_SALES_ORDER_DETAILS_ITEM_USER_1)
            .salesOrderDetailsItemUser2(UPDATED_SALES_ORDER_DETAILS_ITEM_USER_2)
            .salesOrderDetailsItemUser3(UPDATED_SALES_ORDER_DETAILS_ITEM_USER_3)
            .salesOrderDetailsItemUser4(UPDATED_SALES_ORDER_DETAILS_ITEM_USER_4)
            .salesOrderDetailsConvertedToPurchase(UPDATED_SALES_ORDER_DETAILS_CONVERTED_TO_PURCHASE)
            .salesOrderDetailsManualConvertToPurchaseMctp(UPDATED_SALES_ORDER_DETAILS_MANUAL_CONVERT_TO_PURCHASE_MCTP)
            .salesOrderDetailsMctpChargeAmt(UPDATED_SALES_ORDER_DETAILS_MCTP_CHARGE_AMT)
            .salesOrderDetailsMctpAllowedAmt(UPDATED_SALES_ORDER_DETAILS_MCTP_ALLOWED_AMT)
            .salesOrderDetailsMctpModifier1(UPDATED_SALES_ORDER_DETAILS_MCTP_MODIFIER_1)
            .salesOrderDetailsMctpModifier2(UPDATED_SALES_ORDER_DETAILS_MCTP_MODIFIER_2)
            .salesOrderDetailsMctpModifier3(UPDATED_SALES_ORDER_DETAILS_MCTP_MODIFIER_3)
            .salesOrderDetailsMctpModifier4(UPDATED_SALES_ORDER_DETAILS_MCTP_MODIFIER_4)
            .salesOrderDetailsMctpPeriod(UPDATED_SALES_ORDER_DETAILS_MCTP_PERIOD)
            .salesOrderDetailsAddtlModifier1(UPDATED_SALES_ORDER_DETAILS_ADDTL_MODIFIER_1)
            .salesOrderDetailsAddtlModifier2(UPDATED_SALES_ORDER_DETAILS_ADDTL_MODIFIER_2)
            .salesOrderDetailsAddtlModifier3(UPDATED_SALES_ORDER_DETAILS_ADDTL_MODIFIER_3)
            .salesOrderDetailsAddtlModifier4(UPDATED_SALES_ORDER_DETAILS_ADDTL_MODIFIER_4)
            .salesOrderDetailsNextDateOfService(UPDATED_SALES_ORDER_DETAILS_NEXT_DATE_OF_SERVICE)
            .salesOrderDetailsPriceTable(UPDATED_SALES_ORDER_DETAILS_PRICE_TABLE)
            .salesOrderDetailsPriceOptionName(UPDATED_SALES_ORDER_DETAILS_PRICE_OPTION_NAME)
            .salesOrderDetailsExtendedChargeAmount(UPDATED_SALES_ORDER_DETAILS_EXTENDED_CHARGE_AMOUNT)
            .salesOrderDetailsExtendedAllowanceAmount(UPDATED_SALES_ORDER_DETAILS_EXTENDED_ALLOWANCE_AMOUNT)
            .salesOrderDetailsItemNdcCode(UPDATED_SALES_ORDER_DETAILS_ITEM_NDC_CODE)
            .salesOrderDetailsManufacturer(UPDATED_SALES_ORDER_DETAILS_MANUFACTURER)
            .salesOrderDetailsCbPricing(UPDATED_SALES_ORDER_DETAILS_CB_PRICING)
            .salesOrderDetailsCbPriceTableOverride(UPDATED_SALES_ORDER_DETAILS_CB_PRICE_TABLE_OVERRIDE)
            .salesOrderDetailsCbOverride(UPDATED_SALES_ORDER_DETAILS_CB_OVERRIDE)
            .salesOrderDetailsMessages(UPDATED_SALES_ORDER_DETAILS_MESSAGES)
            .salesOrderDetailsLocation(UPDATED_SALES_ORDER_DETAILS_LOCATION)
            .salesOrderDetailsCaloriesPerDay(UPDATED_SALES_ORDER_DETAILS_CALORIES_PER_DAY)
            .salesOrderDetailsSecondaryBillingProcudureCode(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_PROCUDURE_CODE)
            .salesOrderDetailsSecondaryBillingPriceOption(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_PRICE_OPTION)
            .salesOrderDetailsSecondaryBillingPriceOptionName(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_PRICE_OPTION_NAME)
            .salesOrderDetailsSecondaryBillingModifier1(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_1)
            .salesOrderDetailsSecondaryBillingModifier2(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_2)
            .salesOrderDetailsSecondaryBillingModifier3(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_3)
            .salesOrderDetailsSecondaryBillingModifier4(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_4)
            .salesOrderDetailsSecondaryBillingAdditionalModifier1(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_1)
            .salesOrderDetailsSecondaryBillingAdditionalModifier2(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_2)
            .salesOrderDetailsSecondaryBillingAdditionalModifier3(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_3)
            .salesOrderDetailsSecondaryBillingAdditionalModifier4(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_4)
            .salesOrderDetailsSecondaryBillingIgnore(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_IGNORE)
            .salesOrderDetailsSecondarySpecialBilling(UPDATED_SALES_ORDER_DETAILS_SECONDARY_SPECIAL_BILLING)
            .salesOrderDetailsSpanDateSplitBilling(UPDATED_SALES_ORDER_DETAILS_SPAN_DATE_SPLIT_BILLING)
            .salesOrderDetailsCmnparCmnFormId(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_FORM_ID)
            .salesOrderDetailsCmnparCmnKey(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_KEY)
            .salesOrderDetailsCmnparCmnCreateDate(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_CREATE_DATE)
            .salesOrderDetailsCmnparCmnExpirationDate(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_EXPIRATION_DATE)
            .salesOrderDetailsCmnparCmnInitialDate(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_INITIAL_DATE)
            .salesOrderDetailsCmnparCmnRenewalDate(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_RENEWAL_DATE)
            .salesOrderDetailsCmnparCmnRecertificationDate(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_RECERTIFICATION_DATE)
            .salesOrderDetailsCmnparCmnPhysicianDate(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_PHYSICIAN_DATE)
            .salesOrderDetailsCmnparCmnStatus(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_STATUS)
            .salesOrderDetailsCmnparParId(UPDATED_SALES_ORDER_DETAILS_CMNPAR_PAR_ID)
            .salesOrderDetailsCmnparParDescr(UPDATED_SALES_ORDER_DETAILS_CMNPAR_PAR_DESCR)
            .salesOrderDetailsCmnparParInitialDate(UPDATED_SALES_ORDER_DETAILS_CMNPAR_PAR_INITIAL_DATE)
            .salesOrderDetailsCmnparParExpirationDate(UPDATED_SALES_ORDER_DETAILS_CMNPAR_PAR_EXPIRATION_DATE)
            .salesOrderDetailsCmnparCmnLogDate(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_LOG_DATE)
            .salesOrderDetailsCmnparCmnLengthOfNeed(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_LENGTH_OF_NEED)
            .salesOrderDetailsCmnparCmnPrintedDate(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_PRINTED_DATE)
            .salesOrderDetailsCmnparCmnPrintedBy(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_PRINTED_BY)
            .salesOrderDetailsCmnparFaxedDate(UPDATED_SALES_ORDER_DETAILS_CMNPAR_FAXED_DATE)
            .salesOrderDetailsCmnparCmnPlaceholder(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_PLACEHOLDER)
            .salesOrderDetailsCmnparCmnFaxedBy(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_FAXED_BY)
            .salesOrderDetailsCmnparCmnLoggedBy(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_LOGGED_BY)
            .salesOrderDetailsCmnparNumberOfRefills(UPDATED_SALES_ORDER_DETAILS_CMNPAR_NUMBER_OF_REFILLS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .salesOrderDetailsManufacturerItemIdNumber(UPDATED_SALES_ORDER_DETAILS_MANUFACTURER_ITEM_ID_NUMBER)
            .cmnId(UPDATED_CMN_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .salesOrderItemDetailsUuid(UPDATED_SALES_ORDER_ITEM_DETAILS_UUID)
            .salesOrderItemNumber(UPDATED_SALES_ORDER_ITEM_NUMBER)
            .isAssetTagged(UPDATED_IS_ASSET_TAGGED)
            .itemSerialNo(UPDATED_ITEM_SERIAL_NO)
            .salesOrderDetailsIcdPointer(UPDATED_SALES_ORDER_DETAILS_ICD_POINTER)
            .procedureIdentifier(UPDATED_PROCEDURE_IDENTIFIER)
            .parNo(UPDATED_PAR_NO)
            .whetherSerialised(UPDATED_WHETHER_SERIALISED)
            .pickupExchangeNo(UPDATED_PICKUP_EXCHANGE_NO)
            .salesOrderAbnUserResponse(UPDATED_SALES_ORDER_ABN_USER_RESPONSE)
            .isDropshipAllowed(UPDATED_IS_DROPSHIP_ALLOWED)
            .poNumber(UPDATED_PO_NUMBER)
            .purchaseOrderUuid(UPDATED_PURCHASE_ORDER_UUID)
            .isResupplyType(UPDATED_IS_RESUPPLY_TYPE)
            .frequencyCount(UPDATED_FREQUENCY_COUNT)
            .frequencyInterval(UPDATED_FREQUENCY_INTERVAL)
            .itemGroupId(UPDATED_ITEM_GROUP_ID);
        SalesOrderItemDetailsDTO salesOrderItemDetailsDTO = salesOrderItemDetailsMapper.toDto(updatedSalesOrderItemDetails);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, salesOrderItemDetailsDTO.getSalesOrderItemDetailsId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderItemDetailsDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SalesOrderItemDetails in the database
        List<SalesOrderItemDetails> salesOrderItemDetailsList = salesOrderItemDetailsRepository.findAll().collectList().block();
        assertThat(salesOrderItemDetailsList).hasSize(databaseSizeBeforeUpdate);
        SalesOrderItemDetails testSalesOrderItemDetails = salesOrderItemDetailsList.get(salesOrderItemDetailsList.size() - 1);
        assertThat(testSalesOrderItemDetails.getSalesOrderId()).isEqualTo(UPDATED_SALES_ORDER_ID);
        assertThat(testSalesOrderItemDetails.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testSalesOrderItemDetails.getItemLocationId()).isEqualTo(UPDATED_ITEM_LOCATION_ID);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsItemId()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ITEM_ID);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsItemName()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ITEM_NAME);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsStockingUom()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_STOCKING_UOM);
        assertThat(testSalesOrderItemDetails.getItemAssetNo()).isEqualTo(UPDATED_ITEM_ASSET_NO);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsItemDescription()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ITEM_DESCRIPTION);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsDefaultVendor()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_DEFAULT_VENDOR);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsOriginalDos()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ORIGINAL_DOS);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsPreviousBillingDate())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_PREVIOUS_BILLING_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsNextBillingDate())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_NEXT_BILLING_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsDosTo()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_DOS_TO);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsNextPeriod()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_NEXT_PERIOD);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSpecialPricing()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_SPECIAL_PRICING);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsPriceOverride()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_PRICE_OVERRIDE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSpecialTaxRate()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_SPECIAL_TAX_RATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsQty()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_QTY);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsBqty()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_BQTY);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsLineQty()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_LINE_QTY);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsProcCode()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_PROC_CODE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsPriceOption()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_PRICE_OPTION);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsModifier1()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_MODIFIER_1);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsModifier2()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_MODIFIER_2);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsModifier3()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_MODIFIER_3);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsModifier4()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_MODIFIER_4);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsChargeAmt()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_CHARGE_AMT);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAllowedAmt()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ALLOWED_AMT);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsTaxable()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_TAXABLE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAbn()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAbnUpgrade()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_UPGRADE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAbnPrintDate()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_PRINT_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAbnItem()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_ITEM);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAbnProcCode()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_PROC_CODE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAbnAllow()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_ALLOW);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAbnCharge()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_CHARGE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAbnModifier1()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_MODIFIER_1);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAbnModifier2()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_MODIFIER_2);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsTaxRate()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_TAX_RATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsTaxZone()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_TAX_ZONE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsNonTaxReason()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_NON_TAX_REASON);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsNote()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_NOTE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSaleType()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_SALE_TYPE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsItemGroup()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ITEM_GROUP);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsItemUser1()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ITEM_USER_1);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsItemUser2()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ITEM_USER_2);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsItemUser3()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ITEM_USER_3);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsItemUser4()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ITEM_USER_4);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsConvertedToPurchase())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CONVERTED_TO_PURCHASE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsManualConvertToPurchaseMctp())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_MANUAL_CONVERT_TO_PURCHASE_MCTP);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsMctpChargeAmt()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_MCTP_CHARGE_AMT);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsMctpAllowedAmt()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_MCTP_ALLOWED_AMT);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsMctpModifier1()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_MCTP_MODIFIER_1);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsMctpModifier2()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_MCTP_MODIFIER_2);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsMctpModifier3()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_MCTP_MODIFIER_3);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsMctpModifier4()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_MCTP_MODIFIER_4);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsMctpPeriod()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_MCTP_PERIOD);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAddtlModifier1()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ADDTL_MODIFIER_1);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAddtlModifier2()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ADDTL_MODIFIER_2);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAddtlModifier3()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ADDTL_MODIFIER_3);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAddtlModifier4()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ADDTL_MODIFIER_4);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsNextDateOfService())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_NEXT_DATE_OF_SERVICE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsPriceTable()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_PRICE_TABLE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsPriceOptionName())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_PRICE_OPTION_NAME);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsExtendedChargeAmount())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_EXTENDED_CHARGE_AMOUNT);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsExtendedAllowanceAmount())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_EXTENDED_ALLOWANCE_AMOUNT);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsItemNdcCode()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ITEM_NDC_CODE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsManufacturer()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_MANUFACTURER);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCbPricing()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_CB_PRICING);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCbPriceTableOverride())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CB_PRICE_TABLE_OVERRIDE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCbOverride()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_CB_OVERRIDE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsMessages()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_MESSAGES);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsLocation()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_LOCATION);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCaloriesPerDay()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_CALORIES_PER_DAY);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingProcudureCode())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_PROCUDURE_CODE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingPriceOption())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_PRICE_OPTION);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingPriceOptionName())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_PRICE_OPTION_NAME);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingModifier1())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_1);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingModifier2())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_2);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingModifier3())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_3);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingModifier4())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_4);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingAdditionalModifier1())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_1);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingAdditionalModifier2())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_2);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingAdditionalModifier3())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_3);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingAdditionalModifier4())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_4);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingIgnore())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_IGNORE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondarySpecialBilling())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SECONDARY_SPECIAL_BILLING);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSpanDateSplitBilling())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SPAN_DATE_SPLIT_BILLING);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnFormId())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_FORM_ID);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnKey()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_KEY);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnCreateDate())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_CREATE_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnExpirationDate())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_EXPIRATION_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnInitialDate())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_INITIAL_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnRenewalDate())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_RENEWAL_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnRecertificationDate())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_RECERTIFICATION_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnPhysicianDate())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_PHYSICIAN_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnStatus())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_STATUS);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparParId()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_PAR_ID);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparParDescr()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_PAR_DESCR);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparParInitialDate())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_PAR_INITIAL_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparParExpirationDate())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_PAR_EXPIRATION_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnLogDate())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_LOG_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnLengthOfNeed())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_LENGTH_OF_NEED);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnPrintedDate())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_PRINTED_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnPrintedBy())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_PRINTED_BY);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparFaxedDate())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_FAXED_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnPlaceholder())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_PLACEHOLDER);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnFaxedBy())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_FAXED_BY);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnLoggedBy())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_LOGGED_BY);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparNumberOfRefills())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_NUMBER_OF_REFILLS);
        assertThat(testSalesOrderItemDetails.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testSalesOrderItemDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSalesOrderItemDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSalesOrderItemDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSalesOrderItemDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsManufacturerItemIdNumber())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_MANUFACTURER_ITEM_ID_NUMBER);
        assertThat(testSalesOrderItemDetails.getCmnId()).isEqualTo(UPDATED_CMN_ID);
        assertThat(testSalesOrderItemDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testSalesOrderItemDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testSalesOrderItemDetails.getSalesOrderItemDetailsUuid()).isEqualTo(UPDATED_SALES_ORDER_ITEM_DETAILS_UUID);
        assertThat(testSalesOrderItemDetails.getSalesOrderItemNumber()).isEqualTo(UPDATED_SALES_ORDER_ITEM_NUMBER);
        assertThat(testSalesOrderItemDetails.getIsAssetTagged()).isEqualTo(UPDATED_IS_ASSET_TAGGED);
        assertThat(testSalesOrderItemDetails.getItemSerialNo()).isEqualTo(UPDATED_ITEM_SERIAL_NO);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsIcdPointer()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ICD_POINTER);
        assertThat(testSalesOrderItemDetails.getProcedureIdentifier()).isEqualTo(UPDATED_PROCEDURE_IDENTIFIER);
        assertThat(testSalesOrderItemDetails.getParNo()).isEqualTo(UPDATED_PAR_NO);
        assertThat(testSalesOrderItemDetails.getWhetherSerialised()).isEqualTo(UPDATED_WHETHER_SERIALISED);
        assertThat(testSalesOrderItemDetails.getPickupExchangeNo()).isEqualTo(UPDATED_PICKUP_EXCHANGE_NO);
        assertThat(testSalesOrderItemDetails.getSalesOrderAbnUserResponse()).isEqualTo(UPDATED_SALES_ORDER_ABN_USER_RESPONSE);
        assertThat(testSalesOrderItemDetails.getIsDropshipAllowed()).isEqualTo(UPDATED_IS_DROPSHIP_ALLOWED);
        assertThat(testSalesOrderItemDetails.getPoNumber()).isEqualTo(UPDATED_PO_NUMBER);
        assertThat(testSalesOrderItemDetails.getPurchaseOrderUuid()).isEqualTo(UPDATED_PURCHASE_ORDER_UUID);
        assertThat(testSalesOrderItemDetails.getIsResupplyType()).isEqualTo(UPDATED_IS_RESUPPLY_TYPE);
        assertThat(testSalesOrderItemDetails.getFrequencyCount()).isEqualTo(UPDATED_FREQUENCY_COUNT);
        assertThat(testSalesOrderItemDetails.getFrequencyInterval()).isEqualTo(UPDATED_FREQUENCY_INTERVAL);
        assertThat(testSalesOrderItemDetails.getItemGroupId()).isEqualTo(UPDATED_ITEM_GROUP_ID);
    }

    @Test
    void putNonExistingSalesOrderItemDetails() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderItemDetailsRepository.findAll().collectList().block().size();
        salesOrderItemDetails.setSalesOrderItemDetailsId(count.incrementAndGet());

        // Create the SalesOrderItemDetails
        SalesOrderItemDetailsDTO salesOrderItemDetailsDTO = salesOrderItemDetailsMapper.toDto(salesOrderItemDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, salesOrderItemDetailsDTO.getSalesOrderItemDetailsId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderItemDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderItemDetails in the database
        List<SalesOrderItemDetails> salesOrderItemDetailsList = salesOrderItemDetailsRepository.findAll().collectList().block();
        assertThat(salesOrderItemDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchSalesOrderItemDetails() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderItemDetailsRepository.findAll().collectList().block().size();
        salesOrderItemDetails.setSalesOrderItemDetailsId(count.incrementAndGet());

        // Create the SalesOrderItemDetails
        SalesOrderItemDetailsDTO salesOrderItemDetailsDTO = salesOrderItemDetailsMapper.toDto(salesOrderItemDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderItemDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderItemDetails in the database
        List<SalesOrderItemDetails> salesOrderItemDetailsList = salesOrderItemDetailsRepository.findAll().collectList().block();
        assertThat(salesOrderItemDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamSalesOrderItemDetails() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderItemDetailsRepository.findAll().collectList().block().size();
        salesOrderItemDetails.setSalesOrderItemDetailsId(count.incrementAndGet());

        // Create the SalesOrderItemDetails
        SalesOrderItemDetailsDTO salesOrderItemDetailsDTO = salesOrderItemDetailsMapper.toDto(salesOrderItemDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderItemDetailsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SalesOrderItemDetails in the database
        List<SalesOrderItemDetails> salesOrderItemDetailsList = salesOrderItemDetailsRepository.findAll().collectList().block();
        assertThat(salesOrderItemDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateSalesOrderItemDetailsWithPatch() throws Exception {
        // Initialize the database
        salesOrderItemDetailsRepository.save(salesOrderItemDetails).block();

        int databaseSizeBeforeUpdate = salesOrderItemDetailsRepository.findAll().collectList().block().size();

        // Update the salesOrderItemDetails using partial update
        SalesOrderItemDetails partialUpdatedSalesOrderItemDetails = new SalesOrderItemDetails();
        partialUpdatedSalesOrderItemDetails.setSalesOrderItemDetailsId(salesOrderItemDetails.getSalesOrderItemDetailsId());

        partialUpdatedSalesOrderItemDetails
            .salesOrderId(UPDATED_SALES_ORDER_ID)
            .patientId(UPDATED_PATIENT_ID)
            .salesOrderDetailsItemId(UPDATED_SALES_ORDER_DETAILS_ITEM_ID)
            .salesOrderDetailsItemName(UPDATED_SALES_ORDER_DETAILS_ITEM_NAME)
            .salesOrderDetailsDosTo(UPDATED_SALES_ORDER_DETAILS_DOS_TO)
            .salesOrderDetailsNextPeriod(UPDATED_SALES_ORDER_DETAILS_NEXT_PERIOD)
            .salesOrderDetailsSpecialPricing(UPDATED_SALES_ORDER_DETAILS_SPECIAL_PRICING)
            .salesOrderDetailsSpecialTaxRate(UPDATED_SALES_ORDER_DETAILS_SPECIAL_TAX_RATE)
            .salesOrderDetailsBqty(UPDATED_SALES_ORDER_DETAILS_BQTY)
            .salesOrderDetailsProcCode(UPDATED_SALES_ORDER_DETAILS_PROC_CODE)
            .salesOrderDetailsPriceOption(UPDATED_SALES_ORDER_DETAILS_PRICE_OPTION)
            .salesOrderDetailsModifier3(UPDATED_SALES_ORDER_DETAILS_MODIFIER_3)
            .salesOrderDetailsAbnUpgrade(UPDATED_SALES_ORDER_DETAILS_ABN_UPGRADE)
            .salesOrderDetailsAbnPrintDate(UPDATED_SALES_ORDER_DETAILS_ABN_PRINT_DATE)
            .salesOrderDetailsAbnAllow(UPDATED_SALES_ORDER_DETAILS_ABN_ALLOW)
            .salesOrderDetailsAbnModifier2(UPDATED_SALES_ORDER_DETAILS_ABN_MODIFIER_2)
            .salesOrderDetailsTaxZone(UPDATED_SALES_ORDER_DETAILS_TAX_ZONE)
            .salesOrderDetailsNonTaxReason(UPDATED_SALES_ORDER_DETAILS_NON_TAX_REASON)
            .salesOrderDetailsSaleType(UPDATED_SALES_ORDER_DETAILS_SALE_TYPE)
            .salesOrderDetailsItemUser1(UPDATED_SALES_ORDER_DETAILS_ITEM_USER_1)
            .salesOrderDetailsItemUser2(UPDATED_SALES_ORDER_DETAILS_ITEM_USER_2)
            .salesOrderDetailsItemUser3(UPDATED_SALES_ORDER_DETAILS_ITEM_USER_3)
            .salesOrderDetailsConvertedToPurchase(UPDATED_SALES_ORDER_DETAILS_CONVERTED_TO_PURCHASE)
            .salesOrderDetailsMctpChargeAmt(UPDATED_SALES_ORDER_DETAILS_MCTP_CHARGE_AMT)
            .salesOrderDetailsMctpAllowedAmt(UPDATED_SALES_ORDER_DETAILS_MCTP_ALLOWED_AMT)
            .salesOrderDetailsMctpModifier1(UPDATED_SALES_ORDER_DETAILS_MCTP_MODIFIER_1)
            .salesOrderDetailsMctpModifier3(UPDATED_SALES_ORDER_DETAILS_MCTP_MODIFIER_3)
            .salesOrderDetailsMctpModifier4(UPDATED_SALES_ORDER_DETAILS_MCTP_MODIFIER_4)
            .salesOrderDetailsMctpPeriod(UPDATED_SALES_ORDER_DETAILS_MCTP_PERIOD)
            .salesOrderDetailsAddtlModifier2(UPDATED_SALES_ORDER_DETAILS_ADDTL_MODIFIER_2)
            .salesOrderDetailsNextDateOfService(UPDATED_SALES_ORDER_DETAILS_NEXT_DATE_OF_SERVICE)
            .salesOrderDetailsPriceTable(UPDATED_SALES_ORDER_DETAILS_PRICE_TABLE)
            .salesOrderDetailsExtendedAllowanceAmount(UPDATED_SALES_ORDER_DETAILS_EXTENDED_ALLOWANCE_AMOUNT)
            .salesOrderDetailsManufacturer(UPDATED_SALES_ORDER_DETAILS_MANUFACTURER)
            .salesOrderDetailsCbPricing(UPDATED_SALES_ORDER_DETAILS_CB_PRICING)
            .salesOrderDetailsCbPriceTableOverride(UPDATED_SALES_ORDER_DETAILS_CB_PRICE_TABLE_OVERRIDE)
            .salesOrderDetailsCbOverride(UPDATED_SALES_ORDER_DETAILS_CB_OVERRIDE)
            .salesOrderDetailsMessages(UPDATED_SALES_ORDER_DETAILS_MESSAGES)
            .salesOrderDetailsSecondaryBillingPriceOption(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_PRICE_OPTION)
            .salesOrderDetailsSecondaryBillingPriceOptionName(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_PRICE_OPTION_NAME)
            .salesOrderDetailsSecondaryBillingModifier1(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_1)
            .salesOrderDetailsSecondaryBillingModifier2(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_2)
            .salesOrderDetailsSecondaryBillingModifier3(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_3)
            .salesOrderDetailsSecondaryBillingModifier4(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_4)
            .salesOrderDetailsSecondaryBillingAdditionalModifier1(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_1)
            .salesOrderDetailsSecondaryBillingAdditionalModifier2(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_2)
            .salesOrderDetailsSecondaryBillingAdditionalModifier4(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_4)
            .salesOrderDetailsSecondaryBillingIgnore(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_IGNORE)
            .salesOrderDetailsSecondarySpecialBilling(UPDATED_SALES_ORDER_DETAILS_SECONDARY_SPECIAL_BILLING)
            .salesOrderDetailsSpanDateSplitBilling(UPDATED_SALES_ORDER_DETAILS_SPAN_DATE_SPLIT_BILLING)
            .salesOrderDetailsCmnparCmnFormId(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_FORM_ID)
            .salesOrderDetailsCmnparCmnExpirationDate(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_EXPIRATION_DATE)
            .salesOrderDetailsCmnparCmnRenewalDate(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_RENEWAL_DATE)
            .salesOrderDetailsCmnparCmnRecertificationDate(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_RECERTIFICATION_DATE)
            .salesOrderDetailsCmnparParId(UPDATED_SALES_ORDER_DETAILS_CMNPAR_PAR_ID)
            .salesOrderDetailsCmnparParDescr(UPDATED_SALES_ORDER_DETAILS_CMNPAR_PAR_DESCR)
            .salesOrderDetailsCmnparParInitialDate(UPDATED_SALES_ORDER_DETAILS_CMNPAR_PAR_INITIAL_DATE)
            .salesOrderDetailsCmnparParExpirationDate(UPDATED_SALES_ORDER_DETAILS_CMNPAR_PAR_EXPIRATION_DATE)
            .salesOrderDetailsCmnparCmnPrintedBy(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_PRINTED_BY)
            .salesOrderDetailsCmnparCmnPlaceholder(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_PLACEHOLDER)
            .salesOrderDetailsCmnparNumberOfRefills(UPDATED_SALES_ORDER_DETAILS_CMNPAR_NUMBER_OF_REFILLS)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .salesOrderDetailsManufacturerItemIdNumber(UPDATED_SALES_ORDER_DETAILS_MANUFACTURER_ITEM_ID_NUMBER)
            .cmnId(UPDATED_CMN_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .salesOrderItemDetailsUuid(UPDATED_SALES_ORDER_ITEM_DETAILS_UUID)
            .salesOrderItemNumber(UPDATED_SALES_ORDER_ITEM_NUMBER)
            .isAssetTagged(UPDATED_IS_ASSET_TAGGED)
            .whetherSerialised(UPDATED_WHETHER_SERIALISED)
            .pickupExchangeNo(UPDATED_PICKUP_EXCHANGE_NO)
            .poNumber(UPDATED_PO_NUMBER)
            .purchaseOrderUuid(UPDATED_PURCHASE_ORDER_UUID)
            .frequencyInterval(UPDATED_FREQUENCY_INTERVAL);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSalesOrderItemDetails.getSalesOrderItemDetailsId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSalesOrderItemDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SalesOrderItemDetails in the database
        List<SalesOrderItemDetails> salesOrderItemDetailsList = salesOrderItemDetailsRepository.findAll().collectList().block();
        assertThat(salesOrderItemDetailsList).hasSize(databaseSizeBeforeUpdate);
        SalesOrderItemDetails testSalesOrderItemDetails = salesOrderItemDetailsList.get(salesOrderItemDetailsList.size() - 1);
        assertThat(testSalesOrderItemDetails.getSalesOrderId()).isEqualTo(UPDATED_SALES_ORDER_ID);
        assertThat(testSalesOrderItemDetails.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testSalesOrderItemDetails.getItemLocationId()).isEqualTo(DEFAULT_ITEM_LOCATION_ID);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsItemId()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ITEM_ID);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsItemName()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ITEM_NAME);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsStockingUom()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_STOCKING_UOM);
        assertThat(testSalesOrderItemDetails.getItemAssetNo()).isEqualTo(DEFAULT_ITEM_ASSET_NO);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsItemDescription()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ITEM_DESCRIPTION);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsDefaultVendor()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_DEFAULT_VENDOR);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsOriginalDos()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ORIGINAL_DOS);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsPreviousBillingDate())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_PREVIOUS_BILLING_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsNextBillingDate())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_NEXT_BILLING_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsDosTo()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_DOS_TO);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsNextPeriod()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_NEXT_PERIOD);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSpecialPricing()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_SPECIAL_PRICING);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsPriceOverride()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_PRICE_OVERRIDE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSpecialTaxRate()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_SPECIAL_TAX_RATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsQty()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_QTY);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsBqty()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_BQTY);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsLineQty()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_LINE_QTY);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsProcCode()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_PROC_CODE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsPriceOption()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_PRICE_OPTION);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsModifier1()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_MODIFIER_1);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsModifier2()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_MODIFIER_2);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsModifier3()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_MODIFIER_3);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsModifier4()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_MODIFIER_4);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsChargeAmt()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CHARGE_AMT);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAllowedAmt()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ALLOWED_AMT);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsTaxable()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_TAXABLE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAbn()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ABN);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAbnUpgrade()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_UPGRADE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAbnPrintDate()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_PRINT_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAbnItem()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ABN_ITEM);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAbnProcCode()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ABN_PROC_CODE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAbnAllow()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_ALLOW);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAbnCharge()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ABN_CHARGE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAbnModifier1()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ABN_MODIFIER_1);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAbnModifier2()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_MODIFIER_2);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsTaxRate()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_TAX_RATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsTaxZone()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_TAX_ZONE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsNonTaxReason()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_NON_TAX_REASON);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsNote()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_NOTE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSaleType()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_SALE_TYPE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsItemGroup()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ITEM_GROUP);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsItemUser1()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ITEM_USER_1);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsItemUser2()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ITEM_USER_2);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsItemUser3()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ITEM_USER_3);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsItemUser4()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ITEM_USER_4);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsConvertedToPurchase())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CONVERTED_TO_PURCHASE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsManualConvertToPurchaseMctp())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_MANUAL_CONVERT_TO_PURCHASE_MCTP);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsMctpChargeAmt()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_MCTP_CHARGE_AMT);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsMctpAllowedAmt()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_MCTP_ALLOWED_AMT);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsMctpModifier1()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_MCTP_MODIFIER_1);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsMctpModifier2()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_MCTP_MODIFIER_2);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsMctpModifier3()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_MCTP_MODIFIER_3);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsMctpModifier4()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_MCTP_MODIFIER_4);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsMctpPeriod()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_MCTP_PERIOD);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAddtlModifier1()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ADDTL_MODIFIER_1);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAddtlModifier2()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ADDTL_MODIFIER_2);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAddtlModifier3()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ADDTL_MODIFIER_3);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAddtlModifier4()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ADDTL_MODIFIER_4);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsNextDateOfService())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_NEXT_DATE_OF_SERVICE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsPriceTable()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_PRICE_TABLE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsPriceOptionName())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_PRICE_OPTION_NAME);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsExtendedChargeAmount())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_EXTENDED_CHARGE_AMOUNT);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsExtendedAllowanceAmount())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_EXTENDED_ALLOWANCE_AMOUNT);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsItemNdcCode()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ITEM_NDC_CODE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsManufacturer()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_MANUFACTURER);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCbPricing()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_CB_PRICING);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCbPriceTableOverride())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CB_PRICE_TABLE_OVERRIDE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCbOverride()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_CB_OVERRIDE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsMessages()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_MESSAGES);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsLocation()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_LOCATION);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCaloriesPerDay()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CALORIES_PER_DAY);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingProcudureCode())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_PROCUDURE_CODE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingPriceOption())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_PRICE_OPTION);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingPriceOptionName())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_PRICE_OPTION_NAME);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingModifier1())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_1);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingModifier2())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_2);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingModifier3())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_3);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingModifier4())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_4);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingAdditionalModifier1())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_1);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingAdditionalModifier2())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_2);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingAdditionalModifier3())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_3);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingAdditionalModifier4())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_4);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingIgnore())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_IGNORE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondarySpecialBilling())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SECONDARY_SPECIAL_BILLING);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSpanDateSplitBilling())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SPAN_DATE_SPLIT_BILLING);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnFormId())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_FORM_ID);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnKey()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_KEY);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnCreateDate())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_CREATE_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnExpirationDate())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_EXPIRATION_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnInitialDate())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_INITIAL_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnRenewalDate())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_RENEWAL_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnRecertificationDate())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_RECERTIFICATION_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnPhysicianDate())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_PHYSICIAN_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnStatus())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_STATUS);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparParId()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_PAR_ID);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparParDescr()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_PAR_DESCR);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparParInitialDate())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_PAR_INITIAL_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparParExpirationDate())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_PAR_EXPIRATION_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnLogDate())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_LOG_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnLengthOfNeed())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_LENGTH_OF_NEED);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnPrintedDate())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_PRINTED_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnPrintedBy())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_PRINTED_BY);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparFaxedDate())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_FAXED_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnPlaceholder())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_PLACEHOLDER);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnFaxedBy())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_FAXED_BY);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnLoggedBy())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_CMNPAR_CMN_LOGGED_BY);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparNumberOfRefills())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_NUMBER_OF_REFILLS);
        assertThat(testSalesOrderItemDetails.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testSalesOrderItemDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSalesOrderItemDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSalesOrderItemDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSalesOrderItemDetails.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsManufacturerItemIdNumber())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_MANUFACTURER_ITEM_ID_NUMBER);
        assertThat(testSalesOrderItemDetails.getCmnId()).isEqualTo(UPDATED_CMN_ID);
        assertThat(testSalesOrderItemDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testSalesOrderItemDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testSalesOrderItemDetails.getSalesOrderItemDetailsUuid()).isEqualTo(UPDATED_SALES_ORDER_ITEM_DETAILS_UUID);
        assertThat(testSalesOrderItemDetails.getSalesOrderItemNumber()).isEqualTo(UPDATED_SALES_ORDER_ITEM_NUMBER);
        assertThat(testSalesOrderItemDetails.getIsAssetTagged()).isEqualTo(UPDATED_IS_ASSET_TAGGED);
        assertThat(testSalesOrderItemDetails.getItemSerialNo()).isEqualTo(DEFAULT_ITEM_SERIAL_NO);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsIcdPointer()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ICD_POINTER);
        assertThat(testSalesOrderItemDetails.getProcedureIdentifier()).isEqualTo(DEFAULT_PROCEDURE_IDENTIFIER);
        assertThat(testSalesOrderItemDetails.getParNo()).isEqualTo(DEFAULT_PAR_NO);
        assertThat(testSalesOrderItemDetails.getWhetherSerialised()).isEqualTo(UPDATED_WHETHER_SERIALISED);
        assertThat(testSalesOrderItemDetails.getPickupExchangeNo()).isEqualTo(UPDATED_PICKUP_EXCHANGE_NO);
        assertThat(testSalesOrderItemDetails.getSalesOrderAbnUserResponse()).isEqualTo(DEFAULT_SALES_ORDER_ABN_USER_RESPONSE);
        assertThat(testSalesOrderItemDetails.getIsDropshipAllowed()).isEqualTo(DEFAULT_IS_DROPSHIP_ALLOWED);
        assertThat(testSalesOrderItemDetails.getPoNumber()).isEqualTo(UPDATED_PO_NUMBER);
        assertThat(testSalesOrderItemDetails.getPurchaseOrderUuid()).isEqualTo(UPDATED_PURCHASE_ORDER_UUID);
        assertThat(testSalesOrderItemDetails.getIsResupplyType()).isEqualTo(DEFAULT_IS_RESUPPLY_TYPE);
        assertThat(testSalesOrderItemDetails.getFrequencyCount()).isEqualTo(DEFAULT_FREQUENCY_COUNT);
        assertThat(testSalesOrderItemDetails.getFrequencyInterval()).isEqualTo(UPDATED_FREQUENCY_INTERVAL);
        assertThat(testSalesOrderItemDetails.getItemGroupId()).isEqualTo(DEFAULT_ITEM_GROUP_ID);
    }

    @Test
    void fullUpdateSalesOrderItemDetailsWithPatch() throws Exception {
        // Initialize the database
        salesOrderItemDetailsRepository.save(salesOrderItemDetails).block();

        int databaseSizeBeforeUpdate = salesOrderItemDetailsRepository.findAll().collectList().block().size();

        // Update the salesOrderItemDetails using partial update
        SalesOrderItemDetails partialUpdatedSalesOrderItemDetails = new SalesOrderItemDetails();
        partialUpdatedSalesOrderItemDetails.setSalesOrderItemDetailsId(salesOrderItemDetails.getSalesOrderItemDetailsId());

        partialUpdatedSalesOrderItemDetails
            .salesOrderId(UPDATED_SALES_ORDER_ID)
            .patientId(UPDATED_PATIENT_ID)
            .itemLocationId(UPDATED_ITEM_LOCATION_ID)
            .salesOrderDetailsItemId(UPDATED_SALES_ORDER_DETAILS_ITEM_ID)
            .salesOrderDetailsItemName(UPDATED_SALES_ORDER_DETAILS_ITEM_NAME)
            .salesOrderDetailsStockingUom(UPDATED_SALES_ORDER_DETAILS_STOCKING_UOM)
            .itemAssetNo(UPDATED_ITEM_ASSET_NO)
            .salesOrderDetailsItemDescription(UPDATED_SALES_ORDER_DETAILS_ITEM_DESCRIPTION)
            .salesOrderDetailsDefaultVendor(UPDATED_SALES_ORDER_DETAILS_DEFAULT_VENDOR)
            .salesOrderDetailsOriginalDos(UPDATED_SALES_ORDER_DETAILS_ORIGINAL_DOS)
            .salesOrderDetailsPreviousBillingDate(UPDATED_SALES_ORDER_DETAILS_PREVIOUS_BILLING_DATE)
            .salesOrderDetailsNextBillingDate(UPDATED_SALES_ORDER_DETAILS_NEXT_BILLING_DATE)
            .salesOrderDetailsDosTo(UPDATED_SALES_ORDER_DETAILS_DOS_TO)
            .salesOrderDetailsNextPeriod(UPDATED_SALES_ORDER_DETAILS_NEXT_PERIOD)
            .salesOrderDetailsSpecialPricing(UPDATED_SALES_ORDER_DETAILS_SPECIAL_PRICING)
            .salesOrderDetailsPriceOverride(UPDATED_SALES_ORDER_DETAILS_PRICE_OVERRIDE)
            .salesOrderDetailsSpecialTaxRate(UPDATED_SALES_ORDER_DETAILS_SPECIAL_TAX_RATE)
            .salesOrderDetailsQty(UPDATED_SALES_ORDER_DETAILS_QTY)
            .salesOrderDetailsBqty(UPDATED_SALES_ORDER_DETAILS_BQTY)
            .salesOrderDetailsLineQty(UPDATED_SALES_ORDER_DETAILS_LINE_QTY)
            .salesOrderDetailsProcCode(UPDATED_SALES_ORDER_DETAILS_PROC_CODE)
            .salesOrderDetailsPriceOption(UPDATED_SALES_ORDER_DETAILS_PRICE_OPTION)
            .salesOrderDetailsModifier1(UPDATED_SALES_ORDER_DETAILS_MODIFIER_1)
            .salesOrderDetailsModifier2(UPDATED_SALES_ORDER_DETAILS_MODIFIER_2)
            .salesOrderDetailsModifier3(UPDATED_SALES_ORDER_DETAILS_MODIFIER_3)
            .salesOrderDetailsModifier4(UPDATED_SALES_ORDER_DETAILS_MODIFIER_4)
            .salesOrderDetailsChargeAmt(UPDATED_SALES_ORDER_DETAILS_CHARGE_AMT)
            .salesOrderDetailsAllowedAmt(UPDATED_SALES_ORDER_DETAILS_ALLOWED_AMT)
            .salesOrderDetailsTaxable(UPDATED_SALES_ORDER_DETAILS_TAXABLE)
            .salesOrderDetailsAbn(UPDATED_SALES_ORDER_DETAILS_ABN)
            .salesOrderDetailsAbnUpgrade(UPDATED_SALES_ORDER_DETAILS_ABN_UPGRADE)
            .salesOrderDetailsAbnPrintDate(UPDATED_SALES_ORDER_DETAILS_ABN_PRINT_DATE)
            .salesOrderDetailsAbnItem(UPDATED_SALES_ORDER_DETAILS_ABN_ITEM)
            .salesOrderDetailsAbnProcCode(UPDATED_SALES_ORDER_DETAILS_ABN_PROC_CODE)
            .salesOrderDetailsAbnAllow(UPDATED_SALES_ORDER_DETAILS_ABN_ALLOW)
            .salesOrderDetailsAbnCharge(UPDATED_SALES_ORDER_DETAILS_ABN_CHARGE)
            .salesOrderDetailsAbnModifier1(UPDATED_SALES_ORDER_DETAILS_ABN_MODIFIER_1)
            .salesOrderDetailsAbnModifier2(UPDATED_SALES_ORDER_DETAILS_ABN_MODIFIER_2)
            .salesOrderDetailsTaxRate(UPDATED_SALES_ORDER_DETAILS_TAX_RATE)
            .salesOrderDetailsTaxZone(UPDATED_SALES_ORDER_DETAILS_TAX_ZONE)
            .salesOrderDetailsNonTaxReason(UPDATED_SALES_ORDER_DETAILS_NON_TAX_REASON)
            .salesOrderDetailsNote(UPDATED_SALES_ORDER_DETAILS_NOTE)
            .salesOrderDetailsSaleType(UPDATED_SALES_ORDER_DETAILS_SALE_TYPE)
            .salesOrderDetailsItemGroup(UPDATED_SALES_ORDER_DETAILS_ITEM_GROUP)
            .salesOrderDetailsItemUser1(UPDATED_SALES_ORDER_DETAILS_ITEM_USER_1)
            .salesOrderDetailsItemUser2(UPDATED_SALES_ORDER_DETAILS_ITEM_USER_2)
            .salesOrderDetailsItemUser3(UPDATED_SALES_ORDER_DETAILS_ITEM_USER_3)
            .salesOrderDetailsItemUser4(UPDATED_SALES_ORDER_DETAILS_ITEM_USER_4)
            .salesOrderDetailsConvertedToPurchase(UPDATED_SALES_ORDER_DETAILS_CONVERTED_TO_PURCHASE)
            .salesOrderDetailsManualConvertToPurchaseMctp(UPDATED_SALES_ORDER_DETAILS_MANUAL_CONVERT_TO_PURCHASE_MCTP)
            .salesOrderDetailsMctpChargeAmt(UPDATED_SALES_ORDER_DETAILS_MCTP_CHARGE_AMT)
            .salesOrderDetailsMctpAllowedAmt(UPDATED_SALES_ORDER_DETAILS_MCTP_ALLOWED_AMT)
            .salesOrderDetailsMctpModifier1(UPDATED_SALES_ORDER_DETAILS_MCTP_MODIFIER_1)
            .salesOrderDetailsMctpModifier2(UPDATED_SALES_ORDER_DETAILS_MCTP_MODIFIER_2)
            .salesOrderDetailsMctpModifier3(UPDATED_SALES_ORDER_DETAILS_MCTP_MODIFIER_3)
            .salesOrderDetailsMctpModifier4(UPDATED_SALES_ORDER_DETAILS_MCTP_MODIFIER_4)
            .salesOrderDetailsMctpPeriod(UPDATED_SALES_ORDER_DETAILS_MCTP_PERIOD)
            .salesOrderDetailsAddtlModifier1(UPDATED_SALES_ORDER_DETAILS_ADDTL_MODIFIER_1)
            .salesOrderDetailsAddtlModifier2(UPDATED_SALES_ORDER_DETAILS_ADDTL_MODIFIER_2)
            .salesOrderDetailsAddtlModifier3(UPDATED_SALES_ORDER_DETAILS_ADDTL_MODIFIER_3)
            .salesOrderDetailsAddtlModifier4(UPDATED_SALES_ORDER_DETAILS_ADDTL_MODIFIER_4)
            .salesOrderDetailsNextDateOfService(UPDATED_SALES_ORDER_DETAILS_NEXT_DATE_OF_SERVICE)
            .salesOrderDetailsPriceTable(UPDATED_SALES_ORDER_DETAILS_PRICE_TABLE)
            .salesOrderDetailsPriceOptionName(UPDATED_SALES_ORDER_DETAILS_PRICE_OPTION_NAME)
            .salesOrderDetailsExtendedChargeAmount(UPDATED_SALES_ORDER_DETAILS_EXTENDED_CHARGE_AMOUNT)
            .salesOrderDetailsExtendedAllowanceAmount(UPDATED_SALES_ORDER_DETAILS_EXTENDED_ALLOWANCE_AMOUNT)
            .salesOrderDetailsItemNdcCode(UPDATED_SALES_ORDER_DETAILS_ITEM_NDC_CODE)
            .salesOrderDetailsManufacturer(UPDATED_SALES_ORDER_DETAILS_MANUFACTURER)
            .salesOrderDetailsCbPricing(UPDATED_SALES_ORDER_DETAILS_CB_PRICING)
            .salesOrderDetailsCbPriceTableOverride(UPDATED_SALES_ORDER_DETAILS_CB_PRICE_TABLE_OVERRIDE)
            .salesOrderDetailsCbOverride(UPDATED_SALES_ORDER_DETAILS_CB_OVERRIDE)
            .salesOrderDetailsMessages(UPDATED_SALES_ORDER_DETAILS_MESSAGES)
            .salesOrderDetailsLocation(UPDATED_SALES_ORDER_DETAILS_LOCATION)
            .salesOrderDetailsCaloriesPerDay(UPDATED_SALES_ORDER_DETAILS_CALORIES_PER_DAY)
            .salesOrderDetailsSecondaryBillingProcudureCode(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_PROCUDURE_CODE)
            .salesOrderDetailsSecondaryBillingPriceOption(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_PRICE_OPTION)
            .salesOrderDetailsSecondaryBillingPriceOptionName(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_PRICE_OPTION_NAME)
            .salesOrderDetailsSecondaryBillingModifier1(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_1)
            .salesOrderDetailsSecondaryBillingModifier2(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_2)
            .salesOrderDetailsSecondaryBillingModifier3(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_3)
            .salesOrderDetailsSecondaryBillingModifier4(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_4)
            .salesOrderDetailsSecondaryBillingAdditionalModifier1(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_1)
            .salesOrderDetailsSecondaryBillingAdditionalModifier2(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_2)
            .salesOrderDetailsSecondaryBillingAdditionalModifier3(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_3)
            .salesOrderDetailsSecondaryBillingAdditionalModifier4(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_4)
            .salesOrderDetailsSecondaryBillingIgnore(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_IGNORE)
            .salesOrderDetailsSecondarySpecialBilling(UPDATED_SALES_ORDER_DETAILS_SECONDARY_SPECIAL_BILLING)
            .salesOrderDetailsSpanDateSplitBilling(UPDATED_SALES_ORDER_DETAILS_SPAN_DATE_SPLIT_BILLING)
            .salesOrderDetailsCmnparCmnFormId(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_FORM_ID)
            .salesOrderDetailsCmnparCmnKey(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_KEY)
            .salesOrderDetailsCmnparCmnCreateDate(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_CREATE_DATE)
            .salesOrderDetailsCmnparCmnExpirationDate(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_EXPIRATION_DATE)
            .salesOrderDetailsCmnparCmnInitialDate(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_INITIAL_DATE)
            .salesOrderDetailsCmnparCmnRenewalDate(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_RENEWAL_DATE)
            .salesOrderDetailsCmnparCmnRecertificationDate(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_RECERTIFICATION_DATE)
            .salesOrderDetailsCmnparCmnPhysicianDate(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_PHYSICIAN_DATE)
            .salesOrderDetailsCmnparCmnStatus(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_STATUS)
            .salesOrderDetailsCmnparParId(UPDATED_SALES_ORDER_DETAILS_CMNPAR_PAR_ID)
            .salesOrderDetailsCmnparParDescr(UPDATED_SALES_ORDER_DETAILS_CMNPAR_PAR_DESCR)
            .salesOrderDetailsCmnparParInitialDate(UPDATED_SALES_ORDER_DETAILS_CMNPAR_PAR_INITIAL_DATE)
            .salesOrderDetailsCmnparParExpirationDate(UPDATED_SALES_ORDER_DETAILS_CMNPAR_PAR_EXPIRATION_DATE)
            .salesOrderDetailsCmnparCmnLogDate(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_LOG_DATE)
            .salesOrderDetailsCmnparCmnLengthOfNeed(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_LENGTH_OF_NEED)
            .salesOrderDetailsCmnparCmnPrintedDate(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_PRINTED_DATE)
            .salesOrderDetailsCmnparCmnPrintedBy(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_PRINTED_BY)
            .salesOrderDetailsCmnparFaxedDate(UPDATED_SALES_ORDER_DETAILS_CMNPAR_FAXED_DATE)
            .salesOrderDetailsCmnparCmnPlaceholder(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_PLACEHOLDER)
            .salesOrderDetailsCmnparCmnFaxedBy(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_FAXED_BY)
            .salesOrderDetailsCmnparCmnLoggedBy(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_LOGGED_BY)
            .salesOrderDetailsCmnparNumberOfRefills(UPDATED_SALES_ORDER_DETAILS_CMNPAR_NUMBER_OF_REFILLS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .status(UPDATED_STATUS)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .salesOrderDetailsManufacturerItemIdNumber(UPDATED_SALES_ORDER_DETAILS_MANUFACTURER_ITEM_ID_NUMBER)
            .cmnId(UPDATED_CMN_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .salesOrderItemDetailsUuid(UPDATED_SALES_ORDER_ITEM_DETAILS_UUID)
            .salesOrderItemNumber(UPDATED_SALES_ORDER_ITEM_NUMBER)
            .isAssetTagged(UPDATED_IS_ASSET_TAGGED)
            .itemSerialNo(UPDATED_ITEM_SERIAL_NO)
            .salesOrderDetailsIcdPointer(UPDATED_SALES_ORDER_DETAILS_ICD_POINTER)
            .procedureIdentifier(UPDATED_PROCEDURE_IDENTIFIER)
            .parNo(UPDATED_PAR_NO)
            .whetherSerialised(UPDATED_WHETHER_SERIALISED)
            .pickupExchangeNo(UPDATED_PICKUP_EXCHANGE_NO)
            .salesOrderAbnUserResponse(UPDATED_SALES_ORDER_ABN_USER_RESPONSE)
            .isDropshipAllowed(UPDATED_IS_DROPSHIP_ALLOWED)
            .poNumber(UPDATED_PO_NUMBER)
            .purchaseOrderUuid(UPDATED_PURCHASE_ORDER_UUID)
            .isResupplyType(UPDATED_IS_RESUPPLY_TYPE)
            .frequencyCount(UPDATED_FREQUENCY_COUNT)
            .frequencyInterval(UPDATED_FREQUENCY_INTERVAL)
            .itemGroupId(UPDATED_ITEM_GROUP_ID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSalesOrderItemDetails.getSalesOrderItemDetailsId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSalesOrderItemDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the SalesOrderItemDetails in the database
        List<SalesOrderItemDetails> salesOrderItemDetailsList = salesOrderItemDetailsRepository.findAll().collectList().block();
        assertThat(salesOrderItemDetailsList).hasSize(databaseSizeBeforeUpdate);
        SalesOrderItemDetails testSalesOrderItemDetails = salesOrderItemDetailsList.get(salesOrderItemDetailsList.size() - 1);
        assertThat(testSalesOrderItemDetails.getSalesOrderId()).isEqualTo(UPDATED_SALES_ORDER_ID);
        assertThat(testSalesOrderItemDetails.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testSalesOrderItemDetails.getItemLocationId()).isEqualTo(UPDATED_ITEM_LOCATION_ID);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsItemId()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ITEM_ID);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsItemName()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ITEM_NAME);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsStockingUom()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_STOCKING_UOM);
        assertThat(testSalesOrderItemDetails.getItemAssetNo()).isEqualTo(UPDATED_ITEM_ASSET_NO);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsItemDescription()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ITEM_DESCRIPTION);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsDefaultVendor()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_DEFAULT_VENDOR);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsOriginalDos()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ORIGINAL_DOS);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsPreviousBillingDate())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_PREVIOUS_BILLING_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsNextBillingDate())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_NEXT_BILLING_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsDosTo()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_DOS_TO);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsNextPeriod()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_NEXT_PERIOD);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSpecialPricing()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_SPECIAL_PRICING);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsPriceOverride()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_PRICE_OVERRIDE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSpecialTaxRate()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_SPECIAL_TAX_RATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsQty()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_QTY);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsBqty()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_BQTY);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsLineQty()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_LINE_QTY);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsProcCode()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_PROC_CODE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsPriceOption()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_PRICE_OPTION);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsModifier1()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_MODIFIER_1);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsModifier2()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_MODIFIER_2);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsModifier3()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_MODIFIER_3);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsModifier4()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_MODIFIER_4);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsChargeAmt()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_CHARGE_AMT);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAllowedAmt()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ALLOWED_AMT);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsTaxable()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_TAXABLE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAbn()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAbnUpgrade()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_UPGRADE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAbnPrintDate()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_PRINT_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAbnItem()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_ITEM);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAbnProcCode()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_PROC_CODE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAbnAllow()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_ALLOW);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAbnCharge()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_CHARGE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAbnModifier1()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_MODIFIER_1);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAbnModifier2()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_MODIFIER_2);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsTaxRate()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_TAX_RATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsTaxZone()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_TAX_ZONE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsNonTaxReason()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_NON_TAX_REASON);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsNote()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_NOTE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSaleType()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_SALE_TYPE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsItemGroup()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ITEM_GROUP);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsItemUser1()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ITEM_USER_1);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsItemUser2()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ITEM_USER_2);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsItemUser3()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ITEM_USER_3);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsItemUser4()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ITEM_USER_4);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsConvertedToPurchase())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CONVERTED_TO_PURCHASE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsManualConvertToPurchaseMctp())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_MANUAL_CONVERT_TO_PURCHASE_MCTP);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsMctpChargeAmt()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_MCTP_CHARGE_AMT);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsMctpAllowedAmt()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_MCTP_ALLOWED_AMT);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsMctpModifier1()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_MCTP_MODIFIER_1);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsMctpModifier2()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_MCTP_MODIFIER_2);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsMctpModifier3()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_MCTP_MODIFIER_3);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsMctpModifier4()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_MCTP_MODIFIER_4);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsMctpPeriod()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_MCTP_PERIOD);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAddtlModifier1()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ADDTL_MODIFIER_1);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAddtlModifier2()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ADDTL_MODIFIER_2);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAddtlModifier3()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ADDTL_MODIFIER_3);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsAddtlModifier4()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ADDTL_MODIFIER_4);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsNextDateOfService())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_NEXT_DATE_OF_SERVICE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsPriceTable()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_PRICE_TABLE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsPriceOptionName())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_PRICE_OPTION_NAME);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsExtendedChargeAmount())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_EXTENDED_CHARGE_AMOUNT);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsExtendedAllowanceAmount())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_EXTENDED_ALLOWANCE_AMOUNT);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsItemNdcCode()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ITEM_NDC_CODE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsManufacturer()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_MANUFACTURER);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCbPricing()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_CB_PRICING);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCbPriceTableOverride())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CB_PRICE_TABLE_OVERRIDE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCbOverride()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_CB_OVERRIDE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsMessages()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_MESSAGES);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsLocation()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_LOCATION);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCaloriesPerDay()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_CALORIES_PER_DAY);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingProcudureCode())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_PROCUDURE_CODE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingPriceOption())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_PRICE_OPTION);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingPriceOptionName())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_PRICE_OPTION_NAME);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingModifier1())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_1);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingModifier2())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_2);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingModifier3())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_3);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingModifier4())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_MODIFIER_4);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingAdditionalModifier1())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_1);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingAdditionalModifier2())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_2);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingAdditionalModifier3())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_3);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingAdditionalModifier4())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_ADDITIONAL_MODIFIER_4);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondaryBillingIgnore())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SECONDARY_BILLING_IGNORE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSecondarySpecialBilling())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SECONDARY_SPECIAL_BILLING);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsSpanDateSplitBilling())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_SPAN_DATE_SPLIT_BILLING);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnFormId())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_FORM_ID);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnKey()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_KEY);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnCreateDate())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_CREATE_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnExpirationDate())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_EXPIRATION_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnInitialDate())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_INITIAL_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnRenewalDate())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_RENEWAL_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnRecertificationDate())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_RECERTIFICATION_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnPhysicianDate())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_PHYSICIAN_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnStatus())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_STATUS);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparParId()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_PAR_ID);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparParDescr()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_PAR_DESCR);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparParInitialDate())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_PAR_INITIAL_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparParExpirationDate())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_PAR_EXPIRATION_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnLogDate())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_LOG_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnLengthOfNeed())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_LENGTH_OF_NEED);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnPrintedDate())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_PRINTED_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnPrintedBy())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_PRINTED_BY);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparFaxedDate())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_FAXED_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnPlaceholder())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_PLACEHOLDER);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnFaxedBy())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_FAXED_BY);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparCmnLoggedBy())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_CMN_LOGGED_BY);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsCmnparNumberOfRefills())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_CMNPAR_NUMBER_OF_REFILLS);
        assertThat(testSalesOrderItemDetails.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testSalesOrderItemDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSalesOrderItemDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSalesOrderItemDetails.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testSalesOrderItemDetails.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsManufacturerItemIdNumber())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_MANUFACTURER_ITEM_ID_NUMBER);
        assertThat(testSalesOrderItemDetails.getCmnId()).isEqualTo(UPDATED_CMN_ID);
        assertThat(testSalesOrderItemDetails.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testSalesOrderItemDetails.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testSalesOrderItemDetails.getSalesOrderItemDetailsUuid()).isEqualTo(UPDATED_SALES_ORDER_ITEM_DETAILS_UUID);
        assertThat(testSalesOrderItemDetails.getSalesOrderItemNumber()).isEqualTo(UPDATED_SALES_ORDER_ITEM_NUMBER);
        assertThat(testSalesOrderItemDetails.getIsAssetTagged()).isEqualTo(UPDATED_IS_ASSET_TAGGED);
        assertThat(testSalesOrderItemDetails.getItemSerialNo()).isEqualTo(UPDATED_ITEM_SERIAL_NO);
        assertThat(testSalesOrderItemDetails.getSalesOrderDetailsIcdPointer()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ICD_POINTER);
        assertThat(testSalesOrderItemDetails.getProcedureIdentifier()).isEqualTo(UPDATED_PROCEDURE_IDENTIFIER);
        assertThat(testSalesOrderItemDetails.getParNo()).isEqualTo(UPDATED_PAR_NO);
        assertThat(testSalesOrderItemDetails.getWhetherSerialised()).isEqualTo(UPDATED_WHETHER_SERIALISED);
        assertThat(testSalesOrderItemDetails.getPickupExchangeNo()).isEqualTo(UPDATED_PICKUP_EXCHANGE_NO);
        assertThat(testSalesOrderItemDetails.getSalesOrderAbnUserResponse()).isEqualTo(UPDATED_SALES_ORDER_ABN_USER_RESPONSE);
        assertThat(testSalesOrderItemDetails.getIsDropshipAllowed()).isEqualTo(UPDATED_IS_DROPSHIP_ALLOWED);
        assertThat(testSalesOrderItemDetails.getPoNumber()).isEqualTo(UPDATED_PO_NUMBER);
        assertThat(testSalesOrderItemDetails.getPurchaseOrderUuid()).isEqualTo(UPDATED_PURCHASE_ORDER_UUID);
        assertThat(testSalesOrderItemDetails.getIsResupplyType()).isEqualTo(UPDATED_IS_RESUPPLY_TYPE);
        assertThat(testSalesOrderItemDetails.getFrequencyCount()).isEqualTo(UPDATED_FREQUENCY_COUNT);
        assertThat(testSalesOrderItemDetails.getFrequencyInterval()).isEqualTo(UPDATED_FREQUENCY_INTERVAL);
        assertThat(testSalesOrderItemDetails.getItemGroupId()).isEqualTo(UPDATED_ITEM_GROUP_ID);
    }

    @Test
    void patchNonExistingSalesOrderItemDetails() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderItemDetailsRepository.findAll().collectList().block().size();
        salesOrderItemDetails.setSalesOrderItemDetailsId(count.incrementAndGet());

        // Create the SalesOrderItemDetails
        SalesOrderItemDetailsDTO salesOrderItemDetailsDTO = salesOrderItemDetailsMapper.toDto(salesOrderItemDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, salesOrderItemDetailsDTO.getSalesOrderItemDetailsId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderItemDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderItemDetails in the database
        List<SalesOrderItemDetails> salesOrderItemDetailsList = salesOrderItemDetailsRepository.findAll().collectList().block();
        assertThat(salesOrderItemDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchSalesOrderItemDetails() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderItemDetailsRepository.findAll().collectList().block().size();
        salesOrderItemDetails.setSalesOrderItemDetailsId(count.incrementAndGet());

        // Create the SalesOrderItemDetails
        SalesOrderItemDetailsDTO salesOrderItemDetailsDTO = salesOrderItemDetailsMapper.toDto(salesOrderItemDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderItemDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the SalesOrderItemDetails in the database
        List<SalesOrderItemDetails> salesOrderItemDetailsList = salesOrderItemDetailsRepository.findAll().collectList().block();
        assertThat(salesOrderItemDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamSalesOrderItemDetails() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderItemDetailsRepository.findAll().collectList().block().size();
        salesOrderItemDetails.setSalesOrderItemDetailsId(count.incrementAndGet());

        // Create the SalesOrderItemDetails
        SalesOrderItemDetailsDTO salesOrderItemDetailsDTO = salesOrderItemDetailsMapper.toDto(salesOrderItemDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(salesOrderItemDetailsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the SalesOrderItemDetails in the database
        List<SalesOrderItemDetails> salesOrderItemDetailsList = salesOrderItemDetailsRepository.findAll().collectList().block();
        assertThat(salesOrderItemDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteSalesOrderItemDetails() {
        // Initialize the database
        salesOrderItemDetailsRepository.save(salesOrderItemDetails).block();

        int databaseSizeBeforeDelete = salesOrderItemDetailsRepository.findAll().collectList().block().size();

        // Delete the salesOrderItemDetails
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, salesOrderItemDetails.getSalesOrderItemDetailsId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<SalesOrderItemDetails> salesOrderItemDetailsList = salesOrderItemDetailsRepository.findAll().collectList().block();
        assertThat(salesOrderItemDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
