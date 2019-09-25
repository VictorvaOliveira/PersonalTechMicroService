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
}
