package com.wu.billersplus.connector.cashin.pago.efectivo.online.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.entities.MapMsgRestStandard;
import com.wu.billersplus.entities.BillersPlusRequest;
import com.wu.billersplus.entities.ConsultaDeudaRequest;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.wu.billersplus.connector.cashin.pago.efectivo.online.entities.ParametrosConector.OPERATION_CONSULTA;

public class ConsultaRequestDTO extends RequestDTO implements Serializable {
	private static Logger logger = LoggerFactory.getLogger(ConsultaRequestDTO.class);
	private static final long serialVersionUID = -7464996204109127897L;




	/*
        {
        “salePointCode”:”77510219”,
        “terminalNumber”:”PE0219”, BP
        “operationNumber”:”000125”
        “orderNumber”:”8832670”, CIP DeudaBusq2
        “agreementCode”:”0006”,
        “channel”:2,
        “ubigeo”:”010101”,
        “variable1”:””,
        “variable2”:””,
        “secretKey”:” ce695f7c-ccac-4562-8626-2f20bf5634d0”

    }


         */
	public String salePointCode;

	public String terminalNumber;

	public String orderNumber;

	public ConsultaRequestDTO(BillersPlusRequest request, Map<String, String> parametros) {
		super(request,parametros);
		this.setCodOperation(OPERATION_CONSULTA);
		this.setSalePointCode(request.getProductoUtilityCode());
		this.setTerminalNumber(request.getIdTerminal());
		this.setSequence(getSequence(request.getIdTransaccionFe()));
		this.setOperationNumber(getCodTRX(this.getSequence(), this.getMachine(), new DateTime()));
		this.setOrderNumber(request.getDeudaBus2c()); //CIP
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getTerminalNumber() {
		return terminalNumber;
	}

	public void setTerminalNumber(String terminalNumber) {
		this.terminalNumber = terminalNumber;
	}

	public String getSalePointCode() {
		return salePointCode;
	}

	public void setSalePointCode(String salePointCode) {
		this.salePointCode = salePointCode;
	}

}
