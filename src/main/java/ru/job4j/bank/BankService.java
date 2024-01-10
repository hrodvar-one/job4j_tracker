package ru.job4j.bank;

import ru.job4j.bank.Account;
import ru.job4j.bank.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankService {
    private final Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    public void deleteUser(String passport) {
        for (User user : users.keySet()) {
            if (user.getPassport().equals(passport)) {
                users.remove(user);
            }
        }
    }

    public void addAccount(String passport, Account account) {
        for (User user : users.keySet()) {
            if (user.equals(findByPassport(passport))) {
                if (!getAccounts(user).contains(account)) {
                    getAccounts(user).add(account);
                }
            }
        }
    }

    public User findByPassport(String passport) {
        for (User user : users.keySet()) {
            if (user.getPassport().equals(passport)) {
                return user;
            }
        }
        return null;
    }

    public Account findByRequisite(String passport, String requisite) {
        for (User user : users.keySet()) {
            if (findByPassport(passport) != null) {
                for (Account account : getAccounts(user)) {
                    if (account.getRequisite().equals(requisite)) {
                        return account;
                    }
                }
            }
        }
        return null;
    }

    public boolean transferMoney(String sourcePassport, String sourceRequisite,
                                 String destinationPassport, String destinationRequisite,
                                 double amount) {
        boolean result = false;
        for (User user : users.keySet()) {
            if (!getAccounts(user).contains(findByRequisite(sourcePassport, sourceRequisite))
                    || !getAccounts(user).contains(findByRequisite(destinationPassport, destinationRequisite))
                    || findByRequisite(sourcePassport, sourceRequisite).getBalance() < amount) {
                return result;
            }
            findByRequisite(sourcePassport, sourceRequisite).
                    setBalance(findByRequisite(sourcePassport, sourceRequisite).getBalance() - amount);
            findByRequisite(destinationPassport, destinationRequisite).
                    setBalance(findByRequisite(destinationPassport, destinationRequisite).getBalance() + amount);
            result = true;
        }
        return result;
    }

    public List<Account> getAccounts(User user) {
        return users.get(user);
    }
}
