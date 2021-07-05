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
import org.springframework.web.servlet.ModelAndView;
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
    @RequestMapping(value = "/receipt", method = RequestMethod.GET)
    public ModelAndView getAll(@RequestParam(defaultValue = "1", name = "page", required = false) int page) {
        if (page > ptService.totalPage()) {
            page = ptService.totalPage();
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("list", ptService.getAllPaging(page));
        mav.addObject("totalPage", ptService.totalPage());
        mav.addObject("currentPage", page);
        mav.setViewName("receiptsList");
        return mav;
    }

    //Tìm phiếu thu theo tên hoặc mã khách hàng
    @RequestMapping(value = "/receipt/search", method = RequestMethod.GET)
    public ModelAndView searchReceipt(@RequestParam("keyword") String keyword) {
        return new ModelAndView("receiptsList", "list", ptService.searchReceipt(keyword));
    }

    //Lấy 1 phiếu thu
    @RequestMapping(value = "/receipt/{id}", method = RequestMethod.GET)
    public ModelAndView getOne(@PathVariable("id") int id) {
        return new ModelAndView("detailReceipt", "receipt", ptService.getOne(id));
    }
}
