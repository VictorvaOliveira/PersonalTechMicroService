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

	public Aluno cadastrarAluno(Aluno aluno) {

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

	/*
	 * ATUALIZAÇÃO DO STATUS DO ALUNO
	 */
	public Aluno updateAlunoStatus(Aluno aluno) {
		return entityManager.merge(aluno);
	}

	/*
	 * ATUALIZAÇÃO DE DADOS PESSOAIS (NOME) E DATA DA COBRANCA
	 */
	public Aluno updateAluno(Aluno aluno) {
		return entityManager.merge(aluno);
	}

	/*
	 * DELETAR ALUNO
	 */
	public int deletar(int id) {

		Aluno aluno = entityManager.find(Aluno.class, id);

		if (aluno != null) {
			entityManager.remove(aluno);
			return 1;
		} else {
			return 2;
		}
	}

}
