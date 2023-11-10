package com.sunknowledge.dme.rcm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sunknowledge.dme.rcm.IntegrationTest;
import com.sunknowledge.dme.rcm.domain.ClaimsCOB835Details;
import com.sunknowledge.dme.rcm.repository.ClaimsCOB835DetailsRepository;
import com.sunknowledge.dme.rcm.service.dto.ClaimsCOB835DetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.ClaimsCOB835DetailsMapper;
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
 * Integration tests for the {@link ClaimsCOB835DetailsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ClaimsCOB835DetailsResourceIT {

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

    private static final LocalDate DEFAULT_SERVICE_DATE_TO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SERVICE_DATE_TO = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_UNIT_COUNT = 1;
    private static final Integer UPDATED_UNIT_COUNT = 2;

    private static final UUID DEFAULT_CLAIMS_COB_835_DETAILS_UUID = UUID.randomUUID();
    private static final UUID UPDATED_CLAIMS_COB_835_DETAILS_UUID = UUID.randomUUID();

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_POST_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_POST_STATUS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/claims-cob-835-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{claimCob835DetailId}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ClaimsCOB835DetailsRepository claimsCOB835DetailsRepository;

    @Autowired
    private ClaimsCOB835DetailsMapper claimsCOB835DetailsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restClaimsCOB835DetailsMockMvc;

    private ClaimsCOB835Details claimsCOB835Details;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClaimsCOB835Details createEntity(EntityManager em) {
        ClaimsCOB835Details claimsCOB835Details = new ClaimsCOB835Details()
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
            .serviceDateTo(DEFAULT_SERVICE_DATE_TO)
            .unitCount(DEFAULT_UNIT_COUNT)
            .claimsCob835DetailsUuid(DEFAULT_CLAIMS_COB_835_DETAILS_UUID)
            .status(DEFAULT_STATUS)
            .postStatus(DEFAULT_POST_STATUS);
        return claimsCOB835Details;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClaimsCOB835Details createUpdatedEntity(EntityManager em) {
        ClaimsCOB835Details claimsCOB835Details = new ClaimsCOB835Details()
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
            .serviceDateTo(UPDATED_SERVICE_DATE_TO)
            .unitCount(UPDATED_UNIT_COUNT)
            .claimsCob835DetailsUuid(UPDATED_CLAIMS_COB_835_DETAILS_UUID)
            .status(UPDATED_STATUS)
            .postStatus(UPDATED_POST_STATUS);
        return claimsCOB835Details;
    }

    @BeforeEach
    public void initTest() {
        claimsCOB835Details = createEntity(em);
    }

    @Test
    @Transactional
    void createClaimsCOB835Details() throws Exception {
        int databaseSizeBeforeCreate = claimsCOB835DetailsRepository.findAll().size();
        // Create the ClaimsCOB835Details
        ClaimsCOB835DetailsDTO claimsCOB835DetailsDTO = claimsCOB835DetailsMapper.toDto(claimsCOB835Details);
        restClaimsCOB835DetailsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimsCOB835DetailsDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ClaimsCOB835Details in the database
        List<ClaimsCOB835Details> claimsCOB835DetailsList = claimsCOB835DetailsRepository.findAll();
        assertThat(claimsCOB835DetailsList).hasSize(databaseSizeBeforeCreate + 1);
        ClaimsCOB835Details testClaimsCOB835Details = claimsCOB835DetailsList.get(claimsCOB835DetailsList.size() - 1);
        assertThat(testClaimsCOB835Details.getServiceDate()).isEqualTo(DEFAULT_SERVICE_DATE);
        assertThat(testClaimsCOB835Details.getAdjudicatedProcedureCode()).isEqualTo(DEFAULT_ADJUDICATED_PROCEDURE_CODE);
        assertThat(testClaimsCOB835Details.getAdjudicatedProcedureModifierCodes()).isEqualTo(DEFAULT_ADJUDICATED_PROCEDURE_MODIFIER_CODES);
        assertThat(testClaimsCOB835Details.getChargeAmount()).isEqualTo(DEFAULT_CHARGE_AMOUNT);
        assertThat(testClaimsCOB835Details.getAllowedAmount()).isEqualTo(DEFAULT_ALLOWED_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentPrCode1()).isEqualTo(DEFAULT_ADJUSTMENT_PR_CODE_1);
        assertThat(testClaimsCOB835Details.getAdjustmentPrCode1Amount()).isEqualTo(DEFAULT_ADJUSTMENT_PR_CODE_1_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentPrCode2()).isEqualTo(DEFAULT_ADJUSTMENT_PR_CODE_2);
        assertThat(testClaimsCOB835Details.getAdjustmentPrCode2Amount()).isEqualTo(DEFAULT_ADJUSTMENT_PR_CODE_2_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentPrCode3()).isEqualTo(DEFAULT_ADJUSTMENT_PR_CODE_3);
        assertThat(testClaimsCOB835Details.getAdjustmentPrCode3Amount()).isEqualTo(DEFAULT_ADJUSTMENT_PR_CODE_3_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentPrCode4()).isEqualTo(DEFAULT_ADJUSTMENT_PR_CODE_4);
        assertThat(testClaimsCOB835Details.getAdjustmentPrCode4Amount()).isEqualTo(DEFAULT_ADJUSTMENT_PR_CODE_4_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentCoCode1()).isEqualTo(DEFAULT_ADJUSTMENT_CO_CODE_1);
        assertThat(testClaimsCOB835Details.getAdjustmentCoCode1Amount()).isEqualTo(DEFAULT_ADJUSTMENT_CO_CODE_1_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentCoCode2()).isEqualTo(DEFAULT_ADJUSTMENT_CO_CODE_2);
        assertThat(testClaimsCOB835Details.getAdjustmentCoCode2Amount()).isEqualTo(DEFAULT_ADJUSTMENT_CO_CODE_2_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentCoCode3()).isEqualTo(DEFAULT_ADJUSTMENT_CO_CODE_3);
        assertThat(testClaimsCOB835Details.getAdjustmentCoCode3Amount()).isEqualTo(DEFAULT_ADJUSTMENT_CO_CODE_3_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentCoCode4()).isEqualTo(DEFAULT_ADJUSTMENT_CO_CODE_4);
        assertThat(testClaimsCOB835Details.getAdjustmentCoCode4Amount()).isEqualTo(DEFAULT_ADJUSTMENT_CO_CODE_4_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentCrCode1()).isEqualTo(DEFAULT_ADJUSTMENT_CR_CODE_1);
        assertThat(testClaimsCOB835Details.getAdjustmentCrCode1Amount()).isEqualTo(DEFAULT_ADJUSTMENT_CR_CODE_1_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentCrCode2()).isEqualTo(DEFAULT_ADJUSTMENT_CR_CODE_2);
        assertThat(testClaimsCOB835Details.getAdjustmentCrCode2Amount()).isEqualTo(DEFAULT_ADJUSTMENT_CR_CODE_2_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentCrCode3()).isEqualTo(DEFAULT_ADJUSTMENT_CR_CODE_3);
        assertThat(testClaimsCOB835Details.getAdjustmentCrCode3Amount()).isEqualTo(DEFAULT_ADJUSTMENT_CR_CODE_3_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentCrCode4()).isEqualTo(DEFAULT_ADJUSTMENT_CR_CODE_4);
        assertThat(testClaimsCOB835Details.getAdjustmentCrCode4Amount()).isEqualTo(DEFAULT_ADJUSTMENT_CR_CODE_4_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentOaCode1()).isEqualTo(DEFAULT_ADJUSTMENT_OA_CODE_1);
        assertThat(testClaimsCOB835Details.getAdjustmentOaCode1Amount()).isEqualTo(DEFAULT_ADJUSTMENT_OA_CODE_1_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentOaCode2()).isEqualTo(DEFAULT_ADJUSTMENT_OA_CODE_2);
        assertThat(testClaimsCOB835Details.getAdjustmentOaCode2Amount()).isEqualTo(DEFAULT_ADJUSTMENT_OA_CODE_2_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentOaCode3()).isEqualTo(DEFAULT_ADJUSTMENT_OA_CODE_3);
        assertThat(testClaimsCOB835Details.getAdjustmentOaCode3Amount()).isEqualTo(DEFAULT_ADJUSTMENT_OA_CODE_3_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentOaCode4()).isEqualTo(DEFAULT_ADJUSTMENT_OA_CODE_4);
        assertThat(testClaimsCOB835Details.getAdjustmentOaCode4Amount()).isEqualTo(DEFAULT_ADJUSTMENT_OA_CODE_4_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentPiCode1()).isEqualTo(DEFAULT_ADJUSTMENT_PI_CODE_1);
        assertThat(testClaimsCOB835Details.getAdjustmentPiCode1Amount()).isEqualTo(DEFAULT_ADJUSTMENT_PI_CODE_1_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentPiCode2()).isEqualTo(DEFAULT_ADJUSTMENT_PI_CODE_2);
        assertThat(testClaimsCOB835Details.getAdjustmentPiCode2Amount()).isEqualTo(DEFAULT_ADJUSTMENT_PI_CODE_2_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentPiCode3()).isEqualTo(DEFAULT_ADJUSTMENT_PI_CODE_3);
        assertThat(testClaimsCOB835Details.getAdjustmentPiCode3Amount()).isEqualTo(DEFAULT_ADJUSTMENT_PI_CODE_3_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentPiCode4()).isEqualTo(DEFAULT_ADJUSTMENT_PI_CODE_4);
        assertThat(testClaimsCOB835Details.getAdjustmentPiCode4Amount()).isEqualTo(DEFAULT_ADJUSTMENT_PI_CODE_4_AMOUNT);
        assertThat(testClaimsCOB835Details.getProviderPaymentAmount()).isEqualTo(DEFAULT_PROVIDER_PAYMENT_AMOUNT);
        assertThat(testClaimsCOB835Details.getClaimCob835MasterId()).isEqualTo(DEFAULT_CLAIM_COB_835_MASTER_ID);
        assertThat(testClaimsCOB835Details.getServiceDateTo()).isEqualTo(DEFAULT_SERVICE_DATE_TO);
        assertThat(testClaimsCOB835Details.getUnitCount()).isEqualTo(DEFAULT_UNIT_COUNT);
        assertThat(testClaimsCOB835Details.getClaimsCob835DetailsUuid()).isEqualTo(DEFAULT_CLAIMS_COB_835_DETAILS_UUID);
        assertThat(testClaimsCOB835Details.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testClaimsCOB835Details.getPostStatus()).isEqualTo(DEFAULT_POST_STATUS);
    }

    @Test
    @Transactional
    void createClaimsCOB835DetailsWithExistingId() throws Exception {
        // Create the ClaimsCOB835Details with an existing ID
        claimsCOB835Details.setClaimCob835DetailId(1L);
        ClaimsCOB835DetailsDTO claimsCOB835DetailsDTO = claimsCOB835DetailsMapper.toDto(claimsCOB835Details);

        int databaseSizeBeforeCreate = claimsCOB835DetailsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restClaimsCOB835DetailsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimsCOB835DetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimsCOB835Details in the database
        List<ClaimsCOB835Details> claimsCOB835DetailsList = claimsCOB835DetailsRepository.findAll();
        assertThat(claimsCOB835DetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllClaimsCOB835Details() throws Exception {
        // Initialize the database
        claimsCOB835DetailsRepository.saveAndFlush(claimsCOB835Details);

        // Get all the claimsCOB835DetailsList
        restClaimsCOB835DetailsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=claimCob835DetailId,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].claimCob835DetailId").value(hasItem(claimsCOB835Details.getClaimCob835DetailId().intValue())))
            .andExpect(jsonPath("$.[*].serviceDate").value(hasItem(DEFAULT_SERVICE_DATE.toString())))
            .andExpect(jsonPath("$.[*].adjudicatedProcedureCode").value(hasItem(DEFAULT_ADJUDICATED_PROCEDURE_CODE)))
            .andExpect(jsonPath("$.[*].adjudicatedProcedureModifierCodes").value(hasItem(DEFAULT_ADJUDICATED_PROCEDURE_MODIFIER_CODES)))
            .andExpect(jsonPath("$.[*].chargeAmount").value(hasItem(DEFAULT_CHARGE_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].allowedAmount").value(hasItem(DEFAULT_ALLOWED_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].adjustmentPrCode1").value(hasItem(DEFAULT_ADJUSTMENT_PR_CODE_1)))
            .andExpect(jsonPath("$.[*].adjustmentPrCode1Amount").value(hasItem(DEFAULT_ADJUSTMENT_PR_CODE_1_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].adjustmentPrCode2").value(hasItem(DEFAULT_ADJUSTMENT_PR_CODE_2)))
            .andExpect(jsonPath("$.[*].adjustmentPrCode2Amount").value(hasItem(DEFAULT_ADJUSTMENT_PR_CODE_2_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].adjustmentPrCode3").value(hasItem(DEFAULT_ADJUSTMENT_PR_CODE_3)))
            .andExpect(jsonPath("$.[*].adjustmentPrCode3Amount").value(hasItem(DEFAULT_ADJUSTMENT_PR_CODE_3_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].adjustmentPrCode4").value(hasItem(DEFAULT_ADJUSTMENT_PR_CODE_4)))
            .andExpect(jsonPath("$.[*].adjustmentPrCode4Amount").value(hasItem(DEFAULT_ADJUSTMENT_PR_CODE_4_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].adjustmentCoCode1").value(hasItem(DEFAULT_ADJUSTMENT_CO_CODE_1)))
            .andExpect(jsonPath("$.[*].adjustmentCoCode1Amount").value(hasItem(DEFAULT_ADJUSTMENT_CO_CODE_1_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].adjustmentCoCode2").value(hasItem(DEFAULT_ADJUSTMENT_CO_CODE_2)))
            .andExpect(jsonPath("$.[*].adjustmentCoCode2Amount").value(hasItem(DEFAULT_ADJUSTMENT_CO_CODE_2_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].adjustmentCoCode3").value(hasItem(DEFAULT_ADJUSTMENT_CO_CODE_3)))
            .andExpect(jsonPath("$.[*].adjustmentCoCode3Amount").value(hasItem(DEFAULT_ADJUSTMENT_CO_CODE_3_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].adjustmentCoCode4").value(hasItem(DEFAULT_ADJUSTMENT_CO_CODE_4)))
            .andExpect(jsonPath("$.[*].adjustmentCoCode4Amount").value(hasItem(DEFAULT_ADJUSTMENT_CO_CODE_4_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].adjustmentCrCode1").value(hasItem(DEFAULT_ADJUSTMENT_CR_CODE_1)))
            .andExpect(jsonPath("$.[*].adjustmentCrCode1Amount").value(hasItem(DEFAULT_ADJUSTMENT_CR_CODE_1_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].adjustmentCrCode2").value(hasItem(DEFAULT_ADJUSTMENT_CR_CODE_2)))
            .andExpect(jsonPath("$.[*].adjustmentCrCode2Amount").value(hasItem(DEFAULT_ADJUSTMENT_CR_CODE_2_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].adjustmentCrCode3").value(hasItem(DEFAULT_ADJUSTMENT_CR_CODE_3)))
            .andExpect(jsonPath("$.[*].adjustmentCrCode3Amount").value(hasItem(DEFAULT_ADJUSTMENT_CR_CODE_3_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].adjustmentCrCode4").value(hasItem(DEFAULT_ADJUSTMENT_CR_CODE_4)))
            .andExpect(jsonPath("$.[*].adjustmentCrCode4Amount").value(hasItem(DEFAULT_ADJUSTMENT_CR_CODE_4_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].adjustmentOaCode1").value(hasItem(DEFAULT_ADJUSTMENT_OA_CODE_1)))
            .andExpect(jsonPath("$.[*].adjustmentOaCode1Amount").value(hasItem(DEFAULT_ADJUSTMENT_OA_CODE_1_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].adjustmentOaCode2").value(hasItem(DEFAULT_ADJUSTMENT_OA_CODE_2)))
            .andExpect(jsonPath("$.[*].adjustmentOaCode2Amount").value(hasItem(DEFAULT_ADJUSTMENT_OA_CODE_2_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].adjustmentOaCode3").value(hasItem(DEFAULT_ADJUSTMENT_OA_CODE_3)))
            .andExpect(jsonPath("$.[*].adjustmentOaCode3Amount").value(hasItem(DEFAULT_ADJUSTMENT_OA_CODE_3_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].adjustmentOaCode4").value(hasItem(DEFAULT_ADJUSTMENT_OA_CODE_4)))
            .andExpect(jsonPath("$.[*].adjustmentOaCode4Amount").value(hasItem(DEFAULT_ADJUSTMENT_OA_CODE_4_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].adjustmentPiCode1").value(hasItem(DEFAULT_ADJUSTMENT_PI_CODE_1)))
            .andExpect(jsonPath("$.[*].adjustmentPiCode1Amount").value(hasItem(DEFAULT_ADJUSTMENT_PI_CODE_1_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].adjustmentPiCode2").value(hasItem(DEFAULT_ADJUSTMENT_PI_CODE_2)))
            .andExpect(jsonPath("$.[*].adjustmentPiCode2Amount").value(hasItem(DEFAULT_ADJUSTMENT_PI_CODE_2_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].adjustmentPiCode3").value(hasItem(DEFAULT_ADJUSTMENT_PI_CODE_3)))
            .andExpect(jsonPath("$.[*].adjustmentPiCode3Amount").value(hasItem(DEFAULT_ADJUSTMENT_PI_CODE_3_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].adjustmentPiCode4").value(hasItem(DEFAULT_ADJUSTMENT_PI_CODE_4)))
            .andExpect(jsonPath("$.[*].adjustmentPiCode4Amount").value(hasItem(DEFAULT_ADJUSTMENT_PI_CODE_4_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].providerPaymentAmount").value(hasItem(DEFAULT_PROVIDER_PAYMENT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].claimCob835MasterId").value(hasItem(DEFAULT_CLAIM_COB_835_MASTER_ID.intValue())))
            .andExpect(jsonPath("$.[*].serviceDateTo").value(hasItem(DEFAULT_SERVICE_DATE_TO.toString())))
            .andExpect(jsonPath("$.[*].unitCount").value(hasItem(DEFAULT_UNIT_COUNT)))
            .andExpect(jsonPath("$.[*].claimsCob835DetailsUuid").value(hasItem(DEFAULT_CLAIMS_COB_835_DETAILS_UUID.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].postStatus").value(hasItem(DEFAULT_POST_STATUS)));
    }

    @Test
    @Transactional
    void getClaimsCOB835Details() throws Exception {
        // Initialize the database
        claimsCOB835DetailsRepository.saveAndFlush(claimsCOB835Details);

        // Get the claimsCOB835Details
        restClaimsCOB835DetailsMockMvc
            .perform(get(ENTITY_API_URL_ID, claimsCOB835Details.getClaimCob835DetailId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.claimCob835DetailId").value(claimsCOB835Details.getClaimCob835DetailId().intValue()))
            .andExpect(jsonPath("$.serviceDate").value(DEFAULT_SERVICE_DATE.toString()))
            .andExpect(jsonPath("$.adjudicatedProcedureCode").value(DEFAULT_ADJUDICATED_PROCEDURE_CODE))
            .andExpect(jsonPath("$.adjudicatedProcedureModifierCodes").value(DEFAULT_ADJUDICATED_PROCEDURE_MODIFIER_CODES))
            .andExpect(jsonPath("$.chargeAmount").value(DEFAULT_CHARGE_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.allowedAmount").value(DEFAULT_ALLOWED_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.adjustmentPrCode1").value(DEFAULT_ADJUSTMENT_PR_CODE_1))
            .andExpect(jsonPath("$.adjustmentPrCode1Amount").value(DEFAULT_ADJUSTMENT_PR_CODE_1_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.adjustmentPrCode2").value(DEFAULT_ADJUSTMENT_PR_CODE_2))
            .andExpect(jsonPath("$.adjustmentPrCode2Amount").value(DEFAULT_ADJUSTMENT_PR_CODE_2_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.adjustmentPrCode3").value(DEFAULT_ADJUSTMENT_PR_CODE_3))
            .andExpect(jsonPath("$.adjustmentPrCode3Amount").value(DEFAULT_ADJUSTMENT_PR_CODE_3_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.adjustmentPrCode4").value(DEFAULT_ADJUSTMENT_PR_CODE_4))
            .andExpect(jsonPath("$.adjustmentPrCode4Amount").value(DEFAULT_ADJUSTMENT_PR_CODE_4_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.adjustmentCoCode1").value(DEFAULT_ADJUSTMENT_CO_CODE_1))
            .andExpect(jsonPath("$.adjustmentCoCode1Amount").value(DEFAULT_ADJUSTMENT_CO_CODE_1_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.adjustmentCoCode2").value(DEFAULT_ADJUSTMENT_CO_CODE_2))
            .andExpect(jsonPath("$.adjustmentCoCode2Amount").value(DEFAULT_ADJUSTMENT_CO_CODE_2_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.adjustmentCoCode3").value(DEFAULT_ADJUSTMENT_CO_CODE_3))
            .andExpect(jsonPath("$.adjustmentCoCode3Amount").value(DEFAULT_ADJUSTMENT_CO_CODE_3_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.adjustmentCoCode4").value(DEFAULT_ADJUSTMENT_CO_CODE_4))
            .andExpect(jsonPath("$.adjustmentCoCode4Amount").value(DEFAULT_ADJUSTMENT_CO_CODE_4_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.adjustmentCrCode1").value(DEFAULT_ADJUSTMENT_CR_CODE_1))
            .andExpect(jsonPath("$.adjustmentCrCode1Amount").value(DEFAULT_ADJUSTMENT_CR_CODE_1_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.adjustmentCrCode2").value(DEFAULT_ADJUSTMENT_CR_CODE_2))
            .andExpect(jsonPath("$.adjustmentCrCode2Amount").value(DEFAULT_ADJUSTMENT_CR_CODE_2_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.adjustmentCrCode3").value(DEFAULT_ADJUSTMENT_CR_CODE_3))
            .andExpect(jsonPath("$.adjustmentCrCode3Amount").value(DEFAULT_ADJUSTMENT_CR_CODE_3_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.adjustmentCrCode4").value(DEFAULT_ADJUSTMENT_CR_CODE_4))
            .andExpect(jsonPath("$.adjustmentCrCode4Amount").value(DEFAULT_ADJUSTMENT_CR_CODE_4_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.adjustmentOaCode1").value(DEFAULT_ADJUSTMENT_OA_CODE_1))
            .andExpect(jsonPath("$.adjustmentOaCode1Amount").value(DEFAULT_ADJUSTMENT_OA_CODE_1_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.adjustmentOaCode2").value(DEFAULT_ADJUSTMENT_OA_CODE_2))
            .andExpect(jsonPath("$.adjustmentOaCode2Amount").value(DEFAULT_ADJUSTMENT_OA_CODE_2_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.adjustmentOaCode3").value(DEFAULT_ADJUSTMENT_OA_CODE_3))
            .andExpect(jsonPath("$.adjustmentOaCode3Amount").value(DEFAULT_ADJUSTMENT_OA_CODE_3_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.adjustmentOaCode4").value(DEFAULT_ADJUSTMENT_OA_CODE_4))
            .andExpect(jsonPath("$.adjustmentOaCode4Amount").value(DEFAULT_ADJUSTMENT_OA_CODE_4_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.adjustmentPiCode1").value(DEFAULT_ADJUSTMENT_PI_CODE_1))
            .andExpect(jsonPath("$.adjustmentPiCode1Amount").value(DEFAULT_ADJUSTMENT_PI_CODE_1_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.adjustmentPiCode2").value(DEFAULT_ADJUSTMENT_PI_CODE_2))
            .andExpect(jsonPath("$.adjustmentPiCode2Amount").value(DEFAULT_ADJUSTMENT_PI_CODE_2_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.adjustmentPiCode3").value(DEFAULT_ADJUSTMENT_PI_CODE_3))
            .andExpect(jsonPath("$.adjustmentPiCode3Amount").value(DEFAULT_ADJUSTMENT_PI_CODE_3_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.adjustmentPiCode4").value(DEFAULT_ADJUSTMENT_PI_CODE_4))
            .andExpect(jsonPath("$.adjustmentPiCode4Amount").value(DEFAULT_ADJUSTMENT_PI_CODE_4_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.providerPaymentAmount").value(DEFAULT_PROVIDER_PAYMENT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.claimCob835MasterId").value(DEFAULT_CLAIM_COB_835_MASTER_ID.intValue()))
            .andExpect(jsonPath("$.serviceDateTo").value(DEFAULT_SERVICE_DATE_TO.toString()))
            .andExpect(jsonPath("$.unitCount").value(DEFAULT_UNIT_COUNT))
            .andExpect(jsonPath("$.claimsCob835DetailsUuid").value(DEFAULT_CLAIMS_COB_835_DETAILS_UUID.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.postStatus").value(DEFAULT_POST_STATUS));
    }

    @Test
    @Transactional
    void getNonExistingClaimsCOB835Details() throws Exception {
        // Get the claimsCOB835Details
        restClaimsCOB835DetailsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewClaimsCOB835Details() throws Exception {
        // Initialize the database
        claimsCOB835DetailsRepository.saveAndFlush(claimsCOB835Details);

        int databaseSizeBeforeUpdate = claimsCOB835DetailsRepository.findAll().size();

        // Update the claimsCOB835Details
        ClaimsCOB835Details updatedClaimsCOB835Details = claimsCOB835DetailsRepository
            .findById(claimsCOB835Details.getClaimCob835DetailId())
            .get();
        // Disconnect from session so that the updates on updatedClaimsCOB835Details are not directly saved in db
        em.detach(updatedClaimsCOB835Details);
        updatedClaimsCOB835Details
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
            .serviceDateTo(UPDATED_SERVICE_DATE_TO)
            .unitCount(UPDATED_UNIT_COUNT)
            .claimsCob835DetailsUuid(UPDATED_CLAIMS_COB_835_DETAILS_UUID)
            .status(UPDATED_STATUS)
            .postStatus(UPDATED_POST_STATUS);
        ClaimsCOB835DetailsDTO claimsCOB835DetailsDTO = claimsCOB835DetailsMapper.toDto(updatedClaimsCOB835Details);

        restClaimsCOB835DetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, claimsCOB835DetailsDTO.getClaimCob835DetailId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimsCOB835DetailsDTO))
            )
            .andExpect(status().isOk());

        // Validate the ClaimsCOB835Details in the database
        List<ClaimsCOB835Details> claimsCOB835DetailsList = claimsCOB835DetailsRepository.findAll();
        assertThat(claimsCOB835DetailsList).hasSize(databaseSizeBeforeUpdate);
        ClaimsCOB835Details testClaimsCOB835Details = claimsCOB835DetailsList.get(claimsCOB835DetailsList.size() - 1);
        assertThat(testClaimsCOB835Details.getServiceDate()).isEqualTo(UPDATED_SERVICE_DATE);
        assertThat(testClaimsCOB835Details.getAdjudicatedProcedureCode()).isEqualTo(UPDATED_ADJUDICATED_PROCEDURE_CODE);
        assertThat(testClaimsCOB835Details.getAdjudicatedProcedureModifierCodes()).isEqualTo(UPDATED_ADJUDICATED_PROCEDURE_MODIFIER_CODES);
        assertThat(testClaimsCOB835Details.getChargeAmount()).isEqualTo(UPDATED_CHARGE_AMOUNT);
        assertThat(testClaimsCOB835Details.getAllowedAmount()).isEqualTo(UPDATED_ALLOWED_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentPrCode1()).isEqualTo(UPDATED_ADJUSTMENT_PR_CODE_1);
        assertThat(testClaimsCOB835Details.getAdjustmentPrCode1Amount()).isEqualTo(UPDATED_ADJUSTMENT_PR_CODE_1_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentPrCode2()).isEqualTo(UPDATED_ADJUSTMENT_PR_CODE_2);
        assertThat(testClaimsCOB835Details.getAdjustmentPrCode2Amount()).isEqualTo(UPDATED_ADJUSTMENT_PR_CODE_2_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentPrCode3()).isEqualTo(UPDATED_ADJUSTMENT_PR_CODE_3);
        assertThat(testClaimsCOB835Details.getAdjustmentPrCode3Amount()).isEqualTo(UPDATED_ADJUSTMENT_PR_CODE_3_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentPrCode4()).isEqualTo(UPDATED_ADJUSTMENT_PR_CODE_4);
        assertThat(testClaimsCOB835Details.getAdjustmentPrCode4Amount()).isEqualTo(UPDATED_ADJUSTMENT_PR_CODE_4_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentCoCode1()).isEqualTo(UPDATED_ADJUSTMENT_CO_CODE_1);
        assertThat(testClaimsCOB835Details.getAdjustmentCoCode1Amount()).isEqualTo(UPDATED_ADJUSTMENT_CO_CODE_1_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentCoCode2()).isEqualTo(UPDATED_ADJUSTMENT_CO_CODE_2);
        assertThat(testClaimsCOB835Details.getAdjustmentCoCode2Amount()).isEqualTo(UPDATED_ADJUSTMENT_CO_CODE_2_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentCoCode3()).isEqualTo(UPDATED_ADJUSTMENT_CO_CODE_3);
        assertThat(testClaimsCOB835Details.getAdjustmentCoCode3Amount()).isEqualTo(UPDATED_ADJUSTMENT_CO_CODE_3_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentCoCode4()).isEqualTo(UPDATED_ADJUSTMENT_CO_CODE_4);
        assertThat(testClaimsCOB835Details.getAdjustmentCoCode4Amount()).isEqualTo(UPDATED_ADJUSTMENT_CO_CODE_4_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentCrCode1()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_1);
        assertThat(testClaimsCOB835Details.getAdjustmentCrCode1Amount()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_1_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentCrCode2()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_2);
        assertThat(testClaimsCOB835Details.getAdjustmentCrCode2Amount()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_2_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentCrCode3()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_3);
        assertThat(testClaimsCOB835Details.getAdjustmentCrCode3Amount()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_3_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentCrCode4()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_4);
        assertThat(testClaimsCOB835Details.getAdjustmentCrCode4Amount()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_4_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentOaCode1()).isEqualTo(UPDATED_ADJUSTMENT_OA_CODE_1);
        assertThat(testClaimsCOB835Details.getAdjustmentOaCode1Amount()).isEqualTo(UPDATED_ADJUSTMENT_OA_CODE_1_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentOaCode2()).isEqualTo(UPDATED_ADJUSTMENT_OA_CODE_2);
        assertThat(testClaimsCOB835Details.getAdjustmentOaCode2Amount()).isEqualTo(UPDATED_ADJUSTMENT_OA_CODE_2_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentOaCode3()).isEqualTo(UPDATED_ADJUSTMENT_OA_CODE_3);
        assertThat(testClaimsCOB835Details.getAdjustmentOaCode3Amount()).isEqualTo(UPDATED_ADJUSTMENT_OA_CODE_3_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentOaCode4()).isEqualTo(UPDATED_ADJUSTMENT_OA_CODE_4);
        assertThat(testClaimsCOB835Details.getAdjustmentOaCode4Amount()).isEqualTo(UPDATED_ADJUSTMENT_OA_CODE_4_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentPiCode1()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_1);
        assertThat(testClaimsCOB835Details.getAdjustmentPiCode1Amount()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_1_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentPiCode2()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_2);
        assertThat(testClaimsCOB835Details.getAdjustmentPiCode2Amount()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_2_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentPiCode3()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_3);
        assertThat(testClaimsCOB835Details.getAdjustmentPiCode3Amount()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_3_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentPiCode4()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_4);
        assertThat(testClaimsCOB835Details.getAdjustmentPiCode4Amount()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_4_AMOUNT);
        assertThat(testClaimsCOB835Details.getProviderPaymentAmount()).isEqualTo(UPDATED_PROVIDER_PAYMENT_AMOUNT);
        assertThat(testClaimsCOB835Details.getClaimCob835MasterId()).isEqualTo(UPDATED_CLAIM_COB_835_MASTER_ID);
        assertThat(testClaimsCOB835Details.getServiceDateTo()).isEqualTo(UPDATED_SERVICE_DATE_TO);
        assertThat(testClaimsCOB835Details.getUnitCount()).isEqualTo(UPDATED_UNIT_COUNT);
        assertThat(testClaimsCOB835Details.getClaimsCob835DetailsUuid()).isEqualTo(UPDATED_CLAIMS_COB_835_DETAILS_UUID);
        assertThat(testClaimsCOB835Details.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testClaimsCOB835Details.getPostStatus()).isEqualTo(UPDATED_POST_STATUS);
    }

    @Test
    @Transactional
    void putNonExistingClaimsCOB835Details() throws Exception {
        int databaseSizeBeforeUpdate = claimsCOB835DetailsRepository.findAll().size();
        claimsCOB835Details.setClaimCob835DetailId(count.incrementAndGet());

        // Create the ClaimsCOB835Details
        ClaimsCOB835DetailsDTO claimsCOB835DetailsDTO = claimsCOB835DetailsMapper.toDto(claimsCOB835Details);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaimsCOB835DetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, claimsCOB835DetailsDTO.getClaimCob835DetailId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimsCOB835DetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimsCOB835Details in the database
        List<ClaimsCOB835Details> claimsCOB835DetailsList = claimsCOB835DetailsRepository.findAll();
        assertThat(claimsCOB835DetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchClaimsCOB835Details() throws Exception {
        int databaseSizeBeforeUpdate = claimsCOB835DetailsRepository.findAll().size();
        claimsCOB835Details.setClaimCob835DetailId(count.incrementAndGet());

        // Create the ClaimsCOB835Details
        ClaimsCOB835DetailsDTO claimsCOB835DetailsDTO = claimsCOB835DetailsMapper.toDto(claimsCOB835Details);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimsCOB835DetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimsCOB835DetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimsCOB835Details in the database
        List<ClaimsCOB835Details> claimsCOB835DetailsList = claimsCOB835DetailsRepository.findAll();
        assertThat(claimsCOB835DetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamClaimsCOB835Details() throws Exception {
        int databaseSizeBeforeUpdate = claimsCOB835DetailsRepository.findAll().size();
        claimsCOB835Details.setClaimCob835DetailId(count.incrementAndGet());

        // Create the ClaimsCOB835Details
        ClaimsCOB835DetailsDTO claimsCOB835DetailsDTO = claimsCOB835DetailsMapper.toDto(claimsCOB835Details);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimsCOB835DetailsMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(claimsCOB835DetailsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ClaimsCOB835Details in the database
        List<ClaimsCOB835Details> claimsCOB835DetailsList = claimsCOB835DetailsRepository.findAll();
        assertThat(claimsCOB835DetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateClaimsCOB835DetailsWithPatch() throws Exception {
        // Initialize the database
        claimsCOB835DetailsRepository.saveAndFlush(claimsCOB835Details);

        int databaseSizeBeforeUpdate = claimsCOB835DetailsRepository.findAll().size();

        // Update the claimsCOB835Details using partial update
        ClaimsCOB835Details partialUpdatedClaimsCOB835Details = new ClaimsCOB835Details();
        partialUpdatedClaimsCOB835Details.setClaimCob835DetailId(claimsCOB835Details.getClaimCob835DetailId());

        partialUpdatedClaimsCOB835Details
            .serviceDate(UPDATED_SERVICE_DATE)
            .adjudicatedProcedureCode(UPDATED_ADJUDICATED_PROCEDURE_CODE)
            .adjudicatedProcedureModifierCodes(UPDATED_ADJUDICATED_PROCEDURE_MODIFIER_CODES)
            .chargeAmount(UPDATED_CHARGE_AMOUNT)
            .allowedAmount(UPDATED_ALLOWED_AMOUNT)
            .adjustmentPrCode1(UPDATED_ADJUSTMENT_PR_CODE_1)
            .adjustmentPrCode2Amount(UPDATED_ADJUSTMENT_PR_CODE_2_AMOUNT)
            .adjustmentPrCode4(UPDATED_ADJUSTMENT_PR_CODE_4)
            .adjustmentPrCode4Amount(UPDATED_ADJUSTMENT_PR_CODE_4_AMOUNT)
            .adjustmentCoCode3Amount(UPDATED_ADJUSTMENT_CO_CODE_3_AMOUNT)
            .adjustmentCoCode4(UPDATED_ADJUSTMENT_CO_CODE_4)
            .adjustmentCrCode1(UPDATED_ADJUSTMENT_CR_CODE_1)
            .adjustmentCrCode1Amount(UPDATED_ADJUSTMENT_CR_CODE_1_AMOUNT)
            .adjustmentCrCode3(UPDATED_ADJUSTMENT_CR_CODE_3)
            .adjustmentCrCode3Amount(UPDATED_ADJUSTMENT_CR_CODE_3_AMOUNT)
            .adjustmentCrCode4Amount(UPDATED_ADJUSTMENT_CR_CODE_4_AMOUNT)
            .adjustmentOaCode1Amount(UPDATED_ADJUSTMENT_OA_CODE_1_AMOUNT)
            .adjustmentOaCode2(UPDATED_ADJUSTMENT_OA_CODE_2)
            .adjustmentOaCode3(UPDATED_ADJUSTMENT_OA_CODE_3)
            .adjustmentOaCode4(UPDATED_ADJUSTMENT_OA_CODE_4)
            .adjustmentPiCode1(UPDATED_ADJUSTMENT_PI_CODE_1)
            .adjustmentPiCode2(UPDATED_ADJUSTMENT_PI_CODE_2)
            .adjustmentPiCode3(UPDATED_ADJUSTMENT_PI_CODE_3)
            .adjustmentPiCode4(UPDATED_ADJUSTMENT_PI_CODE_4)
            .claimsCob835DetailsUuid(UPDATED_CLAIMS_COB_835_DETAILS_UUID)
            .status(UPDATED_STATUS)
            .postStatus(UPDATED_POST_STATUS);

        restClaimsCOB835DetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClaimsCOB835Details.getClaimCob835DetailId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClaimsCOB835Details))
            )
            .andExpect(status().isOk());

        // Validate the ClaimsCOB835Details in the database
        List<ClaimsCOB835Details> claimsCOB835DetailsList = claimsCOB835DetailsRepository.findAll();
        assertThat(claimsCOB835DetailsList).hasSize(databaseSizeBeforeUpdate);
        ClaimsCOB835Details testClaimsCOB835Details = claimsCOB835DetailsList.get(claimsCOB835DetailsList.size() - 1);
        assertThat(testClaimsCOB835Details.getServiceDate()).isEqualTo(UPDATED_SERVICE_DATE);
        assertThat(testClaimsCOB835Details.getAdjudicatedProcedureCode()).isEqualTo(UPDATED_ADJUDICATED_PROCEDURE_CODE);
        assertThat(testClaimsCOB835Details.getAdjudicatedProcedureModifierCodes()).isEqualTo(UPDATED_ADJUDICATED_PROCEDURE_MODIFIER_CODES);
        assertThat(testClaimsCOB835Details.getChargeAmount()).isEqualTo(UPDATED_CHARGE_AMOUNT);
        assertThat(testClaimsCOB835Details.getAllowedAmount()).isEqualTo(UPDATED_ALLOWED_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentPrCode1()).isEqualTo(UPDATED_ADJUSTMENT_PR_CODE_1);
        assertThat(testClaimsCOB835Details.getAdjustmentPrCode1Amount()).isEqualTo(DEFAULT_ADJUSTMENT_PR_CODE_1_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentPrCode2()).isEqualTo(DEFAULT_ADJUSTMENT_PR_CODE_2);
        assertThat(testClaimsCOB835Details.getAdjustmentPrCode2Amount()).isEqualTo(UPDATED_ADJUSTMENT_PR_CODE_2_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentPrCode3()).isEqualTo(DEFAULT_ADJUSTMENT_PR_CODE_3);
        assertThat(testClaimsCOB835Details.getAdjustmentPrCode3Amount()).isEqualTo(DEFAULT_ADJUSTMENT_PR_CODE_3_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentPrCode4()).isEqualTo(UPDATED_ADJUSTMENT_PR_CODE_4);
        assertThat(testClaimsCOB835Details.getAdjustmentPrCode4Amount()).isEqualTo(UPDATED_ADJUSTMENT_PR_CODE_4_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentCoCode1()).isEqualTo(DEFAULT_ADJUSTMENT_CO_CODE_1);
        assertThat(testClaimsCOB835Details.getAdjustmentCoCode1Amount()).isEqualTo(DEFAULT_ADJUSTMENT_CO_CODE_1_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentCoCode2()).isEqualTo(DEFAULT_ADJUSTMENT_CO_CODE_2);
        assertThat(testClaimsCOB835Details.getAdjustmentCoCode2Amount()).isEqualTo(DEFAULT_ADJUSTMENT_CO_CODE_2_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentCoCode3()).isEqualTo(DEFAULT_ADJUSTMENT_CO_CODE_3);
        assertThat(testClaimsCOB835Details.getAdjustmentCoCode3Amount()).isEqualTo(UPDATED_ADJUSTMENT_CO_CODE_3_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentCoCode4()).isEqualTo(UPDATED_ADJUSTMENT_CO_CODE_4);
        assertThat(testClaimsCOB835Details.getAdjustmentCoCode4Amount()).isEqualTo(DEFAULT_ADJUSTMENT_CO_CODE_4_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentCrCode1()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_1);
        assertThat(testClaimsCOB835Details.getAdjustmentCrCode1Amount()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_1_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentCrCode2()).isEqualTo(DEFAULT_ADJUSTMENT_CR_CODE_2);
        assertThat(testClaimsCOB835Details.getAdjustmentCrCode2Amount()).isEqualTo(DEFAULT_ADJUSTMENT_CR_CODE_2_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentCrCode3()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_3);
        assertThat(testClaimsCOB835Details.getAdjustmentCrCode3Amount()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_3_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentCrCode4()).isEqualTo(DEFAULT_ADJUSTMENT_CR_CODE_4);
        assertThat(testClaimsCOB835Details.getAdjustmentCrCode4Amount()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_4_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentOaCode1()).isEqualTo(DEFAULT_ADJUSTMENT_OA_CODE_1);
        assertThat(testClaimsCOB835Details.getAdjustmentOaCode1Amount()).isEqualTo(UPDATED_ADJUSTMENT_OA_CODE_1_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentOaCode2()).isEqualTo(UPDATED_ADJUSTMENT_OA_CODE_2);
        assertThat(testClaimsCOB835Details.getAdjustmentOaCode2Amount()).isEqualTo(DEFAULT_ADJUSTMENT_OA_CODE_2_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentOaCode3()).isEqualTo(UPDATED_ADJUSTMENT_OA_CODE_3);
        assertThat(testClaimsCOB835Details.getAdjustmentOaCode3Amount()).isEqualTo(DEFAULT_ADJUSTMENT_OA_CODE_3_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentOaCode4()).isEqualTo(UPDATED_ADJUSTMENT_OA_CODE_4);
        assertThat(testClaimsCOB835Details.getAdjustmentOaCode4Amount()).isEqualTo(DEFAULT_ADJUSTMENT_OA_CODE_4_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentPiCode1()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_1);
        assertThat(testClaimsCOB835Details.getAdjustmentPiCode1Amount()).isEqualTo(DEFAULT_ADJUSTMENT_PI_CODE_1_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentPiCode2()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_2);
        assertThat(testClaimsCOB835Details.getAdjustmentPiCode2Amount()).isEqualTo(DEFAULT_ADJUSTMENT_PI_CODE_2_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentPiCode3()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_3);
        assertThat(testClaimsCOB835Details.getAdjustmentPiCode3Amount()).isEqualTo(DEFAULT_ADJUSTMENT_PI_CODE_3_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentPiCode4()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_4);
        assertThat(testClaimsCOB835Details.getAdjustmentPiCode4Amount()).isEqualTo(DEFAULT_ADJUSTMENT_PI_CODE_4_AMOUNT);
        assertThat(testClaimsCOB835Details.getProviderPaymentAmount()).isEqualTo(DEFAULT_PROVIDER_PAYMENT_AMOUNT);
        assertThat(testClaimsCOB835Details.getClaimCob835MasterId()).isEqualTo(DEFAULT_CLAIM_COB_835_MASTER_ID);
        assertThat(testClaimsCOB835Details.getServiceDateTo()).isEqualTo(DEFAULT_SERVICE_DATE_TO);
        assertThat(testClaimsCOB835Details.getUnitCount()).isEqualTo(DEFAULT_UNIT_COUNT);
        assertThat(testClaimsCOB835Details.getClaimsCob835DetailsUuid()).isEqualTo(UPDATED_CLAIMS_COB_835_DETAILS_UUID);
        assertThat(testClaimsCOB835Details.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testClaimsCOB835Details.getPostStatus()).isEqualTo(UPDATED_POST_STATUS);
    }

    @Test
    @Transactional
    void fullUpdateClaimsCOB835DetailsWithPatch() throws Exception {
        // Initialize the database
        claimsCOB835DetailsRepository.saveAndFlush(claimsCOB835Details);

        int databaseSizeBeforeUpdate = claimsCOB835DetailsRepository.findAll().size();

        // Update the claimsCOB835Details using partial update
        ClaimsCOB835Details partialUpdatedClaimsCOB835Details = new ClaimsCOB835Details();
        partialUpdatedClaimsCOB835Details.setClaimCob835DetailId(claimsCOB835Details.getClaimCob835DetailId());

        partialUpdatedClaimsCOB835Details
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
            .serviceDateTo(UPDATED_SERVICE_DATE_TO)
            .unitCount(UPDATED_UNIT_COUNT)
            .claimsCob835DetailsUuid(UPDATED_CLAIMS_COB_835_DETAILS_UUID)
            .status(UPDATED_STATUS)
            .postStatus(UPDATED_POST_STATUS);

        restClaimsCOB835DetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClaimsCOB835Details.getClaimCob835DetailId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClaimsCOB835Details))
            )
            .andExpect(status().isOk());

        // Validate the ClaimsCOB835Details in the database
        List<ClaimsCOB835Details> claimsCOB835DetailsList = claimsCOB835DetailsRepository.findAll();
        assertThat(claimsCOB835DetailsList).hasSize(databaseSizeBeforeUpdate);
        ClaimsCOB835Details testClaimsCOB835Details = claimsCOB835DetailsList.get(claimsCOB835DetailsList.size() - 1);
        assertThat(testClaimsCOB835Details.getServiceDate()).isEqualTo(UPDATED_SERVICE_DATE);
        assertThat(testClaimsCOB835Details.getAdjudicatedProcedureCode()).isEqualTo(UPDATED_ADJUDICATED_PROCEDURE_CODE);
        assertThat(testClaimsCOB835Details.getAdjudicatedProcedureModifierCodes()).isEqualTo(UPDATED_ADJUDICATED_PROCEDURE_MODIFIER_CODES);
        assertThat(testClaimsCOB835Details.getChargeAmount()).isEqualTo(UPDATED_CHARGE_AMOUNT);
        assertThat(testClaimsCOB835Details.getAllowedAmount()).isEqualTo(UPDATED_ALLOWED_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentPrCode1()).isEqualTo(UPDATED_ADJUSTMENT_PR_CODE_1);
        assertThat(testClaimsCOB835Details.getAdjustmentPrCode1Amount()).isEqualTo(UPDATED_ADJUSTMENT_PR_CODE_1_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentPrCode2()).isEqualTo(UPDATED_ADJUSTMENT_PR_CODE_2);
        assertThat(testClaimsCOB835Details.getAdjustmentPrCode2Amount()).isEqualTo(UPDATED_ADJUSTMENT_PR_CODE_2_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentPrCode3()).isEqualTo(UPDATED_ADJUSTMENT_PR_CODE_3);
        assertThat(testClaimsCOB835Details.getAdjustmentPrCode3Amount()).isEqualTo(UPDATED_ADJUSTMENT_PR_CODE_3_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentPrCode4()).isEqualTo(UPDATED_ADJUSTMENT_PR_CODE_4);
        assertThat(testClaimsCOB835Details.getAdjustmentPrCode4Amount()).isEqualTo(UPDATED_ADJUSTMENT_PR_CODE_4_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentCoCode1()).isEqualTo(UPDATED_ADJUSTMENT_CO_CODE_1);
        assertThat(testClaimsCOB835Details.getAdjustmentCoCode1Amount()).isEqualTo(UPDATED_ADJUSTMENT_CO_CODE_1_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentCoCode2()).isEqualTo(UPDATED_ADJUSTMENT_CO_CODE_2);
        assertThat(testClaimsCOB835Details.getAdjustmentCoCode2Amount()).isEqualTo(UPDATED_ADJUSTMENT_CO_CODE_2_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentCoCode3()).isEqualTo(UPDATED_ADJUSTMENT_CO_CODE_3);
        assertThat(testClaimsCOB835Details.getAdjustmentCoCode3Amount()).isEqualTo(UPDATED_ADJUSTMENT_CO_CODE_3_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentCoCode4()).isEqualTo(UPDATED_ADJUSTMENT_CO_CODE_4);
        assertThat(testClaimsCOB835Details.getAdjustmentCoCode4Amount()).isEqualTo(UPDATED_ADJUSTMENT_CO_CODE_4_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentCrCode1()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_1);
        assertThat(testClaimsCOB835Details.getAdjustmentCrCode1Amount()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_1_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentCrCode2()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_2);
        assertThat(testClaimsCOB835Details.getAdjustmentCrCode2Amount()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_2_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentCrCode3()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_3);
        assertThat(testClaimsCOB835Details.getAdjustmentCrCode3Amount()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_3_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentCrCode4()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_4);
        assertThat(testClaimsCOB835Details.getAdjustmentCrCode4Amount()).isEqualTo(UPDATED_ADJUSTMENT_CR_CODE_4_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentOaCode1()).isEqualTo(UPDATED_ADJUSTMENT_OA_CODE_1);
        assertThat(testClaimsCOB835Details.getAdjustmentOaCode1Amount()).isEqualTo(UPDATED_ADJUSTMENT_OA_CODE_1_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentOaCode2()).isEqualTo(UPDATED_ADJUSTMENT_OA_CODE_2);
        assertThat(testClaimsCOB835Details.getAdjustmentOaCode2Amount()).isEqualTo(UPDATED_ADJUSTMENT_OA_CODE_2_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentOaCode3()).isEqualTo(UPDATED_ADJUSTMENT_OA_CODE_3);
        assertThat(testClaimsCOB835Details.getAdjustmentOaCode3Amount()).isEqualTo(UPDATED_ADJUSTMENT_OA_CODE_3_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentOaCode4()).isEqualTo(UPDATED_ADJUSTMENT_OA_CODE_4);
        assertThat(testClaimsCOB835Details.getAdjustmentOaCode4Amount()).isEqualTo(UPDATED_ADJUSTMENT_OA_CODE_4_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentPiCode1()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_1);
        assertThat(testClaimsCOB835Details.getAdjustmentPiCode1Amount()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_1_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentPiCode2()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_2);
        assertThat(testClaimsCOB835Details.getAdjustmentPiCode2Amount()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_2_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentPiCode3()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_3);
        assertThat(testClaimsCOB835Details.getAdjustmentPiCode3Amount()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_3_AMOUNT);
        assertThat(testClaimsCOB835Details.getAdjustmentPiCode4()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_4);
        assertThat(testClaimsCOB835Details.getAdjustmentPiCode4Amount()).isEqualTo(UPDATED_ADJUSTMENT_PI_CODE_4_AMOUNT);
        assertThat(testClaimsCOB835Details.getProviderPaymentAmount()).isEqualTo(UPDATED_PROVIDER_PAYMENT_AMOUNT);
        assertThat(testClaimsCOB835Details.getClaimCob835MasterId()).isEqualTo(UPDATED_CLAIM_COB_835_MASTER_ID);
        assertThat(testClaimsCOB835Details.getServiceDateTo()).isEqualTo(UPDATED_SERVICE_DATE_TO);
        assertThat(testClaimsCOB835Details.getUnitCount()).isEqualTo(UPDATED_UNIT_COUNT);
        assertThat(testClaimsCOB835Details.getClaimsCob835DetailsUuid()).isEqualTo(UPDATED_CLAIMS_COB_835_DETAILS_UUID);
        assertThat(testClaimsCOB835Details.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testClaimsCOB835Details.getPostStatus()).isEqualTo(UPDATED_POST_STATUS);
    }

    @Test
    @Transactional
    void patchNonExistingClaimsCOB835Details() throws Exception {
        int databaseSizeBeforeUpdate = claimsCOB835DetailsRepository.findAll().size();
        claimsCOB835Details.setClaimCob835DetailId(count.incrementAndGet());

        // Create the ClaimsCOB835Details
        ClaimsCOB835DetailsDTO claimsCOB835DetailsDTO = claimsCOB835DetailsMapper.toDto(claimsCOB835Details);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaimsCOB835DetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, claimsCOB835DetailsDTO.getClaimCob835DetailId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claimsCOB835DetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimsCOB835Details in the database
        List<ClaimsCOB835Details> claimsCOB835DetailsList = claimsCOB835DetailsRepository.findAll();
        assertThat(claimsCOB835DetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchClaimsCOB835Details() throws Exception {
        int databaseSizeBeforeUpdate = claimsCOB835DetailsRepository.findAll().size();
        claimsCOB835Details.setClaimCob835DetailId(count.incrementAndGet());

        // Create the ClaimsCOB835Details
        ClaimsCOB835DetailsDTO claimsCOB835DetailsDTO = claimsCOB835DetailsMapper.toDto(claimsCOB835Details);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimsCOB835DetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claimsCOB835DetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClaimsCOB835Details in the database
        List<ClaimsCOB835Details> claimsCOB835DetailsList = claimsCOB835DetailsRepository.findAll();
        assertThat(claimsCOB835DetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamClaimsCOB835Details() throws Exception {
        int databaseSizeBeforeUpdate = claimsCOB835DetailsRepository.findAll().size();
        claimsCOB835Details.setClaimCob835DetailId(count.incrementAndGet());

        // Create the ClaimsCOB835Details
        ClaimsCOB835DetailsDTO claimsCOB835DetailsDTO = claimsCOB835DetailsMapper.toDto(claimsCOB835Details);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClaimsCOB835DetailsMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(claimsCOB835DetailsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ClaimsCOB835Details in the database
        List<ClaimsCOB835Details> claimsCOB835DetailsList = claimsCOB835DetailsRepository.findAll();
        assertThat(claimsCOB835DetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteClaimsCOB835Details() throws Exception {
        // Initialize the database
        claimsCOB835DetailsRepository.saveAndFlush(claimsCOB835Details);

        int databaseSizeBeforeDelete = claimsCOB835DetailsRepository.findAll().size();

        // Delete the claimsCOB835Details
        restClaimsCOB835DetailsMockMvc
            .perform(
                delete(ENTITY_API_URL_ID, claimsCOB835Details.getClaimCob835DetailId()).with(csrf()).accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ClaimsCOB835Details> claimsCOB835DetailsList = claimsCOB835DetailsRepository.findAll();
        assertThat(claimsCOB835DetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
