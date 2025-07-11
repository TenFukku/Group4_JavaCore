public class EmployeeTest {
    public static void main(String[] args) {
        // Tạo mảng nhân viên
        Employee[] staff = new Employee[3];

        staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);

        // Tăng lương cho toàn bộ nhân viên
        for (Employee e : staff) {
            e.raiseSalary(Employee.DEFAULT_RAISE);
        }

        // In thông tin nhân viên
        for (Employee e : staff) {
            System.out.printf("Name=%s, Salary=%.2f, HireDay=%s%n",
                    e.getName(), e.getSalary(), e.getHireDay());
        }
    }
}

