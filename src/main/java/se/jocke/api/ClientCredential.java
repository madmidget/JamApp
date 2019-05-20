package se.jocke.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientCredential {

  private String access_token;
  private String token_type;
  private Integer expires_in;

  public ClientCredential(){}

  @JsonProperty
  public String getAccess_token() {
    return access_token;
  }

  @JsonProperty
  public void setAccess_token(String access_token) {
    this.access_token = access_token;
  }

  @JsonProperty
  public String getToken_type() {
    return token_type;
  }

  @JsonProperty
  public void setToken_type(String token_type) {
    this.token_type = token_type;
  }

  @JsonProperty
  public Integer getExpires_in() {
    return expires_in;
  }

  @JsonProperty
  public void setExpires_in(Integer expires_in) {
    this.expires_in = expires_in;
  }

  @Override
  public String toString() {
    return "ClientCredential{" +
        "access_token='" + access_token + '\'' +
        ", token_type='" + token_type + '\'' +
        ", expires_in=" + expires_in +
        '}';
  }
}
