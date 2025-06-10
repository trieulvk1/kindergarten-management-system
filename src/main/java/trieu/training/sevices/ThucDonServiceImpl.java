package trieu.training.sevices;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import trieu.training.dto.ThucDonBuaAnDto;
import trieu.training.dto.ThucDonDto;
import trieu.training.model.BuaAn;
import trieu.training.model.Ngay;
import trieu.training.model.NhomTuoi;
import trieu.training.model.SuatAn;
import trieu.training.model.ThucDon;
import trieu.training.model.ThucDonMau;
import trieu.training.repository.BuaAnRepository;
import trieu.training.repository.NgayRepository;
import trieu.training.repository.NhomTuoiRepository;
import trieu.training.repository.SuatAnRepository;
import trieu.training.repository.ThucDonMauRepository;
import trieu.training.repository.ThucDonRepository;

/**
 * Class service để thực hiện các hoạt động liên quan đến ThucDon.
 * Sử dụng để hiển thị danh sách thực đơn, tất cả thông tin thực đơn mẫu,
 * hiển thị thực đơn theo tuần, và lưu thông tin thực đơn.
 * @author LuongVanTrieu 20/06/2001
 */
@Service
@Transactional
public class ThucDonServiceImpl implements ThucDonServiceInterface{
	@Autowired
	private ThucDonRepository thucDonRepository;

	@Autowired
	private NgayRepository ngayRepository;

	@Autowired
	private BuaAnRepository buaAnRepository;

	@Autowired
	private NhomTuoiRepository nhomTuoiRepository;

	@Autowired
	private SuatAnRepository suatAnRepository;

	@Autowired
	private ThucDonMauRepository thucDonMauRepository;
	
	 /**
     * Hiển thị danh sách thực đơn mẫu.
     * @return Danh sách đối tượng ThucDonDto chứa thông tin thực đơn mẫu.
     */
	public List<ThucDonDto> getAllThucDon() {
		List<ThucDonDto> thucDonMau = thucDonRepository.findThucDonMau(true);
		return thucDonMau;
	}

	/**
     * Hiển thị tất cả thông tin thực đơn mẫu.
     * @param maThucDonMau Mã thực đơn mẫu cần hiển thị. 
     * Nếu maThucDonMau là null, hiển thị tất cả thông tin thực đơn mẫu.
     * @return Danh sách đối tượng ThucDonDto chứa thông tin thực đơn mẫu.
     */
	public List<ThucDonDto> getAllThucDonMauById(Long maThucDonMau) {
		List<ThucDon> thucDons;
		if (maThucDonMau != null) {
			thucDons = thucDonRepository.getAllThucDonMau(maThucDonMau);
		} else {
			thucDons = thucDonRepository.findAll();
		}

		return thucDons.stream()
				.map(thucDon -> new ThucDonDto(thucDon.getNgay().getMaNgay(), thucDon.getBuaAn().getMaBuaAn(),
						thucDon.getNhomTuoi().getMaNhomTuoi(), thucDon.getSuatAn().getMaSuatAn(),
						thucDon.getThucDonMau().getMaThucDonMau(), thucDon.getThucDonMau().getTenThucDonMau(),
						thucDon.isThucDonMau()))
				.collect(Collectors.toList());
	}

	 /**
     * Hiển thị danh sách thực đơn theo tuần của một ngày được chọn.
     * @param selectedDate Ngày được chọn.
     * @return Danh sách đối tượng ThucDonDto chứa thông tin thực đơn theo tuần.
     */
	public List<ThucDonDto> getThucDonByWeek(LocalDate selectedDate) {
		LocalDate startDate = selectedDate.minusDays(selectedDate.getDayOfWeek().getValue() - 1);
		LocalDate endDate = startDate.plusDays(6);
		List<ThucDon> thucDons = thucDonRepository.findThucDonByWeek(startDate, endDate);

		return thucDons.stream()
				.map(thucDon -> new ThucDonDto(thucDon.getNgay().getMaNgay(), thucDon.getBuaAn().getMaBuaAn(),
						thucDon.getNhomTuoi().getMaNhomTuoi(), thucDon.getSuatAn().getMaSuatAn(),
						thucDon.isThucDonMau()))
				.collect(Collectors.toList());
	}

