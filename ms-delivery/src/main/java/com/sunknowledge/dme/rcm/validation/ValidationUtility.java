package com.sunknowledge.dme.rcm.validation;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.service.dto.DeliveryAssignmentDTO;
import com.sunknowledge.dme.rcm.service.dto.DeliveryTicketDTO;
import com.sunknowledge.dme.rcm.service.dto.delivery.CourierTypeDeliveryInput;
import com.sunknowledge.dme.rcm.service.dto.delivery.DeliveryAssignInput;
import com.sunknowledge.dme.rcm.service.dto.delivery.SetupTechnicianInput;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ValidationUtility {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(Locale.getDefault());
    public static ServiceOutcome<DeliveryAssignmentDTO> validateDeliveryTicketAssignmentRequestParams(DeliveryAssignInput deliveryAssignInput) {
        if(deliveryAssignInput.getDeliveryTicketId() == null || deliveryAssignInput.getDeliveryTicketId().equals("")){
            return new ServiceOutcome<>(null, false, "ID field should not be empty!");
        }
        if(deliveryAssignInput.getAgentFirstName() == null || deliveryAssignInput.getAgentFirstName().equals("")){
            return new ServiceOutcome<>(null, false, "Agent first name should not be empty!");
        }
        if(deliveryAssignInput.getAgentLastName() == null || deliveryAssignInput.getAgentLastName().equals("")){
            return new ServiceOutcome<>(null, false, "Agent last name should not be empty!");
        }
        if(deliveryAssignInput.getAgentVehicleNo() == null || deliveryAssignInput.getAgentVehicleNo().equals("")){
            return new ServiceOutcome<>(null, false, "Agent vehicle number should not be empty!");
        }
        if(deliveryAssignInput.getAgentIdNo() == null || deliveryAssignInput.getAgentIdNo().equals("")){
            return new ServiceOutcome<>(null, false, "Agent number should not be empty!");
        }
        if(deliveryAssignInput.getAgentContact1() == null || deliveryAssignInput.getAgentContact1().equals("")){
            return new ServiceOutcome<>(null, false, "Agent contact number should not be empty!");
        }
        return new ServiceOutcome<>(new DeliveryAssignmentDTO(), true, "Validated successfully!");
    }

    public static ServiceOutcome<DeliveryTicketDTO> validateCourierTypeDeliveryRequestParams(CourierTypeDeliveryInput courierTypeDeliveryInput) {
        if(courierTypeDeliveryInput.getDeliveryTicketId() == null || courierTypeDeliveryInput.getDeliveryTicketId().equals("")){
            return new ServiceOutcome<>(null, false, "ID field should not be empty!");
        }
        if(courierTypeDeliveryInput.getDeliverType() == null || courierTypeDeliveryInput.getDeliverType().equals("")){
            return new ServiceOutcome<>(null, false, "Delivery type should not be empty!");
        }
        if(courierTypeDeliveryInput.getCarrierName() == null || courierTypeDeliveryInput.getCarrierName().equals("")){
            return new ServiceOutcome<>(null, false, "Carrier name should not be empty!");
        }
        if(courierTypeDeliveryInput.getReferenceNo() == null || courierTypeDeliveryInput.getReferenceNo().equals("")){
            return new ServiceOutcome<>(null, false, "Reference name should not be empty!");
        }
        if(courierTypeDeliveryInput.getShippingDate() == null || courierTypeDeliveryInput.getShippingDate().equals("")){
            return new ServiceOutcome<>(null, false, "Shipping date should not be empty!");
        }
        if(courierTypeDeliveryInput.getTrackingNo() == null || courierTypeDeliveryInput.getTrackingNo().equals("")){
            return new ServiceOutcome<>(null, false, "Tracking number should not be empty!");
        }
        return new ServiceOutcome<>(new DeliveryTicketDTO(), true, "Validated successfully!");
    }

    public static ServiceOutcome<DeliveryTicketDTO> validateSetupTechnicianRequestParams(SetupTechnicianInput setupTechnicianInput) {
        if(setupTechnicianInput.getDeliveryTicketId() == null || setupTechnicianInput.getDeliveryTicketId().equals("")){
            return new ServiceOutcome<>(null, false, "ID field should not be empty!");
        }
        if(setupTechnicianInput.getSetupTechnicianNo() == null || setupTechnicianInput.getSetupTechnicianNo().equals("")){
            return new ServiceOutcome<>(null, false, "Technician number should not be empty!");
        }
        if(setupTechnicianInput.getSetupMethod() == null || setupTechnicianInput.getSetupMethod().equals("")){
            return new ServiceOutcome<>(null, false, "Setup method should not be empty!");
        }
        if(setupTechnicianInput.getSetupTechnicianContactNo() == null || setupTechnicianInput.getSetupTechnicianContactNo().equals("")){
            return new ServiceOutcome<>(null, false, "Technician contact number should not be empty!");
        }
        if(setupTechnicianInput.getSetupTechnicianFirstName() == null || setupTechnicianInput.getSetupTechnicianFirstName().equals("")){
            return new ServiceOutcome<>(null, false, "Technician first name should not be empty!");
        }
        if(setupTechnicianInput.getSetupTechnicianLastName() == null || setupTechnicianInput.getSetupTechnicianLastName().equals("")){
            return new ServiceOutcome<>(null, false, "Technician last name should not be empty!");
        }
        if(setupTechnicianInput.getSetupDateTime() == null || setupTechnicianInput.getSetupDateTime().equals("")){
            return new ServiceOutcome<>(null, false, "Setup date time should not be empty!");
        }
        if(setupTechnicianInput.getSetupDateTime() != null){
            try {
                LocalDate date = LocalDate.parse(setupTechnicianInput.getSetupDateTime(), formatter);
                return new ServiceOutcome<>(new DeliveryTicketDTO(), true, "Setup date time is the valid date!");
            }
            catch(Exception e){
                return new ServiceOutcome<>(null, false, "Setup date time should be a valid date!");
            }
        }
        return new ServiceOutcome<>(new DeliveryTicketDTO(), true, "Validated successfully!");
    }
}
