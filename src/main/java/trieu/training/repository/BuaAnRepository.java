package trieu.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import trieu.training.model.BuaAn;

/**
 * Repository cho đối tượng BuaAn.
 * 
 * This interface extends JpaRepository, providing the basic CRUD operations.
 * 
 * @Repository: Đánh dấu đây là một bean repository Spring.
 * @param <BuaAn> kiểu dữ liệu của đối tượng BuaAn.
 * @param <String> kiểu dữ liệu của khóa chính của BuaAn.

 * @author LuongVanTrieu 20/06/2001
 */
@Repository
public interface BuaAnRepository extends JpaRepository<BuaAn, String> {

    /**
     * Tìm kiếm một bữa ăn dựa trên mã bữa ăn.
     * 
     * @param maBuaAn Mã bữa ăn cần tìm kiếm.
     * @return Đối tượng BuaAn có mã bữa ăn tương ứng, hoặc null nếu không tìm thấy.
     * @author LuongVanTrieu 20/06/2001
     */
    BuaAn findByMaBuaAn(String maBuaAn);
    
    /**
     * Tìm kiếm danh sách bữa ăn dựa trên một danh sách các mã bữa ăn.
     * 
     * @param maBuaAns Danh sách mã bữa ăn cần tìm kiếm.
     * @return Danh sách các đối tượng BuaAn có mã bữa ăn trong danh sách đầu vào.
     * @author LuongVanTrieu 20/06/2001
     */
    List<BuaAn> findByMaBuaAnIn(List<String> maBuaAns);
}
