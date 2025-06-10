package trieu.training.sevices;

import java.util.List;

import trieu.training.dto.SuatAnDto;
import trieu.training.dto.ThucPhamDto;
import trieu.training.model.ThucPham;

/**
 * Interface định nghĩa các phương thức cung cấp dịch vụ liên quan đến quản lý thực phẩm.
 * Các lớp cụ thể sẽ triển khai interface này để thực hiện các phương thức cụ thể.
 * @author LuongVanTrieu 20/06/2001
 */
public interface ThucPhamServiceInterface {

	/**
	 * Lưu hoặc cập nhật thông tin thực phẩm.
	 * 
	 * @param thucPham Thực phẩm cần lưu hoặc cập nhật.
	 * @return Thực phẩm đã được lưu hoặc cập nhật.
	 */
	public ThucPham saveOrUpdate(ThucPham thucPham);

	/**
	 * Hiển thị thông tin thực phẩm theo id.
	 * 
	 * @param idThucPham Id của thực phẩm cần hiển thị thông tin.
	 * @return Thực phẩm có id tương ứng hoặc null nếu không tìm thấy.
	 */
	public ThucPham findByIdThucPham(String idThucPham);

	/**
	 * Hiển thị các thực phẩm theo mã nhóm.
	 * 
	 * @param maNhom Mã nhóm thực phẩm.
	 * @return Danh sách đối tượng ThucPhamDto chứa thông tin các thực phẩm trong
	 *         nhóm.
	 */
	public List<ThucPhamDto> listThucPhamByMaNhom(String maNhom);

	/**
	 * Hiển thị chi tiết thông tin thực phẩm.
	 * 
	 * @param idThucPham Id của thực phẩm cần hiển thị chi tiết.
	 * @return Đối tượng ThucPhamDto chứa chi tiết thông tin thực phẩm hoặc null nếu
	 *         không tìm thấy.
	 */
	public ThucPhamDto listCTCThucPham(String idThucPham);

	/**
	 * Hiển thị tất cả các món ăn trong suất ăn.
	 * 
	 * @param suatAn Mã suất ăn.
	 * @return Danh sách đối tượng SuatAnDto chứa thông tin các món ăn trong suất
	 *         ăn.
	 */
	public List<SuatAnDto> findThucPhamBySuatAn(Long suatAn);
	/**
	 * Tìm kiếm thực phẩm theo tên.
	 * 
	 * @param searchKey Từ khóa tìm kiếm.
	 * @return Danh sách đối tượng ThucPhamDto chứa thông tin thực phẩm được tìm
	 *         thấy.
	 */
	public List<ThucPhamDto> findThucPhamByTen(String searchKey);
}
