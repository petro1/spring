package users.service;

import java.util.List;
import users.models.User;

public interface UserManager {

	public boolean addEntity(User user) throws Exception;
	public User getEntityById(long id) throws Exception;
	public List<User> getEntityList() throws Exception;
	public boolean deleteEntity(User user) throws Exception;
	public void update(User user) throws Exception;	
}
