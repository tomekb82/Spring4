package pl.training.bank.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.training.bank.Bank;
import pl.training.bank.BankException;
import pl.training.bank.entity.Operation;
import pl.training.bank.service.repository.AccountsRepository;

@Controller
public class OperationsController {
    
    private Bank bank;
    private AccountsRepository accountsRepository;
    
    @Autowired
    public OperationsController(Bank bank, AccountsRepository accountsRepository) {
        this.bank = bank;
        this.accountsRepository = accountsRepository;
    }
    
    @RequestMapping(value = "operationForm", method = RequestMethod.GET)
    public ModelAndView prepareOperationForm() {
        ModelAndView modelAndView = new ModelAndView("operationForm");
        modelAndView.addObject(new Operation());
        return modelAndView;
    }
    
    @RequestMapping(value = "processOperation", method = RequestMethod.POST)
    public ModelAndView processOperation(@Valid Operation operation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("operationForm");
        }
        
        String accountNumber = operation.getAccountNumber();
        if (accountNumber.length() < 26) {
            operation.setAccountNumber(String.format("%026d", Long.valueOf(accountNumber)));
        }
        bank.processOperation(operation);
        ModelAndView modelAndView = new ModelAndView("processOperationConfirmation");
        modelAndView.addObject(operation);
        modelAndView.addObject(accountsRepository.getByNumber(operation.getAccountNumber()));
        return modelAndView;
    }
    
    @ExceptionHandler(BankException.class)
    public ModelAndView onBankException(BankException bankException) {
        return new ModelAndView("error", "errorType", bankException.getClass()
                .getSimpleName());
    }
    
}
