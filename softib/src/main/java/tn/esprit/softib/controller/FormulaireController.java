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

import tn.esprit.softib.entity.Formulaire;
import tn.esprit.softib.entity.User;
import tn.esprit.softib.service.IFormulaireService;

@RestController
@RequestMapping("/formulaire")
public class FormulaireController {
	
	@Autowired
	IFormulaireService formulaireService;
	
	@GetMapping("/findAll")
	@ResponseBody
	public List<Formulaire> findAll(){
		List<Formulaire> forms = (List<Formulaire>) formulaireService.getAllFormulaires();
		return forms;
	}
	
	@GetMapping("/findByEmail/{email}")
	@ResponseBody
	public Formulaire findByEmail(@PathVariable("email") String email){
		Formulaire form = formulaireService.getFormulaireByEmail(email);
		return form;
	}
	
	@GetMapping("/findById/{id}")
	@ResponseBody
	public Formulaire findById(@PathVariable("id") Long id){
		Formulaire form = formulaireService.getFormulaireById(id);
		return form;
	}
	
	@PostMapping("/save")
	@ResponseBody
	public Formulaire save(@RequestBody Formulaire formulaire){
		Formulaire form = formulaireService.addFormulaire(formulaire);
		return form;
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable("id") Long id){
		formulaireService.deleteFormulaire(id);
	}
	
	@PutMapping("/update")
	@ResponseBody
	public Formulaire update(@RequestBody Formulaire newFrom){
		return formulaireService.updateFormulaire(newFrom);
	}
		
	@PostMapping("/confirmFormulaire/{id}")
	@ResponseBody
	public User confirmFormulaire(@PathVariable("id") Long id ) {
		return formulaireService.confirmFormulaire(id);
	}
	

}
