/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import service.ILichBanHangService;

/**
 *
 * @author THAIHUYNH
 */
@RestController
public class LichBanHangController {

    @Autowired
    private ILichBanHangService lichBanHangService;

    @RequestMapping(value = "/log", method = RequestMethod.GET)
    public ModelAndView getAll(@RequestParam(defaultValue = "1", name = "page", required = false) int page) {
        if (page > lichBanHangService.totalPage()) {
            page = lichBanHangService.totalPage();
        }
        if (page < 1) {
            page = 1;
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("list", lichBanHangService.getAllPaging(page));
        mav.addObject("totalPage", lichBanHangService.totalPage());
        mav.addObject("currentPage", page);
        mav.setViewName("logList");
        return mav;
    }

    @RequestMapping(value = "/log/search", method = RequestMethod.GET)
    public ModelAndView getAllSearch(@RequestParam(defaultValue = "1", name = "page", required = false) int page, @RequestParam(defaultValue = "1", name = "makh") int makh) {
        if (page > lichBanHangService.totalPageSearch(makh)) {
            page = lichBanHangService.totalPageSearch(makh);
        }
        if (page < 1) {
            page = 1;
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("list", lichBanHangService.getAllPagingSearch(page, makh));
        mav.addObject("totalPage", lichBanHangService.totalPageSearch(makh));
        mav.addObject("currentPage", page);
        mav.addObject("keyword", makh);
        mav.setViewName("logList");
        return mav;
    }
}
