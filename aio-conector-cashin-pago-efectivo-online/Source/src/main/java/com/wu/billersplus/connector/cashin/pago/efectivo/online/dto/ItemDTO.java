package com.wu.billersplus.connector.cashin.pago.efectivo.online.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class ItemDTO implements Serializable{

	private static final long serialVersionUID = -6027300205719657617L;

	@NotNull(message = "{itemDTO.id_item.null}")
	@JsonProperty(value = "id_item" )
	private String idItem;

	@JsonProperty("cod_barra")
	private String codBarra;

	@NotNull(message = "{itemDTO.importe.null}")
	@JsonProperty(value = "importe")
	private String amount;

	@JsonProperty("monto_abierto")
	private String openAmount;

	@Size(max = 50 , message = "{itemDTO.texto_mostrar.size}")
	@NotNull(message = "{itemDTO.texto_mostrar.null}")
	@JsonProperty(value = "texto_mostrar")
	private String msgLook;

	@Size(max = 120 , message = "{itemDTO.texto_ticket_consulta.size}")
	@JsonProperty("texto_ticket_consulta")
	private String textoTicketConsulta;

	@JsonProperty("fecha_vencimiento")
	private String dateExpirate;

	public String getIdItem() {
		return idItem;
	}
	public void setIdItem(String idItem) {
		this.idItem = idItem;
	}
	public String getCodBarra() {
		return codBarra;
	}
	public void setCodBarra(String codBarra) {
		this.codBarra = codBarra;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getOpenAmount() {
		return openAmount;
	}
	public void setOpenAmount(String openAmount) {
		this.openAmount = openAmount;
	}
	public String getMsgLook() {
		return msgLook;
	}
	public void setMsgLook(String msgLook) {
		this.msgLook = msgLook;
	}
	public String getDateExpirate() {
		return dateExpirate;
	}
	public void setDateExpirate(String dateExpirate) {
		this.dateExpirate = dateExpirate;
	}
	public String getTextoTicketConsulta() {
		return textoTicketConsulta;
	}
	public void setTextoTicketConsulta(String textoTicketConsulta) {
		this.textoTicketConsulta = textoTicketConsulta;
	}
}
