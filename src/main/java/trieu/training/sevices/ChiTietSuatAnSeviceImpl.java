package trieu.training.sevices;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import trieu.training.model.ChiTietSuatAn;
import trieu.training.repository.ChiTietSuatAnRepository;

/**
 * Class service để thực hiện các hoạt động liên quan đến ChiTietSuatAn.
 * Được sử dụng để thêm mới chi tiết suất ăn.
 * @author LuongVanTrieu 20/06/2001
 */
@Service
@Transactional
public class ChiTietSuatAnSeviceImpl implements ChiTietSuatAnSeviceInterface{

    @Autowired
    private ChiTietSuatAnRepository chiTietSuatAnRepository;

    /**
     * Thêm mới chi tiết suất ăn.
     * @param chiTietSuatAn Đối tượng ChiTietSuatAn cần thêm mới.
     * @return Đối tượng ChiTietSuatAn đã được thêm mới.
     */
    public ChiTietSuatAn addMonAnToSuatAn(ChiTietSuatAn chiTietSuatAn) {
        return chiTietSuatAnRepository.save(chiTietSuatAn);
    }
    
}
