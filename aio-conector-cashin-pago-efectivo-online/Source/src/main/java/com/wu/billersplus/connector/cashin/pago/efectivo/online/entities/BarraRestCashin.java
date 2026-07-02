package com.wu.billersplus.connector.cashin.pago.efectivo.online.entities;

import com.wu.billersplus.connector.cashin.pago.efectivo.online.dto.ItemDTO;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.utils.DateUtils;
import org.apache.commons.lang.StringUtils;

import static com.wu.billersplus.connector.cashin.pago.efectivo.online.entities.ParametrosConector.*;

public class BarraRestCashin {
	private String utility;
	private String idItem;
	private String openAmount;
	private String amount;
	private String dateJulian;
	private String filler;
	private String vowels;

	public BarraRestCashin(ItemDTO item, String utility, String parametroRecargaBarra) {
		this.utility = utility;
		this.idItem =  item.getIdItem();
		this.openAmount = "1";
		this.amount = item.getAmount();
		this.dateJulian = DateUtils.getJulianDate(DateUtils.getDate(item.getDateExpirate(), DateUtils.yyyyMMdd));
		this.filler = "0";
		this.vowels = parametroRecargaBarra;
	}

	public String getBarraRest(){
		StringBuffer barra = new StringBuffer();
		barra
		.append(StringUtils.leftPad(this.utility, LENGTH_8, '0'))
		.append(StringUtils.leftPad(this.idItem, LENGTH_21, '0'))
		.append(this.openAmount)
		.append(StringUtils.leftPad(((this.amount != null ? this.amount.trim().replace(",", ""):"").replace(".", "")), LENGTH_11, '0'))
		.append(this.dateJulian)
		.append(StringUtils.leftPad(this.filler, LENGTH_13, '0'))
		.append(this.vowels);

		return barra.toString();
	}

	public static String getCodBarraWithOutLetterO(String codBarra, String plusBarra){
		String barra = codBarra;
		if(codBarra.substring(codBarra.length()-1, codBarra.length()).equals(plusBarra)){
			barra = codBarra.substring(0, codBarra.length()-1); //obtiene la barra sin el la letra "O" al final
		}
		return barra;
	}


	public String getUtility() {
		return utility;
	}
	public void setUtility(String utility) {
		this.utility = utility;
	}
	public String getIdItem() {
		return idItem;
	}
	public void setIdItem(String idItem) {
		this.idItem = idItem;
	}
	public String getOpenAmount() {
		return openAmount;
	}
	public void setOpenAmount(String openAmount) {
		this.openAmount = openAmount;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDateJulian() {
		return dateJulian;
	}
	public void setDateJulian(String dateJulian) {
		this.dateJulian = dateJulian;
	}
	public String getFiller() {
		return filler;
	}
	public void setFiller(String filler) {
		this.filler = filler;
	}
	public String getVowels() {
		return vowels;
	}
	public void setVowels(String vowels) {
		this.vowels = vowels;
	}
}

