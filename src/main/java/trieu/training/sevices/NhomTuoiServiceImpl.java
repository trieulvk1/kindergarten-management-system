package trieu.training.sevices;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import trieu.training.model.NhomTuoi;
import trieu.training.repository.NhomTuoiRepository;

/**
 * Class service để thực hiện các hoạt động liên quan đến NhomTuoi.
 * Sử dụng để hiển thị danh sách nhóm tuổi.
 * @author LuongVanTrieu 20/06/2001
 */
@Service
@Transactional
public class NhomTuoiServiceImpl implements NhomTuoiServiceInterface{

    @Autowired
    private NhomTuoiRepository nhomTuoiRepository;
    
    /**
     * Hiển thị danh sách nhóm tuổi.
     * @return Danh sách các đối tượng NhomTuoi.
     */
    public List<NhomTuoi> getAllNhomTuoi() {
        return nhomTuoiRepository.findAll();
    }
}
