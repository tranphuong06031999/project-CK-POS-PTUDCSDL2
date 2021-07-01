/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import entity.GioHangEntity;
import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.IGioHangService;

/**
 *
 * @author NguyenThanhDat
 */
@RestController
public class GioHangApi {

    @Autowired
    IGioHangService service;

    @RequestMapping(value = "/api/cart", method = RequestMethod.GET)
    public ArrayList<GioHangEntity> showCart() {
        return service.getAll();
    }

    @RequestMapping(value = "/api/cart/add", method = RequestMethod.POST)
    public HashMap<String, String> createCart(@RequestBody GioHangEntity gh) {
        return service.createCart(gh);
    }

    @RequestMapping(value = "/api/cart/delete", method = RequestMethod.DELETE)
    public HashMap<String, String> deleteCart(@RequestBody GioHangEntity gh) {
        return service.deleteCart(gh.getMagiohang());
    }
}
