package com.wu.billersplus.connector.cashin.pago.efectivo.online.dtoUpgrade;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class DataReversaResponseDTO {

    @NotNull
    @Size(max = 19)
    @JsonProperty("responseDate")
    private String responseDate;

    @NotNull
    @Size(min = 1, max = 14)
    @JsonProperty("movementNumber")
    private String movementNumber;

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
    @JsonProperty("refundOperationNumber")
    private String refundOperationNumber;

    @NotNull
    @Size(min = 1, max = 14)
    @JsonProperty("operationNumber")
    private String operationNumber;

    @NotNull
    @Size(min = 1, max = 14)
    @JsonProperty("orderNumber")
    private String orderNumber;

    @NotNull
    @JsonProperty("documentsQuantity")
    private Integer documentsQuantity;

    @NotNull
    @JsonProperty("documentsDetail")
    private List<DocumentReversaDetailResponseDTO> documentsDetail;

    @Size(min = 1, max = 50)
    @JsonProperty("variable1")
    private String variable1;

    @Size(min = 1, max = 50)
    @JsonProperty("variable2")
    private String variable2;

    // Getters y setters
    public String getResponseDate() { return responseDate; }
    public void setResponseDate(String responseDate) { this.responseDate = responseDate; }

    public String getMovementNumber() { return movementNumber; }
    public void setMovementNumber(String movementNumber) { this.movementNumber = movementNumber; }

    public String getSalePointCode() { return salePointCode; }
    public void setSalePointCode(String salePointCode) { this.salePointCode = salePointCode; }

    public String getTerminalNumber() { return terminalNumber; }
    public void setTerminalNumber(String terminalNumber) { this.terminalNumber = terminalNumber; }

    public String getRefundOperationNumber() { return refundOperationNumber; }
    public void setRefundOperationNumber(String refundOperationNumber) { this.refundOperationNumber = refundOperationNumber; }

    public String getOperationNumber() { return operationNumber; }
    public void setOperationNumber(String operationNumber) { this.operationNumber = operationNumber; }

    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }

    public Integer getDocumentsQuantity() { return documentsQuantity; }
    public void setDocumentsQuantity(Integer documentsQuantity) { this.documentsQuantity = documentsQuantity; }

    public List<DocumentReversaDetailResponseDTO> getDocumentsDetail() { return documentsDetail; }
    public void setDocumentsDetail(List<DocumentReversaDetailResponseDTO> documentsDetail) { this.documentsDetail = documentsDetail; }

    public String getVariable1() { return variable1; }
    public void setVariable1(String variable1) { this.variable1 = variable1; }

    public String getVariable2() { return variable2; }
    public void setVariable2(String variable2) { this.variable2 = variable2; }
}