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
	
	public List<PersonalTrainer> getPersonalTrainers(){
		
		String jpql = ("select pt from PersonalTrainer pt");
		Query query = entityManager.createQuery(jpql, PersonalTrainer.class);
		List<PersonalTrainer> personaltrainer = query.getResultList();
		
		if (personaltrainer != null) {
			return personaltrainer;
		}
		return null;
	}
	
	public PersonalTrainer getOnePersonal(int id) {
		PersonalTrainer personal = entityManager.find(PersonalTrainer.class, id);
		
		if(personal != null)
			return personal;
		return null;
	}
	
	public PersonalTrainer cadastrarPersonal(String name, String email, String senha) {
		PersonalTrainer personal = new PersonalTrainer();
		personal.setNome(name);
		personal.setEmail(email);
		personal.setSenha(senha);
		entityManager.persist(personal);
		return personal;
	}
}
