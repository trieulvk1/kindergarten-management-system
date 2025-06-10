package trieu.training.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Nationalized;

@Entity
public class Ngay {
	@Id
	private String maNgay;

	@Nationalized
	private String tenNgay;

	@OneToMany(mappedBy = "ngay", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ThucDon> thucDon = new ArrayList<>();

	/**
	 * 
	 */
	public Ngay() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param maNgay
	 * @param tenNgay
	 * @param thucDon
	 * @param thucDonMau
	 */
	public Ngay(String maNgay, String tenNgay, List<ThucDon> thucDon) {
		super();
		this.maNgay = maNgay;
		this.tenNgay = tenNgay;
		this.thucDon = thucDon;

	}

	public String getMaNgay() {
		return maNgay;
	}

	public void setMaNgay(String maNgay) {
		this.maNgay = maNgay;
	}

	public String getTenNgay() {
		return tenNgay;
	}

	public void setTenNgay(String tenNgay) {
		this.tenNgay = tenNgay;
	}

	public List<ThucDon> getThucDon() {
		return thucDon;
	}

	public void setThucDon(List<ThucDon> thucDon) {
		this.thucDon = thucDon;
	}

}
