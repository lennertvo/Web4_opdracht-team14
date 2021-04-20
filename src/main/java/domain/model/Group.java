package domain.model;

import domain.db.UserDBInMemory;

import java.util.ArrayList;

public class Group {
    private String name;
    private ArrayList<User> users;
    private UserDBInMemory userDBInMemory = new UserDBInMemory();

    public Group(String name) {
        users = new ArrayList<>();
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void addUser(String userid){
        User user = userDBInMemory.findUser(userid);
        this.users.add(user);
    }
}
