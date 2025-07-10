# 🏢 Java Interfaces & Inner Classes Demo Project

## 📋 Mô tả

Project này là một demo minh họa các khái niệm cơ bản về **Interfaces** trong Java, thuộc Chapter 6 của sách Core Java Volume I- Fundamentals 9th Edition- Horstmann, Cay S. & Cornell, Gary.

Project mô phỏng hệ thống quản lý nhân viên với các interfaces để demo các tính năng OOP nâng cao.

## 🎯 Mục tiêu

- Minh họa khái niệm Interfaces trong Java
- Demo Abstract Classes vs Interfaces
- Thực hành với Inner Classes (static và non-static)
- Hiểu về Anonymous Inner Classes
- Demo Proxy Pattern
- Thực hành với Callback Pattern
- Hiểu về Final Variables trong Inner Classes

## 📁 Cấu trúc Project

```
Chapter6_Interfaces_and_Inner_Classes/
├── Main.java                 # Entry point của ứng dụng
├── interfaces/               # Package chứa các interfaces
│   ├── Payable.java         # Interface cho tính lương
│   ├── Promotable.java      # Interface cho thăng chức
│   ├── CustomCloneable.java # Interface cho clone objects
│   └── Callback.java        # Interface cho callback pattern
├── people/                   # Package chứa các lớp nhân viên
│   ├── Person.java          # Abstract class với inner classes
│   ├── Employee.java        # Với inner class SalaryManager
│   ├── Manager.java         # Với inner class TeamManager
│   └── CEO.java            # Với inner class CompanyManager
├── proxy/                    # Package chứa proxy pattern
│   └── LogProxy.java       # Proxy implementation với logging
└── utils/                    # Package chứa utility classes
    └── Department.java      # Với inner class StatisticsManager
```

## 🏗️ Hệ thống phân cấp

```
Person (abstract) implements Payable, CustomCloneable
├── Employee implements Promotable
│   └── Manager
└── CEO implements Payable, CustomCloneable
```

## 📝 Kết quả chạy

Khi chạy chương trình, bạn sẽ thấy output tương tự:

```
=== Demo Interfaces và Inner Classes trong Java ===
=== Quản lý nhân viên trong một công ty ===

📊 Thông tin lương:
- Lương cơ bản: 4,200,000 VND
- Lương cơ bản: 6,000,000 VND
- Lương CEO: 150,000,000 VND (Base: 100M + Bonus: 50,000,000 VND)

🎯 Thông tin thăng chức:
Vị trí: Nhân viên, Có thể thăng chức: true
✅ Thăng chức thành công! Lương mới: 5,040,000 VND

🏢 Quản lý nội bộ:
Lương hiện tại: 4,200,000 VND
Sau điều chỉnh: 5,000,000 VND
Phòng: IT, Team size: 0/20
✅ Thêm thành viên thành công. Team size: 1
✅ Thêm thành viên thành công. Team size: 2
Sau thêm thành viên: Phòng: IT, Team size: 2/20
```

## 🎓 Các khái niệm Java được demo

### 1. **Interfaces**

```java
public interface Payable {
    double calculateSalary();
    String getPaymentInfo();
}

public interface Promotable {
    boolean canPromote();
    void promote();
    String getPromotionInfo();
}
```

### 2. **Abstract vs Interface**

```java
// Abstract class có thể có implementation
public abstract class Person implements Payable, CustomCloneable {
    // Có thể có fields và methods với implementation
}

// Interface chỉ định nghĩa contract
public interface Payable {
    // Chỉ abstract methods
}
```

### 3. **Inner Classes**

```java
// Non-static inner class
public class SalaryManager {
    private final double MIN_SALARY = 3000000; // final variable
    public void adjustSalary(double newSalary) {
        if (isValidSalary(newSalary)) {
            Employee.this.salary = newSalary; // Access outer class
        }
    }
}

// Static inner class
public static class PersonFormatter {
    public static String formatInfo(Person person) {
        return String.format("[%d] %s", person.getId(), person.getName());
    }
}
```

### 4. **Anonymous Inner Classes**

```java
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
```

### 5. **Final Variables trong Inner Classes**

```java
public class SalaryManager {
    private final double MIN_SALARY = 3000000; // final variable
}

public class TeamManager {
    private final int MAX_TEAM_SIZE = 20; // final variable
}
```

### 6. **Static Inner Classes**

```java
public static class BonusCalculator {
    public static double calculateManagerBonus(double baseSalary, int teamSize) {
        return baseSalary * 0.1 * (1 + teamSize * 0.05);
    }
}
```

### 7. **Proxy Pattern**

```java
public class LogProxy implements Payable {
    private final Person person;
    private final Logger logger = Logger.getLogger(LogProxy.class.getName());

    @Override
    public double calculateSalary() {
        logger.info("Tính lương cho: " + person.getName());
        double salary = person.calculateSalary();
        logger.info("Lương của " + person.getName() + ": " + formatVND((int)salary));
        return salary;
    }
}
```

### 8. **Callback Pattern**

```java
public interface Callback {
    void onSuccess(String message);
    void onError(String error);
    void onProgress(int percentage);
}

// Sử dụng trong Employee
public void promote() {
    if (canPromote()) {
        // ... logic thăng chức
        if (promotionCallback != null) {
            promotionCallback.onSuccess("Thăng chức thành công!");
        }
    }
}
```

### 9. **Cloneable Interface**

```java
public interface CustomCloneable {
    CustomCloneable createClone();
    boolean isCloneable();
}

@Override
public CustomCloneable createClone() {
    return new Employee(getName(), this.salary);
}
```

## 🔧 Tính năng chính

- ✅ **Interface implementations** với Payable, Promotable, CustomCloneable
- ✅ **Inner Classes** (static và non-static) trong mỗi class
- ✅ **Anonymous Inner Classes** cho callback implementations
- ✅ **Final Variables** trong inner classes
- ✅ **Proxy Pattern** với LogProxy
- ✅ **Callback Pattern** cho event handling
- ✅ **Static Inner Classes** cho utility functions
- ✅ **Vietnamese localization** với VND currency
- ✅ **Realistic business logic** với salary management

## 🚀 Cách chạy

```bash
# Compile với UTF-8 encoding
javac -encoding UTF-8 -cp . Main.java

# Chạy chương trình
java Main
```

## 📚 Kiến thức thu được

1. **Interfaces vs Abstract Classes**: Hiểu sự khác biệt và khi nào sử dụng
2. **Inner Classes**: Non-static để access outer class, static cho utility
3. **Anonymous Inner Classes**: Cho one-time implementations
4. **Proxy Pattern**: Wrapping objects với additional behavior
5. **Callback Pattern**: Event-driven programming
6. **Final Variables**: Trong inner classes context
7. **Static Inner Classes**: Utility functions không cần access outer class

Project này cung cấp một **demo hoàn chỉnh** về Interfaces và Inner Classes trong Java! 🎉
