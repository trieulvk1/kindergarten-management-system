package trieu.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import trieu.training.model.Login;

/**
 * Repository cho đối tượng Login.
 * 
 * This interface extends JpaRepository, providing the basic CRUD operations.
 * 
 * @Repository: Đánh dấu đây là một bean repository Spring.
 * @param <Login> kiểu dữ liệu của đối tượng Login.
 * @param <String> kiểu dữ liệu của khóa chính của Login.
 * 
 * @author LuongVanTrieu 20/06/2001
 */
@Repository
public interface LoginRepository extends JpaRepository<Login, String>{

    /**
     * Phương thức đăng nhập.
     * 
     * @param userName Tên người dùng.
     * @param passWord Mật khẩu.
     * @return Danh sách các đối tượng Login có userName và passWord tương ứng.
     */
    @Query("SELECT l FROM Login l WHERE l.userName = :userName and l.passWord = :passWord")
    public Login doLogin(@Param("userName") String userName, @Param("passWord") String passWord);
    
    /**
     * Phương thức đăng nhập.
     * 
     * @param userName Tên người dùng.
     * @return Kiểm tra userName có tồn tại trong database hay không.
     */
    @Query("SELECT l FROM Login l WHERE l.userName = :userName")
    public Login findByUserName(@Param("userName") String userName);
}
