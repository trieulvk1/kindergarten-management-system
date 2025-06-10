package trieu.training.dto;

import java.util.List;

import trieu.training.model.SuatAn;

public class SuatAnRequest {
    private SuatAn suatAn;
    private List<ThucPhamRequest> thucPhamList;
    
    
	/**
	 * 
	 */
	public SuatAnRequest() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param suatAn
	 * @param thucPhamList
	 */
	public SuatAnRequest(SuatAn suatAn, List<ThucPhamRequest> thucPhamList) {
		super();
		this.suatAn = suatAn;
		this.thucPhamList = thucPhamList;
	}


	public SuatAn getSuatAn() {
		return suatAn;
	}


	public void setSuatAn(SuatAn suatAn) {
		this.suatAn = suatAn;
	}


	public List<ThucPhamRequest> getThucPhamList() {
		return thucPhamList;
	}


	public void setThucPhamList(List<ThucPhamRequest> thucPhamList) {
		this.thucPhamList = thucPhamList;
	}

    
}
