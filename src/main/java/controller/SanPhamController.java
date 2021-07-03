/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.SanPhamEntity;
import java.util.ArrayList;
import java.util.HashMap;
import mapper.RequestPayinMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ModelAndView searchCustomer(@RequestParam("keyword") String keyword) {
        return new ModelAndView("searchProduct", "productList" ,sanPhamService.searchSanPham(keyword));
    }
    
    //Get view addproduct
    @RequestMapping(value = "/product/add")
    public ModelAndView addNewProduct() {
        return new ModelAndView("addNewProduct");
    }
    
    //Get view editproduct
    @RequestMapping(value = "/product/edit/{masp}")
    public ModelAndView editProduct(@PathVariable("masp") int masp) {
        return new ModelAndView("editProduct", "sanpham", sanPhamService.findOne(masp));
    }
    
    //GET view danh sách sản phẩm 
    @RequestMapping(value = "/products-list", method = RequestMethod.GET)
    public ModelAndView productList() {
        return new ModelAndView("productList", "productList" ,sanPhamService.productList());
    }
    
        //GET view danh sách sản phẩm 
    @RequestMapping(value = "/customers-list", method = RequestMethod.GET)
    public ModelAndView customersList() {
        return new ModelAndView("customersList", "productList" ,sanPhamService.productList());
    }
}
