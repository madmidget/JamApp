package se.jocke;

import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.text.DateFormat;
import javax.ws.rs.client.Client;
import java.text.SimpleDateFormat;
import se.jocke.client.SpotifyClient;
import se.jocke.resources.EventResource;
import se.jocke.resources.SpotifyResource;

public class App extends Application<Config> {

  @Override
  public void initialize(final Bootstrap<Config> bootstrap) {
    // TODO: application initialization
  }

  @Override
  public void run(Config configuration, Environment environment) {

    final Client client = new JerseyClientBuilder(environment)
        .using(configuration.getJerseyClientConfiguration()).using(environment)
        .build(getName());

    EventResource eventResource = new EventResource();
    environment.jersey().register(eventResource);

    SpotifyClient spotifyClient = new SpotifyClient(client);

    SpotifyResource spotifyResource = new SpotifyResource(spotifyClient);
    environment.jersey().register(spotifyResource);
}
  public static void main(final String[] args) throws Exception {
    new App().run(args);
  }

  /**
   * Resource file path string.
   *
   * @param resourceClassPathLocation the resource class path location.
   * @return the string.
   */

  public static String resourceFilePath(final String resourceClassPathLocation) {
    try {
      return App.class.getResource(resourceClassPathLocation).toString();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}




