import model.BonusCalculator;
import people.*;
import utils.*;

public class Main {
    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        
        System.out.println("=== Demo Kế thừa trong Java ===\n");
        System.out.println("=== Quản lý nhân viên trong một công ty ===\n");

        Employee emp1 = new Employee("Nguyễn Thị Lan Anh", 4200000);
        Employee emp2 = new Employee("Châu Trần Vỹ Linh", 4100000);
        Manager mgr = new Manager("Vũ Minh Hiếu", 6000000, "IT");
        CEO ceo = new CEO("Đỗ Nguyễn Thiên Phúc");

        Department dept = new Department();
        dept.addMembers(emp1, emp2, mgr, ceo);
        System.out.println("📋 Danh sách nhân viên:");
        dept.showMembers();

        // Sử dụng AutoBoxing
        Integer bonus = BonusCalculator.calculateBonus(1000000, 2);
        System.out.println("Thưởng = " + formatVND(bonus));

        // Sử dụng instanceof + ép kiểu
        for (Person p : new Person[]{emp1, emp2, mgr, ceo}) {
            if (p instanceof Manager) {
                Manager m = (Manager) p;
                System.out.println("→ Phòng ban của quản lý: " + m.getDepartment());
            }
        }

        // Sử dụng equals + hashCode test logic
        Employee emp12 = new Employee("Nguyễn Thị Lan Anh", 5000000);
        System.out.println("emp1.equals(emp12)? " + emp1.equals(emp12));
        
        Employee emp13 = new Employee("Nguyễn Thị Lan Anh", 4200000);
        System.out.println("emp1.equals(emp13)? " + emp1.equals(emp13));
    }
    
    private static String formatVND(int amount) {
        return String.format("%,d VND", amount);
    }
}
