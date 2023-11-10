package com.sunknowledge.dme.rcm.service.impl.others;

import com.sunknowledge.dme.rcm.domain.PosMaster;
import com.sunknowledge.dme.rcm.repository.others.PosMasterRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.PosMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.PosMasterExtendedDTO;
import com.sunknowledge.dme.rcm.service.mapper.PosMasterMapper;
import com.sunknowledge.dme.rcm.service.others.PosMasterServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.management.InvalidAttributeValueException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Primary
@Service
public class PosMasterServiceExtendedImpl implements PosMasterServiceExtended {
    private final Logger log = LoggerFactory.getLogger(PosMasterServiceExtendedImpl.class);

    @Autowired
    PosMasterRepositoryExtended posMasterRepositoryExtended;

    @Autowired
    PosMasterMapper posMasterMapper;

    @Override
    public PosMasterDTO save(PosMasterDTO posMasterDTO) {
        return null;
    }

    @Override
    public PosMasterDTO update(PosMasterDTO posMasterDTO) {
        return null;
    }

    @Override
    public Optional<PosMasterDTO> partialUpdate(PosMasterDTO posMasterDTO) {
        return Optional.empty();
    }

    @Override
    public Page<PosMasterDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<PosMasterDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<PosMasterDTO> getAllPOSInfo() {
        List<PosMaster> data = posMasterRepositoryExtended.findByStatusIgnoreCase("active");
        return posMasterMapper.toDto(data);
    }

    @Override
    public ResponseDTO savePOSInfo(PosMasterExtendedDTO posMasterExtendedDTO) throws InvalidAttributeValueException {
        if (posMasterExtendedDTO.getPosName() == null) {
            throw new InvalidAttributeValueException("Invalid Attribute (posName)");
        } else if (posMasterExtendedDTO.getPosName().trim().equals("")) {
            throw new InputMismatchException("posName must be provided");
        }

        ResponseDTO outcome = new ResponseDTO();
        if (posMasterExtendedDTO.getStatus().equalsIgnoreCase("active") || posMasterExtendedDTO.getStatus().equalsIgnoreCase("inActive")) {
            if (posMasterExtendedDTO.getPosName() != null && !posMasterExtendedDTO.getPosName().equals("")) {
                PosMasterDTO posMasterDTO = (posMasterExtendedDTO.getPosMasterUuid() == null) ? new PosMasterDTO() :
                    (posMasterRepositoryExtended.findByPosMasterUuid(posMasterExtendedDTO.getPosMasterUuid()) != null ?
                        posMasterMapper.toDto(posMasterRepositoryExtended.findByPosMasterUuid(posMasterExtendedDTO.getPosMasterUuid())) :
                        new PosMasterDTO());

                BeanUtils.copyProperties(posMasterExtendedDTO, posMasterDTO);
                if (posMasterDTO.getPosMasterUuid() == null) {
                    posMasterDTO.setPosId(null);
                    posMasterDTO.setCreatedById(31L);
                    posMasterDTO.setCreatedDate(LocalDate.now());
                    posMasterDTO.setCreatedByName("Falguni");
                    posMasterDTO.setPosMasterUuid(UUID.randomUUID());
                } else {
                    posMasterDTO.setUpdatedDate(LocalDate.now());
                    posMasterDTO.setUpdatedById(31L);
                    posMasterDTO.setUpdatedByName("Falguni");
                }
                PosMasterDTO savedPosMasterDTO = posMasterMapper.toDto(
                    posMasterRepositoryExtended.save(posMasterMapper.toEntity(posMasterDTO))
                );

                log.info("=======savedPosMasterDTO=======" + savedPosMasterDTO);

                return new ResponseDTO(true, "Successfully Saved.", List.of(savedPosMasterDTO));
            } else {
                outcome.setStatus(false);
                outcome.setMessage("Data Not Saved.");
                return outcome;
            }
        } else {
            throw new InputMismatchException("Status Should be active/inactive");
        }
    }

    @Override
    public List<Map<String, Object>> getPlaceOfServiceForDropdown() {
        List<PosMasterDTO> posMasterList = getAllPOSInfo();
        if (posMasterList.size() > 0) {
            return posMasterList.stream().map(p -> {
                Map<String, Object> map = new HashMap<>();
                map.put("id", p.getPosId());
                map.put("title", p.getPosName());
                return map;
            }).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }

    }
}
