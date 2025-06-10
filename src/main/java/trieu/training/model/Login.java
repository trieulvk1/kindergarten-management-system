package trieu.training.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Login {
	
	@Id
	private String userName;
	private String passWord;
	private Integer soLanDangNhap;
	private Integer accoutLocked;
	private LocalDateTime ngayKhoa;

	
	/**
	 * @param userName
	 * @param passWord
	 * @param soLanDangNhap
	 * @param accoutLocked
	 * @param ngayKhoa
	 */
	public Login(String userName, String passWord, Integer soLanDangNhap, Integer accoutLocked, LocalDateTime ngayKhoa) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.soLanDangNhap = soLanDangNhap;
		this.accoutLocked = accoutLocked;
		this.ngayKhoa = ngayKhoa;
	}
	/**
	 * 
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public Integer getSoLanDangNhap() {
		return soLanDangNhap;
	}
	public void setSoLanDangNhap(Integer soLanDangNhap) {
		this.soLanDangNhap = soLanDangNhap;
	}
	public Integer getAccoutLocked() {
		return accoutLocked;
	}
	public void setAccoutLocked(Integer accoutLocked) {
		this.accoutLocked = accoutLocked;
	}
	public LocalDateTime getNgayKhoa() {
		return ngayKhoa;
	}
	public void setNgayKhoa(LocalDateTime ngayKhoa) {
		this.ngayKhoa = ngayKhoa;
	}
	
	
}
