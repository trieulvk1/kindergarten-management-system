package trieu.training.dto;

public class ThucPhamRequest {
	private String idThucPham;

	/**
	 * @param idThucPham
	 */
	public ThucPhamRequest(String idThucPham) {
		super();
		this.idThucPham = idThucPham;
	}

	public String getIdThucPham() {
		return idThucPham;
	}

	public void setIdThucPham(String idThucPham) {
		this.idThucPham = idThucPham;
	}
}
