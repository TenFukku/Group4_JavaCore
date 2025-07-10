package utils;

import people.Person;
import java.util.ArrayList;

public class Department {
    private ArrayList<Person> members = new ArrayList<>();

    public void addMembers(Person... people) {
        for (Person p : people) {
            if (p != null) {
                members.add(p);
            }
        }
    }

    public void showMembers() {
        for (Person p : members) {
            System.out.println(p + " | " + p.getDescription());
        }
    }

    protected int count() {
        return members.size();
    }
}