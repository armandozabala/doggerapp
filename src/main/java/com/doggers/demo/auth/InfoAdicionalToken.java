package com.doggers.demo.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;


@Component
public class InfoAdicionalToken implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		Map<String, Object> infoMap = new HashMap<>();
		infoMap.put("info_adicional", "Hola".concat(authentication.getName()));
		
		
		
		return accessToken;
	}

}
