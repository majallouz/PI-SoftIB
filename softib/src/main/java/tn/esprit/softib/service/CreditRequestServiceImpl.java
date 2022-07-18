package tn.esprit.softib.service;

import java.util.Date;
import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.softib.entity.Credit;
import tn.esprit.softib.enums.CreditStatus;
import tn.esprit.softib.enums.TypeCredit;
import tn.esprit.softib.entity.CreditRequest;
import tn.esprit.softib.entity.Insurance;
import tn.esprit.softib.repository.CreditRequestRepository;
import tn.esprit.softib.repository.InsuranceRepository;
import tn.esprit.softib.utility.SystemDeclarations;

@Service
public class CreditRequestServiceImpl implements ICreditRequestService {
	
	@Autowired
    private CreditRequestRepository creditRequestRepository;

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private CreditServiceImpl creditService;

    @Autowired
    private CompteServiceImpl CompteService;
  

    
    @Override
    public CreditRequest addCreditRequest(CreditRequest creditRequest, Integer id)  {
        creditRequest.setCreationDate(new Date());
        creditRequest.setCreditRequestStatus(CreditStatus.CREATED);
        creditRequest.setCompte(CompteService.getCompteById(id.longValue()));
        return creditRequestRepository.save(creditRequest);
    }

    @Override
    public String deleteCreditRequest(Integer id)  {
        if (creditRequestRepository.findById(id.longValue()).isPresent()) {
            creditRequestRepository.deleteById(id.longValue());
            return "Credit Request Deleted Successfully";
        } else
            return "Credit Request Not Found";
    }

    @Override
    public String updateCreditRequest(Integer id, CreditRequest newCreditRequest)  {
        if (creditRequestRepository.findById(id.longValue()).isPresent()) {
            CreditRequest oldCreditRequest = creditRequestRepository.findById(id.longValue()).get();
            if (newCreditRequest.getCin() != null) {
                oldCreditRequest.setCin(newCreditRequest.getCin());
            }
            if (newCreditRequest.getCreditTerm() != null) {
                oldCreditRequest.setCreditTerm(newCreditRequest.getCreditTerm());
            }
            if (newCreditRequest.getCreditAmount() != null) {
                oldCreditRequest.setCreditAmount(newCreditRequest.getCreditAmount());
            }
            if (newCreditRequest.getCreditRepayment() != null) {
                oldCreditRequest.setCreditRepayment(newCreditRequest.getCreditRepayment());
            }
            if (newCreditRequest.getCreditRepaymentAmount() != null) {
                oldCreditRequest.setCreditRepaymentAmount(newCreditRequest.getCreditRepaymentAmount());
            }
            if (newCreditRequest.getAddress() != null) {
                oldCreditRequest.setAddress(newCreditRequest.getAddress());
            }
            if (newCreditRequest.getCivilState() != null) {
                oldCreditRequest.setCivilState(newCreditRequest.getCivilState());
            }
            if (newCreditRequest.getJob() != null) {
                oldCreditRequest.setJob(newCreditRequest.getJob());
            }
            if (newCreditRequest.getAge() != null) {
                oldCreditRequest.setAge(newCreditRequest.getAge());
            }
            if (newCreditRequest.getCreditRequestStatus() != null) {
                oldCreditRequest.setCreditRequestStatus(newCreditRequest.getCreditRequestStatus());
            }
            creditRequestRepository.save(oldCreditRequest);
            return "Credit Request Updated Successfully";
        } else {
            return "Credit Request Not Found";
        }
    }

    @Override
    public CreditRequest getCreditRequest(Integer id) {
        return creditRequestRepository.findById(id.longValue()).get();
    }

    @Override
    public CreditRequest rejectCreditRequest(Integer id) {
        if (creditRequestRepository.findById(id.longValue()).isPresent()) {
            CreditRequest creditRequest = creditRequestRepository.findById(id.longValue()).get();
            if (!creditRequest.getCreditRequestStatus().toString().equals(CreditStatus.CONFIRMED.toString())) {
                creditRequest.setCreditRequestStatus(CreditStatus.WAITINGFORCLIENTACCEPTANCE);
                suggestCreditRequest(creditRequest);
                return creditRequest;
            }
        }
        return null;
    }

