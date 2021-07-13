/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.QuyCachEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import service.IQuyCachService;
import service.ISanPhamService;

/**
 *
 * @author NguyenThanhDat
 */
@RestController
public class QuyCachController {

    @Autowired
    IQuyCachService qcService;

    @Autowired
    ISanPhamService spService;

    @RequestMapping(value = "/specifications/{masp}", method = RequestMethod.GET)
    public ModelAndView getAll(@PathVariable("masp") int masp, @RequestParam(name = "message", required = false) String message) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("masp", masp);
        mav.addObject("product", spService.findOne(masp));
        mav.addObject("list", qcService.getAllByMaSP(masp));
        mav.addObject("message", message);
        mav.setViewName("specificationsList");
        return mav;
    }

    @RequestMapping(value = "/specifications/{masp}/add", method = RequestMethod.POST)
    public ModelAndView addSpecifications(@ModelAttribute QuyCachEntity qc, @PathVariable("masp") int masp) {
        ModelAndView mav = new ModelAndView();
        qc.setMasp(masp);
        mav.addObject("message", qcService.addSpecifications(qc));
        mav.setViewName("redirect:/specifications/" + masp);
        return mav;
    }

    @RequestMapping(value = "/specifications/{masp}/update", method = RequestMethod.POST)
    public ModelAndView updateSpecifications(@ModelAttribute QuyCachEntity qc, @PathVariable("masp") int masp) {
        ModelAndView model = new ModelAndView();
        model.addObject("message", qcService.updateSpecifications(qc));
        model.setViewName("redirect:/specifications/" + masp);
        return model;
    }

    @RequestMapping(value = "/specifications/{masp}/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteSpecifications(@PathVariable("id") int id, @PathVariable("masp") int masp) {
        ModelAndView model = new ModelAndView();
        model.addObject("message", qcService.deleteSpecifications(id));
        model.setViewName("redirect:/specifications/" + masp);
        return model;
    }
}
