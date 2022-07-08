package tn.esprit.softib.controller;

import java.util.List;
import java.util.Set;

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

import tn.esprit.softib.entity.Compte;
import tn.esprit.softib.service.ICompteService;


@RestController
@RequestMapping("/compte")
public class CompteController {
	
	@Autowired
	ICompteService compteService;
	
	@GetMapping("/findAll")
	@ResponseBody
	public List<Compte> findAll(){
		List<Compte> comptes = (List<Compte>) compteService.getAllComptes();
		return comptes;
	}
	
	@GetMapping("/findById/{id}")
	@ResponseBody
	public Compte findById(@PathVariable("id") Long id){
		Compte compte = compteService.getCompteById(id);
		return compte;
	}
	
	@PostMapping("/save")
	@ResponseBody
	public Compte save(@RequestBody Compte compte){
		Compte compteResult = compteService.addCompte(compte);
		return compteResult;
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable("id") Long id){
		compteService.deleteCompte(id);
	}
	
	@PutMapping("/update")
	@ResponseBody
	public Compte update(@RequestBody Compte compte){
		return compteService.updateCompte(compte);
	}
		
	

}
