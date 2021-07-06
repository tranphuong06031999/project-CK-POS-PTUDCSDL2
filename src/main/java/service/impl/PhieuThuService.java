/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import entity.PhieuThuEntity;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.IPhieuThuRepository;
import service.IPhieuThuService;

/**
 *
 * @author NguyenThanhDat
 */
@Service
public class PhieuThuService implements IPhieuThuService {

    @Autowired
    private IPhieuThuRepository ptRepository;

    @Override
    public ArrayList<PhieuThuEntity> getAll() {
        return ptRepository.findAll();
    }

    @Override
    public ArrayList<PhieuThuEntity> searchReceipt(String keyword) {
        return ptRepository.search(keyword);
    }

    @Override
    public PhieuThuEntity getOne(int id) {
        return ptRepository.findOne(id);
    }

    @Override
    public ArrayList<PhieuThuEntity> getAllPaging(int page) {
        return ptRepository.paging(page);
    }

    @Override
    public int totalPage() {
        int totalPage = (int) Math.ceil((double) ptRepository.count() / 10);
        return totalPage;
    }

    @Override
    public ArrayList<PhieuThuEntity> searchPaging(String keyword, int page) {
        return ptRepository.searchPaging(keyword, page);
    }

    @Override
    public int totalPageSearch(String keyword) {
        int totalPage = (int) Math.ceil((double) ptRepository.countSearch(keyword) / 10);
        return totalPage;
    }

}
