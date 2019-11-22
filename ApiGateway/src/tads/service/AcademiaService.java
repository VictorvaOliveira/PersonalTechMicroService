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

import tads.entidades.Academia;
import tads.jwtConfiguration.JsonTokenNeeded;
import tads.util.JwTokenHelper;


@Path("/academia")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class AcademiaService {

	public String baseUrl = "http://localhost:8080/academiarest/api/academia";
	
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response loginAcademia(Academia academia) {
		
		String login = academia.getEmail();
		String password = academia.getSenha();
		
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseUrl.concat("/login"));

		//Gerando token
		String token = JwTokenHelper.getInstance().generateToken(login, password);
		
		if(academia != null) {
			//Atribuindo o token ao usuário
			academia.setToken(token);
			System.out.println("Academia(token) : " + academia.getToken());
		}
		
		
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(academia, MediaType.APPLICATION_JSON));
		
		return response;
	
	}
	
	@GET
	@Path("/oneacademia/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@JsonTokenNeeded
	public Response oneAcademia(@PathParam("id") int id) {
		
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseUrl.concat("/") + id);

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();

		return response;
	}
	
	@POST
	@Path("/excluiracademia/{id}")
	@JsonTokenNeeded
	@Produces(MediaType.APPLICATION_JSON)
	public Response excluirAluno(@PathParam("id") int id) {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseUrl.concat("/excluir/") + id);

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

		Response response = invocationBuilder.delete();

		System.out.println(response.getStatus());

		return Response.ok().build();
	}
}
