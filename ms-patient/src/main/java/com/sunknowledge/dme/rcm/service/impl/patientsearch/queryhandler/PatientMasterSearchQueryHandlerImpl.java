package com.sunknowledge.dme.rcm.service.impl.patientsearch.queryhandler;

import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.repository.PatientMasterSearchRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.PatientCombinedSearchOutputDTO;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.CheckingPatientExistanceByNameAndZipAndBranchAndDob;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientSearchByBasicInfoOrAddressOrBranch;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientSearchByCombinedInfo;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientSearchByPatientId;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientSearchByPatientIdNo;
import com.sunknowledge.dme.rcm.service.mapper.PatientMasterMapper;
import com.sunknowledge.dme.rcm.service.patientsearch.queryhandler.PatientMasterSearchQueryHandler;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class PatientMasterSearchQueryHandlerImpl implements PatientMasterSearchQueryHandler {

    @Autowired
    PatientMasterSearchRepositoryExtended patientMasterSearchRepository;

    private final PatientMasterMapper patientMasterMapper;

    public PatientMasterSearchQueryHandlerImpl(PatientMasterMapper patientMasterMapper) {
        this.patientMasterMapper = patientMasterMapper;
    }

//    public static String mergeName(String firstName, String middleName, String lastName) {
//        if (middleName == null && lastName == null) {
//            return firstName.trim();
//        } else if (firstName == null && lastName == null) {
//            return middleName.trim();
//        } else if (middleName == null && firstName == null) {
//            return lastName.trim();
//        } else if (firstName != null && lastName != null &&
//            middleName != null && !middleName.trim().equals("")) {
//            return firstName.trim() + " " + middleName.trim() + " " + lastName.trim();
//        } else if (firstName != null && lastName != null &&
//            (middleName == null || middleName.trim().equals(""))) {
//            return firstName.trim() + " " + lastName.trim();
//        } else {
//            return "NA";
//        }
//    }

    @Override
    public Flux<PatientMasterDTO> getPatientBySearchParametersQueryHandler(PatientSearchByBasicInfoOrAddressOrBranch obj) {

        if (obj.getName() != null && obj.getDob() != null &&
            !obj.getName().trim().equals("") && obj.getDob().isBefore(LocalDate.now())) {
            return patientMasterSearchRepository.findAll()
                .map(patientMasterMapper::toDto)
                .filter(x ->
                    (
                        CommonUtilities.mergeName(x.getPatientFirstName(), x.getPatientMiddleName(), x.getPatientLastName())
                            .contains(obj.getName().trim())
                            && x.getDob().compareTo(obj.getDob()) == 0
                    )
                        && x.getStatus().trim().equalsIgnoreCase("active")
                );
        } else if (obj.getName() != null && obj.getAddress() != null &&
            !obj.getName().trim().equals("") && !obj.getAddress().trim().equals("")) {
            return patientMasterSearchRepository.findAll()
                .map(patientMasterMapper::toDto)
                .filter(x ->
                    (
                        CommonUtilities.mergeName(x.getPatientFirstName(), x.getPatientMiddleName(), x.getPatientLastName())
                            .contains(obj.getName().trim())
                            && ((x.getAddressLine1() + x.getAddressLine2() + x.getCity() +
                            x.getState() + x.getCountry() + x.getZip())
                            .contains(obj.getAddress().trim()))
                    )
                        && x.getStatus().trim().equalsIgnoreCase("active")
                );
        } else if (obj.getName() != null && obj.getPhone() != null &&
            !obj.getName().trim().equals("") && !obj.getPhone().trim().equals("")) {
            return patientMasterSearchRepository.findAll()
                .map(patientMasterMapper::toDto)
                .filter(x ->
                    (
                        CommonUtilities.mergeName(x.getPatientFirstName(), x.getPatientMiddleName(), x.getPatientLastName())
                            .contains(obj.getName().trim())
                            && ((x.getContactNo1() + x.getContactNo2())
                            .contains(obj.getPhone().trim()))
                    )
                        && x.getStatus().trim().equalsIgnoreCase("active")
                );
        } else if (obj.getName() != null && obj.getBranchName() != null &&
            !obj.getName().trim().equals("") && !obj.getBranchName().trim().equals("")) {
            return patientMasterSearchRepository.findAll()
                .map(patientMasterMapper::toDto)
                .filter(x ->
                    (
                        CommonUtilities.mergeName(x.getPatientFirstName(), x.getPatientMiddleName(), x.getPatientLastName())
                            .contains(obj.getName().trim())
                            && x.getBranchName().contains(obj.getBranchName().trim())
                    )
                        && x.getStatus().trim().equalsIgnoreCase("active")
                );
        } else {
            return patientMasterSearchRepository.findAll()
                .map(patientMasterMapper::toDto)
                .filter(x ->
                    (
                        x.getPatientId().equals(obj.getPatientID()) ||
                            (obj.getName() != null && CommonUtilities.mergeName(x.getPatientFirstName(), x.getPatientMiddleName(), x.getPatientLastName())
                                .contains(obj.getName().trim()) && !obj.getName().trim().equals("")) ||
                            (obj.getDob() != null && x.getDob().compareTo(obj.getDob()) == 0) ||
                            (obj.getSsnNo() != null && x.getSsn().equalsIgnoreCase(obj.getSsnNo().trim())) ||
                            (obj.getAddress() != null && (x.getAddressLine1() + x.getAddressLine2() + x.getCity() +
                                x.getState() + x.getCountry() + x.getZip())
                                .contains(obj.getAddress().trim()) && !obj.getAddress().trim().equals("")) ||
                            (obj.getPhone() != null && (x.getContactNo1() + x.getContactNo2())
                                .contains(obj.getPhone().trim()) && !obj.getPhone().trim().equals(""))
                            || (obj.getBranchId() != null && x.getBranchId().equals(obj.getBranchId()))
                            || (obj.getBranchName() != null
                            && x.getBranchName().contains(obj.getBranchName().trim())
                            && !obj.getBranchName().trim().equals(""))
                    )
                        && x.getStatus().trim().equalsIgnoreCase("active")
                );
        }

    }

    @Override
    public Flux<PatientMasterDTO> checkPatientIsAlreadyExistOrNotQueryHandler(CheckingPatientExistanceByNameAndZipAndBranchAndDob obj) {
        return patientMasterSearchRepository.findAll()
            .map(patientMasterMapper::toDto)
            .filter(x ->
                (
                    CommonUtilities.mergeName(x.getPatientFirstName(), x.getPatientMiddleName(), x.getPatientLastName())
                        .trim().contains(obj.getName().trim()) &&
                        x.getDob().compareTo(obj.getDob()) == 0 &&
                        x.getZip().trim().equals(obj.getZip().trim()) &&
                        (x.getContactNo1() + x.getContactNo2()).trim().contains(obj.getPhone().trim()) &&
                        x.getBranchId().equals(obj.getBranchId()) &&
                        x.getStatus().trim().equalsIgnoreCase("active")
                )

            );
    }

    @SneakyThrows
    @Override
    public Flux<PatientMasterDTO> getPatientByPatientIdNoQueryHandler(PatientSearchByPatientIdNo obj) {
        return patientMasterSearchRepository.findAll()
            .map(patientMasterMapper::toDto)
            .filter(x ->
                (
                    x.getPatientIdNumber().equals(obj.getPatientIdNo().trim())
                        && x.getStatus().trim().equalsIgnoreCase("active")
                )
            );
    }

    @Override
    public String generatePatientIDNumber() {
        try {
            return patientMasterSearchRepository.generatePatientIDNumber().toFuture().get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Mono<Long> getIDByUUID(UUID patientUuid) {
        return patientMasterSearchRepository.getIDByUUID(patientUuid);
    }

    @Override
    public Flux<PatientCombinedSearchOutputDTO> getPatientByCombinedSearchParameters(PatientSearchByCombinedInfo obj) {
        String patientIdNo = obj.getPatientIdNo().trim();
        String patientFirstName = obj.getPatientFirstName().trim();
        String patientMiddleName = obj.getPatientMiddleName().trim();
        String patientLastName = obj.getPatientLastName().trim();
        String pdob = obj.getPdob().trim();
        String ssnNo = obj.getSsnNo().trim();
        String addressLine1 = obj.getAddressLine1().trim();
        String addressLine2 = obj.getAddressLine2().trim();
        String pcity = obj.getPcity().trim();
        String patientState = obj.getPatientState().trim();
        String pzip = obj.getPzip().trim();
        String phone = obj.getPhone().trim();
        Long branchId = obj.getBranchId();
        return patientMasterSearchRepository.getPatientByCombinedSearchParameters(patientIdNo, patientFirstName, patientMiddleName, patientLastName, pdob, ssnNo, addressLine1, addressLine2, pcity, patientState, pzip, phone, branchId);
    }

    @Override
    public String getPosNameById(Long posId) {
        try {
            return patientMasterSearchRepository.getPosNameById(posId).toFuture().get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Mono<PatientMasterDTO> getPatientByPatientIdQueryHandler(PatientSearchByPatientId obj) {
        return patientMasterSearchRepository.findById(obj.getPatientId())
            .map(patientMasterMapper::toDto)
            .filter(x ->
                (
                    x.getStatus().trim().equalsIgnoreCase("active")
                )
            );
    }

    @Override
    public Mono<PatientMasterDTO> getPatientByPatientId(Long patientId){
        return patientMasterSearchRepository.findByPatientId(patientId);
    }

    @Override
    public Mono<String> getBranchNameByBranchId(Long branchId) {
        return patientMasterSearchRepository.getBranchNameByBranchId(branchId);
    }

}
