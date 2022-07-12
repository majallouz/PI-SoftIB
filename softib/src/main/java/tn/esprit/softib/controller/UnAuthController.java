package tn.esprit.softib.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.softib.entity.Formulaire;
import tn.esprit.softib.service.IFormulaireService;

@RestController
@RequestMapping("/api/unauth")
public class UnAuthController {

	@Autowired
	IFormulaireService formulaireService;
	
	@PostMapping("/createFormulaire")
	@ResponseBody
	//@PreAuthorize("hasRole('CLIENT') or hasRole('ADMIN')")
	public Formulaire save(@RequestBody Formulaire formulaire){
		Formulaire form = formulaireService.addFormulaire(formulaire);
		return form;
	}
}
