/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entity.HoaDonEntity;
import java.util.ArrayList;

/**
 *
 * @author NguyenThanhDat
 */
public interface IHoaDonRepository {

    public ArrayList<HoaDonEntity> findAll();

    public ArrayList<HoaDonEntity> search(int makh);

    public HoaDonEntity findOne(int mahd);

    public int count();

    public ArrayList<HoaDonEntity> paging(int page);
}
