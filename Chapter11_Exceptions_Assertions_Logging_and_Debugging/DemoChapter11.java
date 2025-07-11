import java.util.InputMismatchException; 
import java.util.Scanner;
import java.util.logging.*;            

class ExceptionDiemKhongHopLe extends Exception {
    public ExceptionDiemKhongHopLe(String message) {
        super(message); // gọi constructor của lớp cha Exception
    }
}

class NguoiHoc {
    private String hoTen;
    private double diemTB;

    // throw + throws + Checked Exception
    public NguoiHoc(String hoTen, double diemTB) throws ExceptionDiemKhongHopLe {
        if (diemTB < 0 || diemTB > 10) {
            throw new ExceptionDiemKhongHopLe("Điểm phải nằm trong khoảng từ 0 đến 10.");
        }
        this.hoTen = hoTen;
        this.diemTB = diemTB;
    }

    public void hienThi() {
        System.out.println("Họ tên: " + hoTen + ", Điểm TB: " + diemTB);
    }
}

public class DemoChapter11 {
    // Logger
    private static final Logger logger = Logger.getLogger(DemoChapter11.class.getName());

    public static void main(String[] args) {
        // cấu hình logger 
        logger.setLevel(Level.ALL);
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.ALL);
        logger.addHandler(ch);
        logger.setUseParentHandlers(false); // tắt handler mặc định

        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Nhập họ tên học viên: ");
            String ten = sc.nextLine();

            System.out.print("Nhập điểm trung bình: ");
            double diem = sc.nextDouble();

            // Assertion: kiểm tra logic trước khi xử lý (chạy với: java -ea DemoChapter11)
            assert diem >= 0 && diem <= 10 : "Điểm không hợp lệ";

            // Gọi constructor
            NguoiHoc hv = new NguoiHoc(ten, diem);
            hv.hienThi();

        } catch (ExceptionDiemKhongHopLe e) {
            // Bắt Custom Exception (checked exception)
            logger.warning("Lỗi nhập điểm: " + e.getMessage());

        } catch (InputMismatchException e) {
            // Bắt lỗi nhập sai kiểu (unchecked exception)
            logger.severe("Lỗi: Điểm phải là số.");
            e.printStackTrace(); // debug khi lỗi

        } catch (Exception e) {
            // Exception tổng
            logger.log(Level.SEVERE, "Lỗi không xác định", e);

            // getStackTrace(): lấy thông tin từng dòng lỗi
            for (StackTraceElement element : e.getStackTrace()) {
                System.out.println("tại: " + element);
            }

        } finally {
            System.out.println("Kết thúc chương trình");
            sc.close();
        }
    }
}