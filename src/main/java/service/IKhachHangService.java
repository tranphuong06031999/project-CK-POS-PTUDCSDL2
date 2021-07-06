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

    public String addCustomer(KhachHangEntity kh);

    public String updateCustomer(KhachHangEntity kh);

    public String payIn(int makh, int tiennap);

    public ArrayList<KhachHangEntity> getAllPaging(int page);

    public int totalPage();

    public ArrayList<KhachHangEntity> searchPaging(String keyword, int page);

    public int totalPageSearch(String keyword);
}
