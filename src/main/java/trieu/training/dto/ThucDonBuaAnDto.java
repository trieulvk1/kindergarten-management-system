package trieu.training.dto;

public class ThucDonBuaAnDto {
    private String maBuaAn;
    private Long maSuatAn;
    private String tenThucDonMau;
    private boolean isThucDonMau;
	/**
	 * 
	 */
	public ThucDonBuaAnDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param maBuaAn
	 * @param maSuatAn
	 * @param tenThucDonMau
	 * @param isThucDonMau
	 */
	public ThucDonBuaAnDto(String maBuaAn, Long maSuatAn, String tenThucDonMau, boolean isThucDonMau) {
		super();
		this.maBuaAn = maBuaAn;
		this.maSuatAn = maSuatAn;
		this.tenThucDonMau = tenThucDonMau;
		this.isThucDonMau = isThucDonMau;
	}
	public String getMaBuaAn() {
		return maBuaAn;
	}
	public void setMaBuaAn(String maBuaAn) {
		this.maBuaAn = maBuaAn;
	}
	public Long getMaSuatAn() {
		return maSuatAn;
	}
	public void setMaSuatAn(Long maSuatAn) {
		this.maSuatAn = maSuatAn;
	}
	public String getTenThucDonMau() {
		return tenThucDonMau;
	}
	public void setTenThucDonMau(String tenThucDonMau) {
		this.tenThucDonMau = tenThucDonMau;
	}
	public boolean isThucDonMau() {
		return isThucDonMau;
	}
	public void setThucDonMau(boolean isThucDonMau) {
		this.isThucDonMau = isThucDonMau;
	}

    
}