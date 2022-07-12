package tn.esprit.softib.service;

import java.util.List;

import tn.esprit.softib.entity.Compte;

public interface ICompteService {
	
	public List<Compte> getAllComptes();
	public Compte getCompteById(long id);
	public Compte addCompte(Compte compte);
	public void deleteCompte(long id);
	public Compte updateCompte(Compte compte);


}
