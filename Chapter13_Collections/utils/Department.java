package utils;

import java.util.*;
import people.Employee;
import people.Person;

public class Department {
    private List<Person> members = new ArrayList<>();
    private Set<String> uniqueNames = new HashSet<>();
    private Map<String, Person> memberMap = new HashMap<>();
    private final String departmentName;
    private final int maxCapacity;

    public Department() {
        this("General", 50);
    }

    public Department(String departmentName, int maxCapacity) {
        this.departmentName = departmentName;
        this.maxCapacity = maxCapacity;
    }

    public void addMembers(Person... people) {
        for (Person p : people) {
            if (p != null && canAddMember()) {
                members.add(p);
                uniqueNames.add(p.getName());
                memberMap.put(p.getName(), p);
                System.out.println("✅ Thêm " + p.getName() + " vào department");
            } else if (!canAddMember()) {
                System.out.println("❌ Không thể thêm " + p.getName() + " - department đã đầy");
            }
        }
    }

    public void showMembers() {
        System.out.println("📋 Danh sách nhân viên trong " + departmentName + ":");
        for (Person p : members) {
            System.out.println("  - " + p + " | " + p.getDescription());
        }
    }

    public void showUniqueNames() {
        System.out.println("Tên nhân viên duy nhất (Set): " + uniqueNames);
    }

    public void showMapView() {
        System.out.println("Map view (keySet): " + memberMap.keySet());
        System.out.println("Map view (values): " + memberMap.values());
        System.out.println("Map view (entrySet):");
        for (Map.Entry<String, Person> entry : memberMap.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }
    }

    public void showSubList(int from, int to) {
        if (from >= 0 && to <= members.size() && from < to) {
            List<Person> sub = members.subList(from, to);
            System.out.println("SubList từ " + from + " đến " + (to-1) + ": " + sub);
        }
    }

    public void showUnmodifiableList() {
        List<Person> unmod = Collections.unmodifiableList(members);
        System.out.println("Unmodifiable List: " + unmod);
    }

    public void shuffleMembers() {
        Collections.shuffle(members);
        System.out.println("Danh sách sau khi shuffle: " + members);
    }

    public void sortMembersByName() {
        members.sort(Comparator.comparing(Person::getName));
        System.out.println("Danh sách sau khi sort theo tên: " + members);
    }

    public void sortMembersBySalary() {
        members.sort((a, b) -> {
            if (a instanceof Employee && b instanceof Employee) {
                return Double.compare(((Employee)a).getSalary(), ((Employee)b).getSalary());
            }
            return 0;
        });
        System.out.println("Danh sách sau khi sort theo lương: " + members);
    }

    public void showMinMaxSalary() {
        Optional<Person> min = members.stream().filter(p -> p instanceof Employee).min(Comparator.comparingDouble(p -> ((Employee)p).getSalary()));
        Optional<Person> max = members.stream().filter(p -> p instanceof Employee).max(Comparator.comparingDouble(p -> ((Employee)p).getSalary()));
        System.out.println("Nhân viên lương thấp nhất: " + min.orElse(null));
        System.out.println("Nhân viên lương cao nhất: " + max.orElse(null));
    }

    public void showIterator() {
        System.out.println("Duyệt bằng Iterator:");
        Iterator<Person> it = members.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    public void showQueueDemo() {
        Queue<Person> queue = new LinkedList<>(members);
        System.out.println("Queue demo:");
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }

    protected int count() {
        return members.size();
    }

    private boolean canAddMember() {
        return members.size() < maxCapacity;
    }

    //Sử dụng Inner class để quản lý thống kê
    public class StatisticsManager {
        private final int MIN_MEMBERS_FOR_BONUS = 5; // Sử dụng final variable
        
        public int getMemberCount() {
            return members.size();
        }
        
        public double getAverageSalary() {
            if (members.isEmpty()) return 0;
            
            double totalSalary = 0;
            for (Person p : members) {
                totalSalary += p.calculateSalary();
            }
            return totalSalary / members.size();
        }
        
        public boolean isEligibleForBonus() {
            return members.size() >= MIN_MEMBERS_FOR_BONUS;
        }
        
        public String getStatistics() {
            return String.format("Department: %s, Members: %d/%d, Avg Salary: %,.0f VND, Bonus Eligible: %s",
                    departmentName, members.size(), maxCapacity, getAverageSalary(), isEligibleForBonus());
        }
    }

    // Sử dụng Static inner class để tạo department
    public static class DepartmentBuilder {
        private String name = "General";
        private int capacity = 50;
        
        public DepartmentBuilder setName(String name) {
            this.name = name;
            return this;
        }
        
        public DepartmentBuilder setCapacity(int capacity) {
            this.capacity = capacity;
            return this;
        }
        
        public Department build() {
            return new Department(name, capacity);
        }
    }

    // Sử dụng Anonymous inner class cho event listener
    public void setMemberAddedListener() {
        // Sử dụng Anonymous inner class implementation
        new Object() {
            public void onMemberAdded(Person person) {
                System.out.println("🎉 Event: " + person.getName() + " đã được thêm vào " + departmentName);
            }
        }.onMemberAdded(members.get(members.size() - 1));
    }
}