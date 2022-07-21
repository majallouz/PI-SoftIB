package tn.esprit.softib.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.softib.service.LoanSimulatorService;

@RestController
@RequestMapping(value = "/api/simulator")
public class LoanSimulatorController {
    @Autowired
    LoanSimulatorService loanSimulatorService;

    @PostMapping(value = "/otherSimulator")
    @ResponseBody
    public double simpleLoan(@RequestParam double loanAmount, double intrestRate, double months) {
        return loanSimulatorService.calculateCreditMentuality(loanAmount, intrestRate, months);

    }

    @PostMapping(value = "/carSimulator")
    @ResponseBody
    public double carLoan(@RequestParam double loanAmount, double months) {
        return loanSimulatorService.calculateCarCreditMentuality(loanAmount, months);
    }

    @PostMapping(value = "/homeSimulator")
    @ResponseBody
    public double homeLoan(@RequestParam double loanAmount, double months) {
        return loanSimulatorService.calculateHomeCreditMentuality(loanAmount, months);
    }
}
