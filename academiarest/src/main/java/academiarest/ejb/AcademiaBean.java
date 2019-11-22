package academiarest.ejb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.Query;

import academiarest.entidade.Academia;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AcademiaBean {

	@PersistenceContext(unitName = "pu")
	private EntityManager entityManager;

	@PostConstruct
	private void initializeBean() {

	}

	public List<Academia> getAllAcademia() {

		String jpql = ("select ac from Academia ac");
		Query query = entityManager.createQuery(jpql, Academia.class);
		List<Academia> academias = query.getResultList();

		if (academias != null)
			return academias;
		return null;

	}

	public Academia getOneAcademia(int id) {

		Academia Academia = entityManager.find(Academia.class, id);

		if (Academia != null)
			return Academia;
		return null;
	}

	public int remove(int id) {
		
		Academia academia = entityManager.find(Academia.class, id);
		
		if (academia != null) {
			entityManager.remove(academia);
			return 1;
		} else {
			return 2;
		}
	}

	public Academia login(Academia newAcademia) {
		String jpql = ("select a from Academia a where a.email = :email and a.senha = :senha");
		Query query = entityManager.createQuery(jpql);
		query.setParameter("email", newAcademia.getEmail());
		query.setParameter("senha", newAcademia.getSenha());
		
		Academia academiax  = (Academia) query.getSingleResult();
		academiax.setToken(newAcademia.getToken());
		
		academiax = updateAcademia(academiax);
		System.out.println("AcadeimaBean (token):" + academiax.getToken());
		
		return academiax;
	}

	private Academia updateAcademia(Academia academiax) {
		// TODO Auto-generated method stub
		return entityManager.merge(academiax);
	}
}
