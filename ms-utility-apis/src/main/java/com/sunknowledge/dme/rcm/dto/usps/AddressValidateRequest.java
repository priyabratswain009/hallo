package com.sunknowledge.dme.rcm.dto.usps;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "AddressValidateRequest")
@XmlType(propOrder = {"revision", "address"})
public class AddressValidateRequest {
	private String userId = "<enter usps id here>";
	private String revision;
	private Address address;
	//private String id = "<enter id value here>";
	public String getUserId() {
		return userId;
	}
	@XmlAttribute(name="USERID")
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRevision() {
		return revision;
	}
	@XmlElement(name="Revision")
	public void setRevision(String revision) {
		this.revision = revision;
	}
	public Address getAddress() {
		return address;
	}
	@XmlElement(name="Address")
	public void setAddress(Address address) {
		this.address = address;
	}
//	public String getId() {
//		return id;
//	}
//	@XmlElement(name="ID")
//	public void setId(String id) {
//		this.id = id;
//	}

	public String generateXML() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(AddressValidateRequest.class);
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
