package ru.job4j.ex;

public class UserStore {
    public static User findUser(User[] users, String login) throws UserNotFoundException {
        for (User user : users) {
            if (!user.getUsername().equals(login)) {
                throw new UserNotFoundException("Пользователь не найден");
            } else {
                return user;
            }
        }
        return null;
    }

    public static boolean validate(User user) throws UserInvalidException {
        if (!user.isValid() || user.getUsername().length() < 3) {
            throw new UserInvalidException("Пользователь не валидный");
        }
        return false;
    }

    public static void main(String[] args) {
        try {
            User[] users = {
                    new User("Petr Arsentev", true)
            };
            User user = findUser(users, "Petr Arsentev");
            if (validate(user)) {
                System.out.println("This user has an access");
            }
        } catch (UserInvalidException uie) {
            uie.printStackTrace();
        } catch (UserNotFoundException unfe) {
            unfe.printStackTrace();
        }
    }
}
