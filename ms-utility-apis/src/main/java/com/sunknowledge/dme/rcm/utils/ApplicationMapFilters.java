package com.sunknowledge.dme.rcm.utils;

import java.util.HashMap;
import java.util.stream.Collectors;

public class ApplicationMapFilters {
    public static String uspsDPVFootnotes(String key){
        HashMap<String, String> dpvFootnotes = new HashMap<String, String>();
        dpvFootnotes.put("BB", "The input address matches an address in the USPS DPV data set in all respects.");
        dpvFootnotes.put("CC", "The primary number in the input address matches an address in the USPS DPV data set, but the secondary number does not match.");
        dpvFootnotes.put("M1", "The primary number in the input address is absent.");
        dpvFootnotes.put("M3", "The primary number in the input address is not valid.");
        dpvFootnotes.put("P1", "The rural route or highway contract number in the input address is absent.");
        dpvFootnotes.put("P3", "The post office, rural route, or highway contract number in the input address is absent.");
        dpvFootnotes.put("F1", "The input address matches a military address.");
        dpvFootnotes.put("G1", "The input address matches a general delivery address. An address is considered \"general delivery\" if the addressee cannot receive mail at a physical mailbox. General delivery addressees can collect mail at a USPS post office.");
        dpvFootnotes.put("U1", "The input address matches a unique ZIP Code.");
        dpvFootnotes.put("AA", "Input address matched to the ZIP+4 file.");
        dpvFootnotes.put("A1", "Input address not matched to the ZIP+4 file.");
        dpvFootnotes.put("N1", "High-rise address missing secondary number.");

        System.out.println("============KEY>"+key);
        String value = dpvFootnotes.entrySet().stream()
            .filter(a -> a.getKey().equalsIgnoreCase(key))
            .map(a -> a.getValue())
            .collect(Collectors.joining());
        System.out.println("============>"+value);
        return value;
    }
}
