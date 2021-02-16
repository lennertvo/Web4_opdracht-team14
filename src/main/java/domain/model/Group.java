package domain.model;

import java.util.ArrayList;

public class Group {
    private String name;
    private ArrayList<User> users;

    public Group(String name) {
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

    }
}
