/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import service.IGioHangService;
import service.IHoaDonService;
import service.IKhachHangService;

/**
 *
 * @author NguyenThanhDat
 */
@RestController
public class HoaDonController {

    @Autowired
    private IHoaDonService hdService;

    @Autowired
    private IKhachHangService khService;

    @Autowired
    private IGioHangService ghService;

    @RequestMapping(value = "/bill", method = RequestMethod.GET)
    public ModelAndView getAll(@RequestParam(defaultValue = "1", name = "page", required = false) int page) {
        if (page > hdService.totalPage()) {
            page = hdService.totalPage();
        }
        if (page < 1) {
            page = 1;
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("list", hdService.getAllPaging(page));
        mav.addObject("totalPage", hdService.totalPage());
        mav.addObject("currentPage", page);
        mav.setViewName("billsList");
        return mav;
    }

    @RequestMapping(value = "/bill/search", method = RequestMethod.GET)
    public ModelAndView search(@RequestParam(defaultValue = "1", name = "makh") int makh, @RequestParam(defaultValue = "1", name = "page", required = false) int page) {
        if (page > hdService.totalPageSearch(makh)) {
            page = hdService.totalPageSearch(makh);
        }
        if (page < 1) {
            page = 1;
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("list", hdService.searchPaging(makh, page));
        mav.addObject("totalPage", hdService.totalPageSearch(makh));
        mav.addObject("currentPage", page);
        mav.addObject("keyword", makh);
        mav.setViewName("billsList");
        return mav;
    }

    @RequestMapping(value = "/bill/{id}", method = RequestMethod.GET)
    public ModelAndView getAllCTHD(@PathVariable("id") int mahd) {
        ModelAndView model = new ModelAndView();
        model.addObject("kh", khService.getOne(hdService.getOne(mahd).getMakh()));
        model.addObject("hoadon", hdService.getOne(mahd));
        model.addObject("list", hdService.getAllCTHD(mahd));
        model.setViewName("detailBill");
        return model;
    }

}
