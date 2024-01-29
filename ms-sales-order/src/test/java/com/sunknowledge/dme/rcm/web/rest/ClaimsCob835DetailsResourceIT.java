package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ClaimsCob835Details;
import com.sunknowledge.dme.rcm.repository.ClaimsCob835DetailsRepository;
import com.sunknowledge.dme.rcm.repository.EntityManager;
import com.sunknowledge.dme.rcm.service.dto.ClaimsCob835DetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.ClaimsCob835DetailsMapper;
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
 * Integration tests for the {@link ClaimsCob835DetailsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class ClaimsCob835DetailsResourceIT {

    private static final LocalDate DEFAULT_SERVICE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SERVICE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ADJUDICATED_PROCEDURE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ADJUDICATED_PROCEDURE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ADJUDICATED_PROCEDURE_MODIFIER_CODES = "AAAAAAAAAA";
    private static final String UPDATED_ADJUDICATED_PROCEDURE_MODIFIER_CODES = "BBBBBBBBBB";

    private static final Double DEFAULT_CHARGE_AMOUNT = 1D;
    private static final Double UPDATED_CHARGE_AMOUNT = 2D;

    private static final Double DEFAULT_ALLOWED_AMOUNT = 1D;
    private static final Double UPDATED_ALLOWED_AMOUNT = 2D;

    private static final String DEFAULT_ADJUSTMENT_PR_CODE_1 = "AAAAAAAAAA";
    private static final String UPDATED_ADJUSTMENT_PR_CODE_1 = "BBBBBBBBBB";

    private static final Double DEFAULT_ADJUSTMENT_PR_CODE_1_AMOUNT = 1D;
    private static final Double UPDATED_ADJUSTMENT_PR_CODE_1_AMOUNT = 2D;

    private static final String DEFAULT_ADJUSTMENT_PR_CODE_2 = "AAAAAAAAAA";
    private static final String UPDATED_ADJUSTMENT_PR_CODE_2 = "BBBBBBBBBB";

    private static final Double DEFAULT_ADJUSTMENT_PR_CODE_2_AMOUNT = 1D;
    private static final Double UPDATED_ADJUSTMENT_PR_CODE_2_AMOUNT = 2D;

    private static final String DEFAULT_ADJUSTMENT_PR_CODE_3 = "AAAAAAAAAA";
    private static final String UPDATED_ADJUSTMENT_PR_CODE_3 = "BBBBBBBBBB";

    private static final Double DEFAULT_ADJUSTMENT_PR_CODE_3_AMOUNT = 1D;
    private static final Double UPDATED_ADJUSTMENT_PR_CODE_3_AMOUNT = 2D;

    private static final String DEFAULT_ADJUSTMENT_PR_CODE_4 = "AAAAAAAAAA";
    private static final String UPDATED_ADJUSTMENT_PR_CODE_4 = "BBBBBBBBBB";

    private static final Double DEFAULT_ADJUSTMENT_PR_CODE_4_AMOUNT = 1D;
    private static final Double UPDATED_ADJUSTMENT_PR_CODE_4_AMOUNT = 2D;

    private static final String DEFAULT_ADJUSTMENT_CO_CODE_1 = "AAAAAAAAAA";
    private static final String UPDATED_ADJUSTMENT_CO_CODE_1 = "BBBBBBBBBB";

    private static final Double DEFAULT_ADJUSTMENT_CO_CODE_1_AMOUNT = 1D;
    private static final Double UPDATED_ADJUSTMENT_CO_CODE_1_AMOUNT = 2D;

    private static final String DEFAULT_ADJUSTMENT_CO_CODE_2 = "AAAAAAAAAA";
    private static final String UPDATED_ADJUSTMENT_CO_CODE_2 = "BBBBBBBBBB";

    private static final Double DEFAULT_ADJUSTMENT_CO_CODE_2_AMOUNT = 1D;
    private static final Double UPDATED_ADJUSTMENT_CO_CODE_2_AMOUNT = 2D;

    private static final String DEFAULT_ADJUSTMENT_CO_CODE_3 = "AAAAAAAAAA";
    private static final String UPDATED_ADJUSTMENT_CO_CODE_3 = "BBBBBBBBBB";

    private static final Double DEFAULT_ADJUSTMENT_CO_CODE_3_AMOUNT = 1D;
    private static final Double UPDATED_ADJUSTMENT_CO_CODE_3_AMOUNT = 2D;

    private static final String DEFAULT_ADJUSTMENT_CO_CODE_4 = "AAAAAAAAAA";
    private static final String UPDATED_ADJUSTMENT_CO_CODE_4 = "BBBBBBBBBB";

    private static final Double DEFAULT_ADJUSTMENT_CO_CODE_4_AMOUNT = 1D;
    private static final Double UPDATED_ADJUSTMENT_CO_CODE_4_AMOUNT = 2D;

    private static final String DEFAULT_ADJUSTMENT_CR_CODE_1 = "AAAAAAAAAA";
    private static final String UPDATED_ADJUSTMENT_CR_CODE_1 = "BBBBBBBBBB";

    private static final Double DEFAULT_ADJUSTMENT_CR_CODE_1_AMOUNT = 1D;
    private static final Double UPDATED_ADJUSTMENT_CR_CODE_1_AMOUNT = 2D;

    private static final String DEFAULT_ADJUSTMENT_CR_CODE_2 = "AAAAAAAAAA";
    private static final String UPDATED_ADJUSTMENT_CR_CODE_2 = "BBBBBBBBBB";

    private static final Double DEFAULT_ADJUSTMENT_CR_CODE_2_AMOUNT = 1D;
    private static final Double UPDATED_ADJUSTMENT_CR_CODE_2_AMOUNT = 2D;

    private static final String DEFAULT_ADJUSTMENT_CR_CODE_3 = "AAAAAAAAAA";
    private static final String UPDATED_ADJUSTMENT_CR_CODE_3 = "BBBBBBBBBB";

    private static final Double DEFAULT_ADJUSTMENT_CR_CODE_3_AMOUNT = 1D;
    private static final Double UPDATED_ADJUSTMENT_CR_CODE_3_AMOUNT = 2D;

    private static final String DEFAULT_ADJUSTMENT_CR_CODE_4 = "AAAAAAAAAA";
    private static final String UPDATED_ADJUSTMENT_CR_CODE_4 = "BBBBBBBBBB";

    private static final Double DEFAULT_ADJUSTMENT_CR_CODE_4_AMOUNT = 1D;
    private static final Double UPDATED_ADJUSTMENT_CR_CODE_4_AMOUNT = 2D;

    private static final String DEFAULT_ADJUSTMENT_OA_CODE_1 = "AAAAAAAAAA";
    private static final String UPDATED_ADJUSTMENT_OA_CODE_1 = "BBBBBBBBBB";

    private static final Double DEFAULT_ADJUSTMENT_OA_CODE_1_AMOUNT = 1D;
    private static final Double UPDATED_ADJUSTMENT_OA_CODE_1_AMOUNT = 2D;

    private static final String DEFAULT_ADJUSTMENT_OA_CODE_2 = "AAAAAAAAAA";
    private static final String UPDATED_ADJUSTMENT_OA_CODE_2 = "BBBBBBBBBB";

    private static final Double DEFAULT_ADJUSTMENT_OA_CODE_2_AMOUNT = 1D;
    private static final Double UPDATED_ADJUSTMENT_OA_CODE_2_AMOUNT = 2D;

    private static final String DEFAULT_ADJUSTMENT_OA_CODE_3 = "AAAAAAAAAA";
    private static final String UPDATED_ADJUSTMENT_OA_CODE_3 = "BBBBBBBBBB";

    private static final Double DEFAULT_ADJUSTMENT_OA_CODE_3_AMOUNT = 1D;
    private static final Double UPDATED_ADJUSTMENT_OA_CODE_3_AMOUNT = 2D;

    private static final String DEFAULT_ADJUSTMENT_OA_CODE_4 = "AAAAAAAAAA";
    private static final String UPDATED_ADJUSTMENT_OA_CODE_4 = "BBBBBBBBBB";

    private static final Double DEFAULT_ADJUSTMENT_OA_CODE_4_AMOUNT = 1D;
    private static final Double UPDATED_ADJUSTMENT_OA_CODE_4_AMOUNT = 2D;

    private static final String DEFAULT_ADJUSTMENT_PI_CODE_1 = "AAAAAAAAAA";
    private static final String UPDATED_ADJUSTMENT_PI_CODE_1 = "BBBBBBBBBB";

    private static final Double DEFAULT_ADJUSTMENT_PI_CODE_1_AMOUNT = 1D;
    private static final Double UPDATED_ADJUSTMENT_PI_CODE_1_AMOUNT = 2D;

    private static final String DEFAULT_ADJUSTMENT_PI_CODE_2 = "AAAAAAAAAA";
    private static final String UPDATED_ADJUSTMENT_PI_CODE_2 = "BBBBBBBBBB";

    private static final Double DEFAULT_ADJUSTMENT_PI_CODE_2_AMOUNT = 1D;
    private static final Double UPDATED_ADJUSTMENT_PI_CODE_2_AMOUNT = 2D;

    private static final String DEFAULT_ADJUSTMENT_PI_CODE_3 = "AAAAAAAAAA";
    private static final String UPDATED_ADJUSTMENT_PI_CODE_3 = "BBBBBBBBBB";

    private static final Double DEFAULT_ADJUSTMENT_PI_CODE_3_AMOUNT = 1D;
    private static final Double UPDATED_ADJUSTMENT_PI_CODE_3_AMOUNT = 2D;

    private static final String DEFAULT_ADJUSTMENT_PI_CODE_4 = "AAAAAAAAAA";
    private static final String UPDATED_ADJUSTMENT_PI_CODE_4 = "BBBBBBBBBB";

    private static final Double DEFAULT_ADJUSTMENT_PI_CODE_4_AMOUNT = 1D;
    private static final Double UPDATED_ADJUSTMENT_PI_CODE_4_AMOUNT = 2D;

    private static final Double DEFAULT_PROVIDER_PAYMENT_AMOUNT = 1D;
    private static final Double UPDATED_PROVIDER_PAYMENT_AMOUNT = 2D;

    private static final Long DEFAULT_CLAIM_COB_835_MASTER_ID = 1L;
    private static final Long UPDATED_CLAIM_COB_835_MASTER_ID = 2L;

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SERVICE_DATE_TO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SERVICE_DATE_TO = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_UNIT_COUNT = 1L;
    private static final Long UPDATED_UNIT_COUNT = 2L;

    private static final UUID DEFAULT_CLAIMS_COB_835_DETAILS_UUID = UUID.randomUUID();
    private static final UUID UPDATED_CLAIMS_COB_835_DETAILS_UUID = UUID.randomUUID();

    private static final String ENTITY_API_URL = "/api/claims-cob-835-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{claimCob835DetailId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ClaimsCob835DetailsRepository claimsCob835DetailsRepository;

    @Autowired
    private ClaimsCob835DetailsMapper claimsCob835DetailsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private ClaimsCob835Details claimsCob835Details;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClaimsCob835Details createEntity(EntityManager em) {
        ClaimsCob835Details claimsCob835Details = new ClaimsCob835Details()
            .serviceDate(DEFAULT_SERVICE_DATE)
            .adjudicatedProcedureCode(DEFAULT_ADJUDICATED_PROCEDURE_CODE)
            .adjudicatedProcedureModifierCodes(DEFAULT_ADJUDICATED_PROCEDURE_MODIFIER_CODES)
            .chargeAmount(DEFAULT_CHARGE_AMOUNT)
            .allowedAmount(DEFAULT_ALLOWED_AMOUNT)
            .adjustmentPrCode1(DEFAULT_ADJUSTMENT_PR_CODE_1)
            .adjustmentPrCode1Amount(DEFAULT_ADJUSTMENT_PR_CODE_1_AMOUNT)
            .adjustmentPrCode2(DEFAULT_ADJUSTMENT_PR_CODE_2)
            .adjustmentPrCode2Amount(DEFAULT_ADJUSTMENT_PR_CODE_2_AMOUNT)
            .adjustmentPrCode3(DEFAULT_ADJUSTMENT_PR_CODE_3)
            .adjustmentPrCode3Amount(DEFAULT_ADJUSTMENT_PR_CODE_3_AMOUNT)
            .adjustmentPrCode4(DEFAULT_ADJUSTMENT_PR_CODE_4)
            .adjustmentPrCode4Amount(DEFAULT_ADJUSTMENT_PR_CODE_4_AMOUNT)
            .adjustmentCoCode1(DEFAULT_ADJUSTMENT_CO_CODE_1)
            .adjustmentCoCode1Amount(DEFAULT_ADJUSTMENT_CO_CODE_1_AMOUNT)
            .adjustmentCoCode2(DEFAULT_ADJUSTMENT_CO_CODE_2)
            .adjustmentCoCode2Amount(DEFAULT_ADJUSTMENT_CO_CODE_2_AMOUNT)
            .adjustmentCoCode3(DEFAULT_ADJUSTMENT_CO_CODE_3)
            .adjustmentCoCode3Amount(DEFAULT_ADJUSTMENT_CO_CODE_3_AMOUNT)
            .adjustmentCoCode4(DEFAULT_ADJUSTMENT_CO_CODE_4)
            .adjustmentCoCode4Amount(DEFAULT_ADJUSTMENT_CO_CODE_4_AMOUNT)
            .adjustmentCrCode1(DEFAULT_ADJUSTMENT_CR_CODE_1)
            .adjustmentCrCode1Amount(DEFAULT_ADJUSTMENT_CR_CODE_1_AMOUNT)
            .adjustmentCrCode2(DEFAULT_ADJUSTMENT_CR_CODE_2)
            .adjustmentCrCode2Amount(DEFAULT_ADJUSTMENT_CR_CODE_2_AMOUNT)
            .adjustmentCrCode3(DEFAULT_ADJUSTMENT_CR_CODE_3)
            .adjustmentCrCode3Amount(DEFAULT_ADJUSTMENT_CR_CODE_3_AMOUNT)
            .adjustmentCrCode4(DEFAULT_ADJUSTMENT_CR_CODE_4)
            .adjustmentCrCode4Amount(DEFAULT_ADJUSTMENT_CR_CODE_4_AMOUNT)
            .adjustmentOaCode1(DEFAULT_ADJUSTMENT_OA_CODE_1)
            .adjustmentOaCode1Amount(DEFAULT_ADJUSTMENT_OA_CODE_1_AMOUNT)
            .adjustmentOaCode2(DEFAULT_ADJUSTMENT_OA_CODE_2)
            .adjustmentOaCode2Amount(DEFAULT_ADJUSTMENT_OA_CODE_2_AMOUNT)
            .adjustmentOaCode3(DEFAULT_ADJUSTMENT_OA_CODE_3)
            .adjustmentOaCode3Amount(DEFAULT_ADJUSTMENT_OA_CODE_3_AMOUNT)
            .adjustmentOaCode4(DEFAULT_ADJUSTMENT_OA_CODE_4)
            .adjustmentOaCode4Amount(DEFAULT_ADJUSTMENT_OA_CODE_4_AMOUNT)
            .adjustmentPiCode1(DEFAULT_ADJUSTMENT_PI_CODE_1)
            .adjustmentPiCode1Amount(DEFAULT_ADJUSTMENT_PI_CODE_1_AMOUNT)
            .adjustmentPiCode2(DEFAULT_ADJUSTMENT_PI_CODE_2)
            .adjustmentPiCode2Amount(DEFAULT_ADJUSTMENT_PI_CODE_2_AMOUNT)
            .adjustmentPiCode3(DEFAULT_ADJUSTMENT_PI_CODE_3)
            .adjustmentPiCode3Amount(DEFAULT_ADJUSTMENT_PI_CODE_3_AMOUNT)
            .adjustmentPiCode4(DEFAULT_ADJUSTMENT_PI_CODE_4)
            .adjustmentPiCode4Amount(DEFAULT_ADJUSTMENT_PI_CODE_4_AMOUNT)
            .providerPaymentAmount(DEFAULT_PROVIDER_PAYMENT_AMOUNT)
            .claimCob835MasterId(DEFAULT_CLAIM_COB_835_MASTER_ID)
            .status(DEFAULT_STATUS)
            .serviceDateTo(DEFAULT_SERVICE_DATE_TO)
            .unitCount(DEFAULT_UNIT_COUNT)
            .claimsCob835DetailsUuid(DEFAULT_CLAIMS_COB_835_DETAILS_UUID);
        return claimsCob835Details;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClaimsCob835Details createUpdatedEntity(EntityManager em) {
        ClaimsCob835Details claimsCob835Details = new ClaimsCob835Details()
            .serviceDate(UPDATED_SERVICE_DATE)
            .adjudicatedProcedureCode(UPDATED_ADJUDICATED_PROCEDURE_CODE)
            .adjudicatedProcedureModifierCodes(UPDATED_ADJUDICATED_PROCEDURE_MODIFIER_CODES)
            .chargeAmount(UPDATED_CHARGE_AMOUNT)
            .allowedAmount(UPDATED_ALLOWED_AMOUNT)
            .adjustmentPrCode1(UPDATED_ADJUSTMENT_PR_CODE_1)
            .adjustmentPrCode1Amount(UPDATED_ADJUSTMENT_PR_CODE_1_AMOUNT)
            .adjustmentPrCode2(UPDATED_ADJUSTMENT_PR_CODE_2)
            .adjustmentPrCode2Amount(UPDATED_ADJUSTMENT_PR_CODE_2_AMOUNT)
            .adjustmentPrCode3(UPDATED_ADJUSTMENT_PR_CODE_3)
            .adjustmentPrCode3Amount(UPDATED_ADJUSTMENT_PR_CODE_3_AMOUNT)
            .adjustmentPrCode4(UPDATED_ADJUSTMENT_PR_CODE_4)
            .adjustmentPrCode4Amount(UPDATED_ADJUSTMENT_PR_CODE_4_AMOUNT)
            .adjustmentCoCode1(UPDATED_ADJUSTMENT_CO_CODE_1)
            .adjustmentCoCode1Amount(UPDATED_ADJUSTMENT_CO_CODE_1_AMOUNT)
            .adjustmentCoCode2(UPDATED_ADJUSTMENT_CO_CODE_2)
            .adjustmentCoCode2Amount(UPDATED_ADJUSTMENT_CO_CODE_2_AMOUNT)
            .adjustmentCoCode3(UPDATED_ADJUSTMENT_CO_CODE_3)
            .adjustmentCoCode3Amount(UPDATED_ADJUSTMENT_CO_CODE_3_AMOUNT)
            .adjustmentCoCode4(UPDATED_ADJUSTMENT_CO_CODE_4)
            .adjustmentCoCode4Amount(UPDATED_ADJUSTMENT_CO_CODE_4_AMOUNT)
            .adjustmentCrCode1(UPDATED_ADJUSTMENT_CR_CODE_1)
            .adjustmentCrCode1Amount(UPDATED_ADJUSTMENT_CR_CODE_1_AMOUNT)
            .adjustmentCrCode2(UPDATED_ADJUSTMENT_CR_CODE_2)
            .adjustmentCrCode2Amount(UPDATED_ADJUSTMENT_CR_CODE_2_AMOUNT)
            .adjustmentCrCode3(UPDATED_ADJUSTMENT_CR_CODE_3)
            .adjustmentCrCode3Amount(UPDATED_ADJUSTMENT_CR_CODE_3_AMOUNT)
            .adjustmentCrCode4(UPDATED_ADJUSTMENT_CR_CODE_4)
            .adjustmentCrCode4Amount(UPDATED_ADJUSTMENT_CR_CODE_4_AMOUNT)
            .adjustmentOaCode1(UPDATED_ADJUSTMENT_OA_CODE_1)
            .adjustmentOaCode1Amount(UPDATED_ADJUSTMENT_OA_CODE_1_AMOUNT)
            .adjustmentOaCode2(UPDATED_ADJUSTMENT_OA_CODE_2)
            .adjustmentOaCode2Amount(UPDATED_ADJUSTMENT_OA_CODE_2_AMOUNT)
            .adjustmentOaCode3(UPDATED_ADJUSTMENT_OA_CODE_3)
            .adjustmentOaCode3Amount(UPDATED_ADJUSTMENT_OA_CODE_3_AMOUNT)
            .adjustmentOaCode4(UPDATED_ADJUSTMENT_OA_CODE_4)
            .adjustmentOaCode4Amount(UPDATED_ADJUSTMENT_OA_CODE_4_AMOUNT)
            .adjustmentPiCode1(UPDATED_ADJUSTMENT_PI_CODE_1)
            .adjustmentPiCode1Amount(UPDATED_ADJUSTMENT_PI_CODE_1_AMOUNT)
            .adjustmentPiCode2(UPDATED_ADJUSTMENT_PI_CODE_2)
            .adjustmentPiCode2Amount(UPDATED_ADJUSTMENT_PI_CODE_2_AMOUNT)
            .adjustmentPiCode3(UPDATED_ADJUSTMENT_PI_CODE_3)
            .adjustmentPiCode3Amount(UPDATED_ADJUSTMENT_PI_CODE_3_AMOUNT)
            .adjustmentPiCode4(UPDATED_ADJUSTMENT_PI_CODE_4)
            .adjustmentPiCode4Amount(UPDATED_ADJUSTMENT_PI_CODE_4_AMOUNT)
            .providerPaymentAmount(UPDATED_PROVIDER_PAYMENT_AMOUNT)
            .claimCob835MasterId(UPDATED_CLAIM_COB_835_MASTER_ID)
            .status(UPDATED_STATUS)
            .serviceDateTo(UPDATED_SERVICE_DATE_TO)
            .unitCount(UPDATED_UNIT_COUNT)
            .claimsCob835DetailsUuid(UPDATED_CLAIMS_COB_835_DETAILS_UUID);
        return claimsCob835Details;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(ClaimsCob835Details.class).block();
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
        claimsCob835Details = createEntity(em);
    }

    @Test
    void createClaimsCob835Details() throws Exception {
        int databaseSizeBeforeCreate = claimsCob835DetailsRepository.findAll().collectList().block().size();
        // Create the ClaimsCob835Details
        ClaimsCob835DetailsDTO claimsCob835DetailsDTO = claimsCob835DetailsMapper.toDto(claimsCob835Details);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(claimsCob835DetailsDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the ClaimsCob835Details in the database
        List<ClaimsCob835Details> claimsCob835DetailsList = claimsCob835DetailsRepository.findAll().collectList().block();
        assertThat(claimsCob835DetailsList).hasSize(databaseSizeBeforeCreate + 1);
        ClaimsCob835Details testClaimsCob835Details = claimsCob835DetailsList.get(claimsCob835DetailsList.size() - 1);
        assertThat(testClaimsCob835Details.getServiceDate()).isEqualTo(DEFAULT_SERVICE_DATE);
        assertThat(testClaimsCob835Details.getAdjudicatedProcedureCode()).isEqualTo(DEFAULT_ADJUDICATED_PROCEDURE_CODE);
        assertThat(testClaimsCob835Details.getAdjudicatedProcedureModifierCodes()).isEqualTo(DEFAULT_ADJUDICATED_PROCEDURE_MODIFIER_CODES);
        assertThat(testClaimsCob835Details.getChargeAmount()).isEqualTo(DEFAULT_CHARGE_AMOUNT);
        assertThat(testClaimsCob835Details.getAllowedAmount()).isEqualTo(DEFAULT_ALLOWED_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentPrCode1()).isEqualTo(DEFAULT_ADJUSTMENT_PR_CODE_1);
        assertThat(testClaimsCob835Details.getAdjustmentPrCode1Amount()).isEqualTo(DEFAULT_ADJUSTMENT_PR_CODE_1_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentPrCode2()).isEqualTo(DEFAULT_ADJUSTMENT_PR_CODE_2);
        assertThat(testClaimsCob835Details.getAdjustmentPrCode2Amount()).isEqualTo(DEFAULT_ADJUSTMENT_PR_CODE_2_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentPrCode3()).isEqualTo(DEFAULT_ADJUSTMENT_PR_CODE_3);
        assertThat(testClaimsCob835Details.getAdjustmentPrCode3Amount()).isEqualTo(DEFAULT_ADJUSTMENT_PR_CODE_3_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentPrCode4()).isEqualTo(DEFAULT_ADJUSTMENT_PR_CODE_4);
        assertThat(testClaimsCob835Details.getAdjustmentPrCode4Amount()).isEqualTo(DEFAULT_ADJUSTMENT_PR_CODE_4_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentCoCode1()).isEqualTo(DEFAULT_ADJUSTMENT_CO_CODE_1);
        assertThat(testClaimsCob835Details.getAdjustmentCoCode1Amount()).isEqualTo(DEFAULT_ADJUSTMENT_CO_CODE_1_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentCoCode2()).isEqualTo(DEFAULT_ADJUSTMENT_CO_CODE_2);
        assertThat(testClaimsCob835Details.getAdjustmentCoCode2Amount()).isEqualTo(DEFAULT_ADJUSTMENT_CO_CODE_2_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentCoCode3()).isEqualTo(DEFAULT_ADJUSTMENT_CO_CODE_3);
        assertThat(testClaimsCob835Details.getAdjustmentCoCode3Amount()).isEqualTo(DEFAULT_ADJUSTMENT_CO_CODE_3_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentCoCode4()).isEqualTo(DEFAULT_ADJUSTMENT_CO_CODE_4);
        assertThat(testClaimsCob835Details.getAdjustmentCoCode4Amount()).isEqualTo(DEFAULT_ADJUSTMENT_CO_CODE_4_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentCrCode1()).isEqualTo(DEFAULT_ADJUSTMENT_CR_CODE_1);
        assertThat(testClaimsCob835Details.getAdjustmentCrCode1Amount()).isEqualTo(DEFAULT_ADJUSTMENT_CR_CODE_1_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentCrCode2()).isEqualTo(DEFAULT_ADJUSTMENT_CR_CODE_2);
        assertThat(testClaimsCob835Details.getAdjustmentCrCode2Amount()).isEqualTo(DEFAULT_ADJUSTMENT_CR_CODE_2_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentCrCode3()).isEqualTo(DEFAULT_ADJUSTMENT_CR_CODE_3);
        assertThat(testClaimsCob835Details.getAdjustmentCrCode3Amount()).isEqualTo(DEFAULT_ADJUSTMENT_CR_CODE_3_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentCrCode4()).isEqualTo(DEFAULT_ADJUSTMENT_CR_CODE_4);
        assertThat(testClaimsCob835Details.getAdjustmentCrCode4Amount()).isEqualTo(DEFAULT_ADJUSTMENT_CR_CODE_4_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentOaCode1()).isEqualTo(DEFAULT_ADJUSTMENT_OA_CODE_1);
        assertThat(testClaimsCob835Details.getAdjustmentOaCode1Amount()).isEqualTo(DEFAULT_ADJUSTMENT_OA_CODE_1_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentOaCode2()).isEqualTo(DEFAULT_ADJUSTMENT_OA_CODE_2);
        assertThat(testClaimsCob835Details.getAdjustmentOaCode2Amount()).isEqualTo(DEFAULT_ADJUSTMENT_OA_CODE_2_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentOaCode3()).isEqualTo(DEFAULT_ADJUSTMENT_OA_CODE_3);
        assertThat(testClaimsCob835Details.getAdjustmentOaCode3Amount()).isEqualTo(DEFAULT_ADJUSTMENT_OA_CODE_3_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentOaCode4()).isEqualTo(DEFAULT_ADJUSTMENT_OA_CODE_4);
        assertThat(testClaimsCob835Details.getAdjustmentOaCode4Amount()).isEqualTo(DEFAULT_ADJUSTMENT_OA_CODE_4_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentPiCode1()).isEqualTo(DEFAULT_ADJUSTMENT_PI_CODE_1);
        assertThat(testClaimsCob835Details.getAdjustmentPiCode1Amount()).isEqualTo(DEFAULT_ADJUSTMENT_PI_CODE_1_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentPiCode2()).isEqualTo(DEFAULT_ADJUSTMENT_PI_CODE_2);
        assertThat(testClaimsCob835Details.getAdjustmentPiCode2Amount()).isEqualTo(DEFAULT_ADJUSTMENT_PI_CODE_2_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentPiCode3()).isEqualTo(DEFAULT_ADJUSTMENT_PI_CODE_3);
        assertThat(testClaimsCob835Details.getAdjustmentPiCode3Amount()).isEqualTo(DEFAULT_ADJUSTMENT_PI_CODE_3_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentPiCode4()).isEqualTo(DEFAULT_ADJUSTMENT_PI_CODE_4);
        assertThat(testClaimsCob835Details.getAdjustmentPiCode4Amount()).isEqualTo(DEFAULT_ADJUSTMENT_PI_CODE_4_AMOUNT);
        assertThat(testClaimsCob835Details.getProviderPaymentAmount()).isEqualTo(DEFAULT_PROVIDER_PAYMENT_AMOUNT);
        assertThat(testClaimsCob835Details.getClaimCob835MasterId()).isEqualTo(DEFAULT_CLAIM_COB_835_MASTER_ID);
        assertThat(testClaimsCob835Details.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testClaimsCob835Details.getServiceDateTo()).isEqualTo(DEFAULT_SERVICE_DATE_TO);
        assertThat(testClaimsCob835Details.getUnitCount()).isEqualTo(DEFAULT_UNIT_COUNT);
        assertThat(testClaimsCob835Details.getClaimsCob835DetailsUuid()).isEqualTo(DEFAULT_CLAIMS_COB_835_DETAILS_UUID);
    }

    @Test
    void createClaimsCob835DetailsWithExistingId() throws Exception {
        // Create the ClaimsCob835Details with an existing ID
        claimsCob835Details.setClaimCob835DetailId(1L);
        ClaimsCob835DetailsDTO claimsCob835DetailsDTO = claimsCob835DetailsMapper.toDto(claimsCob835Details);

        int databaseSizeBeforeCreate = claimsCob835DetailsRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(claimsCob835DetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ClaimsCob835Details in the database
        List<ClaimsCob835Details> claimsCob835DetailsList = claimsCob835DetailsRepository.findAll().collectList().block();
        assertThat(claimsCob835DetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllClaimsCob835Details() {
        // Initialize the database
        claimsCob835DetailsRepository.save(claimsCob835Details).block();

        // Get all the claimsCob835DetailsList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=claimCob835DetailId,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].claimCob835DetailId")
            .value(hasItem(claimsCob835Details.getClaimCob835DetailId().intValue()))
            .jsonPath("$.[*].serviceDate")
            .value(hasItem(DEFAULT_SERVICE_DATE.toString()))
            .jsonPath("$.[*].adjudicatedProcedureCode")
            .value(hasItem(DEFAULT_ADJUDICATED_PROCEDURE_CODE))
            .jsonPath("$.[*].adjudicatedProcedureModifierCodes")
            .value(hasItem(DEFAULT_ADJUDICATED_PROCEDURE_MODIFIER_CODES))
            .jsonPath("$.[*].chargeAmount")
            .value(hasItem(DEFAULT_CHARGE_AMOUNT.doubleValue()))
            .jsonPath("$.[*].allowedAmount")
            .value(hasItem(DEFAULT_ALLOWED_AMOUNT.doubleValue()))
            .jsonPath("$.[*].adjustmentPrCode1")
            .value(hasItem(DEFAULT_ADJUSTMENT_PR_CODE_1))
            .jsonPath("$.[*].adjustmentPrCode1Amount")
            .value(hasItem(DEFAULT_ADJUSTMENT_PR_CODE_1_AMOUNT.doubleValue()))
            .jsonPath("$.[*].adjustmentPrCode2")
            .value(hasItem(DEFAULT_ADJUSTMENT_PR_CODE_2))
            .jsonPath("$.[*].adjustmentPrCode2Amount")
            .value(hasItem(DEFAULT_ADJUSTMENT_PR_CODE_2_AMOUNT.doubleValue()))
            .jsonPath("$.[*].adjustmentPrCode3")
            .value(hasItem(DEFAULT_ADJUSTMENT_PR_CODE_3))
            .jsonPath("$.[*].adjustmentPrCode3Amount")
            .value(hasItem(DEFAULT_ADJUSTMENT_PR_CODE_3_AMOUNT.doubleValue()))
            .jsonPath("$.[*].adjustmentPrCode4")
            .value(hasItem(DEFAULT_ADJUSTMENT_PR_CODE_4))
            .jsonPath("$.[*].adjustmentPrCode4Amount")
            .value(hasItem(DEFAULT_ADJUSTMENT_PR_CODE_4_AMOUNT.doubleValue()))
            .jsonPath("$.[*].adjustmentCoCode1")
            .value(hasItem(DEFAULT_ADJUSTMENT_CO_CODE_1))
            .jsonPath("$.[*].adjustmentCoCode1Amount")
            .value(hasItem(DEFAULT_ADJUSTMENT_CO_CODE_1_AMOUNT.doubleValue()))
            .jsonPath("$.[*].adjustmentCoCode2")
            .value(hasItem(DEFAULT_ADJUSTMENT_CO_CODE_2))
            .jsonPath("$.[*].adjustmentCoCode2Amount")
            .value(hasItem(DEFAULT_ADJUSTMENT_CO_CODE_2_AMOUNT.doubleValue()))
            .jsonPath("$.[*].adjustmentCoCode3")
            .value(hasItem(DEFAULT_ADJUSTMENT_CO_CODE_3))
            .jsonPath("$.[*].adjustmentCoCode3Amount")
            .value(hasItem(DEFAULT_ADJUSTMENT_CO_CODE_3_AMOUNT.doubleValue()))
            .jsonPath("$.[*].adjustmentCoCode4")
            .value(hasItem(DEFAULT_ADJUSTMENT_CO_CODE_4))
            .jsonPath("$.[*].adjustmentCoCode4Amount")
            .value(hasItem(DEFAULT_ADJUSTMENT_CO_CODE_4_AMOUNT.doubleValue()))
            .jsonPath("$.[*].adjustmentCrCode1")
            .value(hasItem(DEFAULT_ADJUSTMENT_CR_CODE_1))
            .jsonPath("$.[*].adjustmentCrCode1Amount")
            .value(hasItem(DEFAULT_ADJUSTMENT_CR_CODE_1_AMOUNT.doubleValue()))
            .jsonPath("$.[*].adjustmentCrCode2")
            .value(hasItem(DEFAULT_ADJUSTMENT_CR_CODE_2))
            .jsonPath("$.[*].adjustmentCrCode2Amount")
            .value(hasItem(DEFAULT_ADJUSTMENT_CR_CODE_2_AMOUNT.doubleValue()))
            .jsonPath("$.[*].adjustmentCrCode3")
            .value(hasItem(DEFAULT_ADJUSTMENT_CR_CODE_3))
            .jsonPath("$.[*].adjustmentCrCode3Amount")
            .value(hasItem(DEFAULT_ADJUSTMENT_CR_CODE_3_AMOUNT.doubleValue()))
            .jsonPath("$.[*].adjustmentCrCode4")
            .value(hasItem(DEFAULT_ADJUSTMENT_CR_CODE_4))
            .jsonPath("$.[*].adjustmentCrCode4Amount")
            .value(hasItem(DEFAULT_ADJUSTMENT_CR_CODE_4_AMOUNT.doubleValue()))
            .jsonPath("$.[*].adjustmentOaCode1")
            .value(hasItem(DEFAULT_ADJUSTMENT_OA_CODE_1))
            .jsonPath("$.[*].adjustmentOaCode1Amount")
            .value(hasItem(DEFAULT_ADJUSTMENT_OA_CODE_1_AMOUNT.doubleValue()))
            .jsonPath("$.[*].adjustmentOaCode2")
            .value(hasItem(DEFAULT_ADJUSTMENT_OA_CODE_2))
            .jsonPath("$.[*].adjustmentOaCode2Amount")
            .value(hasItem(DEFAULT_ADJUSTMENT_OA_CODE_2_AMOUNT.doubleValue()))
            .jsonPath("$.[*].adjustmentOaCode3")
            .value(hasItem(DEFAULT_ADJUSTMENT_OA_CODE_3))
            .jsonPath("$.[*].adjustmentOaCode3Amount")
            .value(hasItem(DEFAULT_ADJUSTMENT_OA_CODE_3_AMOUNT.doubleValue()))
            .jsonPath("$.[*].adjustmentOaCode4")
            .value(hasItem(DEFAULT_ADJUSTMENT_OA_CODE_4))
            .jsonPath("$.[*].adjustmentOaCode4Amount")
            .value(hasItem(DEFAULT_ADJUSTMENT_OA_CODE_4_AMOUNT.doubleValue()))
            .jsonPath("$.[*].adjustmentPiCode1")
            .value(hasItem(DEFAULT_ADJUSTMENT_PI_CODE_1))
            .jsonPath("$.[*].adjustmentPiCode1Amount")
            .value(hasItem(DEFAULT_ADJUSTMENT_PI_CODE_1_AMOUNT.doubleValue()))
            .jsonPath("$.[*].adjustmentPiCode2")
            .value(hasItem(DEFAULT_ADJUSTMENT_PI_CODE_2))
            .jsonPath("$.[*].adjustmentPiCode2Amount")
            .value(hasItem(DEFAULT_ADJUSTMENT_PI_CODE_2_AMOUNT.doubleValue()))
            .jsonPath("$.[*].adjustmentPiCode3")
            .value(hasItem(DEFAULT_ADJUSTMENT_PI_CODE_3))
            .jsonPath("$.[*].adjustmentPiCode3Amount")
            .value(hasItem(DEFAULT_ADJUSTMENT_PI_CODE_3_AMOUNT.doubleValue()))
            .jsonPath("$.[*].adjustmentPiCode4")
            .value(hasItem(DEFAULT_ADJUSTMENT_PI_CODE_4))
            .jsonPath("$.[*].adjustmentPiCode4Amount")
            .value(hasItem(DEFAULT_ADJUSTMENT_PI_CODE_4_AMOUNT.doubleValue()))
            .jsonPath("$.[*].providerPaymentAmount")
            .value(hasItem(DEFAULT_PROVIDER_PAYMENT_AMOUNT.doubleValue()))
            .jsonPath("$.[*].claimCob835MasterId")
            .value(hasItem(DEFAULT_CLAIM_COB_835_MASTER_ID.intValue()))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].serviceDateTo")
            .value(hasItem(DEFAULT_SERVICE_DATE_TO.toString()))
            .jsonPath("$.[*].unitCount")
            .value(hasItem(DEFAULT_UNIT_COUNT.intValue()))
            .jsonPath("$.[*].claimsCob835DetailsUuid")
            .value(hasItem(DEFAULT_CLAIMS_COB_835_DETAILS_UUID.toString()));
    }

    @Test
    void getClaimsCob835Details() {
        // Initialize the database
        claimsCob835DetailsRepository.save(claimsCob835Details).block();

        // Get the claimsCob835Details
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, claimsCob835Details.getClaimCob835DetailId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.claimCob835DetailId")
            .value(is(claimsCob835Details.getClaimCob835DetailId().intValue()))
            .jsonPath("$.serviceDate")
            .value(is(DEFAULT_SERVICE_DATE.toString()))
            .jsonPath("$.adjudicatedProcedureCode")
            .value(is(DEFAULT_ADJUDICATED_PROCEDURE_CODE))
            .jsonPath("$.adjudicatedProcedureModifierCodes")
            .value(is(DEFAULT_ADJUDICATED_PROCEDURE_MODIFIER_CODES))
            .jsonPath("$.chargeAmount")
            .value(is(DEFAULT_CHARGE_AMOUNT.doubleValue()))
            .jsonPath("$.allowedAmount")
            .value(is(DEFAULT_ALLOWED_AMOUNT.doubleValue()))
            .jsonPath("$.adjustmentPrCode1")
            .value(is(DEFAULT_ADJUSTMENT_PR_CODE_1))
            .jsonPath("$.adjustmentPrCode1Amount")
            .value(is(DEFAULT_ADJUSTMENT_PR_CODE_1_AMOUNT.doubleValue()))
            .jsonPath("$.adjustmentPrCode2")
            .value(is(DEFAULT_ADJUSTMENT_PR_CODE_2))
            .jsonPath("$.adjustmentPrCode2Amount")
            .value(is(DEFAULT_ADJUSTMENT_PR_CODE_2_AMOUNT.doubleValue()))
            .jsonPath("$.adjustmentPrCode3")
            .value(is(DEFAULT_ADJUSTMENT_PR_CODE_3))
            .jsonPath("$.adjustmentPrCode3Amount")
            .value(is(DEFAULT_ADJUSTMENT_PR_CODE_3_AMOUNT.doubleValue()))
            .jsonPath("$.adjustmentPrCode4")
            .value(is(DEFAULT_ADJUSTMENT_PR_CODE_4))
            .jsonPath("$.adjustmentPrCode4Amount")
            .value(is(DEFAULT_ADJUSTMENT_PR_CODE_4_AMOUNT.doubleValue()))
            .jsonPath("$.adjustmentCoCode1")
            .value(is(DEFAULT_ADJUSTMENT_CO_CODE_1))
            .jsonPath("$.adjustmentCoCode1Amount")
            .value(is(DEFAULT_ADJUSTMENT_CO_CODE_1_AMOUNT.doubleValue()))
            .jsonPath("$.adjustmentCoCode2")
            .value(is(DEFAULT_ADJUSTMENT_CO_CODE_2))
            .jsonPath("$.adjustmentCoCode2Amount")
            .value(is(DEFAULT_ADJUSTMENT_CO_CODE_2_AMOUNT.doubleValue()))
            .jsonPath("$.adjustmentCoCode3")
            .value(is(DEFAULT_ADJUSTMENT_CO_CODE_3))
            .jsonPath("$.adjustmentCoCode3Amount")
            .value(is(DEFAULT_ADJUSTMENT_CO_CODE_3_AMOUNT.doubleValue()))
            .jsonPath("$.adjustmentCoCode4")
            .value(is(DEFAULT_ADJUSTMENT_CO_CODE_4))
            .jsonPath("$.adjustmentCoCode4Amount")
            .value(is(DEFAULT_ADJUSTMENT_CO_CODE_4_AMOUNT.doubleValue()))
            .jsonPath("$.adjustmentCrCode1")
            .value(is(DEFAULT_ADJUSTMENT_CR_CODE_1))
            .jsonPath("$.adjustmentCrCode1Amount")
            .value(is(DEFAULT_ADJUSTMENT_CR_CODE_1_AMOUNT.doubleValue()))
            .jsonPath("$.adjustmentCrCode2")
            .value(is(DEFAULT_ADJUSTMENT_CR_CODE_2))
            .jsonPath("$.adjustmentCrCode2Amount")
            .value(is(DEFAULT_ADJUSTMENT_CR_CODE_2_AMOUNT.doubleValue()))
            .jsonPath("$.adjustmentCrCode3")
            .value(is(DEFAULT_ADJUSTMENT_CR_CODE_3))
            .jsonPath("$.adjustmentCrCode3Amount")
            .value(is(DEFAULT_ADJUSTMENT_CR_CODE_3_AMOUNT.doubleValue()))
            .jsonPath("$.adjustmentCrCode4")
            .value(is(DEFAULT_ADJUSTMENT_CR_CODE_4))
            .jsonPath("$.adjustmentCrCode4Amount")
            .value(is(DEFAULT_ADJUSTMENT_CR_CODE_4_AMOUNT.doubleValue()))
            .jsonPath("$.adjustmentOaCode1")
            .value(is(DEFAULT_ADJUSTMENT_OA_CODE_1))
            .jsonPath("$.adjustmentOaCode1Amount")
            .value(is(DEFAULT_ADJUSTMENT_OA_CODE_1_AMOUNT.doubleValue()))
            .jsonPath("$.adjustmentOaCode2")
            .value(is(DEFAULT_ADJUSTMENT_OA_CODE_2))
            .jsonPath("$.adjustmentOaCode2Amount")
            .value(is(DEFAULT_ADJUSTMENT_OA_CODE_2_AMOUNT.doubleValue()))
            .jsonPath("$.adjustmentOaCode3")
            .value(is(DEFAULT_ADJUSTMENT_OA_CODE_3))
            .jsonPath("$.adjustmentOaCode3Amount")
            .value(is(DEFAULT_ADJUSTMENT_OA_CODE_3_AMOUNT.doubleValue()))
            .jsonPath("$.adjustmentOaCode4")
            .value(is(DEFAULT_ADJUSTMENT_OA_CODE_4))
            .jsonPath("$.adjustmentOaCode4Amount")
            .value(is(DEFAULT_ADJUSTMENT_OA_CODE_4_AMOUNT.doubleValue()))
            .jsonPath("$.adjustmentPiCode1")
            .value(is(DEFAULT_ADJUSTMENT_PI_CODE_1))
            .jsonPath("$.adjustmentPiCode1Amount")
            .value(is(DEFAULT_ADJUSTMENT_PI_CODE_1_AMOUNT.doubleValue()))
            .jsonPath("$.adjustmentPiCode2")
            .value(is(DEFAULT_ADJUSTMENT_PI_CODE_2))
            .jsonPath("$.adjustmentPiCode2Amount")
            .value(is(DEFAULT_ADJUSTMENT_PI_CODE_2_AMOUNT.doubleValue()))
            .jsonPath("$.adjustmentPiCode3")
            .value(is(DEFAULT_ADJUSTMENT_PI_CODE_3))
            .jsonPath("$.adjustmentPiCode3Amount")
            .value(is(DEFAULT_ADJUSTMENT_PI_CODE_3_AMOUNT.doubleValue()))
            .jsonPath("$.adjustmentPiCode4")
            .value(is(DEFAULT_ADJUSTMENT_PI_CODE_4))
            .jsonPath("$.adjustmentPiCode4Amount")
            .value(is(DEFAULT_ADJUSTMENT_PI_CODE_4_AMOUNT.doubleValue()))
            .jsonPath("$.providerPaymentAmount")
            .value(is(DEFAULT_PROVIDER_PAYMENT_AMOUNT.doubleValue()))
            .jsonPath("$.claimCob835MasterId")
            .value(is(DEFAULT_CLAIM_COB_835_MASTER_ID.intValue()))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.serviceDateTo")
            .value(is(DEFAULT_SERVICE_DATE_TO.toString()))
            .jsonPath("$.unitCount")
            .value(is(DEFAULT_UNIT_COUNT.intValue()))
            .jsonPath("$.claimsCob835DetailsUuid")
            .value(is(DEFAULT_CLAIMS_COB_835_DETAILS_UUID.toString()));
    }

    @Test
    void getNonExistingClaimsCob835Details() {
        // Get the claimsCob835Details
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingClaimsCob835Details() throws Exception {
        // Initialize the database
        claimsCob835DetailsRepository.save(claimsCob835Details).block();

        int databaseSizeBeforeUpdate = claimsCob835DetailsRepository.findAll().collectList().block().size();

        // Update the claimsCob835Details
        ClaimsCob835Details updatedClaimsCob835Details = claimsCob835DetailsRepository
            .findById(claimsCob835Details.getClaimCob835DetailId())
            .block();
        updatedClaimsCob835Details
            .serviceDate(UPDATED_SERVICE_DATE)
            .adjudicatedProcedureCode(UPDATED_ADJUDICATED_PROCEDURE_CODE)
            .adjudicatedProcedureModifierCodes(UPDATED_ADJUDICATED_PROCEDURE_MODIFIER_CODES)
            .chargeAmount(UPDATED_CHARGE_AMOUNT)
            .allowedAmount(UPDATED_ALLOWED_AMOUNT)
            .adjustmentPrCode1(UPDATED_ADJUSTMENT_PR_CODE_1)
            .adjustmentPrCode1Amount(UPDATED_ADJUSTMENT_PR_CODE_1_AMOUNT)
            .adjustmentPrCode2(UPDATED_ADJUSTMENT_PR_CODE_2)
            .adjustmentPrCode2Amount(UPDATED_ADJUSTMENT_PR_CODE_2_AMOUNT)
            .adjustmentPrCode3(UPDATED_ADJUSTMENT_PR_CODE_3)
            .adjustmentPrCode3Amount(UPDATED_ADJUSTMENT_PR_CODE_3_AMOUNT)
            .adjustmentPrCode4(UPDATED_ADJUSTMENT_PR_CODE_4)
            .adjustmentPrCode4Amount(UPDATED_ADJUSTMENT_PR_CODE_4_AMOUNT)
            .adjustmentCoCode1(UPDATED_ADJUSTMENT_CO_CODE_1)
            .adjustmentCoCode1Amount(UPDATED_ADJUSTMENT_CO_CODE_1_AMOUNT)
            .adjustmentCoCode2(UPDATED_ADJUSTMENT_CO_CODE_2)
            .adjustmentCoCode2Amount(UPDATED_ADJUSTMENT_CO_CODE_2_AMOUNT)
            .adjustmentCoCode3(UPDATED_ADJUSTMENT_CO_CODE_3)
            .adjustmentCoCode3Amount(UPDATED_ADJUSTMENT_CO_CODE_3_AMOUNT)
            .adjustmentCoCode4(UPDATED_ADJUSTMENT_CO_CODE_4)
            .adjustmentCoCode4Amount(UPDATED_ADJUSTMENT_CO_CODE_4_AMOUNT)
            .adjustmentCrCode1(UPDATED_ADJUSTMENT_CR_CODE_1)
            .adjustmentCrCode1Amount(UPDATED_ADJUSTMENT_CR_CODE_1_AMOUNT)
            .adjustmentCrCode2(UPDATED_ADJUSTMENT_CR_CODE_2)
            .adjustmentCrCode2Amount(UPDATED_ADJUSTMENT_CR_CODE_2_AMOUNT)
            .adjustmentCrCode3(UPDATED_ADJUSTMENT_CR_CODE_3)
            .adjustmentCrCode3Amount(UPDATED_ADJUSTMENT_CR_CODE_3_AMOUNT)
            .adjustmentCrCode4(UPDATED_ADJUSTMENT_CR_CODE_4)
            .adjustmentCrCode4Amount(UPDATED_ADJUSTMENT_CR_CODE_4_AMOUNT)
            .adjustmentOaCode1(UPDATED_ADJUSTMENT_OA_CODE_1)
            .adjustmentOaCode1Amount(UPDATED_ADJUSTMENT_OA_CODE_1_AMOUNT)
            .adjustmentOaCode2(UPDATED_ADJUSTMENT_OA_CODE_2)
            .adjustmentOaCode2Amount(UPDATED_ADJUSTMENT_OA_CODE_2_AMOUNT)
            .adjustmentOaCode3(UPDATED_ADJUSTMENT_OA_CODE_3)
            .adjustmentOaCode3Amount(UPDATED_ADJUSTMENT_OA_CODE_3_AMOUNT)
            .adjustmentOaCode4(UPDATED_ADJUSTMENT_OA_CODE_4)
            .adjustmentOaCode4Amount(UPDATED_ADJUSTMENT_OA_CODE_4_AMOUNT)
            .adjustmentPiCode1(UPDATED_ADJUSTMENT_PI_CODE_1)
            .adjustmentPiCode1Amount(UPDATED_ADJUSTMENT_PI_CODE_1_AMOUNT)
            .adjustmentPiCode2(UPDATED_ADJUSTMENT_PI_CODE_2)
            .adjustmentPiCode2Amount(UPDATED_ADJUSTMENT_PI_CODE_2_AMOUNT)
            .adjustmentPiCode3(UPDATED_ADJUSTMENT_PI_CODE_3)
            .adjustmentPiCode3Amount(UPDATED_ADJUSTMENT_PI_CODE_3_AMOUNT)
            .adjustmentPiCode4(UPDATED_ADJUSTMENT_PI_CODE_4)
            .adjustmentPiCode4Amount(UPDATED_ADJUSTMENT_PI_CODE_4_AMOUNT)
            .providerPaymentAmount(UPDATED_PROVIDER_PAYMENT_AMOUNT)
            .claimCob835MasterId(UPDATED_CLAIM_COB_835_MASTER_ID)
            .status(UPDATED_STATUS)
            .serviceDateTo(UPDATED_SERVICE_DATE_TO)
            .unitCount(UPDATED_UNIT_COUNT)
            .claimsCob835DetailsUuid(UPDATED_CLAIMS_COB_835_DETAILS_UUID);
        ClaimsCob835DetailsDTO claimsCob835DetailsDTO = claimsCob835DetailsMapper.toDto(updatedClaimsCob835Details);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, claimsCob835DetailsDTO.getClaimCob835DetailId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(claimsCob835DetailsDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ClaimsCob835Details in the database
        List<ClaimsCob835Details> claimsCob835DetailsList = claimsCob835DetailsRepository.findAll().collectList().block();
        assertThat(claimsCob835DetailsList).hasSize(databaseSizeBeforeUpdate);
        ClaimsCob835Details testClaimsCob835Details = claimsCob835DetailsList.get(claimsCob835DetailsList.size() - 1);
        assertThat(testClaimsCob835Details.getServiceDate()).isEqualTo(UPDATED_SERVICE_DATE);
        assertThat(testClaimsCob835Details.getAdjudicatedProcedureCode()).isEqualTo(UPDATED_ADJUDICATED_PROCEDURE_CODE);
        assertThat(testClaimsCob835Details.getAdjudicatedProcedureModifierCodes()).isEqualTo(UPDATED_ADJUDICATED_PROCEDURE_MODIFIER_CODES);
        assertThat(testClaimsCob835Details.getChargeAmount()).isEqualTo(UPDATED_CHARGE_AMOUNT);
        assertThat(testClaimsCob835Details.getAllowedAmount()).isEqualTo(UPDATED_ALLOWED_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentPrCode1()).isEqualTo(UPDATED_ADJUSTMENT_PR_CODE_1);
        assertThat(testClaimsCob835Details.getAdjustmentPrCode1Amount()).isEqualTo(UPDATED_ADJUSTMENT_PR_CODE_1_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentPrCode2()).isEqualTo(UPDATED_ADJUSTMENT_PR_CODE_2);
        assertThat(testClaimsCob835Details.getAdjustmentPrCode2Amount()).isEqualTo(UPDATED_ADJUSTMENT_PR_CODE_2_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentPrCode3()).isEqualTo(UPDATED_ADJUSTMENT_PR_CODE_3);
        assertThat(testClaimsCob835Details.getAdjustmentPrCode3Amount()).isEqualTo(UPDATED_ADJUSTMENT_PR_CODE_3_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentPrCode4()).isEqualTo(UPDATED_ADJUSTMENT_PR_CODE_4);
        assertThat(testClaimsCob835Details.getAdjustmentPrCode4Amount()).isEqualTo(UPDATED_ADJUSTMENT_PR_CODE_4_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentCoCode1()).isEqualTo(UPDATED_ADJUSTMENT_CO_CODE_1);
        assertThat(testClaimsCob835Details.getAdjustmentCoCode1Amount()).isEqualTo(UPDATED_ADJUSTMENT_CO_CODE_1_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentCoCode2()).isEqualTo(UPDATED_ADJUSTMENT_CO_CODE_2);
        assertThat(testClaimsCob835Details.getAdjustmentCoCode2Amount()).isEqualTo(UPDATED_ADJUSTMENT_CO_CODE_2_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentCoCode3()).isEqualTo(UPDATED_ADJUSTMENT_CO_CODE_3);
        assertThat(testClaimsCob835Details.getAdjustmentCoCode3Amount()).isEqualTo(UPDATED_ADJUSTMENT_CO_CODE_3_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentCoCode4()).isEqualTo(UPDATED_ADJUSTMENT_CO_CODE_4);
        assertThat(testClaimsCob835Details.getAdjustmentCoCode4Amount()).isEqualTo(UPDATED_ADJUSTMENT_CO_CODE_4_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentCrCode1()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_1);
        assertThat(testClaimsCob835Details.getAdjustmentCrCode1Amount()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_1_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentCrCode2()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_2);
        assertThat(testClaimsCob835Details.getAdjustmentCrCode2Amount()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_2_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentCrCode3()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_3);
        assertThat(testClaimsCob835Details.getAdjustmentCrCode3Amount()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_3_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentCrCode4()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_4);
        assertThat(testClaimsCob835Details.getAdjustmentCrCode4Amount()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_4_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentOaCode1()).isEqualTo(UPDATED_ADJUSTMENT_OA_CODE_1);
        assertThat(testClaimsCob835Details.getAdjustmentOaCode1Amount()).isEqualTo(UPDATED_ADJUSTMENT_OA_CODE_1_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentOaCode2()).isEqualTo(UPDATED_ADJUSTMENT_OA_CODE_2);
        assertThat(testClaimsCob835Details.getAdjustmentOaCode2Amount()).isEqualTo(UPDATED_ADJUSTMENT_OA_CODE_2_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentOaCode3()).isEqualTo(UPDATED_ADJUSTMENT_OA_CODE_3);
        assertThat(testClaimsCob835Details.getAdjustmentOaCode3Amount()).isEqualTo(UPDATED_ADJUSTMENT_OA_CODE_3_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentOaCode4()).isEqualTo(UPDATED_ADJUSTMENT_OA_CODE_4);
        assertThat(testClaimsCob835Details.getAdjustmentOaCode4Amount()).isEqualTo(UPDATED_ADJUSTMENT_OA_CODE_4_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentPiCode1()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_1);
        assertThat(testClaimsCob835Details.getAdjustmentPiCode1Amount()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_1_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentPiCode2()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_2);
        assertThat(testClaimsCob835Details.getAdjustmentPiCode2Amount()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_2_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentPiCode3()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_3);
        assertThat(testClaimsCob835Details.getAdjustmentPiCode3Amount()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_3_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentPiCode4()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_4);
        assertThat(testClaimsCob835Details.getAdjustmentPiCode4Amount()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_4_AMOUNT);
        assertThat(testClaimsCob835Details.getProviderPaymentAmount()).isEqualTo(UPDATED_PROVIDER_PAYMENT_AMOUNT);
        assertThat(testClaimsCob835Details.getClaimCob835MasterId()).isEqualTo(UPDATED_CLAIM_COB_835_MASTER_ID);
        assertThat(testClaimsCob835Details.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testClaimsCob835Details.getServiceDateTo()).isEqualTo(UPDATED_SERVICE_DATE_TO);
        assertThat(testClaimsCob835Details.getUnitCount()).isEqualTo(UPDATED_UNIT_COUNT);
        assertThat(testClaimsCob835Details.getClaimsCob835DetailsUuid()).isEqualTo(UPDATED_CLAIMS_COB_835_DETAILS_UUID);
    }

    @Test
    void putNonExistingClaimsCob835Details() throws Exception {
        int databaseSizeBeforeUpdate = claimsCob835DetailsRepository.findAll().collectList().block().size();
        claimsCob835Details.setClaimCob835DetailId(count.incrementAndGet());

        // Create the ClaimsCob835Details
        ClaimsCob835DetailsDTO claimsCob835DetailsDTO = claimsCob835DetailsMapper.toDto(claimsCob835Details);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, claimsCob835DetailsDTO.getClaimCob835DetailId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(claimsCob835DetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ClaimsCob835Details in the database
        List<ClaimsCob835Details> claimsCob835DetailsList = claimsCob835DetailsRepository.findAll().collectList().block();
        assertThat(claimsCob835DetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchClaimsCob835Details() throws Exception {
        int databaseSizeBeforeUpdate = claimsCob835DetailsRepository.findAll().collectList().block().size();
        claimsCob835Details.setClaimCob835DetailId(count.incrementAndGet());

        // Create the ClaimsCob835Details
        ClaimsCob835DetailsDTO claimsCob835DetailsDTO = claimsCob835DetailsMapper.toDto(claimsCob835Details);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(claimsCob835DetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ClaimsCob835Details in the database
        List<ClaimsCob835Details> claimsCob835DetailsList = claimsCob835DetailsRepository.findAll().collectList().block();
        assertThat(claimsCob835DetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamClaimsCob835Details() throws Exception {
        int databaseSizeBeforeUpdate = claimsCob835DetailsRepository.findAll().collectList().block().size();
        claimsCob835Details.setClaimCob835DetailId(count.incrementAndGet());

        // Create the ClaimsCob835Details
        ClaimsCob835DetailsDTO claimsCob835DetailsDTO = claimsCob835DetailsMapper.toDto(claimsCob835Details);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(claimsCob835DetailsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ClaimsCob835Details in the database
        List<ClaimsCob835Details> claimsCob835DetailsList = claimsCob835DetailsRepository.findAll().collectList().block();
        assertThat(claimsCob835DetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateClaimsCob835DetailsWithPatch() throws Exception {
        // Initialize the database
        claimsCob835DetailsRepository.save(claimsCob835Details).block();

        int databaseSizeBeforeUpdate = claimsCob835DetailsRepository.findAll().collectList().block().size();

        // Update the claimsCob835Details using partial update
        ClaimsCob835Details partialUpdatedClaimsCob835Details = new ClaimsCob835Details();
        partialUpdatedClaimsCob835Details.setClaimCob835DetailId(claimsCob835Details.getClaimCob835DetailId());

        partialUpdatedClaimsCob835Details
            .chargeAmount(UPDATED_CHARGE_AMOUNT)
            .adjustmentPrCode3Amount(UPDATED_ADJUSTMENT_PR_CODE_3_AMOUNT)
            .adjustmentCoCode1(UPDATED_ADJUSTMENT_CO_CODE_1)
            .adjustmentCoCode2Amount(UPDATED_ADJUSTMENT_CO_CODE_2_AMOUNT)
            .adjustmentCoCode3(UPDATED_ADJUSTMENT_CO_CODE_3)
            .adjustmentCoCode4(UPDATED_ADJUSTMENT_CO_CODE_4)
            .adjustmentCrCode1(UPDATED_ADJUSTMENT_CR_CODE_1)
            .adjustmentCrCode1Amount(UPDATED_ADJUSTMENT_CR_CODE_1_AMOUNT)
            .adjustmentCrCode2(UPDATED_ADJUSTMENT_CR_CODE_2)
            .adjustmentCrCode3(UPDATED_ADJUSTMENT_CR_CODE_3)
            .adjustmentOaCode1Amount(UPDATED_ADJUSTMENT_OA_CODE_1_AMOUNT)
            .adjustmentOaCode4Amount(UPDATED_ADJUSTMENT_OA_CODE_4_AMOUNT)
            .adjustmentPiCode1Amount(UPDATED_ADJUSTMENT_PI_CODE_1_AMOUNT)
            .adjustmentPiCode2(UPDATED_ADJUSTMENT_PI_CODE_2)
            .adjustmentPiCode3(UPDATED_ADJUSTMENT_PI_CODE_3)
            .adjustmentPiCode4(UPDATED_ADJUSTMENT_PI_CODE_4)
            .providerPaymentAmount(UPDATED_PROVIDER_PAYMENT_AMOUNT)
            .serviceDateTo(UPDATED_SERVICE_DATE_TO)
            .claimsCob835DetailsUuid(UPDATED_CLAIMS_COB_835_DETAILS_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedClaimsCob835Details.getClaimCob835DetailId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedClaimsCob835Details))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ClaimsCob835Details in the database
        List<ClaimsCob835Details> claimsCob835DetailsList = claimsCob835DetailsRepository.findAll().collectList().block();
        assertThat(claimsCob835DetailsList).hasSize(databaseSizeBeforeUpdate);
        ClaimsCob835Details testClaimsCob835Details = claimsCob835DetailsList.get(claimsCob835DetailsList.size() - 1);
        assertThat(testClaimsCob835Details.getServiceDate()).isEqualTo(DEFAULT_SERVICE_DATE);
        assertThat(testClaimsCob835Details.getAdjudicatedProcedureCode()).isEqualTo(DEFAULT_ADJUDICATED_PROCEDURE_CODE);
        assertThat(testClaimsCob835Details.getAdjudicatedProcedureModifierCodes()).isEqualTo(DEFAULT_ADJUDICATED_PROCEDURE_MODIFIER_CODES);
        assertThat(testClaimsCob835Details.getChargeAmount()).isEqualTo(UPDATED_CHARGE_AMOUNT);
        assertThat(testClaimsCob835Details.getAllowedAmount()).isEqualTo(DEFAULT_ALLOWED_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentPrCode1()).isEqualTo(DEFAULT_ADJUSTMENT_PR_CODE_1);
        assertThat(testClaimsCob835Details.getAdjustmentPrCode1Amount()).isEqualTo(DEFAULT_ADJUSTMENT_PR_CODE_1_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentPrCode2()).isEqualTo(DEFAULT_ADJUSTMENT_PR_CODE_2);
        assertThat(testClaimsCob835Details.getAdjustmentPrCode2Amount()).isEqualTo(DEFAULT_ADJUSTMENT_PR_CODE_2_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentPrCode3()).isEqualTo(DEFAULT_ADJUSTMENT_PR_CODE_3);
        assertThat(testClaimsCob835Details.getAdjustmentPrCode3Amount()).isEqualTo(UPDATED_ADJUSTMENT_PR_CODE_3_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentPrCode4()).isEqualTo(DEFAULT_ADJUSTMENT_PR_CODE_4);
        assertThat(testClaimsCob835Details.getAdjustmentPrCode4Amount()).isEqualTo(DEFAULT_ADJUSTMENT_PR_CODE_4_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentCoCode1()).isEqualTo(UPDATED_ADJUSTMENT_CO_CODE_1);
        assertThat(testClaimsCob835Details.getAdjustmentCoCode1Amount()).isEqualTo(DEFAULT_ADJUSTMENT_CO_CODE_1_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentCoCode2()).isEqualTo(DEFAULT_ADJUSTMENT_CO_CODE_2);
        assertThat(testClaimsCob835Details.getAdjustmentCoCode2Amount()).isEqualTo(UPDATED_ADJUSTMENT_CO_CODE_2_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentCoCode3()).isEqualTo(UPDATED_ADJUSTMENT_CO_CODE_3);
        assertThat(testClaimsCob835Details.getAdjustmentCoCode3Amount()).isEqualTo(DEFAULT_ADJUSTMENT_CO_CODE_3_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentCoCode4()).isEqualTo(UPDATED_ADJUSTMENT_CO_CODE_4);
        assertThat(testClaimsCob835Details.getAdjustmentCoCode4Amount()).isEqualTo(DEFAULT_ADJUSTMENT_CO_CODE_4_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentCrCode1()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_1);
        assertThat(testClaimsCob835Details.getAdjustmentCrCode1Amount()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_1_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentCrCode2()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_2);
        assertThat(testClaimsCob835Details.getAdjustmentCrCode2Amount()).isEqualTo(DEFAULT_ADJUSTMENT_CR_CODE_2_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentCrCode3()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_3);
        assertThat(testClaimsCob835Details.getAdjustmentCrCode3Amount()).isEqualTo(DEFAULT_ADJUSTMENT_CR_CODE_3_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentCrCode4()).isEqualTo(DEFAULT_ADJUSTMENT_CR_CODE_4);
        assertThat(testClaimsCob835Details.getAdjustmentCrCode4Amount()).isEqualTo(DEFAULT_ADJUSTMENT_CR_CODE_4_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentOaCode1()).isEqualTo(DEFAULT_ADJUSTMENT_OA_CODE_1);
        assertThat(testClaimsCob835Details.getAdjustmentOaCode1Amount()).isEqualTo(UPDATED_ADJUSTMENT_OA_CODE_1_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentOaCode2()).isEqualTo(DEFAULT_ADJUSTMENT_OA_CODE_2);
        assertThat(testClaimsCob835Details.getAdjustmentOaCode2Amount()).isEqualTo(DEFAULT_ADJUSTMENT_OA_CODE_2_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentOaCode3()).isEqualTo(DEFAULT_ADJUSTMENT_OA_CODE_3);
        assertThat(testClaimsCob835Details.getAdjustmentOaCode3Amount()).isEqualTo(DEFAULT_ADJUSTMENT_OA_CODE_3_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentOaCode4()).isEqualTo(DEFAULT_ADJUSTMENT_OA_CODE_4);
        assertThat(testClaimsCob835Details.getAdjustmentOaCode4Amount()).isEqualTo(UPDATED_ADJUSTMENT_OA_CODE_4_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentPiCode1()).isEqualTo(DEFAULT_ADJUSTMENT_PI_CODE_1);
        assertThat(testClaimsCob835Details.getAdjustmentPiCode1Amount()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_1_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentPiCode2()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_2);
        assertThat(testClaimsCob835Details.getAdjustmentPiCode2Amount()).isEqualTo(DEFAULT_ADJUSTMENT_PI_CODE_2_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentPiCode3()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_3);
        assertThat(testClaimsCob835Details.getAdjustmentPiCode3Amount()).isEqualTo(DEFAULT_ADJUSTMENT_PI_CODE_3_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentPiCode4()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_4);
        assertThat(testClaimsCob835Details.getAdjustmentPiCode4Amount()).isEqualTo(DEFAULT_ADJUSTMENT_PI_CODE_4_AMOUNT);
        assertThat(testClaimsCob835Details.getProviderPaymentAmount()).isEqualTo(UPDATED_PROVIDER_PAYMENT_AMOUNT);
        assertThat(testClaimsCob835Details.getClaimCob835MasterId()).isEqualTo(DEFAULT_CLAIM_COB_835_MASTER_ID);
        assertThat(testClaimsCob835Details.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testClaimsCob835Details.getServiceDateTo()).isEqualTo(UPDATED_SERVICE_DATE_TO);
        assertThat(testClaimsCob835Details.getUnitCount()).isEqualTo(DEFAULT_UNIT_COUNT);
        assertThat(testClaimsCob835Details.getClaimsCob835DetailsUuid()).isEqualTo(UPDATED_CLAIMS_COB_835_DETAILS_UUID);
    }

    @Test
    void fullUpdateClaimsCob835DetailsWithPatch() throws Exception {
        // Initialize the database
        claimsCob835DetailsRepository.save(claimsCob835Details).block();

        int databaseSizeBeforeUpdate = claimsCob835DetailsRepository.findAll().collectList().block().size();

        // Update the claimsCob835Details using partial update
        ClaimsCob835Details partialUpdatedClaimsCob835Details = new ClaimsCob835Details();
        partialUpdatedClaimsCob835Details.setClaimCob835DetailId(claimsCob835Details.getClaimCob835DetailId());

        partialUpdatedClaimsCob835Details
            .serviceDate(UPDATED_SERVICE_DATE)
            .adjudicatedProcedureCode(UPDATED_ADJUDICATED_PROCEDURE_CODE)
            .adjudicatedProcedureModifierCodes(UPDATED_ADJUDICATED_PROCEDURE_MODIFIER_CODES)
            .chargeAmount(UPDATED_CHARGE_AMOUNT)
            .allowedAmount(UPDATED_ALLOWED_AMOUNT)
            .adjustmentPrCode1(UPDATED_ADJUSTMENT_PR_CODE_1)
            .adjustmentPrCode1Amount(UPDATED_ADJUSTMENT_PR_CODE_1_AMOUNT)
            .adjustmentPrCode2(UPDATED_ADJUSTMENT_PR_CODE_2)
            .adjustmentPrCode2Amount(UPDATED_ADJUSTMENT_PR_CODE_2_AMOUNT)
            .adjustmentPrCode3(UPDATED_ADJUSTMENT_PR_CODE_3)
            .adjustmentPrCode3Amount(UPDATED_ADJUSTMENT_PR_CODE_3_AMOUNT)
            .adjustmentPrCode4(UPDATED_ADJUSTMENT_PR_CODE_4)
            .adjustmentPrCode4Amount(UPDATED_ADJUSTMENT_PR_CODE_4_AMOUNT)
            .adjustmentCoCode1(UPDATED_ADJUSTMENT_CO_CODE_1)
            .adjustmentCoCode1Amount(UPDATED_ADJUSTMENT_CO_CODE_1_AMOUNT)
            .adjustmentCoCode2(UPDATED_ADJUSTMENT_CO_CODE_2)
            .adjustmentCoCode2Amount(UPDATED_ADJUSTMENT_CO_CODE_2_AMOUNT)
            .adjustmentCoCode3(UPDATED_ADJUSTMENT_CO_CODE_3)
            .adjustmentCoCode3Amount(UPDATED_ADJUSTMENT_CO_CODE_3_AMOUNT)
            .adjustmentCoCode4(UPDATED_ADJUSTMENT_CO_CODE_4)
            .adjustmentCoCode4Amount(UPDATED_ADJUSTMENT_CO_CODE_4_AMOUNT)
            .adjustmentCrCode1(UPDATED_ADJUSTMENT_CR_CODE_1)
            .adjustmentCrCode1Amount(UPDATED_ADJUSTMENT_CR_CODE_1_AMOUNT)
            .adjustmentCrCode2(UPDATED_ADJUSTMENT_CR_CODE_2)
            .adjustmentCrCode2Amount(UPDATED_ADJUSTMENT_CR_CODE_2_AMOUNT)
            .adjustmentCrCode3(UPDATED_ADJUSTMENT_CR_CODE_3)
            .adjustmentCrCode3Amount(UPDATED_ADJUSTMENT_CR_CODE_3_AMOUNT)
            .adjustmentCrCode4(UPDATED_ADJUSTMENT_CR_CODE_4)
            .adjustmentCrCode4Amount(UPDATED_ADJUSTMENT_CR_CODE_4_AMOUNT)
            .adjustmentOaCode1(UPDATED_ADJUSTMENT_OA_CODE_1)
            .adjustmentOaCode1Amount(UPDATED_ADJUSTMENT_OA_CODE_1_AMOUNT)
            .adjustmentOaCode2(UPDATED_ADJUSTMENT_OA_CODE_2)
            .adjustmentOaCode2Amount(UPDATED_ADJUSTMENT_OA_CODE_2_AMOUNT)
            .adjustmentOaCode3(UPDATED_ADJUSTMENT_OA_CODE_3)
            .adjustmentOaCode3Amount(UPDATED_ADJUSTMENT_OA_CODE_3_AMOUNT)
            .adjustmentOaCode4(UPDATED_ADJUSTMENT_OA_CODE_4)
            .adjustmentOaCode4Amount(UPDATED_ADJUSTMENT_OA_CODE_4_AMOUNT)
            .adjustmentPiCode1(UPDATED_ADJUSTMENT_PI_CODE_1)
            .adjustmentPiCode1Amount(UPDATED_ADJUSTMENT_PI_CODE_1_AMOUNT)
            .adjustmentPiCode2(UPDATED_ADJUSTMENT_PI_CODE_2)
            .adjustmentPiCode2Amount(UPDATED_ADJUSTMENT_PI_CODE_2_AMOUNT)
            .adjustmentPiCode3(UPDATED_ADJUSTMENT_PI_CODE_3)
            .adjustmentPiCode3Amount(UPDATED_ADJUSTMENT_PI_CODE_3_AMOUNT)
            .adjustmentPiCode4(UPDATED_ADJUSTMENT_PI_CODE_4)
            .adjustmentPiCode4Amount(UPDATED_ADJUSTMENT_PI_CODE_4_AMOUNT)
            .providerPaymentAmount(UPDATED_PROVIDER_PAYMENT_AMOUNT)
            .claimCob835MasterId(UPDATED_CLAIM_COB_835_MASTER_ID)
            .status(UPDATED_STATUS)
            .serviceDateTo(UPDATED_SERVICE_DATE_TO)
            .unitCount(UPDATED_UNIT_COUNT)
            .claimsCob835DetailsUuid(UPDATED_CLAIMS_COB_835_DETAILS_UUID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedClaimsCob835Details.getClaimCob835DetailId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedClaimsCob835Details))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ClaimsCob835Details in the database
        List<ClaimsCob835Details> claimsCob835DetailsList = claimsCob835DetailsRepository.findAll().collectList().block();
        assertThat(claimsCob835DetailsList).hasSize(databaseSizeBeforeUpdate);
        ClaimsCob835Details testClaimsCob835Details = claimsCob835DetailsList.get(claimsCob835DetailsList.size() - 1);
        assertThat(testClaimsCob835Details.getServiceDate()).isEqualTo(UPDATED_SERVICE_DATE);
        assertThat(testClaimsCob835Details.getAdjudicatedProcedureCode()).isEqualTo(UPDATED_ADJUDICATED_PROCEDURE_CODE);
        assertThat(testClaimsCob835Details.getAdjudicatedProcedureModifierCodes()).isEqualTo(UPDATED_ADJUDICATED_PROCEDURE_MODIFIER_CODES);
        assertThat(testClaimsCob835Details.getChargeAmount()).isEqualTo(UPDATED_CHARGE_AMOUNT);
        assertThat(testClaimsCob835Details.getAllowedAmount()).isEqualTo(UPDATED_ALLOWED_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentPrCode1()).isEqualTo(UPDATED_ADJUSTMENT_PR_CODE_1);
        assertThat(testClaimsCob835Details.getAdjustmentPrCode1Amount()).isEqualTo(UPDATED_ADJUSTMENT_PR_CODE_1_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentPrCode2()).isEqualTo(UPDATED_ADJUSTMENT_PR_CODE_2);
        assertThat(testClaimsCob835Details.getAdjustmentPrCode2Amount()).isEqualTo(UPDATED_ADJUSTMENT_PR_CODE_2_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentPrCode3()).isEqualTo(UPDATED_ADJUSTMENT_PR_CODE_3);
        assertThat(testClaimsCob835Details.getAdjustmentPrCode3Amount()).isEqualTo(UPDATED_ADJUSTMENT_PR_CODE_3_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentPrCode4()).isEqualTo(UPDATED_ADJUSTMENT_PR_CODE_4);
        assertThat(testClaimsCob835Details.getAdjustmentPrCode4Amount()).isEqualTo(UPDATED_ADJUSTMENT_PR_CODE_4_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentCoCode1()).isEqualTo(UPDATED_ADJUSTMENT_CO_CODE_1);
        assertThat(testClaimsCob835Details.getAdjustmentCoCode1Amount()).isEqualTo(UPDATED_ADJUSTMENT_CO_CODE_1_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentCoCode2()).isEqualTo(UPDATED_ADJUSTMENT_CO_CODE_2);
        assertThat(testClaimsCob835Details.getAdjustmentCoCode2Amount()).isEqualTo(UPDATED_ADJUSTMENT_CO_CODE_2_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentCoCode3()).isEqualTo(UPDATED_ADJUSTMENT_CO_CODE_3);
        assertThat(testClaimsCob835Details.getAdjustmentCoCode3Amount()).isEqualTo(UPDATED_ADJUSTMENT_CO_CODE_3_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentCoCode4()).isEqualTo(UPDATED_ADJUSTMENT_CO_CODE_4);
        assertThat(testClaimsCob835Details.getAdjustmentCoCode4Amount()).isEqualTo(UPDATED_ADJUSTMENT_CO_CODE_4_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentCrCode1()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_1);
        assertThat(testClaimsCob835Details.getAdjustmentCrCode1Amount()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_1_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentCrCode2()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_2);
        assertThat(testClaimsCob835Details.getAdjustmentCrCode2Amount()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_2_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentCrCode3()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_3);
        assertThat(testClaimsCob835Details.getAdjustmentCrCode3Amount()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_3_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentCrCode4()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_4);
        assertThat(testClaimsCob835Details.getAdjustmentCrCode4Amount()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_4_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentOaCode1()).isEqualTo(UPDATED_ADJUSTMENT_OA_CODE_1);
        assertThat(testClaimsCob835Details.getAdjustmentOaCode1Amount()).isEqualTo(UPDATED_ADJUSTMENT_OA_CODE_1_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentOaCode2()).isEqualTo(UPDATED_ADJUSTMENT_OA_CODE_2);
        assertThat(testClaimsCob835Details.getAdjustmentOaCode2Amount()).isEqualTo(UPDATED_ADJUSTMENT_OA_CODE_2_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentOaCode3()).isEqualTo(UPDATED_ADJUSTMENT_OA_CODE_3);
        assertThat(testClaimsCob835Details.getAdjustmentOaCode3Amount()).isEqualTo(UPDATED_ADJUSTMENT_OA_CODE_3_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentOaCode4()).isEqualTo(UPDATED_ADJUSTMENT_OA_CODE_4);
        assertThat(testClaimsCob835Details.getAdjustmentOaCode4Amount()).isEqualTo(UPDATED_ADJUSTMENT_OA_CODE_4_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentPiCode1()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_1);
        assertThat(testClaimsCob835Details.getAdjustmentPiCode1Amount()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_1_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentPiCode2()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_2);
        assertThat(testClaimsCob835Details.getAdjustmentPiCode2Amount()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_2_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentPiCode3()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_3);
        assertThat(testClaimsCob835Details.getAdjustmentPiCode3Amount()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_3_AMOUNT);
        assertThat(testClaimsCob835Details.getAdjustmentPiCode4()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_4);
        assertThat(testClaimsCob835Details.getAdjustmentPiCode4Amount()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_4_AMOUNT);
        assertThat(testClaimsCob835Details.getProviderPaymentAmount()).isEqualTo(UPDATED_PROVIDER_PAYMENT_AMOUNT);
        assertThat(testClaimsCob835Details.getClaimCob835MasterId()).isEqualTo(UPDATED_CLAIM_COB_835_MASTER_ID);
        assertThat(testClaimsCob835Details.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testClaimsCob835Details.getServiceDateTo()).isEqualTo(UPDATED_SERVICE_DATE_TO);
        assertThat(testClaimsCob835Details.getUnitCount()).isEqualTo(UPDATED_UNIT_COUNT);
        assertThat(testClaimsCob835Details.getClaimsCob835DetailsUuid()).isEqualTo(UPDATED_CLAIMS_COB_835_DETAILS_UUID);
    }

    @Test
    void patchNonExistingClaimsCob835Details() throws Exception {
        int databaseSizeBeforeUpdate = claimsCob835DetailsRepository.findAll().collectList().block().size();
        claimsCob835Details.setClaimCob835DetailId(count.incrementAndGet());

        // Create the ClaimsCob835Details
        ClaimsCob835DetailsDTO claimsCob835DetailsDTO = claimsCob835DetailsMapper.toDto(claimsCob835Details);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, claimsCob835DetailsDTO.getClaimCob835DetailId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(claimsCob835DetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ClaimsCob835Details in the database
        List<ClaimsCob835Details> claimsCob835DetailsList = claimsCob835DetailsRepository.findAll().collectList().block();
        assertThat(claimsCob835DetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchClaimsCob835Details() throws Exception {
        int databaseSizeBeforeUpdate = claimsCob835DetailsRepository.findAll().collectList().block().size();
        claimsCob835Details.setClaimCob835DetailId(count.incrementAndGet());

        // Create the ClaimsCob835Details
        ClaimsCob835DetailsDTO claimsCob835DetailsDTO = claimsCob835DetailsMapper.toDto(claimsCob835Details);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(claimsCob835DetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ClaimsCob835Details in the database
        List<ClaimsCob835Details> claimsCob835DetailsList = claimsCob835DetailsRepository.findAll().collectList().block();
        assertThat(claimsCob835DetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamClaimsCob835Details() throws Exception {
        int databaseSizeBeforeUpdate = claimsCob835DetailsRepository.findAll().collectList().block().size();
        claimsCob835Details.setClaimCob835DetailId(count.incrementAndGet());

        // Create the ClaimsCob835Details
        ClaimsCob835DetailsDTO claimsCob835DetailsDTO = claimsCob835DetailsMapper.toDto(claimsCob835Details);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(claimsCob835DetailsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ClaimsCob835Details in the database
        List<ClaimsCob835Details> claimsCob835DetailsList = claimsCob835DetailsRepository.findAll().collectList().block();
        assertThat(claimsCob835DetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteClaimsCob835Details() {
        // Initialize the database
        claimsCob835DetailsRepository.save(claimsCob835Details).block();

        int databaseSizeBeforeDelete = claimsCob835DetailsRepository.findAll().collectList().block().size();

        // Delete the claimsCob835Details
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, claimsCob835Details.getClaimCob835DetailId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<ClaimsCob835Details> claimsCob835DetailsList = claimsCob835DetailsRepository.findAll().collectList().block();
        assertThat(claimsCob835DetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
