package tn.esprit.softib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.softib.repository.LoanSimulatorRepository;
import tn.esprit.softib.util.SystemMessages;
import tn.esprit.softib.exception.SoftIBInvalidArguementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class LoanSimulatorService {
	  private static final Logger LOG = LoggerFactory.getLogger(LoanSimulatorService.class);
	    @Autowired
	    private LoanSimulatorRepository loanSimulatorRepository;

	    public double calculateCreditMentuality(double loanAmount, double intrestRate, double months) {
	        double t = intrestRate / 100;
	        double t1 = loanAmount * t / 12;
	        double t2 = 1 - Math.pow(1 + t / 12, -months);
	        return t1 / t2;
	    }

	    public double calculateCarCreditMentuality(double loanAmount, double months) {
	        double intrestRate = 0;
	        if (months < 36) {
	            LOG.error("Car loan should be on 3 years or more");
	            throw new SoftIBInvalidArguementException(SystemMessages.CAR_LOAN_MIN_YEARS);
	        } else if (months == 36) {
	            intrestRate = 4.21;
	        } else if (months <= 48 && months >= 37) {
	            intrestRate = 4.31;
	        } else if (months <= 60 && months >= 49) {
	            intrestRate = 4.37;
	        } else if (months <= 72 && months >= 61) {
	            intrestRate = 4.45;
	        } else if (months > 72) {
	            LOG.error("Car loan couldn't be on more than 6 years ");
	            throw new SoftIBInvalidArguementException(SystemMessages.CAR_LOAN_MAX_YEARS);
	        }
	        double t = intrestRate / 100;
	        double t1 = loanAmount * t / 12;
	        double t2 = 1 - Math.pow(1 + t / 12, -months);
	        return t1 / t2;
	    }

	    public double calculateHomeCreditMentuality(double loanAmount, double months) {
	        double intrestRate = 0;
	
	        if ((months / 12) < 15) {
	            LOG.error("Home loan should be on more than 15 years ");
	            throw new SoftIBInvalidArguementException(SystemMessages.HOME_LOAN_MIN_YEARS);
	        } else if ((months / 12) == 15) {
	            intrestRate = 2.5;
	        } else if ((months / 12) <= 30 && (months / 12) >= 16) {
	            intrestRate = 3.2;
	        } else if ((months / 12) > 30) {
	            LOG.error("Home loan couldn't be on more than 30 years ");
	            throw new SoftIBInvalidArguementException(SystemMessages.HOME_LOAN_MAX_YEARS);
	        }
	        double t = intrestRate / 100;
	        double t1 = loanAmount * t / 12;
	        double t2 = 1 - Math.pow(1 + t / 12, -months);
	        return t1 / t2;
	    }

}
