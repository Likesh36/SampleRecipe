package com.recipe.jwt;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TokenEntity implements Serializable {

	private final String token;

	public TokenEntity(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

}
