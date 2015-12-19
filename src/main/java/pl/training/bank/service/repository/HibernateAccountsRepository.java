package pl.training.bank.service.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import pl.training.bank.entity.Account;

public class HibernateAccountsRepository implements OldAccountsRepository {

    private static final String SELECT_BY_NUMBER = "select a from Account a where a.number = :number";

    private SessionFactory sessionFactory;

    public HibernateAccountsRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Account save(Account account) {
        Session session = getSession();
        session.save(account);
        session.flush();
        session.refresh(account);
        return account;
    }

    @Override
    public void update(Account account) {
        getSession().update(account);
    }

    @Override
    public Account getByNumber(String accountNumber) {
        Account account = (Account) getSession().createQuery(SELECT_BY_NUMBER)
                .setParameter("number", accountNumber)
                .uniqueResult();
        if (account == null) {
            throw new AccountNotFoundException();
        }
        return account;
    }

}
