package io.emiliocalvet.algamoney_api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserDetailsService userDetailsService;

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.inMemory()
            .withClient("angular")
             // secret = @ngul@r
            .secret("$2a$10$XuzdhFPFC07m0MZxdTi3.uYmhvmSbSYHt1fFOtyCXFtkRhTMi7s7.")
            .scopes("read", "write")
            .authorizedGrantTypes("password", "refresh_token")
            .accessTokenValiditySeconds(1800)
            .refreshTokenValiditySeconds(3600 * 24)
           .and()
            .withClient("mobile")
             // secret = m0b1l3
            .secret("$2a$10$7r8D/TVP4NaI5TKkcWqPD.ZlyTCfToOOb9bAGv6ZJA4c5Gn3dr.Ci")
            .scopes("read")
            .authorizedGrantTypes("password", "refresh_token")
            .accessTokenValiditySeconds(1800)
            .refreshTokenValiditySeconds(3600 * 24);
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.tokenStore(tokenStore())
             .accessTokenConverter(this.accessTokenConverter())
             .reuseRefreshTokens(false)
             .userDetailsService(this.userDetailsService)
             .authenticationManager(this.authenticationManager);
  }

  @Bean
  public JwtAccessTokenConverter accessTokenConverter() {
    JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
    accessTokenConverter.setSigningKey("algaworks");
    return accessTokenConverter;
  }

  @Bean
  public TokenStore tokenStore() {
    return new JwtTokenStore(accessTokenConverter());
  }
}
