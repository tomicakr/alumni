package hr.petsonly.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


public class ComplexQueryRepositoryImpl implements ComplexQueryRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Object[]> findAllUsersAndPets() {
		String query = "SELECT u.name, u.surname, p.name FROM User u INNER JOIN Pet p ON user.user_id";
		Query nativeQuery = em.createNativeQuery(query);
		return nativeQuery.getResultList();
	}

}
