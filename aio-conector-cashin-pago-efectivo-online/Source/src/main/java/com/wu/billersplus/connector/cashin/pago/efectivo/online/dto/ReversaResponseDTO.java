package com.wu.billersplus.connector.cashin.pago.efectivo.online.dto;

import java.io.Serializable;

public class ReversaResponseDTO extends ResponseTrxDTO implements Serializable {

    private static final long serialVersionUID = -6334791373409964501L;

    public ReversaResponseDTO() {
    }

    public ReversaResponseDTO(ErrorDTO errorDTO) {
        this.setCodResponse(errorDTO.getCodigoError());
        this.setMsgResponse(errorDTO.getMensajeError());
    }
}

