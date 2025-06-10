package trieu.training.sevices;

import java.time.LocalDate;
import java.util.List;

import trieu.training.dto.ThucDonDto;

/**
 * Interface định nghĩa các phương thức cung cấp dịch vụ liên quan đến quản lý thực đơn.
 * Các lớp cụ thể sẽ triển khai interface này để thực hiện các phương thức cụ thể.
 * @author LuongVanTrieu 20/06/2001
 */
public interface ThucDonServiceInterface {
	
	 /**
     * Hiển thị danh sách thực đơn mẫu.
     * @return Danh sách đối tượng ThucDonDto chứa thông tin thực đơn mẫu.
     */
	public List<ThucDonDto> getAllThucDon();

	/**
     * Hiển thị tất cả thông tin thực đơn mẫu.
     * @param maThucDonMau Mã thực đơn mẫu cần hiển thị. 
     * Nếu maThucDonMau là null, hiển thị tất cả thông tin thực đơn mẫu.
     * @return Danh sách đối tượng ThucDonDto chứa thông tin thực đơn mẫu.
     */
	public List<ThucDonDto> getAllThucDonMauById(Long maThucDonMau);

	 /**
     * Hiển thị danh sách thực đơn theo tuần của một ngày được chọn.
     * @param selectedDate Ngày được chọn.
     * @return Danh sách đối tượng ThucDonDto chứa thông tin thực đơn theo tuần.
     */
	public List<ThucDonDto> getThucDonByWeek(LocalDate selectedDate);

	 /**
     * Lưu thông tin thực đơn.
     * @param selectedDate Ngày được chọn.
     * @param thucDonDtos Danh sách đối tượng ThucDonDto chứa thông tin thực đơn.
     * @param maThucDon Mã thực đơn mẫu cần lưu. 
     * Nếu maThucDon là null, tạo mới một thực đơn mẫu.
     */
	public void saveThucDon(LocalDate selectedDate, List<ThucDonDto> thucDonDtos, Long maThucDon);

}
