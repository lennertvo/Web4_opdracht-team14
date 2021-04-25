package domain.db;

import domain.model.FriendShip;
import domain.model.User;

import java.util.ArrayList;

public class FriendShipMessagesInMemory {

    ArrayList<FriendShip> friendShips;

    public FriendShipMessagesInMemory() {
        friendShips = new ArrayList<>();

    }


    public void addMessage(String id, String id2, String message) {
        for (FriendShip f : friendShips) {
            if (f.getId().equals(id2)) {
                f.getMessages().add(message);
            }
        }
    }


    public ArrayList<String> getMessagesBetween2Users(String id, String id2) {
        ArrayList<String> messages = new ArrayList<>();
        FriendShip a = getFriendShip(id, id2);
        for(FriendShip f : friendShips) {
            if(f.equals(a)){
                messages.addAll(f.getMessages());
            }
        }

        return messages;

    }


    public void addFriendShip(FriendShip friendShip) {
        friendShips.add(friendShip);
    }

    public FriendShip getFriendShip(String id, String id2) {
        for (FriendShip f : friendShips) {
            if (f.getId().equals(id) || f.getId().equals(id2)) {
                return f;
            }
        }
        return null;

    }
}
