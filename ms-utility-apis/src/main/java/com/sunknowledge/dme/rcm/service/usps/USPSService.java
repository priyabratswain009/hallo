package com.sunknowledge.dme.rcm.service.usps;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.AddressVerificationResponse;
import com.sunknowledge.dme.rcm.dto.usps.*;
import com.sunknowledge.dme.rcm.exception.USPSExceptionHandler;
import com.sunknowledge.dme.rcm.repository.usps.AddressVerificationResponseRepo;
import com.sunknowledge.dme.rcm.utils.ApplicationConstants;
import com.sunknowledge.dme.rcm.utils.ApplicationMapFilters;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;

@Service
public class USPSService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient webClient;

    @Autowired
    private AddressVerificationResponseRepo addressVerificationResponseRepository;

    public ServiceOutcome<AddressValidateResponse> addressValidateRequest(AddressInput address) throws ExecutionException, InterruptedException {
        AddressValidateResponse addressResponse = new AddressValidateResponse();
        String url = null;
        HttpEntity<String> requestEntity = null;
        ServiceOutcome<AddressValidateResponse> responseServiceOutcome = new ServiceOutcome<AddressValidateResponse>();
        try {
            addressValidation(address);
            AddressValidateRequest addressValidateRequest = new AddressValidateRequest();
            Address addr = new Address();
            addr.setAddress1(address.getAddress1());
            addr.setAddress2(address.getAddress2());
            addr.setCity(address.getCity());
            addr.setState(address.getState());
            addr.setZip4(address.getZip4());
            addr.setZip5(address.getZip5());

            addressValidateRequest.setAddress(addr);
            addressValidateRequest.setRevision("1");
            addressValidateRequest.setUserId(ApplicationConstants.USPS_USERID);
            String addressRequestXML = addressValidateRequest.generateXML();
            addressRequestXML = addressRequestXML.replaceAll("(<\\?xml.*?\\?>)", "");

            url = ApplicationConstants.USPS_URL + "?" + "API=" + ApplicationConstants.USPS_API_NAME + "&XML=" + addressRequestXML;
            System.out.println("REQUEST URL:" + url);
            HttpHeaders headers = new HttpHeaders();
            requestEntity = new HttpEntity<>(headers);
        } catch (Exception e) {
            USPSExceptionHandler.processOnError(e);
        }
        AddressValidateResponse addressValidateResponse = webClient.post().uri(url)
            .retrieve()
            .bodyToMono(AddressValidateResponse.class)
            .toFuture().get();

        System.out.println("============addressValidateResponse============"+addressValidateResponse);

        addressResponse = new AddressValidateResponse();
        if(addressValidateResponse != null){
            AddressInfoDTO addressInfoDTO = new AddressInfoDTO();
            addressInfoDTO.setRspAddressLine2(addressValidateResponse.getAddress().getAddress2());
            addressInfoDTO.setRspAddressLine1(addressValidateResponse.getAddress().getAddress1());
            addressInfoDTO.setRspCity(addressValidateResponse.getAddress().getCity());
            addressInfoDTO.setRspState(addressValidateResponse.getAddress().getState());
            addressInfoDTO.setRspZip4(addressValidateResponse.getAddress().getZip4());
            addressInfoDTO.setRspZip5(addressValidateResponse.getAddress().getZip5());


            addressResponse.setAddress(addressValidateResponse.getAddress());
            addressResponse.setFootnotesDefinition(addressValidateResponse.getFootnotesDefinition());
            addressResponse.setDpvConfirmationDefinition(addressValidateResponse.getDpvConfirmationDefinition());
            String dpvFootnotes = ApplicationMapFilters.uspsDPVFootnotes(addressValidateResponse.getDpvFootnotesDefinition());
            addressResponse.setDpvFootnotesDefinition(dpvFootnotes);
            addressInfoDTO.setDpvFootnotesCode(addressValidateResponse.getDpvFootnotesDefinition());
            addressInfoDTO.setDpvFootnotesDescription(dpvFootnotes);

            if(addressValidateResponse.getDpvcmra() != null && addressValidateResponse.getDpvcmra().equals("Y")) {
                addressResponse.setDpvcmra("Address is found in the CMRA table.");
            }
            else if(addressValidateResponse.getDpvcmra() != null && addressValidateResponse.getDpvcmra().equals("N")) {
                addressResponse.setDpvcmra("Address is not found in the CMRA table.");
            }
            else
                addressResponse.setDpvcmra("Address is not found.");

            if(addressValidateResponse.getBusiness() != null && addressValidateResponse.getBusiness().equals("Y"))
                addressResponse.setBusiness("Business");
            else if(addressValidateResponse.getBusiness() != null && addressValidateResponse.getBusiness().equals("N"))
                addressResponse.setBusiness("Not Business");
            else
                addressResponse.setBusiness("Business Data Not Exists.");

            if(addressValidateResponse.getCentralDeliveryPoint() != null && addressValidateResponse.getCentralDeliveryPoint().equals("Y"))
                addressResponse.setCentralDeliveryPoint("Central Delivery Available.");
            else if(addressValidateResponse.getCentralDeliveryPoint() != null && addressValidateResponse.getCentralDeliveryPoint().equals("N"))
                addressResponse.setCentralDeliveryPoint("Central Delivery not Available.");
            else
                addressResponse.setCentralDeliveryPoint("Central Delivery Data Not Exists.");

            if(addressValidateResponse.getVacant() != null && addressValidateResponse.getVacant().equals("Y"))
                addressResponse.setVacant("Location is Occupied.");
            else if(addressValidateResponse.getVacant() != null && addressValidateResponse.getVacant().equals("N"))
                addressResponse.setVacant("Location is not Occupied.");
            else
                addressResponse.setVacant("Location Data not Exists.");

            System.out.println("========addressValidateResponse========="+addressValidateResponse);

            if(addressValidateResponse.getAddress().getDpvConfirmation() != null && addressValidateResponse.getAddress().getDpvConfirmation().equals("N")) {
                addressResponse.setMessage("Not a Valid Address");
                addressResponse.setMessageCode("NV");
                responseServiceOutcome.setOutcome(true);
                responseServiceOutcome.setMessage("Not a Valid Address.");
                addressInfoDTO.setStatus("INVALID");
            }
            else if(addressValidateResponse.getAddress().getDpvConfirmation() != null && (addressValidateResponse.getAddress().getDpvConfirmation().equals("Y") || addressValidateResponse.getAddress().getDpvConfirmation().equals("D") || addressValidateResponse.getAddress().getDpvConfirmation().equals("S"))) {
                addressResponse.setMessage("Valid Address");
                addressResponse.setMessageCode("VA");
                responseServiceOutcome.setOutcome(true);
                responseServiceOutcome.setMessage("Valid Address.");
                addressInfoDTO.setStatus("VALID");
            }
            else{
                addressResponse.setMessage("Not Available");
                addressResponse.setMessageCode("NA");
                responseServiceOutcome.setOutcome(false);
                responseServiceOutcome.setMessage("Input data is not valid.");
                addressInfoDTO.setStatus("Input data is not valid.");
            }
            responseServiceOutcome.setData(addressResponse);

            AddressVerificationResponse addressInfo = new AddressVerificationResponse();
            BeanUtils.copyProperties(addressInfoDTO, addressInfo);
            if(addressInfo != null){
                System.out.println("addressInfo.getRspAddressLine2()====>"+addressInfo.getRspAddressLine2());
                System.out.println("addressInfo.getPatientId()====>"+address.getPatientId());
                AddressVerificationResponse addr = addressVerificationResponseRepository.getAddressVerificationResponseOnPatient(address.getPatientId());
                if(addr != null){
                    System.out.println("==================UPDATE====================");
                    addr.setPatientId(address.getPatientId());
                    addr.setReqAddressLine1(address.getAddress1());
                    addr.setReqAddressLine2(address.getAddress2());
                    addr.setReqCity(address.getCity());
                    addr.setReqState(address.getState());
                    addr.setReqZip4(address.getZip4());
                    addr.setReqZip5(address.getZip5());
                    addr.setReqDate(LocalDate.now());

                    addr.setRspAddressLine1(addressInfoDTO.getRspAddressLine1());
                    addr.setRspAddressLine2(addressInfoDTO.getRspAddressLine2());
                    addr.setRspCity(addressInfoDTO.getRspCity());
                    addr.setRspState(addressInfoDTO.getRspState());
                    addr.setRspZip4(addressInfoDTO.getRspZip4());
                    addr.setRspZip5(addressInfoDTO.getRspZip5());
                    addr.setRspDate(LocalDate.now());
                    addr.setDpvFootnotesCode(addressInfoDTO.getDpvFootnotesCode());
                    addr.setDpvFootnotesDescription(addressInfoDTO.getDpvFootnotesDescription());
                    addr.setStatus(addressInfoDTO.getStatus());
                    addressVerificationResponseRepository.save(addr);
                }
                else{
                    System.out.println("==================SAVE====================");
                    addressInfo.setPatientId(address.getPatientId());
                    addressInfo.setReqAddressLine1(address.getAddress1());
                    addressInfo.setReqAddressLine2(address.getAddress2());
                    addressInfo.setReqCity(address.getCity());
                    addressInfo.setReqState(address.getState());
                    addressInfo.setReqZip4(address.getZip4());
                    addressInfo.setReqZip5(address.getZip5());
                    addressInfo.setReqDate(LocalDate.now());

                    addressInfo.setRspAddressLine1(addressInfoDTO.getRspAddressLine1());
                    addressInfo.setRspAddressLine2(addressInfoDTO.getRspAddressLine2());
                    addressInfo.setRspCity(addressInfoDTO.getRspCity());
                    addressInfo.setRspState(addressInfoDTO.getRspState());
                    addressInfo.setRspZip4(addressInfoDTO.getRspZip4());
                    addressInfo.setRspZip5(addressInfoDTO.getRspZip5());
                    addressInfo.setRspDate(LocalDate.now());
                    addressInfo.setDpvFootnotesCode(addressInfoDTO.getDpvFootnotesCode());
                    addressInfo.setDpvFootnotesDescription(addressInfoDTO.getDpvFootnotesDescription());
                    addressInfo.setStatus(addressInfoDTO.getStatus());
                    addressVerificationResponseRepository.save(addressInfo);
                }
            }
        }
        else {
            Address noAddress = new Address();
            noAddress.setFirmName("NA");
            noAddress.setAddress1("NA");
            noAddress.setAddress2("NA");
            noAddress.setCity("NA");
            noAddress.setState("NA");
            noAddress.setUrbanization("NA");
            noAddress.setZip5("NA");
            noAddress.setZip4("NA");
            noAddress.setDeliveryPoint("NA");
            noAddress.setCarrierRoute("NA");
            addressResponse.setAddress(noAddress);
            addressResponse.setBusiness("NA");
            addressResponse.setCentralDeliveryPoint("NA");
            addressResponse.setDpvcmra("NA");
            addressResponse.setDpvConfirmationDefinition("NA");
            addressResponse.setDpvFootnotesDefinition("NA");
            addressResponse.setFootnotesDefinition("NA");
            addressResponse.setMessage("ADDRESS NOT FOUND");
            addressResponse.setMessage("NA");
            addressResponse.setVacant("NA");

            responseServiceOutcome.setData(addressResponse);
            responseServiceOutcome.setOutcome(false);
            responseServiceOutcome.setMessage("Unable to fetch any address data.");
        }
        return responseServiceOutcome;
    }

    public AddressInput createUSPSAddress(AddressInput address) {
        try {
            if(address.getAddress1() == null)
                address.setAddress1("");
            if(address.getAddress2() == null)
                address.setAddress2("");
            if(address.getCity() == null)
                address.setCity("");
            if(address.getState() == null)
                address.setState("");
            if(address.getZip5() == null)
                address.setZip5("");
            if(address.getZip4() == null)
                address.setZip4("");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return address;
    }

    public AddressInput addressValidation(AddressInput address){
        if(address.getAddress2().equals(""))
            address.setAddress2(address.getCity());
        return address;
    }
}
