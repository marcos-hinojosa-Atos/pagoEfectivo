package com.wu.billersplus.connector.cashin.pago.efectivo.online.dtoUpgrade;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

public class DirectaRequestDTO {

    @NotNull(message = "{directaRequest.salePointCode.required}")
    @Size(min = 1, max = 14, message = "{directaRequest.salePointCode.size}")
    @JsonProperty("salePointCode")
    private String salePointCode;

    @NotNull(message = "{directaRequest.terminalNumber.required}")
    @Size(min = 1, max = 14, message = "{directaRequest.terminalNumber.size}")
    @JsonProperty("terminalNumber")
    private String terminalNumber;

    @NotNull(message = "{directaRequest.operationNumber.required}")
    @Size(min = 1, max = 14, message = "{directaRequest.operationNumber.size}")
    @JsonProperty("operationNumber")
    private String operationNumber;

    @NotNull(message = "{directaRequest.orderNumber.required}")
    @Size(min = 1, max = 14, message = "{directaRequest.orderNumber.size}")
    @JsonProperty("orderNumber")
    private String orderNumber;

    @NotNull(message = "{directaRequest.responseDate.required}")
    @JsonProperty("responseDate")
    private Date responseDate;

    @NotNull(message = "{directaRequest.agreementCode.required}")
    @Size(min = 1, max = 7, message = "{directaRequest.agreementCode.size}")
    @JsonProperty("agreementCode")
    private String agreementCode;

    @NotNull(message = "{directaRequest.channel.required}")
    @Min(value = 1, message = "{directaRequest.channel.min}")
    @Max(value = 16, message = "{directaRequest.channel.max}")
    @JsonProperty("channel")
    private Integer channel;

    @NotNull(message = "{directaRequest.paymentMethodCode.required}")
    @JsonProperty("paymentMethodCode")
    private Integer paymentMethodCode;

    @NotNull(message = "{directaRequest.ubigeo.required}")
    @Size(min = 6, max = 6, message = "{directaRequest.ubigeo.size}")
    @JsonProperty("ubigeo")
    private String ubigeo;

    @NotNull(message = "{directaRequest.documentsQuantity.required}")
    @Min(value = 1, message = "{directaRequest.documentsQuantity.min}")
    @Max(value = 1, message = "{directaRequest.documentsQuantity.max}")
    @JsonProperty("documentsQuantity")
    private Integer documentsQuantity;

    @NotNull(message = "{directaRequest.documentsDetail.required}")
    @Size(min = 1, max = 1,
            message = "{directaRequest.documentsDetail.size}")
    @Valid
    @JsonProperty("documentsDetail")
    private List<DocumentDetailDirectaDTO> documentsDetail;

    @Size(max = 50, message = "{directaRequest.variable1.size}")
    @JsonProperty("variable1")
    private String variable1;

    @Size(max = 50, message = "{directaRequest.variable2.size}")
    @JsonProperty("variable2")
    private String variable2;

    @NotNull(message = "{directaRequest.secretKey.required}")
    @Size(min = 36, max = 36, message = "{directaRequest.secretKey.size}")
    @JsonProperty("secretKey")
    private String secretKey;

    // GETTERS Y SETTERS

    public String getSalePointCode() {
        return salePointCode;
    }

    public void setSalePointCode(String salePointCode) {
        this.salePointCode = salePointCode;
    }

    public String getTerminalNumber() {
        return terminalNumber;
    }

    public void setTerminalNumber(String terminalNumber) {
        this.terminalNumber = terminalNumber;
    }

    public String getOperationNumber() {
        return operationNumber;
    }

    public void setOperationNumber(String operationNumber) {
        this.operationNumber = operationNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }

    public String getAgreementCode() {
        return agreementCode;
    }

    public void setAgreementCode(String agreementCode) {
        this.agreementCode = agreementCode;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Integer getPaymentMethodCode() {
        return paymentMethodCode;
    }

    public void setPaymentMethodCode(Integer paymentMethodCode) {
        this.paymentMethodCode = paymentMethodCode;
    }

    public String getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }

    public Integer getDocumentsQuantity() {
        return documentsQuantity;
    }

    public void setDocumentsQuantity(Integer documentsQuantity) {
        this.documentsQuantity = documentsQuantity;
    }

    public List<DocumentDetailDirectaDTO> getDocumentsDetail() {
        return documentsDetail;
    }

    public void setDocumentsDetail(List<DocumentDetailDirectaDTO> documentsDetail) {
        this.documentsDetail = documentsDetail;
    }

    public String getVariable1() {
        return variable1;
    }

    public void setVariable1(String variable1) {
        this.variable1 = variable1;
    }

    public String getVariable2() {
        return variable2;
    }

    public void setVariable2(String variable2) {
        this.variable2 = variable2;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
