package alunorest.service;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
	public Response persistAluno(
			@FormParam("nome") String nome,
			@FormParam("telefone") String telefone,
			@FormParam("dataCobranca") String dataCobranca,
			@FormParam("idPersonal") int idPersonalTrainer) {

		Aluno aluno = alunoBean.cadastrarAluno(nome, telefone, dataCobranca, idPersonalTrainer);

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

	@PUT
	@Path("/updatealunostatus")
	@Consumes(APPLICATION_FORM_URLENCODED)
//	@Path("/updatealunostatus")
	public Response updateAlunoStatus(@PathParam("id") int id) {
//	public Response updateAlunoStatus() {
		Aluno aluno = alunoBean.getOneAluno(1);

		if (aluno == null) {
			return null;
		}

		String dataAtualCobranca = aluno.getDataCobranca();

		SimpleDateFormat formatacao = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();

		try {
			c.setTime(formatacao.parse(dataAtualCobranca));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		c.add(Calendar.MONTH, 1);

		String dataProxCobranca = formatacao.format(c.getTime());
		String statusAtual = "APTO";
		String proximoStatus = "INAPTO";

		System.out.println("Method(updateAlunoStatus) -> Data proxima cobrança: " + dataProxCobranca);

		if (alunoBean.updateAlunoStatus(1, dataProxCobranca, statusAtual, proximoStatus) == 1)
			return Response.ok().build();
		return Response.status(500).build();
	}

	@DELETE
	@Path("excluir")
	@Consumes(APPLICATION_FORM_URLENCODED)
	public Response excluir(@PathParam("id") int id) {

		try {
			alunoBean.deletar(id);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(500).build();
		}
	}
}
