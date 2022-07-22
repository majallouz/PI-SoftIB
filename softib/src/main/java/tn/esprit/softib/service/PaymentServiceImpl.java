package tn.esprit.softib.service;

import java.time.LocalDate;
import java.util.Set;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.softib.repository.PaymentRepository;
import tn.esprit.softib.entity.Credit;
import tn.esprit.softib.entity.Operation;
import tn.esprit.softib.entity.Payment;
import tn.esprit.softib.enums.CreditStatus;
import tn.esprit.softib.repository.CreditRepository;


@Service
@Slf4j
public class PaymentServiceImpl implements IPaymentService {
	
	@Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private CreditRepository CreditRepository;

    @Autowired
    private CompteServiceImpl CompteService;
 

    

    @Override
    public String deletePayment(Integer id)  {
        if (paymentRepository.findById(id.longValue()).isPresent()) {
            paymentRepository.deleteById(id.longValue());
            return "Payment Deleted Successfully";
        } else
            return "Payment Not Found";
    }

    @Override
    public String updatePayment(Integer id, Payment newPayment) {
    	Optional<Payment> value = this.paymentRepository.findById(id.longValue());
        if (value.isPresent()) {
            Payment oldPayment = value.get();
            if (newPayment.getPaymentAmount() != null) {
                oldPayment.setPaymentAmount(newPayment.getPaymentAmount());
            }
            if (newPayment.getPaymentDate() != null) {
                oldPayment.setPaymentDate(newPayment.getPaymentDate());
            }
            if (newPayment.getPaymentInterest() != null) {
                oldPayment.setPaymentInterest(newPayment.getPaymentInterest());
            }
            if (newPayment.getPaymentDueDate() != null) {
                oldPayment.setPaymentDueDate(newPayment.getPaymentDueDate());
            }
            if (newPayment.getPenality() != null) {
                oldPayment.setPenality(newPayment.getPenality());
            }
            if(newPayment.getPaymentStatus() != null) {
            	oldPayment.setPaymentStatus(newPayment.getPaymentStatus());
            }
            paymentRepository.save(oldPayment);
            return "Payment Updated Successfully";
        } else {
            return "Payment Not Found";
        }
    }
    

    @Override
    public Payment getPayment(Integer id)  {
        return paymentRepository.findById(id.longValue()).get();
    }

    @Override
    public String pay(Integer creditId){
        StringBuilder msg = new StringBuilder();
        Optional<Credit> value = this.CreditRepository.findById(creditId.longValue());
        if (value.isPresent()) {
            Credit credit = value.get();
            if (credit.getCreditStatus() != null) {
            if(!credit.getCreditStatus().equals(CreditStatus.PAYED))
            {
                Set<Payment> payments = paymentRepository.findAllNotPayedPayments(credit, CreditStatus.CREATED);
                
                if(credit.getCreditTerm() != null ) {
                if(!payments.isEmpty()) {
                    
                	Iterator<Payment> paymentIterator = payments.iterator();
                    
                    Payment payment = paymentIterator.next();
                    payment.setPaymentStatus(CreditStatus.PAYED);
                    payment.setPaymentDate(LocalDate.now());
                    
                    if (credit.getCompte() != null) {
                    	if (credit.getCompte().getSolde() != null) {
                    if(credit.getCompte().getSolde().compareTo(new BigDecimal(payment.getPaymentAmount())) >= 0) {
                        payment.setPenality(0d);
                    } else {
                        payment.setPenality(0.03);
                        payment.setPaymentAmount(payment.getPaymentAmount()+(payment.getPaymentAmount()*payment.getPenality()));
                    }
                
                    paymentRepository.save(payment);
                    credit.setRemainingAmount(credit.getRemainingAmount()-payment.getPaymentAmount());
                    credit.setPayedAmount(credit.getPayedAmount()+payment.getPaymentAmount());
                } }
                    
                    if(!paymentIterator.hasNext()){
                        credit.setCreditStatus(CreditStatus.PAYED);
                    }
                    
                    CreditRepository.save(credit);
                    Operation operation = new Operation();
                    operation.setAmount(new BigDecimal(payment.getPaymentAmount()));
                    return ("Payment for the date "+ payment.getPaymentDueDate()+ " of "+ payment.getPaymentAmount() + " is "+payment.getPaymentStatus().toString()+ " With Interest "+payment.getPaymentInterest());
                }
                else {
                	log.warn("list of payment is empty" );
                    return ("List Of Payment Is Empty");
                }
            }
            else {
            	Double RA = credit.getRemainingAmount() ;
            	Double remaining = RA - 100.0 ;
            	credit.setRemainingAmount(remaining);
            	CreditRepository.save(credit);
                return ("Your credit Remaining Amount after payment " + remaining);
            }
            }
        } else 
        	log.warn("you have to specify a credit status");
        	return ("Please specify a Credit Status");
        }
        else {
            return ("Credit Not Found");
        }
    }
}
