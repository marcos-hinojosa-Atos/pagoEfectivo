package com.wu.billersplus.connector.cashin.pago.efectivo.online.dtoUpgrade;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.*;

import java.math.BigDecimal;

import java.util.Date;

public class DocumentDetailDirectaDTO {

    @NotNull(message = "{documentDetailDirecta.cip.required}")
    @Size(min = 1, max = 14, message = "{documentDetail.cip.size}")
    @JsonProperty("cip")
    private String cip;

    @NotNull(message = "{documentDetailDirecta.total.required}")
    @Digits(integer = 15, fraction = 2,
            message = "{documentDetail.total.format}")
    @JsonProperty("total")
    private BigDecimal total;

    @NotNull(message = "{documentDetailDirecta.amount.required}")
    @Digits(integer = 15, fraction = 2,
            message = "{documentDetail.amount.format}")
    @JsonProperty("amount")
    private BigDecimal amount;

    @NotNull(message = "{documentDetailDirecta.latePaymentFee.required}")
    @Digits(integer = 15, fraction = 2,
            message = "{documentDetail.latePaymentFee.format}")
    @JsonProperty("latePaymentFee")
    private BigDecimal latePaymentFee;

    @NotNull(message = "{documentDetailDirecta.currency.required}")
    @Size(min = 3, max = 3,
            message = "{documentDetail.currency.size}")
    @JsonProperty("currency")
    private String currency;

    @NotNull(message = "{documentDetailDirecta.issueDate.required}")
    @JsonProperty("issueDate")
    private Date issueDate;

    @NotNull(message = "{documentDetailDirecta.expirationDate.required}")
    @JsonProperty("expirationDate")
    private Date expirationDate;

    @NotNull(message = "{documentDetailDirecta.description.required}")
    @Size(min = 1, max = 25,
            message = "{documentDetail.description.size}")
    @JsonProperty("description")
    private String description;

    @NotNull(message = "{documentDetailDirecta.agreementCode.required}")
    @Size(min = 1, max = 7,
            message = "{documentDetailDirecta.agreementCode.size}")
    @JsonProperty("agreementCode")
    private String agreementCode;

    // GETTERS Y SETTERS

    public String getCip() {
        return cip;
    }

    public void setCip(String cip) {
        this.cip = cip;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getLatePaymentFee() {
        return latePaymentFee;
    }

    public void setLatePaymentFee(BigDecimal latePaymentFee) {
        this.latePaymentFee = latePaymentFee;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
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