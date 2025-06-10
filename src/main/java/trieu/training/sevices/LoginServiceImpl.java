package trieu.training.sevices;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import trieu.training.model.Login;
import trieu.training.repository.LoginRepository;

/**
 * Class service để thực hiện các hoạt động liên quan đến đăng nhập. Sử dụng để
 * xác thực thông tin đăng nhập của người dùng.
 * 
 * @author LuongVanTrieu
 * @since 20/06/2001
 */
@Service
@Transactional
public class LoginServiceImpl implements LoginServiceInterface {

	@Autowired
	private LoginRepository loginRepository;

	/**
	 * Xác thực thông tin đăng nhập của người dùng.
	 * 
	 * @param userName Tên đăng nhập của người dùng.
	 * @param passWord Mật khẩu của người dùng.
	 * @return Đối tượng Login tương ứng với thông tin đăng nhập cung cấp.
	 */
	public Login doLogin(String userName, String passWord) {
		return loginRepository.doLogin(userName, passWord);
	}

	/**
	 * Tìm kiếm thông tin đăng nhập của người dùng bằng tên đăng nhập.
	 * 
	 * @param userName Tên đăng nhập của người dùng cần tìm.
	 * @return Đối tượng Login tương ứng với tên đăng nhập cung cấp.
	 */
	public Login findById(String userName) {
		return loginRepository.findByUserName(userName);
	}

	/**
	 * Lưu hoặc cập nhật thông tin đăng nhập của người dùng.
	 * 
	 * @param login Đối tượng Login cần lưu hoặc cập nhật.
	 * @return Đối tượng Login sau khi đã lưu hoặc cập nhật.
	 */
	public Login saveOrUpdate(Login login) {
		return loginRepository.save(login);
	}
}
