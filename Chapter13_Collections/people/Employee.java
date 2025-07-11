package people;

import interfaces.Callback;
import interfaces.CustomCloneable;
import interfaces.Promotable;
import java.util.Comparator;

public class Employee extends Person implements Promotable {
    private double salary;
    private String position;
    private Callback promotionCallback;

    public Employee(String name, double salary) {
        super(name);
        this.salary = salary;
        this.position = "Nhân viên";
    }

    @Override
    public String getDescription() {
        return "Nhân viên với lương: " + formatVND((int)salary);
    }

    public final double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Callback getPromotionCallback() {
        return promotionCallback;
    }

    public void setPromotionCallback(Callback callback) {
        this.promotionCallback = callback;
    }

    // Sử dụng Comparator so sánh theo lương
    public static final Comparator<Employee> BY_SALARY = Comparator.comparingDouble(Employee::getSalary);
    // Sử dụng Comparator so sánh theo tên
    public static final Comparator<Employee> BY_NAME = Comparator.comparing(Employee::getName, String.CASE_INSENSITIVE_ORDER);

    // Sử dụng Override equals/hashCode để phù hợp cho Set/Map
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Employee)) return false;
        Employee e = (Employee) other;
        return this.getName().equals(e.getName()) && Double.compare(this.salary, e.salary) == 0;
    }

    @Override
    public int hashCode() {
        return getName().hashCode() * 31 + Double.hashCode(salary);
    }

    // Sử dụng Implement Promotable interface
    @Override
    public boolean canPromote() {
        return salary < 8000000; 
    }

    @Override
    public void promote() {
        if (canPromote()) {
            double oldSalary = salary;
            salary *= 1.2; // Tăng lương 20%
            position = "Nhân viên cao cấp";
            
            if (promotionCallback != null) {
                promotionCallback.onSuccess("Thăng chức thành công! Lương mới: " + formatVND((int)salary));
            }
        } else {
            if (promotionCallback != null) {
                promotionCallback.onError("Không thể thăng chức - lương đã quá cao");
            }
        }
    }

    @Override
    public String getPromotionInfo() {
        return "Vị trí: " + position + ", Có thể thăng chức: " + canPromote();
    }

    // Sử dụng Implement Payable interface
    @Override
    public double calculateSalary() {
        return salary;
    }

    @Override
    public String getPaymentInfo() {
        return "Lương cơ bản: " + formatVND((int)salary);
    }

    // Sử dụng Implement CustomCloneable interface
    @Override
    public CustomCloneable createClone() {
        return new Employee(getName(), this.salary);
    }

    @Override
    public boolean isCloneable() {
        return true;
    }

    // Sử dụng Inner class để quản lý lương
    public class SalaryManager {
        private final double MIN_SALARY = 3000000; // Sử dụng final variable trong inner class
        
        public boolean isValidSalary(double salary) {
            return salary >= MIN_SALARY;
        }
        
        public void adjustSalary(double newSalary) {
            if (isValidSalary(newSalary)) {
                Employee.this.salary = newSalary;
            }
        }
        
        public String getSalaryInfo() {
            return "Lương hiện tại: " + formatVND((int)Employee.this.salary);
        }
    }

    // Sử dụng Anonymous inner class cho callback
    public void setDefaultCallback() {
        this.promotionCallback = new Callback() {
            @Override
            public void onSuccess(String message) {
                System.out.println("✅ " + message);
            }
            
            @Override
            public void onError(String error) {
                System.out.println("❌ " + error);
            }
            
            @Override
            public void onProgress(int percentage) {
                System.out.println("📊 Tiến độ: " + percentage + "%");
            }
        };
    }
    
    private String formatVND(int amount) {
        return String.format("%,d VND", amount);
    }
}
