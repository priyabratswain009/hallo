package com.sunknowledge.changehealthcare.validation;

import java.util.Objects;

import com.sunknowledge.changehealthcare.core.ServiceOutcome;
import com.sunknowledge.changehealthcare.pojo.claimStatus.ClaimStatusRequestInput;

public class ClaimStatusComponentValidation {

	public static ServiceOutcome<ClaimStatusRequestInput> validateUserInputs(
			ClaimStatusRequestInput claimStatusRequestInput) {
		ServiceOutcome<ClaimStatusRequestInput> serviceOutcome = new ServiceOutcome<ClaimStatusRequestInput>();
		serviceOutcome.setOutcome(true);
		boolean isValid = true;
		boolean resp = false;
		// Control Number Validation
		if (GenericComponentValidations.isStringNullOrBlank(claimStatusRequestInput.getControlNumber())) {

			resp = GenericComponentValidations.isdigit(claimStatusRequestInput.getControlNumber());
			if (resp != true || claimStatusRequestInput.getControlNumber().length() != 9) {
				serviceOutcome.setMessage("Length of Control number should be valid 9 digits");

				serviceOutcome.setData(claimStatusRequestInput);
				serviceOutcome.setOutcome(false);
				isValid = false;
			}
			if (!isValid)
				return serviceOutcome;
		} else {
			serviceOutcome.setData(claimStatusRequestInput);
			serviceOutcome.setMessage("Controll Number cannot be null");
			serviceOutcome.setOutcome(false);
			return serviceOutcome;
		}
		// tradingPartnerServiceId
		if (GenericComponentValidations.isStringNullOrBlank(claimStatusRequestInput.getTradingPartnerServiceId())) {
			resp = GenericComponentValidations.isalphanumeric(claimStatusRequestInput.getTradingPartnerServiceId());
			if (!resp || GenericComponentValidations.islength80(claimStatusRequestInput.getTradingPartnerServiceId())) {
				serviceOutcome.setMessage("Trading Partner Service Id should be alphanumeric");
				serviceOutcome.setOutcome(false);
				serviceOutcome.setData(claimStatusRequestInput);
				isValid = false;
			}
			if (!isValid)
				return serviceOutcome;
		} else {
			serviceOutcome.setData(claimStatusRequestInput);
			serviceOutcome.setMessage("Trading Partner Service Id cannot be null");
			serviceOutcome.setOutcome(false);
			return serviceOutcome;
		}

		// PROVIDER
		if (!Objects.isNull(claimStatusRequestInput.getProviders())) {
			// Organization Name
			for (int i = 0; i < claimStatusRequestInput.getProviders().size(); i++) {
				if (GenericComponentValidations
						.isStringNullOrBlank(claimStatusRequestInput.getProviders().get(i).getOrganizationName())) {
					if (!GenericComponentValidations
							.isName(claimStatusRequestInput.getProviders().get(i).getOrganizationName())
							|| GenericComponentValidations
									.islength60(claimStatusRequestInput.getProviders().get(i).getOrganizationName())) {
						serviceOutcome.setMessage("Provider OrganizationName or LastName can only have alphabets");
						serviceOutcome.setData(claimStatusRequestInput);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// First Name
				if (GenericComponentValidations
						.isStringNullOrBlank(claimStatusRequestInput.getProviders().get(i).getFirstName())) {
					if (!GenericComponentValidations
							.isName(claimStatusRequestInput.getProviders().get(i).getFirstName())
							|| GenericComponentValidations
									.islength35(claimStatusRequestInput.getProviders().get(i).getFirstName())) {
						serviceOutcome.setMessage("Provider OrganizationName or LastName can only have alphabets");
						serviceOutcome.setData(claimStatusRequestInput);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// Last Name
				if (GenericComponentValidations
						.isStringNullOrBlank(claimStatusRequestInput.getProviders().get(i).getLastName())) {
					if (!GenericComponentValidations.isName(claimStatusRequestInput.getProviders().get(i).getLastName())
							|| GenericComponentValidations
									.islength35(claimStatusRequestInput.getProviders().get(i).getLastName())) {
						serviceOutcome.setMessage("Provider OrganizationName or LastName can only have alphabets");
						serviceOutcome.setData(claimStatusRequestInput);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// NPI
				if (GenericComponentValidations
						.isStringNullOrBlank(claimStatusRequestInput.getProviders().get(i).getNpi())) {
					if (GenericComponentValidations
							.islength80(claimStatusRequestInput.getProviders().get(i).getNpi())) {
						serviceOutcome.setMessage("Length of NPI should not be more than 80 digit");
						serviceOutcome.setData(claimStatusRequestInput);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// spn
				if (GenericComponentValidations
						.isStringNullOrBlank(claimStatusRequestInput.getProviders().get(i).getSpn())) {
					if (GenericComponentValidations
							.islength80(claimStatusRequestInput.getProviders().get(i).getSpn())) {
						serviceOutcome.setMessage("Length of SPN should not be more than 80 digit");
						serviceOutcome.setData(claimStatusRequestInput);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// tin
				if (GenericComponentValidations
						.isStringNullOrBlank(claimStatusRequestInput.getProviders().get(i).getSpn())) {
					if (GenericComponentValidations
							.islength80(claimStatusRequestInput.getProviders().get(i).getSpn())) {
						serviceOutcome.setMessage("Length of SPN should not be more than 80 digit");
						serviceOutcome.setData(claimStatusRequestInput);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// taxId
				if (GenericComponentValidations
						.isStringNullOrBlank(claimStatusRequestInput.getProviders().get(i).getTaxId())) {
					if (GenericComponentValidations
							.islength80(claimStatusRequestInput.getProviders().get(i).getTaxId())) {
						serviceOutcome.setMessage("Length of Tax Id cannot be more than 80 digit");
						serviceOutcome.setData(claimStatusRequestInput);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// etin
				if (GenericComponentValidations
						.isStringNullOrBlank(claimStatusRequestInput.getProviders().get(i).getEtin())) {
					if (GenericComponentValidations
							.islength80(claimStatusRequestInput.getProviders().get(i).getEtin())) {
						serviceOutcome.setMessage("Length of Tax Id cannot be more than 80 digit");
						serviceOutcome.setData(claimStatusRequestInput);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// Provider Type
				if (GenericComponentValidations
						.isStringNullOrBlank(claimStatusRequestInput.getProviders().get(i).getProviderType())) {
					String value = claimStatusRequestInput.getProviders().get(i).getProviderType();
					if (!(value.equalsIgnoreCase("BillingProvider") || value.equalsIgnoreCase("ServiceProvider"))) {
						serviceOutcome.setMessage("Kindly enter Proper Provider Type values");
						serviceOutcome.setData(claimStatusRequestInput);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
			}

		}
		// SUBSCRIBER
		// Member ID
		if (GenericComponentValidations.isStringNullOrBlank(claimStatusRequestInput.getSubscriber().getMemberId())) {
			resp = GenericComponentValidations.isdigit(claimStatusRequestInput.getSubscriber().getMemberId());
			if (!resp
					&& GenericComponentValidations.islength80(claimStatusRequestInput.getSubscriber().getMemberId())) {
				serviceOutcome.setMessage("Subscriber Member ID should be valid 10digits number");
				serviceOutcome.setData(claimStatusRequestInput);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			}
		} else {
			serviceOutcome.setMessage("Subscriber Member ID cannot be null");
			serviceOutcome.setData(claimStatusRequestInput);
			serviceOutcome.setOutcome(false);
			return serviceOutcome;
		}
		// First Name
		if (GenericComponentValidations.isStringNullOrBlank(claimStatusRequestInput.getSubscriber().getFirstName())) {
			resp = GenericComponentValidations.isName(claimStatusRequestInput.getSubscriber().getFirstName());
			if (!resp
					|| GenericComponentValidations.islength35(claimStatusRequestInput.getSubscriber().getFirstName())) {
				serviceOutcome.setMessage("Subscriber First Name should be alphabets");
				serviceOutcome.setData(claimStatusRequestInput);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			}
		} else {
			serviceOutcome.setMessage("Subscriber First Name cannot be null");
			serviceOutcome.setData(claimStatusRequestInput);
			serviceOutcome.setOutcome(false);
			return serviceOutcome;
		}
		// Last Name
		if (GenericComponentValidations.isStringNullOrBlank(claimStatusRequestInput.getSubscriber().getLastName())) {
			resp = GenericComponentValidations.isalphabets(claimStatusRequestInput.getSubscriber().getLastName());
			if (!resp
					|| GenericComponentValidations.islength35(claimStatusRequestInput.getSubscriber().getLastName())) {
				serviceOutcome.setMessage("Subscriber Last Name should be alphabets");
				serviceOutcome.setData(claimStatusRequestInput);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			}
		} else {
			serviceOutcome.setMessage("Subscriber Last Name cannot be null");
			serviceOutcome.setData(claimStatusRequestInput);
			serviceOutcome.setOutcome(false);
			return serviceOutcome;
		}
		// Gender
		if (GenericComponentValidations.isStringNullOrBlank(claimStatusRequestInput.getSubscriber().getGender())) {

			resp = GenericComponentValidations.isalphabets(claimStatusRequestInput.getSubscriber().getGender());
			if (!claimStatusRequestInput.getSubscriber().getGender().equalsIgnoreCase("M")
					&& !claimStatusRequestInput.getSubscriber().getGender().equalsIgnoreCase("F")
					&& !claimStatusRequestInput.getSubscriber().getGender().equalsIgnoreCase("U")) {
				serviceOutcome.setMessage("Kindly specify proper Gender");
				serviceOutcome.setData(claimStatusRequestInput);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			}
		}
		// Date Of Birth
		if (!GenericComponentValidations
				.isStringNullOrBlank(claimStatusRequestInput.getSubscriber().getDateOfBirth())) {
			serviceOutcome.setMessage("Subscriber Date Of Birth cannot be null");
			serviceOutcome.setOutcome(false);
			serviceOutcome.setData(claimStatusRequestInput);
			return serviceOutcome;
		} else {
			resp = GenericComponentValidations.isValidDate(claimStatusRequestInput.getSubscriber().getDateOfBirth());
			if (!resp) {
				serviceOutcome.setMessage("Subscriber Date Of Birth is not valid");
				serviceOutcome.setOutcome(false);
				serviceOutcome.setData(claimStatusRequestInput);
				return serviceOutcome;
			}
		}
		// Group Number
		if (GenericComponentValidations.isStringNullOrBlank(claimStatusRequestInput.getSubscriber().getGroupNumber())) {
			resp = GenericComponentValidations.isdigit(claimStatusRequestInput.getSubscriber().getGroupNumber());
			if (resp != true || GenericComponentValidations
					.islength50(claimStatusRequestInput.getSubscriber().getGroupNumber())) {
				serviceOutcome.setMessage("Length of Group Number cannot be more than 50 digits");
				serviceOutcome.setData(claimStatusRequestInput);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			}
		}

		// DEPENDANT
		if (!Objects.isNull(claimStatusRequestInput.getDependent())) {
			// First Name
			if (GenericComponentValidations
					.isStringNullOrBlank(claimStatusRequestInput.getDependent().getFirstName())) {
				resp = GenericComponentValidations.isName(claimStatusRequestInput.getDependent().getFirstName());
				if (!resp || GenericComponentValidations
						.islength35(claimStatusRequestInput.getDependent().getFirstName())) {
					serviceOutcome.setMessage("Dependent First Name should be alphabets");
					serviceOutcome.setData(claimStatusRequestInput);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			} else {
				serviceOutcome.setMessage("Dependent First Name cannot be blank");
				serviceOutcome.setData(claimStatusRequestInput);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			}
			// Last Name
			if (GenericComponentValidations.isStringNullOrBlank(claimStatusRequestInput.getDependent().getLastName())) {
				resp = GenericComponentValidations.isalphabets(claimStatusRequestInput.getDependent().getLastName());
				if (!resp || GenericComponentValidations
						.islength35(claimStatusRequestInput.getDependent().getFirstName())) {
					serviceOutcome.setMessage("Dependent Last Name should be alphabets");
					serviceOutcome.setData(claimStatusRequestInput);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			} else {
				serviceOutcome.setMessage("Dependent Last Name cannot be blank");
				serviceOutcome.setData(claimStatusRequestInput);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			}
			// Gender
			if (GenericComponentValidations.isStringNullOrBlank(claimStatusRequestInput.getDependent().getGender())) {

				resp = GenericComponentValidations.isalphabets(claimStatusRequestInput.getDependent().getGender());
				if (!claimStatusRequestInput.getDependent().getGender().equalsIgnoreCase("M")
						&& !claimStatusRequestInput.getDependent().getGender().equalsIgnoreCase("F")
						&& !claimStatusRequestInput.getDependent().getGender().equalsIgnoreCase("U")) {
					serviceOutcome.setMessage("Kindly specify proper Gender for Dependent");
					serviceOutcome.setData(claimStatusRequestInput);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// Date Of Birth
			if (!GenericComponentValidations
					.isStringNullOrBlank(claimStatusRequestInput.getDependent().getDateOfBirth())) {
				serviceOutcome.setMessage("Dependent Date Of Birth cannot be null");
				serviceOutcome.setOutcome(false);
				serviceOutcome.setData(claimStatusRequestInput);
				return serviceOutcome;
			} else {
				resp = GenericComponentValidations.isValidDate(claimStatusRequestInput.getDependent().getDateOfBirth());
				if (!resp) {
					serviceOutcome.setMessage("Dependent Date Of Birth is not valid");
					serviceOutcome.setOutcome(false);
					serviceOutcome.setData(claimStatusRequestInput);
					return serviceOutcome;
				}
			}
			// Group Number
			if (GenericComponentValidations
					.isStringNullOrBlank(claimStatusRequestInput.getDependent().getGroupNumber())) {
				resp = GenericComponentValidations.isdigit(claimStatusRequestInput.getDependent().getGroupNumber());
				if (resp != true || GenericComponentValidations
						.islength50(claimStatusRequestInput.getDependent().getGroupNumber())) {
					serviceOutcome.setMessage("Length of Dependent Group Number cannot be more than 50 digits");
					serviceOutcome.setData(claimStatusRequestInput);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
		}

		// ENCOUNTER
		// Date Of Service
		if (!Objects.isNull(claimStatusRequestInput.getEncounter())) {

			if (!GenericComponentValidations
					.isStringNullOrBlank(claimStatusRequestInput.getEncounter().getBeginningDateOfService())) {
				serviceOutcome.setMessage(
						"A single date may be provided in dateOfService or a date range with both beginningDateOfService and endDateOfService");
				serviceOutcome.setData(claimStatusRequestInput);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			}
			if (!GenericComponentValidations
					.isStringNullOrBlank(claimStatusRequestInput.getEncounter().getEndDateOfService())) {
				serviceOutcome.setMessage(
						"A single date may be provided in dateOfService or a date range with both beginningDateOfService and endDateOfService");
				serviceOutcome.setData(claimStatusRequestInput);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			}
			// Tracking Number
			if (GenericComponentValidations
					.isStringNullOrBlank(claimStatusRequestInput.getEncounter().getTrackingNumber())) {
				if (GenericComponentValidations
						.islength50(claimStatusRequestInput.getEncounter().getTrackingNumber())) {
					serviceOutcome.setMessage("Maximum Length of Tracking Number is 50 characters");
					serviceOutcome.setData(claimStatusRequestInput);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// Trading Partner Claim Number
			if (GenericComponentValidations
					.isStringNullOrBlank(claimStatusRequestInput.getEncounter().getTradingPartnerClaimNumber())) {
				if (GenericComponentValidations
						.islength50(claimStatusRequestInput.getEncounter().getTradingPartnerClaimNumber())) {
					serviceOutcome.setMessage("Maximum Length of Trading Partner Claim Number is 50 characters");
					serviceOutcome.setData(claimStatusRequestInput);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// Billing Type
			if (GenericComponentValidations
					.isStringNullOrBlank(claimStatusRequestInput.getEncounter().getBillingType())) {
				if (GenericComponentValidations.islength50(claimStatusRequestInput.getEncounter().getBillingType())) {
					serviceOutcome.setMessage("Maximum Length of Billing Type is 50 characters");
					serviceOutcome.setData(claimStatusRequestInput);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// Patient Account Number
			if (GenericComponentValidations
					.isStringNullOrBlank(claimStatusRequestInput.getEncounter().getPatientAccountNumber())) {
				if (GenericComponentValidations
						.islength50(claimStatusRequestInput.getEncounter().getPatientAccountNumber())) {
					serviceOutcome.setMessage("Maximum Length of Patient Account Number is 50 characters");
					serviceOutcome.setData(claimStatusRequestInput);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// Pharmacy Prescription Number
			if (GenericComponentValidations
					.isStringNullOrBlank(claimStatusRequestInput.getEncounter().getPharmacyPrescriptionNumber())) {
				if (GenericComponentValidations
						.islength50(claimStatusRequestInput.getEncounter().getPharmacyPrescriptionNumber())) {
					serviceOutcome.setMessage("Maximum Length of Pharmacy Prescription Number is 50 characters");
					serviceOutcome.setData(claimStatusRequestInput);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// Clearing House Claim Number
			if (GenericComponentValidations
					.isStringNullOrBlank(claimStatusRequestInput.getEncounter().getClearingHouseClaimNumber())) {
				if (GenericComponentValidations
						.islength50(claimStatusRequestInput.getEncounter().getClearingHouseClaimNumber())) {
					serviceOutcome.setMessage("Maximum Length of Clearing House Claim Number is 50 characters");
					serviceOutcome.setData(claimStatusRequestInput);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// Submitted Amount
			if (GenericComponentValidations
					.isStringNullOrBlank(claimStatusRequestInput.getEncounter().getSubmittedAmount())) {
				if (GenericComponentValidations
						.islength50(claimStatusRequestInput.getEncounter().getSubmittedAmount())) {
					serviceOutcome.setMessage("Maximum Length of Submitted Amount is 18 characters");
					serviceOutcome.setData(claimStatusRequestInput);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
		}
		// SERVICE LINE INFORMATION
		if (!Objects.isNull(claimStatusRequestInput.getServiceLineInformation())) {
			String value = null;
			// productOrServiceIDQualifier
			if (GenericComponentValidations.isStringNullOrBlank(
					claimStatusRequestInput.getServiceLineInformation().getProductOrServiceIDQualifier())) {
				value = claimStatusRequestInput.getServiceLineInformation().getProductOrServiceIDQualifier();
				if (!value.matches("AD|ER|HC|HP|IV|N4|NU|WK")) {
					serviceOutcome.setMessage("Kindly enter Proper productOrServiceIDQualifier value");
					serviceOutcome.setData(claimStatusRequestInput);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
		}

		return serviceOutcome;
	}

}
