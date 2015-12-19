package pl.training.bank.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import pl.training.bank.Bank;
import pl.training.bank.SpringBank;
import pl.training.bank.service.AccountNumberGenerator;
import pl.training.bank.service.ConsoleOperationLogger;
import pl.training.bank.service.SequentialAccountNumberGenerator;
import pl.training.bank.service.repository.AccountsRepository;

@Import(Persistence.class)
@EnableAspectJAutoProxy
@Configuration
public class BeansConfig {

    @Bean
    public AccountNumberGenerator accountNumberGenerator(SessionFactory sessionFactory) {
        return new SequentialAccountNumberGenerator(sessionFactory);
    }

    @Bean
    public Bank bank(AccountNumberGenerator accountNumberGenerator, AccountsRepository accountsRepository) {
        return new SpringBank(accountNumberGenerator, accountsRepository);
    }

    @Bean
    public ConsoleOperationLogger operationLogger() {
        return new ConsoleOperationLogger();
    }

}
