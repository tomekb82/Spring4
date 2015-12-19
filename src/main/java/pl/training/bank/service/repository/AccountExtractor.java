package pl.training.bank.service.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import pl.training.bank.entity.Account;

public class AccountExtractor implements ResultSetExtractor<Account> {

    private static final String ID_COLUMN = "id";
    private static final String NUMBER_COLUMN = "number";
    private static final String BALANCE_COLUMN = "balance";

    @Override
    public Account extractData(ResultSet rs) throws SQLException, DataAccessException {
        Account account = null;
        if (rs.next()) {
            account = new Account(rs.getString(NUMBER_COLUMN));
            account.setId(rs.getLong(ID_COLUMN));
            account.setBalance(rs.getLong(BALANCE_COLUMN));
        }
        return account;
    }

}

