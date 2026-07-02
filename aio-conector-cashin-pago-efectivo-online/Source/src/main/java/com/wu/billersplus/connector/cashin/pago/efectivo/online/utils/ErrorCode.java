package com.wu.billersplus.connector.cashin.pago.efectivo.online.utils;

public enum ErrorCode {

	ERROR_PROXY_URL(90000),
	ERROR_PROXY_INVALID(90001),
	ERROR_CODIGO_MONEDA(90002),
	ERROR_BILLER_APTO(90003),
	ERROR_DOCUMENT_INVALID(90004),
	ERROR_MSG_INVALID(90022),
	PARAM_MSG_INVALID(90023);

    private Integer codigo;

    private ErrorCode(Integer codigo) {
    	this.codigo = codigo;
    }

    public Integer getCodigo() {
        return this.codigo;
    }
}
