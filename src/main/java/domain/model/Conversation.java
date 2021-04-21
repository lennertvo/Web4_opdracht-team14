package domain.model;

import java.util.List;

public class Conversation {
    private User user1, user2;
    private List<Message> messages;

    public Conversation(User user1, User user2) {
        setUser1(user1);
        setUser2(user2);
    }

    private void setUser1(User user1) {
        if (user1 == null) throw new DomainException("User cannot be null");
        this.user1 = user1;
    }

    private void setUser2(User user2) {
        if (user2 == null) throw new DomainException("User cannot be null");
        this.user2 = user2;
    }


}
