package com.wu.billersplus.connector.cashin.pago.efectivo.online.service;

import java.util.Map;

import com.wu.billersplus.connector.cashin.pago.efectivo.online.dto.ConsultaRequestDTO;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.dtoUpgrade.ConsultaResponseDTO;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.dto.ReversaRequestDTO;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.entities.ParametrosConector;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.helpers.ResponseNotValidException;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.helpers.RestExecute;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.utils.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wu.billersplus.connector.cashin.pago.efectivo.online.dtoUpgrade.DirectaRequestDTO;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.dto.DirectaResponseDTO;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.dto.ReversaResponseDTO;
import com.wu.billersplus.connector.exceptions.ConectorException;
import com.wu.billersplus.connector.exceptions.ProxyException;
import com.wu.billersplus.framework.entities.BillerResponseWrapper;
import com.wu.billersplus.framework.service.ProxyService;
import com.wu.billersplus.framework.service.impl.ProxyServiceAbstract;

public class ProxyServiceImpl extends ProxyServiceAbstract {

    private static final Logger logger = LoggerFactory.getLogger(ProxyServiceImpl.class);
    private RestExecute restExecuteService;



	public ProxyServiceImpl() {
		super();
	}


    @Override
    public BillerResponseWrapper<?> consulta(Object reqConsulta) throws ConectorException
    {
        ConsultaResponseDTO respDTO;
        ConsultaRequestDTO consultaRequest = (ConsultaRequestDTO) reqConsulta;
        //logger.info("BillerResponseWrapper<?> consulta: PRE restExecuteService.sendConsulta(consultaRequest)", consultaRequest.toString());
        logger.info("EN PROXYSERVICEIMPL");
        try
        {
            respDTO = restExecuteService.sendConsulta(consultaRequest);
            logger.info("LUEGO DE sendConsulta");
            logger.info("TOSTRING: "+ respDTO.toString());
        } catch (ResponseNotValidException e) {
            ConsultaResponseDTO consultaResponseDTO = new ConsultaResponseDTO(e.getErrorDTO());
            logger.info(e.getMessage() + " - " + e.getErrorDTO() + " ==> Datos de request asociada ====> "  + consultaRequest);
            return new BillerResponseWrapper<ConsultaResponseDTO>(consultaResponseDTO, mapBillerTrace(reqConsulta), mapBillerTrace(consultaResponseDTO));
        } catch (Exception e) {
            logger.info("ACA LLEGA!!!");
			logger.error("Exception: ", e);
			throw new ConectorException(ErrorCode.ERROR_MSG_INVALID.getCodigo(), e.getMessage());
        }

        return new BillerResponseWrapper<ConsultaResponseDTO>(respDTO, mapBillerTrace(reqConsulta), mapBillerTrace(respDTO));
    }


    @Override
    public BillerResponseWrapper<?> directa(Object reqDirecta) throws ConectorException
    {
        DirectaResponseDTO respDTO;
        DirectaRequestDTO directaRequest = (DirectaRequestDTO) reqDirecta;
        try
        {
            respDTO = restExecuteService.sendDirecta(directaRequest);

        } catch (ResponseNotValidException e) {
            DirectaResponseDTO directaResponseDTO = new DirectaResponseDTO(e.getErrorDTO());
            logger.info(e.getMessage() + " - " + e.getErrorDTO() + " ==> Datos de request asociada ====> "  + directaRequest);
            return new BillerResponseWrapper<DirectaResponseDTO>(directaResponseDTO, mapBillerTrace(reqDirecta), mapBillerTrace(directaResponseDTO));
        } catch (Exception e) {
			logger.error("Exception: ", e);
			throw new ConectorException(ErrorCode.ERROR_MSG_INVALID.getCodigo(), e.getMessage());
        }

        return new BillerResponseWrapper<DirectaResponseDTO>(respDTO, mapBillerTrace(reqDirecta), mapBillerTrace(respDTO));
    }


