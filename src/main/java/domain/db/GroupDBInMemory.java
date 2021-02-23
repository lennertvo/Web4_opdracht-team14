package domain.db;

import domain.model.Group;
import org.graalvm.compiler.core.common.GraalBailoutException;
import java.util.Iterator;

import java.util.ArrayList;

public class GroupDBInMemory {
    ArrayList<Group> groups = new ArrayList<>();

    public GroupDBInMemory() {
        groups = new ArrayList<>();
        groups.add(new Group("web 4"));
        groups.add(new Group("2de jaar Informatica"));
        groups.add(new Group("ISW"));
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

    public Group getGroup() {
        return this.groups.get(0);
    }
}
