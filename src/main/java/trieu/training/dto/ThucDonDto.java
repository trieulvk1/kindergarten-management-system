package trieu.training.dto;

import java.time.LocalDate;
import java.util.List;

public class ThucDonDto {
    private String maNgay;
    private LocalDate ngayThucDon;
    private String maBuaAn;
    private String maNhomTuoi;
    private Long maSuatAn;
    private Long maThucDonMau;
    private String tenThucDonMau;
    private boolean isThucDonMau;
    private List<ThucDonBuaAnDto> maBuaAns;

    // Default constructor
    public ThucDonDto() {
    }

    // All arguments constructor
    public ThucDonDto(String maNgay, String maBuaAn, String maNhomTuoi, Long maSuatAn, Long maThucDonMau,
                      String tenThucDonMau, boolean isThucDonMau) {
        this.maNgay = maNgay;
        this.maBuaAn = maBuaAn;
        this.maNhomTuoi = maNhomTuoi;
        this.maSuatAn = maSuatAn;
        this.maThucDonMau = maThucDonMau;
        this.tenThucDonMau = tenThucDonMau;
        this.isThucDonMau = isThucDonMau;
    }
    
    

    /**
	 * @param maNgay
	 * @param maBuaAn
	 * @param maNhomTuoi
	 * @param maSuatAn
	 * @param isThucDonMau
	 */
	public ThucDonDto(String maNgay, String maBuaAn, String maNhomTuoi, Long maSuatAn, boolean isThucDonMau) {
		super();
		this.maNgay = maNgay;
		this.maBuaAn = maBuaAn;
		this.maNhomTuoi = maNhomTuoi;
		this.maSuatAn = maSuatAn;
		this.isThucDonMau = isThucDonMau;
	}

	// Minimal arguments constructor
    public ThucDonDto(Long maThucDonMau, String tenThucDonMau, boolean isThucDonMau) {
        this.maThucDonMau = maThucDonMau;
        this.tenThucDonMau = tenThucDonMau;
        this.isThucDonMau = isThucDonMau;
    }

    public LocalDate getNgayThucDon() {
		return ngayThucDon;
	}

	public void setNgayThucDon(LocalDate ngayThucDon) {
		this.ngayThucDon = ngayThucDon;
	}

	public List<ThucDonBuaAnDto> getMaBuaAns() {
		return maBuaAns;
	}

	public void setMaBuaAns(List<ThucDonBuaAnDto> maBuaAns) {
		this.maBuaAns = maBuaAns;
	}

	// Getters and setters
    public String getMaNgay() {
        return maNgay;
    }

    public void setMaNgay(String maNgay) {
        this.maNgay = maNgay;
    }

    public String getMaBuaAn() {
        return maBuaAn;
    }

    public void setMaBuaAn(String maBuaAn) {
        this.maBuaAn = maBuaAn;
    }

    public String getMaNhomTuoi() {
        return maNhomTuoi;
    }

    public void setMaNhomTuoi(String maNhomTuoi) {
        this.maNhomTuoi = maNhomTuoi;
    }

    public Long getMaSuatAn() {
        return maSuatAn;
    }

    public void setMaSuatAn(Long maSuatAn) {
        this.maSuatAn = maSuatAn;
    }

    public Long getMaThucDonMau() {
        return maThucDonMau;
    }

    public void setMaThucDonMau(Long maThucDonMau) {
        this.maThucDonMau = maThucDonMau;
    }

    public String getTenThucDonMau() {
        return tenThucDonMau;
    }

    public void setTenThucDonMau(String tenThucDonMau) {
        this.tenThucDonMau = tenThucDonMau;
    }

    public boolean getIsThucDonMau() {
        return isThucDonMau;
    }

    public void setThucDonMau(boolean isThucDonMau) {
        this.isThucDonMau = isThucDonMau;
    }
}
