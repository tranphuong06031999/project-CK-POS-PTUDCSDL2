/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entity.GioHangEntity;
import java.util.ArrayList;

/**
 *
 * @author NguyenThanhDat
 */
public interface IGioHangRepository {

    public boolean create(GioHangEntity cart);

    public boolean delete(int id);

    public boolean update(int id, int price, int qty);

    public GioHangEntity findOne(int id);
    
    public int isExists(int masp);
    
    public ArrayList<GioHangEntity> findAll(int makh);
}
