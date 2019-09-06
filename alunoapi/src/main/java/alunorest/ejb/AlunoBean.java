package alunorest.ejb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import alunorest.entidade.Aluno;

@Stateless
public class AlunoBean {

	@PersistenceContext(unitName = "pu")
	private EntityManager entityManager;

	@PostConstruct
	private void initializeBean() {

	}

	public List<Aluno> getAllAluno() {
		
		String jpql = ("select al from Aluno al");
		Query query = entityManager.createQuery(jpql, Aluno.class);
		List<Aluno> alunos = query.getResultList();

		if (alunos != null)
			return alunos;
		return null;
	}

	public Aluno getOneAluno(int id) {
		
		Aluno aluno = entityManager.find(Aluno.class, id);

		if (aluno != null)
			return aluno;
		return null;
	}

	public Aluno cadastrarAluno(String nome, String dataCobranca, int idPersonalTrainer) {
		
		Aluno aluno = new Aluno();
		
		aluno.setNome(nome);
		aluno.setDataCobranca(dataCobranca);
		aluno.setStatusAtual("APTO");
		aluno.setStatusProxCobranca("INAPTO");
		aluno.setIdPersonalTrainer(idPersonalTrainer);
		
		entityManager.persist(aluno);
		return aluno;
	}

	public List<Aluno> alunoPerPersonal(int id) {
		
		String jpql = ("select al from Aluno al where al.idPersonalTrainer = ?1");
		Query query = entityManager.createQuery(jpql, Aluno.class);
		List<Aluno> alunos = query.setParameter(1, id).getResultList();
		
		if (alunos != null)
			return alunos;
		return null;
	}
}
