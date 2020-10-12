package com.doggers.demo.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.doggers.demo.entity.Users;
import com.doggers.demo.service.UserServiceImpl;



@Component
public class InfoAdicionalToken implements TokenEnhancer {
	
	@Autowired
	private UserServiceImpl userService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		Users users = userService.findByEmail(authentication.getName());
		
		Map<String, Object> info = new HashMap<>();
		
		//info.put("info_adicional", "Hola".concat(authentication.getName()));
		
		info.put("name", users.getName());
		//info.put("lastname", users.getLastname());
		//info.put("email", users.getEmail());
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		
		return accessToken;
	}

}
