package se.jocke;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Config extends Configuration {

  @Valid
  @NotNull
  private JerseyClientConfiguration jerseyClient = new JerseyClientConfiguration();

  @JsonProperty("jerseyClient")
  public JerseyClientConfiguration getJerseyClientConfiguration() {
    jerseyClient.setGzipEnabled(false);
    return jerseyClient;
  }

  @JsonProperty("jerseyClient")
  public void setJerseyClientConfiguration(JerseyClientConfiguration jerseyClient) {
    this.jerseyClient = jerseyClient;
  }
}