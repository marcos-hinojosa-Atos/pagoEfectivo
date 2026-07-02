package com.wu.billersplus.connector.cashin.pago.efectivo.online.transformers;


import ar.com.sepsa.commons.model.biller.Connector;
import ar.com.sepsa.commons.model.biller.ConnectorParameter;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.dto.*;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.entities.BarraRestCashin;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.utils.DateUtils;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.utils.ErrorCode;
import com.wu.billersplus.connector.entities.ConnectorRequest;
import com.wu.billersplus.connector.entities.ConnectorResponse;
import com.wu.billersplus.entities.*;
import com.wu.billersplus.framework.cdi.qualifiers.BillerPlusServiceBean;
import com.wu.billersplus.framework.entities.BillerResponseWrapper;
import com.wu.billersplus.framework.service.ParametrosConectorService;
import com.wu.billersplus.framework.transformer.BillerPlusTransformerService;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wu.billersplus.connector.cashin.pago.efectivo.online.entities.DeudaParameters.*;
import static com.wu.billersplus.connector.cashin.pago.efectivo.online.entities.ParametrosConector.*;


@Stateless
@BillerPlusServiceBean
public class BillerTransformer implements BillerPlusTransformerService {
	
	private static final Logger logger = LoggerFactory.getLogger(BillerTransformer.class);
	@Inject
	@BillerPlusServiceBean
	private ParametrosConectorService parametrosConector;
	Map<String, String> parametros;	


	private Map<String, String> getParametrosConector(BillersPlusRequest request, Connector connector)
	{
		logger.info("** OBTENIENDO PARAMETROS DEL CONECTOR [{}]  **",request.getProductoUtilityCode());
		if(connector != null && connector.getParameters() != null && !connector.getParameters().isEmpty()) {
			Map<String,String> parametros = new HashMap<>();
			for(ConnectorParameter cp : connector.getParameters()) {
				parametros.put(cp.getCode(),cp.getValue());
			}
			logger.info("Se obtuvieron los parametros del conector [{}] desde el payload",request.getProductoUtilityCode());
			return parametros;
		}
		return parametrosConector.getParametrosConectorPorBiller(request.getProductoUtilityCode());
	}

	
	@Override
	public Object getDeudaRequest(ConsultaDeudaRequest request)
	{
		parametros = getParametrosConector(request,request.getConnector());
		logger.info("Consulta [CodigoProducto = {}] Campos de Busqueda Dinamicos [{}] || Estaticos: [ DeudaBus1 {} DeudaBus2 {} ]",
				request.getProductoUtilityCode(), request.getCamposBusqueda(), request.getDeudaBus1c(), request.getDeudaBus2c());
		return new ConsultaRequestDTO(request,parametros);
	}

	
	@Override
	public ConnectorResponse<ConsultaDeudaResponse> getResponseForConsultaDeuda(ConsultaDeudaRequest request,
			ConsultaDeudaResponse response, BillerResponseWrapper<?> consultaResponse) 
	{
		logger.info("[Conector Codigo {}] Transformando Response de CONSULTA con [CodigoProducto {}]",CONNECTOR_CODE, response.getProductoUtilityCode());
		if (consultaResponse == null || consultaResponse.getResponseBiller() == null) {
			logger.error("La respuesta de la consulta es nula");
			return new ConnectorResponse<ConsultaDeudaResponse>(response, null, null, null);
		}
		if (response.getCodigoError() == ErrorCode.ERROR_MSG_INVALID.getCodigo()) {
			logger.error("Se obtuvo un error al recibir la respuesta [{}]", response.getCodigoError());
			return new ConnectorResponse<ConsultaDeudaResponse>(response, null, null, null);
		}

		com.wu.billersplus.connector.cashin.pago.efectivo.online.dtoUpgrade.ConsultaResponseDTO responseDTO = (com.wu.billersplus.connector.cashin.pago.efectivo.online.dtoUpgrade.ConsultaResponseDTO) consultaResponse.getResponseBiller();
		if(!StringUtils.equalsIgnoreCase(ID_OPERATION_OK, responseDTO.getCodResponse())){
			//response.setCodigoError(Integer.valueOf(responseDTO.getCodResponse()));
			response.setCodigoError(1);
			//response.setDescripcionError("ENT: ".concat(responseDTO.getCodResponse()).concat(" ").concat(responseDTO.getMsgResponse()));
			response.setDescripcionError("ENT: ".concat("1").concat(" ").concat("MSG_RESPONSE"));
			logger.info("Emtrando ConnectorResponse: responseDTO.getMsgResponse(): " + responseDTO.getMsgResponse());
			return new ConnectorResponse<ConsultaDeudaResponse>( response, null, consultaResponse.getRequestBillerTrace(), consultaResponse.getResponseBillerTrace());
		}

		/*
		if(responseDTO.getListItem() == null || responseDTO.getListItem().isEmpty()){//validate list for deb client.
			response.setCodigoError(1);
			response.setDescripcionError("El cliente no posee deudas.");
			return new ConnectorResponse<ConsultaDeudaResponse>( response, null, consultaResponse.getRequestBillerTrace(), consultaResponse.getResponseBillerTrace());
		}

		try
		{
			response.setFechaHoraFE(new DateTime());
			response.setCodigoError(0);
			parametros = getParametrosConector(request,request.getConnector());
			
			List<BillersPlusDeuda> list = new ArrayList<BillersPlusDeuda>();
			for(ItemDTO item : responseDTO.getListItem()){
				BillersPlusDeuda deuda = new BillersPlusDeuda();
				deuda.setDeudaImp1(getAmout(item.getAmount()));
				deuda.setDeudaDato1(responseDTO.getNameClient());
				deuda.setDeudaTicket(item.getTextoTicketConsulta() != null ? item.getTextoTicketConsulta() : null);
				deuda.setDeudaVenc1(DateUtils.getDate(item.getDateExpirate(), DateUtils.yyyyMMdd));
				deuda.setDeudaMsg1(item.getMsgLook());
				deuda.setCodigoBarra(item.getCodBarra() != null && !item.getCodBarra().isEmpty()
						? item.getCodBarra()+ parametros.get(RECARGA_CON_BARRA)  // La barra la provee la entidad
						: new BarraRestCashin(item, request.getProductoUtilityCode(), parametros.get(RECARGA_CON_BARRA)).getBarraRest()); // Armamos la barra
				deuda.setIdProducto(responseDTO.getUtility());
				deuda.setCodigoMoneda(request.getCodigoMoneda() != null ? request.getCodigoMoneda() : "USD");

				Map<String, String>  parametrosDeuda = new HashMap<String, String>();
				parametrosDeuda.put(PARAM_ITEM, String.valueOf(item.getIdItem()));
				parametrosDeuda.put(PARAM_COD_CLIENT, responseDTO.getCodClient());
				deuda.setParametrosDeuda(parametrosDeuda);
				list.add(deuda);
			}
			response.setDeudas(list);
			
			
		}catch(Exception e){
			logger.error("Error al parsear la rta de la entidad. ", e);
			response.setCodigoError(99);
			response.setDescripcionError("Error al parsear la rta de la entidad: " + e.getMessage());
		}
		*/
		return new ConnectorResponse<ConsultaDeudaResponse>( response, null, consultaResponse.getRequestBillerTrace(), consultaResponse.getResponseBillerTrace());
	}
	
