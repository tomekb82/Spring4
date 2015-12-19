package pl.training.bank.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.training.bank.entity.Account;
import pl.training.bank.service.repository.AccountsRepository;

@RestController
public class AccountsRestService {
    
    @Autowired
    private AccountsRepository accountsRepository;
    
    @RequestMapping(value = "rest/accounts", method = RequestMethod.GET)
    public List<Account> getAll() {
       return accountsRepository.findAll();
    }
    
    @RequestMapping(value = "rest/accounts/{id}", method = RequestMethod.GET)
    public Account getById(@PathVariable Long id) {
        return accountsRepository.getOne(id);
    }
    
    
}
