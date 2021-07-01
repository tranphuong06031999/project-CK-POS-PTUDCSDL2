/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.GioHangEntity;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author NguyenThanhDat
 */
public interface IGioHangService {

    public HashMap<String, String> createCart(GioHangEntity cart);

    public HashMap<String, String> deleteCart(int magiohang);
    
    public ArrayList<GioHangEntity> getAll();
}
