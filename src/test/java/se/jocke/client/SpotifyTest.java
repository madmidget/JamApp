package se.jocke.client;

import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.ClassRule;
import org.junit.Test;
import se.jocke.App;
import se.jocke.Config;
import se.jocke.api.ClientCredential;

public class SpotifyTest {

  @ClassRule
  public static final DropwizardAppRule<Config> RULE =
      new DropwizardAppRule<>(App.class,
          ResourceHelpers.resourceFilePath("config.yml"));

  @Test
  public void testIt() {
    ClientCredential cc = token().readEntity(ClientCredential.class);

    final String getTrackUrl = "https://api.spotify.com/v1/tracks/2TpxZ7JUBn3uw46aR7qd6V";

    Response response1 = RULE.client().target(getTrackUrl).request()
        .header("Authorization", " Bearer " + cc.getAccess_token())
        .get();
    System.out.println("Respons:   " + cc);
    System.out.println("track: " + response1.readEntity(String.class));
  }

  private static Response token() {
    HttpAuthenticationFeature auth = HttpAuthenticationFeature
        .basic("24ff2fed143b42969ccd12b9a12bf3d0", "eb19a04c3b3246379df49ed73cc195bb");
    Client client = RULE.client();
    WebTarget webTarget = client.register(auth).target("https://accounts.spotify.com/api/token");
    webTarget.register(new LoggingFilter());
    Response response = webTarget.request()
        .post(Entity.form(new Form("grant_type", "client_credentials")));

    return response;
  }
}