package trieu.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import trieu.training.model.ThucDonMau;

/**
 * Interface định nghĩa các phương thức truy vấn dữ liệu liên quan đến đối tượng ThucDonMau.
 * Sử dụng JpaRepository để tương tác với cơ sở dữ liệu.
 * @author LuongVanTrieu 20/06/2001
 */
@Repository
public interface ThucDonMauRepository extends JpaRepository<ThucDonMau, Long> {

    /**
     * Tìm kiếm thực đơn mẫu bằng tên thực đơn mẫu.
     * @param tenThucDonMau Tên thực đơn mẫu cần tìm.
     * @return Đối tượng ThucDonMau tương ứng hoặc null nếu không tìm thấy.
     */
    @Query("SELECT t FROM ThucDonMau t WHERE t.tenThucDonMau = :tenThucDonMau")
    ThucDonMau findByTenThucDonMau(@Param("tenThucDonMau") String tenThucDonMau);
}
