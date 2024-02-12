package com.sunknowledge.dme.rcm.application.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileUploadConfigProperties {
    /*#######################################Start - DELIVERY-SERVICE#################################################*/
    @Bean
    @ConfigurationProperties(prefix = "file.delivery.signatures")
    public FileUploadProperties getSignatureProperties(){return new FileUploadProperties();}

    @Bean
    @ConfigurationProperties(prefix = "file.delivery.template")
    public FileUploadProperties getTemplateProperties(){return new FileUploadProperties();}

    @Bean
    @ConfigurationProperties(prefix = "file.delivery.delivery-documents")
    public FileUploadProperties getDeliveryDocumentProperties(){ return new FileUploadProperties();}

    @Bean
    @ConfigurationProperties(prefix = "file.delivery.temp-qrcode")
    public FileUploadProperties getTempDocumentProperties(){ return new FileUploadProperties(); }

    @Bean
    @ConfigurationProperties(prefix = "file.delivery.carrier-documents")
    public FileUploadProperties getDeliveryCarrierProperties(){return new FileUploadProperties();}

    @Bean
    @ConfigurationProperties(prefix = "file.signature.upload")
    public FileUploadProperties getSignatureImageProperties(){return new FileUploadProperties();}

    @Bean
    @ConfigurationProperties(prefix = "file.abn.template")
    public FileUploadProperties getAbnTemplateProperties(){
        return new FileUploadProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "file.abn-delivery-documents")
    public FileUploadProperties getAbnDeliveryDocumentProperties(){ return new FileUploadProperties();}
    /*#######################################End - DELIVERY-SERVICE####################################################*/

    /*#######################################Start - SALESORDER-SERVICE#################################################*/
    @Bean
    @ConfigurationProperties(prefix = "file.cmn.generated.document")
    public FileUploadProperties getCmnGeneratedDocumentProperties(){return new FileUploadProperties();}

    @Bean
    @ConfigurationProperties(prefix = "file.cmn.return.document")
    public FileUploadProperties getCmnReturnDocumentProperties(){return new FileUploadProperties();}

    @Bean
    @ConfigurationProperties(prefix = "file.cmn.temp-qrcode")
    public FileUploadProperties getCmnTempQrcodeProperties(){return new FileUploadProperties();}

    @Bean
    @ConfigurationProperties(prefix = "file.prior.authorization.document")
    public FileUploadProperties getPriorAuthorizationDocumentProperties(){return new FileUploadProperties();}

    @Bean
    @ConfigurationProperties(prefix = "file.prior.authorization.temp-qrcode")
    public FileUploadProperties getPriorAuthorizationQrcodeProperties(){return new FileUploadProperties();}
    /*#######################################End - SALESORDER-SERVICE#########################################################*/

    /*#######################################Start - PICK UP EXCHANGE-SERVICE#################################################*/
    @Bean
    @ConfigurationProperties(prefix = "file.delivery.pickup-exchange-documents")
    public FileUploadProperties getPickupExchangeDocumentProperties(){ return new FileUploadProperties();}

    @Bean
    @ConfigurationProperties(prefix = "file.delivery.tmp-documents-pickup-exchange")
    public FileUploadProperties getPickupExchangeTempQrCodeProperties(){ return new FileUploadProperties();}
    /*#######################################End - PICK UP EXCHANGE-SERVICE#################################################*/

    /*#######################################Start - Patient -SERVICE#################################################*/

    @Bean
    @ConfigurationProperties(prefix = "file.patient.document")
    public FileUploadProperties getPatientDocumentsPDDorPIDorPMRProperties(){ return new FileUploadProperties();}

    @Bean
    @ConfigurationProperties(prefix = "file.patient.qrcode")
    public FileUploadProperties getPatientDocumentsQRcodeProperties(){ return new FileUploadProperties();}

    /*#######################################End - Patient-SERVICE#################################################*/

    /*#######################################Start - DOCUMENT MANAGEMENT-SERVICE#################################################*/
    @Bean
    @ConfigurationProperties(prefix = "file.document-management.patient.documents")
    public FileUploadProperties getDmePatientDocuments(){return new FileUploadProperties();}
    @Bean
    @ConfigurationProperties(prefix = "file.document-management.temp-qrcode")
    public FileUploadProperties getDmeTempQrCode(){return new FileUploadProperties();}
    /*#######################################End - DOCUMENT MANAGEMENT-SERVICE####################################################*/

    /*#######################################Start - CLAIM-SERVICE################################################################*/
    @Bean
    @ConfigurationProperties(prefix = "file.base.form-cms1500.document")
    public FileUploadProperties getBaseFormCMS1500Documents(){return new FileUploadProperties();}
    @Bean
    @ConfigurationProperties(prefix = "file.primary-claim.form-cms1500.documents")
    public FileUploadProperties getPrimaryClaimFormCMS1500Documents(){return new FileUploadProperties();}
    @Bean
    @ConfigurationProperties(prefix = "file.secondary-claim.form-cms1500.documents")
    public FileUploadProperties getSecondaryClaimFormCMS1500Documents(){return new FileUploadProperties();}
    @Bean
    @ConfigurationProperties(prefix = "file.re-claim.form-cms1500.documents")
    public FileUploadProperties getReClaimFormCMS1500Documents(){return new FileUploadProperties();}
    @Bean
    @ConfigurationProperties(prefix = "file.temp-claim.form-cms1500.documents")
    public FileUploadProperties getTempClaimFormCMS1500Documents(){return new FileUploadProperties();}
    /*#######################################End - CLAIM-SERVICE#################################################################*/

    /*#######################################Start - ITEMS-SERVICE#################################################*/
    @Bean
    @ConfigurationProperties(prefix = "file.purchase-order.documents")
    public FileUploadProperties getPurchaseOrderDocuments(){return new FileUploadProperties();}
    @Bean
    @ConfigurationProperties(prefix = "file.purchase-order.temp-qrcode")
    public FileUploadProperties getPurchaseOrderTempQrCode(){return new FileUploadProperties();}
    /*#######################################End - ITEMS-SERVICE####################################################*/
}
