package trieu.training.controller;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import trieu.training.model.Login;
import trieu.training.sevices.LoginServiceInterface;

/**
 * Lớp Controller để xử lý các yêu cầu liên quan đến đăng nhập.
 * 
 * @author LuongVanTrieu 20/06/2001
 */
@Controller
public class LoginController {

	@Autowired
	private LoginServiceInterface loginService;

	/**
	 * Hiển thị trang chính và truyền đối tượng loginForm đến view.
	 * 
	 * @param model Đối tượng Model để truyền dữ liệu đến view.
	 * @return Tên của view "main".
	 */
	@RequestMapping(value = { "/", "/login" })
	public String showMain(Model model) {
		model.addAttribute("loginForm", new Login());
		return "main";
	}

	/**
	 * Xử lý đăng nhập và trả về kết quả dưới dạng chuỗi.
	 * 
	 * @param login Đối tượng Login chứa thông tin đăng nhập bao gồm tên người dùng và mật khẩu.
	 * @return Chuỗi kết quả phản hồi:
	 *         - "true" nếu đăng nhập thành công.
	 *         - "accountIsLock" nếu tài khoản bị khóa do đăng nhập thất bại nhiều lần.
	 *         - "userNameTrue" nếu tên người dùng đúng nhưng mật khẩu sai.
	 *         - "false" nếu tài khoản không tồn tại hoặc thông tin đăng nhập không chính xác.
	 */
	@PostMapping("/login")
	@ResponseBody
	public String doLogin(@ModelAttribute("loginForm") Login login) {
		String userName = login.getUserName();
		String passWord = login.getPassWord();

		Login user = loginService.findById(userName);

		if (user != null) {
			if (user.getAccoutLocked() == 1) {
				// Lấy thời điểm hiện tại
				LocalDateTime now = LocalDateTime.now();

				// Lấy thời điểm tài khoản bị khóa
				LocalDateTime lockoutTimestamp = user.getNgayKhoa();

				// Tính thời gian chênh lệch
				Duration duration = Duration.between(lockoutTimestamp, now);
				long minutesBetween = duration.toMinutes();
				System.out.println("so phutttt" + minutesBetween);
				if (minutesBetween >= 5) {
					user.setAccoutLocked(0);
					user.setNgayKhoa(null);
					user.setSoLanDangNhap(3);
					loginService.saveOrUpdate(user);
				} else {
					loginService.saveOrUpdate(user);
					return "accountIsLock";
				}
			} else {
				if (loginService.doLogin(userName, passWord) != null) {
					user.setSoLanDangNhap(0);
					loginService.saveOrUpdate(user);
					return "true";
				} else {
					if (user.getSoLanDangNhap() <= 5) {
						user.setSoLanDangNhap(user.getSoLanDangNhap() + 1);
					}
					if (user.getSoLanDangNhap() >= 5) {
						user.setAccoutLocked(1);
						user.setNgayKhoa(LocalDateTime.now());
						loginService.saveOrUpdate(user);
						return "accountIsLock";
					} else {
						loginService.saveOrUpdate(user);
						return "userNameTrue";
					}

				}
			}
		}
		return "false";
	}

}
