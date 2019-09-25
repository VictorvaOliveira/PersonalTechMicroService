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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import personaltech.entidades.PersonalTrainer;
import personaltech.ejb.*;

@Path("/personal")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Transactional
public class PersonalTechService {

	@EJB
	private PersonalTrainerBean personalTrainerBean;

	/*
	 * Recupera uma lista de Personal Trainer
	 */
	@GET
//	@JsonTokenNeeded
	public Response findAllPersonalTrainer() {
		List<PersonalTrainer> allPersonal = personalTrainerBean.getPersonalTrainers();
		if (!allPersonal.isEmpty())
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
	@Consumes(APPLICATION_JSON)
	public Response persistPersonal(PersonalTrainer personal) {

		PersonalTrainer newPersonal = new PersonalTrainer();
		newPersonal.setNome(personal.getNome());
		newPersonal.setEmail(personal.getEmail());
		newPersonal.setSenha("123");
		newPersonal.setIdAcademia(personal.getIdAcademia());

		PersonalTrainer personalTemp = personalTrainerBean.cadastrarPersonal(newPersonal);

		if (personalTemp != null) {
			return Response.ok(personalTemp).build();
		}
		return Response.status(500).build();
	}

	@GET
	@Path("/personalacademia/{id}")
	public Response findPersonalPorAcademia(@PathParam("id") int id) {
		List<PersonalTrainer> personals = personalTrainerBean.personalPorAcademia(id);

		if (!personals.isEmpty()) {
			return Response.ok(personals).build();
		}
		return Response.status(404).build();
	}

	@PUT
	@Path("/updatepersonal")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePersonal(PersonalTrainer personal) {

		PersonalTrainer personalx = personalTrainerBean.getOnePersonal(personal.getId());

		if (personalx == null)
			return Response.status(404).build();

		personalx.setNome(personal.getNome());
		personalx.setEmail(personal.getEmail());
		personalx.setIdAcademia(personal.getIdAcademia());

		if (personalTrainerBean.updatePersonal(personalx) != null)
			return Response.ok(personalx).build();
		return Response.status(500).build();
	}
}
