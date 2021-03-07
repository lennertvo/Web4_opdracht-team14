package domain.model;

public class Comment {
    private String name, text, topic;

    public Comment(String name, String text, String topic){
        setName(name);
        setText(text);
        setTopic(topic);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
