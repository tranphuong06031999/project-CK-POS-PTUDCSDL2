/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.AbstractEntity;
import entity.KhachHangEntity;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import service.IKhachHangService;

/**
 *
 * @author NguyenThanhDat
 */
@Controller
public class KhachHangController {

    @Autowired
    private IKhachHangService khService;

    //Lấy danh sách khách hàng
    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public ModelAndView getAll(@RequestParam(defaultValue = "1", name = "page", required = false) int page, @RequestParam(name = "message", required = false) String message) {
        if (page > khService.totalPage()) {
            page = khService.totalPage();
        }
        if (page < 1) {
            page = 1;
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("list", khService.getAllPaging(page));
        mav.addObject("totalPage", khService.totalPage());
        mav.addObject("currentPage", page);
        mav.addObject("message", message);
        mav.setViewName("customersList");
        return mav;
    }

    //Tìm theo tên hoặc mã khách hàng
    @RequestMapping(value = "/customer/search", method = RequestMethod.GET)
    public ModelAndView searchCustomer(@RequestParam("keyword") String keyword, @RequestParam(defaultValue = "1", name = "page", required = false) int page) {
        if (page > khService.totalPageSearch(keyword)) {
            page = khService.totalPageSearch(keyword);
        }
        if (page < 1) {
            page = 1;
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("list", khService.searchPaging(keyword, page));
        mav.addObject("totalPage", khService.totalPageSearch(keyword));
        mav.addObject("currentPage", page);
        mav.addObject("keyword", keyword);
        mav.setViewName("customersList");
        return mav;
    }

    //Thêm khách hàng
    @RequestMapping(value = "/customer/add", method = RequestMethod.POST)
    public RedirectView addCustomer(@ModelAttribute KhachHangEntity kh, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("message", khService.addCustomer(kh));
        return new RedirectView("/customer");
    }

    @RequestMapping(value = "/customer/update", method = RequestMethod.POST)
    public RedirectView updateCustomer(@ModelAttribute KhachHangEntity kh, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("message", khService.updateCustomer(kh));
        return new RedirectView("/customer");
    }

    @RequestMapping(value = "/customer/pay-in", method = RequestMethod.POST)
    public RedirectView payIn(@ModelAttribute AbstractEntity entity, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("message", khService.payIn(entity.getMakh(), entity.getTiennap()));
        return new RedirectView("/customer");
    }
}
