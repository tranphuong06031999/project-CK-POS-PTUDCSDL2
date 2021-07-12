/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.GioHangEntity;
import entity.KhuyenMaiEntity;
import entity.SanPhamKhuyenMaiEntity;
import java.util.ArrayList;

/**
 *
 * @author NguyenThanhDat
 */
public interface IGioHangService {

    public String createCart(GioHangEntity cart);
    
    public String updateCart(GioHangEntity cart);

    public String deleteCart(int magiohang);
    
    public ArrayList<GioHangEntity> getAll(int makh);  
    
    public int totalPrice(int makh);
    
    public int discount(int makh);
    
   public ArrayList<KhuyenMaiEntity> khuyenMai(int makh);
   
   public ArrayList<KhuyenMaiEntity> chietKhau(int makh);
   
   public ArrayList<KhuyenMaiEntity> tongKhuyenMai(int makh);
   
   public int tienGiamCuaKhachHangThanThiet( int makh );
}
