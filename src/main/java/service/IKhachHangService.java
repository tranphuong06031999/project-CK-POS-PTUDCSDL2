/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.KhachHangEntity;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author NguyenThanhDat
 */
public interface IKhachHangService {

    public ArrayList<KhachHangEntity> getAll();

    public KhachHangEntity getOne(int id);

    public ArrayList<KhachHangEntity> searchCustomer(String keyword);

    public HashMap<String, String> addCustomer(KhachHangEntity kh);

    public HashMap<String, String> updateCustomer(KhachHangEntity kh);

    public HashMap<String, String> payIn(int makh, int tiennap);
}
