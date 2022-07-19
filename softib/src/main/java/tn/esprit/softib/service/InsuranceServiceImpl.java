package tn.esprit.softib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import tn.esprit.softib.entity.CreditRequest;
import tn.esprit.softib.entity.Insurance;
import tn.esprit.softib.repository.CreditRequestRepository;
import tn.esprit.softib.repository.InsuranceRepository;
import tn.esprit.softib.utility.SystemDeclarations;

import java.util.Date;

@Service
public class InsuranceServiceImpl implements IInsuranceService {
	
	
	@Autowired
    private InsuranceRepository insuranceRepository;
    @Autowired
    private CreditRequestRepository creditRequestRepository;

    @Override
    public String addInsurance(Integer creditRequestId) {
        if (creditRequestRepository.findById(creditRequestId.longValue()).isPresent()) {
            CreditRequest creditRequest = creditRequestRepository.findById(creditRequestId.longValue()).get();
            Insurance insurance = new Insurance();
            insurance.setType(creditRequest.getType());
            insurance.setCreationDate(new Date());
            insurance.setAmount(creditRequest.getCreditAmount()* SystemDeclarations.INSURANCE_PERCENTAGE);
            insurance.setBeneficiary("Bank Agent");
            creditRequest.setInsurance(insurance);
            insuranceRepository.save(insurance);
            creditRequestRepository.save(creditRequest);
            return "Insurance With Amount "+ insurance.getAmount() +" Added To Credit Request Successfully";
        }
        return "Insurance Cannot Be Added : Credit Request Not Found";
    }

    @Override
    public String deleteInsurance(Integer id)  {
        if (insuranceRepository.findById(id.longValue()).isPresent()) {
            insuranceRepository.deleteById(id.longValue());
            return "Insurance Deleted Successfully";
        } else
            return "Insurance Not Found";
    }

    @Override
    public String updateInsurance(Insurance newInsurance)  {
            Insurance oldInsurance = insuranceRepository.findById(newInsurance.getId()).orElse(null);
            if (newInsurance.getAmount() != null) {
                oldInsurance.setAmount(newInsurance.getAmount());
            }
            if (newInsurance.getExpiryDate() != null) {
                oldInsurance.setExpiryDate(newInsurance.getExpiryDate());
            }
            if (newInsurance.getType() != null) {
                oldInsurance.setType(newInsurance.getType());
            }
            if (newInsurance.getBeneficiary() != null) {
                oldInsurance.setBeneficiary(newInsurance.getBeneficiary());
            }

            insuranceRepository.save(oldInsurance);
            return "Insurance Updated Successfully";
        } 
    

    @Override
    public Insurance getInsurance(Integer id)  {
        return insuranceRepository.findById(id.longValue()).get();
    }

}
