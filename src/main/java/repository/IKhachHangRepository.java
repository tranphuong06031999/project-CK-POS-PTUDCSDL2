/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entity.KhachHangEntity;
import java.util.ArrayList;

/**
 *
 * @author NguyenThanhDat
 */
public interface IKhachHangRepository {

    public ArrayList<KhachHangEntity> findAll();

    public KhachHangEntity findOne(int id);
    
    public boolean findOneByPhone(String sodienthoai);

    public ArrayList<KhachHangEntity> search(String keyword);

    public boolean create(KhachHangEntity kh);

    public boolean update(KhachHangEntity kh);

    public boolean updateSodu(int makh, int tiennap);
}
