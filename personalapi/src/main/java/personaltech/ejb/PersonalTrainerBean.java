package personaltech.ejb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import personaltech.entidades.PersonalTrainer;

@Stateless
public class PersonalTrainerBean {

	@PersistenceContext(unitName = "pu")
	private EntityManager entityManager;

	@PostConstruct
	private void initializeBean() {

	}

	public List<PersonalTrainer> getPersonalTrainers() {

		String jpql = ("select pt from PersonalTrainer pt");
		Query query = entityManager.createQuery(jpql, PersonalTrainer.class);
		List<PersonalTrainer> personaltrainer = query.getResultList();

		if (!personaltrainer.isEmpty()) {
			return personaltrainer;
		}
		return null;
	}

	public PersonalTrainer getOnePersonal(int id) {
		PersonalTrainer personal = entityManager.find(PersonalTrainer.class, id);

		if (personal != null)
			return personal;
		return null;
	}

	public PersonalTrainer cadastrarPersonal(PersonalTrainer personal) {

		entityManager.persist(personal);
		return personal;
	}
	
	public List<PersonalTrainer> personalPorAcademia(int id){
		String jpql = ("select pt from PersonalTrainer pt where pt.idAcademia = ?1");
		Query query = entityManager.createQuery(jpql, PersonalTrainer.class);
		
		List<PersonalTrainer> personals = query.setParameter(1,id).getResultList();
		
		if (!personals.isEmpty())
			return personals;
		return null;
	}

	public PersonalTrainer login(String login, String senha) {
		String jpql = ("select pt from PersonalTrainer pt where pt.email = :pLogin and pt.senha= :pSenha");
		Query query = entityManager.createQuery(jpql, PersonalTrainer.class);
		query.setParameter("pLogin", login);
		query.setParameter("pSenha", senha);
		PersonalTrainer personal = (PersonalTrainer) query.getSingleResult();
		return personal;
	}
	
	public int deletar(int id) {
		
		PersonalTrainer personal = entityManager.find(PersonalTrainer.class, id);
		
		if(personal != null) {
			entityManager.remove(personal);
			return 1;
		}else{
			return 2;
		}
	}
	
	public PersonalTrainer updatePersonal(PersonalTrainer personal) {
		
		return entityManager.merge(personal);
	
	}
}
