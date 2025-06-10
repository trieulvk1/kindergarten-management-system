package trieu.training.dto;

public class ThucPhamDto {
    private String idThucPham;
    private String tenThucPham;
    private String maNhom;
    private String tenNhom;

    private Double damDV;

    private Double damTV;

    private Double beoDV;

    private Double beoTV;
    
    public ThucPhamDto(String idThucPham, String tenThucPham, String maNhom, String tenNhom, 
                      Double damDV, Double damTV, 
                       Double beoDV, Double beoTV) {
        this.idThucPham = idThucPham;
        this.tenThucPham = tenThucPham;
        this.maNhom = maNhom;
        this.tenNhom = tenNhom;
        this.damDV = damDV;
        this.damTV = damTV;
        this.beoDV = beoDV;
        this.beoTV = beoTV;
    }

	public String getIdThucPham() {
		return idThucPham;
	}

	public void setIdThucPham(String idThucPham) {
		this.idThucPham = idThucPham;
	}

	public String getTenThucPham() {
		return tenThucPham;
	}

	public void setTenThucPham(String tenThucPham) {
		this.tenThucPham = tenThucPham;
	}

	public String getMaNhom() {
		return maNhom;
	}

	public void setMaNhom(String maNhom) {
		this.maNhom = maNhom;
	}

	public String getTenNhom() {
		return tenNhom;
	}

	public void setTenNhom(String tenNhom) {
		this.tenNhom = tenNhom;
	
	}

	public Double getDamDV() {
		return damDV;
	}

	public void setDamDV(Double damDV) {
		this.damDV = damDV;
	}

	public Double getDamTV() {
		return damTV;
	}

	public void setDamTV(Double damTV) {
		this.damTV = damTV;
	}

	public Double getBeoDV() {
		return beoDV;
	}

	public void setBeoDV(Double beoDV) {
		this.beoDV = beoDV;
	}

	public Double getBeoTV() {
		return beoTV;
	}

	public void setBeoTV(Double beoTV) {
		this.beoTV = beoTV;
	}


	   
	
    
}
