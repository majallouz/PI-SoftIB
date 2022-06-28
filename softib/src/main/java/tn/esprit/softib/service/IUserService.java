package tn.esprit.softib.service;

import java.util.List;

import tn.esprit.softib.entity.User;

public interface IUserService {
	public List<User> getAllUsers();
	public User getUserById(long id);
	public User getUserByEmail(String email);
	public User getUserByCin(String cin);
	public User addUser(User user);
	public void deleteUser(long id);
	public User updateUser(User user);
	public User signUser(long id);
	
}
