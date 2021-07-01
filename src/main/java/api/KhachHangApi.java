/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import entity.KhachHangEntity;
import java.util.ArrayList;
import java.util.HashMap;
import mapper.RequestPayinMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.IKhachHangService;
/**
 *
 * @author NguyenThanhDat
 */
@RestController
public class KhachHangApi {

    @Autowired
    private IKhachHangService khService;

    //Lấy danh sách khách hàng
    @RequestMapping(value = "/api/customer", method = RequestMethod.GET)
    public ArrayList<KhachHangEntity> getAll() {
        return khService.getAll();
    }

    //Tìm theo tên hoặc mã khách hàng
    @RequestMapping(value = "/api/customer/search", method = RequestMethod.GET)
    public ArrayList<KhachHangEntity> searchCustomer(@RequestParam("keyword") String keyword) {
        return khService.searchCustomer(keyword);
    }

    //Thêm khách hàng
    @RequestMapping(value = "/api/customer/add", method = RequestMethod.POST)
    public HashMap<String, String> addCustomer(@RequestBody KhachHangEntity kh) {
        return khService.addCustomer(kh);
    }

    //Sửa khách hàng
    @RequestMapping(value = "/api/customer/update", method = RequestMethod.PUT)
    public HashMap<String, String> updateCustomer(@RequestBody KhachHangEntity kh) {
        return khService.updateCustomer(kh);
    }

    //Lấy 1 khách hàng
    @RequestMapping(value = "/api/customer/{id}", method = RequestMethod.GET)
    public KhachHangEntity getOne(@PathVariable("id") int id) {
        return khService.getOne(id);
    }

    //Nạp tiền
    @RequestMapping(value = "/api/customer/pay-in", method = RequestMethod.PUT)
    public HashMap<String, String> payIn(@RequestBody RequestPayinMapper mapper) {
        return khService.payIn(mapper.getMakh(),mapper.getTiennap());
    }
}
