package domain.model;

import java.time.LocalTime;

public class Message {

    private User sender;
    private User receiver;
    private String text;
    private LocalTime time;


    public Message(User sender, User receiver, String text) {
        setSender(sender);
        setReceiver(receiver);
        setText(text);
        setTime(LocalTime.now());

    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
