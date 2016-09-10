package users.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import users.models.User;

@Repository
@Transactional
public class UserManagerImpl implements UserManager {
	
	private EntityManager entityManager;
	
	@Autowired
	public UserManagerImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public boolean addEntity(User user) throws Exception {
		entityManager.persist(user);
		return false;
	}

	public User getEntityById(long id) throws Exception {
		return entityManager.find(User.class, id);
	}

	public List<User> getEntityList() throws Exception {
		return entityManager.createQuery("from User").getResultList();
	}

	public boolean deleteEntity(User user) throws Exception {
		if (entityManager.contains(user)) {
			entityManager.remove(user);
			return true;
		}
		return false;
	}

	public void update(User user) throws Exception {
		entityManager.merge(user);
		return;	
	}
}
