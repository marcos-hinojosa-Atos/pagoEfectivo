package com.wu.billersplus.connector.cashin.pago.efectivo.online.dtoUpgrade;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class DataDTO {

    @NotNull(message = "{data.responseDate.null}")
    @Size(min = 25, max = 40, message = "{data.responseDate.size}")
    // ISO datetime: "2026-04-10T17:36:54-05:00"
    @JsonProperty("responseDate")
    private String responseDate;

    @NotNull(message = "{data.user.null}")
    @Size(min = 1, max = 100, message = "{data.user.size}")
    @JsonProperty("user")
    private String user;

    @NotNull(message = "{data.salePointCode.null}")
    @Size(min = 1, max = 15, message = "{data.salePointCode.size}")
    @JsonProperty("salePointCode")
    private String salePointCode;

    @NotNull(message = "{data.terminalNumber.null}")
    @Size(min = 1, max = 10, message = "{data.terminalNumber.size}")
    @JsonProperty("terminalNumber")
    private String terminalNumber;

    @NotNull(message = "{data.operationNumber.null}")
    @Size(min = 1, max = 10, message = "{data.operationNumber.size}")
    @JsonProperty("operationNumber")
    private String operationNumber;

    @NotNull(message = "{data.orderNumber.null}")
    @Size(min = 1, max = 15, message = "{data.orderNumber.size}")
    @JsonProperty("orderNumber")
    private String orderNumber;

    @NotNull(message = "{data.documentsQuantity.null}")
    @JsonProperty("documentsQuantity")
    private Integer documentsQuantity;

    @NotNull(message = "{data.portal.null}")
    @Size(min = 1, max = 50, message = "{data.portal.size}")
    @JsonProperty("portal")
    private String portal;

    @Size(max = 50, message = "{data.variable1.size}")
    @JsonProperty("variable1")
    private String variable1;

    @Size(max = 50, message = "{data.variable2.size}")
    @JsonProperty("variable2")
    private String variable2;

    @NotNull(message = "{data.documentsDetail.null}")
    @Valid
    @JsonProperty("documentsDetail")
    private List<DocumentDetailDTO> documentsDetail;

    public String getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(String responseDate) {
        this.responseDate = responseDate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

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

    public Integer getDocumentsQuantity() {
        return documentsQuantity;
    }

    public void setDocumentsQuantity(Integer documentsQuantity) {
        this.documentsQuantity = documentsQuantity;
    }

    public String getPortal() {
        return portal;
    }

    public void setPortal(String portal) {
        this.portal = portal;
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

    public List<DocumentDetailDTO> getDocumentsDetail() {
        return documentsDetail;
    }

    public void setDocumentsDetail(List<DocumentDetailDTO> documentsDetail) {
        this.documentsDetail = documentsDetail;
    }

    @Override
    public String toString() {
        return "DataDTO{" +
                "responseDate='" + responseDate + '\'' +
                ", user='" + user + '\'' +
                ", salePointCode='" + salePointCode + '\'' +
                ", terminalNumber='" + terminalNumber + '\'' +
                ", operationNumber='" + operationNumber + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", documentsQuantity=" + documentsQuantity +
                ", portal='" + portal + '\'' +
                ", variable1='" + variable1 + '\'' +
                ", variable2='" + variable2 + '\'' +
                ", documentsDetail=" + documentsDetail +
                '}';
    }
}

