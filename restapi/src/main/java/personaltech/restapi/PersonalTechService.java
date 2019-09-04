package personaltech.restapi;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import personaltech.entidades.PersonalTrainer;
import personaltech.jwtConfiguration.JsonTokenNeeded;
import personaltech.util.JwTokenHelper;
import personaltech.ejb.*;

@Path("/personal")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Transactional
public class PersonalTechService {

	@EJB
	private PersonalTrainerBean personalTrainerBean;

	/*
	 * Login personal trainer
	 */
	@POST
	@Path("/login")
	@Consumes(APPLICATION_FORM_URLENCODED)
	public Response login(@FormParam("login") String login, @FormParam("senha") String senha) {

		PersonalTrainer personal = personalTrainerBean.login(login, senha);

		String token = null;

		if (personal != null) {
			token = JwTokenHelper.getInstance().generateToken(login, senha);
			return Response.ok(personal).header(AUTHORIZATION, "Bearer" + token).build();
		}
		return Response.status(NOT_FOUND).build();

	}

	/*
	 * Recupera uma lista de Personal Trainer
	 */
	@GET
	@JsonTokenNeeded
	public Response findAllPersonalTrainer() {
		List<PersonalTrainer> allPersonal = personalTrainerBean.getPersonalTrainers();
		if (allPersonal != null)
			return Response.ok(allPersonal).build();
		return Response.status(NOT_FOUND).build();
	}

	/*
	 * Recupera somente um Personal Trainer
	 */
	@GET
	@Path("/{id}")
	public Response findById(@PathParam("id") int id) {
		PersonalTrainer personal = personalTrainerBean.getOnePersonal(id);
		if (personal != null)
			return Response.ok(personal).build();
		return Response.status(NOT_FOUND).build();
	}

	/*
	 * Persiste no banco de dados um objeto Personal trainer
	 */
	@POST
	@Path("/newpersonal")
	@Consumes(APPLICATION_FORM_URLENCODED)
	public Response persistPersonal(@FormParam("name") String name, @FormParam("email") String email,
			@FormParam("senha") String senha) {
		PersonalTrainer personal = personalTrainerBean.cadastrarPersonal(name, email, senha);

		if (personal != null) {
			return Response.ok(personal).build();
		}
		return Response.status(500).build();
	}

}
