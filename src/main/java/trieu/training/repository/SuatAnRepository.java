package trieu.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import trieu.training.dto.SuatAnDto;
import trieu.training.model.SuatAn;

/**
 * Interface định nghĩa các phương thức truy vấn dữ liệu liên quan đến đối tượng SuatAn.
 * Sử dụng JpaRepository để tương tác với cơ sở dữ liệu.
 * @author LuongVanTrieu 20/06/2001
 */
@Repository
public interface SuatAnRepository extends JpaRepository<SuatAn, Long> {

    
	@Query("SELECT new trieu.training.dto.SuatAnDto(sa.maSuatAn, sa.tenSuatAn) FROM SuatAn sa")
    List<SuatAnDto> findAllSuatAnMau();
	
    /**
     * Truy vấn thông tin suất ăn để làm mẫu dựa trên mã suất ăn.
     * @param maSuatAn Mã suất ăn cần tìm thông tin.
     * @return Danh sách đối tượng SuatAnDto chứa thông tin suất ăn và các thực phẩm tương ứng.
     * @author LuongVanTrieu 20/06/2001
     */
    @Query("SELECT new trieu.training.dto.SuatAnDto(sa.maSuatAn, sa.tenSuatAn,sa.noiDung, sa.luaTuoi, sa.saSoLuong, sa.loaiSuatAn, sa.tongTien, tp.idThucPham, tp.tenThucPham, tp.donViTinh, "
            + "tp.soLuong, tp.donGia, tp.thanhTien, tp.nangLuong, tp.chatDam, tp.chatBeo, tp.botDuong, tp.noiLay, ct.damDongVat, ct.damThucVat, "
            + "ct.beoDongVat, ct.beoThucVat) " + "FROM SuatAn sa " + "JOIN sa.chiTietSuatAn ctsa "
            + "JOIN ctsa.thucPham tp " + "JOIN tp.chiTietChat ct " + "WHERE sa.maSuatAn = :maSuatAn")
    List<SuatAnDto> findSuatAnMauById(@Param("maSuatAn") Long maSuatAn);
    
    /**
     * Tìm suất ăn theo mã suất ăn.
     * @param id Mã suất ăn cần tìm.
     * @return Đối tượng SuatAn tương ứng với mã suất ăn hoặc null nếu không tìm thấy.
     * @author LuongVanTrieu 20/06/2001
     */
    SuatAn findByMaSuatAn(Long id);
    
    /**
     * Tìm suất ăn theo mã suất ăn.
     * @param maSuatAn Mã suất ăn cần tìm.
     * @return Đối tượng SuatAn tương ứng với mã suất ăn hoặc null nếu không tìm thấy.
     * @author LuongVanTrieu 20/06/2001
     */
    SuatAn findByMaSuatAn(String maSuatAn);
    
    /**
     * Tìm danh sách suất ăn dựa trên danh sách mã suất ăn.
     * @param maSuatAns Danh sách mã suất ăn cần tìm.
     * @return Danh sách các đối tượng SuatAn tương ứng.
     * @author LuongVanTrieu 20/06/2001
     */
    List<SuatAn> findByMaSuatAnIn(List<Long> maSuatAns);
    
    /**
     * Tìm một đối tượng SuatAn dựa trên tên của nó.
     *
     * @param tenSuatAn Tên của SuatAn cần tìm.
     * @return Đối tượng SuatAn tương ứng với tên được cung cấp.
     */
    @Query("SELECT sa FROM SuatAn sa WHERE sa.tenSuatAn = :tenSuatAn")
    SuatAn findSuatAnByTenSuatAn(@Param("tenSuatAn") String tenSuatAn);
    
    /**
     * Kiểm tra xem một bản ghi SuatAn tồn tại dựa trên các thông tin nhất định.
     *
     * @param tenSuatAn   Tên của SuatAn cần kiểm tra.
     * @param luaTuoi     Tuổi của SuatAn cần kiểm tra.
     * @param saSoLuong   Số lượng của SuatAn cần kiểm tra.
     * @param noiDung     Nội dung của SuatAn cần kiểm tra.
     * @param loaiSuatAn  Loại của SuatAn cần kiểm tra.
     * @return true nếu tồn tại một bản ghi SuatAn thỏa mãn các điều kiện, ngược lại trả về false.
     */
    @Query("SELECT CASE WHEN COUNT(sa) > 0 THEN true ELSE false END FROM SuatAn sa WHERE sa.tenSuatAn = :tenSuatAn AND sa.luaTuoi = :luaTuoi AND sa.saSoLuong = :saSoLuong AND sa.noiDung = :noiDung AND sa.loaiSuatAn = :loaiSuatAn")
    boolean existsByTenSuatAnAndLuaTuoiAndSaSoLuongAndNoiDungAndLoaiSuatAn(
        @Param("tenSuatAn") String tenSuatAn,
        @Param("luaTuoi") String luaTuoi,
        @Param("saSoLuong") int saSoLuong,
        @Param("noiDung") String noiDung,
        @Param("loaiSuatAn") String loaiSuatAn
    );
    
    /**
     * Tìm một đối tượng SuatAn dựa trên thông tin nhất định.
     *
     * @param tenSuatAn   Tên của SuatAn cần tìm.
     * @param luaTuoi     Tuổi của SuatAn cần tìm.
     * @param saSoLuong   Số lượng của SuatAn cần tìm.
     * @param noiDung     Nội dung của SuatAn cần tìm.
     * @param loaiSuatAn  Loại của SuatAn cần tìm.
     * @return Đối tượng SuatAn tương ứng với các thông tin được cung cấp.
     */
    @Query("SELECT sa FROM SuatAn sa WHERE sa.tenSuatAn = :tenSuatAn AND sa.luaTuoi = :luaTuoi AND sa.saSoLuong = :saSoLuong AND sa.noiDung = :noiDung AND sa.loaiSuatAn = :loaiSuatAn")
    SuatAn findByTenSuatAnAndLuaTuoiAndSaSoLuongAndNoiDungAndLoaiSuatAn(
        @Param("tenSuatAn") String tenSuatAn,
        @Param("luaTuoi") String luaTuoi,
        @Param("saSoLuong") int saSoLuong,
        @Param("noiDung") String noiDung,
        @Param("loaiSuatAn") String loaiSuatAn
    );

    List<SuatAn> findByTenSuatAnStartingWith(String tenSuatAn);
    
    @Query("SELECT new trieu.training.dto.SuatAnDto(sa.maSuatAn, sa.tenSuatAn,sa.noiDung, sa.luaTuoi, sa.saSoLuong, sa.loaiSuatAn, sa.tongTien) "
    		+ "FROM SuatAn sa "
    		+ "WHERE sa.maSuatAn = :maSuatAn")
    List<SuatAnDto> findByMaSuatAnList(@Param("maSuatAn")Long maSuatAn);
}
