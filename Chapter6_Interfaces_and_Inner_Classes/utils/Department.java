package utils;

import java.util.ArrayList;
import people.Person;

public class Department {
    private ArrayList<Person> members = new ArrayList<>();
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

    protected int count() {
        return members.size();
    }

    private boolean canAddMember() {
        return members.size() < maxCapacity;
    }

    //Sử dụng Inner class để quản lý thống kê
    public class StatisticsManager {
        private final int MIN_MEMBERS_FOR_BONUS = 5; // final variable
        
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
        // Anonymous inner class implementation
        new Object() {
            public void onMemberAdded(Person person) {
                System.out.println("🎉 Event: " + person.getName() + " đã được thêm vào " + departmentName);
            }
        }.onMemberAdded(members.get(members.size() - 1));
    }
}