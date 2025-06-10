package trieu.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import trieu.training.model.NhomTuoi;

/**
 * Interface repository để thao tác với bảng NhomTuoi trong cơ sở dữ liệu. Cung
 * cấp các phương thức để tìm kiếm nhóm tuổi dựa trên mã nhóm tuổi hoặc danh
 * sách mã nhóm tuổi.
 */
@Repository
public interface NhomTuoiRepository extends JpaRepository<NhomTuoi, String> {

	/**
	 * Tìm kiếm nhóm tuổi dựa trên mã nhóm tuổi.
	 * 
	 * @param maNhomTuoi Mã nhóm tuổi cần tìm kiếm.
	 * @return Đối tượng NhomTuoi tương ứng hoặc null nếu không tìm thấy.
	 * @author LuongVanTrieu 20/06/2001
	 */
	NhomTuoi findByMaNhomTuoi(String maNhomTuoi);

	/**
	 * Tìm kiếm danh sách nhóm tuổi dựa trên một danh sách mã nhóm tuổi.
	 * 
	 * @param maNhomTuois Danh sách mã nhóm tuổi cần tìm kiếm.
	 * @return Danh sách các đối tượng NhomTuoi tương ứng với danh sách mã nhóm tuổi
	 *         đã cung cấp.
	 * @author LuongVanTrieu 20/06/2001
	 */
	List<NhomTuoi> findByMaNhomTuoiIn(List<String> maNhomTuois);
}
