package tn.esprit.softib.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.softib.entity.Formulaire;
import tn.esprit.softib.entity.User;
import tn.esprit.softib.service.IFormulaireService;
import tn.esprit.softib.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	IUserService userService;
	
	@GetMapping("/findAll")
	@ResponseBody
	public List<User> findAll(){
		List<User> users = (List<User>) userService.getAllUsers();
		return users;
	}
	
	@GetMapping("/findByEmail/{email}")
	@ResponseBody
	public User findByEmail(@PathVariable("email") String email){
		User user = userService.getUserByEmail(email);
		return user;
	}
	
	@GetMapping("/findByCin/{cin}")
	@ResponseBody
	public User findByCin(@PathVariable("cin") String cin){
		User user = userService.getUserByCin(cin);
		return user;
	}
	
		
	@GetMapping("/findById/{id}")
	@ResponseBody
	public User findById(@PathVariable("id") Long id){
		User user = userService.getUserById(id);
		return user;
	}
	
	@PostMapping("/save")
	@ResponseBody
	public User save(@RequestBody User userBody){
		User user = userService.addUser(userBody);
		return user;
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable("id") Long id){
		userService.deleteUser(id);
	}
	
	@PutMapping("/update")
	@ResponseBody
	public User update(@RequestBody User newUser){
		return userService.updateUser(newUser);
	}
	
	@PutMapping("/signUser/{id}")
	@ResponseBody
	public User signUser(@PathVariable("id") Long id){
		return userService.signUser(id);
	}
	

}
