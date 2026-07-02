package com.wu.billersplus.connector.cashin.pago.efectivo.online.dtoUpgrade;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DirectaResponseDTO {

    @NotNull(message = "code no puede ser null")
    @Size(min = 3, max = 3, message = "code debe tener longitud 3")
    @JsonProperty("code")
    private String code;

    @NotNull(message = "message no puede ser null")
    @Size(max = 200, message = "message máximo 200 caracteres")
    @JsonProperty("message")
    private String message;

    @NotNull(message = "data no puede ser null")
    @JsonProperty("data")
    private DataDirectaResponseDTO data;

    // Getters y setters
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public DataDirectaResponseDTO getData() { return data; }
    public void setData(DataDirectaResponseDTO data) { this.data = data; }
}
