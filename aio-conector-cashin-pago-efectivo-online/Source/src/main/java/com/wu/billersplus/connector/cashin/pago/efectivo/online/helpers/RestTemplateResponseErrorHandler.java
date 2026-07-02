package com.wu.billersplus.connector.cashin.pago.efectivo.online.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.dto.ErrorDTO;

import org.apache.log4j.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.*;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {


    private static Logger logger = Logger.getLogger(RestTemplateResponseErrorHandler.class);

    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        return !httpResponse.getStatusCode().equals(HttpStatus.OK);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        String mensaje = "Valores de error OK";
        ErrorDTO errorDTO = null;
        StringBuffer content = null;

        try {
            logger.info(">>>>>>  PROCESAR INPUT STREAM");
            content = processInputStream(httpResponse.getBody());
            String response = content.toString();
            ObjectMapper mapper = new ObjectMapper();
            errorDTO = mapper.readValue(response, ErrorDTO.class);

            if (errorDTO == null || errorDTO.getMensajeError() == null || errorDTO.getCodigoError() == null) {
                throw new ResponseNotValidException("Los campos recibidos no son los que se esperaban");
            }
        } catch(ResponseNotValidException ex){
            mensaje = ex.getMessage();
            errorDTO = new ErrorDTO("Error Generico, consultar logs para mas detalles", "9999");
        } catch (Exception ex) {
            logger.info(">>>>>>  ERROR AL PROCESAR INPUT STREAM, SE CREA MENSAJE GENERICO");
            mensaje = "No se recibio la respuesta esperada";
            errorDTO = new ErrorDTO("Error Generico, consultar logs para mas detalles", "9999");
            // ex.printStackTrace();
        }
        throw new ResponseNotValidException(mensaje,errorDTO);
    }

    private static StringBuffer processInputStream(InputStream in) throws Exception {

        String line = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuffer response = new StringBuffer();
        while (( line = reader.readLine()) != null) {
            response.append(line);
        }
        in.close();

        return response;
    }
}
