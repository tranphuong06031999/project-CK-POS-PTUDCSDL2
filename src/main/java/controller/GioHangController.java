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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
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
    public RedirectView createCart(@ModelAttribute GioHangEntity gh, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("message", ghService.createCart(gh));
        return new RedirectView("/product");
    }

    //view cart
    @RequestMapping(value = "/cart/{makh}", method = RequestMethod.GET)
    public ModelAndView cartList(@PathVariable("makh") int makh, @RequestParam(name = "message", required = false) String message) {
        ModelAndView modelView = new ModelAndView();
        modelView.addObject("message", message);
        modelView.addObject("cartList", ghService.getAll(makh));
        modelView.addObject("totalPrice", ghService.totalPrice(makh));        
        modelView.addObject("discount", ghService.discount(makh));
        modelView.setViewName("cartList");
        return modelView;
    }

    //Thêm số lượng
    @RequestMapping(value = "/cart/update", method = RequestMethod.POST)
    public RedirectView updateCart(@ModelAttribute GioHangEntity gh) {
        ghService.updateCart(gh);
        String url = "/cart/" + gh.getMakh();
        return new RedirectView(url);
    }

    //Xóa sản phẩm khỏi giỏ hàng
    @RequestMapping(value = "/cart/delete/{makh}/{magiohang}", method = RequestMethod.GET)
    public RedirectView deleteProductCart(@PathVariable("magiohang") int magiohang, @PathVariable("makh") int makh, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("message", ghService.deleteCart(magiohang));
        String url = "/cart/" + makh;
        return new RedirectView(url);
    }
}
