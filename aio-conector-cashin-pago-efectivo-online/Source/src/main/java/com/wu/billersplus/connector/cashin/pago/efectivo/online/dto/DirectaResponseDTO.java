package com.wu.billersplus.connector.cashin.pago.efectivo.online.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Size;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DirectaResponseDTO extends ResponseTrxDTO implements Serializable {

	private static final long serialVersionUID = 5436982577599207374L;

	@Size(max = 2000 , message = "{directaResponseDTO.texto_ticket.size}")
	@JsonProperty("texto_ticket")
	private String msgTicket;

	public DirectaResponseDTO() {
	}

	public DirectaResponseDTO(ErrorDTO errorDTO) {
		this.setCodResponse(errorDTO.getCodigoError());
		this.setMsgResponse(errorDTO.getMensajeError());
    }

    public String getMsgTicket() {
		return msgTicket;
	}
	public void setMsgTicket(String msgTicket) {
		this.msgTicket = msgTicket;
	}

}
