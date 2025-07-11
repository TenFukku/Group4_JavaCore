package people;

import interfaces.Promotable;

public class Manager extends Employee implements Promotable {
    private String department;
    private int teamSize;

    public Manager(String name, double salary, String department) {
        super(name, salary);
        this.department = department;
        this.teamSize = 0;
        setPosition("Quản lý");
    }

    @Override
    public String getDescription() {
        return "Quản lý phòng " + department + " với lương " + formatVND((int)getSalary());
    }

    public String getDepartment() {
        return department;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }

    // Override promote method từ Employee
    @Override
    public void promote() {
        if (canPromote()) {
            double oldSalary = getSalary();
            // Tăng lương 30% cho Manager
            setSalary(getSalary() * 1.3);
            setPosition("Quản lý cao cấp");
            
            if (getPromotionCallback() != null) {
                getPromotionCallback().onSuccess("Thăng chức Manager thành công! Lương mới: " + formatVND((int)getSalary()));
            }
        } else {
            if (getPromotionCallback() != null) {
                getPromotionCallback().onError("Manager không thể thăng chức - lương đã quá cao");
            }
        }
    }

    // Inner class để quản lý team
    public class TeamManager {
        private final int MAX_TEAM_SIZE = 20; // final variable trong inner class
        
        public boolean canAddMember() {
            return teamSize < MAX_TEAM_SIZE;
        }
        
        public void addMember() {
            if (canAddMember()) {
                teamSize++;
                System.out.println("✅ Thêm thành viên thành công. Team size: " + teamSize);
            } else {
                System.out.println("❌ Không thể thêm thành viên - team đã đầy");
            }
        }
        
        public void removeMember() {
            if (teamSize > 0) {
                teamSize--;
                System.out.println("✅ Xóa thành viên thành công. Team size: " + teamSize);
            }
        }
        
        public String getTeamInfo() {
            return "Phòng: " + department + ", Team size: " + teamSize + "/" + MAX_TEAM_SIZE;
        }
    }

    // Static inner class để tính toán bonus
    public static class BonusCalculator {
        public static double calculateManagerBonus(double baseSalary, int teamSize) {
            return baseSalary * 0.1 * (1 + teamSize * 0.05);
        }
        
        public static String formatBonus(double bonus) {
            return String.format("%,.0f VND", bonus);
        }
    }
    
    // Helper method để format VND
    private String formatVND(int amount) {
        return String.format("%,d VND", amount);
    }
}
