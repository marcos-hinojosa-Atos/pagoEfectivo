package com.wu.billersplus.connector.cashin.pago.efectivo.online.dtoUpgrade;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DocumentReversaDetailResponseDTO {

    @NotNull
    @Size(min = 1, max = 14)
    @JsonProperty("movementNumber")
    private String movementNumber;

    @NotNull
    @Size(min = 1, max = 14)
    @JsonProperty("cip")
    private String cip;

    // Getters y setters
    public String getMovementNumber() { return movementNumber; }
    public void setMovementNumber(String movementNumber) { this.movementNumber = movementNumber; }

    public String getCip() { return cip; }
    public void setCip(String cip) { this.cip = cip; }
}

