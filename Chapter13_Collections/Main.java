import java.util.*;
import people.*;
import utils.*;

public class Main {
    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        System.setProperty("console.encoding", "UTF-8");
        
        System.out.println("=== Demo Java Collections Framework ===\n");
        System.out.println("=== Quản lý nhân viên trong một công ty ===\n");

        // 1. Sử dụng List để quản lý danh sách nhân viên
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Phúc đẹp trai", 5000000));
        employees.add(new Employee("Hiếu", 7000000));
        employees.add(new Employee("Linh", 6000000));
        employees.add(new Employee("Lan AnhAnh", 5000000)); // duplicate name, same salary
        System.out.println("Danh sách nhân viên (List):");
        for (Employee e : employees) System.out.println(e);

        // 2. Sử dụng Iterator để duyệt danh sách
        System.out.println("\nDuyệt bằng Iterator:");
        Iterator<Employee> it = employees.iterator();
        while (it.hasNext()) System.out.println(it.next());

        // 3. Sử dụng Set để lưu trữ các nhân viên duy nhất
        Set<Employee> empSet = new HashSet<>(employees);
        System.out.println("\nCác nhân viên duy nhất (HashSet): " + empSet);
        Set<Employee> empTreeSet = new TreeSet<>(employees);
        System.out.println("Các nhân viên (TreeSet, sort by name): " + empTreeSet);

        // 4. Sử dụng Map để lưu trữ nhân viên theo tên
        Map<String, Employee> empMap = new HashMap<>();
        for (Employee e : employees) empMap.put(e.getName(), e);
        System.out.println("\nMap nhân viên (HashMap):");
        for (Map.Entry<String, Employee> entry : empMap.entrySet())
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        Map<String, Employee> empTreeMap = new TreeMap<>(empMap);
        System.out.println("Map nhân viên (TreeMap): " + empTreeMap);

        // 5. Sử dụng Queue để lưu trữ nhân viên theo tên tăng dần
        Queue<Employee> queue = new PriorityQueue<>(employees); // sort by name (Comparable)
        System.out.println("\nQueue theo tên tăng dần:");
        while (!queue.isEmpty()) System.out.println(queue.poll());

        // 6. Sử dụng Comparator để sắp xếp nhân viên theo lương và tên
        employees.sort(Employee.BY_SALARY.reversed());
        System.out.println("\nSắp xếp theo lương giảm dần: " + employees);
        employees.sort(Employee.BY_NAME);
        System.out.println("Sắp xếp theo tên: " + employees);

        // 7. Sử dụng Views để lấy các phần tử của danh sách
        List<Employee> subList = employees.subList(0, Math.min(2, employees.size()));
        System.out.println("\nSubList: " + subList);
        Set<String> keys = empMap.keySet();
        System.out.println("Keys view: " + keys);
        Collection<Employee> values = empMap.values();
        System.out.println("Values view: " + values);
        Set<Map.Entry<String, Employee>> entries = empMap.entrySet();
        System.out.println("EntrySet view: " + entries);

        // 8. Sử dụng Wrappers để lấy các phần tử của danh sách
        List<Employee> unmodifiable = Collections.unmodifiableList(employees);
        System.out.println("\nUnmodifiable List: " + unmodifiable);
        List<Employee> synchronizedList = Collections.synchronizedList(employees);
        System.out.println("Synchronized List: " + synchronizedList);
        List<Employee> checkedList = Collections.checkedList(new ArrayList<>(), Employee.class);
        checkedList.addAll(employees);
        System.out.println("Checked List: " + checkedList);

        // 9. Sử dụng các thuật toán collections
        Collections.shuffle(employees);
        System.out.println("\nShuffle: " + employees);
        Collections.reverse(employees);
        System.out.println("Reverse: " + employees);
        Employee minEmp = Collections.min(employees, Employee.BY_SALARY);
        System.out.println("Nhân viên lương thấp nhất: " + minEmp);
        Employee maxEmp = Collections.max(employees, Employee.BY_SALARY);
        System.out.println("Nhân viên lương cao nhất: " + maxEmp);
        Collections.sort(employees, Employee.BY_NAME);
        System.out.println("Sort by name: " + employees);

        // 10. Sử dụng Department để quản lý danh sách nhân viên
        System.out.println("\n=== Demo Department với Collections ===");
        Department dept = new Department();
        dept.addMembers(employees.toArray(new Employee[0]));
        dept.showMembers();
        dept.showUniqueNames();
        dept.showMapView();
        dept.showSubList(0, Math.min(2, employees.size()));
        dept.showUnmodifiableList();
        dept.shuffleMembers();
        dept.sortMembersByName();
        dept.sortMembersBySalary();
        dept.showMinMaxSalary();
        dept.showIterator();
        dept.showQueueDemo();
    }
}
