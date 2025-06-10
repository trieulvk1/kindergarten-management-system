package trieu.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import trieu.training.model.Ngay;

/**
 * Interface định nghĩa các phương thức truy vấn dữ liệu liên quan đến đối tượng Ngay.
 * Sử dụng JpaRepository để tương tác với cơ sở dữ liệu.
 * @author LuongVanTrieu 20/06/2001
 */
@Repository
public interface NgayRepository extends JpaRepository<Ngay, String> {

    /**
     * Tìm kiếm một Ngay bằng mã Ngay.
     * @param maNgay Mã Ngay cần tìm.
     * @return Đối tượng Ngay nếu tồn tại, ngược lại trả về null.
     */
    Ngay findByMaNgay(String maNgay);

    /**
     * Tìm kiếm danh sách Ngay bằng danh sách mã Ngay.
     * @param maNgays Danh sách mã Ngay cần tìm.
     * @return Danh sách các đối tượng Ngay tương ứng.
     */
    List<Ngay> findByMaNgayIn(List<String> maNgays);
}
