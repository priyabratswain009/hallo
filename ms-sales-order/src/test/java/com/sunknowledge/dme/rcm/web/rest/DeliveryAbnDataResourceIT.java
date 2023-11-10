package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.DeliveryAbnData;
import com.sunknowledge.dme.rcm.repository.DeliveryAbnDataRepository;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.service.dto.DeliveryAbnDataDTO;
import com.sunknowledge.dme.rcm.service.mapper.DeliveryAbnDataMapper;
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
 * Integration tests for the {@link DeliveryAbnDataResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class DeliveryAbnDataResourceIT {

    private static final Long DEFAULT_SALES_ORDER_ID = 1L;
    private static final Long UPDATED_SALES_ORDER_ID = 2L;

    private static final Long DEFAULT_PATIENT_ID = 1L;
    private static final Long UPDATED_PATIENT_ID = 2L;

    private static final String DEFAULT_PRIMARY_INSURANCE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_INSURANCE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_INSURANCE_POLICY_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_INSURANCE_POLICY_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_LAST_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SALES_ORDER_DETAILS_ABN_PRINT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SALES_ORDER_DETAILS_ABN_PRINT_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SALES_ORDER_DETAILS_ABN_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_ABN_ITEM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_ABN_ITEM_PROC_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_ABN_ITEM_PROC_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_ABN_ITEM_CHARGE_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_ABN_ITEM_CHARGE_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_PATIENT_ABN_RESPONSE = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_PATIENT_ABN_RESPONSE = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_ABN_DELIVERY_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_ABN_DELIVERY_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_ABN_PATIENT_SIGNATURE_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_ABN_PATIENT_SIGNATURE_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_ABN_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_ABN_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_ABN_REASON = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_ABN_REASON = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_ABN_MODIFIER_1 = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_ABN_MODIFIER_1 = "BBBBBBBBBB";

    private static final String DEFAULT_SALES_ORDER_DETAILS_ABN_MODIFIER_2 = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_DETAILS_ABN_MODIFIER_2 = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_ID = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_ID = "BBBBBBBBBB";

    private static final String DEFAULT_QR_CODE = "AAAAAAAAAA";
    private static final String UPDATED_QR_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PATIENT_ID_NO = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_ID_NO = "BBBBBBBBBB";

    private static final String DEFAULT_ABN_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ABN_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final UUID DEFAULT_DELIVERY_ABN_DATA_UUID = UUID.randomUUID();
    private static final UUID UPDATED_DELIVERY_ABN_DATA_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/delivery-abn-data";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{deliveryAbnDataId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DeliveryAbnDataRepository deliveryAbnDataRepository;

    @Autowired
    private DeliveryAbnDataMapper deliveryAbnDataMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private DeliveryAbnData deliveryAbnData;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DeliveryAbnData createEntity(EntityManager em) {
        DeliveryAbnData deliveryAbnData = new DeliveryAbnData()
            .salesOrderId(DEFAULT_SALES_ORDER_ID)
            .patientId(DEFAULT_PATIENT_ID)
            .primaryInsuranceName(DEFAULT_PRIMARY_INSURANCE_NAME)
            .primaryInsurancePolicyNumber(DEFAULT_PRIMARY_INSURANCE_POLICY_NUMBER)
            .patientFirstName(DEFAULT_PATIENT_FIRST_NAME)
            .patientMiddleName(DEFAULT_PATIENT_MIDDLE_NAME)
            .patientLastName(DEFAULT_PATIENT_LAST_NAME)
            .salesOrderDetailsAbnPrintDate(DEFAULT_SALES_ORDER_DETAILS_ABN_PRINT_DATE)
            .salesOrderDetailsAbnItemName(DEFAULT_SALES_ORDER_DETAILS_ABN_ITEM_NAME)
            .salesOrderDetailsAbnItemProcCode(DEFAULT_SALES_ORDER_DETAILS_ABN_ITEM_PROC_CODE)
            .salesOrderDetailsAbnItemChargeAmount(DEFAULT_SALES_ORDER_DETAILS_ABN_ITEM_CHARGE_AMOUNT)
            .salesOrderDetailsPatientAbnResponse(DEFAULT_SALES_ORDER_DETAILS_PATIENT_ABN_RESPONSE)
            .salesOrderDetailsAbnDeliveryStatus(DEFAULT_SALES_ORDER_DETAILS_ABN_DELIVERY_STATUS)
            .salesOrderDetailsAbnPatientSignatureStatus(DEFAULT_SALES_ORDER_DETAILS_ABN_PATIENT_SIGNATURE_STATUS)
            .salesOrderDetailsAbnStatus(DEFAULT_SALES_ORDER_DETAILS_ABN_STATUS)
            .salesOrderDetailsAbnReason(DEFAULT_SALES_ORDER_DETAILS_ABN_REASON)
            .salesOrderDetailsAbnModifier1(DEFAULT_SALES_ORDER_DETAILS_ABN_MODIFIER_1)
            .salesOrderDetailsAbnModifier2(DEFAULT_SALES_ORDER_DETAILS_ABN_MODIFIER_2)
            .branchName(DEFAULT_BRANCH_NAME)
            .branchId(DEFAULT_BRANCH_ID)
            .qrCode(DEFAULT_QR_CODE)
            .patientIdNo(DEFAULT_PATIENT_ID_NO)
            .abnNumber(DEFAULT_ABN_NUMBER)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .deliveryAbnDataUuid(DEFAULT_DELIVERY_ABN_DATA_UUID);
        return deliveryAbnData;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DeliveryAbnData createUpdatedEntity(EntityManager em) {
        DeliveryAbnData deliveryAbnData = new DeliveryAbnData()
            .salesOrderId(UPDATED_SALES_ORDER_ID)
            .patientId(UPDATED_PATIENT_ID)
            .primaryInsuranceName(UPDATED_PRIMARY_INSURANCE_NAME)
            .primaryInsurancePolicyNumber(UPDATED_PRIMARY_INSURANCE_POLICY_NUMBER)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientMiddleName(UPDATED_PATIENT_MIDDLE_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .salesOrderDetailsAbnPrintDate(UPDATED_SALES_ORDER_DETAILS_ABN_PRINT_DATE)
            .salesOrderDetailsAbnItemName(UPDATED_SALES_ORDER_DETAILS_ABN_ITEM_NAME)
            .salesOrderDetailsAbnItemProcCode(UPDATED_SALES_ORDER_DETAILS_ABN_ITEM_PROC_CODE)
            .salesOrderDetailsAbnItemChargeAmount(UPDATED_SALES_ORDER_DETAILS_ABN_ITEM_CHARGE_AMOUNT)
            .salesOrderDetailsPatientAbnResponse(UPDATED_SALES_ORDER_DETAILS_PATIENT_ABN_RESPONSE)
            .salesOrderDetailsAbnDeliveryStatus(UPDATED_SALES_ORDER_DETAILS_ABN_DELIVERY_STATUS)
            .salesOrderDetailsAbnPatientSignatureStatus(UPDATED_SALES_ORDER_DETAILS_ABN_PATIENT_SIGNATURE_STATUS)
            .salesOrderDetailsAbnStatus(UPDATED_SALES_ORDER_DETAILS_ABN_STATUS)
            .salesOrderDetailsAbnReason(UPDATED_SALES_ORDER_DETAILS_ABN_REASON)
            .salesOrderDetailsAbnModifier1(UPDATED_SALES_ORDER_DETAILS_ABN_MODIFIER_1)
            .salesOrderDetailsAbnModifier2(UPDATED_SALES_ORDER_DETAILS_ABN_MODIFIER_2)
            .branchName(UPDATED_BRANCH_NAME)
            .branchId(UPDATED_BRANCH_ID)
            .qrCode(UPDATED_QR_CODE)
            .patientIdNo(UPDATED_PATIENT_ID_NO)
            .abnNumber(UPDATED_ABN_NUMBER)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .deliveryAbnDataUuid(UPDATED_DELIVERY_ABN_DATA_UUID);
        return deliveryAbnData;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(DeliveryAbnData.class).block();
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
        deliveryAbnData = createEntity(em);
    }

    @Test
    void createDeliveryAbnData() throws Exception {
        int databaseSizeBeforeCreate = deliveryAbnDataRepository.findAll().collectList().block().size();
        // Create the DeliveryAbnData
        DeliveryAbnDataDTO deliveryAbnDataDTO = deliveryAbnDataMapper.toDto(deliveryAbnData);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(deliveryAbnDataDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the DeliveryAbnData in the database
        List<DeliveryAbnData> deliveryAbnDataList = deliveryAbnDataRepository.findAll().collectList().block();
        assertThat(deliveryAbnDataList).hasSize(databaseSizeBeforeCreate + 1);
        DeliveryAbnData testDeliveryAbnData = deliveryAbnDataList.get(deliveryAbnDataList.size() - 1);
        assertThat(testDeliveryAbnData.getSalesOrderId()).isEqualTo(DEFAULT_SALES_ORDER_ID);
        assertThat(testDeliveryAbnData.getPatientId()).isEqualTo(DEFAULT_PATIENT_ID);
        assertThat(testDeliveryAbnData.getPrimaryInsuranceName()).isEqualTo(DEFAULT_PRIMARY_INSURANCE_NAME);
        assertThat(testDeliveryAbnData.getPrimaryInsurancePolicyNumber()).isEqualTo(DEFAULT_PRIMARY_INSURANCE_POLICY_NUMBER);
        assertThat(testDeliveryAbnData.getPatientFirstName()).isEqualTo(DEFAULT_PATIENT_FIRST_NAME);
        assertThat(testDeliveryAbnData.getPatientMiddleName()).isEqualTo(DEFAULT_PATIENT_MIDDLE_NAME);
        assertThat(testDeliveryAbnData.getPatientLastName()).isEqualTo(DEFAULT_PATIENT_LAST_NAME);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnPrintDate()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ABN_PRINT_DATE);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnItemName()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ABN_ITEM_NAME);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnItemProcCode()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ABN_ITEM_PROC_CODE);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnItemChargeAmount())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ABN_ITEM_CHARGE_AMOUNT);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsPatientAbnResponse())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_PATIENT_ABN_RESPONSE);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnDeliveryStatus()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ABN_DELIVERY_STATUS);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnPatientSignatureStatus())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ABN_PATIENT_SIGNATURE_STATUS);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnStatus()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ABN_STATUS);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnReason()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ABN_REASON);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnModifier1()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ABN_MODIFIER_1);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnModifier2()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ABN_MODIFIER_2);
        assertThat(testDeliveryAbnData.getBranchName()).isEqualTo(DEFAULT_BRANCH_NAME);
        assertThat(testDeliveryAbnData.getBranchId()).isEqualTo(DEFAULT_BRANCH_ID);
        assertThat(testDeliveryAbnData.getQrCode()).isEqualTo(DEFAULT_QR_CODE);
        assertThat(testDeliveryAbnData.getPatientIdNo()).isEqualTo(DEFAULT_PATIENT_ID_NO);
        assertThat(testDeliveryAbnData.getAbnNumber()).isEqualTo(DEFAULT_ABN_NUMBER);
        assertThat(testDeliveryAbnData.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDeliveryAbnData.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testDeliveryAbnData.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testDeliveryAbnData.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testDeliveryAbnData.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testDeliveryAbnData.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testDeliveryAbnData.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testDeliveryAbnData.getDeliveryAbnDataUuid()).isEqualTo(DEFAULT_DELIVERY_ABN_DATA_UUID);
    }

    @Test
    void createDeliveryAbnDataWithExistingId() throws Exception {
        // Create the DeliveryAbnData with an existing ID
        deliveryAbnData.setDeliveryAbnDataId(1L);
        DeliveryAbnDataDTO deliveryAbnDataDTO = deliveryAbnDataMapper.toDto(deliveryAbnData);

        int databaseSizeBeforeCreate = deliveryAbnDataRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(deliveryAbnDataDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DeliveryAbnData in the database
        List<DeliveryAbnData> deliveryAbnDataList = deliveryAbnDataRepository.findAll().collectList().block();
        assertThat(deliveryAbnDataList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllDeliveryAbnData() {
        // Initialize the database
        deliveryAbnDataRepository.save(deliveryAbnData).block();

        // Get all the deliveryAbnDataList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=deliveryAbnDataId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].deliveryAbnDataId")
            .value(hasItem(deliveryAbnData.getDeliveryAbnDataId().intValue()))
            .jsonPath("$.[*].salesOrderId")
            .value(hasItem(DEFAULT_SALES_ORDER_ID.intValue()))
            .jsonPath("$.[*].patientId")
            .value(hasItem(DEFAULT_PATIENT_ID.intValue()))
            .jsonPath("$.[*].primaryInsuranceName")
            .value(hasItem(DEFAULT_PRIMARY_INSURANCE_NAME))
            .jsonPath("$.[*].primaryInsurancePolicyNumber")
            .value(hasItem(DEFAULT_PRIMARY_INSURANCE_POLICY_NUMBER))
            .jsonPath("$.[*].patientFirstName")
            .value(hasItem(DEFAULT_PATIENT_FIRST_NAME))
            .jsonPath("$.[*].patientMiddleName")
            .value(hasItem(DEFAULT_PATIENT_MIDDLE_NAME))
            .jsonPath("$.[*].patientLastName")
            .value(hasItem(DEFAULT_PATIENT_LAST_NAME))
            .jsonPath("$.[*].salesOrderDetailsAbnPrintDate")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_ABN_PRINT_DATE.toString()))
            .jsonPath("$.[*].salesOrderDetailsAbnItemName")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_ABN_ITEM_NAME))
            .jsonPath("$.[*].salesOrderDetailsAbnItemProcCode")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_ABN_ITEM_PROC_CODE))
            .jsonPath("$.[*].salesOrderDetailsAbnItemChargeAmount")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_ABN_ITEM_CHARGE_AMOUNT))
            .jsonPath("$.[*].salesOrderDetailsPatientAbnResponse")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_PATIENT_ABN_RESPONSE))
            .jsonPath("$.[*].salesOrderDetailsAbnDeliveryStatus")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_ABN_DELIVERY_STATUS))
            .jsonPath("$.[*].salesOrderDetailsAbnPatientSignatureStatus")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_ABN_PATIENT_SIGNATURE_STATUS))
            .jsonPath("$.[*].salesOrderDetailsAbnStatus")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_ABN_STATUS))
            .jsonPath("$.[*].salesOrderDetailsAbnReason")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_ABN_REASON))
            .jsonPath("$.[*].salesOrderDetailsAbnModifier1")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_ABN_MODIFIER_1))
            .jsonPath("$.[*].salesOrderDetailsAbnModifier2")
            .value(hasItem(DEFAULT_SALES_ORDER_DETAILS_ABN_MODIFIER_2))
            .jsonPath("$.[*].branchName")
            .value(hasItem(DEFAULT_BRANCH_NAME))
            .jsonPath("$.[*].branchId")
            .value(hasItem(DEFAULT_BRANCH_ID))
            .jsonPath("$.[*].qrCode")
            .value(hasItem(DEFAULT_QR_CODE))
            .jsonPath("$.[*].patientIdNo")
            .value(hasItem(DEFAULT_PATIENT_ID_NO))
            .jsonPath("$.[*].abnNumber")
            .value(hasItem(DEFAULT_ABN_NUMBER))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].createdById")
            .value(hasItem(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.[*].createdByName")
            .value(hasItem(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.[*].createdDate")
            .value(hasItem(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.[*].updatedById")
            .value(hasItem(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.[*].updatedByName")
            .value(hasItem(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.[*].updatedDate")
            .value(hasItem(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.[*].deliveryAbnDataUuid")
            .value(hasItem(DEFAULT_DELIVERY_ABN_DATA_UUID.toString()));
    }

    @Test
    void getDeliveryAbnData() {
        // Initialize the database
        deliveryAbnDataRepository.save(deliveryAbnData).block();

        // Get the deliveryAbnData
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, deliveryAbnData.getDeliveryAbnDataId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.deliveryAbnDataId")
            .value(is(deliveryAbnData.getDeliveryAbnDataId().intValue()))
            .jsonPath("$.salesOrderId")
            .value(is(DEFAULT_SALES_ORDER_ID.intValue()))
            .jsonPath("$.patientId")
            .value(is(DEFAULT_PATIENT_ID.intValue()))
            .jsonPath("$.primaryInsuranceName")
            .value(is(DEFAULT_PRIMARY_INSURANCE_NAME))
            .jsonPath("$.primaryInsurancePolicyNumber")
            .value(is(DEFAULT_PRIMARY_INSURANCE_POLICY_NUMBER))
            .jsonPath("$.patientFirstName")
            .value(is(DEFAULT_PATIENT_FIRST_NAME))
            .jsonPath("$.patientMiddleName")
            .value(is(DEFAULT_PATIENT_MIDDLE_NAME))
            .jsonPath("$.patientLastName")
            .value(is(DEFAULT_PATIENT_LAST_NAME))
            .jsonPath("$.salesOrderDetailsAbnPrintDate")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_ABN_PRINT_DATE.toString()))
            .jsonPath("$.salesOrderDetailsAbnItemName")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_ABN_ITEM_NAME))
            .jsonPath("$.salesOrderDetailsAbnItemProcCode")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_ABN_ITEM_PROC_CODE))
            .jsonPath("$.salesOrderDetailsAbnItemChargeAmount")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_ABN_ITEM_CHARGE_AMOUNT))
            .jsonPath("$.salesOrderDetailsPatientAbnResponse")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_PATIENT_ABN_RESPONSE))
            .jsonPath("$.salesOrderDetailsAbnDeliveryStatus")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_ABN_DELIVERY_STATUS))
            .jsonPath("$.salesOrderDetailsAbnPatientSignatureStatus")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_ABN_PATIENT_SIGNATURE_STATUS))
            .jsonPath("$.salesOrderDetailsAbnStatus")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_ABN_STATUS))
            .jsonPath("$.salesOrderDetailsAbnReason")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_ABN_REASON))
            .jsonPath("$.salesOrderDetailsAbnModifier1")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_ABN_MODIFIER_1))
            .jsonPath("$.salesOrderDetailsAbnModifier2")
            .value(is(DEFAULT_SALES_ORDER_DETAILS_ABN_MODIFIER_2))
            .jsonPath("$.branchName")
            .value(is(DEFAULT_BRANCH_NAME))
            .jsonPath("$.branchId")
            .value(is(DEFAULT_BRANCH_ID))
            .jsonPath("$.qrCode")
            .value(is(DEFAULT_QR_CODE))
            .jsonPath("$.patientIdNo")
            .value(is(DEFAULT_PATIENT_ID_NO))
            .jsonPath("$.abnNumber")
            .value(is(DEFAULT_ABN_NUMBER))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.createdById")
            .value(is(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.createdByName")
            .value(is(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.createdDate")
            .value(is(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.updatedById")
            .value(is(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.updatedByName")
            .value(is(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.updatedDate")
            .value(is(DEFAULT_UPDATED_DATE.toString()))
            .jsonPath("$.deliveryAbnDataUuid")
            .value(is(DEFAULT_DELIVERY_ABN_DATA_UUID.toString()));
    }

    @Test
    void getNonExistingDeliveryAbnData() {
        // Get the deliveryAbnData
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putNewDeliveryAbnData() throws Exception {
        // Initialize the database
        deliveryAbnDataRepository.save(deliveryAbnData).block();

        int databaseSizeBeforeUpdate = deliveryAbnDataRepository.findAll().collectList().block().size();

        // Update the deliveryAbnData
        DeliveryAbnData updatedDeliveryAbnData = deliveryAbnDataRepository.findById(deliveryAbnData.getDeliveryAbnDataId()).block();
        updatedDeliveryAbnData
            .salesOrderId(UPDATED_SALES_ORDER_ID)
            .patientId(UPDATED_PATIENT_ID)
            .primaryInsuranceName(UPDATED_PRIMARY_INSURANCE_NAME)
            .primaryInsurancePolicyNumber(UPDATED_PRIMARY_INSURANCE_POLICY_NUMBER)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientMiddleName(UPDATED_PATIENT_MIDDLE_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .salesOrderDetailsAbnPrintDate(UPDATED_SALES_ORDER_DETAILS_ABN_PRINT_DATE)
            .salesOrderDetailsAbnItemName(UPDATED_SALES_ORDER_DETAILS_ABN_ITEM_NAME)
            .salesOrderDetailsAbnItemProcCode(UPDATED_SALES_ORDER_DETAILS_ABN_ITEM_PROC_CODE)
            .salesOrderDetailsAbnItemChargeAmount(UPDATED_SALES_ORDER_DETAILS_ABN_ITEM_CHARGE_AMOUNT)
            .salesOrderDetailsPatientAbnResponse(UPDATED_SALES_ORDER_DETAILS_PATIENT_ABN_RESPONSE)
            .salesOrderDetailsAbnDeliveryStatus(UPDATED_SALES_ORDER_DETAILS_ABN_DELIVERY_STATUS)
            .salesOrderDetailsAbnPatientSignatureStatus(UPDATED_SALES_ORDER_DETAILS_ABN_PATIENT_SIGNATURE_STATUS)
            .salesOrderDetailsAbnStatus(UPDATED_SALES_ORDER_DETAILS_ABN_STATUS)
            .salesOrderDetailsAbnReason(UPDATED_SALES_ORDER_DETAILS_ABN_REASON)
            .salesOrderDetailsAbnModifier1(UPDATED_SALES_ORDER_DETAILS_ABN_MODIFIER_1)
            .salesOrderDetailsAbnModifier2(UPDATED_SALES_ORDER_DETAILS_ABN_MODIFIER_2)
            .branchName(UPDATED_BRANCH_NAME)
            .branchId(UPDATED_BRANCH_ID)
            .qrCode(UPDATED_QR_CODE)
            .patientIdNo(UPDATED_PATIENT_ID_NO)
            .abnNumber(UPDATED_ABN_NUMBER)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .deliveryAbnDataUuid(UPDATED_DELIVERY_ABN_DATA_UUID);
        DeliveryAbnDataDTO deliveryAbnDataDTO = deliveryAbnDataMapper.toDto(updatedDeliveryAbnData);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, deliveryAbnDataDTO.getDeliveryAbnDataId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(deliveryAbnDataDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DeliveryAbnData in the database
        List<DeliveryAbnData> deliveryAbnDataList = deliveryAbnDataRepository.findAll().collectList().block();
        assertThat(deliveryAbnDataList).hasSize(databaseSizeBeforeUpdate);
        DeliveryAbnData testDeliveryAbnData = deliveryAbnDataList.get(deliveryAbnDataList.size() - 1);
        assertThat(testDeliveryAbnData.getSalesOrderId()).isEqualTo(UPDATED_SALES_ORDER_ID);
        assertThat(testDeliveryAbnData.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testDeliveryAbnData.getPrimaryInsuranceName()).isEqualTo(UPDATED_PRIMARY_INSURANCE_NAME);
        assertThat(testDeliveryAbnData.getPrimaryInsurancePolicyNumber()).isEqualTo(UPDATED_PRIMARY_INSURANCE_POLICY_NUMBER);
        assertThat(testDeliveryAbnData.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testDeliveryAbnData.getPatientMiddleName()).isEqualTo(UPDATED_PATIENT_MIDDLE_NAME);
        assertThat(testDeliveryAbnData.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnPrintDate()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_PRINT_DATE);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnItemName()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_ITEM_NAME);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnItemProcCode()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_ITEM_PROC_CODE);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnItemChargeAmount())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_ITEM_CHARGE_AMOUNT);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsPatientAbnResponse())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_PATIENT_ABN_RESPONSE);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnDeliveryStatus()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_DELIVERY_STATUS);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnPatientSignatureStatus())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_PATIENT_SIGNATURE_STATUS);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnStatus()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_STATUS);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnReason()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_REASON);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnModifier1()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_MODIFIER_1);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnModifier2()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_MODIFIER_2);
        assertThat(testDeliveryAbnData.getBranchName()).isEqualTo(UPDATED_BRANCH_NAME);
        assertThat(testDeliveryAbnData.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testDeliveryAbnData.getQrCode()).isEqualTo(UPDATED_QR_CODE);
        assertThat(testDeliveryAbnData.getPatientIdNo()).isEqualTo(UPDATED_PATIENT_ID_NO);
        assertThat(testDeliveryAbnData.getAbnNumber()).isEqualTo(UPDATED_ABN_NUMBER);
        assertThat(testDeliveryAbnData.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDeliveryAbnData.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testDeliveryAbnData.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testDeliveryAbnData.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDeliveryAbnData.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testDeliveryAbnData.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testDeliveryAbnData.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testDeliveryAbnData.getDeliveryAbnDataUuid()).isEqualTo(UPDATED_DELIVERY_ABN_DATA_UUID);
    }

    @Test
    void putNonExistingDeliveryAbnData() throws Exception {
        int databaseSizeBeforeUpdate = deliveryAbnDataRepository.findAll().collectList().block().size();
        deliveryAbnData.setDeliveryAbnDataId(count.incrementAndGet());

        // Create the DeliveryAbnData
        DeliveryAbnDataDTO deliveryAbnDataDTO = deliveryAbnDataMapper.toDto(deliveryAbnData);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, deliveryAbnDataDTO.getDeliveryAbnDataId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(deliveryAbnDataDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DeliveryAbnData in the database
        List<DeliveryAbnData> deliveryAbnDataList = deliveryAbnDataRepository.findAll().collectList().block();
        assertThat(deliveryAbnDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchDeliveryAbnData() throws Exception {
        int databaseSizeBeforeUpdate = deliveryAbnDataRepository.findAll().collectList().block().size();
        deliveryAbnData.setDeliveryAbnDataId(count.incrementAndGet());

        // Create the DeliveryAbnData
        DeliveryAbnDataDTO deliveryAbnDataDTO = deliveryAbnDataMapper.toDto(deliveryAbnData);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(deliveryAbnDataDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DeliveryAbnData in the database
        List<DeliveryAbnData> deliveryAbnDataList = deliveryAbnDataRepository.findAll().collectList().block();
        assertThat(deliveryAbnDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamDeliveryAbnData() throws Exception {
        int databaseSizeBeforeUpdate = deliveryAbnDataRepository.findAll().collectList().block().size();
        deliveryAbnData.setDeliveryAbnDataId(count.incrementAndGet());

        // Create the DeliveryAbnData
        DeliveryAbnDataDTO deliveryAbnDataDTO = deliveryAbnDataMapper.toDto(deliveryAbnData);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(deliveryAbnDataDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the DeliveryAbnData in the database
        List<DeliveryAbnData> deliveryAbnDataList = deliveryAbnDataRepository.findAll().collectList().block();
        assertThat(deliveryAbnDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateDeliveryAbnDataWithPatch() throws Exception {
        // Initialize the database
        deliveryAbnDataRepository.save(deliveryAbnData).block();

        int databaseSizeBeforeUpdate = deliveryAbnDataRepository.findAll().collectList().block().size();

        // Update the deliveryAbnData using partial update
        DeliveryAbnData partialUpdatedDeliveryAbnData = new DeliveryAbnData();
        partialUpdatedDeliveryAbnData.setDeliveryAbnDataId(deliveryAbnData.getDeliveryAbnDataId());

        partialUpdatedDeliveryAbnData
            .patientMiddleName(UPDATED_PATIENT_MIDDLE_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .salesOrderDetailsAbnItemName(UPDATED_SALES_ORDER_DETAILS_ABN_ITEM_NAME)
            .salesOrderDetailsAbnItemChargeAmount(UPDATED_SALES_ORDER_DETAILS_ABN_ITEM_CHARGE_AMOUNT)
            .salesOrderDetailsAbnStatus(UPDATED_SALES_ORDER_DETAILS_ABN_STATUS)
            .salesOrderDetailsAbnModifier1(UPDATED_SALES_ORDER_DETAILS_ABN_MODIFIER_1)
            .qrCode(UPDATED_QR_CODE)
            .abnNumber(UPDATED_ABN_NUMBER)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .deliveryAbnDataUuid(UPDATED_DELIVERY_ABN_DATA_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedDeliveryAbnData.getDeliveryAbnDataId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedDeliveryAbnData))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DeliveryAbnData in the database
        List<DeliveryAbnData> deliveryAbnDataList = deliveryAbnDataRepository.findAll().collectList().block();
        assertThat(deliveryAbnDataList).hasSize(databaseSizeBeforeUpdate);
        DeliveryAbnData testDeliveryAbnData = deliveryAbnDataList.get(deliveryAbnDataList.size() - 1);
        assertThat(testDeliveryAbnData.getSalesOrderId()).isEqualTo(DEFAULT_SALES_ORDER_ID);
        assertThat(testDeliveryAbnData.getPatientId()).isEqualTo(DEFAULT_PATIENT_ID);
        assertThat(testDeliveryAbnData.getPrimaryInsuranceName()).isEqualTo(DEFAULT_PRIMARY_INSURANCE_NAME);
        assertThat(testDeliveryAbnData.getPrimaryInsurancePolicyNumber()).isEqualTo(DEFAULT_PRIMARY_INSURANCE_POLICY_NUMBER);
        assertThat(testDeliveryAbnData.getPatientFirstName()).isEqualTo(DEFAULT_PATIENT_FIRST_NAME);
        assertThat(testDeliveryAbnData.getPatientMiddleName()).isEqualTo(UPDATED_PATIENT_MIDDLE_NAME);
        assertThat(testDeliveryAbnData.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnPrintDate()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ABN_PRINT_DATE);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnItemName()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_ITEM_NAME);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnItemProcCode()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ABN_ITEM_PROC_CODE);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnItemChargeAmount())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_ITEM_CHARGE_AMOUNT);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsPatientAbnResponse())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_PATIENT_ABN_RESPONSE);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnDeliveryStatus()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ABN_DELIVERY_STATUS);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnPatientSignatureStatus())
            .isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ABN_PATIENT_SIGNATURE_STATUS);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnStatus()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_STATUS);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnReason()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ABN_REASON);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnModifier1()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_MODIFIER_1);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnModifier2()).isEqualTo(DEFAULT_SALES_ORDER_DETAILS_ABN_MODIFIER_2);
        assertThat(testDeliveryAbnData.getBranchName()).isEqualTo(DEFAULT_BRANCH_NAME);
        assertThat(testDeliveryAbnData.getBranchId()).isEqualTo(DEFAULT_BRANCH_ID);
        assertThat(testDeliveryAbnData.getQrCode()).isEqualTo(UPDATED_QR_CODE);
        assertThat(testDeliveryAbnData.getPatientIdNo()).isEqualTo(DEFAULT_PATIENT_ID_NO);
        assertThat(testDeliveryAbnData.getAbnNumber()).isEqualTo(UPDATED_ABN_NUMBER);
        assertThat(testDeliveryAbnData.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDeliveryAbnData.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testDeliveryAbnData.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testDeliveryAbnData.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDeliveryAbnData.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testDeliveryAbnData.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testDeliveryAbnData.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testDeliveryAbnData.getDeliveryAbnDataUuid()).isEqualTo(UPDATED_DELIVERY_ABN_DATA_UUID);
    }

    @Test
    void fullUpdateDeliveryAbnDataWithPatch() throws Exception {
        // Initialize the database
        deliveryAbnDataRepository.save(deliveryAbnData).block();

        int databaseSizeBeforeUpdate = deliveryAbnDataRepository.findAll().collectList().block().size();

        // Update the deliveryAbnData using partial update
        DeliveryAbnData partialUpdatedDeliveryAbnData = new DeliveryAbnData();
        partialUpdatedDeliveryAbnData.setDeliveryAbnDataId(deliveryAbnData.getDeliveryAbnDataId());

        partialUpdatedDeliveryAbnData
            .salesOrderId(UPDATED_SALES_ORDER_ID)
            .patientId(UPDATED_PATIENT_ID)
            .primaryInsuranceName(UPDATED_PRIMARY_INSURANCE_NAME)
            .primaryInsurancePolicyNumber(UPDATED_PRIMARY_INSURANCE_POLICY_NUMBER)
            .patientFirstName(UPDATED_PATIENT_FIRST_NAME)
            .patientMiddleName(UPDATED_PATIENT_MIDDLE_NAME)
            .patientLastName(UPDATED_PATIENT_LAST_NAME)
            .salesOrderDetailsAbnPrintDate(UPDATED_SALES_ORDER_DETAILS_ABN_PRINT_DATE)
            .salesOrderDetailsAbnItemName(UPDATED_SALES_ORDER_DETAILS_ABN_ITEM_NAME)
            .salesOrderDetailsAbnItemProcCode(UPDATED_SALES_ORDER_DETAILS_ABN_ITEM_PROC_CODE)
            .salesOrderDetailsAbnItemChargeAmount(UPDATED_SALES_ORDER_DETAILS_ABN_ITEM_CHARGE_AMOUNT)
            .salesOrderDetailsPatientAbnResponse(UPDATED_SALES_ORDER_DETAILS_PATIENT_ABN_RESPONSE)
            .salesOrderDetailsAbnDeliveryStatus(UPDATED_SALES_ORDER_DETAILS_ABN_DELIVERY_STATUS)
            .salesOrderDetailsAbnPatientSignatureStatus(UPDATED_SALES_ORDER_DETAILS_ABN_PATIENT_SIGNATURE_STATUS)
            .salesOrderDetailsAbnStatus(UPDATED_SALES_ORDER_DETAILS_ABN_STATUS)
            .salesOrderDetailsAbnReason(UPDATED_SALES_ORDER_DETAILS_ABN_REASON)
            .salesOrderDetailsAbnModifier1(UPDATED_SALES_ORDER_DETAILS_ABN_MODIFIER_1)
            .salesOrderDetailsAbnModifier2(UPDATED_SALES_ORDER_DETAILS_ABN_MODIFIER_2)
            .branchName(UPDATED_BRANCH_NAME)
            .branchId(UPDATED_BRANCH_ID)
            .qrCode(UPDATED_QR_CODE)
            .patientIdNo(UPDATED_PATIENT_ID_NO)
            .abnNumber(UPDATED_ABN_NUMBER)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .deliveryAbnDataUuid(UPDATED_DELIVERY_ABN_DATA_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedDeliveryAbnData.getDeliveryAbnDataId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedDeliveryAbnData))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DeliveryAbnData in the database
        List<DeliveryAbnData> deliveryAbnDataList = deliveryAbnDataRepository.findAll().collectList().block();
        assertThat(deliveryAbnDataList).hasSize(databaseSizeBeforeUpdate);
        DeliveryAbnData testDeliveryAbnData = deliveryAbnDataList.get(deliveryAbnDataList.size() - 1);
        assertThat(testDeliveryAbnData.getSalesOrderId()).isEqualTo(UPDATED_SALES_ORDER_ID);
        assertThat(testDeliveryAbnData.getPatientId()).isEqualTo(UPDATED_PATIENT_ID);
        assertThat(testDeliveryAbnData.getPrimaryInsuranceName()).isEqualTo(UPDATED_PRIMARY_INSURANCE_NAME);
        assertThat(testDeliveryAbnData.getPrimaryInsurancePolicyNumber()).isEqualTo(UPDATED_PRIMARY_INSURANCE_POLICY_NUMBER);
        assertThat(testDeliveryAbnData.getPatientFirstName()).isEqualTo(UPDATED_PATIENT_FIRST_NAME);
        assertThat(testDeliveryAbnData.getPatientMiddleName()).isEqualTo(UPDATED_PATIENT_MIDDLE_NAME);
        assertThat(testDeliveryAbnData.getPatientLastName()).isEqualTo(UPDATED_PATIENT_LAST_NAME);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnPrintDate()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_PRINT_DATE);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnItemName()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_ITEM_NAME);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnItemProcCode()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_ITEM_PROC_CODE);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnItemChargeAmount())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_ITEM_CHARGE_AMOUNT);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsPatientAbnResponse())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_PATIENT_ABN_RESPONSE);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnDeliveryStatus()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_DELIVERY_STATUS);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnPatientSignatureStatus())
            .isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_PATIENT_SIGNATURE_STATUS);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnStatus()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_STATUS);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnReason()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_REASON);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnModifier1()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_MODIFIER_1);
        assertThat(testDeliveryAbnData.getSalesOrderDetailsAbnModifier2()).isEqualTo(UPDATED_SALES_ORDER_DETAILS_ABN_MODIFIER_2);
        assertThat(testDeliveryAbnData.getBranchName()).isEqualTo(UPDATED_BRANCH_NAME);
        assertThat(testDeliveryAbnData.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testDeliveryAbnData.getQrCode()).isEqualTo(UPDATED_QR_CODE);
        assertThat(testDeliveryAbnData.getPatientIdNo()).isEqualTo(UPDATED_PATIENT_ID_NO);
        assertThat(testDeliveryAbnData.getAbnNumber()).isEqualTo(UPDATED_ABN_NUMBER);
        assertThat(testDeliveryAbnData.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDeliveryAbnData.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testDeliveryAbnData.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testDeliveryAbnData.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDeliveryAbnData.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testDeliveryAbnData.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testDeliveryAbnData.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testDeliveryAbnData.getDeliveryAbnDataUuid()).isEqualTo(UPDATED_DELIVERY_ABN_DATA_UUID);
    }

    @Test
    void patchNonExistingDeliveryAbnData() throws Exception {
        int databaseSizeBeforeUpdate = deliveryAbnDataRepository.findAll().collectList().block().size();
        deliveryAbnData.setDeliveryAbnDataId(count.incrementAndGet());

        // Create the DeliveryAbnData
        DeliveryAbnDataDTO deliveryAbnDataDTO = deliveryAbnDataMapper.toDto(deliveryAbnData);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, deliveryAbnDataDTO.getDeliveryAbnDataId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(deliveryAbnDataDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DeliveryAbnData in the database
        List<DeliveryAbnData> deliveryAbnDataList = deliveryAbnDataRepository.findAll().collectList().block();
        assertThat(deliveryAbnDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchDeliveryAbnData() throws Exception {
        int databaseSizeBeforeUpdate = deliveryAbnDataRepository.findAll().collectList().block().size();
        deliveryAbnData.setDeliveryAbnDataId(count.incrementAndGet());

        // Create the DeliveryAbnData
        DeliveryAbnDataDTO deliveryAbnDataDTO = deliveryAbnDataMapper.toDto(deliveryAbnData);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(deliveryAbnDataDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DeliveryAbnData in the database
        List<DeliveryAbnData> deliveryAbnDataList = deliveryAbnDataRepository.findAll().collectList().block();
        assertThat(deliveryAbnDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamDeliveryAbnData() throws Exception {
        int databaseSizeBeforeUpdate = deliveryAbnDataRepository.findAll().collectList().block().size();
        deliveryAbnData.setDeliveryAbnDataId(count.incrementAndGet());

        // Create the DeliveryAbnData
        DeliveryAbnDataDTO deliveryAbnDataDTO = deliveryAbnDataMapper.toDto(deliveryAbnData);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(deliveryAbnDataDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the DeliveryAbnData in the database
        List<DeliveryAbnData> deliveryAbnDataList = deliveryAbnDataRepository.findAll().collectList().block();
        assertThat(deliveryAbnDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteDeliveryAbnData() {
        // Initialize the database
        deliveryAbnDataRepository.save(deliveryAbnData).block();

        int databaseSizeBeforeDelete = deliveryAbnDataRepository.findAll().collectList().block().size();

        // Delete the deliveryAbnData
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, deliveryAbnData.getDeliveryAbnDataId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<DeliveryAbnData> deliveryAbnDataList = deliveryAbnDataRepository.findAll().collectList().block();
        assertThat(deliveryAbnDataList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
