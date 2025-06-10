package trieu.training.sevices;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import trieu.training.dto.SuatAnDto;
import trieu.training.dto.ThucPhamDto;
import trieu.training.model.ThucPham;
import trieu.training.repository.ThucPhamRepository;

/**
 * Class service để thực hiện các hoạt động liên quan đến ThucPham. Sử dụng để
 * lưu hoặc cập nhật thực phẩm, hiển thị thực phẩm theo id, theo nhóm, chi tiết
 * theo từng thực phẩm, hiển thị tất cả các món ăn trong suất ăn, và tìm kiếm
 * thực phẩm theo tên.
 */
@Service
@Transactional
public class ThucPhamServiceImpl implements ThucPhamServiceInterface{

	@Autowired
	private ThucPhamRepository thucPhamRepository;

	/**
	 * Lưu hoặc cập nhật thông tin thực phẩm.
	 * 
	 * @param thucPham Thực phẩm cần lưu hoặc cập nhật.
	 * @return Thực phẩm đã được lưu hoặc cập nhật.
	 */
	public ThucPham saveOrUpdate(ThucPham thucPham) {
		ThucPham existingThucPham = thucPhamRepository.findById(thucPham.getIdThucPham()).orElse(null);
		System.out.println(thucPham.getNoiLay());
		if (existingThucPham != null) {
			thucPham.setNhomThucPham(existingThucPham.getNhomThucPham());
			thucPham.setChiTietChat(existingThucPham.getChiTietChat());
			thucPham.setChiTietSuatAn(existingThucPham.getChiTietSuatAn());
		}
		return thucPhamRepository.save(thucPham);
	}

	/**
	 * Hiển thị thông tin thực phẩm theo id.
	 * 
	 * @param idThucPham Id của thực phẩm cần hiển thị thông tin.
	 * @return Thực phẩm có id tương ứng hoặc null nếu không tìm thấy.
	 */
	public ThucPham findByIdThucPham(String idThucPham) {
		Optional<ThucPham> thucPhamOptional = thucPhamRepository.findById(idThucPham);
		return thucPhamOptional.orElse(null);
	}

	/**
	 * Hiển thị các thực phẩm theo mã nhóm.
	 * 
	 * @param maNhom Mã nhóm thực phẩm.
	 * @return Danh sách đối tượng ThucPhamDto chứa thông tin các thực phẩm trong
	 *         nhóm.
	 */
	public List<ThucPhamDto> listThucPhamByMaNhom(String maNhom) {
		List<ThucPhamDto> thucPhams = thucPhamRepository.findByMaNhomThucPham(maNhom);
		return thucPhams;
	}

	/**
	 * Hiển thị chi tiết thông tin thực phẩm.
	 * 
	 * @param idThucPham Id của thực phẩm cần hiển thị chi tiết.
	 * @return Đối tượng ThucPhamDto chứa chi tiết thông tin thực phẩm hoặc null nếu
	 *         không tìm thấy.
	 */
	public ThucPhamDto listCTCThucPham(String idThucPham) {
		ThucPhamDto cTCThucPham = thucPhamRepository.findByThucPham(idThucPham);
		return cTCThucPham;
	}

	/**
	 * Hiển thị tất cả các món ăn trong suất ăn.
	 * 
	 * @param suatAn Mã suất ăn.
	 * @return Danh sách đối tượng SuatAnDto chứa thông tin các món ăn trong suất
	 *         ăn.
	 */
	public List<SuatAnDto> findThucPhamBySuatAn(Long suatAn) {
		return thucPhamRepository.findThucPhamBySuatAn(suatAn);
	}

	/**
	 * Tìm kiếm thực phẩm theo tên.
	 * 
	 * @param searchKey Từ khóa tìm kiếm.
	 * @return Danh sách đối tượng ThucPhamDto chứa thông tin thực phẩm được tìm
	 *         thấy.
	 */
	public List<ThucPhamDto> findThucPhamByTen(String searchKey) {
		return thucPhamRepository.findThucPhamByTen(searchKey);
	}
}
