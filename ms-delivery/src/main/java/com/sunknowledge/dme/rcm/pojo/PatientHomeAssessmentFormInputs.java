package com.sunknowledge.dme.rcm.pojo;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "formName",
    "deliveryTicketId",
    "companyName",
    "streetAddress",
    "cityStateZip",
    "phoneNo",
    "fax",
    "patientName",
    "address",
    "p_cityStateZip",
    "phone",
    "patientId",
    "account",
    "typeOfMobilityCane",
    "typeOfMobilityCrutches",
    "typeOfMobilityWalker",
    "typeOfMobilityManualwheelchair",
    "typeOfMobilityPovscooter",
    "typeOfMobilityPowerwheelchair",
    "typeOfMobilityOtherchk",
    "typeOfMobilityOther",
    "othermedicalequipment",
    "othermedicalequipmentValue",
    "providerby",
    "typeOfHome",
    "handicapAccessible",
    "handicapAccessibleValue",
    "rampsElevators",
    "rampsElevatorsValue",
    "others2",
    "other2value",
    "equipmentTrials",
    "carpetThrowRugs",
    "looseUnevenFloors",
    "stairsSteps",
    "wcRampsInsideOrOutsideTheHome",
    "explainAccessOptions",
    "explainAccessOptionsValue",
    "othersSection",
    "othersSectionValue",
    "caneCrutchesWalkerManualWheelchair",
    "povScooter",
    "powerWheelchair",
    "bathroomFacilities",
    "roomAccess",
    "roomAccessValue",
    "entryDoors",
    "entryDoorsSl",
    "entryDoorsValue",
    "bedRoom",
    "bedRoomSl",
    "bedRoomValue",
    "kitchen",
    "kitchenSl",
    "kitchenValue",
    "bathroom",
    "bathroomSl",
    "bathroomValue",
    "hallways",
    "hallwaysSl",
    "hallwaysValue",
    "otherRooms",
    "otherRoomsSl",
    "otherRoomsValue",
    "assessmentPerformedVerballyCheck",
    "completedPreliminaryAssessmentCheck",
    "applyCane",
    "applyCrutches",
    "applyWalker",
    "applyManualWheelchair",
    "applyPovScooter",
    "applyPowerWheelchair",
    "dateOfHomeAssessment",
    "signaturePatientRepresentative",
    "patientRepresentativeName",
    "patientRelationship",
    "reasonNotToSign",
    "date",
    "companyRepresentativeSign",
    "companyRepresentativeName"
})
@Generated("jsonschema2pojo")
public class PatientHomeAssessmentFormInputs {
    @JsonProperty("formName")
    private String formName;
    @JsonProperty("deliveryTicketId")
    private String deliveryTicketId;
    @JsonProperty("companyName")
    private String companyName;
    @JsonProperty("streetAddress")
    private String streetAddress;
    @JsonProperty("cityStateZip")
    private String cityStateZip;
    @JsonProperty("phoneNo")
    private String phoneNo;
    @JsonProperty("fax")
    private String fax;
    @JsonProperty("patientName")
    private String patientName;
    @JsonProperty("address")
    private String address;
    @JsonProperty("p_cityStateZip")
    private String pCityStateZip;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("patientId")
    private String patientId;
    @JsonProperty("account")
    private String account;
    @JsonProperty("typeOfMobilityCane")
    private Boolean typeOfMobilityCane;
    @JsonProperty("typeOfMobilityCrutches")
    private Boolean typeOfMobilityCrutches;
    @JsonProperty("typeOfMobilityWalker")
    private Boolean typeOfMobilityWalker;
    @JsonProperty("typeOfMobilityManualwheelchair")
    private Boolean typeOfMobilityManualwheelchair;
    @JsonProperty("typeOfMobilityPovscooter")
    private Boolean typeOfMobilityPovscooter;
    @JsonProperty("typeOfMobilityPowerwheelchair")
    private Boolean typeOfMobilityPowerwheelchair;
    @JsonProperty("typeOfMobilityOtherchk")
    private Boolean typeOfMobilityOtherchk;
    @JsonProperty("typeOfMobilityOther")
    private String typeOfMobilityOther;
    @JsonProperty("othermedicalequipment")
    private Boolean othermedicalequipment;
    @JsonProperty("othermedicalequipmentValue")
    private String othermedicalequipmentValue;
    @JsonProperty("providerby")
    private String providerby;
    @JsonProperty("typeOfHome")
    private String typeOfHome;
    @JsonProperty("handicapAccessible")
    private Boolean handicapAccessible;
    @JsonProperty("handicapAccessibleValue")
    private String handicapAccessibleValue;
    @JsonProperty("rampsElevators")
    private Boolean rampsElevators;
    @JsonProperty("rampsElevatorsValue")
    private String rampsElevatorsValue;
    @JsonProperty("others2")
    private Boolean others2;
    @JsonProperty("other2value")
    private String other2value;
    @JsonProperty("equipmentTrials")
    private String equipmentTrials;
    @JsonProperty("carpetThrowRugs")
    private String carpetThrowRugs;
    @JsonProperty("looseUnevenFloors")
    private String looseUnevenFloors;
    @JsonProperty("stairsSteps")
    private String stairsSteps;
    @JsonProperty("wcRampsInsideOrOutsideTheHome")
    private String wcRampsInsideOrOutsideTheHome;
    @JsonProperty("explainAccessOptions")
    private String explainAccessOptions;
    @JsonProperty("explainAccessOptionsValue")
    private String explainAccessOptionsValue;
    @JsonProperty("othersSection")
    private String othersSection;
    @JsonProperty("othersSectionValue")
    private String othersSectionValue;
    @JsonProperty("caneCrutchesWalkerManualWheelchair")
    private String caneCrutchesWalkerManualWheelchair;
    @JsonProperty("povScooter")
    private String povScooter;
    @JsonProperty("powerWheelchair")
    private String powerWheelchair;
    @JsonProperty("bathroomFacilities")
    private String bathroomFacilities;
    @JsonProperty("roomAccess")
    private String roomAccess;
    @JsonProperty("roomAccessValue")
    private String roomAccessValue;
    @JsonProperty("entryDoors")
    private String entryDoors;
    @JsonProperty("entryDoorsSl")
    private String entryDoorsSl;
    @JsonProperty("entryDoorsValue")
    private String entryDoorsValue;
    @JsonProperty("bedRoom")
    private String bedRoom;
    @JsonProperty("bedRoomSl")
    private String bedRoomSl;
    @JsonProperty("bedRoomValue")
    private String bedRoomValue;
    @JsonProperty("kitchen")
    private String kitchen;
    @JsonProperty("kitchenSl")
    private String kitchenSl;
    @JsonProperty("kitchenValue")
    private String kitchenValue;
    @JsonProperty("bathroom")
    private String bathroom;
    @JsonProperty("bathroomSl")
    private String bathroomSl;
    @JsonProperty("bathroomValue")
    private String bathroomValue;
    @JsonProperty("hallways")
    private String hallways;
    @JsonProperty("hallwaysSl")
    private String hallwaysSl;
    @JsonProperty("hallwaysValue")
    private String hallwaysValue;
    @JsonProperty("otherRooms")
    private String otherRooms;
    @JsonProperty("otherRoomsSl")
    private String otherRoomsSl;
    @JsonProperty("otherRoomsValue")
    private String otherRoomsValue;
    @JsonProperty("assessmentPerformedVerballyCheck")
    private Boolean assessmentPerformedVerballyCheck;
    @JsonProperty("completedPreliminaryAssessmentCheck")
    private Boolean completedPreliminaryAssessmentCheck;
    @JsonProperty("applyCane")
    private Boolean applyCane;
    @JsonProperty("applyCrutches")
    private Boolean applyCrutches;
    @JsonProperty("applyWalker")
    private Boolean applyWalker;
    @JsonProperty("applyManualWheelchair")
    private Boolean applyManualWheelchair;
    @JsonProperty("applyPovScooter")
    private Boolean applyPovScooter;
    @JsonProperty("applyPowerWheelchair")
    private Boolean applyPowerWheelchair;
    @JsonProperty("dateOfHomeAssessment")
    private String dateOfHomeAssessment;
    @JsonProperty("signaturePatientRepresentative")
    private String signaturePatientRepresentative;
    @JsonProperty("patientRepresentativeName")
    private String patientRepresentativeName;
    @JsonProperty("patientRelationship")
    private String patientRelationship;
    @JsonProperty("reasonNotToSign")
    private String reasonNotToSign;
    @JsonProperty("date")
    private String date;
    @JsonProperty("companyRepresentativeSign")
    private String companyRepresentativeSign;
    @JsonProperty("companyRepresentativeName")
    private String companyRepresentativeName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("formName")
    public String getFormName() {
        return formName;
    }

