package com.sunknowledge.dme.rcm.service.dto.soentryandsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesOrderSearchParameterDTO {
    Long createdById;
    @NotBlank(message = "Value must not be blank")
    String status;
    @NotBlank(message = "Value must not be blank")
    @Pattern(regexp = "^([0-2][0-9]|(3)[0-1])(\\-)(((0)[0-9])|((1)[0-2]))(\\-)\\d{4}$", message = "From_Date accepts only 'dd-mm-yyyy' format")
    String fromDate;
    @NotBlank(message = "Value must not be blank")
    @Pattern(regexp = "^([0-2][0-9]|(3)[0-1])(\\-)(((0)[0-9])|((1)[0-2]))(\\-)\\d{4}$", message = "To_Date accepts only 'dd-mm-yyyy' format")
    String toDate;
    @NotBlank(message = "Value must not be blank")
    String branchName;
    @NotBlank(message = "Value must not be blank")
    String facilityName;
    @NotBlank(message = "Value must not be blank")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Provide only alphanumeric characters for SO_Number")
    String salesOrderNumber;
    @NotBlank(message = "Value must not be blank")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Provide only appropriate Patient_Name")
    String patientName;
    Long patientId;
    Long confirmationbyid;
    @NotBlank(message = "Value must not be blank")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Provide only appropriate Confirmation_By_Name")
    String confirmationByName;
    @NotBlank(message = "Value must not be blank")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Provide only appropriate Created_By_Name")
    String createdByName;
    @NotBlank(message = "Value must not be blank")
    @Pattern(regexp = "^([0-2][0-9]|(3)[0-1])(\\-)(((0)[0-9])|((1)[0-2]))(\\-)\\d{4}$", message = "Delivery_Schedule_Date_Time accepts only 'dd-mm-yyyy' format")
    String deliveryScheduleDatetime;
    @NotBlank(message = "Value must not be blank")
    @Pattern(regexp = "^([0-2][0-9]|(3)[0-1])(\\-)(((0)[0-9])|((1)[0-2]))(\\-)\\d{4}$", message = "Delivery_Actual_Date_Time accepts only 'dd-mm-yyyy' format")
    String deliveryActualDatetime;
    @NotBlank(message = "Value must not be blank")
    @Pattern(regexp = "^([0-2][0-9]|(3)[0-1])(\\-)(((0)[0-9])|((1)[0-2]))(\\-)\\d{4}$", message = "Created_Date accepts only 'dd-mm-yyyy' format")
    String createdDate;
    @NotBlank(message = "Patient_DOB must be a date in the past")
    @Pattern(regexp = "^([0-2][0-9]|(3)[0-1])(\\-)(((0)[0-9])|((1)[0-2]))(\\-)\\d{4}$", message = "Patient_DOB accepts only 'dd-mm-yyyy' format")
    //@Past()
    String patientDOB;
    @NotBlank(message = "Value must not be blank")
    String insurerName;
    @NotBlank(message = "Value must not be blank")
    String patientAddress1;
    Long insuranceId;
}
