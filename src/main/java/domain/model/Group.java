package domain.model;

import domain.db.UserDBInMemory;

import java.util.ArrayList;

public class Group {
    private String name;
    private ArrayList<User> users;
    private ArrayList<String> messages;
    private UserDBInMemory userDBInMemory = new UserDBInMemory();

    public Group(String name) {
        users = new ArrayList<>();
        setName(name);
        messages = new ArrayList<>();
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

    public boolean containsUserWithID(String userID) {
        for (User user : getUsers()) {
            if (user.getUserid().equals(userID)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<String> messages) {
        this.messages = messages;
    }

    public void addMessage(String message){
        if(message == null || message.trim().isEmpty()){
            throw new IllegalArgumentException("Message can't be empty");
        }else this.messages.add(message);
    }
}
