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
    public HashMap<String, String> createCart(GioHangEntity cart) {
        int magiohang = ghRepository.isExists(cart.getMasp());
        HashMap<String, String> message = new HashMap<>();
        if (magiohang < 0) {
            int giatong = cart.getGia() * cart.getSoluong();
            cart.setGiatong(giatong);
            if (ghRepository.create(cart) == true) {
                message.put("message", "Success");
            } else {
                message.put("message", "Error");
            }
        } else {
            if (ghRepository.update(magiohang, cart.getGia(), cart.getSoluong()) == true) {
                message.put("message", "Success");
            } else {
                message.put("message", "Error");
            };
        }
        return message;
    }

    @Override
    public HashMap<String, String> deleteCart(int magiohang) {
        HashMap<String, String> message = new HashMap<>();
        if (ghRepository.delete(magiohang) == true) {
            message.put("message", "Success");
        } else {
            message.put("message", "Error");
        }
        return message;
    }

    @Override
    public ArrayList<GioHangEntity> getAll() {
        return ghRepository.findAll();
    }

}
