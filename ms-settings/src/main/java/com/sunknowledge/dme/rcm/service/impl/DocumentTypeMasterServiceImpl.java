package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.DocumentTypeMaster;
import com.sunknowledge.dme.rcm.repository.DocumentTypeMasterRepository;
import com.sunknowledge.dme.rcm.service.DocumentTypeMasterService;
import com.sunknowledge.dme.rcm.service.dto.DocumentTypeMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.DocumentTypeMasterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link DocumentTypeMaster}.
 */
@Service
@Transactional
public class DocumentTypeMasterServiceImpl implements DocumentTypeMasterService {

    private final Logger log = LoggerFactory.getLogger(DocumentTypeMasterServiceImpl.class);

    private final DocumentTypeMasterRepository documentTypeMasterRepository;

    private final DocumentTypeMasterMapper documentTypeMasterMapper;

    public DocumentTypeMasterServiceImpl(
        DocumentTypeMasterRepository documentTypeMasterRepository,
        DocumentTypeMasterMapper documentTypeMasterMapper
    ) {
        this.documentTypeMasterRepository = documentTypeMasterRepository;
        this.documentTypeMasterMapper = documentTypeMasterMapper;
    }

    @Override
    public DocumentTypeMasterDTO save(DocumentTypeMasterDTO documentTypeMasterDTO) {
        log.debug("Request to save DocumentTypeMaster : {}", documentTypeMasterDTO);
        DocumentTypeMaster documentTypeMaster = documentTypeMasterMapper.toEntity(documentTypeMasterDTO);
        documentTypeMaster = documentTypeMasterRepository.save(documentTypeMaster);
        return documentTypeMasterMapper.toDto(documentTypeMaster);
    }

    @Override
    public DocumentTypeMasterDTO update(DocumentTypeMasterDTO documentTypeMasterDTO) {
        log.debug("Request to save DocumentTypeMaster : {}", documentTypeMasterDTO);
        DocumentTypeMaster documentTypeMaster = documentTypeMasterMapper.toEntity(documentTypeMasterDTO);
        documentTypeMaster = documentTypeMasterRepository.save(documentTypeMaster);
        return documentTypeMasterMapper.toDto(documentTypeMaster);
    }

    @Override
    public Optional<DocumentTypeMasterDTO> partialUpdate(DocumentTypeMasterDTO documentTypeMasterDTO) {
        log.debug("Request to partially update DocumentTypeMaster : {}", documentTypeMasterDTO);

        return documentTypeMasterRepository
            .findById(documentTypeMasterDTO.getDocumentTypeId())
            .map(existingDocumentTypeMaster -> {
                documentTypeMasterMapper.partialUpdate(existingDocumentTypeMaster, documentTypeMasterDTO);

                return existingDocumentTypeMaster;
            })
            .map(documentTypeMasterRepository::save)
            .map(documentTypeMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DocumentTypeMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DocumentTypeMasters");
        return documentTypeMasterRepository.findAll(pageable).map(documentTypeMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DocumentTypeMasterDTO> findOne(Long id) {
        log.debug("Request to get DocumentTypeMaster : {}", id);
        return documentTypeMasterRepository.findById(id).map(documentTypeMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DocumentTypeMaster : {}", id);
        documentTypeMasterRepository.deleteById(id);
    }
}
