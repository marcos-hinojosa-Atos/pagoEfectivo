package com.wu.billersplus.connector.cashin.pago.efectivo.online.entities;

import com.wu.billersplus.framework.configuration.PropertyHelper;

/** Clase que define las constantes del proyecto
 *
 * @author mtangari
 *
 */

public class ParametrosConector {

	public static final Integer LENGTH_2 = 2;
	public static final Integer LENGTH_8 = 8;
	public static final Integer LENGTH_10 = 10;
	public static final Integer LENGTH_11 = 11;
	public static final Integer LENGTH_13 = 13;
	public static final Integer LENGTH_16 = 16;
	public static final Integer LENGTH_21 = 21;
	public static final Integer LENGTH_24 = 24;

	public static final String OPERATION_CONSULTA = "C";
	public static final String OPERATION_DIRECTA  = "D";
	public static final String OPERATION_REVERSA  = "R";

	public static final String ID_OPERATION_OK = "0";

	public static final String RECARGA_CON_BARRA = "recarga.con.barra";

	public static final String TYPE_OPERATION = "CashIn";
	public static final String SECURITY_USER = "client.user";
	public static final String SECURITY_PASS = "client.pass";

	public static final String TOKEN_USER = "client.tokenUser";
	public static final String TOKEN_PASS = "client.tokenPass";
	public static final String TOKEN_GRANT_TYPE = "client.tokenGrantType";

	// EN CASO DE QUE UTILICEN TOKEN FIJO
	public static final String KEY = "client.key";
	public static final String KEY_TYPE = "client.keyType";

	public static final String PROXY_URL_CONSULT_PARAMETER = "client.url.consulta";
	public static final String PROXY_URL_DIRECT_PARAMETER = "client.url.directa";
	public static final String PROXY_URL_REVERT_PARAMETER = "client.url.reversa";
	public static final String PROXY_URL_TOKEN_PARAMETER = "client.url.token";

	public static final String AGREEMENT = "client.agreement";

	public static final String CHANNEL = "client.channel";

	public static final String SECRET_KEY = "client.secret.key";

	public static final String USER_AGENT = "client.useragent";
	public static final String PROXY_TIMEOUT_PROPERTY = "client.connectionTimeout";

	public static final String CONNECTOR_CODE = PropertyHelper.getConnectorCode();
	public static final String VERSION = "1.0.0.0";
}
