package trieu.training.sevices;

import java.util.List;

import trieu.training.model.NhomThucPham;
/**
 * Interface định nghĩa các phương thức cung cấp dịch vụ liên quan đến quản lý nhóm thực phẩm.
 * Các lớp cụ thể sẽ triển khai interface này để thực hiện các phương thức cụ thể.
 * @author LuongVanTrieu 20/06/2001
 */
public interface NhomThucPhamServiceInterface {

	/**
	 * Hiển thị tất cả các nhóm thực phẩm.
	 * 
	 * @return Danh sách các đối tượng NhomThucPham.
	 */
	public List<NhomThucPham> listNhomThucPham();
}
