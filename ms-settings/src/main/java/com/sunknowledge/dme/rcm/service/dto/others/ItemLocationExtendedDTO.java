package com.sunknowledge.dme.rcm.service.dto.others;

import com.sunknowledge.dme.rcm.domain.ItemLocation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemLocationExtendedDTO extends ItemLocation {
    private Long id;
    private String title;
}
