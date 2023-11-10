package com.sunknowledge.dme.rcm.service.impl.po;

import com.sunknowledge.dme.rcm.domain.PurchaseOrder;
import com.sunknowledge.dme.rcm.repository.po.PurchaseOrderItemsRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderItemsDTO;
import com.sunknowledge.dme.rcm.service.po.PurchaseOrderItemsServiceExtended;
import com.sunknowledge.dme.rcm.service.mapper.PurchaseOrderItemsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service
public class PurchaseOrderItemsServiceExtendedImpl implements PurchaseOrderItemsServiceExtended {

    @Autowired
    PurchaseOrderItemsRepositoryExtended purchaseOrderItemsRepositoryExtended;
    @Autowired
    PurchaseOrderItemsMapper purchaseOrderItemsMapper;

    @Override
    public PurchaseOrderItemsDTO save(PurchaseOrderItemsDTO purchaseOrderItemsDTO) {
        return null;
    }

    @Override
    public PurchaseOrderItemsDTO update(PurchaseOrderItemsDTO purchaseOrderItemsDTO) {
        return null;
    }

    @Override
    public Optional<PurchaseOrderItemsDTO> partialUpdate(PurchaseOrderItemsDTO purchaseOrderItemsDTO) {
        return Optional.empty();
    }

    @Override
    public Page<PurchaseOrderItemsDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<PurchaseOrderItemsDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<PurchaseOrderItemsDTO> getPurchaseOrderItemsByItemId(Long itemId) {
        return purchaseOrderItemsMapper.toDto(purchaseOrderItemsRepositoryExtended.findByItemIdAndStatusIgnoreCase(itemId, "active"));
    }

    @Override
    public List<PurchaseOrderItemsDTO> findByPoIdAndItemIdIn(Long poId, List itemList) {
        return purchaseOrderItemsMapper.toDto(purchaseOrderItemsRepositoryExtended.findByPoIdAndItemIdIn(poId, itemList));
    }

    @Override
    public List<PurchaseOrderItemsDTO> getItemsByPOIdAndStatus(Long poId, String status) {
        return purchaseOrderItemsMapper.toDto(
            purchaseOrderItemsRepositoryExtended.getItemsByPoIdAndStatusIgnoreCase(poId, status)
        );
    }

    @Override
    public List<PurchaseOrderItemsDTO> getItemsByPoNumberAndStatus(String poNumber, String status) {
        return purchaseOrderItemsMapper.toDto(
            purchaseOrderItemsRepositoryExtended.getItemsByPoNumberAndStatusIgnoreCase(poNumber, status)
        );
    }

    @Override
    public List<PurchaseOrderItemsDTO> getItemsByPOItemsIDAndStatus(Long poItemsID, String status){
        return purchaseOrderItemsMapper.toDto(
            purchaseOrderItemsRepositoryExtended.getItemsByPoItemsIdAndStatusIgnoreCase(poItemsID, status)
        );
    }
}
