package com.sunknowledge.dme.rcm.service.impl.patientsearch.queryhandler;

import com.sunknowledge.dme.rcm.repository.PatientDoctorMappingSearchRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.PatientDoctorMapDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.PatientDoctorMappingOutputDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientDoctorMapPatientMasterExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientDoctorMappingSearchByPatIdOrMapIdOrDocId;
import com.sunknowledge.dme.rcm.service.mapper.PatientDoctorMapMapper;
import com.sunknowledge.dme.rcm.service.patientsearch.queryhandler.PatientDoctorMappingSearchQueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class PatientDoctorMappingSearchQueryHandlerImpl implements PatientDoctorMappingSearchQueryHandler {

    private final Logger log = LoggerFactory.getLogger(PatientDoctorMappingSearchQueryHandlerImpl.class);
    @Autowired
    PatientDoctorMappingSearchRepositoryExtended patientDoctorMappingSearchRepositoryExtended;

    private final PatientDoctorMapMapper patientDoctorMapMapper;

    public PatientDoctorMappingSearchQueryHandlerImpl(PatientDoctorMapMapper patientDoctorMapMapper) {
        this.patientDoctorMapMapper = patientDoctorMapMapper;
    }

    @Override
    public Mono<PatientDoctorMappingOutputDTO> getPatientDoctorMappingBySearchParameters(PatientDoctorMappingSearchByPatIdOrMapIdOrDocId obj) {
        return patientDoctorMappingSearchRepositoryExtended.getPatientDoctorMappingBySearchParameters(obj.getDoctorNpiNumber(),obj.getPatientId());

        /*try {
            if ((obj.getPatientId() == null ||
                obj.getPatientId() == 0) &&
                (obj.getDoctorNpiNumber() == null ||
                    obj.getDoctorNpiNumber().equals("0")) &&
                (obj.getPatientDoctorMapId() == null ||
                    obj.getPatientDoctorMapId() == 0)) {
                return patientDoctorMappingSearchRepositoryExtended.findAll()
                    .map(patientDoctorMapMapper::toDto)
                    .filter(x -> x.getStatus().trim().equalsIgnoreCase("active"));
            } else {
                return patientDoctorMappingSearchRepositoryExtended.findAll()
                    .map(patientDoctorMapMapper::toDto)
                    .filter(x -> ((obj.getPatientId() == null ||
                            obj.getPatientId() == 0) &&
                            (obj.getDoctorNpiNumber() == null ||
                                obj.getDoctorNpiNumber().equals("0")) &&
                            obj.getPatientDoctorMapId() == x.getPatientDoctorMapId()
                        ) ||
                            ((obj.getPatientDoctorMapId() == null ||
                                obj.getPatientDoctorMapId() == 0) &&
                                (obj.getDoctorNpiNumber() == null ||
                                    obj.getDoctorNpiNumber().equals("0")) &&
                                obj.getPatientId() == x.getPatientId()
                            ) ||
                            ((obj.getPatientDoctorMapId() == null ||
                                obj.getPatientDoctorMapId() == 0) &&
                                (obj.getPatientId() == null ||
                                    obj.getPatientId() == 0) &&
                                obj.getDoctorNpiNumber().equals(x.getDoctorNpiNumber())
                            ) ||
                            ((obj.getPatientDoctorMapId() == null ||
                                    obj.getPatientDoctorMapId() == 0) &&
                                    (obj.getPatientId().equals(x.getPatientId()) &&
                                        obj.getDoctorNpiNumber().equals(x.getDoctorNpiNumber()))
                                ) &&
                                x.getStatus().trim().equalsIgnoreCase("active")
                    );
            }

        } catch (Exception e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }*/
    }

    @Override
    public Mono<Long> getIDByUUID(UUID patientDoctorMapUuid) {
        return patientDoctorMappingSearchRepositoryExtended.getIDByUUID(patientDoctorMapUuid);
    }

    @Override
    public Flux<PatientDoctorMapPatientMasterExtendedDTO> getPatientDoctorsByPatientId(Long patientId) {
        return patientDoctorMappingSearchRepositoryExtended.getPatientDoctorsByPatientId(patientId);
    }

    /*@Override
    public Flux<PatientDoctorMapPatientMasterExtendedDTO> getPatientDoctorsByPatientId(Long patientId){
        try {
            return patientDoctorMappingSearchRepositoryExtended.findAll()
                .map(patientDoctorMapMapper::toDto)
                .filter(x ->
                    (
                        x.getPatientId() == patientId
                    )
                        && x.getStatus().trim().equalsIgnoreCase("active")
                );
        } catch (Exception e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }*/

    @Override
    public Mono<Object> getPatientDoctorsByPatientDoctorId(Long patientDoctorMapId) {
        try {
            return patientDoctorMappingSearchRepositoryExtended.findById(patientDoctorMapId)
                .map(patientDoctorMapMapper::toDto);
        } catch (Exception e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Mono<Object> getCurrentPatientDoctorsByMaxId(Long patientId) {
        try {
            return patientDoctorMappingSearchRepositoryExtended.findByMaxIdAndMaxCreatedDate(patientId)
                .map(patientDoctorMapMapper::toDto);
        } catch (Exception e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Mono<PatientDoctorMappingOutputDTO> getPatientDoctorsByNpi(Long patientId, String npiId) {
        return patientDoctorMappingSearchRepositoryExtended.getPatientDoctorMappingBySearchParameters(npiId,patientId);
    }
}
