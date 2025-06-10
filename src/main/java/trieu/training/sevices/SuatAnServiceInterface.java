package trieu.training.sevices;

import java.util.List;

import trieu.training.dto.SuatAnDto;
import trieu.training.dto.SuatAnRequest;
import trieu.training.model.SuatAn;

/**
 * Interface định nghĩa các phương thức cung cấp dịch vụ liên quan đến quản lý suất ăn.
 * Các lớp cụ thể sẽ triển khai interface này để thực hiện các phương thức cụ thể.
 * @author LuongVanTrieu 20/06/2001
 */
public interface SuatAnServiceInterface {

	/**
	 * Thêm mới suất ăn cùng với các thực phẩm của suất ăn.
	 * 
	 * @param suatAnRequest Đối tượng SuatAnRequest chứa thông tin suất ăn và danh
	 *                      sách thực phẩm.
	 * @return Đối tượng SuatAn đã được thêm mới.
	 * @throws RuntimeException Ném ra khi không tìm thấy ThucPham tương ứng với các
	 *                          id thực phẩm trong danh sách.
	 */
	public SuatAn saveSuatAnChiTiet(SuatAnRequest suatAnRequest);

	/**
	 * Kiểm tra sự tồn tại của một bản ghi SuatAn và các bản ghi ChiTietSuatAn tương
	 * ứng trong cơ sở dữ liệu.
	 * 
	 * @param suatAnRequest Đối tượng SuatAnRequest chứa thông tin về SuatAn và danh
	 *                      sách ThucPham.
	 * @return true nếu tất cả các bản ghi tồn tại và false nếu có bất kỳ bản ghi
	 *         nào không tồn tại.
	 */
	public boolean kiemTraSuatAnTonTai(SuatAnRequest suatAnRequest);

	/**
	 * Xóa suất ăn.
	 * 
	 * @param maSuatAn Mã suất ăn cần xóa.
	 */
	public void deleteSuatAnById(Long maSuatAn);

	/**
	 * Hiển thị list suất ăn mẫu.
	 * 
	 * @return Danh sách suất ăn mẫu.
	 */
	public List<SuatAnDto> findAllSuatAnMau();

	/**
	 * Xóa thực phẩm trong bảng chi tiết suất ăn.
	 * 
	 * @param idSuatAn Mã suất ăn.
	 * @param idMonAn  Mã thực phẩm.
	 */
	public void deleteById(Long idSuatAn, String idMonAn);

	/**
	 * Hiển thị chi tiết của một thực phẩm.
	 * 
	 * @param idThucPham Mã thực phẩm.
	 * @return Đối tượng SuatAnDto chứa thông tin thực phẩm.
	 */
	public SuatAnDto addThucPhamById(String idThucPham);

	/**
	 * Hiển thị tất cả thông tin suất ăn mẫu.
	 * 
	 * @param maSuatAn Mã suất ăn mẫu.
	 * @return Danh sách các đối tượng SuatAnDto chứa thông tin suất ăn mẫu.
	 */
	public List<SuatAnDto> findSuatAnMauById(Long maSuatAn);

	/**
	 * Hiển thị tất cả suất ăn dùng cho thực đơn.
	 * 
	 * @return Danh sách tất cả suất ăn dùng cho thực đơn.
	 */
	public List<SuatAn> getAllSuatAn();

	/**
	 * Hiển thị tất cả thông tin suất ăn mẫu.
	 * 
	 * @param maSuatAn Mã suất ăn mẫu.
	 * @return Lưu đối tượng SuatAnDto chứa thông tin suất ăn mẫu.
	 */
	public boolean findSuatAnMauByIdToCopy(Long maSuatAn);

	/**
	 * Trích xuất chỉ số bản sao từ tên suất ăn dựa trên các tiền tố và hậu tố của bản sao.
	 * 
	 * @param tenSuatAn   Tên của suất ăn cần trích xuất chỉ số bản sao.
	 * @param baseName    Phần cơ bản của tên suất ăn.
	 * @param copyPrefix  Tiền tố của bản sao trong tên suất ăn.
	 * @param copySuffix  Hậu tố của bản sao trong tên suất ăn.
	 * @return Chỉ số của bản sao, nếu không tìm thấy trả về 0.
	 */
	public int getCopyIndexFromName(String tenSuatAn, String baseName, String copyPrefix, String copySuffix);

}
