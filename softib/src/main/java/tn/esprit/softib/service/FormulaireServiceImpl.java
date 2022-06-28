package tn.esprit.softib.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.softib.entity.Formulaire;
import tn.esprit.softib.entity.User;
import tn.esprit.softib.enums.Gender;
import tn.esprit.softib.enums.Nature;
import tn.esprit.softib.repository.FormulaireRepository;
import tn.esprit.softib.repository.UserRepository;

@Service
public class FormulaireServiceImpl implements IFormulaireService {

	@Autowired
	FormulaireRepository formulaireRepository;

	@Autowired
	IUserService userService;

	@Override
	public List<Formulaire> getAllFormulaires() {
		return formulaireRepository.findAll();
	}

	@Override
	public Formulaire getFormulaireById(long id) {
		return formulaireRepository.findById(id).orElse(null);
	}

	@Override
	public Formulaire getFormulaireByEmail(String email) {
		return formulaireRepository.findByEmail(email).orElse(null);
	}

	@Override
	public Formulaire addFormulaire(Formulaire formulaire) {
		return formulaireRepository.save(formulaire);
	}

	@Override
	public void deleteFormulaire(long id) {
		formulaireRepository.deleteById(id);

	}

	@Override
	@Transactional
	public Formulaire updateFormulaire(Formulaire formulaire) {
		Formulaire oldFormulaire = formulaireRepository.findById(formulaire.getId()).orElse(null);
		if (oldFormulaire != null) {
			if (formulaire.getFirstName() != null && !("".equals(oldFormulaire.getFirstName().trim()))
					&& !(oldFormulaire.getFirstName().equals(formulaire.getFirstName()))) {
				oldFormulaire.setFirstName(formulaire.getFirstName());
			}
			if (formulaire.getLastName() != null && !("".equals(oldFormulaire.getLastName().trim()))
					&& !(oldFormulaire.getLastName().equals(formulaire.getLastName()))) {
				oldFormulaire.setLastName(formulaire.getLastName());
			}
			if (formulaire.getPhone() != null && !("".equals(oldFormulaire.getPhone()))
					&& !(oldFormulaire.getPhone().equals(formulaire.getPhone()))) {
				oldFormulaire.setPhone(formulaire.getPhone());
			}
			if (formulaire.getGender() != null && !("".equals(oldFormulaire.getGender()))
					&& !(oldFormulaire.getGender().equals(formulaire.getGender()))) {
				oldFormulaire.setGender(formulaire.getGender());
			}
			if (formulaire.getAdresse() != null && !("".equals(oldFormulaire.getAdresse().trim()))
					&& !(oldFormulaire.getAdresse().equals(formulaire.getAdresse()))) {
				oldFormulaire.setAdresse(formulaire.getAdresse());
			}
			if (formulaire.getEmail() != null && !("".equals(oldFormulaire.getEmail().trim()))
					&& !(oldFormulaire.getEmail().equals(formulaire.getEmail()))) {
				oldFormulaire.setEmail(formulaire.getEmail());
			}
			if (formulaire.getNatureCompte() != null && !("".equals(oldFormulaire.getNatureCompte()))
					&& !(oldFormulaire.getNatureCompte().equals(formulaire.getNatureCompte()))) {
				oldFormulaire.setNatureCompte(formulaire.getNatureCompte());
			}
			if (formulaire.getSalaireNet() != 0.0f && oldFormulaire.getSalaireNet() != formulaire.getSalaireNet()) {
				oldFormulaire.setSalaireNet(formulaire.getSalaireNet());
			}
			if (formulaire.getJob() != null && !("".equals(oldFormulaire.getJob().trim()))
					&& !(oldFormulaire.getJob().equals(formulaire.getJob()))) {
				oldFormulaire.setJob(formulaire.getJob());
			}
			if (formulaire.getCin() != null) {
				oldFormulaire.setCin(formulaire.getCin());
			}
			if (formulaire.getType() != null ) {
				oldFormulaire.setType(formulaire.getType());
			}
			formulaireRepository.save(oldFormulaire);
			return oldFormulaire;
		}
		return null;
	}

	/**
	 * 
	 */
	@Override
	public User confirmFormulaire(long id) {
		Formulaire formulaire = getFormulaireById(id);
		User user = userService.getUserByEmail(formulaire.getEmail());
		if (user!=null
				/*&& checkNatureForUser(formulaire.getNatureCompte())*/) {
			
		} else {
			if (user == null) {
				User newUser = mapFormulaireToUser(formulaire);
				userService.addUser(newUser);
			}
		}
		
		return user;
	}

	private boolean checkNatureForUser(Nature natureCompte) {
		// TODO Auto-generated method stub
		return false;
	}

	private User mapFormulaireToUser(Formulaire formulaire) {
		User user = new User();
		user.setCin(formulaire.getCin());
		user.setFirstName(formulaire.getFirstName());
		user.setLastName(formulaire.getLastName());
		user.setPhone(formulaire.getPhone());
		user.setGender(formulaire.getGender());
		user.setAdresse(formulaire.getAdresse());
		user.setEmail(formulaire.getEmail());
		user.setSalaireNet(formulaire.getSalaireNet());
		user.setJob(formulaire.getJob());
		user.setType(formulaire.getType());
		user.setIsSigned(false);
		//SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
		Date date = new Date();  
		user.setCreationDate(date);
		return user;
	}

}
