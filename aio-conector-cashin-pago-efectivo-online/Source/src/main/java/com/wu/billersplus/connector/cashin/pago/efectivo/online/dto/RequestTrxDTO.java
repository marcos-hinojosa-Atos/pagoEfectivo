package com.wu.billersplus.connector.cashin.pago.efectivo.online.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.entities.ParametrosConector;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.utils.DateUtils;
import com.wu.billersplus.entities.BillersPlusRequest;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

import java.util.Map;

public class RequestTrxDTO extends RequestDTO{

    private static final long serialVersionUID = 1782501156768105806L;

    @JsonProperty("cod_trx")
    private String codTrx;

    @JsonProperty("importe")
    private String amount;

    @JsonProperty("secuencia")
    private String sequence;

    public RequestTrxDTO(BillersPlusRequest request, Map<String, String> parametros) {
        super(request, parametros);
    }

    public String getSequence(Long sequence){
        String value, valueReturn = "";
        if(sequence != null){
            value = String.valueOf(sequence);
            valueReturn = value.substring(value.length()-4, value.length());
        }
        return valueReturn;
    }

    public String getCodTRX(String sequence, String idMachine, DateTime dateTimeZone){
        String value = "";
        value = idMachine+ DateUtils.getDate(dateTimeZone, DateUtils.yyyyMMddHHmmss)+sequence;
        return StringUtils.leftPad(value, ParametrosConector.LENGTH_24, '0');
    }

    public String getOperationNumber() {
        return codTrx;
    }
    public void setOperationNumber(String operationNumber) {
        this.codTrx = operationNumber;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getSequence() {
        return sequence;
    }
    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
