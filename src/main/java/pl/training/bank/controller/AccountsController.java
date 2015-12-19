package pl.training.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.training.bank.Bank;
import pl.training.bank.entity.Account;

@Controller
public class AccountsController {
    
    @Autowired
    private Bank bank;

    public void setBank(Bank bank) {
        this.bank = bank;
    }
    
    @RequestMapping(value = "createAccount")
    public ModelAndView createAccount() {
        Account account = bank.createAccount();
        ModelAndView modelAndView = new ModelAndView("createAccountConfirmation");
        modelAndView.addObject(account);
        return modelAndView;
    }
    
}
