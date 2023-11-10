package com.sunknowledge.dme.rcm.service.dto.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class POItemVendorOutputDTO {
    private Long itemId;
    private String itemName;
    private String itemNo;
    private String hcpcsCode;
    private Long vendorid;
    private String vendorname;
}
