package com.wu.billersplus.connector.cashin.pago.efectivo.online.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.entities.ParametrosConector;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.utils.DateUtils;
import com.wu.billersplus.entities.BillersPlusRequest;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.Map;

import static com.wu.billersplus.connector.cashin.pago.efectivo.online.entities.ParametrosConector.*;
import static com.wu.billersplus.connector.cashin.pago.efectivo.online.entities.ParametrosConector.TYPE_OPERATION;

public class RequestDTO extends TrazabilidadDTO implements  Serializable{

	private static final long serialVersionUID = -7464996204109127897L;

	@JsonProperty("user")
	private String user;

	@JsonProperty("password")
	private String password;

	@JsonProperty("cod_trx")
	private String operationNumber;

	@JsonProperty("secuencia")
	private String sequence;
/*
	{
	“salePointCode”:”77510219”, // datos debe ser extraido de la base de datos, sin embargo NO se puede. Solo trabajar con los datos que llegan
	“terminalNumber”:”PE0219”, //TrazabilidadDTO.machine
	“operationNumber”:”000125”
	“orderNumber”:”8832670”, //CIP
	“agreementCode”:”0006”, //BP
	“channel”:2, //BP
	“ubigeo”:”010101” //OK
	“variable1”:””, //OK
	“variable2”:””, //OK
	“secretKey”:” ce695f7c-ccac-4562-8626-2f20bf5634d0” //BP
	}

 */
	public RequestDTO(BillersPlusRequest request, Map<String, String> parametros) {
		DateTime dateTimeZone = DateUtils.getDataTimeZone(request.getFechaHoraFe(), request.getHusoHorario());
		this.setTypeOperation(TYPE_OPERATION);
		this.setUtility(request.getProductoUtilityCode());
		this.setDate(DateUtils.getDate(dateTimeZone, DateUtils.yyyyMMdd));
		this.setHour(DateUtils.getDate(dateTimeZone, DateUtils.HHmmss));
		this.setMachine(request.getIdTerminal());
		this.setUser(parametros.get(SECURITY_USER));
		this.setPassword(parametros.get(SECURITY_PASS));
		//Parametros de BP
		this.setAgreement(parametros.get(AGREEMENT));
		this.setChannel(parametros.get(CHANNEL));
		this.setSecretKey(parametros.get(SECRET_KEY));
		//Parametros internos
		this.setUbigeo("010101");
		this.setVariable1("");
		this.setVariable2("");
		//this.setSalePointCode(request.getSalePointCode());

		// operationNumber
		this.setSequence(getSequence(request.getIdTransaccionFe()));
		this.setOperationNumber(getCodTRX(this.getSequence(), this.getMachine(), dateTimeZone));

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
		return operationNumber;
	}
	public void setOperationNumber(String operationNumber) {
		this.operationNumber = operationNumber;
	}

	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}



	private void setVariable2(String s) {
	}

	private void setVariable1(String s) {
	}

	private void setSecretKey(String s) {
	}

	private void setChannel(String s) {
	}

	private void setAgreement(String s) {
	}

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
