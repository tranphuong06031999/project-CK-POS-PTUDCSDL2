/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import entity.GioHangEntity;
import entity.SanPhamEntity;
import java.util.ArrayList;
import java.util.HashMap;
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
        int discount = discount(makh);
        total = total - (total * discount / 100);
        return total;
    }
    
    @Override
    public int discount(int makh){
        int qty = ghRepository.quantity(makh);
        if(qty <= 100){
            return 2;
        }
        else if(qty > 100 && qty <= 200){
            return 3;
        }
        else if(qty > 200 && qty <= 500){
            return 5;
        }
        else{
            return 7;
        }
    }

}
