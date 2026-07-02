package com.wu.billersplus.connector.cashin.pago.efectivo.online.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.wu.billersplus.connector.cashin.pago.efectivo.online.dto.ConsultaRequestDTO;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.dto.ReversaRequestDTO;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.dtoUpgrade.ConsultaResponseDTO;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.dtoUpgrade.DirectaRequestDTO;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.dto.DirectaResponseDTO;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.dto.ReversaResponseDTO;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.dto.TokenRequestDTO;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.dto.TokenResponseDTO;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.utils.DateUtils;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.utils.JsonMapper;

/**
 *
 */
public class RestExecute {

    private static Logger logger = Logger.getLogger(RestExecute.class);
    private final String userAgent;
    private RestTemplate template;
    private ObjectMapper mapper;
    private TokenResponseDTO tokenDTO;
    private HttpHeaders defaultHeaders;
    private ValidatorFactory factory;
    private Validator validator;
    private String urlToken;
    private String urlConsulta;
    private String urlDirecta;
    private String urlReversa;
    private String userToken;
    private String passToken;
    private String grantTypeToken;
    private String key;
    private String keyType;
    private int timeOut;

    /**
     *
     */
    public RestExecute(String urlConsulta, String urlDirecta, String urlReversa, int timeOut,
                       String urlToken, String userToken, String passToken, String gratTypeToken, String key, String keyType, String userAgent) {
        this.urlToken = urlToken;
        this.urlConsulta = urlConsulta;
        this.urlDirecta = urlDirecta;
        this.urlReversa = urlReversa;
        this.timeOut = timeOut;
        this.userToken = userToken;
        this.passToken = passToken;
        this.grantTypeToken = gratTypeToken;
        this.key = key;
        this.keyType = keyType;
        this.template = getRestTemplate();
        this.mapper = JsonMapper.objectMapper;
        this.factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
        this.userAgent = userAgent;
    }


    /**
     * @param requestConsulta
     * @return ConsultaResponseDTO
     * @throws Exception
     */
    public ConsultaResponseDTO sendConsulta(ConsultaRequestDTO requestConsulta) {

        HttpEntity<ConsultaRequestDTO> requestEntity = new HttpEntity<ConsultaRequestDTO>(requestConsulta, getDefaultHeaders());
        ResponseEntity<ConsultaResponseDTO> responseEntity;
        responseEntity = template.exchange(this.urlConsulta, HttpMethod.POST, requestEntity, ConsultaResponseDTO.class);

        Set<ConstraintViolation<ConsultaResponseDTO>> violations = validator.validate(responseEntity.getBody());
        if (!violations.isEmpty()) {
            for (ConstraintViolation<ConsultaResponseDTO> violation : violations) logger.info(violation.getMessage());
            throw new RuntimeException("Error en los datos recibidos de la entidad");
        }

        return responseEntity.getBody();
    }


    /**
     * @param requestDirecta
     * @return DirectaResponseDTO
     * @throws Exception
     */
    public DirectaResponseDTO sendDirecta(DirectaRequestDTO requestDirecta) throws Exception {

        HttpEntity<DirectaRequestDTO> requestEntity = new HttpEntity<DirectaRequestDTO>(requestDirecta, getDefaultHeaders());
        ResponseEntity<DirectaResponseDTO> responseEntity;
        responseEntity = template.exchange(this.urlDirecta, HttpMethod.POST, requestEntity, DirectaResponseDTO.class);

        Set<ConstraintViolation<DirectaResponseDTO>> violations = validator.validate(responseEntity.getBody());
        if (!violations.isEmpty()) {
            for (ConstraintViolation<DirectaResponseDTO> violation : violations) logger.info(violation.getMessage());
            throw new Exception("Error en los datos recibidos de la entidad");
        }

        return responseEntity.getBody();
    }


