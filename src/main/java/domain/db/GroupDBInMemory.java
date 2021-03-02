package domain.db;

import domain.model.Group;

import java.util.Iterator;

import java.util.ArrayList;

public class GroupDBInMemory {
    ArrayList<Group> groups;

    public GroupDBInMemory() {
        groups = new ArrayList<>();
        Group web4 = new Group("web 4");
        groups.add(web4);
        Group tweede = new Group("2de jaar Informatica");
        groups.add(tweede);
        Group isw = new Group("ISW");
        groups.add(isw);
        web4.addUser("Daan");
        web4.addUser("Brick");
        tweede.addUser("Ruben");
        tweede.addUser("Lennert");
        tweede.addUser("Eddy");
        isw.addUser("Freddy");
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public void removeGroup(String groupName) {
        Iterator<Group> it = groups.iterator();
        while(it.hasNext()) {
            Group group = it.next();
            if (group.getName().equals(groupName)) {
                it.remove();
            }
        }
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }
}
