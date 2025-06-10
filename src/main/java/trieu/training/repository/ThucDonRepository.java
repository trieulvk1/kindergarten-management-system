package trieu.training.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import trieu.training.dto.ThucDonDto;
import trieu.training.model.BuaAn;
import trieu.training.model.Ngay;
import trieu.training.model.NhomTuoi;
import trieu.training.model.SuatAn;
import trieu.training.model.ThucDon;
import trieu.training.model.ThucDonMau;

/**
 * Interface định nghĩa các phương thức truy vấn dữ liệu liên quan đến đối tượng ThucDon.
 * Sử dụng JpaRepository để tương tác với cơ sở dữ liệu.
 * @author LuongVanTrieu 20/06/2001
 */
@Repository
public interface ThucDonRepository extends JpaRepository<ThucDon, Long> {

    /**
     * Hiển thị tên thực đơn mẫu.
     * @param isThucDonMau Flag chỉ định liệu thực đơn có phải là thực đơn mẫu hay không.
     * @return Danh sách DTO ThucDonDto chứa mã và tên của thực đơn mẫu.
     * @author LuongVanTrieu 20/06/2001
     */
    @Query("SELECT DISTINCT new trieu.training.dto.ThucDonDto(t.thucDonMau.maThucDonMau, m.tenThucDonMau, t.isThucDonMau) "
            + "FROM ThucDon t JOIN t.thucDonMau m " + "WHERE t.isThucDonMau = :isThucDonMau")
    public List<ThucDonDto> findThucDonMau(@Param("isThucDonMau") boolean isThucDonMau);

    /**
     * Hiển thị tất cả thông tin thực đơn mẫu.
     * @param maThucDonMau Mã của thực đơn mẫu cần hiển thị thông tin.
     * @return Danh sách các thực đơn có mã thực đơn mẫu tương ứng.
     * @author LuongVanTrieu 20/06/2001
     */
    @Query("SELECT t FROM ThucDon t WHERE t.thucDonMau.maThucDonMau=:maThucDonMau")
    List<ThucDon> getAllThucDonMau(@Param("maThucDonMau") Long maThucDonMau);

    ThucDon findByNgayThucDonAndNgayAndBuaAn(@Param("ngayThucDon") LocalDate ngayThucDon, @Param("ngay") Ngay ngay, @Param("buaAn") BuaAn buaAn);

    /**
     * Hiển thị thông tin thực đơn trong một tuần.
     * @param startDate Ngày bắt đầu của tuần.
     * @param endDate Ngày kết thúc của tuần.
     * @return Danh sách các thực đơn trong khoảng thời gian từ startDate đến endDate.
     * @author LuongVanTrieu 20/06/2001
     */
    @Query("SELECT td FROM ThucDon td WHERE td.ngayThucDon BETWEEN :startDate AND :endDate")
    List<ThucDon> findThucDonByWeek(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    /**
     * Hiển thị các thực đơn từ thứ 2 đến thứ 7 của tuần chứa ngày được chọn.
     * @param selectedDate Ngày được chọn.
     * @return Danh sách các thực đơn từ thứ 2 đến thứ 7 của tuần chứa selectedDate.
     * @author LuongVanTrieu 20/06/2001
     */
    @Query(value = "SELECT * FROM ThucDon td WHERE td.ngayThucDon BETWEEN DATE_SUB(:selectedDate, INTERVAL (DAYOFWEEK(:selectedDate) - 2) DAY) "
            + "AND DATE_ADD(:selectedDate, INTERVAL (7 - DAYOFWEEK(:selectedDate)) DAY)", nativeQuery = true)
    List<ThucDon> findThucDonByWeek(@Param("selectedDate") LocalDate selectedDate);

    /**
     * Hiển thị ngày thực đơn, ngày, bữa ăn và nhóm ăn.
     * @param selectedDate Ngày được chọn.
     * @return Thực đơn  chứa selectedDate.
     * @author LuongVanTrieu 20/06/2001
     */
    ThucDon findByNgayThucDonAndNgayAndBuaAnAndNhomTuoiAndSuatAn(
            LocalDate ngayThucDon, Ngay ngay, BuaAn buaAn, NhomTuoi nhomTuoi, SuatAn suatAn);

    /**
     * Hiển thị thực đơn theo ngày tùy chọn.
     * @param Ngày được chọn.
     * @return Danh sách các thực đơn  chứa date.
     * @author LuongVanTrieu 20/06/2001
     */
    List<ThucDon> findByNgayThucDon(LocalDate date);
    
    /**
     * Tìm các thực đơn trong một khoảng thời gian từ startDate đến endDate.
     * @param startDate Ngày bắt đầu của khoảng thời gian.
     * @param endDate Ngày kết thúc của khoảng thời gian.
     * @return Danh sách các thực đơn trong khoảng thời gian từ startDate đến endDate.
     * @author LuongVanTrieu 20/06/2001
     */
    List<ThucDon> findByNgayThucDonBetween(LocalDate startDate, LocalDate endDate);
    
    /**
     * Kiểm tra xem có tồn tại thực đơn nào cho ngày nhất định, bữa ăn, nhóm tuổi và suất ăn đã cho hay không.
     * @param ngayThucDon Ngày của thực đơn.
     * @param buaAn Bữa ăn của thực đơn.
     * @param nhomTuoi Nhóm tuổi của thực đơn.
     * @param suatAn Suất ăn của thực đơn.
     * @return true nếu tồn tại, ngược lại trả về false.
     * @author LuongVanTrieu 20/06/2001
     */
    boolean existsByNgayThucDonAndBuaAnAndNhomTuoiAndSuatAn(LocalDate ngayThucDon, BuaAn buaAn, NhomTuoi nhomTuoi, SuatAn suatAn);
    
    /**
     * Tìm thực đơn bằng ngày thực đơn, bữa ăn và ngày.
     * @param ngayThucDon Ngày thực đơn.
     * @param buaAn Bữa ăn của thực đơn.
     * @param ngay Ngày của thực đơn.
     * @return Đối tượng ThucDon tương ứng hoặc null nếu không tìm thấy.
     * @author LuongVanTrieu 20/06/2001
     */
    @Query("SELECT t FROM ThucDon t WHERE t.ngayThucDon = :ngayThucDon AND t.buaAn = :buaAn AND t.ngay=:ngay")
    ThucDon findByNgayThucDonAndBuaAnAndNgay(@Param("ngayThucDon") LocalDate ngayThucDon, @Param("buaAn") BuaAn buaAn, @Param("ngay") Ngay ngay);
    
    /**
     * Tìm kiếm các bản ghi ThucDon dựa trên ThucDonMau cho trước.
     *
     * @param thucDonMau Thực đơn mẫu dùng để tìm kiếm các bản ghi ThucDon.
     * @return Danh sách các bản ghi ThucDon tương ứng với ThucDonMau được cung cấp.
     */
    List<ThucDon> findByThucDonMau(@Param("thucDonMau") ThucDonMau thucDonMau);
    
    List<ThucDon> findBySuatAn_MaSuatAn(Long maSuatAn);
}
