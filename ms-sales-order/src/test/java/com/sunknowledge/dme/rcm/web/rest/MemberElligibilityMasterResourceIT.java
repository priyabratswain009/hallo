package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.MemberElligibilityMaster;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.MemberElligibilityMasterRepository;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link MemberElligibilityMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class MemberElligibilityMasterResourceIT {

    private static final Long DEFAULT_SALES_ORDER_ID = 1L;
    private static final Long UPDATED_SALES_ORDER_ID = 2L;

    private static final String DEFAULT_ELLIGIBILITY_CONTROL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ELLIGIBILITY_CONTROL_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_TRADING_PARTNER_SERVICE_ID = "AAAAAAAAAA";
    private static final String UPDATED_TRADING_PARTNER_SERVICE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TRADING_PARTNER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TRADING_PARTNER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PROVIDER_ORGANIZATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PROVIDER_ORGANIZATION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PROVIDER_NPI = "AAAAAAAAAA";
    private static final String UPDATED_PROVIDER_NPI = "BBBBBBBBBB";

    private static final String DEFAULT_PROVIDER_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PROVIDER_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIBER_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIBER_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIBER_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIBER_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIBER_MEMBER_ID = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIBER_MEMBER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIBER_IDCARD = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIBER_IDCARD = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SUBSCRIBER_DOB = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SUBSCRIBER_DOB = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SUBSCRIBER_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIBER_GENDER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SUBSCRIBER_PLAN_ISSUE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SUBSCRIBER_PLAN_ISSUE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_INSURED_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INSURED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_INSURED_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INSURED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_INSURED_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_INSURED_GENDER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_INSURED_DOB = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_INSURED_DOB = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_INSURED_RELATIONSHIPWITH_SUBSCRIBER = "AAAAAAAAAA";
    private static final String UPDATED_INSURED_RELATIONSHIPWITH_SUBSCRIBER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_OF_SERVICE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_OF_SERVICE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SERVICE_TYPE_CODES = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_TYPE_CODES = "BBBBBBBBBB";

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

    private static final UUID DEFAULT_MEMBER_ELLIGIBILITY_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_MEMBER_ELLIGIBILITY_MASTER_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/member-elligibility-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{claimElligibilityMasterId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private MemberElligibilityMasterRepository memberElligibilityMasterRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private MemberElligibilityMaster memberElligibilityMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MemberElligibilityMaster createEntity(EntityManager em) {
        MemberElligibilityMaster memberElligibilityMaster = new MemberElligibilityMaster()
            .salesOrderId(DEFAULT_SALES_ORDER_ID)
            .elligibilityControlNumber(DEFAULT_ELLIGIBILITY_CONTROL_NUMBER)
            .tradingPartnerServiceId(DEFAULT_TRADING_PARTNER_SERVICE_ID)
            .tradingPartnerName(DEFAULT_TRADING_PARTNER_NAME)
            .providerOrganizationName(DEFAULT_PROVIDER_ORGANIZATION_NAME)
            .providerNpi(DEFAULT_PROVIDER_NPI)
            .providerType(DEFAULT_PROVIDER_TYPE)
            .subscriberFirstName(DEFAULT_SUBSCRIBER_FIRST_NAME)
            .subscriberLastName(DEFAULT_SUBSCRIBER_LAST_NAME)
            .subscriberMemberId(DEFAULT_SUBSCRIBER_MEMBER_ID)
            .subscriberIdcard(DEFAULT_SUBSCRIBER_IDCARD)
            .subscriberDob(DEFAULT_SUBSCRIBER_DOB)
            .subscriberGender(DEFAULT_SUBSCRIBER_GENDER)
            .subscriberPlanIssueDate(DEFAULT_SUBSCRIBER_PLAN_ISSUE_DATE)
            .insuredFirstName(DEFAULT_INSURED_FIRST_NAME)
            .insuredLastName(DEFAULT_INSURED_LAST_NAME)
            .insuredGender(DEFAULT_INSURED_GENDER)
            .insuredDob(DEFAULT_INSURED_DOB)
            .insuredRelationshipwithSubscriber(DEFAULT_INSURED_RELATIONSHIPWITH_SUBSCRIBER)
            .dateOfService(DEFAULT_DATE_OF_SERVICE)
            .serviceTypeCodes(DEFAULT_SERVICE_TYPE_CODES)
            .status(DEFAULT_STATUS)
            .createdById(DEFAULT_CREATED_BY_ID)
            .createdByName(DEFAULT_CREATED_BY_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedById(DEFAULT_UPDATED_BY_ID)
            .updatedByName(DEFAULT_UPDATED_BY_NAME)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .memberElligibilityMasterUuid(DEFAULT_MEMBER_ELLIGIBILITY_MASTER_UUID);
        return memberElligibilityMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MemberElligibilityMaster createUpdatedEntity(EntityManager em) {
        MemberElligibilityMaster memberElligibilityMaster = new MemberElligibilityMaster()
            .salesOrderId(UPDATED_SALES_ORDER_ID)
            .elligibilityControlNumber(UPDATED_ELLIGIBILITY_CONTROL_NUMBER)
            .tradingPartnerServiceId(UPDATED_TRADING_PARTNER_SERVICE_ID)
            .tradingPartnerName(UPDATED_TRADING_PARTNER_NAME)
            .providerOrganizationName(UPDATED_PROVIDER_ORGANIZATION_NAME)
            .providerNpi(UPDATED_PROVIDER_NPI)
            .providerType(UPDATED_PROVIDER_TYPE)
            .subscriberFirstName(UPDATED_SUBSCRIBER_FIRST_NAME)
            .subscriberLastName(UPDATED_SUBSCRIBER_LAST_NAME)
            .subscriberMemberId(UPDATED_SUBSCRIBER_MEMBER_ID)
            .subscriberIdcard(UPDATED_SUBSCRIBER_IDCARD)
            .subscriberDob(UPDATED_SUBSCRIBER_DOB)
            .subscriberGender(UPDATED_SUBSCRIBER_GENDER)
            .subscriberPlanIssueDate(UPDATED_SUBSCRIBER_PLAN_ISSUE_DATE)
            .insuredFirstName(UPDATED_INSURED_FIRST_NAME)
            .insuredLastName(UPDATED_INSURED_LAST_NAME)
            .insuredGender(UPDATED_INSURED_GENDER)
            .insuredDob(UPDATED_INSURED_DOB)
            .insuredRelationshipwithSubscriber(UPDATED_INSURED_RELATIONSHIPWITH_SUBSCRIBER)
            .dateOfService(UPDATED_DATE_OF_SERVICE)
            .serviceTypeCodes(UPDATED_SERVICE_TYPE_CODES)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .memberElligibilityMasterUuid(UPDATED_MEMBER_ELLIGIBILITY_MASTER_UUID);
        return memberElligibilityMaster;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(MemberElligibilityMaster.class).block();
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
        memberElligibilityMaster = createEntity(em);
    }

    @Test
    void createMemberElligibilityMaster() throws Exception {
        int databaseSizeBeforeCreate = memberElligibilityMasterRepository.findAll().collectList().block().size();
        // Create the MemberElligibilityMaster
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(memberElligibilityMaster))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the MemberElligibilityMaster in the database
        List<MemberElligibilityMaster> memberElligibilityMasterList = memberElligibilityMasterRepository.findAll().collectList().block();
        assertThat(memberElligibilityMasterList).hasSize(databaseSizeBeforeCreate + 1);
        MemberElligibilityMaster testMemberElligibilityMaster = memberElligibilityMasterList.get(memberElligibilityMasterList.size() - 1);
        assertThat(testMemberElligibilityMaster.getSalesOrderId()).isEqualTo(DEFAULT_SALES_ORDER_ID);
        assertThat(testMemberElligibilityMaster.getElligibilityControlNumber()).isEqualTo(DEFAULT_ELLIGIBILITY_CONTROL_NUMBER);
        assertThat(testMemberElligibilityMaster.getTradingPartnerServiceId()).isEqualTo(DEFAULT_TRADING_PARTNER_SERVICE_ID);
        assertThat(testMemberElligibilityMaster.getTradingPartnerName()).isEqualTo(DEFAULT_TRADING_PARTNER_NAME);
        assertThat(testMemberElligibilityMaster.getProviderOrganizationName()).isEqualTo(DEFAULT_PROVIDER_ORGANIZATION_NAME);
        assertThat(testMemberElligibilityMaster.getProviderNpi()).isEqualTo(DEFAULT_PROVIDER_NPI);
        assertThat(testMemberElligibilityMaster.getProviderType()).isEqualTo(DEFAULT_PROVIDER_TYPE);
        assertThat(testMemberElligibilityMaster.getSubscriberFirstName()).isEqualTo(DEFAULT_SUBSCRIBER_FIRST_NAME);
        assertThat(testMemberElligibilityMaster.getSubscriberLastName()).isEqualTo(DEFAULT_SUBSCRIBER_LAST_NAME);
        assertThat(testMemberElligibilityMaster.getSubscriberMemberId()).isEqualTo(DEFAULT_SUBSCRIBER_MEMBER_ID);
        assertThat(testMemberElligibilityMaster.getSubscriberIdcard()).isEqualTo(DEFAULT_SUBSCRIBER_IDCARD);
        assertThat(testMemberElligibilityMaster.getSubscriberDob()).isEqualTo(DEFAULT_SUBSCRIBER_DOB);
        assertThat(testMemberElligibilityMaster.getSubscriberGender()).isEqualTo(DEFAULT_SUBSCRIBER_GENDER);
        assertThat(testMemberElligibilityMaster.getSubscriberPlanIssueDate()).isEqualTo(DEFAULT_SUBSCRIBER_PLAN_ISSUE_DATE);
        assertThat(testMemberElligibilityMaster.getInsuredFirstName()).isEqualTo(DEFAULT_INSURED_FIRST_NAME);
        assertThat(testMemberElligibilityMaster.getInsuredLastName()).isEqualTo(DEFAULT_INSURED_LAST_NAME);
        assertThat(testMemberElligibilityMaster.getInsuredGender()).isEqualTo(DEFAULT_INSURED_GENDER);
        assertThat(testMemberElligibilityMaster.getInsuredDob()).isEqualTo(DEFAULT_INSURED_DOB);
        assertThat(testMemberElligibilityMaster.getInsuredRelationshipwithSubscriber())
            .isEqualTo(DEFAULT_INSURED_RELATIONSHIPWITH_SUBSCRIBER);
        assertThat(testMemberElligibilityMaster.getDateOfService()).isEqualTo(DEFAULT_DATE_OF_SERVICE);
        assertThat(testMemberElligibilityMaster.getServiceTypeCodes()).isEqualTo(DEFAULT_SERVICE_TYPE_CODES);
        assertThat(testMemberElligibilityMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testMemberElligibilityMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testMemberElligibilityMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testMemberElligibilityMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testMemberElligibilityMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testMemberElligibilityMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testMemberElligibilityMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testMemberElligibilityMaster.getMemberElligibilityMasterUuid()).isEqualTo(DEFAULT_MEMBER_ELLIGIBILITY_MASTER_UUID);
    }

    @Test
    void createMemberElligibilityMasterWithExistingId() throws Exception {
        // Create the MemberElligibilityMaster with an existing ID
        memberElligibilityMaster.setClaimElligibilityMasterId(1L);

        int databaseSizeBeforeCreate = memberElligibilityMasterRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(memberElligibilityMaster))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the MemberElligibilityMaster in the database
        List<MemberElligibilityMaster> memberElligibilityMasterList = memberElligibilityMasterRepository.findAll().collectList().block();
        assertThat(memberElligibilityMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllMemberElligibilityMastersAsStream() {
        // Initialize the database
        memberElligibilityMasterRepository.save(memberElligibilityMaster).block();

        List<MemberElligibilityMaster> memberElligibilityMasterList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(MemberElligibilityMaster.class)
            .getResponseBody()
            .filter(memberElligibilityMaster::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(memberElligibilityMasterList).isNotNull();
        assertThat(memberElligibilityMasterList).hasSize(1);
        MemberElligibilityMaster testMemberElligibilityMaster = memberElligibilityMasterList.get(0);
        assertThat(testMemberElligibilityMaster.getSalesOrderId()).isEqualTo(DEFAULT_SALES_ORDER_ID);
        assertThat(testMemberElligibilityMaster.getElligibilityControlNumber()).isEqualTo(DEFAULT_ELLIGIBILITY_CONTROL_NUMBER);
        assertThat(testMemberElligibilityMaster.getTradingPartnerServiceId()).isEqualTo(DEFAULT_TRADING_PARTNER_SERVICE_ID);
        assertThat(testMemberElligibilityMaster.getTradingPartnerName()).isEqualTo(DEFAULT_TRADING_PARTNER_NAME);
        assertThat(testMemberElligibilityMaster.getProviderOrganizationName()).isEqualTo(DEFAULT_PROVIDER_ORGANIZATION_NAME);
        assertThat(testMemberElligibilityMaster.getProviderNpi()).isEqualTo(DEFAULT_PROVIDER_NPI);
        assertThat(testMemberElligibilityMaster.getProviderType()).isEqualTo(DEFAULT_PROVIDER_TYPE);
        assertThat(testMemberElligibilityMaster.getSubscriberFirstName()).isEqualTo(DEFAULT_SUBSCRIBER_FIRST_NAME);
        assertThat(testMemberElligibilityMaster.getSubscriberLastName()).isEqualTo(DEFAULT_SUBSCRIBER_LAST_NAME);
        assertThat(testMemberElligibilityMaster.getSubscriberMemberId()).isEqualTo(DEFAULT_SUBSCRIBER_MEMBER_ID);
        assertThat(testMemberElligibilityMaster.getSubscriberIdcard()).isEqualTo(DEFAULT_SUBSCRIBER_IDCARD);
        assertThat(testMemberElligibilityMaster.getSubscriberDob()).isEqualTo(DEFAULT_SUBSCRIBER_DOB);
        assertThat(testMemberElligibilityMaster.getSubscriberGender()).isEqualTo(DEFAULT_SUBSCRIBER_GENDER);
        assertThat(testMemberElligibilityMaster.getSubscriberPlanIssueDate()).isEqualTo(DEFAULT_SUBSCRIBER_PLAN_ISSUE_DATE);
        assertThat(testMemberElligibilityMaster.getInsuredFirstName()).isEqualTo(DEFAULT_INSURED_FIRST_NAME);
        assertThat(testMemberElligibilityMaster.getInsuredLastName()).isEqualTo(DEFAULT_INSURED_LAST_NAME);
        assertThat(testMemberElligibilityMaster.getInsuredGender()).isEqualTo(DEFAULT_INSURED_GENDER);
        assertThat(testMemberElligibilityMaster.getInsuredDob()).isEqualTo(DEFAULT_INSURED_DOB);
        assertThat(testMemberElligibilityMaster.getInsuredRelationshipwithSubscriber())
            .isEqualTo(DEFAULT_INSURED_RELATIONSHIPWITH_SUBSCRIBER);
        assertThat(testMemberElligibilityMaster.getDateOfService()).isEqualTo(DEFAULT_DATE_OF_SERVICE);
        assertThat(testMemberElligibilityMaster.getServiceTypeCodes()).isEqualTo(DEFAULT_SERVICE_TYPE_CODES);
        assertThat(testMemberElligibilityMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testMemberElligibilityMaster.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testMemberElligibilityMaster.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testMemberElligibilityMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testMemberElligibilityMaster.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testMemberElligibilityMaster.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testMemberElligibilityMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testMemberElligibilityMaster.getMemberElligibilityMasterUuid()).isEqualTo(DEFAULT_MEMBER_ELLIGIBILITY_MASTER_UUID);
    }

    @Test
    void getAllMemberElligibilityMasters() {
        // Initialize the database
        memberElligibilityMasterRepository.save(memberElligibilityMaster).block();

        // Get all the memberElligibilityMasterList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=claimElligibilityMasterId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].claimElligibilityMasterId")
            .value(hasItem(memberElligibilityMaster.getClaimElligibilityMasterId().intValue()))
            .jsonPath("$.[*].salesOrderId")
            .value(hasItem(DEFAULT_SALES_ORDER_ID.intValue()))
            .jsonPath("$.[*].elligibilityControlNumber")
            .value(hasItem(DEFAULT_ELLIGIBILITY_CONTROL_NUMBER))
            .jsonPath("$.[*].tradingPartnerServiceId")
            .value(hasItem(DEFAULT_TRADING_PARTNER_SERVICE_ID))
            .jsonPath("$.[*].tradingPartnerName")
            .value(hasItem(DEFAULT_TRADING_PARTNER_NAME))
            .jsonPath("$.[*].providerOrganizationName")
            .value(hasItem(DEFAULT_PROVIDER_ORGANIZATION_NAME))
            .jsonPath("$.[*].providerNpi")
            .value(hasItem(DEFAULT_PROVIDER_NPI))
            .jsonPath("$.[*].providerType")
            .value(hasItem(DEFAULT_PROVIDER_TYPE))
            .jsonPath("$.[*].subscriberFirstName")
            .value(hasItem(DEFAULT_SUBSCRIBER_FIRST_NAME))
            .jsonPath("$.[*].subscriberLastName")
            .value(hasItem(DEFAULT_SUBSCRIBER_LAST_NAME))
            .jsonPath("$.[*].subscriberMemberId")
            .value(hasItem(DEFAULT_SUBSCRIBER_MEMBER_ID))
            .jsonPath("$.[*].subscriberIdcard")
            .value(hasItem(DEFAULT_SUBSCRIBER_IDCARD))
            .jsonPath("$.[*].subscriberDob")
            .value(hasItem(DEFAULT_SUBSCRIBER_DOB.toString()))
            .jsonPath("$.[*].subscriberGender")
            .value(hasItem(DEFAULT_SUBSCRIBER_GENDER))
            .jsonPath("$.[*].subscriberPlanIssueDate")
            .value(hasItem(DEFAULT_SUBSCRIBER_PLAN_ISSUE_DATE.toString()))
            .jsonPath("$.[*].insuredFirstName")
            .value(hasItem(DEFAULT_INSURED_FIRST_NAME))
            .jsonPath("$.[*].insuredLastName")
            .value(hasItem(DEFAULT_INSURED_LAST_NAME))
            .jsonPath("$.[*].insuredGender")
            .value(hasItem(DEFAULT_INSURED_GENDER))
            .jsonPath("$.[*].insuredDob")
            .value(hasItem(DEFAULT_INSURED_DOB.toString()))
            .jsonPath("$.[*].insuredRelationshipwithSubscriber")
            .value(hasItem(DEFAULT_INSURED_RELATIONSHIPWITH_SUBSCRIBER))
            .jsonPath("$.[*].dateOfService")
            .value(hasItem(DEFAULT_DATE_OF_SERVICE.toString()))
            .jsonPath("$.[*].serviceTypeCodes")
            .value(hasItem(DEFAULT_SERVICE_TYPE_CODES))
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
            .jsonPath("$.[*].memberElligibilityMasterUuid")
            .value(hasItem(DEFAULT_MEMBER_ELLIGIBILITY_MASTER_UUID.toString()));
    }

    @Test
    void getMemberElligibilityMaster() {
        // Initialize the database
        memberElligibilityMasterRepository.save(memberElligibilityMaster).block();

        // Get the memberElligibilityMaster
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, memberElligibilityMaster.getClaimElligibilityMasterId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.claimElligibilityMasterId")
            .value(is(memberElligibilityMaster.getClaimElligibilityMasterId().intValue()))
            .jsonPath("$.salesOrderId")
            .value(is(DEFAULT_SALES_ORDER_ID.intValue()))
            .jsonPath("$.elligibilityControlNumber")
            .value(is(DEFAULT_ELLIGIBILITY_CONTROL_NUMBER))
            .jsonPath("$.tradingPartnerServiceId")
            .value(is(DEFAULT_TRADING_PARTNER_SERVICE_ID))
            .jsonPath("$.tradingPartnerName")
            .value(is(DEFAULT_TRADING_PARTNER_NAME))
            .jsonPath("$.providerOrganizationName")
            .value(is(DEFAULT_PROVIDER_ORGANIZATION_NAME))
            .jsonPath("$.providerNpi")
            .value(is(DEFAULT_PROVIDER_NPI))
            .jsonPath("$.providerType")
            .value(is(DEFAULT_PROVIDER_TYPE))
            .jsonPath("$.subscriberFirstName")
            .value(is(DEFAULT_SUBSCRIBER_FIRST_NAME))
            .jsonPath("$.subscriberLastName")
            .value(is(DEFAULT_SUBSCRIBER_LAST_NAME))
            .jsonPath("$.subscriberMemberId")
            .value(is(DEFAULT_SUBSCRIBER_MEMBER_ID))
            .jsonPath("$.subscriberIdcard")
            .value(is(DEFAULT_SUBSCRIBER_IDCARD))
            .jsonPath("$.subscriberDob")
            .value(is(DEFAULT_SUBSCRIBER_DOB.toString()))
            .jsonPath("$.subscriberGender")
            .value(is(DEFAULT_SUBSCRIBER_GENDER))
            .jsonPath("$.subscriberPlanIssueDate")
            .value(is(DEFAULT_SUBSCRIBER_PLAN_ISSUE_DATE.toString()))
            .jsonPath("$.insuredFirstName")
            .value(is(DEFAULT_INSURED_FIRST_NAME))
            .jsonPath("$.insuredLastName")
            .value(is(DEFAULT_INSURED_LAST_NAME))
            .jsonPath("$.insuredGender")
            .value(is(DEFAULT_INSURED_GENDER))
            .jsonPath("$.insuredDob")
            .value(is(DEFAULT_INSURED_DOB.toString()))
            .jsonPath("$.insuredRelationshipwithSubscriber")
            .value(is(DEFAULT_INSURED_RELATIONSHIPWITH_SUBSCRIBER))
            .jsonPath("$.dateOfService")
            .value(is(DEFAULT_DATE_OF_SERVICE.toString()))
            .jsonPath("$.serviceTypeCodes")
            .value(is(DEFAULT_SERVICE_TYPE_CODES))
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
            .jsonPath("$.memberElligibilityMasterUuid")
            .value(is(DEFAULT_MEMBER_ELLIGIBILITY_MASTER_UUID.toString()));
    }

    @Test
    void getNonExistingMemberElligibilityMaster() {
        // Get the memberElligibilityMaster
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingMemberElligibilityMaster() throws Exception {
        // Initialize the database
        memberElligibilityMasterRepository.save(memberElligibilityMaster).block();

        int databaseSizeBeforeUpdate = memberElligibilityMasterRepository.findAll().collectList().block().size();

        // Update the memberElligibilityMaster
        MemberElligibilityMaster updatedMemberElligibilityMaster = memberElligibilityMasterRepository
            .findById(memberElligibilityMaster.getClaimElligibilityMasterId())
            .block();
        updatedMemberElligibilityMaster
            .salesOrderId(UPDATED_SALES_ORDER_ID)
            .elligibilityControlNumber(UPDATED_ELLIGIBILITY_CONTROL_NUMBER)
            .tradingPartnerServiceId(UPDATED_TRADING_PARTNER_SERVICE_ID)
            .tradingPartnerName(UPDATED_TRADING_PARTNER_NAME)
            .providerOrganizationName(UPDATED_PROVIDER_ORGANIZATION_NAME)
            .providerNpi(UPDATED_PROVIDER_NPI)
            .providerType(UPDATED_PROVIDER_TYPE)
            .subscriberFirstName(UPDATED_SUBSCRIBER_FIRST_NAME)
            .subscriberLastName(UPDATED_SUBSCRIBER_LAST_NAME)
            .subscriberMemberId(UPDATED_SUBSCRIBER_MEMBER_ID)
            .subscriberIdcard(UPDATED_SUBSCRIBER_IDCARD)
            .subscriberDob(UPDATED_SUBSCRIBER_DOB)
            .subscriberGender(UPDATED_SUBSCRIBER_GENDER)
            .subscriberPlanIssueDate(UPDATED_SUBSCRIBER_PLAN_ISSUE_DATE)
            .insuredFirstName(UPDATED_INSURED_FIRST_NAME)
            .insuredLastName(UPDATED_INSURED_LAST_NAME)
            .insuredGender(UPDATED_INSURED_GENDER)
            .insuredDob(UPDATED_INSURED_DOB)
            .insuredRelationshipwithSubscriber(UPDATED_INSURED_RELATIONSHIPWITH_SUBSCRIBER)
            .dateOfService(UPDATED_DATE_OF_SERVICE)
            .serviceTypeCodes(UPDATED_SERVICE_TYPE_CODES)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .memberElligibilityMasterUuid(UPDATED_MEMBER_ELLIGIBILITY_MASTER_UUID);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedMemberElligibilityMaster.getClaimElligibilityMasterId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedMemberElligibilityMaster))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the MemberElligibilityMaster in the database
        List<MemberElligibilityMaster> memberElligibilityMasterList = memberElligibilityMasterRepository.findAll().collectList().block();
        assertThat(memberElligibilityMasterList).hasSize(databaseSizeBeforeUpdate);
        MemberElligibilityMaster testMemberElligibilityMaster = memberElligibilityMasterList.get(memberElligibilityMasterList.size() - 1);
        assertThat(testMemberElligibilityMaster.getSalesOrderId()).isEqualTo(UPDATED_SALES_ORDER_ID);
        assertThat(testMemberElligibilityMaster.getElligibilityControlNumber()).isEqualTo(UPDATED_ELLIGIBILITY_CONTROL_NUMBER);
        assertThat(testMemberElligibilityMaster.getTradingPartnerServiceId()).isEqualTo(UPDATED_TRADING_PARTNER_SERVICE_ID);
        assertThat(testMemberElligibilityMaster.getTradingPartnerName()).isEqualTo(UPDATED_TRADING_PARTNER_NAME);
        assertThat(testMemberElligibilityMaster.getProviderOrganizationName()).isEqualTo(UPDATED_PROVIDER_ORGANIZATION_NAME);
        assertThat(testMemberElligibilityMaster.getProviderNpi()).isEqualTo(UPDATED_PROVIDER_NPI);
        assertThat(testMemberElligibilityMaster.getProviderType()).isEqualTo(UPDATED_PROVIDER_TYPE);
        assertThat(testMemberElligibilityMaster.getSubscriberFirstName()).isEqualTo(UPDATED_SUBSCRIBER_FIRST_NAME);
        assertThat(testMemberElligibilityMaster.getSubscriberLastName()).isEqualTo(UPDATED_SUBSCRIBER_LAST_NAME);
        assertThat(testMemberElligibilityMaster.getSubscriberMemberId()).isEqualTo(UPDATED_SUBSCRIBER_MEMBER_ID);
        assertThat(testMemberElligibilityMaster.getSubscriberIdcard()).isEqualTo(UPDATED_SUBSCRIBER_IDCARD);
        assertThat(testMemberElligibilityMaster.getSubscriberDob()).isEqualTo(UPDATED_SUBSCRIBER_DOB);
        assertThat(testMemberElligibilityMaster.getSubscriberGender()).isEqualTo(UPDATED_SUBSCRIBER_GENDER);
        assertThat(testMemberElligibilityMaster.getSubscriberPlanIssueDate()).isEqualTo(UPDATED_SUBSCRIBER_PLAN_ISSUE_DATE);
        assertThat(testMemberElligibilityMaster.getInsuredFirstName()).isEqualTo(UPDATED_INSURED_FIRST_NAME);
        assertThat(testMemberElligibilityMaster.getInsuredLastName()).isEqualTo(UPDATED_INSURED_LAST_NAME);
        assertThat(testMemberElligibilityMaster.getInsuredGender()).isEqualTo(UPDATED_INSURED_GENDER);
        assertThat(testMemberElligibilityMaster.getInsuredDob()).isEqualTo(UPDATED_INSURED_DOB);
        assertThat(testMemberElligibilityMaster.getInsuredRelationshipwithSubscriber())
            .isEqualTo(UPDATED_INSURED_RELATIONSHIPWITH_SUBSCRIBER);
        assertThat(testMemberElligibilityMaster.getDateOfService()).isEqualTo(UPDATED_DATE_OF_SERVICE);
        assertThat(testMemberElligibilityMaster.getServiceTypeCodes()).isEqualTo(UPDATED_SERVICE_TYPE_CODES);
        assertThat(testMemberElligibilityMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testMemberElligibilityMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testMemberElligibilityMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testMemberElligibilityMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testMemberElligibilityMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testMemberElligibilityMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testMemberElligibilityMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testMemberElligibilityMaster.getMemberElligibilityMasterUuid()).isEqualTo(UPDATED_MEMBER_ELLIGIBILITY_MASTER_UUID);
    }

    @Test
    void putNonExistingMemberElligibilityMaster() throws Exception {
        int databaseSizeBeforeUpdate = memberElligibilityMasterRepository.findAll().collectList().block().size();
        memberElligibilityMaster.setClaimElligibilityMasterId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, memberElligibilityMaster.getClaimElligibilityMasterId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(memberElligibilityMaster))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the MemberElligibilityMaster in the database
        List<MemberElligibilityMaster> memberElligibilityMasterList = memberElligibilityMasterRepository.findAll().collectList().block();
        assertThat(memberElligibilityMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchMemberElligibilityMaster() throws Exception {
        int databaseSizeBeforeUpdate = memberElligibilityMasterRepository.findAll().collectList().block().size();
        memberElligibilityMaster.setClaimElligibilityMasterId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(memberElligibilityMaster))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the MemberElligibilityMaster in the database
        List<MemberElligibilityMaster> memberElligibilityMasterList = memberElligibilityMasterRepository.findAll().collectList().block();
        assertThat(memberElligibilityMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamMemberElligibilityMaster() throws Exception {
        int databaseSizeBeforeUpdate = memberElligibilityMasterRepository.findAll().collectList().block().size();
        memberElligibilityMaster.setClaimElligibilityMasterId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(memberElligibilityMaster))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the MemberElligibilityMaster in the database
        List<MemberElligibilityMaster> memberElligibilityMasterList = memberElligibilityMasterRepository.findAll().collectList().block();
        assertThat(memberElligibilityMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateMemberElligibilityMasterWithPatch() throws Exception {
        // Initialize the database
        memberElligibilityMasterRepository.save(memberElligibilityMaster).block();

        int databaseSizeBeforeUpdate = memberElligibilityMasterRepository.findAll().collectList().block().size();

        // Update the memberElligibilityMaster using partial update
        MemberElligibilityMaster partialUpdatedMemberElligibilityMaster = new MemberElligibilityMaster();
        partialUpdatedMemberElligibilityMaster.setClaimElligibilityMasterId(memberElligibilityMaster.getClaimElligibilityMasterId());

        partialUpdatedMemberElligibilityMaster
            .tradingPartnerServiceId(UPDATED_TRADING_PARTNER_SERVICE_ID)
            .providerOrganizationName(UPDATED_PROVIDER_ORGANIZATION_NAME)
            .providerNpi(UPDATED_PROVIDER_NPI)
            .providerType(UPDATED_PROVIDER_TYPE)
            .subscriberLastName(UPDATED_SUBSCRIBER_LAST_NAME)
            .subscriberIdcard(UPDATED_SUBSCRIBER_IDCARD)
            .subscriberDob(UPDATED_SUBSCRIBER_DOB)
            .subscriberGender(UPDATED_SUBSCRIBER_GENDER)
            .subscriberPlanIssueDate(UPDATED_SUBSCRIBER_PLAN_ISSUE_DATE)
            .insuredFirstName(UPDATED_INSURED_FIRST_NAME)
            .insuredDob(UPDATED_INSURED_DOB)
            .dateOfService(UPDATED_DATE_OF_SERVICE)
            .serviceTypeCodes(UPDATED_SERVICE_TYPE_CODES)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedMemberElligibilityMaster.getClaimElligibilityMasterId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedMemberElligibilityMaster))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the MemberElligibilityMaster in the database
        List<MemberElligibilityMaster> memberElligibilityMasterList = memberElligibilityMasterRepository.findAll().collectList().block();
        assertThat(memberElligibilityMasterList).hasSize(databaseSizeBeforeUpdate);
        MemberElligibilityMaster testMemberElligibilityMaster = memberElligibilityMasterList.get(memberElligibilityMasterList.size() - 1);
        assertThat(testMemberElligibilityMaster.getSalesOrderId()).isEqualTo(DEFAULT_SALES_ORDER_ID);
        assertThat(testMemberElligibilityMaster.getElligibilityControlNumber()).isEqualTo(DEFAULT_ELLIGIBILITY_CONTROL_NUMBER);
        assertThat(testMemberElligibilityMaster.getTradingPartnerServiceId()).isEqualTo(UPDATED_TRADING_PARTNER_SERVICE_ID);
        assertThat(testMemberElligibilityMaster.getTradingPartnerName()).isEqualTo(DEFAULT_TRADING_PARTNER_NAME);
        assertThat(testMemberElligibilityMaster.getProviderOrganizationName()).isEqualTo(UPDATED_PROVIDER_ORGANIZATION_NAME);
        assertThat(testMemberElligibilityMaster.getProviderNpi()).isEqualTo(UPDATED_PROVIDER_NPI);
        assertThat(testMemberElligibilityMaster.getProviderType()).isEqualTo(UPDATED_PROVIDER_TYPE);
        assertThat(testMemberElligibilityMaster.getSubscriberFirstName()).isEqualTo(DEFAULT_SUBSCRIBER_FIRST_NAME);
        assertThat(testMemberElligibilityMaster.getSubscriberLastName()).isEqualTo(UPDATED_SUBSCRIBER_LAST_NAME);
        assertThat(testMemberElligibilityMaster.getSubscriberMemberId()).isEqualTo(DEFAULT_SUBSCRIBER_MEMBER_ID);
        assertThat(testMemberElligibilityMaster.getSubscriberIdcard()).isEqualTo(UPDATED_SUBSCRIBER_IDCARD);
        assertThat(testMemberElligibilityMaster.getSubscriberDob()).isEqualTo(UPDATED_SUBSCRIBER_DOB);
        assertThat(testMemberElligibilityMaster.getSubscriberGender()).isEqualTo(UPDATED_SUBSCRIBER_GENDER);
        assertThat(testMemberElligibilityMaster.getSubscriberPlanIssueDate()).isEqualTo(UPDATED_SUBSCRIBER_PLAN_ISSUE_DATE);
        assertThat(testMemberElligibilityMaster.getInsuredFirstName()).isEqualTo(UPDATED_INSURED_FIRST_NAME);
        assertThat(testMemberElligibilityMaster.getInsuredLastName()).isEqualTo(DEFAULT_INSURED_LAST_NAME);
        assertThat(testMemberElligibilityMaster.getInsuredGender()).isEqualTo(DEFAULT_INSURED_GENDER);
        assertThat(testMemberElligibilityMaster.getInsuredDob()).isEqualTo(UPDATED_INSURED_DOB);
        assertThat(testMemberElligibilityMaster.getInsuredRelationshipwithSubscriber())
            .isEqualTo(DEFAULT_INSURED_RELATIONSHIPWITH_SUBSCRIBER);
        assertThat(testMemberElligibilityMaster.getDateOfService()).isEqualTo(UPDATED_DATE_OF_SERVICE);
        assertThat(testMemberElligibilityMaster.getServiceTypeCodes()).isEqualTo(UPDATED_SERVICE_TYPE_CODES);
        assertThat(testMemberElligibilityMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testMemberElligibilityMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testMemberElligibilityMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testMemberElligibilityMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testMemberElligibilityMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testMemberElligibilityMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testMemberElligibilityMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testMemberElligibilityMaster.getMemberElligibilityMasterUuid()).isEqualTo(DEFAULT_MEMBER_ELLIGIBILITY_MASTER_UUID);
    }

    @Test
    void fullUpdateMemberElligibilityMasterWithPatch() throws Exception {
        // Initialize the database
        memberElligibilityMasterRepository.save(memberElligibilityMaster).block();

        int databaseSizeBeforeUpdate = memberElligibilityMasterRepository.findAll().collectList().block().size();

        // Update the memberElligibilityMaster using partial update
        MemberElligibilityMaster partialUpdatedMemberElligibilityMaster = new MemberElligibilityMaster();
        partialUpdatedMemberElligibilityMaster.setClaimElligibilityMasterId(memberElligibilityMaster.getClaimElligibilityMasterId());

        partialUpdatedMemberElligibilityMaster
            .salesOrderId(UPDATED_SALES_ORDER_ID)
            .elligibilityControlNumber(UPDATED_ELLIGIBILITY_CONTROL_NUMBER)
            .tradingPartnerServiceId(UPDATED_TRADING_PARTNER_SERVICE_ID)
            .tradingPartnerName(UPDATED_TRADING_PARTNER_NAME)
            .providerOrganizationName(UPDATED_PROVIDER_ORGANIZATION_NAME)
            .providerNpi(UPDATED_PROVIDER_NPI)
            .providerType(UPDATED_PROVIDER_TYPE)
            .subscriberFirstName(UPDATED_SUBSCRIBER_FIRST_NAME)
            .subscriberLastName(UPDATED_SUBSCRIBER_LAST_NAME)
            .subscriberMemberId(UPDATED_SUBSCRIBER_MEMBER_ID)
            .subscriberIdcard(UPDATED_SUBSCRIBER_IDCARD)
            .subscriberDob(UPDATED_SUBSCRIBER_DOB)
            .subscriberGender(UPDATED_SUBSCRIBER_GENDER)
            .subscriberPlanIssueDate(UPDATED_SUBSCRIBER_PLAN_ISSUE_DATE)
            .insuredFirstName(UPDATED_INSURED_FIRST_NAME)
            .insuredLastName(UPDATED_INSURED_LAST_NAME)
            .insuredGender(UPDATED_INSURED_GENDER)
            .insuredDob(UPDATED_INSURED_DOB)
            .insuredRelationshipwithSubscriber(UPDATED_INSURED_RELATIONSHIPWITH_SUBSCRIBER)
            .dateOfService(UPDATED_DATE_OF_SERVICE)
            .serviceTypeCodes(UPDATED_SERVICE_TYPE_CODES)
            .status(UPDATED_STATUS)
            .createdById(UPDATED_CREATED_BY_ID)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedById(UPDATED_UPDATED_BY_ID)
            .updatedByName(UPDATED_UPDATED_BY_NAME)
            .updatedDate(UPDATED_UPDATED_DATE)
            .memberElligibilityMasterUuid(UPDATED_MEMBER_ELLIGIBILITY_MASTER_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedMemberElligibilityMaster.getClaimElligibilityMasterId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedMemberElligibilityMaster))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the MemberElligibilityMaster in the database
        List<MemberElligibilityMaster> memberElligibilityMasterList = memberElligibilityMasterRepository.findAll().collectList().block();
        assertThat(memberElligibilityMasterList).hasSize(databaseSizeBeforeUpdate);
        MemberElligibilityMaster testMemberElligibilityMaster = memberElligibilityMasterList.get(memberElligibilityMasterList.size() - 1);
        assertThat(testMemberElligibilityMaster.getSalesOrderId()).isEqualTo(UPDATED_SALES_ORDER_ID);
        assertThat(testMemberElligibilityMaster.getElligibilityControlNumber()).isEqualTo(UPDATED_ELLIGIBILITY_CONTROL_NUMBER);
        assertThat(testMemberElligibilityMaster.getTradingPartnerServiceId()).isEqualTo(UPDATED_TRADING_PARTNER_SERVICE_ID);
        assertThat(testMemberElligibilityMaster.getTradingPartnerName()).isEqualTo(UPDATED_TRADING_PARTNER_NAME);
        assertThat(testMemberElligibilityMaster.getProviderOrganizationName()).isEqualTo(UPDATED_PROVIDER_ORGANIZATION_NAME);
        assertThat(testMemberElligibilityMaster.getProviderNpi()).isEqualTo(UPDATED_PROVIDER_NPI);
        assertThat(testMemberElligibilityMaster.getProviderType()).isEqualTo(UPDATED_PROVIDER_TYPE);
        assertThat(testMemberElligibilityMaster.getSubscriberFirstName()).isEqualTo(UPDATED_SUBSCRIBER_FIRST_NAME);
        assertThat(testMemberElligibilityMaster.getSubscriberLastName()).isEqualTo(UPDATED_SUBSCRIBER_LAST_NAME);
        assertThat(testMemberElligibilityMaster.getSubscriberMemberId()).isEqualTo(UPDATED_SUBSCRIBER_MEMBER_ID);
        assertThat(testMemberElligibilityMaster.getSubscriberIdcard()).isEqualTo(UPDATED_SUBSCRIBER_IDCARD);
        assertThat(testMemberElligibilityMaster.getSubscriberDob()).isEqualTo(UPDATED_SUBSCRIBER_DOB);
        assertThat(testMemberElligibilityMaster.getSubscriberGender()).isEqualTo(UPDATED_SUBSCRIBER_GENDER);
        assertThat(testMemberElligibilityMaster.getSubscriberPlanIssueDate()).isEqualTo(UPDATED_SUBSCRIBER_PLAN_ISSUE_DATE);
        assertThat(testMemberElligibilityMaster.getInsuredFirstName()).isEqualTo(UPDATED_INSURED_FIRST_NAME);
        assertThat(testMemberElligibilityMaster.getInsuredLastName()).isEqualTo(UPDATED_INSURED_LAST_NAME);
        assertThat(testMemberElligibilityMaster.getInsuredGender()).isEqualTo(UPDATED_INSURED_GENDER);
        assertThat(testMemberElligibilityMaster.getInsuredDob()).isEqualTo(UPDATED_INSURED_DOB);
        assertThat(testMemberElligibilityMaster.getInsuredRelationshipwithSubscriber())
            .isEqualTo(UPDATED_INSURED_RELATIONSHIPWITH_SUBSCRIBER);
        assertThat(testMemberElligibilityMaster.getDateOfService()).isEqualTo(UPDATED_DATE_OF_SERVICE);
        assertThat(testMemberElligibilityMaster.getServiceTypeCodes()).isEqualTo(UPDATED_SERVICE_TYPE_CODES);
        assertThat(testMemberElligibilityMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testMemberElligibilityMaster.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testMemberElligibilityMaster.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testMemberElligibilityMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testMemberElligibilityMaster.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testMemberElligibilityMaster.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testMemberElligibilityMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testMemberElligibilityMaster.getMemberElligibilityMasterUuid()).isEqualTo(UPDATED_MEMBER_ELLIGIBILITY_MASTER_UUID);
    }

    @Test
    void patchNonExistingMemberElligibilityMaster() throws Exception {
        int databaseSizeBeforeUpdate = memberElligibilityMasterRepository.findAll().collectList().block().size();
        memberElligibilityMaster.setClaimElligibilityMasterId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, memberElligibilityMaster.getClaimElligibilityMasterId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(memberElligibilityMaster))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the MemberElligibilityMaster in the database
        List<MemberElligibilityMaster> memberElligibilityMasterList = memberElligibilityMasterRepository.findAll().collectList().block();
        assertThat(memberElligibilityMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchMemberElligibilityMaster() throws Exception {
        int databaseSizeBeforeUpdate = memberElligibilityMasterRepository.findAll().collectList().block().size();
        memberElligibilityMaster.setClaimElligibilityMasterId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(memberElligibilityMaster))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the MemberElligibilityMaster in the database
        List<MemberElligibilityMaster> memberElligibilityMasterList = memberElligibilityMasterRepository.findAll().collectList().block();
        assertThat(memberElligibilityMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamMemberElligibilityMaster() throws Exception {
        int databaseSizeBeforeUpdate = memberElligibilityMasterRepository.findAll().collectList().block().size();
        memberElligibilityMaster.setClaimElligibilityMasterId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(memberElligibilityMaster))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the MemberElligibilityMaster in the database
        List<MemberElligibilityMaster> memberElligibilityMasterList = memberElligibilityMasterRepository.findAll().collectList().block();
        assertThat(memberElligibilityMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteMemberElligibilityMaster() {
        // Initialize the database
        memberElligibilityMasterRepository.save(memberElligibilityMaster).block();

        int databaseSizeBeforeDelete = memberElligibilityMasterRepository.findAll().collectList().block().size();

        // Delete the memberElligibilityMaster
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, memberElligibilityMaster.getClaimElligibilityMasterId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<MemberElligibilityMaster> memberElligibilityMasterList = memberElligibilityMasterRepository.findAll().collectList().block();
        assertThat(memberElligibilityMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
