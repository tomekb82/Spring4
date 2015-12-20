package pl.training.bank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import pl.training.bank.oauth.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@ComponentScan("pl.training.bank.oauth")
public class Security extends WebSecurityConfigurerAdapter {

    //@Autowired
    //DataSource dataSource;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // JNDI data source
        //JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
        //DataSource dataSource = dataSourceLookup.getDataSource("jdbc/bank");

        // database data source
        //auth.jdbcAuthentication()
        //        .passwordEncoder(new Md5PasswordEncoder())
        //        .dataSource(dataSource);

        // OAuth
        auth.userDetailsService(userDetailsService);
    }




    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/resources/**")
                    .permitAll()
                .antMatchers("/BankService/**")
                    .permitAll()
                .antMatchers("/rest/accounts**")
                    .hasRole("ADMIN")
                .anyRequest()
                    .authenticated()
                .and()
                    .formLogin()
                        .loginPage("/login")
                        .permitAll()
                .and()
                    .httpBasic()
                .and()
                    .logout()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }


}
