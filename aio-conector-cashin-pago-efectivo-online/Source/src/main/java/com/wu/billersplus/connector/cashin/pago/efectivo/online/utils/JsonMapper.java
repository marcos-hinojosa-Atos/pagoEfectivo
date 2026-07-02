package com.wu.billersplus.connector.cashin.pago.efectivo.online.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;


public class JsonMapper {

    private static Logger logger = Logger.getLogger(JsonMapper.class);

    public static ObjectMapper objectMapper = getDefaultObjectMapper();

    private static ObjectMapper getDefaultObjectMapper() {
        if(objectMapper == null) objectMapper = new ObjectMapper();
        return objectMapper;
    }

}
