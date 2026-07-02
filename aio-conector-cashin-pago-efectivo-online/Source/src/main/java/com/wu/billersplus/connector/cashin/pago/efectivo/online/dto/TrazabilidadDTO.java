package com.wu.billersplus.connector.cashin.pago.efectivo.online.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class TrazabilidadDTO implements Serializable {

    private static final long serialVersionUID = 6336350114380133523L;

    @NotNull(message = "{trazabilidadDTO.tipo_operacion.null}")
    @JsonProperty("tipo_operacion")
    private String typeOperation;

    @NotNull(message = "{trazabilidadDTO.utility.null}")
    @JsonProperty("utility")
    private String utility;

    @NotNull(message = "{trazabilidadDTO.terminal.null}")
    @JsonProperty("terminal")
    private String machine;

    @NotNull(message = "{trazabilidadDTO.fecha.null}")
    @JsonProperty("fecha")
    private String date;

    @NotNull(message = "{trazabilidadDTO.hora.null}")
    @JsonProperty("hora")
    private String hour;

    @NotNull(message = "{trazabilidadDTO.cod_operacion.null}")
    @JsonProperty("cod_operacion")
    private String codOperation;

    @NotNull(message = "{trazabilidadDTO.ubigeo.null}")
    @JsonProperty("ubigeo")
    private String ubigeo;

    public String getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(String ubigeo){
         this.ubigeo = ubigeo;
    }

    public String getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(String typeOperation) {
        this.typeOperation = typeOperation;
    }
    public String getUtility() {
        return utility;
    }
    public void setUtility(String utility) {
        this.utility = utility;
    }
    public String getMachine() {
        return machine;
    }
    public void setMachine(String machine) {
        this.machine = machine;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getHour() {
        return hour;
    }
    public void setHour(String hour) {
        this.hour = hour;
    }
    public String getCodOperation() {
        return codOperation;
    }
    public void setCodOperation(String codOperation) {
        this.codOperation = codOperation;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Trazabilidad:");
        sb.append(" utility: ").append(utility);
        sb.append(", terminal: ").append(machine);
        sb.append(", fecha: ").append(date);
        sb.append(", hora: ").append(hour);
        sb.append(", cod_operacion: ").append(codOperation);
        return sb.toString();
    }


}
