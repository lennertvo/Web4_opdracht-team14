package domain.db;

import domain.db.sorters.NameSorter;
import domain.db.sorters.NumberSorter;
import domain.model.Group;
import domain.model.User;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.Collections;
import java.util.Iterator;

import java.util.ArrayList;
import java.util.List;

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
        Group UCLL = new Group("UCLL");
        groups.add(UCLL);

        web4.addUser("r0720550");
        web4.addUser("r0795577");
        web4.addMessage("Dit is de chatroom van web 4");

        tweede.addUser("r0789520");
        tweede.addUser("r0782485");

        isw.addUser("r0789520");

        UCLL.addUser("r0782485");
        UCLL.addUser("r0795577");
        UCLL.addMessage("Dit is de chatroom van UCLL");

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

    public ArrayList<Group> getGroupsOfUser(User user) {
        String userID = user.getUserid();
        ArrayList<Group> result = new ArrayList<>();
        for (Group group : groups) {
            if (group.containsUserWithID(userID)) {
                result.add(group);
            }
        }
        return result;
    }

    public ArrayList<Group> getGroupsWithMaxUsers(int max) {
        ArrayList<Group> groupWithMax = new ArrayList<>();
        for(Group g: groups) {
            if(g.getUsers().size() <= max) {
                groupWithMax.add(g);
            }
        }
        return groupWithMax;
    }

    public Group getGroupByName(String name){
        for(Group g: this.groups){
            if(g.getName().equals(name)){
                return g;
            }
        }
        throw new IllegalArgumentException("No groups found");
    }

    public ArrayList<Group> searchGroup(String name){
        ArrayList<Group> groups = getGroups();
        ArrayList<Group> searchedGroup = new ArrayList<>();
        if(name == null || name.trim().equalsIgnoreCase("")){
            return getGroups();
        }
        for (Group group:groups) {
            if(group.getName().equalsIgnoreCase(name)) {
                searchedGroup.add(group);
                return searchedGroup;
            }
        }
        return null;
    }

    public ArrayList<Group> sortedGroups(String columnName, String sortDirection){
        boolean sort = !Boolean.parseBoolean(sortDirection);
        ArrayList<Group> groups = getGroups();
        if(columnName.equals("name")) {
            return sortNameColumn(sort, groups);
        }
        else{
            return sortNumberColumn(sort, groups);
        }
    }

    public ArrayList<Group> sortNumberColumn(boolean sort, ArrayList<Group> groups){
        if(sort){
            groups.sort(new NumberSorter());
            return groups;
        }
        else{
            groups.sort(new NumberSorter());
            Collections.reverse(groups);
            return groups;
        }
    }


    public ArrayList<Group> sortNameColumn(boolean sort, ArrayList<Group> groups) {
        if(sort){
            groups.sort(new NameSorter());
            return groups;
        }
        else{
            groups.sort(new NameSorter());
            Collections.reverse(groups);
            return groups;
        }
    }

    public ArrayList<String> getMessagesOfGroup(String name){
        ArrayList<String> messages = new ArrayList<>();
        Group group = getGroupByName(name);
        for (Group g: groups){
            if(g.equals(group)){
                messages.addAll(g.getMessages());
            }
        }
        return messages;
    }

}
