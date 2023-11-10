package com.sunknowledge.dme.rcm.dto.usps;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@XmlRootElement(name="AddressValidateResponse")
@Data
public class AddressValidateResponse {
	private Address address;
	private String footnotesDefinition;
	private String dpvConfirmationDefinition;
	private String dpvFootnotesDefinition;
	private String dpvcmra;
	private String business;
	private String centralDeliveryPoint;
	private String vacant;
	private String message;
	private String messageCode;

	public Address getAddress() {
		return address;
	}

	@XmlElement(name="Address")
	public void setAddress(Address address) {
		this.address = address;
	}

	public String generateXML() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(AddressValidateResponse.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			StringWriter writer = new StringWriter();
			marshaller.marshal(this, writer);
			String xml = writer.toString();
			return xml;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
