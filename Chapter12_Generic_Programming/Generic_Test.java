package Chapter12_Generic_Programming;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

public class Generic_Test {
    // [1] Method generic không phụ thuộc class
    public static <T> T getMiddle(T... a) {
        return a[a.length / 2];
    }

    // [2] Method generic có ràng buộc kiểu
    public static <T extends Comparable<? super T>> Pair<T> minmax(T[] a) {
        if (a == null || a.length == 0) return null;
        T min = a[0];
        T max = a[0];

        for (T elem : a) {
            if (elem.compareTo(min) < 0) min = elem;
            if (elem.compareTo(max) > 0) max = elem;
        }
        return new Pair<>(min, max);
    }

    // [3] Dùng wildcard (đọc)
    public static void printUpperBound(Pair<? extends Number> p) {
        System.out.println("First: " + p.getFirst());
        System.out.println("Second: " + p.getSecond());
    }

    // [4] Dùng wildcard (ghi)
    public static void setLowerBound(Pair<? super Integer> p ) {
        p.setFirst(100);
        p.setSecond(200);
    }

    public static void main(String[] args) {
        // Test getMiddle
        String middle = getMiddle("Java", "Python", "C++", "Ruby");
        System.out.println("Middle: " + middle);

        // Test minmax với LocalDate
        LocalDate[] dates = {
                LocalDate.of(2022, 5, 20),
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2025, 07, 11)
        };

        Pair<LocalDate> result = minmax(dates);
        System.out.println("Min date: " + result.getFirst());
        System.out.println("Max date: " + result.getSecond());

        // Test wildcard
        Pair<Integer> p1 = new Pair<>(10, 20);
        printUpperBound(p1);
        setLowerBound(p1);
        System.out.println("After setting: " + p1.getFirst() + ", " + p1.getSecond());
    }
}
