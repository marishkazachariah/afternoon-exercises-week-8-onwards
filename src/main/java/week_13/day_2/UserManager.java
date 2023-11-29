package week_13.day_2;

import java.util.ArrayList;
import java.util.List;

// Task 2
public class UserManager {
    private static final List<User> users = new ArrayList<>();

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public void addUser(User user) {
        users.add(user);
        System.out.println("users" + users);
        System.out.println("the user:" + user);
    }

    public void updateUser(User updatedUser) {
        for (User user : users) {
            if (user.getId() == (updatedUser.getId())) {
                user.setUsername(updatedUser.getUsername());
                user.setEmail(updatedUser.getEmail());
                return;
            }
        }
    }

    public void deleteUser(int id) {
        users.removeIf(user -> user.getId() == id);
    }
}

