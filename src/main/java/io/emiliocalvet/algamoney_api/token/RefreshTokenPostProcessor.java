package io.emiliocalvet.algamoney_api.token;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import io.emiliocalvet.algamoney_api.config.property.AlgamoneyApiProperty;

@ControllerAdvice
public class RefreshTokenPostProcessor implements ResponseBodyAdvice<OAuth2AccessToken> {

  @Autowired
  private AlgamoneyApiProperty algamoneyApiProperty;

  @Override
  public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
    return returnType.getMethod().getName().equals("postAccessToken");
  }

  @Override
  public OAuth2AccessToken beforeBodyWrite(OAuth2AccessToken body, MethodParameter returnType,
    MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
    ServerHttpRequest request, ServerHttpResponse response) {

    DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) body;

    HttpServletRequest req = ((ServletServerHttpRequest) request).getServletRequest();
    HttpServletResponse res = ((ServletServerHttpResponse) response).getServletResponse();

    String refreshToken = body.getRefreshToken().getValue();
    adicionarRefreshTokenNoCookie(refreshToken, req, res);
    removerRefreshTokenDoBody(token);

    return body;
  }

  private void adicionarRefreshTokenNoCookie(String refreshToken, HttpServletRequest req, HttpServletResponse res) {
    Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
    refreshTokenCookie.setHttpOnly(true);
    refreshTokenCookie.setSecure(algamoneyApiProperty.getSeguranca().isEnableHttps());
    refreshTokenCookie.setPath(req.getContextPath() + "/oauth/token");
    refreshTokenCookie.setMaxAge(2592000);
    res.addCookie(refreshTokenCookie);
  }

  private void removerRefreshTokenDoBody(DefaultOAuth2AccessToken token) {
    token.setRefreshToken(null);
  }
}
