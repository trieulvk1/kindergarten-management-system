package trieu.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import trieu.training.model.NhomThucPham;

/**
 * Interface định nghĩa các phương thức truy vấn dữ liệu liên quan đến đối tượng NhomThucPham.
 * Sử dụng JpaRepository để tương tác với cơ sở dữ liệu.
 * @author LuongVanTrieu 20/06/2001
 */
public interface NhomThucPhamRepository extends JpaRepository<NhomThucPham, String> {

    /**
     * Truy vấn mã nhóm dựa trên tên nhóm thực phẩm.
     * @param tenNhom Tên nhóm thực phẩm cần tìm mã.
     * @return Mã nhóm tương ứng với tên nhóm thực phẩm hoặc null nếu không tìm thấy.
     */
    @Query("SELECT n.maNhom FROM NhomThucPham n WHERE n.tenNhom = :tenNhom")
    String findMaNhomByTenNhom(@Param("tenNhom") String tenNhom);
}