    @JsonProperty("formName")
    public void setFormName(String formName) {
        this.formName = formName;
    }

    @JsonProperty("deliveryTicketId")
    public String getDeliveryTicketId() {
        return deliveryTicketId;
    }

    @JsonProperty("deliveryTicketId")
    public void setDeliveryTicketId(String deliveryTicketId) {
        this.deliveryTicketId = deliveryTicketId;
    }

    @JsonProperty("companyName")
    public String getCompanyName() {
        return companyName;
    }

    @JsonProperty("companyName")
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @JsonProperty("streetAddress")
    public String getStreetAddress() {
        return streetAddress;
    }

    @JsonProperty("streetAddress")
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    @JsonProperty("cityStateZip")
    public String getCityStateZip() {
        return cityStateZip;
    }

    @JsonProperty("cityStateZip")
    public void setCityStateZip(String cityStateZip) {
        this.cityStateZip = cityStateZip;
    }

    @JsonProperty("phoneNo")
    public String getPhoneNo() {
        return phoneNo;
    }

    @JsonProperty("phoneNo")
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @JsonProperty("fax")
    public String getFax() {
        return fax;
    }

    @JsonProperty("fax")
    public void setFax(String fax) {
        this.fax = fax;
    }

    @JsonProperty("patientName")
    public String getPatientName() {
        return patientName;
    }

