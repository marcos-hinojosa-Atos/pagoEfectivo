package com.wu.billersplus.connector.cashin.pago.efectivo.online.dtoUpgrade;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ReversaResponseDTO {

    @NotNull
    @Size(min = 3, max = 3)
    @JsonProperty("code")
    private String code;

    @NotNull
    @Size(max = 200)
    @JsonProperty("message")
    private String message;

    @NotNull
    @JsonProperty("data")
    private DataReversaResponseDTO data;

    // Getters y setters
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public DataReversaResponseDTO getData() { return data; }
    public void setData(DataReversaResponseDTO data) { this.data = data; }
}
