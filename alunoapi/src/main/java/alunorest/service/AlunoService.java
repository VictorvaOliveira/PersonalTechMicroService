package alunorest.service;

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

import alunorest.ejb.AlunoBean;
import alunorest.entidade.Aluno;

@Path("/aluno")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Transactional
public class AlunoService {

	@EJB
	private AlunoBean alunoBean;

	@GET
	public Response findAllAluno() {
		List<Aluno> alunos = alunoBean.getAllAluno();
		if (alunos != null)
			return Response.ok(alunos).build();
		return Response.status(404).build();
	}

	@GET
	@Path("/{id}")
	public Response findById(@PathParam("id") int id) {
		Aluno aluno = alunoBean.getOneAluno(id);
		if (aluno != null)
			return Response.ok(aluno).build();
		return Response.status(404).build();
	}

	@POST
	@Path("/newaluno")
	@Consumes(APPLICATION_FORM_URLENCODED)
	public Response persistAluno(@FormParam("nome") String nome, @FormParam("dataCobranca") String dataCobranca,
			@FormParam("idPersonal") int idPersonalTrainer) {

		Aluno aluno = alunoBean.cadastrarAluno(nome, dataCobranca, idPersonalTrainer);

		if (aluno != null)
			return Response.ok(aluno).build();
		return Response.status(500).build();
	}

	@GET
	@Path("/alunopersonal/{id}")
	public Response findAlunoPerPersonal(@PathParam("id") int id) {
		List<Aluno> alunos = alunoBean.alunoPerPersonal(id);

		if (alunos != null)
			return Response.ok(alunos).build();
		return Response.status(404).build();
	}
}
