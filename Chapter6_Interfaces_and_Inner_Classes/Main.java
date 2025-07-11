import interfaces.Callback;
import people.*;
import proxy.LogProxy;
import utils.*;

public class Main {
    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        
        System.out.println("=== Demo Interfaces trong Java ===\n");
        System.out.println("=== Quản lý nhân viên trong một công ty ===\n");

        Employee emp1 = new Employee("Nguyễn Thị Lan Anh", 4200000);
        Manager mgr = new Manager("Vũ Minh Hiếu", 6000000, "IT");
        CEO ceo = new CEO("Đỗ Nguyễn Thiên Phúc");

        System.out.println("📊 Thông tin lương:");
        System.out.println("- " + emp1.getPaymentInfo());
        System.out.println("- " + mgr.getPaymentInfo());
        System.out.println("- " + ceo.getPaymentInfo());

        System.out.println("\n🎯 Thông tin thăng chức:");
        emp1.setDefaultCallback();
        System.out.println(emp1.getPromotionInfo());
        emp1.promote();

        System.out.println("\n🏢 Quản lý nội bộ:");
        Employee.SalaryManager salaryMgr = emp1.new SalaryManager();
        System.out.println(salaryMgr.getSalaryInfo());
        salaryMgr.adjustSalary(5000000);
        System.out.println("Sau điều chỉnh: " + salaryMgr.getSalaryInfo());

        Manager.TeamManager teamMgr = mgr.new TeamManager();
        System.out.println(teamMgr.getTeamInfo());
        teamMgr.addMember();
        teamMgr.addMember();
        System.out.println("Sau thêm thành viên: " + teamMgr.getTeamInfo());

        System.out.println("\n📈 Thống kê và tính toán:");
        System.out.println("Person Formatter: " + Person.PersonFormatter.formatInfo(emp1));
        
        double bonus = Manager.BonusCalculator.calculateManagerBonus(mgr.getSalary(), mgr.getTeamSize());
        System.out.println("Bonus Manager: " + Manager.BonusCalculator.formatBonus(bonus));

        double totalComp = CEO.CEOSalaryCalculator.calculateTotalCompensation(100000000, 50000000, 20000000);
        System.out.println("CEO Total Compensation: " + CEO.CEOSalaryCalculator.formatCompensation(totalComp));

        System.out.println("\n🔄 Clone objects:");
        Employee empClone = (Employee) emp1.createClone();
        System.out.println("Original: " + emp1);
        System.out.println("Clone: " + empClone);

        System.out.println("\n🔍 Proxy pattern:");
        LogProxy empProxy = new LogProxy(emp1);
        double proxySalary = empProxy.calculateSalary();
        System.out.println("Proxy calculated salary: " + formatVND((int)proxySalary));

        System.out.println("\n🏢 Quản lý công ty:");
        CEO.CompanyManager companyMgr = ceo.new CompanyManager();
        companyMgr.showDepartments();
        System.out.println("IT là phòng ban hợp lệ: " + companyMgr.isValidDepartment("IT"));
        System.out.println("Sales là phòng ban hợp lệ: " + companyMgr.isValidDepartment("Sales"));

        System.out.println("\n📋 Quản lý department:");
        Department dept = new Department();
        dept.addMembers(emp1, mgr, ceo);
        dept.showMembers();

        System.out.println("\n🎭 Anonymous inner class:");
        Callback customCallback = new Callback() {
            @Override
            public void onSuccess(String message) {
                System.out.println("🎉 " + message);
            }
            
            @Override
            public void onError(String error) {
                System.out.println("💥 " + error);
            }
            
            @Override
            public void onProgress(int percentage) {
                System.out.println("📊 Tiến độ: " + percentage + "%");
            }
        };
        
        mgr.setPromotionCallback(customCallback);
        mgr.promote();
    }
    
    private static String formatVND(int amount) {
        return String.format("%,d VND", amount);
    }
}
