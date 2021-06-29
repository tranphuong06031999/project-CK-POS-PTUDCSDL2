/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import entity.KhachHangEntity;
import entity.PhieuThuEntity;
import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.IKhachHangRepository;
import repository.IPhieuThuRepository;
import service.IKhachHangService;

/**
 *
 * @author NguyenThanhDat
 */
@Service
public class KhachHangService implements IKhachHangService {

    @Autowired
    private IKhachHangRepository khRepository;

    @Autowired
    private IPhieuThuRepository ptRepository;

    @Override
    public ArrayList<KhachHangEntity> getAll() {
        return khRepository.findAll();
    }

    @Override
    public ArrayList<KhachHangEntity> searchCustomer(String keyword) {
        return khRepository.search(keyword);
    }

    @Override
    public HashMap<String, String> addCustomer(KhachHangEntity kh) {
        HashMap<String, String> message = new HashMap<>();
        if (khRepository.findOneByPhone(kh.getSodienthoai()) == true) {
            message.put("message", "Phone number already exists");
            message.put("redirect", "/customer");
        } else {
            if (khRepository.create(kh) == true) {
                message.put("message", "Success");
                message.put("redirect", "/customer");
            } else {
                message.put("message", "Error");
                message.put("redirect", "/customer");
            }
        }
        return message;
    }

    @Override
    public HashMap<String, String> updateCustomer(KhachHangEntity kh) {
        HashMap<String, String> message = new HashMap<>();
        KhachHangEntity khTemp = khRepository.findOne(kh.getMakh());
        if (khTemp == null) {
            message.put("message", "Not found customer");
            message.put("redirect", "/customer");
        } else {
            if (khRepository.update(kh) == true) {
                message.put("message", "Success");
                message.put("redirect", "/customer");
            } else {
                message.put("message", "Error");
                message.put("redirect", "/customer");
            }
        }
        return message;
    }

    @Override
    public KhachHangEntity getOne(int id) {
        return khRepository.findOne(id);
    }

    @Override
    public HashMap<String, String> payIn(int makh, int tiennap) {
        KhachHangEntity khTemp = khRepository.findOne(makh);
        PhieuThuEntity pt = new PhieuThuEntity();
        HashMap<String, String> message = new HashMap<>();
        if (khTemp == null) {
            message.put("message", "Not found customer");
            message.put("redirect", "/customer");
        } else {
            if (khRepository.updateSodu(makh, tiennap) == true) {
                khTemp = khRepository.findOne(makh);
                pt.setMakh(makh);
                pt.setSotiennap(tiennap);
                pt.setSodu(khTemp.getSodu());
                pt.setTenkh(khTemp.getTenkh());
                if (ptRepository.create(pt) == true) {
                    message.put("message", "Success");
                    message.put("redirect", "/customer");
                } else {
                    message.put("message", "Error");
                    message.put("redirect", "/customer");
                }
            } else {
                message.put("message", "Error");
                message.put("redirect", "/customer");
            }
        }
        return message;
    }

}
