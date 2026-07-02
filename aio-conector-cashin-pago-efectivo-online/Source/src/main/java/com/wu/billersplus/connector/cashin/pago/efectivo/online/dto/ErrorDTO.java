package com.wu.billersplus.connector.cashin.pago.efectivo.online.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorDTO {

    @JsonProperty("msg_respuesta")
    private String mensajeError;

    @JsonProperty("cod_respuesta")
    private String codigoError;

    public ErrorDTO() {
    }

    public ErrorDTO(String mensajeError, String codigoError) {
        this.mensajeError = mensajeError;
        this.codigoError = codigoError;
    }

    public String getMensajeError() {
        return mensajeError;
    }
    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }
    public String getCodigoError() {
        return codigoError;
    }
    public void setCodigoError(String codigoError) {
        this.codigoError = codigoError;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
                sb.append(" mensajeError:").append(mensajeError);
                sb.append(", codigoError: ").append(codigoError);
                return sb.toString();
    }
}
