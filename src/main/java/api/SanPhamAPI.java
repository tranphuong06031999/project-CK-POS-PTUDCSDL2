/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

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
public class SanPhamAPI {
    
    @Autowired
    private ISanPhamService sanPhamService;
    
//    //Tìm kiếm sản phẩm
//    @RequestMapping(value = "/api/product/search", method = RequestMethod.GET)
//    public ModelAndView searchCustomer(@RequestParam("keyword") String keyword) {
//        return new ModelAndView("searchProduct", "productList" ,sanPhamService.searchSanPham(keyword));
////        return sanPhamService.searchSanPham(keyword);
//    }
    
//    //Get view addproduct
//    @RequestMapping(value = "/api/product/add")
//    public ModelAndView addNewProduct() {
//        return new ModelAndView("addNewProduct");
//    }
    
    //Thêm sản phẩm mới
    @RequestMapping(value = "/api/product/add", method = RequestMethod.POST)
    public HashMap<String, String> addProduct(@RequestBody SanPhamEntity sp) {
        return sanPhamService.addSanPham(sp);
    }
    
//    //Get view editproduct
//    @RequestMapping(value = "/api/product/edit/{masp}")
//    public ModelAndView editProduct(@PathVariable("masp") int masp) {
//        return new ModelAndView("editProduct", "sanpham", sanPhamService.findOne(masp));
//    }
    
    //Sửa một sản phẩm
    @RequestMapping(value = "/api/product/edit", method = RequestMethod.POST)
    public HashMap<String, SanPhamEntity> updateProduct(@RequestBody SanPhamEntity sp) {
        return sanPhamService.updateSanPham(sp);
    }
    
//    //GET view danh sách sản phẩm 
//    @RequestMapping(value = "/api/products-list", method = RequestMethod.GET)
//    public ModelAndView productList() {
//        return new ModelAndView("productList", "productList" ,sanPhamService.productList());
//    }
//    
//        public ArrayList<SanPhamEntity> productList() {
////        return sanPhamService.productList();
//        return new ModelAndView("productList", "productList" ,sanPhamService.productList());
//    }
}
