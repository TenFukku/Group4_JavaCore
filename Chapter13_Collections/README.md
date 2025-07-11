# 🏢 Java Collections Framework Demo Project

## 📋 Mô tả

Project này là một demo minh họa các khái niệm cơ bản về **Java Collections Framework** trong Java, thuộc Chapter 13 của sách Core Java Volume I- Fundamentals 9th Edition- Horstmann, Cay S. & Cornell, Gary.

Project mô phỏng hệ thống quản lý nhân viên với các collection interfaces, iterator, views, wrappers và thuật toán collections để demo các tính năng JCF nâng cao.

## 🎯 Mục tiêu

- Minh họa các Collection Interfaces (List, Set, Map, Queue)
- Demo Iterator pattern và enhanced for-loop
- Thực hành với Object Comparison (Comparable, Comparator)
- Hiểu về Views (subList, keySet, entrySet, values)
- Demo Wrappers (unmodifiable, synchronized, checked)
- Thực hành với Collections Algorithms (sort, shuffle, reverse, min, max)
- Hiểu về Set vs List, HashMap vs TreeMap, ArrayList vs LinkedList

## 📁 Cấu trúc Project

```
Chapter13_Collections/
├── Main.java                 # Entry point với demo đầy đủ JCF
├── interfaces/               # Package chứa các interfaces
│   ├── Payable.java         # Interface cho tính lương
│   ├── Promotable.java      # Interface cho thăng chức
│   ├── CustomCloneable.java # Interface cho clone objects
│   └── Callback.java        # Interface cho callback pattern
├── people/                   # Package chứa các lớp nhân viên
│   ├── Person.java          # Abstract class với Comparable
│   ├── Employee.java        # Với Comparator và equals/hashCode
│   ├── Manager.java         # Với inner class TeamManager
│   └── CEO.java            # Với inner class CompanyManager
├── proxy/                    # Package chứa proxy pattern
│   └── LogProxy.java       # Proxy implementation với logging
└── utils/                    # Package chứa utility classes
    └── Department.java      # Với các collection methods
```

## 🏗️ Hệ thống phân cấp

```
Person (abstract) implements Payable, CustomCloneable, Comparable<Person>
├── Employee implements Promotable
│   └── Manager
└── CEO implements Payable, CustomCloneable
```

## 📝 Kết quả chạy

Khi chạy chương trình, bạn sẽ thấy output tương tự:

```
=== Demo Java Collections Framework ===
=== Quản lý nhân viên trong một công ty ===

Danh sách nhân viên (List):
Name: Phúc đẹp trai (ID: 1000)
Name: Hiếu (ID: 1001)
Name: Linh (ID: 1002)
Name: Lan Anh (ID: 1003)

Duyệt bằng Iterator:
Name: Phúc đẹp trai (ID: 1000)
Name: Hiếu (ID: 1001)
Name: Linh (ID: 1002)
Name: Lan Anh (ID: 1003)

Các nhân viên duy nhất (HashSet): [Name: Hiếu (ID: 1001), Name: Lan Anh (ID: 1003), ...]

Map nhân viên (HashMap):
Phúc đẹp trai -> Name: Phúc đẹp trai (ID: 1000)
Hiếu -> Name: Hiếu (ID: 1001)
Linh -> Name: Linh (ID: 1002)
Lan Anh -> Name: Lan Anh (ID: 1003)

Queue theo tên tăng dần:
Name: Hiếu (ID: 1001)
Name: Lan Anh (ID: 1003)
Name: Linh (ID: 1002)
Name: Phúc đẹp trai (ID: 1000)

Sắp xếp theo lương giảm dần: [Name: Hiếu (ID: 1001), Name: Linh (ID: 1002), ...]
Sắp xếp theo tên: [Name: Hiếu (ID: 1001), Name: Lan Anh (ID: 1003), ...]

SubList: [Name: Hiếu (ID: 1001), Name: Lan Anh (ID: 1003)]
Keys view: [Phúc đẹp trai, Hiếu, Linh, Lan Anh]
Values view: [Name: Phúc đẹp trai (ID: 1000), Name: Hiếu (ID: 1001), ...]

Unmodifiable List: [Name: Hiếu (ID: 1001), Name: Lan Anh (ID: 1003), ...]
Synchronized List: [Name: Hiếu (ID: 1001), Name: Lan Anh (ID: 1003), ...]
Checked List: [Name: Hiếu (ID: 1001), Name: Lan Anh (ID: 1003), ...]

Shuffle: [Name: Lan Anh (ID: 1003), Name: Hiếu (ID: 1001), ...]
Reverse: [Name: Phúc đẹp trai (ID: 1000), Name: Linh (ID: 1002), ...]
Nhân viên lương thấp nhất: Name: Lan Anh (ID: 1003)
Nhân viên lương cao nhất: Name: Hiếu (ID: 1001)
```

## 🎓 Các khái niệm Java Collections được demo

### 1. **Collection Interfaces**

```java
// List - Ordered collection with duplicates
List<Employee> employees = new ArrayList<>();
employees.add(new Employee("Lan", 5000000));

// Set - No duplicates
Set<Employee> empSet = new HashSet<>(employees);
Set<Employee> empTreeSet = new TreeSet<>(employees); // Sorted

// Map - Key-Value pairs
Map<String, Employee> empMap = new HashMap<>();
empMap.put("Lan", new Employee("Lan", 5000000));

// Queue - FIFO/LIFO
Queue<Employee> queue = new PriorityQueue<>(employees);
```

### 2. **Iterator Pattern**

```java
// Traditional Iterator
Iterator<Employee> it = employees.iterator();
while (it.hasNext()) {
    System.out.println(it.next());
}

// Enhanced for-loop (uses Iterator internally)
for (Employee e : employees) {
    System.out.println(e);
}
```

