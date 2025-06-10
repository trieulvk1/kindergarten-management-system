package trieu.training.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class ThucDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maThucDon;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ngayThucDon;
    
    private boolean isThucDonMau;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ngay")
    private Ngay ngay;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buaAn")
    private BuaAn buaAn;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nhomTuoi")
    private NhomTuoi nhomTuoi;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "suatAn")
    private SuatAn suatAn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maThucDonMau")
    private ThucDonMau thucDonMau;
    
    @OneToMany(mappedBy = "thucDonMau", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ThucDon> thucDonChiTiet = new ArrayList<>();
	/**
	 * 
	 */
	public ThucDon() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param maThucDon
	 * @param ngayThucDon
	 * @param isThucDonMau
	 * @param ngay
	 * @param buaAn
	 * @param nhomTuoi
	 * @param suatAn
	 * @param thucDonMau
	 * @param thucDonChiTiet
	 */
	public ThucDon(Long maThucDon, LocalDate ngayThucDon, boolean isThucDonMau, Ngay ngay, BuaAn buaAn,
			NhomTuoi nhomTuoi, SuatAn suatAn, ThucDonMau thucDonMau, List<ThucDon> thucDonChiTiet) {
		super();
		this.maThucDon = maThucDon;
		this.ngayThucDon = ngayThucDon;
		this.isThucDonMau = isThucDonMau;
		this.ngay = ngay;
		this.buaAn = buaAn;
		this.nhomTuoi = nhomTuoi;
		this.suatAn = suatAn;
		this.thucDonMau = thucDonMau;
		this.thucDonChiTiet = thucDonChiTiet;
	}


	public boolean isThucDonMau() {
		return isThucDonMau;
	}


	public void setIsThucDonMau(boolean isThucDonMau) {
		this.isThucDonMau = isThucDonMau;
	}

	public Long getMaThucDon() {
		return maThucDon;
	}
	public void setMaThucDon(Long maThucDon) {
		this.maThucDon = maThucDon;
	}
	public LocalDate getNgayThucDon() {
		return ngayThucDon;
	}
	public void setNgayThucDon(LocalDate ngayThucDon) {
		this.ngayThucDon = ngayThucDon;
	}
	public Ngay getNgay() {
		return ngay;
	}
	public void setNgay(Ngay ngay) {
		this.ngay = ngay;
	}
	public BuaAn getBuaAn() {
		return buaAn;
	}
	public void setBuaAn(BuaAn buaAn) {
		this.buaAn = buaAn;
	}
	public NhomTuoi getNhomTuoi() {
		return nhomTuoi;
	}
	public void setNhomTuoi(NhomTuoi nhomTuoi) {
		this.nhomTuoi = nhomTuoi;
	}
	public SuatAn getSuatAn() {
		return suatAn;
	}
	public void setSuatAn(SuatAn suatAn) {
		this.suatAn = suatAn;
	}
	public ThucDonMau getThucDonMau() {
		return thucDonMau;
	}
	public void setThucDonMau(ThucDonMau thucDonMau) {
		this.thucDonMau = thucDonMau;
	}
	public List<ThucDon> getThucDonChiTiet() {
		return thucDonChiTiet;
	}
	public void setThucDonChiTiet(List<ThucDon> thucDonChiTiet) {
		this.thucDonChiTiet = thucDonChiTiet;
	}

	
	
	
}
