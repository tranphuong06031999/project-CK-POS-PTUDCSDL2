/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.ChiTietHoaDonEntity;
import entity.HoaDonEntity;
import java.util.ArrayList;

/**
 *
 * @author NguyenThanhDat
 */
public interface IHoaDonService {

    public ArrayList<HoaDonEntity> getAll();

    public ArrayList<ChiTietHoaDonEntity> getAllCTHD(int mahd);

    public ArrayList<HoaDonEntity> searchBill(int makh);

    public HoaDonEntity getOne(int mahd);

    public ArrayList<HoaDonEntity> getAllPaging(int page);

    public int totalPage();
}
