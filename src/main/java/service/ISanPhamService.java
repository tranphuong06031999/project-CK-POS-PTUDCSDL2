/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.SanPhamEntity;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Trần Đinh Phương
 */
public interface ISanPhamService {

    public SanPhamEntity findOne(int masp);

    public String addSanPham(SanPhamEntity sp);

    public String updateSanPham(SanPhamEntity sp);

    public ArrayList<SanPhamEntity> searchSanPham(String keyword);

    public ArrayList<SanPhamEntity> productList();

    public ArrayList<SanPhamEntity> getAllPaging(int page);

    public int totalPage();
}
