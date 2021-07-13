/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import entity.QuyCachEntity;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.IQuyCachRepository;
import service.IQuyCachService;

/**
 *
 * @author NguyenThanhDat
 */
@Service
public class QuyCachService implements IQuyCachService {

    @Autowired
    IQuyCachRepository qcRepository;

    @Override
    public ArrayList<QuyCachEntity> getAllByMaSP(int masp) {
        return qcRepository.findAllByMaSP(masp);
    }

    @Override
    public String addSpecifications(QuyCachEntity qc) {
        if (qcRepository.create(qc) == true) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public String updateSpecifications(QuyCachEntity qc) {
        if (qcRepository.update(qc) == true) {
            return "Cập nhập thành công";
        } else {
            return "Cập nhập thất bại";
        }
    }

    @Override
    public String deleteSpecifications(int id) {
        if (qcRepository.delete(id) == true) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }

}
