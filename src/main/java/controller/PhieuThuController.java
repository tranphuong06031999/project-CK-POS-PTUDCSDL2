/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.PhieuThuEntity;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.IPhieuThuService;

/**
 *
 * @author NguyenThanhDat
 */
@RestController
public class PhieuThuController {

    @Autowired
    private IPhieuThuService ptService;

    //Lấy danh sách phiếu thu
    @RequestMapping(value = "/api/receipt", method = RequestMethod.GET)
    public ArrayList<PhieuThuEntity> getAll() {
        return ptService.getAll();
    }

    //Tìm phiếu thu theo tên hoặc mã khách hàng
    @RequestMapping(value = "/api/receipt/search", method = RequestMethod.GET)
    public ArrayList<PhieuThuEntity> searchReceipt(@RequestParam("keyword")String keyword){
        return ptService.searchReceipt(keyword);
    }

    //Lấy 1 phiếu thu
    @RequestMapping(value = "/api/receipt/{id}", method = RequestMethod.GET)
    public PhieuThuEntity getOne(@PathVariable("id") int id) {
        return ptService.getOne(id);
    }
}
