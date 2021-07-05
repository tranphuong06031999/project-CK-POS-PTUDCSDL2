/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.GioHangEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
    public RedirectView createCart(@ModelAttribute GioHangEntity gh,RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("message",ghService.createCart(gh));
        return new RedirectView("/product");
    }
}
