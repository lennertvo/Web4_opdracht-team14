package domain.db;

import domain.model.Group;

import java.util.ArrayList;

public class GroupDBInMemory {
    ArrayList<Group> groups;

    public GroupDBInMemory(ArrayList<Group> groups) {
        this.groups = groups;
        groups.add(new Group("web 4"));
        groups.add(new Group("2de jaar Informatica"));
        groups.add(new Group("ISW"));
    }
}