    @Override
    public BillerResponseWrapper<?> reversa(Object request) throws ConectorException
    {
        ReversaRequestDTO reversaRequest = (ReversaRequestDTO) request;
        ReversaResponseDTO respDTO = null;
        try {

            respDTO = restExecuteService.sendReversa(reversaRequest);

        } catch (ResponseNotValidException e) {
            ReversaResponseDTO reversaResponseDTO = new ReversaResponseDTO(e.getErrorDTO());
            logger.info(e.getMessage() + " - " + e.getErrorDTO() + " ==> Datos de request asociada ====> "  + reversaRequest);
            return new BillerResponseWrapper<ReversaResponseDTO>(reversaResponseDTO, mapBillerTrace(request), mapBillerTrace(reversaResponseDTO));
        } catch (Exception e) {
			logger.error("Exception: ", e);
			throw new ConectorException(ErrorCode.ERROR_MSG_INVALID.getCodigo(), e.getMessage());
        }

        return new BillerResponseWrapper<ReversaResponseDTO>(respDTO, mapBillerTrace(request), mapBillerTrace(respDTO));
    }


    @Override
    public BillerResponseWrapper<?> consultaEstado(Object request) throws ConectorException
    {
        logger.warn("Operacion no implementada");
        return null;
    }


    //Clase que crea el cliente
    @Override
    public ProxyService createClient(Map<String, String> arguments) throws ProxyException
    {
        if (arguments.get(ParametrosConector.PROXY_URL_CONSULT_PARAMETER) == null
                || arguments.get(ParametrosConector.PROXY_URL_DIRECT_PARAMETER) == null) {
            logger.error("los datos de las url no esta correctos.");
            throw new ProxyException(ErrorCode.ERROR_PROXY_URL.getCodigo(), ProxyException.URL_INEXISTENTE);
        }

        //Si implementa token validar que los datos esten correctos.

        if (arguments.get(ParametrosConector.PROXY_URL_TOKEN_PARAMETER) != null
                && (arguments.get(ParametrosConector.TOKEN_USER) == null
                && arguments.get(ParametrosConector.TOKEN_PASS) == null)) {
            logger.error("los datos del token no estan completos.");
            logger.error(ParametrosConector.PROXY_URL_TOKEN_PARAMETER + " : " + arguments.get(ParametrosConector.PROXY_URL_TOKEN_PARAMETER));
            logger.error(ParametrosConector.TOKEN_USER + " : " + arguments.get(ParametrosConector.TOKEN_USER));
            logger.error(ParametrosConector.TOKEN_PASS + " : " + arguments.get(ParametrosConector.TOKEN_PASS));
            throw new ProxyException(ErrorCode.ERROR_PROXY_URL.getCodigo(), ProxyException.URL_INEXISTENTE);
        }

        try {
					restExecuteService = new RestExecute(	arguments.get(ParametrosConector.PROXY_URL_CONSULT_PARAMETER),
															arguments.get(ParametrosConector.PROXY_URL_DIRECT_PARAMETER),
															arguments.get(ParametrosConector.PROXY_URL_REVERT_PARAMETER),
					        								Integer.parseInt(arguments.get(ParametrosConector.PROXY_TIMEOUT_PROPERTY)),
					        								arguments.get(ParametrosConector.PROXY_URL_TOKEN_PARAMETER),
					        						        arguments.get(ParametrosConector.TOKEN_USER),
					        						        arguments.get(ParametrosConector.TOKEN_PASS),
					        						        arguments.get(ParametrosConector.TOKEN_GRANT_TYPE),
                                                            arguments.get(ParametrosConector.KEY),
                                                            arguments.get(ParametrosConector.KEY_TYPE),
                                                            arguments.get(ParametrosConector.USER_AGENT));

		} catch (Exception e) {
			logger.error("No se pudo crear el cliente RestExecute: {}", e);
            throw new ProxyException(ErrorCode.ERROR_PROXY_INVALID.getCodigo(), ProxyException.ERROR_CODIGO_CONECTOR );
		}

        return this;
    }

}



