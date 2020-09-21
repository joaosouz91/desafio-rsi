package br.com.brasilprev.api.config.security.token;

import br.com.brasilprev.api.config.security.SystemUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author "Joao Victor de Souza Santos"
 * 
 * @apiNote
 * Classe de customização do TokenEnhancer.
 * Captura o nome do usuário vindo OAuth2Authentication e o adiciona no mapeamento do Access Token.
 *
 */
public class CustomTokenEnhancer implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		SystemUser systemUser = (SystemUser) authentication.getPrincipal();
		
		Map<String, Object> addInfo = new HashMap<>();
		addInfo.put("name", systemUser.getUserAccount().getName());
		
		((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(addInfo);
		
		return accessToken;
	}

}
