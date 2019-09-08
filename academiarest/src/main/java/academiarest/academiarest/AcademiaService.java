package academiarest.academiarest;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.List;

import javax.ejb.EJB;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import academiarest.ejb.AcademiaBean;
import academiarest.entidade.Academia;

@Path("/academia")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Transactional
public class AcademiaService {

	@EJB
	private AcademiaBean academiaBean;
	
	@GET
	public Response findAllAcademia() {
		List<Academia> academias = academiaBean.getAllAcademia();
		if (academias != null)
			return Response.ok(academias).build();
		return Response.status(404).build();
	}
	
	/**
	 * FAZER OUTROS MÉTODOS BASEADO 
	 * NA NECESSIDADE DO PROJETO
	 */
}
