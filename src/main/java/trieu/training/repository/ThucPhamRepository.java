package trieu.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import trieu.training.dto.SuatAnDto;
import trieu.training.dto.ThucPhamDto;
import trieu.training.model.ThucPham;

/**
 * Interface định nghĩa các phương thức truy vấn dữ liệu liên quan đến đối tượng ThucPham.
 * Sử dụng JpaRepository để tương tác với cơ sở dữ liệu.
 * @author LuongVanTrieu 20/06/2001
 */
public interface ThucPhamRepository extends JpaRepository<ThucPham, String> {
	
    /**
     * Hiển thị thông tin thực phẩm theo mã nhóm.
     * @param maNhom Mã nhóm thực phẩm.
     * @return Danh sách DTO ThucPhamDto chứa thông tin thực phẩm.
     * @author LuongVanTrieu 20/06/2001
     */
    @Query("SELECT new trieu.training.dto.ThucPhamDto(t.idThucPham, t.tenThucPham, t.nhomThucPham.maNhom, "
            + "t.nhomThucPham.tenNhom, t.chiTietChat.damDongVat, t.chiTietChat.damThucVat, t.chiTietChat.beoDongVat, t.chiTietChat.beoThucVat) "
            + "FROM ThucPham t WHERE t.nhomThucPham.maNhom = :maNhom")
    List<ThucPhamDto> findByMaNhomThucPham(@Param("maNhom") String maNhom);

    /**
     * Hiển thị thông tin thực phẩm theo mã thực phẩm.
     * @param idThucPham Mã thực phẩm.
     * @return DTO ThucPhamDto chứa thông tin thực phẩm.
     * @author LuongVanTrieu 20/06/2001
     */
    @Query("SELECT new trieu.training.dto.ThucPhamDto(t.idThucPham, t.tenThucPham, t.nhomThucPham.maNhom, "
            + "t.nhomThucPham.tenNhom, t.chiTietChat.damDongVat, t.chiTietChat.damThucVat, t.chiTietChat.beoDongVat, t.chiTietChat.beoThucVat) "
            + "FROM ThucPham t WHERE t.idThucPham = :idThucPham")
    ThucPhamDto findByThucPham(@Param("idThucPham") String idThucPham);
	
    /**
     * Hiển thị thông tin suất ăn theo mã thực phẩm.
     * @param idThucPham Mã thực phẩm.
     * @return DTO SuatAnDto chứa thông tin suất ăn.
     * @author LuongVanTrieu 20/06/2001
     */
    @Query("SELECT new trieu.training.dto.SuatAnDto(tp.idThucPham, tp.tenThucPham, tp.donViTinh, "
            + "tp.soLuong, tp.donGia, tp.thanhTien, tp.nangLuong, tp.chatDam, tp.chatBeo, tp.botDuong, tp.noiLay, ct.damDongVat, ct.damThucVat, "
            + "ct.beoDongVat, ct.beoThucVat) FROM ThucPham tp "
            + "JOIN tp.chiTietChat ct WHERE tp.idThucPham = :idThucPham")
    SuatAnDto findByThucPhamById(@Param("idThucPham") String idThucPham);

    /**
     * Tìm kiếm thực phẩm theo id.
     * @param idThucPham Id thực phẩm.
     * @return Đối tượng ThucPham tương ứng hoặc null nếu không tìm thấy.
     * @author LuongVanTrieu 20/06/2001
     */
    ThucPham findByIdThucPham(String idThucPham);

    /**
     * Hiển thị danh sách thực phẩm trong suất ăn.
     * @param maSuatAn Mã suất ăn.
     * @return Danh sách DTO SuatAnDto chứa thông tin thực phẩm trong suất ăn.
     * @author LuongVanTrieu 20/06/2001
     */
    @Query("SELECT new trieu.training.dto.SuatAnDto(sa.maSuatAn, sa.tenSuatAn, sa.tongTien, tp.idThucPham, tp.tenThucPham, tp.donViTinh, "
            + "tp.soLuong, tp.donGia, tp.thanhTien, tp.nangLuong, tp.chatDam, tp.chatBeo, tp.botDuong, tp.noiLay, ct.damDongVat, ct.damThucVat, "
            + "ct.beoDongVat, ct.beoThucVat) " + "FROM SuatAn sa " + "JOIN sa.chiTietSuatAn ctsa "
            + "JOIN ctsa.thucPham tp " + "JOIN tp.chiTietChat ct " + "WHERE sa.maSuatAn = :maSuatAn")
    List<SuatAnDto> findThucPhamBySuatAn(@Param("maSuatAn") Long maSuatAn);
	
    /**
     * Tìm kiếm thực phẩm theo tên.
     * @param searchKey Từ khóa tìm kiếm.
     * @return Danh sách DTO ThucPhamDto chứa thông tin thực phẩm.
     * @author LuongVanTrieu 20/06/2001
     */
    @Query("SELECT new trieu.training.dto.ThucPhamDto(t.idThucPham, t.tenThucPham, t.nhomThucPham.maNhom, "
            + "t.nhomThucPham.tenNhom, t.chiTietChat.damDongVat, t.chiTietChat.damThucVat, t.chiTietChat.beoDongVat, t.chiTietChat.beoThucVat) "
            + "FROM ThucPham t WHERE LOWER(t.tenThucPham) LIKE LOWER(CONCAT('%', :searchKey, '%'))")
    List<ThucPhamDto> findThucPhamByTen(@Param("searchKey") String searchKey);
    
    
}
