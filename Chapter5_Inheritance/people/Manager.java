package people;

public class Manager extends Employee {
    private String department;

    public Manager(String name, double salary, String department) {
        super(name, salary);
        this.department = department;
    }

    @Override
    public String getDescription() {
        return "Quản lý phòng " + department + " với lương " + formatVND((int)getSalary());
    }

    public String getDepartment() {
        return department;
    }
    
    private String formatVND(int amount) {
        return String.format("%,d VND", amount);
    }
}
