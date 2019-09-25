package tads.service;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tads.entidades.PersonalTrainer;

@Path("/personal")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class PersonalService {

	public String baseUrl = "http://localhost:8080/restapi/api/personal";

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response allPersonal() {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseUrl);

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();

		return response;
	}

	@GET
	@Path("/onepersonal/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response onePersonal(@PathParam("id") int id) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseUrl.concat("/") + id);

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();

		return response;
	}

	@POST
	@Path("/newpersonal")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response newPersonal(PersonalTrainer personal) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseUrl.concat("/newpersonal"));

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(personal, MediaType.APPLICATION_JSON));

		return response;

	}

	@GET
	@Path("/personalacademia/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response personalPorAcademia(@PathParam("id") int id) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseUrl.concat("/personalacademia/") + id);

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();

		return response;
	}

	@POST
	@Path("/updatepersonal")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePersonal(PersonalTrainer personal) {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseUrl.concat("/updatepersonal"));
		
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		
		Response response = invocationBuilder.put(Entity.entity(personal, MediaType.APPLICATION_JSON));
		
		return Response.ok().build();
	}

}
