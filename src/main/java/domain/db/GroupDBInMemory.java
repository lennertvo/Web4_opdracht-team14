package domain.db;

import domain.model.Group;

import java.util.ArrayList;

public class GroupDBInMemory {
    ArrayList<Group> groups = new ArrayList<>();

    public GroupDBInMemory() {
        groups.add(new Group("web 4"));
        groups.add(new Group("2de jaar Informatica"));
        groups.add(new Group("ISW"));
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public Group getGroup() {
        return this.groups.get(0);
    }
}
