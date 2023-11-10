package com.sunknowledge.dme.rcm.service.soentryandsearch;

import com.sunknowledge.dme.rcm.domain.SalesOrderDocuments;
import com.sunknowledge.dme.rcm.repository.SalesOrderDocumentsRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderDocumentsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Primary
@Service
public class SalesOrderDocumentsServiceExtendedImpl implements SalesOrderDocumentsServiceExtended{

    @Autowired
    SalesOrderDocumentsRepositoryExtended salesOrderDocumentsRepositoryExtended;

    @Override
    public Mono<SalesOrderDocumentsDTO> save(SalesOrderDocumentsDTO salesOrderDocumentsDTO) {
        return null;
    }

    @Override
    public Mono<SalesOrderDocumentsDTO> update(SalesOrderDocumentsDTO salesOrderDocumentsDTO) {
        return null;
    }

    @Override
    public Mono<SalesOrderDocumentsDTO> partialUpdate(SalesOrderDocumentsDTO salesOrderDocumentsDTO) {
        return null;
    }

    @Override
    public Flux<SalesOrderDocumentsDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Mono<Long> countAll() {
        return null;
    }

    @Override
    public Mono<SalesOrderDocumentsDTO> findOne(Long id) {
        return null;
    }

    @Override
    public Mono<Void> delete(Long id) {
        return null;
    }

    @Override
    public Flux<SalesOrderDocuments> findBySalesOrderId(Long SOID) {
        return salesOrderDocumentsRepositoryExtended.findBySalesOrderId(SOID);
    }

    @Override
    public Flux<SalesOrderDocuments> getSODocumentsBySODocumentUUID(UUID sODocumentsUUID) {
        return salesOrderDocumentsRepositoryExtended.getSODocumentsBySODocumentUUID(sODocumentsUUID);
    }

    @Override
    public Long getIDByUUID(UUID sODocumentsUUID) {
        try {
            return salesOrderDocumentsRepositoryExtended.getIDByUUID(sODocumentsUUID).toFuture().get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Mono<SalesOrderDocuments> findByDocumentId(Long id) {
        return salesOrderDocumentsRepositoryExtended.findById(id);
    }
}
