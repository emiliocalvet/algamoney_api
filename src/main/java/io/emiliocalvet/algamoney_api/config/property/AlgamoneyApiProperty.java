package io.emiliocalvet.algamoney_api.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("algamoney")
public class AlgamoneyApiProperty {

  private String originPermitida = "http://localhost:8000";

  private final Seguranca seguranca = new Seguranca();

  public Seguranca getSeguranca() {
    return this.seguranca;
  }

  public String getOriginPermitida() {
    return this.originPermitida;
  }

  public void setOriginPermitida(String originPermitida) {
    this.originPermitida = originPermitida;
  }

  public static class Seguranca {
    private boolean enableHttps;
    
    public boolean isEnableHttps() {
      return this.enableHttps;
    }
    
    public void setEnableHttps(boolean enableHttps) {
      this.enableHttps = enableHttps;
    }
  }
  
}
