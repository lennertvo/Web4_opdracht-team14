package domain.db.sorters;

import domain.model.Group;

import java.util.Comparator;

public class NumberSorter implements Comparator<Group> {
    @Override
    public int compare(Group g1, Group g2) {
        return g1.getUsers().size() - (g2.getUsers().size());
    }
}
