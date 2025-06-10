package trieu.training.dto;

import java.time.LocalDate;
import java.util.List;

public class ThucDonRequest {
	private LocalDate selectedDate;
	private List<ThucDonDto> thucDonDtos;

	/**
	 * 
	 */
	public ThucDonRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param selectedDate
	 * @param thucDonDtos
	 */
	public ThucDonRequest(LocalDate selectedDate, List<ThucDonDto> thucDonDtos) {
		super();
		this.selectedDate = selectedDate;
		this.thucDonDtos = thucDonDtos;
	}

	public LocalDate getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(LocalDate selectedDate) {
		this.selectedDate = selectedDate;
	}

	public List<ThucDonDto> getThucDonDtos() {
		return thucDonDtos;
	}

	public void setThucDonDtos(List<ThucDonDto> thucDonDtos) {
		this.thucDonDtos = thucDonDtos;
	}

}
