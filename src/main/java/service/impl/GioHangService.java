/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import entity.GioHangEntity;
import entity.KhuyenMaiEntity;
import entity.SanPhamEntity;
import entity.SanPhamKhuyenMaiEntity;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.IGioHangRepository;
import repository.IKhachHangRepository;
import repository.ISanPhamRepository;
import service.IGioHangService;

/**
 *
 * @author NguyenThanhDat
 */
@Service
public class GioHangService implements IGioHangService {

    @Autowired
    IGioHangRepository ghRepository;

    @Autowired
    IKhachHangRepository khRepository;

    @Autowired
    ISanPhamRepository spRepository;

    @Override
    public String createCart(GioHangEntity cart) {
        if (khRepository.findOne(cart.getMakh()) == null) {
            return "Không tìm thấy khách hàng";
        } else {
            int magiohang = ghRepository.isExists(cart.getMasp(), cart.getMakh());
            SanPhamEntity sp = spRepository.findById(cart.getMasp());
            int soluong = sp.getSoLuong() - cart.getSoluong();
            if (spRepository.updateSoluong(sp.getMaSP(), soluong) == true) {
                if (magiohang < 0) {
                    int giatong = cart.getGia() * cart.getSoluong();
                    cart.setGiatong(giatong);
                    if (ghRepository.create(cart) == true) {
                        return "Thêm giỏ hàng thành công";
                    } else {
                        return "Thêm giỏ hàng thất bại";
                    }
                } else {
                    if (ghRepository.incremental(magiohang, cart.getGia(), cart.getSoluong()) == true) {
                        return "Thêm giỏ hàng thành công";
                    } else {
                        return "Thêm giỏ hàng thất bại";
                    }
                }
            } else {
                return "Thêm giỏ hàng thất bại";
            }
        }
    }

    @Override
    public String updateCart(GioHangEntity cart) {
        SanPhamEntity sp = spRepository.findById(cart.getMasp());
        GioHangEntity gh = ghRepository.findById(cart.getMagiohang());
        int soluong = sp.getSoLuong();
        if (cart.getSoluong() - gh.getSoluong() == 1) {
            soluong = soluong - 1;
        } else if (cart.getSoluong() - gh.getSoluong() == -1) {
            soluong = soluong + 1;
        } else if (cart.getSoluong() - gh.getSoluong() < -1) {
            soluong = soluong + (gh.getSoluong() - cart.getSoluong());
        } else if (cart.getSoluong() - gh.getSoluong() > 1) {
            soluong = soluong - (cart.getSoluong() - gh.getSoluong());
        }
        if (soluong < 1) {
            return "Số lượng đã vượt quá số lượng tồn";
        } else {
            if (spRepository.updateSoluong(sp.getMaSP(), soluong) == true) {
                if (ghRepository.update(cart) == true) {
                    return "Cập nhật số lượng thành công";
                } else {
                    return "Cập nhật số lượng thất bại";
                }
            } else {
                return "Cập nhật số lượng thất bại";
            }
        }
    }

    @Override
    public String deleteCart(int magiohang) {
        GioHangEntity gh = ghRepository.findById(magiohang);
        SanPhamEntity sp = spRepository.findById(gh.getMasp());
        int soluong = sp.getSoLuong() + gh.getSoluong();
        if (spRepository.updateSoluong(sp.getMaSP(), soluong) == true) {
            if (ghRepository.delete(magiohang) == true) {
                return "Xóa giỏ hàng thành công";
            } else {
                return "Xóa giỏ hàng thất bại";
            }
        } else {
            return "Xóa giỏ hàng thất bại";
        }
    }

    @Override
    public ArrayList<GioHangEntity> getAll(int makh) {
        return ghRepository.findAll(makh);
    }

    @Override
    public int totalPrice(int makh) {
        int total = ghRepository.totalPrice(makh);
        return total;
    }
    
    @Override
    public int discount(int makh){
        ArrayList<KhuyenMaiEntity> sp_km = khuyenMai(makh);
        ArrayList<KhuyenMaiEntity> chietKhau = chietKhau(makh);
        
        int discount = 0;
        
        for ( KhuyenMaiEntity ck: chietKhau){
                discount += ck.getGiaTienGiam();           
        }  
        
        if (sp_km != null){
            for ( KhuyenMaiEntity spkm: sp_km){
                discount += spkm.getGiaTienGiam();           
            }
        }   
        return discount;
    }
    
