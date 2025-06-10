package trieu.training.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Nationalized;

@Entity
public class SuatAn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maSuatAn;
    
    @Nationalized
    @NotNull
    private String tenSuatAn;
    
    @NotNull
    @Nationalized
    private String noiDung;
    
    @Nationalized
    @NotNull
    private String luaTuoi;
    
    @NotNull
    private Integer saSoLuong;
    
    @Nationalized
    @NotNull
    private String loaiSuatAn;

    private Double tongTien;
    
    @OneToMany(mappedBy = "suatAn", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ChiTietSuatAn> chiTietSuatAn  = new ArrayList<>();
    
    @OneToMany(mappedBy = "suatAn", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ThucDon> thucDon = new ArrayList<>();



    /**
	 * 
	 */
	public SuatAn() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SuatAn(Long maSuatAn, String tenSuatAn, String noiDung, String luaTuoi, Integer saSoLuong, String loaiSuatAn, Double tongTien) {
        this.maSuatAn = maSuatAn;
        this.tenSuatAn = tenSuatAn;
        this.noiDung = noiDung;
        this.luaTuoi = luaTuoi;
        this.saSoLuong = saSoLuong;
        this.loaiSuatAn = loaiSuatAn;
        this.tongTien = tongTien;
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

	public List<ChiTietSuatAn> getChiTietSuatAn() {
		return chiTietSuatAn;
	}

	public void setChiTietSuatAn(List<ChiTietSuatAn> chiTietSuatAn) {
		this.chiTietSuatAn = chiTietSuatAn;
	}

	public String getLoaiSuatAn() {
        return loaiSuatAn;
    }

    public void setLoaiSuatAn(String loaiSuatAn) {
        this.loaiSuatAn = loaiSuatAn;
    }

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }

    public List<ThucDon> getThucDon() {
        return thucDon;
    }

    public void setThucDon(List<ThucDon> thucDon) {
        this.thucDon = thucDon;
    }
}
