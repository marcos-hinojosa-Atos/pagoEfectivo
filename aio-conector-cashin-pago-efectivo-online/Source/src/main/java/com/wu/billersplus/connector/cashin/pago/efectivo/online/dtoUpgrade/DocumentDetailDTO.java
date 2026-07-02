package com.wu.billersplus.connector.cashin.pago.efectivo.online.dtoUpgrade;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DocumentDetailDTO {

    @NotNull(message = "{document.cip.null}")
    @Size(min = 1, max = 20, message = "{document.cip.size}")
    @JsonProperty("cip")
    private String cip;

    @NotNull(message = "{document.total.null}")
    @JsonProperty("total")
    private Integer total;

    @NotNull(message = "{document.amount.null}")
    @JsonProperty("amount")
    private Integer amount;

    @NotNull(message = "{document.latePaymentFee.null}")
    @JsonProperty("latePaymentFee")
    private Integer latePaymentFee;

    @NotNull(message = "{document.currency.null}")
    @Size(min = 3, max = 3, message = "{document.currency.size}")
    // ISO currency: PEN, USD
    @JsonProperty("currency")
    private String currency;

    @NotNull(message = "{document.issueDate.null}")
    @Size(min = 25, max = 40, message = "{document.issueDate.size}")
    @JsonProperty("issueDate")
    private String issueDate;

    @NotNull(message = "{document.expirationDate.null}")
    @Size(min = 25, max = 40, message = "{document.expirationDate.size}")
    @JsonProperty("expirationDate")
    private String expirationDate;

    @NotNull(message = "{document.description.null}")
    @Size(min = 1, max = 255, message = "{document.description.size}")
    @JsonProperty("description")
    private String description;

    @NotNull(message = "{document.agreementCode.null}")
    @Size(min = 1, max = 10, message = "{document.agreementCode.size}")
    @JsonProperty("agreementCode")
    private String agreementCode;

    public String getCip() {
        return cip;
    }

    public void setCip(String cip) {
        this.cip = cip;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getLatePaymentFee() {
        return latePaymentFee;
    }

    public void setLatePaymentFee(Integer latePaymentFee) {
        this.latePaymentFee = latePaymentFee;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAgreementCode() {
        return agreementCode;
    }

    public void setAgreementCode(String agreementCode) {
        this.agreementCode = agreementCode;
    }
}

