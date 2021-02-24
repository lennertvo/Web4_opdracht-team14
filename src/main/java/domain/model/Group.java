package domain.model;

import java.util.ArrayList;

public class Group {
    private String name;
    private ArrayList<String> users;

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

    public ArrayList<String> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }

    public void addUser(String name){
        this.users.add(name);
    }
}
