package se.jocke.resources;

import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import se.jocke.Event;
import se.jocke.api.ClientCredential;

@Path("events")
@Produces(MediaType.APPLICATION_JSON)
public class EventResource {

  @GET
  public List<Event> allEvents() {

    Event e = new Event();
    e.setDate(new Date());
    e.setName("BirthDay");
    e.setId(1L);
    e.setDescription("Please be on time");
    e.setLocation("Stockholm");

    return Collections.singletonList(e);
  }


  @GET
  @Path("/test")
  public Response test(@Context HttpServletRequest request) throws URISyntaxException {
    String url = request.getRequestURL().toString();
    String reqString = url + " + ";
    return Response.status(Status.OK).entity(reqString).build();
  }

  @GET
  @Path("/token")
  @Produces(MediaType.APPLICATION_JSON)
  public ClientCredential tokenTest() {
    ClientCredential c = new ClientCredential();
    c.setAccess_token("asdf34q5");

    //return new ClientCredential();
    return c;

  }
}


