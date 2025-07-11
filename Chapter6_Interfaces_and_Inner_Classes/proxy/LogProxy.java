package proxy;

import interfaces.Payable;
import people.Person;
import java.util.logging.Logger;
import java.util.logging.Level;

public class LogProxy implements Payable {
    private final Person person;
    private final Logger logger = Logger.getLogger(LogProxy.class.getName());

    public LogProxy(Person person) {
        this.person = person;
    }

    @Override
    public double calculateSalary() {
        logger.info("Tính lương cho: " + person.getName());
        double salary = person.calculateSalary();
        logger.info("Lương của " + person.getName() + ": " + formatVND((int)salary));
        return salary;
    }

    @Override
    public String getPaymentInfo() {
        logger.info("Lấy thông tin lương cho: " + person.getName());
        String info = person.getPaymentInfo();
        logger.info("Thông tin lương: " + info);
        return info;
    }

    public void logPromotion(String action) {
        logger.log(Level.INFO, "Hoạt động thăng chức: {0} cho {1}", 
                   new Object[]{action, person.getName()});
    }

    public void logSalaryChange(double oldSalary, double newSalary) {
        logger.log(Level.INFO, "Thay đổi lương: {0} -> {1} cho {2}", 
                   new Object[]{formatVND((int)oldSalary), formatVND((int)newSalary), person.getName()});
    }

    private String formatVND(int amount) {
        return String.format("%,d VND", amount);
    }
}
