package domain.model;

import java.util.ArrayList;
import java.util.List;

public class FriendShip {

    private String id;
    private User user1;
    private User user2;

    private ArrayList<String> messages;

    public FriendShip(String id,  User user1, User user2) {
        messages = new ArrayList<>();
        setId(id);
        setUser1(user1);


    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }


    public ArrayList<String> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<String> messages) {
        this.messages = messages;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public void addMessage(String message){
        messages.add(message);
    }
}
