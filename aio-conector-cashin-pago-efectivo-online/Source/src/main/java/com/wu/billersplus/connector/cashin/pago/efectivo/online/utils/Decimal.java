package com.wu.billersplus.connector.cashin.pago.efectivo.online.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.commons.lang.StringUtils;

/**
 * Wrapper para valores de importes. Aplica el corrimiento de digitos para tomarlos como valor
 * entero.
 * @author FLOPEZ
 *
 */
public class Decimal {

	private BigDecimal importe;

	private static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_UP;
	private static final String SEPARADOR_DECIMAL = ".";

	public static final int SCALE_2DEC		= 2;
	public static final int SCALE_3DEC		= 3;
	public static final int SCALE_4DEC		= 4;
	public static final int DEFAULT_SCALE	= SCALE_2DEC;

	public Decimal() {

	}

	public Decimal(double importe) {

		this.importe = new BigDecimal(importe).setScale(DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
	}

	public Decimal(String importe) {

		this.importe = new BigDecimal(importe).setScale(DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
	}

	public Decimal (BigDecimal importe) {

		this.importe = importe.setScale(DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
	}

	public Decimal(String importe, int scale) {

		this.importe = new BigDecimal(importe).setScale(scale, DEFAULT_ROUNDING_MODE);
	}

	/**
	 * Retorna el valor en un bigdecimal.
	 * @return
	 */
	public BigDecimal toBigDecimal() {
		return importe;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return importe.toPlainString();
	}

	/**
	 * Formatea el importe entero ingresado a decimal, tomando los ultimos digitos
	 * como expresion decimal.
	 * Ej. 1200 -> 12.00
	 * @param importe
	 * @return
	 */
	public static Decimal format(String importe, int scale) {

		if (importe.indexOf(scale) >= 0) {
			throw new RuntimeException("Formato de importe invalido. Se espera numero entero.");
		}

		String impAux = importe;
		for (int i = impAux.length(); i < scale; i++) {
			impAux = "0" + impAux;
		}

		String decimales = impAux.substring(impAux.length() - scale, impAux.length());
		String entero = impAux.substring(0, impAux.length() - scale);

		return new Decimal(entero + SEPARADOR_DECIMAL + decimales);
	}

	/**
	 * Formatea el importe entero ingresado a decimal, tomando los ultimos dos digitos
	 * como expresion decimal.
	 * Ej. 1200 -> 12.00
	 * @param importe
	 * @return
	 */
	public static Decimal format(String importe) {
		return format(importe, DEFAULT_SCALE);
	}

	/**
	 * Formatea el importe entero ingresado a decimal, tomando los ultimos dos digitos
	 * como expresion decimal.
	 * Ej. 1200 -> 12.00
	 * @param importe
	 * @return
	 */
	public Decimal format() {
		return format(importe.toString(), DEFAULT_SCALE);
	}

	/**
	 * Desformatea el importe conviertiendolo a string sin separador decimal, tomando la cantidad
	 * de digitos pasados en la var. scale como decimales.
	 * Ej. 12.00 -> 1200
	 * @param importeConDecimales
	 * @param scale
	 * @return
	 */
	public static String unformat(BigDecimal importeConDecimales, int scale) {

		int indexPunto = importeConDecimales.toPlainString().indexOf(SEPARADOR_DECIMAL);
		String parteDecimal = "0";
		String parteEntera = "0";
		if (indexPunto >= 0 ) {
			parteDecimal = importeConDecimales.toPlainString().substring(indexPunto + 1);
			parteEntera = importeConDecimales.toPlainString().substring(0, indexPunto);
		}
		else {
			parteEntera = importeConDecimales.toPlainString();
		}

		if (parteDecimal.length() >= scale) {
			//si esd mas largo se trunca
			parteDecimal = parteDecimal.substring(0, scale);
		} else {
			parteDecimal = StringUtils.rightPad(parteDecimal, scale, '0');
			//rellenar con ceros
		}

		return parteEntera + parteDecimal;
	}

	/**
	 * Desformatea el importe conviertiendolo a string sin separador decimal, tomando los
	 * digitos DEFAULT_SCALE como decimales.
	 * Ej. 12.00 -> 1200
	 * @param importeConDecimales
	 * @return
	 */
	public static String unformat(BigDecimal importeConDecimales) {

		return unformat(importeConDecimales, DEFAULT_SCALE);
	}

	/**
	 * Desformatea el importe conviertiendolo a string sin separador decimal, tomando los ultimos dos
	 * digitos como decimales.
	 * Ej. 12.00 -> 1200
	 * @param scale
	 * @return
	 */
	public String unformat(int scale) {

		return unformat(importe, scale);
	}

	/**
	 * Desformatea el importe conviertiendolo a string sin separador decimal, tomando los ultimos dos
	 * digitos como decimales, utiliza la DEFAULT_SCALE.
	 * Ej. 12.00 -> 1200
	 * @return
	 */
	public String unformat() {

		return unformat(importe, DEFAULT_SCALE);
	}

}

