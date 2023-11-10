package com.sunknowledge.dme.rcm.repository.items;

import com.sunknowledge.dme.rcm.domain.ItemProcedureCodeMap;
import com.sunknowledge.dme.rcm.repository.ItemProcedureCodeMapRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemProcedureCodeMapRepositoryExtended extends ItemProcedureCodeMapRepository {
    List<ItemProcedureCodeMap> findByItemNoAndStatusIgnoreCase(String itemNumber,String status);

    @Query("FROM ItemProcedureCodeMap WHERE itemMaster.itemId = :itemId")
    List<ItemProcedureCodeMap> findByItemId(Long itemId);
    List<ItemProcedureCodeMap> findByItemMasterItemIdAndStatusIgnoreCase(Long itemMasterItemId,String status);

    List<ItemProcedureCodeMap> findByItemNameLikeAndStatusIgnoreCase(String s,String status);
    List<ItemProcedureCodeMap> findByItemDescriptionLikeAndStatusIgnoreCase(String s,String status);
    List<ItemProcedureCodeMap> findByStatusIgnoreCase(String status);

    List<ItemProcedureCodeMap> findByProcedureCodeIgnoreCase(String procedureCode);

    List<ItemProcedureCodeMap>  findByItemMasterItemId(Long itemMasterItemId);

    ItemProcedureCodeMap findByItemProcedureCodeMapId(Long itemProcedureCodeMapId);

    List<ItemProcedureCodeMap> findByItemNameLikeIgnoreCaseAndStatusIgnoreCase(String s, String active);

    List<ItemProcedureCodeMap> findByItemDescriptionLikeIgnoreCaseAndStatusIgnoreCase(String s, String active);
}
