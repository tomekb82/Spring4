package pl.training.bank.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import pl.training.bank.service.repository.AccountsRepository;
import pl.training.bank.service.repository.HibernateAccountsRepository;

@EnableTransactionManagement
@PropertySource({"classpath:jdbc.properties", "classpath:hibernate.properties"})
@Configuration
public class Persistence {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(env.getProperty("url"));
        dataSource.setUsername(env.getProperty("login"));
        dataSource.setPassword(env.getProperty("password"));
        dataSource.setDriverClassName(env.getProperty("driverClass"));
        dataSource.setInitialSize(30);
        dataSource.setMaxWaitMillis(3000);
        return dataSource;
    }

    @Bean
    public AccountsRepository accountsRepository(SessionFactory sessionFactory) {
        return new HibernateAccountsRepository(sessionFactory);
    }

    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    @Bean
    public PropertiesFactoryBean hibernateProperties() {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("hibernate.properties"));
        return propertiesFactoryBean;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource, Properties hibernateProperties) {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan("pl.training.bank.entity");
        factoryBean.setHibernateProperties(hibernateProperties);
        return factoryBean;
    }

}
