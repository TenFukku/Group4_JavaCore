package people;

import interfaces.Callback;
import interfaces.CustomCloneable;
import interfaces.Promotable;

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

    // Implement Promotable interface
    @Override
    public boolean canPromote() {
        return salary < 8000000; // Có thể thăng chức nếu lương < 8M
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

    // Implement Payable interface
    @Override
    public double calculateSalary() {
        return salary;
    }

    @Override
    public String getPaymentInfo() {
        return "Lương cơ bản: " + formatVND((int)salary);
    }

    // Implement CustomCloneable interface
    @Override
    public CustomCloneable createClone() {
        return new Employee(getName(), this.salary);
    }

    @Override
    public boolean isCloneable() {
        return true;
    }

    // Inner class để quản lý lương
    public class SalaryManager {
        private final double MIN_SALARY = 3000000; // final variable trong inner class
        
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

    // Anonymous inner class cho callback
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
    
    // Helper method để format VND
    private String formatVND(int amount) {
        return String.format("%,d VND", amount);
    }
}
