package com.wu.billersplus.connector.cashin.pago.efectivo.online.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenRequestDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("userName")
	private String userName;

	@JsonProperty("password")
	private String password;

	@JsonProperty("grant_type")
	private String grantType;

	public TokenRequestDTO(String userName, String password, String grantType) {
		this.userName = userName;
		this.password = password;
		this.grantType = grantType;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGrantType() {
		return grantType;
	}
	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}

}