    @JsonProperty("patientName")
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("p_cityStateZip")
    public String getpCityStateZip() {
        return pCityStateZip;
    }

    @JsonProperty("p_cityStateZip")
    public void setpCityStateZip(String pCityStateZip) {
        this.pCityStateZip = pCityStateZip;
    }

    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    @JsonProperty("phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonProperty("patientId")
    public String getPatientId() {
        return patientId;
    }

    @JsonProperty("patientId")
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    @JsonProperty("account")
    public String getAccount() {
        return account;
    }

    @JsonProperty("account")
    public void setAccount(String account) {
        this.account = account;
    }

    @JsonProperty("typeOfMobilityCane")
    public Boolean getTypeOfMobilityCane() {
        return typeOfMobilityCane;
    }

    @JsonProperty("typeOfMobilityCane")
    public void setTypeOfMobilityCane(Boolean typeOfMobilityCane) {
        this.typeOfMobilityCane = typeOfMobilityCane;
    }

    @JsonProperty("typeOfMobilityCrutches")
    public Boolean getTypeOfMobilityCrutches() {
        return typeOfMobilityCrutches;
    }

    @JsonProperty("typeOfMobilityCrutches")
    public void setTypeOfMobilityCrutches(Boolean typeOfMobilityCrutches) {
        this.typeOfMobilityCrutches = typeOfMobilityCrutches;
    }

    @JsonProperty("typeOfMobilityWalker")
    public Boolean getTypeOfMobilityWalker() {
        return typeOfMobilityWalker;
    }

    @JsonProperty("typeOfMobilityWalker")
    public void setTypeOfMobilityWalker(Boolean typeOfMobilityWalker) {
        this.typeOfMobilityWalker = typeOfMobilityWalker;
    }

    @JsonProperty("typeOfMobilityManualwheelchair")
    public Boolean getTypeOfMobilityManualwheelchair() {
        return typeOfMobilityManualwheelchair;
    }

    @JsonProperty("typeOfMobilityManualwheelchair")
    public void setTypeOfMobilityManualwheelchair(Boolean typeOfMobilityManualwheelchair) {
        this.typeOfMobilityManualwheelchair = typeOfMobilityManualwheelchair;
    }

    @JsonProperty("typeOfMobilityPovscooter")
    public Boolean getTypeOfMobilityPovscooter() {
        return typeOfMobilityPovscooter;
    }

    @JsonProperty("typeOfMobilityPovscooter")
    public void setTypeOfMobilityPovscooter(Boolean typeOfMobilityPovscooter) {
        this.typeOfMobilityPovscooter = typeOfMobilityPovscooter;
    }

    @JsonProperty("typeOfMobilityPowerwheelchair")
    public Boolean getTypeOfMobilityPowerwheelchair() {
        return typeOfMobilityPowerwheelchair;
    }

    @JsonProperty("typeOfMobilityPowerwheelchair")
    public void setTypeOfMobilityPowerwheelchair(Boolean typeOfMobilityPowerwheelchair) {
        this.typeOfMobilityPowerwheelchair = typeOfMobilityPowerwheelchair;
    }

    @JsonProperty("typeOfMobilityOtherchk")
    public Boolean getTypeOfMobilityOtherchk() {
        return typeOfMobilityOtherchk;
    }

    @JsonProperty("typeOfMobilityOtherchk")
    public void setTypeOfMobilityOtherchk(Boolean typeOfMobilityOtherchk) {
        this.typeOfMobilityOtherchk = typeOfMobilityOtherchk;
    }

    @JsonProperty("typeOfMobilityOther")
    public String getTypeOfMobilityOther() {
        return typeOfMobilityOther;
    }

    @JsonProperty("typeOfMobilityOther")
    public void setTypeOfMobilityOther(String typeOfMobilityOther) {
        this.typeOfMobilityOther = typeOfMobilityOther;
    }

    @JsonProperty("othermedicalequipment")
    public Boolean getOthermedicalequipment() {
        return othermedicalequipment;
    }

    @JsonProperty("othermedicalequipment")
    public void setOthermedicalequipment(Boolean othermedicalequipment) {
        this.othermedicalequipment = othermedicalequipment;
    }

    @JsonProperty("othermedicalequipmentValue")
    public String getOthermedicalequipmentValue() {
        return othermedicalequipmentValue;
    }

    @JsonProperty("othermedicalequipmentValue")
    public void setOthermedicalequipmentValue(String othermedicalequipmentValue) {
        this.othermedicalequipmentValue = othermedicalequipmentValue;
    }

    @JsonProperty("providerby")
    public String getProviderby() {
        return providerby;
    }

    @JsonProperty("providerby")
    public void setProviderby(String providerby) {
        this.providerby = providerby;
    }

    @JsonProperty("typeOfHome")
    public String getTypeOfHome() {
        return typeOfHome;
    }

    @JsonProperty("typeOfHome")
    public void setTypeOfHome(String typeOfHome) {
        this.typeOfHome = typeOfHome;
    }

    @JsonProperty("handicapAccessible")
    public Boolean getHandicapAccessible() {
        return handicapAccessible;
    }

    @JsonProperty("handicapAccessible")
    public void setHandicapAccessible(Boolean handicapAccessible) {
        this.handicapAccessible = handicapAccessible;
    }

    @JsonProperty("handicapAccessibleValue")
    public String getHandicapAccessibleValue() {
        return handicapAccessibleValue;
    }

    @JsonProperty("handicapAccessibleValue")
    public void setHandicapAccessibleValue(String handicapAccessibleValue) {
        this.handicapAccessibleValue = handicapAccessibleValue;
    }

    @JsonProperty("rampsElevators")
    public Boolean getRampsElevators() {
        return rampsElevators;
    }

    @JsonProperty("rampsElevators")
    public void setRampsElevators(Boolean rampsElevators) {
        this.rampsElevators = rampsElevators;
    }

    @JsonProperty("rampsElevatorsValue")
    public String getRampsElevatorsValue() {
        return rampsElevatorsValue;
    }

    @JsonProperty("rampsElevatorsValue")
    public void setRampsElevatorsValue(String rampsElevatorsValue) {
        this.rampsElevatorsValue = rampsElevatorsValue;
    }

    @JsonProperty("others2")
    public Boolean getOthers2() {
        return others2;
    }

    @JsonProperty("others2")
    public void setOthers2(Boolean others2) {
        this.others2 = others2;
    }

    @JsonProperty("other2value")
    public String getOther2value() {
        return other2value;
    }

    @JsonProperty("other2value")
    public void setOther2value(String other2value) {
        this.other2value = other2value;
    }

    @JsonProperty("equipmentTrials")
    public String getEquipmentTrials() {
        return equipmentTrials;
    }

    @JsonProperty("equipmentTrials")
    public void setEquipmentTrials(String equipmentTrials) {
        this.equipmentTrials = equipmentTrials;
    }

    @JsonProperty("carpetThrowRugs")
    public String getCarpetThrowRugs() {
        return carpetThrowRugs;
    }

    @JsonProperty("carpetThrowRugs")
    public void setCarpetThrowRugs(String carpetThrowRugs) {
        this.carpetThrowRugs = carpetThrowRugs;
    }

    @JsonProperty("looseUnevenFloors")
    public String getLooseUnevenFloors() {
        return looseUnevenFloors;
    }

    @JsonProperty("looseUnevenFloors")
    public void setLooseUnevenFloors(String looseUnevenFloors) {
        this.looseUnevenFloors = looseUnevenFloors;
    }

    @JsonProperty("stairsSteps")
    public String getStairsSteps() {
        return stairsSteps;
    }

    @JsonProperty("stairsSteps")
    public void setStairsSteps(String stairsSteps) {
        this.stairsSteps = stairsSteps;
    }

    @JsonProperty("wcRampsInsideOrOutsideTheHome")
    public String getWcRampsInsideOrOutsideTheHome() {
        return wcRampsInsideOrOutsideTheHome;
    }

    @JsonProperty("wcRampsInsideOrOutsideTheHome")
    public void setWcRampsInsideOrOutsideTheHome(String wcRampsInsideOrOutsideTheHome) {
        this.wcRampsInsideOrOutsideTheHome = wcRampsInsideOrOutsideTheHome;
    }

    @JsonProperty("explainAccessOptions")
    public String getExplainAccessOptions() {
        return explainAccessOptions;
    }

    @JsonProperty("explainAccessOptions")
    public void setExplainAccessOptions(String explainAccessOptions) {
        this.explainAccessOptions = explainAccessOptions;
    }

    @JsonProperty("explainAccessOptionsValue")
    public String getExplainAccessOptionsValue() {
        return explainAccessOptionsValue;
    }

    @JsonProperty("explainAccessOptionsValue")
    public void setExplainAccessOptionsValue(String explainAccessOptionsValue) {
        this.explainAccessOptionsValue = explainAccessOptionsValue;
    }

    @JsonProperty("othersSection")
    public String getOthersSection() {
        return othersSection;
    }

    @JsonProperty("othersSection")
    public void setOthersSection(String othersSection) {
        this.othersSection = othersSection;
    }

    @JsonProperty("othersSectionValue")
    public String getOthersSectionValue() {
        return othersSectionValue;
    }

    @JsonProperty("othersSectionValue")
    public void setOthersSectionValue(String othersSectionValue) {
        this.othersSectionValue = othersSectionValue;
    }

    @JsonProperty("caneCrutchesWalkerManualWheelchair")
    public String getCaneCrutchesWalkerManualWheelchair() {
        return caneCrutchesWalkerManualWheelchair;
    }

    @JsonProperty("caneCrutchesWalkerManualWheelchair")
    public void setCaneCrutchesWalkerManualWheelchair(String caneCrutchesWalkerManualWheelchair) {
        this.caneCrutchesWalkerManualWheelchair = caneCrutchesWalkerManualWheelchair;
    }

    @JsonProperty("povScooter")
    public String getPovScooter() {
        return povScooter;
    }

    @JsonProperty("povScooter")
    public void setPovScooter(String povScooter) {
        this.povScooter = povScooter;
    }

    @JsonProperty("powerWheelchair")
    public String getPowerWheelchair() {
        return powerWheelchair;
    }

    @JsonProperty("powerWheelchair")
    public void setPowerWheelchair(String powerWheelchair) {
        this.powerWheelchair = powerWheelchair;
    }

    @JsonProperty("bathroomFacilities")
    public String getBathroomFacilities() {
        return bathroomFacilities;
    }

    @JsonProperty("bathroomFacilities")
    public void setBathroomFacilities(String bathroomFacilities) {
        this.bathroomFacilities = bathroomFacilities;
    }

    @JsonProperty("roomAccess")
    public String getRoomAccess() {
        return roomAccess;
    }

    @JsonProperty("roomAccess")
    public void setRoomAccess(String roomAccess) {
        this.roomAccess = roomAccess;
    }

    @JsonProperty("roomAccessValue")
    public String getRoomAccessValue() {
        return roomAccessValue;
    }

    @JsonProperty("roomAccessValue")
    public void setRoomAccessValue(String roomAccessValue) {
        this.roomAccessValue = roomAccessValue;
    }

    @JsonProperty("entryDoors")
    public String getEntryDoors() {
        return entryDoors;
    }

    @JsonProperty("entryDoors")
    public void setEntryDoors(String entryDoors) {
        this.entryDoors = entryDoors;
    }

    @JsonProperty("entryDoorsSl")
    public String getEntryDoorsSl() {
        return entryDoorsSl;
    }

    @JsonProperty("entryDoorsSl")
    public void setEntryDoorsSl(String entryDoorsSl) {
        this.entryDoorsSl = entryDoorsSl;
    }

    @JsonProperty("entryDoorsValue")
    public String getEntryDoorsValue() {
        return entryDoorsValue;
    }

    @JsonProperty("entryDoorsValue")
    public void setEntryDoorsValue(String entryDoorsValue) {
        this.entryDoorsValue = entryDoorsValue;
    }

    @JsonProperty("bedRoom")
    public String getBedRoom() {
        return bedRoom;
    }

    @JsonProperty("bedRoom")
    public void setBedRoom(String bedRoom) {
        this.bedRoom = bedRoom;
    }

    @JsonProperty("bedRoomSl")
    public String getBedRoomSl() {
        return bedRoomSl;
    }

    @JsonProperty("bedRoomSl")
    public void setBedRoomSl(String bedRoomSl) {
        this.bedRoomSl = bedRoomSl;
    }

    @JsonProperty("bedRoomValue")
    public String getBedRoomValue() {
        return bedRoomValue;
    }

    @JsonProperty("bedRoomValue")
    public void setBedRoomValue(String bedRoomValue) {
        this.bedRoomValue = bedRoomValue;
    }

    @JsonProperty("kitchen")
    public String getKitchen() {
        return kitchen;
    }

    @JsonProperty("kitchen")
    public void setKitchen(String kitchen) {
        this.kitchen = kitchen;
    }

    @JsonProperty("kitchenSl")
    public String getKitchenSl() {
        return kitchenSl;
    }

    @JsonProperty("kitchenSl")
    public void setKitchenSl(String kitchenSl) {
        this.kitchenSl = kitchenSl;
    }

    @JsonProperty("kitchenValue")
    public String getKitchenValue() {
        return kitchenValue;
    }

    @JsonProperty("kitchenValue")
    public void setKitchenValue(String kitchenValue) {
        this.kitchenValue = kitchenValue;
    }

    @JsonProperty("bathroom")
    public String getBathroom() {
        return bathroom;
    }

    @JsonProperty("bathroom")
    public void setBathroom(String bathroom) {
        this.bathroom = bathroom;
    }

    @JsonProperty("bathroomSl")
    public String getBathroomSl() {
        return bathroomSl;
    }

    @JsonProperty("bathroomSl")
    public void setBathroomSl(String bathroomSl) {
        this.bathroomSl = bathroomSl;
    }

    @JsonProperty("bathroomValue")
    public String getBathroomValue() {
        return bathroomValue;
    }

    @JsonProperty("bathroomValue")
    public void setBathroomValue(String bathroomValue) {
        this.bathroomValue = bathroomValue;
    }

    @JsonProperty("hallways")
    public String getHallways() {
        return hallways;
    }

    @JsonProperty("hallways")
    public void setHallways(String hallways) {
        this.hallways = hallways;
    }

    @JsonProperty("hallwaysSl")
    public String getHallwaysSl() {
        return hallwaysSl;
    }

    @JsonProperty("hallwaysSl")
    public void setHallwaysSl(String hallwaysSl) {
        this.hallwaysSl = hallwaysSl;
    }

    @JsonProperty("hallwaysValue")
    public String getHallwaysValue() {
        return hallwaysValue;
    }

    @JsonProperty("hallwaysValue")
    public void setHallwaysValue(String hallwaysValue) {
        this.hallwaysValue = hallwaysValue;
    }

    @JsonProperty("otherRooms")
    public String getOtherRooms() {
        return otherRooms;
    }

    @JsonProperty("otherRooms")
    public void setOtherRooms(String otherRooms) {
        this.otherRooms = otherRooms;
    }

    @JsonProperty("otherRoomsSl")
    public String getOtherRoomsSl() {
        return otherRoomsSl;
    }

    @JsonProperty("otherRoomsSl")
    public void setOtherRoomsSl(String otherRoomsSl) {
        this.otherRoomsSl = otherRoomsSl;
    }

    @JsonProperty("otherRoomsValue")
    public String getOtherRoomsValue() {
        return otherRoomsValue;
    }

    @JsonProperty("otherRoomsValue")
    public void setOtherRoomsValue(String otherRoomsValue) {
        this.otherRoomsValue = otherRoomsValue;
    }

    @JsonProperty("assessmentPerformedVerballyCheck")
    public Boolean getAssessmentPerformedVerballyCheck() {
        return assessmentPerformedVerballyCheck;
    }

    @JsonProperty("assessmentPerformedVerballyCheck")
    public void setAssessmentPerformedVerballyCheck(Boolean assessmentPerformedVerballyCheck) {
        this.assessmentPerformedVerballyCheck = assessmentPerformedVerballyCheck;
    }

    @JsonProperty("completedPreliminaryAssessmentCheck")
    public Boolean getCompletedPreliminaryAssessmentCheck() {
        return completedPreliminaryAssessmentCheck;
    }

    @JsonProperty("completedPreliminaryAssessmentCheck")
    public void setCompletedPreliminaryAssessmentCheck(Boolean completedPreliminaryAssessmentCheck) {
        this.completedPreliminaryAssessmentCheck = completedPreliminaryAssessmentCheck;
    }

    @JsonProperty("applyCane")
    public Boolean getApplyCane() {
        return applyCane;
    }

    @JsonProperty("applyCane")
    public void setApplyCane(Boolean applyCane) {
        this.applyCane = applyCane;
    }

    @JsonProperty("applyCrutches")
    public Boolean getApplyCrutches() {
        return applyCrutches;
    }

    @JsonProperty("applyCrutches")
    public void setApplyCrutches(Boolean applyCrutches) {
        this.applyCrutches = applyCrutches;
    }

    @JsonProperty("applyWalker")
    public Boolean getApplyWalker() {
        return applyWalker;
    }

    @JsonProperty("applyWalker")
    public void setApplyWalker(Boolean applyWalker) {
        this.applyWalker = applyWalker;
    }

    @JsonProperty("applyManualWheelchair")
    public Boolean getApplyManualWheelchair() {
        return applyManualWheelchair;
    }

    @JsonProperty("applyManualWheelchair")
    public void setApplyManualWheelchair(Boolean applyManualWheelchair) {
        this.applyManualWheelchair = applyManualWheelchair;
    }

    @JsonProperty("applyPovScooter")
    public Boolean getApplyPovScooter() {
        return applyPovScooter;
    }

    @JsonProperty("applyPovScooter")
    public void setApplyPovScooter(Boolean applyPovScooter) {
        this.applyPovScooter = applyPovScooter;
    }

    @JsonProperty("applyPowerWheelchair")
    public Boolean getApplyPowerWheelchair() {
        return applyPowerWheelchair;
    }

    @JsonProperty("applyPowerWheelchair")
    public void setApplyPowerWheelchair(Boolean applyPowerWheelchair) {
        this.applyPowerWheelchair = applyPowerWheelchair;
    }

    @JsonProperty("dateOfHomeAssessment")
    public String getDateOfHomeAssessment() {
        return dateOfHomeAssessment;
    }

    @JsonProperty("dateOfHomeAssessment")
    public void setDateOfHomeAssessment(String dateOfHomeAssessment) {
        this.dateOfHomeAssessment = dateOfHomeAssessment;
    }

    @JsonProperty("signaturePatientRepresentative")
    public String getSignaturePatientRepresentative() {
        return signaturePatientRepresentative;
    }

    @JsonProperty("signaturePatientRepresentative")
    public void setSignaturePatientRepresentative(String signaturePatientRepresentative) {
        this.signaturePatientRepresentative = signaturePatientRepresentative;
    }

    @JsonProperty("patientRepresentativeName")
    public String getPatientRepresentativeName() {
        return patientRepresentativeName;
    }

    @JsonProperty("patientRepresentativeName")
    public void setPatientRepresentativeName(String patientRepresentativeName) {
        this.patientRepresentativeName = patientRepresentativeName;
    }

    @JsonProperty("patientRelationship")
    public String getPatientRelationship() {
        return patientRelationship;
    }

    @JsonProperty("patientRelationship")
    public void setPatientRelationship(String patientRelationship) {
        this.patientRelationship = patientRelationship;
    }

    @JsonProperty("reasonNotToSign")
    public String getReasonNotToSign() {
        return reasonNotToSign;
    }

    @JsonProperty("reasonNotToSign")
    public void setReasonNotToSign(String reasonNotToSign) {
        this.reasonNotToSign = reasonNotToSign;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("companyRepresentativeSign")
    public String getCompanyRepresentativeSign() {
        return companyRepresentativeSign;
    }

    @JsonProperty("companyRepresentativeSign")
    public void setCompanyRepresentativeSign(String companyRepresentativeSign) {
        this.companyRepresentativeSign = companyRepresentativeSign;
    }

    @JsonProperty("companyRepresentativeName")
    public String getCompanyRepresentativeName() {
        return companyRepresentativeName;
    }

    @JsonProperty("companyRepresentativeName")
    public void setCompanyRepresentativeName(String companyRepresentativeName) {
        this.companyRepresentativeName = companyRepresentativeName;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
