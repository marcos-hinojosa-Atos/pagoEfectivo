package com.wu.billersplus.connector.cashin.pago.efectivo.online.dtoUpgrade;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DocumentReversaDetailRequestDTO {

    @NotNull
    @Size(min = 1, max = 14)
    @JsonProperty("cip")
    private String cip;

    @NotNull
    @JsonProperty("total")
    private Double total;

    @NotNull
    @JsonProperty("amount")
    private Double amount;

    @NotNull
    @JsonProperty("latePaymentFee")
    private Double latePaymentFee;

    @NotNull
    @Size(min = 3, max = 3)
    @JsonProperty("currency")
    private String currency;

    @NotNull
    @Size(max = 25)
    @JsonProperty("issueDate")
    private String issueDate;

    @NotNull
    @Size(max = 25)
    @JsonProperty("expirationDate")
    private String expirationDate;

    @NotNull
    @Size(min = 1, max = 25)
    @JsonProperty("description")
    private String description;

    // Getters y setters

    public String getCip() { return cip; }
    public void setCip(String cip) { this.cip = cip; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public Double getLatePaymentFee() { return latePaymentFee; }
    public void setLatePaymentFee(Double latePaymentFee) { this.latePaymentFee = latePaymentFee; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getIssueDate() { return issueDate; }
    public void setIssueDate(String issueDate) { this.issueDate = issueDate; }

    public String getExpirationDate() { return expirationDate; }
    public void setExpirationDate(String expirationDate) { this.expirationDate = expirationDate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
