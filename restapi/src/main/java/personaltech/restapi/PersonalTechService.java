package personaltech.restapi;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import java.util.List;

import javax.ejb.EJB;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
//import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
//import javax.ws.rs.POST;
import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
	
	@GET
	public Response findAllPersonalTrainer() {
		List<PersonalTrainer> allPersonal = personalTrainerBean.getPersonalTrainers();
		if (allPersonal != null) 
			return Response.ok(allPersonal).build();
		return Response.status(NOT_FOUND).build();
	}
	
}
