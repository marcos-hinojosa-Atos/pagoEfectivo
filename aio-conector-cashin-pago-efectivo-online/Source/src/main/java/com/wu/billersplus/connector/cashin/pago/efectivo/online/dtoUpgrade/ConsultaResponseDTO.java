package com.wu.billersplus.connector.cashin.pago.efectivo.online.dtoUpgrade;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.dto.ErrorDTO;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.dto.RequestDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.dto.ResponseDTO;
import com.wu.billersplus.entities.BillersPlusRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsultaResponseDTO {

    @NotNull(message = "{consultaResponseDTO.code.null}")
    @Size(min = 1, max = 5, message = "{consultaResponseDTO.code.size}") // ej: "100"
    @JsonProperty("code")
    private String code;

    @NotNull(message = "{consultaResponseDTO.message.null}")
    @Size(min = 1, max = 255, message = "{consultaResponseDTO.message.size}") // texto descriptivo
    @JsonProperty("message")
    private String message;

    @NotNull(message = "{consultaResponseDTO.data.null}")
    @Valid
    @JsonProperty("data")
    private DataDTO data;
/*
    public ConsultaResponseDTO(BillersPlusRequest request, Map<String, String> parametros) {
        super(request, parametros);
    }
*/
    private String codResponse;
    private String msgResponse;

    public ConsultaResponseDTO (){
    }

    public ConsultaResponseDTO(ErrorDTO errorDTO) {
        this.setCodResponse(errorDTO.getCodigoError());
        this.setMsgResponse(errorDTO.getMensajeError());
    }

    public String getCodResponse() {
        return codResponse;
    }

    public void setCodResponse(String codResponse) {
        this.codResponse = codResponse;
    }

    public String getMsgResponse() {
        return msgResponse;
    }

    public void setMsgResponse(String msgResponse) {
        this.msgResponse = msgResponse;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "ConsultaResponseDTO{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", codResponse='" + codResponse + '\'' +
                ", msgResponse='" + msgResponse + '\'' +
                ", data=" + (data != null ? data.toString() : "null") +
                '}';
    }


}

