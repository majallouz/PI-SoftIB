package tn.esprit.softib.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.softib.Response.ResponseMessage;
import tn.esprit.softib.entity.Compte;
import tn.esprit.softib.entity.User;
import tn.esprit.softib.enums.Nature;
import tn.esprit.softib.repository.CompteRepository;
import tn.esprit.softib.repository.UserRepository;

@Service
public class CompteServiceImpl implements ICompteService {
	@Autowired
	CompteRepository compteRepository;
	@Autowired
	UserRepository userRepository;

	
	@Override
	public List<Compte> getAllComptes() {
		return compteRepository.findAll();
	}


	@Override
	public Compte getCompteById(long id) {
		// TODO Auto-generated method stub
		return compteRepository.findById(id).orElse(null);
		
	}

	@Override
	public Compte addCompte(Compte newCompte) {
		// TODO Auto-generated method stub
		return compteRepository.save(newCompte);
	}


	@Override
	public void deleteCompte(long id) {
		compteRepository.deleteById(id);

	}

	@Override
	public Compte updateCompte(Compte compte) {
		// TODO Auto-generated method stub
		Compte oldCompte = compteRepository.findById(compte.getId()).orElse(null);
		if (compte.getNomComplet()!=null 
				&& !("".equals(compte.getNomComplet().trim()))
				&& !(oldCompte.getNomComplet().equals(compte.getNomComplet()))) {
			oldCompte.setNomComplet(compte.getNomComplet());
		}
		if (compte.getNatureCompte()!=null 
				&& oldCompte.getNatureCompte() != compte.getNatureCompte()) {
			oldCompte.setNatureCompte(compte.getNatureCompte());
		}
		if (compte.getRib()!=null 
				&& !("".equals(compte.getRib().trim()))
				&& !(oldCompte.getRib().equals(compte.getRib()))) {
			oldCompte.setRib(compte.getRib());
		}
		if (compte.getIban()!=null 
				&& !("".equals(compte.getIban().trim()))
				&& !(oldCompte.getIban().equals(compte.getIban()))) {
			oldCompte.setIban(compte.getIban());
		}
		if (compte.getCodeBic()!=null 
				&& !("".equals(compte.getIban().trim()))
				&& !(oldCompte.getCodeBic().equals(compte.getCodeBic()))) {
			oldCompte.setCodeBic(compte.getCodeBic());
		}
		if ( compte.getSolde()!=new BigDecimal(0)
				&& oldCompte.getSolde() != compte.getSolde()) {
			oldCompte.setSolde(compte.getSolde());
		}
		compteRepository.save(compte);

        return compte;
	}
	 public Compte store(long id, MultipartFile file) throws IOException {
		    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		    Compte newCompte = getCompteById(id);
		    newCompte.setData(file.getBytes());
		    return compteRepository.save(newCompte);
		  }


	@Override
	public ResponseEntity<ResponseMessage> verifCardType(long id) {
		// TODO Auto-generated method stub
		User user = userRepository.getById(id);
		float salaire = user.getSalaireNet();
		if(salaire < 1000) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("You can get a PINK CARD "
					+ "with bank limit "+ salaire * 0.2 +" per week"));
		} else if(salaire < 2500) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("You can get a GREEN CARD "
					+ "with bank limit "+ salaire * 0.2 +" per week"));
		} else if(salaire < 4500) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("You can get a RED CARD "
					+ "with bank limit "+ salaire * 0.2 +" per week"));
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("You can get a GOLDEN CARD "
					+ "with bank limit "+ salaire * 0.2 +" per week"));
		}
		
	}

	

	 
	 
	



}
