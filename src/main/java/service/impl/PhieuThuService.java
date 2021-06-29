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

}
