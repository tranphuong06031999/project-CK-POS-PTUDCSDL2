/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.KhachHangEntity;
import entity.SanPhamEntity;
import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import service.ISanPhamService;

/**
 *
 * @author Trần Đinh Phương
 */
@RestController
public class SanPhamController {

    @Autowired
    private ISanPhamService sanPhamService;

    //Tìm kiếm sản phẩm
    @RequestMapping(value = "/product/search", method = RequestMethod.GET)
    public ModelAndView searchProduct(@RequestParam("keyword") String keyword) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("list", sanPhamService.searchSanPham(keyword));
        mav.addObject("totalPage", 1);
        mav.addObject("currentPage", 1);
        mav.setViewName("productList");
        return mav;
    }

    //Get view addproduct
    @RequestMapping(value = "/product/add")
    public RedirectView addNewProduct(@ModelAttribute SanPhamEntity sp, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("message", sanPhamService.addSanPham(sp));
        return new RedirectView("/product");
    }

    @RequestMapping(value = "/product/update")
    public RedirectView updateProduct(@ModelAttribute SanPhamEntity sp, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("message", sanPhamService.updateSanPham(sp));
        return new RedirectView("/product");
    }

    //GET view danh sách sản phẩm 
    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public ModelAndView productList(@RequestParam(defaultValue = "1", name = "page", required = false) int page, @RequestParam(name = "message", required = false) String message) {
        if (page > sanPhamService.totalPage()) {
            page = sanPhamService.totalPage();
        }
        if (page < 1) {
            page = 1;
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("list", sanPhamService.getAllPaging(page));
        mav.addObject("totalPage", sanPhamService.totalPage());
        mav.addObject("currentPage", page);
        mav.addObject("message", message);
        mav.setViewName("productList");
        return mav;
    }
}
