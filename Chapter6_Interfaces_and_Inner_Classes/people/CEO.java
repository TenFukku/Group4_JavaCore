package people;

import interfaces.CustomCloneable;
import interfaces.Payable;

public final class CEO extends Person implements Payable, CustomCloneable {
    private double bonus;
    private final String companyName;

    public CEO(String name) {
        super(name);
        this.bonus = 50000000; // 50M VND bonus
        this.companyName = "TechCorp Vietnam";
    }

    @Override
    public String getDescription() {
        return "Giám đốc điều hành của " + companyName;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public String getCompanyName() {
        return companyName;
    }

    // Sử dụng Payable interface
    @Override
    public double calculateSalary() {
        return 100000000 + bonus; // 100M base + bonus
    }

    @Override
    public String getPaymentInfo() {
        return "Lương CEO: " + formatVND((int)calculateSalary()) + " (Base: 100M + Bonus: " + formatVND((int)bonus) + ")";
    }

    // Sử dụng CustomCloneable interface
    @Override
    public CustomCloneable createClone() {
        CEO clone = new CEO(getName());
        clone.setBonus(this.bonus);
        return clone;
    }

    @Override
    public boolean isCloneable() {
        return true;
    }

    // Sử dụng Inner class để quản lý công ty
    public class CompanyManager {
        private final String[] departments = {"IT", "HR", "Finance", "Marketing"}; // final variable
        
        public void showDepartments() {
            System.out.println("📋 Các phòng ban trong " + companyName + ":");
            for (String dept : departments) {
                System.out.println("  - " + dept);
            }
        }
        
        public boolean isValidDepartment(String dept) {
            for (String department : departments) {
                if (department.equals(dept)) {
                    return true;
                }
            }
            return false;
        }
    }

    // Sử dụng Static inner class để tính toán lương CEO
    public static class CEOSalaryCalculator {
        public static double calculateTotalCompensation(double baseSalary, double bonus, double stockOptions) {
            return baseSalary + bonus + stockOptions;
        }
        
        public static String formatCompensation(double amount) {
            return String.format("%,.0f VND", amount);
        }
    }
    
    private String formatVND(int amount) {
        return String.format("%,d VND", amount);
    }
}
