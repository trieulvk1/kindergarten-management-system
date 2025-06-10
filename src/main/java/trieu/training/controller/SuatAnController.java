package trieu.training.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import trieu.training.dto.SuatAnDto;
import trieu.training.dto.SuatAnRequest;
import trieu.training.model.NhomThucPham;
import trieu.training.model.SuatAn;
import trieu.training.sevices.NhomThucPhamServiceInterface;
import trieu.training.sevices.SuatAnServiceInterface;

/**
 * Lớp Controller để quản lý các yêu cầu liên quan đến suất ăn.
 * 
 * @author LuongVanTrieu 20/06/2001
 */
@Controller
@RequestMapping("/quanly")
public class SuatAnController {
	@Autowired
	private NhomThucPhamServiceInterface nhomThucPhamService;
	@Autowired
	private SuatAnServiceInterface suatAnService;

	/**
	 * Hiển thị trang suất ăn dinh dưỡng.
	 * 
	 * @param model Đối tượng Model để truyền dữ liệu đến view.
	 * @return Tên của view "quanly/suatan".
	 */
	@GetMapping("/suatan")
	public String showForm(Model model) {
		model.addAttribute("suatAnForm", new SuatAn());
		return "quanly/suatan";
	}

	/**
	 * Lưu suất ăn và trả lại kết quả.
	 * 
	 * @param suatAnRequest Đối tượng SuatAnRequest chứa thông tin suất ăn.
	 * @return ResponseEntity chứa mã suất ăn hoặc trạng thái thành công.
	 */
	@PostMapping("/suatan/saveSuatAn")
	public ResponseEntity<?> themThucPhamVaSuatAn(@RequestBody SuatAnRequest suatAnRequest) {
		try {
			SuatAn savedSuatAn = suatAnService.saveSuatAnChiTiet(suatAnRequest);

			if (savedSuatAn != null) {
				return ResponseEntity.ok(savedSuatAn.getMaSuatAn());

			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi xảy ra: " + e.getMessage());
		}
	}
	
	/**
	 * Kiểm tra sự tồn tại của suất ăn trước khi xuất.
	 * 
	 * @param suatAnRequest Đối tượng SuatAnRequest chứa thông tin yêu cầu kiểm tra suất ăn.
	 * @return Đối tượng ResponseEntity chứa giá trị boolean:
	 *         - true nếu suất ăn tồn tại.
	 *         - false nếu suất ăn không tồn tại.
	 */
	@PostMapping("/suatan/checkBeforeExport")
    public ResponseEntity<Boolean> kiemTraSuatAnTonTai(@RequestBody SuatAnRequest suatAnRequest) {
        boolean result = suatAnService.kiemTraSuatAnTonTai(suatAnRequest);
        return ResponseEntity.ok(result);
    }

	/**
	 * Xóa suất ăn theo mã suất ăn.
	 * 
	 * @param maSuatAn Mã suất ăn cần xóa.
	 * @return ResponseEntity với trạng thái thành công hoặc lỗi.
	 */
	@DeleteMapping("/suatan/deleteSuatAn/{maSuatAn}")
	public ResponseEntity<?> deleteSuatAnById(@PathVariable Long maSuatAn) {
		try {
			suatAnService.deleteSuatAnById(maSuatAn);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi xảy ra: " + e.getMessage());
		}
	}

	/**
	 * Hiển thị các suất ăn mẫu.
	 * 
	 * @return Danh sách các suất ăn mẫu.
	 */
	@GetMapping(value = "/suatan/getSuatAnMauList", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<SuatAnDto>> getSuatAnMauList() {
		try {
			List<SuatAnDto> suatAnList = suatAnService.findAllSuatAnMau();
			return new ResponseEntity<>(suatAnList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Hiển thị chi tiết các chất khi chọn thực phẩm.
	 * 
	 * @param idThucPham Mã thực phẩm cần thêm.
	 * @return Đối tượng SuatAnDto chứa thông tin chi tiết thực phẩm.
	 */
	@GetMapping("/suatan/addThucPham")
	@ResponseBody
	public SuatAnDto addThucPhamById(@RequestParam String idThucPham) {
		return suatAnService.addThucPhamById(idThucPham);
	}

	/**
	 * Hiển thị tất cả các nhóm thực phẩm.
	 * 
	 * @return Danh sách các nhóm thực phẩm.
	 */
	@ModelAttribute("listNhomThucPham")
	public List<NhomThucPham> initNhomThucPham() {
		return nhomThucPhamService.listNhomThucPham();
	}

	/**
	 * Hiển thị tất cả thông tin suất ăn mẫu.
	 * 
	 * @param maSuatAn Mã suất ăn mẫu (tùy chọn).
	 * @return Danh sách các suất ăn mẫu.
	 */
	@GetMapping("/suatan/getSuatAnMau")
	@ResponseBody
	public List<SuatAnDto> findSuatAnMauById(@RequestParam(required = false) Long maSuatAn) {
		List<SuatAnDto> suatAnList = suatAnService.findSuatAnMauById(maSuatAn);
		return suatAnList;
	}

	/**
	 * Hiển thị tất cả thông tin suất ăn mẫu.
	 * 
	 * @param maSuatAn Mã suất ăn mẫu (tùy chọn).
	 * @return Danh sách các suất ăn mẫu.
	 */
	@GetMapping("/suatan/getSuatAnMauToCopy")
	@ResponseBody
	public boolean findSuatAnMauByIdToCopy(@RequestParam(required = false) Long maSuatAn) {
		return suatAnService.findSuatAnMauByIdToCopy(maSuatAn);
	}

}
