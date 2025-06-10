package trieu.training.sevices;

import trieu.training.model.ChiTietSuatAn;
/**
 * Giao diện dịch vụ cho các hoạt động liên quan đến Chi Tiết Suất Ăn.
 * Cung cấp các phương thức để thao tác và truy vấn dữ liệu liên quan đến Chi Tiết Suất Ăn.
 * @author LuongVanTrieu 20/06/2001
 */
public interface ChiTietSuatAnSeviceInterface {

    /**
     * Thêm mới chi tiết suất ăn.
     * @param chiTietSuatAn Đối tượng ChiTietSuatAn cần thêm mới.
     * @return Đối tượng ChiTietSuatAn đã được thêm mới.
     */
    public ChiTietSuatAn addMonAnToSuatAn(ChiTietSuatAn chiTietSuatAn);
    
}
