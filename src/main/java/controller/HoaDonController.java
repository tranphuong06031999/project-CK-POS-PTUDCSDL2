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
import service.IHoaDonService;

/**
 *
 * @author NguyenThanhDat
 */
@RestController
public class HoaDonController {

    @Autowired
    private IHoaDonService hdService;

    @RequestMapping(value = "/bill", method = RequestMethod.GET)
    public ModelAndView getAll(@RequestParam(defaultValue = "1", name = "page", required = false) int page) {
        if (page > hdService.totalPage()) {
            page = hdService.totalPage();
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("list", hdService.getAllPaging(page));
        mav.addObject("totalPage", hdService.totalPage());
        mav.addObject("currentPage", page);
        mav.setViewName("billsList");
        return mav;
    }

    @RequestMapping(value = "/bill/search", method = RequestMethod.GET)
    public ModelAndView search(@RequestParam("makh") int makh) {
        return new ModelAndView("billsList", "list", hdService.searchBill(makh));
    }

    @RequestMapping(value = "/bill/{id}", method = RequestMethod.GET)
    public ModelAndView getAllCTHD(@PathVariable("id") int mahd) {
        ModelAndView model = new ModelAndView();
        model.addObject("total", hdService.getOne(mahd).getTongtien());
        model.addObject("list", hdService.getAllCTHD(mahd));
        model.setViewName("detailBill");
        return model;
    }

}
