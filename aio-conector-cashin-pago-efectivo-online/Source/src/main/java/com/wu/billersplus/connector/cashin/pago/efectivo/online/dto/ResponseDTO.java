package com.wu.billersplus.connector.cashin.pago.efectivo.online.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDTO extends TrazabilidadDTO implements Serializable{

	private static final long serialVersionUID = -7464996204109127897L;

	@NotNull(message = "{responseDTO.cod_respuesta.null}")
	@JsonProperty("cod_respuesta")
	private String codResponse;

	@Size(min = 1, message = "{responseDTO.texto_mostrar.size}")
	@NotNull(message = "{responseDTO.msg_respuesta.null}")
	@JsonProperty("msg_respuesta")
	private String msgResponse;

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

}
