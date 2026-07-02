package com.wu.billersplus.connector.cashin.pago.efectivo.online.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class ResponseTrxDTO extends ResponseDTO{

    private static final long serialVersionUID = -520959109914840418L;

    @NotNull(message = "{responseTrxDTO.secuencia.null}")
    @JsonProperty("secuencia")
    private String sequence;

    @NotNull(message = "{responseTrxDTO.cod_trx.null}")
    @JsonProperty("cod_trx")
    private String codTrx;

    public String getSequence() {
        return sequence;
    }
    public void setSequence(String sequence) {
        this.sequence = sequence;
    }
    public String getCodTrx() {
        return codTrx;
    }
    public void setCodTrx(String codTrx) {
        this.codTrx = codTrx;
    }
}
