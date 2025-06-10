package trieu.training.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Nationalized;

@Entity
public class BuaAn {

	@Id
	private String maBuaAn;
	
	 @Nationalized
	private String tenBuaAn;
	
	@OneToMany(mappedBy = "buaAn", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ThucDon> thucDon = new ArrayList<>();
	
	/**
	 * 
	 */
	public BuaAn() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param maBuaAn
	 * @param tenBuaAn
	 * @param thucDon
	 * @param thucDonMau
	 */
	public BuaAn(String maBuaAn, String tenBuaAn, List<ThucDon> thucDon) {
		super();
		this.maBuaAn = maBuaAn;
		this.tenBuaAn = tenBuaAn;
		this.thucDon = thucDon;
	}

	public String getMaBuaAn() {
		return maBuaAn;
	}

	public void setMaBuaAn(String maBuaAn) {
		this.maBuaAn = maBuaAn;
	}

	public String getTenBuaAn() {
		return tenBuaAn;
	}

	public void setTenBuaAn(String tenBuaAn) {
		this.tenBuaAn = tenBuaAn;
	}

	public List<ThucDon> getThucDon() {
		return thucDon;
	}

	public void setThucDon(List<ThucDon> thucDon) {
		this.thucDon = thucDon;
	}

	
}
