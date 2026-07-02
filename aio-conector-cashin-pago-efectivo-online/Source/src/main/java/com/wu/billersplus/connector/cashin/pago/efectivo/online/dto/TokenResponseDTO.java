package com.wu.billersplus.connector.cashin.pago.efectivo.online.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "{tokenResponseDTO.access_token.null}")
	@JsonProperty("access_token")
	private String accessToken;

	@NotNull(message = "{tokenResponseDTO.expires_in.null}")
	@JsonProperty("expires_in")
	private String expiresIn;

	@NotNull(message = "{tokenResponseDTO.token_type.null}")
	@JsonProperty("token_type")
	private String tokenType;

	private Date dateExpire;

	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}
	public Date getDateExpire() {
		return dateExpire;
	}
	public void setDateExpire(Date dateExpire) {
		this.dateExpire = dateExpire;
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getToken(){
		String token = "";
		if(this.tokenType != null)
			token += this.tokenType+" ";
		if(this.accessToken != null)
			token += this.accessToken;
		return token;
	}
}