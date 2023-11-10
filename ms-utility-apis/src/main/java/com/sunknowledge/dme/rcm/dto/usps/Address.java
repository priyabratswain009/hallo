package com.sunknowledge.dme.rcm.dto.usps;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@XmlRootElement(name = "Address")
@XmlType(propOrder = {"firmName", "address1", "address2", "city", "state", "urbanization", "zip5", "zip4", "deliveryPoint", "carrierRoute", "footnotes", "dpvConfirmation", "dpvcmra", "dpvFootnotes", "business", "centralDeliveryPoint", "vacant"})
@ApiModel
public class Address {
	private String firmName;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String urbanization;
	private String zip5;
	private String zip4;

	@ApiModelProperty(required=true, hidden=true)
	private String deliveryPoint;
	@ApiModelProperty(required=true, hidden=true)
	private String carrierRoute;
	@ApiModelProperty(required=true, hidden=true)
	private String footnotes;
	@ApiModelProperty(required=true, hidden=true)
	private String dpvConfirmation;
	@ApiModelProperty(required=true, hidden=true)
	private String dpvcmra;
	@ApiModelProperty(required=true, hidden=true)
	private String dpvFootnotes;
	@ApiModelProperty(required=true, hidden=true)
	private String business;
	@ApiModelProperty(required=true, hidden=true)
	private String centralDeliveryPoint;
	@ApiModelProperty(required=true, hidden=true)
	private String vacant;

	public String getAddress1() {
		return address1;
	}
	@XmlElement(name="Address1")
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	@XmlElement(name="Address2")
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	@XmlElement(name="City")
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	@XmlElement(name="State")
	public void setState(String state) {
		this.state = state;
	}
	public String getZip5() {
		return zip5;
	}
	@XmlElement(name="Zip5")
	public void setZip5(String zip5) {
		this.zip5 = zip5;
	}
	public String getZip4() {
		return zip4;
	}
	@XmlElement(name="Zip4")
	public void setZip4(String zip4) {
		this.zip4 = zip4;
	}
	public String getDeliveryPoint() {
		return deliveryPoint;
	}

	@XmlElement(name="DeliveryPoint")
	public void setDeliveryPoint(String deliveryPoint) {
		this.deliveryPoint = deliveryPoint;
	}
	public String getCarrierRoute() {
		return carrierRoute;
	}
	@XmlElement(name="CarrierRoute")
	public void setCarrierRoute(String carrierRoute) {
		this.carrierRoute = carrierRoute;
	}

	public String getFootnotes() {
		return footnotes;
	}

	@XmlElement(name="Footnotes")
	public void setFootnotes(String footnotes) {
		this.footnotes = footnotes;
	}

	public String getDpvConfirmation() {
		return dpvConfirmation;
	}

	@XmlElement(name="DPVConfirmation")
	public void setDpvConfirmation(String dpvConfirmation) {
		this.dpvConfirmation = dpvConfirmation;
	}

	public String getDpvcmra() {
		return dpvcmra;
	}

	@XmlElement(name="DPVCMRA")
	public void setDpvcmra(String dpvcmra) {
		this.dpvcmra = dpvcmra;
	}

	public String getDpvFootnotes() {
		return dpvFootnotes;
	}

	@XmlElement(name="DPVFootnotes")
	public void setDpvFootnotes(String dpvFootnotes) {
		this.dpvFootnotes = dpvFootnotes;
	}

	public String getBusiness() {
		return business;
	}

	@XmlElement(name="Business")
	public void setBusiness(String business) {
		this.business = business;
	}

	public String getCentralDeliveryPoint() {
		return centralDeliveryPoint;
	}

	@XmlElement(name="CentralDeliveryPoint")
	public void setCentralDeliveryPoint(String centralDeliveryPoint) {
		this.centralDeliveryPoint = centralDeliveryPoint;
	}

	public String getVacant() {
		return vacant;
	}

	@XmlElement(name="Vacant")
	public void setVacant(String vacant) {
		this.vacant = vacant;
	}
	public String getFirmName() {
		return firmName;
	}
	@XmlElement(name="FirmName")
	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}
	public String getUrbanization() {
		return urbanization;
	}
	@XmlElement(name="Urbanization")
	public void setUrbanization(String urbanization) {
		this.urbanization = urbanization;
	}


}
