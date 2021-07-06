/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import entity.KhachHangEntity;
import entity.PhieuThuEntity;
import java.util.ArrayList;
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
    public String addCustomer(KhachHangEntity kh) {
        if (khRepository.findOneByPhone(kh.getSodienthoai()) == true) {
            return "Số điện thoại đã tồn tại";
        } else {
            if (khRepository.create(kh) == true) {
                return "Thêm khách hàng thành công";
            } else {
                return "Thêm khách hàng thất bại";
            }
        }
    }

    @Override
    public String updateCustomer(KhachHangEntity kh) {
        KhachHangEntity khTemp = khRepository.findOne(kh.getMakh());
        if (khTemp == null) {
            return "Không tìm thấy khách hàng";
        } else {
            if (khRepository.update(kh) == true) {
                return "Cập nhập khách hàng thành công";
            } else {
                return "Cập nhập khách hàng thất bại";
            }
        }
    }

    @Override
    public KhachHangEntity getOne(int id) {
        return khRepository.findOne(id);
    }

    @Override
    public String payIn(int makh, int tiennap) {
        KhachHangEntity khTemp = khRepository.findOne(makh);
        PhieuThuEntity pt = new PhieuThuEntity();
        if (khTemp == null) {
            return "Không tìm thấy khách hàng";
        } else {
            if (khRepository.updateSodu(makh, tiennap) == true) {
                khTemp = khRepository.findOne(makh);
                pt.setMakh(makh);
                pt.setSotiennap(tiennap);
                pt.setSodu(khTemp.getSodu());
                pt.setTenkh(khTemp.getTenkh());
                if (khTemp.getSodu() >= 10000000) {
                    khRepository.updateVip(makh);
                }
                if (ptRepository.create(pt) == true) {
                    return "Nạp tiền thành công";
                } else {
                    return "Nạp tiền thất bại";
                }
            } else {
                return "Nạp tiền thất bại";
            }
        }
    }

    @Override
    public ArrayList<KhachHangEntity> getAllPaging(int page) {
        return khRepository.paging(page);
    }

    @Override
    public int totalPage() {
        int totalPage = (int) Math.ceil((double) khRepository.count() / 10);
        return totalPage;
    }

    @Override
    public ArrayList<KhachHangEntity> searchPaging(String keyword, int page) {
        return khRepository.searchPaging(keyword, page);
    }

    @Override
    public int totalPageSearch(String keyword) {
        int totalPage = (int) Math.ceil((double) khRepository.countSearch(keyword) / 10);
        return totalPage;
    }
}
