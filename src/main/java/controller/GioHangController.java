/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.GioHangEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import service.IGioHangService;

/**
 *
 * @author NguyenThanhDat
 */
@RestController
public class GioHangController {

    @Autowired
    IGioHangService ghService;

    //Thêm khách hàng
    @RequestMapping(value = "/cart/add", method = RequestMethod.POST)
    public ModelAndView createCart(@ModelAttribute GioHangEntity gh) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", ghService.createCart(gh));
        mav.setViewName("redirect:/specifications/" + gh.getMasp());
        return mav;
    }

    //view cart
    @RequestMapping(value = "/cart/{makh}", method = RequestMethod.GET)
    public ModelAndView cartList(@PathVariable("makh") int makh, @RequestParam(name = "message", required = false) String message) {
        ModelAndView modelView = new ModelAndView();
        modelView.addObject("message", message);
        modelView.addObject("makh", makh);
        modelView.addObject("cartList", ghService.getAll(makh));
        modelView.addObject("totalPrice", ghService.totalPrice(makh));
        modelView.addObject("discount", ghService.discount(makh));
        modelView.addObject("khuyenmai", ghService.tongKhuyenMai(makh));
        modelView.addObject("chietkhau", ghService.chietKhau(makh));
        modelView.addObject("sale_khtt", ghService.tienGiamCuaKhachHangThanThiet(makh));
        modelView.setViewName("cartList");
        return modelView;
    }

    //Xóa sản phẩm khỏi giỏ hàng
    @RequestMapping(value = "/cart/delete/{makh}/{magiohang}", method = RequestMethod.GET)
    public ModelAndView deleteProductCart(@PathVariable("magiohang") int magiohang, @PathVariable("makh") int makh) {
        String url = "/cart/" + makh;
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", ghService.deleteCart(magiohang));
        mav.setViewName("redirect:" + url);
        return mav;
    }

    @RequestMapping(value = "/cart/thanhtoan/{makh}", method = RequestMethod.GET)
    public ModelAndView checkoutCart(@PathVariable("makh") int makh) {
//        ghService.checkoutCart(makh);
//        String url = "/cart/" + makh;
//        return new ModelAndView("redirect:" + url);   
        ModelAndView mav = new ModelAndView();
        String url = "/cart/" + makh;
        mav.addObject("message", ghService.checkoutCart(makh));
        mav.setViewName("redirect:" + url);
        return mav;
    }

}
