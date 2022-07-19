package tn.esprit.softib.service;

import tn.esprit.softib.entity.Insurance;

public interface IInsuranceService {
	public String addInsurance(Integer creditRequestId);
	public String deleteInsurance(Integer id);
	public String updateInsurance(Insurance newInsurance);
	public Insurance getInsurance(Integer id);

}
