package com.wu.billersplus.connector.cashin.pago.efectivo.online.service;

import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wu.billersplus.framework.cdi.qualifiers.BillerPlusServiceBean;
import com.wu.billersplus.framework.configuration.PropertyHelper;
import com.wu.billersplus.framework.service.facade.AnalizarErrorService;

/**
 * Se deberá implementar aqui el análisis de datos de los códigos de error que
 * el biller devuelva. El objetivo es que el método retorne un TRUE si el error
 * es continuable. No es obligatorio implementar esta clase, pero es recomendado
 *
 */
@Stateless
@BillerPlusServiceBean
public class AnalizarErrorServiceImpl extends AnalizarErrorService {

	private static Logger logger = LoggerFactory.getLogger(AnalizarErrorServiceImpl.class);

	public AnalizarErrorServiceImpl() {
		super();
		logger.debug("Inicializando Servicio de Analisis de Errores");
	}

	@Override
	public Boolean analizarReversaErrorEsConstinuable(Integer codigoError, String descripcionError) {
		logger.debug("[Conector Codigo {}] Analizando errror de reversa con [codigo {}] y [descripcion {}]",
				PropertyHelper.getConnectorCode(), codigoError, descripcionError);
		if (codigoError == -1) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean analizarDirectaErrorEsConstinuable(Integer codigoError, String descripcionError) {
		logger.debug("[Conector Codigo {}] Analizando errror de directa con [codigo {}] y [descripcion {}]",
				PropertyHelper.getConnectorCode(), codigoError, descripcionError);
		if (codigoError == -1) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean analizarConsutaEstadoErrorEsContinuable(Integer codigoError, String descripcionError) {
		logger.debug("[Conector Codigo {}] Analizando errror de consultaEstado con [codigo {}] y [descripcion {}]",
				PropertyHelper.getConnectorCode(), codigoError, descripcionError);
		if (codigoError == -1) {
			return true;
		}
		return false;
	}

}
