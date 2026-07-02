package com.wu.billersplus.connector.cashin.pago.efectivo.online.service;

import com.wu.billersplus.connector.cashin.pago.efectivo.online.dto.DirectaRequestDTO;
import com.wu.billersplus.connector.entities.ConnectorRequest;
import com.wu.billersplus.connector.entities.ConnectorResponse;
import com.wu.billersplus.connector.exceptions.ConectorException;
import com.wu.billersplus.entities.PagoResponse;
import com.wu.billersplus.framework.cdi.qualifiers.BillerPlusServiceBean;
import com.wu.billersplus.framework.configuration.PropertyHandler;
import com.wu.billersplus.framework.configuration.PropertyHelper;
import com.wu.billersplus.framework.entities.BillerResponseWrapper;
import com.wu.billersplus.framework.service.ActualizarConectorService;
import com.wu.billersplus.framework.service.ProxyHelperService;
import com.wu.billersplus.framework.service.ProxyService;
import com.wu.billersplus.framework.service.facade.AnalizarErrorService;
import com.wu.billersplus.framework.service.impl.EchoServiceAbstract;
import com.wu.billersplus.framework.transformer.BillerPlusTransformerService;

import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * Servicio que implementa la operación de ECHO del servicio. En caso de una
 * respuesta satisfactoria del cliente, se debe retornar la cantidad de
 * milisegundos que tardó el servicio en contestar. En caso de una error con
 * severidad no crítica se debe devolver el valor de ERROR_NO_CRITICO, caso de
 * error crítico se debería deolver el vlaor de ERROR_CRITICO. En caso de un
 * error de configuracion devolver ERROR_CONFIGURACION
 *
 * A modo de sugerencia, ya se tiene importado en el proyecto JodaTime, por lo
 * que hacer la diferencia de tiempo es mínimo con esta librería
 *
 */
@Stateless
@BillerPlusServiceBean
public class EchoServiceImpl extends EchoServiceAbstract {

	private static final Logger logger = LoggerFactory.getLogger(EchoServiceAbstract.class);
	private static final String ECHO_FILE_REQUEST = "requestEcho.json.file.path";

	private static final Integer ERROR_CODE = 161;


	public EchoServiceImpl(ProxyHelperService pHelper, BillerPlusTransformerService transformer,
			AnalizarErrorService aErrorService, ActualizarConectorService actualizarService) {
		super(pHelper, transformer, aErrorService, actualizarService);
	}

	public EchoServiceImpl() {
		super();
	}

	@Override
	public Long echoBiller(ProxyService client, String productoUtilityCode, String obj) throws ConectorException {
		if (PropertyHandler.getPropertyString(ECHO_FILE_REQUEST) == null) {
			logger.error("No se seteo valor para la property {} en el conector", ECHO_FILE_REQUEST);
			return ERROR_CONFIGURACION;
		}

		logger.debug("Se buscará el request de echo con el archivo {}",
				PropertyHandler.getPropertyString(ECHO_FILE_REQUEST));

		InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream(PropertyHandler.getPropertyString(ECHO_FILE_REQUEST));
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

		String requestJson = null;
		try {
			requestJson = IOUtils.toString(reader);
		} catch (IOException e1) {
			logger.error("No existe el archivo {} que contiene al request de ECHO",
					PropertyHandler.getPropertyString(ECHO_FILE_REQUEST));
			return ERROR_CONFIGURACION;
		}

		if (requestJson.isEmpty()) {
			logger.error("No existe el archivo {} que contiene al request de ECHO",
					PropertyHandler.getPropertyString(ECHO_FILE_REQUEST));
			return ERROR_CONFIGURACION;
		}

		ConnectorRequest readValue;
		try {
			readValue = mapper.readValue(requestJson, ConnectorRequest.class);
			readValue.getBillersPlusRequest().setProductoUtilityCode(productoUtilityCode);
		} catch (IOException e) {
			logger.error("Existio una excepcion en la operacion de echo al deserializar el request");
			return ERROR_CONFIGURACION;
		}

		DirectaRequestDTO directaRequest = (DirectaRequestDTO) transformer.getDirectaRequest(readValue);

		DateTime initTime = DateTime.now();
		BillerResponseWrapper<?> billerResponse = client.directa(directaRequest);
		DateTime endTime = DateTime.now();
		long durationMillis = new Interval(initTime, endTime).toDurationMillis();

		ConnectorResponse<PagoResponse> consultaDeuda = transformer.getResponseForDirecta(readValue, new PagoResponse(),
				billerResponse);

		logger.debug("[Conector Codigo {}] la operación ECHO tardó {}ms devolvió un código de error {}",
				PropertyHelper.getConnectorCode(), durationMillis);
		if (consultaDeuda.getResponse().getCodigoError().equals(ERROR_CODE)) {
			return durationMillis;
		} else if (consultaDeuda.getResponse().getCodigoError() == 1) {
			logger.debug("[Conector Codigo {}] la operación ECHO retornará un error no critico",
					PropertyHelper.getConnectorCode(), durationMillis);
			return ERROR_NO_CRITICO;
		} else {
			logger.debug("[Conector Codigo {}] la operación ECHO retornará un error critico",
					PropertyHelper.getConnectorCode());
			return ERROR_CRITICO;
		}
	}

}
