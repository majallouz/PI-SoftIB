package tn.esprit.softib.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.softib.entity.Compte;
import tn.esprit.softib.entity.User;
import tn.esprit.softib.enums.ERole;
import tn.esprit.softib.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	UserRepository userRepo;
	@Autowired
	PasswordEncoder encoder;
	

	@Override
	public List<User> getAllUsers() {
		return  userRepo.findAll();
	}

	@Override
	public User getUserById(long id) {
		return userRepo.findById(id).orElse(null);
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepo.findByEmail(email).orElse(null);
	}

	@Override
	public User getUserByCin(String cin) {
		return userRepo.findByCin(cin).orElse(null);
	}

	@Override
	public User addUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public void deleteUser(long id) {
		userRepo.deleteById(id);

	}

	@Override
	@Transactional
	public User updateUser(User user) {
		User oldUser = userRepo.findById(user.getId()).orElse(null);
		if (user.getEmail()!=null 
				&& !("".equals(user.getEmail().trim()))
				&& !(oldUser.getEmail().equals(user.getEmail()))) {
			oldUser.setEmail(user.getEmail());
		}
		if (user.getPhone()!=null 
				&& user.getPhone()!=0L
				&& oldUser.getPhone() != user.getPhone()) {
			oldUser.setPhone(user.getPhone());
		}
		if (user.getAdresse()!=null 
				&& !("".equals(user.getAdresse().trim()))
				&& !(oldUser.getAdresse().equals(user.getAdresse()))) {
			oldUser.setAdresse(user.getAdresse());
		}
		if (user.getJob()!=null 
				&& !("".equals(user.getJob().trim()))
				&& !(oldUser.getJob().equals(user.getJob()))) {
			oldUser.setAdresse(user.getJob());
		}
		if ( user.getSalaireNet()!=0.0f
				&& oldUser.getSalaireNet() != user.getSalaireNet()) {
			oldUser.setSalaireNet(user.getSalaireNet());
		}
			
		userRepo.save(oldUser);
		return oldUser;
	}

	@Override
	@Transactional
	public User signUser(long id) {
		User user = this.getUserById(id);
		user.setIsSigned(Boolean.TRUE);
		user.setPassword(encoder.encode("userPwd"));
		//mail 
		
		return user;
	}
	
	@Override
	public User getUserByUsername(String username) {
		User user = userRepo.findByUsername(username).orElse(null);
		return user;
	}

	@Override
	@Transactional
	public User banUser(String username, String banRaison) {
		User user = this.getUserByUsername(username);
		user.setIsBanned(true);
		user.setBanRaison(banRaison);
				
		return user;
	}

	@Override
	public List<User> getClients() {
		List<User> clients = userRepo.findClients(ERole.ROLE_CLIENT);
		return clients;
	}

	

	@Override
	public Compte deleteAutoUser() {
		return userRepo.deleteAutoUser();
		
	}

}
