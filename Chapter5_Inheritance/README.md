# 🏢 Java Inheritance Demo Project

## 📋 Mô tả

Project này là một demo minh họa các khái niệm cơ bản về **Inheritance (Kế thừa)** trong Java, thuộc Chapter 5 của sách Core Java Volume I- Fundamentals 9th Edition- Horstmann, Cay S. & Cornell, Gary.

Project mô phỏng hệ thống quản lý nhân viên với các cấp bậc khác nhau.

## 🎯 Mục tiêu

- Minh họa khái niệm Inheritance trong Java
- Demo các tính năng OOP cơ bản
- Thực hành với abstract classes, method overriding
- Hiểu về polymorphism và type casting

## 📁 Cấu trúc Project

```
Chapter5_Inheritance/
├── Main.java                 # Entry point của ứng dụng
├── people/                   # Package chứa các lớp nhân viên
│   ├── Person.java          # Lớp abstract cơ sở
│   ├── Employee.java        # Kế thừa từ Person
│   ├── Manager.java         # Kế thừa từ Employee
│   └── CEO.java            # Kế thừa từ Person (final class)
├── utils/                    # Package chứa các utility classes
│   ├── Department.java      # Quản lý danh sách nhân viên
│   └── Rank.java           # Enum định nghĩa cấp bậc
└── model/                    # Package chứa business logic
    └── BonusCalculator.java # Tính toán bonus với autoboxing
```

## 🏗️ Hệ thống phân cấp

```
Person (abstract)
├── Employee
│   └── Manager
└── CEO (final)
```

## 📝 Kết quả chạy

Khi chạy chương trình, bạn sẽ thấy output tương tự:

```
Name: Alice | Employee with salary: $40000.0
Name: Bob | Manager of IT with salary $60000.0
Name: Charlie | The CEO of the company
Bonus = $2000
→ Department of manager: IT
emp.equals(emp2)? true
```

## 🎓 Các khái niệm Java được demo

### 1. **Inheritance (Kế thừa)**

```java
public class Employee extends Person
public class Manager extends Employee
public class CEO extends Person
```

### 2. **Abstract Classes**

```java
public abstract class Person {
    public abstract String getDescription();
}
```

### 3. **Method Overriding**

```java
@Override
public String getDescription() {
    return "Manager of " + department + " with salary $" + getSalary();
}
```

### 4. **Polymorphism**

```java
ArrayList<Person> members = new ArrayList<>();
// Có thể chứa Employee, Manager, CEO objects
```

### 5. **instanceof + Type Casting**

```java
if (p instanceof Manager) {
    Manager m = (Manager) p;
    System.out.println("Department: " + m.getDepartment());
}
```

### 6. **equals() + hashCode()**

```java
@Override
public boolean equals(Object other) {
    if (this == other) return true;
    if (!(other instanceof Person)) return false;
    Person p = (Person) other;
    return name.equals(p.name);
}
```

### 7. **Autoboxing**

```java
public static Integer calculateBonus(int base, int multiplier) {
    return base * multiplier; // int → Integer
}
```

### 8. **Final Classes & Methods**

```java
public final class CEO extends Person
public final double getSalary()
```

### 9. **Varargs**

```java
public void addMembers(Person... people)
```

## 🔧 Tính năng chính

- ✅ **Hệ thống phân cấp nhân viên** với inheritance
- ✅ **Polymorphism** với ArrayList<Person>
- ✅ **Type checking** với instanceof
- ✅ **Method overriding** cho getDescription()
- ✅ **Autoboxing** trong BonusCalculator
- ✅ **equals() và hashCode()** implementation
- ✅ **Final classes và methods**
- ✅ **Varargs** trong Department
