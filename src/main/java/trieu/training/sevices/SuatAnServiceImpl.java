package trieu.training.sevices;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import trieu.training.dto.SuatAnDto;
import trieu.training.dto.SuatAnRequest;
import trieu.training.dto.ThucPhamRequest;
import trieu.training.model.ChiTietSuatAn;
import trieu.training.model.SuatAn;
import trieu.training.model.ThucDon;
import trieu.training.model.ThucPham;
import trieu.training.repository.ChiTietSuatAnRepository;
import trieu.training.repository.SuatAnRepository;
import trieu.training.repository.ThucDonRepository;
import trieu.training.repository.ThucPhamRepository;

/**
 * Class service để thực hiện các hoạt động liên quan đến SuatAn và
 * ChiTietSuatAn. Sử dụng để thêm suất ăn, thêm thực phẩm vào suất ăn, xóa suất
 * ăn và thực phẩm, hiển thị danh sách suất ăn mẫu và thông tin suất ăn mẫu,
 * hiển thị tất cả suất ăn dùng cho thực đơn.
 * 
 * @author LuongVanTrieu 20/06/2001
 */
@Service
@Transactional
public class SuatAnServiceImpl implements SuatAnServiceInterface {

	@Autowired
	private SuatAnRepository suatAnRepository;

	@Autowired
	private ThucPhamRepository thucPhamRepository;

	@Autowired
	private ChiTietSuatAnRepository chiTietSuatAnRepository;
	
	@Autowired
	private ThucDonRepository thucDonRepository;

	/**
	 * Thêm mới suất ăn cùng với các thực phẩm của suất ăn.
	 * 
	 * @param suatAnRequest Đối tượng SuatAnRequest chứa thông tin suất ăn và danh
	 *                      sách thực phẩm.
	 * @return Đối tượng SuatAn đã được thêm mới.
	 * @throws RuntimeException Ném ra khi không tìm thấy ThucPham tương ứng với các
	 *                          id thực phẩm trong danh sách.
	 */
	public SuatAn saveSuatAnChiTiet(SuatAnRequest suatAnRequest) {
	    SuatAn suatAn = suatAnRequest.getSuatAn();

	    // Kiểm tra trường hợp thêm mới (maSuatAn == null)
	    if (suatAn.getMaSuatAn() == null) {
	        // Kiểm tra nếu tên suất ăn đã tồn tại
	        SuatAn existingSuatAn = suatAnRepository.findSuatAnByTenSuatAn(suatAn.getTenSuatAn());
	        if (existingSuatAn != null) {
	            throw new RuntimeException("isExist");
	        }
	    }

	    // Thiết lập thông tin suất ăn
	    suatAn.setTenSuatAn(suatAnRequest.getSuatAn().getTenSuatAn());
	    suatAn.setNoiDung(suatAnRequest.getSuatAn().getNoiDung());
	    suatAn.setLuaTuoi(suatAnRequest.getSuatAn().getLuaTuoi());
	    suatAn.setSaSoLuong(suatAnRequest.getSuatAn().getSaSoLuong());
	    suatAn.setLoaiSuatAn(suatAnRequest.getSuatAn().getLoaiSuatAn());
	    suatAn.setTongTien(suatAnRequest.getSuatAn().getTongTien());

	    // Lưu suất ăn
	    SuatAn savedSuatAn = suatAnRepository.save(suatAn);

	    // Xóa chi tiết suất ăn cũ nếu cập nhật
	    if (suatAn.getMaSuatAn() != null) {
	        chiTietSuatAnRepository.deleteBySuatAn(savedSuatAn);
	    }

	    // Lưu chi tiết suất ăn mới
	    for (ThucPhamRequest thucPhamRequest : suatAnRequest.getThucPhamList()) {
	        ThucPham thucPham = thucPhamRepository.findById(thucPhamRequest.getIdThucPham()).orElse(null);
	        if (thucPham != null) {
	            ChiTietSuatAn chiTietSuatAn = new ChiTietSuatAn();
	            chiTietSuatAn.setSuatAn(savedSuatAn);
	            chiTietSuatAn.setThucPham(thucPham);
	            chiTietSuatAnRepository.save(chiTietSuatAn);
	        } else {
	            throw new RuntimeException("ThucPham with ID " + thucPhamRequest.getIdThucPham() + " not found!");
	        }
	    }

	    // Cập nhật lại các bản ghi ThucDon liên quan
	    List<ThucDon> thucDonList = thucDonRepository.findBySuatAn_MaSuatAn(savedSuatAn.getMaSuatAn());
	    for (ThucDon thucDon : thucDonList) {
	        thucDon.setSuatAn(savedSuatAn);
	    }
	    thucDonRepository.saveAll(thucDonList);

	    return savedSuatAn;
	}