    @Override
    public ArrayList<KhuyenMaiEntity> khuyenMai(int makh){
        ArrayList<Integer> masp = spRepository.getMaSanPham(makh);
        ArrayList<SanPhamKhuyenMaiEntity> spKm = new ArrayList<SanPhamKhuyenMaiEntity>();
        
        ArrayList<KhuyenMaiEntity> listKm = new ArrayList<KhuyenMaiEntity>();
        
        if (masp != null){
            for (int i = 0; i < masp.size(); i++) {
                SanPhamKhuyenMaiEntity sp = new SanPhamKhuyenMaiEntity();
                sp = ghRepository.getThongTinKhuyenMaiSanPham(masp.get(i));
                if ( sp.getTenKhuyenMai() != null){
                    spKm.add(sp);
                }
            }
        }else{
            spKm = null;
        }
        
        ArrayList<KhuyenMaiEntity> chietKhau = chietKhau(makh);
        
        if ( spKm != null){
            for ( SanPhamKhuyenMaiEntity spkm: spKm) {
                int sl_sp = 0;
                KhuyenMaiEntity thongTinKhuyenMai = new KhuyenMaiEntity();           
                sl_sp = ghRepository.soLuongSanPhamTrongGioHang( spkm.getMaSanPham(), makh);
                int giaSp = 0;
                int demKhachHangChietKhau = 0;
                
                for ( KhuyenMaiEntity ck: chietKhau ){         
                    if ( ck.getTenKhuyenMai().equals(String.valueOf(spkm.getMaSanPham())) && ck.getGiaTienGiam() > 0){
                        giaSp = spkm.getGiaSanPham() * sl_sp - ck.getGiaTienGiam();
                        demKhachHangChietKhau = 1;
                    }
                }
                
                if (demKhachHangChietKhau == 0){
                    giaSp = spkm.getGiaSanPham() * sl_sp;
                }
                
                int giaSpGiamGia = (int)(giaSp * ((float)spkm.getMaGiamGia() / 100 ));
                
                if ( giaSpGiamGia > spkm.getToiDaKhuyenMai() && spkm.getToiDaKhuyenMai() != 0){
                    giaSpGiamGia = spkm.getToiDaKhuyenMai();
                }
                String tenKm = spkm.getTenKhuyenMai() + " ( mã sản phẩm " + spkm.getMaSanPham() + " giảm tối đa " + spkm.getToiDaKhuyenMai() + "đ )";
                thongTinKhuyenMai.setGiaTienGiam(giaSpGiamGia);
                thongTinKhuyenMai.setPhanTramGiamGia(spkm.getMaGiamGia());
                thongTinKhuyenMai.setTenKhuyenMai(tenKm);    
                listKm.add(thongTinKhuyenMai);
            }
        }else{
            listKm = null ;
        }
        return listKm;
    }
    
    @Override
    public ArrayList<KhuyenMaiEntity> chietKhau(int makh){
        ArrayList<KhuyenMaiEntity> chietKhau = new ArrayList<KhuyenMaiEntity>();
        ArrayList<Integer> masp = spRepository.getMaSanPham(makh);
        if ( masp != null ){
            for ( int sp: masp) {
                KhuyenMaiEntity thongTinChietKhau = new KhuyenMaiEntity();

                int giaSp = spRepository.getGiaSanPham(sp);
                int soLg = ghRepository.soLuongSanPhamTrongGioHang(sp, makh);

                if (soLg >= 10 ){
                    String ten = String.valueOf(sp);
                    int giaGiam = (int)(giaSp * soLg * (float)0.05);
                    int phanTramGiamGia = 5;

                    thongTinChietKhau.setGiaTienGiam(giaGiam);
                    thongTinChietKhau.setPhanTramGiamGia(phanTramGiamGia);
                    thongTinChietKhau.setTenKhuyenMai(ten);
                    chietKhau.add(thongTinChietKhau);
                }  else{
                    String ten = String.valueOf(sp);
                    thongTinChietKhau.setGiaTienGiam(0);
                    thongTinChietKhau.setPhanTramGiamGia(0);
                    thongTinChietKhau.setTenKhuyenMai(ten);
                    chietKhau.add(thongTinChietKhau);
                } 
            }
        }else{
            return null;
        }
        return chietKhau;
    }
    
    @Override
    public ArrayList<KhuyenMaiEntity> tongKhuyenMai(int makh){
        ArrayList<KhuyenMaiEntity> tongKhuyenMai = khuyenMai(makh);
        int totalPrice = totalPrice(makh);
        int discount = discount(makh);
        
        int price = totalPrice - discount;
        
        int kiemTraKhachHangThanThiet = khRepository.kiemTraKhachHangThanThiet(makh);
        if ( kiemTraKhachHangThanThiet > 0){
            KhuyenMaiEntity thongTinKhuyenMai = new KhuyenMaiEntity();   
            int giaSpGiamGia = (int)(price * 0.1);
            int maGiamGia = 10;
            thongTinKhuyenMai.setGiaTienGiam(giaSpGiamGia);
            thongTinKhuyenMai.setPhanTramGiamGia(maGiamGia);
            thongTinKhuyenMai.setTenKhuyenMai("Khách hàng thân thiết");    
            tongKhuyenMai.add(thongTinKhuyenMai);
        }
        return tongKhuyenMai;
    }    
   
    @Override
    public int tienGiamCuaKhachHangThanThiet(int makh){
        int priceResult = 0;
        int totalPrice = totalPrice(makh);
        int discount = discount(makh);
        
        int price = totalPrice - discount;
        
        int kiemTraKhachHangThanThiet = khRepository.kiemTraKhachHangThanThiet(makh);
        if ( kiemTraKhachHangThanThiet > 0){   
            priceResult = (int)(price * 0.1);
        }
        System.out.print("price: " + price);
        System.out.print("khtt: " + priceResult);
        
        return priceResult;
    }    
}
