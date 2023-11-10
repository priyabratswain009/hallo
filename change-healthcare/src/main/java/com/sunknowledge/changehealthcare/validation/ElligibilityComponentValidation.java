package com.sunknowledge.changehealthcare.validation;

import java.util.Iterator;
import java.util.Objects;

import com.sunknowledge.changehealthcare.core.ServiceOutcome;
import com.sunknowledge.changehealthcare.pojo.elligibility.ElligiblityRequestInput;

public class ElligibilityComponentValidation {
	public static ServiceOutcome<ElligiblityRequestInput> validateUserInputs(
			ElligiblityRequestInput elligibilityModel) {
		ServiceOutcome<ElligiblityRequestInput> serviceOutcome = new ServiceOutcome<ElligiblityRequestInput>();
		serviceOutcome.setOutcome(true);
		boolean isValid = true;
		boolean resp = false;
		String value = null;
		// Control Number Validation
		if (GenericComponentValidations.isStringNullOrBlank(elligibilityModel.getControlNumber())) {

			resp = GenericComponentValidations.isdigit(elligibilityModel.getControlNumber());
			if (resp != true || elligibilityModel.getControlNumber().length() != 9) {
				serviceOutcome.setMessage("Length of Control number should be valid 9 digits");

				serviceOutcome.setData(elligibilityModel);
				serviceOutcome.setOutcome(false);
				isValid = false;
			}
			if (!isValid)
				return serviceOutcome;
		} else {
			serviceOutcome.setData(elligibilityModel);
			serviceOutcome.setMessage("Controll Number cannot be null");
			serviceOutcome.setOutcome(false);
			return serviceOutcome;
		}

		// Trading Partner Service Id Validation
		if (GenericComponentValidations.isStringNullOrBlank(elligibilityModel.getTradingPartnerServiceId())) {
			resp = GenericComponentValidations.isalphanumeric(elligibilityModel.getTradingPartnerServiceId());
			if (!resp || GenericComponentValidations.islength80(elligibilityModel.getTradingPartnerServiceId())) {
				serviceOutcome.setMessage("Trading Partner Service Id should be alphanumeric");
				serviceOutcome.setOutcome(false);
				serviceOutcome.setData(elligibilityModel);
				isValid = false;
			}
			if (!isValid)
				return serviceOutcome;
		} else {
			serviceOutcome.setData(elligibilityModel);
			serviceOutcome.setMessage("Trading Partner Service Id cannot be null");
			serviceOutcome.setOutcome(false);
			return serviceOutcome;
		}

		// Trading Partner Service Name Validation
		if (GenericComponentValidations.isStringNullOrBlank(elligibilityModel.getTradingPartnerName())) {
			if (GenericComponentValidations.islength80(elligibilityModel.getTradingPartnerName())) {
				serviceOutcome.setData(elligibilityModel);
				serviceOutcome.setMessage("Trading Partner Service name cannot be more than 80 characters");
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			}
		}

		// Portal User Name
		if (GenericComponentValidations.isStringNullOrBlank(elligibilityModel.getPortalUsername())) {
			if (GenericComponentValidations.islength50(elligibilityModel.getPortalUsername())) {
				serviceOutcome.setData(elligibilityModel);
				serviceOutcome.setMessage("Portal name cannot be more than 50 characters");
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			}
		}

		// Portal User Password
		if (GenericComponentValidations.isStringNullOrBlank(elligibilityModel.getPortalPassword())) {
			if (GenericComponentValidations.islength50(elligibilityModel.getPortalPassword())) {
				serviceOutcome.setData(elligibilityModel);
				serviceOutcome.setMessage("Portal name cannot be more than 50 characters");
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			}
		}

		// PROVIDER
		// Organization Name
		if (!Objects.isNull(elligibilityModel.getProvider())) {
			if (GenericComponentValidations
					.isStringNullOrBlank(elligibilityModel.getProvider().getOrganizationName())) {
				if (!GenericComponentValidations
						.isalphanumericspace(elligibilityModel.getProvider().getOrganizationName())
						|| GenericComponentValidations
								.islength60(elligibilityModel.getProvider().getOrganizationName())) {
					serviceOutcome.setMessage("Provider OrganizationName or LastName can only have alphabets");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			} else {
				serviceOutcome.setMessage("Either provider OrganizationName or LastName are required.");
				serviceOutcome.setData(elligibilityModel);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			}

			// First Name
			if (GenericComponentValidations.isStringNullOrBlank(elligibilityModel.getProvider().getFirstName())) {
				if (GenericComponentValidations.islength35(elligibilityModel.getProvider().getFirstName())) {
					serviceOutcome.setMessage("Provider FirstName length cannot exceed 35");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}

			// Last Name
			if (GenericComponentValidations.isStringNullOrBlank(elligibilityModel.getProvider().getLastName())) {
				if (GenericComponentValidations.islength35(elligibilityModel.getProvider().getLastName())) {
					serviceOutcome.setMessage("Provider LastName length cannot exceed 35");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}

			// NPI
			if (GenericComponentValidations.isStringNullOrBlank(elligibilityModel.getProvider().getNpi())) {
				if (!GenericComponentValidations.isalphanumeric(elligibilityModel.getProvider().getNpi())
						|| GenericComponentValidations.islength80(elligibilityModel.getProvider().getNpi())) {
					serviceOutcome.setMessage("Provider NPI shuld be 2-80 alphanumeric characters");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}

			// Service Provider Number
			if (GenericComponentValidations
					.isStringNullOrBlank(elligibilityModel.getProvider().getServiceProviderNumber())) {
				resp = GenericComponentValidations
						.isalphanumericspace(elligibilityModel.getProvider().getServiceProviderNumber());
				if (resp != true || GenericComponentValidations
						.islength80(elligibilityModel.getProvider().getServiceProviderNumber())) {
					serviceOutcome
							.setMessage("Provider Service Provider Number should be 2-80 alphanumeric characters");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}

			// payorId
			if (GenericComponentValidations.isStringNullOrBlank(elligibilityModel.getProvider().getPayorID())) {
				if (GenericComponentValidations.islength80(elligibilityModel.getProvider().getPayorID())) {
					serviceOutcome.setMessage("Provider Payor ID should be 2-80 alphanumeric characters");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}

			// taxId
			if (GenericComponentValidations.isStringNullOrBlank(elligibilityModel.getProvider().getTaxId())) {
				if (GenericComponentValidations.islength80(elligibilityModel.getProvider().getTaxId())) {
					serviceOutcome.setMessage("Provider Tax ID should be 2-80 alphanumeric characters");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}

			// SSN
			if (GenericComponentValidations.isStringNullOrBlank(elligibilityModel.getProvider().getSsn())) {
				if (GenericComponentValidations.islength80(elligibilityModel.getProvider().getSsn())) {
					serviceOutcome.setMessage("Provider SSN should be 2-80 alphanumeric characters");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}

			// pharmacyProcessorNumber
			if (GenericComponentValidations
					.isStringNullOrBlank(elligibilityModel.getProvider().getPharmacyProcessorNumber())) {
				if (GenericComponentValidations
						.islength80(elligibilityModel.getProvider().getPharmacyProcessorNumber())) {
					serviceOutcome
							.setMessage("Provider Pharmacy Processor Number should be 2-80 alphanumeric characters");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}

			// servicesPlanID
			if (GenericComponentValidations.isStringNullOrBlank(elligibilityModel.getProvider().getServicesPlanID())) {
				if (GenericComponentValidations.islength80(elligibilityModel.getProvider().getServicesPlanID())) {
					serviceOutcome.setMessage("Provider Service Plan ID should be 2-80 alphanumeric characters");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}

			// employersId
			if (GenericComponentValidations.isStringNullOrBlank(elligibilityModel.getProvider().getEmployersId())) {
				if (GenericComponentValidations.islength80(elligibilityModel.getProvider().getEmployersId())) {
					serviceOutcome.setMessage("Provider Employers ID should be 2-80 alphanumeric characters");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}

			// Provider Code
			if (GenericComponentValidations.isStringNullOrBlank(elligibilityModel.getProvider().getProviderCode())) {
				value = elligibilityModel.getProvider().getProviderCode();
				if (!value.matches("AD|AT|BI|CO|CV|H|HH|LA|OT|P1|P2|PC|PE|R|RF|SB|SK|SU")) {
					serviceOutcome.setMessage(
							"Allowed Values are: 'AD' Admitting, 'AT' Attending, 'BI' Billing, 'CO' Consulting, 'CV' Covering, 'H' Hospital, 'HH' Home Health Care, 'LA' Laboratory, 'OT' Other Physician, 'P1' Pharmacist, 'P2' Pharmacy, 'PC' Primary Care Physician, 'PE' Performing, 'R' Rural Health Clinic, 'RF' Referring, 'SK' Skilled Nursing Facility, 'SU' Supervising");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}

			// Provider Type
			if (GenericComponentValidations.isStringNullOrBlank(elligibilityModel.getProvider().getProviderType())) {
				value = elligibilityModel.getProvider().getProviderType();
				if (!value.matches(
						"payer|third-party administrator|employer|hospital|facility|gateway provider|plan sponser|provider")) {
					serviceOutcome.setMessage(
							"Allowed descriptions are: 'PR' when providerType='payer' && payerId is present,'2B' when providerType='third-party administrator','36' when providerType='employer','80' when providerType='hospital','FA' when providerType='facility','GP' when providerType='gateway provider', 'P5' when providerType='plan sponsor',  '1P' when providerType= 'provider'");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
		}
		// INFORMATION RECIEVER NAME
		if (!Objects.isNull(elligibilityModel.getInformationReceiverName())) {
			// stateLicenceNumber
			if (GenericComponentValidations
					.isStringNullOrBlank(elligibilityModel.getInformationReceiverName().getStateLicenceNumber())) {
				if (GenericComponentValidations
						.islength50(elligibilityModel.getInformationReceiverName().getStateLicenceNumber())) {
					serviceOutcome.setMessage("State Licence Number should be 2-50 alphanumeric characters");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// medicareProviderNumber
			if (GenericComponentValidations
					.isStringNullOrBlank(elligibilityModel.getInformationReceiverName().getMedicareProviderNumber())) {
				if (GenericComponentValidations
						.islength50(elligibilityModel.getInformationReceiverName().getMedicareProviderNumber())) {
					serviceOutcome.setMessage("Medicare Provider Number should be 2-50 alphanumeric characters");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// medicaidProviderNumber
			if (GenericComponentValidations
					.isStringNullOrBlank(elligibilityModel.getInformationReceiverName().getMedicaidProviderNumber())) {
				if (GenericComponentValidations
						.islength50(elligibilityModel.getInformationReceiverName().getMedicaidProviderNumber())) {
					serviceOutcome.setMessage("Medicaid Provider Number should be 2-50 alphanumeric characters");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// facilityIdNumber
			if (GenericComponentValidations
					.isStringNullOrBlank(elligibilityModel.getInformationReceiverName().getFacilityIdNumber())) {
				if (GenericComponentValidations
						.islength50(elligibilityModel.getInformationReceiverName().getFacilityIdNumber())) {
					serviceOutcome.setMessage("Facility Id Number should be 2-50 alphanumeric characters");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// contactNumber
			if (GenericComponentValidations
					.isStringNullOrBlank(elligibilityModel.getInformationReceiverName().getContactNumber())) {
				if (GenericComponentValidations
						.islength50(elligibilityModel.getInformationReceiverName().getContactNumber())) {
					serviceOutcome.setMessage("Contact Number should be 2-50 alphanumeric characters");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// devicePinNumber
			if (GenericComponentValidations
					.isStringNullOrBlank(elligibilityModel.getInformationReceiverName().getContactNumber())) {
				if (GenericComponentValidations
						.islength50(elligibilityModel.getInformationReceiverName().getContactNumber())) {
					serviceOutcome.setMessage("Device Pin Number should be 2-50 alphanumeric characters");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// submitterIdNumber
			if (GenericComponentValidations
					.isStringNullOrBlank(elligibilityModel.getInformationReceiverName().getSubmitterIdNumber())) {
				if (GenericComponentValidations
						.islength50(elligibilityModel.getInformationReceiverName().getSubmitterIdNumber())) {
					serviceOutcome.setMessage("Submitter Id Number should be 2-50 alphanumeric characters");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// nationalProviderIdentifier
			if (GenericComponentValidations.isStringNullOrBlank(
					elligibilityModel.getInformationReceiverName().getNationalProviderIdentifier())) {
				if (GenericComponentValidations
						.islength50(elligibilityModel.getInformationReceiverName().getNationalProviderIdentifier())) {
					serviceOutcome.setMessage("National Provider Identifier should be 2-50 alphanumeric characters");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// providerPlanNetworkIdNumber
			if (GenericComponentValidations.isStringNullOrBlank(
					elligibilityModel.getInformationReceiverName().getNationalProviderIdentifier())) {
				if (GenericComponentValidations
						.islength50(elligibilityModel.getInformationReceiverName().getNationalProviderIdentifier())) {
					serviceOutcome.setMessage("Provider Plan Network Id Number should be 2-50 alphanumeric characters");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// facilityNetworkIdNumber
			if (GenericComponentValidations
					.isStringNullOrBlank(elligibilityModel.getInformationReceiverName().getFacilityNetworkIdNumber())) {
				if (GenericComponentValidations
						.islength50(elligibilityModel.getInformationReceiverName().getFacilityNetworkIdNumber())) {
					serviceOutcome.setMessage("Facility Network Id Number should be 2-50 alphanumeric characters");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// priorIdentifierNumber
			if (GenericComponentValidations
					.isStringNullOrBlank(elligibilityModel.getInformationReceiverName().getPriorIdentifierNumber())) {
				if (GenericComponentValidations
						.islength50(elligibilityModel.getInformationReceiverName().getPriorIdentifierNumber())) {
					serviceOutcome.setMessage("Prior Identifier Number should be 2-50 alphanumeric characters");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// socialSecurityNumber
			if (GenericComponentValidations
					.isStringNullOrBlank(elligibilityModel.getInformationReceiverName().getSocialSecurityNumber())) {
				if (GenericComponentValidations
						.islength50(elligibilityModel.getInformationReceiverName().getSocialSecurityNumber())) {
					serviceOutcome.setMessage("Social Security Number should be 2-50 alphanumeric characters");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// federalTaxpayerIdentificationNumber
			if (GenericComponentValidations
					.isStringNullOrBlank(elligibilityModel.getInformationReceiverName().getSocialSecurityNumber())) {
				if (GenericComponentValidations
						.islength50(elligibilityModel.getInformationReceiverName().getSocialSecurityNumber())) {
					serviceOutcome.setMessage(
							"Federal Taxpayer Identification Number should be 2-50 alphanumeric characters");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// informationReceiverAdditionalIdentifierState
			if (GenericComponentValidations
					.isStringNullOrBlank(elligibilityModel.getInformationReceiverName().getSocialSecurityNumber())) {
				if (GenericComponentValidations
						.islength50(elligibilityModel.getInformationReceiverName().getSocialSecurityNumber())) {
					serviceOutcome.setMessage(
							"Information Receiver Additional Identifier State should be 2-50 alphanumeric characters");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// ADDRESS
			if (!Objects.isNull(elligibilityModel.getInformationReceiverName().getAddress())) {
				// address1
				if (GenericComponentValidations.isStringNullOrBlank(
						elligibilityModel.getInformationReceiverName().getAddress().getAddress1())) {
					if (GenericComponentValidations
							.islength55(elligibilityModel.getInformationReceiverName().getAddress().getAddress1())) {
						serviceOutcome.setMessage("Address1 should be 2-55 characters");
						serviceOutcome.setData(elligibilityModel);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// address2
				if (GenericComponentValidations.isStringNullOrBlank(
						elligibilityModel.getInformationReceiverName().getAddress().getAddress2())) {
					if (GenericComponentValidations
							.islength55(elligibilityModel.getInformationReceiverName().getAddress().getAddress2())) {
						serviceOutcome.setMessage("Address2 should be 2-55 characters");
						serviceOutcome.setData(elligibilityModel);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// city
				if (GenericComponentValidations
						.isStringNullOrBlank(elligibilityModel.getInformationReceiverName().getAddress().getCity())) {
					if (GenericComponentValidations
							.isCity(elligibilityModel.getInformationReceiverName().getAddress().getCity())) {
						serviceOutcome.setMessage("City should be 2-30 characters");
						serviceOutcome.setData(elligibilityModel);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// state
				if (GenericComponentValidations
						.isStringNullOrBlank(elligibilityModel.getInformationReceiverName().getAddress().getState())) {
					if (GenericComponentValidations
							.isState(elligibilityModel.getInformationReceiverName().getAddress().getState())) {
						serviceOutcome.setMessage("State should be 0-2 characters");
						serviceOutcome.setData(elligibilityModel);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// Postal Code
				if (GenericComponentValidations.isStringNullOrBlank(
						elligibilityModel.getInformationReceiverName().getAddress().getPostalCode())) {
					if (!GenericComponentValidations
							.isdigit(elligibilityModel.getInformationReceiverName().getAddress().getPostalCode())) {
						serviceOutcome.setMessage("Postal Code should be 0-15 characters");
						serviceOutcome.setData(elligibilityModel);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
			}

		}

		// SUBSCRIBER
		if (!Objects.isNull(elligibilityModel.getSubscriber())) {
			// birthSequenceNumber
			if (GenericComponentValidations
					.isStringNullOrBlank(elligibilityModel.getSubscriber().getBirthSequenceNumber())) {
				if (GenericComponentValidations
						.issequenceNo(elligibilityModel.getSubscriber().getBirthSequenceNumber())) {
					serviceOutcome
							.setMessage("Birth Sequence Number must be exactly 9 positive unsigned numeric characters");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}

			}
			// caseNumber
			if (GenericComponentValidations
					.isStringNullOrBlank(elligibilityModel.getSubscriber().getBirthSequenceNumber())) {
				if (!GenericComponentValidations
						.isalphanumeric(elligibilityModel.getSubscriber().getBirthSequenceNumber())
						|| GenericComponentValidations
								.islength50(elligibilityModel.getSubscriber().getBirthSequenceNumber())) {
					serviceOutcome.setMessage("Case Number must not contain more than 50 characters");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}

			}
			// medicaidRecipientIdentificationNumber
			if (GenericComponentValidations.isStringNullOrBlank(
					elligibilityModel.getSubscriber().getMedicaidRecipientIdentificationNumber())) {
				if (!GenericComponentValidations
						.isalphanumeric(elligibilityModel.getSubscriber().getMedicaidRecipientIdentificationNumber())
						|| GenericComponentValidations.islength80(
								elligibilityModel.getSubscriber().getMedicaidRecipientIdentificationNumber())) {
					serviceOutcome.setMessage(
							"Medicaid Recipient Identification Number must not contain more than 80 characters");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}

			}
			// Spend Down Amount
			if (GenericComponentValidations
					.isStringNullOrBlank(elligibilityModel.getSubscriber().getSpendDownAmount())) {
				if (elligibilityModel.getSubscriber().getSpendDownAmount().length() > 18) {
					serviceOutcome.setMessage("Spend Down Amount must not contain more than 18 characters");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}

			}
			// Spend Down Total Billed Amount
			if (GenericComponentValidations
					.isStringNullOrBlank(elligibilityModel.getSubscriber().getSpendDownTotalBilledAmount())) {
				if (elligibilityModel.getSubscriber().getSpendDownTotalBilledAmount().length() > 18) {
					serviceOutcome
							.setMessage("Spend Down Total Billed Amount must not contain more than 18 characters");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}

			}

			// Coverage Level Code
			if (GenericComponentValidations
					.isStringNullOrBlank(elligibilityModel.getSubscriber().getCoverageLevelCode())) {
				if (elligibilityModel.getSubscriber().getCoverageLevelCode().length() > 3) {
					serviceOutcome.setMessage("Spend Down Total Billed Amount must not contain more than 3 characters");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}

			}
			// Member Id
			if (GenericComponentValidations.isStringNullOrBlank(elligibilityModel.getSubscriber().getMemberId())) {
				if (!GenericComponentValidations.isalphanumeric(elligibilityModel.getSubscriber().getMemberId())
						|| GenericComponentValidations.islength80(elligibilityModel.getSubscriber().getMemberId())) {
					serviceOutcome.setMessage("Member Id must not contain more than 80 alphanumeric characters");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}

			}
			// Subscriber Member Id Validation
			if (GenericComponentValidations.isStringNullOrBlank(elligibilityModel.getSubscriber().getMemberId())) {
				if (elligibilityModel.getSubscriber().getMemberId().length() < 2
						&& elligibilityModel.getSubscriber().getMemberId().length() > 80) {
					serviceOutcome.setMessage("Subscriber Member Id length should be between 2 and 80");
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
				resp = GenericComponentValidations.isalphanumeric(elligibilityModel.getSubscriber().getMemberId());
				if (!resp) {
					serviceOutcome.setMessage("Subscriber Member Id should be alphanumeric");
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
				serviceOutcome.setData(elligibilityModel);
				if (!isValid)
					return serviceOutcome;
			} else {
				serviceOutcome.setMessage("Controll Number cannot be null");
				serviceOutcome.setData(elligibilityModel);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			}

			// First Name
			if (GenericComponentValidations.isStringNullOrBlank(elligibilityModel.getSubscriber().getFirstName())) {

				resp = GenericComponentValidations.isalphabets(elligibilityModel.getSubscriber().getFirstName());
				if (!resp || elligibilityModel.getSubscriber().getFirstName().length() > 35) {
					serviceOutcome.setMessage("Subscriber First Name should contain only alphabets");
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
				serviceOutcome.setData(elligibilityModel);
				if (!isValid)
					return serviceOutcome;
			} else {
				serviceOutcome.setMessage("Subscriber First Name cannot be null");
				serviceOutcome.setData(elligibilityModel);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			}

			// Middle Name
			if (GenericComponentValidations.isStringNullOrBlank(elligibilityModel.getSubscriber().getMiddleName())) {

				resp = GenericComponentValidations.isalphabets(elligibilityModel.getSubscriber().getMiddleName());
				if (!resp || elligibilityModel.getSubscriber().getMiddleName().length() > 25) {
					serviceOutcome.setMessage("Subscriber Middle Name should contain only alphabets");
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
				serviceOutcome.setData(elligibilityModel);
				if (!isValid)
					return serviceOutcome;
			}

			// Last Name
			if (GenericComponentValidations.isStringNullOrBlank(elligibilityModel.getSubscriber().getLastName())) {

				resp = GenericComponentValidations.isalphabets(elligibilityModel.getSubscriber().getLastName());
				if (!resp || elligibilityModel.getSubscriber().getLastName().length() > 60) {
					serviceOutcome.setMessage("Subscriber Last Name should contain only alphabets");
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
				serviceOutcome.setData(elligibilityModel);
				if (!isValid)
					return serviceOutcome;
			} else {
				serviceOutcome.setMessage("Subscriber Last Name cannot be null");
				serviceOutcome.setData(elligibilityModel);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			}
			// SUFFIX
			if (GenericComponentValidations.isStringNullOrBlank(elligibilityModel.getSubscriber().getSuffix())) {
				if (elligibilityModel.getSubscriber().getSuffix().length() > 10) {
					serviceOutcome.setMessage("Subscriber Suffix must not be more than 10 characters");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}

			// Gender
			if (GenericComponentValidations.isStringNullOrBlank(elligibilityModel.getSubscriber().getGender())) {

				resp = GenericComponentValidations.isalphabets(elligibilityModel.getSubscriber().getGender());
				if (!elligibilityModel.getSubscriber().getGender().equalsIgnoreCase("M")
						&& !elligibilityModel.getSubscriber().getGender().equalsIgnoreCase("F")
						&& !elligibilityModel.getSubscriber().getGender().equalsIgnoreCase("U")) {
					serviceOutcome.setMessage("Kindly specify proper Gender");
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
				serviceOutcome.setData(elligibilityModel);
				if (!isValid)
					return serviceOutcome;
			}

			// Date Of Birth
			if (!GenericComponentValidations.isStringNullOrBlank(elligibilityModel.getSubscriber().getDateOfBirth())) {
				serviceOutcome.setMessage("Subscriber Date Of Birth cannot be null");
				serviceOutcome.setOutcome(false);
				serviceOutcome.setData(elligibilityModel);
				return serviceOutcome;
			} else {
				resp = GenericComponentValidations.isValidDate(elligibilityModel.getSubscriber().getDateOfBirth());
				if (!resp) {
					serviceOutcome.setMessage("Subscriber Date Of Birth is not valid");
					serviceOutcome.setOutcome(false);
					serviceOutcome.setData(elligibilityModel);
					return serviceOutcome;
				}
			}

			// SSN
			if (GenericComponentValidations.isStringNullOrBlank(elligibilityModel.getSubscriber().getSsn())) {
				resp = GenericComponentValidations.isdigit(elligibilityModel.getSubscriber().getSsn());
				if (resp != true || elligibilityModel.getSubscriber().getSsn().length() != 9) {
					serviceOutcome.setMessage("Length of Service Provider Number should be valid 9 digits");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// groupNumber
			if (GenericComponentValidations.isStringNullOrBlank(elligibilityModel.getSubscriber().getGroupNumber())) {
				if (elligibilityModel.getSubscriber().getGroupNumber().length() > 50) {
					serviceOutcome.setMessage("Subscriber Group Number should not contain more than 50 characters");
					serviceOutcome.setOutcome(false);
					serviceOutcome.setData(elligibilityModel);
					return serviceOutcome;
				}
			}

			// ID Card
			if (GenericComponentValidations.isStringNullOrBlank(elligibilityModel.getSubscriber().getIdCard())) {
				resp = GenericComponentValidations.isalphanumeric(elligibilityModel.getSubscriber().getIdCard());
				if (!resp) {
					serviceOutcome.setMessage("Subscriber Id Card should be alphanumeric");
					serviceOutcome.setOutcome(false);
					serviceOutcome.setData(elligibilityModel);
					return serviceOutcome;
				}

			}
			// Provider Code
			if (GenericComponentValidations.isStringNullOrBlank(elligibilityModel.getSubscriber().getProviderCode())) {
				if (!elligibilityModel.getSubscriber().getProviderCode()
						.matches("AD|AT|BI|CO|CV|H|HH|LA|OT|P1|P2|PC|PE|R|RF|SB|SK|SU")) {
					serviceOutcome.setMessage(
							"Allowed Values are: 'AD' Admitting, 'AT' Attending, 'BI' Billing, 'CO' Consulting, 'CV' Covering, 'H' Hospital, 'HH' Home Health Care, 'LA' Laboratory, 'OT' Other Physician, 'P1' Pharmacist, 'P2' Pharmacy, 'PC' Primary Care Physician, 'PE' Performing, 'R' Rural Health Clinic, 'RF' Referring, 'SK' Skilled Nursing Facility, 'SU' Supervising");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}

			}
			// referenceIdentificationQualifier
			if (GenericComponentValidations
					.isStringNullOrBlank(elligibilityModel.getSubscriber().getReferenceIdentificationQualifier())) {
				if (!elligibilityModel.getSubscriber().getReferenceIdentificationQualifier()
						.matches("9K|D3|EI|HPI|PXC|SY|TJ")) {
					serviceOutcome.setMessage("referenceIdentificationQualifier should be proper");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}

			}
			// providerIdentifier
			if (GenericComponentValidations
					.isStringNullOrBlank(elligibilityModel.getSubscriber().getProviderIdentifier())) {
				if (!GenericComponentValidations
						.isalphanumeric(elligibilityModel.getSubscriber().getProviderIdentifier())
						|| elligibilityModel.getSubscriber().getProviderIdentifier().length() > 50) {
					serviceOutcome.setMessage("referenceIdentificationQualifier should be proper");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}

			}
			// healthCareCodeInformation
			if (!Objects.isNull(elligibilityModel.getSubscriber().getHealthCareCodeInformation())) {
				for (int i = 0; i < elligibilityModel.getSubscriber().getHealthCareCodeInformation().size(); i++) {
					// diagnosisTypeCode
					if (GenericComponentValidations.isStringNullOrBlank(elligibilityModel.getSubscriber()
							.getHealthCareCodeInformation().get(i).getDiagnosisTypeCode())) {
						if (GenericComponentValidations.isdiagnosisTypeCode(elligibilityModel.getSubscriber()
								.getHealthCareCodeInformation().get(i).getDiagnosisTypeCode())) {
							serviceOutcome.setMessage("Diagnosis Type Code should be proper");
							serviceOutcome.setData(elligibilityModel);
							serviceOutcome.setOutcome(false);
							return serviceOutcome;
						}
					}
					// diagnosisCode
					if (GenericComponentValidations.isStringNullOrBlank(elligibilityModel.getSubscriber()
							.getHealthCareCodeInformation().get(i).getDiagnosisCode())) {
						if (!GenericComponentValidations.isalphanumeric(elligibilityModel.getSubscriber()
								.getHealthCareCodeInformation().get(i).getDiagnosisTypeCode())
								|| elligibilityModel.getSubscriber().getHealthCareCodeInformation().get(i)
										.getDiagnosisTypeCode().length() > 30) {
							serviceOutcome.setMessage("Diagnosis Code should be proper");
							serviceOutcome.setData(elligibilityModel);
							serviceOutcome.setOutcome(false);
							return serviceOutcome;
						}
					}
				}
			}

			// ADDRESS
			if (!Objects.isNull(elligibilityModel.getSubscriber().getAddress())) {
				// address1
				if (GenericComponentValidations
						.isStringNullOrBlank(elligibilityModel.getSubscriber().getAddress().getAddress1())) {
					if (GenericComponentValidations
							.islength55(elligibilityModel.getSubscriber().getAddress().getAddress1())) {
						serviceOutcome.setMessage("Address1 should be 2-55 characters");
						serviceOutcome.setData(elligibilityModel);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// address2
				if (GenericComponentValidations
						.isStringNullOrBlank(elligibilityModel.getSubscriber().getAddress().getAddress2())) {
					if (GenericComponentValidations
							.islength55(elligibilityModel.getSubscriber().getAddress().getAddress2())) {
						serviceOutcome.setMessage("Address2 should be 2-55 characters");
						serviceOutcome.setData(elligibilityModel);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// city
				if (GenericComponentValidations
						.isStringNullOrBlank(elligibilityModel.getSubscriber().getAddress().getCity())) {
					if (GenericComponentValidations.isCity(elligibilityModel.getSubscriber().getAddress().getCity())) {
						serviceOutcome.setMessage("City should be 2-30 characters");
						serviceOutcome.setData(elligibilityModel);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// state
				if (GenericComponentValidations
						.isStringNullOrBlank(elligibilityModel.getSubscriber().getAddress().getState())) {
					if (GenericComponentValidations
							.isState(elligibilityModel.getSubscriber().getAddress().getState())) {
						serviceOutcome.setMessage("State should be 0-2 characters");
						serviceOutcome.setData(elligibilityModel);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// Postal Code
				if (GenericComponentValidations
						.isStringNullOrBlank(elligibilityModel.getSubscriber().getAddress().getPostalCode())) {
					if (!GenericComponentValidations
							.isdigit(elligibilityModel.getSubscriber().getAddress().getPostalCode())) {
						serviceOutcome.setMessage("Postal Code should be 0-15 characters");
						serviceOutcome.setData(elligibilityModel);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
			}
		}

		// DEPENDENTS
		if (!Objects.isNull(elligibilityModel.getDependents())) {
			for (int i = 0; i < elligibilityModel.getDependents().size(); i++) {
				// birthSequenceNumber
				if (GenericComponentValidations
						.isStringNullOrBlank(elligibilityModel.getDependents().get(i).getBirthSequenceNumber())) {
					if (GenericComponentValidations
							.issequenceNo(elligibilityModel.getDependents().get(i).getBirthSequenceNumber())) {
						serviceOutcome
								.setMessage("Dependants Birth Sequence Number should not contain more than 9 digits");
						serviceOutcome.setOutcome(false);
						serviceOutcome.setData(elligibilityModel);
						return serviceOutcome;
					}

				}
				// individualRelationshipCode
				if (GenericComponentValidations.isStringNullOrBlank(
						elligibilityModel.getDependents().get(i).getIndividualRelationshipCode())) {
					if (!elligibilityModel.getDependents().get(i).getIndividualRelationshipCode().matches("01|19|34")) {
						serviceOutcome.setMessage("Allowed Values are: '01' - Spouse, '19' - Child, '34' Other Adult");
						serviceOutcome.setOutcome(false);
						serviceOutcome.setData(elligibilityModel);
						return serviceOutcome;
					}

				}
				// issueNumber
				if (GenericComponentValidations
						.isStringNullOrBlank(elligibilityModel.getDependents().get(i).getIssueNumber())) {
					if (GenericComponentValidations
							.islength50(elligibilityModel.getDependents().get(i).getIssueNumber())) {
						serviceOutcome.setMessage("Subscriber Issue Number cannot exceed 50");
						serviceOutcome.setOutcome(false);
						serviceOutcome.setData(elligibilityModel);
						return serviceOutcome;
					}

				}
				// eligibilityCategory
				if (GenericComponentValidations
						.isStringNullOrBlank(elligibilityModel.getDependents().get(i).getEligibilityCategory())) {
					if (GenericComponentValidations
							.islength50(elligibilityModel.getDependents().get(i).getEligibilityCategory())) {
						serviceOutcome.setMessage("Elligibility Category Issue Number cannot exceed 50");
						serviceOutcome.setOutcome(false);
						serviceOutcome.setData(elligibilityModel);
						return serviceOutcome;
					}

				}
				// memberId
				if (GenericComponentValidations
						.isStringNullOrBlank(elligibilityModel.getDependents().get(i).getMemberId())) {
					if (GenericComponentValidations
							.islength80(elligibilityModel.getDependents().get(i).getMemberId())) {
						serviceOutcome.setMessage("Member ID cannot exceed 80");
						serviceOutcome.setOutcome(false);
						serviceOutcome.setData(elligibilityModel);
						return serviceOutcome;
					}

				}
				// First Name
				if (GenericComponentValidations
						.isStringNullOrBlank(elligibilityModel.getDependents().get(i).getFirstName())) {
					resp = GenericComponentValidations
							.isalphabets(elligibilityModel.getDependents().get(i).getFirstName());
					if (!resp || elligibilityModel.getDependents().get(i).getFirstName().length() > 35) {
						serviceOutcome.setMessage("Dependants First Name should contain only alphabets");
						serviceOutcome.setOutcome(false);
						serviceOutcome.setData(elligibilityModel);
						return serviceOutcome;
					}
				}

				// Last Name
				if (GenericComponentValidations
						.isStringNullOrBlank(elligibilityModel.getDependents().get(i).getLastName())) {
					resp = GenericComponentValidations
							.isalphabets(elligibilityModel.getDependents().get(i).getLastName());
					if (!resp || elligibilityModel.getDependents().get(i).getLastName().length() > 60) {
						serviceOutcome.setMessage("Dependants Last Name should contain only alphabets");
						serviceOutcome.setOutcome(false);
						serviceOutcome.setData(elligibilityModel);
						return serviceOutcome;
					}
				}
				// middle Name
				if (GenericComponentValidations
						.isStringNullOrBlank(elligibilityModel.getDependents().get(i).getMiddleName())) {
					resp = GenericComponentValidations
							.isalphabets(elligibilityModel.getDependents().get(i).getMiddleName());
					if (!resp || elligibilityModel.getDependents().get(i).getMiddleName().length() > 30) {
						serviceOutcome.setMessage("Dependants Last Name should contain only alphabets");
						serviceOutcome.setOutcome(false);
						serviceOutcome.setData(elligibilityModel);
						return serviceOutcome;
					}
				}
				// suffix
				if (GenericComponentValidations
						.isStringNullOrBlank(elligibilityModel.getDependents().get(i).getMiddleName())) {
					if (elligibilityModel.getDependents().get(i).getSuffix().length() > 10) {
						serviceOutcome.setMessage("Suffix cannot be more trhan 10 digits");
						serviceOutcome.setOutcome(false);
						serviceOutcome.setData(elligibilityModel);
						return serviceOutcome;
					}
				}

				// Gender
				if (GenericComponentValidations
						.isStringNullOrBlank(elligibilityModel.getDependents().get(i).getGender())) {
					if (!elligibilityModel.getSubscriber().getGender().equalsIgnoreCase("M")
							&& !elligibilityModel.getSubscriber().getGender().equalsIgnoreCase("F")
							&& !elligibilityModel.getSubscriber().getGender().equalsIgnoreCase("U")) {
						serviceOutcome.setMessage("Kindly specify proper Gender");
						serviceOutcome.setOutcome(false);
						serviceOutcome.setData(elligibilityModel);
						return serviceOutcome;
					}
				}

				// Date Of Birth
				if (!GenericComponentValidations
						.isStringNullOrBlank(elligibilityModel.getDependents().get(i).getDateOfBirth())) {
					serviceOutcome.setMessage("Dependants Date Of Birth cannot be null");
					serviceOutcome.setOutcome(false);
					serviceOutcome.setData(elligibilityModel);
					return serviceOutcome;
				} else {
					resp = GenericComponentValidations
							.isValidDate(elligibilityModel.getDependents().get(i).getDateOfBirth());
					if (!resp) {
						serviceOutcome.setMessage("Dependants Date Of Birth is not valid");
						serviceOutcome.setOutcome(false);
						serviceOutcome.setData(elligibilityModel);
						return serviceOutcome;
					}
				}
				// SSN
				if (GenericComponentValidations
						.isStringNullOrBlank(elligibilityModel.getDependents().get(i).getSsn())) {
					if (GenericComponentValidations.islength50(elligibilityModel.getDependents().get(i).getSsn())) {
						serviceOutcome.setMessage("Dependants SSN cannot exceed 50 characters");
						serviceOutcome.setOutcome(false);
						serviceOutcome.setData(elligibilityModel);
						return serviceOutcome;
					}
				}

				// Group Number
				if (GenericComponentValidations
						.isStringNullOrBlank(elligibilityModel.getDependents().get(i).getGroupNumber())) {
					resp = GenericComponentValidations
							.isdigit(elligibilityModel.getDependents().get(i).getGroupNumber());
					if (resp != true || elligibilityModel.getDependents().get(i).getGroupNumber().length() != 10) {
						serviceOutcome.setMessage("Length of Group Number should be valid 10 digits");
						serviceOutcome.setData(elligibilityModel);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}

				// ID Card
				if (GenericComponentValidations
						.isStringNullOrBlank(elligibilityModel.getDependents().get(i).getIdCard())) {
					resp = GenericComponentValidations
							.isalphanumeric(elligibilityModel.getDependents().get(i).getIdCard());
					if (!resp) {
						serviceOutcome.setMessage("Subscriber Id Card should be alphanumeric");
						serviceOutcome.setOutcome(false);
						serviceOutcome.setData(elligibilityModel);
						return serviceOutcome;
					}

				}
				// Provider Code
				if (GenericComponentValidations
						.isStringNullOrBlank(elligibilityModel.getDependents().get(i).getProviderCode())) {
					if (!elligibilityModel.getDependents().get(i).getProviderCode()
							.matches("AD|AT|BI|CO|CV|H|HH|LA|OT|P1|P2|PC|PE|R|RF|SB|SK|SU")) {
						serviceOutcome.setMessage(
								"Allowed Values are: 'AD' Admitting, 'AT' Attending, 'BI' Billing, 'CO' Consulting, 'CV' Covering, 'H' Hospital, 'HH' Home Health Care, 'LA' Laboratory, 'OT' Other Physician, 'P1' Pharmacist, 'P2' Pharmacy, 'PC' Primary Care Physician, 'PE' Performing, 'R' Rural Health Clinic, 'RF' Referring, 'SK' Skilled Nursing Facility, 'SU' Supervising");
						serviceOutcome.setData(elligibilityModel);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}

				}
				// referenceIdentificationQualifier
				if (GenericComponentValidations.isStringNullOrBlank(
						elligibilityModel.getDependents().get(i).getReferenceIdentificationQualifier())) {
					if (!elligibilityModel.getDependents().get(i).getReferenceIdentificationQualifier()
							.matches("9K|D3|EI|HPI|PXC|SY|TJ")) {
						serviceOutcome.setMessage("referenceIdentificationQualifier should be proper");
						serviceOutcome.setData(elligibilityModel);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}

				}
				// providerIdentifier
				if (GenericComponentValidations
						.isStringNullOrBlank(elligibilityModel.getDependents().get(i).getProviderIdentifier())) {
					if (!GenericComponentValidations
							.isalphanumeric(elligibilityModel.getDependents().get(i).getProviderIdentifier())
							|| elligibilityModel.getSubscriber().getProviderIdentifier().length() > 50) {
						serviceOutcome.setMessage("referenceIdentificationQualifier should be proper");
						serviceOutcome.setData(elligibilityModel);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}

				}
				// healthCareCodeInformation
				if (!Objects.isNull(elligibilityModel.getDependents().get(i).getHealthCareCodeInformation())) {
					for (int j = 0; j < elligibilityModel.getSubscriber().getHealthCareCodeInformation().size(); j++) {
						// diagnosisTypeCode
						if (GenericComponentValidations.isStringNullOrBlank(elligibilityModel.getDependents().get(i)
								.getHealthCareCodeInformation().get(j).getDiagnosisTypeCode())) {
							if (GenericComponentValidations.isdiagnosisTypeCode(elligibilityModel.getDependents().get(i)
									.getHealthCareCodeInformation().get(j).getDiagnosisTypeCode())) {
								serviceOutcome.setMessage("Diagnosis Type Code should be proper");
								serviceOutcome.setData(elligibilityModel);
								serviceOutcome.setOutcome(false);
								return serviceOutcome;
							}
						}
						// diagnosisCode
						if (GenericComponentValidations.isStringNullOrBlank(elligibilityModel.getDependents().get(i)
								.getHealthCareCodeInformation().get(j).getDiagnosisCode())) {
							if (!GenericComponentValidations.isalphanumeric(elligibilityModel.getDependents().get(i)
									.getHealthCareCodeInformation().get(j).getDiagnosisTypeCode())
									|| elligibilityModel.getDependents().get(i).getHealthCareCodeInformation().get(j)
											.getDiagnosisTypeCode().length() > 30) {
								serviceOutcome.setMessage("Diagnosis Code should be proper");
								serviceOutcome.setData(elligibilityModel);
								serviceOutcome.setOutcome(false);
								return serviceOutcome;
							}
						}
					}
				}

				// ADDRESS
				if (!Objects.isNull(elligibilityModel.getDependents().get(i).getAddress())) {
					// address1
					if (GenericComponentValidations
							.isStringNullOrBlank(elligibilityModel.getDependents().get(i).getAddress().getAddress1())) {
						if (GenericComponentValidations
								.islength55(elligibilityModel.getDependents().get(i).getAddress().getAddress1())) {
							serviceOutcome.setMessage("Address1 should be 2-55 characters");
							serviceOutcome.setData(elligibilityModel);
							serviceOutcome.setOutcome(false);
							return serviceOutcome;
						}
					}
					// address2
					if (GenericComponentValidations
							.isStringNullOrBlank(elligibilityModel.getDependents().get(i).getAddress().getAddress2())) {
						if (GenericComponentValidations
								.islength55(elligibilityModel.getDependents().get(i).getAddress().getAddress2())) {
							serviceOutcome.setMessage("Address2 should be 2-55 characters");
							serviceOutcome.setData(elligibilityModel);
							serviceOutcome.setOutcome(false);
							return serviceOutcome;
						}
					}
					// city
					if (GenericComponentValidations
							.isStringNullOrBlank(elligibilityModel.getDependents().get(i).getAddress().getCity())) {
						if (GenericComponentValidations
								.isCity(elligibilityModel.getDependents().get(i).getAddress().getCity())) {
							serviceOutcome.setMessage("City should be 2-30 characters");
							serviceOutcome.setData(elligibilityModel);
							serviceOutcome.setOutcome(false);
							return serviceOutcome;
						}
					}
					// state
					if (GenericComponentValidations
							.isStringNullOrBlank(elligibilityModel.getDependents().get(i).getAddress().getState())) {
						if (GenericComponentValidations
								.isState(elligibilityModel.getDependents().get(i).getAddress().getState())) {
							serviceOutcome.setMessage("State should be 0-2 characters");
							serviceOutcome.setData(elligibilityModel);
							serviceOutcome.setOutcome(false);
							return serviceOutcome;
						}
					}
					// Postal Code
					if (GenericComponentValidations.isStringNullOrBlank(
							elligibilityModel.getDependents().get(i).getAddress().getPostalCode())) {
						if (!GenericComponentValidations
								.isdigit(elligibilityModel.getDependents().get(i).getAddress().getPostalCode())) {
							serviceOutcome.setMessage("Postal Code should be 0-15 characters");
							serviceOutcome.setData(elligibilityModel);
							serviceOutcome.setOutcome(false);
							return serviceOutcome;
						}
					}
				}
			}
		}
		// ENCOUNTER
		// Date Of Service
		if (!Objects.isNull(elligibilityModel.getEncounter())) {
			if (GenericComponentValidations.isStringNullOrBlank(elligibilityModel.getEncounter().getDateOfService())) {
				if (GenericComponentValidations
						.isStringNullOrBlank(elligibilityModel.getEncounter().getBeginningDateOfService())
						|| GenericComponentValidations
								.isStringNullOrBlank(elligibilityModel.getEncounter().getEndDateOfService())) {
					serviceOutcome.setMessage(
							"A single date may be provided in dateOfService or a date range with both beginningDateOfService and endDateOfService");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				} else {
					resp = GenericComponentValidations.isValidDate(elligibilityModel.getEncounter().getDateOfService());
					if (!resp) {
						serviceOutcome.setMessage("Date of Service is not valid");
						serviceOutcome.setOutcome(false);
						serviceOutcome.setData(elligibilityModel);
						return serviceOutcome;
					}
				}
			} else {
				if (!(GenericComponentValidations
						.isStringNullOrBlank(elligibilityModel.getEncounter().getBeginningDateOfService())
						|| GenericComponentValidations
								.isStringNullOrBlank(elligibilityModel.getEncounter().getEndDateOfService()))) {
					serviceOutcome.setMessage(
							"A single date may be provided in dateOfService or a date range with both beginningDateOfService and endDateOfService");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				} else {
					resp = GenericComponentValidations.isValidDate(elligibilityModel.getEncounter().getDateOfService());
					if (!resp) {
						serviceOutcome.setMessage("Beginning/End Date of Service is not valid");
						serviceOutcome.setOutcome(false);
						serviceOutcome.setData(elligibilityModel);
						return serviceOutcome;
					}
				}
			}

			// System Codes
			if (!Objects.isNull(elligibilityModel.getEncounter().getServiceTypeCodes())) {
				Iterator<String> it = elligibilityModel.getEncounter().getServiceTypeCodes().iterator();
				while (it.hasNext())
					if (!(it.next().toString().matches("[a-zA-Z0-9]+"))) {
						serviceOutcome.setMessage("Systemcodes must be alphanumeric");
						serviceOutcome.setData(elligibilityModel);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
			}
			// referenceIdentificationQualifier
			if (GenericComponentValidations
					.isStringNullOrBlank(elligibilityModel.getEncounter().getReferenceIdentificationQualifier())) {
				if (!(elligibilityModel.getEncounter().getReferenceIdentificationQualifier().equalsIgnoreCase("9F")
						|| elligibilityModel.getEncounter().getReferenceIdentificationQualifier()
								.equalsIgnoreCase("G1"))) {
					serviceOutcome.setMessage("Reference Identification Qualifier must be Proper");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// Industry Code
			if (GenericComponentValidations.isStringNullOrBlank(elligibilityModel.getEncounter().getIndustryCode())) {
				if (GenericComponentValidations.isIndustryCode(elligibilityModel.getEncounter().getIndustryCode())) {
					serviceOutcome.setMessage("Industry Code must be Proper");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// productOrServiceIDQualifier
			if (GenericComponentValidations
					.isStringNullOrBlank(elligibilityModel.getEncounter().getProductOrServiceIDQualifier())) {
				if (!elligibilityModel.getEncounter().getProductOrServiceIDQualifier().matches("AD|CJ|HC|ID|IV|N4|ZZ")) {
					serviceOutcome.setMessage("ProductOr ServiceID Qualifier must be Proper");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// procedureCode
			if (GenericComponentValidations.isStringNullOrBlank(elligibilityModel.getEncounter().getProcedureCode())) {
				if (elligibilityModel.getEncounter().getProcedureCode().length() > 48) {
					serviceOutcome.setMessage("Procedure Code must not be more than 48 characters");
					serviceOutcome.setData(elligibilityModel);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
		}
		return serviceOutcome;

	}
}
