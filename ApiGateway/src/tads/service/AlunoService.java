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

import tads.entidades.Aluno;
import tads.jwtConfiguration.JsonTokenNeeded;

@Path("/aluno")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class AlunoService {

	public String baseUrl = "http://localhost:8080/aluno/api/aluno";

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	@JsonTokenNeeded
	public Response allAlunos() {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseUrl);
//		WebTarget webTarget = client.target("http://localhost:8080/aluno/api/aluno");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();

		return response;
	}
	
	@GET
	@Path("/onealuno/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@JsonTokenNeeded
	public Response oneAluno(@PathParam("id") int id) {
		
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseUrl.concat("/") + id);

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();

		return response;
	}

	@POST
	@Path("/newaluno")
	@Produces(MediaType.APPLICATION_JSON)
	@JsonTokenNeeded
	public Response newAluno(Aluno aluno) {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseUrl.concat("/newaluno"));

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

		Response response = invocationBuilder.post(Entity.entity(aluno, MediaType.APPLICATION_JSON));

		System.out.println(response.getStatus());

		return Response.ok().build();
	}

	@POST
	@Path("/updatestatus")
	@Produces(MediaType.APPLICATION_JSON)
	@JsonTokenNeeded
	public Response updataStatus(Aluno aluno) {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseUrl.concat("/updatealunostatus/"));

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

		Response response = invocationBuilder.put(Entity.entity(aluno, MediaType.APPLICATION_JSON));

		System.out.println(response.getStatus());

		return Response.ok().build();

	}

	@POST
	@Path("/updatealuno")
	@Produces(MediaType.APPLICATION_JSON)
	@JsonTokenNeeded
	public Response updataAluno(Aluno aluno) {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseUrl.concat("/updatealuno/"));

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

		Response response = invocationBuilder.put(Entity.entity(aluno, MediaType.APPLICATION_JSON));

		System.out.println(response.getStatus());

		return Response.ok().build();

	}

	@POST
	@Path("/excluiraluno/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@JsonTokenNeeded
	public Response excluirAluno(@PathParam("id") int id) {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseUrl.concat("/excluir/") + id);

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

		Response response = invocationBuilder.delete();

		System.out.println(response.getStatus());

		return Response.ok().build();
	}
}
