package trieu.training.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Nationalized;

@Entity
public class NhomTuoi {
	@Id
	private String maNhomTuoi;

	@Nationalized
	private String tenNhomTuoi;

	@OneToMany(mappedBy = "nhomTuoi", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ThucDon> thucDon = new ArrayList<>();

	/**
	 * 
	 */
	public NhomTuoi() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param maNhomTuoi
	 * @param tenNhomTuoi
	 * @param thucDon
	 * @param thucDonMau
	 */
	public NhomTuoi(String maNhomTuoi, String tenNhomTuoi, List<ThucDon> thucDon) {
		super();
		this.maNhomTuoi = maNhomTuoi;
		this.tenNhomTuoi = tenNhomTuoi;
		this.thucDon = thucDon;
	}

	public String getMaNhomTuoi() {
		return maNhomTuoi;
	}

	public void setMaNhomTuoi(String maNhomTuoi) {
		this.maNhomTuoi = maNhomTuoi;
	}

	public String getTenNhomTuoi() {
		return tenNhomTuoi;
	}

	public void setTenNhomTuoi(String tenNhomTuoi) {
		this.tenNhomTuoi = tenNhomTuoi;
	}

	public List<ThucDon> getThucDon() {
		return thucDon;
	}

	public void setThucDon(List<ThucDon> thucDon) {
		this.thucDon = thucDon;
	}

}
