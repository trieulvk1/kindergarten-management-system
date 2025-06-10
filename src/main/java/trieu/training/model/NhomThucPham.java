package trieu.training.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Nationalized;

@Entity
public class NhomThucPham {

    @Id
    private String maNhom;
    
    @Nationalized
    private String tenNhom;
    
    @OneToMany(mappedBy = "nhomThucPham", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ThucPham> thucPham = new ArrayList<>();

    public NhomThucPham(String maNhom, String tenNhom, List<ThucPham> thucPham) {
        this.maNhom = maNhom;
        this.tenNhom = tenNhom;
        this.thucPham = thucPham;
    }

    public NhomThucPham() {
    }

    public String getMaNhom() {
        return maNhom;
    }

    public void setMaNhom(String maNhom) {
        this.maNhom = maNhom;
    }

    public String getTenNhom() {
        return tenNhom;
    }

    public void setTenNhom(String tenNhom) {
        this.tenNhom = tenNhom;
    }

    public List<ThucPham> getThucPham() {
        return thucPham;
    }

    public void setThucPham(List<ThucPham> thucPham) {
        this.thucPham = thucPham;
    }
}
