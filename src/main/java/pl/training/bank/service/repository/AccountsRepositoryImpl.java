package pl.training.bank.service.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import pl.training.bank.entity.Account;

public class AccountsRepositoryImpl implements AccountsRepositoryCustom {

    @PersistenceContext(unitName = "bank")
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Account getByNumber(String accountNumber) {
        try {
            return entityManager.createNamedQuery(Account.GET_BY_NUMBER, Account.class)
                    .setParameter("number", accountNumber).getSingleResult();
        } catch (NoResultException ex) {
            throw new AccountNotFoundException();
        }
    }

}
