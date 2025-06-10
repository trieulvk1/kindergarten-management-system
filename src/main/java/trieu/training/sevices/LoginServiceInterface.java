package trieu.training.sevices;

import trieu.training.model.Login;

/**
 * Interface định nghĩa các phương thức cung cấp dịch vụ liên quan đến quản lý đăng nhập.
 * Các lớp cụ thể sẽ triển khai interface này để thực hiện các phương thức cụ thể.
 * @author LuongVanTrieu 20/06/2001
 */
public interface LoginServiceInterface {

	/**
	 * Xác thực thông tin đăng nhập của người dùng.
	 * 
	 * @param userName Tên đăng nhập của người dùng.
	 * @param passWord Mật khẩu của người dùng.
	 * @return Đối tượng Login tương ứng với thông tin đăng nhập cung cấp.
	 */
	public Login doLogin(String userName, String passWord);

	/**
	 * Tìm kiếm thông tin đăng nhập của người dùng bằng tên đăng nhập.
	 * 
	 * @param userName Tên đăng nhập của người dùng cần tìm.
	 * @return Đối tượng Login tương ứng với tên đăng nhập cung cấp.
	 */
	public Login findById(String userName);

	/**
	 * Lưu hoặc cập nhật thông tin đăng nhập của người dùng.
	 * 
	 * @param login Đối tượng Login cần lưu hoặc cập nhật.
	 * @return Đối tượng Login sau khi đã lưu hoặc cập nhật.
	 */
	public Login saveOrUpdate(Login login);
}
