package com.wu.billersplus.connector.cashin.pago.efectivo.online.dtoUpgrade;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class ReversaRequestDTO {

    @NotNull
    @Size(min = 1, max = 14)
    @JsonProperty("salePointCode")
    private String salePointCode;

    @NotNull
    @Size(min = 1, max = 14)
    @JsonProperty("terminalNumber")
    private String terminalNumber;

    @NotNull
    @Size(min = 1, max = 14)
    @JsonProperty("paymentOperationNumber")
    private String paymentOperationNumber;

    @NotNull
    @Size(min = 1, max = 14)
    @JsonProperty("refundOperationNumber")
    private String refundOperationNumber;

    @Size(max = 25)
    @JsonProperty("responseDate")
    private String responseDate;

    @NotNull
    @Size(min = 1, max = 14)
    @JsonProperty("orderNumber")
    private String orderNumber;

    @NotNull
    @Size(min = 1, max = 7)
    @JsonProperty("agreementCode")
    private String agreementCode;

    @NotNull
    @Size(min = 3, max = 3)
    @JsonProperty("currency")
    private String currency;

    @NotNull
    @JsonProperty("channel")
    private Integer channel;

    @NotNull
    @JsonProperty("paymentMethodCode")
    private Integer paymentMethodCode;

    @NotNull
    @JsonProperty("documentsQuantity")
    private Integer documentsQuantity;

    @NotNull
    @JsonProperty("documentsDetail")
    private List<DocumentReversaDetailRequestDTO> documentsDetail;

    @Size(min = 1, max = 50)
    @JsonProperty("variable1")
    private String variable1;

    @Size(min = 1, max = 50)
    @JsonProperty("variable2")
    private String variable2;

    @NotNull
    @Size(min = 36, max = 36)
    @JsonProperty("secretKey")
    private String secretKey;

    // Getters y setters

    public String getSalePointCode() { return salePointCode; }
    public void setSalePointCode(String salePointCode) { this.salePointCode = salePointCode; }

    public String getTerminalNumber() { return terminalNumber; }
    public void setTerminalNumber(String terminalNumber) { this.terminalNumber = terminalNumber; }

    public String getPaymentOperationNumber() { return paymentOperationNumber; }
    public void setPaymentOperationNumber(String paymentOperationNumber) { this.paymentOperationNumber = paymentOperationNumber; }

    public String getRefundOperationNumber() { return refundOperationNumber; }
    public void setRefundOperationNumber(String refundOperationNumber) { this.refundOperationNumber = refundOperationNumber; }

    public String getResponseDate() { return responseDate; }
    public void setResponseDate(String responseDate) { this.responseDate = responseDate; }

    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }

    public String getAgreementCode() { return agreementCode; }
    public void setAgreementCode(String agreementCode) { this.agreementCode = agreementCode; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public Integer getChannel() { return channel; }
    public void setChannel(Integer channel) { this.channel = channel; }

    public Integer getPaymentMethodCode() { return paymentMethodCode; }
    public void setPaymentMethodCode(Integer paymentMethodCode) { this.paymentMethodCode = paymentMethodCode; }

    public Integer getDocumentsQuantity() { return documentsQuantity; }
    public void setDocumentsQuantity(Integer documentsQuantity) { this.documentsQuantity = documentsQuantity; }

    public List<DocumentReversaDetailRequestDTO> getDocumentsDetail() { return documentsDetail; }
    public void setDocumentsDetail(List<DocumentReversaDetailRequestDTO> documentsDetail) { this.documentsDetail = documentsDetail; }

    public String getVariable1() { return variable1; }
    public void setVariable1(String variable1) { this.variable1 = variable1; }

    public String getVariable2() { return variable2; }
    public void setVariable2(String variable2) { this.variable2 = variable2; }

    public String getSecretKey() { return secretKey; }
    public void setSecretKey(String secretKey) { this.secretKey = secretKey; }
}
