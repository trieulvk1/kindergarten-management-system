package trieu.training.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ChiTietSuatAn {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idChiTiet;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maSuatAn")
	private  SuatAn suatAn;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maThucPham")
	private  ThucPham thucPham;

	/**
	 * 
	 */
	public ChiTietSuatAn() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param idChiTiet
	 * @param suatAn
	 * @param suatAnMau
	 * @param thucPham
	 */
	public ChiTietSuatAn(Long idChiTiet, SuatAn suatAn, ThucPham thucPham) {
		super();
		this.idChiTiet = idChiTiet;
		this.suatAn = suatAn;
		this.thucPham = thucPham;
	}

	public Long getIdChiTiet() {
		return idChiTiet;
	}

	public void setIdChiTiet(Long idChiTiet) {
		this.idChiTiet = idChiTiet;
	}

	public SuatAn getSuatAn() {
		return suatAn;
	}

	public void setSuatAn(SuatAn suatAn) {
		this.suatAn = suatAn;
	}


	public ThucPham getThucPham() {
		return thucPham;
	}

	public void setThucPham(ThucPham thucPham) {
		this.thucPham = thucPham;
	}
	
	
}
