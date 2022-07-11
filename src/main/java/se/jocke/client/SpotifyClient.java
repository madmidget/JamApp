package se.jocke.client;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class SpotifyClient {

  private final Client client;

  private final CacheLoader<String, String> loader;
  private final LoadingCache<String, String> cache;
  private final Map<String, String> map = new HashMap<>();

  private static Response token(final Client client) {
    HttpAuthenticationFeature auth = HttpAuthenticationFeature
        .basic("24ff2fed143b42969ccd12b9a12bf3d0", "eb19a04c3b3246379df49ed73cc195bb");

    final WebTarget webTarget = client.target("https://accounts.spotify.com/api/token");
    webTarget.register(new LoggingFilter());
    final Response response = webTarget.register(auth).request()
        .post(Entity.form(new Form("grant_type", "client_credentials")));
    System.out.println("Respons:   " + response);

    return response;
  }

  public SpotifyClient(final Client client) {
    this.client = client;
    loader = new CacheLoader<String, String>() {
      @Override
      public String load(final String key) throws ExecutionException {

        System.out.println("cache: " + token(client).readEntity(String.class));
        return "";
      }
    };
    cache = CacheBuilder.newBuilder().expireAfterWrite(55, TimeUnit.SECONDS).build(loader);
  }

  public String getTrack(final String track) throws ExecutionException {

    String token = cache.get("access_token");

    final String getTrackUrl = "https://api.spotify.com/v1/tracks/";

    System.out.println("track url: " + getTrackUrl + track);
    Response response = client.target(getTrackUrl).path(track).request()
        .header("Authorization", " Bearer " + token)
        .get();

    System.out.println("Respons2: " + response);
    System.out.println("Token: " + token);

    return response.readEntity(String.class);
  }
}
