package trieu.training.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import trieu.training.model.ChiTietSuatAn;
import trieu.training.model.SuatAn;

/**
 * Repository cho đối tượng ChiTietSuatAn.
 * 
 * This interface extends JpaRepository, providing the basic CRUD operations.
 * 
 * @Repository: Đánh dấu đây là một bean repository Spring.
 * @param <ChiTietSuatAn> kiểu dữ liệu của đối tượng ChiTietSuatAn.
 * @param <Long> kiểu dữ liệu của khóa chính của ChiTietSuatAn.
 * 
 * @author LuongVanTrieu 20/06/2001
 */
@Repository
public interface ChiTietSuatAnRepository extends JpaRepository<ChiTietSuatAn, Long>{
	
    /**
     * Xóa một chi tiết suất ăn dựa trên mã suất ăn và mã thực phẩm.
     * 
     * @param maSuatAn Mã suất ăn của chi tiết suất ăn cần xóa.
     * @param idThucPham Mã thực phẩm của chi tiết suất ăn cần xóa.
     * @author LuongVanTrieu 20/06/2001
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM ChiTietSuatAn ct WHERE ct.suatAn.maSuatAn = :maSuatAn AND ct.thucPham.idThucPham = :idThucPham")
    void deleteById(@Param("maSuatAn") Long maSuatAn, @Param("idThucPham") String idThucPham);
    
    /**
     * Kiểm tra thông tin mã suất ăn, mã thực phẩm có tồn tại trong bảng chi tiết suất ăn hay không.
     * 
     * @param maSuatAn Mã suất ăn.
     * @param idThucPham Mã thực phẩm.
     * @author LuongVanTrieu 20/06/2001
     */
    @Query("SELECT CASE WHEN COUNT(ct) > 0 THEN true ELSE false END FROM ChiTietSuatAn ct WHERE ct.suatAn.maSuatAn = :maSuatAn AND ct.thucPham.idThucPham = :idThucPham")
    boolean existsBySuatAn_MaSuatAnAndThucPham_IdThucPham(@Param("maSuatAn") Long maSuatAn, @Param("idThucPham") String idThucPham);
    
    /**
     * Kiểm tra thông tin mã suất ăn có tồn tại trong bảng chi tiết suất ăn hay không.
     * @param maSuatAn Mã suất ăn.
     * @author LuongVanTrieu 20/06/2001
     */
    @Query("SELECT COUNT(ct) FROM ChiTietSuatAn ct WHERE ct.suatAn.maSuatAn = :maSuatAn")
    long countThucPhamByIdSuatAn(@Param("maSuatAn") Long maSuatAn);
    
    void deleteBySuatAn(SuatAn suatAn);
}
