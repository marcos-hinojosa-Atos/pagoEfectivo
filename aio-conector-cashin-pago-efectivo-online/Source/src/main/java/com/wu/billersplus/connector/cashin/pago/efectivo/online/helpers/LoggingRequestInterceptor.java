package com.wu.billersplus.connector.cashin.pago.efectivo.online.helpers;

import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.log4j.Logger;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.wu.billersplus.connector.cashin.pago.efectivo.online.entities.ParametrosConector.*;

/**
 *
 * @author ogagli
 *
 */
public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {

    final static Logger logger = Logger.getLogger(LoggingRequestInterceptor.class);

    /**
     *
     */
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        traceRequest(request, body);
        ClientHttpResponse response = null;
        try {
            response = execution.execute(request, body);
            traceResponse(response);
        } catch (HttpHostConnectException e) {
            logger.error(e);
            throw e;
        } catch (ConnectTimeoutException e) {
            logger.error(e);
            throw e;
        } catch (IOException e) {
            logger.error(e);
            throw e;
        } catch (Exception e) {
            logger.error(e);
            throw new IOException(e.getCause().toString());
        }
        return response;
    }

    /**
     *
     * @param request
     * @param body
     * @throws IOException
     */
    private void traceRequest(HttpRequest request, byte[] body) throws IOException {
        logger.info("===========================request begin================================================");
        logger.info("Version : " + VERSION + " -- Codigo Conector : " + CONNECTOR_CODE);
        logger.info( request.getMethod() + " URI : " + request.getURI());
        logger.info("Headers : " + request.getHeaders());
        logger.info("Request Body : " + new String(body, "UTF-8"));
        logger.info("==========================request end================================================");
    }

    /**
     *
     * @param response
     * @throws IOException
     */
    private void traceResponse(ClientHttpResponse response) throws IOException, Exception {

        if (response == null) {
            logger.error("No se recibe respuesta valida.");
            return;
        }
        if (response != null && response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
            logger.info("============================response begin==========================================");
            logger.info("status code: " + response.getStatusCode() + " - status text: " + response.getStatusText());
            logger.info("=======================response end=================================================");
            throw new IOException(response.getStatusText());
        }
        if (response != null) {
            logger.info("============================response begin==========================================");
            logger.info("status code: " + response.getStatusCode() + " - status text: " + response.getStatusText());

            StringBuilder inputStringBuilder = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), "UTF-8"));
            String aux = "";
            while ((aux = bufferedReader.readLine()) != null) {
                inputStringBuilder.append(aux);
            }

            logger.info("Response Body : " + inputStringBuilder);
            logger.info("=======================response end=================================================");
        }
    }

}