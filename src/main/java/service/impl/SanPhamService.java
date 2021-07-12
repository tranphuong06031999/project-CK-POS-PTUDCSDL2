/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import entity.SanPhamEntity;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ISanPhamRepository;
import service.ISanPhamService;

/**
 *
 * @author Trần Đinh Phương
 */
@Service
public class SanPhamService implements ISanPhamService {

    @Autowired
    private ISanPhamRepository sanPhamRepository;

    @Override
    public SanPhamEntity findOne(int masp) {
        return sanPhamRepository.findById(masp);
    }

    @Override
    public String addSanPham(SanPhamEntity sp) {
        if (sanPhamRepository.create(sp) == true) {
            return "Thêm sản phẩm thành công";
        } else {
            return "Thêm sản phẩm thất bại!";
        }
    }

    @Override
    public String updateSanPham(SanPhamEntity sp) {
        if (sanPhamRepository.update(sp) == true) {
            SanPhamEntity sanpham = sanPhamRepository.findById(sp.getMaSP());
            return "Cập nhập sản phẩm thành công";
        } else {
            return "Cập nhập sản phẩm thất bại";
        }
    }

    @Override
    public ArrayList<SanPhamEntity> searchSanPham(String keyword) {
        return sanPhamRepository.search(keyword);
    }

    @Override
    public ArrayList<SanPhamEntity> productList() {
        return sanPhamRepository.productsList();
    }

    @Override
    public ArrayList<SanPhamEntity> getAllPaging(int page) {
        return sanPhamRepository.paging(page);
    }

    @Override
    public int totalPage() {
        int totalPage = (int) Math.ceil((double) sanPhamRepository.count() / 10);
        return totalPage;
    }

    @Override
    public ArrayList<SanPhamEntity> searchPaging(String keyword, int page) {
        return sanPhamRepository.searchPaging(keyword, page);
    }

    @Override
    public int totalPageSearch(String keyword) {
        int totalPage = (int) Math.ceil((double) sanPhamRepository.countSearch(keyword) / 10);
        return totalPage;
    }

    @Override
    public boolean updateSoluong(int masp, int soluong) {
        return sanPhamRepository.updateSoluong(masp, soluong);
    }
}
