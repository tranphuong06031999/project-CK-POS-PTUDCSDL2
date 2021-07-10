/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import entity.LichBanHangEnity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ILichBanHangRepository;
import service.ILichBanHangService;

/**
 *
 * @author THAIHUYNH
 */
@Service
public class LichBanHangService implements ILichBanHangService {

    @Autowired
    private ILichBanHangRepository lichBanHangRepository;

    @Override
    public List<LichBanHangEnity> getAllPaging(int page) {
        return lichBanHangRepository.paging(page);
    }

    @Override
    public int totalPage() {
        int totalPage = (int) Math.ceil((double) lichBanHangRepository.count() / 10);
        return totalPage;
    }

    

}