	/**
	 * Kiểm tra sự tồn tại của một bản ghi SuatAn và các bản ghi ChiTietSuatAn tương
	 * ứng trong cơ sở dữ liệu.
	 * 
	 * @param suatAnRequest Đối tượng SuatAnRequest chứa thông tin về SuatAn và danh
	 *                      sách ThucPham.
	 * @return true nếu tất cả các bản ghi tồn tại và false nếu có bất kỳ bản ghi
	 *         nào không tồn tại.
	 */
	public boolean kiemTraSuatAnTonTai(SuatAnRequest suatAnRequest) {
		boolean validFlag = true;
		long countThucPham = 0;
		Long maSuatAn = null;
		// Kiểm tra sự tồn tại của bản ghi SuatAn
		if (!suatAnRepository.existsByTenSuatAnAndLuaTuoiAndSaSoLuongAndNoiDungAndLoaiSuatAn(
				suatAnRequest.getSuatAn().getTenSuatAn(), suatAnRequest.getSuatAn().getLuaTuoi(),
				suatAnRequest.getSuatAn().getSaSoLuong(), suatAnRequest.getSuatAn().getNoiDung(),
				suatAnRequest.getSuatAn().getLoaiSuatAn())) {
			validFlag = false;
		} else {
			// Nếu bản ghi SuatAn tồn tại, lấy maSuatAn
			maSuatAn = suatAnRepository.findByTenSuatAnAndLuaTuoiAndSaSoLuongAndNoiDungAndLoaiSuatAn(
					suatAnRequest.getSuatAn().getTenSuatAn(), suatAnRequest.getSuatAn().getLuaTuoi(),
					suatAnRequest.getSuatAn().getSaSoLuong(), suatAnRequest.getSuatAn().getNoiDung(),
					suatAnRequest.getSuatAn().getLoaiSuatAn()).getMaSuatAn();

			// Kiểm tra sự tồn tại của các bản ghi ChiTietSuatAn
			for (ThucPhamRequest thucPhamRequest : suatAnRequest.getThucPhamList()) {
				countThucPham += 1;
				if (!chiTietSuatAnRepository.existsBySuatAn_MaSuatAnAndThucPham_IdThucPham(maSuatAn,
						thucPhamRequest.getIdThucPham())) {
					validFlag = false;
				}
			}
		}
		if (countThucPham != chiTietSuatAnRepository.countThucPhamByIdSuatAn(maSuatAn)) {
			System.out.println("count for" + countThucPham);
			System.out.println("count count ham tinh" + chiTietSuatAnRepository.countThucPhamByIdSuatAn(maSuatAn));
			validFlag = false;
		}

		return validFlag;
	}

	/**
	 * Xóa suất ăn.
	 * 
	 * @param maSuatAn Mã suất ăn cần xóa.
	 */
	public void deleteSuatAnById(Long maSuatAn) {
		suatAnRepository.deleteById(maSuatAn);
	}

	/**
	 * Hiển thị list suất ăn mẫu.
	 * 
	 * @return Danh sách suất ăn mẫu.
	 */
	public List<SuatAnDto> findAllSuatAnMau() {
		return suatAnRepository.findAllSuatAnMau();
	}

	/**
	 * Xóa thực phẩm trong bảng chi tiết suất ăn.
	 * 
	 * @param idSuatAn Mã suất ăn.
	 * @param idMonAn  Mã thực phẩm.
	 */
	public void deleteById(Long idSuatAn, String idMonAn) {
		chiTietSuatAnRepository.deleteById(idSuatAn, idMonAn);
	}

	/**
	 * Hiển thị chi tiết của một thực phẩm.
	 * 
	 * @param idThucPham Mã thực phẩm.
	 * @return Đối tượng SuatAnDto chứa thông tin thực phẩm.
	 */
	public SuatAnDto addThucPhamById(String idThucPham) {
		SuatAnDto suatAnDto = thucPhamRepository.findByThucPhamById(idThucPham);
		if(suatAnDto.getChatBeo()==null) {
			suatAnDto.setChatBeo(suatAnDto.getBeoDongVat() + suatAnDto.getBeoThucVat());
		}
		if(suatAnDto.getChatDam()==null) {
			suatAnDto.setChatDam(suatAnDto.getDamDongVat() + suatAnDto.getDamThucVat());
		}
		
		suatAnDto.setThanhTien((double) (suatAnDto.getSoLuong() * suatAnDto.getDonGia()));
		return suatAnDto;
	}

	/**
	 * Hiển thị tất cả thông tin suất ăn mẫu.
	 * 
	 * @param maSuatAn Mã suất ăn mẫu.
	 * @return Danh sách các đối tượng SuatAnDto chứa thông tin suất ăn mẫu.
	 */
	public List<SuatAnDto> findSuatAnMauById(Long maSuatAn) {
		List<SuatAnDto> listAnDtos = suatAnRepository.findSuatAnMauById(maSuatAn);
		List<SuatAnDto> listSuatAn = suatAnRepository.findByMaSuatAnList(maSuatAn);
		SuatAn suatAn = suatAnRepository.findByMaSuatAn(maSuatAn);
		if (listAnDtos.size() == 0) {
			return listSuatAn;

		}
		for (SuatAnDto suatAnDto : listAnDtos) {
			suatAnDto.setChatBeo(suatAnDto.getBeoDongVat() + suatAnDto.getBeoThucVat());
			suatAnDto.setChatDam(suatAnDto.getDamDongVat() + suatAnDto.getDamThucVat());
			Double tongTien = (double) (suatAnDto.getSoLuong() * suatAnDto.getDonGia());
			suatAnDto.setThanhTien(tongTien);
		}
		System.out.println(suatAn.getMaSuatAn());
		return listAnDtos;
	}

