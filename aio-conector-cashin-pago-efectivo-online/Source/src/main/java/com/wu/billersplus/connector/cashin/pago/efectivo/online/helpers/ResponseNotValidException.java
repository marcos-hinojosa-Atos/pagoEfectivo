package com.wu.billersplus.connector.cashin.pago.efectivo.online.helpers;

import com.wu.billersplus.connector.cashin.pago.efectivo.online.dto.ErrorDTO;

public class ResponseNotValidException extends RuntimeException  {

    ErrorDTO errorDTO;

    public ResponseNotValidException() {
    }

    public ResponseNotValidException(String message) {
        super(message);
    }

    public ResponseNotValidException(ErrorDTO errorDTO) {
        this.errorDTO = errorDTO;
    }

    public ResponseNotValidException(String message, ErrorDTO errorDTO) {
        super(message);
        this.errorDTO = errorDTO;
    }

    public ErrorDTO getErrorDTO() {
        return errorDTO;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(this.getClass().toString());
        sb.append(this.errorDTO.toString());
        return sb.toString();
    }

}
