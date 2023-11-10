package com.sunknowledge.dme.rcm.web.rest.items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunknowledge.dme.rcm.service.dto.ItemSerialNumberDTO;
import com.sunknowledge.dme.rcm.service.items.ItemSerialNumberServiceExtended;

@RestController
@RequestMapping("/api")
public class ItemSerialNumberResourceExtended {
	
    @Value("${jhipster.clientApp.name}")
    private String applicationName;
    
    public static String TYPE = "text/csv";

    @Autowired
    ItemSerialNumberServiceExtended itemSerialNumberServiceExtended;

    @GetMapping("/getItemSerialNumberByserialNo")
    public ItemSerialNumberDTO getItemSerialNumberByserialNo(
        @RequestParam("serialNumber") String serialNumber, 
        @RequestParam("itemId") Long itemId){
        return itemSerialNumberServiceExtended.getItemSerialNumberByserialNo(serialNumber, itemId);
    }

}
