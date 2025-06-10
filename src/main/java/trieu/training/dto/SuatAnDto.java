package trieu.training.dto;

public class SuatAnDto {

	private Long maSuatAn;

	private String tenSuatAn;

	private String noiDung;

	private String luaTuoi;

	private Integer saSoLuong;

	private String loaiSuatAn;

	private Double tongTien;

	private String idThucPham;

	private String tenThucPham;

	private String donViTinh;

	private Integer soLuong;

	private Integer donGia;

	private Double thanhTien;

	private Double nangLuong;
	

	private Double chatDam;

	private Double chatBeo;

	private Double botDuong;

	private String noiLay;

	private Double damDongVat;

	private Double damThucVat;
	
	private Double beoDongVat;

	private Double beoThucVat;

	/**
	 * 
	 */
	public SuatAnDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * @param maSuatAn
	 * @param tenSuatAn
	 * @param noiDung
	 * @param luaTuoi
	 * @param saSoLuong
	 * @param loaiSuatAn
	 * @param tongTien
	 */
	public SuatAnDto(Long maSuatAn, String tenSuatAn, String noiDung, String luaTuoi, Integer saSoLuong,
			String loaiSuatAn, Double tongTien) {
		super();
		this.maSuatAn = maSuatAn;
		this.tenSuatAn = tenSuatAn;
		this.noiDung = noiDung;
		this.luaTuoi = luaTuoi;
		this.saSoLuong = saSoLuong;
		this.loaiSuatAn = loaiSuatAn;
		this.tongTien = tongTien;
	}


	/**
	 * @param maSuatAn
	 * @param tenSuatAn
	 * @param tongTien
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
	 * @param damDongVat
	 * @param damThucVat
	 * @param beoDongVat
	 * @param beoThucVat
	 */
	public SuatAnDto(Long maSuatAn, String tenSuatAn, Double tongTien, String idThucPham, String tenThucPham,
			String donViTinh, Integer soLuong, Integer donGia, Double thanhTien, Double nangLuong, Double chatDam,
			Double chatBeo, Double botDuong, String noiLay, Double damDongVat, Double damThucVat, Double beoDongVat,
			Double beoThucVat) {
		super();
		this.maSuatAn = maSuatAn;
		this.tenSuatAn = tenSuatAn;
		this.tongTien = tongTien;
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
		this.damDongVat = damDongVat;
		this.damThucVat = damThucVat;
		this.beoDongVat = beoDongVat;
		this.beoThucVat = beoThucVat;
	}

	/**
	 * @param maSuatAn
	 * @param tenSuatAn
	 * @param noiDung
	 * @param luaTuoi
	 * @param saSoLuong
	 * @param loaiSuatAn
	 * @param tongTien
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
	 * @param damDongVat
	 * @param damThucVat
	 * @param beoDongVat
	 * @param beoThucVat
	 */
	public SuatAnDto(Long maSuatAn, String tenSuatAn, String noiDung, String luaTuoi, Integer saSoLuong,
			String loaiSuatAn, Double tongTien, String idThucPham, String tenThucPham, String donViTinh,
			Integer soLuong, Integer donGia, Double thanhTien, Double nangLuong, Double chatDam, Double chatBeo,
			Double botDuong, String noiLay, Double damDongVat, Double damThucVat, Double beoDongVat,
			Double beoThucVat) {
		super();
		this.maSuatAn = maSuatAn;
		this.tenSuatAn = tenSuatAn;
		this.noiDung = noiDung;
		this.luaTuoi = luaTuoi;
		this.saSoLuong = saSoLuong;
		this.loaiSuatAn = loaiSuatAn;
		this.tongTien = tongTien;
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
		this.damDongVat = damDongVat;
		this.damThucVat = damThucVat;
		this.beoDongVat = beoDongVat;
		this.beoThucVat = beoThucVat;
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
	 * @param damDongVat
	 * @param damThucVat
	 * @param beoDongVat
	 * @param beoThucVat
	 */
	public SuatAnDto(String idThucPham, String tenThucPham, String donViTinh, Integer soLuong, Integer donGia,
			Double thanhTien, Double nangLuong, Double chatDam, Double chatBeo, Double botDuong, String noiLay,
			Double damDongVat, Double damThucVat, Double beoDongVat, Double beoThucVat) {
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
		this.damDongVat = damDongVat;
		this.damThucVat = damThucVat;
		this.beoDongVat = beoDongVat;
		this.beoThucVat = beoThucVat;
	}

	
	
	/**
	 * @param maSuatAn
	 * @param tenSuatAn
	 */
	public SuatAnDto(Long maSuatAn, String tenSuatAn) {
		super();
		this.maSuatAn = maSuatAn;
		this.tenSuatAn = tenSuatAn;
	}

	public Long getMaSuatAn() {
		return maSuatAn;
	}

	public void setMaSuatAn(Long maSuatAn) {
		this.maSuatAn = maSuatAn;
	}

	public String getTenSuatAn() {
		return tenSuatAn;
	}

	public void setTenSuatAn(String tenSuatAn) {
		this.tenSuatAn = tenSuatAn;
	}

	public Double getTongTien() {
		return tongTien;
	}

	public void setTongTien(Double tongTien) {
		this.tongTien = tongTien;
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

	public Double getDamDongVat() {
		return damDongVat;
	}

	public void setDamDongVat(Double damDongVat) {
		this.damDongVat = damDongVat;
	}

	public Double getDamThucVat() {
		return damThucVat;
	}

	public void setDamThucVat(Double damThucVat) {
		this.damThucVat = damThucVat;
	}

	public Double getBeoDongVat() {
		return beoDongVat;
	}

	public void setBeoDongVat(Double beoDongVat) {
		this.beoDongVat = beoDongVat;
	}

	public Double getBeoThucVat() {
		return beoThucVat;
	}

	public void setBeoThucVat(Double beoThucVat) {
		this.beoThucVat = beoThucVat;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public String getLuaTuoi() {
		return luaTuoi;
	}

	public void setLuaTuoi(String luaTuoi) {
		this.luaTuoi = luaTuoi;
	}

	public Integer getSaSoLuong() {
		return saSoLuong;
	}

	public void setSaSoLuong(Integer saSoLuong) {
		this.saSoLuong = saSoLuong;
	}

	public String getLoaiSuatAn() {
		return loaiSuatAn;
	}

	public void setLoaiSuatAn(String loaiSuatAn) {
		this.loaiSuatAn = loaiSuatAn;
	}

	
}