	/**
	 * Hiển thị tất cả suất ăn dùng cho thực đơn.
	 * 
	 * @return Danh sách tất cả suất ăn dùng cho thực đơn.
	 */
	public List<SuatAn> getAllSuatAn() {
		return suatAnRepository.findAll();
	}

	/**
	 * Hiển thị tất cả thông tin suất ăn mẫu.
	 * 
	 * @param maSuatAn Mã suất ăn mẫu.
	 * @return Lưu đối tượng SuatAnDto chứa thông tin suất ăn mẫu.
	 */
	public boolean findSuatAnMauByIdToCopy(Long maSuatAn) {
		List<SuatAnDto> listSuatAnDto = suatAnRepository.findSuatAnMauById(maSuatAn);
		List<SuatAnDto> listAnDtos;

		if (listSuatAnDto.isEmpty()) {
			listAnDtos = suatAnRepository.findByMaSuatAnList(maSuatAn);
			if (listAnDtos.isEmpty()) {
				return false;
			}
			createSuatAnWithoutDetails(listAnDtos.get(0));
			return false;
		} else {
			listAnDtos = listSuatAnDto;
		}

		SuatAnDto firstDto = listAnDtos.get(0);
		SuatAn newSuatAn = createSuatAnWithoutDetails(firstDto);

		for (SuatAnDto suatAnDto : listAnDtos) {
			ChiTietSuatAn chiTietSuatAn = new ChiTietSuatAn();
			chiTietSuatAn.setSuatAn(newSuatAn);
			chiTietSuatAn.setThucPham(thucPhamRepository.findByIdThucPham(suatAnDto.getIdThucPham()));
			chiTietSuatAnRepository.save(chiTietSuatAn);
		}

		return true;
	}

	private SuatAn createSuatAnWithoutDetails(SuatAnDto firstDto) {
		String tenSuatAn = firstDto.getTenSuatAn();
		String baseName = tenSuatAn.split(" \\(Copy")[0].trim();
		String copyPrefix = " (Copy ";
		String copySuffix = ")";

		List<SuatAn> existingSuatAns = suatAnRepository.findByTenSuatAnStartingWith(baseName);
		int maxCopyIndex = 0;
		for (SuatAn existingSuatAn : existingSuatAns) {
			String existingTenSuatAn = existingSuatAn.getTenSuatAn();
			int copyIndex = getCopyIndexFromName(existingTenSuatAn, baseName, copyPrefix, copySuffix);
			if (copyIndex > maxCopyIndex) {
				maxCopyIndex = copyIndex;
			}
		}

		int newCopyIndex = maxCopyIndex + 1;
		String newTenSuatAn = baseName + copyPrefix + newCopyIndex + copySuffix;

		SuatAn suatAn = new SuatAn();
		suatAn.setTenSuatAn(newTenSuatAn);
		suatAn.setNoiDung(firstDto.getNoiDung());
		suatAn.setLuaTuoi(firstDto.getLuaTuoi());
		suatAn.setSaSoLuong(firstDto.getSaSoLuong());
		suatAn.setLoaiSuatAn(firstDto.getLoaiSuatAn());
		suatAn.setTongTien(firstDto.getTongTien());
		suatAnRepository.save(suatAn);

		return suatAn;
	}

	/**
	 * Trích xuất chỉ số bản sao từ tên suất ăn dựa trên các tiền tố và hậu tố của
	 * bản sao.
	 * 
	 * @param tenSuatAn  Tên của suất ăn cần trích xuất chỉ số bản sao.
	 * @param baseName   Phần cơ bản của tên suất ăn.
	 * @param copyPrefix Tiền tố của bản sao trong tên suất ăn.
	 * @param copySuffix Hậu tố của bản sao trong tên suất ăn.
	 * @return Chỉ số của bản sao, nếu không tìm thấy trả về 0.
	 */
	public int getCopyIndexFromName(String tenSuatAn, String baseName, String copyPrefix, String copySuffix) {
		int copyIndex = 0;
		Pattern pattern = Pattern.compile(Pattern.quote(copyPrefix) + "(\\d+)" + Pattern.quote(copySuffix));
		Matcher matcher = pattern.matcher(tenSuatAn);
		if (matcher.find() && tenSuatAn.startsWith(baseName)) {
			copyIndex = Integer.parseInt(matcher.group(1));
		}
		return copyIndex;
	}

}
