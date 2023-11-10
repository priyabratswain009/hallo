package com.sunknowledge.dme.rcm.dto.icd;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ICDRoot {
    @JsonProperty("MyArray")
    public ArrayList<Object> getMyArray() {
        return this.myArray; }
    public void setMyArray(ArrayList<Object> myArray) {
        this.myArray = myArray; }
    ArrayList<Object> myArray;
}
