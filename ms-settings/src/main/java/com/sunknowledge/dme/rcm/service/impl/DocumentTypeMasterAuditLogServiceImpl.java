package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.DocumentTypeMasterAuditLog;
import com.sunknowledge.dme.rcm.repository.DocumentTypeMasterAuditLogRepository;
import com.sunknowledge.dme.rcm.service.DocumentTypeMasterAuditLogService;
import com.sunknowledge.dme.rcm.service.dto.DocumentTypeMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.mapper.DocumentTypeMasterAuditLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link DocumentTypeMasterAuditLog}.
 */
@Service
@Transactional
public class DocumentTypeMasterAuditLogServiceImpl implements DocumentTypeMasterAuditLogService {

    private final Logger log = LoggerFactory.getLogger(DocumentTypeMasterAuditLogServiceImpl.class);

    private final DocumentTypeMasterAuditLogRepository documentTypeMasterAuditLogRepository;

    private final DocumentTypeMasterAuditLogMapper documentTypeMasterAuditLogMapper;

    public DocumentTypeMasterAuditLogServiceImpl(
        DocumentTypeMasterAuditLogRepository documentTypeMasterAuditLogRepository,
        DocumentTypeMasterAuditLogMapper documentTypeMasterAuditLogMapper
    ) {
        this.documentTypeMasterAuditLogRepository = documentTypeMasterAuditLogRepository;
        this.documentTypeMasterAuditLogMapper = documentTypeMasterAuditLogMapper;
    }

    @Override
    public DocumentTypeMasterAuditLogDTO save(DocumentTypeMasterAuditLogDTO documentTypeMasterAuditLogDTO) {
        log.debug("Request to save DocumentTypeMasterAuditLog : {}", documentTypeMasterAuditLogDTO);
        DocumentTypeMasterAuditLog documentTypeMasterAuditLog = documentTypeMasterAuditLogMapper.toEntity(documentTypeMasterAuditLogDTO);
        documentTypeMasterAuditLog = documentTypeMasterAuditLogRepository.save(documentTypeMasterAuditLog);
        return documentTypeMasterAuditLogMapper.toDto(documentTypeMasterAuditLog);
    }

    @Override
    public DocumentTypeMasterAuditLogDTO update(DocumentTypeMasterAuditLogDTO documentTypeMasterAuditLogDTO) {
        log.debug("Request to save DocumentTypeMasterAuditLog : {}", documentTypeMasterAuditLogDTO);
        DocumentTypeMasterAuditLog documentTypeMasterAuditLog = documentTypeMasterAuditLogMapper.toEntity(documentTypeMasterAuditLogDTO);
        documentTypeMasterAuditLog = documentTypeMasterAuditLogRepository.save(documentTypeMasterAuditLog);
        return documentTypeMasterAuditLogMapper.toDto(documentTypeMasterAuditLog);
    }

    @Override
    public Optional<DocumentTypeMasterAuditLogDTO> partialUpdate(DocumentTypeMasterAuditLogDTO documentTypeMasterAuditLogDTO) {
        log.debug("Request to partially update DocumentTypeMasterAuditLog : {}", documentTypeMasterAuditLogDTO);

        return documentTypeMasterAuditLogRepository
            .findById(documentTypeMasterAuditLogDTO.getId())
            .map(existingDocumentTypeMasterAuditLog -> {
                documentTypeMasterAuditLogMapper.partialUpdate(existingDocumentTypeMasterAuditLog, documentTypeMasterAuditLogDTO);

                return existingDocumentTypeMasterAuditLog;
            })
            .map(documentTypeMasterAuditLogRepository::save)
            .map(documentTypeMasterAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DocumentTypeMasterAuditLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DocumentTypeMasterAuditLogs");
        return documentTypeMasterAuditLogRepository.findAll(pageable).map(documentTypeMasterAuditLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DocumentTypeMasterAuditLogDTO> findOne(Long id) {
        log.debug("Request to get DocumentTypeMasterAuditLog : {}", id);
        return documentTypeMasterAuditLogRepository.findById(id).map(documentTypeMasterAuditLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DocumentTypeMasterAuditLog : {}", id);
        documentTypeMasterAuditLogRepository.deleteById(id);
    }
}
