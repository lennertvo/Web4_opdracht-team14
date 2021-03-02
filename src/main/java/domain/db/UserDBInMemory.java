package domain.db;


import domain.model.Group;
import domain.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDBInMemory {
    ArrayList<User> users;

    public UserDBInMemory() {
        users = new ArrayList<>();
        users.add(new User("r0782485", "Lennert", "Van Oosterwyck", "123", "lennert@spookmail.com", "0412345678", LocalDate.of(2000, 4,20 )));
        users.add(new User("r0789520", "Ruben", "Bottu", "123", "ruben@spookmail.com", "0412345643", LocalDate.of(2000, 1, 30)));
    }

    public ArrayList<User> getUsers(){
        return users;
    }

    /*public void removeUser(String userID) {
        Iterator<User> it = users.iterator();
        while(it.hasNext()) {
            User user = it.next();
            if (user.getUserid().equals(userID)) {
                it.remove();
            }
        }
    }*/

    public void removeUser(String userID) {
        users.removeIf(user -> user.getUserid().equals(userID));
    }

    public void addUser(User user) {
        users.add(user);
    }












}
