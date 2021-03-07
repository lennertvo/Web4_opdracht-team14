package domain.db.sorters;

import domain.model.Group;

import java.util.Comparator;

public class NameSorter implements Comparator<Group> {
    @Override
    public int compare(Group g1, Group g2) {
        return g1.getName().compareToIgnoreCase(g2.getName());
    }
}
