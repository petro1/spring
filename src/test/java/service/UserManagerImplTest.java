package service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import users.models.User;
import users.service.UserManager;
import users.service.UserManagerImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserManagerImplTest {

	private EntityManager entityManager;
	private UserManager userManager;
	private Query mockedQuery;
	
	@Before
	public void setUp() {
		entityManager = mock(EntityManager.class);
		userManager = new UserManagerImpl(entityManager);
		mockedQuery = mock(Query.class);
	}
	
	@After
	public void tearDown()  {
		entityManager = null;
		userManager = null;
		mockedQuery = null;
	}
	
	@Test
	public void shouldReturnUsersList() {
		Long userId = (long)1;
		User user = new User(userId);
		List<User> userList = new ArrayList<User>();
		userList.add(user);
		
		when(mockedQuery.getResultList()).thenReturn(userList);
		when(entityManager.createQuery("from User")).thenReturn(mockedQuery);
		
		List<User> resultList = null;
		try {
			resultList = userManager.getEntityList();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		assertEquals(userList, resultList);
	}
	
	@Test
	public void shouldGetEntityById() {
		Long userId = (long)1;
		User user = new User(userId);
		
		when(entityManager.find(User.class, userId)).thenReturn(user);
		
		User resultUser = null;
		try {
			resultUser = userManager.getEntityById(userId);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		verify(entityManager, times(1)).find(User.class, userId);
		assertEquals(user, resultUser);
	}
}
