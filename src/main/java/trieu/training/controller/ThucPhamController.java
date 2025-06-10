package trieu.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import trieu.training.dto.SuatAnDto;
import trieu.training.dto.ThucPhamDto;
import trieu.training.model.ThucPham;
import trieu.training.sevices.ThucPhamServiceInterface;

/**
 * Lớp Controller để quản lý các yêu cầu liên quan đến thực phẩm.
 * @author LuongVanTrieu 20/06/2001
 */
@Controller
@RequestMapping("/quanly")
public class ThucPhamController {

    @Autowired
    private ThucPhamServiceInterface thucPhamService;

    /**
     * Hiển thị thực phẩm khi chọn nhóm thực phẩm.
     * @param maNhom Mã nhóm thực phẩm (tùy chọn).
     * @return Danh sách ThucPhamDto theo nhóm thực phẩm.
     */
    @GetMapping("/thucpham/getNhom")
    @ResponseBody
    public List<ThucPhamDto> getThucPhamByNhomThucPham(@RequestParam(required = false) String maNhom) {
        List<ThucPhamDto> thucPhamList = thucPhamService.listThucPhamByMaNhom(maNhom);
        return thucPhamList;
    }

    /**
     * Hiển thị chi tiết các chất khi chọn thực phẩm.
     * @param idThucPham Mã thực phẩm.
     * @return Đối tượng ThucPhamDto chứa thông tin chi tiết của thực phẩm.
     */
    @GetMapping("/thucpham/getThucPham")
    @ResponseBody
    public ThucPhamDto getThucPhamById(@RequestParam String idThucPham) {
        ThucPhamDto thucPham = thucPhamService.listCTCThucPham(idThucPham);
        return thucPham;
    }

    /**
     * Lưu hoặc cập nhật thực phẩm.
     * @param thucPham Đối tượng ThucPham cần lưu hoặc cập nhật.
     * @return ResponseEntity chứa thông báo kết quả.
     */
	    @PostMapping("thucpham/save")
	    public ResponseEntity<?> updateFood(@RequestBody ThucPham thucPham) {
	        ThucPham updateSuccess = thucPhamService.saveOrUpdate(thucPham);
	        if (updateSuccess != null) {
	            return ResponseEntity.ok("Thông tin thực phẩm đã được cập nhật thành công");
	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi cập nhật thông tin thực phẩm");
	        }
	    }

    /**
     * API lấy danh sách thực phẩm theo ID suất ăn.
     * @param maSuatAn Mã suất ăn.
     * @return ResponseEntity chứa danh sách SuatAnDto theo mã suất ăn.
     */
    @GetMapping("/thucpham/{maSuatAn}")
    public ResponseEntity<List<SuatAnDto>> getThucPhamBySuatAn(@PathVariable Long maSuatAn) {
        List<SuatAnDto> thucPhamList = thucPhamService.findThucPhamBySuatAn(maSuatAn);
        for (SuatAnDto suatAnDto : thucPhamList) {
            suatAnDto.setChatBeo(suatAnDto.getBeoDongVat() + suatAnDto.getBeoThucVat());
            suatAnDto.setChatDam(suatAnDto.getDamDongVat() + suatAnDto.getDamThucVat());
        }
        return ResponseEntity.ok(thucPhamList);
    }

    /**
     * Tìm kiếm thực phẩm bằng tên.
     * @param searchKey Từ khóa tìm kiếm.
     * @return Danh sách ThucPhamDto theo từ khóa tìm kiếm.
     */
    @GetMapping("/thucpham/search")
    @ResponseBody
    public List<ThucPhamDto> searchThucPham(@RequestParam("searchKey") String searchKey) {
        List<ThucPhamDto> thucPhamList = thucPhamService.findThucPhamByTen(searchKey);
        return thucPhamList;
    }
}
