package trieu.training.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class ChiTietChat implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idThucPham", referencedColumnName = "idThucPham")
	private ThucPham idThucPham;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.##")
	private Double damDongVat;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.##")
	private Double damThucVat;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.##")
	private Double beoDongVat;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.##")
	private Double beoThucVat;
	/**
	 * 
	 */
	public ChiTietChat() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param damDongVat
	 * @param damThucVat
	 */
	/**
	 * @param id
	 * @param damDongVat
	 * @param damThucVat
	 * @param beoDongVat
	 * @param beoThucVat
	 */
	public ChiTietChat(ThucPham idThucPham, Double damDongVat, Double damThucVat, Double beoDongVat, Double beoThucVat) {
		super();
		this.idThucPham = idThucPham;
		this.damDongVat = damDongVat;
		this.damThucVat = damThucVat;
		this.beoDongVat = beoDongVat;
		this.beoThucVat = beoThucVat;
	}
	public ThucPham getIdThucPham() {
		return idThucPham;
	}
	public void setIdThucPham(ThucPham idThucPham) {
		this.idThucPham = idThucPham;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
