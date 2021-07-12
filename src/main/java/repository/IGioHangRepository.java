/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entity.GioHangEntity;
import entity.KhuyenMaiEntity;
import entity.SanPhamKhuyenMaiEntity;
import java.util.ArrayList;

/**
 *
 * @author NguyenThanhDat
 */
public interface IGioHangRepository {

    public boolean create(GioHangEntity cart);

    public boolean delete(int id);

    public boolean incremental(int id, int price, int qty);

    public boolean update(GioHangEntity cart);

    public GioHangEntity findOne(int id);

    public int isExists(int masp, int makh);

    public ArrayList<GioHangEntity> findAll(int makh);

    public int totalPrice(int makh);

    public int quantity(int makh);
    
    public GioHangEntity findById(int magiohang);
    
    public SanPhamKhuyenMaiEntity getThongTinKhuyenMaiSanPham(int masp);
    
    public int soLuongSanPhamTrongGioHang ( int masp, int makh);

}
