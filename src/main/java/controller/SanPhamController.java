/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.SanPhamEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
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
    public ModelAndView searchProduct(@RequestParam("keyword") String keyword, @RequestParam(defaultValue = "1", name = "page", required = false) int page) {
        if (page > sanPhamService.totalPageSearch(keyword)) {
            page = sanPhamService.totalPageSearch(keyword);
        }
        if (page < 1) {
            page = 1;
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("list", sanPhamService.searchPaging(keyword, page));
        mav.addObject("totalPage", sanPhamService.totalPageSearch(keyword));
        mav.addObject("currentPage", page);
        mav.addObject("keyword", keyword);
        mav.setViewName("productList");
        return mav;
    }

    //Get view addproduct
    @RequestMapping(value = "/product/add")
    public ModelAndView addNewProduct(@ModelAttribute SanPhamEntity sp) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", sanPhamService.addSanPham(sp));
        mav.setViewName("redirect:/product");
        return mav;
    }

    @RequestMapping(value = "/product/update")
    public ModelAndView updateProduct(@ModelAttribute SanPhamEntity sp) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", sanPhamService.updateSanPham(sp));
        mav.setViewName("redirect:/product");
        return mav;
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
