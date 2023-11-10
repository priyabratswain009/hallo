package com.sunknowledge.changehealthcare.validation;

import java.util.Objects;

import com.sunknowledge.changehealthcare.core.ServiceOutcome;
import com.sunknowledge.changehealthcare.pojo.professionalclaims.ProfessionalClaimSubmission;
import com.sunknowledge.changehealthcare.pojo.professionalclaims.ProfessionalClaimValidation;

public class ProfessionalComponentValidation {
	public static ServiceOutcome<ProfessionalClaimValidation> validateValidationUserInputs(
			ProfessionalClaimValidation professionalClaimValidation) {
		ServiceOutcome<ProfessionalClaimValidation> serviceOutcome = new ServiceOutcome<ProfessionalClaimValidation>();
		serviceOutcome.setOutcome(true);
		boolean isValid = true;
		boolean resp = false;
		String value = null;

		// Control Number Validation
		if (GenericComponentValidations.isStringNullOrBlank(professionalClaimValidation.getControlNumber())) {

			resp = GenericComponentValidations.isdigit(professionalClaimValidation.getControlNumber());
			if (resp != true || professionalClaimValidation.getControlNumber().length() != 9) {
				serviceOutcome.setMessage("Length of Control number should be valid 9 digits");

				serviceOutcome.setData(professionalClaimValidation);
				serviceOutcome.setOutcome(false);
				isValid = false;
			}
			if (!isValid)
				return serviceOutcome;
		} else {
			serviceOutcome.setData(professionalClaimValidation);
			serviceOutcome.setMessage("Controll Number cannot be null");
			serviceOutcome.setOutcome(false);
			return serviceOutcome;
		}

		// Trading Partner Service Id Validation
		if (GenericComponentValidations
				.isStringNullOrBlank(professionalClaimValidation.getSubscriber().getMemberId())) {
			resp = GenericComponentValidations
					.isalphanumeric(professionalClaimValidation.getSubscriber().getMemberId());
			if (!resp) {
				serviceOutcome.setMessage("Trading Partner Service Id should be alphanumeric");
				serviceOutcome.setOutcome(false);
				serviceOutcome.setData(professionalClaimValidation);
				isValid = false;
			}
			if (!isValid)
				return serviceOutcome;
		} else {
			serviceOutcome.setData(professionalClaimValidation);
			serviceOutcome.setMessage("Trading Partner Service Id cannot be null");
			serviceOutcome.setOutcome(false);
			return serviceOutcome;
		}

		// SUBMITTER
		// Organization Name
		if (!Objects.isNull(professionalClaimValidation.getSubmitter())) {
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getSubmitter().getOrganizationName())) {
				resp = GenericComponentValidations
						.isName(professionalClaimValidation.getSubmitter().getOrganizationName());
				if (!resp) {
					serviceOutcome.setMessage("Submitter Organization Name should contain only alphabets");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// First Name
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getSubmitter().getFirstName())) {
				resp = GenericComponentValidations.isName(professionalClaimValidation.getSubmitter().getFirstName());
				if (!resp || GenericComponentValidations
						.islength35(professionalClaimValidation.getSubmitter().getFirstName())) {
					serviceOutcome.setMessage("Submitter First Name should contain at max 35 alphabets");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// Last Name
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getSubmitter().getLastName())) {
				resp = GenericComponentValidations.isName(professionalClaimValidation.getSubmitter().getLastName());
				if (!resp || GenericComponentValidations
						.islength35(professionalClaimValidation.getSubmitter().getLastName())) {
					serviceOutcome.setMessage("Submitter Last Name should contain at max 35 alphabets");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// Middle Name
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getSubmitter().getMiddleName())) {
				resp = GenericComponentValidations.isName(professionalClaimValidation.getSubmitter().getMiddleName());
				if (!resp || GenericComponentValidations
						.islength35(professionalClaimValidation.getSubmitter().getMiddleName())) {
					serviceOutcome.setMessage("Submitter Middle Name should contain at max 35 alphabets");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// CONTACT INFORMATION
			if (!Objects.isNull(professionalClaimValidation.getSubmitter().getContactInformation())) {
				// Name
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getSubmitter().getContactInformation().getName())) {
					resp = GenericComponentValidations
							.isName(professionalClaimValidation.getSubmitter().getContactInformation().getName());
					if (!resp) {
						serviceOutcome.setMessage("Submitter Name should contain only alphabets");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// Phone Number
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getSubmitter().getContactInformation().getPhoneNumber())) {
					if (professionalClaimValidation.getSubmitter().getContactInformation().getPhoneNumber()
							.length() > 10) {
						serviceOutcome.setMessage("Phone Number should not contain more than 10 digits");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					} else {
						resp = GenericComponentValidations.isdigit(
								professionalClaimValidation.getSubmitter().getContactInformation().getPhoneNumber());
						if (!resp) {
							serviceOutcome.setMessage("Phone Number should only be numeric values");
							serviceOutcome.setData(professionalClaimValidation);
							serviceOutcome.setOutcome(false);
							return serviceOutcome;
						}
					}
				}
				// email
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getSubmitter().getContactInformation().getEmail())) {
					if (GenericComponentValidations
							.isemail(professionalClaimValidation.getSubmitter().getContactInformation().getEmail())) {
						serviceOutcome.setMessage("Kindly enter valid Submitter Email ID");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}

			} else {
				serviceOutcome.setMessage("Submitter Contact Information cannot be null");
				serviceOutcome.setData(professionalClaimValidation);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			}
		} else {
			serviceOutcome.setMessage("Submitter Information cannot be null");
			serviceOutcome.setData(professionalClaimValidation);
			serviceOutcome.setOutcome(false);
			return serviceOutcome;
		}

		// RECIEVER
		// Organization Name
		if (!Objects.isNull(professionalClaimValidation.getReceiver())) {
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getReceiver().getOrganizationName())) {
				resp = GenericComponentValidations
						.isName(professionalClaimValidation.getReceiver().getOrganizationName());
				if (!resp) {
					serviceOutcome.setMessage("Reciever Organization Name should contain only alphabets");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			} else {
				serviceOutcome.setMessage("Reciever Organization Name cannot be null");
				serviceOutcome.setData(professionalClaimValidation);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			}
		} else {
			serviceOutcome.setMessage("Reciever Object cannot be null");
			serviceOutcome.setData(professionalClaimValidation);
			serviceOutcome.setOutcome(false);
			return serviceOutcome;
		}

		// SUBSCRIBER
		if (!Objects.isNull(professionalClaimValidation.getSubscriber())) {
			// Member ID
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getSubscriber().getMemberId())) {
				resp = GenericComponentValidations.isdigit(professionalClaimValidation.getSubscriber().getMemberId());
				if (resp != true || professionalClaimValidation.getSubscriber().getMemberId().length() != 10) {
					serviceOutcome.setMessage("Subscriber Member ID should contain only 10 digits");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// paymentResponsibilityLevelCode
			if (GenericComponentValidations.isStringNullOrBlank(
					professionalClaimValidation.getSubscriber().getPaymentResponsibilityLevelCode())) {
				resp = GenericComponentValidations
						.isalphabets(professionalClaimValidation.getSubscriber().getPaymentResponsibilityLevelCode());
				value = professionalClaimValidation.getSubscriber().getPaymentResponsibilityLevelCode();
				if (resp != true || !value.matches("A|B|C|D|E|F|G|H|P|S|T|U")) {
					serviceOutcome.setMessage("Kindly enter valid Payment Responsibility Level Code");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			} else {
				serviceOutcome.setMessage("Payment Responsibility Level Code cannot be null");
				serviceOutcome.setData(professionalClaimValidation);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			}
			// insuranceTypeCode
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getSubscriber().getInsuranceTypeCode())) {
				value = professionalClaimValidation.getSubscriber().getInsuranceTypeCode();
				if (!value.matches("12|13|14|15|16|41|42|43|47")) {
					serviceOutcome.setMessage("Kindly enter valid Insurance Type Code");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// subscriberGroupName
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getSubscriber().getSubscriberGroupName())) {
				if (!GenericComponentValidations
						.isName(professionalClaimValidation.getSubscriber().getSubscriberGroupName())) {
					serviceOutcome.setMessage("Kindly enter valid Subscriber Group Name");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// firstName
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getSubscriber().getFirstName())) {
				resp = GenericComponentValidations
						.isalphabets(professionalClaimValidation.getSubscriber().getFirstName());
				if (resp != true || GenericComponentValidations
						.islength35(professionalClaimValidation.getSubscriber().getFirstName())) {
					serviceOutcome.setMessage("Subscriber First Name should contain only alphabets");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// lastName
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getSubscriber().getLastName())) {
				resp = GenericComponentValidations
						.isalphabets(professionalClaimValidation.getSubscriber().getLastName());
				if (resp != true || GenericComponentValidations
						.islength35(professionalClaimValidation.getSubscriber().getFirstName())) {
					serviceOutcome.setMessage("Subscriber Last Name should contain only alphabets");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// middle Name
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getSubscriber().getMiddleName())) {
				resp = GenericComponentValidations
						.isalphabets(professionalClaimValidation.getSubscriber().getMiddleName());
				if (resp != true || GenericComponentValidations
						.islength35(professionalClaimValidation.getSubscriber().getMiddleName())) {
					serviceOutcome.setMessage("Subscriber Last Name should contain only alphabets");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// Gender
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getSubscriber().getGender())) {

				resp = GenericComponentValidations.isalphabets(professionalClaimValidation.getSubscriber().getGender());
				if (!professionalClaimValidation.getSubscriber().getGender().equalsIgnoreCase("M")
						&& !professionalClaimValidation.getSubscriber().getGender().equalsIgnoreCase("F")
						&& !professionalClaimValidation.getSubscriber().getGender().equalsIgnoreCase("U")) {
					serviceOutcome.setMessage("Kindly specify proper Gender");
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
				serviceOutcome.setData(professionalClaimValidation);
				if (!isValid)
					return serviceOutcome;
			}

			// Date Of Birth
			if (!GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getSubscriber().getDateOfBirth())) {
				serviceOutcome.setMessage("Subscriber Date Of Birth cannot be null");
				serviceOutcome.setOutcome(false);
				serviceOutcome.setData(professionalClaimValidation);
				return serviceOutcome;
			} else {
				resp = GenericComponentValidations
						.isValidDate(professionalClaimValidation.getSubscriber().getDateOfBirth());
				if (!resp) {
					serviceOutcome.setMessage("Subscriber Date Of Birth is not valid");
					serviceOutcome.setOutcome(false);
					serviceOutcome.setData(professionalClaimValidation);
					return serviceOutcome;
				}
			}
			// policyNumber
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getSubscriber().getPolicyNumber())) {
				resp = GenericComponentValidations
						.isdigit(professionalClaimValidation.getSubscriber().getPolicyNumber());
				if (resp != true || professionalClaimValidation.getSubscriber().getPolicyNumber().length() != 5) {
					serviceOutcome.setMessage("Policy Number should contain only 5 digits");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// CONTACT INFORMATION
			if (!Objects.isNull(professionalClaimValidation.getSubscriber().getContactInformation())) {
				// name
				if (!GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getSubscriber().getContactInformation().getName())) {
					serviceOutcome.setMessage("Subscriber Contact Information Name cannot be blank");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
				// Phone Number
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getSubscriber().getContactInformation().getPhoneNumber())) {
					if (professionalClaimValidation.getSubscriber().getContactInformation().getPhoneNumber()
							.length() > 10) {
						serviceOutcome.setMessage("Subscriber Phone Number should not contain more than 10 digits");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					} else {
						resp = GenericComponentValidations.isdigit(
								professionalClaimValidation.getSubscriber().getContactInformation().getPhoneNumber());
						if (!resp) {
							serviceOutcome.setMessage("Subscriber Phone Number should only be numeric values");
							serviceOutcome.setData(professionalClaimValidation);
							serviceOutcome.setOutcome(false);
							return serviceOutcome;
						}
					}
				}
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getSubscriber().getContactInformation().getEmail())) {
					if (GenericComponentValidations
							.isemail(professionalClaimValidation.getSubscriber().getContactInformation().getEmail())) {
						serviceOutcome.setMessage("Subscriber email should be proper");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
			}
			// ADDRESS
			if (!Objects.isNull(professionalClaimValidation.getSubscriber().getAddress())) {
				// address1
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getSubscriber().getAddress().getAddress1())) {
					resp = GenericComponentValidations.isalphanumericspace(
							professionalClaimValidation.getSubscriber().getAddress().getAddress1());
					if (resp != true) {
						serviceOutcome.setMessage("Subscriber Address 1 should contain only alphanumeic values");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// city
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getSubscriber().getAddress().getCity())) {
					resp = GenericComponentValidations
							.isalphanumericspace(professionalClaimValidation.getSubscriber().getAddress().getCity());
					if (resp != true) {
						serviceOutcome.setMessage("Subscriber City should contain only alphabets and numbers");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// state
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getSubscriber().getAddress().getState())) {
					resp = GenericComponentValidations
							.isalphabets(professionalClaimValidation.getSubscriber().getAddress().getState());
					if (resp != true
							|| professionalClaimValidation.getSubscriber().getAddress().getState().length() != 2) {
						serviceOutcome.setMessage("Subscriber State should contain only 2digit alphabets");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// Postal Code
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getSubscriber().getAddress().getPostalCode())) {
					resp = GenericComponentValidations
							.isdigit(professionalClaimValidation.getSubscriber().getAddress().getPostalCode());
					if (resp != true
							|| (professionalClaimValidation.getSubscriber().getAddress().getPostalCode().length() != 5
									&& professionalClaimValidation.getSubscriber().getAddress().getPostalCode()
											.length() != 9)) {
						serviceOutcome
								.setMessage("Subscriber Postal Code should contain only numbers of 5 or 9 digits");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
			}

		} else {
			serviceOutcome.setMessage("Subscriber Object cannot be null");
			serviceOutcome.setData(professionalClaimValidation);
			serviceOutcome.setOutcome(false);
			return serviceOutcome;
		}

		// DEDPENDANT
		if (!Objects.isNull(professionalClaimValidation.getDependents())) {
			// firstName
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getDependents().getFirstName())) {
				resp = GenericComponentValidations.isName(professionalClaimValidation.getDependents().getFirstName());
				if (resp != true) {
					serviceOutcome.setMessage("Dependant First Name should contain only alphabets");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			} else {
				serviceOutcome.setMessage("Dependant First Name cannot be null");
				serviceOutcome.setData(professionalClaimValidation);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			}
			// lastName
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getDependents().getLastName())) {
				resp = GenericComponentValidations.isName(professionalClaimValidation.getDependents().getLastName());
				if (resp != true) {
					serviceOutcome.setMessage("Dependant Last Name should contain only alphabets");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			} else {
				serviceOutcome.setMessage("Dependant Last Name cannot be null");
				serviceOutcome.setData(professionalClaimValidation);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			}
			// Gender
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getDependents().getGender())) {
				String gender = professionalClaimValidation.getDependents().getGender();
				resp = GenericComponentValidations.isalphabets(gender);
				if (!gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("F") && !gender.equalsIgnoreCase("U")) {
					serviceOutcome.setMessage("Kindly specify proper Dependant Gender");
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
				serviceOutcome.setData(professionalClaimValidation);
				if (!isValid)
					return serviceOutcome;
			}
			// Date Of Birth
			if (!GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getSubscriber().getDateOfBirth())) {
				serviceOutcome.setMessage("Dependant Date Of Birth cannot be null");
				serviceOutcome.setOutcome(false);
				serviceOutcome.setData(professionalClaimValidation);
				return serviceOutcome;
			} else {
				resp = GenericComponentValidations
						.isValidDate(professionalClaimValidation.getSubscriber().getDateOfBirth());
				if (!resp) {
					serviceOutcome.setMessage("Dependant Date Of Birth is not valid");
					serviceOutcome.setOutcome(false);
					serviceOutcome.setData(professionalClaimValidation);
					return serviceOutcome;
				}
			}
			// Member ID
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getDependents().getMemberId())) {
				resp = GenericComponentValidations.isdigit(professionalClaimValidation.getDependents().getMemberId());
				if (resp != true || professionalClaimValidation.getDependents().getMemberId().length() != 10) {
					serviceOutcome.setMessage("Dependant Member ID should contain only 10 digits");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// relationshipToSubscriberCode
			if (GenericComponentValidations.isStringNullOrBlank(
					professionalClaimValidation.getDependents().getRelationshipToSubscriberCode())) {
				resp = GenericComponentValidations
						.isdigit(professionalClaimValidation.getDependents().getRelationshipToSubscriberCode());
				if (resp != true) {
					serviceOutcome.setMessage("Dependant Relationship to Subscriber Code should be proper");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			} else {
				serviceOutcome.setMessage("Dependant Relationship to Subscriber Code cannot be null");
				serviceOutcome.setData(professionalClaimValidation);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			}
			// paymentResponsibilityLevelCode
			if (GenericComponentValidations.isStringNullOrBlank(
					professionalClaimValidation.getDependents().getPaymentResponsibilityLevelCode())) {
				resp = GenericComponentValidations
						.isalphabets(professionalClaimValidation.getDependents().getPaymentResponsibilityLevelCode());
				if (resp != true || professionalClaimValidation.getDependents().getPaymentResponsibilityLevelCode()
						.length() != 1) {
					serviceOutcome
							.setMessage("Dependant Payment Responsibility Level Code should contain only 1 digits");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// policyNumber
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getDependents().getPolicyNumber())) {
				resp = GenericComponentValidations
						.isdigit(professionalClaimValidation.getDependents().getPolicyNumber());
				if (resp != true || professionalClaimValidation.getDependents().getPolicyNumber().length() != 5) {
					serviceOutcome.setMessage("Dependant Policy Number should contain only 5 digits");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// CONTACT INFORMATION
			if (!Objects.isNull(professionalClaimValidation.getDependents().getContactInformation())) {
				// name
				if (!GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getDependents().getContactInformation().getName())) {
					serviceOutcome.setMessage("Subscriber Contact Information Name cannot be blank");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
				// Phone Number
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getDependents().getContactInformation().getPhoneNumber())) {
					if (professionalClaimValidation.getDependents().getContactInformation().getPhoneNumber()
							.length() > 10) {
						serviceOutcome.setMessage("Subscriber Phone Number should not contain more than 10 digits");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					} else {
						resp = GenericComponentValidations.isdigit(
								professionalClaimValidation.getDependents().getContactInformation().getPhoneNumber());
						if (!resp) {
							serviceOutcome.setMessage("Subscriber Phone Number should only be numeric values");
							serviceOutcome.setData(professionalClaimValidation);
							serviceOutcome.setOutcome(false);
							return serviceOutcome;
						}
					}
				}
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getDependents().getContactInformation().getEmail())) {
					if (GenericComponentValidations
							.isemail(professionalClaimValidation.getSubscriber().getContactInformation().getEmail())) {
						serviceOutcome.setMessage("Subscriber email should be proper");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
			}
			// ADDRESS
			if (!Objects.isNull(professionalClaimValidation.getDependents().getAddress())) {
				// address1
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getDependents().getAddress().getAddress1())) {
					resp = GenericComponentValidations.isalphanumericspace(
							professionalClaimValidation.getDependents().getAddress().getAddress1());
					if (resp != true) {
						serviceOutcome.setMessage("Dependant Address 1 should contain only alphanumeic values");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// city
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getDependents().getAddress().getCity())) {
					resp = GenericComponentValidations
							.isalphanumericspace(professionalClaimValidation.getDependents().getAddress().getCity());
					if (resp != true) {
						serviceOutcome.setMessage("Dependant City should contain only alphabets and numbers");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// state
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getDependents().getAddress().getState())) {
					resp = GenericComponentValidations
							.isalphabets(professionalClaimValidation.getDependents().getAddress().getState());
					if (resp != true
							|| professionalClaimValidation.getDependents().getAddress().getState().length() != 2) {
						serviceOutcome.setMessage("Dependant State should contain only alphabets of 2 digits");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// Postal Code
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getDependents().getAddress().getPostalCode())) {
					resp = GenericComponentValidations
							.isdigit(professionalClaimValidation.getDependents().getAddress().getPostalCode());
					if (resp != true
							|| (professionalClaimValidation.getDependents().getAddress().getPostalCode().length() != 5
									&& professionalClaimValidation.getDependents().getAddress().getPostalCode()
											.length() != 9)) {
						serviceOutcome.setMessage("Dependant Postal Code should contain only numbers of 5 or 9 digits");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
			}

		}
		// PAY TO ADDRESS
		if (!Objects.isNull(professionalClaimValidation.getPayToAddress())) {
			// Address1
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getPayToAddress().getAddress1())) {
				if (!GenericComponentValidations
						.isalphanumericspace(professionalClaimValidation.getPayToAddress().getAddress1())) {
					serviceOutcome.setMessage("providers address1 must contain alpha numeric values");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
			}
			// City
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getPayToAddress().getCity())) {
				if (!GenericComponentValidations
						.isalphanumericspace(professionalClaimValidation.getPayToAddress().getCity())) {
					serviceOutcome.setMessage("providers city must contain only Alphabets and numbers");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
			}
			// State
			if (!GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getPayToAddress().getState())) {
				boolean resp2 = GenericComponentValidations
						.isalphabets(professionalClaimValidation.getPayToAddress().getState());
				if (!resp2 || professionalClaimValidation.getPayToAddress().getState().length() != 2) {
					serviceOutcome.setMessage("providers State must contain only 2 Digit Alphabets");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
			}
			// Postal Code
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getPayToAddress().getPostalCode())) {
				resp = GenericComponentValidations
						.isdigit(professionalClaimValidation.getPayToAddress().getPostalCode());
				if (resp != true || (professionalClaimValidation.getPayToAddress().getPostalCode().length() != 5
						&& professionalClaimValidation.getPayToAddress().getPostalCode().length() != 9)) {
					serviceOutcome.setMessage("Providers Postal Code should contain only numbers of 5 or 9 digits");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
		}
		// PAY TO PLAN
		if (!Objects.isNull(professionalClaimValidation.getPayToPlan())) {
			// organizationName
			if (!GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getPayToPlan().getOrganizationName())) {
				serviceOutcome.setMessage("Pay to Plan Organization Name cannot be null");
				serviceOutcome.setData(professionalClaimValidation);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			}
			// Primary Identifier Code
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getPayToPlan().getPrimaryIdentifierTypeCode())) {
				value = professionalClaimValidation.getPayToPlan().getPrimaryIdentifierTypeCode();
				if (!value.matches("PI|XV")) {
					serviceOutcome.setMessage("Pay to Plan Primary Identification Code is not proper");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// Secondary Identifier Code
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getPayToPlan().getSecondaryIdentifierTypeCode())) {
				value = professionalClaimValidation.getPayToPlan().getSecondaryIdentifierTypeCode();
				if (!value.matches("2U|FY|NF")) {
					serviceOutcome.setMessage("Pay to Plan Secondary Identifier Code is not proper");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// Primary Identifier
			if (!GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getPayToPlan().getPrimaryIdentifier())) {
				serviceOutcome.setMessage("Pay to Plan Primary Identifier cannot be null");
				serviceOutcome.setData(professionalClaimValidation);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			}
			// Tax Identification Number
			if (!GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getPayToPlan().getTaxIdentificationNumber())) {
				serviceOutcome.setMessage("Pay to Plan Tax Identification Number cannot be null");
				serviceOutcome.setData(professionalClaimValidation);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			}
			// ADDRESS
			if (!Objects.isNull(professionalClaimValidation.getPayToPlan().getAddress())) {
				// Address1
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getPayToPlan().getAddress().getAddress1())) {
					if (!GenericComponentValidations.isalphanumericspace(
							professionalClaimValidation.getPayToPlan().getAddress().getAddress1())) {
						serviceOutcome.setMessage("providers address1 must contain alpha numeric values");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
				} else {
					serviceOutcome.setMessage("providers address1 cannot be null");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
				// City
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getPayToPlan().getAddress().getCity())) {
					if (!GenericComponentValidations
							.isalphanumericspace(professionalClaimValidation.getPayToPlan().getAddress().getCity())) {
						serviceOutcome.setMessage("providers city must contain only Alphabets and numbers");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
				} else {
					serviceOutcome.setMessage("providers city cannot be null");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
				// State
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getPayToPlan().getAddress().getState())) {
					boolean resp2 = GenericComponentValidations
							.isalphabets(professionalClaimValidation.getPayToPlan().getAddress().getState());
					if (!resp2 || professionalClaimValidation.getPayToPlan().getAddress().getState().length() != 2) {
						serviceOutcome.setMessage("providers State must contain only 2 Digit Alphabets");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
				}
				// Postal Code
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getPayToPlan().getAddress().getPostalCode())) {
					resp = GenericComponentValidations
							.isdigit(professionalClaimValidation.getPayToPlan().getAddress().getPostalCode());
					if (resp != true || (professionalClaimValidation.getPayToPlan().getAddress().getPostalCode()
							.length() != 5
							&& professionalClaimValidation.getPayToPlan().getAddress().getPostalCode().length() != 9)) {
						serviceOutcome.setMessage("Providers Postal Code should contain only numbers of 5 or 9 digits");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
			}
		}
		// PAYERS ADDRESS
		if (!Objects.isNull(professionalClaimValidation.getPayerAddress())) {
			// Address1
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getPayerAddress().getAddress1())) {
				if (!GenericComponentValidations
						.isalphanumericspace(professionalClaimValidation.getPayerAddress().getAddress1())) {
					serviceOutcome.setMessage("providers address1 must contain alpha numeric values");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
			} else {
				serviceOutcome.setMessage("providers address1 cannot be null");
				serviceOutcome.setData(professionalClaimValidation);
				serviceOutcome.setOutcome(false);
				isValid = false;
			}
			// City
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getPayerAddress().getCity())) {
				if (!GenericComponentValidations
						.isalphanumericspace(professionalClaimValidation.getPayerAddress().getCity())) {
					serviceOutcome.setMessage("providers city must contain only Alphabets and numbers");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
			} else {
				serviceOutcome.setMessage("providers city cannot be null");
				serviceOutcome.setData(professionalClaimValidation);
				serviceOutcome.setOutcome(false);
				isValid = false;
			}
			// State
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getPayerAddress().getState())) {
				boolean resp2 = GenericComponentValidations
						.isalphabets(professionalClaimValidation.getPayerAddress().getState());
				if (!resp2 || professionalClaimValidation.getPayerAddress().getState().length() != 2) {
					serviceOutcome.setMessage("providers State must contain only 2 Digit Alphabets");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
			}
			// Postal Code
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getPayerAddress().getPostalCode())) {
				resp = GenericComponentValidations
						.isdigit(professionalClaimValidation.getPayerAddress().getPostalCode());
				if (resp != true || (professionalClaimValidation.getPayerAddress().getPostalCode().length() != 5
						&& professionalClaimValidation.getPayerAddress().getPostalCode().length() != 9)) {
					serviceOutcome.setMessage("Providers Postal Code should contain only numbers of 5 or 9 digits");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
		}

		// BILLING
		if (!Objects.isNull(professionalClaimValidation.getBilling())) {
			// providerType
			if (!GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getBilling().getProviderType())) {
				serviceOutcome.setMessage("Billing Provider Type cannot be null");
				serviceOutcome.setData(professionalClaimValidation);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			} else {
				String providerType = professionalClaimValidation.getBilling().getProviderType().trim();
				if (!(providerType.equalsIgnoreCase("BillingProvider")
						|| providerType.equalsIgnoreCase("ReferringProvider")
						|| providerType.equalsIgnoreCase("RenderingProvider")
						|| providerType.equalsIgnoreCase("OrderingProvider")
						|| providerType.equalsIgnoreCase("SupervisingProvider"))) {
					serviceOutcome.setMessage("Provider Type is not Valid");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}

			}
			// ssn
			if (GenericComponentValidations.isStringNullOrBlank(professionalClaimValidation.getBilling().getSsn())) {
				if (GenericComponentValidations.isdigit(professionalClaimValidation.getBilling().getSsn())) {
					if (professionalClaimValidation.getBilling().getSsn().length() != 9) {
						serviceOutcome.setMessage("Billing SSN should be valid 9 digit numeric");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				} else {
					serviceOutcome.setMessage("Billing SSN should be numeric");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// employers id
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getBilling().getEmployerId())) {
				if (GenericComponentValidations.isdigit(professionalClaimValidation.getBilling().getEmployerId())) {
					if (professionalClaimValidation.getBilling().getSsn().length() != 9) {
						serviceOutcome.setMessage("Billing SSN should be valid 9 digit numeric");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				} else {
					serviceOutcome.setMessage("Billing SSN should be numeric");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			if (!Objects.isNull(professionalClaimValidation.getBilling().getAddress())) {
				// Address1
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getBilling().getAddress().getAddress1())) {
					if (!GenericComponentValidations
							.isalphanumericspace(professionalClaimValidation.getBilling().getAddress().getAddress1())) {
						serviceOutcome.setMessage("Billing address1 must contain alpha numeric values");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
				} else {
					serviceOutcome.setMessage("Billing address1 cannot be null");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
				// City
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getBilling().getAddress().getCity())) {
					if (!GenericComponentValidations
							.isalphanumericspace(professionalClaimValidation.getBilling().getAddress().getCity())) {
						serviceOutcome.setMessage("Billing city must contain only Alphabets and numbers");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
				} else {
					serviceOutcome.setMessage("Billing city cannot be null");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
				// State
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getBilling().getAddress().getState())) {
					boolean resp2 = GenericComponentValidations
							.isalphabets(professionalClaimValidation.getBilling().getAddress().getState());
					if (!resp2 || professionalClaimValidation.getBilling().getAddress().getState().length() != 2) {
						serviceOutcome.setMessage("Billing State must contain only 2 Digit Alphabets");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
				}
				// Postal Code
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getBilling().getAddress().getPostalCode())) {
					resp = GenericComponentValidations
							.isdigit(professionalClaimValidation.getBilling().getAddress().getPostalCode());
					if (resp != true || (professionalClaimValidation.getBilling().getAddress().getPostalCode()
							.length() != 5
							&& professionalClaimValidation.getBilling().getAddress().getPostalCode().length() != 9)) {
						serviceOutcome.setMessage("Billing Postal Code should contain only numbers of 5 or 9 digits");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
			}
			// CONTACT INFORMATION
			if (!Objects.isNull(professionalClaimValidation.getBilling().getContactInformation())) {
				// Phone Number
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getBilling().getContactInformation().getPhoneNumber())) {
					boolean resp3 = GenericComponentValidations
							.isdigit(professionalClaimValidation.getBilling().getContactInformation().getPhoneNumber());
					if (resp3 != true || professionalClaimValidation.getBilling().getContactInformation()
							.getPhoneNumber().length() > 10) {
						serviceOutcome.setMessage("Billing Contact Information Phone Number must be 10 Digit Numeric");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// Name
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getBilling().getContactInformation().getName())) {
					if (!GenericComponentValidations
							.isName(professionalClaimValidation.getBilling().getContactInformation().getName())) {
						serviceOutcome.setMessage("Billing Contact Information Name must contain alphabets");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// email
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getBilling().getContactInformation().getEmail())) {
					if (GenericComponentValidations
							.isemail(professionalClaimValidation.getBilling().getContactInformation().getEmail())) {
						serviceOutcome.setMessage("Kindly enter valid Billing Email ID");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
			}

		}

		// REFFERING
		if (!Objects.isNull(professionalClaimValidation.getReferring())) {
			// providerType
			if (!GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getReferring().getProviderType())) {
				serviceOutcome.setMessage("Referring Provider Type cannot be null");
				serviceOutcome.setData(professionalClaimValidation);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			} else {
				String providerType = professionalClaimValidation.getReferring().getProviderType().trim();
				if (!(providerType.equalsIgnoreCase("BillingProvider")
						|| providerType.equalsIgnoreCase("ReferringProvider")
						|| providerType.equalsIgnoreCase("RenderingProvider")
						|| providerType.equalsIgnoreCase("OrderingProvider")
						|| providerType.equalsIgnoreCase("SupervisingProvider"))) {
					serviceOutcome.setMessage("Provider Type is not Valid");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}

			}
			// ssn
			if (GenericComponentValidations.isStringNullOrBlank(professionalClaimValidation.getReferring().getSsn())) {
				if (GenericComponentValidations.isdigit(professionalClaimValidation.getReferring().getSsn())) {
					if (professionalClaimValidation.getReferring().getSsn().length() != 9) {
						serviceOutcome.setMessage("Referring SSN should be valid 9 digit numeric");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				} else {
					serviceOutcome.setMessage("Referring SSN should be numeric");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// employers id
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getReferring().getEmployerId())) {
				if (GenericComponentValidations.isdigit(professionalClaimValidation.getReferring().getEmployerId())) {
					if (professionalClaimValidation.getReferring().getSsn().length() != 9) {
						serviceOutcome.setMessage("Referring SSN should be valid 9 digit numeric");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				} else {
					serviceOutcome.setMessage("Referring SSN should be numeric");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			if (!Objects.isNull(professionalClaimValidation.getReferring().getAddress())) {
				// Address1
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getReferring().getAddress().getAddress1())) {
					if (!GenericComponentValidations.isalphanumericspace(
							professionalClaimValidation.getReferring().getAddress().getAddress1())) {
						serviceOutcome.setMessage("Referring address1 must contain alpha numeric values");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
				} else {
					serviceOutcome.setMessage("Referring address1 cannot be null");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
				// City
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getReferring().getAddress().getCity())) {
					if (!GenericComponentValidations
							.isalphanumericspace(professionalClaimValidation.getReferring().getAddress().getCity())) {
						serviceOutcome.setMessage("Referring city must contain only Alphabets and numbers");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
				} else {
					serviceOutcome.setMessage("Referring city cannot be null");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
				// State
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getReferring().getAddress().getState())) {
					boolean resp2 = GenericComponentValidations
							.isalphabets(professionalClaimValidation.getReferring().getAddress().getState());
					if (!resp2 || professionalClaimValidation.getReferring().getAddress().getState().length() != 2) {
						serviceOutcome.setMessage("Referring State must contain only 2 Digit Alphabets");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
				}
				// Postal Code
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getReferring().getAddress().getPostalCode())) {
					resp = GenericComponentValidations
							.isdigit(professionalClaimValidation.getReferring().getAddress().getPostalCode());
					if (resp != true || (professionalClaimValidation.getReferring().getAddress().getPostalCode()
							.length() != 5
							&& professionalClaimValidation.getReferring().getAddress().getPostalCode().length() != 9)) {
						serviceOutcome.setMessage("Referring Postal Code should contain only numbers of 5 or 9 digits");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
			}
			// CONTACT INFORMATION
			if (!Objects.isNull(professionalClaimValidation.getReferring().getContactInformation())) {
				// Phone Number
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getReferring().getContactInformation().getPhoneNumber())) {
					boolean resp3 = GenericComponentValidations.isdigit(
							professionalClaimValidation.getReferring().getContactInformation().getPhoneNumber());
					if (resp3 != true || professionalClaimValidation.getReferring().getContactInformation()
							.getPhoneNumber().length() > 10) {
						serviceOutcome
								.setMessage("Referring Contact Information Phone Number must be 10 Digit Numeric");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// Name
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getReferring().getContactInformation().getName())) {
					if (!GenericComponentValidations
							.isName(professionalClaimValidation.getReferring().getContactInformation().getName())) {
						serviceOutcome.setMessage("Referring Contact Information Name must contain alphabets");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// email
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getReferring().getContactInformation().getEmail())) {
					if (GenericComponentValidations
							.isemail(professionalClaimValidation.getReferring().getContactInformation().getEmail())) {
						serviceOutcome.setMessage("Kindly enter valid Referring Email ID");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
			}

		}
		// RENDERING
		if (!Objects.isNull(professionalClaimValidation.getRendering())) {
			// providerType
			if (!GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getRendering().getProviderType())) {
				serviceOutcome.setMessage("Rendering Provider Type cannot be null");
				serviceOutcome.setData(professionalClaimValidation);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			} else {
				String providerType = professionalClaimValidation.getRendering().getProviderType().trim();
				if (!(providerType.equalsIgnoreCase("BillingProvider")
						|| providerType.equalsIgnoreCase("ReferringProvider")
						|| providerType.equalsIgnoreCase("RenderingProvider")
						|| providerType.equalsIgnoreCase("OrderingProvider")
						|| providerType.equalsIgnoreCase("SupervisingProvider"))) {
					serviceOutcome.setMessage("Provider Type is not Valid");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}

			}
			// ssn
			if (GenericComponentValidations.isStringNullOrBlank(professionalClaimValidation.getRendering().getSsn())) {
				if (GenericComponentValidations.isdigit(professionalClaimValidation.getRendering().getSsn())) {
					if (professionalClaimValidation.getRendering().getSsn().length() != 9) {
						serviceOutcome.setMessage("Rendering SSN should be valid 9 digit numeric");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				} else {
					serviceOutcome.setMessage("Rendering SSN should be numeric");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// employers id
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getRendering().getEmployerId())) {
				if (GenericComponentValidations.isdigit(professionalClaimValidation.getRendering().getEmployerId())) {
					if (professionalClaimValidation.getRendering().getSsn().length() != 9) {
						serviceOutcome.setMessage("Rendering SSN should be valid 9 digit numeric");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				} else {
					serviceOutcome.setMessage("Rendering SSN should be numeric");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			if (!Objects.isNull(professionalClaimValidation.getRendering().getAddress())) {
				// Address1
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getRendering().getAddress().getAddress1())) {
					if (!GenericComponentValidations.isalphanumericspace(
							professionalClaimValidation.getRendering().getAddress().getAddress1())) {
						serviceOutcome.setMessage("Rendering address1 must contain alpha numeric values");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
				} else {
					serviceOutcome.setMessage("Rendering address1 cannot be null");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
				// City
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getRendering().getAddress().getCity())) {
					if (!GenericComponentValidations
							.isalphanumericspace(professionalClaimValidation.getRendering().getAddress().getCity())) {
						serviceOutcome.setMessage("Rendering city must contain only Alphabets and numbers");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
				} else {
					serviceOutcome.setMessage("Rendering city cannot be null");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
				// State
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getRendering().getAddress().getState())) {
					boolean resp2 = GenericComponentValidations
							.isalphabets(professionalClaimValidation.getRendering().getAddress().getState());
					if (!resp2 || professionalClaimValidation.getRendering().getAddress().getState().length() != 2) {
						serviceOutcome.setMessage("Rendering State must contain only 2 Digit Alphabets");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
				}
				// Postal Code
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getRendering().getAddress().getPostalCode())) {
					resp = GenericComponentValidations
							.isdigit(professionalClaimValidation.getRendering().getAddress().getPostalCode());
					if (resp != true || (professionalClaimValidation.getRendering().getAddress().getPostalCode()
							.length() != 5
							&& professionalClaimValidation.getRendering().getAddress().getPostalCode().length() != 9)) {
						serviceOutcome.setMessage("Rendering Postal Code should contain only numbers of 5 or 9 digits");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
			}
			// CONTACT INFORMATION
			if (!Objects.isNull(professionalClaimValidation.getRendering().getContactInformation())) {
				// Phone Number
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getRendering().getContactInformation().getPhoneNumber())) {
					boolean resp3 = GenericComponentValidations.isdigit(
							professionalClaimValidation.getRendering().getContactInformation().getPhoneNumber());
					if (resp3 != true || professionalClaimValidation.getRendering().getContactInformation()
							.getPhoneNumber().length() > 10) {
						serviceOutcome
								.setMessage("Rendering Contact Information Phone Number must be 10 Digit Numeric");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// Name
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getRendering().getContactInformation().getName())) {
					if (!GenericComponentValidations
							.isName(professionalClaimValidation.getRendering().getContactInformation().getName())) {
						serviceOutcome.setMessage("Rendering Contact Information Name must contain alphabets");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// email
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getRendering().getContactInformation().getEmail())) {
					if (GenericComponentValidations
							.isemail(professionalClaimValidation.getRendering().getContactInformation().getEmail())) {
						serviceOutcome.setMessage("Kindly enter valid Rendering Email ID");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
			}

		}
		// ORDERING
		if (!Objects.isNull(professionalClaimValidation.getOrdering())) {
			// providerType
			if (!GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getOrdering().getProviderType())) {
				serviceOutcome.setMessage("Ordering Provider Type cannot be null");
				serviceOutcome.setData(professionalClaimValidation);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			} else {
				String providerType = professionalClaimValidation.getOrdering().getProviderType().trim();
				if (!(providerType.equalsIgnoreCase("BillingProvider")
						|| providerType.equalsIgnoreCase("ReferringProvider")
						|| providerType.equalsIgnoreCase("RenderingProvider")
						|| providerType.equalsIgnoreCase("OrderingProvider")
						|| providerType.equalsIgnoreCase("SupervisingProvider"))) {
					serviceOutcome.setMessage("Provider Type is not Valid");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}

			}
			// ssn
			if (GenericComponentValidations.isStringNullOrBlank(professionalClaimValidation.getOrdering().getSsn())) {
				if (GenericComponentValidations.isdigit(professionalClaimValidation.getOrdering().getSsn())) {
					if (professionalClaimValidation.getOrdering().getSsn().length() != 9) {
						serviceOutcome.setMessage("Ordering SSN should be valid 9 digit numeric");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				} else {
					serviceOutcome.setMessage("Ordering SSN should be numeric");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// employers id
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getOrdering().getEmployerId())) {
				if (GenericComponentValidations.isdigit(professionalClaimValidation.getOrdering().getEmployerId())) {
					if (professionalClaimValidation.getOrdering().getSsn().length() != 9) {
						serviceOutcome.setMessage("Ordering SSN should be valid 9 digit numeric");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				} else {
					serviceOutcome.setMessage("Ordering SSN should be numeric");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			if (!Objects.isNull(professionalClaimValidation.getOrdering().getAddress())) {
				// Address1
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getOrdering().getAddress().getAddress1())) {
					if (!GenericComponentValidations.isalphanumericspace(
							professionalClaimValidation.getOrdering().getAddress().getAddress1())) {
						serviceOutcome.setMessage("Ordering address1 must contain alpha numeric values");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
				} else {
					serviceOutcome.setMessage("Ordering address1 cannot be null");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
				// City
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getOrdering().getAddress().getCity())) {
					if (!GenericComponentValidations
							.isalphanumericspace(professionalClaimValidation.getOrdering().getAddress().getCity())) {
						serviceOutcome.setMessage("Ordering city must contain only Alphabets and numbers");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
				} else {
					serviceOutcome.setMessage("Ordering city cannot be null");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
				// State
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getOrdering().getAddress().getState())) {
					boolean resp2 = GenericComponentValidations
							.isalphabets(professionalClaimValidation.getOrdering().getAddress().getState());
					if (!resp2 || professionalClaimValidation.getOrdering().getAddress().getState().length() != 2) {
						serviceOutcome.setMessage("Ordering State must contain only 2 Digit Alphabets");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
				}
				// Postal Code
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getOrdering().getAddress().getPostalCode())) {
					resp = GenericComponentValidations
							.isdigit(professionalClaimValidation.getOrdering().getAddress().getPostalCode());
					if (resp != true || (professionalClaimValidation.getOrdering().getAddress().getPostalCode()
							.length() != 5
							&& professionalClaimValidation.getOrdering().getAddress().getPostalCode().length() != 9)) {
						serviceOutcome.setMessage("Ordering Postal Code should contain only numbers of 5 or 9 digits");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
			}
			// CONTACT INFORMATION
			if (!Objects.isNull(professionalClaimValidation.getOrdering().getContactInformation())) {
				// Phone Number
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getOrdering().getContactInformation().getPhoneNumber())) {
					boolean resp3 = GenericComponentValidations.isdigit(
							professionalClaimValidation.getOrdering().getContactInformation().getPhoneNumber());
					if (resp3 != true || professionalClaimValidation.getOrdering().getContactInformation()
							.getPhoneNumber().length() > 10) {
						serviceOutcome.setMessage("Ordering Contact Information Phone Number must be 10 Digit Numeric");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// Name
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getOrdering().getContactInformation().getName())) {
					if (!GenericComponentValidations
							.isName(professionalClaimValidation.getOrdering().getContactInformation().getName())) {
						serviceOutcome.setMessage("Ordering Contact Information Name must contain alphabets");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// email
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getOrdering().getContactInformation().getEmail())) {
					if (GenericComponentValidations
							.isemail(professionalClaimValidation.getOrdering().getContactInformation().getEmail())) {
						serviceOutcome.setMessage("Kindly enter valid Ordering Email ID");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
			}

		}
		// SUPERVISING
		if (!Objects.isNull(professionalClaimValidation.getSupervising())) {
			// providerType
			if (!GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getSupervising().getProviderType())) {
				serviceOutcome.setMessage("Supervising Provider Type cannot be null");
				serviceOutcome.setData(professionalClaimValidation);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			} else {
				String providerType = professionalClaimValidation.getSupervising().getProviderType().trim();
				if (!(providerType.equalsIgnoreCase("BillingProvider")
						|| providerType.equalsIgnoreCase("ReferringProvider")
						|| providerType.equalsIgnoreCase("RenderingProvider")
						|| providerType.equalsIgnoreCase("OrderingProvider")
						|| providerType.equalsIgnoreCase("SupervisingProvider"))) {
					serviceOutcome.setMessage("Supervising Provider Type is not Valid");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}

			}
			// ssn
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getSupervising().getSsn())) {
				if (GenericComponentValidations.isdigit(professionalClaimValidation.getSupervising().getSsn())) {
					if (professionalClaimValidation.getSupervising().getSsn().length() != 9) {
						serviceOutcome.setMessage("Supervising SSN should be valid 9 digit numeric");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				} else {
					serviceOutcome.setMessage("Supervising SSN should be numeric");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// employers id
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getSupervising().getEmployerId())) {
				if (GenericComponentValidations.isdigit(professionalClaimValidation.getSupervising().getEmployerId())) {
					if (professionalClaimValidation.getSupervising().getSsn().length() != 9) {
						serviceOutcome.setMessage("Supervising SSN should be valid 9 digit numeric");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				} else {
					serviceOutcome.setMessage("Supervising SSN should be numeric");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			if (!Objects.isNull(professionalClaimValidation.getSupervising().getAddress())) {
				// Address1
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getSupervising().getAddress().getAddress1())) {
					if (!GenericComponentValidations.isalphanumericspace(
							professionalClaimValidation.getSupervising().getAddress().getAddress1())) {
						serviceOutcome.setMessage("Supervising address1 must contain alpha numeric values");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
				} else {
					serviceOutcome.setMessage("Supervising address1 cannot be null");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
				// City
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getSupervising().getAddress().getCity())) {
					if (!GenericComponentValidations
							.isalphanumericspace(professionalClaimValidation.getSupervising().getAddress().getCity())) {
						serviceOutcome.setMessage("Supervising city must contain only Alphabets and numbers");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
				} else {
					serviceOutcome.setMessage("Supervising city cannot be null");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
				// State
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getSupervising().getAddress().getState())) {
					boolean resp2 = GenericComponentValidations
							.isalphabets(professionalClaimValidation.getSupervising().getAddress().getState());
					if (!resp2 || professionalClaimValidation.getSupervising().getAddress().getState().length() != 2) {
						serviceOutcome.setMessage("Supervising State must contain only 2 Digit Alphabets");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
				}
				// Postal Code
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getSupervising().getAddress().getPostalCode())) {
					resp = GenericComponentValidations
							.isdigit(professionalClaimValidation.getSupervising().getAddress().getPostalCode());
					if (resp != true
							|| (professionalClaimValidation.getSupervising().getAddress().getPostalCode().length() != 5
									&& professionalClaimValidation.getSupervising().getAddress().getPostalCode()
											.length() != 9)) {
						serviceOutcome
								.setMessage("Supervising Postal Code should contain only numbers of 5 or 9 digits");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
			}
			// CONTACT INFORMATION
			if (!Objects.isNull(professionalClaimValidation.getSupervising().getContactInformation())) {
				// Phone Number
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getSupervising().getContactInformation().getPhoneNumber())) {
					boolean resp3 = GenericComponentValidations.isdigit(
							professionalClaimValidation.getSupervising().getContactInformation().getPhoneNumber());
					if (resp3 != true || professionalClaimValidation.getSupervising().getContactInformation()
							.getPhoneNumber().length() > 10) {
						serviceOutcome
								.setMessage("Supervising Contact Information Phone Number must be 10 Digit Numeric");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// Name
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getSupervising().getContactInformation().getName())) {
					if (!GenericComponentValidations
							.isName(professionalClaimValidation.getSupervising().getContactInformation().getName())) {
						serviceOutcome.setMessage("Supervising Contact Information Name must contain alphabets");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// email
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getSupervising().getContactInformation().getEmail())) {
					if (GenericComponentValidations
							.isemail(professionalClaimValidation.getSupervising().getContactInformation().getEmail())) {
						serviceOutcome.setMessage("Kindly enter valid Supervising Email ID");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
			}

		}

		// PROVIDER
		if (!Objects.isNull(professionalClaimValidation.getProviders())) {
			for (int i = 0; i < professionalClaimValidation.getProviders().size(); i++) {
				// Provider Type
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getProviders().get(i).getProviderType())) {
					String providerType = professionalClaimValidation.getProviders().get(i).getProviderType().trim();
					if (!(providerType.equalsIgnoreCase("BillingProvider")
							|| providerType.equalsIgnoreCase("ReferringProvider")
							|| providerType.equalsIgnoreCase("RenderingProvider")
							|| providerType.equalsIgnoreCase("OrderingProvider")
							|| providerType.equalsIgnoreCase("SupervisingProvider"))) {
						serviceOutcome.setMessage("Provider Type is not Valid");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}

				// Organization Name
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getProviders().get(i).getOrganizationName())) {
					resp = GenericComponentValidations
							.isName(professionalClaimValidation.getProviders().get(i).getOrganizationName());
					if (!resp) {
						serviceOutcome.setMessage("Provider Organization Name should contain only alphabets");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// NPI
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getProviders().get(i).getNpi())) {

					resp = GenericComponentValidations
							.isdigit(professionalClaimValidation.getProviders().get(i).getNpi());
					if (resp != true || professionalClaimValidation.getProviders().get(i).getNpi().length() != 10) {
						serviceOutcome.setMessage("Length of Provider NPI should be valid 10 digits");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
					if (!isValid)
						return serviceOutcome;
				} else {
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setMessage("Provider NPI cannot be null");
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
				// Employer ID
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getProviders().get(i).getEmployerId())) {

					resp = GenericComponentValidations
							.isdigit(professionalClaimValidation.getProviders().get(i).getEmployerId());
					if (!resp) {
						serviceOutcome.setMessage("Povider Employer ID should contain only numbers");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
					if (!isValid)
						return serviceOutcome;
				} else {
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setMessage("Provider Employer ID cannot be null");
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
				// ADDRESS
				if (!Objects.isNull(professionalClaimValidation.getProviders().get(i).getAddress())) {
					// Address1
					if (GenericComponentValidations.isStringNullOrBlank(
							professionalClaimValidation.getProviders().get(i).getAddress().getAddress1())) {
						if (!GenericComponentValidations.isalphanumericspace(
								professionalClaimValidation.getProviders().get(i).getAddress().getAddress1())) {
							serviceOutcome.setMessage("providers address1 must contain alpha numeric values");
							serviceOutcome.setData(professionalClaimValidation);
							serviceOutcome.setOutcome(false);
							isValid = false;
						}
					} else {
						serviceOutcome.setMessage("providers address1 cannot be null");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
					// City
					if (GenericComponentValidations.isStringNullOrBlank(
							professionalClaimValidation.getProviders().get(i).getAddress().getCity())) {
						if (!GenericComponentValidations.isalphanumericspace(
								professionalClaimValidation.getProviders().get(i).getAddress().getCity())) {
							serviceOutcome.setMessage("providers city must contain only Alphabets and numbers");
							serviceOutcome.setData(professionalClaimValidation);
							serviceOutcome.setOutcome(false);
							isValid = false;
						}
					} else {
						serviceOutcome.setMessage("providers city cannot be null");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
					// State
					if (GenericComponentValidations.isStringNullOrBlank(
							professionalClaimValidation.getProviders().get(i).getAddress().getState())) {
						boolean resp2 = GenericComponentValidations
								.isalphabets(professionalClaimValidation.getProviders().get(i).getAddress().getState());
						if (!resp2 || professionalClaimValidation.getProviders().get(i).getAddress().getState()
								.length() != 2) {
							serviceOutcome.setMessage("providers State must contain only 2 Digit Alphabets");
							serviceOutcome.setData(professionalClaimValidation);
							serviceOutcome.setOutcome(false);
							isValid = false;
						}
					}
					// Postal Code
					if (GenericComponentValidations.isStringNullOrBlank(
							professionalClaimValidation.getProviders().get(i).getAddress().getPostalCode())) {
						resp = GenericComponentValidations.isdigit(
								professionalClaimValidation.getProviders().get(i).getAddress().getPostalCode());
						if (resp != true || (professionalClaimValidation.getProviders().get(i).getAddress()
								.getPostalCode().length() != 5
								&& professionalClaimValidation.getProviders().get(i).getAddress().getPostalCode()
										.length() != 9)) {
							serviceOutcome
									.setMessage("Providers Postal Code should contain only numbers of 5 or 9 digits");
							serviceOutcome.setData(professionalClaimValidation);
							serviceOutcome.setOutcome(false);
							return serviceOutcome;
						}
					}
				}
				// CONTACT INFORMATION
				if (!Objects.isNull(professionalClaimValidation.getProviders().get(i).getContactInformation())) {
					// Phone Number
					if (GenericComponentValidations.isStringNullOrBlank(professionalClaimValidation.getProviders()
							.get(i).getContactInformation().getPhoneNumber())) {
						boolean resp3 = GenericComponentValidations.isdigit(professionalClaimValidation.getProviders()
								.get(i).getContactInformation().getPhoneNumber());
						if (resp3 != true || professionalClaimValidation.getProviders().get(i).getContactInformation()
								.getPhoneNumber().length() > 10) {
							serviceOutcome
									.setMessage("providers Contact Information Phone Number must be 10 Digit Numeric");
							serviceOutcome.setData(professionalClaimValidation);
							serviceOutcome.setOutcome(false);
							return serviceOutcome;
						}
					}
					// Name
					if (GenericComponentValidations.isStringNullOrBlank(
							professionalClaimValidation.getProviders().get(i).getContactInformation().getName())) {
						if (!GenericComponentValidations.isName(
								professionalClaimValidation.getProviders().get(i).getContactInformation().getName())) {
							serviceOutcome.setMessage("providers Contact Information Name must contain alphabets");
							serviceOutcome.setData(professionalClaimValidation);
							serviceOutcome.setOutcome(false);
							return serviceOutcome;
						}
					}
				}

			}
		} else {
			serviceOutcome.setMessage("Provider Object cannot be null");
			serviceOutcome.setData(professionalClaimValidation);
			serviceOutcome.setOutcome(false);
			return serviceOutcome;
		}

		// CLAIM INFORMATION
		if (!Objects.isNull(professionalClaimValidation.getClaimInformation())) {
			// claimFilingCode
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getClaimInformation().getClaimFilingCode())) {
				if (professionalClaimValidation.getClaimInformation().getClaimFilingCode().length() > 19) {
					serviceOutcome.setMessage("claimFilingCode length must not be more than 19 Characters");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// patientControlNumber
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getClaimInformation().getPatientControlNumber())) {
				if (!GenericComponentValidations
						.isdigit(professionalClaimValidation.getClaimInformation().getPatientControlNumber())
						|| professionalClaimValidation.getClaimInformation().getPatientControlNumber().length() != 5) {
					serviceOutcome.setMessage("Kindly enter valid Patient Control Number");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}

			// claimChargeAmount
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getClaimInformation().getClaimChargeAmount())) {
				String[] noOfDigits = GenericComponentValidations
						.noOfDigits(professionalClaimValidation.getClaimInformation().getClaimChargeAmount())
						.split(":");
				if (Integer.parseInt(noOfDigits[1]) > 2) {
					serviceOutcome.setMessage("Claim Charge Amount can have only two numbers after decimal");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}

			// placeOfServiceCode
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getClaimInformation().getPlaceOfServiceCode())) {
				if (!GenericComponentValidations
						.isdigit(professionalClaimValidation.getClaimInformation().getPlaceOfServiceCode())
						|| professionalClaimValidation.getClaimInformation().getPatientControlNumber().length() > 99) {
					serviceOutcome.setMessage("Kindly enter valid Place of Service Codes");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// claimFrequencyCode
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getClaimInformation().getClaimFrequencyCode())) {
				if (!GenericComponentValidations
						.isdigit(professionalClaimValidation.getClaimInformation().getClaimFrequencyCode())) {
					serviceOutcome.setMessage("Claim Frequency Codes can only be Numeric");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// signatureIndicator
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getClaimInformation().getClaimFrequencyCode())) {
				String signatureIndicator = professionalClaimValidation.getClaimInformation().getClaimFrequencyCode();
				if (signatureIndicator.equalsIgnoreCase("Y") || signatureIndicator.equalsIgnoreCase("N")) {
					serviceOutcome.setMessage("Kindly enter valid Signature Indicator");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// planParticipationCode
			if (GenericComponentValidations.isStringNullOrBlank(
					professionalClaimValidation.getClaimInformation().getPlanParticipationCode())) {
				String signatureIndicator = professionalClaimValidation.getClaimInformation()
						.getPlanParticipationCode();
				if (!(signatureIndicator.equalsIgnoreCase("A") || signatureIndicator.equalsIgnoreCase("B"))) {
					serviceOutcome.setMessage("Kindly enter valid Plan Participation Code");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// benefitsAssignmentCertificationIndicator
			if (GenericComponentValidations.isStringNullOrBlank(
					professionalClaimValidation.getClaimInformation().getBenefitsAssignmentCertificationIndicator())) {
				String signatureIndicator = professionalClaimValidation.getClaimInformation()
						.getBenefitsAssignmentCertificationIndicator();
				if (!(signatureIndicator.equalsIgnoreCase("Y") || signatureIndicator.equalsIgnoreCase("N"))) {
					serviceOutcome.setMessage("Kindly enter valid Benefits Assignment Certification Indicator");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// releaseInformationCode
			if (GenericComponentValidations.isStringNullOrBlank(
					professionalClaimValidation.getClaimInformation().getReleaseInformationCode())) {
				String signatureIndicator = professionalClaimValidation.getClaimInformation()
						.getReleaseInformationCode();
				if (!(signatureIndicator.equalsIgnoreCase("Y") || signatureIndicator.equalsIgnoreCase("N"))) {
					serviceOutcome.setMessage("Kindly enter valid Release Information Code");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}

			// CLAIM SUPPLEMENTATAL INFORMATION
			if (!Objects.isNull(professionalClaimValidation.getClaimInformation().getClaimSupplementalInformation())) {
				// repricedClaimNumber
				if (GenericComponentValidations.isStringNullOrBlank(professionalClaimValidation.getClaimInformation()
						.getClaimSupplementalInformation().getRepricedClaimNumber())) {
					boolean resp3 = GenericComponentValidations.isdigit(professionalClaimValidation
							.getClaimInformation().getClaimSupplementalInformation().getRepricedClaimNumber());
					if (resp3 != true || professionalClaimValidation.getClaimInformation()
							.getClaimSupplementalInformation().getRepricedClaimNumber().length() != 5) {
						serviceOutcome.setMessage("Repriced claim Number must be 5 Digit Numeric");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// claimNumber
				if (GenericComponentValidations.isStringNullOrBlank(professionalClaimValidation.getClaimInformation()
						.getClaimSupplementalInformation().getClaimNumber())) {
					boolean resp3 = GenericComponentValidations.isdigit(professionalClaimValidation
							.getClaimInformation().getClaimSupplementalInformation().getClaimNumber());
					if (resp3 != true || professionalClaimValidation.getClaimInformation()
							.getClaimSupplementalInformation().getClaimNumber().length() != 5) {
						serviceOutcome.setMessage("Claim Number must be 5 Digit Numeric");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
			}

			// healthCareCodeInformation
			if (!Objects.isNull(professionalClaimValidation.getClaimInformation().getHealthCareCodeInformation())) {
				int n = professionalClaimValidation.getClaimInformation().getHealthCareCodeInformation().size();
				String code = null;
				for (int i = 0; i < n; i++) {
					if (GenericComponentValidations.isStringNullOrBlank(professionalClaimValidation
							.getClaimInformation().getHealthCareCodeInformation().get(i).getDiagnosisCode())) {
						code = professionalClaimValidation.getClaimInformation().getHealthCareCodeInformation().get(i)
								.getDiagnosisTypeCode();
						if (!(code.equalsIgnoreCase("BK") || code.equalsIgnoreCase("ABK") || code.equalsIgnoreCase("BF")
								|| code.equalsIgnoreCase("ABF"))) {
							serviceOutcome
									.setMessage("Kindly enter proper Diagnosis Type Code for Health Care Information");
							serviceOutcome.setData(professionalClaimValidation);
							serviceOutcome.setOutcome(false);
							return serviceOutcome;
						}
					}
					if (GenericComponentValidations.isStringNullOrBlank(professionalClaimValidation
							.getClaimInformation().getHealthCareCodeInformation().get(i).getDiagnosisTypeCode())) {
						if (!GenericComponentValidations.isalphabets(code)) {
							serviceOutcome.setMessage("Kindly enter proper Diagnosis Code Health Care Information");
							serviceOutcome.setData(professionalClaimValidation);
							serviceOutcome.setOutcome(false);
							return serviceOutcome;
						}
					}
				}
			}
			// serviceFacilityLocation
			if (!Objects.isNull(professionalClaimValidation.getClaimInformation().getServiceFacilityLocation())) {
				// organizationName
				if (GenericComponentValidations.isStringNullOrBlank(professionalClaimValidation.getClaimInformation()
						.getServiceFacilityLocation().getOrganizationName())) {
					if (!GenericComponentValidations.isName(professionalClaimValidation.getClaimInformation()
							.getServiceFacilityLocation().getOrganizationName())) {
						serviceOutcome.setMessage("Service FacilityLocation Organization Name should be valid");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// Address
				if (!Objects.isNull(
						professionalClaimValidation.getClaimInformation().getServiceFacilityLocation().getAddress())) {

					// address1
					if (GenericComponentValidations.isStringNullOrBlank(professionalClaimValidation
							.getClaimInformation().getServiceFacilityLocation().getAddress().getAddress1())) {
						resp = GenericComponentValidations.isalphanumericspace(professionalClaimValidation
								.getClaimInformation().getServiceFacilityLocation().getAddress().getAddress1());
						if (resp != true) {
							serviceOutcome.setMessage(
									"serviceFacilityLocation Address 1 should contain only alphanumeic values");
							serviceOutcome.setData(professionalClaimValidation);
							serviceOutcome.setOutcome(false);
							return serviceOutcome;
						}
					}
					// city
					if (GenericComponentValidations.isStringNullOrBlank(professionalClaimValidation
							.getClaimInformation().getServiceFacilityLocation().getAddress().getCity())) {
						resp = GenericComponentValidations.isalphanumericspace(professionalClaimValidation
								.getClaimInformation().getServiceFacilityLocation().getAddress().getCity());
						if (resp != true) {
							serviceOutcome.setMessage(
									"Service FacilityLocation City should contain only alphabets and Numbers");
							serviceOutcome.setData(professionalClaimValidation);
							serviceOutcome.setOutcome(false);
							return serviceOutcome;
						}
					}
					// state
					if (GenericComponentValidations.isStringNullOrBlank(professionalClaimValidation
							.getClaimInformation().getServiceFacilityLocation().getAddress().getState())) {
						resp = GenericComponentValidations.isName(professionalClaimValidation.getClaimInformation()
								.getServiceFacilityLocation().getAddress().getState());
						if (resp != true) {
							serviceOutcome.setMessage("serviceFacilityLocation State should contain only alphabets");
							serviceOutcome.setData(professionalClaimValidation);
							serviceOutcome.setOutcome(false);
							return serviceOutcome;
						}
					}
					// Postal Code
					if (GenericComponentValidations.isStringNullOrBlank(professionalClaimValidation
							.getClaimInformation().getServiceFacilityLocation().getAddress().getPostalCode())) {
						resp = GenericComponentValidations.isdigit(professionalClaimValidation.getClaimInformation()
								.getServiceFacilityLocation().getAddress().getPostalCode());
						if (resp != true || (professionalClaimValidation.getSubscriber().getAddress().getPostalCode()
								.length() != 5
								&& professionalClaimValidation.getSubscriber().getAddress().getPostalCode()
										.length() != 9)) {
							serviceOutcome.setMessage(
									"serviceFacilityLocation Postal Code should contain only numbers of 5 or 9 digits");
							serviceOutcome.setData(professionalClaimValidation);
							serviceOutcome.setOutcome(false);
							return serviceOutcome;
						}
					}

				}

			}
			if (!Objects.isNull(professionalClaimValidation.getClaimInformation().getServiceLines())) {
				for (int i = 0; i < professionalClaimValidation.getClaimInformation().getServiceLines().size(); i++) {
					if (!Objects.isNull(professionalClaimValidation.getClaimInformation().getServiceLines().get(i)
							.getProfessionalService())) {

						// serviceDate
						if (!GenericComponentValidations.isStringNullOrBlank(professionalClaimValidation
								.getClaimInformation().getServiceLines().get(i).getServiceDate())) {
							serviceOutcome.setMessage("Service Date Of Birth cannot be null");
							serviceOutcome.setOutcome(false);
							serviceOutcome.setData(professionalClaimValidation);
							return serviceOutcome;
						} else {
							resp = GenericComponentValidations.isValidDate(professionalClaimValidation
									.getClaimInformation().getServiceLines().get(i).getServiceDate());
							if (!resp) {
								serviceOutcome.setMessage("Service Date Of Birth is not valid");
								serviceOutcome.setOutcome(false);
								serviceOutcome.setData(professionalClaimValidation);
								return serviceOutcome;
							}
						}

						// procedureIdentifier
						if (GenericComponentValidations
								.isStringNullOrBlank(professionalClaimValidation.getClaimInformation().getServiceLines()
										.get(i).getProfessionalService().getProcedureIdentifier())) {
							String procedureIdentifier = professionalClaimValidation.getClaimInformation()
									.getServiceLines().get(i).getProfessionalService().getProcedureIdentifier();
							if (!(procedureIdentifier.equalsIgnoreCase("ER")
									|| procedureIdentifier.equalsIgnoreCase("HC")
									|| procedureIdentifier.equalsIgnoreCase("IV")
									|| procedureIdentifier.equalsIgnoreCase("WK"))) {
								serviceOutcome.setMessage("Kindly Provide proper values for procedureIdentifier");
								serviceOutcome.setData(professionalClaimValidation);
								serviceOutcome.setOutcome(false);
								return serviceOutcome;
							}
						}
						// procedureCode
						if (GenericComponentValidations
								.isStringNullOrBlank(professionalClaimValidation.getClaimInformation().getServiceLines()
										.get(i).getProfessionalService().getProcedureCode())) {
							if (!GenericComponentValidations
									.isalphanumeric(professionalClaimValidation.getClaimInformation().getServiceLines()
											.get(i).getProfessionalService().getProcedureCode())) {
								serviceOutcome.setMessage("Kindly Provide proper values for procedureCode");
								serviceOutcome.setData(professionalClaimValidation);
								serviceOutcome.setOutcome(false);
								return serviceOutcome;
							}

						}
						// measurementUnit
						if (GenericComponentValidations
								.isStringNullOrBlank(professionalClaimValidation.getClaimInformation().getServiceLines()
										.get(i).getProfessionalService().getMeasurementUnit())) {
							if (!GenericComponentValidations
									.isalphanumeric(professionalClaimValidation.getClaimInformation().getServiceLines()
											.get(i).getProfessionalService().getProcedureCode())) {
								serviceOutcome.setMessage("Kindly Provide proper values for procedureCode");
								serviceOutcome.setData(professionalClaimValidation);
								serviceOutcome.setOutcome(false);
								return serviceOutcome;
							}

						}
						// serviceUnitCount
						if (GenericComponentValidations
								.isStringNullOrBlank(professionalClaimValidation.getClaimInformation().getServiceLines()
										.get(i).getProfessionalService().getServiceUnitCount())) {
							if (!GenericComponentValidations.isdigit(professionalClaimValidation.getClaimInformation()
									.getServiceLines().get(i).getProfessionalService().getServiceUnitCount())) {
								serviceOutcome.setMessage("Kindly Provide proper values for serviceUnitCount");
								serviceOutcome.setData(professionalClaimValidation);
								serviceOutcome.setOutcome(false);
								return serviceOutcome;
							}
						}
					}
				}
			}

		}

		return serviceOutcome;
	}

	public static ServiceOutcome<ProfessionalClaimSubmission> validateSubmissionUserInputs(
			ProfessionalClaimSubmission professionalClaimValidation) {
		ServiceOutcome<ProfessionalClaimSubmission> serviceOutcome = new ServiceOutcome<ProfessionalClaimSubmission>();
		serviceOutcome.setOutcome(true);
		boolean isValid = true;
		boolean resp = false;
		String value = null;

		// Control Number Validation
		if (GenericComponentValidations.isStringNullOrBlank(professionalClaimValidation.getControlNumber())) {

			resp = GenericComponentValidations.isdigit(professionalClaimValidation.getControlNumber());
			if (resp != true || professionalClaimValidation.getControlNumber().length() != 9) {
				serviceOutcome.setMessage("Length of Control number should be valid 9 digits");

				serviceOutcome.setData(professionalClaimValidation);
				serviceOutcome.setOutcome(false);
				isValid = false;
			}
			if (!isValid)
				return serviceOutcome;
		} else {
			serviceOutcome.setData(professionalClaimValidation);
			serviceOutcome.setMessage("Controll Number cannot be null");
			serviceOutcome.setOutcome(false);
			return serviceOutcome;
		}

		// Trading Partner Service Id Validation
		if (GenericComponentValidations
				.isStringNullOrBlank(professionalClaimValidation.getSubscriber().getMemberId())) {
			resp = GenericComponentValidations
					.isalphanumeric(professionalClaimValidation.getSubscriber().getMemberId());
			if (!resp) {
				serviceOutcome.setMessage("Trading Partner Service Id should be alphanumeric");
				serviceOutcome.setOutcome(false);
				serviceOutcome.setData(professionalClaimValidation);
				isValid = false;
			}
			if (!isValid)
				return serviceOutcome;
		} else {
			serviceOutcome.setData(professionalClaimValidation);
			serviceOutcome.setMessage("Trading Partner Service Id cannot be null");
			serviceOutcome.setOutcome(false);
			return serviceOutcome;
		}

		// SUBMITTER
		// Organization Name
		if (!Objects.isNull(professionalClaimValidation.getSubmitter())) {
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getSubmitter().getOrganizationName())) {
				resp = GenericComponentValidations
						.isName(professionalClaimValidation.getSubmitter().getOrganizationName());
				if (!resp) {
					serviceOutcome.setMessage("Submitter Organization Name should contain only alphabets");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// First Name
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getSubmitter().getFirstName())) {
				resp = GenericComponentValidations.isName(professionalClaimValidation.getSubmitter().getFirstName());
				if (!resp || GenericComponentValidations
						.islength35(professionalClaimValidation.getSubmitter().getFirstName())) {
					serviceOutcome.setMessage("Submitter First Name should contain at max 35 alphabets");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// Last Name
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getSubmitter().getLastName())) {
				resp = GenericComponentValidations.isName(professionalClaimValidation.getSubmitter().getLastName());
				if (!resp || GenericComponentValidations
						.islength35(professionalClaimValidation.getSubmitter().getLastName())) {
					serviceOutcome.setMessage("Submitter Last Name should contain at max 35 alphabets");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// Middle Name
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getSubmitter().getMiddleName())) {
				resp = GenericComponentValidations.isName(professionalClaimValidation.getSubmitter().getMiddleName());
				if (!resp || GenericComponentValidations
						.islength35(professionalClaimValidation.getSubmitter().getMiddleName())) {
					serviceOutcome.setMessage("Submitter Middle Name should contain at max 35 alphabets");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// CONTACT INFORMATION
			if (!Objects.isNull(professionalClaimValidation.getSubmitter().getContactInformation())) {
				// Name
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getSubmitter().getContactInformation().getName())) {
					resp = GenericComponentValidations
							.isName(professionalClaimValidation.getSubmitter().getContactInformation().getName());
					if (!resp) {
						serviceOutcome.setMessage("Submitter Name should contain only alphabets");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// Phone Number
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getSubmitter().getContactInformation().getPhoneNumber())) {
					if (professionalClaimValidation.getSubmitter().getContactInformation().getPhoneNumber()
							.length() > 10) {
						serviceOutcome.setMessage("Phone Number should not contain more than 10 digits");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					} else {
						resp = GenericComponentValidations.isdigit(
								professionalClaimValidation.getSubmitter().getContactInformation().getPhoneNumber());
						if (!resp) {
							serviceOutcome.setMessage("Phone Number should only be numeric values");
							serviceOutcome.setData(professionalClaimValidation);
							serviceOutcome.setOutcome(false);
							return serviceOutcome;
						}
					}
				}
				// email
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getSubmitter().getContactInformation().getEmail())) {
					if (GenericComponentValidations
							.isemail(professionalClaimValidation.getSubmitter().getContactInformation().getEmail())) {
						serviceOutcome.setMessage("Kindly enter valid Submitter Email ID");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}

			} else {
				serviceOutcome.setMessage("Submitter Contact Information cannot be null");
				serviceOutcome.setData(professionalClaimValidation);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			}
		} else {
			serviceOutcome.setMessage("Submitter Information cannot be null");
			serviceOutcome.setData(professionalClaimValidation);
			serviceOutcome.setOutcome(false);
			return serviceOutcome;
		}

		// RECIEVER
		// Organization Name
		if (!Objects.isNull(professionalClaimValidation.getReceiver())) {
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getReceiver().getOrganizationName())) {
				resp = GenericComponentValidations
						.isName(professionalClaimValidation.getReceiver().getOrganizationName());
				if (!resp) {
					serviceOutcome.setMessage("Reciever Organization Name should contain only alphabets");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			} else {
				serviceOutcome.setMessage("Reciever Organization Name cannot be null");
				serviceOutcome.setData(professionalClaimValidation);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			}
		} else {
			serviceOutcome.setMessage("Reciever Object cannot be null");
			serviceOutcome.setData(professionalClaimValidation);
			serviceOutcome.setOutcome(false);
			return serviceOutcome;
		}

		// SUBSCRIBER
		if (!Objects.isNull(professionalClaimValidation.getSubscriber())) {
			// Member ID
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getSubscriber().getMemberId())) {
				resp = GenericComponentValidations.isdigit(professionalClaimValidation.getSubscriber().getMemberId());
				if (resp != true || professionalClaimValidation.getSubscriber().getMemberId().length() != 10) {
					serviceOutcome.setMessage("Subscriber Member ID should contain only 10 digits");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// paymentResponsibilityLevelCode
			if (GenericComponentValidations.isStringNullOrBlank(
					professionalClaimValidation.getSubscriber().getPaymentResponsibilityLevelCode())) {
				resp = GenericComponentValidations
						.isalphabets(professionalClaimValidation.getSubscriber().getPaymentResponsibilityLevelCode());
				value = professionalClaimValidation.getSubscriber().getPaymentResponsibilityLevelCode();
				if (resp != true || !value.matches("A|B|C|D|E|F|G|H|P|S|T|U")) {
					serviceOutcome.setMessage("Kindly enter valid Payment Responsibility Level Code");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			} else {
				serviceOutcome.setMessage("Payment Responsibility Level Code cannot be null");
				serviceOutcome.setData(professionalClaimValidation);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			}
			// insuranceTypeCode
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getSubscriber().getInsuranceTypeCode())) {
				value = professionalClaimValidation.getSubscriber().getInsuranceTypeCode();
				if (!value.matches("12|13|14|15|16|41|42|43|47")) {
					serviceOutcome.setMessage("Kindly enter valid Insurance Type Code");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// subscriberGroupName
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getSubscriber().getSubscriberGroupName())) {
				if (!GenericComponentValidations
						.isName(professionalClaimValidation.getSubscriber().getSubscriberGroupName())) {
					serviceOutcome.setMessage("Kindly enter valid Subscriber Group Name");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// firstName
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getSubscriber().getFirstName())) {
				resp = GenericComponentValidations
						.isalphabets(professionalClaimValidation.getSubscriber().getFirstName());
				if (resp != true || GenericComponentValidations
						.islength35(professionalClaimValidation.getSubscriber().getFirstName())) {
					serviceOutcome.setMessage("Subscriber First Name should contain only alphabets");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// lastName
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getSubscriber().getLastName())) {
				resp = GenericComponentValidations
						.isalphabets(professionalClaimValidation.getSubscriber().getLastName());
				if (resp != true || GenericComponentValidations
						.islength35(professionalClaimValidation.getSubscriber().getFirstName())) {
					serviceOutcome.setMessage("Subscriber Last Name should contain only alphabets");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// middle Name
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getSubscriber().getMiddleName())) {
				resp = GenericComponentValidations
						.isalphabets(professionalClaimValidation.getSubscriber().getMiddleName());
				if (resp != true || GenericComponentValidations
						.islength35(professionalClaimValidation.getSubscriber().getMiddleName())) {
					serviceOutcome.setMessage("Subscriber Last Name should contain only alphabets");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// Gender
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getSubscriber().getGender())) {

				resp = GenericComponentValidations.isalphabets(professionalClaimValidation.getSubscriber().getGender());
				if (!professionalClaimValidation.getSubscriber().getGender().equalsIgnoreCase("M")
						&& !professionalClaimValidation.getSubscriber().getGender().equalsIgnoreCase("F")
						&& !professionalClaimValidation.getSubscriber().getGender().equalsIgnoreCase("U")) {
					serviceOutcome.setMessage("Kindly specify proper Gender");
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
				serviceOutcome.setData(professionalClaimValidation);
				if (!isValid)
					return serviceOutcome;
			}

			// Date Of Birth
			if (!GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getSubscriber().getDateOfBirth())) {
				serviceOutcome.setMessage("Subscriber Date Of Birth cannot be null");
				serviceOutcome.setOutcome(false);
				serviceOutcome.setData(professionalClaimValidation);
				return serviceOutcome;
			} else {
				resp = GenericComponentValidations
						.isValidDate(professionalClaimValidation.getSubscriber().getDateOfBirth());
				if (!resp) {
					serviceOutcome.setMessage("Subscriber Date Of Birth is not valid");
					serviceOutcome.setOutcome(false);
					serviceOutcome.setData(professionalClaimValidation);
					return serviceOutcome;
				}
			}
			// policyNumber
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getSubscriber().getPolicyNumber())) {
				resp = GenericComponentValidations
						.isdigit(professionalClaimValidation.getSubscriber().getPolicyNumber());
				if (resp != true || professionalClaimValidation.getSubscriber().getPolicyNumber().length() != 5) {
					serviceOutcome.setMessage("Policy Number should contain only 5 digits");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// CONTACT INFORMATION
			if (!Objects.isNull(professionalClaimValidation.getSubscriber().getContactInformation())) {
				// name
				if (!GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getSubscriber().getContactInformation().getName())) {
					serviceOutcome.setMessage("Subscriber Contact Information Name cannot be blank");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
				// Phone Number
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getSubscriber().getContactInformation().getPhoneNumber())) {
					if (professionalClaimValidation.getSubscriber().getContactInformation().getPhoneNumber()
							.length() > 10) {
						serviceOutcome.setMessage("Subscriber Phone Number should not contain more than 10 digits");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					} else {
						resp = GenericComponentValidations.isdigit(
								professionalClaimValidation.getSubscriber().getContactInformation().getPhoneNumber());
						if (!resp) {
							serviceOutcome.setMessage("Subscriber Phone Number should only be numeric values");
							serviceOutcome.setData(professionalClaimValidation);
							serviceOutcome.setOutcome(false);
							return serviceOutcome;
						}
					}
				}
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getSubscriber().getContactInformation().getEmail())) {
					if (GenericComponentValidations
							.isemail(professionalClaimValidation.getSubscriber().getContactInformation().getEmail())) {
						serviceOutcome.setMessage("Subscriber email should be proper");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
			}
			// ADDRESS
			if (!Objects.isNull(professionalClaimValidation.getSubscriber().getAddress())) {
				// address1
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getSubscriber().getAddress().getAddress1())) {
					resp = GenericComponentValidations.isalphanumericspace(
							professionalClaimValidation.getSubscriber().getAddress().getAddress1());
					if (resp != true) {
						serviceOutcome.setMessage("Subscriber Address 1 should contain only alphanumeic values");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// city
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getSubscriber().getAddress().getCity())) {
					resp = GenericComponentValidations
							.isalphanumericspace(professionalClaimValidation.getSubscriber().getAddress().getCity());
					if (resp != true) {
						serviceOutcome.setMessage("Subscriber City should contain only alphabets and numbers");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// state
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getSubscriber().getAddress().getState())) {
					resp = GenericComponentValidations
							.isalphabets(professionalClaimValidation.getSubscriber().getAddress().getState());
					if (resp != true
							|| professionalClaimValidation.getSubscriber().getAddress().getState().length() != 2) {
						serviceOutcome.setMessage("Subscriber State should contain only 2digit alphabets");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// Postal Code
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getSubscriber().getAddress().getPostalCode())) {
					resp = GenericComponentValidations
							.isdigit(professionalClaimValidation.getSubscriber().getAddress().getPostalCode());
					if (resp != true
							|| (professionalClaimValidation.getSubscriber().getAddress().getPostalCode().length() != 5
									&& professionalClaimValidation.getSubscriber().getAddress().getPostalCode()
											.length() != 9)) {
						serviceOutcome
								.setMessage("Subscriber Postal Code should contain only numbers of 5 or 9 digits");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
			}

		} else {
			serviceOutcome.setMessage("Subscriber Object cannot be null");
			serviceOutcome.setData(professionalClaimValidation);
			serviceOutcome.setOutcome(false);
			return serviceOutcome;
		}

		// DEDPENDANT
		if (!Objects.isNull(professionalClaimValidation.getDependents())) {
			// firstName
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getDependents().getFirstName())) {
				resp = GenericComponentValidations.isName(professionalClaimValidation.getDependents().getFirstName());
				if (resp != true) {
					serviceOutcome.setMessage("Dependant First Name should contain only alphabets");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			} else {
				serviceOutcome.setMessage("Dependant First Name cannot be null");
				serviceOutcome.setData(professionalClaimValidation);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			}
			// lastName
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getDependents().getLastName())) {
				resp = GenericComponentValidations.isName(professionalClaimValidation.getDependents().getLastName());
				if (resp != true) {
					serviceOutcome.setMessage("Dependant Last Name should contain only alphabets");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			} else {
				serviceOutcome.setMessage("Dependant Last Name cannot be null");
				serviceOutcome.setData(professionalClaimValidation);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			}
			// Gender
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getDependents().getGender())) {
				String gender = professionalClaimValidation.getDependents().getGender();
				resp = GenericComponentValidations.isalphabets(gender);
				if (!gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("F") && !gender.equalsIgnoreCase("U")) {
					serviceOutcome.setMessage("Kindly specify proper Dependant Gender");
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
				serviceOutcome.setData(professionalClaimValidation);
				if (!isValid)
					return serviceOutcome;
			}
			// Date Of Birth
			if (!GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getSubscriber().getDateOfBirth())) {
				serviceOutcome.setMessage("Dependant Date Of Birth cannot be null");
				serviceOutcome.setOutcome(false);
				serviceOutcome.setData(professionalClaimValidation);
				return serviceOutcome;
			} else {
				resp = GenericComponentValidations
						.isValidDate(professionalClaimValidation.getSubscriber().getDateOfBirth());
				if (!resp) {
					serviceOutcome.setMessage("Dependant Date Of Birth is not valid");
					serviceOutcome.setOutcome(false);
					serviceOutcome.setData(professionalClaimValidation);
					return serviceOutcome;
				}
			}
			// Member ID
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getDependents().getMemberId())) {
				resp = GenericComponentValidations.isdigit(professionalClaimValidation.getDependents().getMemberId());
				if (resp != true || professionalClaimValidation.getDependents().getMemberId().length() != 10) {
					serviceOutcome.setMessage("Dependant Member ID should contain only 10 digits");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// relationshipToSubscriberCode
			if (GenericComponentValidations.isStringNullOrBlank(
					professionalClaimValidation.getDependents().getRelationshipToSubscriberCode())) {
				resp = GenericComponentValidations
						.isdigit(professionalClaimValidation.getDependents().getRelationshipToSubscriberCode());
				if (resp != true) {
					serviceOutcome.setMessage("Dependant Relationship to Subscriber Code should be proper");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			} else {
				serviceOutcome.setMessage("Dependant Relationship to Subscriber Code cannot be null");
				serviceOutcome.setData(professionalClaimValidation);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			}
			// paymentResponsibilityLevelCode
			if (GenericComponentValidations.isStringNullOrBlank(
					professionalClaimValidation.getDependents().getPaymentResponsibilityLevelCode())) {
				resp = GenericComponentValidations
						.isalphabets(professionalClaimValidation.getDependents().getPaymentResponsibilityLevelCode());
				if (resp != true || professionalClaimValidation.getDependents().getPaymentResponsibilityLevelCode()
						.length() != 1) {
					serviceOutcome
							.setMessage("Dependant Payment Responsibility Level Code should contain only 1 digits");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// policyNumber
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getDependents().getPolicyNumber())) {
				resp = GenericComponentValidations
						.isdigit(professionalClaimValidation.getDependents().getPolicyNumber());
				if (resp != true || professionalClaimValidation.getDependents().getPolicyNumber().length() != 5) {
					serviceOutcome.setMessage("Dependant Policy Number should contain only 5 digits");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// CONTACT INFORMATION
			if (!Objects.isNull(professionalClaimValidation.getDependents().getContactInformation())) {
				// name
				if (!GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getDependents().getContactInformation().getName())) {
					serviceOutcome.setMessage("Subscriber Contact Information Name cannot be blank");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
				// Phone Number
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getDependents().getContactInformation().getPhoneNumber())) {
					if (professionalClaimValidation.getDependents().getContactInformation().getPhoneNumber()
							.length() > 10) {
						serviceOutcome.setMessage("Subscriber Phone Number should not contain more than 10 digits");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					} else {
						resp = GenericComponentValidations.isdigit(
								professionalClaimValidation.getDependents().getContactInformation().getPhoneNumber());
						if (!resp) {
							serviceOutcome.setMessage("Subscriber Phone Number should only be numeric values");
							serviceOutcome.setData(professionalClaimValidation);
							serviceOutcome.setOutcome(false);
							return serviceOutcome;
						}
					}
				}
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getDependents().getContactInformation().getEmail())) {
					if (GenericComponentValidations
							.isemail(professionalClaimValidation.getSubscriber().getContactInformation().getEmail())) {
						serviceOutcome.setMessage("Subscriber email should be proper");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
			}
			// ADDRESS
			if (!Objects.isNull(professionalClaimValidation.getDependents().getAddress())) {
				// address1
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getDependents().getAddress().getAddress1())) {
					resp = GenericComponentValidations.isalphanumericspace(
							professionalClaimValidation.getDependents().getAddress().getAddress1());
					if (resp != true) {
						serviceOutcome.setMessage("Dependant Address 1 should contain only alphanumeic values");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// city
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getDependents().getAddress().getCity())) {
					resp = GenericComponentValidations
							.isalphanumericspace(professionalClaimValidation.getDependents().getAddress().getCity());
					if (resp != true) {
						serviceOutcome.setMessage("Dependant City should contain only alphabets and numbers");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// state
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getDependents().getAddress().getState())) {
					resp = GenericComponentValidations
							.isalphabets(professionalClaimValidation.getDependents().getAddress().getState());
					if (resp != true
							|| professionalClaimValidation.getDependents().getAddress().getState().length() != 2) {
						serviceOutcome.setMessage("Dependant State should contain only alphabets of 2 digits");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// Postal Code
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getDependents().getAddress().getPostalCode())) {
					resp = GenericComponentValidations
							.isdigit(professionalClaimValidation.getDependents().getAddress().getPostalCode());
					if (resp != true
							|| (professionalClaimValidation.getDependents().getAddress().getPostalCode().length() != 5
									&& professionalClaimValidation.getDependents().getAddress().getPostalCode()
											.length() != 9)) {
						serviceOutcome.setMessage("Dependant Postal Code should contain only numbers of 5 or 9 digits");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
			}

		}
		// PAY TO ADDRESS
		if (!Objects.isNull(professionalClaimValidation.getPayToAddress())) {
			// Address1
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getPayToAddress().getAddress1())) {
				if (!GenericComponentValidations
						.isalphanumericspace(professionalClaimValidation.getPayToAddress().getAddress1())) {
					serviceOutcome.setMessage("providers address1 must contain alpha numeric values");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
			}
			// City
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getPayToAddress().getCity())) {
				if (!GenericComponentValidations
						.isalphanumericspace(professionalClaimValidation.getPayToAddress().getCity())) {
					serviceOutcome.setMessage("providers city must contain only Alphabets and numbers");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
			}
			// State
			if (!GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getPayToAddress().getState())) {
				boolean resp2 = GenericComponentValidations
						.isalphabets(professionalClaimValidation.getPayToAddress().getState());
				if (!resp2 || professionalClaimValidation.getPayToAddress().getState().length() != 2) {
					serviceOutcome.setMessage("providers State must contain only 2 Digit Alphabets");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
			}
			// Postal Code
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getPayToAddress().getPostalCode())) {
				resp = GenericComponentValidations
						.isdigit(professionalClaimValidation.getPayToAddress().getPostalCode());
				if (resp != true || (professionalClaimValidation.getPayToAddress().getPostalCode().length() != 5
						&& professionalClaimValidation.getPayToAddress().getPostalCode().length() != 9)) {
					serviceOutcome.setMessage("Providers Postal Code should contain only numbers of 5 or 9 digits");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
		}
		// PAY TO PLAN
		if (!Objects.isNull(professionalClaimValidation.getPayToPlan())) {
			// organizationName
			if (!GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getPayToPlan().getOrganizationName())) {
				serviceOutcome.setMessage("Pay to Plan Organization Name cannot be null");
				serviceOutcome.setData(professionalClaimValidation);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			}
			// Primary Identifier Code
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getPayToPlan().getPrimaryIdentifierTypeCode())) {
				value = professionalClaimValidation.getPayToPlan().getPrimaryIdentifierTypeCode();
				if (!value.matches("PI|XV")) {
					serviceOutcome.setMessage("Pay to Plan Primary Identification Code is not proper");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// Secondary Identifier Code
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getPayToPlan().getSecondaryIdentifierTypeCode())) {
				value = professionalClaimValidation.getPayToPlan().getSecondaryIdentifierTypeCode();
				if (!value.matches("2U|FY|NF")) {
					serviceOutcome.setMessage("Pay to Plan Secondary Identifier Code is not proper");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// Primary Identifier
			if (!GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getPayToPlan().getPrimaryIdentifier())) {
				serviceOutcome.setMessage("Pay to Plan Primary Identifier cannot be null");
				serviceOutcome.setData(professionalClaimValidation);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			}
			// Tax Identification Number
			if (!GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getPayToPlan().getTaxIdentificationNumber())) {
				serviceOutcome.setMessage("Pay to Plan Tax Identification Number cannot be null");
				serviceOutcome.setData(professionalClaimValidation);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			}
			// ADDRESS
			if (!Objects.isNull(professionalClaimValidation.getPayToPlan().getAddress())) {
				// Address1
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getPayToPlan().getAddress().getAddress1())) {
					if (!GenericComponentValidations.isalphanumericspace(
							professionalClaimValidation.getPayToPlan().getAddress().getAddress1())) {
						serviceOutcome.setMessage("providers address1 must contain alpha numeric values");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
				} else {
					serviceOutcome.setMessage("providers address1 cannot be null");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
				// City
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getPayToPlan().getAddress().getCity())) {
					if (!GenericComponentValidations
							.isalphanumericspace(professionalClaimValidation.getPayToPlan().getAddress().getCity())) {
						serviceOutcome.setMessage("providers city must contain only Alphabets and numbers");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
				} else {
					serviceOutcome.setMessage("providers city cannot be null");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
				// State
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getPayToPlan().getAddress().getState())) {
					boolean resp2 = GenericComponentValidations
							.isalphabets(professionalClaimValidation.getPayToPlan().getAddress().getState());
					if (!resp2 || professionalClaimValidation.getPayToPlan().getAddress().getState().length() != 2) {
						serviceOutcome.setMessage("providers State must contain only 2 Digit Alphabets");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
				}
				// Postal Code
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getPayToPlan().getAddress().getPostalCode())) {
					resp = GenericComponentValidations
							.isdigit(professionalClaimValidation.getPayToPlan().getAddress().getPostalCode());
					if (resp != true || (professionalClaimValidation.getPayToPlan().getAddress().getPostalCode()
							.length() != 5
							&& professionalClaimValidation.getPayToPlan().getAddress().getPostalCode().length() != 9)) {
						serviceOutcome.setMessage("Providers Postal Code should contain only numbers of 5 or 9 digits");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
			}
		}
		// PAYERS ADDRESS
		if (!Objects.isNull(professionalClaimValidation.getPayerAddress())) {
			// Address1
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getPayerAddress().getAddress1())) {
				if (!GenericComponentValidations
						.isalphanumericspace(professionalClaimValidation.getPayerAddress().getAddress1())) {
					serviceOutcome.setMessage("providers address1 must contain alpha numeric values");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
			} else {
				serviceOutcome.setMessage("providers address1 cannot be null");
				serviceOutcome.setData(professionalClaimValidation);
				serviceOutcome.setOutcome(false);
				isValid = false;
			}
			// City
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getPayerAddress().getCity())) {
				if (!GenericComponentValidations
						.isalphanumericspace(professionalClaimValidation.getPayerAddress().getCity())) {
					serviceOutcome.setMessage("providers city must contain only Alphabets and numbers");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
			} else {
				serviceOutcome.setMessage("providers city cannot be null");
				serviceOutcome.setData(professionalClaimValidation);
				serviceOutcome.setOutcome(false);
				isValid = false;
			}
			// State
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getPayerAddress().getState())) {
				boolean resp2 = GenericComponentValidations
						.isalphabets(professionalClaimValidation.getPayerAddress().getState());
				if (!resp2 || professionalClaimValidation.getPayerAddress().getState().length() != 2) {
					serviceOutcome.setMessage("providers State must contain only 2 Digit Alphabets");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
			}
			// Postal Code
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getPayerAddress().getPostalCode())) {
				resp = GenericComponentValidations
						.isdigit(professionalClaimValidation.getPayerAddress().getPostalCode());
				if (resp != true || (professionalClaimValidation.getPayerAddress().getPostalCode().length() != 5
						&& professionalClaimValidation.getPayerAddress().getPostalCode().length() != 9)) {
					serviceOutcome.setMessage("Providers Postal Code should contain only numbers of 5 or 9 digits");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
		}

		// BILLING
		if (!Objects.isNull(professionalClaimValidation.getBilling())) {
			// providerType
			if (!GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getBilling().getProviderType())) {
				serviceOutcome.setMessage("Billing Provider Type cannot be null");
				serviceOutcome.setData(professionalClaimValidation);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			} else {
				String providerType = professionalClaimValidation.getBilling().getProviderType().trim();
				if (!(providerType.equalsIgnoreCase("BillingProvider")
						|| providerType.equalsIgnoreCase("ReferringProvider")
						|| providerType.equalsIgnoreCase("RenderingProvider")
						|| providerType.equalsIgnoreCase("OrderingProvider")
						|| providerType.equalsIgnoreCase("SupervisingProvider"))) {
					serviceOutcome.setMessage("Provider Type is not Valid");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}

			}
			// ssn
			if (GenericComponentValidations.isStringNullOrBlank(professionalClaimValidation.getBilling().getSsn())) {
				if (GenericComponentValidations.isdigit(professionalClaimValidation.getBilling().getSsn())) {
					if (professionalClaimValidation.getBilling().getSsn().length() != 9) {
						serviceOutcome.setMessage("Billing SSN should be valid 9 digit numeric");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				} else {
					serviceOutcome.setMessage("Billing SSN should be numeric");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// employers id
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getBilling().getEmployerId())) {
				if (GenericComponentValidations.isdigit(professionalClaimValidation.getBilling().getEmployerId())) {
					if (professionalClaimValidation.getBilling().getSsn().length() != 9) {
						serviceOutcome.setMessage("Billing SSN should be valid 9 digit numeric");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				} else {
					serviceOutcome.setMessage("Billing SSN should be numeric");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			if (!Objects.isNull(professionalClaimValidation.getBilling().getAddress())) {
				// Address1
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getBilling().getAddress().getAddress1())) {
					if (!GenericComponentValidations
							.isalphanumericspace(professionalClaimValidation.getBilling().getAddress().getAddress1())) {
						serviceOutcome.setMessage("Billing address1 must contain alpha numeric values");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
				} else {
					serviceOutcome.setMessage("Billing address1 cannot be null");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
				// City
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getBilling().getAddress().getCity())) {
					if (!GenericComponentValidations
							.isalphanumericspace(professionalClaimValidation.getBilling().getAddress().getCity())) {
						serviceOutcome.setMessage("Billing city must contain only Alphabets and numbers");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
				} else {
					serviceOutcome.setMessage("Billing city cannot be null");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
				// State
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getBilling().getAddress().getState())) {
					boolean resp2 = GenericComponentValidations
							.isalphabets(professionalClaimValidation.getBilling().getAddress().getState());
					if (!resp2 || professionalClaimValidation.getBilling().getAddress().getState().length() != 2) {
						serviceOutcome.setMessage("Billing State must contain only 2 Digit Alphabets");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
				}
				// Postal Code
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getBilling().getAddress().getPostalCode())) {
					resp = GenericComponentValidations
							.isdigit(professionalClaimValidation.getBilling().getAddress().getPostalCode());
					if (resp != true || (professionalClaimValidation.getBilling().getAddress().getPostalCode()
							.length() != 5
							&& professionalClaimValidation.getBilling().getAddress().getPostalCode().length() != 9)) {
						serviceOutcome.setMessage("Billing Postal Code should contain only numbers of 5 or 9 digits");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
			}
			// CONTACT INFORMATION
			if (!Objects.isNull(professionalClaimValidation.getBilling().getContactInformation())) {
				// Phone Number
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getBilling().getContactInformation().getPhoneNumber())) {
					boolean resp3 = GenericComponentValidations
							.isdigit(professionalClaimValidation.getBilling().getContactInformation().getPhoneNumber());
					if (resp3 != true || professionalClaimValidation.getBilling().getContactInformation()
							.getPhoneNumber().length() > 10) {
						serviceOutcome.setMessage("Billing Contact Information Phone Number must be 10 Digit Numeric");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// Name
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getBilling().getContactInformation().getName())) {
					if (!GenericComponentValidations
							.isName(professionalClaimValidation.getBilling().getContactInformation().getName())) {
						serviceOutcome.setMessage("Billing Contact Information Name must contain alphabets");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// email
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getBilling().getContactInformation().getEmail())) {
					if (GenericComponentValidations
							.isemail(professionalClaimValidation.getBilling().getContactInformation().getEmail())) {
						serviceOutcome.setMessage("Kindly enter valid Billing Email ID");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
			}

		}

		// REFFERING
		if (!Objects.isNull(professionalClaimValidation.getReferring())) {
			// providerType
			if (!GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getReferring().getProviderType())) {
				serviceOutcome.setMessage("Referring Provider Type cannot be null");
				serviceOutcome.setData(professionalClaimValidation);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			} else {
				String providerType = professionalClaimValidation.getReferring().getProviderType().trim();
				if (!(providerType.equalsIgnoreCase("BillingProvider")
						|| providerType.equalsIgnoreCase("ReferringProvider")
						|| providerType.equalsIgnoreCase("RenderingProvider")
						|| providerType.equalsIgnoreCase("OrderingProvider")
						|| providerType.equalsIgnoreCase("SupervisingProvider"))) {
					serviceOutcome.setMessage("Provider Type is not Valid");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}

			}
			// ssn
			if (GenericComponentValidations.isStringNullOrBlank(professionalClaimValidation.getReferring().getSsn())) {
				if (GenericComponentValidations.isdigit(professionalClaimValidation.getReferring().getSsn())) {
					if (professionalClaimValidation.getReferring().getSsn().length() != 9) {
						serviceOutcome.setMessage("Referring SSN should be valid 9 digit numeric");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				} else {
					serviceOutcome.setMessage("Referring SSN should be numeric");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// employers id
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getReferring().getEmployerId())) {
				if (GenericComponentValidations.isdigit(professionalClaimValidation.getReferring().getEmployerId())) {
					if (professionalClaimValidation.getReferring().getSsn().length() != 9) {
						serviceOutcome.setMessage("Referring SSN should be valid 9 digit numeric");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				} else {
					serviceOutcome.setMessage("Referring SSN should be numeric");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			if (!Objects.isNull(professionalClaimValidation.getReferring().getAddress())) {
				// Address1
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getReferring().getAddress().getAddress1())) {
					if (!GenericComponentValidations.isalphanumericspace(
							professionalClaimValidation.getReferring().getAddress().getAddress1())) {
						serviceOutcome.setMessage("Referring address1 must contain alpha numeric values");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
				} else {
					serviceOutcome.setMessage("Referring address1 cannot be null");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
				// City
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getReferring().getAddress().getCity())) {
					if (!GenericComponentValidations
							.isalphanumericspace(professionalClaimValidation.getReferring().getAddress().getCity())) {
						serviceOutcome.setMessage("Referring city must contain only Alphabets and numbers");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
				} else {
					serviceOutcome.setMessage("Referring city cannot be null");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
				// State
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getReferring().getAddress().getState())) {
					boolean resp2 = GenericComponentValidations
							.isalphabets(professionalClaimValidation.getReferring().getAddress().getState());
					if (!resp2 || professionalClaimValidation.getReferring().getAddress().getState().length() != 2) {
						serviceOutcome.setMessage("Referring State must contain only 2 Digit Alphabets");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
				}
				// Postal Code
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getReferring().getAddress().getPostalCode())) {
					resp = GenericComponentValidations
							.isdigit(professionalClaimValidation.getReferring().getAddress().getPostalCode());
					if (resp != true || (professionalClaimValidation.getReferring().getAddress().getPostalCode()
							.length() != 5
							&& professionalClaimValidation.getReferring().getAddress().getPostalCode().length() != 9)) {
						serviceOutcome.setMessage("Referring Postal Code should contain only numbers of 5 or 9 digits");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
			}
			// CONTACT INFORMATION
			if (!Objects.isNull(professionalClaimValidation.getReferring().getContactInformation())) {
				// Phone Number
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getReferring().getContactInformation().getPhoneNumber())) {
					boolean resp3 = GenericComponentValidations.isdigit(
							professionalClaimValidation.getReferring().getContactInformation().getPhoneNumber());
					if (resp3 != true || professionalClaimValidation.getReferring().getContactInformation()
							.getPhoneNumber().length() > 10) {
						serviceOutcome
								.setMessage("Referring Contact Information Phone Number must be 10 Digit Numeric");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// Name
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getReferring().getContactInformation().getName())) {
					if (!GenericComponentValidations
							.isName(professionalClaimValidation.getReferring().getContactInformation().getName())) {
						serviceOutcome.setMessage("Referring Contact Information Name must contain alphabets");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// email
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getReferring().getContactInformation().getEmail())) {
					if (GenericComponentValidations
							.isemail(professionalClaimValidation.getReferring().getContactInformation().getEmail())) {
						serviceOutcome.setMessage("Kindly enter valid Referring Email ID");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
			}

		}
		// RENDERING
		if (!Objects.isNull(professionalClaimValidation.getRendering())) {
			// providerType
			if (!GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getRendering().getProviderType())) {
				serviceOutcome.setMessage("Rendering Provider Type cannot be null");
				serviceOutcome.setData(professionalClaimValidation);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			} else {
				String providerType = professionalClaimValidation.getRendering().getProviderType().trim();
				if (!(providerType.equalsIgnoreCase("BillingProvider")
						|| providerType.equalsIgnoreCase("ReferringProvider")
						|| providerType.equalsIgnoreCase("RenderingProvider")
						|| providerType.equalsIgnoreCase("OrderingProvider")
						|| providerType.equalsIgnoreCase("SupervisingProvider"))) {
					serviceOutcome.setMessage("Provider Type is not Valid");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}

			}
			// ssn
			if (GenericComponentValidations.isStringNullOrBlank(professionalClaimValidation.getRendering().getSsn())) {
				if (GenericComponentValidations.isdigit(professionalClaimValidation.getRendering().getSsn())) {
					if (professionalClaimValidation.getRendering().getSsn().length() != 9) {
						serviceOutcome.setMessage("Rendering SSN should be valid 9 digit numeric");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				} else {
					serviceOutcome.setMessage("Rendering SSN should be numeric");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// employers id
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getRendering().getEmployerId())) {
				if (GenericComponentValidations.isdigit(professionalClaimValidation.getRendering().getEmployerId())) {
					if (professionalClaimValidation.getRendering().getSsn().length() != 9) {
						serviceOutcome.setMessage("Rendering SSN should be valid 9 digit numeric");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				} else {
					serviceOutcome.setMessage("Rendering SSN should be numeric");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			if (!Objects.isNull(professionalClaimValidation.getRendering().getAddress())) {
				// Address1
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getRendering().getAddress().getAddress1())) {
					if (!GenericComponentValidations.isalphanumericspace(
							professionalClaimValidation.getRendering().getAddress().getAddress1())) {
						serviceOutcome.setMessage("Rendering address1 must contain alpha numeric values");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
				} else {
					serviceOutcome.setMessage("Rendering address1 cannot be null");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
				// City
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getRendering().getAddress().getCity())) {
					if (!GenericComponentValidations
							.isalphanumericspace(professionalClaimValidation.getRendering().getAddress().getCity())) {
						serviceOutcome.setMessage("Rendering city must contain only Alphabets and numbers");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
				} else {
					serviceOutcome.setMessage("Rendering city cannot be null");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
				// State
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getRendering().getAddress().getState())) {
					boolean resp2 = GenericComponentValidations
							.isalphabets(professionalClaimValidation.getRendering().getAddress().getState());
					if (!resp2 || professionalClaimValidation.getRendering().getAddress().getState().length() != 2) {
						serviceOutcome.setMessage("Rendering State must contain only 2 Digit Alphabets");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
				}
				// Postal Code
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getRendering().getAddress().getPostalCode())) {
					resp = GenericComponentValidations
							.isdigit(professionalClaimValidation.getRendering().getAddress().getPostalCode());
					if (resp != true || (professionalClaimValidation.getRendering().getAddress().getPostalCode()
							.length() != 5
							&& professionalClaimValidation.getRendering().getAddress().getPostalCode().length() != 9)) {
						serviceOutcome.setMessage("Rendering Postal Code should contain only numbers of 5 or 9 digits");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
			}
			// CONTACT INFORMATION
			if (!Objects.isNull(professionalClaimValidation.getRendering().getContactInformation())) {
				// Phone Number
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getRendering().getContactInformation().getPhoneNumber())) {
					boolean resp3 = GenericComponentValidations.isdigit(
							professionalClaimValidation.getRendering().getContactInformation().getPhoneNumber());
					if (resp3 != true || professionalClaimValidation.getRendering().getContactInformation()
							.getPhoneNumber().length() > 10) {
						serviceOutcome
								.setMessage("Rendering Contact Information Phone Number must be 10 Digit Numeric");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// Name
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getRendering().getContactInformation().getName())) {
					if (!GenericComponentValidations
							.isName(professionalClaimValidation.getRendering().getContactInformation().getName())) {
						serviceOutcome.setMessage("Rendering Contact Information Name must contain alphabets");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// email
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getRendering().getContactInformation().getEmail())) {
					if (GenericComponentValidations
							.isemail(professionalClaimValidation.getRendering().getContactInformation().getEmail())) {
						serviceOutcome.setMessage("Kindly enter valid Rendering Email ID");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
			}

		}
		// ORDERING
		if (!Objects.isNull(professionalClaimValidation.getOrdering())) {
			// providerType
			if (!GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getOrdering().getProviderType())) {
				serviceOutcome.setMessage("Ordering Provider Type cannot be null");
				serviceOutcome.setData(professionalClaimValidation);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			} else {
				String providerType = professionalClaimValidation.getOrdering().getProviderType().trim();
				if (!(providerType.equalsIgnoreCase("BillingProvider")
						|| providerType.equalsIgnoreCase("ReferringProvider")
						|| providerType.equalsIgnoreCase("RenderingProvider")
						|| providerType.equalsIgnoreCase("OrderingProvider")
						|| providerType.equalsIgnoreCase("SupervisingProvider"))) {
					serviceOutcome.setMessage("Provider Type is not Valid");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}

			}
			// ssn
			if (GenericComponentValidations.isStringNullOrBlank(professionalClaimValidation.getOrdering().getSsn())) {
				if (GenericComponentValidations.isdigit(professionalClaimValidation.getOrdering().getSsn())) {
					if (professionalClaimValidation.getOrdering().getSsn().length() != 9) {
						serviceOutcome.setMessage("Ordering SSN should be valid 9 digit numeric");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				} else {
					serviceOutcome.setMessage("Ordering SSN should be numeric");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// employers id
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getOrdering().getEmployerId())) {
				if (GenericComponentValidations.isdigit(professionalClaimValidation.getOrdering().getEmployerId())) {
					if (professionalClaimValidation.getOrdering().getSsn().length() != 9) {
						serviceOutcome.setMessage("Ordering SSN should be valid 9 digit numeric");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				} else {
					serviceOutcome.setMessage("Ordering SSN should be numeric");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			if (!Objects.isNull(professionalClaimValidation.getOrdering().getAddress())) {
				// Address1
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getOrdering().getAddress().getAddress1())) {
					if (!GenericComponentValidations.isalphanumericspace(
							professionalClaimValidation.getOrdering().getAddress().getAddress1())) {
						serviceOutcome.setMessage("Ordering address1 must contain alpha numeric values");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
				} else {
					serviceOutcome.setMessage("Ordering address1 cannot be null");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
				// City
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getOrdering().getAddress().getCity())) {
					if (!GenericComponentValidations
							.isalphanumericspace(professionalClaimValidation.getOrdering().getAddress().getCity())) {
						serviceOutcome.setMessage("Ordering city must contain only Alphabets and numbers");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
				} else {
					serviceOutcome.setMessage("Ordering city cannot be null");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
				// State
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getOrdering().getAddress().getState())) {
					boolean resp2 = GenericComponentValidations
							.isalphabets(professionalClaimValidation.getOrdering().getAddress().getState());
					if (!resp2 || professionalClaimValidation.getOrdering().getAddress().getState().length() != 2) {
						serviceOutcome.setMessage("Ordering State must contain only 2 Digit Alphabets");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
				}
				// Postal Code
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getOrdering().getAddress().getPostalCode())) {
					resp = GenericComponentValidations
							.isdigit(professionalClaimValidation.getOrdering().getAddress().getPostalCode());
					if (resp != true || (professionalClaimValidation.getOrdering().getAddress().getPostalCode()
							.length() != 5
							&& professionalClaimValidation.getOrdering().getAddress().getPostalCode().length() != 9)) {
						serviceOutcome.setMessage("Ordering Postal Code should contain only numbers of 5 or 9 digits");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
			}
			// CONTACT INFORMATION
			if (!Objects.isNull(professionalClaimValidation.getOrdering().getContactInformation())) {
				// Phone Number
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getOrdering().getContactInformation().getPhoneNumber())) {
					boolean resp3 = GenericComponentValidations.isdigit(
							professionalClaimValidation.getOrdering().getContactInformation().getPhoneNumber());
					if (resp3 != true || professionalClaimValidation.getOrdering().getContactInformation()
							.getPhoneNumber().length() > 10) {
						serviceOutcome.setMessage("Ordering Contact Information Phone Number must be 10 Digit Numeric");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// Name
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getOrdering().getContactInformation().getName())) {
					if (!GenericComponentValidations
							.isName(professionalClaimValidation.getOrdering().getContactInformation().getName())) {
						serviceOutcome.setMessage("Ordering Contact Information Name must contain alphabets");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// email
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getOrdering().getContactInformation().getEmail())) {
					if (GenericComponentValidations
							.isemail(professionalClaimValidation.getOrdering().getContactInformation().getEmail())) {
						serviceOutcome.setMessage("Kindly enter valid Ordering Email ID");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
			}

		}
		// SUPERVISING
		if (!Objects.isNull(professionalClaimValidation.getSupervising())) {
			// providerType
			if (!GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getSupervising().getProviderType())) {
				serviceOutcome.setMessage("Supervising Provider Type cannot be null");
				serviceOutcome.setData(professionalClaimValidation);
				serviceOutcome.setOutcome(false);
				return serviceOutcome;
			} else {
				String providerType = professionalClaimValidation.getSupervising().getProviderType().trim();
				if (!(providerType.equalsIgnoreCase("BillingProvider")
						|| providerType.equalsIgnoreCase("ReferringProvider")
						|| providerType.equalsIgnoreCase("RenderingProvider")
						|| providerType.equalsIgnoreCase("OrderingProvider")
						|| providerType.equalsIgnoreCase("SupervisingProvider"))) {
					serviceOutcome.setMessage("Supervising Provider Type is not Valid");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}

			}
			// ssn
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getSupervising().getSsn())) {
				if (GenericComponentValidations.isdigit(professionalClaimValidation.getSupervising().getSsn())) {
					if (professionalClaimValidation.getSupervising().getSsn().length() != 9) {
						serviceOutcome.setMessage("Supervising SSN should be valid 9 digit numeric");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				} else {
					serviceOutcome.setMessage("Supervising SSN should be numeric");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// employers id
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getSupervising().getEmployerId())) {
				if (GenericComponentValidations.isdigit(professionalClaimValidation.getSupervising().getEmployerId())) {
					if (professionalClaimValidation.getSupervising().getSsn().length() != 9) {
						serviceOutcome.setMessage("Supervising SSN should be valid 9 digit numeric");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				} else {
					serviceOutcome.setMessage("Supervising SSN should be numeric");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			if (!Objects.isNull(professionalClaimValidation.getSupervising().getAddress())) {
				// Address1
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getSupervising().getAddress().getAddress1())) {
					if (!GenericComponentValidations.isalphanumericspace(
							professionalClaimValidation.getSupervising().getAddress().getAddress1())) {
						serviceOutcome.setMessage("Supervising address1 must contain alpha numeric values");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
				} else {
					serviceOutcome.setMessage("Supervising address1 cannot be null");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
				// City
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getSupervising().getAddress().getCity())) {
					if (!GenericComponentValidations
							.isalphanumericspace(professionalClaimValidation.getSupervising().getAddress().getCity())) {
						serviceOutcome.setMessage("Supervising city must contain only Alphabets and numbers");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
				} else {
					serviceOutcome.setMessage("Supervising city cannot be null");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					isValid = false;
				}
				// State
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getSupervising().getAddress().getState())) {
					boolean resp2 = GenericComponentValidations
							.isalphabets(professionalClaimValidation.getSupervising().getAddress().getState());
					if (!resp2 || professionalClaimValidation.getSupervising().getAddress().getState().length() != 2) {
						serviceOutcome.setMessage("Supervising State must contain only 2 Digit Alphabets");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
				}
				// Postal Code
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getSupervising().getAddress().getPostalCode())) {
					resp = GenericComponentValidations
							.isdigit(professionalClaimValidation.getSupervising().getAddress().getPostalCode());
					if (resp != true
							|| (professionalClaimValidation.getSupervising().getAddress().getPostalCode().length() != 5
									&& professionalClaimValidation.getSupervising().getAddress().getPostalCode()
											.length() != 9)) {
						serviceOutcome
								.setMessage("Supervising Postal Code should contain only numbers of 5 or 9 digits");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
			}
			// CONTACT INFORMATION
			if (!Objects.isNull(professionalClaimValidation.getSupervising().getContactInformation())) {
				// Phone Number
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getSupervising().getContactInformation().getPhoneNumber())) {
					boolean resp3 = GenericComponentValidations.isdigit(
							professionalClaimValidation.getSupervising().getContactInformation().getPhoneNumber());
					if (resp3 != true || professionalClaimValidation.getSupervising().getContactInformation()
							.getPhoneNumber().length() > 10) {
						serviceOutcome
								.setMessage("Supervising Contact Information Phone Number must be 10 Digit Numeric");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// Name
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getSupervising().getContactInformation().getName())) {
					if (!GenericComponentValidations
							.isName(professionalClaimValidation.getSupervising().getContactInformation().getName())) {
						serviceOutcome.setMessage("Supervising Contact Information Name must contain alphabets");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// email
				if (GenericComponentValidations.isStringNullOrBlank(
						professionalClaimValidation.getSupervising().getContactInformation().getEmail())) {
					if (GenericComponentValidations
							.isemail(professionalClaimValidation.getSupervising().getContactInformation().getEmail())) {
						serviceOutcome.setMessage("Kindly enter valid Supervising Email ID");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
			}

		}

		// PROVIDER
		if (!Objects.isNull(professionalClaimValidation.getProviders())) {
			for (int i = 0; i < professionalClaimValidation.getProviders().size(); i++) {
				// Provider Type
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getProviders().get(i).getProviderType())) {
					String providerType = professionalClaimValidation.getProviders().get(i).getProviderType().trim();
					if (!(providerType.equalsIgnoreCase("BillingProvider")
							|| providerType.equalsIgnoreCase("ReferringProvider")
							|| providerType.equalsIgnoreCase("RenderingProvider")
							|| providerType.equalsIgnoreCase("OrderingProvider")
							|| providerType.equalsIgnoreCase("SupervisingProvider"))) {
						serviceOutcome.setMessage("Provider Type is not Valid");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}

				// Organization Name
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getProviders().get(i).getOrganizationName())) {
					resp = GenericComponentValidations
							.isName(professionalClaimValidation.getProviders().get(i).getOrganizationName());
					if (!resp) {
						serviceOutcome.setMessage("Provider Organization Name should contain only alphabets");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// NPI
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getProviders().get(i).getNpi())) {

					resp = GenericComponentValidations
							.isdigit(professionalClaimValidation.getProviders().get(i).getNpi());
					if (resp != true || professionalClaimValidation.getProviders().get(i).getNpi().length() != 10) {
						serviceOutcome.setMessage("Length of Provider NPI should be valid 10 digits");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
					if (!isValid)
						return serviceOutcome;
				} else {
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setMessage("Provider NPI cannot be null");
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
				// Employer ID
				if (GenericComponentValidations
						.isStringNullOrBlank(professionalClaimValidation.getProviders().get(i).getEmployerId())) {

					resp = GenericComponentValidations
							.isdigit(professionalClaimValidation.getProviders().get(i).getEmployerId());
					if (!resp) {
						serviceOutcome.setMessage("Povider Employer ID should contain only numbers");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
					if (!isValid)
						return serviceOutcome;
				} else {
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setMessage("Provider Employer ID cannot be null");
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
				// ADDRESS
				if (!Objects.isNull(professionalClaimValidation.getProviders().get(i).getAddress())) {
					// Address1
					if (GenericComponentValidations.isStringNullOrBlank(
							professionalClaimValidation.getProviders().get(i).getAddress().getAddress1())) {
						if (!GenericComponentValidations.isalphanumericspace(
								professionalClaimValidation.getProviders().get(i).getAddress().getAddress1())) {
							serviceOutcome.setMessage("providers address1 must contain alpha numeric values");
							serviceOutcome.setData(professionalClaimValidation);
							serviceOutcome.setOutcome(false);
							isValid = false;
						}
					} else {
						serviceOutcome.setMessage("providers address1 cannot be null");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
					// City
					if (GenericComponentValidations.isStringNullOrBlank(
							professionalClaimValidation.getProviders().get(i).getAddress().getCity())) {
						if (!GenericComponentValidations.isalphanumericspace(
								professionalClaimValidation.getProviders().get(i).getAddress().getCity())) {
							serviceOutcome.setMessage("providers city must contain only Alphabets and numbers");
							serviceOutcome.setData(professionalClaimValidation);
							serviceOutcome.setOutcome(false);
							isValid = false;
						}
					} else {
						serviceOutcome.setMessage("providers city cannot be null");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						isValid = false;
					}
					// State
					if (GenericComponentValidations.isStringNullOrBlank(
							professionalClaimValidation.getProviders().get(i).getAddress().getState())) {
						boolean resp2 = GenericComponentValidations
								.isalphabets(professionalClaimValidation.getProviders().get(i).getAddress().getState());
						if (!resp2 || professionalClaimValidation.getProviders().get(i).getAddress().getState()
								.length() != 2) {
							serviceOutcome.setMessage("providers State must contain only 2 Digit Alphabets");
							serviceOutcome.setData(professionalClaimValidation);
							serviceOutcome.setOutcome(false);
							isValid = false;
						}
					}
					// Postal Code
					if (GenericComponentValidations.isStringNullOrBlank(
							professionalClaimValidation.getProviders().get(i).getAddress().getPostalCode())) {
						resp = GenericComponentValidations.isdigit(
								professionalClaimValidation.getProviders().get(i).getAddress().getPostalCode());
						if (resp != true || (professionalClaimValidation.getProviders().get(i).getAddress()
								.getPostalCode().length() != 5
								&& professionalClaimValidation.getProviders().get(i).getAddress().getPostalCode()
										.length() != 9)) {
							serviceOutcome
									.setMessage("Providers Postal Code should contain only numbers of 5 or 9 digits");
							serviceOutcome.setData(professionalClaimValidation);
							serviceOutcome.setOutcome(false);
							return serviceOutcome;
						}
					}
				}
				// CONTACT INFORMATION
				if (!Objects.isNull(professionalClaimValidation.getProviders().get(i).getContactInformation())) {
					// Phone Number
					if (GenericComponentValidations.isStringNullOrBlank(professionalClaimValidation.getProviders()
							.get(i).getContactInformation().getPhoneNumber())) {
						boolean resp3 = GenericComponentValidations.isdigit(professionalClaimValidation.getProviders()
								.get(i).getContactInformation().getPhoneNumber());
						if (resp3 != true || professionalClaimValidation.getProviders().get(i).getContactInformation()
								.getPhoneNumber().length() > 10) {
							serviceOutcome
									.setMessage("providers Contact Information Phone Number must be 10 Digit Numeric");
							serviceOutcome.setData(professionalClaimValidation);
							serviceOutcome.setOutcome(false);
							return serviceOutcome;
						}
					}
					// Name
					if (GenericComponentValidations.isStringNullOrBlank(
							professionalClaimValidation.getProviders().get(i).getContactInformation().getName())) {
						if (!GenericComponentValidations.isName(
								professionalClaimValidation.getProviders().get(i).getContactInformation().getName())) {
							serviceOutcome.setMessage("providers Contact Information Name must contain alphabets");
							serviceOutcome.setData(professionalClaimValidation);
							serviceOutcome.setOutcome(false);
							return serviceOutcome;
						}
					}
				}

			}
		} else {
			serviceOutcome.setMessage("Provider Object cannot be null");
			serviceOutcome.setData(professionalClaimValidation);
			serviceOutcome.setOutcome(false);
			return serviceOutcome;
		}

		// CLAIM INFORMATION
		if (!Objects.isNull(professionalClaimValidation.getClaimInformation())) {
			// claimFilingCode
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getClaimInformation().getClaimFilingCode())) {
				if (professionalClaimValidation.getClaimInformation().getClaimFilingCode().length() > 19) {
					serviceOutcome.setMessage("claimFilingCode length must not be more than 19 Characters");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// patientControlNumber
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getClaimInformation().getPatientControlNumber())) {
				if (!GenericComponentValidations
						.isdigit(professionalClaimValidation.getClaimInformation().getPatientControlNumber())
						|| professionalClaimValidation.getClaimInformation().getPatientControlNumber().length() != 5) {
					serviceOutcome.setMessage("Kindly enter valid Patient Control Number");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}

			// claimChargeAmount
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getClaimInformation().getClaimChargeAmount())) {
				String[] noOfDigits = GenericComponentValidations
						.noOfDigits(professionalClaimValidation.getClaimInformation().getClaimChargeAmount())
						.split(":");
				if (Integer.parseInt(noOfDigits[1]) > 2) {
					serviceOutcome.setMessage("Claim Charge Amount can have only two numbers after decimal");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}

			// placeOfServiceCode
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getClaimInformation().getPlaceOfServiceCode())) {
				if (!GenericComponentValidations
						.isdigit(professionalClaimValidation.getClaimInformation().getPlaceOfServiceCode())
						|| professionalClaimValidation.getClaimInformation().getPatientControlNumber().length() > 99) {
					serviceOutcome.setMessage("Kindly enter valid Place of Service Codes");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// claimFrequencyCode
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getClaimInformation().getClaimFrequencyCode())) {
				if (!GenericComponentValidations
						.isdigit(professionalClaimValidation.getClaimInformation().getClaimFrequencyCode())) {
					serviceOutcome.setMessage("Claim Frequency Codes can only be Numeric");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// signatureIndicator
			if (GenericComponentValidations
					.isStringNullOrBlank(professionalClaimValidation.getClaimInformation().getClaimFrequencyCode())) {
				String signatureIndicator = professionalClaimValidation.getClaimInformation().getClaimFrequencyCode();
				if (signatureIndicator.equalsIgnoreCase("Y") || signatureIndicator.equalsIgnoreCase("N")) {
					serviceOutcome.setMessage("Kindly enter valid Signature Indicator");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// planParticipationCode
			if (GenericComponentValidations.isStringNullOrBlank(
					professionalClaimValidation.getClaimInformation().getPlanParticipationCode())) {
				String signatureIndicator = professionalClaimValidation.getClaimInformation()
						.getPlanParticipationCode();
				if (!(signatureIndicator.equalsIgnoreCase("A") || signatureIndicator.equalsIgnoreCase("B"))) {
					serviceOutcome.setMessage("Kindly enter valid Plan Participation Code");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// benefitsAssignmentCertificationIndicator
			if (GenericComponentValidations.isStringNullOrBlank(
					professionalClaimValidation.getClaimInformation().getBenefitsAssignmentCertificationIndicator())) {
				String signatureIndicator = professionalClaimValidation.getClaimInformation()
						.getBenefitsAssignmentCertificationIndicator();
				if (!(signatureIndicator.equalsIgnoreCase("Y") || signatureIndicator.equalsIgnoreCase("N"))) {
					serviceOutcome.setMessage("Kindly enter valid Benefits Assignment Certification Indicator");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}
			// releaseInformationCode
			if (GenericComponentValidations.isStringNullOrBlank(
					professionalClaimValidation.getClaimInformation().getReleaseInformationCode())) {
				String signatureIndicator = professionalClaimValidation.getClaimInformation()
						.getReleaseInformationCode();
				if (!(signatureIndicator.equalsIgnoreCase("Y") || signatureIndicator.equalsIgnoreCase("N"))) {
					serviceOutcome.setMessage("Kindly enter valid Release Information Code");
					serviceOutcome.setData(professionalClaimValidation);
					serviceOutcome.setOutcome(false);
					return serviceOutcome;
				}
			}

			// CLAIM SUPPLEMENTATAL INFORMATION
			if (!Objects.isNull(professionalClaimValidation.getClaimInformation().getClaimSupplementalInformation())) {
				// repricedClaimNumber
				if (GenericComponentValidations.isStringNullOrBlank(professionalClaimValidation.getClaimInformation()
						.getClaimSupplementalInformation().getRepricedClaimNumber())) {
					boolean resp3 = GenericComponentValidations.isdigit(professionalClaimValidation
							.getClaimInformation().getClaimSupplementalInformation().getRepricedClaimNumber());
					if (resp3 != true || professionalClaimValidation.getClaimInformation()
							.getClaimSupplementalInformation().getRepricedClaimNumber().length() != 5) {
						serviceOutcome.setMessage("Repriced claim Number must be 5 Digit Numeric");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// claimNumber
				if (GenericComponentValidations.isStringNullOrBlank(professionalClaimValidation.getClaimInformation()
						.getClaimSupplementalInformation().getClaimNumber())) {
					boolean resp3 = GenericComponentValidations.isdigit(professionalClaimValidation
							.getClaimInformation().getClaimSupplementalInformation().getClaimNumber());
					if (resp3 != true || professionalClaimValidation.getClaimInformation()
							.getClaimSupplementalInformation().getClaimNumber().length() != 5) {
						serviceOutcome.setMessage("Claim Number must be 5 Digit Numeric");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
			}

			// healthCareCodeInformation
			if (!Objects.isNull(professionalClaimValidation.getClaimInformation().getHealthCareCodeInformation())) {
				int n = professionalClaimValidation.getClaimInformation().getHealthCareCodeInformation().size();
				String code = null;
				for (int i = 0; i < n; i++) {
					if (GenericComponentValidations.isStringNullOrBlank(professionalClaimValidation
							.getClaimInformation().getHealthCareCodeInformation().get(i).getDiagnosisCode())) {
						code = professionalClaimValidation.getClaimInformation().getHealthCareCodeInformation().get(i)
								.getDiagnosisTypeCode();
						if (!(code.equalsIgnoreCase("BK") || code.equalsIgnoreCase("ABK") || code.equalsIgnoreCase("BF")
								|| code.equalsIgnoreCase("ABF"))) {
							serviceOutcome
									.setMessage("Kindly enter proper Diagnosis Type Code for Health Care Information");
							serviceOutcome.setData(professionalClaimValidation);
							serviceOutcome.setOutcome(false);
							return serviceOutcome;
						}
					}
					if (GenericComponentValidations.isStringNullOrBlank(professionalClaimValidation
							.getClaimInformation().getHealthCareCodeInformation().get(i).getDiagnosisTypeCode())) {
						if (!GenericComponentValidations.isalphabets(code)) {
							serviceOutcome.setMessage("Kindly enter proper Diagnosis Code Health Care Information");
							serviceOutcome.setData(professionalClaimValidation);
							serviceOutcome.setOutcome(false);
							return serviceOutcome;
						}
					}
				}
			}
			// serviceFacilityLocation
			if (!Objects.isNull(professionalClaimValidation.getClaimInformation().getServiceFacilityLocation())) {
				// organizationName
				if (GenericComponentValidations.isStringNullOrBlank(professionalClaimValidation.getClaimInformation()
						.getServiceFacilityLocation().getOrganizationName())) {
					if (!GenericComponentValidations.isName(professionalClaimValidation.getClaimInformation()
							.getServiceFacilityLocation().getOrganizationName())) {
						serviceOutcome.setMessage("Service FacilityLocation Organization Name should be valid");
						serviceOutcome.setData(professionalClaimValidation);
						serviceOutcome.setOutcome(false);
						return serviceOutcome;
					}
				}
				// Address
				if (!Objects.isNull(
						professionalClaimValidation.getClaimInformation().getServiceFacilityLocation().getAddress())) {

					// address1
					if (GenericComponentValidations.isStringNullOrBlank(professionalClaimValidation
							.getClaimInformation().getServiceFacilityLocation().getAddress().getAddress1())) {
						resp = GenericComponentValidations.isalphanumericspace(professionalClaimValidation
								.getClaimInformation().getServiceFacilityLocation().getAddress().getAddress1());
						if (resp != true) {
							serviceOutcome.setMessage(
									"serviceFacilityLocation Address 1 should contain only alphanumeic values");
							serviceOutcome.setData(professionalClaimValidation);
							serviceOutcome.setOutcome(false);
							return serviceOutcome;
						}
					}
					// city
					if (GenericComponentValidations.isStringNullOrBlank(professionalClaimValidation
							.getClaimInformation().getServiceFacilityLocation().getAddress().getCity())) {
						resp = GenericComponentValidations.isalphanumericspace(professionalClaimValidation
								.getClaimInformation().getServiceFacilityLocation().getAddress().getCity());
						if (resp != true) {
							serviceOutcome.setMessage(
									"Service FacilityLocation City should contain only alphabets and Numbers");
							serviceOutcome.setData(professionalClaimValidation);
							serviceOutcome.setOutcome(false);
							return serviceOutcome;
						}
					}
					// state
					if (GenericComponentValidations.isStringNullOrBlank(professionalClaimValidation
							.getClaimInformation().getServiceFacilityLocation().getAddress().getState())) {
						resp = GenericComponentValidations.isName(professionalClaimValidation.getClaimInformation()
								.getServiceFacilityLocation().getAddress().getState());
						if (resp != true) {
							serviceOutcome.setMessage("serviceFacilityLocation State should contain only alphabets");
							serviceOutcome.setData(professionalClaimValidation);
							serviceOutcome.setOutcome(false);
							return serviceOutcome;
						}
					}
					// Postal Code
					if (GenericComponentValidations.isStringNullOrBlank(professionalClaimValidation
							.getClaimInformation().getServiceFacilityLocation().getAddress().getPostalCode())) {
						resp = GenericComponentValidations.isdigit(professionalClaimValidation.getClaimInformation()
								.getServiceFacilityLocation().getAddress().getPostalCode());
						if (resp != true || (professionalClaimValidation.getSubscriber().getAddress().getPostalCode()
								.length() != 5
								&& professionalClaimValidation.getSubscriber().getAddress().getPostalCode()
										.length() != 9)) {
							serviceOutcome.setMessage(
									"serviceFacilityLocation Postal Code should contain only numbers of 5 or 9 digits");
							serviceOutcome.setData(professionalClaimValidation);
							serviceOutcome.setOutcome(false);
							return serviceOutcome;
						}
					}

				}

			}
			if (!Objects.isNull(professionalClaimValidation.getClaimInformation().getServiceLines())) {
				for (int i = 0; i < professionalClaimValidation.getClaimInformation().getServiceLines().size(); i++) {
					if (!Objects.isNull(professionalClaimValidation.getClaimInformation().getServiceLines().get(i)
							.getProfessionalService())) {

						// serviceDate
						if (!GenericComponentValidations.isStringNullOrBlank(professionalClaimValidation
								.getClaimInformation().getServiceLines().get(i).getServiceDate())) {
							serviceOutcome.setMessage("Service Date Of Birth cannot be null");
							serviceOutcome.setOutcome(false);
							serviceOutcome.setData(professionalClaimValidation);
							return serviceOutcome;
						} else {
							resp = GenericComponentValidations.isValidDate(professionalClaimValidation
									.getClaimInformation().getServiceLines().get(i).getServiceDate());
							if (!resp) {
								serviceOutcome.setMessage("Service Date Of Birth is not valid");
								serviceOutcome.setOutcome(false);
								serviceOutcome.setData(professionalClaimValidation);
								return serviceOutcome;
							}
						}

						// procedureIdentifier
						if (GenericComponentValidations
								.isStringNullOrBlank(professionalClaimValidation.getClaimInformation().getServiceLines()
										.get(i).getProfessionalService().getProcedureIdentifier())) {
							String procedureIdentifier = professionalClaimValidation.getClaimInformation()
									.getServiceLines().get(i).getProfessionalService().getProcedureIdentifier();
							if (!(procedureIdentifier.equalsIgnoreCase("ER")
									|| procedureIdentifier.equalsIgnoreCase("HC")
									|| procedureIdentifier.equalsIgnoreCase("IV")
									|| procedureIdentifier.equalsIgnoreCase("WK"))) {
								serviceOutcome.setMessage("Kindly Provide proper values for procedureIdentifier");
								serviceOutcome.setData(professionalClaimValidation);
								serviceOutcome.setOutcome(false);
								return serviceOutcome;
							}
						}
						// procedureCode
						if (GenericComponentValidations
								.isStringNullOrBlank(professionalClaimValidation.getClaimInformation().getServiceLines()
										.get(i).getProfessionalService().getProcedureCode())) {
							if (!GenericComponentValidations
									.isalphanumeric(professionalClaimValidation.getClaimInformation().getServiceLines()
											.get(i).getProfessionalService().getProcedureCode())) {
								serviceOutcome.setMessage("Kindly Provide proper values for procedureCode");
								serviceOutcome.setData(professionalClaimValidation);
								serviceOutcome.setOutcome(false);
								return serviceOutcome;
							}

						}
						// measurementUnit
						if (GenericComponentValidations
								.isStringNullOrBlank(professionalClaimValidation.getClaimInformation().getServiceLines()
										.get(i).getProfessionalService().getMeasurementUnit())) {
							if (!GenericComponentValidations
									.isalphanumeric(professionalClaimValidation.getClaimInformation().getServiceLines()
											.get(i).getProfessionalService().getProcedureCode())) {
								serviceOutcome.setMessage("Kindly Provide proper values for procedureCode");
								serviceOutcome.setData(professionalClaimValidation);
								serviceOutcome.setOutcome(false);
								return serviceOutcome;
							}

						}
						// serviceUnitCount
						if (GenericComponentValidations
								.isStringNullOrBlank(professionalClaimValidation.getClaimInformation().getServiceLines()
										.get(i).getProfessionalService().getServiceUnitCount())) {
							if (!GenericComponentValidations.isdigit(professionalClaimValidation.getClaimInformation()
									.getServiceLines().get(i).getProfessionalService().getServiceUnitCount())) {
								serviceOutcome.setMessage("Kindly Provide proper values for serviceUnitCount");
								serviceOutcome.setData(professionalClaimValidation);
								serviceOutcome.setOutcome(false);
								return serviceOutcome;
							}
						}
					}
				}
			}

		}

		return serviceOutcome;
	}
}
