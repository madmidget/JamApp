package se.jocke.resources;

import java.util.concurrent.ExecutionException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import se.jocke.client.SpotifyClient;

@Path("nisse")
public class SpotifyResource {

  private final SpotifyClient client;

  public SpotifyResource(final SpotifyClient client) {
    this.client = client;
  }

  @GET
  @Path("track/{track}")
  @Consumes("application/x-www-form-urlencoded")
  @Produces(MediaType.APPLICATION_JSON)
  public String getTrack(@PathParam("track") final String track) throws ExecutionException {
    System.out.println("The track is:   " + track);

    return client.getTrack(track);
  }
}
