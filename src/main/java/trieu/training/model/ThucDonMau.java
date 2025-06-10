package trieu.training.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Nationalized;

@Entity
public class ThucDonMau {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long maThucDonMau;
	
	 @Nationalized
	private String tenThucDonMau; 
	

	 @OneToMany(mappedBy = "thucDonMau")
	    private List<ThucDon> thucDon = new ArrayList<>();
	/**
	 * 
	 */
	public ThucDonMau() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param maThucDonMau
	 * @param tenThucDonMau
	 * @param ngay
	 * @param buaAn
	 * @param nhomTuoi
	 * @param suatAn
	 * @param thucDon
	 */
	public ThucDonMau(Long maThucDonMau, String tenThucDonMau,
			String suatAn, List<ThucDon> thucDon) {
		super();
		this.maThucDonMau = maThucDonMau;
		this.tenThucDonMau = tenThucDonMau;
		this.thucDon = thucDon;
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

	public List<ThucDon> getThucDon() {
		return thucDon;
	}
	public void setThucDon(List<ThucDon> thucDon) {
		this.thucDon = thucDon;
	}

	
}
