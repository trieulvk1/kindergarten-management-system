package trieu.training.sevices;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import trieu.training.model.NhomThucPham;
import trieu.training.repository.NhomThucPhamRepository;

/**
 * Class service để thực hiện các hoạt động liên quan đến NhomThucPham.
 * Sử dụng để hiển thị tất cả các nhóm thực phẩm.
 * @author LuongVanTrieu 20/06/2001
 */
@Service
@Transactional
public class NhomThucPhamServiceImpl implements NhomThucPhamServiceInterface{

    @Autowired
    private NhomThucPhamRepository nhomThucPhamRepository;

    /**
     * Hiển thị tất cả các nhóm thực phẩm.
     * @return Danh sách các đối tượng NhomThucPham.
     */
    public List<NhomThucPham> listNhomThucPham() {
        return nhomThucPhamRepository.findAll();
    }
}
