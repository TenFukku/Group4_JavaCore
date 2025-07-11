package people;

import interfaces.CustomCloneable;
import interfaces.Payable;

public abstract class Person implements Payable, CustomCloneable, Comparable<Person> {
    private String name;
    private final int id; // Sử dụng final variable trong inner class context

    public Person(String name) {
        this.name = name;
        this.id = generateId();
    }

    public abstract String getDescription();

    @Override
    public String toString() {
        return "Name: " + name + " (ID: " + id + ")";
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Person)) return false;
        Person p = (Person) other;
        return name.equals(p.name) && id == p.id;
    }

    @Override
    public int hashCode() {
        return name.hashCode() * 31 + id;
    }

    // Sử dụng Comparable<Person> (so sánh theo tên)
    @Override
    public int compareTo(Person other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    // Sử dụng Inner class để quản lý ID
    public static class IdManager {
        private static int nextId = 1000;
        
        public static int generateId() {
            return nextId++;
        }
        
        public static void resetId() {
            nextId = 1000;
        }
    }
    
    private int generateId() {
        return IdManager.generateId();
    }

    // Sử dụng Static inner class để format thông tin
    public static class PersonFormatter {
        public static String formatInfo(Person person) {
            return String.format("[%d] %s", person.getId(), person.getName());
        }
    }
}