	private BigDecimal getAmout(String amount){
		BigDecimal valor = null;
		if(amount != null && !amount.isEmpty()){
			if (amount.length()>2){
				valor = new BigDecimal(amount.substring(0, amount.length()-2)+"."+amount.substring(amount.length()-2, amount.length()));
			}else{
				valor = new BigDecimal(amount);
			}
		}
		return valor;
	}	

	
	@Override
	public Object getDirectaRequest(ConnectorRequest connectorRequest) 
	{
		BillersPlusRequest request = connectorRequest.getBillersPlusRequest();
		BillersPlusDeuda requestDeuda = connectorRequest.getDeuda();
		logger.debug("Transformando request de DIRECTA con [CodigoProducto = {}]", request.getProductoUtilityCode());
		parametros = getParametrosConector(request,connectorRequest.getConnector());
		DirectaRequestDTO directRequestDTO = new DirectaRequestDTO(request,requestDeuda, parametros);
		Map<String, String> dataAdic = new HashMap<String, String>();
		dataAdic.put(PARAM_SEQUENCE, directRequestDTO.getSequence());
		dataAdic.put(PARAM_COD_TRX, directRequestDTO.getOperationNumber());
		connectorRequest.setDatosAdicionalesAnulacion(dataAdic);
		return directRequestDTO;
	}

	
	@Override
	public ConnectorResponse<PagoResponse> getResponseForDirecta(ConnectorRequest req,PagoResponse pago, BillerResponseWrapper<?> directa) 
	{
		ConnectorResponse<PagoResponse> cResponse = null;
		Map<String, String> datoAdicional;
		if (directa == null) {
			logger.warn("La respuesta de la directa es nula");
			datoAdicional = req.getDatosAdicionalesAnulacion();
			cResponse = new ConnectorResponse<PagoResponse>(pago, null, null, null);
			if(datoAdicional != null && datoAdicional.get(PARAM_SEQUENCE) != null){
				cResponse.setDatosAdicionalesDirecta(datoAdicional);
			}
			return cResponse;
		}
		if (directa.getResponseBiller() == null) {
			logger.warn("La respuesta wrappeada del biller es nula");
			return new ConnectorResponse<PagoResponse>(pago, req.getDatosAdicionalesAnulacion(),directa.getRequestBillerTrace(), directa.getResponseBillerTrace());
		}
		DirectaResponseDTO responseDto = (DirectaResponseDTO) directa.getResponseBiller();
		// Seteo el pago con el codigo de error y el msg
		if(responseDto != null){
			logger.debug("Codigo respuesta de directa: "+responseDto.getCodResponse());
			pago.setCodigoError(Integer.parseInt(responseDto.getCodResponse()));
		    pago.setDeudaTicket(responseDto.getMsgTicket());
			pago.setDescripcionError("ENT: ".concat(responseDto.getCodResponse()).concat(" ").concat(responseDto.getMsgResponse()));
		}
		logger.debug("Transformando respuesta de DIRECTA con [CodigoProducto = {}]", pago.getProductoUtilityCode());
		//fields for revert
		datoAdicional = req.getDatosAdicionalesAnulacion();
		if(datoAdicional == null || (datoAdicional != null 	&& datoAdicional.get(PARAM_SEQUENCE) == null && datoAdicional.get(PARAM_COD_TRX) == null)){
			datoAdicional = new HashMap<String, String>();
			datoAdicional.put(PARAM_SEQUENCE, responseDto.getSequence());
			datoAdicional.put(PARAM_COD_TRX, responseDto.getCodTrx());
		}else{
			datoAdicional.put(PARAM_SEQUENCE, datoAdicional.get(PARAM_SEQUENCE));
			datoAdicional.put(PARAM_COD_TRX, datoAdicional.get(PARAM_COD_TRX));
		}
		req.setDatosAdicionalesAnulacion(datoAdicional);
		logger.info("[Conector Codigo {}] Transfromando Response de DIRECTA con [CodigoProducto {}]",CONNECTOR_CODE, pago.getProductoUtilityCode());
		return new ConnectorResponse<PagoResponse>(pago,datoAdicional,directa.getRequestBillerTrace(),directa.getResponseBillerTrace());
	}

	
	@Override
	public Object getReversaRequest(ConnectorRequest connectorRequest) 
	{
		BillersPlusRequest request = connectorRequest.getBillersPlusRequest();
		Map<String, String> dataAdic = connectorRequest.getDatosAdicionalesAnulacion();
		parametros = getParametrosConector(request,connectorRequest.getConnector());
		logger.debug("Transformando request de REVERSA con  [CodigoProducto = {}]",request.getProductoUtilityCode());
		return new ReversaRequestDTO( request, dataAdic,parametros);
	}

	
	@Override
	public ConnectorResponse<PagoResponse> getResponseForReversa(ConnectorRequest req, PagoResponse pago, BillerResponseWrapper<?> reversa) 
	{

		if (reversa == null) {
			logger.warn("La respuesta de la reversa es nula");
			return new ConnectorResponse<PagoResponse>(pago, null, null, null);
		}
		if (reversa.getResponseBiller() == null) {
			logger.warn("La respuesta wrappeada del biller es nula");
			return new ConnectorResponse<PagoResponse>(pago, null, reversa.getRequestBillerTrace(),	reversa.getResponseBillerTrace());
		}
		ReversaResponseDTO response = (ReversaResponseDTO) reversa.getResponseBiller();
		if(response != null){
			pago.setCodigoError(Integer.parseInt(response.getCodResponse()));
			pago.setDescripcionError(response.getMsgResponse());
		}
		logger.info("[Conector Codigo {}] Transfromando Response de REVERSA con [CodigoProducto {}]",CONNECTOR_CODE, pago.getProductoUtilityCode());
		return new ConnectorResponse<PagoResponse>(pago, new HashMap<String, String>(), reversa.getRequestBillerTrace(),reversa.getRequestBillerTrace());
	}

	
	@Override
	public Object getConsultaEstadoRequest(ConnectorRequest request) 
	{
		logger.warn("Operacion getConsultaEstadoRequest no implementada");
		return null;
	}

	
	@Override
	public ConnectorResponse<PagoResponse> getResponseForConsultaEstado(ConnectorRequest arg0, PagoResponse arg1,BillerResponseWrapper<?> arg2) 
	{
		logger.warn("Operacion getResponseForConsultaEstado no implementada");		
		return null;
	}

}
