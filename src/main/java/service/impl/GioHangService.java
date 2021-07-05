/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import entity.GioHangEntity;
import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.IGioHangRepository;
import service.IGioHangService;

/**
 *
 * @author NguyenThanhDat
 */
@Service
public class GioHangService implements IGioHangService {

    @Autowired
    IGioHangRepository ghRepository;

    @Override
    public String createCart(GioHangEntity cart) {
        int magiohang = ghRepository.isExists(cart.getMasp());
        if (magiohang < 0) {
            int giatong = cart.getGia() * cart.getSoluong();
            cart.setGiatong(giatong);
            if (ghRepository.create(cart) == true) {
                return "Thêm giỏ hàng thành công";
            } else {
                return "Thêm giỏ hàng thất bại";
            }
        } else {
            if (ghRepository.update(magiohang, cart.getGia(), cart.getSoluong()) == true) {
                return "Thêm giỏ hàng thành công";
            } else {
                return "Thêm giỏ hàng thất bại";
            }
        }
    }

    @Override
    public String deleteCart(int magiohang) {
        if (ghRepository.delete(magiohang) == true) {
            return "Xóa giỏ hàng thành công";
        } else {
            return "Xóa giỏ hàng thất bại";
        }
    }

    @Override
    public ArrayList<GioHangEntity> getAll() {
        return ghRepository.findAll();
    }

}
