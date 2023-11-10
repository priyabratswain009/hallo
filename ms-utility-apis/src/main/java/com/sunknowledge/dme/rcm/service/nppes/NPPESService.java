package com.sunknowledge.dme.rcm.service.nppes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.DoctorMaster;
import com.sunknowledge.dme.rcm.domain.DoctorMedicalInfo;
import com.sunknowledge.dme.rcm.dto.nppes.Address;
import com.sunknowledge.dme.rcm.dto.nppes.ErrorMessage;
import com.sunknowledge.dme.rcm.dto.nppes.NPIFailureOutcome;
import com.sunknowledge.dme.rcm.dto.nppes.NPISuccessOutcome;
import com.sunknowledge.dme.rcm.dto.nppes.NPPESIndividualsInputCriteria;
import com.sunknowledge.dme.rcm.dto.nppes.NPPESInputNPICriteria;
import com.sunknowledge.dme.rcm.dto.nppes.ResultNPIDataset;
import com.sunknowledge.dme.rcm.dto.nppes.ResultNPIOutcome;
import com.sunknowledge.dme.rcm.dto.nppes.Taxonomy;
import com.sunknowledge.dme.rcm.repository.nppes.DoctorMasterRepo;
import com.sunknowledge.dme.rcm.repository.nppes.DoctorMedicalInfoRepo;
import com.sunknowledge.dme.rcm.service.common.DoctorMasterAndMedicalInfoProjection;
import com.sunknowledge.dme.rcm.service.dto.DoctorMasterDTO;
import com.sunknowledge.dme.rcm.utils.ApplicationConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class NPPESService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DoctorMasterRepo doctorMasterRepository;

    @Autowired
    private DoctorMedicalInfoRepo doctorMedicalInfoRepository;

    public String generateQueryParameter(NPPESIndividualsInputCriteria nppesIndividualsInputCriteria) {
        String queryParameters = "";
        NPPESIndividualsInputCriteria inputParameters = new NPPESIndividualsInputCriteria();
        if (!nppesIndividualsInputCriteria.getAddressPurpose().equals("") || nppesIndividualsInputCriteria.getAddressPurpose() != null) {
            inputParameters.setAddressPurpose(nppesIndividualsInputCriteria.getAddressPurpose());
            queryParameters = queryParameters + "address_purpose=" + nppesIndividualsInputCriteria.getAddressPurpose() + "&";
        }
        if (!nppesIndividualsInputCriteria.getCity().equals("") || nppesIndividualsInputCriteria.getCity() != null) {
            inputParameters.setCity(nppesIndividualsInputCriteria.getCity());
            queryParameters = queryParameters + "city=" + nppesIndividualsInputCriteria.getCity() + "&";
        }
        if (!nppesIndividualsInputCriteria.getCountryCode().equals("") || nppesIndividualsInputCriteria.getCountryCode() != null) {
            inputParameters.setCountryCode(nppesIndividualsInputCriteria.getCountryCode());
            queryParameters = queryParameters + "country_code=" + nppesIndividualsInputCriteria.getCountryCode() + "&";
        }
        if (!nppesIndividualsInputCriteria.getEnumerationType().equals("") || nppesIndividualsInputCriteria.getEnumerationType() != null) {
            inputParameters.setEnumerationType(nppesIndividualsInputCriteria.getEnumerationType());
            queryParameters = queryParameters + "enumeration_type=" + nppesIndividualsInputCriteria.getEnumerationType() + "&";
        }
        if (!nppesIndividualsInputCriteria.getFirstName().equals("") || nppesIndividualsInputCriteria.getFirstName() != null) {
            inputParameters.setFirstName(nppesIndividualsInputCriteria.getFirstName());
            queryParameters = queryParameters + "first_name=" + nppesIndividualsInputCriteria.getFirstName() + "&";
        }
        if (!nppesIndividualsInputCriteria.getLastName().equals("") || nppesIndividualsInputCriteria.getLastName() != null) {
            inputParameters.setLastName(nppesIndividualsInputCriteria.getLastName());
            queryParameters = queryParameters + "last_name=" + nppesIndividualsInputCriteria.getLastName() + "&";
        }
        if (!nppesIndividualsInputCriteria.getLimit().equals("") || nppesIndividualsInputCriteria.getLimit() != null) {
            inputParameters.setLimit(nppesIndividualsInputCriteria.getLimit());
            queryParameters = queryParameters + "limit=" + nppesIndividualsInputCriteria.getLimit() + "&";
        }
        if (!nppesIndividualsInputCriteria.getNpiNumber().equals("") || nppesIndividualsInputCriteria.getNpiNumber() != null) {
            inputParameters.setNpiNumber(nppesIndividualsInputCriteria.getNpiNumber());
            queryParameters = queryParameters + "number=" + nppesIndividualsInputCriteria.getNpiNumber() + "&";
        }
        if (!nppesIndividualsInputCriteria.getOrganizationName().equals("") || nppesIndividualsInputCriteria.getOrganizationName() != null) {
            inputParameters.setOrganizationName(nppesIndividualsInputCriteria.getOrganizationName());
            queryParameters = queryParameters + "organization_name=" + nppesIndividualsInputCriteria.getOrganizationName() + "&";
        }
        if (!nppesIndividualsInputCriteria.getPostalCode().equals("") || nppesIndividualsInputCriteria.getPostalCode() != null) {
            inputParameters.setPostalCode(nppesIndividualsInputCriteria.getPostalCode());
            queryParameters = queryParameters + "postal_code=" + nppesIndividualsInputCriteria.getPostalCode() + "&";
        }
        if (!nppesIndividualsInputCriteria.getState().equals("") || nppesIndividualsInputCriteria.getState() != null) {
            inputParameters.setState(nppesIndividualsInputCriteria.getState());
            queryParameters = queryParameters + "state=" + nppesIndividualsInputCriteria.getState() + "&";
        }
        if (!nppesIndividualsInputCriteria.getTaxonomyDescription().equals("") || nppesIndividualsInputCriteria.getTaxonomyDescription() != null) {
            inputParameters.setTaxonomyDescription(nppesIndividualsInputCriteria.getTaxonomyDescription());
            queryParameters = queryParameters + "taxonomy_description=" + nppesIndividualsInputCriteria.getTaxonomyDescription() + "&";
        }
        if (nppesIndividualsInputCriteria.getPretty() != null) {
            if (nppesIndividualsInputCriteria.getPretty().equals("true") || nppesIndividualsInputCriteria.getPretty().equals("on")) {
                inputParameters.setPretty("on");
                queryParameters = queryParameters + "pretty=" + inputParameters.getPretty() + "&";
            }
        }
        queryParameters = queryParameters + "version=" + ApplicationConstants.NPPES_VERSION;
        return queryParameters;
    }

    public ServiceOutcome<ResultNPIOutcome> persistNPPESNPIRecord(NPPESIndividualsInputCriteria nppesIndividualsInputCriteria) {
        NPISuccessOutcome npiSuccessOutcome = null;
        NPIFailureOutcome npiFailureOutcome = null;
        List<ResultNPIDataset> resultOutcome = null;
        ResultNPIOutcome resultNPIOutcome = new ResultNPIOutcome();
        ServiceOutcome<ResultNPIOutcome> routcome = new ServiceOutcome<ResultNPIOutcome>();
        try {
            String queryParameters = generateQueryParameter(nppesIndividualsInputCriteria);
            log.info("============query Params==================>" + queryParameters);
            String url = ApplicationConstants.NPPES_URL + "?" + queryParameters;
            log.info("==============URL==================>" + url);
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
            log.info("RESPONSE:" + response);
            ObjectMapper mapper = new ObjectMapper();
            if (response.getStatusCodeValue() == 200) {
                npiSuccessOutcome = mapper.readValue(response.getBody(), NPISuccessOutcome.class);
                System.out.println("Result Count:" + npiSuccessOutcome.getResultCount());

                resultOutcome = new ArrayList<ResultNPIDataset>();
                if (npiSuccessOutcome.getResultCount() != null) {
                    if (npiSuccessOutcome.getResultCount() > 0 && npiSuccessOutcome.getResults() != null) {
                        for (int i = 0; i < npiSuccessOutcome.getResults().size(); i++) {
                            ResultNPIDataset outcome = new ResultNPIDataset();
                            outcome.setResultCount(npiSuccessOutcome.getResultCount());
                            outcome.setNpiNumber(npiSuccessOutcome.getResults().get(i).getNumber().toString());
                            outcome.setNpiName(npiSuccessOutcome.getResults().get(i).getBasic().getName());
                            if (npiSuccessOutcome.getResults().get(i).getEnumerationType().equals("NPI-1"))
                                outcome.setEnumerationType("Individual NPI");
                            if (npiSuccessOutcome.getResults().get(i).getEnumerationType().equals("NPI-2"))
                                outcome.setEnumerationType("Organizational NPI");
                            for (int j = 0; j < npiSuccessOutcome.getResults().get(i).getAddresses().size(); j++) {
                                outcome.setAddress(npiSuccessOutcome.getResults().get(i).getAddresses().get(0).getAddress1() + " " + npiSuccessOutcome.getResults().get(i).getAddresses().get(0).getAddress2() + "\n" + npiSuccessOutcome.getResults().get(i).getAddresses().get(0).getCity() + "," + npiSuccessOutcome.getResults().get(i).getAddresses().get(0).getState() + " " + npiSuccessOutcome.getResults().get(i).getAddresses().get(0).getPostalCode());
                                outcome.setPhone(npiSuccessOutcome.getResults().get(i).getAddresses().get(0).getTelephoneNumber());
                            }
                            if (npiSuccessOutcome.getResults().get(i).getTaxonomies().size() >= 1) {
                                for (int k = 0; k < npiSuccessOutcome.getResults().get(i).getTaxonomies().size(); k++) {
                                    if (npiSuccessOutcome.getResults().get(i).getTaxonomies().get(k).getPrimary() == true) {
                                        Taxonomy taxonomy = new Taxonomy();
                                        taxonomy.setCode(npiSuccessOutcome.getResults().get(i).getTaxonomies().get(k).getCode());
                                        taxonomy.setDesc(npiSuccessOutcome.getResults().get(i).getTaxonomies().get(k).getDesc());
                                        taxonomy.setLicense(npiSuccessOutcome.getResults().get(i).getTaxonomies().get(k).getLicense());
                                        taxonomy.setPrimary(npiSuccessOutcome.getResults().get(i).getTaxonomies().get(k).getPrimary());
                                        taxonomy.setState(npiSuccessOutcome.getResults().get(i).getTaxonomies().get(k).getState());
                                        outcome.setPrimaryTaxonomy(taxonomy);
                                        if (!npiSuccessOutcome.getResults().get(i).getTaxonomies().get(k).getCode().equals("") && npiSuccessOutcome.getResults().get(i).getBasic().getStatus().equalsIgnoreCase("A"))
                                            outcome.setStatus(true);
                                        else
                                            outcome.setStatus(false);
                                    }
                                }
                                outcome.setTaxonomies(npiSuccessOutcome.getResults().get(i).getTaxonomies());
                            } else {
                                outcome.setPrimaryTaxonomy(null);
                                outcome.setStatus(false);
                            }
                            outcome.setBasicDetails(npiSuccessOutcome.getResults().get(i).getBasic());
                            outcome.setAddressList(npiSuccessOutcome.getResults().get(i).getAddresses());
                            outcome.setOtherNamesList(npiSuccessOutcome.getResults().get(i).getOtherNames());
                            outcome.setEndpoints(npiSuccessOutcome.getResults().get(i).getEndpoints());
                            outcome.setPracticeLocations(npiSuccessOutcome.getResults().get(i).getPracticeLocations());
                            resultOutcome.add(outcome);
                        }
                        resultNPIOutcome.setResultNPIDataset(resultOutcome);
                        routcome.setData(resultNPIOutcome);
                        routcome.setMessage("Doctor data fetched from external sources!");
                        routcome.setOutcome(true);
                    } else if (npiSuccessOutcome.getResultCount() == 0) {
                        ResultNPIDataset outcome = new ResultNPIDataset();
                        outcome.setResultCount(0);
                        outcome.setNpiNumber("");
                        outcome.setNpiName("");
                        outcome.setEnumerationType("");
                        outcome.setAddress("");
                        outcome.setPhone("");
                        outcome.setPrimaryTaxonomy(null);
                        outcome.setStatus(false);
                        outcome.setBasicDetails(null);
                        outcome.setAddressList(null);
                        outcome.setOtherNamesList(null);
                        outcome.setEndpoints(null);
                        outcome.setPracticeLocations(null);
                        resultOutcome.add(outcome);
                        routcome.setMessage("Unable to fetch NPI data due to looking for NPI-2 Organization data!!");
                        routcome.setOutcome(false);
                    } else {
                        npiFailureOutcome = mapper.readValue(response.getBody(), NPIFailureOutcome.class);
                        if (npiFailureOutcome != null) {
                            List<ErrorMessage> errorMessageList = new ArrayList<ErrorMessage>();
                            ResultNPIDataset outcome = new ResultNPIDataset();
                            for (ErrorMessage errorMsg : npiFailureOutcome.getErrors()) {
                                ErrorMessage errorMessage = new ErrorMessage();
                                errorMessage.setDescription(errorMsg.getDescription());
                                errorMessage.setField(errorMsg.getField());
                                errorMessage.setNumber(errorMsg.getNumber());
                                errorMessageList.add(errorMessage);
                            }
                            outcome.setError(errorMessageList);
                            resultOutcome.add(outcome);
                            routcome.setMessage("Improper Data Input.");
                            routcome.setOutcome(false);
                        }
                    }
                } else {
                    npiFailureOutcome = mapper.readValue(response.getBody(), NPIFailureOutcome.class);
                    if (npiFailureOutcome != null) {
                        List<ErrorMessage> errorMessageList = new ArrayList<ErrorMessage>();
                        ResultNPIDataset outcome = new ResultNPIDataset();
                        for (ErrorMessage errorMsg : npiFailureOutcome.getErrors()) {
                            ErrorMessage errorMessage = new ErrorMessage();
                            errorMessage.setDescription(errorMsg.getDescription());
                            errorMessage.setField(errorMsg.getField());
                            errorMessage.setNumber(errorMsg.getNumber());
                            errorMessageList.add(errorMessage);
                        }
                        outcome.setError(errorMessageList);
                        resultOutcome.add(outcome);
                        routcome.setMessage("Improper Data Input.");
                        routcome.setOutcome(false);
                    }
                }
            }
        } catch (Exception e) {
            routcome.setData(null);
            routcome.setMessage("NPI Registry Server is down, Please try again later!");
            routcome.setOutcome(false);
//            e.printStackTrace();
        }
        return routcome;
    }

    public ServiceOutcome<DoctorMasterDTO> searchNPPESNPIRecord(NPPESIndividualsInputCriteria nppesIndividualsInputCriteria) {
        NPISuccessOutcome npiSuccessOutcome = null;
        NPIFailureOutcome npiFailureOutcome = null;
        List<ResultNPIDataset> resultOutcome = null;
        ServiceOutcome<DoctorMasterDTO> routcome = new ServiceOutcome<DoctorMasterDTO>();
        try {
            String queryParameters = generateQueryParameter(nppesIndividualsInputCriteria);
            log.info("====queryParameters=====>" + queryParameters);
            String url = ApplicationConstants.NPPES_URL + "?" + queryParameters;
            log.info("=====URL=====>>>>" + url);
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
            log.info("RESPONSE:" + response);
            ObjectMapper mapper = new ObjectMapper();
            DoctorMasterDTO doctorMasterDTO = new DoctorMasterDTO();
            if (response.getStatusCodeValue() == 200) {
                npiSuccessOutcome = mapper.readValue(response.getBody(), NPISuccessOutcome.class);
                System.out.println("Result Count:" + npiSuccessOutcome.getResultCount());

                resultOutcome = new ArrayList<ResultNPIDataset>();
                if (npiSuccessOutcome.getResultCount() != null) {
                    if (npiSuccessOutcome.getResultCount() > 0 && npiSuccessOutcome.getResults() != null) {
                        npiSuccessOutcome.getResults().stream().forEach(npiDataSet -> {
                            doctorMasterDTO.setFirstName(npiDataSet.getBasic().getFirstName());
                            doctorMasterDTO.setMiddleName(npiDataSet.getBasic().getMiddleName());
                            doctorMasterDTO.setLastName(npiDataSet.getBasic().getLastName());
                            if (npiDataSet.getBasic().getNamePrefix() != null && npiDataSet.getBasic().getNamePrefix().equalsIgnoreCase("Dr.")) {
                                doctorMasterDTO.setTitle("Doctor");
                                doctorMasterDTO.setSuffix(npiDataSet.getBasic().getNamePrefix());
                            } else {
                                doctorMasterDTO.setTitle(npiDataSet.getBasic().getNameSuffix());
                                doctorMasterDTO.setSuffix(npiDataSet.getBasic().getNamePrefix());
                            }
                            doctorMasterDTO.setDoctorSpecialityId(null);
                            doctorMasterDTO.setAddlIdentifier(null);
                            doctorMasterDTO.setNpiNumber(npiDataSet.getNumber());
                            doctorMasterDTO.setGender(npiDataSet.getBasic().getGender());
                            doctorMasterDTO.setEnumerationDate(LocalDate.parse(npiDataSet.getBasic().getEnumerationDate()));
                            Address address = npiDataSet.getAddresses().stream()
                                .filter(addr -> addr.getAddressPurpose().equalsIgnoreCase("MAILING"))
                                .findFirst().get();
                            doctorMasterDTO.setCountryCode(address.getCountryCode());
                            doctorMasterDTO.setCountryName(address.getCountryName());
                            doctorMasterDTO.setAddressPurpose(address.getAddressPurpose());
                            doctorMasterDTO.setAddressType(address.getAddressType());
                            doctorMasterDTO.setAddressLine1(address.getAddress1());
                            doctorMasterDTO.setAddressLine2(address.getAddress2());
                            doctorMasterDTO.setCity(address.getCity());
                            doctorMasterDTO.setState(address.getState());
                            doctorMasterDTO.setPostalCode(address.getPostalCode());
                            doctorMasterDTO.setContactNo1(address.getTelephoneNumber());
                            doctorMasterDTO.setFaxNumber(address.getFaxNumber());
                            doctorMasterDTO.setEmail(null);
                            doctorMasterDTO.setRelationship(null);
                            if (npiDataSet.getTaxonomies().size() >= 1) {
                                npiDataSet.getTaxonomies().stream().forEach(taxonomies -> {
                                    if (taxonomies.getPrimary() == true) {
                                        doctorMasterDTO.setTaxonomyCode(taxonomies.getCode());
                                        doctorMasterDTO.setTaxonomyDesc(taxonomies.getDesc());
                                        doctorMasterDTO.setPracticeState(taxonomies.getState());
                                        doctorMasterDTO.setLicenceNo(taxonomies.getLicense());
                                        if (!taxonomies.getCode().equals("") && npiDataSet.getBasic().getStatus().equalsIgnoreCase("A"))
                                            routcome.setOutcome(true);
                                        else
                                            routcome.setOutcome(false);
                                    }
                                });
                            }
                            doctorMasterDTO.setCreatedById(0L);
                            doctorMasterDTO.setCreatedByName("System");
                            doctorMasterDTO.setCreatedDate(LocalDate.now());
                            doctorMasterDTO.setUpdatedById(0L);
                            doctorMasterDTO.setUpdatedByName("System");
                            doctorMasterDTO.setUpdatedDate(LocalDate.now());
                            doctorMasterDTO.setStatus("Active");

                            routcome.setData(doctorMasterDTO);
                            routcome.setMessage("Doctor data fetched from external sources!");
                        });
                    } else if (npiSuccessOutcome.getResultCount() == 0) {
                        ResultNPIDataset outcome = new ResultNPIDataset();
                        outcome.setResultCount(0);
                        outcome.setNpiNumber("");
                        outcome.setNpiName("");
                        outcome.setEnumerationType("");
                        outcome.setAddress("");
                        outcome.setPhone("");
                        outcome.setPrimaryTaxonomy(null);
                        outcome.setStatus(false);
                        outcome.setBasicDetails(null);
                        outcome.setAddressList(null);
                        outcome.setOtherNamesList(null);
                        outcome.setEndpoints(null);
                        outcome.setPracticeLocations(null);
                        resultOutcome.add(outcome);
                        routcome.setMessage("Unable to fetch NPI data due to looking for NPI-2 Organization data!!");
                        routcome.setOutcome(false);
                    } else {
                        npiFailureOutcome = mapper.readValue(response.getBody(), NPIFailureOutcome.class);
                        if (npiFailureOutcome != null) {
                            List<ErrorMessage> errorMessageList = new ArrayList<ErrorMessage>();
                            ResultNPIDataset outcome = new ResultNPIDataset();
                            for (ErrorMessage errorMsg : npiFailureOutcome.getErrors()) {
                                ErrorMessage errorMessage = new ErrorMessage();
                                errorMessage.setDescription(errorMsg.getDescription());
                                errorMessage.setField(errorMsg.getField());
                                errorMessage.setNumber(errorMsg.getNumber());
                                errorMessageList.add(errorMessage);
                            }
                            outcome.setError(errorMessageList);
                            resultOutcome.add(outcome);
                            routcome.setMessage("Improper Data Input.");
                            routcome.setOutcome(false);
                        }
                    }
                } else {
                    npiFailureOutcome = mapper.readValue(response.getBody(), NPIFailureOutcome.class);
                    if (npiFailureOutcome != null) {
                        List<ErrorMessage> errorMessageList = new ArrayList<ErrorMessage>();
                        ResultNPIDataset outcome = new ResultNPIDataset();
                        for (ErrorMessage errorMsg : npiFailureOutcome.getErrors()) {
                            ErrorMessage errorMessage = new ErrorMessage();
                            errorMessage.setDescription(errorMsg.getDescription());
                            errorMessage.setField(errorMsg.getField());
                            errorMessage.setNumber(errorMsg.getNumber());
                            errorMessageList.add(errorMessage);
                        }
                        outcome.setError(errorMessageList);
                        resultOutcome.add(outcome);
                        routcome.setMessage("Improper Data Input.");
                        routcome.setOutcome(false);
                    }
                }
            }
        } catch (Exception e) {
            routcome.setData(null);
            routcome.setMessage("NPI Registry Server is down, Please try again later!");
            routcome.setOutcome(false);
            e.printStackTrace();
        }
        return routcome;
    }

    public NPPESIndividualsInputCriteria prepareQueryParameter(String addressPurpose, String city, String countryCode, String enumerationType, String firstName, String lastName, String limit, String number,
                                                               String organizationName, String postalCode, String state, String taxonomyDescription, String pretty) {
        NPPESIndividualsInputCriteria nppesIndividualsInputCriteria = new NPPESIndividualsInputCriteria();
        if (addressPurpose != null)
            nppesIndividualsInputCriteria.setAddressPurpose(addressPurpose);
        if (city != null)
            nppesIndividualsInputCriteria.setCity(city);
        if (countryCode != null)
            nppesIndividualsInputCriteria.setCountryCode(countryCode);
        if (enumerationType != null)
            nppesIndividualsInputCriteria.setEnumerationType(enumerationType);
        if (firstName != null)
            nppesIndividualsInputCriteria.setFirstName(firstName);
        if (lastName != null)
            nppesIndividualsInputCriteria.setLastName(lastName);
        if (limit != null)
            nppesIndividualsInputCriteria.setLimit(limit);
        if (number != null)
            nppesIndividualsInputCriteria.setNpiNumber(number);
        if (organizationName != null)
            nppesIndividualsInputCriteria.setOrganizationName(organizationName);
        if (postalCode != null)
            nppesIndividualsInputCriteria.setPostalCode(postalCode);
        if (state != null)
            nppesIndividualsInputCriteria.setState(state);
        if (taxonomyDescription != null)
            nppesIndividualsInputCriteria.setTaxonomyDescription(taxonomyDescription);
        if (pretty != null) {
            if (pretty.equals("on")) {
                nppesIndividualsInputCriteria.setPretty("on");
            }
        }

        return nppesIndividualsInputCriteria;
    }

    public NPPESIndividualsInputCriteria setupNullValuesForInputCriterias(NPPESIndividualsInputCriteria nppesIndividualsInputCriteria) {
        nppesIndividualsInputCriteria.setAddressPurpose("");
        nppesIndividualsInputCriteria.setCity("");
        nppesIndividualsInputCriteria.setCountryCode("");
        nppesIndividualsInputCriteria.setEnumerationType("");
        nppesIndividualsInputCriteria.setFirstName("");
        nppesIndividualsInputCriteria.setLastName("");
        nppesIndividualsInputCriteria.setLimit("");
        nppesIndividualsInputCriteria.setNpiNumber("");
        nppesIndividualsInputCriteria.setOrganizationName("");
        nppesIndividualsInputCriteria.setPostalCode("");
        nppesIndividualsInputCriteria.setState("");
        nppesIndividualsInputCriteria.setTaxonomyDescription("");
        nppesIndividualsInputCriteria.setPretty("");
        return nppesIndividualsInputCriteria;
    }

    @Transactional
    public ServiceOutcome<DoctorMasterDTO> saveNUpdateNPIData(ServiceOutcome<ResultNPIOutcome> resultOutcome) {
        ServiceOutcome outcomeDoctorMaster = new ServiceOutcome();
        DoctorMasterDTO doctorMasterDTO = new DoctorMasterDTO();
        if (resultOutcome.getData().getResultNPIDataset().size() > 0) {
            resultOutcome.getData().getResultNPIDataset().stream()
                .forEach(npiDataSet -> {
                    DoctorMaster doctorNpi = doctorMasterRepository.getDoctorMasterByNpiNumber(npiDataSet.getNpiNumber());
                    if (doctorNpi != null) {
                        List<DoctorMedicalInfo> doctorMedicalInfos = new ArrayList<>();
                        doctorNpi.setFirstName(npiDataSet.getBasicDetails().getFirstName());
                        doctorNpi.setMiddleName(npiDataSet.getBasicDetails().getMiddleName());
                        doctorNpi.setLastName(npiDataSet.getBasicDetails().getLastName());
                        if (npiDataSet.getBasicDetails().getNamePrefix() != null && npiDataSet.getBasicDetails().getNamePrefix().equalsIgnoreCase("Dr.")) {
                            doctorNpi.setTitle("Doctor");
                            doctorNpi.setSuffix(npiDataSet.getBasicDetails().getNamePrefix());
                        } else {
                            doctorNpi.setTitle(npiDataSet.getBasicDetails().getNameSuffix());
                            doctorNpi.setSuffix(npiDataSet.getBasicDetails().getNamePrefix());
                        }
                        doctorNpi.setDoctorSpecialityId(null);
                        doctorNpi.setAddlIdentifier(null);
                        doctorNpi.setNpiNumber(npiDataSet.getNpiNumber());
                        doctorNpi.setGender(npiDataSet.getBasicDetails().getGender());
                        doctorNpi.setEnumerationDate(LocalDate.parse(npiDataSet.getBasicDetails().getEnumerationDate()));
//                        doctorNpi.setLastUpdated(npiDataSet.getBasicDetails().getLastUpdated());

                        Address address = npiDataSet.getAddressList().stream()
                            .filter(addr -> addr.getAddressPurpose().equalsIgnoreCase("MAILING"))
                            .findFirst().get();
                        doctorNpi.setCountryCode(address.getCountryCode());
                        doctorNpi.setCountryName(address.getCountryName());
                        doctorNpi.setAddressPurpose(address.getAddressPurpose());
                        doctorNpi.setAddressType(address.getAddressType());
                        doctorNpi.setAddressLine1(address.getAddress1());
                        doctorNpi.setAddressLine2(address.getAddress2());
                        doctorNpi.setCity(address.getCity());
                        doctorNpi.setState(address.getState());
                        doctorNpi.setPostalCode(address.getPostalCode());
                        doctorNpi.setContactNo1(address.getTelephoneNumber());
                        doctorNpi.setFaxNumber(address.getFaxNumber());
                        doctorNpi.setEmail(null);
                        doctorNpi.setRelationship(null);

                        doctorNpi.setTaxonomyCode(npiDataSet.getPrimaryTaxonomy().getCode());
                        doctorNpi.setTaxonomyGroup(npiDataSet.getPrimaryTaxonomy().getTaxonomyGroup());
                        doctorNpi.setTaxonomyDesc(npiDataSet.getPrimaryTaxonomy().getDesc());
                        doctorNpi.setPracticeState(npiDataSet.getPrimaryTaxonomy().getState());
                        doctorNpi.setLicenceNo(npiDataSet.getPrimaryTaxonomy().getLicense());

                        doctorNpi.setCreatedById(0L);
                        doctorNpi.setCreatedByName("System");
                        doctorNpi.setCreatedDate(LocalDate.now());
                        doctorNpi.setUpdatedById(0L);
                        doctorNpi.setUpdatedByName("System");
                        doctorNpi.setUpdatedDate(LocalDate.now());
                        doctorNpi.setStatus("Active");
                        DoctorMaster updateDoctorNpi = doctorMasterRepository.save(doctorNpi);
                        if (updateDoctorNpi != null) {
                            BeanUtils.copyProperties(updateDoctorNpi, doctorMasterDTO);
                            outcomeDoctorMaster.setData(doctorMasterDTO);
                            outcomeDoctorMaster.setOutcome(true);
                            outcomeDoctorMaster.setMessage("Doctor data fetched from internal sources!");
                            List<DoctorMedicalInfo> doctorMedicalInfoList = doctorMedicalInfoRepository.getDoctorsMedicalInfoListOnDoctor(updateDoctorNpi.getDoctorId());
                            doctorMedicalInfoList.stream().forEach(doctorMedicalInfo -> {
                                doctorMedicalInfoRepository.delete(doctorMedicalInfo);
                            });

                            if (updateDoctorNpi != null && npiDataSet.getTaxonomies().size() > 0) {
                                System.out.println("Taxonomies Size is greater that to 0==>" + npiDataSet.getTaxonomies().size());
                                npiDataSet.getTaxonomies().stream()
                                    .forEach(taxonomy -> {
                                        DoctorMedicalInfo doctorMedicalInfo = new DoctorMedicalInfo();
                                        doctorMedicalInfo.setDoctorId(updateDoctorNpi.getDoctorId());
//                                        doctorMedicalInfo.setDoctorMaster(updateDoctorNpi);
                                        doctorMedicalInfo.setCommericialNo(null);
                                        doctorMedicalInfo.setDea(null);
                                        doctorMedicalInfo.setLicExperiationDate(null);
                                        doctorMedicalInfo.setLocationId(null);
                                        doctorMedicalInfo.setNotes(null);
                                        doctorMedicalInfo.setUser2(null);
                                        doctorMedicalInfo.setUser1(null);
                                        doctorMedicalInfo.setUpin(null);
                                        doctorMedicalInfo.setPecosCertifiedStatus(null);

                                        doctorMedicalInfo.setTaxonomyDesc(taxonomy.getDesc());
                                        doctorMedicalInfo.setTaxonomyCode(taxonomy.getCode());
                                        doctorMedicalInfo.setPrimaryTaxonomy(taxonomy.getPrimary().toString());
                                        doctorMedicalInfo.setPracticeState(taxonomy.getState());
                                        doctorMedicalInfo.setLicenceNo(taxonomy.getLicense());
                                        if (taxonomy.getTaxonomyGroup() != null)
                                            doctorMedicalInfo.setTaxonomyGroup(taxonomy.getTaxonomyGroup());
                                        else
                                            doctorMedicalInfo.setTaxonomyGroup(null);

                                        doctorMedicalInfo.setCreatedById(0L);
                                        doctorMedicalInfo.setCreatedDate(LocalDate.now());
                                        doctorMedicalInfo.setCreatedByName("System");
                                        doctorMedicalInfo.setUpdatedById(0L);
                                        doctorMedicalInfo.setUpdatedByName("System");
                                        doctorMedicalInfo.setUpdatedDate(LocalDate.now());
                                        //doctorMedicalInfo.setDoctorMedicalInfoUuid();
                                        doctorMedicalInfoRepository.save(doctorMedicalInfo);
                                    });
                            }
                        }
                    } else {
                        List<DoctorMedicalInfo> doctorMedicalInfos = new ArrayList<>();
                        DoctorMaster doctorMaster = new DoctorMaster();
                        doctorMaster.setFirstName(npiDataSet.getBasicDetails().getFirstName());
                        doctorMaster.setMiddleName(npiDataSet.getBasicDetails().getMiddleName());
                        doctorMaster.setLastName(npiDataSet.getBasicDetails().getLastName());
                        if (npiDataSet.getBasicDetails().getNamePrefix() != null && npiDataSet.getBasicDetails().getNamePrefix().equalsIgnoreCase("Dr.")) {
                            doctorMaster.setTitle("Doctor");
                            doctorMaster.setSuffix(npiDataSet.getBasicDetails().getNamePrefix());
                        } else {
                            doctorMaster.setTitle(npiDataSet.getBasicDetails().getNameSuffix());
                            doctorMaster.setSuffix(npiDataSet.getBasicDetails().getNamePrefix());
                        }
                        doctorMaster.setDoctorSpecialityId(null);
                        doctorMaster.setAddlIdentifier(null);
                        doctorMaster.setNpiNumber(npiDataSet.getNpiNumber());
                        doctorMaster.setGender(npiDataSet.getBasicDetails().getGender());
                        doctorMaster.setEnumerationDate(LocalDate.parse(npiDataSet.getBasicDetails().getEnumerationDate()));
//                        doctorMaster.setLastUpdated(npiDataSet.getBasicDetails().getLastUpdated());

                        Address address = npiDataSet.getAddressList().stream()
                            .filter(addr -> addr.getAddressPurpose().equalsIgnoreCase("MAILING"))
                            .findFirst().get();
                        doctorMaster.setCountryCode(address.getCountryCode());
                        doctorMaster.setCountryName(address.getCountryName());
                        doctorMaster.setAddressPurpose(address.getAddressPurpose());
                        doctorMaster.setAddressType(address.getAddressType());
                        doctorMaster.setAddressLine1(address.getAddress1());
                        doctorMaster.setAddressLine2(address.getAddress2());
                        doctorMaster.setCity(address.getCity());
                        doctorMaster.setState(address.getState());
                        doctorMaster.setPostalCode(address.getPostalCode());
                        doctorMaster.setContactNo1(address.getTelephoneNumber());
                        doctorMaster.setFaxNumber(address.getFaxNumber());
                        doctorMaster.setEmail(null);
                        doctorMaster.setRelationship(null);

                        doctorMaster.setTaxonomyCode(npiDataSet.getPrimaryTaxonomy().getCode());
                        doctorMaster.setTaxonomyGroup(npiDataSet.getPrimaryTaxonomy().getTaxonomyGroup());
                        doctorMaster.setTaxonomyDesc(npiDataSet.getPrimaryTaxonomy().getDesc());
                        doctorMaster.setPracticeState(npiDataSet.getPrimaryTaxonomy().getState());
                        doctorMaster.setLicenceNo(npiDataSet.getPrimaryTaxonomy().getLicense());

                        doctorMaster.setCreatedById(0L);
                        doctorMaster.setCreatedByName("System");
                        doctorMaster.setCreatedDate(LocalDate.now());
                        doctorMaster.setUpdatedById(0L);
                        doctorMaster.setUpdatedByName("System");
                        doctorMaster.setUpdatedDate(LocalDate.now());
                        doctorMaster.setStatus("Active");

                        doctorMaster.setDoctorMasterUuid(UUID.randomUUID());
                        DoctorMaster saveDoctorNpi = doctorMasterRepository.save(doctorMaster);
                        if (saveDoctorNpi != null && npiDataSet.getTaxonomies().size() > 0) {
                            BeanUtils.copyProperties(saveDoctorNpi, doctorMasterDTO);
                            outcomeDoctorMaster.setData(doctorMasterDTO);
                            outcomeDoctorMaster.setOutcome(true);
                            outcomeDoctorMaster.setMessage("Doctor data fetched from internal sources!");
                            System.out.println("Taxonomies Size is greater that to 0==>" + npiDataSet.getTaxonomies().size());
                            npiDataSet.getTaxonomies().stream()
                                .forEach(taxonomy -> {
                                    DoctorMedicalInfo doctorMedicalInfo = new DoctorMedicalInfo();
                                    doctorMedicalInfo.setDoctorId(saveDoctorNpi.getDoctorId());
//                                    doctorMedicalInfo.setDoctorMaster(saveDoctorNpi);
                                    doctorMedicalInfo.setCommericialNo(null);
                                    doctorMedicalInfo.setDea(null);
                                    doctorMedicalInfo.setLicExperiationDate(null);
                                    doctorMedicalInfo.setLocationId(null);
                                    doctorMedicalInfo.setNotes(null);
                                    doctorMedicalInfo.setUser2(null);
                                    doctorMedicalInfo.setUser1(null);
                                    doctorMedicalInfo.setUpin(null);
                                    doctorMedicalInfo.setPecosCertifiedStatus(null);

                                    doctorMedicalInfo.setTaxonomyDesc(taxonomy.getDesc());
                                    doctorMedicalInfo.setTaxonomyCode(taxonomy.getCode());
                                    doctorMedicalInfo.setPrimaryTaxonomy(taxonomy.getPrimary().toString());
                                    doctorMedicalInfo.setPracticeState(taxonomy.getState());
                                    doctorMedicalInfo.setLicenceNo(taxonomy.getLicense());
                                    if (taxonomy.getTaxonomyGroup() != null)
                                        doctorMedicalInfo.setTaxonomyGroup(taxonomy.getTaxonomyGroup());
                                    else
                                        doctorMedicalInfo.setTaxonomyGroup(null);

                                    doctorMedicalInfo.setCreatedById(0L);
                                    doctorMedicalInfo.setCreatedDate(LocalDate.now());
                                    doctorMedicalInfo.setCreatedByName("System");
                                    doctorMedicalInfo.setUpdatedById(0L);
                                    doctorMedicalInfo.setUpdatedByName("System");
                                    doctorMedicalInfo.setUpdatedDate(LocalDate.now());
                                    doctorMedicalInfo.setDoctorMedicalInfoUuid(UUID.randomUUID());
                                    doctorMedicalInfoRepository.save(doctorMedicalInfo);
                                });
                        }
                    }
                });
        }
        return outcomeDoctorMaster;
    }

    public ServiceOutcome<DoctorMasterDTO> getNppesDoctorDetailsOnNpiNumber(NPPESInputNPICriteria nppesInputNPICriteria) {
        ServiceOutcome<DoctorMasterDTO> outcomeDoctorMaster = new ServiceOutcome<DoctorMasterDTO>();
        DoctorMasterDTO doctorMasterDTO = new DoctorMasterDTO();
        DoctorMaster doctorMaster = doctorMasterRepository.getDoctorMasterByNpiNumber(nppesInputNPICriteria.getNpiNumber());
        if (doctorMaster != null) {
            log.info("======================>Doctor Id======>" + doctorMaster.getDoctorId());
            BeanUtils.copyProperties(doctorMaster, doctorMasterDTO);
            outcomeDoctorMaster.setOutcome(true);
            outcomeDoctorMaster.setData(doctorMasterDTO);
            outcomeDoctorMaster.setMessage("Doctor data fetched from Internal Sources!");
        } else {
            log.info("======================>ELSE Doctor Id======>");
            outcomeDoctorMaster.setOutcome(false);
            outcomeDoctorMaster.setData(doctorMasterDTO);
            outcomeDoctorMaster.setMessage("NPI Registry Server is down/Data not exists, Please try again later!");
        }
        return outcomeDoctorMaster;
    }

    public ServiceOutcome<List<String>> getDoctorDetailsByName(String searchText) {
        ServiceOutcome<List<String>> outcomeDoctorDetails = new ServiceOutcome<List<String>>();
        List<String> doctorList = doctorMasterRepository.getDoctorDetailsByName(searchText);
        if (doctorList.size() > 0) {
            outcomeDoctorDetails.setOutcome(true);
            outcomeDoctorDetails.setMessage("Doctor's data accessed successfully!");
            outcomeDoctorDetails.setData(doctorList);
        } else {
            outcomeDoctorDetails.setOutcome(false);
            outcomeDoctorDetails.setMessage("Failure to access Doctor's data!");
            outcomeDoctorDetails.setData(null);
        }
        return outcomeDoctorDetails;
    }

    public ServiceOutcome<List<String>> getDoctorDetailsByNPINumber(String searchNPINumber) {
        ServiceOutcome<List<String>> outcomeDoctorDetails = new ServiceOutcome<List<String>>();
        List<String> doctorList = doctorMasterRepository.getDoctorDetailsByNPINumber(searchNPINumber);
        if (doctorList.size() > 0) {
            outcomeDoctorDetails.setOutcome(true);
            outcomeDoctorDetails.setMessage("Doctor's data accessed successfully!");
            outcomeDoctorDetails.setData(doctorList);
        } else {
            outcomeDoctorDetails.setOutcome(false);
            outcomeDoctorDetails.setMessage("Failure to access Doctor's data!");
            outcomeDoctorDetails.setData(null);
        }
        return outcomeDoctorDetails;
    }

    public ServiceOutcome<List<DoctorMaster>> getAllDoctorDetails() {
        ServiceOutcome<List<DoctorMaster>> outcomeDoctorDetails = new ServiceOutcome<List<DoctorMaster>>();
        List<DoctorMaster> doctorList = doctorMasterRepository.findAll();
        if (doctorList.size() > 0) {
            outcomeDoctorDetails.setOutcome(true);
            outcomeDoctorDetails.setMessage("Doctor's data accessed successfully!");
            outcomeDoctorDetails.setData(doctorList);
        } else {
            outcomeDoctorDetails.setOutcome(false);
            outcomeDoctorDetails.setMessage("Failure to access Doctor's data!");
            outcomeDoctorDetails.setData(null);
        }
        return outcomeDoctorDetails;
    }

    public List<DoctorMasterAndMedicalInfoProjection> getDoctorMasterWithMedicalInfo(Long doctorId) {

        return doctorMedicalInfoRepository.getDoctorMasterWithMedicalInfo(doctorId);
    }
}
