package alunorest.service;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
	@Consumes(APPLICATION_JSON)
	public Response persistAluno(Aluno aluno) {

		/*
		 * RECUPERANDO DATA ATUAL ADICIONANDO 1 (UM) MÊS
		 * PARA REALIZAR A PRÓXIMA COBRANÇA 
		 */
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 1);
		Date date = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dataAtualFormatada = sdf.format(date);

		Aluno newAluno = new Aluno();
		newAluno.setNome(aluno.getNome());
		newAluno.setTelefone(aluno.getTelefone());
		newAluno.setDataCobranca(dataAtualFormatada);
		newAluno.setStatusAtual("Apto");
		newAluno.setStatusProxCobranca("Inapto");
		newAluno.setIdPersonalTrainer(aluno.getIdPersonalTrainer());

		Aluno alunoTemp = alunoBean.cadastrarAluno(newAluno);
		if (alunoTemp != null)
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
	@Consumes(APPLICATION_JSON)
	public Response updateAlunoStatus(Aluno alune) {
		
		Aluno aluno = alunoBean.getOneAluno(alune.getId());

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

		System.out.println("Method(updateAlunoStatus) -> Data proxima cobrança: " + dataProxCobranca);

		aluno.setDataCobranca(dataProxCobranca);
		aluno.setStatusAtual("APTO");
		aluno.setStatusProxCobranca("INAPTO");
		if (alunoBean.updateAlunoStatus(aluno) != null)
			return Response.ok().build();
		return Response.status(500).build();
	}

	@PUT
	@Path("/updatealuno")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateAluno(Aluno alune) {
		
		Aluno aluno = alunoBean.getOneAluno(alune.getId());
		
		if(aluno == null)
			return Response.status(404).build();
		
		aluno.setNome(alune.getNome());
		aluno.setDataCobranca(alune.getDataCobranca());
		
		if(alunoBean.updateAluno(aluno) != null) 
			return Response.ok(aluno).build();
		return Response.status(500).build();
	}
	@POST
	@Path("excluir/{id}")
	@Consumes(APPLICATION_JSON)
	public Response excluir(@PathParam("id") int id) {

		try {
			alunoBean.deletar(id);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(500).build();
		}
	}
}