    @Override
    public CreditRequest acceptCreditRequestChanges(Integer id)  {
        if (creditRequestRepository.findById(id.longValue()).isPresent()) {
            CreditRequest creditRequest = creditRequestRepository.findById(id.longValue()).get();
            if (creditRequest.getCreditRequestStatus().toString().equals(CreditStatus.WAITINGFORCLIENTACCEPTANCE.toString())) {
                creditRequest.setCreditRequestStatus(CreditStatus.ACCEPTED);
                creditRequest.setRejectionReason(null);
                creditRequestRepository.save(creditRequest);
                return creditRequest;
            }
        }
        return null;
    }

    @Override
    public Credit createCreditFromCreditRequest(Integer id)  {
        if (creditRequestRepository.findById(id.longValue()).isPresent()) {
            CreditRequest creditRequest = creditRequestRepository.findById(id.longValue()).get();
            if (creditRequest.getCreditRequestStatus().toString().equals(CreditStatus.VALIDATED.toString())) {
                creditRequest.setCreditRequestStatus(CreditStatus.CONFIRMED);
                creditRequestRepository.save(creditRequest);
                return creditService.addCredit(mapCreditFromCreditRequest(creditRequest));
            }
        }
        return null;
    }

    @Override
    public Credit mapCreditFromCreditRequest(CreditRequest creditRequest) {
        Credit credit = new Credit();
        credit.setCreditStatus(CreditStatus.CREATED);
        credit.setCreditAmount(creditRequest.getCreditAmount());
        credit.setCreditTerm(creditRequest.getCreditTerm());
        credit.setCreditRepayment(creditRequest.getCreditRepayment());
        credit.setCreditRepaymentAmount(creditRequest.getCreditRepaymentAmount());
        credit.setType(creditRequest.getType());
        credit.setCreationDate(LocalDate.now());
        credit.setCreditInterest(SystemDeclarations.CREDIT_INTEREST);
        credit.setCreditRepaymentInterest(SystemDeclarations.CREDIT_INTEREST);
        credit.setCreditFees(SystemDeclarations.CREDIT_FEES);
        credit.setCreditRequest(creditRequest);
        credit.setAgent("BankAgent");
        credit.setReleaseDate(credit.getCreationDate().plusDays(15));
        credit.setCompte(creditRequest.getCompte());
        return credit;
    }

    @Override
    public CreditRequest treatCreditRequest(Integer id) {
        if (creditRequestRepository.findById(id.longValue()).isPresent()) {
            CreditRequest creditRequest = creditRequestRepository.findById(id.longValue()).get();
            creditRequest = checkEligibaleCreditRequest(creditRequest);
            return creditRequestRepository.save(creditRequest);

        }
        return null;
    }

    @Override
    public CreditRequest suggestCreditRequest(CreditRequest creditRequest) {

        String rejectionReason = creditRequest.getRejectionReason();
        if (rejectionReason.contains(SystemDeclarations.CREDIT_INSURANCE_NULL)) {
            Insurance insurance = new Insurance();
            insurance.setType(creditRequest.getType());
            insurance.setCreationDate(new Date());
            insurance.setAmount(creditRequest.getCreditAmount() * SystemDeclarations.INSURANCE_PERCENTAGE);
            insurance.setBeneficiary(creditRequest.getCompte().getNomComplet());
            insuranceRepository.save(insurance);
            creditRequest.setInsurance(insurance);
        }
        if (rejectionReason.contains(SystemDeclarations.CREDIT_CONSUMPTION_CREDIT_TERM_EXCEEDED)) {
            creditRequest.setCreditTerm(36);
        }
        if (rejectionReason.contains(SystemDeclarations.CREDIT_CAR_CREDIT_TERM_EXCEEDED)) {
            creditRequest.setCreditTerm(87);
        }
        if (rejectionReason.contains(SystemDeclarations.CREDIT_HOME_CREDIT_TERM_EXCEEDED)) {
            creditRequest.setCreditTerm(300);
        }
        if (rejectionReason.contains(SystemDeclarations.CREDIT_CONSUMPTION_AMOUNT_EXCEEDED)) {
            creditRequest.setCreditTerm(20000);
        }
        if (rejectionReason.contains(SystemDeclarations.CREDIT_NOT_ENOUGH_SALARY)) {
            Double creditAmount = creditRequest.getCreditAmount()
                    + (creditRequest.getCreditAmount() * SystemDeclarations.CREDIT_INTEREST)
                    + creditRequest.getInsurance().getAmount() + SystemDeclarations.CREDIT_FEES;
            Double netSalary = creditRequest.getNetSalary();
            Double amountToPay = 0d;
            Double possiblePercent = 0d;
            Integer newCreditTerm = creditRequest.getCreditTerm();
            boolean check = false;
            for (int i =creditRequest.getCreditTerm(); i< 36; i++){
                amountToPay = creditAmount / i;
                possiblePercent = amountToPay / netSalary;
                if(possiblePercent <= 0.4){
                    newCreditTerm = i;
                    check = true;
                    break;
                }
            }
            if(check){
                creditRequest.setCreditTerm(newCreditTerm);
            }
            else{
                creditRequest.setRejectionReason(SystemDeclarations.CREDIT_NOT_POSSIBLE);
                creditRequest.setCreditRequestStatus(CreditStatus.CLOSED);
            }
        }
        return creditRequestRepository.save(creditRequest);
    }


