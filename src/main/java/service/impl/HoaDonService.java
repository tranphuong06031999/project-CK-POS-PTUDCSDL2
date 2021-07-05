/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import entity.ChiTietHoaDonEntity;
import entity.HoaDonEntity;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.IChiTietHoaDonRepository;
import repository.IHoaDonRepository;
import service.IHoaDonService;

/**
 *
 * @author NguyenThanhDat
 */
@Service
public class HoaDonService implements IHoaDonService {

    @Autowired
    IHoaDonRepository hdRepository;

    @Autowired
    IChiTietHoaDonRepository cthdRepository;

    @Override
    public ArrayList<HoaDonEntity> getAll() {
        return hdRepository.findAll();
    }

    @Override
    public ArrayList<ChiTietHoaDonEntity> getAllCTHD(int mahd) {
        return cthdRepository.findByMaHD(mahd);
    }

    @Override
    public ArrayList<HoaDonEntity> searchBill(int makh) {
        return hdRepository.search(makh);
    }

    @Override
    public HoaDonEntity getOne(int mahd) {
        return hdRepository.findOne(mahd);
    }

    @Override
    public ArrayList<HoaDonEntity> getAllPaging(int page) {
        return hdRepository.paging(page);
    }

    @Override
    public int totalPage() {
        int totalPage = (int) Math.ceil((double) hdRepository.count() / 10);
        return totalPage;
    }

}
