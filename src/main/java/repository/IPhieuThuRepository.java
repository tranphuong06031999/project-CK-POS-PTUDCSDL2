/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entity.PhieuThuEntity;
import java.util.ArrayList;

/**
 *
 * @author NguyenThanhDat
 */
public interface IPhieuThuRepository {

    public boolean create(PhieuThuEntity pt);

    public ArrayList<PhieuThuEntity> findAll();

    public ArrayList<PhieuThuEntity> search(String keyword);

    public PhieuThuEntity findOne(int id);

    public int count();

    public ArrayList<PhieuThuEntity> paging(int page);
}
