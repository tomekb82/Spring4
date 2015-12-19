package pl.training.bank.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import pl.training.bank.service.repository.AccountsRepository;
import pl.training.bank.service.repository.JpaAccountsRepository;

@EnableTransactionManagement
@Configuration
public class Persistence {

    @Bean
    public AccountsRepository accountsRepository() {
        return new JpaAccountsRepository();
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPersistenceUnitName("bank");
        return factoryBean;
    }

    @Bean
    public PersistenceAnnotationBeanPostProcessor annotationBeanPostProcessor() {
        return new PersistenceAnnotationBeanPostProcessor();
    }

}
