package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс описывает работу банковской системы.
 * В системе можно производить следующие действия.
 * 1. Регистрировать пользователя.
 * 2. Удалять пользователя из системы.
 * 3. Добавлять пользователю банковский счет. У пользователя системы могут быть несколько счетов.
 * 4. Переводить деньги с одного банковского счета на другой счет.
 * @author ALEKSANDR KOBELSKIY
 * @version 1.0
 */
public class BankService {
    /**
     * Хранение данных пользователя осуществляется в коллекции типа Map
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод принимает на вход пользователя
     * и добавляет его в мапу users, если такого пользователя ещё нет в ней.
     * @param user пользователь который добавляется в мапу
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Метод принимает на вход строку с данными паспорта
     * и удаляет пользователя с данными этого паспорта из мапы users если он в ней есть.
     * @param passport паспорт пользователя которого надо удалить из мапы users
     */
    public void deleteUser(String passport) {
        users.remove(new User(passport, ""));
    }

    /**
     * Метод принимает на вход строку с данными паспорта и счет пользователя.
     * Затем происходит поиск пользователя с помощью метода findByPassport
     * который принимает на вход данные паспорта.
     * Далее происходит проверка, что пользователь не null и
     * список счетов этого пользователя не содержит переданный в метод счет.
     * @param passport паспорт по которому нужно найти пользователя
     * @param account счет который нужно добавить в аккаунт пользователя
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null && !getAccounts(user).contains(account)) {
            getAccounts(user).add(account);
        }
    }

    /**
     * Метод принимает на вход строку с данными паспорта пользователя.
     * Затем происходит поиск пользователя в мапе users, где паспорт совпадает
     * с паспортом переданным на вход метода.
     * @param passport паспорт по которому осуществляется поиск пользователя
     * @return Если пользователя с таким паспортом нет, то метод возвращает null
     */
    public User findByPassport(String passport) {
        for (User user : users.keySet()) {
            if (user.getPassport().equals(passport)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Метод принимает на вход строку с данными паспорта и строку с реквизитами.
     * Затем происходит поиск пользователя с данным паспортом.
     * Для найденного пользователя, если он не null
     * находим счет с реквизитами равными реквизитам переданным в метод.
     * @param passport строка с паспортом
     * @param requisite строка с реквизитами счета
     * @return Если пользователь равен null, то возвращаем null
     */
    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        if (user != null) {
            for (Account account : getAccounts(user)) {
                if (account.getRequisite().equals(requisite)) {
                    return account;
                }
            }
        }
        return null;
    }

    /**
     * Метод принимает на вход даные исходного паспорта и реквизита,
     * данные целевого паспорта и реквизита и количество денег которые надо перевести.
     * @param sourcePassport данные исходного паспорта
     * @param sourceRequisite данные исходных реквизитов
     * @param destinationPassport данные целевого паспорта
     * @param destinationRequisite данные целевых реквизитов
     * @param amount количество денег которые надо перевести между счетами
     * @return возвращает истину если трансфер денег возможен, иначе возвращает ложь
     */
    public boolean transferMoney(String sourcePassport, String sourceRequisite,
                                 String destinationPassport, String destinationRequisite,
                                 double amount) {
        Account sourceAccount = findByRequisite(sourcePassport, sourceRequisite);
        Account destinationAccount = findByRequisite(destinationPassport, destinationRequisite);
        if (sourceAccount == null || destinationAccount == null || sourceAccount.getBalance() < amount) {
            return false;
        }
        sourceAccount.setBalance(findByRequisite(sourcePassport, sourceRequisite).getBalance() - amount);
        destinationAccount.setBalance(findByRequisite(destinationPassport, destinationRequisite).getBalance() + amount);
        return true;
    }

    /**
     * Метод принимает на вход пользователя и находит список аккаунтов данного пользователя
     * @param user пользователь банка
     * @return возвращает список аккаунтов пользователя
     */
    public List<Account> getAccounts(User user) {
        return users.get(user);
    }
}