	 /**
     * Lưu thông tin thực đơn.
     * @param selectedDate Ngày được chọn.
     * @param thucDonDtos Danh sách đối tượng ThucDonDto chứa thông tin thực đơn.
     * @param maThucDon Mã thực đơn mẫu cần lưu. 
     * Nếu maThucDon là null, tạo mới một thực đơn mẫu.
     */
	public void saveThucDon(LocalDate selectedDate, List<ThucDonDto> thucDonDtos, Long maThucDon) {
	    LocalDate startDate = selectedDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
	    LocalDate endDate = selectedDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));

	    if (maThucDon != null) {
	        ThucDonMau thucDonMauToDelete = thucDonMauRepository.findById(maThucDon)
	                .orElseThrow(() -> new EntityNotFoundException("ThucDonMau not found"));
	        // Đặt maThucDonMau của các ThucDon liên quan thành null
	        List<ThucDon> thucDonsToUpdate = thucDonRepository.findByThucDonMau(thucDonMauToDelete);
	        for (ThucDon thucDon : thucDonsToUpdate) {
	            thucDon.setThucDonMau(null);
	            thucDonRepository.save(thucDon);
	        }
	        thucDonMauRepository.delete(thucDonMauToDelete);
	    }
	    // Tìm hoặc tạo mới ThucDonMau
	    ThucDonMau existingThucDonMau = null;
	    for (ThucDonDto dto : thucDonDtos) {
	        if (dto.getIsThucDonMau()) { // Nếu đây là Thực Đơn Mẫu
	            String tenThucDonMau = "Thực Đơn Mẫu " + startDate.toString();
	            existingThucDonMau = thucDonMauRepository.findByTenThucDonMau(tenThucDonMau);
	            if (existingThucDonMau == null) {
	                existingThucDonMau = new ThucDonMau();
	                existingThucDonMau.setMaThucDonMau(dto.getMaThucDonMau());
	                existingThucDonMau.setTenThucDonMau(tenThucDonMau);
	                thucDonMauRepository.save(existingThucDonMau);
	            }
	        }

	        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
	            Ngay ngay = ngayRepository.findByMaNgay(dto.getMaNgay());

	            // Tính toán ngày thực tế dựa trên mã ngày
	            LocalDate ngayThucDon = startDate.plusDays(Integer.parseInt(dto.getMaNgay().substring(2)) - 2);

	            NhomTuoi nhomTuoi = nhomTuoiRepository.findByMaNhomTuoi(dto.getMaNhomTuoi());

	            for (ThucDonBuaAnDto buaAnDto : dto.getMaBuaAns()) {
	                BuaAn buaAn = buaAnRepository.findByMaBuaAn(buaAnDto.getMaBuaAn());
	                SuatAn suatAn = suatAnRepository.findByMaSuatAn(buaAnDto.getMaSuatAn());

	                // Tìm bản ghi đã tồn tại
	                ThucDon existingThucDon = thucDonRepository.findByNgayThucDonAndBuaAnAndNgay(ngayThucDon, buaAn, ngay);
	                if (existingThucDon != null) {
	                    // Cập nhật bản ghi
	                    existingThucDon.setSuatAn(suatAn);
	                    existingThucDon.setNhomTuoi(nhomTuoi);
	                    if (dto.getIsThucDonMau()) { // Nếu đây là Thực Đơn Mẫu
	                        existingThucDon.setThucDonMau(existingThucDonMau);
	                        existingThucDon.setIsThucDonMau(true);
	                    } else {
	                        existingThucDon.setIsThucDonMau(false);
	                    }
	                    thucDonRepository.save(existingThucDon);
	                } else {
	                    // Tạo bản ghi mới
	                    ThucDon newThucDon = new ThucDon();
	                    newThucDon.setNgayThucDon(ngayThucDon);
	                    newThucDon.setNgay(ngay);
	                    newThucDon.setBuaAn(buaAn);
	                    newThucDon.setNhomTuoi(nhomTuoi);
	                    newThucDon.setSuatAn(suatAn);
	                    if (dto.getIsThucDonMau()) { // Nếu đây là Thực Đơn Mẫu
	                        newThucDon.setThucDonMau(existingThucDonMau);
	                        newThucDon.setIsThucDonMau(true);
	                    } else {
	                        newThucDon.setIsThucDonMau(false);
	                    }
	                    thucDonRepository.saveAndFlush(newThucDon);
	                }
	            }
	        }
	    }
	}


}
