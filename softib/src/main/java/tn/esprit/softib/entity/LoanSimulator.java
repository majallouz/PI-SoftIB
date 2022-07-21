package tn.esprit.softib.entity;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import tn.esprit.softib.enums.TypeCredit;

@Entity
public class LoanSimulator {
	 @Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	    @Column(name = "id", nullable = false, updatable = false)
	    private Long id;
	    private double loanAmount;
	    private int salary;
	    private double intrestRate;
	    private double months;
	    private TypeCredit type;


	    public LoanSimulator() {
	    }

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public double getLoanAmount() {
	        return loanAmount;
	    }

	    public void setLoanAmount(int loanAmount) {
	        this.loanAmount = loanAmount;
	    }

	    public int getSalary() {
	        return salary;
	    }

	    public void setSalary(int salary) {
	        this.salary = salary;
	    }

	    public double getIntrestRate() {
	        return intrestRate;
	    }

	    public void setIntrestRate(double intrestRate) {
	        this.intrestRate = intrestRate;
	    }

	    public double getMonths() {
	        return months;
	    }

	    public void setMonths(double months) {
	        this.months = months;
	    }

	    public TypeCredit getType() {
	        return type;
	    }

	    public void setType(TypeCredit type) {
	        this.type = type;
	    }
	}


