package domain.db;

import domain.db.sorters.NameSorter;
import domain.db.sorters.NumberSorter;
import domain.model.Group;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.Collections;
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
        Group UCLL = new Group("UCLL");
        groups.add(UCLL);
        web4.addUser("Daan");
        web4.addUser("Brick");
        tweede.addUser("Ruben");
        tweede.addUser("Lennert");
        tweede.addUser("Eddy");
        isw.addUser("Freddy");
        UCLL.addUser("Lol");
        UCLL.addUser("Lol");
        UCLL.addUser("Lol");
        UCLL.addUser("Lol");
        UCLL.addUser("Lol");
        UCLL.addUser("Lol");
        UCLL.addUser("Lol");
        UCLL.addUser("Lol");
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


    public ArrayList<Group> getGroupsWithMaxUsers(int max) {
        ArrayList<Group> groupWithMax = new ArrayList<>();
        for(Group g: groups) {
            if(g.getUsers().size() <= max) {
                groupWithMax.add(g);
            }
        }
        return groupWithMax;
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
}
