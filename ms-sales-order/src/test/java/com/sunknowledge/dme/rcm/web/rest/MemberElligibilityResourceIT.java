package com.sunknowledge.dme.rcm.web.rest;

import static com.sunknowledge.dme.rcm.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.MemberElligibility;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.repository.MemberElligibilityRepository;
import com.sunknowledge.dme.rcm.service.dto.MemberElligibilityDTO;
import com.sunknowledge.dme.rcm.service.mapper.MemberElligibilityMapper;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
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
 * Integration tests for the {@link MemberElligibilityResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class MemberElligibilityResourceIT {

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

    private static final ZonedDateTime DEFAULT_SUBSCRIBER_PLAN_ISSUE_DATE = ZonedDateTime.ofInstant(
        Instant.ofEpochMilli(0L),
        ZoneOffset.UTC
    );
    private static final ZonedDateTime UPDATED_SUBSCRIBER_PLAN_ISSUE_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

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

    private static final ZonedDateTime DEFAULT_DATE_OF_SERVICE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_OF_SERVICE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_SERVICE_TYPE_CODES = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_TYPE_CODES = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_STATUS = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_STATUS = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_CREATED_BY_ID = 1L;
    private static final Long UPDATED_CREATED_BY_ID = 2L;

    private static final String DEFAULT_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Long DEFAULT_UPDATED_BY_ID = 1L;
    private static final Long UPDATED_UPDATED_BY_ID = 2L;

    private static final String DEFAULT_UPDATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY_NAME = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_UPDATED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final UUID DEFAULT_MEMBER_ELLIGIBILITY_MASTER_UUID = UUID.randomUUID();
    private static final UUID UPDATED_MEMBER_ELLIGIBILITY_MASTER_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/member-elligibilities";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{claimElligibilityMasterId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private MemberElligibilityRepository memberElligibilityRepository;

    @Autowired
    private MemberElligibilityMapper memberElligibilityMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private MemberElligibility memberElligibility;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MemberElligibility createEntity(EntityManager em) {
        MemberElligibility memberElligibility = new MemberElligibility()
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
        return memberElligibility;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MemberElligibility createUpdatedEntity(EntityManager em) {
        MemberElligibility memberElligibility = new MemberElligibility()
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
        return memberElligibility;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(MemberElligibility.class).block();
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
        memberElligibility = createEntity(em);
    }

    @Test
    void createMemberElligibility() throws Exception {
        int databaseSizeBeforeCreate = memberElligibilityRepository.findAll().collectList().block().size();
        // Create the MemberElligibility
        MemberElligibilityDTO memberElligibilityDTO = memberElligibilityMapper.toDto(memberElligibility);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(memberElligibilityDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the MemberElligibility in the database
        List<MemberElligibility> memberElligibilityList = memberElligibilityRepository.findAll().collectList().block();
        assertThat(memberElligibilityList).hasSize(databaseSizeBeforeCreate + 1);
        MemberElligibility testMemberElligibility = memberElligibilityList.get(memberElligibilityList.size() - 1);
        assertThat(testMemberElligibility.getSalesOrderId()).isEqualTo(DEFAULT_SALES_ORDER_ID);
        assertThat(testMemberElligibility.getElligibilityControlNumber()).isEqualTo(DEFAULT_ELLIGIBILITY_CONTROL_NUMBER);
        assertThat(testMemberElligibility.getTradingPartnerServiceId()).isEqualTo(DEFAULT_TRADING_PARTNER_SERVICE_ID);
        assertThat(testMemberElligibility.getTradingPartnerName()).isEqualTo(DEFAULT_TRADING_PARTNER_NAME);
        assertThat(testMemberElligibility.getProviderOrganizationName()).isEqualTo(DEFAULT_PROVIDER_ORGANIZATION_NAME);
        assertThat(testMemberElligibility.getProviderNpi()).isEqualTo(DEFAULT_PROVIDER_NPI);
        assertThat(testMemberElligibility.getProviderType()).isEqualTo(DEFAULT_PROVIDER_TYPE);
        assertThat(testMemberElligibility.getSubscriberFirstName()).isEqualTo(DEFAULT_SUBSCRIBER_FIRST_NAME);
        assertThat(testMemberElligibility.getSubscriberLastName()).isEqualTo(DEFAULT_SUBSCRIBER_LAST_NAME);
        assertThat(testMemberElligibility.getSubscriberMemberId()).isEqualTo(DEFAULT_SUBSCRIBER_MEMBER_ID);
        assertThat(testMemberElligibility.getSubscriberIdcard()).isEqualTo(DEFAULT_SUBSCRIBER_IDCARD);
        assertThat(testMemberElligibility.getSubscriberDob()).isEqualTo(DEFAULT_SUBSCRIBER_DOB);
        assertThat(testMemberElligibility.getSubscriberGender()).isEqualTo(DEFAULT_SUBSCRIBER_GENDER);
        assertThat(testMemberElligibility.getSubscriberPlanIssueDate()).isEqualTo(DEFAULT_SUBSCRIBER_PLAN_ISSUE_DATE);
        assertThat(testMemberElligibility.getInsuredFirstName()).isEqualTo(DEFAULT_INSURED_FIRST_NAME);
        assertThat(testMemberElligibility.getInsuredLastName()).isEqualTo(DEFAULT_INSURED_LAST_NAME);
        assertThat(testMemberElligibility.getInsuredGender()).isEqualTo(DEFAULT_INSURED_GENDER);
        assertThat(testMemberElligibility.getInsuredDob()).isEqualTo(DEFAULT_INSURED_DOB);
        assertThat(testMemberElligibility.getInsuredRelationshipwithSubscriber()).isEqualTo(DEFAULT_INSURED_RELATIONSHIPWITH_SUBSCRIBER);
        assertThat(testMemberElligibility.getDateOfService()).isEqualTo(DEFAULT_DATE_OF_SERVICE);
        assertThat(testMemberElligibility.getServiceTypeCodes()).isEqualTo(DEFAULT_SERVICE_TYPE_CODES);
        assertThat(testMemberElligibility.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testMemberElligibility.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testMemberElligibility.getCreatedByName()).isEqualTo(DEFAULT_CREATED_BY_NAME);
        assertThat(testMemberElligibility.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testMemberElligibility.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testMemberElligibility.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testMemberElligibility.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testMemberElligibility.getMemberElligibilityMasterUuid()).isEqualTo(DEFAULT_MEMBER_ELLIGIBILITY_MASTER_UUID);
    }

    @Test
    void createMemberElligibilityWithExistingId() throws Exception {
        // Create the MemberElligibility with an existing ID
        memberElligibility.setClaimElligibilityMasterId(1L);
        MemberElligibilityDTO memberElligibilityDTO = memberElligibilityMapper.toDto(memberElligibility);

        int databaseSizeBeforeCreate = memberElligibilityRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(memberElligibilityDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the MemberElligibility in the database
        List<MemberElligibility> memberElligibilityList = memberElligibilityRepository.findAll().collectList().block();
        assertThat(memberElligibilityList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkSalesOrderIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = memberElligibilityRepository.findAll().collectList().block().size();
        // set the field null
        memberElligibility.setSalesOrderId(null);

        // Create the MemberElligibility, which fails.
        MemberElligibilityDTO memberElligibilityDTO = memberElligibilityMapper.toDto(memberElligibility);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(memberElligibilityDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<MemberElligibility> memberElligibilityList = memberElligibilityRepository.findAll().collectList().block();
        assertThat(memberElligibilityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllMemberElligibilities() {
        // Initialize the database
        memberElligibilityRepository.save(memberElligibility).block();

        // Get all the memberElligibilityList
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
            .value(hasItem(memberElligibility.getClaimElligibilityMasterId().intValue()))
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
            .value(hasItem(sameInstant(DEFAULT_SUBSCRIBER_PLAN_ISSUE_DATE)))
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
            .value(hasItem(sameInstant(DEFAULT_DATE_OF_SERVICE)))
            .jsonPath("$.[*].serviceTypeCodes")
            .value(hasItem(DEFAULT_SERVICE_TYPE_CODES))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS.toString()))
            .jsonPath("$.[*].createdById")
            .value(hasItem(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.[*].createdByName")
            .value(hasItem(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.[*].createdDate")
            .value(hasItem(sameInstant(DEFAULT_CREATED_DATE)))
            .jsonPath("$.[*].updatedById")
            .value(hasItem(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.[*].updatedByName")
            .value(hasItem(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.[*].updatedDate")
            .value(hasItem(sameInstant(DEFAULT_UPDATED_DATE)))
            .jsonPath("$.[*].memberElligibilityMasterUuid")
            .value(hasItem(DEFAULT_MEMBER_ELLIGIBILITY_MASTER_UUID.toString()));
    }

    @Test
    void getMemberElligibility() {
        // Initialize the database
        memberElligibilityRepository.save(memberElligibility).block();

        // Get the memberElligibility
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, memberElligibility.getClaimElligibilityMasterId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.claimElligibilityMasterId")
            .value(is(memberElligibility.getClaimElligibilityMasterId().intValue()))
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
            .value(is(sameInstant(DEFAULT_SUBSCRIBER_PLAN_ISSUE_DATE)))
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
            .value(is(sameInstant(DEFAULT_DATE_OF_SERVICE)))
            .jsonPath("$.serviceTypeCodes")
            .value(is(DEFAULT_SERVICE_TYPE_CODES))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS.toString()))
            .jsonPath("$.createdById")
            .value(is(DEFAULT_CREATED_BY_ID.intValue()))
            .jsonPath("$.createdByName")
            .value(is(DEFAULT_CREATED_BY_NAME))
            .jsonPath("$.createdDate")
            .value(is(sameInstant(DEFAULT_CREATED_DATE)))
            .jsonPath("$.updatedById")
            .value(is(DEFAULT_UPDATED_BY_ID.intValue()))
            .jsonPath("$.updatedByName")
            .value(is(DEFAULT_UPDATED_BY_NAME))
            .jsonPath("$.updatedDate")
            .value(is(sameInstant(DEFAULT_UPDATED_DATE)))
            .jsonPath("$.memberElligibilityMasterUuid")
            .value(is(DEFAULT_MEMBER_ELLIGIBILITY_MASTER_UUID.toString()));
    }

    @Test
    void getNonExistingMemberElligibility() {
        // Get the memberElligibility
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putNewMemberElligibility() throws Exception {
        // Initialize the database
        memberElligibilityRepository.save(memberElligibility).block();

        int databaseSizeBeforeUpdate = memberElligibilityRepository.findAll().collectList().block().size();

        // Update the memberElligibility
        MemberElligibility updatedMemberElligibility = memberElligibilityRepository
            .findById(memberElligibility.getClaimElligibilityMasterId())
            .block();
        updatedMemberElligibility
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
        MemberElligibilityDTO memberElligibilityDTO = memberElligibilityMapper.toDto(updatedMemberElligibility);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, memberElligibilityDTO.getClaimElligibilityMasterId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(memberElligibilityDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the MemberElligibility in the database
        List<MemberElligibility> memberElligibilityList = memberElligibilityRepository.findAll().collectList().block();
        assertThat(memberElligibilityList).hasSize(databaseSizeBeforeUpdate);
        MemberElligibility testMemberElligibility = memberElligibilityList.get(memberElligibilityList.size() - 1);
        assertThat(testMemberElligibility.getSalesOrderId()).isEqualTo(UPDATED_SALES_ORDER_ID);
        assertThat(testMemberElligibility.getElligibilityControlNumber()).isEqualTo(UPDATED_ELLIGIBILITY_CONTROL_NUMBER);
        assertThat(testMemberElligibility.getTradingPartnerServiceId()).isEqualTo(UPDATED_TRADING_PARTNER_SERVICE_ID);
        assertThat(testMemberElligibility.getTradingPartnerName()).isEqualTo(UPDATED_TRADING_PARTNER_NAME);
        assertThat(testMemberElligibility.getProviderOrganizationName()).isEqualTo(UPDATED_PROVIDER_ORGANIZATION_NAME);
        assertThat(testMemberElligibility.getProviderNpi()).isEqualTo(UPDATED_PROVIDER_NPI);
        assertThat(testMemberElligibility.getProviderType()).isEqualTo(UPDATED_PROVIDER_TYPE);
        assertThat(testMemberElligibility.getSubscriberFirstName()).isEqualTo(UPDATED_SUBSCRIBER_FIRST_NAME);
        assertThat(testMemberElligibility.getSubscriberLastName()).isEqualTo(UPDATED_SUBSCRIBER_LAST_NAME);
        assertThat(testMemberElligibility.getSubscriberMemberId()).isEqualTo(UPDATED_SUBSCRIBER_MEMBER_ID);
        assertThat(testMemberElligibility.getSubscriberIdcard()).isEqualTo(UPDATED_SUBSCRIBER_IDCARD);
        assertThat(testMemberElligibility.getSubscriberDob()).isEqualTo(UPDATED_SUBSCRIBER_DOB);
        assertThat(testMemberElligibility.getSubscriberGender()).isEqualTo(UPDATED_SUBSCRIBER_GENDER);
        assertThat(testMemberElligibility.getSubscriberPlanIssueDate()).isEqualTo(UPDATED_SUBSCRIBER_PLAN_ISSUE_DATE);
        assertThat(testMemberElligibility.getInsuredFirstName()).isEqualTo(UPDATED_INSURED_FIRST_NAME);
        assertThat(testMemberElligibility.getInsuredLastName()).isEqualTo(UPDATED_INSURED_LAST_NAME);
        assertThat(testMemberElligibility.getInsuredGender()).isEqualTo(UPDATED_INSURED_GENDER);
        assertThat(testMemberElligibility.getInsuredDob()).isEqualTo(UPDATED_INSURED_DOB);
        assertThat(testMemberElligibility.getInsuredRelationshipwithSubscriber()).isEqualTo(UPDATED_INSURED_RELATIONSHIPWITH_SUBSCRIBER);
        assertThat(testMemberElligibility.getDateOfService()).isEqualTo(UPDATED_DATE_OF_SERVICE);
        assertThat(testMemberElligibility.getServiceTypeCodes()).isEqualTo(UPDATED_SERVICE_TYPE_CODES);
        assertThat(testMemberElligibility.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testMemberElligibility.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testMemberElligibility.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testMemberElligibility.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testMemberElligibility.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testMemberElligibility.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testMemberElligibility.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testMemberElligibility.getMemberElligibilityMasterUuid()).isEqualTo(UPDATED_MEMBER_ELLIGIBILITY_MASTER_UUID);
    }

    @Test
    void putNonExistingMemberElligibility() throws Exception {
        int databaseSizeBeforeUpdate = memberElligibilityRepository.findAll().collectList().block().size();
        memberElligibility.setClaimElligibilityMasterId(count.incrementAndGet());

        // Create the MemberElligibility
        MemberElligibilityDTO memberElligibilityDTO = memberElligibilityMapper.toDto(memberElligibility);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, memberElligibilityDTO.getClaimElligibilityMasterId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(memberElligibilityDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the MemberElligibility in the database
        List<MemberElligibility> memberElligibilityList = memberElligibilityRepository.findAll().collectList().block();
        assertThat(memberElligibilityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchMemberElligibility() throws Exception {
        int databaseSizeBeforeUpdate = memberElligibilityRepository.findAll().collectList().block().size();
        memberElligibility.setClaimElligibilityMasterId(count.incrementAndGet());

        // Create the MemberElligibility
        MemberElligibilityDTO memberElligibilityDTO = memberElligibilityMapper.toDto(memberElligibility);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(memberElligibilityDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the MemberElligibility in the database
        List<MemberElligibility> memberElligibilityList = memberElligibilityRepository.findAll().collectList().block();
        assertThat(memberElligibilityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamMemberElligibility() throws Exception {
        int databaseSizeBeforeUpdate = memberElligibilityRepository.findAll().collectList().block().size();
        memberElligibility.setClaimElligibilityMasterId(count.incrementAndGet());

        // Create the MemberElligibility
        MemberElligibilityDTO memberElligibilityDTO = memberElligibilityMapper.toDto(memberElligibility);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(memberElligibilityDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the MemberElligibility in the database
        List<MemberElligibility> memberElligibilityList = memberElligibilityRepository.findAll().collectList().block();
        assertThat(memberElligibilityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateMemberElligibilityWithPatch() throws Exception {
        // Initialize the database
        memberElligibilityRepository.save(memberElligibility).block();

        int databaseSizeBeforeUpdate = memberElligibilityRepository.findAll().collectList().block().size();

        // Update the memberElligibility using partial update
        MemberElligibility partialUpdatedMemberElligibility = new MemberElligibility();
        partialUpdatedMemberElligibility.setClaimElligibilityMasterId(memberElligibility.getClaimElligibilityMasterId());

        partialUpdatedMemberElligibility
            .elligibilityControlNumber(UPDATED_ELLIGIBILITY_CONTROL_NUMBER)
            .providerNpi(UPDATED_PROVIDER_NPI)
            .providerType(UPDATED_PROVIDER_TYPE)
            .subscriberFirstName(UPDATED_SUBSCRIBER_FIRST_NAME)
            .subscriberDob(UPDATED_SUBSCRIBER_DOB)
            .subscriberPlanIssueDate(UPDATED_SUBSCRIBER_PLAN_ISSUE_DATE)
            .insuredFirstName(UPDATED_INSURED_FIRST_NAME)
            .dateOfService(UPDATED_DATE_OF_SERVICE)
            .serviceTypeCodes(UPDATED_SERVICE_TYPE_CODES)
            .createdByName(UPDATED_CREATED_BY_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedMemberElligibility.getClaimElligibilityMasterId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedMemberElligibility))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the MemberElligibility in the database
        List<MemberElligibility> memberElligibilityList = memberElligibilityRepository.findAll().collectList().block();
        assertThat(memberElligibilityList).hasSize(databaseSizeBeforeUpdate);
        MemberElligibility testMemberElligibility = memberElligibilityList.get(memberElligibilityList.size() - 1);
        assertThat(testMemberElligibility.getSalesOrderId()).isEqualTo(DEFAULT_SALES_ORDER_ID);
        assertThat(testMemberElligibility.getElligibilityControlNumber()).isEqualTo(UPDATED_ELLIGIBILITY_CONTROL_NUMBER);
        assertThat(testMemberElligibility.getTradingPartnerServiceId()).isEqualTo(DEFAULT_TRADING_PARTNER_SERVICE_ID);
        assertThat(testMemberElligibility.getTradingPartnerName()).isEqualTo(DEFAULT_TRADING_PARTNER_NAME);
        assertThat(testMemberElligibility.getProviderOrganizationName()).isEqualTo(DEFAULT_PROVIDER_ORGANIZATION_NAME);
        assertThat(testMemberElligibility.getProviderNpi()).isEqualTo(UPDATED_PROVIDER_NPI);
        assertThat(testMemberElligibility.getProviderType()).isEqualTo(UPDATED_PROVIDER_TYPE);
        assertThat(testMemberElligibility.getSubscriberFirstName()).isEqualTo(UPDATED_SUBSCRIBER_FIRST_NAME);
        assertThat(testMemberElligibility.getSubscriberLastName()).isEqualTo(DEFAULT_SUBSCRIBER_LAST_NAME);
        assertThat(testMemberElligibility.getSubscriberMemberId()).isEqualTo(DEFAULT_SUBSCRIBER_MEMBER_ID);
        assertThat(testMemberElligibility.getSubscriberIdcard()).isEqualTo(DEFAULT_SUBSCRIBER_IDCARD);
        assertThat(testMemberElligibility.getSubscriberDob()).isEqualTo(UPDATED_SUBSCRIBER_DOB);
        assertThat(testMemberElligibility.getSubscriberGender()).isEqualTo(DEFAULT_SUBSCRIBER_GENDER);
        assertThat(testMemberElligibility.getSubscriberPlanIssueDate()).isEqualTo(UPDATED_SUBSCRIBER_PLAN_ISSUE_DATE);
        assertThat(testMemberElligibility.getInsuredFirstName()).isEqualTo(UPDATED_INSURED_FIRST_NAME);
        assertThat(testMemberElligibility.getInsuredLastName()).isEqualTo(DEFAULT_INSURED_LAST_NAME);
        assertThat(testMemberElligibility.getInsuredGender()).isEqualTo(DEFAULT_INSURED_GENDER);
        assertThat(testMemberElligibility.getInsuredDob()).isEqualTo(DEFAULT_INSURED_DOB);
        assertThat(testMemberElligibility.getInsuredRelationshipwithSubscriber()).isEqualTo(DEFAULT_INSURED_RELATIONSHIPWITH_SUBSCRIBER);
        assertThat(testMemberElligibility.getDateOfService()).isEqualTo(UPDATED_DATE_OF_SERVICE);
        assertThat(testMemberElligibility.getServiceTypeCodes()).isEqualTo(UPDATED_SERVICE_TYPE_CODES);
        assertThat(testMemberElligibility.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testMemberElligibility.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testMemberElligibility.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testMemberElligibility.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testMemberElligibility.getUpdatedById()).isEqualTo(DEFAULT_UPDATED_BY_ID);
        assertThat(testMemberElligibility.getUpdatedByName()).isEqualTo(DEFAULT_UPDATED_BY_NAME);
        assertThat(testMemberElligibility.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testMemberElligibility.getMemberElligibilityMasterUuid()).isEqualTo(DEFAULT_MEMBER_ELLIGIBILITY_MASTER_UUID);
    }

    @Test
    void fullUpdateMemberElligibilityWithPatch() throws Exception {
        // Initialize the database
        memberElligibilityRepository.save(memberElligibility).block();

        int databaseSizeBeforeUpdate = memberElligibilityRepository.findAll().collectList().block().size();

        // Update the memberElligibility using partial update
        MemberElligibility partialUpdatedMemberElligibility = new MemberElligibility();
        partialUpdatedMemberElligibility.setClaimElligibilityMasterId(memberElligibility.getClaimElligibilityMasterId());

        partialUpdatedMemberElligibility
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
            .uri(ENTITY_API_URL_ID, partialUpdatedMemberElligibility.getClaimElligibilityMasterId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedMemberElligibility))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the MemberElligibility in the database
        List<MemberElligibility> memberElligibilityList = memberElligibilityRepository.findAll().collectList().block();
        assertThat(memberElligibilityList).hasSize(databaseSizeBeforeUpdate);
        MemberElligibility testMemberElligibility = memberElligibilityList.get(memberElligibilityList.size() - 1);
        assertThat(testMemberElligibility.getSalesOrderId()).isEqualTo(UPDATED_SALES_ORDER_ID);
        assertThat(testMemberElligibility.getElligibilityControlNumber()).isEqualTo(UPDATED_ELLIGIBILITY_CONTROL_NUMBER);
        assertThat(testMemberElligibility.getTradingPartnerServiceId()).isEqualTo(UPDATED_TRADING_PARTNER_SERVICE_ID);
        assertThat(testMemberElligibility.getTradingPartnerName()).isEqualTo(UPDATED_TRADING_PARTNER_NAME);
        assertThat(testMemberElligibility.getProviderOrganizationName()).isEqualTo(UPDATED_PROVIDER_ORGANIZATION_NAME);
        assertThat(testMemberElligibility.getProviderNpi()).isEqualTo(UPDATED_PROVIDER_NPI);
        assertThat(testMemberElligibility.getProviderType()).isEqualTo(UPDATED_PROVIDER_TYPE);
        assertThat(testMemberElligibility.getSubscriberFirstName()).isEqualTo(UPDATED_SUBSCRIBER_FIRST_NAME);
        assertThat(testMemberElligibility.getSubscriberLastName()).isEqualTo(UPDATED_SUBSCRIBER_LAST_NAME);
        assertThat(testMemberElligibility.getSubscriberMemberId()).isEqualTo(UPDATED_SUBSCRIBER_MEMBER_ID);
        assertThat(testMemberElligibility.getSubscriberIdcard()).isEqualTo(UPDATED_SUBSCRIBER_IDCARD);
        assertThat(testMemberElligibility.getSubscriberDob()).isEqualTo(UPDATED_SUBSCRIBER_DOB);
        assertThat(testMemberElligibility.getSubscriberGender()).isEqualTo(UPDATED_SUBSCRIBER_GENDER);
        assertThat(testMemberElligibility.getSubscriberPlanIssueDate()).isEqualTo(UPDATED_SUBSCRIBER_PLAN_ISSUE_DATE);
        assertThat(testMemberElligibility.getInsuredFirstName()).isEqualTo(UPDATED_INSURED_FIRST_NAME);
        assertThat(testMemberElligibility.getInsuredLastName()).isEqualTo(UPDATED_INSURED_LAST_NAME);
        assertThat(testMemberElligibility.getInsuredGender()).isEqualTo(UPDATED_INSURED_GENDER);
        assertThat(testMemberElligibility.getInsuredDob()).isEqualTo(UPDATED_INSURED_DOB);
        assertThat(testMemberElligibility.getInsuredRelationshipwithSubscriber()).isEqualTo(UPDATED_INSURED_RELATIONSHIPWITH_SUBSCRIBER);
        assertThat(testMemberElligibility.getDateOfService()).isEqualTo(UPDATED_DATE_OF_SERVICE);
        assertThat(testMemberElligibility.getServiceTypeCodes()).isEqualTo(UPDATED_SERVICE_TYPE_CODES);
        assertThat(testMemberElligibility.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testMemberElligibility.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testMemberElligibility.getCreatedByName()).isEqualTo(UPDATED_CREATED_BY_NAME);
        assertThat(testMemberElligibility.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testMemberElligibility.getUpdatedById()).isEqualTo(UPDATED_UPDATED_BY_ID);
        assertThat(testMemberElligibility.getUpdatedByName()).isEqualTo(UPDATED_UPDATED_BY_NAME);
        assertThat(testMemberElligibility.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testMemberElligibility.getMemberElligibilityMasterUuid()).isEqualTo(UPDATED_MEMBER_ELLIGIBILITY_MASTER_UUID);
    }

    @Test
    void patchNonExistingMemberElligibility() throws Exception {
        int databaseSizeBeforeUpdate = memberElligibilityRepository.findAll().collectList().block().size();
        memberElligibility.setClaimElligibilityMasterId(count.incrementAndGet());

        // Create the MemberElligibility
        MemberElligibilityDTO memberElligibilityDTO = memberElligibilityMapper.toDto(memberElligibility);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, memberElligibilityDTO.getClaimElligibilityMasterId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(memberElligibilityDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the MemberElligibility in the database
        List<MemberElligibility> memberElligibilityList = memberElligibilityRepository.findAll().collectList().block();
        assertThat(memberElligibilityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchMemberElligibility() throws Exception {
        int databaseSizeBeforeUpdate = memberElligibilityRepository.findAll().collectList().block().size();
        memberElligibility.setClaimElligibilityMasterId(count.incrementAndGet());

        // Create the MemberElligibility
        MemberElligibilityDTO memberElligibilityDTO = memberElligibilityMapper.toDto(memberElligibility);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(memberElligibilityDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the MemberElligibility in the database
        List<MemberElligibility> memberElligibilityList = memberElligibilityRepository.findAll().collectList().block();
        assertThat(memberElligibilityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamMemberElligibility() throws Exception {
        int databaseSizeBeforeUpdate = memberElligibilityRepository.findAll().collectList().block().size();
        memberElligibility.setClaimElligibilityMasterId(count.incrementAndGet());

        // Create the MemberElligibility
        MemberElligibilityDTO memberElligibilityDTO = memberElligibilityMapper.toDto(memberElligibility);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(memberElligibilityDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the MemberElligibility in the database
        List<MemberElligibility> memberElligibilityList = memberElligibilityRepository.findAll().collectList().block();
        assertThat(memberElligibilityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteMemberElligibility() {
        // Initialize the database
        memberElligibilityRepository.save(memberElligibility).block();

        int databaseSizeBeforeDelete = memberElligibilityRepository.findAll().collectList().block().size();

        // Delete the memberElligibility
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, memberElligibility.getClaimElligibilityMasterId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<MemberElligibility> memberElligibilityList = memberElligibilityRepository.findAll().collectList().block();
        assertThat(memberElligibilityList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
