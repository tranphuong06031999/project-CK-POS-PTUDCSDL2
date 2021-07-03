/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import entity.SanPhamEntity;
import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ISanPhamRepository;
import service.ISanPhamService;

/**
 *
 * @author Trần Đinh Phương
 */
@Service
public class SanPhamService implements ISanPhamService{
    @Autowired
    private ISanPhamRepository sanPhamRepository;
    
    @Override
    public SanPhamEntity findOne(int masp){      
        return sanPhamRepository.findById(masp);
    }
    @Override
    public HashMap<String, String> addSanPham(SanPhamEntity sp){
        HashMap<String, String> message = new HashMap<>();
        if(sanPhamRepository.create(sp) == true){
            message.put("message", "Success");
        }
        else{
            message.put("message", "Add failed!");
        }
        return message;
    }
    @Override
    public HashMap<String, SanPhamEntity> updateSanPham(SanPhamEntity sp){
        HashMap<String, SanPhamEntity> data = new HashMap<>();
        if(sanPhamRepository.update(sp) == true){
            SanPhamEntity sanpham = sanPhamRepository.findById(sp.getMaSP());
//            data.put("message", "Success");
            data.put("sanpham", sanpham);
        }
        else{
            data.put("sanpham", null);
//            message.put("redirect", "/update");
        }
        return data;
    }
    @Override
    public ArrayList<SanPhamEntity> searchSanPham(String keyword) {
        return sanPhamRepository.search(keyword);
    }
    @Override
    public ArrayList<SanPhamEntity> productList() {
        return sanPhamRepository.productsList();
    }
}