    /**
     * @param requestReversa
     * @return ReversaResponseDTO
     * @throws Exception
     */
    public ReversaResponseDTO sendReversa(ReversaRequestDTO requestReversa) throws Exception {
        HttpEntity<ReversaRequestDTO> requestEntity = new HttpEntity<ReversaRequestDTO>(requestReversa, getDefaultHeaders());
        ResponseEntity<ReversaResponseDTO> responseEntity;
        responseEntity = template.exchange(this.urlReversa, HttpMethod.POST, requestEntity, ReversaResponseDTO.class);

        Set<ConstraintViolation<ReversaResponseDTO>> violations = validator.validate(responseEntity.getBody());
        if (!violations.isEmpty()) {
            for (ConstraintViolation<ReversaResponseDTO> violation : violations) logger.info(violation.getMessage());
            throw new Exception("Error en los datos recibidos de la entidad");
        }

        return responseEntity.getBody();
    }


    /**
     * @param tokenRequestDTO
     * @return
     * @throws Exception
     */
    public TokenResponseDTO obtenerToken(TokenRequestDTO tokenRequestDTO) throws Exception {
        TokenResponseDTO tokenResponse;

        HttpEntity<TokenRequestDTO> requestEntity = new HttpEntity<TokenRequestDTO>(tokenRequestDTO, this.defaultHeaders);
        ResponseEntity<TokenResponseDTO> responseEntity = template.exchange(urlToken, HttpMethod.POST, requestEntity, TokenResponseDTO.class);

        Set<ConstraintViolation<TokenResponseDTO>> violations = validator.validate(responseEntity.getBody());
        if (!violations.isEmpty()) {
            for (ConstraintViolation<TokenResponseDTO> violation : violations) {
                logger.info(violation.getMessage());
            }
            throw new Exception("Error en los datos recibidos de la entidad");
        }

        tokenResponse = responseEntity.getBody();
        tokenResponse.setDateExpire(DateUtils.addSecond(new Date(), tokenResponse.getExpiresIn()));

        return tokenResponse;
    }


    /**
     * @return HttpHeaders
     *
     * 3 opciones de token  para setear en el header
     *      1. Usa url de token
     *      2. Usa token fijo Api-Key
     *      3. No usa nada
     */
    private HttpHeaders getDefaultHeaders() {
        if (this.defaultHeaders == null) {
            this.defaultHeaders = new HttpHeaders();
            this.defaultHeaders.setContentType(MediaType.APPLICATION_JSON);
            this.defaultHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            if(StringUtils.isNotBlank(userAgent))
                this.defaultHeaders.set("User-Agent",userAgent);
        }

        if (StringUtils.isNotBlank(urlToken))
            setToken();

        if (StringUtils.isBlank(urlToken)  && StringUtils.isNotBlank(key) && StringUtils.isNotBlank(keyType))
            this.defaultHeaders.set(keyType, key);

        return this.defaultHeaders;
    }

    private void setToken() {
        if (this.tokenDTO == null || StringUtils.isBlank(this.tokenDTO.getAccessToken()) || tokenDTO.getDateExpire().before(new Date())) {
            try {
                this.tokenDTO = obtenerToken(new TokenRequestDTO(userToken, passToken, grantTypeToken));
            } catch (Exception e) {
                logger.error("OCURRIO UN ERROR AL INTENTAR OBTENER TOKEN", e);
            }
        }
        this.defaultHeaders.set("Authorization", tokenDTO.getTokenType().concat(" " + tokenDTO.getAccessToken()));
    }


    /**
     * @return
     */
    private RestTemplate getRestTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(timeOut);
        factory.setConnectTimeout(timeOut);

        RestTemplate template = new RestTemplate(factory);
        template.setErrorHandler((new RestTemplateResponseErrorHandler()));

        List<ClientHttpRequestInterceptor> ris = new ArrayList<ClientHttpRequestInterceptor>();
        ris.add(new LoggingRequestInterceptor());
        template.setInterceptors(ris);

        template.setRequestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));

        MappingJackson2HttpMessageConverter mapping = new MappingJackson2HttpMessageConverter();
        mapping.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        template.getMessageConverters().add(mapping);

        return template;
    }

}
