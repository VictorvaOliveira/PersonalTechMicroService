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
}