### 3. **Object Comparison**

```java
// Comparable (natural ordering)
public class Person implements Comparable<Person> {
    @Override
    public int compareTo(Person other) {
        return this.name.compareToIgnoreCase(other.name);
    }
}

// Comparator (custom ordering)
public static final Comparator<Employee> BY_SALARY =
    Comparator.comparingDouble(Employee::getSalary);
public static final Comparator<Employee> BY_NAME =
    Comparator.comparing(Employee::getName, String.CASE_INSENSITIVE_ORDER);

// Usage
employees.sort(Employee.BY_SALARY.reversed());
employees.sort(Employee.BY_NAME);
```

### 4. **Views**

```java
// List views
List<Employee> subList = employees.subList(0, 2);

// Map views
Set<String> keys = empMap.keySet();
Collection<Employee> values = empMap.values();
Set<Map.Entry<String, Employee>> entries = empMap.entrySet();

// Iterating over views
for (Map.Entry<String, Employee> entry : empMap.entrySet()) {
    System.out.println(entry.getKey() + " -> " + entry.getValue());
}
```

### 5. **Wrappers**

```java
// Unmodifiable wrapper
List<Employee> unmodifiable = Collections.unmodifiableList(employees);
// unmodifiable.add(new Employee("Test", 1000)); // Throws UnsupportedOperationException

// Synchronized wrapper
List<Employee> synchronizedList = Collections.synchronizedList(employees);

// Checked wrapper
List<Employee> checkedList = Collections.checkedList(new ArrayList<>(), Employee.class);
```

### 6. **Collections Algorithms**

```java
// Sorting
Collections.sort(employees); // Natural ordering
Collections.sort(employees, Employee.BY_SALARY); // Custom comparator

// Shuffling
Collections.shuffle(employees);

// Reversing
Collections.reverse(employees);

// Finding min/max
Employee minEmp = Collections.min(employees, Employee.BY_SALARY);
Employee maxEmp = Collections.max(employees, Employee.BY_SALARY);

// Binary search (requires sorted list)
int index = Collections.binarySearch(employees, targetEmployee);
```

### 7. **Set vs List**

```java
// List allows duplicates, maintains order
List<String> names = Arrays.asList("Lan", "Bình", "Lan"); // [Lan, Bình, Lan]

// Set removes duplicates
Set<String> uniqueNames = new HashSet<>(names); // [Lan, Bình]
Set<String> sortedNames = new TreeSet<>(names); // [Bình, Lan] (sorted)
```

### 8. **HashMap vs TreeMap**

```java
// HashMap - O(1) average, no ordering
Map<String, Employee> hashMap = new HashMap<>();
hashMap.put("Lan", emp1);
hashMap.put("Bình", emp2);

// TreeMap - O(log n), sorted by keys
Map<String, Employee> treeMap = new TreeMap<>(hashMap);
// Keys are automatically sorted: [Bình, Lan]
```

### 9. **ArrayList vs LinkedList**

```java
// ArrayList - Fast random access, slow insertions
List<Employee> arrayList = new ArrayList<>();
arrayList.add(0, newEmployee); // O(n) - shifts elements

// LinkedList - Fast insertions, slow random access
List<Employee> linkedList = new LinkedList<>();
linkedList.add(0, newEmployee); // O(1) - just link nodes
```

### 10. **equals() và hashCode() cho Collections**

```java
@Override
public boolean equals(Object other) {
    if (this == other) return true;
    if (!(other instanceof Employee)) return false;
    Employee e = (Employee) other;
    return this.getName().equals(e.getName()) &&
           Double.compare(this.salary, e.salary) == 0;
}

@Override
public int hashCode() {
    return getName().hashCode() * 31 + Double.hashCode(salary);
}
```

## 🔧 Tính năng chính

- ✅ **Collection Interfaces** (List, Set, Map, Queue)
- ✅ **Iterator Pattern** và enhanced for-loop
- ✅ **Object Comparison** (Comparable, Comparator)
- ✅ **Views** (subList, keySet, entrySet, values)
- ✅ **Wrappers** (unmodifiable, synchronized, checked)
- ✅ **Collections Algorithms** (sort, shuffle, reverse, min, max)
- ✅ **Set vs List** (duplicates, ordering)
- ✅ **HashMap vs TreeMap** (performance, ordering)
- ✅ **ArrayList vs LinkedList** (access patterns)
- ✅ **equals() và hashCode()** cho Set/Map
- ✅ **Vietnamese localization** với VND currency

## 📚 Kiến thức thu được

1. **Collection Interfaces**: Hiểu sự khác biệt giữa List, Set, Map, Queue
2. **Iterator Pattern**: Cách duyệt collections an toàn
3. **Object Comparison**: Comparable cho natural ordering, Comparator cho custom ordering
4. **Views**: Cách truy cập dữ liệu collections qua views
5. **Wrappers**: Bảo vệ collections khỏi thay đổi không mong muốn
6. **Collections Algorithms**: Các thuật toán tiêu chuẩn của JCF
7. **Performance**: Hiểu khi nào dùng HashMap vs TreeMap, ArrayList vs LinkedList
8. **equals() và hashCode()**: Quan trọng cho Set và Map

## 🎯 Ứng dụng thực tế

- **List**: Quản lý danh sách nhân viên, sản phẩm
- **Set**: Lưu trữ tags, categories, unique identifiers
- **Map**: Cache, configuration, lookup tables
- **Queue**: Task scheduling, event processing
- **Views**: Data analysis, reporting
- **Wrappers**: API design, security
- **Algorithms**: Data processing, analytics

Project này cung cấp một **demo hoàn chỉnh** về Java Collections Framework! 🎉
