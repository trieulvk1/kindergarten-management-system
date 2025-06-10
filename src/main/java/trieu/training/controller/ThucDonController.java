package trieu.training.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import trieu.training.dto.SuatAnDto;
import trieu.training.dto.ThucDonDto;
import trieu.training.dto.ThucDonRequest;
import trieu.training.model.NhomTuoi;
import trieu.training.model.SuatAn;
import trieu.training.sevices.NhomTuoiServiceInterface;
import trieu.training.sevices.SuatAnServiceInterface;
import trieu.training.sevices.ThucDonServiceInterface;

/**
 * Lớp Controller để quản lý các yêu cầu liên quan đến thực đơn.
 * @author LuongVanTrieu 20/06/2001
 */
@Controller
@RequestMapping("/quanly")
public class ThucDonController {

    @Autowired
    private SuatAnServiceInterface suatAnService;
    
    @Autowired
    private ThucDonServiceInterface thucDonService;

    @Autowired
    private NhomTuoiServiceInterface nhomTuoiService;
    
    /**
     * Hiển thị trang thực đơn.
     * @param model Đối tượng Model để truyền dữ liệu đến view.
     * @return Tên của view "quanly/thucdon".
     */
    @GetMapping("/thucdon")
    public String showThucDon(Model model) {
        return "quanly/thucdon";
    }

    /**
     * Hiển thị danh sách suất ăn mẫu.
     * @return Danh sách các suất ăn.
     */
    @ModelAttribute("listSuatAn")
    public List<SuatAn> getAllSuatAn() {
        return suatAnService.getAllSuatAn();
    }
    
    /**
     * Hiển thị thông tin thực đơn mẫu.
     * @return Danh sách các thực đơn.
     */
    @GetMapping("/thucdon/getListThucDonMau")
    public ResponseEntity<List<ThucDonDto>> getAllThucDon() {
        try {
        	List<ThucDonDto>listThucDon= thucDonService.getAllThucDon();
            return new ResponseEntity<>(listThucDon, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    /**
     * Hiển thị thông tin tên nhóm tuổi.
     * @return Danh sách các nhóm tuổi.
     */
    @ModelAttribute("listNhomTuoi")
    public List<NhomTuoi> getAllNhomTuoi() {
        return nhomTuoiService.getAllNhomTuoi();
    }

    /**
     * Hiển thị tất cả thông tin suất ăn mẫu.
     * @param maSuatAn Mã suất ăn (tùy chọn).
     * @return Danh sách các suất ăn mẫu.
     */
    @GetMapping("/thucdon/getSuatAnMau")
    @ResponseBody
    public List<SuatAnDto> getSuatAnMauById(@RequestParam(required = false) Long maSuatAn) {
        List<SuatAnDto> suatAnList = suatAnService.findSuatAnMauById(maSuatAn);
        return suatAnList;
    }
    
    /**
     * Hiển thị tất cả thông tin thực đơn mẫu.
     * @param maThucDonMau Mã thực đơn mẫu (tùy chọn).
     * @return Danh sách các thực đơn mẫu.
     */
    @GetMapping("/thucdon/getThucDonMau")
    @ResponseBody
    public List<ThucDonDto> getThucDonMauById(@RequestParam(required = false) Long maThucDonMau) {
        List<ThucDonDto> suatAnList = thucDonService.getAllThucDonMauById(maThucDonMau);
        return suatAnList;
    }
    
    /**
     * Hiển thị thông tin thực đơn theo tuần.
     * @param selectedDateStr Ngày được chọn dưới dạng chuỗi.
     * @return Đối tượng ResponseEntity chứa danh sách các thực đơn theo tuần.
     */
    @GetMapping("/thucdon/getThucDonByWeek")
    public ResponseEntity<List<ThucDonDto>> getThucDonByWeek(@RequestParam("selectedDate") String selectedDateStr) {
        LocalDate selectedDate = LocalDate.parse(selectedDateStr);
        List<ThucDonDto> thucDonDtos = thucDonService.getThucDonByWeek(selectedDate);
        return ResponseEntity.ok(thucDonDtos);
    }
    
    /**
     * Lưu thực đơn và xóa thực đơn mẫu nếu được chọn.
     * @param thucDonRequest Đối tượng chứa thông tin thực đơn cần lưu.
     * @param maThucDon Mã thực đơn mẫu.
     * @return Đối tượng ResponseEntity thông báo kết quả lưu thực đơn.
     */
    @PostMapping("/thucdon/save/{idThucDonMau}")
    public ResponseEntity<?> saveThucDonAnDeleteTDM(@RequestBody ThucDonRequest thucDonRequest, @PathVariable("idThucDonMau") Long maThucDon) {
        thucDonService.saveThucDon(thucDonRequest.getSelectedDate(), thucDonRequest.getThucDonDtos(), maThucDon);
        return ResponseEntity.ok("Thực đơn đã được lưu thành công.");
    }
    
    /**
     * Lưu thực đơn.
     * @param thucDonRequest Đối tượng chứa thông tin thực đơn cần lưu.
     * @return Đối tượng ResponseEntity thông báo kết quả lưu thực đơn.
     */
    @PostMapping("/thucdon/save")
    public ResponseEntity<?> saveThucDon(@RequestBody ThucDonRequest thucDonRequest) {
        thucDonService.saveThucDon(thucDonRequest.getSelectedDate(), thucDonRequest.getThucDonDtos(), null);
        return ResponseEntity.ok("Thực đơn đã được lưu thành công.");
    }
}
