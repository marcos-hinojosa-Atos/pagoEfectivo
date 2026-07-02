package com.wu.billersplus.connector.cashin.pago.efectivo.online.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsultaResponseDTO extends ResponseDTO implements Serializable {

	private static final long serialVersionUID = 8704251296520068997L;

	@NotNull(message = "{consultaResponseDTO.cod_cliente.null}")
	@JsonProperty(value ="cod_cliente")
	private String codClient;

	@NotNull(message = "{consultaResponseDTO.nom_cliente.null}")
	@JsonProperty("nom_cliente")
	private String nameClient;

	@NotNull(message = "{consultaResponseDTO.items.null}")
	@Valid
	@JsonProperty("items")
	private List<ItemDTO> listItem;

	public ConsultaResponseDTO() {
	}

	public ConsultaResponseDTO(ErrorDTO errorDTO) {
		this.setCodResponse(errorDTO.getCodigoError());
		this.setMsgResponse(errorDTO.getMensajeError());
	}

	public String getCodClient() {
		return codClient;
	}
	public void setCodClient(String codClient) {
		this.codClient = codClient;
	}
	public String getNameClient() {
		return nameClient;
	}
	public void setNameClient(String nameClient) {
		this.nameClient = nameClient;
	}
	public List<ItemDTO> getListItem() {
		return listItem;
	}
	public void setListItem(List<ItemDTO> listItem) {
		this.listItem = listItem;
	}

}