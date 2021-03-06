package domain.db;


import domain.model.Group;
import domain.model.Status;
import domain.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDBInMemory {
    ArrayList<User> users;
    public UserDBInMemory() {
        users = new ArrayList<>();
        users.add(new User("r0782485", "Lennert", "Van Oosterwyck", "123", "lennert@spookmail.com", "0412345678"));
        users.add(new User("r0789520", "Ruben", "Bottu", "123", "ruben@spookmail.com", "0412345643"));
        users.add(new User("r0795577", "Brick", "van Roekel", "123", "brick@spookmail.com", "0412345678"));
        users.add(new User("r0720550", "Daan", "Stallaert", "123", "daan@spookmail.com", "046942069"));

    }

    public ArrayList<User> getUsers() {
        return users;
    }


    // Deelopdracht 1a individueel - Ruben Bottu r0789520 - gebruiker zoeken op voornaam
    public ArrayList<User> findUsers(String firstName) {
        ArrayList<User> result = new ArrayList<>();
        for (User user : users) {
            if (user.getFirstName().equalsIgnoreCase(firstName)) {
                result.add(user);
            }
        }
        return result;
    }

    public ArrayList<User> getFriends(User u){
        ArrayList<User> f = new ArrayList<>();

        for(User friend: u.getFriends()){
            for(User all: users){
                if(friend.getUserid().equals(all.getUserid())){
                    f.add(all);
                }
            }

        }
        return f;
    }

    public User findUser(String userId) {
        for (User user : users) {
            if (user.getUserid().equals(userId)) {
                return user;
            }
        }
        return null;
    }


    public ArrayList<User> getPotentialFriends(String id) {
        User user = findUser(id);
        ArrayList<User> f = new ArrayList<>();


        for (User u : getUsers()) {

            if (!u.getUserid().equals(id)) {

                f.add(u);
            }
        }

        for (User a : user.getFriends()) {
            f.remove(a);
        }
        System.out.println(f);
        return f;
    }

    public void removeUser (String userID){
        users.removeIf(user -> user.getUserid().equals(userID));
    }

    public void addUser (User user){
        users.add(user);
    }

    public void setStatusUser(User u, Status status) {
        for(User a: users){
            if(a.getUserid().equals(u.getUserid())){
                a.setStatus(status);
            }
        }
    }

    public boolean isAlreadyFriend(User a, User friend) {
        for(User f:  a.getFriends()){
            if(f.equals(friend)){
                return true;
            }
        }
        return false;
    }


    public Status getStatusUser(User u) {
        Status s = Status.ONLINE;
        for(User a: users){
            if(a.getUserid().equals(u.getUserid())){
                s = a.getStatus();
            }
        }
        return s;
    }


}














