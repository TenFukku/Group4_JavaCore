package people;

public class Employee extends Person {
    private double salary;

    public Employee(String name, double salary) {
        super(name);
        this.salary = salary;
    }

    @Override
    public String getDescription() {
        return "Nhân viên với lương: " + formatVND((int)salary);
    }

    public final double getSalary() {
        return salary;
    }
    
    // Helper method để format VND
    private String formatVND(int amount) {
        return String.format("%,d VND", amount);
    }
}
