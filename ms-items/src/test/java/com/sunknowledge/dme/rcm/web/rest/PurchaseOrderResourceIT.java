package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.PurchaseOrder;
import com.sunknowledge.dme.rcm.repository.PurchaseOrderRepository;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderDTO;
import com.sunknowledge.dme.rcm.service.mapper.PurchaseOrderMapper;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link PurchaseOrderResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PurchaseOrderResourceIT {

    private static final String DEFAULT_PO_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PO_NUMBER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PO_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PO_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_ITEM_LOCATION_ID = 1L;
    private static final Long UPDATED_ITEM_LOCATION_ID = 2L;

    private static final String DEFAULT_ITEM_LOCATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_LOCATION_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_SALES_ORDER_ID = 1L;
    private static final Long UPDATED_SALES_ORDER_ID = 2L;

    private static final String DEFAULT_SALES_ORDER_NO = "AAAAAAAAAA";
    private static final String UPDATED_SALES_ORDER_NO = "BBBBBBBBBB";

    private static final Long DEFAULT_VENDOR_ID = 1L;
    private static final Long UPDATED_VENDOR_ID = 2L;

    private static final String DEFAULT_VENDOR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_VENDOR_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_ADDRESS_CITY = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_ADDRESS_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_ADDRESS_STATE = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_ADDRESS_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_ADDRESS_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_ADDRESS_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_CONTACT_NO = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_CONTACT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_CONTACT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_CONTACT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_CONTACT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_CONTACT_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_ADDRESS_CITY = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_ADDRESS_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_ADDRESS_STATE = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_ADDRESS_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_ADDRESS_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_ADDRESS_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_CONTACT_NO = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_CONTACT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_CONTACT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_CONTACT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_CONTACT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_CONTACT_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PRIORITY = "AAAAAAAAAA";
    private static final String UPDATED_PRIORITY = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_METHOD = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_METHOD = "BBBBBBBBBB";

    private static final Double DEFAULT_FREIGHT_CHARGES = 1D;
    private static final Double UPDATED_FREIGHT_CHARGES = 2D;

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_NOTES = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final UUID DEFAULT_PURCHASE_ORDER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_PURCHASE_ORDER_UUID = UUID.randomUUID();

    private static final String DEFAULT_PO_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_PO_STATUS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_VENDOR_DELIVERY = false;
    private static final Boolean UPDATED_VENDOR_DELIVERY = true;

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_BRANCH_ID = 1L;
    private static final Long UPDATED_BRANCH_ID = 2L;

    private static final String DEFAULT_BRANCH_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VENDOR_FAX_NO = "AAAAAAAAAA";
    private static final String UPDATED_VENDOR_FAX_NO = "BBBBBBBBBB";

    private static final String DEFAULT_VENDOR_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_VENDOR_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_VENDOR_FAX_REQUEST_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_VENDOR_FAX_REQUEST_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_VENDOR_EMAIL_REQUEST_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_VENDOR_EMAIL_REQUEST_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_PO_REQUEST_DOCUMENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PO_REQUEST_DOCUMENT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PO_REQUEST_ACK_RECEIVED_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_PO_REQUEST_ACK_RECEIVED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_PO_850_EDI_STRING = "AAAAAAAAAA";
    private static final String UPDATED_PO_850_EDI_STRING = "BBBBBBBBBB";

    private static final String DEFAULT_PO_855_EDI_STRING = "AAAAAAAAAA";
    private static final String UPDATED_PO_855_EDI_STRING = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PO_REQUEST_SEND_DATETIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PO_REQUEST_SEND_DATETIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_PO_ACK_RECEIVED_DATETIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PO_ACK_RECEIVED_DATETIME = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/purchase-orders";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{poId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPurchaseOrderMockMvc;

    private PurchaseOrder purchaseOrder;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PurchaseOrder createEntity(EntityManager em) {
        PurchaseOrder purchaseOrder = new PurchaseOrder()
            .poNumber(DEFAULT_PO_NUMBER)
            .poDate(DEFAULT_PO_DATE)
            .itemLocationId(DEFAULT_ITEM_LOCATION_ID)
            .itemLocationName(DEFAULT_ITEM_LOCATION_NAME)
            .salesOrderId(DEFAULT_SALES_ORDER_ID)
            .salesOrderNo(DEFAULT_SALES_ORDER_NO)
            .vendorId(DEFAULT_VENDOR_ID)
            .vendorName(DEFAULT_VENDOR_NAME)
            .billingAddressLine1(DEFAULT_BILLING_ADDRESS_LINE_1)
            .billingAddressLine2(DEFAULT_BILLING_ADDRESS_LINE_2)
            .billingAddressCity(DEFAULT_BILLING_ADDRESS_CITY)
            .billingAddressState(DEFAULT_BILLING_ADDRESS_STATE)
            .billingAddressZip(DEFAULT_BILLING_ADDRESS_ZIP)
            .billingContactNo(DEFAULT_BILLING_CONTACT_NO)
            .billingContactName(DEFAULT_BILLING_CONTACT_NAME)
            .billingContactEmail(DEFAULT_BILLING_CONTACT_EMAIL)
            .deliveryAddressLine1(DEFAULT_DELIVERY_ADDRESS_LINE_1)
            .deliveryAddressLine2(DEFAULT_DELIVERY_ADDRESS_LINE_2)
            .deliveryAddressCity(DEFAULT_DELIVERY_ADDRESS_CITY)
            .deliveryAddressState(DEFAULT_DELIVERY_ADDRESS_STATE)
            .deliveryAddressZip(DEFAULT_DELIVERY_ADDRESS_ZIP)
            .deliveryContactNo(DEFAULT_DELIVERY_CONTACT_NO)
            .deliveryContactName(DEFAULT_DELIVERY_CONTACT_NAME)
            .deliveryContactEmail(DEFAULT_DELIVERY_CONTACT_EMAIL)
            .priority(DEFAULT_PRIORITY)
            .shippingMethod(DEFAULT_SHIPPING_METHOD)
            .freightCharges(DEFAULT_FREIGHT_CHARGES)
            .status(DEFAULT_STATUS)
            .notes(DEFAULT_NOTES)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .purchaseOrderUuid(DEFAULT_PURCHASE_ORDER_UUID)
            .poStatus(DEFAULT_PO_STATUS)
            .vendorDelivery(DEFAULT_VENDOR_DELIVERY)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .branchId(DEFAULT_BRANCH_ID)
            .branchName(DEFAULT_BRANCH_NAME)
            .vendorFaxNo(DEFAULT_VENDOR_FAX_NO)
            .vendorEmail(DEFAULT_VENDOR_EMAIL)
            .vendorFaxRequestStatus(DEFAULT_VENDOR_FAX_REQUEST_STATUS)
            .vendorEmailRequestStatus(DEFAULT_VENDOR_EMAIL_REQUEST_STATUS)
            .poRequestDocumentName(DEFAULT_PO_REQUEST_DOCUMENT_NAME)
            .poRequestAckReceivedStatus(DEFAULT_PO_REQUEST_ACK_RECEIVED_STATUS)
            .po850EdiString(DEFAULT_PO_850_EDI_STRING)
            .po855EdiString(DEFAULT_PO_855_EDI_STRING)
            .poRequestSendDatetime(DEFAULT_PO_REQUEST_SEND_DATETIME)
            .poAckReceivedDatetime(DEFAULT_PO_ACK_RECEIVED_DATETIME);
        return purchaseOrder;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PurchaseOrder createUpdatedEntity(EntityManager em) {
        PurchaseOrder purchaseOrder = new PurchaseOrder()
            .poNumber(UPDATED_PO_NUMBER)
            .poDate(UPDATED_PO_DATE)
            .itemLocationId(UPDATED_ITEM_LOCATION_ID)
            .itemLocationName(UPDATED_ITEM_LOCATION_NAME)
            .salesOrderId(UPDATED_SALES_ORDER_ID)
            .salesOrderNo(UPDATED_SALES_ORDER_NO)
            .vendorId(UPDATED_VENDOR_ID)
            .vendorName(UPDATED_VENDOR_NAME)
            .billingAddressLine1(UPDATED_BILLING_ADDRESS_LINE_1)
            .billingAddressLine2(UPDATED_BILLING_ADDRESS_LINE_2)
            .billingAddressCity(UPDATED_BILLING_ADDRESS_CITY)
            .billingAddressState(UPDATED_BILLING_ADDRESS_STATE)
            .billingAddressZip(UPDATED_BILLING_ADDRESS_ZIP)
            .billingContactNo(UPDATED_BILLING_CONTACT_NO)
            .billingContactName(UPDATED_BILLING_CONTACT_NAME)
            .billingContactEmail(UPDATED_BILLING_CONTACT_EMAIL)
            .deliveryAddressLine1(UPDATED_DELIVERY_ADDRESS_LINE_1)
            .deliveryAddressLine2(UPDATED_DELIVERY_ADDRESS_LINE_2)
            .deliveryAddressCity(UPDATED_DELIVERY_ADDRESS_CITY)
            .deliveryAddressState(UPDATED_DELIVERY_ADDRESS_STATE)
            .deliveryAddressZip(UPDATED_DELIVERY_ADDRESS_ZIP)
            .deliveryContactNo(UPDATED_DELIVERY_CONTACT_NO)
            .deliveryContactName(UPDATED_DELIVERY_CONTACT_NAME)
            .deliveryContactEmail(UPDATED_DELIVERY_CONTACT_EMAIL)
            .priority(UPDATED_PRIORITY)
            .shippingMethod(UPDATED_SHIPPING_METHOD)
            .freightCharges(UPDATED_FREIGHT_CHARGES)
            .status(UPDATED_STATUS)
            .notes(UPDATED_NOTES)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .purchaseOrderUuid(UPDATED_PURCHASE_ORDER_UUID)
            .poStatus(UPDATED_PO_STATUS)
            .vendorDelivery(UPDATED_VENDOR_DELIVERY)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .branchId(UPDATED_BRANCH_ID)
            .branchName(UPDATED_BRANCH_NAME)
            .vendorFaxNo(UPDATED_VENDOR_FAX_NO)
            .vendorEmail(UPDATED_VENDOR_EMAIL)
            .vendorFaxRequestStatus(UPDATED_VENDOR_FAX_REQUEST_STATUS)
            .vendorEmailRequestStatus(UPDATED_VENDOR_EMAIL_REQUEST_STATUS)
            .poRequestDocumentName(UPDATED_PO_REQUEST_DOCUMENT_NAME)
            .poRequestAckReceivedStatus(UPDATED_PO_REQUEST_ACK_RECEIVED_STATUS)
            .po850EdiString(UPDATED_PO_850_EDI_STRING)
            .po855EdiString(UPDATED_PO_855_EDI_STRING)
            .poRequestSendDatetime(UPDATED_PO_REQUEST_SEND_DATETIME)
            .poAckReceivedDatetime(UPDATED_PO_ACK_RECEIVED_DATETIME);
        return purchaseOrder;
    }

    @BeforeEach
    public void initTest() {
        purchaseOrder = createEntity(em);
    }

    @Test
    @Transactional
    void createPurchaseOrder() throws Exception {
        int databaseSizeBeforeCreate = purchaseOrderRepository.findAll().size();
        // Create the PurchaseOrder
        PurchaseOrderDTO purchaseOrderDTO = purchaseOrderMapper.toDto(purchaseOrder);
        restPurchaseOrderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderDTO))
            )
            .andExpect(status().isCreated());

        // Validate the PurchaseOrder in the database
        List<PurchaseOrder> purchaseOrderList = purchaseOrderRepository.findAll();
        assertThat(purchaseOrderList).hasSize(databaseSizeBeforeCreate + 1);
        PurchaseOrder testPurchaseOrder = purchaseOrderList.get(purchaseOrderList.size() - 1);
        assertThat(testPurchaseOrder.getPoNumber()).isEqualTo(DEFAULT_PO_NUMBER);
        assertThat(testPurchaseOrder.getPoDate()).isEqualTo(DEFAULT_PO_DATE);
        assertThat(testPurchaseOrder.getItemLocationId()).isEqualTo(DEFAULT_ITEM_LOCATION_ID);
        assertThat(testPurchaseOrder.getItemLocationName()).isEqualTo(DEFAULT_ITEM_LOCATION_NAME);
        assertThat(testPurchaseOrder.getSalesOrderId()).isEqualTo(DEFAULT_SALES_ORDER_ID);
        assertThat(testPurchaseOrder.getSalesOrderNo()).isEqualTo(DEFAULT_SALES_ORDER_NO);
        assertThat(testPurchaseOrder.getVendorId()).isEqualTo(DEFAULT_VENDOR_ID);
        assertThat(testPurchaseOrder.getVendorName()).isEqualTo(DEFAULT_VENDOR_NAME);
        assertThat(testPurchaseOrder.getBillingAddressLine1()).isEqualTo(DEFAULT_BILLING_ADDRESS_LINE_1);
        assertThat(testPurchaseOrder.getBillingAddressLine2()).isEqualTo(DEFAULT_BILLING_ADDRESS_LINE_2);
        assertThat(testPurchaseOrder.getBillingAddressCity()).isEqualTo(DEFAULT_BILLING_ADDRESS_CITY);
        assertThat(testPurchaseOrder.getBillingAddressState()).isEqualTo(DEFAULT_BILLING_ADDRESS_STATE);
        assertThat(testPurchaseOrder.getBillingAddressZip()).isEqualTo(DEFAULT_BILLING_ADDRESS_ZIP);
        assertThat(testPurchaseOrder.getBillingContactNo()).isEqualTo(DEFAULT_BILLING_CONTACT_NO);
        assertThat(testPurchaseOrder.getBillingContactName()).isEqualTo(DEFAULT_BILLING_CONTACT_NAME);
        assertThat(testPurchaseOrder.getBillingContactEmail()).isEqualTo(DEFAULT_BILLING_CONTACT_EMAIL);
        assertThat(testPurchaseOrder.getDeliveryAddressLine1()).isEqualTo(DEFAULT_DELIVERY_ADDRESS_LINE_1);
        assertThat(testPurchaseOrder.getDeliveryAddressLine2()).isEqualTo(DEFAULT_DELIVERY_ADDRESS_LINE_2);
        assertThat(testPurchaseOrder.getDeliveryAddressCity()).isEqualTo(DEFAULT_DELIVERY_ADDRESS_CITY);
        assertThat(testPurchaseOrder.getDeliveryAddressState()).isEqualTo(DEFAULT_DELIVERY_ADDRESS_STATE);
        assertThat(testPurchaseOrder.getDeliveryAddressZip()).isEqualTo(DEFAULT_DELIVERY_ADDRESS_ZIP);
        assertThat(testPurchaseOrder.getDeliveryContactNo()).isEqualTo(DEFAULT_DELIVERY_CONTACT_NO);
        assertThat(testPurchaseOrder.getDeliveryContactName()).isEqualTo(DEFAULT_DELIVERY_CONTACT_NAME);
        assertThat(testPurchaseOrder.getDeliveryContactEmail()).isEqualTo(DEFAULT_DELIVERY_CONTACT_EMAIL);
        assertThat(testPurchaseOrder.getPriority()).isEqualTo(DEFAULT_PRIORITY);
        assertThat(testPurchaseOrder.getShippingMethod()).isEqualTo(DEFAULT_SHIPPING_METHOD);
        assertThat(testPurchaseOrder.getFreightCharges()).isEqualTo(DEFAULT_FREIGHT_CHARGES);
        assertThat(testPurchaseOrder.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPurchaseOrder.getNotes()).isEqualTo(DEFAULT_NOTES);
        assertThat(testPurchaseOrder.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testPurchaseOrder.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testPurchaseOrder.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testPurchaseOrder.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testPurchaseOrder.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPurchaseOrder.getPurchaseOrderUuid()).isEqualTo(DEFAULT_PURCHASE_ORDER_UUID);
        assertThat(testPurchaseOrder.getPoStatus()).isEqualTo(DEFAULT_PO_STATUS);
        assertThat(testPurchaseOrder.getVendorDelivery()).isEqualTo(DEFAULT_VENDOR_DELIVERY);
        assertThat(testPurchaseOrder.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testPurchaseOrder.getBranchId()).isEqualTo(DEFAULT_BRANCH_ID);
        assertThat(testPurchaseOrder.getBranchName()).isEqualTo(DEFAULT_BRANCH_NAME);
        assertThat(testPurchaseOrder.getVendorFaxNo()).isEqualTo(DEFAULT_VENDOR_FAX_NO);
        assertThat(testPurchaseOrder.getVendorEmail()).isEqualTo(DEFAULT_VENDOR_EMAIL);
        assertThat(testPurchaseOrder.getVendorFaxRequestStatus()).isEqualTo(DEFAULT_VENDOR_FAX_REQUEST_STATUS);
        assertThat(testPurchaseOrder.getVendorEmailRequestStatus()).isEqualTo(DEFAULT_VENDOR_EMAIL_REQUEST_STATUS);
        assertThat(testPurchaseOrder.getPoRequestDocumentName()).isEqualTo(DEFAULT_PO_REQUEST_DOCUMENT_NAME);
        assertThat(testPurchaseOrder.getPoRequestAckReceivedStatus()).isEqualTo(DEFAULT_PO_REQUEST_ACK_RECEIVED_STATUS);
        assertThat(testPurchaseOrder.getPo850EdiString()).isEqualTo(DEFAULT_PO_850_EDI_STRING);
        assertThat(testPurchaseOrder.getPo855EdiString()).isEqualTo(DEFAULT_PO_855_EDI_STRING);
        assertThat(testPurchaseOrder.getPoRequestSendDatetime()).isEqualTo(DEFAULT_PO_REQUEST_SEND_DATETIME);
        assertThat(testPurchaseOrder.getPoAckReceivedDatetime()).isEqualTo(DEFAULT_PO_ACK_RECEIVED_DATETIME);
    }

    @Test
    @Transactional
    void createPurchaseOrderWithExistingId() throws Exception {
        // Create the PurchaseOrder with an existing ID
        purchaseOrder.setPoId(1L);
        PurchaseOrderDTO purchaseOrderDTO = purchaseOrderMapper.toDto(purchaseOrder);

        int databaseSizeBeforeCreate = purchaseOrderRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPurchaseOrderMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseOrder in the database
        List<PurchaseOrder> purchaseOrderList = purchaseOrderRepository.findAll();
        assertThat(purchaseOrderList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPurchaseOrders() throws Exception {
        // Initialize the database
        purchaseOrderRepository.saveAndFlush(purchaseOrder);

        // Get all the purchaseOrderList
        restPurchaseOrderMockMvc
            .perform(get(ENTITY_API_URL + "?sort=poId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].poId").value(hasItem(purchaseOrder.getPoId().intValue())))
            .andExpect(jsonPath("$.[*].poNumber").value(hasItem(DEFAULT_PO_NUMBER)))
            .andExpect(jsonPath("$.[*].poDate").value(hasItem(DEFAULT_PO_DATE.toString())))
            .andExpect(jsonPath("$.[*].itemLocationId").value(hasItem(DEFAULT_ITEM_LOCATION_ID.intValue())))
            .andExpect(jsonPath("$.[*].itemLocationName").value(hasItem(DEFAULT_ITEM_LOCATION_NAME)))
            .andExpect(jsonPath("$.[*].salesOrderId").value(hasItem(DEFAULT_SALES_ORDER_ID.intValue())))
            .andExpect(jsonPath("$.[*].salesOrderNo").value(hasItem(DEFAULT_SALES_ORDER_NO)))
            .andExpect(jsonPath("$.[*].vendorId").value(hasItem(DEFAULT_VENDOR_ID.intValue())))
            .andExpect(jsonPath("$.[*].vendorName").value(hasItem(DEFAULT_VENDOR_NAME)))
            .andExpect(jsonPath("$.[*].billingAddressLine1").value(hasItem(DEFAULT_BILLING_ADDRESS_LINE_1)))
            .andExpect(jsonPath("$.[*].billingAddressLine2").value(hasItem(DEFAULT_BILLING_ADDRESS_LINE_2)))
            .andExpect(jsonPath("$.[*].billingAddressCity").value(hasItem(DEFAULT_BILLING_ADDRESS_CITY)))
            .andExpect(jsonPath("$.[*].billingAddressState").value(hasItem(DEFAULT_BILLING_ADDRESS_STATE)))
            .andExpect(jsonPath("$.[*].billingAddressZip").value(hasItem(DEFAULT_BILLING_ADDRESS_ZIP)))
            .andExpect(jsonPath("$.[*].billingContactNo").value(hasItem(DEFAULT_BILLING_CONTACT_NO)))
            .andExpect(jsonPath("$.[*].billingContactName").value(hasItem(DEFAULT_BILLING_CONTACT_NAME)))
            .andExpect(jsonPath("$.[*].billingContactEmail").value(hasItem(DEFAULT_BILLING_CONTACT_EMAIL)))
            .andExpect(jsonPath("$.[*].deliveryAddressLine1").value(hasItem(DEFAULT_DELIVERY_ADDRESS_LINE_1)))
            .andExpect(jsonPath("$.[*].deliveryAddressLine2").value(hasItem(DEFAULT_DELIVERY_ADDRESS_LINE_2)))
            .andExpect(jsonPath("$.[*].deliveryAddressCity").value(hasItem(DEFAULT_DELIVERY_ADDRESS_CITY)))
            .andExpect(jsonPath("$.[*].deliveryAddressState").value(hasItem(DEFAULT_DELIVERY_ADDRESS_STATE)))
            .andExpect(jsonPath("$.[*].deliveryAddressZip").value(hasItem(DEFAULT_DELIVERY_ADDRESS_ZIP)))
            .andExpect(jsonPath("$.[*].deliveryContactNo").value(hasItem(DEFAULT_DELIVERY_CONTACT_NO)))
            .andExpect(jsonPath("$.[*].deliveryContactName").value(hasItem(DEFAULT_DELIVERY_CONTACT_NAME)))
            .andExpect(jsonPath("$.[*].deliveryContactEmail").value(hasItem(DEFAULT_DELIVERY_CONTACT_EMAIL)))
            .andExpect(jsonPath("$.[*].priority").value(hasItem(DEFAULT_PRIORITY)))
            .andExpect(jsonPath("$.[*].shippingMethod").value(hasItem(DEFAULT_SHIPPING_METHOD)))
            .andExpect(jsonPath("$.[*].freightCharges").value(hasItem(DEFAULT_FREIGHT_CHARGES.doubleValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES)))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdByName").value(hasItem(DEFAULT_CREATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedById").value(hasItem(DEFAULT_UPDATED_BY_ID.intValue())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].purchaseOrderUuid").value(hasItem(DEFAULT_PURCHASE_ORDER_UUID.toString())))
            .andExpect(jsonPath("$.[*].poStatus").value(hasItem(DEFAULT_PO_STATUS)))
            .andExpect(jsonPath("$.[*].vendorDelivery").value(hasItem(DEFAULT_VENDOR_DELIVERY.booleanValue())))
            .andExpect(jsonPath("$.[*].updatedByName").value(hasItem(DEFAULT_UPDATED_BY_NAME)))
            .andExpect(jsonPath("$.[*].branchId").value(hasItem(DEFAULT_BRANCH_ID.intValue())))
            .andExpect(jsonPath("$.[*].branchName").value(hasItem(DEFAULT_BRANCH_NAME)))
            .andExpect(jsonPath("$.[*].vendorFaxNo").value(hasItem(DEFAULT_VENDOR_FAX_NO)))
            .andExpect(jsonPath("$.[*].vendorEmail").value(hasItem(DEFAULT_VENDOR_EMAIL)))
            .andExpect(jsonPath("$.[*].vendorFaxRequestStatus").value(hasItem(DEFAULT_VENDOR_FAX_REQUEST_STATUS)))
            .andExpect(jsonPath("$.[*].vendorEmailRequestStatus").value(hasItem(DEFAULT_VENDOR_EMAIL_REQUEST_STATUS)))
            .andExpect(jsonPath("$.[*].poRequestDocumentName").value(hasItem(DEFAULT_PO_REQUEST_DOCUMENT_NAME)))
            .andExpect(jsonPath("$.[*].poRequestAckReceivedStatus").value(hasItem(DEFAULT_PO_REQUEST_ACK_RECEIVED_STATUS)))
            .andExpect(jsonPath("$.[*].po850EdiString").value(hasItem(DEFAULT_PO_850_EDI_STRING)))
            .andExpect(jsonPath("$.[*].po855EdiString").value(hasItem(DEFAULT_PO_855_EDI_STRING)))
            .andExpect(jsonPath("$.[*].poRequestSendDatetime").value(hasItem(DEFAULT_PO_REQUEST_SEND_DATETIME.toString())))
            .andExpect(jsonPath("$.[*].poAckReceivedDatetime").value(hasItem(DEFAULT_PO_ACK_RECEIVED_DATETIME.toString())));
    }

    @Test
    @Transactional
    void getPurchaseOrder() throws Exception {
        // Initialize the database
        purchaseOrderRepository.saveAndFlush(purchaseOrder);

        // Get the purchaseOrder
        restPurchaseOrderMockMvc
            .perform(get(ENTITY_API_URL_ID, purchaseOrder.getPoId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.poId").value(purchaseOrder.getPoId().intValue()))
            .andExpect(jsonPath("$.poNumber").value(DEFAULT_PO_NUMBER))
            .andExpect(jsonPath("$.poDate").value(DEFAULT_PO_DATE.toString()))
            .andExpect(jsonPath("$.itemLocationId").value(DEFAULT_ITEM_LOCATION_ID.intValue()))
            .andExpect(jsonPath("$.itemLocationName").value(DEFAULT_ITEM_LOCATION_NAME))
            .andExpect(jsonPath("$.salesOrderId").value(DEFAULT_SALES_ORDER_ID.intValue()))
            .andExpect(jsonPath("$.salesOrderNo").value(DEFAULT_SALES_ORDER_NO))
            .andExpect(jsonPath("$.vendorId").value(DEFAULT_VENDOR_ID.intValue()))
            .andExpect(jsonPath("$.vendorName").value(DEFAULT_VENDOR_NAME))
            .andExpect(jsonPath("$.billingAddressLine1").value(DEFAULT_BILLING_ADDRESS_LINE_1))
            .andExpect(jsonPath("$.billingAddressLine2").value(DEFAULT_BILLING_ADDRESS_LINE_2))
            .andExpect(jsonPath("$.billingAddressCity").value(DEFAULT_BILLING_ADDRESS_CITY))
            .andExpect(jsonPath("$.billingAddressState").value(DEFAULT_BILLING_ADDRESS_STATE))
            .andExpect(jsonPath("$.billingAddressZip").value(DEFAULT_BILLING_ADDRESS_ZIP))
            .andExpect(jsonPath("$.billingContactNo").value(DEFAULT_BILLING_CONTACT_NO))
            .andExpect(jsonPath("$.billingContactName").value(DEFAULT_BILLING_CONTACT_NAME))
            .andExpect(jsonPath("$.billingContactEmail").value(DEFAULT_BILLING_CONTACT_EMAIL))
            .andExpect(jsonPath("$.deliveryAddressLine1").value(DEFAULT_DELIVERY_ADDRESS_LINE_1))
            .andExpect(jsonPath("$.deliveryAddressLine2").value(DEFAULT_DELIVERY_ADDRESS_LINE_2))
            .andExpect(jsonPath("$.deliveryAddressCity").value(DEFAULT_DELIVERY_ADDRESS_CITY))
            .andExpect(jsonPath("$.deliveryAddressState").value(DEFAULT_DELIVERY_ADDRESS_STATE))
            .andExpect(jsonPath("$.deliveryAddressZip").value(DEFAULT_DELIVERY_ADDRESS_ZIP))
            .andExpect(jsonPath("$.deliveryContactNo").value(DEFAULT_DELIVERY_CONTACT_NO))
            .andExpect(jsonPath("$.deliveryContactName").value(DEFAULT_DELIVERY_CONTACT_NAME))
            .andExpect(jsonPath("$.deliveryContactEmail").value(DEFAULT_DELIVERY_CONTACT_EMAIL))
            .andExpect(jsonPath("$.priority").value(DEFAULT_PRIORITY))
            .andExpect(jsonPath("$.shippingMethod").value(DEFAULT_SHIPPING_METHOD))
            .andExpect(jsonPath("$.freightCharges").value(DEFAULT_FREIGHT_CHARGES.doubleValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.createdByName").value(DEFAULT_CREATED_BY_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedById").value(DEFAULT_UPDATED_BY_ID.intValue()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.purchaseOrderUuid").value(DEFAULT_PURCHASE_ORDER_UUID.toString()))
            .andExpect(jsonPath("$.poStatus").value(DEFAULT_PO_STATUS))
            .andExpect(jsonPath("$.vendorDelivery").value(DEFAULT_VENDOR_DELIVERY.booleanValue()))
            .andExpect(jsonPath("$.updatedByName").value(DEFAULT_UPDATED_BY_NAME))
            .andExpect(jsonPath("$.branchId").value(DEFAULT_BRANCH_ID.intValue()))
            .andExpect(jsonPath("$.branchName").value(DEFAULT_BRANCH_NAME))
            .andExpect(jsonPath("$.vendorFaxNo").value(DEFAULT_VENDOR_FAX_NO))
            .andExpect(jsonPath("$.vendorEmail").value(DEFAULT_VENDOR_EMAIL))
            .andExpect(jsonPath("$.vendorFaxRequestStatus").value(DEFAULT_VENDOR_FAX_REQUEST_STATUS))
            .andExpect(jsonPath("$.vendorEmailRequestStatus").value(DEFAULT_VENDOR_EMAIL_REQUEST_STATUS))
            .andExpect(jsonPath("$.poRequestDocumentName").value(DEFAULT_PO_REQUEST_DOCUMENT_NAME))
            .andExpect(jsonPath("$.poRequestAckReceivedStatus").value(DEFAULT_PO_REQUEST_ACK_RECEIVED_STATUS))
            .andExpect(jsonPath("$.po850EdiString").value(DEFAULT_PO_850_EDI_STRING))
            .andExpect(jsonPath("$.po855EdiString").value(DEFAULT_PO_855_EDI_STRING))
            .andExpect(jsonPath("$.poRequestSendDatetime").value(DEFAULT_PO_REQUEST_SEND_DATETIME.toString()))
            .andExpect(jsonPath("$.poAckReceivedDatetime").value(DEFAULT_PO_ACK_RECEIVED_DATETIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingPurchaseOrder() throws Exception {
        // Get the purchaseOrder
        restPurchaseOrderMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewPurchaseOrder() throws Exception {
        // Initialize the database
        purchaseOrderRepository.saveAndFlush(purchaseOrder);

        int databaseSizeBeforeUpdate = purchaseOrderRepository.findAll().size();

        // Update the purchaseOrder
        PurchaseOrder updatedPurchaseOrder = purchaseOrderRepository.findById(purchaseOrder.getPoId()).get();
        // Disconnect from session so that the updates on updatedPurchaseOrder are not directly saved in db
        em.detach(updatedPurchaseOrder);
        updatedPurchaseOrder
            .poNumber(UPDATED_PO_NUMBER)
            .poDate(UPDATED_PO_DATE)
            .itemLocationId(UPDATED_ITEM_LOCATION_ID)
            .itemLocationName(UPDATED_ITEM_LOCATION_NAME)
            .salesOrderId(UPDATED_SALES_ORDER_ID)
            .salesOrderNo(UPDATED_SALES_ORDER_NO)
            .vendorId(UPDATED_VENDOR_ID)
            .vendorName(UPDATED_VENDOR_NAME)
            .billingAddressLine1(UPDATED_BILLING_ADDRESS_LINE_1)
            .billingAddressLine2(UPDATED_BILLING_ADDRESS_LINE_2)
            .billingAddressCity(UPDATED_BILLING_ADDRESS_CITY)
            .billingAddressState(UPDATED_BILLING_ADDRESS_STATE)
            .billingAddressZip(UPDATED_BILLING_ADDRESS_ZIP)
            .billingContactNo(UPDATED_BILLING_CONTACT_NO)
            .billingContactName(UPDATED_BILLING_CONTACT_NAME)
            .billingContactEmail(UPDATED_BILLING_CONTACT_EMAIL)
            .deliveryAddressLine1(UPDATED_DELIVERY_ADDRESS_LINE_1)
            .deliveryAddressLine2(UPDATED_DELIVERY_ADDRESS_LINE_2)
            .deliveryAddressCity(UPDATED_DELIVERY_ADDRESS_CITY)
            .deliveryAddressState(UPDATED_DELIVERY_ADDRESS_STATE)
            .deliveryAddressZip(UPDATED_DELIVERY_ADDRESS_ZIP)
            .deliveryContactNo(UPDATED_DELIVERY_CONTACT_NO)
            .deliveryContactName(UPDATED_DELIVERY_CONTACT_NAME)
            .deliveryContactEmail(UPDATED_DELIVERY_CONTACT_EMAIL)
            .priority(UPDATED_PRIORITY)
            .shippingMethod(UPDATED_SHIPPING_METHOD)
            .freightCharges(UPDATED_FREIGHT_CHARGES)
            .status(UPDATED_STATUS)
            .notes(UPDATED_NOTES)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .purchaseOrderUuid(UPDATED_PURCHASE_ORDER_UUID)
            .poStatus(UPDATED_PO_STATUS)
            .vendorDelivery(UPDATED_VENDOR_DELIVERY)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .branchId(UPDATED_BRANCH_ID)
            .branchName(UPDATED_BRANCH_NAME)
            .vendorFaxNo(UPDATED_VENDOR_FAX_NO)
            .vendorEmail(UPDATED_VENDOR_EMAIL)
            .vendorFaxRequestStatus(UPDATED_VENDOR_FAX_REQUEST_STATUS)
            .vendorEmailRequestStatus(UPDATED_VENDOR_EMAIL_REQUEST_STATUS)
            .poRequestDocumentName(UPDATED_PO_REQUEST_DOCUMENT_NAME)
            .poRequestAckReceivedStatus(UPDATED_PO_REQUEST_ACK_RECEIVED_STATUS)
            .po850EdiString(UPDATED_PO_850_EDI_STRING)
            .po855EdiString(UPDATED_PO_855_EDI_STRING)
            .poRequestSendDatetime(UPDATED_PO_REQUEST_SEND_DATETIME)
            .poAckReceivedDatetime(UPDATED_PO_ACK_RECEIVED_DATETIME);
        PurchaseOrderDTO purchaseOrderDTO = purchaseOrderMapper.toDto(updatedPurchaseOrder);

        restPurchaseOrderMockMvc
            .perform(
                put(ENTITY_API_URL_ID, purchaseOrderDTO.getPoId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderDTO))
            )
            .andExpect(status().isOk());

        // Validate the PurchaseOrder in the database
        List<PurchaseOrder> purchaseOrderList = purchaseOrderRepository.findAll();
        assertThat(purchaseOrderList).hasSize(databaseSizeBeforeUpdate);
        PurchaseOrder testPurchaseOrder = purchaseOrderList.get(purchaseOrderList.size() - 1);
        assertThat(testPurchaseOrder.getPoNumber()).isEqualTo(UPDATED_PO_NUMBER);
        assertThat(testPurchaseOrder.getPoDate()).isEqualTo(UPDATED_PO_DATE);
        assertThat(testPurchaseOrder.getItemLocationId()).isEqualTo(UPDATED_ITEM_LOCATION_ID);
        assertThat(testPurchaseOrder.getItemLocationName()).isEqualTo(UPDATED_ITEM_LOCATION_NAME);
        assertThat(testPurchaseOrder.getSalesOrderId()).isEqualTo(UPDATED_SALES_ORDER_ID);
        assertThat(testPurchaseOrder.getSalesOrderNo()).isEqualTo(UPDATED_SALES_ORDER_NO);
        assertThat(testPurchaseOrder.getVendorId()).isEqualTo(UPDATED_VENDOR_ID);
        assertThat(testPurchaseOrder.getVendorName()).isEqualTo(UPDATED_VENDOR_NAME);
        assertThat(testPurchaseOrder.getBillingAddressLine1()).isEqualTo(UPDATED_BILLING_ADDRESS_LINE_1);
        assertThat(testPurchaseOrder.getBillingAddressLine2()).isEqualTo(UPDATED_BILLING_ADDRESS_LINE_2);
        assertThat(testPurchaseOrder.getBillingAddressCity()).isEqualTo(UPDATED_BILLING_ADDRESS_CITY);
        assertThat(testPurchaseOrder.getBillingAddressState()).isEqualTo(UPDATED_BILLING_ADDRESS_STATE);
        assertThat(testPurchaseOrder.getBillingAddressZip()).isEqualTo(UPDATED_BILLING_ADDRESS_ZIP);
        assertThat(testPurchaseOrder.getBillingContactNo()).isEqualTo(UPDATED_BILLING_CONTACT_NO);
        assertThat(testPurchaseOrder.getBillingContactName()).isEqualTo(UPDATED_BILLING_CONTACT_NAME);
        assertThat(testPurchaseOrder.getBillingContactEmail()).isEqualTo(UPDATED_BILLING_CONTACT_EMAIL);
        assertThat(testPurchaseOrder.getDeliveryAddressLine1()).isEqualTo(UPDATED_DELIVERY_ADDRESS_LINE_1);
        assertThat(testPurchaseOrder.getDeliveryAddressLine2()).isEqualTo(UPDATED_DELIVERY_ADDRESS_LINE_2);
        assertThat(testPurchaseOrder.getDeliveryAddressCity()).isEqualTo(UPDATED_DELIVERY_ADDRESS_CITY);
        assertThat(testPurchaseOrder.getDeliveryAddressState()).isEqualTo(UPDATED_DELIVERY_ADDRESS_STATE);
        assertThat(testPurchaseOrder.getDeliveryAddressZip()).isEqualTo(UPDATED_DELIVERY_ADDRESS_ZIP);
        assertThat(testPurchaseOrder.getDeliveryContactNo()).isEqualTo(UPDATED_DELIVERY_CONTACT_NO);
        assertThat(testPurchaseOrder.getDeliveryContactName()).isEqualTo(UPDATED_DELIVERY_CONTACT_NAME);
        assertThat(testPurchaseOrder.getDeliveryContactEmail()).isEqualTo(UPDATED_DELIVERY_CONTACT_EMAIL);
        assertThat(testPurchaseOrder.getPriority()).isEqualTo(UPDATED_PRIORITY);
        assertThat(testPurchaseOrder.getShippingMethod()).isEqualTo(UPDATED_SHIPPING_METHOD);
        assertThat(testPurchaseOrder.getFreightCharges()).isEqualTo(UPDATED_FREIGHT_CHARGES);
        assertThat(testPurchaseOrder.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPurchaseOrder.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testPurchaseOrder.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPurchaseOrder.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPurchaseOrder.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPurchaseOrder.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPurchaseOrder.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPurchaseOrder.getPurchaseOrderUuid()).isEqualTo(UPDATED_PURCHASE_ORDER_UUID);
        assertThat(testPurchaseOrder.getPoStatus()).isEqualTo(UPDATED_PO_STATUS);
        assertThat(testPurchaseOrder.getVendorDelivery()).isEqualTo(UPDATED_VENDOR_DELIVERY);
        assertThat(testPurchaseOrder.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPurchaseOrder.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testPurchaseOrder.getBranchName()).isEqualTo(UPDATED_BRANCH_NAME);
        assertThat(testPurchaseOrder.getVendorFaxNo()).isEqualTo(UPDATED_VENDOR_FAX_NO);
        assertThat(testPurchaseOrder.getVendorEmail()).isEqualTo(UPDATED_VENDOR_EMAIL);
        assertThat(testPurchaseOrder.getVendorFaxRequestStatus()).isEqualTo(UPDATED_VENDOR_FAX_REQUEST_STATUS);
        assertThat(testPurchaseOrder.getVendorEmailRequestStatus()).isEqualTo(UPDATED_VENDOR_EMAIL_REQUEST_STATUS);
        assertThat(testPurchaseOrder.getPoRequestDocumentName()).isEqualTo(UPDATED_PO_REQUEST_DOCUMENT_NAME);
        assertThat(testPurchaseOrder.getPoRequestAckReceivedStatus()).isEqualTo(UPDATED_PO_REQUEST_ACK_RECEIVED_STATUS);
        assertThat(testPurchaseOrder.getPo850EdiString()).isEqualTo(UPDATED_PO_850_EDI_STRING);
        assertThat(testPurchaseOrder.getPo855EdiString()).isEqualTo(UPDATED_PO_855_EDI_STRING);
        assertThat(testPurchaseOrder.getPoRequestSendDatetime()).isEqualTo(UPDATED_PO_REQUEST_SEND_DATETIME);
        assertThat(testPurchaseOrder.getPoAckReceivedDatetime()).isEqualTo(UPDATED_PO_ACK_RECEIVED_DATETIME);
    }

    @Test
    @Transactional
    void putNonExistingPurchaseOrder() throws Exception {
        int databaseSizeBeforeUpdate = purchaseOrderRepository.findAll().size();
        purchaseOrder.setPoId(count.incrementAndGet());

        // Create the PurchaseOrder
        PurchaseOrderDTO purchaseOrderDTO = purchaseOrderMapper.toDto(purchaseOrder);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPurchaseOrderMockMvc
            .perform(
                put(ENTITY_API_URL_ID, purchaseOrderDTO.getPoId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseOrder in the database
        List<PurchaseOrder> purchaseOrderList = purchaseOrderRepository.findAll();
        assertThat(purchaseOrderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPurchaseOrder() throws Exception {
        int databaseSizeBeforeUpdate = purchaseOrderRepository.findAll().size();
        purchaseOrder.setPoId(count.incrementAndGet());

        // Create the PurchaseOrder
        PurchaseOrderDTO purchaseOrderDTO = purchaseOrderMapper.toDto(purchaseOrder);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPurchaseOrderMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseOrder in the database
        List<PurchaseOrder> purchaseOrderList = purchaseOrderRepository.findAll();
        assertThat(purchaseOrderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPurchaseOrder() throws Exception {
        int databaseSizeBeforeUpdate = purchaseOrderRepository.findAll().size();
        purchaseOrder.setPoId(count.incrementAndGet());

        // Create the PurchaseOrder
        PurchaseOrderDTO purchaseOrderDTO = purchaseOrderMapper.toDto(purchaseOrder);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPurchaseOrderMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PurchaseOrder in the database
        List<PurchaseOrder> purchaseOrderList = purchaseOrderRepository.findAll();
        assertThat(purchaseOrderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePurchaseOrderWithPatch() throws Exception {
        // Initialize the database
        purchaseOrderRepository.saveAndFlush(purchaseOrder);

        int databaseSizeBeforeUpdate = purchaseOrderRepository.findAll().size();

        // Update the purchaseOrder using partial update
        PurchaseOrder partialUpdatedPurchaseOrder = new PurchaseOrder();
        partialUpdatedPurchaseOrder.setPoId(purchaseOrder.getPoId());

        partialUpdatedPurchaseOrder
            .itemLocationId(UPDATED_ITEM_LOCATION_ID)
            .salesOrderId(UPDATED_SALES_ORDER_ID)
            .salesOrderNo(UPDATED_SALES_ORDER_NO)
            .billingAddressLine1(UPDATED_BILLING_ADDRESS_LINE_1)
            .billingAddressLine2(UPDATED_BILLING_ADDRESS_LINE_2)
            .billingAddressZip(UPDATED_BILLING_ADDRESS_ZIP)
            .deliveryAddressLine2(UPDATED_DELIVERY_ADDRESS_LINE_2)
            .deliveryAddressCity(UPDATED_DELIVERY_ADDRESS_CITY)
            .deliveryAddressState(UPDATED_DELIVERY_ADDRESS_STATE)
            .deliveryContactNo(UPDATED_DELIVERY_CONTACT_NO)
            .deliveryContactName(UPDATED_DELIVERY_CONTACT_NAME)
            .deliveryContactEmail(UPDATED_DELIVERY_CONTACT_EMAIL)
            .priority(UPDATED_PRIORITY)
            .shippingMethod(UPDATED_SHIPPING_METHOD)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .purchaseOrderUuid(UPDATED_PURCHASE_ORDER_UUID)
            .vendorDelivery(UPDATED_VENDOR_DELIVERY)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .branchId(UPDATED_BRANCH_ID)
            .branchName(UPDATED_BRANCH_NAME)
            .vendorFaxNo(UPDATED_VENDOR_FAX_NO)
            .vendorEmail(UPDATED_VENDOR_EMAIL)
            .vendorFaxRequestStatus(UPDATED_VENDOR_FAX_REQUEST_STATUS)
            .vendorEmailRequestStatus(UPDATED_VENDOR_EMAIL_REQUEST_STATUS)
            .poRequestDocumentName(UPDATED_PO_REQUEST_DOCUMENT_NAME)
            .poRequestAckReceivedStatus(UPDATED_PO_REQUEST_ACK_RECEIVED_STATUS)
            .po850EdiString(UPDATED_PO_850_EDI_STRING)
            .poAckReceivedDatetime(UPDATED_PO_ACK_RECEIVED_DATETIME);

        restPurchaseOrderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPurchaseOrder.getPoId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPurchaseOrder))
            )
            .andExpect(status().isOk());

        // Validate the PurchaseOrder in the database
        List<PurchaseOrder> purchaseOrderList = purchaseOrderRepository.findAll();
        assertThat(purchaseOrderList).hasSize(databaseSizeBeforeUpdate);
        PurchaseOrder testPurchaseOrder = purchaseOrderList.get(purchaseOrderList.size() - 1);
        assertThat(testPurchaseOrder.getPoNumber()).isEqualTo(DEFAULT_PO_NUMBER);
        assertThat(testPurchaseOrder.getPoDate()).isEqualTo(DEFAULT_PO_DATE);
        assertThat(testPurchaseOrder.getItemLocationId()).isEqualTo(UPDATED_ITEM_LOCATION_ID);
        assertThat(testPurchaseOrder.getItemLocationName()).isEqualTo(DEFAULT_ITEM_LOCATION_NAME);
        assertThat(testPurchaseOrder.getSalesOrderId()).isEqualTo(UPDATED_SALES_ORDER_ID);
        assertThat(testPurchaseOrder.getSalesOrderNo()).isEqualTo(UPDATED_SALES_ORDER_NO);
        assertThat(testPurchaseOrder.getVendorId()).isEqualTo(DEFAULT_VENDOR_ID);
        assertThat(testPurchaseOrder.getVendorName()).isEqualTo(DEFAULT_VENDOR_NAME);
        assertThat(testPurchaseOrder.getBillingAddressLine1()).isEqualTo(UPDATED_BILLING_ADDRESS_LINE_1);
        assertThat(testPurchaseOrder.getBillingAddressLine2()).isEqualTo(UPDATED_BILLING_ADDRESS_LINE_2);
        assertThat(testPurchaseOrder.getBillingAddressCity()).isEqualTo(DEFAULT_BILLING_ADDRESS_CITY);
        assertThat(testPurchaseOrder.getBillingAddressState()).isEqualTo(DEFAULT_BILLING_ADDRESS_STATE);
        assertThat(testPurchaseOrder.getBillingAddressZip()).isEqualTo(UPDATED_BILLING_ADDRESS_ZIP);
        assertThat(testPurchaseOrder.getBillingContactNo()).isEqualTo(DEFAULT_BILLING_CONTACT_NO);
        assertThat(testPurchaseOrder.getBillingContactName()).isEqualTo(DEFAULT_BILLING_CONTACT_NAME);
        assertThat(testPurchaseOrder.getBillingContactEmail()).isEqualTo(DEFAULT_BILLING_CONTACT_EMAIL);
        assertThat(testPurchaseOrder.getDeliveryAddressLine1()).isEqualTo(DEFAULT_DELIVERY_ADDRESS_LINE_1);
        assertThat(testPurchaseOrder.getDeliveryAddressLine2()).isEqualTo(UPDATED_DELIVERY_ADDRESS_LINE_2);
        assertThat(testPurchaseOrder.getDeliveryAddressCity()).isEqualTo(UPDATED_DELIVERY_ADDRESS_CITY);
        assertThat(testPurchaseOrder.getDeliveryAddressState()).isEqualTo(UPDATED_DELIVERY_ADDRESS_STATE);
        assertThat(testPurchaseOrder.getDeliveryAddressZip()).isEqualTo(DEFAULT_DELIVERY_ADDRESS_ZIP);
        assertThat(testPurchaseOrder.getDeliveryContactNo()).isEqualTo(UPDATED_DELIVERY_CONTACT_NO);
        assertThat(testPurchaseOrder.getDeliveryContactName()).isEqualTo(UPDATED_DELIVERY_CONTACT_NAME);
        assertThat(testPurchaseOrder.getDeliveryContactEmail()).isEqualTo(UPDATED_DELIVERY_CONTACT_EMAIL);
        assertThat(testPurchaseOrder.getPriority()).isEqualTo(UPDATED_PRIORITY);
        assertThat(testPurchaseOrder.getShippingMethod()).isEqualTo(UPDATED_SHIPPING_METHOD);
        assertThat(testPurchaseOrder.getFreightCharges()).isEqualTo(DEFAULT_FREIGHT_CHARGES);
        assertThat(testPurchaseOrder.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPurchaseOrder.getNotes()).isEqualTo(DEFAULT_NOTES);
        assertThat(testPurchaseOrder.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testPurchaseOrder.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPurchaseOrder.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testPurchaseOrder.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPurchaseOrder.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testPurchaseOrder.getPurchaseOrderUuid()).isEqualTo(UPDATED_PURCHASE_ORDER_UUID);
        assertThat(testPurchaseOrder.getPoStatus()).isEqualTo(DEFAULT_PO_STATUS);
        assertThat(testPurchaseOrder.getVendorDelivery()).isEqualTo(UPDATED_VENDOR_DELIVERY);
        assertThat(testPurchaseOrder.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPurchaseOrder.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testPurchaseOrder.getBranchName()).isEqualTo(UPDATED_BRANCH_NAME);
        assertThat(testPurchaseOrder.getVendorFaxNo()).isEqualTo(UPDATED_VENDOR_FAX_NO);
        assertThat(testPurchaseOrder.getVendorEmail()).isEqualTo(UPDATED_VENDOR_EMAIL);
        assertThat(testPurchaseOrder.getVendorFaxRequestStatus()).isEqualTo(UPDATED_VENDOR_FAX_REQUEST_STATUS);
        assertThat(testPurchaseOrder.getVendorEmailRequestStatus()).isEqualTo(UPDATED_VENDOR_EMAIL_REQUEST_STATUS);
        assertThat(testPurchaseOrder.getPoRequestDocumentName()).isEqualTo(UPDATED_PO_REQUEST_DOCUMENT_NAME);
        assertThat(testPurchaseOrder.getPoRequestAckReceivedStatus()).isEqualTo(UPDATED_PO_REQUEST_ACK_RECEIVED_STATUS);
        assertThat(testPurchaseOrder.getPo850EdiString()).isEqualTo(UPDATED_PO_850_EDI_STRING);
        assertThat(testPurchaseOrder.getPo855EdiString()).isEqualTo(DEFAULT_PO_855_EDI_STRING);
        assertThat(testPurchaseOrder.getPoRequestSendDatetime()).isEqualTo(DEFAULT_PO_REQUEST_SEND_DATETIME);
        assertThat(testPurchaseOrder.getPoAckReceivedDatetime()).isEqualTo(UPDATED_PO_ACK_RECEIVED_DATETIME);
    }

    @Test
    @Transactional
    void fullUpdatePurchaseOrderWithPatch() throws Exception {
        // Initialize the database
        purchaseOrderRepository.saveAndFlush(purchaseOrder);

        int databaseSizeBeforeUpdate = purchaseOrderRepository.findAll().size();

        // Update the purchaseOrder using partial update
        PurchaseOrder partialUpdatedPurchaseOrder = new PurchaseOrder();
        partialUpdatedPurchaseOrder.setPoId(purchaseOrder.getPoId());

        partialUpdatedPurchaseOrder
            .poNumber(UPDATED_PO_NUMBER)
            .poDate(UPDATED_PO_DATE)
            .itemLocationId(UPDATED_ITEM_LOCATION_ID)
            .itemLocationName(UPDATED_ITEM_LOCATION_NAME)
            .salesOrderId(UPDATED_SALES_ORDER_ID)
            .salesOrderNo(UPDATED_SALES_ORDER_NO)
            .vendorId(UPDATED_VENDOR_ID)
            .vendorName(UPDATED_VENDOR_NAME)
            .billingAddressLine1(UPDATED_BILLING_ADDRESS_LINE_1)
            .billingAddressLine2(UPDATED_BILLING_ADDRESS_LINE_2)
            .billingAddressCity(UPDATED_BILLING_ADDRESS_CITY)
            .billingAddressState(UPDATED_BILLING_ADDRESS_STATE)
            .billingAddressZip(UPDATED_BILLING_ADDRESS_ZIP)
            .billingContactNo(UPDATED_BILLING_CONTACT_NO)
            .billingContactName(UPDATED_BILLING_CONTACT_NAME)
            .billingContactEmail(UPDATED_BILLING_CONTACT_EMAIL)
            .deliveryAddressLine1(UPDATED_DELIVERY_ADDRESS_LINE_1)
            .deliveryAddressLine2(UPDATED_DELIVERY_ADDRESS_LINE_2)
            .deliveryAddressCity(UPDATED_DELIVERY_ADDRESS_CITY)
            .deliveryAddressState(UPDATED_DELIVERY_ADDRESS_STATE)
            .deliveryAddressZip(UPDATED_DELIVERY_ADDRESS_ZIP)
            .deliveryContactNo(UPDATED_DELIVERY_CONTACT_NO)
            .deliveryContactName(UPDATED_DELIVERY_CONTACT_NAME)
            .deliveryContactEmail(UPDATED_DELIVERY_CONTACT_EMAIL)
            .priority(UPDATED_PRIORITY)
            .shippingMethod(UPDATED_SHIPPING_METHOD)
            .freightCharges(UPDATED_FREIGHT_CHARGES)
            .status(UPDATED_STATUS)
            .notes(UPDATED_NOTES)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedDate(UPDATED_UPDATED_DATE)
            .purchaseOrderUuid(UPDATED_PURCHASE_ORDER_UUID)
            .poStatus(UPDATED_PO_STATUS)
            .vendorDelivery(UPDATED_VENDOR_DELIVERY)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .branchId(UPDATED_BRANCH_ID)
            .branchName(UPDATED_BRANCH_NAME)
            .vendorFaxNo(UPDATED_VENDOR_FAX_NO)
            .vendorEmail(UPDATED_VENDOR_EMAIL)
            .vendorFaxRequestStatus(UPDATED_VENDOR_FAX_REQUEST_STATUS)
            .vendorEmailRequestStatus(UPDATED_VENDOR_EMAIL_REQUEST_STATUS)
            .poRequestDocumentName(UPDATED_PO_REQUEST_DOCUMENT_NAME)
            .poRequestAckReceivedStatus(UPDATED_PO_REQUEST_ACK_RECEIVED_STATUS)
            .po850EdiString(UPDATED_PO_850_EDI_STRING)
            .po855EdiString(UPDATED_PO_855_EDI_STRING)
            .poRequestSendDatetime(UPDATED_PO_REQUEST_SEND_DATETIME)
            .poAckReceivedDatetime(UPDATED_PO_ACK_RECEIVED_DATETIME);

        restPurchaseOrderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPurchaseOrder.getPoId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPurchaseOrder))
            )
            .andExpect(status().isOk());

        // Validate the PurchaseOrder in the database
        List<PurchaseOrder> purchaseOrderList = purchaseOrderRepository.findAll();
        assertThat(purchaseOrderList).hasSize(databaseSizeBeforeUpdate);
        PurchaseOrder testPurchaseOrder = purchaseOrderList.get(purchaseOrderList.size() - 1);
        assertThat(testPurchaseOrder.getPoNumber()).isEqualTo(UPDATED_PO_NUMBER);
        assertThat(testPurchaseOrder.getPoDate()).isEqualTo(UPDATED_PO_DATE);
        assertThat(testPurchaseOrder.getItemLocationId()).isEqualTo(UPDATED_ITEM_LOCATION_ID);
        assertThat(testPurchaseOrder.getItemLocationName()).isEqualTo(UPDATED_ITEM_LOCATION_NAME);
        assertThat(testPurchaseOrder.getSalesOrderId()).isEqualTo(UPDATED_SALES_ORDER_ID);
        assertThat(testPurchaseOrder.getSalesOrderNo()).isEqualTo(UPDATED_SALES_ORDER_NO);
        assertThat(testPurchaseOrder.getVendorId()).isEqualTo(UPDATED_VENDOR_ID);
        assertThat(testPurchaseOrder.getVendorName()).isEqualTo(UPDATED_VENDOR_NAME);
        assertThat(testPurchaseOrder.getBillingAddressLine1()).isEqualTo(UPDATED_BILLING_ADDRESS_LINE_1);
        assertThat(testPurchaseOrder.getBillingAddressLine2()).isEqualTo(UPDATED_BILLING_ADDRESS_LINE_2);
        assertThat(testPurchaseOrder.getBillingAddressCity()).isEqualTo(UPDATED_BILLING_ADDRESS_CITY);
        assertThat(testPurchaseOrder.getBillingAddressState()).isEqualTo(UPDATED_BILLING_ADDRESS_STATE);
        assertThat(testPurchaseOrder.getBillingAddressZip()).isEqualTo(UPDATED_BILLING_ADDRESS_ZIP);
        assertThat(testPurchaseOrder.getBillingContactNo()).isEqualTo(UPDATED_BILLING_CONTACT_NO);
        assertThat(testPurchaseOrder.getBillingContactName()).isEqualTo(UPDATED_BILLING_CONTACT_NAME);
        assertThat(testPurchaseOrder.getBillingContactEmail()).isEqualTo(UPDATED_BILLING_CONTACT_EMAIL);
        assertThat(testPurchaseOrder.getDeliveryAddressLine1()).isEqualTo(UPDATED_DELIVERY_ADDRESS_LINE_1);
        assertThat(testPurchaseOrder.getDeliveryAddressLine2()).isEqualTo(UPDATED_DELIVERY_ADDRESS_LINE_2);
        assertThat(testPurchaseOrder.getDeliveryAddressCity()).isEqualTo(UPDATED_DELIVERY_ADDRESS_CITY);
        assertThat(testPurchaseOrder.getDeliveryAddressState()).isEqualTo(UPDATED_DELIVERY_ADDRESS_STATE);
        assertThat(testPurchaseOrder.getDeliveryAddressZip()).isEqualTo(UPDATED_DELIVERY_ADDRESS_ZIP);
        assertThat(testPurchaseOrder.getDeliveryContactNo()).isEqualTo(UPDATED_DELIVERY_CONTACT_NO);
        assertThat(testPurchaseOrder.getDeliveryContactName()).isEqualTo(UPDATED_DELIVERY_CONTACT_NAME);
        assertThat(testPurchaseOrder.getDeliveryContactEmail()).isEqualTo(UPDATED_DELIVERY_CONTACT_EMAIL);
        assertThat(testPurchaseOrder.getPriority()).isEqualTo(UPDATED_PRIORITY);
        assertThat(testPurchaseOrder.getShippingMethod()).isEqualTo(UPDATED_SHIPPING_METHOD);
        assertThat(testPurchaseOrder.getFreightCharges()).isEqualTo(UPDATED_FREIGHT_CHARGES);
        assertThat(testPurchaseOrder.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPurchaseOrder.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testPurchaseOrder.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testPurchaseOrder.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testPurchaseOrder.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPurchaseOrder.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testPurchaseOrder.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testPurchaseOrder.getPurchaseOrderUuid()).isEqualTo(UPDATED_PURCHASE_ORDER_UUID);
        assertThat(testPurchaseOrder.getPoStatus()).isEqualTo(UPDATED_PO_STATUS);
        assertThat(testPurchaseOrder.getVendorDelivery()).isEqualTo(UPDATED_VENDOR_DELIVERY);
        assertThat(testPurchaseOrder.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testPurchaseOrder.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testPurchaseOrder.getBranchName()).isEqualTo(UPDATED_BRANCH_NAME);
        assertThat(testPurchaseOrder.getVendorFaxNo()).isEqualTo(UPDATED_VENDOR_FAX_NO);
        assertThat(testPurchaseOrder.getVendorEmail()).isEqualTo(UPDATED_VENDOR_EMAIL);
        assertThat(testPurchaseOrder.getVendorFaxRequestStatus()).isEqualTo(UPDATED_VENDOR_FAX_REQUEST_STATUS);
        assertThat(testPurchaseOrder.getVendorEmailRequestStatus()).isEqualTo(UPDATED_VENDOR_EMAIL_REQUEST_STATUS);
        assertThat(testPurchaseOrder.getPoRequestDocumentName()).isEqualTo(UPDATED_PO_REQUEST_DOCUMENT_NAME);
        assertThat(testPurchaseOrder.getPoRequestAckReceivedStatus()).isEqualTo(UPDATED_PO_REQUEST_ACK_RECEIVED_STATUS);
        assertThat(testPurchaseOrder.getPo850EdiString()).isEqualTo(UPDATED_PO_850_EDI_STRING);
        assertThat(testPurchaseOrder.getPo855EdiString()).isEqualTo(UPDATED_PO_855_EDI_STRING);
        assertThat(testPurchaseOrder.getPoRequestSendDatetime()).isEqualTo(UPDATED_PO_REQUEST_SEND_DATETIME);
        assertThat(testPurchaseOrder.getPoAckReceivedDatetime()).isEqualTo(UPDATED_PO_ACK_RECEIVED_DATETIME);
    }

    @Test
    @Transactional
    void patchNonExistingPurchaseOrder() throws Exception {
        int databaseSizeBeforeUpdate = purchaseOrderRepository.findAll().size();
        purchaseOrder.setPoId(count.incrementAndGet());

        // Create the PurchaseOrder
        PurchaseOrderDTO purchaseOrderDTO = purchaseOrderMapper.toDto(purchaseOrder);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPurchaseOrderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, purchaseOrderDTO.getPoId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseOrder in the database
        List<PurchaseOrder> purchaseOrderList = purchaseOrderRepository.findAll();
        assertThat(purchaseOrderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPurchaseOrder() throws Exception {
        int databaseSizeBeforeUpdate = purchaseOrderRepository.findAll().size();
        purchaseOrder.setPoId(count.incrementAndGet());

        // Create the PurchaseOrder
        PurchaseOrderDTO purchaseOrderDTO = purchaseOrderMapper.toDto(purchaseOrder);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPurchaseOrderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseOrder in the database
        List<PurchaseOrder> purchaseOrderList = purchaseOrderRepository.findAll();
        assertThat(purchaseOrderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPurchaseOrder() throws Exception {
        int databaseSizeBeforeUpdate = purchaseOrderRepository.findAll().size();
        purchaseOrder.setPoId(count.incrementAndGet());

        // Create the PurchaseOrder
        PurchaseOrderDTO purchaseOrderDTO = purchaseOrderMapper.toDto(purchaseOrder);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPurchaseOrderMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(purchaseOrderDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PurchaseOrder in the database
        List<PurchaseOrder> purchaseOrderList = purchaseOrderRepository.findAll();
        assertThat(purchaseOrderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePurchaseOrder() throws Exception {
        // Initialize the database
        purchaseOrderRepository.saveAndFlush(purchaseOrder);

        int databaseSizeBeforeDelete = purchaseOrderRepository.findAll().size();

        // Delete the purchaseOrder
        restPurchaseOrderMockMvc
            .perform(delete(ENTITY_API_URL_ID, purchaseOrder.getPoId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PurchaseOrder> purchaseOrderList = purchaseOrderRepository.findAll();
        assertThat(purchaseOrderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
