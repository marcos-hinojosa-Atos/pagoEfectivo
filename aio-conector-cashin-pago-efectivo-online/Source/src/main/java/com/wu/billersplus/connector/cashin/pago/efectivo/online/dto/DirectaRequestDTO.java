package com.wu.billersplus.connector.cashin.pago.efectivo.online.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.entities.BarraRestCashin;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.entities.DeudaParameters;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.entities.ParametrosConector;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.utils.DateUtils;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.utils.Decimal;
import com.wu.billersplus.entities.BillersPlusDeuda;
import com.wu.billersplus.entities.BillersPlusRequest;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.Map;

public class DirectaRequestDTO extends RequestTrxDTO implements Serializable {
	private static final long serialVersionUID = -8105627170719299907L;

	@JsonProperty("id_item")
	private String idItem;

	@JsonProperty("cod_cliente")
	private String codClient;

	@JsonProperty("cod_barra")
	private String codBarra;

	public DirectaRequestDTO(BillersPlusRequest request, BillersPlusDeuda requestDeuda, Map<String, String> parametros) {
		super(request,parametros);

		DateTime dateTimeZone = DateUtils.getDataTimeZone(request.getFechaHoraFe(), request.getHusoHorario());
		this.setCodOperation(ParametrosConector.OPERATION_DIRECTA);
		this.setCodBarra(BarraRestCashin.getCodBarraWithOutLetterO(request.getCodigoBarra(), parametros.get(ParametrosConector.RECARGA_CON_BARRA)));
		this.setIdItem(requestDeuda != null && requestDeuda.getParametrosDeuda() != null
				? requestDeuda.getParametrosDeuda().get(DeudaParameters.PARAM_ITEM)
				: null);
		this.setAmount(Decimal.unformat(request.getImporte(), Decimal.SCALE_2DEC));
		this.setCodClient(requestDeuda != null && requestDeuda.getParametrosDeuda() != null
				? requestDeuda.getParametrosDeuda().get(DeudaParameters.PARAM_COD_CLIENT)
				: null);
		this.setSequence(getSequence(request.getIdTransaccionFe()));
		this.setOperationNumber(getCodTRX(this.getSequence(), this.getMachine(), dateTimeZone));
	}

	public String getIdItem() {
		return idItem;
	}
	public void setIdItem(String idItem) {
		this.idItem = idItem;
	}
	public String getCodClient() {
		return codClient;
	}
	public void setCodClient(String codClient) {
		this.codClient = codClient;
	}
	public String getCodBarra() {
		return codBarra;
	}
	public void setCodBarra(String codBarra) {
		this.codBarra = codBarra;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(" idItem: ").append(idItem);
		sb.append(" codCliente: ").append(codClient);
		sb.append(" codBarra: ").append(codBarra);
		return sb.toString();
	}
}