    @Override
    public CreditRequest checkEligibaleCreditRequest(CreditRequest creditRequest) {
        StringBuilder rejectionReason = new StringBuilder();
        boolean check = true;
        if (creditRequest.getInsurance() == null) {
            rejectionReason.append(SystemDeclarations.CREDIT_INSURANCE_NULL);
            check = false;
        }
        if (calculateAmountToPayForSalary(creditRequest) >= 0.4) {
            if (rejectionReason.length() > 0) {
                rejectionReason.append(", ");
            }
            rejectionReason.append(SystemDeclarations.CREDIT_NOT_ENOUGH_SALARY);
            check = false;
        }
        if (creditRequest.getType().toString().equals(TypeCredit.CONSUMPTION.toString())) {
            if (creditRequest.getCreditAmount() > 20000) {
                check = false;
                if (rejectionReason.length() > 0) {
                    rejectionReason.append(", ");
                }
                rejectionReason.append(SystemDeclarations.CREDIT_CONSUMPTION_AMOUNT_EXCEEDED);
            }
            if (creditRequest.getCreditTerm() > 36) {
                check = false;
                if (rejectionReason.length() > 0) {
                    rejectionReason.append(", ");
                }
                rejectionReason.append(SystemDeclarations.CREDIT_CONSUMPTION_CREDIT_TERM_EXCEEDED);
            }
        }
        if (creditRequest.getType().toString().equals(TypeCredit.CAR.toString())) {
            if (creditRequest.getCreditTerm() > 87) {
                check = false;
                if (rejectionReason.length() > 0) {
                    rejectionReason.append(", ");
                }
                rejectionReason.append(SystemDeclarations.CREDIT_CAR_CREDIT_TERM_EXCEEDED);
            }
        }
        if (creditRequest.getType().toString().equals(TypeCredit.HOME.toString())) {
            if (creditRequest.getCreditTerm() > 300) {
                check = false;
                if (rejectionReason.length() > 0) {
                    rejectionReason.append(", ");
                }
                rejectionReason.append(SystemDeclarations.CREDIT_HOME_CREDIT_TERM_EXCEEDED);
            }
        }
        if (check) {
            creditRequest.setCreditRequestStatus(CreditStatus.VALIDATED);
            creditRequest.setRejectionReason(null);
        } else {
            creditRequest.setCreditRequestStatus(CreditStatus.REJECTED);
            creditRequest.setRejectionReason(rejectionReason.toString());
        }
        return creditRequest;
    }
    
    
    @Override
    public Double calculateAmountToPayForSalary(CreditRequest creditRequest) {
        Integer creditTerm = creditRequest.getCreditTerm();
        Double creditAmount = 0d;
        if(creditRequest.getInsurance() != null) {
            creditAmount = creditRequest.getCreditAmount() + (creditRequest.getCreditAmount() * SystemDeclarations.CREDIT_INTEREST) + creditRequest.getInsurance().getAmount() + SystemDeclarations.CREDIT_FEES;
        } else {
            creditAmount = creditRequest.getCreditAmount() + (creditRequest.getCreditAmount() * SystemDeclarations.CREDIT_INTEREST)+ SystemDeclarations.CREDIT_FEES;

        }
        Double netSalary = creditRequest.getNetSalary();
        Double amountToPay = creditAmount / creditTerm;
        return amountToPay / netSalary;
    }

    @Override
    public Set<CreditRequest> getAllCreditRequestAcceptedFromClients(){
        return creditRequestRepository.findAllCreditRequestWithStatus(CreditStatus.ACCEPTED);
    }

}
