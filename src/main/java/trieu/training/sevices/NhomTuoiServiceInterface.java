package trieu.training.sevices;

import java.util.List;

import trieu.training.model.NhomTuoi;
/**
 * Interface định nghĩa các phương thức cung cấp dịch vụ liên quan đến quản lý nhóm tuổi.
 * Các lớp cụ thể sẽ triển khai interface này để thực hiện các phương thức cụ thể.
 * @author LuongVanTrieu 20/06/2001
 */
public interface NhomTuoiServiceInterface{

    /**
     * Hiển thị danh sách nhóm tuổi.
     * @return Danh sách các đối tượng NhomTuoi.
     */
    public List<NhomTuoi> getAllNhomTuoi();
}
