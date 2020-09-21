package br.com.brasilprev.api.config.security.token;

import br.com.brasilprev.api.property.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Joao Victor de Souza Santos
 *
 * @apiNote
 * Classe de pós processamento do OAuth2AccessToken.
 * Seu objetivo é remover o Token do Body da resposta do servidor
 * e realocá-lo em um Cookie seguro antes que ele seja de fato devolvido ao client.
 *
 */

@ControllerAdvice
public class RefreshTokenPostProcessor implements ResponseBodyAdvice<OAuth2AccessToken>{

	@Autowired
	private ApplicationProperties applicationProperties;
	
	/**
	 * beforeBodyWrite só será executado quando o nome do método
	 * da requisição for "postAccessToken"
	 */
	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return returnType.getMethod().getName().equals("postAccessToken");
	}

	/**
	 * Faz a troca da localização do refresh token
	 */
	@Override
	public OAuth2AccessToken beforeBodyWrite(OAuth2AccessToken body, MethodParameter returnType,
			MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
			ServerHttpRequest request, ServerHttpResponse response) {
		
		HttpServletRequest req = ((ServletServerHttpRequest) request).getServletRequest();
		HttpServletResponse resp = ((ServletServerHttpResponse) response).getServletResponse();
		DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) body;
		String refreshToken = body.getRefreshToken().getValue();
		
		addCookieOnRefreshToken(refreshToken, req, resp);
		removeRefreshTokenFromBody(token);
		
		return body;
	}

	private void removeRefreshTokenFromBody(DefaultOAuth2AccessToken token) {
		token.setRefreshToken(null);
	}

	private void addCookieOnRefreshToken(String refreshToken, HttpServletRequest req, HttpServletResponse resp) {
		Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
		refreshTokenCookie.setHttpOnly(true); // JavaScript não consegue acessar
		refreshTokenCookie.setSecure(applicationProperties.getSecurity().isEnableHttps());
		refreshTokenCookie.setPath(req.getContextPath() + "/oauth/token");
		refreshTokenCookie.setMaxAge(2592000);
		resp.addCookie(refreshTokenCookie);
	}

}
