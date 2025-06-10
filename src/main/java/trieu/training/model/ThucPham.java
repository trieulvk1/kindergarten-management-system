package trieu.training.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Nationalized;

@Entity
public class ThucPham {

	@Id
	private String idThucPham;

	@Nationalized
	private String tenThucPham;
	@Nationalized
	private String donViTinh;

	private Integer soLuong;

	private Integer donGia;

	private Double thanhTien;
	
	private Double nangLuong;
	
	private Double chatDam;
	private Double chatBeo;
	private Double botDuong;

	@Nationalized
	private String noiLay;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maNhom")
	private NhomThucPham nhomThucPham;

	@OneToMany(mappedBy = "thucPham", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ChiTietSuatAn> chiTietSuatAn = new ArrayList<>();

	@OneToOne(mappedBy = "idThucPham", cascade = CascadeType.ALL, orphanRemoval = true)
	private ChiTietChat chiTietChat;

	/**
	 * 
	 */
	public ThucPham() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param idThucPham
	 * @param tenThucPham
	 * @param donViTinh
	 * @param soLuong
	 * @param donGia
	 * @param thanhTien
	 * @param nangLuong
	 * @param chatDam
	 * @param chatBeo
	 * @param botDuong
	 * @param noiLay
	 * @param nhomThucPham
	 * @param suatAn
	 * @param suatAnMau
	 * @param chiTietChat
	 */
	public ThucPham(String idThucPham, String tenThucPham, String donViTinh, Integer soLuong, Integer donGia,
			Double thanhTien, Double nangLuong, Double chatDam, Double chatBeo, Double botDuong, String noiLay,
			NhomThucPham nhomThucPham, ChiTietChat chiTietChat) {
		super();
		this.idThucPham = idThucPham;
		this.tenThucPham = tenThucPham;
		this.donViTinh = donViTinh;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.thanhTien = thanhTien;
		this.nangLuong = nangLuong;
		this.chatDam = chatDam;
		this.chatBeo = chatBeo;
		this.botDuong = botDuong;
		this.noiLay = noiLay;
		this.nhomThucPham = nhomThucPham;
		this.chiTietChat = chiTietChat;
	}

	public String getIdThucPham() {
		return idThucPham;
	}

	public void setIdThucPham(String idThucPham) {
		this.idThucPham = idThucPham;
	}

	public String getTenThucPham() {
		return tenThucPham;
	}

	public void setTenThucPham(String tenThucPham) {
		this.tenThucPham = tenThucPham;
	}

	public String getDonViTinh() {
		return donViTinh;
	}

	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}

	public Integer getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(Integer soLuong) {
		this.soLuong = soLuong;
	}

	public Integer getDonGia() {
		return donGia;
	}

	public void setDonGia(Integer donGia) {
		this.donGia = donGia;
	}

	public Double getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(Double thanhTien) {
		this.thanhTien = thanhTien;
	}

	public Double getNangLuong() {
		return nangLuong;
	}

	public void setNangLuong(Double nangLuong) {
		this.nangLuong = nangLuong;
	}

	public Double getChatDam() {
		return chatDam;
	}

	public void setChatDam(Double chatDam) {
		this.chatDam = chatDam;
	}

	public Double getChatBeo() {
		return chatBeo;
	}

	public void setChatBeo(Double chatBeo) {
		this.chatBeo = chatBeo;
	}

	public Double getBotDuong() {
		return botDuong;
	}

	public void setBotDuong(Double botDuong) {
		this.botDuong = botDuong;
	}

	public String getNoiLay() {
		return noiLay;
	}

	public void setNoiLay(String noiLay) {
		this.noiLay = noiLay;
	}

	public NhomThucPham getNhomThucPham() {
		return nhomThucPham;
	}

	public void setNhomThucPham(NhomThucPham nhomThucPham) {
		this.nhomThucPham = nhomThucPham;
	}

	public ChiTietChat getChiTietChat() {
		return chiTietChat;
	}

	public void setChiTietChat(ChiTietChat chiTietChat) {
		this.chiTietChat = chiTietChat;
	}

	public List<ChiTietSuatAn> getChiTietSuatAn() {
		return chiTietSuatAn;
	}

	public void setChiTietSuatAn(List<ChiTietSuatAn> chiTietSuatAn) {
		this.chiTietSuatAn = chiTietSuatAn;
	}

}
